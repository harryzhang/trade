/*
 * Powered By zhangyunhua
 * Web Site:  
 * Since 2008 - 2015
 */

package com.redpack.wallet.service;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.redpack.account.dao.IUserDao;
import com.redpack.account.faced.IUserService;
import com.redpack.account.model.UserDo;
import com.redpack.base.exception.BusinessException;
import com.redpack.base.result.IResult;
import com.redpack.base.result.ResultSupport;
import com.redpack.customer.ICustomerService;
import com.redpack.grade.model.GroupDo;
import com.redpack.grade.model.GroupUserDo;
import com.redpack.group.IGroupService;
import com.redpack.group.IGroupUserService;
import com.redpack.sms.ISmsService;
import com.redpack.userWaiting.dao.IUserWaitingDao;
import com.redpack.userWaiting.model.UserWaitingDo;
import com.redpack.wallet.IWalletService;
import com.redpack.wallet.dao.IWalletDao;
import com.redpack.wallet.model.WalletDo;

/**
 * @author zhangyunhua
 * @version 1.0
 * @since 1.0
 */


@Service("walletService")
public class WalletServiceImpl implements IWalletService {

	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired	
    private IWalletDao  walletDao;
	
	@Autowired	
    private IUserDao  userDao;
	
	@Autowired	
    private IGroupService groupService;
	
	@Autowired	
    private IUserService userService;
	
	@Autowired	
    private IGroupUserService groupUserService;
	
	@Autowired	
    private ICustomerService customerService;
	
	@Autowired	
    private IUserWaitingDao userWaitingDao;
	
	@Autowired	
    private ISmsService smsService;

	@Override
	public List<Map> selectUserSk(Long skUserId,Integer skStatus) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("skUserId", skUserId);
		param.put("skStatus", skStatus);
		return walletDao.selectUserSk(param);
	}

	@Override
	public List<Map> selectUserFk(Long fkUserId,Integer fkStatus) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("fkUserId", fkUserId);
		param.put("fkStatus", fkStatus);
		return walletDao.selectUserFk(param);
	}

	/**
	 * 更新付款记录状态
	 * 
	 */
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	@Override
	public IResult confirmFK(Long optId, long recordId) {
		WalletDo newWalletDo = new WalletDo();
		newWalletDo.setId(recordId);
		newWalletDo.setFkOptUserId( optId);
		newWalletDo.setFkUpdateTime(new Date(System.currentTimeMillis()));
		newWalletDo.setFkStatus(1);
		walletDao.updateWalletById(newWalletDo);
		return ResultSupport.buildResult(0, "成功");
	}

	/**
	 * 1. 是否最后一条收款 , 是-->更新用户状态成正式用户
	 * 2. 判断是组里最后一个用户收款确认, 分盘
	 */
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	@Override
	public IResult confirmSK(Long optId, long recordId) {
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("id", recordId);
		paraMap.put("valid", 1);
		List<WalletDo>  walletList = walletDao.selectWallet(paraMap);
		if(null == walletList || walletList.size()<1){
			return ResultSupport.buildResult(1, "无效参数,收款记录无效");
		}
		
		WalletDo currRec = walletList.get(0);
		if(currRec.getSkStatus() != 0 ){
			return ResultSupport.buildResult(1, "当前记录已被确认收款过");
		}
		
		//更新状态 
		currRec.setSkOptUserId(optId);
		currRec.setSkUpdateTime(new Date(System.currentTimeMillis()));
		currRec.setSkStatus(1);
		walletDao.updateWalletById(currRec);
		
		paraMap.clear();
		paraMap.put("fkUserId", currRec.getFkUserId());
		paraMap.put("fkStatus", 0);
		paraMap.put("valid", 1);
		paraMap.put("groupName", currRec.getGroupName());
		List<WalletDo>  walletAllList = walletDao.selectWallet(paraMap);
		// last confirm sk record
		if(null == walletAllList || walletAllList.size()<1){
			UserDo tempUser = new UserDo();
			tempUser.setId(currRec.getFkUserId());
			tempUser.setStatus(1);
			userDao.updateUser(tempUser);
		}
		
		//last confirm in group
		paraMap.clear();
		
		paraMap.put("groupName", currRec.getGroupName());
		paraMap.put("skStatus", 1);
		paraMap.put("fkStatus", 1);
		paraMap.put("valid", 1);
		
		//付款成功人组所在付款状态
		List<WalletDo>  walletGroupAllList =walletDao.selectWallet(paraMap);
		
		paraMap.clear();
		paraMap.put("groupName", currRec.getGroupName());
		List<GroupDo> groupList = groupService.selectGroup(paraMap);
		if(null == groupList || groupList.size()<1){
			throw new BusinessException("找不到组");
		}
		
		GroupDo fenpanGroup = groupList.get(0);
		if("0".equals(String.valueOf(fenpanGroup.getStatus()))){ 
			//已经分配的更新状态退出
			return ResultSupport.buildResult(0,"成功");
		}
		
		//分盘 , 封号的人付款记录 需要处理  valid
		Set<Long> fkSet = new HashSet<Long>();
		for(WalletDo wDo : walletGroupAllList){
			fkSet.add(wDo.getFkUserId());
		}
		
		//有27个付款人确认过了，并且不存在有未确认的记录就可以分盘了
		if(fkSet.size()>=27){
			
			 List<WalletDo> unrecordList = walletDao.selectUnConfirmRecord(currRec.getGroupName());
			if(unrecordList == null || unrecordList.size()==0){
	            //出局
				if("A".equals(fenpanGroup.getNetworkGroup())){
					groupService.applyGroupGrade(fenpanGroup.getGroupRootUser(),fenpanGroup);
				}
				if("B".equals(fenpanGroup.getNetworkGroup())){
					groupService.applyGroupGradeForB(fenpanGroup.getGroupRootUser(),fenpanGroup);
				}				
				processWaitingUser(currRec.getGroupName());
			}
			
		}
		return ResultSupport.buildResult(0, "成功");
	}

	/**
	 * 处理等待分盘的用户
	 * @param groupName
	 */
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void processWaitingUser(String groupName){
		
		Map<String,Object> paraMap = new HashMap<String,Object>();
		//String groupName="GA2014031600002";
		//处理等待排队的用户
		paraMap.clear();
		paraMap.put("groupName", groupName);
		paraMap.put("status", "1");
		List<UserWaitingDo> listWaitDo = userWaitingDao.selectUserWaiting(paraMap);
		for(UserWaitingDo waitUser : listWaitDo){
			UserDo waitUserDo = userService.getById(waitUser.getUserId());
//			String waitGroupName = userService.getGroupByRefUserId(waitUserDo.getReferrerId(),"A");
//			userService.fengPeiWaitUser(waitUser.getUserId(), waitUserDo.getReferrerId(), waitGroupName);
//			userWaitingDao.deleteByUserId(waitUser.getUserId());
		}
	}
	
	@Override
	public List<WalletDo> selectWallet(Map<String, Object> paraMap) {
		return walletDao.selectWallet(paraMap);
	}

	/**
	 * 判断是否存在部分已经确认付款的了
	 * @param onePersonWalletList 所有的待付款记录
	 * @return
	 */
	private boolean haveConfirm(List<WalletDo> onePersonWalletList){
		for(WalletDo wd: onePersonWalletList){
			if(wd.getFkStatus() == 1 || wd.getSkStatus() == 1){
				return true;
			}
		}
		return false;
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void replaceUser(WalletDo walletDo) {
		
		UserDo  replaceUserDo  =  userDao.getById(walletDo.getFkUserId());
		
		//查找这个人的这个组的有效打款记录
		Map<String, Object> paraMap = new HashMap<String,Object>();
		paraMap.put("valid", 1);
		paraMap.put("fkUserId", walletDo.getFkUserId());
		paraMap.put("groupName", walletDo.getGroupName());
		List<WalletDo> onePersonWalletList = walletDao.selectWallet(paraMap);
		//判断是否存在部分已经确认付款的了
		if(haveConfirm(onePersonWalletList)){
			//存在部分确认的用户不能被失效
			return;
		}
				
		//查找替换对象
		UserWaitingDo waitRecordDo = userWaitingDao.selectUserWaitingByGroupName(walletDo.getGroupName());
		if(null == waitRecordDo ){ //没有排队，失效，直接退出
			//失效
			customerService.setUnvalidUser(walletDo.getFkUserId());
			//短信提示，过期封号
			try{
				smsService.sendMessage(replaceUserDo.getUserName(), "你已过期，账号被封");
			}catch(Exception e){
				System.out.println("==================短信发送失败===================");
				e.printStackTrace();
			}
			return;
		}
		UserDo waitUserDo = userDao.getById(waitRecordDo.getUserId());
		//生成替换人的费用记录
		generateRepalceUserWallet(onePersonWalletList,waitUserDo);
		
		//替换
		doRepalce(walletDo,waitUserDo);		
		//删除等待记录
		userWaitingDao.deleteByUserId(waitRecordDo.getUserId());
		
		//失效
		customerService.setUnvalidUser(walletDo.getFkUserId());
		
		//发短信提示打款
		try{
			smsService.sendMessage(waitUserDo.getUserName(), "您匹配成功，请登录系统，查看 打 款 信息");
		}catch(Exception e){
			System.out.println("==================短信发送失败===================");
			e.printStackTrace();
		}
		
	}


	/**
	 * 生成替换人的费用记录
	 * @param onePersonWalletList
	 * @param userId
	 */
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	private void generateRepalceUserWallet(List<WalletDo> onePersonWalletList,
			UserDo waitingUser) {
		Date currDate = new Date(System.currentTimeMillis());
		//复制的记录
		for(WalletDo wd: onePersonWalletList){
			if(wd.getType() == 1 ){//推荐人
				wd.setSkUserId(waitingUser.getReferrerId());
				walletDao.addWallet(wd);
			}
			wd.setId(null);
			wd.setFkUserId(waitingUser.getId());
			wd.setCreateTime(currDate);
			walletDao.addWallet(wd);
		}
	}

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void doRepalce(WalletDo walletDo, UserDo waitUserDo) {
		
		//修改groupUser
		Map<String,Object> groupPara = new HashMap<String,Object>();
		groupPara.put("groupName", walletDo.getGroupName());
		groupPara.put("userId", walletDo.getFkUserId());
		groupPara.put("status", "T");
	
		List<GroupUserDo> replaceGroupList = groupUserService.selectGroupUser(groupPara);
		if(null == replaceGroupList || replaceGroupList.size()<0){
			System.out.println("==============替换用户时，找不到组====================");
			throw new RuntimeException("替换用户时，找不到组");
		}
		GroupUserDo currGroupUserDo = replaceGroupList.get(0);
		currGroupUserDo.setUserId(waitUserDo.getId());
		groupUserService.updateGroupUserById(currGroupUserDo);
		
		// 修改排队人的用户状态
		waitUserDo.setStatus(1);
		userDao.updateUser(waitUserDo);
	}

	@Override
	public List<WalletDo> selectOverTimePerson(Map<String, Object> paraMap) {
		return walletDao.selectOverTimePerson(paraMap);
	}

	@Override
	public void updateWalletById(WalletDo newWalletDo) {
		walletDao.updateWalletById(newWalletDo);		
	}


}

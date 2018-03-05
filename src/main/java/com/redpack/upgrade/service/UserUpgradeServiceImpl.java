
/*
 * Powered By zhangyunhua
 * Web Site:  
 * Since 2008 - 2015
 */

package com.redpack.upgrade.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.redpack.account.faced.IUserInfoService;
import com.redpack.account.faced.IUserService;
import com.redpack.account.model.UserDo;
import com.redpack.account.model.UserInfoDo;
import com.redpack.grade.IGradeFeeService;
import com.redpack.grade.model.GradeFeeDo;
import com.redpack.upgrade.IUserUpgradeService;
import com.redpack.upgrade.dao.IUserUpgradeDao;
import com.redpack.upgrade.model.UserUpgradeDo;

/**
 * @author zhangyunhua
 * @version 1.0
 * @since 1.0
 */


@Service("userUpgradeService")
public class UserUpgradeServiceImpl implements IUserUpgradeService {

	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	IUserService  userService;
	
	@Autowired
	IUserInfoService  userInfoService;
	
	@Autowired	
    private IUserUpgradeDao  userUpgradeDao;
	
	@Autowired
	IGradeFeeService gradeFeeService;

	/**
	 * 根据ID 查询
	 * @parameter id
	 */
	public UserUpgradeDo getById(Long id){
	  return userUpgradeDao.getById(id);
	}
	
	/**
	 *根据条件查询列表
	 */
	public List<UserUpgradeDo> selectUserUpgrade(Map<String,Object> parameterMap){
		return userUpgradeDao.selectUserUpgrade(parameterMap);
	}
	
	/**
	 * 更新
	 */
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public int  updateUserUpgradeById(UserUpgradeDo newUserUpgradeDo){
		return userUpgradeDao.updateUserUpgradeById(newUserUpgradeDo);
	}
	
	/**
	 * 新增
	 */
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public int addUserUpgrade(UserUpgradeDo newUserUpgradeDo){
		return userUpgradeDao.addUserUpgrade(newUserUpgradeDo);
	}
	
	/**
	 * 删除
	 */
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public int deleteById(int id){
		return userUpgradeDao.deleteById(id);
	}

	/**
	 * 
	 * @return
	 */
	private UserDo getReceiver(UserDo applyUser,int nextGrade){
		userService.getAllParent(applyUser);
		UserDo parent = applyUser.getParentDo();;
		//找到对应的层级
		for(int initGrade = 2 ; initGrade < nextGrade ;initGrade++  ){
			if(null == parent){
				throw new RuntimeException("找不到相应的上级");
			}
			parent = parent.getParentDo();
		}
		
		//判断层级上的parent是否符合条件， 不符合继续往上找
		while(parent != null && parent.getGrade()<nextGrade){
			parent = parent.getParentDo();
		}
		
		if(null == parent){
			throw new RuntimeException("找不到相应的上级");
		}
		return parent;
		
	}
	
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	@Override
	public UserUpgradeDo applyUpgrade(long userId) {
		
		//获取申请人
		UserDo applyUser = userService.getById(userId);
		int currentGrade = applyUser.getGrade();
		
		/*
		//获取下一级
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("beforeUpgrade", currentGrade);
		List<GradeFeeDo> gradeFeeList = gradeFeeService.selectGradeFee(parameterMap);
		int nextGrade = 0;
		if(gradeFeeList == null || gradeFeeList.size()<1){
			throw new RuntimeException("找不到对应的升级规则");
		}
		GradeFeeDo gradeFee = gradeFeeList.get(0);
		nextGrade = gradeFee.getAfterUpgrade();
		//end 获取下一级
		
		//获取接受人
		UserDo receiverUser = this.getReceiver(applyUser,nextGrade);
		*/
		
		//创建申请记录
		UserInfoDo applyUserInfo = userInfoService.getByUserId(userId);
		//UserInfoDo receiverUserInfo = userInfoService.getById(receiverUser.getId());
		
		UserUpgradeDo newUserUpgradeDo = new UserUpgradeDo();
		newUserUpgradeDo.setAccountExpend(applyUserInfo.getWeixiNumber());
		//newUserUpgradeDo.setAccountReceive(receiverUserInfo.getWeixiNumber());
		//newUserUpgradeDo.setAfterUpgrade(nextGrade);
		newUserUpgradeDo.setBeforeUpgrade(currentGrade);
		//newUserUpgradeDo.setPaymentMethod(value);
		//newUserUpgradeDo.setReceiveUser(receiverUser.getId());
		//newUserUpgradeDo.setUpgradeAmount(gradeFee.getGradeAmount());
		newUserUpgradeDo.setUpgradeDate(new Date());
		newUserUpgradeDo.setUserId(userId);
		newUserUpgradeDo.setStatus("0");
		//保存申请记录
		userUpgradeDao.addUserUpgrade(newUserUpgradeDo);
		return newUserUpgradeDo;
	}

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	@Override
	public void updateUpgradeStatusById(String upgradeId, String newStatus) {
		UserUpgradeDo newUserUpgradeDo = new UserUpgradeDo();
		Long upId = Long.parseLong(upgradeId);
		newUserUpgradeDo.setId(upId);
		newUserUpgradeDo.setStatus(newStatus);
		int result = userUpgradeDao.updateUpgradeStatus(newUserUpgradeDo);
		
		
		UserUpgradeDo upgradeDo =  userUpgradeDao.getById(upId);
		if("1".equals(newStatus)){//通过后更新申请人的当期等级
			userService.updateUserGradeById(upgradeDo.getUserId(),upgradeDo.getAfterUpgrade());
		}
		if(result<1){
			throw new RuntimeException("操作失败");
		}
		
	}

	@Override
	public List<UserUpgradeDo> selectUpgradeAuditList(
			Map<String, Object> parameterMap) {
		return userUpgradeDao.selectUpgradeAuditList(parameterMap);
	}

	@Override
	public Map<String, Object> selectLevelAmount(Long receiveUser, int level) {
	    int status = 1;
		return userUpgradeDao.selectLevelAmount(receiveUser, level,1);
	}

	/**
	 * 进入B网的 parent 手机号是否存在有效
	 * @param refMobile
	 * @return
	 */
	@Override
	public Map checkBNetWorkParent(String refMobile) {
		return userUpgradeDao.checkBNetWorkParent(refMobile);
	}

	/**
	 * 进入B网的 parent 手机号是否存在有效
	 * @param refMobile
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void applyUpgradeBNetWork(long userId, Long refUserId, String applyGroupName) {
//		userService.applyB(userId, refUserId, applyGroupName);
	}
	

}

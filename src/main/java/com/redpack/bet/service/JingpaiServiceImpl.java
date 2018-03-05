/*
 * Powered By  huangzl QQ: 272950754
 * Web Site:  
 * Since 2008 - 2016
 */

package com.redpack.bet.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.SchedulingTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.redpack.account.dao.IUserAccountDetailDao;
import com.redpack.account.dao.IUserAccountIncomeDao;
import com.redpack.account.dao.IUserDao;
import com.redpack.account.faced.IBizUserAccountService;
import com.redpack.account.model.BizUserAccountDo;
import com.redpack.account.model.UserDo;
import com.redpack.base.result.IResult;
import com.redpack.base.result.ResultSupport;
import com.redpack.bet.IJingpaiService;
import com.redpack.bet.dao.IJingpaiDao;
import com.redpack.bet.dao.IUserJingpaiDao;
import com.redpack.bet.model.JingpaiDo;
import com.redpack.bet.model.UserJingpaiDo;
import com.redpack.constant.AccountMsg;
import com.redpack.constant.WebConstants;
import com.redpack.member.dao.IMemberDao;

/**
 * @author  huangzl QQ: 272950754
 * @version 1.0
 * @since 1.0
 */


@Service("jingpaiService")
public class JingpaiServiceImpl implements IJingpaiService {

	private final Logger logger = Logger.getLogger(this.getClass());
	private Random rand = new Random();
	
	@Autowired
    private IJingpaiDao  jingpaiDao;
	
	@Autowired
    private IUserAccountIncomeDao  userAccountIncomeDao;
	
	@Autowired
	private IUserAccountDetailDao  userAccountDetailDao;
	
	@Autowired
	private IMemberDao memberDao;
	
	
	@Autowired
	private IUserDao userDao;
	
	@Autowired
    private IUserJingpaiDao  userJingpaiDao;
	
	@Autowired
	private IBizUserAccountService bizUserAccountService;
	
	@Autowired
	SchedulingTaskExecutor taskExecutor;
	
	
	
	/**
	 * 根据ID 查询
	 * @parameter id
	 */
	@Override
	public JingpaiDo getById(int id){
	  return jingpaiDao.getById(id);
	}
	
	/**
	 *根据条件查询列表
	 */
	@Override
	public List<JingpaiDo> selectJingpai(Map<String,Object> parameterMap){
		return jingpaiDao.selectJingpai(parameterMap);
	}
	
	/**
	 * 更新
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public IResult<?> updateJingpaiById(JingpaiDo newJingpaiDo){
		logger.debug("updateJingpai(JingpaiDo: "+newJingpaiDo);
		int i =jingpaiDao.updateJingpaiById(newJingpaiDo);
		if(i<1){
			return ResultSupport.buildResult(1, newJingpaiDo+"更新失败");
		}
		IResult<Integer> result = ResultSupport.buildResult(0);
		result.setModel(Integer.valueOf(i));
		return result;
	}
	
	/**
	 * 新增
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public IResult<?> addJingpai(JingpaiDo newJingpaiDo){
		logger.debug("addJingpai: "+newJingpaiDo);
		int i =jingpaiDao.addJingpai(newJingpaiDo);
		if(i<1){
			return ResultSupport.buildResult(1, newJingpaiDo+"新增失败");
		}
		IResult<Integer> result = ResultSupport.buildResult(0);
		result.setModel(Integer.valueOf(i));
		return result;
	}
	
	/**
	 * 删除
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public IResult<?> deleteById(int id){
		logger.debug("deleteByIdJingpai:"+id);
		int i =jingpaiDao.deleteById(id);
		if(i<1){
			return ResultSupport.buildResult(1, id+"删除失败");
		}
		IResult<Integer> result = ResultSupport.buildResult(0);
		result.setModel(Integer.valueOf(i));
		return result;
	} 

	@Override
	public List<Map<String, Object>> selectGoodsJingpai(Map param) {
		// TODO Auto-generated method stub
		return jingpaiDao.selectGoodsJingpai(param);
	}

	//同步竞拍
	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public synchronized BigDecimal userJingpai(JingpaiDo jpDo, long userId) {
		// TODO Auto-generated method stub
		
		//更新竞拍信息
		JingpaiDo jpNowDo = new JingpaiDo();
		
		UserDo userDo =userDao.getById(userId);
		
		//更新竞拍
		jpNowDo.setId(jpDo.getId());
		jpNowDo.setLastPrice(new BigDecimal("0.01"));
		jpNowDo.setUserTime(new Date());
		jpNowDo.setLastUser(userDo.getName());
		jpNowDo.setUserId((int)userId);
		int i =jingpaiDao.updateJingPaiPerson(jpNowDo);
		if(i<1){
			throw new RuntimeException("竞拍失败");
		}
				
			
		// 1:减少前台帐户金额
		// 增加用户证券
		BigDecimal lastPrice = jpDo.getLastPrice();
		BizUserAccountDo bizUserAccountDo = new BizUserAccountDo();
		bizUserAccountDo.setAmount(lastPrice.negate());
		bizUserAccountDo.setUserId(userId);
		bizUserAccountDo.setAccountType(WebConstants.RMB_ACCOUNT);
		int result = bizUserAccountService.updateUserAmountById(bizUserAccountDo, AccountMsg.type_12);
		if(result<1){
			throw new RuntimeException("竞拍余额不足");
		}
		
		//插入用户竞拍信息
		UserJingpaiDo ujpDo = new UserJingpaiDo();
		ujpDo.setJingpaiPrice(lastPrice);
		ujpDo.setJingpaiTime(new Date());
		ujpDo.setUserId((int)userId);
		ujpDo.setJingpaiId(jpDo.getId());
		userJingpaiDao.addUserJingpai(ujpDo);
		
		return lastPrice;
	}

	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void betSuccess(JingpaiDo jpDo) {

		//最后竞标人出价
		BigDecimal lastPrice = jpDo.getLastPrice();
		//系统定义的竞拍上限
		BigDecimal endPrice = jpDo.getEndPrice();
		
		//判断是否超60秒, 超时最后一位成功
		long diff = System.currentTimeMillis() - jpDo.getUserTime().getTime();
		if(diff>60*1000){
			logger.warn("竞拍成功:最后竞拍时间>60*1000:"+diff+" 用户竞拍时间："+jpDo.getUserTime());
			jpSuccess(jpDo);
			return;
		}
		
		//达到最高限价
		if(lastPrice.compareTo(endPrice) >=0 ){
			logger.warn("竞拍成功:达到最高限价");
			jpSuccess(jpDo);
			return;
		}
		
	
		
	}

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	private void jpSuccess(JingpaiDo jpDo) {

		if(null == jpDo.getUserId()){
			return;
		}
		
		UserDo lastuser = userDao.getById(Long.valueOf(jpDo.getUserId()));
		jpDo.setStatus(2);
		jpDo.setLastUser(lastuser.getName());
		jingpaiDao.updateJingpaiById(jpDo);
		
		Map jingpai = new HashMap();
		jingpai.put("userId", jpDo.getUserId());
		jingpai.put("price",jpDo.getLastPrice());
		jingpai.put("goodsId", jpDo.getGoodsId());
		jingpai.put("createTime", new Date());
		jingpaiDao.addJingpaiSuccess(jingpai);
	}

	/**
	 * 系统自动竞拍
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void autoBet(final JingpaiDo jpDo) {

		Integer userId = jpDo.getUserId();
		long diff = System.currentTimeMillis() - jpDo.getUserTime().getTime();
		if(diff<20*1000){ //20秒内系统不自动拍
			logger.info("没有达到20秒不需系统拍");
			return;
		}
		
		//最后竞拍的人
		int status = 0 ;
		if(userId != null){
			UserDo userDo = userDao.getById(Long.valueOf(userId));
			status = userDo.getStatus()==null? 0:  userDo.getStatus().intValue();
		}
		
		if(4 == status){ //系统拍的不继续自动拍
			logger.info("平台用户拍的，不用继续拍");
			return;
		}
		
		//系统自动拍
		Map<String,Object> jpMap = new HashMap<String,Object>();
		jpMap.put("status",4);
		List<UserDo> sysUserList = userDao.getAllUser(jpMap);
		if(sysUserList.isEmpty()){
			System.out.println("没有配置系统自动竞拍人员");
			return;
		}
		
		int sysUseCnt = sysUserList.size();
		int randInt = rand.nextInt(sysUseCnt); //生成0-minCount以内的随机数
		final UserDo uDo = sysUserList.get(randInt);
		
		taskExecutor.execute(new Runnable(){
			@Override
			public void run() {
				logger.info("发起平台用户自动拍");
				userJingpai(jpDo, uDo.getId());				
			}
		});
		
		return;	
		
	}

}

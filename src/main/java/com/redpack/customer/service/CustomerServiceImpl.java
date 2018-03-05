

package com.redpack.customer.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.redpack.account.dao.IUserDao;
import com.redpack.account.faced.IUserService;
import com.redpack.account.model.UserDo;
import com.redpack.base.result.IResult;
import com.redpack.customer.ICustomerService;
import com.redpack.group.dao.IGroupUserDao;
import com.redpack.userWaiting.dao.IUserWaitingDao;
import com.redpack.wallet.dao.IWalletDao;
import com.redpack.wallet.model.WalletDo;


@Service("customerService")
public class CustomerServiceImpl implements ICustomerService {

	private final Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private IUserService userService;
	
	@Autowired
	private IGroupUserDao groupUserDao;
	@Autowired
	private IUserDao userDao;
	
	@Autowired
	private IUserWaitingDao userWaitingDao;
	
	@Autowired
	private IWalletDao walletDao;
	
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	@Override
	public IResult setUnvalidUser(Long userId) {

		// 更新用户状态
		UserDo userDo = new UserDo();
		userDo.setId(userId);
		userDo.setStatus(2);
		int saveInt = userService.updateUser(userDo);

		// 判断用户是在分组树里，如果在更新分组树
		Map guParamMap = new HashMap();
		guParamMap.put("userId", userId);
		groupUserDao.delGroupUserByUserId(guParamMap);
		
		//判断用户是否在等待区，如果在等待区直接删除
		userWaitingDao.deleteByUserId(userId);
		
		//更新用户的付款记录表为不可用
		WalletDo walletDo = new WalletDo();
		walletDo.setFkUserId(userId);
		walletDo.setValid(0);
		walletDao.updateWalletByUserId(walletDo);
		
		return null;
	}

	@Override
	public void resetUser(Long userId, String changeName,String changePhone) {
		// TODO Auto-generated method stub
		
		UserDo oldUser = userDao.getById(userId);
		oldUser.setUpdateTime(new Date());
		oldUser.setRemark("客服用户替换操作，替换人：" + changePhone);
		userDao.updateUser(oldUser);
		
		// 更新用户状态
		UserDo userDo = new UserDo();
//		userDo.setId(userId);
		userDo.setUserName(changePhone);
		userDo.setName(changeName);
		userDo.setStatus(1);
		userDo.setReferrerId(oldUser.getReferrerId());
		userDo.setPassword("a8fdfd04ce6ae7179313122274295885");
		Long saveInt =userDao.saveUser(userDo);

		// 判断用户是在分组树里，如果在更新分组树ID为新用户
		Map guParamMap = new HashMap();
		guParamMap.put("userId", userId);
		guParamMap.put("status", "T");
		guParamMap.put("changeUserId", userDo.getId());
		groupUserDao.updateGroupUserByUserId(guParamMap);
		
		guParamMap.clear();
		guParamMap = new HashMap();
		guParamMap.put("parentId", userId);
		guParamMap.put("status", "T");
		guParamMap.put("changeUserId", userDo.getId());
		groupUserDao.updateGroupUserByParentId(guParamMap);

		// 判断用户是否在等待区，如果在等待区直接删除
//		userWaitingDao.deleteByUserId(userId);

		// 更新用户的付款记录表不动
//		WalletDo walletDo = new WalletDo();
//		walletDo.setFkUserId(userId);
//		walletDo.setValid(0);
//		walletDao.updateWalletByUserId(walletDo);

	}
	
	

}

package com.redpacket.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.redpack.account.dao.IUserDao;
import com.redpack.account.faced.IUserService;
import com.redpack.group.IGroupService;
import com.redpack.group.dao.IGroupUserDao;
import com.redpack.userWaiting.dao.IUserWaitingDao;
import com.redpack.wallet.IWalletService;
import com.redpack.wallet.dao.IWalletDao;
import com.redpack.wallet.model.WalletDo;

public class WalletServiceTest extends BaseTestCase {
	
	
	
	@Autowired
	private IGroupService groupService;
	
	@Autowired
	private IGroupUserDao groupUserDao;
	
	@Autowired
	private IUserWaitingDao userWaitingDao;
	
	@Autowired
	private IUserDao userDao;
	
	@Autowired
	private IWalletService walletService;
	
	@Autowired
	private IWalletDao wallDao;
	
	@Autowired
	private IUserService userService;
	
	@Test
	public void testSelectUnconfirm(){
		List<WalletDo> unrecordList = wallDao.selectUnConfirmRecord("dd");
		System.out.println(unrecordList.size());
	}
	
	@Test
	public void testProcessWaitingUser(){
		
		Map<String,Object> paraMap = new HashMap<String,Object>();
		String groupName="GA2016032100001";
		walletService.processWaitingUser(groupName);
	}
	
}

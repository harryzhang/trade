package com.redpacket.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.redpack.account.dao.IUserDao;
import com.redpack.account.service.IMoneyMsg;
import com.redpack.account.service.MoneyMsgFactory;
import com.redpack.group.IGroupService;
import com.redpack.group.dao.IGroupUserDao;

public class FactoryTest extends BaseTestCase {
	
	@Autowired
	private IGroupService groupService;
	
	@Autowired
	private IGroupUserDao groupUserDao;
	
	@Autowired
	private IUserDao userDao;
	
	@Value("#{config['system.name']}")
	private String system;
	
	@Test
	public void testGetBean(){
		
		IMoneyMsg moneyMsg= MoneyMsgFactory.getMoneyMsg(system);
		//处理打款信息
		System.out.println(moneyMsg.getClass());
	}
	
	
}

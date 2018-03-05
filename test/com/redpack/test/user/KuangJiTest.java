package com.redpack.test.user;

import junit.framework.TestCase;

import org.springframework.beans.factory.annotation.Autowired;

import com.redpack.common.account.IUserInfoService;
import com.redpack.service.account.UserAccountServiceImpl;

public class UserTest extends TestCase{

	
	@Override
	protected void setUp() throws Exception {
		userService  = new UserAccountServiceImpl();
	}
	

	
	public void testPrototype() throws Exception {}
}

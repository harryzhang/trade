package com.redpack.test.user;

import junit.framework.TestCase;

import com.redpack.common.account.IUserInfoService;
import com.redpack.service.account.UserAccountServiceImpl;

public class UserTest extends TestCase{

	UserAccountServiceImpl userService;
	
	@Override
	protected void setUp() throws Exception {
		userService  = new UserAccountServiceImpl();
	}
	

	
	public void testPrototype() throws Exception {}
}

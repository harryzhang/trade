package com.redpacket.test;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.redpack.account.dao.IUserDao;
import com.redpack.account.faced.IBizUserAccountService;
import com.redpack.account.model.UserDo;
import com.redpack.constant.WebConstants;
import com.redpack.order.IOrderService;

public class TeamAmountTest extends BaseTestCase {

	@Autowired
	private IUserDao userDao;
	@Autowired
	public IOrderService orderService;
	@Autowired
	IBizUserAccountService bizUserAccountService;
	
	@Test
	public void testJob(){
		
		List<UserDo> userList = userDao.getAllUser(null);
		for (UserDo userDo : userList) {
			long refferId = userDo.getReferrerId();
			BigDecimal amount = bizUserAccountService.getAccountTypeAmount(WebConstants.SECURITY_ACCOUNT, userDo.getId());
			orderService.giftTeamAmount(refferId,amount.multiply(new BigDecimal("10")));
		}
	
	}
	
	
	
	
}

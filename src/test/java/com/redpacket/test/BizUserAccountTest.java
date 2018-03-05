package com.redpacket.test;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.redpack.account.faced.IBizUserAccountService;
import com.redpack.account.model.BizUserAccountDo;
import com.redpack.constant.WebConstants;

public class BizUserAccountTest extends BaseTestCase {

	@Autowired
    private IBizUserAccountService  bizUserAccountService;
	
	
	@Test
	public void testInsert(){
		BizUserAccountDo bizUserAccountDo = new BizUserAccountDo();
		bizUserAccountDo.setUserId(1092L);
		bizUserAccountDo.setAmount(new BigDecimal(0));
		//bizUserAccountDo.setAccountType(WebConstants.POINT_ADD_A);
	
		//bizUserAccountDao.addUserAccount( bizUserAccountDo);
	}
	
	@Test
	public void testGet(){
//		BizUserAccountDo bizUserAccountDo = new BizUserAccountDo();
//		bizUserAccountDo.setUserId(1982L);
//		bizUserAccountDo.setAmount(new BigDecimal(100));
//		bizUserAccountDo.setAccountType(WebConstants.POINT_A);
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("userId", 1982L);
		//parameterMap.put("accountType", WebConstants.POINT_A);
		//List<BizUserAccountDo> list = bizUserAccountDao.selectUserAccount(parameterMap);
	}
	
	@Test
	public void testSelect(){
		BizUserAccountDo bizUserAccountDo = new BizUserAccountDo();
		bizUserAccountDo.setUserId(1982L);
		bizUserAccountDo.setAmount(new BigDecimal(200));
//		bizUserAccountDo.setAccountType(WebConstants.POINT_A);
		
		//bizUserAccountDao.updateUserAmountById(bizUserAccountDo);
	}
	
	@Test
	public void testUpdate(){
		BizUserAccountDo bizUserAccountDo = new BizUserAccountDo();
		bizUserAccountDo.setUserId(1982L);
		bizUserAccountDo.setAmount(new BigDecimal(100));
		//bizUserAccountDo.setAccountType(WebConstants.POINT_A);
		
		//bizUserAccountDao.updateUserAmountById( bizUserAccountDo);
	}
	
	
	@Test
	public void testSaveTransJjd(){
		bizUserAccountService.saveTransJjd(1954L, new BigDecimal("-50"));
		System.out.println("ok");
	}
	
	
	
}

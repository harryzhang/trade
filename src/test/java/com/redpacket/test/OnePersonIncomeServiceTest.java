package com.redpacket.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.redpack.income.service.MuiltLevelBean;
import com.redpack.income.service.OnePersonIncomeService;

public class OnePersonIncomeServiceTest extends BaseTestCase {

	
	@Autowired
	private OnePersonIncomeService  onePersonIncomeService;
	
	
	@Test
	public void testReadMuiltLevelBean() {
		
		//缓存用户积分账户
		onePersonIncomeService.loadJiFenAccount();
				
		MuiltLevelBean muiltLevelBean1 = onePersonIncomeService.getMuiltLevelConfig1();			
		MuiltLevelBean muiltLevelBean2 = onePersonIncomeService.getMuiltLevelConfig2();
		System.out.println(muiltLevelBean1);
		
		System.out.println(muiltLevelBean2);
		
		
		MuiltLevelBean result = onePersonIncomeService.chooseMuiltConfig(4298l, muiltLevelBean1, muiltLevelBean2);
		System.out.println(result);
	}
	
	

	
}

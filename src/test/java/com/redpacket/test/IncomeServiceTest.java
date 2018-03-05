package com.redpacket.test;
import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.redpack.income.IIncomeSevice;

public class IncomeServiceTest extends BaseTestCase {

	@Autowired
    private IIncomeSevice  incomeService;
	
	
	@Test
	public void testExecIncome(){
		//incomeService.execIncome(new Date());
	}
	
}

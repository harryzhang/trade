package com.redpacket.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.redpack.account.faced.IKangjiIncomeService;

public class KuangjiIncomeServiceTest extends BaseTestCase {

	
	@Autowired
	public IKangjiIncomeService kangjiIncomeService;
	
	@Test
	public void testJob(){
		kangjiIncomeService.doJob();
	}
	
	
	
	
}

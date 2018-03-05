package com.redpacket.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.redpack.bet.IBetResultService;

public class ToCaiServiceTest extends BaseTestCase {

	
	@Autowired
	public IBetResultService betResultService;
	
	@Test
	public void testJob(){
		betResultService.openGame(1500);
	}
	
	
	
	
}

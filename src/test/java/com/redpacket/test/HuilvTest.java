package com.redpacket.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.redpack.huilv.IHuilvService;

public class HuilvTest extends BaseTestCase {

	
	@Autowired
	public IHuilvService huilvService;
	
	@Test
	public void testJob(){
		huilvService.getHuilv();
	}
	
	
	
	
}

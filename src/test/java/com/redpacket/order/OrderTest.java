package com.redpacket.order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.redpack.account.model.UserAccountIncomeDo;
import com.redpack.account.model.UserDo;
import com.redpack.order.IOrderService;
import com.redpacket.test.BaseTestCase;

public class OrderTest extends BaseTestCase {

	
	@Autowired
	IOrderService orderService;
	
	int i = 0;
	
	@Test
	public void orderTest(){
		
		String outTradeNo = "order_202520161103415";
		//微信付款
		//股价 * 汇率  * 分为单位 * 数量
		double amount = 30 * 7.5 *100 *2; 
		String total_fee= "" + (int)amount;
		Map<String,String> map = new HashMap<String,String>();
		map.put("out_trade_no",outTradeNo);
		map.put("total_fee",total_fee);
		
		Boolean payStatus = orderService.chongZhiOrder(map);
		
	}
	
	
}

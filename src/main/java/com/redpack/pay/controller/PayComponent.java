package com.redpack.pay.controller;

import java.util.HashMap;
import java.util.Map;

import com.redpack.pay.factory.ThirdPayServiceFactory;
import com.redpack.pay.factory.ThirdPayServiceResultParserFactory;
import com.redpack.pay.service.IThirdPayService;
import com.redpack.pay.service.IThirdPayServiceResultParser;
import com.redpack.pay.service.ParseResult;
import com.redpack.pay.service.PayResult;

public class PayComponent {
	

	/**
	 * 
	 * @param requestUserAgent 客户端请求的userAgent
	 * @param openId    微信用户的openId
	 * @param payAmount 支付金额
	 * @param orderNo   订单号
 	 * @param userId    付款用户id
 	 * @param goodsName 商品名称
 	 * @param payType   支付方式
	 */
	public static  ParseResult toPay(String requestUserAgent, 
									 String openId, 
									 String payAmount,
									 String orderNo,
									 String userId,
									 String goodsName,
									 String payType) {
		
		
		
		//根据客户端获取那种支付方式
		ThirdPayServiceFactory payFactory = ThirdPayServiceFactory.createPayFactory();
		IThirdPayService payService = payFactory.getThirdPayService(payType);
		//请求第三支付
		Map<String,String> payPara = new HashMap<String,String>();
		payPara.put("payAmount",  payAmount);
		payPara.put("openId", openId);
		payPara.put("userId", userId);
		payPara.put("out_trade_no", orderNo);
		payPara.put("goodsName", goodsName);
		
		PayResult payResult = payService.doPay(payPara);
		
		ThirdPayServiceResultParserFactory parseFactory = ThirdPayServiceResultParserFactory.createPayServiceResultParserFactory();
		IThirdPayServiceResultParser payPaser = parseFactory.getThirdPayServiceResultParser(payType);
		//解析第三支付返回
		return payPaser.parse(payResult);
		
	}
}
package com.redpack.pay.service;

import java.util.Map;


/**
 * 第三方支付服务接口
 * @author harry
 *
 */
public interface IThirdPayService {
	
	public PayResult doPay(Map<String,String> payPara);

}

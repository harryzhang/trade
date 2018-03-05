package com.redpack.pay.impl;

import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.redpack.pay.controller.PayUtil;
import com.redpack.pay.service.IThirdPayService;
import com.redpack.pay.service.PayResult;

/**
 * 第三方支付扫描支付实现
 * 
 * @author harry
 * 
 */
@Service("wapPayService")
public class ThirdPayServiceWapImpl implements IThirdPayService {

	@Override
	public PayResult doPay(Map<String, String> payPara) {

		PayResult pr = new PayResult();
		try {
			String payAmount = payPara.get("payAmount");
			String openId = payPara.get("openId");
			String orderNo = payPara.get("out_trade_no");
			String body = payPara.get("goodsName");
			JSONObject jsonObject = PayUtil.getWapPayInfo(payAmount, orderNo, body);
			String res = (String) jsonObject.get("res");
			if ("ok".equals(res)) {
				pr.setPayReturnVal(PayResult.succes);
			}
		} catch (Throwable e) {
			e.printStackTrace();
			pr.setPayReturnVal(PayResult.error);
		}finally{
			
		}

		return pr;
	}

}

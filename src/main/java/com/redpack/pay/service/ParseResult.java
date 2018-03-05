package com.redpack.pay.service;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

public class ParseResult {

	private boolean  finish; //支付完成
	
	private boolean  success; //支付成功
	
	private String orderTradeNo; //交易订单号
	
	private PayResult payResult ; // 支付返回的结果 

	public ParseResult(PayResult result) {
		if(result.error.equals(result.getPayReturnVal()) ){
			this.finish=true;
			this.success=false;
		}
		
		this.payResult = result;
	}

	public boolean isFinish() {
		return finish;
	}

	public void setFinish(boolean finish) {
		this.finish = finish;
	}

	public void outPayReturn(HttpServletResponse httpResponse) throws IOException {
		IOUtils.write(this.payResult.getPayReturnVal(), httpResponse.getOutputStream(), "GB2312");
	}

	public boolean isSuccess() {
		return this.success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getOrderTradeNo() {
		return orderTradeNo;
	}

	public void setOrderTradeNo(String orderTradeNo) {
		this.orderTradeNo = orderTradeNo;
	}

	public String getPayReturnVal() {
		return payResult.getPayReturnVal();
	}

	
	
}

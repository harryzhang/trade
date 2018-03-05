package com.redpack.pay.service;

public class PayResult {
	
	/**
	 * 支付返回
	 */
	private String payReturnVal ;
	
	public final static String error = "error";
	public final static String succes = "succes";

	
	
	public void setPayReturnVal(String payResult) {
		this.payReturnVal = payResult;
	}



	public String getPayReturnVal() {
		return payReturnVal;
	}

	
}

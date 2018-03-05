/**
 * 
 */
package com.redpack.sms;

import com.redpack.base.result.IResult;

/**
 * 功能说明：短信发送
 * @author baishui
 * @2016年3月13日 上午8:53:47
 *
 * @com.redpack.sms.ISmsService.java
 */
public interface ISmsService {
	
	/**
	 * 短信内容发磅
	 * @param phone  手机号码，多个手机号码用 ，号隔开
	 * @param message 发送内容
	 * @return
	 */
	public IResult sendMessage(String phone,String message);

}

/**
 * 
 */
package com.redpack.sms.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.bcloud.msg.http.HttpSender;
import com.redpack.base.result.IResult;
import com.redpack.base.result.ResultSupport;
import com.redpack.constant.WebConstants;
import com.redpack.sms.ISmsService;

//import com.bcloud.msg.http.HttpSender;

/**
 * 功能说明：短信发送接口
 * @author baishui
 * @2016年3月13日 上午8:57:19
 *
 * @com.redpack.service.sms.SmsServiceImpl.java
 */
@Service("smsServiceImpl")
public class SmsServiceImpl implements ISmsService {

	private final Logger logger = Logger.getLogger(this.getClass());
	
//	@Value("#{configProperties['sms.url']}")
	private String url = WebConstants.SMS_URL;// 应用地址
//	@Value("#{configProperties['sms.user']}")
	private String account = WebConstants.SMS_USE;// 账号
	
//	@Value("#{configProperties['sms.password']}")
	private String pswd = WebConstants.SMS_PASSWORD;// 密码
	
	@Override
	public IResult sendMessage(String phone, String message) {
		logger.info("短信内容发送:" + phone+message);
		
		
		
		String mobile = phone;// 手机号码，多个号码使用","分割
		String msg = message;// 短信内容
		boolean needstatus = true;// 是否需要状态报告，需要true，不需要false
		String product = null;// 产品ID
		String extno = null;// 扩展码

		System.out.println("=========" + url   +  account + pswd);
		try {
			//短信发送没有包 20160721
		
			 
			String returnString = HttpSender.batchSend(url, account, pswd, mobile, msg, needstatus, product, extno);
			
			logger.info("短信内容发送成功:" + returnString);
			
			return ResultSupport.buildResult(0, "成功");
			
		} catch (Exception e) {
			logger.info("短信内容发送失败:" );
			e.printStackTrace();
			return ResultSupport.buildResult(1, "失败");
		}
		
	}

	
}

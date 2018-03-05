/**
 * 
 */
package com.redpack.sms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.redpack.base.controller.BaseController;
import com.redpack.base.result.IResult;
import com.redpack.sms.ISmsService;
import com.redpack.utils.ResponseUtils;

import net.sf.json.JSONObject;

/**
 * 功能说明：短信发送
 * 
 * @author baishui
 * @2016年3月13日 上午9:02:54
 *
 * @com.redpack.web.view.sms.controller.SmsController.java
 */
@Controller
@RequestMapping("/sms")
public class SmsController extends BaseController {

	private static final Logger logger = Logger.getLogger(SmsController.class);

	@Autowired
	ISmsService smsService;

	/**
	 * 短信发送
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("sendMessage")
	public void sendMessage(HttpServletRequest request,HttpServletResponse response,
			Model model,String phone, String message) {
		logger.info("----短信发送----");
		
		//valid parameter
		JSONObject jsonObject = new JSONObject();
		if(StringUtils.isBlank(phone) || StringUtils.isBlank(message)){
			jsonObject.put("result", "手机号码或发送信息不能为空,请重试"); 
			jsonObject.put("resultCode", "1"); 
			ResponseUtils.renderText(response, "UTF-8", jsonObject.toString());
			return;
		}
		
		IResult result = smsService.sendMessage(phone, message);
		jsonObject.put("result", result.getErrorMessage()); 
		jsonObject.put("resultCode", result.getResultCode()); 
		ResponseUtils.renderText(response, "UTF-8", jsonObject.toString());
	}

}

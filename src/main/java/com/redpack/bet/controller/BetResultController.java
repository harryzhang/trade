/*
 * Powered By  huangzl QQ: 272950754
 * Web Site:  
 * Since 2008 - 2016
 */


package com.redpack.bet.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.redpack.Thread.ToJingCai;
import com.redpack.base.controller.BaseController;
import com.redpack.bet.IBetResultService;
import com.redpack.utils.ResponseUtils;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/betResult")
public class BetResultController extends BaseController {
	
	private final Logger logger = Logger.getLogger(this.getClass());	
	@Autowired
	private IBetResultService betResultService;
	
	private Map<String, Object> request;
	

	/**
	 * 获取开盘时间
	 * @param request
	 * @param response
	 * @param model
	 * @param userId
	 * @param changeName
	 */
	@RequestMapping("getOpenTime")
	public void setUnvalidUser(HttpServletRequest request,
			HttpServletResponse response, Model model,String userId,String changeName) {
		logger.info("----获取开盘时间----");
		JSONObject jsonObject = new JSONObject();
		
		long openTime = ToJingCai.liveTime;
		int period = ToJingCai.period;
		jsonObject.put("result", "成功"); 
		jsonObject.put("openTime", openTime); 
		jsonObject.put("period", period); 
		ResponseUtils.renderText(response, "UTF-8", jsonObject.toString());
		return;
	}
	
	/**
	 * 新增或修改
	 * 
	 */
	public String saveBetResult(){
		return "";
	}
	

	
	
	/**
	 * 删除
	 * 
	 */
	public String deleteBetResult(){
	    return "";
	}
	
	/**
	 * 查找
	 * 
	 * @return
	 */
	public String findById(){
		return "";
	}
	
	/**
	 * 根据条件查找
	 * 
	 * @return
	 */
	public String query(){
		return "";
	}


}

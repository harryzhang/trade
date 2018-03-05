package com.redpack.basedata.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.redpack.base.controller.BaseController;
import com.redpack.basedata.IBaseDataService;
import com.redpack.utils.ResponseUtils;

@Controller
@RequestMapping("/baseData")
public class BaseDataController extends BaseController{
	private static final Logger logger = Logger.getLogger(BaseDataController.class);
	
	@Autowired
	private IBaseDataService   basedataService;
	
	@RequestMapping("getCity")
	public void getCity(HttpServletRequest request,
						HttpServletResponse response,
						Model model) {
		
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put("result", 0); //成功
		String province = request.getParameter("province");
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("province", province);
		List<Map<String,String>> cityList = basedataService.getCity(parameterMap);
		try{
			jsonObject.put("cityLst", cityList);
		}catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("result", 1); //失败
		}
		
		ResponseUtils.renderText(response, "UTF-8", jsonObject.toString());
	}
	
	
	@RequestMapping("getProvince")
	public void getProvince(HttpServletRequest request,
						HttpServletResponse response,
						Model model) {
		
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put("result", 0); //成功
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		List<Map<String,String>> cityList = basedataService.getProvince(parameterMap);
		
		try{
			jsonObject.put("provinceLst", cityList);
		}catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("result", 1); //失败
		}
		
		ResponseUtils.renderText(response, "UTF-8", jsonObject.toString());
	}

}

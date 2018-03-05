
package com.redpack.param.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.redpack.account.faced.IUserService;
import com.redpack.account.model.UserDo;
import com.redpack.base.controller.BaseController;
import com.redpack.constant.WebConstants;
import com.redpack.grade.model.GradeFeeDo;
import com.redpack.param.IParamService;
import com.redpack.param.model.ParamDo;
import com.redpack.utils.ResponseUtils;

import net.sf.json.JSONObject;


/**
 * 参数管理
 * 功能说明：
 * @author baishui
 * @2016年3月11日 下午2:11:25
 *
 * @com.redpack.web.view.param.controller.ParamController.java
 */
@Controller
@RequestMapping("/param")
public class ParamController  extends BaseController {
	
	private static transient Logger logger = Logger.getLogger(ParamController.class);
	
	@Autowired
	IParamService paramService;
	
	@Autowired
	IUserService userService;
	

	/**
	 * 参数设置界面
	 * @param request
	 * @param map
	 * @param response
	 * @return
	 */
	@RequestMapping("/setting")
	public String setting(HttpServletRequest request,ModelMap map,HttpServletResponse response) {
		
		String refferCenter = paramService.getByName(WebConstants.REFFER_CENTER);
		map.put("refferCenter",refferCenter);
		String eourRate = paramService.getByName(WebConstants.EOUR_RATE);
		map.put("eourRate",eourRate);
		String eourAmount = paramService.getByName(WebConstants.EOUR_AMOUNT);
		map.put("eourAmount",eourAmount);
		String staticOneRate = paramService.getByName(WebConstants.STATIC_ONE_RATE);
		map.put("staticOneRate",staticOneRate);
		String manageRate = paramService.getByName(WebConstants.MANAGE_RATE);
		map.put("manageRate",manageRate);
		String cityRate = paramService.getByName(WebConstants.CITY_RATE);
		map.put("cityRate",cityRate);
		String provinceRate = paramService.getByName(WebConstants.PROVINCE_RATE);
		map.put("provinceRate",provinceRate);
		String sentRate = paramService.getByName(WebConstants.SENT_RATE);
		map.put("sentRate",sentRate);
		String ifProm = paramService.getByName(WebConstants.IF_PROM);
		map.put("ifProm",ifProm);
		String promGuquan = paramService.getByName(WebConstants.PROM_GUQUAN);
		map.put("promGuquan",promGuquan);
		String manageEourPointRate = paramService.getByName(WebConstants.MANAGE_EOUR_POINT_RATE);
		map.put("manageEourPointRate",manageEourPointRate);
		
		return "param/setting";
	}
	
	/**
	 * 设置参数
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/setSetting")
	public void setSetting(HttpServletRequest request,ModelMap map
			,HttpServletResponse response) {
		logger.debug("设置参数");
		ParamDo paramDo = new ParamDo();
		paramDo.setParamName(WebConstants.REFFER_CENTER);
		paramDo.setParamValue(request.getParameter("refferCenter"));
		paramService.setParamName(paramDo);
		
		paramDo = new ParamDo();
		paramDo.setParamName(WebConstants.EOUR_RATE);
		paramDo.setParamValue(request.getParameter("eourRate"));
		paramService.setParamName(paramDo);
		
		paramDo = new ParamDo();
		paramDo.setParamName(WebConstants.EOUR_AMOUNT);
		paramDo.setParamValue(request.getParameter("eourAmount"));
		paramService.setParamName(paramDo);
		
		paramDo = new ParamDo();
		paramDo.setParamName(WebConstants.STATIC_ONE_RATE);
		paramDo.setParamValue(request.getParameter("staticOneRate"));
		paramService.setParamName(paramDo);
		
		paramDo = new ParamDo();
		paramDo.setParamName(WebConstants.MANAGE_RATE);
		paramDo.setParamValue(request.getParameter("manageRate"));
		paramService.setParamName(paramDo);
		
		paramDo = new ParamDo();
		paramDo.setParamName(WebConstants.CITY_RATE);
		paramDo.setParamValue(request.getParameter("cityRate"));
		paramService.setParamName(paramDo);
		paramDo = new ParamDo();
		paramDo.setParamName(WebConstants.PROVINCE_RATE);
		paramDo.setParamValue(request.getParameter("provinceRate"));
		paramService.setParamName(paramDo);
		paramDo = new ParamDo();
		paramDo.setParamName(WebConstants.SENT_RATE);
		paramDo.setParamValue(request.getParameter("sentRate"));
		paramService.setParamName(paramDo);
		
		paramDo = new ParamDo();
		paramDo.setParamName(WebConstants.IF_PROM);
		paramDo.setParamValue(request.getParameter("ifProm"));
		paramService.setParamName(paramDo);
		
		paramDo = new ParamDo();
		paramDo.setParamName(WebConstants.PROM_GUQUAN);
		paramDo.setParamValue(request.getParameter("promGuquan"));
		paramService.setParamName(paramDo);
		
		paramDo = new ParamDo();
		paramDo.setParamName(WebConstants.MANAGE_EOUR_POINT_RATE);
		paramDo.setParamValue(request.getParameter("manageEourPointRate"));
		paramService.setParamName(paramDo);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result","设置成功");
		ResponseUtils.renderText(response, null, jsonObject.toString());
	}
	
	
	/**
	 * 去打款设置界面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/setPayMoney")
	public void setPayMoney(HttpServletRequest request,String payMoney,
			String referrerMoney,HttpServletResponse response) {
		logger.debug("设置打款金额");
		ParamDo paramDo = new ParamDo();
		paramDo.setParamName("PAY_MONEY");
		paramDo.setParamValue(payMoney);
		paramService.setParamName(paramDo);
		
		logger.debug("设置感恩金额");
		paramDo = new ParamDo();
		paramDo.setParamName("REFERRER_MONEY");
		paramDo.setParamValue(referrerMoney);
		paramService.setParamName(paramDo);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result","设置会费成功");
		ResponseUtils.renderText(response, null, jsonObject.toString());
	}
	
	/**
	 * 去超时时间设置界面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/expirationTime")
	public String toExpirationTime(HttpServletRequest request,ModelMap map,HttpServletResponse response) {
		
		String expirationTime = paramService.getByName("EXPIRATION_TIME");
		map.put("expirationTime", expirationTime);
		
		return "param/setExpirationTime";
	}
	
	/**
	 * 去打款设置界面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/setExpirationTime")
	public void setExpirationTime(HttpServletRequest request,String expirationTime,HttpServletResponse response) {
		logger.debug("设置打款金额");
		ParamDo paramDo = new ParamDo();
		paramDo.setParamName("EXPIRATION_TIME");
		paramDo.setParamValue(expirationTime);
		paramService.setParamName(paramDo);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result","设置超时时间成功");
		ResponseUtils.renderText(response, null, jsonObject.toString());
	}
	
	/**
	 * 去设置平台收款账号
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/fxAccount")
	public String toFxAccount(HttpServletRequest request,ModelMap map,HttpServletResponse response) {
		
		UserDo fxUserDo = userService.getById(0L);
		map.put("fxName", fxUserDo.getName());
		map.put("fxPhone", fxUserDo.getUserName());
		map.put("fxZfb", fxUserDo.getZhifubao());
		map.put("fxWeixin", fxUserDo.getWeixin());
		
		return "param/setFxAccount";
	}
	
	/**
	 * 修改用户信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/fxUserAccount")
	public String fxUserAccount(HttpServletRequest request,ModelMap map,HttpServletResponse response) {
		
		UserDo fxUserDo = userService.getById(getUserId(request));
		map.put("fxName", fxUserDo.getName());
		map.put("fxPhone", fxUserDo.getUserName());
		map.put("fxZfb", fxUserDo.getZhifubao());
		map.put("fxWeixin", fxUserDo.getWeixin());
		
		
		return "param/setUserAccount";
	}
	
	/**
	 * 设置平台收款账号
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/setFxAccount")
	public void setFxAccount(HttpServletRequest request,HttpServletResponse response,
			String fxName,String fxPhone,String fxZfb, String fxWeixin) {
		logger.debug("设置平台收款账号");
		
		UserDo userDo = new UserDo();
		userDo.setId(0L);
		userDo.setName(fxName);
		userDo.setUserName(fxPhone);
		userDo.setZhifubao(fxZfb);
		userDo.setWeixin(fxWeixin);
		userService.updateUser(userDo);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result","设置平台账号成功");
		ResponseUtils.renderText(response, null, jsonObject.toString());
	}
	
	
	/**
	 * 修改用户信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/setUserAccount")
	public void setUserAccount(HttpServletRequest request,HttpServletResponse response,
			String fxZfb, String fxWeixin) {
		logger.debug("修改用户信息");
		
		UserDo userDo = new UserDo();
		userDo.setId(getUserId(request));
		userDo.setZhifubao(fxZfb);
		userDo.setWeixin(fxWeixin);
		userService.updateUser(userDo);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result","修改用户信息成功");
		ResponseUtils.renderText(response, null, jsonObject.toString());
	}
	
	/**
	 * 去设置客服
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "toFxKefu")
	public String toFxKefu(HttpServletRequest request,HttpServletResponse response) {
		return "param/setFxKefu";
		
	}
	
	/**
	 * 保存设置客服
	 * 修改用户表t_user 字段organ 为1 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "setFxKefu")
	public void setManagerMember(HttpServletRequest request,HttpServletResponse response,String userName) {
		
		Map parameterMap = new HashMap();
		parameterMap.put("userName", userName);
		UserDo userDo = userService.getByUserDo(parameterMap);
		
		JSONObject jsonObject = new JSONObject();
		if( null == userDo){
			jsonObject.put("result","设置客服失败，没有此手机号码用户");
			
		}else{
			userDo.setOrgan("1");
			userDo.setUpdateTime(new Date());
			userService.updateUser(userDo);
			jsonObject.put("result","设置客服成功");
		}
		ResponseUtils.renderText(response, null, jsonObject.toString());
		
	}

	
}

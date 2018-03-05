package com.redpack.base.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;


public class TokenUtil {
	
	//TOKEN 业务编码， 积分兑换
	public static String BIZ_CODE_JIFENG_DUIHUANG = "JIFENG_DUIHUANG";
	//TOKEN 业务编码， 购买股权
	public static String BIZ_CODE_GOUMAI_GUQUAN = "GOUMAI_GUQUAN";
	//TOKEN 业务编码，冲值
	public static String BIZ_CODE_RECHANGE = "RECHANGE";
	
	/**
	 * 
	 * @param request
	 * @param bizCode 业务code
	 */
	public static String putToken(HttpServletRequest request,String bizCode){
		String token = Base64.encodeBase64String(UUID.randomUUID().toString().getBytes());
		request.getSession().setAttribute(bizCode, token);
		return token;
	}
	
	
	/**
	 * 
	 * @param request
	 * @param bizCode 业务code
	 */
	public static boolean checkToken(HttpServletRequest request,String bizCode){
		String token = request.getParameter("token");
		if(StringUtils.isBlank(token)){
			return false;
		}
		String sessionToken = (String)request.getSession().getAttribute(bizCode) ==null? "" : (String)request.getSession().getAttribute(bizCode);
		request.getSession().removeAttribute(bizCode);
		return token.equalsIgnoreCase(sessionToken);
	}

}

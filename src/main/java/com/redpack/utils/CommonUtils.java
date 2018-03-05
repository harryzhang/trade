package com.redpack.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.math.NumberUtils;

import com.redpack.account.model.UserDo;
import com.redpack.account.model.UserInfoDo;
import com.redpack.constant.WebConstants;
import com.redpack.constant.WebThreadVariable;


/**
 * @Description 描述方法作用
 * @author huangzl QQ: 272950754
 * @date 2015年5月27日 下午5:34:16
 * @Project hehenian-lend-login
 * @Package com.hehenian.login.common.utils 
 * @File CommonUtils.java
*/
public class CommonUtils {
	
	/**
	 * 得到第三方 OpenId
	 * @param request
	 * @return
	 */
	public static String getOpenId(HttpServletRequest request){
		return "openId";
	}

	/**
	 * 得到登录登录用户
	 * @param request
	 * @param response
	 * @return
	 */
	public static UserDo getUserDo(){
		return WebThreadVariable.getUserDo();
	}
	
	/**
	 * 得到登录登录用户
	 * @param request
	 * @param response
	 * @return
	 */
	public static UserInfoDo getUserAccountDo(){
		return WebThreadVariable.getUserAccountDo();
	}
	
	/**
	 * 得到登录memberId
	 * @param request
	 * @param response
	 * @return
	 */
	public static Long getLoginId(){
		UserDo aud = getUserDo();
		if (aud != null) {
			return aud.getId();
		}
		return null;
	}
	
	/**
	 * 从当前线程局部变量中获取当前登录用户sessionId
	 * @return
	 */
	public static String getRoot(){
		return WebThreadVariable.getRoot();
	}
	
	
}

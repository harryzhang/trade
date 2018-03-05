
/**  
 * @Project: hehenian-mobile
 * @Package com.hehenian.mobile.controller
 * @Title: BaseController.java
 * @Description: TODO
 *
 * @author:  zhangyunhua
 * @date 2015-3-26 上午11:21:59
 * @Copyright: HEHENIAN Co.,Ltd. All rights reserved.
 * @version V1.0   
 */
package com.redpack.base.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.redpack.account.model.UserDo;
import com.redpack.account.model.UserInfoDo;
import com.redpack.constant.WebConstants;
import com.redpack.utils.CommonUtils;

/**
 * @Description 描述方法作用
 * @author huangzl QQ: 272950754
 * @date 2015年5月27日 下午5:34:02
 * @Project hehenian-lend-login
 * @Package com.hehenian.login.common 
 * @File BaseController.java
*/
public class BaseController {
	
	protected String  getLocalPath(HttpServletRequest request,String path) {
		UserDo currentUser = (UserDo)  request.getSession().getAttribute(WebConstants.SESSION_USER);
		if( null != currentUser){
			String userLocal = currentUser.getUserLocal();
			if("en".equals(userLocal)){
				if(path.startsWith("/")){
					path = "/page_en" + path;
				}else{
					path = "page_en/" + path;
				}
			}
		}
		
		return path;
	}
	
	protected long getUserId(HttpServletRequest request) {
		UserDo currentUser = (UserDo)  request.getSession().getAttribute(WebConstants.SESSION_USER);
		return currentUser.getId();
	}
	
	protected String getSessionStrAttr(HttpServletRequest request,String attrName) {
		try {
			Object obj = request.getSession().getAttribute(attrName);
	        if (obj!=null){
	            return obj.toString();
	        }
	        
	        return null;
		} catch (Exception e) {
			return null;
		}
	}
	
    protected int getSessionIntAttr(HttpServletRequest request,String attrName,int defaultValue){
        try {
            return (Integer)request.getSession().getAttribute(attrName);
        }catch (Exception e){
            return defaultValue;
        }
    }
    
	public static String FilteSqlInfusion(String input) {
		if ((input == null) || (input.trim() == "")) {
			return "";
		}
		if (!StringUtils.isNumeric(input)) {
			return input.replace("'", "’").replace("update", "ｕｐｄａｔｅ").replace(
					"drop", "ｄｒｏｐ").replace("delete", "ｄｅｌｅｔｅ").replace("exec",
					"ｅｘｅｃ").replace("create", "ｃｒｅａｔｅ").replace("execute",
					"ｅｘｅｃｕｔｅ").replace("where", "ｗｈｅｒｅ").replace("truncate",
					"ｔｒｕｎｃａｔｅ").replace("insert", "ｉｎｓｅｒｔ");
		} else {
			return input;
		}
	}
	
	public String getPath(HttpServletRequest request) {
        int port = request.getServerPort();
        String portStr = "";
        if(port != 80){
            portStr = ":"+port; 
        }
        String path = request.getScheme() + "://" + request.getServerName()
        + portStr + request.getContextPath()
        + "/";
        return path;
    }
	
	
}

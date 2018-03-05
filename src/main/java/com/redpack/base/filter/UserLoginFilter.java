package com.redpack.base.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Properties;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.redpack.account.model.UserDo;
import com.redpack.base.constant.Constants;
import com.redpack.constant.WebConstants;


/**
 * 用于登录成功后跳转到特定什么页面
 * @author liminglmf
 *
 */
public class UserLoginFilter  extends OncePerRequestFilter {
    
    Logger logger = Logger.getLogger(this.getClass());
    
    //private Properties  sysconfigProperties;
    
	@Override
	protected void initFilterBean() throws ServletException {
		super.initFilterBean();
		//WebApplicationContext spring = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		//sysconfigProperties = (Properties)spring.getBean("sysconfig");
	}

   
     
    /**
     * 销毁方法
     */
     public void destroy() {
    	 super.destroy();
     	//sysconfigProperties = null;
     }

	 

	public boolean isFilterUri(String uri){
		return uri.indexOf("/imageCode/imageCode") != -1
			 ||uri.indexOf("/res/") != -1
			 ||uri.indexOf("/login/") != -1
			 ||uri.indexOf("/common/imageCode") != -1
			 ||uri.indexOf("/account/register") != -1
			 ||uri.indexOf("/redPack/personalCenter") != -1
			 ||uri.indexOf("/company/") != -1
			 ||uri.indexOf("/notice/") != -1
			 ||uri.indexOf("/account/regIndex") != -1
			 ||uri.indexOf("/account/sendVirifyCode") != -1
			 ||uri.indexOf("/account/resetPwdIndex") != -1
			 ||uri.indexOf("/cart/cart") != -1
			 ||uri.indexOf("/goods/select") != -1					 
		     ||uri.indexOf("/res-kuangji/") != -1
		     ||uri.indexOf("/pay/notifyResult") != -1
		     ||uri.indexOf("/pay/paySuccess") != -1
		     ||uri.indexOf("/wx/getWxOpenId") != -1
		     ||uri.indexOf("/account/restLoginPwd") != -1
		     ||uri.indexOf("/member/userManager") != -1
		     ||uri.indexOf("/fh/fh") != -1;
	}
	
    /**
     * 统计出不需要登录的页面,其他页面判断是否有会话<br>
     * 否则重定向到登录页面
     * 
     */
	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
    	
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse resp = (HttpServletResponse) response;        
        String uri = req.getRequestURI();//获取请求URI
        
        //String fromUrl =req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+uri;
        
        HttpSession session = req.getSession();
        logger.info("sessionId:======================"+session.getId());
        if(logger.isDebugEnabled()){
    		logger.debug("sessionId:======================"+session.getId());
    	}
        String contextPath = session.getServletContext().getContextPath();
        
        //MemcachedClient memcacheClient = new MemcachedClient(new BinaryConnectionFactory(),AddrUtil.getAddresses("10.50.10.208:11222"));
        boolean isOKFlag = false; //是否放行通过标记
        
        //已登录不拦截
        UserDo  user = (UserDo)session.getAttribute(WebConstants.SESSION_USER);
        if(user != null){
        	isOKFlag = true;
        }
        
        //uri不拦截
        if(uri.equals(contextPath)
        		|| uri.equals(contextPath+"/") 
        		|| this.isFilterUri(uri)){
        	isOKFlag = true;
        }
        
        if(isOKFlag){
        	chain.doFilter(request, response);
        }else{//提示登录
        	
        	//get fromurl
        	StringBuilder severAddress = new StringBuilder();
    		severAddress.append(req.getScheme()).append("://").append(req.getServerName());
    		int port = req.getServerPort();
    		if(80 != port){
    			severAddress.append(":").append( req.getServerPort());
    		}
        	String fromUrl=severAddress.toString()+uri;
        	String queryParams = req.getQueryString();
        	if(StringUtils.isNotBlank(queryParams)){
        		fromUrl = fromUrl+"?"+ queryParams;
        	}
        	
        	String requestType = req.getHeader("X-Requested-With"); 
	    	if (requestType != null && requestType.equals("XMLHttpRequest")) {
		        JSONObject json = new JSONObject();  
		        json.put("resultCode", "-1");  
		        json.put("fromUrl", fromUrl);  
		        PrintWriter pw = response.getWriter();  
		        pw.print(json.toString());  
		        pw.close();  
	    	}else{
	    		StringBuilder loginUrl = new StringBuilder();
	    		loginUrl.append(severAddress+"/login/index.html").append("?fromUrl=");
	    		//加refer 地址， 从哪个页面过来的
	    		loginUrl.append(URLEncoder.encode(fromUrl,"utf-8"));
	        	resp.sendRedirect(loginUrl.toString());
	    	}
        }
    }


}
package com.redpack.weixin.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URLDecoder;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.redpack.account.model.WeiXinOauth2TokenDo;
import com.redpack.base.exception.BusinessException;

import net.sf.json.JSONObject;

/**
 * 微信服务controller
 *
 */

@Controller
@RequestMapping(value = "/wx")
public class WxController {
	
	private static final Logger logger = Logger.getLogger(WxController.class);
	
    /**
	 * 获取 openID 去登陆 登录入口
	 * 
	 * @return
	 * @author: zhangyunhua
	 * @date 2015-3-29 上午3:36:11
	 */
	@RequestMapping(value = "getWxOpenId")
	public String getWxOpenId(HttpServletRequest request) {
		logger.info("----loginInit(初始化登录页面);----");
		String userLocal = (String) request.getParameter("userLocal");
		String openId = WxUtil.getWxOpenId(request);
		request.setAttribute("userLocal", userLocal);
		request.setAttribute("openId", openId);
		request.getSession().setAttribute("openId", openId);
		return "login/login";
	}
	
	
}

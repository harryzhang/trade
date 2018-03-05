package com.redpack.weixin.controller;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;

import com.redpack.account.model.WeiXinOauth2TokenDo;
import com.redpack.base.exception.BusinessException;

public class WxUtil {
	

	private static final Logger logger = Logger.getLogger(WxUtil.class);
	
	private static final String openIdUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";//获取openId接口url地址
	private static final String APPID = "wx6025dafadcc4021e";
	
	private static final String SECRET = "102e3e7afcfa669097c2e1e44e4aeea1";
	
	
	
	/**
	 * 获取微信用户的openId
	 * @param request
	 * @return
	 */
    public static String getWxOpenId(HttpServletRequest request) {

        try {
        	request.setCharacterEncoding("UTF-8");		
	        String code = request.getParameter("code");
	        logger.info("获取网页授权凭证code:"+code);
        	String requestUrl = openIdUrl.replaceAll("APPID",APPID).replaceAll("SECRET",SECRET).replaceAll("CODE",code);
	        String openId ="";
	        if (!"authdeny".equals(code)) {   	
	        	WeiXinOauth2TokenDo weiXinOauth2TokenDo = getOauth2AccessTokenDo(requestUrl);
	        	if(weiXinOauth2TokenDo!=null){
	        		logger.info("==========================================获取网页授权凭证openId:"+weiXinOauth2TokenDo.getOpenId());
	        		return openId = weiXinOauth2TokenDo.getOpenId();	
	        	} 
	        }
	        
        } catch (Exception e) {
        	e.printStackTrace();
        	logger.info("===============================================获取网页授权凭证openId时异常,"+e);
		}
        return "";
	
    }
	

	
	
	public  static String urlCode(String menucode) throws Exception{
		
		String url= "http://wx.skwuas.top/wx/getWxOpenId.do?menuCode="+menucode;
		return URLEncoder.encode(url,"UTF-8");
	}
	
	

	public static void getMenuJson() throws Exception{
		String url = urlCode("menu_login");
		String menu="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+APPID+"&redirect_uri="+url+"&response_type=code&scope=snsapi_base&state=123#wechat_redirect";
		System.out.println(menu);
		
	}
	
	
	public static void createMenu(){
		
	}
	

	
	private static WeiXinOauth2TokenDo getOauth2AccessTokenDo(String requestUrl) {
		WeiXinOauth2TokenDo wat = new WeiXinOauth2TokenDo();
		try{
			JSONObject jsonObject =  httpsGetRequest(requestUrl);
	        if (null != jsonObject) {
                wat.setAccessToken(jsonObject.getString("access_token"));
                wat.setExpiresIn(jsonObject.getInt("expires_in"));
                wat.setRefeshToken(jsonObject.getString("refresh_token"));
                wat.setOpenId(jsonObject.getString("openid"));
                wat.setScope(jsonObject.getString("scope"));
	        }
		}catch (Exception e) {
            logger.info("获取网页授权凭证失败 :",e);
        }
        return wat;
     }
	
	public static JSONObject httpsGetRequest(String requestUrl){
		JSONObject jsonObject = null;
		CloseableHttpClient httpClient = createSSLClientDefault();
		CloseableHttpResponse response = null;
		try{
			HttpGet get = new HttpGet();
			get.setURI(new URI(requestUrl));
			response =httpClient.execute(get);
			InputStream inputStream = response.getEntity().getContent();
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					inputStream));
	
			String line = "";
			StringBuffer resultString = new StringBuffer();
			while ((line = rd.readLine()) != null) {
				resultString.append(line);
			}
			try {
				logger.info("请求"+requestUrl+"回复>>>>>:"
						+ URLDecoder.decode(resultString.toString(), "UTF-8"));
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
			jsonObject = JSONObject.fromObject(resultString.toString());
			return jsonObject;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new BusinessException("发送请求错误:" + e.getMessage());
		} finally {
			    try {
					response.close();
					httpClient.close();		
				} catch (IOException e) {
					e.printStackTrace();
				}
        }
	}
	
	public static CloseableHttpClient createSSLClientDefault(){
		  try {
			             SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
			                 //信任所有
			                 public boolean isTrusted(X509Certificate[] chain,
			                                 String authType) throws CertificateException {
			                     return true;
			                 }
			             }).build();
			             SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
			             return HttpClients.custom().setSSLSocketFactory(sslsf).build();
			         } catch (KeyManagementException e) {
			             e.printStackTrace();
			         } catch (NoSuchAlgorithmException e) {
			             e.printStackTrace();
			         } catch (KeyStoreException e) {
			             e.printStackTrace();
			         }
			         return  HttpClients.createDefault();
			}
	public static void main(String[] args) throws Exception {
		getMenuJson();		
	}
   


}

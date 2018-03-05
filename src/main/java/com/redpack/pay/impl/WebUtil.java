package com.redpack.pay.impl;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.redpack.base.result.IResult;
import com.redpack.base.result.ResultSupport;


/**
 * 发送HTTPS请求,获得网页内容
 * 
 * @param urlstr
 * @return
 * @throws IOException
 */

public class WebUtil {
	
	static BASE64Encoder encoder = new sun.misc.BASE64Encoder();    
    static BASE64Decoder decoder = new sun.misc.BASE64Decoder();  

	private static Logger logger = Logger.getLogger(WebUtil.class);
	
	private static String getResCharset(CloseableHttpResponse response,
			String charset) {
		Header[] contentTypes = response.getHeaders("Content-Type");
		String resCharset = charset;
		int totalTypes = null != contentTypes ? contentTypes.length : 0;
		for (int i = 0; i < totalTypes; i++) {
			String[] value = contentTypes[i].getValue().toLowerCase()
					.split(";");
			for (int j = 0; j < value.length; j++) {
				if (value[j].startsWith("charset=")) {
					resCharset = value[j].substring("charset=".length());
				}
			}

		}
		return resCharset;
	}

	/**
	 * 读取请求的返回 html
	 * @param response
	 * @return
	 * @throws Throwable
	 */
	private static String readResponse(CloseableHttpResponse response)
			throws Throwable {
		String res = "";
		if (null != response) {
			try {
				HttpEntity entity = response.getEntity();
				logger.info(" response status:" + response.getStatusLine());
				if (entity != null) {
					try {
						String charset = getResCharset(response, "gbk");
						res = new String(IOUtils.toByteArray(entity
								.getContent()), charset);
						logger.debug("postContent 解析后的数据: " + res);
						return res;
					} catch (Exception ee) {
						ee.printStackTrace();
					}
				} else {
					logger.warn(" 返回为空");
				}
				EntityUtils.consume(entity);
			} finally {
				response.close();
			}
		}
		return res;
	}

	
	/**
	 * 设置浏览器头，for 图片
	 * 
	 */
	private static void setRequestBrowerImageHeader(HttpRequestBase request) {
		request.addHeader("Accept", "image/webp,image/*,*/*;q=0.8");
		request.addHeader("Accept-Encoding", "gzip, deflate, sdch");
		request.addHeader("Accept-Language", "zh-CN,zh;q=0.8");
		request.addHeader("Connection", "keep-alive");
		request.addHeader("Host", "ipcrs.pbccrc.org.cn");
		request.addHeader("Upgrade-Insecure-Requests", "1");
		request.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.87 Safari/537.36");
	}
	
	/**
	 * 设置浏览器头
	 * 
	 */
	private static void setRequestBrowerHeader(HttpRequestBase request) {
		request.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		request.addHeader("Accept-Encoding", "gzip, deflate, sdch");
		request.addHeader("Accept-Language", "zh-CN,zh;q=0.8");
		request.addHeader("Connection", "keep-alive");
		request.addHeader("Host", "ipcrs.pbccrc.org.cn");
		request.addHeader("Upgrade-Insecure-Requests", "1");
		request.addHeader("Origin", "https://ipcrs.pbccrc.org.cn");
		request.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.87 Safari/537.36");
	}

	
	
	
	public static  String  httpsGet(CloseableHttpClient httpclient, 
									HttpClientContext context,
												  String getUrl,
												  String referer)throws Throwable {
		CloseableHttpResponse response = null;
		try{
			HttpGet httpGet = new HttpGet(getUrl);
			setRequestBrowerHeader(httpGet);
			httpGet.addHeader("Referer",referer);
			response= httpclient.execute(httpGet,context);
			return readResponse(response);
			
		}catch(Throwable e){
			e.printStackTrace();
			throw e;
		}finally{
			if(null != response){
				response.close();
			}
		}
	}
	
	
	public static  IResult<byte[]> httpsGetImage(CloseableHttpClient httpclient,
												 HttpClientContext context,
														String getUrl,
														String referer) {
		
		//String imgFilePath="d:/yzm.png";
		CloseableHttpResponse response=null;
		IResult result = ResultSupport.buildResult(ResultSupport.success_code);
		InputStream input = null;
		byte[] bytes = null;
		try{
			
			HttpGet httpGet = new HttpGet(getUrl);
			setRequestBrowerImageHeader(httpGet);
			httpGet.addHeader("Referer",referer);
			response= httpclient.execute(httpGet,context);
			int statusCode = response.getStatusLine().getStatusCode();
			if(200==statusCode){
				//File storeFile = new File(imgFilePath);  
		        //FileOutputStream output = new FileOutputStream(storeFile);  
		        //得到网络资源的字节数组,并写入文件  
		       //IOUtils.copy(response.getEntity().getContent(), output);
				//input = response.getEntity().getContent();
		        //output.close(); 
				//imgString = IOUtils.toString(input,"GBK");
				
				//BufferedImage bi = ImageIO.read(input);    
	            ByteArrayOutputStream baos = new ByteArrayOutputStream();    
	            //ImageIO.write(bi, "jpg", baos);    
	            IOUtils.copy(response.getEntity().getContent(), baos);
	            bytes = baos.toByteArray(); 
	            
			}
			
		}catch(Throwable e){
			result.setResultCode(ResultSupport.errorCode);
			e.printStackTrace();
		}
		finally{
			if(response != null){
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			result.setModel(bytes);
			return result;
		}
		
		
	}

	
     /**
 	 * 执行pos请求
 	 * @param httpclient
 	 * @param postUrl  url
 	 * @param param    post参数
 	 * @param cookies  设置的cookie
 	 * @param respHeaderLst  设置的header
 	 * @return
 	 */
 	public static String  httpsPostXml(CloseableHttpClient httpclient,
 									HttpClientContext context,
								   String postUrl, 
								   String requestXml,
								   String referer,String charset)throws Throwable {
 		CloseableHttpResponse resp = null;
 		try{
 			HttpPost httppost = new HttpPost(postUrl);
 			setRequestBrowerHeader(httppost);
 			httppost.addHeader("Content-Type","text/xml");
 			httppost.addHeader("Referer",referer);
 			
 			StringEntity entityParams = new StringEntity(requestXml,"utf-8");
 			httppost.setEntity(entityParams);
 			
 			resp= httpclient.execute(httppost,context);
 			logger.info("executing request " + httppost.getRequestLine());
 			return readResponse(resp);
 			
 		}catch(Throwable e){
 			throw e;
 		}finally{
 			if(null != resp){
 				resp.close();
 			}
 		}
 	}
 	
	/**
	 * 执行pos请求
	 * @param httpclient
	 * @param postUrl  url
	 * @param param    post参数
	 * @param cookies  设置的cookie
	 * @param respHeaderLst  设置的header
	 * @return
	 */
	public static String  httpsPost(CloseableHttpClient httpclient,
									HttpClientContext context,
														   String postUrl, 
														   Map<String, String> param,
														   String referer,String charset)throws Throwable {
		CloseableHttpResponse resp = null;
		try{
			HttpPost httppost = new HttpPost(postUrl);
			setRequestBrowerHeader(httppost);
			httppost.addHeader("Content-Type","application/x-www-form-urlencoded");
			httppost.addHeader("Referer",referer);
			
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
			for (String key : param.keySet()) {
				nameValuePairs.add(new BasicNameValuePair(key, param.get(key)));
			}
			
			//httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "utf-8"));
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, charset));
			
			resp= httpclient.execute(httppost,context);
			logger.info("executing request " + httppost.getRequestLine());
			return readResponse(resp);
			
		}catch(Throwable e){
			throw e;
		}finally{
			if(null != resp){
				resp.close();
			}
		}
	}
	/**
	 * 执行pos请求
	 * @param httpclient
	 * @param postUrl  url
	 * @param param    post参数
	 * @param cookies  设置的cookie
	 * @param respHeaderLst  设置的header
	 * @return
	 */
	public static String  httpsPost(CloseableHttpClient httpclient,
			HttpClientContext context,
			String postUrl, 
			Map<String, String> param,
			String referer)throws Throwable {
		return httpsPost(httpclient,context, postUrl, param, referer, "utf-8");
	}

}
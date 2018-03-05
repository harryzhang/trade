package com.redpack.pay.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.ServletException;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.redpack.utils.SignUtils;
import com.redpack.utils.SwiftMD5;
import com.redpack.utils.SwiftpassConfig;
import com.redpack.utils.XmlUtils;

public class PayUtil {
	private static final Logger logger = Logger.getLogger(PayUtil.class);

	 private final static String version = "1.0";
	 private final static String charset = "UTF-8";
	 private final static String sign_type = "MD5";

	public static JSONObject getWapPayInfo(String payAmount, String orderNo,
			String body) throws IOException {
		
//		if(StringUtils.isNotBlank(payAmount)){
//			BigDecimal amount = (new BigDecimal(payAmount)).multiply(new BigDecimal("100"));
//			payAmount = amount.setScale(0,BigDecimal.ROUND_DOWN).toString();
//		}
		
		String payTest = SwiftpassConfig.pay_test;
		if(StringUtils.isNotBlank(payTest) && "1".equals(payTest)){
			payAmount = "1";
		}
		SortedMap<String, String> map = new TreeMap();

		map.put("service", "pay.weixin.wappay");
		map.put("version", version);
		map.put("charset", charset);
		map.put("sign_type", sign_type);
		map.put("is_raw", "1");

		map.put("mch_id", SwiftpassConfig.wap_mch_id);
		map.put("notify_url", SwiftpassConfig.wap_notify_url);
		map.put("callback_url", SwiftpassConfig.wap_success_url);
		map.put("nonce_str", String.valueOf(new Date().getTime()));

		// 前台参数
		if(StringUtils.isBlank(orderNo)){
			java.text.SimpleDateFormat format = new java.text.SimpleDateFormat(
					"yyyyMMddHHmmss");
			String orderTime = format.format(new java.util.Date());
			java.text.DecimalFormat def = new java.text.DecimalFormat("000000");
			int b = (int) (Math.random() * 1000000 % 1000000);// 产生0-1000000的整数随机数
			String lstStr = def.format(b);
			orderNo = orderTime + lstStr;
		}

		map.put("out_trade_no", orderNo);
		// map.put("sub_openid", "ohuPlv_38wfAgLA3N8rfwZgl1");
		map.put("body", body);
		map.put("total_fee", payAmount);
		map.put("mch_create_ip", "127.0.0.1");

		Map<String, String> params = SignUtils.paraFilter(map);
		StringBuilder buf = new StringBuilder((params.size() + 1) * 10);
		SignUtils.buildPayParams(buf, params, false);
		String preStr = buf.toString();
		String sign = SwiftMD5.sign(preStr, "&key=" + SwiftpassConfig.wap_key,
				"utf-8").toUpperCase();
		map.put("sign", sign);

		String reqUrl = SwiftpassConfig.wap_req_url;
		logger.debug("reqUrl：" + reqUrl);

		logger.debug("reqParams:" + XmlUtils.parseXML(map));
		CloseableHttpResponse response = null;
		CloseableHttpClient client = null;
		String res = null;
		Map<String, String> resultMap = null;
		JSONObject jsonObject = new JSONObject();
		try {
			HttpPost httpPost = new HttpPost(reqUrl);
			StringEntity entityParams = new StringEntity(
					XmlUtils.parseXML(map), "utf-8");
			httpPost.setEntity(entityParams);
			httpPost.setHeader("Content-Type", "text/xml;charset=ISO-8859-1");
			client = HttpClients.createDefault();
			response = client.execute(httpPost);

			if (response != null && response.getEntity() != null) {
				resultMap = XmlUtils.toMap(
						EntityUtils.toByteArray(response.getEntity()), "utf-8");
				res = XmlUtils.toXml(resultMap);
				logger.debug("请求结果：" + res);
				if (!SignUtils.checkParam(resultMap, SwiftpassConfig.wap_key)) {
					res = "验证签名不通过";
				} else {
					if ("0".equals(resultMap.get("status"))
							&& "0".equals(resultMap.get("result_code"))) {
						String code_img_url = resultMap.get("code_img_url");
						logger.debug("code_img_url : " + code_img_url);
						res = "ok";
						String pay_info = resultMap.get("pay_info");
						jsonObject.put("pay_info", pay_info);
						jsonObject.put("out_trade_no", map.get("out_trade_no"));
						jsonObject.put("total_fee", map.get("total_fee"));
						jsonObject.put("body", map.get("body"));
					}
				}
			} else {
				res = "操作失败";
			}
		} catch (Exception e) {
			logger.error("操作失败，原因：", e);
			res = "系统异常";
		} finally {
			if (response != null) {
				response.close();
			}
			if (client != null) {
				client.close();
			}
		}
		jsonObject.put("res", res);
		return jsonObject;
	}
	public static JSONObject getWeiXinPayInfo(String payAmount, String orderNo,
			String body,String openId) throws IOException {
		
//		if(StringUtils.isNotBlank(payAmount)){
//			BigDecimal amount = (new BigDecimal(payAmount)).multiply(new BigDecimal("100"));
//			payAmount = amount.setScale(0,BigDecimal.ROUND_DOWN).toString();
//		}
		
		SortedMap<String, String> map = new TreeMap();
		 map.put("service", "pay.weixin.jspay");
//	        map.put("version", version);
//	        map.put("charset", charset);
//	        map.put("sign_type", sign_type);
//	        
//	        map.put("mch_id", SwiftpassConfig.mch_id);
//	        map.put("notify_url", SwiftpassConfig.notify_url);
//	        map.put("nonce_str", String.valueOf(new Date().getTime()));
		
//		 String openId = WxServiceController.getWxOpenId(request);
		map.put("sub_openid", openId);
		map.put("version", version);
		map.put("charset", charset);
		map.put("sign_type", sign_type);
		map.put("is_raw", "1");
		
		map.put("mch_id", SwiftpassConfig.mch_id);
		map.put("notify_url", SwiftpassConfig.wap_notify_url);
		map.put("callback_url", SwiftpassConfig.wap_success_url);
		map.put("nonce_str", String.valueOf(new Date().getTime()));
		
		// 前台参数
		if(StringUtils.isBlank(orderNo)){
			java.text.SimpleDateFormat format = new java.text.SimpleDateFormat(
					"yyyyMMddHHmmss");
			String orderTime = format.format(new java.util.Date());
			java.text.DecimalFormat def = new java.text.DecimalFormat("000000");
			int b = (int) (Math.random() * 1000000 % 1000000);// 产生0-1000000的整数随机数
			String lstStr = def.format(b);
			orderNo = orderTime + lstStr;
		}
		
		map.put("out_trade_no", orderNo);
		// map.put("sub_openid", "ohuPlv_38wfAgLA3N8rfwZgl1");
		map.put("body", body);
		map.put("total_fee", payAmount);
		map.put("mch_create_ip", "127.0.0.1");
		
		Map<String, String> params = SignUtils.paraFilter(map);
		StringBuilder buf = new StringBuilder((params.size() + 1) * 10);
		SignUtils.buildPayParams(buf, params, false);
		String preStr = buf.toString();
		String sign = SwiftMD5.sign(preStr, "&key=" + SwiftpassConfig.key,
				"utf-8").toUpperCase();
		map.put("sign", sign);
		
		String reqUrl = SwiftpassConfig.wap_req_url;
		logger.debug("reqUrl：" + reqUrl);
		
		logger.debug("reqParams:" + XmlUtils.parseXML(map));
		CloseableHttpResponse response = null;
		CloseableHttpClient client = null;
		String res = null;
		Map<String, String> resultMap = null;
		JSONObject jsonObject = new JSONObject();
		try {
			HttpPost httpPost = new HttpPost(reqUrl);
			StringEntity entityParams = new StringEntity(
					XmlUtils.parseXML(map), "utf-8");
			httpPost.setEntity(entityParams);
			httpPost.setHeader("Content-Type", "text/xml;charset=ISO-8859-1");
			client = HttpClients.createDefault();
			response = client.execute(httpPost);
			
			if (response != null && response.getEntity() != null) {
				resultMap = XmlUtils.toMap(
						EntityUtils.toByteArray(response.getEntity()), "utf-8");
				res = XmlUtils.toXml(resultMap);
				logger.debug("请求结果：" + res);
				if (!SignUtils.checkParam(resultMap, SwiftpassConfig.key)) {
					res = "验证签名不通过";
				} else {
					if ("0".equals(resultMap.get("status"))
							&& "0".equals(resultMap.get("result_code"))) {
						String code_img_url = resultMap.get("code_img_url");
						logger.debug("code_img_url : " + code_img_url);
						logger.debug("==================pay_info : " +	resultMap.toString());
						res = "ok";
						String tokenId = resultMap.get("token_id");
						String payInfo ="https://pay.swiftpass.cn/pay/jspay?token_id=" +tokenId + "&showwxtitle=1";
						logger.debug("==================pay_info : " +	payInfo);
						
						jsonObject.put("pay_info", payInfo);
						jsonObject.put("out_trade_no", map.get("out_trade_no"));
						jsonObject.put("total_fee", map.get("total_fee"));
						jsonObject.put("body", map.get("body"));
					}
				}
			} else {
				res = "操作失败";
			}
		} catch (Exception e) {
			logger.error("操作失败，原因：", e);
			res = "系统异常";
		} finally {
			if (response != null) {
				response.close();
			}
			if (client != null) {
				client.close();
			}
		}
		jsonObject.put("res", res);
		return jsonObject;
	}
	
	
	/**
	 * 扫码支付
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public static Map<String,String> getSaomaPayInfo(String payAmount, String orderNo,String body,String openId) throws ServletException, IOException {
		
        SortedMap<String,String> map = new TreeMap<String,String>();
        
        
        map.put("attach", body);
		map.put("body", body);
		map.put("charset", "UTF-8");
		map.put("mch_create_ip", "127.0.0.1");
		map.put("out_trade_no", orderNo);
		map.put("service", "pay.weixin.native");
		map.put("sign_type", "MD5");
		map.put("total_fee", payAmount);
		map.put("version", "1.0");
		
		
        map.put("mch_id", SwiftpassConfig.mch_id);
        map.put("notify_url", SwiftpassConfig.notify_url);
        map.put("nonce_str", String.valueOf(new Date().getTime()));
        
        Map<String,String> params = SignUtils.paraFilter(map);
        StringBuilder buf = new StringBuilder((params.size() +1) * 10);
        SignUtils.buildPayParams(buf,params,false);
        String preStr = buf.toString();
        String sign = MD5.sign(preStr, "&key=" + SwiftpassConfig.key, "utf-8");
        map.put("sign", sign);
        
        String reqUrl = SwiftpassConfig.req_url;
        System.out.println("reqUrl：" + reqUrl);
        
        System.out.println("reqParams:" + XmlUtils.parseXML(map));
        CloseableHttpResponse response = null;
        CloseableHttpClient client = null;
        String res = null;
        Map<String,String> resultMap = new HashMap<String,String>();
        
        try {
            HttpPost httpPost = new HttpPost(reqUrl);
            StringEntity entityParams = new StringEntity(XmlUtils.parseXML(map),"utf-8");
            httpPost.setEntity(entityParams);
            httpPost.setHeader("Content-Type", "text/xml;charset=ISO-8859-1");
            client = HttpClients.createDefault();
            response = client.execute(httpPost);
            if(response != null && response.getEntity() != null){
                resultMap = XmlUtils.toMap(EntityUtils.toByteArray(response.getEntity()), "utf-8");
                System.out.println("请求结果：" + resultMap);
                res= "ok";
                //验证结果的签名
                if(resultMap.containsKey("sign")){
                    if(!SignUtils.checkParam(resultMap, SwiftpassConfig.key)){
                        res = "验证签名不通过";
                    }
                }
            }else{
                res = "操作失败";
            }
        } catch (Exception e) {
            e.printStackTrace();
            res = "系统异常";
        } finally {
            if(response != null){
                response.close();
            }
            if(client != null){
                client.close();
            }
        }
        
		
		resultMap.put("res", res);
        return resultMap;
    }

}

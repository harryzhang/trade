package com.redpack.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import net.sf.json.JSONObject;

public class HuilvUtils {
	
	private  static String baiduKey = "0d263364faa016e5f06075b69b799087";
	
	
	public static JSONObject getBiaduHuilv() {

		
		String httpUrl = "http://apis.baidu.com/apistore/currencyservice/currency";
		String httpArg = "fromCurrency=EUR&toCurrency=USD&amount=1";
		String result = request(httpUrl, httpArg);
		JSONObject json=JSONObject.fromObject(result);
		String retData = json.getString("retData");
		return JSONObject.fromObject(retData);
	}
    
    /**
     * @param urlAll
     *            :请求接口
     * @param httpArg
     *            :参数
     * @return 返回结果
     */
    public static String request(String httpUrl, String httpArg) {
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();
        httpUrl = httpUrl + "?" + httpArg;
        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            // 填入apikey到HTTP header
            connection.setRequestProperty("apikey", baiduKey);
            connection.connect();
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            result = sbf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
    	JSONObject jj = getBiaduHuilv();
    	System.out.println(jj.get("errMsg") +"=="+ jj.get("retData"));
	}
}

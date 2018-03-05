package com.redpack.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * <一句话功能简述>
 * <功能详细描述>配置信息
 * 
 * @author  Administrator
 * @version  [版本号, 2014-8-29]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class SwiftpassConfig {
    
    /**
     * 威富通交易密钥
     */
    public static String key ;
    
    /**
     * 威富通交易密钥
     */
    public static String wap_key ;
    
    /**
     * 威富通商户号
     */
    public static String mch_id;
    /**
     * 威富通微信WAP商户号
     */
    public static String wap_mch_id;
    
    /**
     * 威富通请求url
     */
    public static String req_url;
    public static String wap_req_url;
    
    /**
     * 通知url
     */
    public static String notify_url;
    public static String wap_notify_url;
    public static String wap_success_url;
    
    /**
     * 付款测试
     */
    public static String pay_test;
    
    
    public static String getByKey(String key){
    	return prop.getProperty(key);
    }
    
    private static final Properties prop = new Properties(); 
    
    static{
        InputStream in = SwiftpassConfig.class.getResourceAsStream("/config.properties");   
        try {   
            prop.load(in);   
            key = prop.getProperty("key").trim();   
            mch_id = prop.getProperty("mch_id").trim();   
            req_url = prop.getProperty("req_url").trim();   
            notify_url = prop.getProperty("notify_url").trim();   
            wap_key = prop.getProperty("wap_key").trim();   
            wap_mch_id = prop.getProperty("wap_mch_id").trim();   
            wap_req_url = prop.getProperty("wap_req_url").trim();   
            wap_notify_url = prop.getProperty("wap_notify_url").trim();   
            wap_success_url = prop.getProperty("wap_success_url").trim();   
            pay_test = prop.getProperty("pay_test").trim();   
        } catch (IOException e) {   
            e.printStackTrace();   
        } 
    }
}

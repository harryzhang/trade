package com.redpack.pay.controller;

import org.apache.log4j.Logger;

import com.redpack.pay.factory.ThirdPayServiceFactory;

public class MobileCheckUtil {

	private final static Logger logger = Logger.getLogger(MobileCheckUtil.class);
	
	/**Mobile HttpRequest User-Agent*/
	private static String MOBILE_USER_AGENTS[]=new String[]{
	          "Nokia",//诺基亚
	          "SAMSUNG",//三星手机
	          "MIDP-2",//j2me2.0
	          "SymbianOS",//塞班系统的， "MAUI",//MTK山寨机默认ua
	          "UNTRUSTED/1.0",//疑似山寨机的ua，基本可以确定还是手机
	          "Windows CE",//Windows CE
	          "iPhone",//iPhone
	          "iPad",//iPad
	          "Android",//Android
	          "BlackBerry",//BlackBerry
	          "UCWEB",//ucweb是否只给wap页面？ Nokia5800 XpressMusic/UCWEB7.5.0.66/50/999
	          "ucweb",//小写的ucweb,
	          "BREW",//很奇怪的ua
	          "J2ME",//,很奇怪的ua，只有J2ME四个字母
	          "YULONG",//宇龙手机
	          "YuLong",//还是宇龙
	          "COOLPAD",//宇龙酷派
	          "TIANYU",//天语手机
	          "TY-",//天语
	          "K-Touch",//还是天语
	          "Haier",//海尔手机
	          "DOPOD",//多普达手机
	          "Lenovo",//联想手机
	          "LENOVO",//联想手机
	          "HUAQIN",//华勤手机
	          "AIGO-",//爱国者居手机
	          "CTC/1.0",//含义不明
	          "CTC/2.0",//含义不明
	          "CMCC",//移动定制手机
	          "DAXIAN",//大显手机
	          "MOT-",//摩托罗拉
	          "SonyEricsson",//索爱手机
	          "GIONEE",//金立手机
	          "HTC",//HTC手机
	          "ZTE",//中兴手机
	          "HUAWEI",//华为手机
	          "webOS",//palm手机
	          "GoBrowser",//3g GoBrowser.User-Agent=Nokia5230/GoBrowser/2.0.290 Safari
	          "IEMobile",//Windows CE手机自带浏览器
	          "WAP2.0",//支持wap 2.0的
	};
	
	public static String getPayTypeByClient(String  reqUserAgent) {
		logger.info("请求user-agen:"+reqUserAgent);
    	if(reqUserAgent.indexOf("MicroMessenger")>=0){
    		logger.info("微信请求");
    		return  ThirdPayServiceFactory.WX_PAY;
    	}
    	boolean ismobile = checkMobile(reqUserAgent);
    	if(ismobile){
    		logger.info("移动端请求");
    		return ThirdPayServiceFactory.WAP_PAY;
    	}
    	
    	ismobile = isMobileDevice(reqUserAgent);
    	if(ismobile){
    		logger.info("移动端请求");
    		return ThirdPayServiceFactory.WAP_PAY;
    	}
    	
    	logger.info("PC端请求");
    	return  ThirdPayServiceFactory.SAOMA_PAY;
	}
	

	private static boolean isMobileDevice(String requestHeader) {
		/**
		 * android : 所有android设备 mac os : iphone ipad windows
		 * phone:Nokia等windows系统的手机
		 */
		String[] deviceArray = new String[] { "android", "mac os",	"windows phone", "iPhone","iPad" };
		if (requestHeader == null)
			return false;
		requestHeader = requestHeader.toLowerCase();
		for (int i = 0; i < deviceArray.length; i++) {
			if (requestHeader.indexOf(deviceArray[i]) > 0) {
				return true;
			}
		}
		return false;
	}
	 
	 
	private static boolean checkMobile(String userAgent) {
          for (String mobile : MOBILE_USER_AGENTS) {
                  if (userAgent.contains(mobile) || userAgent.contains(mobile.toUpperCase()) ||
                        userAgent.contains(mobile.toLowerCase())) {
                            return true;
                  }
          }
          return false;
	}
	
}
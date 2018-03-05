package com.redpack.utils;

import java.util.HashMap;
import java.util.Map;


public class IDUtil {
	
	private static final Map<String,Integer> localCacheMap = new HashMap<String,Integer>();
	
	public static String generateGroupCode(String pri) {
		synchronized (localCacheMap) {
			String date = DateUtil.getCurrentDateTime("yyyyMMdd");
	        String orderCodePri = pri+date;
			String key = "groupCode_"+pri+date;
			Integer cacheId = (Integer)localCacheMap.get(key);
			if(null == cacheId){
				cacheId = 1;
			}
			
			String code= StringUtil.fullString(cacheId,5);
			
			cacheId++;
			localCacheMap.put(key, cacheId);
			return orderCodePri+code;
		}
		
	}
	
	public static void main(String[] args) {
		System.out.println( IDUtil.generateGroupCode("GA") );
	}

}

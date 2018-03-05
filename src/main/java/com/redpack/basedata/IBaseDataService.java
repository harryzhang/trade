/*
 * Powered By  huangzl QQ: 272950754
 * Web Site:  
 * Since 2008 - 2016
 */

package com.redpack.basedata;

/**
 * @author  huangzl QQ: 272950754
 * @version 1.0
 * @since 1.0
 */

import java.util.List;
import java.util.Map;


public interface IBaseDataService{

	
	
	/**
	 * 查询省
	 */
	public List<Map<String,String>> getProvince(Map<String,Object> parameterMap);
	
	/**
	 * 查询市
	 */
	public List<Map<String,String>> getCity(Map<String,Object> parameterMap);
	

}

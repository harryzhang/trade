/*
 * Powered By  huangzl QQ: 272950754
 * Web Site:  
 * Since 2008 - 2016
 */

package com.redpack.basedata.dao;

/**
 * @author  huangzl QQ: 272950754
 * @version 1.0
 * @since 1.0
 */


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
@Repository
public interface IBaseDataDao {


	public List<Map<String, String>> getProvince(Map<String, Object> parameterMap);

	public List<Map<String, String>> getCity(Map<String, Object> parameterMap);

}

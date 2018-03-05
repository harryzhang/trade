/*
 * Powered By  huangzl QQ: 272950754
 * Web Site:  
 * Since 2008 - 2016
 */

package com.redpack.basedata.service;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redpack.basedata.IBaseDataService;
import com.redpack.basedata.dao.IBaseDataDao;

/**
 * @author  huangzl QQ: 272950754
 * @version 1.0
 * @since 1.0
 */


@Service("baseDataService")
public class BaseDataServiceImpl implements IBaseDataService {

	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
    private IBaseDataDao  baseDataDao;

	@Override
	public List<Map<String, String>> getProvince(
			Map<String, Object> parameterMap) {
		return baseDataDao.getProvince(parameterMap);
	}

	@Override
	public List<Map<String, String>> getCity(Map<String, Object> parameterMap) {
		return baseDataDao.getCity(parameterMap);
	}
	

}

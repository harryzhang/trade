/**
 * 
 */
package com.redpack.sms.service;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redpack.sms.ISysSmsService;
import com.redpack.sms.dao.ISysSmsDao;
import com.redpack.sms.model.SysSmsDo;

/**
 * 功能说明：系统消息
 * @author baishui
 * @2016年3月13日 上午8:57:19
 *
 * @com.redpack.service.sms.SmsServiceImpl.java
 */
@Service("sysSmsServiceImpl")
public class SysSmsServiceImpl implements ISysSmsService {

	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private ISysSmsDao sysSmsDao;

	@Override
	public SysSmsDo getById(int id) {
		return sysSmsDao.getById(id);
	}

	@Override
	public List<SysSmsDo> selectSysSms(Map<String, Object> parameterMap) {
		return sysSmsDao.selectSysSms(parameterMap);
	}

	@Override
	public int updateSysSmsById(SysSmsDo newSysSmsDo) {
		return sysSmsDao.updateSysSmsById(newSysSmsDo);
	}

	@Override
	public int addSysSms(SysSmsDo newSysSmsDo) {
		return sysSmsDao.addSysSms(newSysSmsDo);
	}

	@Override
	public int deleteById(int id) {
		return sysSmsDao.deleteById(id);
	}
	

}

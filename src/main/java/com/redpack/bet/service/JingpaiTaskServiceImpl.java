/*
 * Powered By  huangzl QQ: 272950754
 * Web Site:  
 * Since 2008 - 2016
 */

package com.redpack.bet.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.redpack.account.dao.IUserDao;
import com.redpack.bet.IJingpaiService;
import com.redpack.bet.IJingpaiTaskService;
import com.redpack.bet.dao.IJingpaiDao;
import com.redpack.bet.model.JingpaiDo;

/**
 * @author  huangzl QQ: 272950754
 * @version 1.0
 * @since 1.0
 */


@Service("jingpaiTaskService")
public class JingpaiTaskServiceImpl implements IJingpaiTaskService {

	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
    private IJingpaiDao  jingpaiDao;
	
	@Autowired
	IJingpaiService  jingpaiService;
	
	
	/**
	 * 判断是否成功
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void betSuccess() {
		Map<String,Object> sjpMap = new HashMap<String,Object>();
		sjpMap.put("status", 1);
		List<JingpaiDo> jingPainList = jingpaiDao.selectJingpai(sjpMap);
		
		for (JingpaiDo jpDo : jingPainList) {
			try{
				jingpaiService.betSuccess(jpDo);
			}catch(Exception e){
				logger.error("竞拍出错");
				e.printStackTrace();
			}
		}
		
	}



	/**
	 * 系统自动竞拍
	 */
	@Override
	public void autoBet() {

		Map<String,Object> sjpMap = new HashMap<String,Object>();
		sjpMap.put("status", 1);
		List<JingpaiDo> jingPainList = jingpaiDao.selectJingpai(sjpMap);
	
		for (JingpaiDo jpDo : jingPainList) {
			try{
				jingpaiService.autoBet(jpDo);
			}catch(Exception e){
				logger.error("系统自动拍出错");
				e.printStackTrace();
			}
		}
		
	}

}

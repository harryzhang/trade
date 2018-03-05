/*
 * Powered By  huangzl QQ: 272950754
 * Web Site:  
 * Since 2008 - 2016
 */

package com.redpack.huilv.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.redpack.base.result.IResult;
import com.redpack.base.result.ResultSupport;
import com.redpack.huilv.IHuilvService;
import com.redpack.huilv.dao.IHuilvDao;
import com.redpack.huilv.model.HuilvDo;
import com.redpack.utils.HuilvUtils;

import net.sf.json.JSONObject;

/**
 * @author  huangzl QQ: 272950754
 * @version 1.0
 * @since 1.0
 */


@Service("huilvService")
public class HuilvServiceImpl implements IHuilvService {

	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
    private IHuilvDao  huilvDao;
	/**
	 * 根据ID 查询
	 * @parameter id
	 */
	@Override
	public HuilvDo getById(int id){
	  return huilvDao.getById(id);
	}
	
	/**
	 *根据条件查询列表
	 */
	@Override
	public List<HuilvDo> selectHuilv(Map<String,Object> parameterMap){
		return huilvDao.selectHuilv(parameterMap);
	}
	
	/**
	 * 更新
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public IResult<?> updateHuilvById(HuilvDo newHuilvDo){
		logger.debug("updateHuilv(HuilvDo: "+newHuilvDo);
		int i =huilvDao.updateHuilvById(newHuilvDo);
		if(i<1){
			return ResultSupport.buildResult(1, newHuilvDo+"更新失败");
		}
		IResult<Integer> result = ResultSupport.buildResult(0);
		result.setModel(Integer.valueOf(i));
		return result;
	}
	
	/**
	 * 新增
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public IResult<?> addHuilv(HuilvDo newHuilvDo){
		logger.debug("addHuilv: "+newHuilvDo);
		int i =huilvDao.addHuilv(newHuilvDo);
		if(i<1){
			return ResultSupport.buildResult(1, newHuilvDo+"新增失败");
		}
		IResult<Integer> result = ResultSupport.buildResult(0);
		result.setModel(Integer.valueOf(i));
		return result;
	}
	
	/**
	 * 删除
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public IResult<?> deleteById(int id){
		logger.debug("deleteByIdHuilv:"+id);
		int i =huilvDao.deleteById(id);
		if(i<1){
			return ResultSupport.buildResult(1, id+"删除失败");
		}
		IResult<Integer> result = ResultSupport.buildResult(0);
		result.setModel(Integer.valueOf(i));
		return result;
	}

	@Override
	public void getHuilv() {

		JSONObject jos = HuilvUtils.getBiaduHuilv();
		HuilvDo hdo = new HuilvDo();
		Double currency  =(Double)jos.get("currency");
		BigDecimal cur = new BigDecimal(currency);
		cur.setScale(2, RoundingMode.HALF_UP);
		hdo.setCurrency(cur);
		hdo.setFromCurrency(jos.getString("fromCurrency"));
		hdo.setGetDate(new Date());
		hdo.setGetTime(new Date());
		hdo.setToCurrency(jos.getString("toCurrency"));
		hdo.setHuilvType("1");
		
		addHuilv(hdo);
	}

	@Override
	public Map<String, Object> selectMaxMin(Map<String, Object> parameterMap) {
		return huilvDao.selectMaxMin(parameterMap);
	}

}

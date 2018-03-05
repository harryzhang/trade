/*
 * Powered By  huangzl QQ: 272950754
 * Web Site:  
 * Since 2008 - 2016
 */

package com.redpack.goods.service;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.redpack.base.result.IResult;
import com.redpack.base.result.ResultSupport;
import com.redpack.goods.IGoodsPromService;
import com.redpack.goods.dao.IGoodsPromDao;
import com.redpack.goods.model.GoodsPromDo;

/**
 * @author  huangzl QQ: 272950754
 * @version 1.0
 * @since 1.0
 */


@Service("goodsPromService")
public class GoodsPromServiceImpl implements IGoodsPromService {

	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
    private IGoodsPromDao  goodsPromDao;
	/**
	 * 根据ID 查询
	 * @parameter id
	 */
	@Override
	public GoodsPromDo getById(int id){
	  return goodsPromDao.getById(id);
	}
	
	/**
	 *根据条件查询列表
	 */
	@Override
	public List<GoodsPromDo> selectGoodsProm(Map<String,Object> parameterMap){
		return goodsPromDao.selectGoodsProm(parameterMap);
	}
	
	/**
	 * 更新
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public IResult<?> updateGoodsPromById(GoodsPromDo newGoodsPromDo){
		logger.debug("updateGoodsProm(GoodsPromDo: "+newGoodsPromDo);
		int i =goodsPromDao.updateGoodsPromById(newGoodsPromDo);
		if(i<1){
			return ResultSupport.buildResult(1, newGoodsPromDo+"更新失败");
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
	public IResult<?> addGoodsProm(GoodsPromDo newGoodsPromDo){
		logger.debug("addGoodsProm: "+newGoodsPromDo);
		int i =goodsPromDao.addGoodsProm(newGoodsPromDo);
		if(i<1){
			return ResultSupport.buildResult(1, newGoodsPromDo+"新增失败");
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
		logger.debug("deleteByIdGoodsProm:"+id);
		int i =goodsPromDao.deleteById(id);
		if(i<1){
			return ResultSupport.buildResult(1, id+"删除失败");
		}
		IResult<Integer> result = ResultSupport.buildResult(0);
		result.setModel(Integer.valueOf(i));
		return result;
	}

}

/*
 * Powered By  huangzl QQ: 272950754
 * Web Site:  
 * Since 2008 - 2016
 */

package com.redpack.goods.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.redpack.base.result.IResult;
import com.redpack.base.result.ResultSupport;
import com.redpack.goods.IGoodsService;
import com.redpack.goods.dao.IGoodsDao;
import com.redpack.goods.dao.IGoodsPromDao;
import com.redpack.goods.model.GoodsDo;
import com.redpack.goods.model.GoodsPromDo;
import com.redpack.utils.DateUtil;

/**
 * @author  huangzl QQ: 272950754
 * @version 1.0
 * @since 1.0
 */


@Service("goodsService")
public class GoodsServiceImpl implements IGoodsService {

	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
    private IGoodsDao  goodsDao;
	
	@Autowired
    private IGoodsPromDao  goodsPromDao;
	
	/**
	 * 根据ID 查询
	 * @parameter id
	 */
	@Override
	public GoodsDo getById(Long id){
	  return goodsDao.getById(id);
	}
	
	/**
	 *根据条件查询列表
	 */
	@Override
	public List<GoodsDo> selectGoods(Map<String,Object> parameterMap){
		return goodsDao.selectGoods(parameterMap);
	}
	
	/**
	 * 更新
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public IResult<?> updateGoodsById(GoodsDo newGoodsDo){
		logger.debug("updateGoods(GoodsDo: "+newGoodsDo);
		int i =goodsDao.updateGoodsById(newGoodsDo);
		if(i<1){
			return ResultSupport.buildResult(1, newGoodsDo+"更新失败");
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
	public IResult<?> addGoods(GoodsDo newGoodsDo){
		logger.debug("addGoods: "+newGoodsDo);
		int i =goodsDao.addGoods(newGoodsDo);
		if(i<1){
			return ResultSupport.buildResult(1, newGoodsDo+"新增失败");
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
		logger.debug("deleteByIdGoods:"+id);
		int i =goodsDao.deleteById(id);
		if(i<1){
			return ResultSupport.buildResult(1, id+"删除失败");
		}
		IResult<Integer> result = ResultSupport.buildResult(0);
		result.setModel(Integer.valueOf(i));
		return result;
	}

	@Override
	public GoodsDo getKuangJi() {
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("gcode", "G_KUANGJI");
		parameterMap.put("status", "1");
		List<GoodsDo> gLst = this.selectGoods(parameterMap);
		if(null != gLst && gLst.size()>0){
			GoodsDo  g = gLst.get(0);
			//查看是否有促销价 
			parameterMap.clear();
			parameterMap.put("goodsId", g.getGoodsId());
			parameterMap.put("status", 1);
			List<GoodsPromDo> gPriceLst = goodsPromDao.selectGoodsProm(parameterMap);
			BigDecimal price = getCurrentGoodsPrice(gPriceLst);
			if(null != price){
				g.setPrice(price);
			}
			return g;
		}
		
		return null;
	}
	
	private BigDecimal getCurrentGoodsPrice(List<GoodsPromDo> gPriceLst){
		if(null == gPriceLst){
			return null;
		}
		long currentDate;
		try {
			currentDate = DateUtil.getTimeCur("yyyy-MM-dd", new Date());
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		for(GoodsPromDo gp : gPriceLst){
			long startDate = gp.getStartTime().getTime();
			long endDate = gp.getEndTime().getTime();
			if( startDate <= currentDate && endDate>=currentDate){
				return gp.getPrice();
				
			}
		}
		return null;		
	}

}

/*
 * Powered By  huangzl QQ: 272950754
 * Web Site:  
 * Since 2008 - 2016
 */

package com.redpack.goods;

/**
 * @author  huangzl QQ: 272950754
 * @version 1.0
 * @since 1.0
 */

import java.util.List;
import java.util.Map;

import com.redpack.base.result.IResult;
import com.redpack.goods.model.GoodsPromDo;


public interface IGoodsPromService{

	/**
	 * 根据ID 查询
	 * @parameter id
	 */
	public GoodsPromDo getById(int id);
	
	/**
	 *根据条件查询列表
	 */
	public List<GoodsPromDo> selectGoodsProm(Map<String,Object> parameterMap);
	
	/**
	 * 更新
	 */
	public IResult<?>  updateGoodsPromById(GoodsPromDo newGoodsPromDo);
	
	/**
	 * 新增
	 */
	public IResult<?> addGoodsProm(GoodsPromDo newGoodsPromDo);
	
	/**
	 * 删除
	 */
	public IResult<?> deleteById(int id);
}

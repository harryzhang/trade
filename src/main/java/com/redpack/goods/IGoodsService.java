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
import com.redpack.goods.model.GoodsDo;


public interface IGoodsService{

	/**
	 * 根据ID 查询
	 * @parameter id
	 */
	public GoodsDo getById(Long id);
	
	/**
	 *根据条件查询列表
	 */
	public List<GoodsDo> selectGoods(Map<String,Object> parameterMap);
	
	/**
	 * 更新
	 */
	public IResult<?>  updateGoodsById(GoodsDo newGoodsDo);
	
	/**
	 * 新增
	 */
	public IResult<?> addGoods(GoodsDo newGoodsDo);
	
	/**
	 * 删除
	 */
	public IResult<?> deleteById(int id);

	/**
	 * 获取商品 矿机
	 * @return
	 */
	public GoodsDo getKuangJi();
}

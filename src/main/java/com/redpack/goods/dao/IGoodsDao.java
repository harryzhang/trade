/*
 * Powered By  huangzl QQ: 272950754
 * Web Site:  
 * Since 2008 - 2016
 */

package com.redpack.goods.dao;

/**
 * @author  huangzl QQ: 272950754
 * @version 1.0
 * @since 1.0
 */


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.redpack.goods.model.GoodsDo;
@Repository
public interface IGoodsDao {

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
	public int  updateGoodsById(GoodsDo newGoodsDo);
	
	/**
	 * 新增
	 */
	public int addGoods(GoodsDo newGoodsDo);
	
	/**
	 * 删除
	 */
	public int deleteById(int id);

}

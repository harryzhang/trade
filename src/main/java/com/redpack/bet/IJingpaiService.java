/*
 * Powered By  huangzl QQ: 272950754
 * Web Site:  
 * Since 2008 - 2016
 */

package com.redpack.bet;

import java.math.BigDecimal;

/**
 * @author  huangzl QQ: 272950754
 * @version 1.0
 * @since 1.0
 */

import java.util.List;
import java.util.Map;

import com.redpack.base.result.IResult;
import com.redpack.bet.model.JingpaiDo;



public interface IJingpaiService{

	/**
	 * 根据ID 查询
	 * @parameter id
	 */
	public JingpaiDo getById(int id);
	
	/**
	 *根据条件查询列表
	 */
	public List<JingpaiDo> selectJingpai(Map<String,Object> parameterMap);
	
	/**
	 * 更新
	 */
	public IResult<?>  updateJingpaiById(JingpaiDo newJingpaiDo);
	
	/**
	 * 新增
	 */
	public IResult<?> addJingpai(JingpaiDo newJingpaiDo);
	
	/**
	 * 删除
	 */
	public IResult<?> deleteById(int id);

	/**
	 * 获取竞拍商品信息
	 * @param param
	 * @return
	 */
	public List<Map<String, Object>> selectGoodsJingpai(Map param);

	/**
	 * 用户竞彩
	 * @param jpDo
	 * @param userId
	 * @return
	 */
	public BigDecimal userJingpai(JingpaiDo jpDo, long userId);

	/**
	 * 判断系统需自动下注 还是竞拍成功
	 * @param jpDo 
	 */
	public void betSuccess(JingpaiDo jpDo);

	/**
	 * 系统自动竞拍
	 * @param jpDo 
	 */
	public void autoBet(JingpaiDo jpDo);
}

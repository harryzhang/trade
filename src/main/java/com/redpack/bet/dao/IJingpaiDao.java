/*
 * Powered By  huangzl QQ: 272950754
 * Web Site:  
 * Since 2008 - 2016
 */

package com.redpack.bet.dao;

/**
 * @author  huangzl QQ: 272950754
 * @version 1.0
 * @since 1.0
 */


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.redpack.bet.model.JingpaiDo;

@Repository
public interface IJingpaiDao {

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
	public int  updateJingpaiById(JingpaiDo newJingpaiDo);
	
	/**
	 * 用户竞拍的时候调用
	 * @param newJingpaiDo
	 * @return
	 */
	int updateJingPaiPerson(JingpaiDo newJingpaiDo);
	
	
	/**
	 * 新增
	 */
	public int addJingpai(JingpaiDo newJingpaiDo);
	
	/**
	 * 竞拍成功信息添加
	 */
	public int addJingpaiSuccess(Map<String,Object> parameterMap);
	
	/**
	 * 删除
	 */
	public int deleteById(int id);

	/**
	 * 获取商品信息
	 * @param param
	 * @return
	 */
	public List<Map<String, Object>> selectGoodsJingpai(Map param);

}

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

import com.redpack.bet.model.BetResultDo;
@Repository
public interface IBetResultDao {

	/**
	 * 根据ID 查询
	 * @parameter id
	 */
	public BetResultDo getById(int id);
	
	/**
	 *根据条件查询列表
	 */
	public List<BetResultDo> selectBetResult(Map<String,Object> parameterMap);
	
	/**
	 * 更新
	 */
	public int  updateBetResultById(BetResultDo newBetResultDo);
	
	/**
	 * 新增
	 */
	public int addBetResult(BetResultDo newBetResultDo);
	
	/**
	 * 删除
	 */
	public int deleteById(int id);

	/**
	 * 获取最近的开奖周期
	 * @return
	 */
	public Integer getMaxPeriod();
	/**
	 * 获取用户的开奖时间
	 * @return
	 */
	public List<Map<String, Object>> selectUserResult(Map<String,Object> parameterMap);

}

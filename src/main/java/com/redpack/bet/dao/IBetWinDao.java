package com.redpack.bet.dao;

/**
 * @author  huangzl QQ: 272950754
 * @version 1.0
 * @since 1.0
 */


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.redpack.bet.model.BetWinDo;
@Repository
public interface IBetWinDao {

	/**
	 * 根据ID 查询
	 * @parameter id
	 */
	public BetWinDo getById(int id);
	
	/**
	 *根据条件查询列表
	 */
	public List<BetWinDo> selectBetWin(Map<String,Object> parameterMap);
	
	/**
	 * 更新
	 */
	public int  updateBetWinById(BetWinDo newBetWinDo);
	
	/**
	 * 新增
	 */
	public int addBetWin(BetWinDo newBetWinDo);
	
	/**
	 * 删除
	 */
	public int deleteById(int id);

}

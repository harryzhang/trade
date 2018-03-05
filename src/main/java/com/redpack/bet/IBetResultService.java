

package com.redpack.bet;

/**
 * @author  huangzl QQ: 272950754
 * @version 1.0
 * @since 1.0
 */

import java.util.List;
import java.util.Map;

import com.redpack.base.result.IResult;
import com.redpack.bet.model.BetResultDo;


public interface IBetResultService{

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
	public IResult<?>  updateBetResultById(BetResultDo newBetResultDo);
	
	/**
	 * 新增
	 */
	public IResult<?> addBetResult(BetResultDo newBetResultDo);
	
	/**
	 * 删除
	 */
	public IResult<?> deleteById(int id);
	
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
	
	public Boolean openGame(int period);
}

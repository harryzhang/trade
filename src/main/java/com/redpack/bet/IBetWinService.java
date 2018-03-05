
package com.redpack.bet;

/**
 * @author  huangzl QQ: 272950754
 * @version 1.0
 * @since 1.0
 */

import java.util.List;
import java.util.Map;

import com.redpack.base.result.IResult;
import com.redpack.bet.model.BetWinDo;


public interface IBetWinService{

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
	public IResult<?>  updateBetWinById(BetWinDo newBetWinDo);
	
	/**
	 * 新增
	 */
	public IResult<?> addBetWin(BetWinDo newBetWinDo);
	
	/**
	 * 删除
	 */
	public IResult<?> deleteById(int id);
}

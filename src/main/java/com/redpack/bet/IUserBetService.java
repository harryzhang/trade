

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
import com.redpack.bet.model.UserBetDo;


public interface IUserBetService{

	/**
	 * 根据ID 查询
	 * @parameter id
	 */
	public UserBetDo getById(int id);
	
	/**
	 *根据条件查询列表
	 */
	public List<UserBetDo> selectUserBet(Map<String,Object> parameterMap);
	
	/**
	 * 更新
	 */
	public IResult<?>  updateUserBetById(UserBetDo newUserBetDo);
	
	/**
	 * 新增
	 */
	public IResult<?> addUserBet(UserBetDo newUserBetDo);
	
	/**
	 * 删除
	 */
	public IResult<?> deleteById(int id);

	/**
	 * 扣除用户积
	 * @param amount
	 */
	public void addBet(BigDecimal amount,long userId);
	/**
	 * 扣除用户积
	 * @param amount
	 */
	public void addBet(BigDecimal amount,long userId,String accType);
}

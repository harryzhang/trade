
package com.redpack.bet.dao;

/**
 * @author  huangzl QQ: 272950754
 * @version 1.0
 * @since 1.0
 */


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.redpack.bet.model.UserBetDo;
@Repository
public interface IUserBetDao {

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
	public int  updateUserBetById(UserBetDo newUserBetDo);
	
	/**
	 * 新增
	 */
	public int addUserBet(UserBetDo newUserBetDo);
	
	/**
	 * 删除
	 */
	public int deleteById(int id);

	public List<Map<String,Object>> selectSumUserBet(Map paramMap);

	
	public String getBetGood(int period);

}

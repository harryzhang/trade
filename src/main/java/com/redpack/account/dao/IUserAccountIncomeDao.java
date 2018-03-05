/*
 * Powered By  huangzl QQ: 272950754
 * Web Site:  
 * Since 2008 - 2016
 */

package com.redpack.account.dao;

/**
 * @author  huangzl QQ: 272950754
 * @version 1.0
 * @since 1.0
 */


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.redpack.account.model.UserAccountIncomeDo;
@Repository
public interface IUserAccountIncomeDao {

	/**
	 * 根据ID 查询
	 * @parameter id
	 */
	public UserAccountIncomeDo getById(Long id);
	
	/**
	 *根据条件查询列表
	 */
	public List<UserAccountIncomeDo> selectUserAccount(Map<String,Object> parameterMap);
	
	/**
	 * 更新
	 */
	public int  updateUserAccountById(UserAccountIncomeDo newUserAccountIncomeDo);
	
	
	/**
	 * 积分转让更新积分
	 */
	public int  updateUserPointById(UserAccountIncomeDo newUserAccountIncomeDo);
	
	
	/**
	 * 新增
	 */
	public int addUserAccount(UserAccountIncomeDo newUserAccountIncomeDo);
	
	/**
	 * 删除
	 */
	public int deleteById(int id);

}

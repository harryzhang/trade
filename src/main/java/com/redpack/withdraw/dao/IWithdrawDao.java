/*
 * Powered By  huangzl QQ: 272950754
 * Web Site:  
 * Since 2008 - 2016
 */

package com.redpack.withdraw.dao;

/**
 * @author  huangzl QQ: 272950754
 * @version 1.0
 * @since 1.0
 */


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.redpack.withdraw.model.WithdrawDo;

@Repository
public interface IWithdrawDao {

	/**
	 * 根据ID 查询
	 * @parameter id
	 */
	public WithdrawDo getById(int id);
	
	/**
	 *根据条件查询列表
	 */
	public List<WithdrawDo> selectWithdraw(Map<String,Object> parameterMap);
	
	/**
	 * 更新
	 */
	public int  updateWithdrawById(WithdrawDo newWithdrawDo);
	
	/**
	 * 新增
	 */		   
	public int addWithdraw(WithdrawDo newWithdrawDo);
	
	/**
	 * 删除
	 */
	public int deleteById(int id);

}

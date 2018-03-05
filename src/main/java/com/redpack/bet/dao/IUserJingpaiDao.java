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

import com.redpack.bet.model.UserJingpaiDo;
@Repository
public interface IUserJingpaiDao {

	/**
	 * 根据ID 查询
	 * @parameter id
	 */
	public UserJingpaiDo getById(int id);
	
	/**
	 *根据条件查询列表
	 */
	public List<UserJingpaiDo> selectUserJingpai(Map<String,Object> parameterMap);
	
	/**
	 * 更新
	 */
	public int  updateUserJingpaiById(UserJingpaiDo newUserJingpaiDo);
	
	/**
	 * 新增
	 */
	public int addUserJingpai(UserJingpaiDo newUserJingpaiDo);
	
	/**
	 * 删除
	 */
	public int deleteById(int id);

}

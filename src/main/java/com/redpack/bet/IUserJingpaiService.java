/*
 * Powered By  huangzl QQ: 272950754
 * Web Site:  
 * Since 2008 - 2016
 */

package com.redpack.bet;

/**
 * @author  huangzl QQ: 272950754
 * @version 1.0
 * @since 1.0
 */

import java.util.List;
import java.util.Map;

import com.redpack.base.result.IResult;
import com.redpack.bet.model.UserJingpaiDo;


public interface IUserJingpaiService{

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
	public IResult<?>  updateUserJingpaiById(UserJingpaiDo newUserJingpaiDo);
	
	/**
	 * 新增
	 */
	public IResult<?> addUserJingpai(UserJingpaiDo newUserJingpaiDo);
	
	/**
	 * 删除
	 */
	public IResult<?> deleteById(int id);
}

/*
 * Powered By  huangzl QQ: 272950754
 * Web Site:  
 * Since 2008 - 2015
 */

package com.redpack.group;

/**
 * @author  huangzl QQ: 272950754
 * @version 1.0
 * @since 1.0
 */

import java.util.List;
import java.util.Map;



import com.redpack.account.model.UserDo;
import com.redpack.base.result.IResult;
import com.redpack.grade.model.GroupUserDo;


public interface IGroupUserService{

	/**
	 * 根据ID 查询
	 * @parameter id
	 */
	public GroupUserDo getById(int id);
	
	/**
	 *根据条件查询列表
	 */
	public List<GroupUserDo> selectGroupUser(Map<String,Object> parameterMap);
	
	/**
	 * 更新
	 */
	public IResult<?>  updateGroupUserById(GroupUserDo newGroupUserDo);
	
	/**
	 * 新增
	 */
	public IResult<?> addGroupUser(GroupUserDo newGroupUserDo);
	
	/**
	 * 删除
	 */
	public IResult<?> deleteById(int id);

	/**
	 * 获取用户的所有子级
	 * @param rootDao
	 */
	public UserDo getAllChildRen(UserDo rootDao);
	
	public Map getUserGroupByNetwork(Map<String,Object> param);

	public List<Map<String, Object>> listGroup(Map<String, Object> queryMap);
	
	/**
	 * 从一个组里删除
	 */
	public void delGroupUserByUserId(String user1Mobile);
}

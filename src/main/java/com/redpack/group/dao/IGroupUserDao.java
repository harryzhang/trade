/*
 * Powered By  huangzl QQ: 272950754
 * Web Site:  
 * Since 2008 - 2015
 */

package com.redpack.group.dao;

/**
 * @author  huangzl QQ: 272950754
 * @version 1.0
 * @since 1.0
 */


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.redpack.account.model.UserDo;
import com.redpack.grade.model.GroupUserDo;
@Repository
public interface IGroupUserDao {

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
	 *根据条件查询团队是否有空的位置
	 */
	public List<GroupUserDo> selectGroupNullUser(Map<String,Object> parameterMap);
	
	/**
	 * 更新
	 */
	public int  updateGroupUserById(GroupUserDo newGroupUserDo);
	
	/**
	 * 删除现有组织树里用户的位置
	 * @param parameterMap
	 * @return
	 */
	public int  delGroupUserByUserId(Map<String,Object> parameterMap);
	
	/**
	 * 新增
	 */
	public int addGroupUser(GroupUserDo newGroupUserDo);
	
	/**
	 * 删除
	 */
	public int deleteById(int id);

	/**
	 * 根据用户id更新用户组的关系
	 * @param id
	 */
	public void updateGroupUserByUserId(Map<String,Object> parameterMap);
	
	/**
	 * 根据用户父id更新用户组的关系
	 * @param id
	 */
	public void updateGroupUserByParentId(Map<String,Object> parameterMap);

	/**
	 * 根据组名失效组和用户关系
	 * @param groupName
	 */
	public void unvalidGroupUserByGroupName(String groupName);
	
	public Map getUserGroupByNetwork(Map<String,Object> param);

	public List<Map<String, Object>> listGroup(Map<String, Object> queryMap);

}

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

import com.redpack.grade.model.GroupDo;
@Repository
public interface IGroupDao {

	/**
	 * 根据ID 查询
	 * @parameter id
	 */
	public GroupDo getById(int id);
	
	/**
	 *根据条件查询列表
	 */
	public List<GroupDo> selectGroup(Map<String,Object> parameterMap);
	
	/**
	 * 更新
	 */
	public int  updateGroupById(GroupDo newGroupDo);
	
	/**
	 * 新增
	 */
	public int addGroup(GroupDo newGroupDo);
	
	/**
	 * 删除
	 */
	public int deleteById(int id);
	
	/**
	 * 找出当前用户所在的组
	 * @param userId
	 * @return
	 */
	public  GroupDo selectGroupByUserId(Long userId);

}

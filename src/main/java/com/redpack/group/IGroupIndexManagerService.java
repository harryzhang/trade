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

import com.redpack.base.result.IResult;
import com.redpack.grade.model.GroupIndexManagerDo;


public interface IGroupIndexManagerService{

	/**
	 * 根据ID 查询
	 * @parameter id
	 */
	public GroupIndexManagerDo getById(int id);
	
	/**
	 *根据条件查询列表
	 */
	public List<GroupIndexManagerDo> selectGroupIndexManager(Map<String,Object> parameterMap);
	
	/**
	 * 更新
	 */
	public IResult<?>  updateGroupIndexManagerById(GroupIndexManagerDo newGroupIndexManagerDo);
	
	/**
	 * 新增
	 */
	public IResult<?> addGroupIndexManager(GroupIndexManagerDo newGroupIndexManagerDo);
	
	/**
	 * 删除
	 */
	public IResult<?> deleteById(int id);
}

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

import com.redpack.grade.model.GroupIndexManagerDo;
@Repository
public interface IGroupIndexManagerDao {

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
	public int  updateGroupIndexManagerById(GroupIndexManagerDo newGroupIndexManagerDo);
	
	/**
	 * 新增
	 */
	public int addGroupIndexManager(GroupIndexManagerDo newGroupIndexManagerDo);
	
	/**
	 * 删除
	 */
	public int deleteById(int id);

	/**
	 * 获取下一个坐标位置
	 * @param nextIdex
	 * @return
	 */
	public GroupIndexManagerDo selectGroupIndexByCurrentIdx(String nextIdex);

	/**
	 * 根据当前坐标位置找出下级的坐标位置
	 * @param groupIndex
	 * @return
	 */
	public List<GroupIndexManagerDo> selectChildIndexByCurrentIndex(
			String groupIndex);

}

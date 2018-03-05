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
import com.redpack.grade.model.GroupDo;


public interface IGroupService{

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
	public IResult<?>  updateGroupById(GroupDo newGroupDo);
	
	/**
	 * 新增
	 */
	public IResult<?> addGroup(GroupDo newGroupDo);
	
	/**
	 * 删除
	 */
	public IResult<?> deleteById(int id);

	/**
	 * 申请出局
	 * @param userId
	 * @param 分盘的组
	 * @return
	 */
	public IResult applyGroupGrade(long userId,GroupDo fenpanGroup);
	
	/**
	 * B网申请出局
	 * @param userId
	 * @param 分盘的组
	 * @return
	 */
	public IResult applyGroupGradeForB(long userId,GroupDo fenpanGroup);
}

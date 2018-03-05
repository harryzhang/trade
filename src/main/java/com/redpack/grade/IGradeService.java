/*
 * Powered By zhangyunhua
 * Web Site:  
 * Since 2008 - 2015
 */

package com.redpack.grade;

/**
 * @author zhangyunhua
 * @version 1.0
 * @since 1.0
 */

import java.util.List;
import java.util.Map;

import com.redpack.grade.model.GradeDo;


public interface IGradeService{

	/**
	 * 根据ID 查询
	 * @parameter id
	 */
	public GradeDo getById(int id);
	
	/**
	 *根据条件查询列表
	 */
	public List<GradeDo> selectGrade(Map<String,Object> parameterMap);
	
	/**
	 * 更新
	 */
	public int  updateGradeById(GradeDo newGradeDo);
	
	/**
	 * 新增
	 */
	public int addGrade(GradeDo newGradeDo);
	
	/**
	 * 删除
	 */
	public int deleteById(int id);
}

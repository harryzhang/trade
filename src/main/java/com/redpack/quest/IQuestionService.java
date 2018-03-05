/*
 * Powered By zhangyunhua
 * Web Site:  
 * Since 2008 - 2015
 */

package com.redpack.quest;

/**
 * @author zhangyunhua
 * @version 1.0
 * @since 1.0
 */

import java.util.List;
import java.util.Map;

import com.redpack.quest.model.QuestionDo;


public interface IQuestionService{

	/**
	 * 根据ID 查询
	 * @parameter id
	 */
	public QuestionDo getById(int id);
	
	/**
	 *根据条件查询列表
	 */
	public List<QuestionDo> selectQuestion(Map<String,Object> parameterMap);
	
	/**
	 *根据条件查询列表
	 */
	public List<Map> selectFeedback(Map<String,Object> parameterMap);
	
	/**
	 * 更新
	 */
	public int  updateQuestionById(QuestionDo newQuestionDo);
	
	/**
	 * 新增
	 */
	public int addQuestion(QuestionDo newQuestionDo);
	
	/**
	 * 删除
	 */
	public int deleteById(int id);
}

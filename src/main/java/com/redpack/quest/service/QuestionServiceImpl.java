/*
 * Powered By zhangyunhua
 * Web Site:  
 * Since 2008 - 2015
 */

package com.redpack.quest.service;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redpack.quest.IQuestionService;
import com.redpack.quest.dao.IQuestionDao;
import com.redpack.quest.model.QuestionDo;

/**
 * @author zhangyunhua
 * @version 1.0
 * @since 1.0
 */


@Service("questionService")
public class QuestionServiceImpl implements IQuestionService {

	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired	
    private IQuestionDao  questionDao;

	/**
	 * 根据ID 查询
	 * @parameter id
	 */
	public QuestionDo getById(int id){
	  return questionDao.getById(id);
	}
	
	/**
	 *根据条件查询列表
	 */
	public List<QuestionDo> selectQuestion(Map<String,Object> parameterMap){
		return questionDao.selectQuestion(parameterMap);
	}
	
	/**
	 * 更新
	 */
	public int  updateQuestionById(QuestionDo newQuestionDo){
		return questionDao.updateQuestionById(newQuestionDo);
	}
	
	/**
	 * 新增
	 */
	public int addQuestion(QuestionDo newQuestionDo){
		return questionDao.addQuestion(newQuestionDo);
	}
	
	/**
	 * 删除
	 */
	public int deleteById(int id){
		return questionDao.deleteById(id);
	}

	@Override
	public List<Map> selectFeedback(Map<String, Object> parameterMap) {
		// TODO Auto-generated method stub
		return  questionDao.selectFeedback(parameterMap);
	}

}

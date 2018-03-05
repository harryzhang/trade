/*
 * Powered By zhangyunhua
 * Web Site:  
 * Since 2008 - 2015
 */

package com.redpack.grade.service;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redpack.grade.IGradeService;
import com.redpack.grade.dao.IGradeDao;
import com.redpack.grade.model.GradeDo;

/**
 * @author zhangyunhua
 * @version 1.0
 * @since 1.0
 */


@Service("gradeService")
public class GradeServiceImpl implements IGradeService {

	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired	
    private IGradeDao  gradeDao;

	/**
	 * 根据ID 查询
	 * @parameter id
	 */
	public GradeDo getById(int id){
	  return gradeDao.getById(id);
	}
	
	/**
	 *根据条件查询列表
	 */
	public List<GradeDo> selectGrade(Map<String,Object> parameterMap){
		return gradeDao.selectGrade(parameterMap);
	}
	
	/**
	 * 更新
	 */
	public int  updateGradeById(GradeDo newGradeDo){
		return gradeDao.updateGradeById(newGradeDo);
	}
	
	/**
	 * 新增
	 */
	public int addGrade(GradeDo newGradeDo){
		return gradeDao.addGrade(newGradeDo);
	}
	
	/**
	 * 删除
	 */
	public int deleteById(int id){
		return gradeDao.deleteById(id);
	}

}

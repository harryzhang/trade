/*
 * Powered By zhangyunhua
 * Web Site:  
 * Since 2008 - 2015
 */

package com.redpack.grade.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.redpack.grade.IGradeFeeService;
import com.redpack.grade.dao.IGradeFeeDao;
import com.redpack.grade.model.GradeFeeDo;

/**
 * @author zhangyunhua
 * @version 1.0
 * @since 1.0
 */


@Service("gradeFeeService")
public class GradeFeeServiceImpl implements IGradeFeeService {

	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired	
    private IGradeFeeDao  gradeFeeDao;

	/**
	 * 根据ID 查询
	 * @parameter id
	 */
	public GradeFeeDo getById(int id){
	  return gradeFeeDao.getById(id);
	}
	
	/**
	 *根据条件查询列表
	 */
	public List<GradeFeeDo> selectGradeFee(Map<String,Object> parameterMap){
		return gradeFeeDao.selectGradeFee(parameterMap);
	}
	
	/**
	 * 更新
	 */
	public int  updateGradeFeeById(GradeFeeDo newGradeFeeDo){
		return gradeFeeDao.updateGradeFeeById(newGradeFeeDo);
	}
	
	/**
	 * 新增
	 */
	public int addGradeFee(GradeFeeDo newGradeFeeDo){
		return gradeFeeDao.addGradeFee(newGradeFeeDo);
	}
	
	/**
	 * 删除
	 */
	public int deleteById(int id){
		return gradeFeeDao.deleteById(id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public int updateGradeFeeByGrade(GradeFeeDo newGradeFeeDo) {
		Map<String, Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("beforeUpgrade", newGradeFeeDo.getBeforeUpgrade());
		parameterMap.put("afterUpgrade", newGradeFeeDo.getAfterUpgrade());
		List<GradeFeeDo> gradeFeeList = gradeFeeDao.selectGradeFee(parameterMap );
		if(null == gradeFeeList || gradeFeeList.size()<1 ){
			return gradeFeeDao.addGradeFee(newGradeFeeDo);
		}else{
			newGradeFeeDo.setId(gradeFeeList.get(0).getId());
			return gradeFeeDao.updateGradeFeeById(newGradeFeeDo);
		}
	}

}

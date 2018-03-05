/*
 * Powered By  huangzl QQ: 272950754
 * Web Site:  
 * Since 2008 - 2016
 */

package com.redpack.account.service;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.redpack.account.dao.IKangjiBuyIncomeDao;
import com.redpack.account.faced.IKangjiBuyIncomeService;
import com.redpack.account.model.KangjiBuyIncomeDo;
import com.redpack.base.result.IResult;
import com.redpack.base.result.ResultSupport;

/**
 * @author  huangzl QQ: 272950754
 * @version 1.0
 * @since 1.0
 */


@Service("kangjiBuyIncomeService")
public class KangjiBuyIncomeServiceImpl implements IKangjiBuyIncomeService {

	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
    private IKangjiBuyIncomeDao  kangjiBuyIncomeDao;
	/**
	 * 根据ID 查询
	 * @parameter id
	 */
	@Override
	public KangjiBuyIncomeDo getById(int id){
	  return kangjiBuyIncomeDao.getById(id);
	}
	
	public List<KangjiBuyIncomeDo> getUserGradeIncome(long userId){
		  return kangjiBuyIncomeDao.getUserGradeIncome(userId);
	}
	
	/**
	 *根据条件查询列表
	 */
	@Override
	public List<KangjiBuyIncomeDo> selectKangjiBuyIncome(Map<String,Object> parameterMap){
		return kangjiBuyIncomeDao.selectKangjiBuyIncome(parameterMap);
	}
	
	/**
	 * 更新
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public int updateKangjiBuyIncomeById(KangjiBuyIncomeDo newKangjiBuyIncomeDo){
		logger.debug("updateKangjiBuyIncome(KangjiBuyIncomeDo: "+newKangjiBuyIncomeDo);
		return kangjiBuyIncomeDao.updateKangjiBuyIncomeById(newKangjiBuyIncomeDo);
		
	}
	
	/**
	 * 新增
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public int  addKangjiBuyIncome(KangjiBuyIncomeDo newKangjiBuyIncomeDo){
		logger.debug("addKangjiBuyIncome: "+newKangjiBuyIncomeDo);
		return kangjiBuyIncomeDao.addKangjiBuyIncome(newKangjiBuyIncomeDo);
	}
	
	/**
	 * 删除
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public  int deleteById(int id){
		logger.debug("deleteByIdKangjiBuyIncome:"+id);
		return kangjiBuyIncomeDao.deleteById(id);
	}

}

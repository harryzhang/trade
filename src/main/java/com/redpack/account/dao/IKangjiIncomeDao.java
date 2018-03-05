/*
 * Powered By  huangzl QQ: 272950754
 * Web Site:  
 * Since 2008 - 2016
 */

package com.redpack.account.dao;

/**
 * @author  huangzl QQ: 272950754
 * @version 1.0
 * @since 1.0
 */


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.redpack.account.model.KangjiIncomeDo;
@Repository
public interface IKangjiIncomeDao {

	/**
	 * 根据ID 查询
	 * @parameter id
	 */
	public KangjiIncomeDo getById(int id);
	
	/**
	 *根据条件查询列表
	 */
	public List<KangjiIncomeDo> selectKangjiIncome(Map<String,Object> parameterMap);
	
	/**
	 *查询用户当天收益
	 */
	public List<KangjiIncomeDo> getUserIncome(Map<String,Object> parameterMap);
	
	/**
	 * 更新
	 */
	public int  updateKangjiIncomeById(KangjiIncomeDo newKangjiIncomeDo);
	
	/**
	 * 新增
	 */
	public int addKangjiIncome(KangjiIncomeDo newKangjiIncomeDo);
	
	/**
	 * 删除
	 */
	public int deleteById(int id);

}

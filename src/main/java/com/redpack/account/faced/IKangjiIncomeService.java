/*
 * Powered By  huangzl QQ: 272950754
 * Web Site:  
 * Since 2008 - 2016
 */

package com.redpack.account.faced;

/**
 * @author  huangzl QQ: 272950754
 * @version 1.0
 * @since 1.0
 */

import java.util.List;
import java.util.Map;

import com.redpack.account.model.KangjiIncomeDo;
import com.redpack.base.result.IResult;


public interface IKangjiIncomeService{

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
	public int  addKangjiIncome(KangjiIncomeDo newKangjiIncomeDo);
	
	/**
	 * 删除
	 */
	public int deleteById(int id);
	
	/**
	 * 每晚分红调度
	 */
	public void doJob();
	
	/**
	 * 指定时间分红调度
	 */
	public void dojobDate(String date);
}

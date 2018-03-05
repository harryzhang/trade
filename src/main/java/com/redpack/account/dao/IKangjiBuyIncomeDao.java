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

import com.redpack.account.model.KangjiBuyIncomeDo;
@Repository
public interface IKangjiBuyIncomeDao {

	/**
	 * 根据ID 查询
	 * @parameter id
	 */
	public KangjiBuyIncomeDo getById(int id);
	
	/**
	 *根据条件查询列表
	 */
	public List<KangjiBuyIncomeDo> selectKangjiBuyIncome(Map<String,Object> parameterMap);
	/**
	 *查询用户等级收益信息
	 */
	public List<KangjiBuyIncomeDo> getUserGradeIncome(long userId);
	
	/**
	 * 更新
	 */
	public int  updateKangjiBuyIncomeById(KangjiBuyIncomeDo newKangjiBuyIncomeDo);
	
	/**
	 * 新增
	 */
	public int addKangjiBuyIncome(KangjiBuyIncomeDo newKangjiBuyIncomeDo);
	
	/**
	 * 删除
	 */
	public int deleteById(int id);

}

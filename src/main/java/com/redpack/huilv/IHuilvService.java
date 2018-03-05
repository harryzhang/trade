/*
 * Powered By  huangzl QQ: 272950754
 * Web Site:  
 * Since 2008 - 2016
 */

package com.redpack.huilv;

/**
 * @author  huangzl QQ: 272950754
 * @version 1.0
 * @since 1.0
 */

import java.util.List;
import java.util.Map;

import com.redpack.base.result.IResult;
import com.redpack.huilv.model.HuilvDo;




public interface IHuilvService{

	/**
	 * 根据ID 查询
	 * @parameter id
	 */
	public HuilvDo getById(int id);
	
	/**
	 *根据条件查询列表
	 */
	public List<HuilvDo> selectHuilv(Map<String,Object> parameterMap);
	
	/**
	 * 更新
	 */
	public IResult<?>  updateHuilvById(HuilvDo newHuilvDo);
	
	/**
	 * 新增
	 */
	public IResult<?> addHuilv(HuilvDo newHuilvDo);
	
	/**
	 * 删除
	 */
	public IResult<?> deleteById(int id);
	
	public void getHuilv();

	/**
	 * 
	 * @param parameterMap  参数是起止时间和结束时间
	 * @return 最大和最小汇率
	 */
	public Map<String, Object> selectMaxMin(Map<String, Object> parameterMap);
}

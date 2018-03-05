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

import com.redpack.account.model.ManagerAccountDo;
import com.redpack.base.result.IResult;


public interface IManagerAccountService{

	/**
	 * 根据ID 查询
	 * @parameter id
	 */
	public ManagerAccountDo getById(int id);
	
	/**
	 *根据条件查询列表
	 */
	public List<ManagerAccountDo> selectManagerAccount(Map<String,Object> parameterMap);
	
	/**
	 * 更新
	 */
	public int  updateManagerAccountById(ManagerAccountDo newManagerAccountDo);
	
	/**
	 * 新增
	 */
	public int addManagerAccount(ManagerAccountDo newManagerAccountDo);
	
	/**
	 * 删除
	 */
	public int deleteById(int id);
}

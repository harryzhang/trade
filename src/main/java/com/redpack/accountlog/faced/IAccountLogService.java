/*
 * Powered By zhangyunhua
 * Web Site:  
 * Since 2008 - 2015
 */

package com.redpack.accountlog.faced;

/**
 * @author zhangyunhua
 * @version 1.0
 * @since 1.0
 */

import com.redpack.accountlog.model.AccountLogDo;

import java.util.List;
import java.util.Map;


public interface IAccountLogService {

	/**
	 * 根据ID 查询
	 * @parameter id
	 */
	public AccountLogDo getById(Long id);
	
	/**
	 *根据条件查询列表
	 */
	public List<AccountLogDo> selectAccountLog(Map<String, Object> parameterMap);
	
	/**
	 * 更新
	 */
	public int updateAccountLogById(AccountLogDo newAccountLogDo);
	
	/**
	 * 新增
	 */
	public int addAccountLog(AccountLogDo newAccountLogDo);
	
	/**
	 * 删除
	 */
	public int deleteById(Long id);
}

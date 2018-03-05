/*
 * Powered By zhangyunhua
 * Web Site:  
 * Since 2008 - 2015
 */

package com.redpack.audit;

/**
 * @author zhangyunhua
 * @version 1.0
 * @since 1.0
 */

import java.util.List;
import java.util.Map;

import com.redpack.audit.model.AuditDo;



public interface IAuditService{

	/**
	 * 根据ID 查询
	 * @parameter id
	 */
	public AuditDo getById(int id);
	
	/**
	 *根据条件查询列表
	 */
	public List<AuditDo> selectAudit(Map<String,Object> parameterMap);
	
	/**
	 * 更新
	 */
	public int  updateAuditById(AuditDo newAuditDo);
	
	/**
	 * 新增
	 */
	public int addAudit(AuditDo newAuditDo);
	
	/**
	 * 删除
	 */
	public int deleteById(int id);
}

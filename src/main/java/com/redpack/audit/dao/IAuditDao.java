/*
 * Powered By zhangyunhua
 * Web Site:  
 * Since 2008 - 2015
 */

package com.redpack.audit.dao;

/**
 * @author zhangyunhua
 * @version 1.0
 * @since 1.0
 */


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.redpack.audit.model.AuditDo;

@Repository
public interface IAuditDao {

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

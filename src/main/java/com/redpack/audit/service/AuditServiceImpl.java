/*
 * Powered By zhangyunhua
 * Web Site:  
 * Since 2008 - 2015
 */

package com.redpack.audit.service;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redpack.audit.IAuditService;
import com.redpack.audit.dao.IAuditDao;
import com.redpack.audit.model.AuditDo;


/**
 * @author zhangyunhua
 * @version 1.0
 * @since 1.0
 */


@Service("auditService")
public class AuditServiceImpl implements IAuditService {

	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired	
    private IAuditDao  auditDao;

	/**
	 * 根据ID 查询
	 * @parameter id
	 */
	public AuditDo getById(int id){
	  return auditDao.getById(id);
	}
	
	/**
	 *根据条件查询列表
	 */
	public List<AuditDo> selectAudit(Map<String,Object> parameterMap){
		return auditDao.selectAudit(parameterMap);
	}
	
	/**
	 * 更新
	 */
	public int  updateAuditById(AuditDo newAuditDo){
		return auditDao.updateAuditById(newAuditDo);
	}
	
	/**
	 * 新增
	 */
	public int addAudit(AuditDo newAuditDo){
		return auditDao.addAudit(newAuditDo);
	}
	
	/**
	 * 删除
	 */
	public int deleteById(int id){
		return auditDao.deleteById(id);
	}

}

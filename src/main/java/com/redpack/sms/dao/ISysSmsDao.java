/*
 * Powered By  huangzl QQ: 272950754
 * Web Site:  
 * Since 2008 - 2015
 */

package com.redpack.sms.dao;

/**
 * @author  huangzl QQ: 272950754
 * @version 1.0
 * @since 1.0
 */


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.redpack.sms.model.SysSmsDo;
@Repository
public interface ISysSmsDao {
	
	/**
	 * 根据ID 查询
	 * @parameter id
	 */
	public SysSmsDo getById(int id);
	
	/**
	 *根据条件查询列表
	 */
	public List<SysSmsDo> selectSysSms(Map<String,Object> parameterMap);
	
	/**
	 * 更新
	 */
	public int   updateSysSmsById(SysSmsDo newSysSmsDo);
	
	/**
	 * 新增
	 */
	public int addSysSms(SysSmsDo newSysSmsDo);
	
	/**
	 * 删除
	 */
	public  int deleteById(int id); 

}

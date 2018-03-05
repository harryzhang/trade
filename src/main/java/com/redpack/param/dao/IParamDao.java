
package com.redpack.param.dao;

import org.springframework.stereotype.Repository;

import com.redpack.param.model.ParamDo;

@Repository
public interface IParamDao {

	/**
	 * 根据名称查询值
	 * @parameter id
	 */
	public String getByName(String name);
	
	
	/**
	 * 更新
	 */
	public int  updateParam(ParamDo paramDo);
	

}

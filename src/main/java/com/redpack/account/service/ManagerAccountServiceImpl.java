/*
 * Powered By  huangzl QQ: 272950754
 * Web Site:  
 * Since 2008 - 2016
 */

package com.redpack.account.service;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.redpack.account.dao.IManagerAccountDao;
import com.redpack.account.faced.IManagerAccountService;
import com.redpack.account.model.ManagerAccountDo;
import com.redpack.base.result.IResult;
import com.redpack.base.result.ResultSupport;

/**
 * @author  huangzl QQ: 272950754
 * @version 1.0
 * @since 1.0
 */


@Service("managerAccountService")
public class ManagerAccountServiceImpl implements IManagerAccountService {

	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
    private IManagerAccountDao  managerAccountDao;
	/**
	 * 根据ID 查询
	 * @parameter id
	 */
	@Override
	public ManagerAccountDo getById(int id){
	  return managerAccountDao.getById(id);
	}
	
	/**
	 *根据条件查询列表
	 */
	@Override
	public List<ManagerAccountDo> selectManagerAccount(Map<String,Object> parameterMap){
		return managerAccountDao.selectManagerAccount(parameterMap);
	}
	
	/**
	 * 更新
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public int updateManagerAccountById(ManagerAccountDo newManagerAccountDo){
		logger.debug("updateManagerAccount(ManagerAccountDo: "+newManagerAccountDo);
		return managerAccountDao.updateManagerAccountById(newManagerAccountDo);
	}
	
	/**
	 * 新增
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public int addManagerAccount(ManagerAccountDo newManagerAccountDo){
		logger.debug("addManagerAccount: "+newManagerAccountDo);
		return managerAccountDao.addManagerAccount(newManagerAccountDo);
	}
	
	/**
	 * 删除
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public int deleteById(int id){
		logger.debug("deleteByIdManagerAccount:"+id);
		return managerAccountDao.deleteById(id);
	}

}

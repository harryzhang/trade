/*
 * Powered By  huangzl QQ: 272950754
 * Web Site:  
 * Since 2008 - 2016
 */

package com.redpack.bet.service;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.redpack.base.result.IResult;
import com.redpack.base.result.ResultSupport;
import com.redpack.bet.IUserJingpaiService;
import com.redpack.bet.dao.IUserJingpaiDao;
import com.redpack.bet.model.UserJingpaiDo;

/**
 * @author  huangzl QQ: 272950754
 * @version 1.0
 * @since 1.0
 */


@Service("userJingpaiService")
public class UserJingpaiServiceImpl implements IUserJingpaiService {

	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
    private IUserJingpaiDao  userJingpaiDao;
	/**
	 * 根据ID 查询
	 * @parameter id
	 */
	@Override
	public UserJingpaiDo getById(int id){
	  return userJingpaiDao.getById(id);
	}
	
	/**
	 *根据条件查询列表
	 */
	@Override
	public List<UserJingpaiDo> selectUserJingpai(Map<String,Object> parameterMap){
		return userJingpaiDao.selectUserJingpai(parameterMap);
	}
	
	/**
	 * 更新
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public IResult<?> updateUserJingpaiById(UserJingpaiDo newUserJingpaiDo){
		logger.debug("updateUserJingpai(UserJingpaiDo: "+newUserJingpaiDo);
		int i =userJingpaiDao.updateUserJingpaiById(newUserJingpaiDo);
		if(i<1){
			return ResultSupport.buildResult(1, newUserJingpaiDo+"更新失败");
		}
		IResult<Integer> result = ResultSupport.buildResult(0);
		result.setModel(Integer.valueOf(i));
		return result;
	}
	
	/**
	 * 新增
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public IResult<?> addUserJingpai(UserJingpaiDo newUserJingpaiDo){
		logger.debug("addUserJingpai: "+newUserJingpaiDo);
		int i =userJingpaiDao.addUserJingpai(newUserJingpaiDo);
		if(i<1){
			return ResultSupport.buildResult(1, newUserJingpaiDo+"新增失败");
		}
		IResult<Integer> result = ResultSupport.buildResult(0);
		result.setModel(Integer.valueOf(i));
		return result;
	}
	
	/**
	 * 删除
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public IResult<?> deleteById(int id){
		logger.debug("deleteByIdUserJingpai:"+id);
		int i =userJingpaiDao.deleteById(id);
		if(i<1){
			return ResultSupport.buildResult(1, id+"删除失败");
		}
		IResult<Integer> result = ResultSupport.buildResult(0);
		result.setModel(Integer.valueOf(i));
		return result;
	}

}

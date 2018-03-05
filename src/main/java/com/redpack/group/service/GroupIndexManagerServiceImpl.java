/*
 * Powered By  huangzl QQ: 272950754
 * Web Site:  
 * Since 2008 - 2015
 */

package com.redpack.group.service;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.redpack.base.result.IResult;
import com.redpack.base.result.ResultSupport;
import com.redpack.grade.model.GroupIndexManagerDo;
import com.redpack.group.IGroupIndexManagerService;
import com.redpack.group.dao.IGroupIndexManagerDao;

/**
 * @author  huangzl QQ: 272950754
 * @version 1.0
 * @since 1.0
 */


@Service("groupIndexManagerService")
public class GroupIndexManagerServiceImpl implements IGroupIndexManagerService {

	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
    private IGroupIndexManagerDao  groupIndexManagerDao;
	/**
	 * 根据ID 查询
	 * @parameter id
	 */
	@Override
	public GroupIndexManagerDo getById(int id){
	  return groupIndexManagerDao.getById(id);
	}
	
	/**
	 *根据条件查询列表
	 */
	@Override
	public List<GroupIndexManagerDo> selectGroupIndexManager(Map<String,Object> parameterMap){
		return groupIndexManagerDao.selectGroupIndexManager(parameterMap);
	}
	
	/**
	 * 更新
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public IResult<?> updateGroupIndexManagerById(GroupIndexManagerDo newGroupIndexManagerDo){
		logger.debug("updateGroupIndexManager(GroupIndexManagerDo: "+newGroupIndexManagerDo);
		int i =groupIndexManagerDao.updateGroupIndexManagerById(newGroupIndexManagerDo);
		if(i<1){
			return ResultSupport.buildResult(1, newGroupIndexManagerDo+"更新失败");
		}
		IResult<Integer> result = ResultSupport.buildResult(0,"更新成功");
		result.setModel(Integer.valueOf(i));
		return result;
	}
	
	/**
	 * 新增
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public IResult<?> addGroupIndexManager(GroupIndexManagerDo newGroupIndexManagerDo){
		logger.debug("addGroupIndexManager: "+newGroupIndexManagerDo);
		int i =groupIndexManagerDao.addGroupIndexManager(newGroupIndexManagerDo);
		if(i<1){
			return ResultSupport.buildResult(1, newGroupIndexManagerDo+"新增失败");
		}
		IResult<Integer> result = ResultSupport.buildResult(0,"更新成功");
		result.setModel(Integer.valueOf(i));
		return result;
	}
	
	/**
	 * 删除
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public IResult<?> deleteById(int id){
		logger.debug("deleteByIdGroupIndexManager:"+id);
		int i =groupIndexManagerDao.deleteById(id);
		if(i<1){
			return ResultSupport.buildResult(1, id+"删除失败");
		}
		IResult<Integer> result = ResultSupport.buildResult(0,"更新成功");
		result.setModel(Integer.valueOf(i));
		return result;
	}

}

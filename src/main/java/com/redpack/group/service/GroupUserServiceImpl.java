/*
 * Powered By  huangzl QQ: 272950754
 * Web Site:  
 * Since 2008 - 2015
 */

package com.redpack.group.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.redpack.account.dao.IUserDao;
import com.redpack.account.model.UserDo;
import com.redpack.base.result.IResult;
import com.redpack.base.result.ResultSupport;
import com.redpack.grade.model.GroupUserDo;
import com.redpack.group.IGroupUserService;
import com.redpack.group.dao.IGroupUserDao;

/**
 * @author  huangzl QQ: 272950754
 * @version 1.0
 * @since 1.0
 */


@Service("groupUserService")
public class GroupUserServiceImpl implements IGroupUserService {

	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
    private IGroupUserDao  groupUserDao;
	
	@Autowired
	private IUserDao  userDao;
	/**
	 * 根据ID 查询
	 * @parameter id
	 */
	@Override
	public GroupUserDo getById(int id){
	  return groupUserDao.getById(id);
	}
	
	/**
	 *根据条件查询列表
	 */
	@Override
	public List<GroupUserDo> selectGroupUser(Map<String,Object> parameterMap){
		return groupUserDao.selectGroupUser(parameterMap);
	}
	
	/**
	 * 更新
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public IResult<?> updateGroupUserById(GroupUserDo newGroupUserDo){
		logger.debug("updateGroupUser(GroupUserDo: "+newGroupUserDo);
		int i =groupUserDao.updateGroupUserById(newGroupUserDo);
		if(i<1){
			return ResultSupport.buildResult(1, newGroupUserDo+"更新失败");
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
	public IResult<?> addGroupUser(GroupUserDo newGroupUserDo){
		logger.debug("addGroupUser: "+newGroupUserDo);
		int i =groupUserDao.addGroupUser(newGroupUserDo);
		if(i<1){
			return ResultSupport.buildResult(1, newGroupUserDo+"新增失败");
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
		logger.debug("deleteByIdGroupUser:"+id);
		int i =groupUserDao.deleteById(id);
		if(i<1){
			return ResultSupport.buildResult(1, id+"删除失败");
		}
		IResult<Integer> result = ResultSupport.buildResult(0,"更新成功");
		result.setModel(Integer.valueOf(i));
		return result;
	}

	@Override
	public UserDo getAllChildRen(UserDo rootDao, String groupName) {
		
		//获取组的所有用户
		Map paramMap = new HashMap();
		paramMap.put("groupName", groupName);
		List<UserDo> userList = userDao.getAllGroupUser(paramMap);
		if( null != rootDao){
			findChildUser(rootDao,userList,0);
		}
		return rootDao ;
//		
//		
//		Map<Long,UserDo>  userMap = new HashMap<Long,UserDo>();		
//		for (UserDo userDo : userList) {
//			if(null == userDo.getId()){
//				continue;
//			}
//			userMap.put(userDo.getId(), userDo);
//		}
//		
//		//覆盖掉 userList 的root
//		userMap.put(rootDao.getId(), rootDao);
//		
//		for (UserDo userDo : userList) {
//			UserDo u = userMap.get(userDo.getParentId());
//			if(null != u){
//				List<UserDo> childList = u.getChildList();
//				if(null == childList){
//					childList = new ArrayList<UserDo>();
//				}
//				childList.add(userDo);
//				u.setChildList(childList);
//			}
//		}
//
//		return rootDao;
		
	}

	//寻找子节点
	public void findChildUser(UserDo rootDo,List<UserDo> userList, int level ){
		level++;
		Long id = rootDo.getId();
		List<UserDo> childList = new ArrayList<UserDo>();
		for (UserDo userDo : userList) {
			Long parentId = userDo.getReferrerId();
			//System.out.println("id=" + id + "level=" + level + "============" + parentId);
			if( null != id && id.equals(parentId)){
				childList.add(userDo);
				//递归找到所有子节点  只找三层
				if( null != userDo && level <3 ){
					findChildUser(userDo,userList, level );
				}
			}
		}
		rootDo.setChildList(childList);
	}

	@Override
	public Map getUserGroupByNetwork(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return groupUserDao.getUserGroupByNetwork(param);
	}

	@Override
	public List<Map<String, Object>> listGroup(Map<String, Object> queryMap) {
		return groupUserDao.listGroup(queryMap);
	}
	
	
	/**
	 * 从一个组里删除
	 */
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void delGroupUserByUserId(String user1Mobile){
		Map<String, Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("userName", user1Mobile);
		List<UserDo> user1List = userDao.getByUserDo(parameterMap);
		Long user1Id = user1List.get(0).getId();
		
		Map<String,Object> groupMap = new HashMap<String,Object>();
		groupMap.put("userId", user1Id);
		groupUserDao.delGroupUserByUserId(groupMap);
		
	}
}

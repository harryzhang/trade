

package com.redpack.userWaiting.dao;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.redpack.userWaiting.model.UserWaitingDo;

/**
 * 等待用户操作DAO
 */
@Repository
public interface IUserWaitingDao {

	/**
	 * 根据用户ID查询，等待信息
	 * @parameter userId
	 */
	public UserWaitingDo getByUserId(Long userId);
	
	
	/**
	 * 新增等待用户
	 */
	public int addUserWaiting(UserWaitingDo userWaitingDo);
	
	/**
	 * 更新等待用户信息
	 */
	public int updateUserWaitingById(UserWaitingDo userWaitingDo);
	
	/**
	 * 根据用户ID删除等待信息
	 */
	public int deleteByUserId(Long userId);

	/**
	 * 根据组找到本组第一个排队等待的人
	 * @param groupName
	 * @return
	 */
	public UserWaitingDo selectUserWaitingByGroupName(String groupName);
	
	/**
	 * 根据组找到本组第一个排队等待的人
	 * @param groupName
	 * @return
	 */
	public List<UserWaitingDo> selectUserWaiting(Map<String,Object> parameterMap);
	
	
	
}

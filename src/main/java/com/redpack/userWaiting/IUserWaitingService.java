package com.redpack.userWaiting;

import java.util.Map;

import com.redpack.userWaiting.model.UserWaitingDo;

public interface IUserWaitingService {
	
	/**
	 * 根据用户ID查询，等待信息
	 * @parameter userId
	 */
	public UserWaitingDo getByUserId(Long userId);
	
	/**
	 * 保存等待用户
	 */
	public void saveUserWaiting(UserWaitingDo userWaitingDo);
	
	/**
	 * 查询用户是否在等待
	 * @param userId
	 * @return
	 */
	public UserWaitingDo selectUserWaiting(Map<String,Object> parameterMap);

	/**
	 * 找到本组排队等待的人
	 * @param groupName
	 * @return
	 */
	public UserWaitingDo selectUserWaitingByGroupName(String groupName);
	

	

}

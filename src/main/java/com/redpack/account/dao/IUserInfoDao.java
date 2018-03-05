/*
 * Powered By zhangyunhua
 * Web Site:  
 * Since 2008 - 2015
 */

package com.redpack.account.dao;

/**
 * @author zhangyunhua
 * @version 1.0
 * @since 1.0
 */


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.redpack.account.model.UserInfoDo;

@Repository
public interface IUserInfoDao {

	/**
	 * 根据ID查询
	 * @author huangzl QQ: 272950754
	 * @date 2015年8月3日 下午7:52:44
	 * @param id
	 * @return
	 */
	public UserInfoDo getById(Long id);
	
	/**
	 * 根据用户ID查询
	 * @author huangzl QQ: 272950754
	 * @date 2015年8月3日 下午7:52:44
	 * @param id
	 * @return
	 */
	public UserInfoDo getByUserId(Long userId);
	/**
	 * 根据条件查询userDo
	 * @param parameterMap
	 * @return
	 */
	public UserInfoDo getByUserInfoDo(Map<String, Object> parameterMap);
	/**
	 * 保存
	 * @author huangzl QQ: 272950754
	 * @date 2015年8月3日 下午7:52:44
	 * @param id
	 * @return
	 */
	public Long saveUserInfo(UserInfoDo userInfoDo);
	/**
	 * 更新
	 * @author huangzl QQ: 272950754
	 * @date 2015年8月3日 下午7:52:44
	 * @param id
	 * @return
	 */
	public int updataUserInfo(UserInfoDo userInfoDo);

}

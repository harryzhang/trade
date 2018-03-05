
/**  
 * @Project: deposit-biz-service
 * @Package com.hehenian.deposit.dal.account
 * @Title: IUserDao.java
 * @Description: TODO
 * @author:  zhangyunhua
 * @date 2015年3月5日 上午10:59:14
 * @Copyright: HEHENIAN Co.,Ltd. All rights reserved.
 * @version V1.0  
 */
package com.redpack.account.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.redpack.account.model.ApplyAgentDo;
import com.redpack.account.model.UserDo;
import com.redpack.base.result.IResult;

/**
 * 
 * @author:  zhangyunhua
 * @date 2015年3月5日 上午10:59:14
 */
@Repository
public interface IUserDao {
    /**
     * 根据ID查询用户对象
     * 
     * @param id
     * @return
     * @author:  zhangyunhua
     * @date: 2015年3月5日上午10:49:36
     */
    UserDo getById(Long id);

    /**
     * 保存用户
     * @param userDo
     * @return
     */
    Long saveUser(UserDo userDo);
    /**
     * 根据条件查询用户对象
     * @param parameterMap
     * @return
     */
	List<UserDo> getByUserDo(Map<String, Object> parameterMap);

	/**
	 * 根据parentid找出所有下级
	 * @param parentId
	 * @return
	 */
	List<UserDo> selectChildByParentId(Long parentId);

	/**
	 * 根据id 更新当前等级
	 * @param userid
	 * @param afterUpgrade
	 * @return
	 */
	int updateUserGradeById(@Param("userId")Long userid, @Param("grade")Integer afterUpgrade);
	/**
	 * @Description 用户保存方法
	 * @author huangzl QQ: 272950754
	 * @date 2015-8-7 下午09:55:59
	 * @Project redpack-common
	 * @Package com.redpack.account
	 * @File IUserService.java
	 * @param tempSave
	 * @return
	*/
	int updateUser(UserDo tempSave);

	/**
	 * 更新出局者的下级parent = null
	 * @param id
	 * @return
	 */
	int updateUserParentNullById(Long id);

	/**
	 * 增加用户积分
	 * @param parent
	 */
	void addUserBonusById(UserDo parent);

	/**
	 * 修改会员状态
	 * @param tempUserId
	 * @param newStatus
	 */
	void updateUserStatusById(@Param("userId")Long tempUserId, @Param("status")int newStatus);

	/**
	 * 推荐总人数
	 * @param id 用户id
	 * @return
	 */
	int getRefUserCount(Long id);
	
	/**
	 * 查询当前用户团队临时会员数量
	 * @param userId 当前用户id
	 * @return
	 */
	int getTempMemberCount(long userId);

	/**
	 * 查询本次升级的抢点用户列表
	 * @param id
	 * @return
	 */
	List<UserDo> getRaceUser(@Param("userId")Long userId,@Param("groupName")String groupName);

	/**
	 * 获取所有用户
	 * @return
	 */
	List<UserDo> getAllUser(Map paramMap);
	
	/**
	 * 获取用户组的所有用户
	 * @param groupName
	 * @return
	 */
	public  List<UserDo> getAllGroupUser(Map paramMap);

	List<Map<String, Object>> listRef(Map<String, Object> queryMap);

	void updateUserId(Map<String, Object> idMap);

	/**
	 * 
	 * @param agentDo
	 * @return
	 */
	int saveApplyAgent(ApplyAgentDo agentDo);


	/**
	 * 用户申请记录
	 */
	List<Map<String, Object>> getApplyAgentUser(Map<String, Object> paraMap);


	/**
	 * 获取省代 市代
	 * @param string
	 * @param userProvince
	 * @return 
	 */
	List<ApplyAgentDo> getAreaAgentUser(Map<String,Object> map);

	/**
	 * 更新 申请审批记录
	 * @param agentDo
	 * @return
	 */
	int updateApplyAgent(ApplyAgentDo agentDo);

	/**
	 * 记录登录日期
	 * @param id
	 */
	void saveLoginlog(Long id);

}

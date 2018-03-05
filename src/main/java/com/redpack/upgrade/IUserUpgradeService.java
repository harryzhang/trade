
/*
 * Powered By zhangyunhua
 * Web Site:  
 * Since 2008 - 2015
 */

package com.redpack.upgrade;

/**
 * @author zhangyunhua
 * @version 1.0
 * @since 1.0
 */

import java.util.List;
import java.util.Map;

import com.redpack.upgrade.model.UserUpgradeDo;


public interface IUserUpgradeService{

	/**
	 * 根据ID 查询
	 * @parameter id
	 */
	public UserUpgradeDo getById(Long id);
	
	/**
	 *根据条件查询列表
	 */
	public List<UserUpgradeDo> selectUserUpgrade(Map<String,Object> parameterMap);
	
	/**
	 * 更新
	 */
	public int  updateUserUpgradeById(UserUpgradeDo newUserUpgradeDo);
	
	/**
	 * 新增
	 */
	public int addUserUpgrade(UserUpgradeDo newUserUpgradeDo);
	
	/**
	 * 删除
	 */
	public int deleteById(int id);

	/**
	 * 申请升级
	 * @param userId
	 * @return
	 */
	public UserUpgradeDo applyUpgrade(long userId);

	/**
	 * 根据id 更改状态
	 * @param upgradeId
	 * @param newStatus
	 */
	public void updateUpgradeStatusById(String upgradeId, String newStatus);

	/**
	 * 查询审批列表
	 * @param parameterMap
	 * @return
	 */
	public List<UserUpgradeDo> selectUpgradeAuditList(
			Map<String, Object> parameterMap);

	/**
	 * 获取每层的申请人数和金额
	 */
	public Map<String, Object> selectLevelAmount(Long id, int level);

	/**
	 * 进入B网的 parent 手机号是否存在有效
	 * @param refMobile
	 * @return
	 */
	public Map checkBNetWorkParent(String refMobile);

	/**
	 * 申请进B网
	 * @param userId
	 * @param refMobile
	 */
	public void applyUpgradeBNetWork(long userId, Long refUserId, String applyGroupName);
}

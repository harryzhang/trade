
/*
 * Powered By zhangyunhua
 * Web Site:  
 * Since 2008 - 2015
 */

package com.redpack.upgrade.dao;

/**
 * @author zhangyunhua
 * @version 1.0
 * @since 1.0
 */


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.redpack.upgrade.model.UserUpgradeDo;

@Repository
public interface IUserUpgradeDao {

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
	 * 查询审批列表
	 * @param parameterMap
	 * @return
	 */
	public List<UserUpgradeDo> selectUpgradeAuditList(
			Map<String, Object> parameterMap);

	/**
	 * 更新申请的状态
	 * @param newUserUpgradeDo
	 * @return
	 */
	public int updateUpgradeStatus(UserUpgradeDo newUserUpgradeDo);

	/**
	 * 获取每层的申请人数和金额
	 * @param id
	 * @param level
	 * @return
	 */
	public Map<String, Object> selectLevelAmount(@Param("receiveUser")Long receiveUser, @Param("beforeUpgrade")int beforeUpgrade, @Param("status") int status);

	/**
	 * 检查B网的手机号是否有效
	 * @param refMobile
	 */
	public Map checkBNetWorkParent(String refMobile);
}

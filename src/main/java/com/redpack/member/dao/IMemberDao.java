
package com.redpack.member.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.redpack.account.model.UserAccountIncomeDo;
import com.redpack.member.model.KuangjiUserAccountDo;

/**
 * 
 * @author MBENBEN
 *
 */
@Repository
public interface IMemberDao {

	/**
	 * 根据ID 查询
	 * @parameter id
	 */
	public KuangjiUserAccountDo getByUserId(long userId);
	
	/**
	 * 更新用户帐户信息
	 * @param userId
	 * @return
	 */
	public int updateAccountById(KuangjiUserAccountDo  memberDo);
	
	/**
	 * 获取用户推广订单信息
	 * @param userId
	 * @return
	 */
	public List<Map<String, Object>> getOrderList(long userId);

	/**
	 * 获取用户推广的人数
	 * @param userId
	 * @return
	 */
	public int getUserReff(long userId);
	
	
	/**
	 * 更新用户金额
	 * @param userId
	 * @return
	 */
	public int updateAmountById(KuangjiUserAccountDo  memberDo);

	/**
	 * 更新用户为报单中心
	 * @param userId
	 */
	public void updateUserCenterById(KuangjiUserAccountDo  memberDo);
	

}

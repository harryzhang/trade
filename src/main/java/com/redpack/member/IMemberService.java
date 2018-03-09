/**
 * 
 */

package com.redpack.member;

import java.util.List;
import java.util.Map;

import com.redpack.account.model.BizUserAccountDo;
import com.redpack.account.model.UserAccountIncomeDo;
import com.redpack.member.model.KuangjiUserAccountDo;

public interface IMemberService{

	/**
	 * 根据ID获取用户帐户信息
	 */
	public KuangjiUserAccountDo getByUserid(long userId);
	
	/**
	 * 更新用户帐户信息
	 * @param userId
	 * @return
	 */
	public int updateAccountById(KuangjiUserAccountDo  memberDo);

	/**
	 * 获取订单订单信息
	 * @param userId
	 * @return
	 */
	public List<Map<String, Object>> getOrderList(long userId);
	
	/**
	 * 获取用户一级推广人数
	 * @param userId
	 * @return
	 */
	public int getUserReff(long userId);
	
	/**
	 * 根据ID获取用户账户信息
	 * @param userId
	 * @return
	 */
	public List<BizUserAccountDo> getUserAccount(long userId);

	/**
	 * 根据账户查询，这类账户可执行的操作
	 * zhangyunhmf
	 *
	 */
    public List<Map<String, Object>> getOptionsByAccountCode(String accountType);
	
}

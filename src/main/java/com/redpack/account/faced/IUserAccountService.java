/*
 * Powered By zhangyunhua
 * Web Site:  
 * Since 2008 - 2015
 */

package com.redpack.account.faced;

/**
 * @author zhangyunhua
 * @version 1.0
 * @since 1.0
 */

import com.redpack.account.model.UserAccountDo;
import com.redpack.base.result.IResult;

import java.util.List;
import java.util.Map;



public interface IUserAccountService {

    /**
     * 找到到期了的子账户
     * @return
     */
    public List<UserAccountDo> findExpireAccounts();

    /**
     * 修改子账户的状态为已赎回
     * @param accountId
     * @return
     */
    public int updateAccountStatusToRedeem(Long accountId);

  



    /**
     *根据用户id获取主账户
     */
    public UserAccountDo getPrimaryAccount(Long userId);

    /**
     * 更新主账户可用余额
     * @return
     */
    public int updatePrimaryAcountUsableAmt(Long userId , Long incrementSum);

    /**
     * 更新账户可用余额
     * @return
     */
    public int updateAcountUsableAmt(Long accountId , Long incrementSum);

    /**
     * 查询指定类型的平台账户
     * @param accountSubType
     * @return
     */
    public UserAccountDo getPlatformAccountBySubType(UserAccountDo.AccountSubType accountSubType);
	/**
	 * 根据ID 查询
	 * @parameter id
	 */
	public UserAccountDo getById(Long id);

	/**
	 *根据条件查询列表
	 */
	public List<UserAccountDo> selectUserAccount(Map<String, Object> parameterMap);
	
	/**
	 * 更新
	 */
	public int updateUserAccountById(UserAccountDo newUserAccountDo);
	
	/**
	 * 新增
	 */
	public int addUserAccount(UserAccountDo newUserAccountDo);
	
	/**
	 * 删除
	 */
	public int deleteById(Long id);
}

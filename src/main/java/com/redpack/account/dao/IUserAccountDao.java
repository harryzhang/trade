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

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.redpack.account.model.UserAccountDo;

@Repository
public interface IUserAccountDao {

    /**
     * 找到到期了的子账户
     * 
     * @return
     */
    public List<UserAccountDo> findExpireAccounts();

    /**
     * 查找指定状态的子账户
     * 
     * @param accountStatus
     * @return
     */
    public List<UserAccountDo> findAccountByStatus(@Param("accountStatus")UserAccountDo.AccountStatus accountStatus);

    /**
     * 修改子账户的状态为已赎回
     * 
     * @param accountId
     * @return
     */
    public int updateAccountStatusToRedeem(@Param("accountId") Long accountId);

    /**
     * 赎回
     * 
     * @param userAccountDo
     * @return
     */
    public int redeem(UserAccountDo userAccountDo);

    /**
     * 清算
     * 
     * @param accountId
     * @param incrementSum
     * @return
     */
    public int liquidateAccount(@Param("accountId") Long accountId, @Param("incrementSum") Long incrementSum);

    /**
     * 根据ID 查询
     * 
     * @parameter id
     */
    public UserAccountDo getById(Long id);

    /**
     * 根据用户id获取主账户
     */
    public UserAccountDo getPrimaryAccount(Long userId);

    /**
     * 更新主账户可用余额
     * 
     * @param userId
     * @return
     */
    public int updatePrimaryAcountUsableAmt(@Param("userId") Long userId, @Param("incrementSum") Long incrementSum);

    /**
     * 更新账户可用余额
     * 
     * @param accountId
     * @return
     */
    public int updateAcountUsableAmt(@Param("accountId") Long accountId, @Param("incrementSum") Long incrementSum);

    /**
     * 查询指定类型的平台账户
     * 
     * @param accountSubType
     * @return
     */
    public UserAccountDo getPlatformAccountBySubType(@Param("accountSubType") UserAccountDo.AccountSubType accountSubType);

    /**
     * 根据条件查询列表
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

    /**
     * 根据账户ID查询用户账户信息
     * 
     * @param accountIdList
     * @return
     * @author: liuzgmf
     * @date: 2015年3月16日下午2:29:38
     */
    public List<UserAccountDo> queryByAccountIds(@Param("accountIdList") List<Long> accountIdList);

    /**
     * 修改用户账户金额
     * 
     * @param updUserAccountDo
     * @return
     * @author: liuzgmf
     * @date: 2015年3月16日下午2:30:25
     */
    public int updateAmount(UserAccountDo updUserAccountDo);

    /**
     * 修改用户余额
     * 
     * @param parameterMap
     * @return
     * @author: zhangyunhmf
     * @date: 2015年3月17日下午4:26:42
     */
    public int subAccountBalance(@Param("userAccountId") long userAccountId, @Param("subAmt") long subAmt);

    /**
     * 根据用户ID查询账户信息
     * 
     * @param userId
     * @return
     * @author: liuzgmf
     * @date: 2015年3月18日上午11:05:55
     */
    public UserAccountDo getByUserId(Long userId);

    /**
     * 根据用户ID查询账户信息
     * 
     * @param userId
     * @return
     * @author: liuzgmf
     * @date: 2015年3月18日上午11:05:55
     */
    public List<UserAccountDo> queryByUserId(Long userId);

    /**
     * 根据用户ID，父账户ID查询账户信息
     * 
     * @param userId
     * @param parentId
     * @return
     * @author: liuzgmf
     * @date: 2015年3月18日上午11:34:25
     */
    public UserAccountDo getByUserIdAndParentId(@Param("userId") Long userId, @Param("parentId") Long parentId);

    /**
     * 查询需要投资状态信息
     * 
     * @return
     * @author: liuzgmf
     * @date: 2015年3月18日下午2:28:16
     */
    public List<UserAccountDo> queryCanInvestAccount();

    /**
     * 查询债权全部认购的用户账户
     * 
     * @param receiptAmt
     * @return
     * @author: liuzgmf
     * @date: 2015年3月20日上午11:18:48
     */
    public UserAccountDo getPurchaseUserAccount(Long receiptAmt);

    /**
     * 查询债权部分认购的用户账户
     * 
     * @param receiptAmt
     * @return
     * @author: liuzgmf
     * @date: 2015年3月20日上午11:19:31
     */
    public UserAccountDo getPartPurchaseUserAccount(Long receiptAmt);

}

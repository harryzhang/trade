/**  
 * @Project: deposit-biz-service
 * @Package com.hehenian.deposit.service.account
 * @Title: UserServiceImpl.java
 * @Description: TODO
 * @author: liuzgmf
 * @date 2015年3月5日 上午10:58:12
 * @Copyright: HEHENIAN Co.,Ltd. All rights reserved.
 * @version V1.0  
 */
package com.redpack.account.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redpack.account.dao.IUserAccountDao;
import com.redpack.account.faced.IUserAccountService;
import com.redpack.account.model.UserAccountDo;

@Service("userAccountService")
public class UserAccountServiceImpl implements IUserAccountService {
    private static final Logger LOGGER = Logger.getLogger(UserAccountServiceImpl.class);
    @Autowired
    private IUserAccountDao userAccountDao;

    @Override public List<UserAccountDo> findExpireAccounts() {
        return userAccountDao.findExpireAccounts();
    }

    @Override public int updateAccountStatusToRedeem(Long accountId) {
        return userAccountDao.updateAccountStatusToRedeem(accountId);
    }
   



    
    /**
     * 计算提前赎回费用  = 产品的提前赎回费率*赎回金额
     *
     * @return
     */
    private Long computeFee(UserAccountDo userAccountDo){
       return -1l;
    }
    /**
     * 计算购买产品获得的收益
     */
    private Long computeProfit(UserAccountDo userAccountDo) {
    	return 0l;
    }


    @Override public UserAccountDo getPrimaryAccount(Long userId) {
        UserAccountDo primaryAccount = userAccountDao.getPrimaryAccount(userId);
        if (primaryAccount==null){
            primaryAccount = new UserAccountDo();
            Date now = new Date();
            primaryAccount.setParentId(0l);
            primaryAccount.setCreateTime(now);
            primaryAccount.setAccountType(UserAccountDo.AccountType.GENERAL);
            primaryAccount.setUserId(userId);
            primaryAccount.setAccountStatus(UserAccountDo.AccountStatus.ACTIVATED);
            primaryAccount.setCashLockAmt(0l);
            primaryAccount.setFreezeAmt(0l);
            primaryAccount.setPurchaseAmt(0l);
            primaryAccount.setRedeemAmt(0l);
            primaryAccount.setUpdateTime(now);
            primaryAccount.setUsableAmt(0l);
            int i = userAccountDao.addUserAccount(primaryAccount);
            if (i>0){
                LOGGER.info("为用户："+userId+"创建主账户成功");
            }else {
                //TODO 抛出异常
                LOGGER.error("为用户："+userId+"创建主账户失败");
            }
        }
        return primaryAccount;
    }

    @Override public int updatePrimaryAcountUsableAmt(Long userId, Long incrementSum) {
        return userAccountDao.updatePrimaryAcountUsableAmt(userId, incrementSum);
    }

    @Override public int updateAcountUsableAmt(Long accountId, Long incrementSum) {
        return userAccountDao.updateAcountUsableAmt(accountId , incrementSum);
    }



    @Override public UserAccountDo getPlatformAccountBySubType(UserAccountDo.AccountSubType accountSubType) {
        return userAccountDao.getPlatformAccountBySubType(accountSubType);
    }

    @Override public UserAccountDo getById(Long id) {
        return userAccountDao.getById(id);
    }

    @Override public List<UserAccountDo> selectUserAccount(Map<String, Object> parameterMap) {
        return null;
    }

    @Override public int updateUserAccountById(UserAccountDo newUserAccountDo) {
        return 0;
    }

    @Override public int addUserAccount(UserAccountDo newUserAccountDo) {
        Date now = new Date();
        if (newUserAccountDo.getUsableAmt()==null)newUserAccountDo.setUsableAmt(0l);
        if (newUserAccountDo.getRedeemAmt()==null)newUserAccountDo.setRedeemAmt(0l);
        if (newUserAccountDo.getAccountStatus()==null)newUserAccountDo.setAccountStatus(UserAccountDo.AccountStatus.ACTIVATED);
        if (newUserAccountDo.getAccountType()==null)newUserAccountDo.setAccountType(UserAccountDo.AccountType.GENERAL);
        if (newUserAccountDo.getCashLockAmt()==null)newUserAccountDo.setCashLockAmt(0l);
        if (newUserAccountDo.getCreateTime()==null)newUserAccountDo.setCreateTime(now);
        if (newUserAccountDo.getFreezeAmt()==null)newUserAccountDo.setFreezeAmt(0l);
        if (newUserAccountDo.getParentId()==null)newUserAccountDo.setParentId(-1l);
        if (newUserAccountDo.getPurchaseAmt()==null)newUserAccountDo.setPurchaseAmt(0l);
        if (newUserAccountDo.getUpdateTime()==null)newUserAccountDo.setUpdateTime(now);
        return userAccountDao.addUserAccount(newUserAccountDo);
    }

    @Override public int deleteById(Long id) {
        return 0;
    }
}

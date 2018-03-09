/*
 * Powered By zhangyunhua
 * Web Site:  
 * Since 2008 - 2015
 */

package com.redpack.account.faced;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.redpack.account.model.BizUserAccountDo;
import com.redpack.account.model.UserDo;
import com.redpack.constant.AccountMsg;
import com.redpack.withdraw.model.WithdrawDo;



public interface IBizUserAccountService {

    /**
     * 获取用户指定账户类型金额
     * @param accountType
     * @param userId
     * @return
     */
    public BigDecimal getAccountTypeAmount(String accountType, Long userId);
    
   /**
    * 更新用户指定用户帐户信息
    * @param bizUserAccountDo   用户账户信息
    * @param useType        消费类型
    * @return
    */
	public int  updateUserAmountById(BizUserAccountDo bizUserAccountDo,AccountMsg type);
	
	
	/**
	 * 判断用户能否购买
	 */
	public boolean canPay(String payType[], BigDecimal amount,Long userId);
	
	/**
	 * 用户多付款方式付款 减用户金额  加不用此方法
	 * @param payType  付款类型
	 * @param amount   金额
	 * @param useType  消费金额  负-
	 * @return
	 */
	public boolean updateUserAmountByMuilt(String payType[],BigDecimal amount, AccountMsg type,Long userId);

	/**
	 * 查询账号
	 * @param parameterMap
	 */
	public List<BizUserAccountDo> selectUserAccount(Map<String, Object> parameterMap);
   
	/**
	 * 用户退本操作
	 * @param parameterMap
	 */
	public int userUnPay(WithdrawDo withdrawDo);
	
	/**
	 * 更新用户等级
	 * @param parameterMap
	 */
	public void updateUserGrade(UserDo user, BigDecimal amount);

	
	///////////////////////////分红////////////////////////////
	
	//备份帐户表
	public void backupAccount(Date now);
	
	public int insertFeiHongTemp(Long userId, BigDecimal resultAmt,
			String account, String calDesc, AccountMsg accountMsgType);

	public List<BizUserAccountDo> selectUserAccountFromTemp();

	public void updateUserAmountByUserIdAccountType(List<BizUserAccountDo> bizUserAccountLst);
	
	public int updateUserAmountByUserIdAccountType(BizUserAccountDo bizUserAccountDo) ;

	//删除上次分红的临时记录
	public void deleteFeiHongTemp();
	
///////////////////////////end 分红////////////////////////////

	
	/**
	 * 积分转让
	 * @param sourceUserId
	 * @param targetUserId
	 * @param jifengAmt
	 */
	public void transJifeng(Long sourceUserId, Long targetUserId,BigDecimal jifengAmt);

	/**
	 * 储值豆转换
	 * @param userId
	 * @param jifengAmt
	 */
	public void saveTransPoint(Long userId, BigDecimal jifengAmt);

	/**
	 * 奖金豆转换积分
	 * @param userId
	 * @param jifengAmt
	 */
	public void saveTransJjd(Long userId, BigDecimal jifengAmt);

	/**
	 * 现金豆转激活豆
	 * @param userId
	 * @param jifengAmt
	 */
	public void saveTransRmb(Long userId, BigDecimal jifengAmt);


	/**
	 * 
	 * 账户之间装换
	 * zhangyunhmf
	 *
	 */
    public void convertBetweenAccount(Long sourceUserId,
									  Long targetUserId,
									  BigDecimal qty,    								  
									  String fromAccount,
									  String toAccount,
									  AccountMsg sourceMsg,
    								  AccountMsg targetMsg);

	/**
	 * 
	 * zhangyunhmf
	 *
	 */
    public void buySecurity(Long userId, BigDecimal qty);
	


}

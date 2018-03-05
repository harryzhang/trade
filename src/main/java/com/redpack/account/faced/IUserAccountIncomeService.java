/*
 * Powered By  huangzl QQ: 272950754
 * Web Site:  
 * Since 2008 - 2016
 */

package com.redpack.account.faced;

/**
 * @author  huangzl QQ: 272950754
 * @version 1.0
 * @since 1.0
 */

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.redpack.account.model.UserAccountIncomeDo;
import com.redpack.base.result.IResult;


public interface IUserAccountIncomeService{

	/**
	 * 根据ID 查询
	 * @parameter id
	 */
	public UserAccountIncomeDo getById(Long id);
	
	/**
	 *根据条件查询列表
	 */
	public List<UserAccountIncomeDo> selectUserAccount(Map<String,Object> parameterMap);
	
	/**
	 * 更新
	 */
	public int  updateUserAccountById(UserAccountIncomeDo newUserAccountIncomeDo);
	
	/**
	 * 积分转让更新积分
	 */
	public int  updateUserPointById(UserAccountIncomeDo newUserAccountIncomeDo);
	
	
	/**
	 * 新增
	 */
	public int addUserAccount(UserAccountIncomeDo newUserAccountIncomeDo);
	
	/**
	 * 删除
	 */
	public int deleteById(int id);

	/**
	 * 比较账号金币+积分余额
	 * @param userId
	 * @param totalMoney
	 * @return
	 */
	public boolean compareAmt(Long userId, double totalMoney);
	/**
	 * 比较账号金币余额
	 * @param userId
	 * @param totalMoney
	 * @param accType 帐户类型
	 * @return
	 */
	public boolean compareAmountAmt(Long userId, double totalMoney);

	/**
	 * 积分转让
	 * @param sourceUserId  出让人 
	 * @param targetUserId  接受人
	 * @param jifengAmt     转让额度
	 */
	public void transJifeng(Long sourceUserId, Long targetUserId, BigDecimal jifengAmt);
}

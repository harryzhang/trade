/*
 * Powered By zhangyunhua
 * Web Site:  
 * Since 2008 - 2015
 */

package com.redpack.account.faced;

import java.math.BigDecimal;

import com.redpack.account.model.UserDo;



public interface ITotalAccountService {
	/**
	 * 
	 * 统计下面人员的账户金额
	 *
	 * zhangyunhmf
	 *
	 */
    public BigDecimal totalAmt(UserDo userDo, String accountType);

	/**
	 * 查询直推额度
	 * zhangyunhmf
	 *
	 */
    public BigDecimal  totalReferAmt(Long id, String securityAccount);
}

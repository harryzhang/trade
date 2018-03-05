/*
 * Powered By  huangzl QQ: 272950754
 * Web Site:  
 * Since 2008 - 2016
 */

package com.redpack.account.model;

import org.apache.ibatis.type.Alias;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author  huangzl QQ: 272950754
 * @version 1.0
 * @since 1.0
 */


@Alias("bizUserAccountDo")
public class BizUserAccountDo  implements java.io.Serializable{
	

	//columns START
	private java.lang.Long userId;
	private java.math.BigDecimal amount;
	private String accountType;
	private java.math.BigDecimal totalConsumeAmount;
	private java.math.BigDecimal totalInocmeAmount;
	private java.math.BigDecimal withdrawTotalDeposit;
	private java.math.BigDecimal frozenDeposit;
	private java.math.BigDecimal incomeAmount;
	private java.math.BigDecimal consueAmount;
	private Date updateTime;
	private String remark;
	
	public BizUserAccountDo(){
		
	}
	
	public BizUserAccountDo(Long userId,BigDecimal amount,String accountType){
		this.userId = userId;
		this.amount = amount;
		this.accountType = accountType;
	}
	public java.lang.Long getUserId() {
		return userId;
	}
	public void setUserId(java.lang.Long userId) {
		this.userId = userId;
	}
	public java.math.BigDecimal getAmount() {
        if (amount == null) {
            return BigDecimal.ZERO;
        }
        return amount;
	}
	public void setAmount(java.math.BigDecimal amount) {
		this.amount = amount;
	}
	public java.math.BigDecimal getTotalConsumeAmount() {
		return totalConsumeAmount;
	}
	public void setTotalConsumeAmount(java.math.BigDecimal totalConsumeAmount) {
		this.totalConsumeAmount = totalConsumeAmount;
	}
	public java.math.BigDecimal getTotalInocmeAmount() {
		return totalInocmeAmount;
	}
	public void setTotalInocmeAmount(java.math.BigDecimal totalInocmeAmount) {
		this.totalInocmeAmount = totalInocmeAmount;
	}
	public java.math.BigDecimal getWithdrawTotalDeposit() {
		return withdrawTotalDeposit;
	}
	public void setWithdrawTotalDeposit(java.math.BigDecimal withdrawTotalDeposit) {
		this.withdrawTotalDeposit = withdrawTotalDeposit;
	}
	public java.math.BigDecimal getFrozenDeposit() {
		return frozenDeposit;
	}
	public void setFrozenDeposit(java.math.BigDecimal frozenDeposit) {
		this.frozenDeposit = frozenDeposit;
	}
	public java.math.BigDecimal getIncomeAmount() {
		return incomeAmount;
	}
	public void setIncomeAmount(java.math.BigDecimal incomeAmount) {
		this.incomeAmount = incomeAmount;
	}
	public java.math.BigDecimal getConsueAmount() {
		return consueAmount;
	}
	public void setConsueAmount(java.math.BigDecimal consueAmount) {
		this.consueAmount = consueAmount;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

    public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
    public String toString() {
        return "BizUserAccountDo{" +
                "userId=" + userId +
                ", amount=" + amount +
                ", accountType='" + accountType + '\'' +
                ", totalConsumeAmount=" + totalConsumeAmount +
                ", totalInocmeAmount=" + totalInocmeAmount +
                ", withdrawTotalDeposit=" + withdrawTotalDeposit +
                ", frozenDeposit=" + frozenDeposit +
                ", incomeAmount=" + incomeAmount +
                ", consueAmount=" + consueAmount +
                ", updateTime=" + updateTime +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        return toString().equals(obj.toString());
    }

	public Date getUpdateTime() {
		return updateTime;
	}
    
    
}


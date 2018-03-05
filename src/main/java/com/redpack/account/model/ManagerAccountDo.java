/*
 * Powered By  huangzl QQ: 272950754
 * Web Site:  
 * Since 2008 - 2016
 */

package com.redpack.account.model;

import org.apache.ibatis.type.Alias;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @author  huangzl QQ: 272950754
 * @version 1.0
 * @since 1.0
 */


@Alias("managerAccountDo")
public class ManagerAccountDo  implements java.io.Serializable{	
	
	//columns START
	private java.lang.Long id;
	private java.lang.Long userId;
	private java.math.BigDecimal totalAmount;
	private java.math.BigDecimal totalIncomeAmount;
	private java.math.BigDecimal totalPayAmount;
	private java.math.BigDecimal totalPayKangjiAmount;
	private java.math.BigDecimal totalWithdrawAmount;
	private java.math.BigDecimal totalChargeAmount;
	private java.math.BigDecimal totalIncomeBuyKangji;
	private java.math.BigDecimal amount;
	private java.math.BigDecimal incomeAmount;
	private java.math.BigDecimal payAmount;
	private java.math.BigDecimal payKangjiAmount;
	private java.math.BigDecimal withdrawAmount;
	private java.math.BigDecimal chargeAmount;
	private java.math.BigDecimal incomeBuyKangji;
	private java.util.Date incomeDay;
	//columns END
	public java.lang.Long getId() {
		return this.id;
	}
	
	public void setId(java.lang.Long value) {
		this.id = value;
	}
	public java.lang.Long getUserId() {
		return this.userId;
	}
	
	public void setUserId(java.lang.Long value) {
		this.userId = value;
	}
	public java.math.BigDecimal getTotalAmount() {
		return this.totalAmount;
	}
	
	public void setTotalAmount(java.math.BigDecimal value) {
		this.totalAmount = value;
	}
	public java.math.BigDecimal getTotalIncomeAmount() {
		return this.totalIncomeAmount;
	}
	
	public void setTotalIncomeAmount(java.math.BigDecimal value) {
		this.totalIncomeAmount = value;
	}
	public java.math.BigDecimal getTotalPayAmount() {
		return this.totalPayAmount;
	}
	
	public void setTotalPayAmount(java.math.BigDecimal value) {
		this.totalPayAmount = value;
	}
	public java.math.BigDecimal getTotalPayKangjiAmount() {
		return this.totalPayKangjiAmount;
	}
	
	public void setTotalPayKangjiAmount(java.math.BigDecimal value) {
		this.totalPayKangjiAmount = value;
	}
	public java.math.BigDecimal getTotalWithdrawAmount() {
		return this.totalWithdrawAmount;
	}
	
	public void setTotalWithdrawAmount(java.math.BigDecimal value) {
		this.totalWithdrawAmount = value;
	}
	public java.math.BigDecimal getTotalChargeAmount() {
		return this.totalChargeAmount;
	}
	
	public void setTotalChargeAmount(java.math.BigDecimal value) {
		this.totalChargeAmount = value;
	}
	public java.math.BigDecimal getTotalIncomeBuyKangji() {
		return this.totalIncomeBuyKangji;
	}
	
	public void setTotalIncomeBuyKangji(java.math.BigDecimal value) {
		this.totalIncomeBuyKangji = value;
	}
	public java.math.BigDecimal getAmount() {
		return this.amount;
	}
	
	public void setAmount(java.math.BigDecimal value) {
		this.amount = value;
	}
	public java.math.BigDecimal getIncomeAmount() {
		return this.incomeAmount;
	}
	
	public void setIncomeAmount(java.math.BigDecimal value) {
		this.incomeAmount = value;
	}
	public java.math.BigDecimal getPayAmount() {
		return this.payAmount;
	}
	
	public void setPayAmount(java.math.BigDecimal value) {
		this.payAmount = value;
	}
	public java.math.BigDecimal getPayKangjiAmount() {
		return this.payKangjiAmount;
	}
	
	public void setPayKangjiAmount(java.math.BigDecimal value) {
		this.payKangjiAmount = value;
	}
	public java.math.BigDecimal getWithdrawAmount() {
		return this.withdrawAmount;
	}
	
	public void setWithdrawAmount(java.math.BigDecimal value) {
		this.withdrawAmount = value;
	}
	public java.math.BigDecimal getChargeAmount() {
		return this.chargeAmount;
	}
	
	public void setChargeAmount(java.math.BigDecimal value) {
		this.chargeAmount = value;
	}
	public java.math.BigDecimal getIncomeBuyKangji() {
		return this.incomeBuyKangji;
	}
	
	public void setIncomeBuyKangji(java.math.BigDecimal value) {
		this.incomeBuyKangji = value;
	}
	public java.util.Date getIncomeDay() {
		return this.incomeDay;
	}
	
	public void setIncomeDay(java.util.Date value) {
		this.incomeDay = value;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("UserId",getUserId())
			.append("TotalAmount",getTotalAmount())
			.append("TotalIncomeAmount",getTotalIncomeAmount())
			.append("TotalPayAmount",getTotalPayAmount())
			.append("TotalPayKangjiAmount",getTotalPayKangjiAmount())
			.append("TotalWithdrawAmount",getTotalWithdrawAmount())
			.append("TotalChargeAmount",getTotalChargeAmount())
			.append("TotalIncomeBuyKangji",getTotalIncomeBuyKangji())
			.append("Amount",getAmount())
			.append("IncomeAmount",getIncomeAmount())
			.append("PayAmount",getPayAmount())
			.append("PayKangjiAmount",getPayKangjiAmount())
			.append("WithdrawAmount",getWithdrawAmount())
			.append("ChargeAmount",getChargeAmount())
			.append("IncomeBuyKangji",getIncomeBuyKangji())
			.append("IncomeDay",getIncomeDay())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof ManagerAccountDo == false) return false;
		if(this == obj) return true;
		ManagerAccountDo other = (ManagerAccountDo)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}


/*
 * Powered By  huangzl QQ: 272950754
 * Web Site:  
 * Since 2008 - 2016
 */

package com.redpack.withdraw.model;

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


@Alias("withdrawDo")
public class WithdrawDo  implements java.io.Serializable{	
	
	//columns START
	private java.lang.Long withdrawId;
	private java.lang.Long withdrawCode;
	private java.lang.Long userId;
	private java.math.BigDecimal amount;
	private java.math.BigDecimal charge;
	private java.math.BigDecimal payAmount;
	private java.util.Date createTime;
	private java.lang.Integer withdrawStatus;
	private java.lang.Integer payStatus;
	private java.util.Date payTime;
	
	private String bankNo;
	private String bankName;
	private String bankUser;
	//columns END
	public java.lang.Long getWithdrawId() {
		return this.withdrawId;
	}
	
	public void setWithdrawId(java.lang.Long value) {
		this.withdrawId = value;
	}
	public java.lang.Long getWithdrawCode() {
		return this.withdrawCode;
	}
	
	public void setWithdrawCode(java.lang.Long value) {
		this.withdrawCode = value;
	}
	public java.lang.Long getUserId() {
		return this.userId;
	}
	
	public void setUserId(java.lang.Long value) {
		this.userId = value;
	}
	public java.math.BigDecimal getAmount() {
		return this.amount;
	}
	
	public void setAmount(java.math.BigDecimal value) {
		this.amount = value;
	}
	public java.math.BigDecimal getCharge() {
		return this.charge;
	}
	
	public void setCharge(java.math.BigDecimal value) {
		this.charge = value;
	}
	public java.math.BigDecimal getPayAmount() {
		return this.payAmount;
	}
	
	public void setPayAmount(java.math.BigDecimal value) {
		this.payAmount = value;
	}
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	public java.lang.Integer getWithdrawStatus() {
		return this.withdrawStatus;
	}
	
	public void setWithdrawStatus(java.lang.Integer value) {
		this.withdrawStatus = value;
	}
	public java.lang.Integer getPayStatus() {
		return this.payStatus;
	}
	
	public void setPayStatus(java.lang.Integer value) {
		this.payStatus = value;
	}
	public java.util.Date getPayTime() {
		return this.payTime;
	}
	
	public void setPayTime(java.util.Date value) {
		this.payTime = value;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("WithdrawId",getWithdrawId())
			.append("WithdrawCode",getWithdrawCode())
			.append("UserId",getUserId())
			.append("Amount",getAmount())
			.append("Charge",getCharge())
			.append("PayAmount",getPayAmount())
			.append("CreateTime",getCreateTime())
			.append("WithdrawStatus",getWithdrawStatus())
			.append("PayStatus",getPayStatus())
			.append("PayTime",getPayTime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getWithdrawId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof WithdrawDo == false) return false;
		if(this == obj) return true;
		WithdrawDo other = (WithdrawDo)obj;
		return new EqualsBuilder()
			.append(getWithdrawId(),other.getWithdrawId())
			.isEquals();
	}

	public String getBankNo() {
		return bankNo;
	}

	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankUser() {
		return bankUser;
	}

	public void setBankUser(String bankUser) {
		this.bankUser = bankUser;
	}
	
	
}


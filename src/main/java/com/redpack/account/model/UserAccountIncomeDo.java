/*
 * Powered By  huangzl QQ: 272950754
 * Web Site:  
 * Since 2008 - 2016
 */

package com.redpack.account.model;

import org.apache.ibatis.type.Alias;

import java.math.BigDecimal;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @author  huangzl QQ: 272950754
 * @version 1.0
 * @since 1.0
 */


@Alias("userAccountIncomeDo")
public class UserAccountIncomeDo  implements java.io.Serializable{
	

	//columns START
	private java.lang.Long userId;
	private java.math.BigDecimal amount;
	private java.math.BigDecimal guquanQty;
	private java.math.BigDecimal point;
	private java.math.BigDecimal incomeTotalAmount;
	private java.math.BigDecimal ququanTotalAmount;
	private java.math.BigDecimal recommendTotalAmount;
	private java.math.BigDecimal poingTotalAmount;
	private java.math.BigDecimal consumeTotalAmount;
	private java.math.BigDecimal withdrawTotalDeposit;
	private java.math.BigDecimal freezeAmount;
	private BigDecimal teamAmount;
	private java.util.Date updateTime;
	//columns END
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
	public java.math.BigDecimal getGuquanQty() {
		return this.guquanQty;
	}
	
	public void setGuquanQty(java.math.BigDecimal value) {
		this.guquanQty = value;
	}
	public java.math.BigDecimal getPoint() {
		return this.point;
	}
	
	public void setPoint(java.math.BigDecimal value) {
		this.point = value;
	}
	public java.math.BigDecimal getIncomeTotalAmount() {
		return this.incomeTotalAmount;
	}
	
	public void setIncomeTotalAmount(java.math.BigDecimal value) {
		this.incomeTotalAmount = value;
	}
	public java.math.BigDecimal getQuquanTotalAmount() {
		return this.ququanTotalAmount;
	}
	
	public void setQuquanTotalAmount(java.math.BigDecimal value) {
		this.ququanTotalAmount = value;
	}
	public java.math.BigDecimal getRecommendTotalAmount() {
		return this.recommendTotalAmount;
	}
	
	public void setRecommendTotalAmount(java.math.BigDecimal value) {
		this.recommendTotalAmount = value;
	}
	public java.math.BigDecimal getPoingTotalAmount() {
		return this.poingTotalAmount;
	}
	
	public void setPoingTotalAmount(java.math.BigDecimal value) {
		this.poingTotalAmount = value;
	}
	public java.math.BigDecimal getConsumeTotalAmount() {
		return this.consumeTotalAmount;
	}
	
	public void setConsumeTotalAmount(java.math.BigDecimal value) {
		this.consumeTotalAmount = value;
	}
	public java.math.BigDecimal getWithdrawTotalDeposit() {
		return this.withdrawTotalDeposit;
	}
	
	public void setWithdrawTotalDeposit(java.math.BigDecimal value) {
		this.withdrawTotalDeposit = value;
	}
	public java.math.BigDecimal getFreezeAmount() {
		return this.freezeAmount;
	}
	
	public void setFreezeAmount(java.math.BigDecimal value) {
		this.freezeAmount = value;
	}
	public java.util.Date getUpdateTime() {
		return this.updateTime;
	}
	
	public void setUpdateTime(java.util.Date value) {
		this.updateTime = value;
	}
	
	

	public BigDecimal getTeamAmount() {
		return teamAmount;
	}

	public void setTeamAmount(BigDecimal teamAmount) {
		this.teamAmount = teamAmount;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("UserId",getUserId())
			.append("Amount",getAmount())
			.append("GuquanQty",getGuquanQty())
			.append("Point",getPoint())
			.append("IncomeTotalAmount",getIncomeTotalAmount())
			.append("QuquanTotalAmount",getQuquanTotalAmount())
			.append("RecommendTotalAmount",getRecommendTotalAmount())
			.append("PoingTotalAmount",getPoingTotalAmount())
			.append("ConsumeTotalAmount",getConsumeTotalAmount())
			.append("WithdrawTotalDeposit",getWithdrawTotalDeposit())
			.append("FreezeAmount",getFreezeAmount())
			.append("UpdateTime",getUpdateTime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getUserId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof UserAccountDo == false) return false;
		if(this == obj) return true;
		UserAccountDo other = (UserAccountDo)obj;
		return new EqualsBuilder()
			.append(getUserId(),other.getUserId())
			.isEquals();
	}
}


/*
 * Powered By  huangzl QQ: 272950754
 * Web Site:  
 * Since 2008 - 2016
 */

package com.redpack.bet.model;

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


@Alias("userBetDo")
public class UserBetDo  implements java.io.Serializable{	
	
	//columns START
	private java.lang.Integer id;
	private java.lang.Integer userId;
	private java.lang.Integer periods;
	private java.util.Date betTime;
	private java.math.BigDecimal amount;
	private String betNumber;
	private java.lang.String status;
	//columns END
	public java.lang.Integer getId() {
		return this.id;
	}
	
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	public java.lang.Integer getUserId() {
		return this.userId;
	}
	
	public void setUserId(java.lang.Integer value) {
		this.userId = value;
	}
	public java.lang.Integer getPeriods() {
		return this.periods;
	}
	
	public void setPeriods(java.lang.Integer value) {
		this.periods = value;
	}
	public java.util.Date getBetTime() {
		return this.betTime;
	}
	
	public void setBetTime(java.util.Date value) {
		this.betTime = value;
	}
	public java.math.BigDecimal getAmount() {
		return this.amount;
	}
	
	public void setAmount(java.math.BigDecimal value) {
		this.amount = value;
	}
	public java.lang.String getStatus() {
		return this.status;
	}
	
	public void setStatus(java.lang.String value) {
		this.status = value;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("UserId",getUserId())
			.append("Periods",getPeriods())
			.append("BetTime",getBetTime())
			.append("Amount",getAmount())
			.append("Status",getStatus())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof UserBetDo == false) return false;
		if(this == obj) return true;
		UserBetDo other = (UserBetDo)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}

	public String getBetNumber() {
		return betNumber;
	}

	public void setBetNumber(String betNumber) {
		this.betNumber = betNumber;
	}
	
	
}


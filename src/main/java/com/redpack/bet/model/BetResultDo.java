/*
 * Powered By  huangzl QQ: 272950754
 * Web Site:  
 * Since 2008 - 2016
 */

package com.redpack.bet.model;

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


@Alias("betResultDo")
public class BetResultDo  implements java.io.Serializable{	
	
	//columns START
	private java.lang.Integer id;
	private java.lang.Integer period;
	private java.lang.String betNum;
	private java.math.BigDecimal betAmount;
	private java.math.BigDecimal oneAmount;
	private java.math.BigDecimal twoAmount;
	private java.math.BigDecimal threeAmount;
	private java.math.BigDecimal fourAmount;
	private java.math.BigDecimal fiveAmount;
	private java.math.BigDecimal sixAmount;
	private java.util.Date betTime;
	private java.lang.String status;
	//columns END
	public java.lang.Integer getId() {
		return this.id;
	}
	
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	public java.lang.Integer getPeriod() {
		return this.period;
	}
	
	public void setPeriod(java.lang.Integer value) {
		this.period = value;
	}
	public java.lang.String getBetNum() {
		return this.betNum;
	}
	
	public void setBetNum(java.lang.String value) {
		this.betNum = value;
	}
	public java.math.BigDecimal getBetAmount() {
		return this.betAmount;
	}
	
	public void setBetAmount(java.math.BigDecimal value) {
		this.betAmount = value;
	}
	public java.math.BigDecimal getOneAmount() {
		return this.oneAmount;
	}
	
	public void setOneAmount(java.math.BigDecimal value) {
		this.oneAmount = value;
	}
	public java.math.BigDecimal getTwoAmount() {
		return this.twoAmount;
	}
	
	public void setTwoAmount(java.math.BigDecimal value) {
		this.twoAmount = value;
	}
	public java.math.BigDecimal getThreeAmount() {
		return this.threeAmount;
	}
	
	public void setThreeAmount(java.math.BigDecimal value) {
		this.threeAmount = value;
	}
	public java.math.BigDecimal getFourAmount() {
		return this.fourAmount;
	}
	
	public void setFourAmount(java.math.BigDecimal value) {
		this.fourAmount = value;
	}
	public java.math.BigDecimal getFiveAmount() {
		return this.fiveAmount;
	}
	
	public void setFiveAmount(java.math.BigDecimal value) {
		this.fiveAmount = value;
	}
	public java.math.BigDecimal getSixAmount() {
		return this.sixAmount;
	}
	
	public void setSixAmount(java.math.BigDecimal value) {
		this.sixAmount = value;
	}
	public java.util.Date getBetTime() {
		return this.betTime;
	}
	
	public void setBetTime(java.util.Date value) {
		this.betTime = value;
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
			.append("Period",getPeriod())
			.append("BetNum",getBetNum())
			.append("BetAmount",getBetAmount())
			.append("OneAmount",getOneAmount())
			.append("TwoAmount",getTwoAmount())
			.append("ThreeAmount",getThreeAmount())
			.append("FourAmount",getFourAmount())
			.append("FiveAmount",getFiveAmount())
			.append("SixAmount",getSixAmount())
			.append("BetTime",getBetTime())
			.append("Status",getStatus())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BetResultDo == false) return false;
		if(this == obj) return true;
		BetResultDo other = (BetResultDo)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}


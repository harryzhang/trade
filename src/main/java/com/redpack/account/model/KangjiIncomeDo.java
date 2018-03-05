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


@Alias("kangjiIncomeDo")
public class KangjiIncomeDo  implements java.io.Serializable{	
	
	//columns START
	private java.lang.Long id;
	private java.lang.Long userId;
	private java.lang.Integer kangjiQty;
	private java.lang.Integer income;
	private java.math.BigDecimal oneCommission;
	private java.math.BigDecimal twoCommission;
	private java.math.BigDecimal threeCommission;
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
	public java.lang.Integer getKangjiQty() {
		return this.kangjiQty;
	}
	
	public void setKangjiQty(java.lang.Integer value) {
		this.kangjiQty = value;
	}
	public java.lang.Integer getIncome() {
		return this.income;
	}
	
	public void setIncome(java.lang.Integer value) {
		this.income = value;
	}
	public java.math.BigDecimal getOneCommission() {
		return this.oneCommission;
	}
	
	public void setOneCommission(java.math.BigDecimal value) {
		this.oneCommission = value;
	}
	public java.math.BigDecimal getTwoCommission() {
		return this.twoCommission;
	}
	
	public void setTwoCommission(java.math.BigDecimal value) {
		this.twoCommission = value;
	}
	public java.math.BigDecimal getThreeCommission() {
		return this.threeCommission;
	}
	
	public void setThreeCommission(java.math.BigDecimal value) {
		this.threeCommission = value;
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
			.append("KangjiQty",getKangjiQty())
			.append("Income",getIncome())
			.append("OneCommission",getOneCommission())
			.append("TwoCommission",getTwoCommission())
			.append("ThreeCommission",getThreeCommission())
			.append("IncomeDay",getIncomeDay())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof KangjiIncomeDo == false) return false;
		if(this == obj) return true;
		KangjiIncomeDo other = (KangjiIncomeDo)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}


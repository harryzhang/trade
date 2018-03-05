/*
 * Powered By  huangzl QQ: 272950754
 * Web Site:  
 * Since 2008 - 2016
 */

package com.redpack.account.model;

import java.math.BigDecimal;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.ibatis.type.Alias;

/**
 * @author  huangzl QQ: 272950754
 * @version 1.0
 * @since 1.0
 */


@Alias("kangjiBuyIncomeDo")
public class KangjiBuyIncomeDo  implements java.io.Serializable{	
	
	//columns START
	private java.lang.Long id;
	private java.lang.Long userId;
	private java.lang.Long orderId;
	private java.lang.Integer kangjiQty;
	private java.math.BigDecimal price;
	private java.math.BigDecimal totalPrice;
	private BigDecimal income;
	private java.lang.Integer grade;
	private java.util.Date createTime;
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
	public java.lang.Long getOrderId() {
		return this.orderId;
	}
	
	public void setOrderId(java.lang.Long value) {
		this.orderId = value;
	}
	public java.lang.Integer getKangjiQty() {
		return this.kangjiQty;
	}
	
	public void setKangjiQty(java.lang.Integer value) {
		this.kangjiQty = value;
	}
	public java.math.BigDecimal getPrice() {
		return this.price;
	}
	
	public void setPrice(java.math.BigDecimal value) {
		this.price = value;
	}
	public java.math.BigDecimal getTotalPrice() {
		return this.totalPrice;
	}
	
	public void setTotalPrice(java.math.BigDecimal value) {
		this.totalPrice = value;
	}
	public BigDecimal getIncome() {
		return this.income;
	}
	
	public void setIncome(BigDecimal value) {
		this.income = value;
	}
	public java.lang.Integer getGrade() {
		return this.grade;
	}
	
	public void setGrade(java.lang.Integer value) {
		this.grade = value;
	}
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("UserId",getUserId())
			.append("OrderId",getOrderId())
			.append("KangjiQty",getKangjiQty())
			.append("Price",getPrice())
			.append("TotalPrice",getTotalPrice())
			.append("Income",getIncome())
			.append("Grade",getGrade())
			.append("CreateTime",getCreateTime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof KangjiBuyIncomeDo == false) return false;
		if(this == obj) return true;
		KangjiBuyIncomeDo other = (KangjiBuyIncomeDo)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}


/*
 * Powered By  huangzl QQ: 272950754
 * Web Site:  
 * Since 2008 - 2016
 */

package com.redpack.huilv.model;

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


@Alias("huilvDo")
public class HuilvDo  implements java.io.Serializable{	
	
	//columns START
	private java.lang.Integer id;
	private java.util.Date getDate;
	private java.lang.String fromCurrency;
	private java.lang.String toCurrency;
	private java.math.BigDecimal currency;
	private java.util.Date getTime;
	private java.lang.String huilvType;
	private byte[] remark;
	//columns END
	public java.lang.Integer getId() {
		return this.id;
	}
	
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	public java.util.Date getGetDate() {
		return this.getDate;
	}
	
	public void setGetDate(java.util.Date value) {
		this.getDate = value;
	}
	public java.lang.String getFromCurrency() {
		return this.fromCurrency;
	}
	
	public void setFromCurrency(java.lang.String value) {
		this.fromCurrency = value;
	}
	public java.lang.String getToCurrency() {
		return this.toCurrency;
	}
	
	public void setToCurrency(java.lang.String value) {
		this.toCurrency = value;
	}
	public java.math.BigDecimal getCurrency() {
		return this.currency;
	}
	
	public void setCurrency(java.math.BigDecimal value) {
		this.currency = value;
	}
	public java.util.Date getGetTime() {
		return this.getTime;
	}
	
	public void setGetTime(java.util.Date value) {
		this.getTime = value;
	}
	public java.lang.String getHuilvType() {
		return this.huilvType;
	}
	
	public void setHuilvType(java.lang.String value) {
		this.huilvType = value;
	}
	public byte[] getRemark() {
		return this.remark;
	}
	
	public void setRemark(byte[] value) {
		this.remark = value;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("GetDate",getGetDate())
			.append("FromCurrency",getFromCurrency())
			.append("ToCurrency",getToCurrency())
			.append("Currency",getCurrency())
			.append("GetTime",getGetTime())
			.append("HuilvType",getHuilvType())
			.append("Remark",getRemark())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof HuilvDo == false) return false;
		if(this == obj) return true;
		HuilvDo other = (HuilvDo)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}


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


@Alias("userJingpaiDo")
public class UserJingpaiDo  implements java.io.Serializable{	
	
	//columns START
	private java.lang.Integer id;
	private java.lang.Integer userId;
	private java.math.BigDecimal jingpaiPrice;
	private java.util.Date jingpaiTime;
	private java.lang.Integer jingpaiId;
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
	public java.math.BigDecimal getJingpaiPrice() {
		return this.jingpaiPrice;
	}
	
	public void setJingpaiPrice(java.math.BigDecimal value) {
		this.jingpaiPrice = value;
	}
	public java.util.Date getJingpaiTime() {
		return this.jingpaiTime;
	}
	
	public void setJingpaiTime(java.util.Date value) {
		this.jingpaiTime = value;
	}
	public java.lang.Integer getJingpaiId() {
		return this.jingpaiId;
	}
	
	public void setJingpaiId(java.lang.Integer value) {
		this.jingpaiId = value;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("UserId",getUserId())
			.append("JingpaiPrice",getJingpaiPrice())
			.append("JingpaiTime",getJingpaiTime())
			.append("JingpaiId",getJingpaiId())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof UserJingpaiDo == false) return false;
		if(this == obj) return true;
		UserJingpaiDo other = (UserJingpaiDo)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}


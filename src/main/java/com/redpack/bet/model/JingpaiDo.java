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


@Alias("jingpaiDo")
public class JingpaiDo  implements java.io.Serializable{	
	
	//columns START
	private java.lang.Integer id;
	private java.lang.Integer goodsId;
	private java.lang.Integer userId;
	private java.util.Date startTime;
	private java.util.Date endTime;
	private java.math.BigDecimal startPrice;
	private java.math.BigDecimal endPrice;
	private java.lang.String lastUser;
	private java.util.Date userTime;
	private java.math.BigDecimal lastPrice;
	private java.lang.Integer status;
	private java.util.Date createTime;
	//columns END
	public java.lang.Integer getId() {
		return this.id;
	}
	
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	public java.lang.Integer getGoodsId() {
		return this.goodsId;
	}
	
	public void setGoodsId(java.lang.Integer value) {
		this.goodsId = value;
	}
	public java.util.Date getStartTime() {
		return this.startTime;
	}
	
	public void setStartTime(java.util.Date value) {
		this.startTime = value;
	}
	public java.util.Date getEndTime() {
		return this.endTime;
	}
	
	public void setEndTime(java.util.Date value) {
		this.endTime = value;
	}
	public java.math.BigDecimal getStartPrice() {
		return this.startPrice;
	}
	
	public void setStartPrice(java.math.BigDecimal value) {
		this.startPrice = value;
	}
	public java.math.BigDecimal getEndPrice() {
		return this.endPrice;
	}
	
	public void setEndPrice(java.math.BigDecimal value) {
		this.endPrice = value;
	}
	public java.lang.String getLastUser() {
		return this.lastUser;
	}
	
	public void setLastUser(java.lang.String value) {
		this.lastUser = value;
	}
	public java.util.Date getUserTime() {
		return this.userTime;
	}
	
	public void setUserTime(java.util.Date value) {
		this.userTime = value;
	}
	public java.math.BigDecimal getLastPrice() {
		return this.lastPrice;
	}
	
	public void setLastPrice(java.math.BigDecimal value) {
		this.lastPrice = value;
	}
	public java.lang.Integer getStatus() {
		return this.status;
	}
	
	public void setStatus(java.lang.Integer value) {
		this.status = value;
	}
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	
	
	public java.lang.Integer getUserId() {
		return userId;
	}

	public void setUserId(java.lang.Integer userId) {
		this.userId = userId;
	}

	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("GoodsId",getGoodsId())
			.append("StartTime",getStartTime())
			.append("EndTime",getEndTime())
			.append("StartPrice",getStartPrice())
			.append("EndPrice",getEndPrice())
			.append("LastUser",getLastUser())
			.append("UserTime",getUserTime())
			.append("LastPrice",getLastPrice())
			.append("Status",getStatus())
			.append("CreateTime",getCreateTime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof JingpaiDo == false) return false;
		if(this == obj) return true;
		JingpaiDo other = (JingpaiDo)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}


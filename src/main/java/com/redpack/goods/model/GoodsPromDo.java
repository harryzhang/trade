/*
 * Powered By  huangzl QQ: 272950754
 * Web Site:  
 * Since 2008 - 2016
 */

package com.redpack.goods.model;

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


@Alias("goodsPromDo")
public class GoodsPromDo  implements java.io.Serializable{	
	
	//columns START
	private java.lang.Long id;
	private java.lang.Long goodsId;
	private java.math.BigDecimal price;
	private java.util.Date startTime;
	private java.util.Date endTime;
	private java.util.Date createTime;
	private java.lang.Integer status;
	//columns END
	public java.lang.Long getId() {
		return this.id;
	}
	
	public void setId(java.lang.Long value) {
		this.id = value;
	}
	public java.lang.Long getGoodsId() {
		return this.goodsId;
	}
	
	public void setGoodsId(java.lang.Long value) {
		this.goodsId = value;
	}
	public java.math.BigDecimal getPrice() {
		return this.price;
	}
	
	public void setPrice(java.math.BigDecimal value) {
		this.price = value;
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
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	public java.lang.Integer getStatus() {
		return this.status;
	}
	
	public void setStatus(java.lang.Integer value) {
		this.status = value;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("GoodsId",getGoodsId())
			.append("Price",getPrice())
			.append("StartTime",getStartTime())
			.append("EndTime",getEndTime())
			.append("CreateTime",getCreateTime())
			.append("Status",getStatus())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof GoodsPromDo == false) return false;
		if(this == obj) return true;
		GoodsPromDo other = (GoodsPromDo)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}


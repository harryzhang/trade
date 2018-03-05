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


@Alias("betWinDo")
public class BetWinDo  implements java.io.Serializable{	
	
	//columns START
	private java.lang.Integer id;
	private java.lang.Integer userId;
	private java.lang.Integer peroid;
	private java.util.Date createTime;
	private java.math.BigDecimal betAmount;
	private java.math.BigDecimal winAmount;
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
	public java.lang.Integer getPeroid() {
		return this.peroid;
	}
	
	public void setPeroid(java.lang.Integer value) {
		this.peroid = value;
	}
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	public java.math.BigDecimal getBetAmount() {
		return this.betAmount;
	}
	
	public void setBetAmount(java.math.BigDecimal value) {
		this.betAmount = value;
	}
	public java.math.BigDecimal getWinAmount() {
		return this.winAmount;
	}
	
	public void setWinAmount(java.math.BigDecimal value) {
		this.winAmount = value;
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
			.append("Peroid",getPeroid())
			.append("CreateTime",getCreateTime())
			.append("BetAmount",getBetAmount())
			.append("WinAmount",getWinAmount())
			.append("Status",getStatus())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BetWinDo == false) return false;
		if(this == obj) return true;
		BetWinDo other = (BetWinDo)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}


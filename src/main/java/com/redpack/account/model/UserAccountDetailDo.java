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


@Alias("userAccountDetailDo")
public class UserAccountDetailDo  implements java.io.Serializable{	
	
	//columns START
	private java.lang.Long id;
	private java.lang.Long userId;
	private java.math.BigDecimal amount;
	private java.lang.String moreOrLess;
	
	//账户类型
	private String accountType;
	private String remark;
	/** 
	 * 没特别说明为积分提成
	 * 购买股权 1:积分购股权 2：现金购买股权 3上一级直推积分提成 4:管理积分奖 5：报单中心积分奖  6：省代提成积分奖  7市代提成积分奖
	 * 8  静态分红金额  9消费提成 10 中奖金币 11中奖积分  12 竞猜消费积分 13 竞猜消费金币 14 竞猜提成  15 竞猜报单中心提成
	 * 16 竞拍消费积分  17 竞拍消费金币  18 上一级直推金提成  19:管理金币提成 20：报单中心金币提成  21：省代金币提成 22市代金币提成
	 * 23 消费金币提成 24 静态分红积分  25 充值　 26 推荐一人获取一金币
	 */
	
	/**
	 * 1-100 充值
	 * 100-200  金币
	 * 200-300 积分
	 * 
	 */
	private java.lang.Integer incomeType;
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
	public java.math.BigDecimal getAmount() {
		return this.amount;
	}
	
	public void setAmount(java.math.BigDecimal value) {
		this.amount = value;
	}
	public java.lang.String getMoreOrLess() {
		return this.moreOrLess;
	}
	
	public void setMoreOrLess(java.lang.String value) {
		this.moreOrLess = value;
	}
	public java.lang.Integer getIncomeType() {
		return this.incomeType;
	}
	
	public void setIncomeType(java.lang.Integer value) {
		this.incomeType = value;
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
			.append("Amount",getAmount())
			.append("MoreOrLess",getMoreOrLess())
			.append("IncomeType",getIncomeType())
			.append("CreateTime",getCreateTime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof UserAccountDetailDo == false) return false;
		if(this == obj) return true;
		UserAccountDetailDo other = (UserAccountDetailDo)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}


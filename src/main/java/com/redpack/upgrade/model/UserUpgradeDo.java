
/*
 * Powered By zhangyunhua
 * Web Site:  
 * Since 2008 - 2015
 */

package com.redpack.upgrade.model;



import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.ibatis.type.Alias;

import com.redpack.account.model.UserInfoDo;

/**
 * @author zhangyunhua
 * @version 1.0
 * @since 1.0
 */


@Alias("userUpgradeDo")
public class UserUpgradeDo  implements java.io.Serializable{	
	
	//columns START
	private Long id;   //主键
	private Long userid;
	private java.lang.Integer beforeUpgrade;
	private java.util.Date upgradeDate;
	private java.lang.Double upgradeAmount;
	private Long receiveUser;
	private java.lang.String paymentMethod;
	private java.lang.String accountReceive;
	private java.lang.String accountExpend;
	private java.lang.Integer afterUpgrade;
	private String status;    //状态  未审批:0 , 审批通过 :1, 拒绝 :2,撤回 : 3
	private String statusName;
	//columns END
	
	private UserInfoDo reciver;  //接受者的个人信息
	
	private UserInfoDo applyUser;//申请者
	
	public UserInfoDo getApplyUser() {
		return applyUser;
	}

	public void setApplyUser(UserInfoDo applyUser) {
		this.applyUser = applyUser;
	}

	public UserInfoDo getReciver() {
		return reciver;
	}

	public void setReciver(UserInfoDo reciver) {
		this.reciver = reciver;
	}

	public String getStatusName() {
		if("0".equals(status)){
			return "未审批";
		}
		if("1".equals(status)){
			return "审批通过";
		}
		if("2".equals(status)){
			return "拒绝";
		}
		if("3".equals(status)){
			return "撤回";
		}
		return "";
	}
	
	public java.lang.Integer getBeforeUpgrade() {
		return this.beforeUpgrade;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getUserId() {
		return userid;
	}

	public void setUserId(Long userid) {
		this.userid = userid;
	}

	public void setBeforeUpgrade(java.lang.Integer value) {
		this.beforeUpgrade = value;
	}
	public java.util.Date getUpgradeDate() {
		return this.upgradeDate;
	}
	
	public void setUpgradeDate(java.util.Date value) {
		this.upgradeDate = value;
	}
	public java.lang.Double getUpgradeAmount() {
		return this.upgradeAmount;
	}
	
	public void setUpgradeAmount(java.lang.Double value) {
		this.upgradeAmount = value;
	}
	
	public Long getReceiveUser() {
		return receiveUser;
	}

	public void setReceiveUser(Long receiveUser) {
		this.receiveUser = receiveUser;
	}

	public java.lang.String getPaymentMethod() {
		return this.paymentMethod;
	}
	
	public void setPaymentMethod(java.lang.String value) {
		this.paymentMethod = value;
	}
	public java.lang.String getAccountReceive() {
		return this.accountReceive;
	}
	
	public void setAccountReceive(java.lang.String value) {
		this.accountReceive = value;
	}
	public java.lang.String getAccountExpend() {
		return this.accountExpend;
	}
	
	public void setAccountExpend(java.lang.String value) {
		this.accountExpend = value;
	}
	public java.lang.Integer getAfterUpgrade() {
		return this.afterUpgrade;
	}
	
	public void setAfterUpgrade(java.lang.Integer value) {
		this.afterUpgrade = value;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Userid",getUserId())
			.append("BeforeUpgrade",getBeforeUpgrade())
			.append("UpgradeDate",getUpgradeDate())
			.append("UpgradeAmount",getUpgradeAmount())
			.append("ReceiveUser",getReceiveUser())
			.append("PaymentMethod",getPaymentMethod())
			.append("AccountReceive",getAccountReceive())
			.append("AccountExpend",getAccountExpend())
			.append("AfterUpgrade",getAfterUpgrade())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getUserId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof UserUpgradeDo == false) return false;
		if(this == obj) return true;
		UserUpgradeDo other = (UserUpgradeDo)obj;
		return new EqualsBuilder()
			.append(getUserId(),other.getUserId())
			.isEquals();
	}
}


package com.redpack.wallet.model;




import java.util.Date;

import org.apache.ibatis.type.Alias;


@Alias("walletDo")
public class WalletDo  implements java.io.Serializable{	
	
	//columns START
	private Long id;
	private java.lang.Long fkUserId;
	private java.lang.Long skUserId;
	
	private Double amt;
	//记录类型： 1 给推荐人打款， 2 给组织树打款
	private Integer type;
	private String remark;
	//0 未处理 1已处理
	private Integer fkStatus;
	private Integer skStatus;
	
	private Date createTime;
	private Date skUpdateTime;
	private Date fkUpdateTime;
	private Integer valid;
	
	private String groupName;
	
	private Long fkOptUserId;
	private Long skOptUserId;
	
	//推荐人
	private Long refUserId;
	
	private Integer fkSmsStatus; // 0 未发， 1 已发
	private Integer skSmsStatus; // 0 未发， 1 已发

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public java.lang.Long getFkUserId() {
		return fkUserId;
	}
	public void setFkUserId(java.lang.Long fkUserId) {
		this.fkUserId = fkUserId;
	}
	public java.lang.Long getSkUserId() {
		return skUserId;
	}
	public void setSkUserId(java.lang.Long skUserId) {
		this.skUserId = skUserId;
	}
	public Double getAmt() {
		return amt;
	}
	public void setAmt(Double amt) {
		this.amt = amt;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getValid() {
		return valid;
	}
	public void setValid(Integer valid) {
		this.valid = valid;
	}
	public Integer getFkStatus() {
		return fkStatus;
	}
	public void setFkStatus(Integer fkStatus) {
		this.fkStatus = fkStatus;
	}
	public Integer getSkStatus() {
		return skStatus;
	}
	public void setSkStatus(Integer skStatus) {
		this.skStatus = skStatus;
	}
	public Date getSkUpdateTime() {
		return skUpdateTime;
	}
	public void setSkUpdateTime(Date skUpdateTime) {
		this.skUpdateTime = skUpdateTime;
	}
	public Date getFkUpdateTime() {
		return fkUpdateTime;
	}
	public void setFkUpdateTime(Date fkUpdateTime) {
		this.fkUpdateTime = fkUpdateTime;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public Long getFkOptUserId() {
		return fkOptUserId;
	}
	public void setFkOptUserId(Long fkOptUserId) {
		this.fkOptUserId = fkOptUserId;
	}
	public Long getSkOptUserId() {
		return skOptUserId;
	}
	public void setSkOptUserId(Long skOptUserId) {
		this.skOptUserId = skOptUserId;
	}
	public Long getRefUserId() {
		return refUserId;
	}
	public void setRefUserId(Long refUserId) {
		this.refUserId = refUserId;
	}
	public Integer getFkSmsStatus() {
		return fkSmsStatus;
	}
	public void setFkSmsStatus(Integer fkSmsStatus) {
		this.fkSmsStatus = fkSmsStatus;
	}
	public Integer getSkSmsStatus() {
		return skSmsStatus;
	}
	public void setSkSmsStatus(Integer skSmsStatus) {
		this.skSmsStatus = skSmsStatus;
	}
	
}


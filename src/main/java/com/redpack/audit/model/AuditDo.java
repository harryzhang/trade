/*
 * Powered By zhangyunhua
 * Web Site:  
 * Since 2008 - 2015
 */

package com.redpack.audit.model;



import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.ibatis.type.Alias;

/**
 * @author zhangyunhua
 * @version 1.0
 * @since 1.0
 */


@Alias("auditDo")
public class AuditDo  implements java.io.Serializable{	
	
	//columns START
	private java.lang.Integer id;
	private java.lang.Integer proposerId;
	private java.lang.String proposerContent;
	private java.lang.Integer receiveId;
	private java.lang.Integer proposerGrade;
	private java.lang.Double proposerAmount;
	private java.util.Date proposerTime;
	private java.lang.String proposerState;
	private java.lang.String auditState;
	private java.lang.String remark;
	private java.util.Date auditTime;
	//columns END
	public java.lang.Integer getId() {
		return this.id;
	}
	
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	public java.lang.Integer getProposerId() {
		return this.proposerId;
	}
	
	public void setProposerId(java.lang.Integer value) {
		this.proposerId = value;
	}
	public java.lang.String getProposerContent() {
		return this.proposerContent;
	}
	
	public void setProposerContent(java.lang.String value) {
		this.proposerContent = value;
	}
	public java.lang.Integer getReceiveId() {
		return this.receiveId;
	}
	
	public void setReceiveId(java.lang.Integer value) {
		this.receiveId = value;
	}
	public java.lang.Integer getProposerGrade() {
		return this.proposerGrade;
	}
	
	public void setProposerGrade(java.lang.Integer value) {
		this.proposerGrade = value;
	}
	public java.lang.Double getProposerAmount() {
		return this.proposerAmount;
	}
	
	public void setProposerAmount(java.lang.Double value) {
		this.proposerAmount = value;
	}
	public java.util.Date getProposerTime() {
		return this.proposerTime;
	}
	
	public void setProposerTime(java.util.Date value) {
		this.proposerTime = value;
	}
	public java.lang.String getProposerState() {
		return this.proposerState;
	}
	
	public void setProposerState(java.lang.String value) {
		this.proposerState = value;
	}
	public java.lang.String getAuditState() {
		return this.auditState;
	}
	
	public void setAuditState(java.lang.String value) {
		this.auditState = value;
	}
	public java.lang.String getRemark() {
		return this.remark;
	}
	
	public void setRemark(java.lang.String value) {
		this.remark = value;
	}
	public java.util.Date getAuditTime() {
		return this.auditTime;
	}
	
	public void setAuditTime(java.util.Date value) {
		this.auditTime = value;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("ProposerId",getProposerId())
			.append("ProposerContent",getProposerContent())
			.append("ReceiveId",getReceiveId())
			.append("ProposerGrade",getProposerGrade())
			.append("ProposerAmount",getProposerAmount())
			.append("ProposerTime",getProposerTime())
			.append("ProposerState",getProposerState())
			.append("AuditState",getAuditState())
			.append("Remark",getRemark())
			.append("AuditTime",getAuditTime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof AuditDo == false) return false;
		if(this == obj) return true;
		AuditDo other = (AuditDo)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}


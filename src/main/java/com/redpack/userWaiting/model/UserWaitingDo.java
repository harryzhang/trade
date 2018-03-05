

package com.redpack.userWaiting.model;



import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.ibatis.type.Alias;

/**
 * 
 * 功能说明：用户等待DO
 * @author baishui
 * @2016年3月11日 下午11:55:03
 *
 * @com.redpack.userWaiting.model.UserWaitingDo.java
 */
@Alias("userWaitingDo")
public class UserWaitingDo  implements java.io.Serializable{	
	
	//columns START
	private java.lang.Integer id;
	private java.lang.String groupName;
	private java.lang.Long userId;
	private java.util.Date createTime;
	private String status;  // 0: 已失效   1：等待分配   2：已分配
	//columns END
	public java.lang.Integer getId() {
		return id;
	}
	public void setId(java.lang.Integer id) {
		this.id = id;
	}
	public java.lang.String getGroupName() {
		return groupName;
	}
	public void setGroupName(java.lang.String groupName) {
		this.groupName = groupName;
	}
	public void setUserId(java.lang.Long userId) {
		this.userId = userId;
	}
	public java.lang.Long getUserId() {
		return userId;
	}
	public java.util.Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}


/*
 * Powered By  huangzl QQ: 272950754
 * Web Site:  
 * Since 2008 - 2015
 */

package com.redpack.grade.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.ibatis.type.Alias;

/**
 * @author  huangzl QQ: 272950754
 * @version 1.0
 * @since 1.0
 */


@Alias("groupUserDo")
public class GroupUserDo  implements java.io.Serializable{	
	
	//columns START
	private java.lang.Long id;
	private java.lang.String groupName;
	private java.lang.Long userId;
	private java.lang.String status;
	private java.util.Date createtime;
	private java.util.Date updatetime;
	private java.lang.String groupuserIdx;
	//columns END
	
	
	private int sort;  //水平方向的顺序号
	private int level; //垂直方向的层级
	private long parentId;
	
	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public java.lang.Long getId() {
		return this.id;
	}
	
	public void setId(java.lang.Long value) {
		this.id = value;
	}
	public java.lang.String getGroupName() {
		return this.groupName;
	}
	
	public void setGroupName(java.lang.String value) {
		this.groupName = value;
	}
	public java.lang.Long getUserId() {
		return this.userId;
	}
	
	public void setUserId(java.lang.Long value) {
		this.userId = value;
	}
	public java.lang.String getStatus() {
		return this.status;
	}
	
	public void setStatus(java.lang.String value) {
		this.status = value;
	}
	public java.util.Date getCreatetime() {
		return this.createtime;
	}
	
	public void setCreatetime(java.util.Date value) {
		this.createtime = value;
	}
	public java.util.Date getUpdatetime() {
		return this.updatetime;
	}
	
	public void setUpdatetime(java.util.Date value) {
		this.updatetime = value;
	}
	public java.lang.String getGroupuserIdx() {
		return this.groupuserIdx;
	}
	
	public void setGroupuserIdx(java.lang.String value) {
		this.groupuserIdx = value;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("GroupName",getGroupName())
			.append("UserId",getUserId())
			.append("Status",getStatus())
			.append("Createtime",getCreatetime())
			.append("Updatetime",getUpdatetime())
			.append("GroupuserIdx",getGroupuserIdx())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof GroupUserDo == false) return false;
		if(this == obj) return true;
		GroupUserDo other = (GroupUserDo)obj;
		return new EqualsBuilder()
			.isEquals();
	}
}


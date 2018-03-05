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


@Alias("groupDo")
public class GroupDo  implements java.io.Serializable{	
	
	//columns START
	private java.lang.Long id;
	private java.lang.String groupName;
	private java.lang.String nextIdex;
	private java.lang.Long status; //0 无效， 1 ，出局， 2 建设中
	private java.lang.String networkGroup;
	private java.lang.Long groupRootUser;
	private java.lang.Long groupPersons;
	private java.lang.Long groupNextReciever;
	//columns END
	
	
	
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
	public java.lang.String getNextIdex() {
		return this.nextIdex;
	}
	
	public void setNextIdex(java.lang.String value) {
		this.nextIdex = value;
	}
	public java.lang.Long getStatus() {
		return this.status;
	}
	
	public void setStatus(java.lang.Long value) {
		this.status = value;
	}
	public java.lang.String getNetworkGroup() {
		return this.networkGroup;
	}
	
	public void setNetworkGroup(java.lang.String value) {
		this.networkGroup = value;
	}
	public java.lang.Long getGroupRootUser() {
		return this.groupRootUser;
	}
	
	public void setGroupRootUser(java.lang.Long value) {
		this.groupRootUser = value;
	}
	public java.lang.Long getGroupPersons() {
		return this.groupPersons;
	}
	
	public void setGroupPersons(java.lang.Long value) {
		this.groupPersons = value;
	}
	public java.lang.Long getGroupNextReciever() {
		return this.groupNextReciever;
	}
	
	public void setGroupNextReciever(java.lang.Long value) {
		this.groupNextReciever = value;
	}


	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("GroupName",getGroupName())
			.append("NextIdex",getNextIdex())
			.append("Status",getStatus())
			.append("NetworkGroup",getNetworkGroup())
			.append("GroupRootUser",getGroupRootUser())
			.append("GroupPersons",getGroupPersons())
			.append("GroupNextReciever",getGroupNextReciever())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof GroupDo == false) return false;
		if(this == obj) return true;
		GroupDo other = (GroupDo)obj;
		return new EqualsBuilder()
			.isEquals();
	}
}


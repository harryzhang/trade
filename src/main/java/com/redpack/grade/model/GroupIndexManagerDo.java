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


@Alias("groupIndexManagerDo")
public class GroupIndexManagerDo  implements java.io.Serializable{	
	
	//columns START
	private java.lang.Long id;
	private java.lang.Long sort;
	private java.lang.Long level;
	private String nextIndex;  //下个节点坐标
	private java.lang.String levelIndex;
	private java.lang.String remark;
	private String parentIndex;  //上级的坐标位置
	//columns END
	public java.lang.Long getId() {
		return this.id;
	}
	
	public void setId(java.lang.Long value) {
		this.id = value;
	}
	public java.lang.Long getSort() {
		return this.sort;
	}
	
	public void setSort(java.lang.Long value) {
		this.sort = value;
	}
	public java.lang.Long getLevel() {
		return this.level;
	}
	
	public void setLevel(java.lang.Long value) {
		this.level = value;
	}
	public java.lang.String getLevelIndex() {
		return this.levelIndex;
	}
	
	public void setLevelIndex(java.lang.String value) {
		this.levelIndex = value;
	}
	public java.lang.String getRemark() {
		return this.remark;
	}
	
	public void setRemark(java.lang.String value) {
		this.remark = value;
	}

	public String getParentIndex() {
		return parentIndex;
	}

	public void setParentIndex(String parentIndex) {
		this.parentIndex = parentIndex;
	}

	public String getNextIndex() {
		return nextIndex;
	}

	public void setNextIndex(String nextIndex) {
		this.nextIndex = nextIndex;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Sort",getSort())
			.append("Level",getLevel())
			.append("LevelIndex",getLevelIndex())
			.append("Remark",getRemark())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof GroupIndexManagerDo == false) return false;
		if(this == obj) return true;
		GroupIndexManagerDo other = (GroupIndexManagerDo)obj;
		return new EqualsBuilder()
			.isEquals();
	}
}


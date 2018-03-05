/*
 * Powered By zhangyunhua
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
 * @author zhangyunhua
 * @version 1.0
 * @since 1.0
 */


@Alias("gradeDo")
public class GradeDo  implements java.io.Serializable{	
	
	//columns START
	private java.lang.Integer id;
	private java.lang.String gradeName;
	private java.lang.String gradeCode;
	private java.lang.Integer sort;
	//columns END
	public java.lang.Integer getId() {
		return this.id;
	}
	
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	public java.lang.String getGradeName() {
		return this.gradeName;
	}
	
	public void setGradeName(java.lang.String value) {
		this.gradeName = value;
	}
	public java.lang.String getGradeCode() {
		return this.gradeCode;
	}
	
	public void setGradeCode(java.lang.String value) {
		this.gradeCode = value;
	}
	public java.lang.Integer getSort() {
		return this.sort;
	}
	
	public void setSort(java.lang.Integer value) {
		this.sort = value;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("GradeName",getGradeName())
			.append("GradeCode",getGradeCode())
			.append("Sort",getSort())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof GradeDo == false) return false;
		if(this == obj) return true;
		GradeDo other = (GradeDo)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}


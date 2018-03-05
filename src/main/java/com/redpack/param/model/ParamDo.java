
package com.redpack.param.model;



import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.ibatis.type.Alias;

/**
 * 
 * 功能说明：参数表
 * @author baishui
 * @2016年3月11日 下午12:11:27
 *
 * @com.redpack.base.param.model.ParamDo.java
 */

@Alias("paramDo")
public class ParamDo  implements java.io.Serializable{	
	
	//columns START
	private java.lang.Integer id;
	private java.lang.String paramName;
	private java.lang.String paramValue;
	private java.lang.String remark;
	//columns END
	public java.lang.Integer getId() {
		return id;
	}
	public void setId(java.lang.Integer id) {
		this.id = id;
	}
	public java.lang.String getParamName() {
		return paramName;
	}
	public void setParamName(java.lang.String paramName) {
		this.paramName = paramName;
	}
	public java.lang.String getParamValue() {
		return paramValue;
	}
	public void setParamValue(java.lang.String paramValue) {
		this.paramValue = paramValue;
	}
	public java.lang.String getRemark() {
		return remark;
	}
	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}
	
}


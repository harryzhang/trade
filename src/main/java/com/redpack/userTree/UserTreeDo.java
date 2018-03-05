/*
 * Powered By zhangyunhua
 * Web Site:  
 * Since 2008 - 2015
 */

package com.redpack.userTree;



import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.ibatis.type.Alias;

/**
 * 
 * @author LiuSiHai
 * Q Q:1052202173
 *
 * 2015-8-6 下午02:26:22
 * com.redpack.userTree.model.UserTreeDo.java
 */


@Alias("userTreeDo")
public class UserTreeDo  implements java.io.Serializable{	
	
	//columns START
	private java.lang.Long id;
	private java.lang.String name;
	private java.lang.String data;
	private List children;
	public java.lang.Long getId() {
		return id;
	}
	public void setId(java.lang.Long id) {
		this.id = id;
	}
	public java.lang.String getName() {
		return name;
	}
	public void setName(java.lang.String name) {
		this.name = name;
	}
	public java.lang.String getData() {
		return data;
	}
	public void setData(java.lang.String data) {
		this.data = data;
	}
	public List getChildren() {
		return children;
	}
	public void setChildren(List children) {
		this.children = children;
	}
	
}


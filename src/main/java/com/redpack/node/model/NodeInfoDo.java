package com.redpack.node.model;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.ibatis.type.Alias;

/**
 * @author zhangyunhua
 * @version 1.0
 * @since 1.0
 */

@Alias("nodeInfoDo")
public class NodeInfoDo implements java.io.Serializable {
    
	private long id;
	private long nodeNo;
	private  int level;
	private long levelNo;
	private long parentId;
	
	
	private List<NodeInfoDo> childList;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getNodeNo() {
		return nodeNo;
	}
	public void setNodeNo(long nodeNo) {
		this.nodeNo = nodeNo;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public long getLevelNo() {
		return levelNo;
	}
	public void setLevelNo(long levelNo) {
		this.levelNo = levelNo;
	}
	public long getParentId() {
		return parentId;
	}
	public void setParentId(long parentId) {
		this.parentId = parentId;
	}
	public List<NodeInfoDo> getChildList() {
		return childList;
	}
	public void setChildList(List<NodeInfoDo> childList) {
		this.childList = childList;
	}
	
	public void addChild(NodeInfoDo child){
		if(this.childList == null){
			this.childList = new ArrayList<NodeInfoDo>();
		}
		this.childList.add(child);
	}
	
	public int hashCode() {
        return new HashCodeBuilder().append(this.getId()).toHashCode();
    }
	
	@Override
	public boolean equals(Object obj) {
		if(null == obj){return false;}
		if(obj instanceof NodeInfoDo){
			return  ((NodeInfoDo)(obj)).getId() == this.getId();
		}
		return false;
	}

}

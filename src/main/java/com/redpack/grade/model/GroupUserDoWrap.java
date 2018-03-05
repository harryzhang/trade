package com.redpack.grade.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import com.redpack.account.model.UserDo;

public class GroupUserDoWrap {
	

	
//	private UserDo node;
	
	private List<GroupUserDo> childNodeList;
	
	
	
	/**
	 * 
	 * @param node 当前组的root节点
	 * @param childNodeList 当前节点的子节点
	 */
	public GroupUserDoWrap( List<GroupUserDo> childNodeList){
		//this.node = node;
		this.childNodeList = childNodeList;
	}
		
	
	
	
	
	/**
	 * 根据节点层级获取节点
	 * @param level
	 * @return
	 */
	public List<GroupUserDo> getChildByLevel(int level){
		List<GroupUserDo> levelChildList = new ArrayList<GroupUserDo>();
		if(null == childNodeList || childNodeList.size()<1){
			return null;
		}
		for(GroupUserDo node : this.childNodeList){
			if(node.getLevel() == level){
				levelChildList.add(node);
			}
		}
		Collections.sort(levelChildList,new Comparator<GroupUserDo>() {
			@Override
			public int compare(GroupUserDo o1, GroupUserDo o2) {
				return (int) (o1.getSort()-o2.getSort());
			}
		});
		return levelChildList;		
	}
	
	
	public GroupUserDo getRootUserId() {
		return getChildByLevel(1).get(0);
	}


}

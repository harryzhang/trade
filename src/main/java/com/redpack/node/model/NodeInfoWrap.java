package com.redpack.node.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import com.redpack.node.model.NodeInfoDo;



public class NodeInfoWrap {
	
	private NodeInfoDo node;
	
	private List<NodeInfoDo> childNodeList;
	
	private Map<Long,NodeInfoDo> nodeMap;
	
	/**
	 * 
	 * @param node 当前节点
	 * @param childNodeList 当前节点的子节点
	 */
	public NodeInfoWrap(NodeInfoDo node, List<NodeInfoDo> childNodeList){
		this.node = node;
		this.childNodeList = childNodeList;
	}
	
	/**
	 * 构建以 id为key 的节点map
	 */
	public void buildMap(){
		if(nodeMap != null){
			return;
		}
		nodeMap.put(node.getId(), node);
		for(NodeInfoDo n : this.childNodeList){
			nodeMap.put(n.getId(), n);
		}
	}
	
	/**
	 * 根据parentId 构建树
	 */
	public void buildTree(){
		buildMap();
		for(NodeInfoDo n : this.childNodeList){
			 NodeInfoDo p = this.nodeMap.get(n.getParentId());
			 if(p != null){
				 p.addChild(n);
			 }
		}
	}
	
	
	
	/**
	 * 根据相对当前节点的下级层数获取节点
	 * @return
	 */
	public List<NodeInfoDo> getChildByRelationLevel(int level){
		int absLevel = this.node.getLevel() + level;
		return this.getChildByLevel(absLevel);		
	}
	
	/**
	 * 根据节点层级获取节点
	 * @param level
	 * @return
	 */
	public List<NodeInfoDo> getChildByLevel(int level){
		List<NodeInfoDo> levelChildList = new ArrayList<NodeInfoDo>();
		if(null == childNodeList || childNodeList.size()<1){
			return null;
		}
		for(NodeInfoDo node : this.childNodeList){
			if(node.getLevel() == level){
				levelChildList.add(node);
			}
		}
		Collections.sort(levelChildList,new Comparator<NodeInfoDo>() {
			@Override
			public int compare(NodeInfoDo o1, NodeInfoDo o2) {
				return (int) (o1.getLevelNo()-o2.getLevelNo());
			}
		});
		return levelChildList;		
	}
	
	/**
	 * 当前节点是组长， 更加当前节点加3
	 * @return
	 */
	public int getMaxLevel() {
		return this.node.getLevel()+3;
	}
	
	
	public static void main(String[]args){
		NodeInfoDo n1 = new NodeInfoDo();
		n1.setLevel(5);
		n1.setLevelNo(2);
		
		NodeInfoDo n2 = new NodeInfoDo();
		n2.setLevel(5);
		n2.setLevelNo(5);
		
		
		NodeInfoDo n3 = new NodeInfoDo();
		n3.setLevel(5);
		n3.setLevelNo(1);
		
		NodeInfoDo n4 = new NodeInfoDo();
		n4.setLevel(4);
		n4.setLevelNo(6);
		
		
		NodeInfoDo n41 = new NodeInfoDo();
		n41.setLevel(4);
		n41.setLevelNo(1);
		
		NodeInfoDo n5 = new NodeInfoDo();
		n5.setLevel(3);
		n5.setLevelNo(1);
		n5.setId(11);
		
		List<NodeInfoDo> l = new ArrayList<NodeInfoDo>();
		l.add(n1);
		l.add(n2);
		l.add(n3);
		l.add(n4);
		l.add(n41);
		l.add(n5);
		
		NodeInfoWrap nw = new NodeInfoWrap(n5,l);
		//List<NodeInfoDo> nlist = nw.getChildByLevel(5);
		List<NodeInfoDo> nlist = nw.getChildByRelationLevel(1);
		for(int i = 0 ; i < nlist.size(); i++){
			System.out.println(nlist.get(i).getLevel() +"+"+ nlist.get(i).getLevelNo());
		}
	}

	public NodeInfoDo getRootNode() {
		return this.node;
	}

	

}

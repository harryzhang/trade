package com.redpack.account.service;

import java.util.List;

import com.redpack.grade.model.GroupDo;
import com.redpack.node.model.NodeInfoDo;

public class UserUpgradeUtil {	
	
	public static final int oneNodechildNum = 3;//三三复制
	public static final int exChidNum = 27; //分盘时，需要多少个节点
	
	/**
	 * 连接两个long类型的参数，成字符串
	 * @param l1
	 * @param l2
	 * @return
	 */
	public static String connectString(long l1, long l2){
		StringBuilder nodeNo = new StringBuilder(); 
		nodeNo.append(l1).append(l2);
		return nodeNo.toString();
	}
	
	/**
	 * 生成组名
	 * @param l1
	 * @param l2
	 * @return
	 */
	public static String genGroupName(long l1, long l2,String suffix){
		StringBuilder groupName = new StringBuilder("G"); 
		groupName.append(l1).append(l2).append("-").append(suffix);
		return groupName.toString();
	}
	
	public static GroupDo[] genNewGroup(List<NodeInfoDo> nlist){
		
		GroupDo[] ngroupArr = new  GroupDo[nlist.size()];
		
		for(int i = 0 ; i < nlist.size();i++){
			NodeInfoDo n = nlist.get(i);
			String groupName = genGroupName(n.getLevel(),n.getLevelNo(),String.valueOf(i+1));
			GroupDo  ng = new GroupDo();
			ng.setGroupName(groupName);
			ng.setGroupRootUser(n.getId());
			ngroupArr[i] = ng;
		}
		return ngroupArr;
	}
	
	
	/**
	 * 生成新的节点，并分配个各个组
	 * 
	 * @param level 新分组的时候，最大的那一层
	 * @param currentLevelMaxNo  目前已经分配到那个号， 没有分配是0
	 * @param parentIds  新节点的父节点id数组，即分盘的这个组的最后一排
	 * @return 返回新分配的节点
	 */
	public static NodeInfoDo[][] splitGroupNewNo(final int level,
												 final long currentLevelMaxNo,
												 final List<NodeInfoDo> lastLevelChild ){
		
		
		//long currentLevelNo = currentLevelMaxNo;
		int  cnt = 0; //计数器
		
		NodeInfoDo[][] childArr = new NodeInfoDo[oneNodechildNum][exChidNum];
		for(int i = 0 ; i < oneNodechildNum;i++){
			for(int k = 0; k<exChidNum; k++){
				
				//int parentIdx = k/oneNodechildNum;
				int parentIdx = cnt/oneNodechildNum; //每个户节点用oneNodechildNum次
				long parentId = lastLevelChild.get(cnt).getId();
				
				NodeInfoDo node = new NodeInfoDo();
				node.setLevel(level);
				
				//long num = 1+currentLevelMaxNo+k+i*exChidNum;
				cnt++;
				node.setLevelNo(currentLevelMaxNo+cnt);
				node.setParentId(parentId);
				
				node.setNodeNo(Long.valueOf(connectString(level,currentLevelMaxNo+cnt)));
				childArr[i][k]=node;
				//System.out.println(currentLevelNo+","+parentId+","+currentLevelNo);
			}
		}
		return childArr;
		
	}
	
}

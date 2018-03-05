/*
 * Powered By  huangzl QQ: 272950754
 * Web Site:  
 * Since 2008 - 2015
 */

package com.redpack.group.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.redpack.account.faced.IUserService;
import com.redpack.account.model.UserDo;
import com.redpack.base.result.IResult;
import com.redpack.base.result.ResultSupport;
import com.redpack.grade.model.GroupDo;
import com.redpack.grade.model.GroupIndexManagerDo;
import com.redpack.grade.model.GroupUserDo;
import com.redpack.grade.model.GroupUserDoWrap;
import com.redpack.group.IGroupService;
import com.redpack.group.dao.IGroupDao;
import com.redpack.group.dao.IGroupIndexManagerDao;
import com.redpack.group.dao.IGroupUserDao;
import com.redpack.userWaiting.dao.IUserWaitingDao;
import com.redpack.userWaiting.model.UserWaitingDo;
import com.redpack.utils.IDUtil;

/**
 * @author  huangzl QQ: 272950754
 * @version 1.0
 * @since 1.0
 */


@Service("groupService")
public class GroupServiceImpl implements IGroupService {

	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private IUserService userService;
	
	//@Autowired
	//private IUserWaitingDao userWaitingDao;
	
	@Autowired
    private IGroupDao  groupDao;
	
	@Autowired
    private IGroupUserDao  groupUserDao;
	@Autowired
	private IGroupIndexManagerDao groupIndexManagerDao;
	
	/**
	 * 根据ID 查询
	 * @parameter id
	 */
	@Override
	public GroupDo getById(int id){
	  return groupDao.getById(id);
	}
	
	/**
	 *根据条件查询列表
	 */
	@Override
	public List<GroupDo> selectGroup(Map<String,Object> parameterMap){
		return groupDao.selectGroup(parameterMap);
	}
	
	/**
	 * 更新
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public IResult<?> updateGroupById(GroupDo newGroupDo){
		logger.debug("updateGroup(GroupDo: "+newGroupDo);
		int i =groupDao.updateGroupById(newGroupDo);
		if(i<1){
			return ResultSupport.buildResult(1, newGroupDo+"更新失败");
		}
		IResult<Integer> result = ResultSupport.buildResult(0,"更新成功");
		result.setModel(Integer.valueOf(i));
		return result;
	}
	
	/**
	 * 新增
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public IResult<?> addGroup(GroupDo newGroupDo){
		logger.debug("addGroup: "+newGroupDo);
		int i =groupDao.addGroup(newGroupDo);
		if(i<1){
			return ResultSupport.buildResult(1, newGroupDo+"新增失败");
		}
		IResult<Integer> result = ResultSupport.buildResult(0,"更新成功");
		result.setModel(Integer.valueOf(i));
		return result;
	}
	
	/**
	 * 删除
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public IResult<?> deleteById(int id){
		logger.debug("deleteByIdGroup:"+id);
		int i =groupDao.deleteById(id);
		if(i<1){
			return ResultSupport.buildResult(1, id+"删除失败");
		}
		IResult<Integer> result = ResultSupport.buildResult(0,"更新成功");
		result.setModel(Integer.valueOf(i));
		return result;
	}

	
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	private void updateChilren(UserDo user,String groupName,String groupIndex){
		if(user == null){return;}
		
		GroupUserDo childGroup = new GroupUserDo();
		childGroup.setGroupName(groupName);
		//childGroup.setGroupuserIdx(groupIndex);
		childGroup.setStatus("T");
		childGroup.setUserId(user.getId());
		//新增团队和用户的关系
		groupUserDao.addGroupUser(childGroup);
				
		
		List<UserDo> userList = user.getChildList();
		if(null == userList || userList.size()< 1){
			return;
		}
		//根据当前坐标位置找出下级的坐标位置
		List<GroupIndexManagerDo> indexList = groupIndexManagerDao.selectChildIndexByCurrentIndex(groupIndex);
		for(int i = 0 ; i < indexList.size(); i++){
			UserDo child =  userList.get(i);
			//更新团队信息, 更新团队总人数， 团队下个新人的接受人，团队下个新人的坐标
			String currentIndex = indexList.get(i).getLevelIndex();
			updateChilren(child, groupName,currentIndex);
		}
		
		
	}
	
	/**
	 * 失效原来的团队和用户关系
	 * @param applyUser
	 */
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	private void UnValidGroupUseRe(UserDo applyUser){
		
		if(null == applyUser){
			return;
		}
		
		List<UserDo> children = applyUser.getChildList();
		if(null == children || children.size()<1){
			return;
		}
		
		Map<String,Object> groupUserMap = new HashMap<String,Object>();
		for(UserDo user : children){
			groupUserMap.clear();
			groupUserMap.put("userId", user.getId());
			groupUserMap.put("status", "F");
			groupUserDao.updateGroupUserByUserId(groupUserMap);
			UnValidGroupUseRe(user);
		}
	}
	
	/**
	 * 分盘的时候新增27个空的节点
	 */
	private GroupUserDo insertintoNewNode(int sort, long parentId, String groupName){
		//27个节点的第1个
		GroupUserDo firstGroupUserDo = null;
		for(int i = 1 ; i <=3; i++){
			GroupUserDo newGroupUserDo = new GroupUserDo();
			if(i== 1){
				firstGroupUserDo = newGroupUserDo;
			}
			newGroupUserDo.setGroupName(groupName);
			newGroupUserDo.setSort(sort*3+i);
			newGroupUserDo.setLevel(4);  //从根到下面开始 1， 2,3,4 依次
			newGroupUserDo.setParentId(parentId);
			newGroupUserDo.setStatus("T");
			groupUserDao.addGroupUser(newGroupUserDo);
		}
		return firstGroupUserDo;
		
	}
	/**
	 * 公排, 1,2,3,4,5 顺序排
	 * 出局申请
	 */
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public IResult applyGroupGradeOrderSort(long userId,GroupDo fenpanGroup) {
		
		//获取用户组
		GroupDo group = fenpanGroup;
		
		String oldGroupName = group.getGroupName();
		UserDo applyUser = userService.getById(userId);
		//userService.getAllChildren(applyUser);
		
		//获取组成员
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("groupName", oldGroupName);
		List<GroupUserDo> groupUserList = groupUserDao.selectGroupUser(parameterMap);
		
		//失效原来的团队和用户关系
//		Map<String,Object> groupUserMap = new HashMap<String,Object>();
//		groupUserMap.clear();
//		groupUserMap.put("userId", applyUser.getId());
//		groupUserMap.put("status", "F");
//		groupUserDao.updateGroupUserByUserId(groupUserMap);
//		UnValidGroupUseRe(applyUser);
		groupUserDao.unvalidGroupUserByGroupName(group.getGroupName());
		
		//根据组用户找到他的2，3，4级
		GroupUserDoWrap userGroupWrap = new GroupUserDoWrap(groupUserList);
		List<GroupUserDo> childList = userGroupWrap.getChildByLevel(2);
		List<GroupUserDo> childList3 = userGroupWrap.getChildByLevel(3);		
		List<GroupUserDo> childList4 = userGroupWrap.getChildByLevel(4);
		
		int cnt = 0; //团队计数器
		
		//分盘复投的节点数
		GroupUserDo firstNode=null;
		
		for(GroupUserDo child :childList){			
			//创建团队
			String groupName = IDUtil.generateGroupCode(group.getGroupName().substring(0,2));
			GroupDo newGroupDo = new GroupDo();
			newGroupDo.setGroupName(groupName);
			newGroupDo.setNextIdex("1");
			newGroupDo.setNetworkGroup("A");
			newGroupDo.setStatus(2L);
			newGroupDo.setGroupName(groupName);
			newGroupDo.setGroupRootUser(child.getUserId());
			groupDao.addGroup(newGroupDo);
	
			//更新申请出局的下一级用户的parent 为空
			//userService.updateUserParentNullById(child.getId());
			
			//updateChilren(child,groupName,"ROOT");	
			
			
			
			//更新新增团队的 groupNextReciever
//			Map<String, Object> groupUserparamMap = new HashMap<String,Object>();
//			groupUserparamMap.put("groupName", groupName);
//			groupUserparamMap.put("groupuserIdx", "B1");
//			List<GroupUserDo> newReciverList = groupUserDao.selectGroupUser(groupUserparamMap );
//			if(null != newReciverList && newReciverList.size()>0 ){
//				newGroupDo.setGroupNextReciever(newReciverList.get(0).getUserId());
//				groupDao.updateGroupById(newGroupDo);
//			}
			
			GroupUserDo  groupUserDo1= new GroupUserDo();
			groupUserDo1.setGroupName(groupName);
			groupUserDo1.setSort(1);
			groupUserDo1.setLevel(1);
			groupUserDo1.setUserId(child.getUserId());
			groupUserDo1.setStatus("T");
			groupUserDao.addGroupUser(groupUserDo1);
			
			//添加二级组空用户
			for(int k2 = 0 ; k2<3;k2++){
				GroupUserDo  groupUserDo3= childList3.get(k2+cnt*3);
				groupUserDo3.setId(null);
				groupUserDo3.setGroupName(groupName);
				groupUserDo3.setSort(k2+1);
				groupUserDo3.setLevel(2);
				groupUserDao.addGroupUser(groupUserDo3);
			}
			
			for(int k4 = 0 ; k4<9;k4++){
				GroupUserDo  groupUserDo4= childList4.get(k4+cnt*9);
				groupUserDo4.setId(null);
				groupUserDo4.setGroupName(groupName);
				groupUserDo4.setSort(k4+1);
				groupUserDo4.setLevel(3);
				groupUserDao.addGroupUser(groupUserDo4);
				
				
				//加27个空的节点
				GroupUserDo resultGroupUserDo = insertintoNewNode(k4,groupUserDo4.getUserId(), groupName);
				//复投
				if(cnt == 0 && k4==0){
					firstNode = resultGroupUserDo;
					firstNode.setUserId(applyUser.getId());
					applyUser.setUserCode1(applyUser.getUserCode()+"-1"); //复投号
					groupUserDao.updateGroupUserById(firstNode);
					userService.updateUser(applyUser);
				}
				
			}			
			
			cnt++;
		}
		
		
		
		//升到另外一个网 添加新用户 
		//找新的组
		//GroupDo upgradeGroup = findApplyGroup(applyUser,group);
		
		//更新用户信息
//		if(upgradeGroup.getGroupNextReciever().longValue() != applyUser.getId().longValue()){//不是第一个,非本人
//			applyUser.setParentId(upgradeGroup.getGroupNextReciever());
//		}
//		applyUser.setGrade(1);
//		userService.updateUser(applyUser);
//		userService.upgradeGroupInfo(applyUser, upgradeGroup);

		//更新升级后原group的状态成已完成
		group.setStatus(0L);
		groupDao.updateGroupById(group);
		
		
		//实现竞争抢位
		//racePosition(childList,userId,oldGroupName);
		
		return ResultSupport.buildResult(0, "申请成功");
		
	}
	
	
	
	/**
	 * 公排, 跳排
	 * 出局申请
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public IResult applyGroupGrade(long userId,GroupDo fenpanGroup) {

		
		//获取用户组
		GroupDo group = fenpanGroup;
		
		String oldGroupName = group.getGroupName();
		UserDo applyUser = userService.getById(userId);
		
		//获取组成员
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("groupName", oldGroupName);
		List<GroupUserDo> groupUserList = groupUserDao.selectGroupUser(parameterMap);
		
		//失效原来的团队和用户关系
		groupUserDao.unvalidGroupUserByGroupName(group.getGroupName());
		
		//根据组用户找到他的2，3，4级
		GroupUserDoWrap userGroupWrap = new GroupUserDoWrap(groupUserList);
		List<GroupUserDo> childList = userGroupWrap.getChildByLevel(2);
		List<GroupUserDo> childList3 = userGroupWrap.getChildByLevel(3);		
		List<GroupUserDo> childList4 = userGroupWrap.getChildByLevel(4);
		
		int cnt = 0; //团队计数器
		
		//分盘复投的节点数
		GroupUserDo firstNode=null;
		int kkk=1;
		for(GroupUserDo child :childList){			
			//创建团队
			String groupName = IDUtil.generateGroupCode(group.getGroupName().substring(0,2));
			groupName= groupName+kkk;
			GroupDo newGroupDo = new GroupDo();
			newGroupDo.setGroupName(groupName);
			newGroupDo.setNextIdex("1");
			newGroupDo.setNetworkGroup("A");
			newGroupDo.setStatus(2L);
			newGroupDo.setGroupName(groupName);
			newGroupDo.setGroupRootUser(child.getUserId());
			groupDao.addGroup(newGroupDo);
	
			//更新申请出局的下一级用户的parent 为空
			//userService.updateUserParentNullById(child.getId());
			
			//新增团队的 第一个节点
			GroupUserDo  groupUserDo1= new GroupUserDo();
			groupUserDo1.setGroupName(groupName);
			groupUserDo1.setSort(1);
			groupUserDo1.setLevel(1);
			groupUserDo1.setUserId(child.getUserId());
			groupUserDo1.setStatus("T");
			groupUserDao.addGroupUser(groupUserDo1);
			
			//添加二级组用户
			for(int k2 = 1 ; k2<=3;k2++){
				int groupSort = cnt+1+(k2-1)*3; // 依次找(1,4,7) (2,5,8) (3,6,9) 设置成:123
				//TODO  获取 groupUserDo 未完善
				GroupUserDo  groupUserDo3= childList3.get(groupSort-1);
				//GroupUserDo  groupUserDo3=userGroupWrap.getParentNode(2,groupSort);
				groupUserDo3.setId(null);
				groupUserDo3.setGroupName(groupName);
				groupUserDo3.setSort(k2);
				groupUserDo3.setLevel(2);
				groupUserDao.addGroupUser(groupUserDo3);
			}
			
			
			// 依次找(1,10,19) (4,13,22) (7,16,25) 设置成:(1,4,7) (2,5,8) (3,6,9)
			// 依次找(2,11,20) (5,14,23) (8,17,26) 设置成:(1,4,7) (2,5,8) (3,6,9)
			// 依次找(3,12,21) (6,15,24) (9,18,27) 设置成:(1,4,7) (2,5,8) (3,6,9)
			for(int k3 = 1 ; k3<=3;k3++){
				int groupSort = cnt+1+(k3-1)*3; 
				for(int k4 = 1 ; k4<=3;k4++){					
					int oldGroupLevel4Sort = groupSort+(k4-1)*9; //依次找{(1,10,19) (4,13,22) (7,16,25)} 计数器
					
					int newGroupLevel3Sort = k3+(k4-1)*3;   //形成 (1,4,7) (2,5,8) (3,6,9) 计数器
					
					//TODO  获取 groupUserDo 未完善
					GroupUserDo  groupUserDo4= childList4.get(oldGroupLevel4Sort-1);
					groupUserDo4.setId(null);
					groupUserDo4.setGroupName(groupName);
					groupUserDo4.setSort(newGroupLevel3Sort);
					groupUserDo4.setLevel(3);
					groupUserDao.addGroupUser(groupUserDo4);
					
					
					//加27个空的节点
					//27个节点的第1个
					for(int i = 1 ; i <=3; i++){
						int newGroupLevel4Sort = newGroupLevel3Sort+(i-1)*9; //新组的第四层下标计数器
						
						GroupUserDo newGroupUserDo = new GroupUserDo();
						if(cnt== 0 && i ==1 && k3==1 &&k4==1 ){
							firstNode = newGroupUserDo;
						}
						newGroupUserDo.setGroupName(groupName);
						newGroupUserDo.setSort(newGroupLevel4Sort);
						newGroupUserDo.setLevel(4);  //从根到下面开始 1， 2,3,4 依次
						newGroupUserDo.setParentId(groupUserDo4.getUserId());
						newGroupUserDo.setStatus("T");
						groupUserDao.addGroupUser(newGroupUserDo);
					}
				}
			}			
			cnt++;
		}
		
		//复投
		firstNode.setUserId(applyUser.getId());
		applyUser.setUserCode1(applyUser.getUserCode()+"-1"); //复投号
		groupUserDao.updateGroupUserById(firstNode);
		userService.updateUser(applyUser);

		//更新升级后原group的状态成已完成
		group.setStatus(0L);
		groupDao.updateGroupById(group);
		
		
		//实现竞争抢位
		//racePosition(childList,userId,oldGroupName);
		
		return ResultSupport.buildResult(0, "申请成功");
	}
	
	
	/**
	 * 出局申请
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public IResult applyGroupGradeForB(long userId, GroupDo fengpanGroup) {
		
		GroupDo group = fengpanGroup;
		
		String oldGroupName = group.getGroupName();
		
		UserDo applyUser = userService.getById(userId);
		//userService.getAllChildren(applyUser);
		
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("groupName", oldGroupName);
		List<GroupUserDo> groupUserList = groupUserDao.selectGroupUser(parameterMap);
		
		//失效原来的团队和用户关系
//		Map<String,Object> groupUserMap = new HashMap<String,Object>();
//		groupUserMap.clear();
//		groupUserMap.put("userId", applyUser.getId());
//		groupUserMap.put("status", "F");
//		groupUserDao.updateGroupUserByUserId(groupUserMap);
//		UnValidGroupUseRe(applyUser);
		groupUserDao.unvalidGroupUserByGroupName(group.getGroupName());
		
		GroupUserDoWrap userGroupWrap = new GroupUserDoWrap(groupUserList);
		List<GroupUserDo> childList = userGroupWrap.getChildByLevel(2);
		List<GroupUserDo> childList3 = userGroupWrap.getChildByLevel(3);		
		List<GroupUserDo> childList4 = userGroupWrap.getChildByLevel(4);
		
		int cnt = 0; //团队计数器
		
		for(GroupUserDo child :childList){			
			//创建团队
			String groupName = IDUtil.generateGroupCode(group.getGroupName().substring(0,2));
			GroupDo newGroupDo = new GroupDo();
			newGroupDo.setGroupName(groupName);
			newGroupDo.setNextIdex("1");
			newGroupDo.setStatus(2L);
			newGroupDo.setGroupName(groupName);
			newGroupDo.setGroupRootUser(child.getUserId());
			groupDao.addGroup(newGroupDo);
	
			//更新申请出局的下一级用户的parent 为空
			//userService.updateUserParentNullById(child.getId());
			
			//updateChilren(child,groupName,"ROOT");	
			
			
			
			//更新新增团队的 groupNextReciever
//			Map<String, Object> groupUserparamMap = new HashMap<String,Object>();
//			groupUserparamMap.put("groupName", groupName);
//			groupUserparamMap.put("groupuserIdx", "B1");
//			List<GroupUserDo> newReciverList = groupUserDao.selectGroupUser(groupUserparamMap );
//			if(null != newReciverList && newReciverList.size()>0 ){
//				newGroupDo.setGroupNextReciever(newReciverList.get(0).getUserId());
//				groupDao.updateGroupById(newGroupDo);
//			}
			
			GroupUserDo  groupUserDo1= new GroupUserDo();
			groupUserDo1.setGroupName(groupName);
			groupUserDo1.setSort(1);
			groupUserDo1.setLevel(1);
			groupUserDo1.setUserId(child.getUserId());
			groupUserDo1.setStatus("T");
			groupUserDao.addGroupUser(groupUserDo1);
			
			for(int k3 = 0 ; k3<3;k3++){
				GroupUserDo  groupUserDo3= childList3.get(k3+cnt*3);
				groupUserDo3.setId(null);
				groupUserDo3.setGroupName(groupName);
				groupUserDo3.setSort(k3+1);
				groupUserDo3.setLevel(2);
				groupUserDao.addGroupUser(groupUserDo3);
			}
			
			for(int k4 = 0 ; k4<9;k4++){
				GroupUserDo  groupUserDo4= childList4.get(k4+cnt*9);
				groupUserDo4.setId(null);
				groupUserDo4.setGroupName(groupName);
				groupUserDo4.setSort(k4+1);
				groupUserDo4.setLevel(3);
				groupUserDao.addGroupUser(groupUserDo4);
				
				
				//加27个空的节点
				insertintoNewNode(k4,groupUserDo4.getUserId(), groupName);
			}			
			
			cnt++;
		}
		
		
		
		//升到另外一个网 添加新用户 
		//找新的组
		//GroupDo upgradeGroup = findApplyGroup(applyUser,group);
		
		//更新用户信息
//		if(upgradeGroup.getGroupNextReciever().longValue() != applyUser.getId().longValue()){//不是第一个,非本人
//			applyUser.setParentId(upgradeGroup.getGroupNextReciever());
//		}
//		applyUser.setGrade(1);
//		userService.updateUser(applyUser);
//		userService.upgradeGroupInfo(applyUser, upgradeGroup);

		//更新升级后原group的状态成已完成
		group.setStatus(0L);
		groupDao.updateGroupById(group);
		
		
		//实现竞争抢位
		//racePosition(childList,userId,oldGroupName);
		
		return ResultSupport.buildResult(0, "申请成功");
		
	}

	//实现竞争抢位
	/**
	 * 前提当前的用户没有完成 3个推荐指标
	 * 抢位的人需要成功推荐 6个指标
	 * 按推荐人数和注册时间倒序
	 * @param childList
	 */
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	private void racePosition(List<UserDo> childList,
			                  Long applyUserId,
			                  String oldGroupName) {
		if(null == childList || childList.size()<1) {
			return;
		}
		
		//获取竞争者
		List<UserDo> raceUserList = new ArrayList<UserDo>();
		int  childSize = childList.size();
		for(int i = 0 ; i < childSize ; i++){
			UserDo child = childList.get(i);
			//实现竞争抢位
			int refCount = userService.getRefUserCount(child.getId());
			if(refCount>= 3){
				continue;
			}
			if(null == raceUserList ||  raceUserList.size()<=i){
				return;
			}
			//抢点
			//userService.repalce(child, raceUserList.get(i));
		}
		
	}

	/**
	 * 策略没有确定，随便取一个
	 * @param applyUser
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	private GroupDo findApplyGroup(UserDo applyUser,GroupDo group) {
		Map<String,Object> parameterMap = new HashMap<String,Object>(); 
		String destGroupName = "B";
		if(group.getGroupName().startsWith("GB")){
			destGroupName =  "C";
		}
		if(group.getGroupName().startsWith("GC")){
			destGroupName =  "D";
		}
		
		parameterMap.put("networkGroup", destGroupName);
		
		parameterMap.put("status", 2);
		List<GroupDo> groupList = groupDao.selectGroup(parameterMap);
		if(groupList != null && groupList.size() > 0){
			return groupList.get(0);
		}else{ //没有找到就创建一个组
			//创建团队
			String groupName = IDUtil.generateGroupCode("G"+destGroupName);
			GroupDo newGroupDo = new GroupDo();
			newGroupDo.setGroupName(groupName);
			newGroupDo.setNextIdex("A1");
			newGroupDo.setStatus(2L);
			newGroupDo.setGroupRootUser(applyUser.getId());
			newGroupDo.setGroupNextReciever(applyUser.getId());
			newGroupDo.setNetworkGroup(destGroupName);
			newGroupDo.setGroupName(groupName);
			groupDao.addGroup(newGroupDo);
			return  newGroupDo;
		}
	}
	

}

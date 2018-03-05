package com.redpacket.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.redpack.account.dao.IUserDao;
import com.redpack.account.model.UserDo;
import com.redpack.grade.model.GroupDo;
import com.redpack.grade.model.GroupUserDo;
import com.redpack.grade.model.GroupUserDoWrap;
import com.redpack.group.IGroupService;
import com.redpack.group.dao.IGroupUserDao;

public class GroupServiceTest extends BaseTestCase {
	
	/*
	 * 第二团队：
王杰：13312958358
周宁：13691750970
蔺静芳：13798529232
李强：15218712484
程晨：13538297418
李紫彤：15218712479
法沅：15817475293
陈利娟：18826548321
邓紫淇：15019266837
张苗：15814749575
尹封竹：15218712524
赵丽：13530917521
陈明东13395983155
	 */
	
	
	private String[] A_PERSONS= {"15915383462",
			"15900000001",
			"15900000002",
			"15900000003",
			"15900000004",
			"15900000005",
			"15900000006",
			"15900000007",
			"15900000008",
			"15900000009",
			"15900000010",
			"15900000011",
			"15900000012"
			                     };

	private String[] A_PERSONS_NAME= {"王子妃"
			,"王子妃1"
			,"王子妃2"
			,"王子妃3"
			,"王子妃4"
			,"王子妃5"
			,"王子妃6"
			,"王子妃7"
			,"王子妃8"
			,"王子妃9"
			,"王子妃10"
			,"王子妃11"
			,"王子妃12"
            };
	
	/*
	1】王振杰 13537722715
	2】王琪 13310867033
	3】查方胜 18520854449
	4】王丽艳 15895217007
	5】王子妃 13310861775
	6】阿明 13312920238
	7】王向芳 13813260019
	8】李松青 15085513353
	9】王兰云 13655217868
	10】唐萍 18796373777
	11】蔡可超 15195479955
	12】沈富明 15689319523
	13】宋亚环 13823794983
	*/
	
	private String[] B_PERSONS= {"B13312958358",
			"B13691750970",
			"B13798529232",
			"B15218712484",
			"B13538297418",
			"B15218712479",
			"B15817475293",
			"B18826548321",
			"B15019266837",
			"B15814749575",
			"B15218712524",
			"B13530917521",
			"B13395983155"
            };

		private String[] B_PERSONS_NAME= {"王杰"
				,"周宁"
				,"蔺静芳"
				,"李强"
				,"程晨"
				,"李紫彤"
				,"法沅"
				,"陈利娟"
				,"邓紫淇"
				,"张苗"
				,"尹封竹"
				,"赵丽"
				,"陈明东"
		};

		/**
		 * 1、王振杰13537722715
2、王琪13310867033
3、金松18520854449
4、王丽艳15895217007
5、王子妃1331086175
6、周定华13715390190
7、王大彬18273395605
8、查方胜15085018088
9、杨智慧13312957828
10、李乐乐13312920238
11、谭永麟13312932968
12、王俊凯18973332313
13、王美丽13952102997
		 */
	
	@Autowired
	private IGroupService groupService;
	
	@Autowired
	private IGroupUserDao groupUserDao;
	
	@Autowired
	private IUserDao userDao;
	
	@Test
	public void testAddGroupUser(){
		GroupUserDo newGroupDo = new GroupUserDo();
		newGroupDo.setGroupName("GA0001");
		newGroupDo.setUserId(183L);
		newGroupDo.setGroupuserIdx("ROOT");
		newGroupDo.setStatus("T");
		newGroupDo.setCreatetime(new Date());
		
		groupUserDao.addGroupUser(newGroupDo);
	}
	
	@Test
	public void testAddGroup(){
		GroupDo newGroupDo = new GroupDo();
		//newGroupDo.setGroupName("GA2016031400001");
		
		newGroupDo.setGroupName("GA2016040200001");
		
		newGroupDo.setGroupNextReciever(1L);
		newGroupDo.setNetworkGroup("A");
		newGroupDo.setGroupRootUser(1L);
		newGroupDo.setNextIdex("A1");
		newGroupDo.setStatus(2L);
		
		groupService.addGroup(newGroupDo);
		
		newGroupDo = new GroupDo();
		//newGroupDo.setGroupName("GA2016031400001");
		
		newGroupDo.setGroupName("GB2015031600001");
		
		newGroupDo.setGroupNextReciever(1L);
		newGroupDo.setNetworkGroup("B");
		newGroupDo.setGroupRootUser(1L);
		newGroupDo.setNextIdex("A1");
		newGroupDo.setStatus(2L);
		
		groupService.addGroup(newGroupDo);
	}
	
	@Test
	public void testInitGroupEmpty(){
		
		
		
		String groupName = "GA2016040200001";
		UserDo rootUserDo = new UserDo();
		rootUserDo.setUserName("15915383462");
		//rootUserDo.setWeixin("w13800000001");
		//rootUserDo.setZhifubao("z13800000001");
		rootUserDo.setName("王子妃");
		rootUserDo.setPassword("a8fdfd04ce6ae7179313122274295885");
		rootUserDo.setStatus(1);
		userDao.saveUser(rootUserDo);
		
		
		GroupDo newGroup = new GroupDo();
		//newGroupDo.setGroupName("GA2016031400001");
		
		newGroup.setGroupName(groupName);
		
		newGroup.setGroupNextReciever(1L);
		newGroup.setNetworkGroup("A");
		newGroup.setGroupRootUser(rootUserDo.getId());
		newGroup.setNextIdex("A1");
		newGroup.setStatus(2L);		
		groupService.addGroup(newGroup);
		
		GroupUserDo newGroupDo = new GroupUserDo();
		newGroupDo.setGroupName(groupName);
		newGroupDo.setUserId(rootUserDo.getId());
		newGroupDo.setGroupuserIdx("ROOT");
		newGroupDo.setStatus("T");
		newGroupDo.setCreatetime(new Date());
		newGroupDo.setSort(1);
		newGroupDo.setLevel(1);
		groupUserDao.addGroupUser(newGroupDo);
		
		
		List<UserDo> list2 = new ArrayList<UserDo>();
		for(int i = 0 ; i<3 ; i ++){
			
			UserDo userDo = new UserDo();
			String mobile= A_PERSONS[1+i];
			userDo.setUserName(mobile);
			userDo.setPassword("a8fdfd04ce6ae7179313122274295885");
			//userDo.setWeixin("w"+mobile);
			//userDo.setZhifubao("z"+mobile);
			userDo.setName(A_PERSONS_NAME[1+i]);
			userDo.setReferrerId(rootUserDo.getId());
			userDo.setStatus(1);
			userDao.saveUser(userDo);
			list2.add(userDo);
			
			newGroupDo = new GroupUserDo();
			newGroupDo.setGroupName(groupName);
			newGroupDo.setParentId(rootUserDo.getId());
			newGroupDo.setUserId(userDo.getId());
			newGroupDo.setStatus("T");
			newGroupDo.setCreatetime(new Date());
			newGroupDo.setSort(i+1);
			newGroupDo.setLevel(2);
			groupUserDao.addGroupUser(newGroupDo);
		}
		
		List<UserDo> list3 = new ArrayList<UserDo>();
		for(int i = 0 ; i<9 ; i ++){
			
			UserDo userDo = new UserDo();
			String mobile= A_PERSONS[4+i];
			userDo.setUserName(mobile);
			userDo.setPassword("a8fdfd04ce6ae7179313122274295885");
			//userDo.setWeixin("w"+mobile);
			//userDo.setZhifubao("z"+mobile);
			userDo.setName(A_PERSONS_NAME[4+i]);
			userDo.setReferrerId(list2.get(i/3).getId());
			userDo.setStatus(1);
			userDao.saveUser(userDo);
			list3.add(userDo);
			
			newGroupDo = new GroupUserDo();
			newGroupDo.setGroupName(groupName);
			newGroupDo.setParentId(list2.get(i/3).getId());
			newGroupDo.setUserId(userDo.getId());
			newGroupDo.setStatus("T");
			newGroupDo.setCreatetime(new Date());
			newGroupDo.setSort(i+1);
			newGroupDo.setLevel(3);
			groupUserDao.addGroupUser(newGroupDo);
		}
		
		for(int i = 0 ; i<27 ; i ++){
			
			newGroupDo = new GroupUserDo();
			newGroupDo.setGroupName(groupName);
			newGroupDo.setParentId(list3.get(i/3).getId());
			newGroupDo.setStatus("T");
			newGroupDo.setCreatetime(new Date());
			newGroupDo.setSort(i+1);
			newGroupDo.setLevel(4);
			groupUserDao.addGroupUser(newGroupDo);
		}
		
	}
	
	
	@Test
	public void testInitGroupA(){
		
		
		
		String groupName = "GA2016040200001";
		UserDo rootUserDo = new UserDo();
		rootUserDo.setUserName(A_PERSONS[0]);
		//rootUserDo.setWeixin("w13800000001");
		//rootUserDo.setZhifubao("z13800000001");
		rootUserDo.setName(A_PERSONS_NAME[0]);
		rootUserDo.setPassword("a8fdfd04ce6ae7179313122274295885");
		rootUserDo.setStatus(1);
		userDao.saveUser(rootUserDo);
		
		
		GroupDo newGroup = new GroupDo();
		//newGroupDo.setGroupName("GA2016031400001");
		
		newGroup.setGroupName(groupName);
		
		newGroup.setGroupNextReciever(1L);
		newGroup.setNetworkGroup("A");
		newGroup.setGroupRootUser(rootUserDo.getId());
		newGroup.setNextIdex("A1");
		newGroup.setStatus(2L);		
		groupService.addGroup(newGroup);
		
		GroupUserDo newGroupDo = new GroupUserDo();
		newGroupDo.setGroupName(groupName);
		newGroupDo.setUserId(rootUserDo.getId());
		newGroupDo.setGroupuserIdx("ROOT");
		newGroupDo.setStatus("T");
		newGroupDo.setCreatetime(new Date());
		newGroupDo.setSort(1);
		newGroupDo.setLevel(1);
		groupUserDao.addGroupUser(newGroupDo);
		
		
		List<UserDo> list2 = new ArrayList<UserDo>();
		for(int i = 0 ; i<3 ; i ++){
			
			UserDo userDo = new UserDo();
			String mobile= A_PERSONS[1+i];
			userDo.setUserName(mobile);
			userDo.setPassword("a8fdfd04ce6ae7179313122274295885");
			//userDo.setWeixin("w"+mobile);
			//userDo.setZhifubao("z"+mobile);
			userDo.setName(A_PERSONS_NAME[1+i]);
			userDo.setReferrerId(rootUserDo.getId());
			userDo.setStatus(1);
			userDao.saveUser(userDo);
			list2.add(userDo);
			
			newGroupDo = new GroupUserDo();
			newGroupDo.setGroupName(groupName);
			newGroupDo.setParentId(rootUserDo.getId());
			newGroupDo.setUserId(userDo.getId());
			newGroupDo.setStatus("T");
			newGroupDo.setCreatetime(new Date());
			newGroupDo.setSort(i+1);
			newGroupDo.setLevel(2);
			groupUserDao.addGroupUser(newGroupDo);
		}
		
		List<UserDo> list3 = new ArrayList<UserDo>();
		for(int i = 0 ; i<9 ; i ++){
			
			UserDo userDo = new UserDo();
			String mobile= A_PERSONS[4+i];
			userDo.setUserName(mobile);
			userDo.setPassword("a8fdfd04ce6ae7179313122274295885");
			//userDo.setWeixin("w"+mobile);
			//userDo.setZhifubao("z"+mobile);
			userDo.setName(A_PERSONS_NAME[4+i]);
			userDo.setReferrerId(list2.get(i/3).getId());
			userDo.setStatus(1);
			userDao.saveUser(userDo);
			list3.add(userDo);
			
			newGroupDo = new GroupUserDo();
			newGroupDo.setGroupName(groupName);
			newGroupDo.setParentId(list2.get(i/3).getId());
			newGroupDo.setUserId(userDo.getId());
			newGroupDo.setStatus("T");
			newGroupDo.setCreatetime(new Date());
			newGroupDo.setSort(i+1);
			newGroupDo.setLevel(3);
			groupUserDao.addGroupUser(newGroupDo);
		}
		
		for(int i = 0 ; i<27 ; i ++){
			
			newGroupDo = new GroupUserDo();
			newGroupDo.setGroupName(groupName);
			newGroupDo.setParentId(list3.get(i/3).getId());
			newGroupDo.setStatus("T");
			newGroupDo.setCreatetime(new Date());
			newGroupDo.setSort(i+1);
			newGroupDo.setLevel(4);
			groupUserDao.addGroupUser(newGroupDo);
		}
		
	}
	
	@Test
	public void testInitGroupB(){
		String groupName = "GB2014031600002";
		UserDo rootUserDo = new UserDo();
		rootUserDo.setUserName(B_PERSONS[0]);
		//rootUserDo.setWeixin("w13800000001");
		//rootUserDo.setZhifubao("z13800000001");
		rootUserDo.setName(B_PERSONS_NAME[0]);
		rootUserDo.setPassword("a8fdfd04ce6ae7179313122274295885");
		rootUserDo.setStatus(1);
		userDao.saveUser(rootUserDo);
		
		
		GroupDo newGroup = new GroupDo();
		//newGroupDo.setGroupName("GA2016031400001");
		
		newGroup.setGroupName(groupName);
		
		newGroup.setGroupNextReciever(1L);
		newGroup.setNetworkGroup("B");
		newGroup.setGroupRootUser(rootUserDo.getId());
		newGroup.setNextIdex("A1");
		newGroup.setStatus(2L);		
		groupService.addGroup(newGroup);
		
		
		GroupUserDo newGroupDo = new GroupUserDo();
		newGroupDo.setGroupName(groupName);
		newGroupDo.setUserId(rootUserDo.getId());
		newGroupDo.setGroupuserIdx("ROOT");
		newGroupDo.setStatus("T");
		newGroupDo.setCreatetime(new Date());
		newGroupDo.setSort(1);
		newGroupDo.setLevel(1);
		groupUserDao.addGroupUser(newGroupDo);
		
		
		List<UserDo> list2 = new ArrayList<UserDo>();
		for(int i = 0 ; i<3 ; i ++){
			
			UserDo userDo = new UserDo();
			String mobile= B_PERSONS[1+i];
			userDo.setUserName(mobile);
			userDo.setPassword("a8fdfd04ce6ae7179313122274295885");
			//userDo.setWeixin("w"+mobile);
			//userDo.setZhifubao("z"+mobile);
			userDo.setName(B_PERSONS_NAME[1+i]);
			userDo.setReferrerId(rootUserDo.getId());
			userDo.setStatus(1);
			userDao.saveUser(userDo);
			list2.add(userDo);
			
			newGroupDo = new GroupUserDo();
			newGroupDo.setGroupName(groupName);
			newGroupDo.setParentId(rootUserDo.getId());
			newGroupDo.setUserId(userDo.getId());
			newGroupDo.setStatus("T");
			newGroupDo.setCreatetime(new Date());
			newGroupDo.setSort(i+1);
			newGroupDo.setLevel(2);
			groupUserDao.addGroupUser(newGroupDo);
		}
		
		List<UserDo> list3 = new ArrayList<UserDo>();
		for(int i = 0 ; i<9 ; i ++){
			
			UserDo userDo = new UserDo();
			String mobile= B_PERSONS[4+i];
			userDo.setUserName(mobile);
			userDo.setPassword("a8fdfd04ce6ae7179313122274295885");
			//userDo.setWeixin("w"+mobile);
			//userDo.setZhifubao("z"+mobile);
			userDo.setName(B_PERSONS_NAME[4+i]);
			userDo.setReferrerId(list2.get(i/3).getId());
			userDo.setStatus(1);
			userDao.saveUser(userDo);
			list3.add(userDo);
			
			newGroupDo = new GroupUserDo();
			newGroupDo.setGroupName(groupName);
			newGroupDo.setParentId(list2.get(i/3).getId());
			newGroupDo.setUserId(userDo.getId());
			newGroupDo.setStatus("T");
			newGroupDo.setCreatetime(new Date());
			newGroupDo.setSort(i+1);
			newGroupDo.setLevel(3);
			groupUserDao.addGroupUser(newGroupDo);
		}
		
		for(int i = 0 ; i<27 ; i ++){
			
			newGroupDo = new GroupUserDo();
			newGroupDo.setGroupName(groupName);
			newGroupDo.setParentId(list3.get(i/3).getId());
			newGroupDo.setStatus("T");
			newGroupDo.setCreatetime(new Date());
			newGroupDo.setSort(i+1);
			newGroupDo.setLevel(4);
			groupUserDao.addGroupUser(newGroupDo);
		}
		
	}
	
	
	@Test
	public void testApplyGroupGrade(){
		long currentUserId = 961L;
		String groupName ="GA2016032100001";
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("groupName", groupName);
		List<GroupDo> groupList = groupService.selectGroup(parameterMap);
		groupService.applyGroupGrade(groupList.get(0).getGroupRootUser(),groupList.get(0));
	}
	
	
	@Test
	public void testConvertOldData(){
		int[] level3={1,4,7,2,5,8,3,6,9};
		int[] level4={1,10,19,
				      4,13,22,
				      7,16,25,
				      2,11,20,
				      5,14,23,
				      8,17,26,
				      3,12,21,
				      6,15,24,
				      9,18,27};
		String groups[] = {"GB2015031600001"
				,"GB2014031600002"
				,"GA2016031700001"
				,"GA2016031800001"
				,"GA2016031800002"
				,"GA2016031800003"
				,"GA20160318000012"
				,"GA20160318000023"
				,"GA20160318000034"
				,"GA2016032000001"
				,"GA2016032000002"
				,"GA2016032000003"};
		for(String group : groups){
			Map<String,Object> parameterMap = new HashMap<String,Object>();
			parameterMap.put("groupName", group);
			List<GroupUserDo>  grouUserList = groupUserDao.selectGroupUser(parameterMap);
			for(GroupUserDo groupUser : grouUserList){
				
				int level = groupUser.getLevel();
				int sort = groupUser.getSort();
				if(level == 1){continue;}			
				if(level == 2){continue;}
				if(level==3){
					groupUser.setSort(level3[groupUser.getSort()-1]);
				}
				if(level==4){
					groupUser.setSort(level4[groupUser.getSort()-1]);
				}
				
				groupUserDao.updateGroupUserById(groupUser);
			}
		}		
	}
	
	
	
	/**
	 * 会所数据转换
	 */
	@Test
	public void testConvertYsjkData(){
		
		int[] level3={1,4,7,2,5,8,3,6,9};
		int[] level4={1,10,19,
				      4,13,22,
				      7,16,25,
				      2,11,20,
				      5,14,23,
				      8,17,26,
				      3,12,21,
				      6,15,24,
				      9,18,27};
		String groups[] = {
				"GA2015102400001"
				,"GA2015102800001"
				,"GA2015102800002"
				,"GA2015102800003"
				,"GA2015112400001"
				,"GA2015120800001"
				,"GA2015120800002"
				,"GA2015120800003"
				,"GA2015121100010"
				,"GA2015121100011"
				,"GA2015121100012"
				,"GA2015121200001"
				,"GA2015121200002"
				,"GA2015121200003"
				,"GA2015122500001"
				,"GA2015122500002"
				,"GA2015122500003"

		};
		for(String group : groups){
			Map<String,Object> parameterMap = new HashMap<String,Object>();
			parameterMap.put("groupName", group);
			List<GroupUserDo>  grouUserList = groupUserDao.selectGroupUser(parameterMap);
			GroupUserDoWrap  groupuserWrap = new GroupUserDoWrap(grouUserList); 
			for(GroupUserDo groupUser : grouUserList){
				
				String groupIdx = groupUser.getGroupuserIdx();
				String sort = groupIdx.substring(1);
				if("ROOT".equalsIgnoreCase(groupIdx)){
					groupUser.setLevel(1);
					groupUser.setSort(1);					
				}			
				if(groupIdx.startsWith("A")){
					groupUser.setLevel(2);
					groupUser.setSort(Integer.valueOf(sort));
					List<GroupUserDo> levelChildList = groupuserWrap.getChildByLevel(1);
					if(null != levelChildList && levelChildList.size()>0){
						groupUser.setParentId(levelChildList.get(0).getUserId());
					}
				}
				if(groupIdx.startsWith("B")){
					groupUser.setLevel(3);					
					groupUser.setSort(level3[Integer.valueOf(sort)-1]);
					List<GroupUserDo> levelChildList = groupuserWrap.getChildByLevel(2);
					if(null != levelChildList && levelChildList.size()>2){
						GroupUserDo gu = null;
						if(Integer.valueOf(sort).intValue() == 1 ||
								Integer.valueOf(sort).intValue() == 4||
										Integer.valueOf(sort).intValue() == 7){
							gu = levelChildList.get(1-1);
						}
						if(Integer.valueOf(sort).intValue() == 2 ||
								Integer.valueOf(sort).intValue() == 5||
										Integer.valueOf(sort).intValue() == 8){
							gu = levelChildList.get(2-1);
						}
						if(Integer.valueOf(sort).intValue() == 3 ||
								Integer.valueOf(sort).intValue() == 6||
										Integer.valueOf(sort).intValue() == 9){
							gu = levelChildList.get(3-1);
						}
						groupUser.setParentId(gu == null? null:gu.getUserId());
					}
				}
				if(groupIdx.startsWith("C")){
					groupUser.setLevel(4);
					groupUser.setSort(level4[Integer.valueOf(sort)-1]);
					List<GroupUserDo> levelChildList = groupuserWrap.getChildByLevel(3);
					if(null != levelChildList && levelChildList.size()>8){
						GroupUserDo gu = null;
						if(Integer.valueOf(sort).intValue() == 1 ||
								Integer.valueOf(sort).intValue() == 10||
										Integer.valueOf(sort).intValue() == 19){
							gu = levelChildList.get(1-1);
						}
						if(Integer.valueOf(sort).intValue() == 2 ||
								Integer.valueOf(sort).intValue() == 11||
										Integer.valueOf(sort).intValue() == 20){
							gu = levelChildList.get(2-1);
						}
						if(Integer.valueOf(sort).intValue() == 3 ||
								Integer.valueOf(sort).intValue() == 12||
										Integer.valueOf(sort).intValue() == 21){
							gu = levelChildList.get(3-1);
						}
						if(Integer.valueOf(sort).intValue() == 4 ||
								Integer.valueOf(sort).intValue() == 13||
										Integer.valueOf(sort).intValue() == 22){
							gu = levelChildList.get(4-1);
						}
						if(Integer.valueOf(sort).intValue() == 5 ||
								Integer.valueOf(sort).intValue() == 14||
										Integer.valueOf(sort).intValue() == 23){
							gu = levelChildList.get(5-1);
						}
						if(Integer.valueOf(sort).intValue() == 6 ||
								Integer.valueOf(sort).intValue() == 15||
										Integer.valueOf(sort).intValue() == 24){
							gu = levelChildList.get(6-1);
						}
						if(Integer.valueOf(sort).intValue() == 7 ||
								Integer.valueOf(sort).intValue() == 16||
										Integer.valueOf(sort).intValue() == 25){
							gu = levelChildList.get(7-1);
						}
						if(Integer.valueOf(sort).intValue() == 8 ||
								Integer.valueOf(sort).intValue() == 17||
										Integer.valueOf(sort).intValue() == 26){
							gu = levelChildList.get(8-1);
						}
						if(Integer.valueOf(sort).intValue() == 9 ||
								Integer.valueOf(sort).intValue() == 18||
										Integer.valueOf(sort).intValue() == 27){
							gu = levelChildList.get(9-1);
						}
						groupUser.setParentId(gu == null? null:gu.getUserId());
					}
					
				}	
				groupUserDao.updateGroupUserById(groupUser);
			}
		}
	}
	
	
	

	/**
	 * 会所数据转换
	 */
	@Test
	public void testConvertYsjkFor40Data(){
		
		int[] level3={1,4,7,2,5,8,3,6,9};
		int[] level4={1,10,19,
				      4,13,22,
				      7,16,25,
				      2,11,20,
				      5,14,23,
				      8,17,26,
				      3,12,21,
				      6,15,24,
				      9,18,27};
		String groups[] = {
				"GA2015102400001"
				,"GA2015102800001"
				,"GA2015102800002"
				,"GA2015102800003"
				,"GA2015112400001"
				,"GA2015120800001"
				,"GA2015120800002"
				,"GA2015120800003"
				,"GA2015121100010"
				,"GA2015121100011"
				,"GA2015121100012"
				,"GA2015121200001"
				,"GA2015121200002"
				,"GA2015121200003"
				,"GA2015122500001"
				,"GA2015122500002"
				,"GA2015122500003"

		};
		for(String group : groups){
			Map<String,Object> parameterMap = new HashMap<String,Object>();
			parameterMap.put("groupName", group);
			List<GroupUserDo>  grouUserList = groupUserDao.selectGroupUser(parameterMap);
			GroupUserDoWrap  groupuserWrap = new GroupUserDoWrap(grouUserList); 
			for(GroupUserDo groupUser : grouUserList){
				
				String groupIdx = groupUser.getGroupuserIdx();
				String sort = groupIdx.substring(1);
				if("ROOT".equalsIgnoreCase(groupIdx)){
					groupUser.setLevel(1);
					groupUser.setSort(1);					
				}			
				if(groupIdx.startsWith("A")){
					groupUser.setLevel(2);
					groupUser.setSort(Integer.valueOf(sort));
					List<GroupUserDo> levelChildList = groupuserWrap.getChildByLevel(1);
					if(null != levelChildList && levelChildList.size()>0){
						groupUser.setParentId(levelChildList.get(0).getUserId());
					}
				}
				if(groupIdx.startsWith("B")){
					groupUser.setLevel(3);					
					groupUser.setSort(level3[Integer.valueOf(sort)-1]);
					List<GroupUserDo> levelChildList = groupuserWrap.getChildByLevel(2);
					if(null != levelChildList && levelChildList.size()>2){
						GroupUserDo gu = null;
						if(Integer.valueOf(sort).intValue() == 1 ||
								Integer.valueOf(sort).intValue() == 4||
										Integer.valueOf(sort).intValue() == 7){
							gu = levelChildList.get(1-1);
						}
						if(Integer.valueOf(sort).intValue() == 2 ||
								Integer.valueOf(sort).intValue() == 5||
										Integer.valueOf(sort).intValue() == 8){
							gu = levelChildList.get(2-1);
						}
						if(Integer.valueOf(sort).intValue() == 3 ||
								Integer.valueOf(sort).intValue() == 6||
										Integer.valueOf(sort).intValue() == 9){
							gu = levelChildList.get(3-1);
						}
						groupUser.setParentId(gu == null? null:gu.getUserId());
					}
				}
				if(groupIdx.startsWith("C")){
					groupUser.setLevel(4);
					groupUser.setSort(level4[Integer.valueOf(sort)-1]);
					List<GroupUserDo> levelChildList = groupuserWrap.getChildByLevel(3);
					if(null != levelChildList && levelChildList.size()>8){
						GroupUserDo gu = null;
						if(Integer.valueOf(sort).intValue() == 1 ||
								Integer.valueOf(sort).intValue() == 10||
										Integer.valueOf(sort).intValue() == 19){
							gu = levelChildList.get(1-1);
						}
						if(Integer.valueOf(sort).intValue() == 2 ||
								Integer.valueOf(sort).intValue() == 11||
										Integer.valueOf(sort).intValue() == 20){
							gu = levelChildList.get(2-1);
						}
						if(Integer.valueOf(sort).intValue() == 3 ||
								Integer.valueOf(sort).intValue() == 12||
										Integer.valueOf(sort).intValue() == 21){
							gu = levelChildList.get(3-1);
						}
						if(Integer.valueOf(sort).intValue() == 4 ||
								Integer.valueOf(sort).intValue() == 13||
										Integer.valueOf(sort).intValue() == 22){
							gu = levelChildList.get(4-1);
						}
						if(Integer.valueOf(sort).intValue() == 5 ||
								Integer.valueOf(sort).intValue() == 14||
										Integer.valueOf(sort).intValue() == 23){
							gu = levelChildList.get(5-1);
						}
						if(Integer.valueOf(sort).intValue() == 6 ||
								Integer.valueOf(sort).intValue() == 15||
										Integer.valueOf(sort).intValue() == 24){
							gu = levelChildList.get(6-1);
						}
						if(Integer.valueOf(sort).intValue() == 7 ||
								Integer.valueOf(sort).intValue() == 16||
										Integer.valueOf(sort).intValue() == 25){
							gu = levelChildList.get(7-1);
						}
						if(Integer.valueOf(sort).intValue() == 8 ||
								Integer.valueOf(sort).intValue() == 17||
										Integer.valueOf(sort).intValue() == 26){
							gu = levelChildList.get(8-1);
						}
						if(Integer.valueOf(sort).intValue() == 9 ||
								Integer.valueOf(sort).intValue() == 18||
										Integer.valueOf(sort).intValue() == 27){
							gu = levelChildList.get(9-1);
						}
						groupUser.setParentId(gu == null? null:gu.getUserId());
					}
					
				}	
				groupUserDao.updateGroupUserById(groupUser);
			}
		}
	}
}

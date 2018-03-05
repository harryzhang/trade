package com.redpack.group.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.redpack.account.dao.IUserDao;
import com.redpack.account.faced.IUserService;
import com.redpack.account.model.UserDo;
import com.redpack.base.controller.BaseController;
import com.redpack.grade.model.GroupDo;
import com.redpack.grade.model.GroupUserDo;
import com.redpack.group.IGroupService;
import com.redpack.group.dao.IGroupUserDao;
import com.redpack.utils.ResponseUtils;

import net.sf.json.JSONObject;


/**
 * 组管理
 * @author:  zhangyunhua
 * @date 2015年3月5日 上午11:01:51
 */
@Controller
@RequestMapping("/groupManager")
public class GroupController  extends BaseController{
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	IGroupService groupService;
	
	@Autowired
	IUserService  userService;
	
	@Autowired
	private IUserDao userDao;
	
	@Autowired
	private IGroupUserDao groupUserDao;
	
	/**
	 * 组列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/listGroup")
    public String listGroup(HttpServletRequest request,ModelMap map,HttpServletResponse response) {
		logger.debug("listGroup");
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("userid", this.getUserId(request));
		List<GroupDo> groupList = groupService.selectGroup(parameterMap);
		map.put("groupList", groupList);
        return "group/listGroup";
    }
	
	
	/**
	 * 添加组与顶级成员
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/addGroup")
	public String addGroup(HttpServletRequest request,ModelMap map,HttpServletResponse response) {
		logger.debug("addGroup");
		return "group/addGroup";
	}
	/**
	 * 添加组与顶级成员
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/addGroupUser")
	public String addGroupUser(HttpServletRequest request,ModelMap map,HttpServletResponse response) {
		logger.debug("addGroupUser");
		return "group/addGroupUser";
	}
	
	/**
	 * 保存群组
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "saveGroup")
	public void saveGroup(HttpServletRequest request,HttpServletResponse response) {
		String groupName = request.getParameter("groupName");
		String mobile = request.getParameter("mobile");
		String name = request.getParameter("name");
		JSONObject jsonObject = new JSONObject();
		
		Map paramMap = new HashMap();
		paramMap.put("groupName", groupName);
		List<GroupDo> group = groupService.selectGroup(paramMap);
		if( null != group && group.size()>0){
			jsonObject.put("result", 1);
			jsonObject.put("message", "组名已存在，请重新输入");
			ResponseUtils.renderText(response, null, jsonObject.toString());
			return;
		}
		
		paramMap.clear();
		paramMap.put("userName", mobile);
		UserDo userDo = userService.getByUserDo(paramMap);
		if( null != userDo && userDo.getId()>0){
			jsonObject.put("result", 1);
			jsonObject.put("message", "此手机号码已存在用户，请换个号码");
			ResponseUtils.renderText(response, null, jsonObject.toString());
			return;
		}
		
		//保存用户
		UserDo saveDo = new UserDo();
		saveDo.setCreateTime(new Date());
		saveDo.setName(name);
		saveDo.setUserName(mobile);
		saveDo.setPassword("a8fdfd04ce6ae7179313122274295885");
		saveDo.setRemark("建组顶级用户");
		saveDo.setStatus(1);
		userDao.saveUser(saveDo);
		
		//保存组
		GroupDo newGroupDo = new GroupDo();
		newGroupDo.setGroupName(groupName);
		
		newGroupDo.setGroupNextReciever(1L);
		newGroupDo.setNetworkGroup("A");
		newGroupDo.setGroupRootUser(saveDo.getId());
		newGroupDo.setNextIdex("A1");
		newGroupDo.setStatus(2L);
		groupService.addGroup(newGroupDo);
		
		//保存组用户
		GroupUserDo groupUserDo = new GroupUserDo();
		groupUserDo.setGroupName(groupName);
		groupUserDo.setUserId(saveDo.getId());
		groupUserDo.setGroupuserIdx("ROOT");
		groupUserDo.setStatus("T");
		groupUserDo.setCreatetime(new Date());
		groupUserDo.setSort(1);
		groupUserDo.setLevel(1);
		groupUserDao.addGroupUser(groupUserDo);
		
		List<UserDo> list2 = new ArrayList<UserDo>();
		for(int i = 0 ; i<3 ; i ++){
			
			userDo = new UserDo();
			mobile= groupName +"1" + i;
			userDo.setUserName(mobile);
			userDo.setPassword("a8fdfd04ce6ae7179313122274295885");
			//userDo.setWeixin("w"+mobile);
			//userDo.setZhifubao("z"+mobile);
			userDo.setName(mobile);
			userDo.setReferrerId(saveDo.getId());
			userDo.setStatus(1);
			userDo.setRemark("手工建组默认加用户");
			userDao.saveUser(userDo);
			list2.add(userDo);
			
			groupUserDo = new GroupUserDo();
			groupUserDo.setGroupName(groupName);
			groupUserDo.setParentId(saveDo.getId());
			groupUserDo.setUserId(userDo.getId());
			groupUserDo.setStatus("T");
			groupUserDo.setCreatetime(new Date());
			groupUserDo.setSort(i+1);
			groupUserDo.setLevel(2);
			groupUserDao.addGroupUser(groupUserDo);
		}
		
		List<UserDo> list3 = new ArrayList<UserDo>();
		for(int i = 0 ; i<9 ; i ++){
			
			userDo = new UserDo();
			mobile= groupName + "2" +i;
			userDo.setUserName(mobile);
			userDo.setPassword("a8fdfd04ce6ae7179313122274295885");
			//userDo.setWeixin("w"+mobile);
			//userDo.setZhifubao("z"+mobile);
			userDo.setName(mobile);
			userDo.setReferrerId(list2.get(i/3).getId());
			userDo.setStatus(1);
			userDo.setRemark("手工建组默认加用户");
			userDao.saveUser(userDo);
			list3.add(userDo);
			
			groupUserDo = new GroupUserDo();
			groupUserDo.setGroupName(groupName);
			groupUserDo.setParentId(list2.get(i/3).getId());
			groupUserDo.setUserId(userDo.getId());
			groupUserDo.setStatus("T");
			groupUserDo.setCreatetime(new Date());
			groupUserDo.setSort(i+1);
			groupUserDo.setLevel(3);
			groupUserDao.addGroupUser(groupUserDo);
		}
		
		for(int i = 0 ; i<27 ; i ++){
			
			groupUserDo = new GroupUserDo();
			groupUserDo.setGroupName(groupName);
			groupUserDo.setParentId(list3.get(i/3).getId());
			groupUserDo.setStatus("T");
			groupUserDo.setCreatetime(new Date());
			groupUserDo.setSort(i+1);
			groupUserDo.setLevel(4);
			groupUserDao.addGroupUser(groupUserDo);
		}
		
		jsonObject.put("result", 0);
		ResponseUtils.renderText(response, null, jsonObject.toString());
		
	}
	
	
	/**
	 * 保存群组用户
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "saveGroupUser")
	public void saveGroupUser(HttpServletRequest request, HttpServletResponse response) {
		String groupName = request.getParameter("groupName");
		String mobile = request.getParameter("mobile");
		String refferMobile = request.getParameter("refferMobile");
		String name = request.getParameter("name");
		JSONObject jsonObject = new JSONObject();

		Map paramMap = new HashMap();
		paramMap.put("groupName", groupName);
		List<GroupDo> group = groupService.selectGroup(paramMap);
		if (null == group || group.size() < 1) {
			jsonObject.put("result", 1);
			jsonObject.put("message", "组名不存在，请重新输入");
			ResponseUtils.renderText(response, null, jsonObject.toString());
			return;
		}

		paramMap.clear();
		paramMap.put("userName", mobile);
		UserDo userDo = userService.getByUserDo(paramMap);
		if (null != userDo && userDo.getId() > 0) {
			jsonObject.put("result", 1);
			jsonObject.put("message", "此手机号码已存在用户，请换个号码");
			ResponseUtils.renderText(response, null, jsonObject.toString());
			return;
		}

		paramMap.clear();
		paramMap.put("userName", refferMobile);
		UserDo userRefferDo = userService.getByUserDo(paramMap);
		if (null == userRefferDo || userRefferDo.getId() < 0) {
			jsonObject.put("result", 1);
			jsonObject.put("message", "推荐人手机用户不存在");
			ResponseUtils.renderText(response, null, jsonObject.toString());
			return;
		}

		// 保存用户
		UserDo saveDo = new UserDo();
		saveDo.setCreateTime(new Date());
		saveDo.setName(name);
		saveDo.setUserName(mobile);
		saveDo.setPassword("a8fdfd04ce6ae7179313122274295885");
		saveDo.setRemark("手工添加群组用户");
		saveDo.setStatus(1);
		userDao.saveUser(saveDo);
		// 寻找团队剩余位置,如果没有则进行等待
		paramMap.clear();
		paramMap = new HashMap<String, Object>();
		paramMap.put("groupName", groupName);
		List<GroupUserDo> groupUserDoList = groupUserDao.selectGroupNullUser(paramMap);
		// 没有空位进行等待
		if (null == groupUserDoList || groupUserDoList.size() < 1) {
			jsonObject.put("result", 1);
			jsonObject.put("message", "用户已满,不需再添加");
			ResponseUtils.renderText(response, null, jsonObject.toString());
			return;
		} else {
			// 更新团队空位信息
			GroupUserDo groupUserDo = groupUserDoList.get(0);
			groupUserDo.setUserId(saveDo.getId());
			groupUserDao.updateGroupUserById(groupUserDo);
		}

		jsonObject.put("result", 0);
		ResponseUtils.renderText(response, null, jsonObject.toString());

	}
	
//	private String buildMsg(GroupDo[] newGroup){
//		StringBuilder sb = new StringBuilder();
//		for(GroupDo g : newGroup){
//			sb.append(" 组：").append(g.getGroupName()).append("分配的号段为：").append(g.getStartNodeNo()).append("--").append(g.getEndNodeNo());
//		}
//		return "";
//	}
	
//	/**
//	 * 触发分盘
//	 * @param request
//	 * @param response
//	 */
//	@RequestMapping(value = "audit")
//	public void audit(HttpServletRequest request,ModelMap map,HttpServletResponse response) {
//		logger.debug("audit");
//		JSONObject jsonObject = new JSONObject();		
//		jsonObject.put("result", 1);
//		String groupName = request.getParameter("groupName");
//		
//		try{
//			NodeInfoDo   node = groupService.getRootNodeByGroupName(groupName);
//			List<NodeInfoDo> nodeList = groupService.getGroupNodeByGroupName(groupName);
//			
//			nodeList.remove(node);
//			NodeInfoWrap n = new NodeInfoWrap(node,nodeList);
//			
//			GroupDo[] newGroup = userService.splitGroup(n);
//			if(newGroup !=null && newGroup.length>1){
//				jsonObject.put("result", 0);
//				jsonObject.put("resultMsg", buildMsg(newGroup));
//			}else{
//				jsonObject.put("result", 1);
//			}
//			
//			
//			jsonObject.put("result", 0);
//			jsonObject.put("resultMsg", "ok");
//		}catch (Exception e) {
//			jsonObject.put("result", 1);
//			e.printStackTrace();
//		}
//		ResponseUtils.renderText(response, null, jsonObject.toString());
//		return;
//	}
//	
	
}

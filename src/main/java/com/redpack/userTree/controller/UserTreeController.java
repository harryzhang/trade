package com.redpack.userTree.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.redpack.account.faced.IUserService;
import com.redpack.account.model.UserDo;
import com.redpack.constant.WebConstants;
import com.redpack.grade.model.GroupDo;
import com.redpack.group.IGroupUserService;
import com.redpack.group.dao.IGroupDao;
import com.redpack.userTree.UserTreeDo;
import com.redpack.utils.DateUtil;
import com.redpack.utils.ResponseUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 用户等级树
 * 
 * @author LiuSiHai Q Q:1052202173
 *
 *         2015-7-31 上午11:44:16
 *         com.redpack.web.view.userTree.controller.UserTreeController.java
 */
@Controller
@RequestMapping(value = "/userTree")
public class UserTreeController {

	private final Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private IGroupDao groupDao;
	
	@Autowired
	private IGroupUserService groupUserService;

	@Autowired
	IUserService userService;

	/**
	 * 访问树节点
	 * 
	 * @param model
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping("/view")
	public String index(Model model, HttpSession session,
			HttpServletRequest request) {
		return "usertree/example1";
	}

	/**
	 * 获取用户组织结构信息
	 * 
	 * @param model
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping("/tree")
	public String demo(Model model, HttpSession session,
			HttpServletRequest request) {
		String netWork = request.getParameter("netWork");
		String userId = request.getParameter("treeDataUserId");
		model.addAttribute("netWork", netWork);
		model.addAttribute("treeDataUserId", userId);
//		getUserInfo(rootDao,childDoList);
		return "usertree/tree";
	}
	
	/**
	 * 获取树结构节点
	 * 
	 * @param model
	 * @param session
	 * @param request
	 */
	@RequestMapping(value = "getTreeData", method = RequestMethod.POST)
	public void getTreeData(HttpServletRequest request,
			HttpServletResponse response,String netWork) {
		
//		String netWork = request.getParameter("netWork");
		
		JSONObject jsonObject = new JSONObject();
		UserDo userDo =null;
		String userId = request.getParameter("treeDataUserId");
		if(StringUtils.isNotBlank(userId)){
			userDo = userService.getById(Long.valueOf(userId));
		}else{
			userDo = (UserDo) request.getSession()
					.getAttribute(WebConstants.SESSION_USER);
		}

		// 找出这个人的所在的团队, 根据创建人所在的网络找到对应的接受人，以及接受的位置
		//GroupDo group = groupDao.selectGroupByUserId(userDo.getId());
		
		Map paramMap = new HashMap();
		//找到用户组所有成员
		paramMap.put("userId", userDo.getId());
		paramMap.put("order", "order");
		paramMap.put("networkGroup", netWork==null?"A":netWork);
		Map groupMap = groupUserService.getUserGroupByNetwork(paramMap);

		if( null == groupMap){
			jsonObject.put("result", 1);
			jsonObject.put("userId", userDo.getId());
			jsonObject.put("dataTree", userDo);
			
			ResponseUtils.renderText(response, "UTF-8", jsonObject.toString());
			return;
		}
		// 获取根节点
		Long rootId = (Long)groupMap.get("groupRootUser");
		UserDo rootUserDo = userService.getById(rootId);
//		List<UserDo> childDoList = userService.selectChildByParentId(rootId);
//		userService.getAllChildren(rootDao);
		rootUserDo = groupUserService.getAllChildRen(rootUserDo,(String)groupMap.get("groupName"));
		
		jsonObject.put("result", 0);
		jsonObject.put("userId", userDo.getId());
		jsonObject.put("dataTree", rootUserDo);
		
		ResponseUtils.renderText(response, "UTF-8", jsonObject.toString());
	}

	
	/**
	 * 获取ztree树结构节点
	 * 
	 * @param model
	 * @param session
	 * @param request
	 */
	@RequestMapping(value = "getMemberTree", method = RequestMethod.POST)
	public void getMemberTree(HttpServletRequest request,
			HttpServletResponse response,String netWork) {
		
		String  userIdStr = request.getParameter("id");//节点 用户ID
		Long userId = Long.valueOf(userIdStr);

		List<UserDo> childDoList = userService.selectChildByParentId(userId);
		JSONArray  childArray = new JSONArray();
		if(null != childDoList && childDoList.size()>0){
			//转换 下级成树的节点
			for(UserDo u  : childDoList){
				JSONObject jsonChild = new JSONObject();
				jsonChild.put("name", u.getName()+""+DateUtil.dateToString(u.getCreateTime())+"团队业绩:"+"");//name 拼装
				jsonChild.put("id", u.getId());
				jsonChild.put("isParent", "true");//true 才会加载下面的子节点
				childArray.add(jsonChild);
			}
		}
		
		//[{ id:'011',	name:'n1.n1',	isParent:true},{ id:'012',	name:'n1.n2',	isParent:false},{ id:'013',	name:'n1.n3',	isParent:true},{ id:'014',	name:'n1.n4',	isParent:false}]
		ResponseUtils.renderText(response, "UTF-8", childArray.toString());
	}
	

	/**
	 * 初始化树节点
	 * 
	 * @param model
	 * @param session
	 * @param request
	 */
	@RequestMapping(value = "initTree", method = RequestMethod.POST)
	public void initTree(HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject();
		// 取缓存登录信息
		String fromUrl = request.getHeader("referer");
		UserTreeDo userTreeDo = new UserTreeDo();

		userTreeDo.setId(100L);
		userTreeDo.setName("陈一");
		// userTreeDo.setData(100L);
		// List childRen = new ArrayList();
		// for(int i=1; i<4; i++){
		// UserTreeDo childTreeDo = new UserTreeDo();
		// childTreeDo.setId(100L+i);
		// childTreeDo.setName("陈五" + i);
		//
		//
		// childRen.add(childTreeDo);
		// }
		userTreeDo.setChildren(addChildren(5, 6));

		jsonObject.put("result", 0);
		jsonObject.put("userTree", userTreeDo);
		// 登陆成功
		ResponseUtils.renderText(response, "UTF-8", jsonObject.toString());
	}

	public List addChildren(int parent, int leavel) {

		List childRen = new ArrayList();
		leavel--;
		if (leavel > 0) {
			for (int i = 1; i < 4; i++) {
				// int id = parent+10 + i;
				// Random random1 = new Random(100);
				int id = (int) (Math.random() * 1000);
				UserTreeDo childTreeDo = new UserTreeDo();
				childTreeDo.setId(Long.valueOf(id));
				childTreeDo.setName("陈五" + id);
				childTreeDo.setChildren(addChildren(id, leavel));

				childRen.add(childTreeDo);
			}
		}
		return childRen;
	}

	private void getUserInfo(UserDo user, List childList) {
		if (null == user) {
			return;
		}
		if (null == user.getChildList() || user.getChildList().size() < 1) {
			return;
		}
		childList.addAll(user.getChildList());
		for (int i = 0; i < childList.size(); i++) {
			UserDo child = (UserDo) childList.get(i);
			getUserInfo(child, childList);
		}

	}

}

package com.redpack.customer.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ibm.wsdl.util.StringUtils;
import com.mchange.v1.util.StringTokenizerUtils;
import com.redpack.account.faced.IUserService;
import com.redpack.account.model.UserDo;
import com.redpack.base.controller.BaseController;
import com.redpack.constant.WebConstants;
import com.redpack.customer.ICustomerService;
import com.redpack.group.IGroupUserService;
import com.redpack.wallet.IWalletService;
import com.redpack.wallet.model.WalletDo;
import com.redpack.utils.ResponseUtils;

/**
 * 客服封号和代确认
 * 
 * @author: zhangyunhua
 * @date 2015年3月5日 上午11:01:51
 */
@Controller
@RequestMapping("/kefu")
public class CustomerController extends BaseController {

	private static final Logger logger = Logger
			.getLogger(CustomerController.class);
	@Autowired
	private IUserService userService;
	@Autowired
	private ICustomerService customerService;
	
	@Autowired
	private IWalletService walletService;
	
	@Autowired
	private IGroupUserService groupUserService;
	
	/**
	 * 跳转封号页面
	 * 
	 * @return
	 * @author: zhangyunhua
	 * @date 2015年8月2日 22:45:08
	 */
	@RequestMapping("unvalidUser")
	public String unValidUser(HttpServletRequest request,
			HttpServletResponse response, Model model,String userName) {
		logger.info("----跳转封号页面----");
		List<UserDo> userList = new ArrayList<UserDo>();
		if( null != userName && !"".equals(userName)){
			Map paramMap = new HashMap();
			//paramMap.put("status", "1");
			paramMap.put("userName", userName);
			userList = userService.getAllUser(paramMap);
		}
		model.addAttribute("userList", userList);
		return "kefu/unvalidUser";
	}
	
	
	/**
	 * 跳转封号页面
	 * 
	 * @return
	 * @author: zhangyunhua
	 * @date 2015年8月2日 22:45:08
	 */
	@RequestMapping("qitai")
	public String qitai(HttpServletRequest request,
			HttpServletResponse response, Model model,String userName) {
		logger.info("----跳转封号页面----");
		
		return "qitai/qitai";
	}
	
	
	
	/**
	 * 指定用户封号
	 * @param request
	 * @param response
	 * @param model
	 * @param userName
	 * @return
	 */
	@RequestMapping("setUnvalidUser")
	public void setUnvalidUser(HttpServletRequest request,
			HttpServletResponse response, Model model,String userId,String changeName) {
		logger.info("----给用户封号----");
		JSONObject jsonObject = new JSONObject();
		
		Long id = Long.valueOf(userId);
		customerService.setUnvalidUser(id);
		jsonObject.put("result", "封号成功"); 
		jsonObject.put("resultCode", "0"); 
		ResponseUtils.renderText(response, "UTF-8", jsonObject.toString());
		return;
	}
	
	/**
	 * 跳转封号页面
	 * 
	 * @return
	 * @author: zhangyunhua
	 * @date 2015年8月2日 22:45:08
	 */
	@RequestMapping("changeUser")
	public String changeUser(HttpServletRequest request,
			HttpServletResponse response, Model model,String userName) {
		logger.info("----跳转替换用户封号页面----");
		List<UserDo> userList = new ArrayList<UserDo>();
		if( null != userName && !"".equals(userName)){
			Map paramMap = new HashMap();
			//paramMap.put("status", "1");
			paramMap.put("userName", userName);
			userList = userService.getAllUser(paramMap);
		}
		model.addAttribute("userList", userList);
		return "kefu/changeUser";
	}
	
	
	/**
	 * 指定用户替换新人
	 * @param request
	 * @param response
	 * @param model
	 * @param userName
	 * @return
	 */
	@RequestMapping("setChangeUser")
	public void setChangeUser(HttpServletRequest request,
			HttpServletResponse response, Model model,String userId,String changeName,
			String changePhone) {
		logger.info("----替换用户封号----");
		JSONObject jsonObject = new JSONObject();
		if( null == userId || null == changePhone || 
				"".equals(userId) || "".equals(changePhone)){
			jsonObject.put("result", "用户ID或者替换用户手机号码为空"); 
			jsonObject.put("resultCode", "1"); 
			ResponseUtils.renderText(response, "UTF-8", jsonObject.toString());
			return;
		}
		Map paramMap= new HashMap();
		paramMap.put("userName", changePhone);
		UserDo changUser = userService.getByUserDo(paramMap);
		
		if( null !=changUser && null != changUser.getId() ){
			jsonObject.put("result", "替换用户已是注册用户，存在组织，请更换"); 
			jsonObject.put("resultCode", "1"); 
			ResponseUtils.renderText(response, "UTF-8", jsonObject.toString());
			return;
		}
		Long id = Long.valueOf(userId);
		
		
//		customerService.setUnvalidUser(id);
		customerService.resetUser(id,changeName,changePhone);
		
		
		jsonObject.put("result", "封号成功"); 
		jsonObject.put("resultCode", "0"); 
		ResponseUtils.renderText(response, "UTF-8", jsonObject.toString());
		return;
	}

	/**
	 * 跳转代确认收款页面
	 * 
	 * @return
	 * @author: zhangyunhua
	 * @date 2015年8月2日 22:45:08
	 */
	@RequestMapping("insteadConfirm")
	public String insteadConfirm(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		logger.info("----跳转代确认收款页面----");
		// model.addAttribute("pwdFlag", pwdFlag);
		return "kefu/insteadConfirm";
	}

	/**
	 * 代用户确认收款
	 * @param request
	 * @param response
	 * @param model
	 * @param userName
	 * @return
	 */
	@RequestMapping("doConfirm")
	public String doConfirm(HttpServletRequest request,
			HttpServletResponse response, Model model,String userId) {
		logger.info("----代用户确认收款----");
		
		String mobile = request.getParameter("userName");
		
		Map<String, Object> queryMap = new HashMap<String ,Object>();
		queryMap.put("userName", mobile);
		List<UserDo> userList = userService.selectUser(queryMap);
		if(null == userList || userList.size()<1){
			return "kefu/insteadConfirm";
		}
		
		Long id = userList.get(0).getId();		
		List<Map> walletList = walletService.selectUserSk(id,0);
		model.addAttribute("walletList", walletList);
		
		return "kefu/insteadConfirm";
	}
	
	
	
	
	/**
	 * 跳转会员位置对换
	 * 
	 * @return
	 * @author: zhangyunhua
	 * @date 2015年8月2日 22:45:08
	 */
	@RequestMapping("exchangePosition")
	public String exchangePosition(HttpServletRequest request,
			HttpServletResponse response, Model model,String userName) {
		logger.info("----跳转会员位置对换----");		
		return "kefu/exchangePosition";
	}
	
	
	/**
	 * 位置互换
	 * @param request
	 * @param response
	 * @param model
	 * @param userName
	 * @return
	 */
	@RequestMapping("doExchangePosition")
	public void doExchangePosition(HttpServletRequest request,
			HttpServletResponse response, Model model,String userId) {
		logger.info("----位置互换----");
		JSONObject jsonObject = new JSONObject();
		
		String user1Mobile= request.getParameter("user1Mobile");
		String user2Mobile= request.getParameter("user2Mobile");
		
		if(org.apache.commons.lang.StringUtils.isBlank(user1Mobile)){
			jsonObject.put("result", "相互位置的电话号码不能为空"); 
			jsonObject.put("resultCode", "1"); 
			ResponseUtils.renderText(response, "UTF-8", jsonObject.toString());
		}
		if(org.apache.commons.lang.StringUtils.isBlank(user2Mobile)){
			jsonObject.put("result", "相互位置的电话号码不能为空"); 
			jsonObject.put("resultCode", "1"); 
			ResponseUtils.renderText(response, "UTF-8", jsonObject.toString());
		}

		userService.exchangeEachOther(user1Mobile, user2Mobile);
		
		jsonObject.put("result", "位置互换成功"); 
		jsonObject.put("resultCode", "0"); 
		ResponseUtils.renderText(response, "UTF-8", jsonObject.toString());
	}
	
	
	/**
	 * 跳转客服删号
	 * 
	 * @return
	 * @author: zhangyunhua
	 * @date 2015年8月2日 22:45:08
	 */
	@RequestMapping("deleteUser")
	public String deleteUser(HttpServletRequest request,
			HttpServletResponse response, Model model,String userName) {
		logger.info("----跳转封号页面----");
		List<UserDo> userList = new ArrayList<UserDo>();
		if( null != userName && !"".equals(userName)){
			Map paramMap = new HashMap();
			//paramMap.put("status", "1");
			paramMap.put("userName", userName);
			userList = userService.getAllUser(paramMap);
		}
		model.addAttribute("userList", userList);
		return "kefu/deleteUser";
	}
	
	
	/**
	 * 删除已经封号的用户给重新注册
	 * @param request
	 * @param response
	 * @param model
	 * @param userName
	 * @return
	 */
	@RequestMapping("doDeleteUser")
	public void doDeleteUser(HttpServletRequest request,
			HttpServletResponse response, Model model,String userId) {
		logger.info("----删除已经封号的用户----");
		JSONObject jsonObject = new JSONObject();
		
		Long id = Long.valueOf(userId);

		UserDo delUser = userService.getById(id);
		if( 2 == delUser.getStatus().intValue()){
			int i = RandomUtils.nextInt(100);
			delUser.setUserName("d"+delUser.getUserName()+"-"+i);
			userService.updateUser(delUser);
		}else{
			jsonObject.put("result", "没有封号的用户不可以删号"); 
			jsonObject.put("resultCode", "1"); 
			ResponseUtils.renderText(response, "UTF-8", jsonObject.toString());
		}
		jsonObject.put("result", "删号成功"); 
		jsonObject.put("resultCode", "0"); 
		ResponseUtils.renderText(response, "UTF-8", jsonObject.toString());
	}
	
	
	
	/**
	 * 跳转客服重置密码
	 * 
	 * @return
	 * @author: zhangyunhua
	 * @date 2015年8月2日 22:45:08
	 */
	@RequestMapping("resetPwdUser")
	public String resetPwdUser(HttpServletRequest request,
			HttpServletResponse response, Model model,String userName) {
		logger.info("----跳转客服重置密码页面----");
		List<UserDo> userList = new ArrayList<UserDo>();
		if( null != userName && !"".equals(userName)){
			Map paramMap = new HashMap();
			paramMap.put("userName", userName);
			userList = userService.getAllUser(paramMap);
		}
		model.addAttribute("userList", userList);
		return "kefu/resetPwdUser";
	}
	
	
	/**
	 * 重置密码
	 * @param request
	 * @param response
	 * @param model
	 * @param userName
	 * @return
	 */
	@RequestMapping("doResetPwdUser")
	public void doResetPwdUser(HttpServletRequest request,
			HttpServletResponse response, Model model,String userId) {
		logger.info("----用户重置密码----");
		JSONObject jsonObject = new JSONObject();
		
		Long id = Long.valueOf(userId);

		UserDo restUser = userService.getById(id);
		String pwdMd5 =DigestUtils.md5Hex("123123" + WebConstants.PASS_KEY);
		restUser.setPassword(pwdMd5);
		userService.updateUser(restUser);
		
		jsonObject.put("result", "重置密码成功"); 
		jsonObject.put("resultCode", "0"); 
		ResponseUtils.renderText(response, "UTF-8", jsonObject.toString());
	}
	
 
     
     /**
 	 * 跳转客户会员查询页面
 	 * 
 	 * @return
 	 * @author: zhangyunhua
 	 * @date 2015年8月2日 22:45:08
 	 */
 	@RequestMapping("queryUser")
 	public String queryUser(HttpServletRequest request,
 			HttpServletResponse response, Model model) {
 		logger.info("----跳转客户会员查询页面----");
 		// model.addAttribute("pwdFlag", pwdFlag);
 		return "kefu/queryUser";
 	}

 	/**
 	 * 客服查询会员
 	 * @param request
 	 * @param response
 	 * @param model
 	 * @param userName
 	 * @return
 	 */
 	@RequestMapping("doQueryUser")
 	public String doQueryUser(HttpServletRequest request,
 			HttpServletResponse response, Model model,String userId) {
 		logger.info("-----客服查询会员----");
 		
 		String mobile = request.getParameter("userName");
 		
 		Map<String, Object> queryMap = new HashMap<String ,Object>();
 		queryMap.put("userName", mobile);
 		List<UserDo> userList = userService.selectUser(queryMap);
 		if(null == userList || userList.size()<1){
 			return "kefu/queryUser";
 		}
 		
 		UserDo queryUser = userList.get(0);
 		UserDo refUser = userService.getById(queryUser.getReferrerId());
 		queryUser.setReferrerDo(refUser);
 		int refCnt = userService.getRefUserCount(queryUser.getId());
 		queryUser.setRefCount(refCnt);
 	
 		model.addAttribute("userDo",queryUser); 		
 		
 		
 		return "kefu/queryUser";
 	}
 	
 	
 	 /**
 	 * 跳转团队查询页面
 	 * 
 	 * @return
 	 * @author: zhangyunhua
 	 * @date 2015年8月2日 22:45:08
 	 */
 	@RequestMapping("listGroup")
 	public String listGroup(HttpServletRequest request,
 			HttpServletResponse response, Model model) {
 		logger.info("----跳转团队查询页面----");
 		// model.addAttribute("pwdFlag", pwdFlag);
 		return "kefu/listGroup";
 	}

 	/**
 	 * 查询团队
 	 * @param request
 	 * @param response
 	 * @param model
 	 * @param userName
 	 * @return
 	 */
 	@RequestMapping("doListGroup")
 	public String doListGroup(HttpServletRequest request,
 			HttpServletResponse response, Model model,String userId) {
 		logger.info("-----查询团队----");
 		
 		String mobile = request.getParameter("userName");
 		
 		Map<String, Object> queryMap = new HashMap<String ,Object>();
 		queryMap.put("userName", mobile);
 		List<Map<String,Object>> groupList = groupUserService.listGroup(queryMap);
 				
 		
 		
 		model.addAttribute("groupList", groupList);
 		
 		return "kefu/listGroup";
 	}
 	
 	
 	 /**
 	 * 跳转查询推荐排行页面
 	 * 
 	 * @return
 	 * @author: zhangyunhua
 	 * @date 2015年8月2日 22:45:08
 	 */
 	@RequestMapping("listRef")
 	public String listRef(HttpServletRequest request,
 			HttpServletResponse response, Model model) {
 		logger.info("----跳转查询推荐排行页面----");
 		// model.addAttribute("pwdFlag", pwdFlag);
 		return "kefu/listRef";
 	}

 	/**
 	 * 查询推荐排行
 	 * @param request
 	 * @param response
 	 * @param model
 	 * @param userName
 	 * @return
 	 */
 	@RequestMapping("doListRef")
 	public String doListRef(HttpServletRequest request,
 			HttpServletResponse response, Model model,String userId) {
 		logger.info("-----查询推荐排行----");
 		
 		String mobile = request.getParameter("userName");
 		
 		Map<String, Object> queryMap = new HashMap<String ,Object>();
 		queryMap.put("userName", mobile);
// 		List<Map<String,Object>> groupList = userService.listRef(queryMap);
// 				
// 		
// 		
// 		model.addAttribute("groupList", groupList);
 		
 		return "kefu/listRef";
 	}
     
	/**
	 * 客服中心
	 * @param pwdFlag
	 * @param model
	 * @return
	 */
	@RequestMapping("serverCenter")
	public String serverCenter(String pwdFlag,Model model) {
		logger.info("----客户中心跳转页面----");
		return "kefu/serverCenter";
	}
	
}

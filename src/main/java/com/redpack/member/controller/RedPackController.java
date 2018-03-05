package com.redpack.member.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.redpack.account.faced.IUserService;
import com.redpack.account.model.UserDo;
import com.redpack.constant.WebConstants;
import com.redpack.grade.IGradeFeeService;
import com.redpack.group.IGroupUserService;
import com.redpack.sms.ISysSmsService;
import com.redpack.sms.model.SysSmsDo;
import com.redpack.upgrade.IUserUpgradeService;
import com.redpack.userWaiting.IUserWaitingService;
import com.redpack.userWaiting.model.UserWaitingDo;
import com.redpack.wallet.IWalletService;

/**
 * @Description 红包控制器
 * @author huangzl QQ: 272950754
 * @date 2015-7-26 下午06:18:37
 * @Project redpack-web
 * @Package com.redpack.view.controllor
 * @File RedPackController.java
 */
@Controller
@RequestMapping(value = "/redPack")
public class RedPackController {

	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	IGradeFeeService gradeFeeService;
	
	
	@Autowired
	IUserService  userService;
	@Autowired
	IGroupUserService  groupUserService;
	
	@Autowired
	IUserUpgradeService userUpgradeService;
	
	@Autowired
	IWalletService walletService;
	
	@Autowired
	private IUserWaitingService userWaitService;
	
	@Autowired
	ISysSmsService sysSmsService;

	/**
	 * @author huangzl QQ:272950754
	 * @version 创建时间：2015-7-26 下午06:22:07
	 * @param model
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping("/index")
	public String index(Model model, HttpSession session, HttpServletRequest request) {
		return "redPack/index";
	}
	
	

	/**
	 * 规则
	 * 
	 * @author huangzl QQ:272950754
	 * @version 创建时间：2015-7-26 下午09:05:57
	 * @param model
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping("/rule")
	public String rule(Model model, HttpSession session, HttpServletRequest request) {
		return "redPack/rule";
	}
	
	/**
	 * 公告
	 * 
	 * @author huangzl QQ:272950754
	 * @version 创建时间：2015-7-26 下午09:05:57
	 * @param model
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping("/notify")
	public String notify(Model model, HttpSession session, HttpServletRequest request) {
		return "redPack/notify";
	}
	/**
	 * 个人中心
	 * @author huangzl QQ: 272950754
	 * @date 2015年8月3日 下午4:36:03
	 * @param model
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping("/personalCenter")
	public String personalCenter(Model model, HttpSession session, HttpServletRequest request) {
		
		UserDo user = (UserDo)session.getAttribute(WebConstants.SESSION_USER);
		String organ = "";
		if(null != user){
			model.addAttribute(WebConstants.SESSION_USER,userService.getById(user.getId()));
			organ = user.getOrgan();
		}
		
		/*
		Map<String, Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("groupName", "MANAGER_GROUP");
		parameterMap.put("userId", user.getId());
		List<GroupUserDo> userGroupList = groupUserService.selectGroupUser(parameterMap );
		if(userGroupList !=null && userGroupList.size()>0){
			model.addAttribute("isManager","T");
		}else{
			model.addAttribute("isManager","F");
		}
		*/
		
		
		//查询待处理的打款和收款记录
//		List<Map>  fkList = walletService.selectUserFk(user.getId(),0);
//		if(null != fkList & fkList.size()>0){
//			model.addAttribute("fkRecord",fkList.size());
//		}
//		List<Map> skList = walletService.selectUserSk(user.getId(),0);
//		if(null != skList & skList.size()>0){
//			model.addAttribute("skRecord",skList.size());
//		}
		
		//查询是否进入等待排序中
//		UserWaitingDo userWait = userWaitService.getByUserId(user.getId());
//		
//		if(userWait != null){
//			model.addAttribute("waiting",1);
//		}
		
		//查询升级消息
//		Map<String,Object> parameterMap = new HashMap<String,Object>();
//		parameterMap.put("msgType", 1);
//		parameterMap.put("userId", user.getId());
//		List<SysSmsDo> smsList = sysSmsService.selectSysSms(parameterMap);
//		
//		if(null != smsList && smsList.size()>0){
//			model.addAttribute("upgradeB",1);
//		}
//		
//		initGroupInfo(model,request);
		//initGroupAmount(model,request);
		
		String userLocal = user.getUserLocal();
		if("en".equals(userLocal)){
			if( "2".equals(organ)){
				return "page_en/redPack/redPack_admin_center";
			}else if("1".equals(organ)){
				return "page_en/redPack/redPack_kefu_center";
			}else{
				return "page_en/redPack/redPack_personal_center";
			}
		}else{
			if( "2".equals(organ)){
				return "redPack/redPack_admin_center";
			}else if("1".equals(organ)){
				return "redPack/redPack_kefu_center";
			}else{
				return "redPack/redPack_personal_center";
			}
		}
	}
	
	/**
	 * 统计每层人数
	 * @param model
	 * @param request
	 */
	private void initGroupInfo(Model model,HttpServletRequest request){
		List<String> groupList = new ArrayList<String>(15);
		UserDo user = (UserDo)request.getSession().getAttribute(WebConstants.SESSION_USER);
		userService.getAllChildren(user);
		List<UserDo> childList = user.getChildList();
		
		
		
		//第一层
		int levelCount = 0;
		levelCount = levelCount + ( childList == null ? 0 : childList.size());
	    groupList.add(levelCount+"/3");
		
	    int allChildCount = levelCount;
	    
		
		//第一层
		for(int level = 2 ; level < 16 ; level ++){
			levelCount = 0;
			int levelPersons = (int) Math.pow(3,level);
			List<UserDo> nextList = new ArrayList<UserDo>();
			for(UserDo userDo : childList){
				nextList.addAll(userDo.getChildList());
				levelCount = levelCount + ( userDo.getChildList() == null ? 0 : userDo.getChildList().size());
			}
			childList = nextList;
			allChildCount = allChildCount + levelCount;
		    groupList.add(levelCount+"/"+levelPersons);
		}
		model.addAttribute("groupList", groupList);
		model.addAttribute("groupCount", allChildCount);
	}
	
	/**
	 * 统计每层金额
	 * @param model
	 * @param request
	 */
	private void initGroupAmount(Model model,HttpServletRequest request){
		List<Map<String,String>> groupList = new ArrayList<Map<String,String>>(15);
		UserDo user = (UserDo)request.getSession().getAttribute(WebConstants.SESSION_USER);
		
		int levelCount = 0;
		//第一层
		for(int level = 1 ; level < 16 ; level ++){
			levelCount = 0;
			int levelPersons = (int) Math.pow(3,level);
			Map<String,Object> groupLevelMoneyMap = userUpgradeService.selectLevelAmount(user.getId(),level);
			Long countPersons = 0L;
			BigDecimal amount = BigDecimal.ZERO;
			if(groupLevelMoneyMap !=null){
				countPersons  = groupLevelMoneyMap.get("countPersons")==null? 0L: (Long)groupLevelMoneyMap.get("countPersons");
				amount = groupLevelMoneyMap.get("amount") == null? BigDecimal.ZERO : (BigDecimal)groupLevelMoneyMap.get("amount");
			}
			Map<String,String> row = new HashMap<String,String>();
			row.put("persons", countPersons+"/"+levelPersons);
			row.put("amount", amount.toString());
			groupList.add(row);
		}
		model.addAttribute("groupMoneyList", groupList);
	}
	

}


package com.redpack.level.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.redpack.account.faced.IUserInfoService;
import com.redpack.account.faced.IUserService;
import com.redpack.account.model.UserDo;
import com.redpack.account.model.UserInfoDo;
import com.redpack.base.controller.BaseController;
import com.redpack.base.exception.BusinessException;
import com.redpack.base.result.IResult;
import com.redpack.constant.WebConstants;
import com.redpack.grade.IGradeFeeService;
import com.redpack.grade.model.GradeFeeDo;
import com.redpack.grade.model.GroupUserDo;
import com.redpack.group.IGroupService;
import com.redpack.group.IGroupUserService;
import com.redpack.sms.ISysSmsService;
import com.redpack.sms.model.SysSmsDo;
import com.redpack.upgrade.IUserUpgradeService;
import com.redpack.upgrade.model.UserUpgradeDo;
import com.redpack.utils.ResponseUtils;


/**
 * 等级管理
 * @author:  zhangyunhua
 * @date 2015年3月5日 上午11:01:51
 */
@Controller
@RequestMapping("/upgrade")
public class LevelController  extends BaseController {
	
	private static transient Logger logger = Logger.getLogger(LevelController.class);
	@Autowired
	IUserUpgradeService userUpgradeService;
	
	@Autowired
	IGroupService groupService;
	
	@Autowired
	IUserService  userService;
	
	@Autowired
	IUserInfoService  userInfoService;
	
	@Autowired
	IGradeFeeService gradeFeeService;
	
	@Autowired
	IGroupUserService groupUserService;
	
	@Autowired
	ISysSmsService sysSmsService;
	
	
	/**
	 * 去升级申请页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/toApply")
    public String toApply(HttpServletRequest request,ModelMap map,HttpServletResponse response) {
		logger.debug("toApply");
		
		UserDo currentUser = (UserDo)  request.getSession().getAttribute(WebConstants.SESSION_USER);
		long  userId = currentUser.getId();
		
		//查询升级消息
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("msgType", 1);
		parameterMap.put("userId", userId);
		List<SysSmsDo> smsList = sysSmsService.selectSysSms(parameterMap);
		
		if(null != smsList && smsList.size()>0){
			map.put("msg", smsList.get(0));
		}

		return "upgrade/toApply";
    }
	
	
	/**
	 * 升级申请信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/apply")
    public void apply(HttpServletRequest request,ModelMap map,HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject();
		
		//消息ID
		String msgIdStr = request.getParameter("msgId");
		if(StringUtils.isBlank(msgIdStr)){
			jsonObject.put("result", 1);
			jsonObject.put("resultMsg", "你还不能升级，升级消息ID无效");
			ResponseUtils.renderText(response, null, jsonObject.toString());
			return;
		}
		
		long msgId = 0 ;
		try{
			msgId = Long.valueOf(msgIdStr);
		}catch(Exception e){
			jsonObject.put("result", 1);
			jsonObject.put("resultMsg", "你还不能升级，升级消息ID无效");
			ResponseUtils.renderText(response, null, jsonObject.toString());
			return;
		}
		
		//B网推荐人的手机号
		String refMobile = request.getParameter("fxPhone");
		if(StringUtils.isBlank(msgIdStr)){
			jsonObject.put("result", 1);
			jsonObject.put("resultMsg", "B网升级推荐人手机号无效");
			ResponseUtils.renderText(response, null, jsonObject.toString());
			return;
		}
		
		//检查该用户是否在B网已经存在，如果存在不需要升级到B网
		UserDo currentUser = (UserDo)  request.getSession().getAttribute(WebConstants.SESSION_USER);
		long  userId = currentUser.getId();
		Map resMap= userUpgradeService.checkBNetWorkParent(currentUser.getUserName());
		if(resMap != null && resMap.size()>0){
			jsonObject.put("result", 1);
			jsonObject.put("resultMsg", "您已经进入B网");
			ResponseUtils.renderText(response, null, jsonObject.toString());
			return;
		}
				
		//检查手机号在B网是否存在，并有效
		resMap= userUpgradeService.checkBNetWorkParent(refMobile);
		if(resMap == null || resMap.size()<1){
			jsonObject.put("result", 1);
			jsonObject.put("resultMsg", "B网升级推荐人手机号无效");
			ResponseUtils.renderText(response, null, jsonObject.toString());
			return;
		}
		
		//进B网
		userUpgradeService.applyUpgradeBNetWork(userId,(Long)resMap.get("id"),(String)resMap.get("groupName"));
		
		//最后更新消息状态
		SysSmsDo  newSysSmsDo = new SysSmsDo();
		newSysSmsDo.setStatus(0);
		newSysSmsDo.setId(msgId);
		sysSmsService.updateSysSmsById(newSysSmsDo);
		
		
		jsonObject.put("result", 0);
		ResponseUtils.renderText(response, null, jsonObject.toString());
    }
	

	
	/**
	 * 申请列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/listApply")
    public String listApply(HttpServletRequest request,ModelMap map,HttpServletResponse response) {
		logger.debug("listApply");
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("userid", this.getUserId(request));
		List<UserUpgradeDo> upgradeList = userUpgradeService.selectUserUpgrade(parameterMap);
		map.put("upgradeList", upgradeList);
        return "upgrade/listMyApply";
    }
	
	/**
	 * 审批列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/listAudit")
    public String listAudit(HttpServletRequest request,ModelMap map,HttpServletResponse response) {
		logger.debug("listAudit");
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		//parameterMap.put("receiveUser", this.getUserId(request));
		parameterMap.put("status", 0);
		List<UserUpgradeDo> upgradeList = userUpgradeService.selectUpgradeAuditList(parameterMap);
		
		map.put("upgradeList", upgradeList);
        return "upgrade/listAudit";
    }
	
	/**
	 * 申请审批
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "audit")
	public void audit(HttpServletRequest request,ModelMap map,HttpServletResponse response) {
		logger.debug("audit");
		JSONObject jsonObject = new JSONObject();		
		jsonObject.put("result", 1);
		String upgradeId = request.getParameter("id");
		String optType = request.getParameter("optType");
		String newStatus = "0";
		if("audit".equals(optType)){
			newStatus = "1";
		}
		if("refused".equals(optType)){
			newStatus = "2";
			userUpgradeService.updateUpgradeStatusById(upgradeId,newStatus);
		}
		
		try{
			
			if("audit".equals(optType)){
				UserUpgradeDo upgradedo = userUpgradeService.getById(Long.valueOf(upgradeId));
				if(upgradedo !=null){
					IResult result = groupService.applyGroupGrade(upgradedo.getUserId(),null);
					if(!result.isSuccess()){
						jsonObject.put("result", 0);
					}else{
						userUpgradeService.updateUpgradeStatusById(upgradeId,newStatus);
					}
				}
			}
			
		}catch (Exception e) {
			jsonObject.put("result", 0);
			e.printStackTrace();
		}
		ResponseUtils.renderText(response, null, jsonObject.toString());
		return;
	}
	
	/**
	 * 撤回
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "tookBack")
	public void tookBack(HttpServletRequest request,HttpServletResponse response) {
		logger.debug("tookBack");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", 1);
		String upgradeId = request.getParameter("id");
		String newStatus = "3";
		try{
			userUpgradeService.updateUpgradeStatusById(upgradeId,newStatus);
		}catch (Exception e) {
			jsonObject.put("result", 0);
			e.printStackTrace();
		}
		ResponseUtils.renderText(response, null, jsonObject.toString());
		return;
	}
	
	/**
	 * 查看下级联系方式
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "viewLowerUserInfo")
	public String viewLowerUserInfo(HttpServletRequest request,HttpServletResponse response) {
		
		String netWork = request.getParameter("netWork");
		
		//UserDo currentUser = userService.getById(this.getUserId(request));
		//userService.getAllChildren(currentUser);
		
		//找到用户组所有成员
		Map<String,Object> paramMap = new HashMap();
		paramMap.put("userId", this.getUserId(request));
		paramMap.put("order", "order");
		paramMap.put("networkGroup", netWork);
		Map groupMap = groupUserService.getUserGroupByNetwork(paramMap);
		
		if( null != groupMap){
			String groupName = (String)groupMap.get("groupName");
			paramMap.clear();
			
			paramMap.put("groupName", groupName);
			List<UserDo> childList = userService.getAllGroupUser(paramMap);
			request.setAttribute("childList", childList);
		
		}
		
		
		
		return "redPack/listGroupMember";
	}
	
	/**
	 * 从上下级对象转成List
	 * @param user
	 * @param childList
	 */
	private void getUserInfo(UserDo user, List<UserDo> childList){
		if(null == user){
			return;
		}
		List<UserDo> newChildrenList = user.getChildList();
		if(null == newChildrenList || newChildrenList.size()<1 ){
			return;
		}
		childList.addAll(newChildrenList);
		for(int i =0; i <newChildrenList.size(); i++){
			UserDo child =(UserDo)newChildrenList.get(i);
			getUserInfo(child,childList);
		}
		
	}
	
	/**
	 * 查看临时会员
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "viewTempUserInfo")
	public String viewTempUserInfo(HttpServletRequest request,HttpServletResponse response) {
//		UserDo currentUser = userService.getById(this.getUserId(request));
//		userService.getAllChildren(currentUser);
//		
//		List<UserDo> childList = new ArrayList<UserDo>();
//		getUserInfo(currentUser, childList);
//		
//		
//		List<UserDo> unTempMemberList = new ArrayList<UserDo>();
//		for(UserDo u : childList){
//			if(u.getGrade().intValue() != 0 ){
//				unTempMemberList.add(u);
//			}
//		}
//		childList.removeAll(unTempMemberList);
//		for(UserDo u : childList){
//			UserInfoDo userInfo = userInfoService.getByUserId(u.getId());
//			u.setUserInfoDo(userInfo);
//		}
		
		Map<String, Object> queryMap = new HashMap<String,Object>();
		queryMap.put("grade", 0);
		List<UserDo>  tempUserList = userService.selectUser(queryMap );
		for(UserDo u : tempUserList){
			UserInfoDo userInfo = userInfoService.getByUserId(u.getId());
			u.setUserInfoDo(userInfo);
		}
		request.setAttribute("childList", tempUserList);
		return "redPack/listTempGroupMember";
	}
	
	/**
	 * 审批临时会员
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "auditTempUser")
	public void auditTempUser(HttpServletRequest request,HttpServletResponse response) {
		String userIdStr = request.getParameter("userId");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", 1);
		if(StringUtils.isNotBlank(userIdStr)){
			try{
				//userService.updateUserGradeById(Long.valueOf(userIdStr), 1);
//				userService.autidTempUser(Long.valueOf(userIdStr),this.getUserId(request));
				ResponseUtils.renderText(response, null, jsonObject.toString());
				return;
			}catch(Exception e){
				e.printStackTrace();
				jsonObject.put("result", 0);
				ResponseUtils.renderText(response, null, jsonObject.toString());
				return;
			}
		}
		
		jsonObject.put("result", 0);
		ResponseUtils.renderText(response, null, jsonObject.toString());
		
	}
	
	/**
	 * 查看我推荐的用户
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "viewMyRefUserInfo")
	public String viewMyRefUserInfo(HttpServletRequest request,HttpServletResponse response) {
		Map<String,Object> queryMap = new HashMap<String,Object>();
		queryMap.put("referrerId", this.getUserId(request));
		List<UserDo> refUserList = userService.selectUser(queryMap);
		for(UserDo u : refUserList){
			UserInfoDo userInfo = userInfoService.getByUserId(u.getId());
			u.setUserInfoDo(userInfo);
		}
		request.setAttribute("refUserList", refUserList);
		return "redPack/listMyRefUserInfo";
		
	}
	
	/**
	 * 去设置会费
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "setJoinGroupMoney")
	public String setJoinGroupMoney(HttpServletRequest request,HttpServletResponse response) {
		Map<String,Object> queryMap = new HashMap<String,Object>();
		return "redPack/setJoinGroupMoney";
		
	}
	/**
	 * 设置会费
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "saveJoinGroupMoney")
	public void saveJoinGroupMoney(HttpServletRequest request,HttpServletResponse response) {
		String groupMoney = request.getParameter("groupMoney");
		BigDecimal money = new BigDecimal(groupMoney);
		JSONObject jsonObject = new JSONObject();
		GradeFeeDo newGradeFeeDo = new GradeFeeDo();
		newGradeFeeDo.setAfterUpgrade(1);
		newGradeFeeDo.setBeforeUpgrade(0);
		newGradeFeeDo.setGradeAmount(money);
		int i = gradeFeeService.updateGradeFeeByGrade(newGradeFeeDo );
		if(i>0){
			jsonObject.put("result","设置会费成功");
		}else{
			jsonObject.put("result","设置会费失败");
		}
		ResponseUtils.renderText(response, null, jsonObject.toString());
		
	}
	
	
	
	/**
	 * 去设置管理员
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "setManagerMember")
	public String setManagerMember(HttpServletRequest request,HttpServletResponse response) {
		Map<String,Object> queryMap = new HashMap<String,Object>();
		return "redPack/setManagerMember";
		
	}
	/**
	 * 设置管理员
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "saveManagerMember")
	public void saveManagerMember(HttpServletRequest request,HttpServletResponse response) {
		String mobile = request.getParameter("mobilePhone");
		String pwd = request.getParameter("pwd");
		String pwdMd5 = DigestUtils.md5Hex(pwd + WebConstants.PASS_KEY);
		JSONObject jsonObject = new JSONObject();
//		int i = userService.addManager(mobile,pwdMd5);
//		if(i>0){
//			jsonObject.put("result","设置管理员成功");
//		}else{
//			jsonObject.put("result", "设置管理员失败");
//		}
		ResponseUtils.renderText(response, null, jsonObject.toString());
		
	}
	
	

	/**
	 * 去设置管理员
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "createGroup")
	public String createGroup(HttpServletRequest request,HttpServletResponse response) {
		Map<String,Object> queryMap = new HashMap<String,Object>();
		return "redPack/createGroup";
		
	}
	/**
	 * 设置管理员
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "saveNewGroup")
	public void saveNewGroup(HttpServletRequest request,HttpServletResponse response) {
		String mobile = request.getParameter("mobilePhone");
		String pwd = request.getParameter("pwd");
		String pwdMd5 = DigestUtils.md5Hex(pwd + WebConstants.PASS_KEY);
		JSONObject jsonObject = new JSONObject();
		try{
//			int i = userService.createGroup(mobile,pwdMd5);
//		
//			if(i>0){
//				jsonObject.put("result","创建团队成功");
//			}else{
//				jsonObject.put("result", "创建团队失败");
//			}
		}catch(BusinessException e){
			jsonObject.put("result", e.getMessage());
		}
		ResponseUtils.renderText(response, null, jsonObject.toString());
		
	}
	
}

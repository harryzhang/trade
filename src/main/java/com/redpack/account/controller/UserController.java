
/**  
 * @Project: deposit-web
 * @Package com.hehenian.deposit.web.view.account.controller
 * @Title: UserController.java
 * @Description: TODO
 * @author:  zhangyunhua
 * @date 2015年3月5日 上午11:01:51
 * @Copyright: HEHENIAN Co.,Ltd. All rights reserved.
 * @version V1.0  
 */
package com.redpack.account.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.redpack.account.faced.IUserInfoService;
import com.redpack.account.faced.IUserService;
import com.redpack.account.model.ApplyAgentDo;
import com.redpack.account.model.UserDo;
import com.redpack.account.model.UserInfoDo;
import com.redpack.base.controller.BaseController;
import com.redpack.base.result.IResult;
import com.redpack.constant.WebConstants;
import com.redpack.param.IParamService;
import com.redpack.sms.ISmsService;
import com.redpack.utils.ResponseUtils;

/**
 * 
 * @author: zhangyunhua
 * @date 2015年3月5日 上午11:01:51
 */
@Controller
@RequestMapping("/account")
public class UserController extends BaseController{
	private static final Logger logger = Logger.getLogger(UserController.class);
	@Autowired
	private IUserService userService;
	@Autowired
	private IUserInfoService userInfoService;
	@Autowired
	private ISmsService smsService;

	@Autowired	
    private IParamService  paramService;


	/**
	 * 重置密码跳转页面
	 * 
	 * @return
	 * @author: huangzl
	 * @date 2015年8月2日 22:45:08
	 */
	@RequestMapping("resetPwdIndex")
	public String resetPwdIndex(String pwdFlag,Model model,HttpServletRequest request) {
		logger.info("----重置密码跳转页面----");
		model.addAttribute("pwdFlag", pwdFlag);
		
		String pwdType = request.getParameter("pwdType");
		if(StringUtils.isNotBlank(pwdType)){
			model.addAttribute("pwdType", pwdType);
		}
		model.addAttribute("pwdFlag", pwdFlag);
		return "login/restPassword";
	}
	
	
	/**
	 * 用户信息修改查看
	 * 
	 */
	@RequestMapping("updateUserInfoView")
	public String updateUserInfoView(String pwdFlag,Model model) {
		logger.info("----更新用户信息跳转页面----");
		return "redPack/updateUserInfo";
	}
	
	/**
	 * 修改密码
	 * @param pwdFlag
	 * @param model
	 * @return
	 */
	@RequestMapping("confirmPassword")
	public void confirmPassword(String password,String newPassword,Model model, 
			HttpSession session,HttpServletResponse response) {
		logger.info("----修改密码----");
		UserDo user = (UserDo) session.getAttribute(WebConstants.SESSION_USER);
		user=userService.getById(user.getId());
		session.setAttribute(WebConstants.SESSION_USER, user);
		JSONObject jsonObject = new JSONObject();
		
		if(user == null){
			jsonObject.put("result", 5); //账号有误或者会话信息超时
			ResponseUtils.renderText(response, "UTF-8", jsonObject.toString());
			return;
		}
		String oldPass = DigestUtils.md5Hex(password + WebConstants.PASS_KEY);
		String newPass =DigestUtils.md5Hex(newPassword + WebConstants.PASS_KEY);
		if(oldPass != user.getPassword()){
			jsonObject.put("result", 6); //账号密码有误
			ResponseUtils.renderText(response, "UTF-8", jsonObject.toString());
			return;
		}
		
		int result = 0;
		UserDo tempSave =new UserDo();
		
		tempSave.setId(user.getId());
		tempSave.setPassword(newPass);
		result = userService.updateUser(tempSave);//修改登录密码
		ResponseUtils.renderText(response, "UTF-8", jsonObject.toString());
	}
	
	/**
	 * 重置密码跳转页面
	 * 
	 * @return
	 * @author: huangzl
	 * @date 2015年8月2日 22:45:08
	 */
	@RequestMapping("modifyPassword")
	public String account(String pwdFlag,Model model) {
		logger.info("----修改密码跳转页面----");
		model.addAttribute("pwdFlag", pwdFlag);
		return "login/modifyPwd";
	}

	/**
	 * 支付密码修改成功
	 */
    @RequestMapping(value = "resetPaySucc")
    public String resetPaySucc(){
    	return "login/resetSucc";
    }
    /**
     * @Description 密码重置
     * @author huangzl QQ: 272950754
     * @date 2015-8-7 下午09:41:54
     * @Project redpack-web
     * @Package com.redpack.web.view.account.controller
     * @File UserController.java
     * @param request
     * @param response
     * @param session
    */
    @RequestMapping(value = "updateLoginPwd")
    public void updateLoginPwd(HttpServletRequest request,HttpServletResponse response, HttpSession session){
    	logger.info("----密码重置----");
     	String pwdFlag = request.getParameter("pwdFlag");
    	JSONObject jsonObject = new JSONObject();
    	String password = request.getParameter("pwd");
		String confirmPass = request.getParameter("confirmPwd");
		if(StringUtils.isBlank(password)){
			 jsonObject.put("result", 1);
			 ResponseUtils.renderText(response, "UTF-8", jsonObject.toString());
			 return;
		}
		 if(password.length()<6 || password.length()>20){
			 jsonObject.put("result", 1);
			 ResponseUtils.renderText(response, "UTF-8", jsonObject.toString());
			 return;
		 }
		 if(!password.endsWith(confirmPass)){
			 jsonObject.put("result", 2);
			 ResponseUtils.renderText(response, "UTF-8", jsonObject.toString());
			 return;
		}
		//userId
		UserDo user = (UserDo) session.getAttribute(WebConstants.SESSION_USER);
		user=userService.getById(user.getId());
		session.setAttribute(WebConstants.SESSION_USER, user);
		try{
			if(user == null){
				jsonObject.put("result", 5); //账号有误或者会话信息超时
				ResponseUtils.renderText(response, "UTF-8", jsonObject.toString());
				return;
			}
			String newPass =DigestUtils.md5Hex(confirmPass + WebConstants.PASS_KEY);
			int result = 0;
			UserDo tempSave =new UserDo();
			if(pwdFlag.equals("pay")){
				String userPwd = user.getTwoLevelPwd();//新密码不能与原密码相同
				if(newPass.equals(userPwd)){
					 jsonObject.put("result", 3);
					 ResponseUtils.renderText(response, "UTF-8", jsonObject.toString());
					 return;
				}
				tempSave.setId(user.getId());
				tempSave.setTwoLevelPwd(newPass);
				result = userService.updateUser(tempSave);//修改支付密码
			}else{
				String userPwd = user.getPassword();
				if(newPass.equals(userPwd)){
					 jsonObject.put("result", 3);
					 ResponseUtils.renderText(response, "UTF-8", jsonObject.toString());
					 return;
				}
				tempSave.setId(user.getId());
				tempSave.setPassword(newPass);
				result = userService.updateUser(tempSave);//修改登录密码
			}
			if(result > 0){
				//添加日志
				logger.info("----t_user"+ user.getUserName()+ "修改会员密码----");
//				operationLogService.addOperationLog("t_user", aud.getUsername(), IConstants.UPDATE, aud.getLastIP(), 0d, "修改会员密码", 1);
				jsonObject.put("result",result > 0?0:4);
			}
		}catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		ResponseUtils.renderText(response, "UTF-8", jsonObject.toString());
    }
    
    

    /**
     * @Description 密码重置
     * @author huangzl QQ: 272950754
     * @date 2015-8-7 下午09:41:54
     * @Project redpack-web
     * @Package com.redpack.web.view.account.controller
     * @File UserController.java
     * @param request
     * @param response
     * @param session
    */
    @RequestMapping(value = "restLoginPwd")
    public void restLoginPwd(HttpServletRequest request,HttpServletResponse response, HttpSession session){
    	logger.info("----密码重置----");
     	String pwdFlag = request.getParameter("pwdFlag");
     	String pwdType = request.getParameter("pwdType");
    	JSONObject jsonObject = new JSONObject();
    	String password = request.getParameter("password");
		String confirmPass = request.getParameter("confirmPwd");
		//验证手机码
		String mobileCode =  request.getParameter("mobile");
		String sendCode =  request.getParameter("mobileCode");
		Object vc =session.getAttribute(mobileCode);
		String verifyCode=null;
		if(vc !=null){
			verifyCode= String.valueOf(vc);
		}
		//验证手机号码  测试时注释此地方
		
		if(!sendCode.equals(verifyCode) ){
			jsonObject.put("result", "手机验证码有误");
			ResponseUtils.renderText(response, "UTF-8", JSONObject.fromObject(jsonObject).toString());
			return;
		}	
			
		if(StringUtils.isBlank(password)){
			 jsonObject.put("result", 1);
			 ResponseUtils.renderText(response, "UTF-8", jsonObject.toString());
			 return;
		}
		 if(password.length()<6 || password.length()>20){
			 jsonObject.put("result", 1);
			 ResponseUtils.renderText(response, "UTF-8", jsonObject.toString());
			 return;
		 }

		 UserDo user = (UserDo) session.getAttribute(WebConstants.SESSION_USER);
	
		
		//如果是手机号码，根据手机号码获取用户信息
		if(user == null){
			Map parameterMap = new HashMap();
			parameterMap.put("userName", mobileCode);
			user = userService.getByUserDo(parameterMap);
		}
		user=userService.getById(user.getId());
		session.setAttribute(WebConstants.SESSION_USER, user);
		
		if(user == null){
			jsonObject.put("result", 5); //账号有误或者会话信息超时
			ResponseUtils.renderText(response, "UTF-8", jsonObject.toString());
			return;
		}
		
		try{
			
			String newPass =DigestUtils.md5Hex(password + WebConstants.PASS_KEY);
			int result = 0;
			UserDo tmpUser =new UserDo();
			tmpUser.setId(user.getId());
			if("twoPwd".equalsIgnoreCase(pwdType)){
				tmpUser.setTwoLevelPwd(newPass);
			}else{
				tmpUser.setPassword(newPass);
			}
			result = userService.updateUser(tmpUser);//修改登录密码
			if(result > 0){
				//添加日志
				logger.info("----t_user"+ user.getUserName()+ "修改会员密码----");
				jsonObject.put("result",result > 0?0:4);
			}
		}catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		ResponseUtils.renderText(response, "UTF-8", jsonObject.toString());
    }
 
    /**
     * 更新用户信息
     * @param request
     * @param response
     * @param session
     */
    @RequestMapping(value = "updateUserInfo")
    public void updateUserInfo(HttpServletRequest request,HttpServletResponse response, HttpSession session){
		String zhifubao =  request.getParameter("zhifubao");
		String weixin =  request.getParameter("weixin");
		JSONObject jsonObject = new JSONObject();
		
		UserDo user = (UserDo) session.getAttribute(WebConstants.SESSION_USER);
		
		UserDo userDo =new UserDo();
		userDo.setId(user.getId());                 //当前用户是记录创建者
		userDo.setZhifubao(zhifubao);
		userDo.setTwoLevelPwd(weixin);
		
		int i = userService.updateUser(userDo);
		
		//userDo.setParentId(Long.valueOf(recieveUserId));					//接点人ID
		//开始保存
//		IResult<Long> result = userService.saveUser(userDo);
		if ( i >0 ) {
			jsonObject.put("result", "用户信息更新成功");
			ResponseUtils.renderText(response, null, JSONObject.fromObject(jsonObject).toString());
		} else {
			jsonObject.put("result", "用户信息更新失败:");
			ResponseUtils.renderText(response,
			null,JSONObject.fromObject(jsonObject).toString());
		}
    }
    
    /**
	 * 注册用户跳转页面
	 * 
	 * @return
	 * @author: huangzl
	 * @date 2015年8月2日 22:45:08
	 */
	@RequestMapping("regIndex")
	public String regIndex(HttpServletRequest request,Model model) {
		
		
		String view = getLocalPath(request,"redPack/register");
		
		//推荐人电话号码
		String refMobile = request.getParameter("mobile");
		
		//A网B网
		String netWork = request.getParameter("netWork");
		
		//推荐人手机
		model.addAttribute("refMobile",refMobile);
		model.addAttribute("netWork",netWork);
		
		logger.info("----注册用户跳转页面----");
		
		return view;
	}
    
	
	/**
	 * 发送短信验证码
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "sendVirifyCode")
    public void sendPhoneVirifyCode(HttpServletRequest request,HttpServletResponse response,
    		HttpSession session) {
		JSONObject jsonObject = new JSONObject();
		String mobilePhone = request.getParameter("mobile");
		if(StringUtils.isBlank(mobilePhone) ){
			jsonObject.put("result", "手机号码为空,请重试"); 
			jsonObject.put("resultCode", "1"); 
			ResponseUtils.renderText(response, "UTF-8", jsonObject.toString());
			return;
		}
		
		//发送信息
		int virifyCode = (int)(Math.random()*9000+1000);
		String  message ="您好!你的短信验证码是"+virifyCode+",5分钟内有效。";
        IResult result = smsService.sendMessage(mobilePhone, message);
        
        session.setAttribute(mobilePhone, virifyCode);
     
        if (0 == result.getResultCode()) {
            //发送成功
        	logger.info("向手机号:"+mobilePhone+"发送验证码成功,验证码为:"+message);
            jsonObject.put("ret","0");
            jsonObject.put("resultMessage","短信发送成功!");
        } else {
            //发送失败
        	logger.info("向手机号:"+mobilePhone+"发送验证码失败");
            jsonObject.put("ret","1");
            jsonObject.put("resultMessage","短信发送失败!");
        }
        ResponseUtils.renderText(response, null,jsonObject.toString());
    }
	
	/*
	private static String getIpAddress(HttpServletRequest request) { 
		String ip = request.getHeader("x-forwarded-for"); 
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
		ip = request.getHeader("Proxy-Client-IP"); 
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
		ip = request.getHeader("WL-Proxy-Client-IP"); 
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
		ip = request.getHeader("HTTP_CLIENT_IP"); 
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
		ip = request.getHeader("HTTP_X_FORWARDED_FOR"); 
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
		ip = request.getRemoteAddr(); 
		}
		return ip; 
	}
	*/
	private static String getIpAddress(HttpServletRequest request) { 
		StringBuffer sb = new StringBuffer();
		sb.append("x-forwarded-for:").append(request.getHeader("x-forwarded-for"))
		  .append("Proxy-Client-IP").append(request.getHeader("Proxy-Client-IP"))
		  .append("WL-Proxy-Client-IP").append(request.getHeader("WL-Proxy-Client-IP"))
		  .append("HTTP_CLIENT_IP").append(request.getHeader("HTTP_CLIENT_IP"))
		  .append("HTTP_X_FORWARDED_FOR").append(request.getHeader("HTTP_X_FORWARDED_FOR"));
		
		return sb.toString(); 
	}
	
	private String cutRef(String ref){
		String m = ref.substring(3);
		return m;
	}
	
	
	/**
	 * 新用户注册
	 * @param request
	 * @param response
	 * @param session
	 */
	@RequestMapping(value = "register", method = RequestMethod.POST)
	public void register(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		JSONObject jsonObject = new JSONObject();
			String name = request.getParameter("name");
			String mobilePhone = request.getParameter("mobile");
			String pwd =  request.getParameter("password");
			String referenceId =  request.getParameter("refferee");
			String mobileCode =  request.getParameter("mobileCode");
			String netWork =  request.getParameter("netWork");
			String passwordTwo =  request.getParameter("passwordTwo");
			if(StringUtils.isBlank(netWork)){
				netWork = "A";
			}
			
			Object vc =session.getAttribute(mobilePhone);
			String verifyCode=null;
			if(vc !=null){
				verifyCode= String.valueOf(vc);
			}
			
			// 测试环境注释验证码
			
			// 测试环境注释验证码
			String ifSend = paramService.getByName(WebConstants.IF_SEND_MESSAGE);
			if(StringUtils.isNotBlank(ifSend) && "1".equals(ifSend)){
				if(!mobileCode.equals(verifyCode) ){
					logger.info("手机号:"+mobilePhone+"验证码失败 session verifycode:"+verifyCode+" 参数："+mobileCode);
					jsonObject.put("result", "手机验证码有误");
					ResponseUtils.renderText(response, "UTF-8", JSONObject.fromObject(jsonObject).toString());
					return;
				}
			}
			
			Map<String, Object> parameterMap = new HashMap<String, Object>();
			parameterMap.put("userName", mobilePhone);
			// 获取登录用户userId
			UserDo temp = userService.getByUserDo(parameterMap);

			if(temp!=null&&temp.getId()!=null){
				jsonObject.put("result", "输入的手机号已存在");
				ResponseUtils.renderText(response, "UTF-8", JSONObject.fromObject(jsonObject).toString());
				return;
			}
			if (StringUtils.isBlank(name)) {
				jsonObject.put("result", "请输入昵称");
				ResponseUtils.renderText(response, "UTF-8", JSONObject.fromObject(jsonObject).toString());
				return;
			}
			if (StringUtils.isBlank(mobilePhone)) {
				jsonObject.put("result", "请输入手机号");
				ResponseUtils.renderText(response, "UTF-8", JSONObject.fromObject(jsonObject).toString());
				return;
			}
			if (StringUtils.isBlank(referenceId)) {
				jsonObject.put("result", "请输入推荐人手机号码");
				ResponseUtils.renderText(response, "UTF-8", JSONObject.fromObject(jsonObject).toString());
				return;
			}
			
			if (StringUtils.isBlank(passwordTwo)) {
				jsonObject.put("result", "请输入二级密码");
				ResponseUtils.renderText(response, "UTF-8", JSONObject.fromObject(jsonObject).toString());
				return;
			}
			
			//根据推荐人的手机号码查找推荐人
			//parameterMap.put("mobile", referenceId);
			parameterMap.clear();
			parameterMap.put("userName", referenceId);
			parameterMap.put("status", "1");
			UserDo refUser = userService.getByUserDo(parameterMap);
			if(refUser == null){
				jsonObject.put("result", "查找不到对应的推荐用户或者推荐用户为非正式会员");
				ResponseUtils.renderText(response, "UTF-8", JSONObject.fromObject(jsonObject).toString());
				return;
			}
			
			String pwdMd5 =DigestUtils.md5Hex(pwd + WebConstants.PASS_KEY);
			String pwdTwoMd5 =DigestUtils.md5Hex(passwordTwo + WebConstants.PASS_KEY);
			UserDo userDo =new UserDo();
			userDo.setCreateUser(-1L);                 //当前用户是记录创建者
			userDo.setUserName(mobilePhone);
			userDo.setPassword(pwdMd5);
			userDo.setTwoLevelPwd(pwdMd5);
			userDo.setGrade(0);								//当前等级
			userDo.setOrgan("0");								//组织机构
			userDo.setEnabled("0");								//状态  默认激活
		
			Random ne=new Random();//实例化一个random的对象ne
	        String activeNum="" + (ne.nextInt(9999-1000+1)+1000);
			userDo.setRemark(activeNum);
			userDo.setReferrerId(refUser.getId());				//推荐人ID
			userDo.setParentId(0l);	
			userDo.setStatus(1);//接点人ID
			userDo.setTreeNode("");								//业务方向
			userDo.setName(name);
			userDo.setTwoLevelPwd(pwdTwoMd5);               //二级密码
			UserInfoDo userInfoDo = new UserInfoDo();
			userInfoDo.setRealName(name);
			userInfoDo.setMobile(mobilePhone);

			userDo.setCreateTime(new Date());
			userDo.setUserInfoDo(userInfoDo);
			
			//开始保存
			IResult<Long> result = userService.saveUser(userDo);
			if (result.isSuccess()) {
				Long userId = userDo.getId();
				userInfoDo.setUserId(userId);
				userInfoService.saveUserInfo(userInfoDo);
				
				jsonObject.put("result", "注册成功");
				ResponseUtils.renderText(response, null, JSONObject.fromObject(jsonObject).toString());
			} else {
				jsonObject.put("result", "注册失败:"+result.getErrorMessage());
				ResponseUtils.renderText(response,
				null,JSONObject.fromObject(jsonObject).toString());
			}
	}
	
	
	/*************************************************
	 * 
	 * 申请代理
	 * 
	 * ***********************************************/
	
	/**
	 * 申请代理页面
	 * 
	 * @return
	 * @author: huangzl
	 */
	@RequestMapping("/applyAgent")
	public String applyAgent(HttpServletRequest request,Model model) {
		
		String view = getLocalPath(request,"member/applyAgent");
		return view;
	}
	/**
	 * 设置报单中心
	 * 
	 * @return
	 * @author: huangzl
	 */
	@RequestMapping("/reportCenter")
	public String reportCenter(HttpServletRequest request,Model model) {
		
		String view = getLocalPath(request,"member/reportCenter");
		return view;
	}
	
	/**
	 * 保存报单中以
	 * 
	 * @return
	 * @author: huangzl
	 */
	@RequestMapping("/saveCenter")
	public void saveCenter(HttpServletRequest request,HttpServletResponse response,Model model) {
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", "1");
		//更新用户报单中以人
		HttpSession  session = request.getSession();
		UserDo user = (UserDo) session.getAttribute(WebConstants.SESSION_USER);
		UserDo treeUser = userService.getById(user.getId());
		if(StringUtils.isNotBlank(treeUser.getTreeNode())){
			jsonObject.put("result", "3");
			jsonObject.put("message", "用户已指定报单中心");
			ResponseUtils.renderText(response, null, JSONObject.fromObject(jsonObject).toString());
			return;
		}
		
		String mobile = request.getParameter("mobile");
		Map<String,Object> parm = new HashMap<String,Object>();
		parm.put("userName", mobile);
		UserDo centerUser = userService.getByUserDo(parm);
		
		if( null == centerUser){
			jsonObject.put("result", "0");
			jsonObject.put("message", "手机号码不存在用户");
			ResponseUtils.renderText(response, null, JSONObject.fromObject(jsonObject).toString());
			return;
		}
		String organ = centerUser.getOrgan();
		if(!"3".equals(organ)){
			jsonObject.put("result", "2");
			jsonObject.put("message", "手机号码不是报单中心");
			ResponseUtils.renderText(response, null, JSONObject.fromObject(jsonObject).toString());
			return;
		}
		
	
		 UserDo tempSave = new UserDo();
		 tempSave.setId(user.getId());
		 tempSave.setTreeNode(centerUser.getId().toString());
		 userService.updateUser(tempSave);
		
		
		jsonObject.put("result", "1");
		jsonObject.put("message", "报单中心保存成功");
		ResponseUtils.renderText(response, null, JSONObject.fromObject(jsonObject).toString());
	
	}
	
	/**
	 * 新用户注册
	 * @param request
	 * @param response
	 * @param session
	 */
	@RequestMapping(value = "saveApplyAgent", method = RequestMethod.POST)
	public void saveApplyAgent(HttpServletRequest request, 
							   HttpServletResponse response) {
		
		JSONObject jsonObject = new JSONObject();
		String agentType = request.getParameter("agentType");
		String province = request.getParameter("province");
		String city =  request.getParameter("city");
		
		HttpSession  session = request.getSession();
		UserDo user = (UserDo) session.getAttribute(WebConstants.SESSION_USER);
		Long userId = user.getId();
			
		if(StringUtils.isBlank(city)){
			jsonObject.put("result", "市区不可以为空");
			ResponseUtils.renderText(response, "UTF-8", JSONObject.fromObject(jsonObject).toString());
			return;
		}
		if (StringUtils.isBlank(province)) {
			jsonObject.put("result", "省不能为空");
			ResponseUtils.renderText(response, "UTF-8", JSONObject.fromObject(jsonObject).toString());
			return;
		}
		if (StringUtils.isBlank(agentType)) {
			jsonObject.put("result", "代理类型不能为空");
			ResponseUtils.renderText(response, "UTF-8", JSONObject.fromObject(jsonObject).toString());
			return;
		}
		
		
		//根据推荐人的手机号码查找推荐人
		ApplyAgentDo agentDo =new ApplyAgentDo();
		agentDo.setUserId(userId);
		agentDo.setCreateTime(new Date());
		agentDo.setAgentType(agentType);
		agentDo.setCity(city);
		agentDo.setProvince(province);
		agentDo.setStatus("F");
		
		//开始保存
		IResult<Long> result = userService.saveApplyAgent(agentDo);
		if (result.isSuccess()) {
			jsonObject.put("result", "代理申请成功");
			ResponseUtils.renderText(response, null, JSONObject.fromObject(jsonObject).toString());
		} else {
			jsonObject.put("result", "代理申请失败:"+result.getErrorMessage());
			ResponseUtils.renderText(response,null,JSONObject.fromObject(jsonObject).toString());
		}
		
	}
	
	/**
	 * 审批 申请代理页面
	 * 
	 * @return
	 * @author: huangzl
	 */
	@RequestMapping("/approveApplyAgent")
	public String approveApplyAgent(HttpServletRequest request,Model model) {
		String view = getLocalPath(request,"member/approveApplyAgent");
		Map<String,Object> paraMap = new HashMap<String,Object>();
		List<Map<String,Object>>  userAgentList = userService.getApplyAgentUser(paraMap);
		model.addAttribute("applyUserList", userAgentList);
		return view;
	}
	
	
	/**
	 * 审批 申请代理页面
	 * 
	 * @return
	 * @author: huangzl
	 */
	@RequestMapping("/saveApproveApplyAgent")
	public void saveApproveApplyAgent(HttpServletRequest request,HttpServletResponse response,Model model) {
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("resultCode", "1");
		
		String id = request.getParameter("id");
		String status = request.getParameter("status");
		
		HttpSession  session = request.getSession();
		UserDo user = (UserDo) session.getAttribute(WebConstants.SESSION_USER);
		Long userId = user.getId();
			
		if(StringUtils.isBlank(id)){
			jsonObject.put("result", "审批记录ID不能为空");
			ResponseUtils.renderText(response, "UTF-8", JSONObject.fromObject(jsonObject).toString());
			return;
		}
		
		if(StringUtils.isBlank(status)){
			jsonObject.put("result", "审批记录状态不能为空");
			ResponseUtils.renderText(response, "UTF-8", JSONObject.fromObject(jsonObject).toString());
			return;
		}
		
		ApplyAgentDo agentDo =new ApplyAgentDo();
		agentDo.setApproveTime(new Date());
		agentDo.setStatus(status);
		agentDo.setId(Long.valueOf(id));
		
		//开始保存
		IResult<Long> result = userService.updateApplyAgent(agentDo);
		if (result.isSuccess()) {
			jsonObject.put("resultCode", "0");
			jsonObject.put("result", "代理申请审批成功");
			ResponseUtils.renderText(response, null, JSONObject.fromObject(jsonObject).toString());
		} else {
			jsonObject.put("result", "代理申请审批失败:"+result.getErrorMessage());
			ResponseUtils.renderText(response,null,JSONObject.fromObject(jsonObject).toString());
		}
	}
	

}

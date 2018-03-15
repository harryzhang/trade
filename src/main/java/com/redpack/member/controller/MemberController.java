
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
package com.redpack.member.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.redpack.account.dao.IUserDao;
import com.redpack.account.faced.IBizUserAccountService;
import com.redpack.account.faced.IUserAccountDetailService;
import com.redpack.account.faced.IUserAccountIncomeService;
import com.redpack.account.faced.IUserService;
import com.redpack.account.model.BizUserAccountDo;
import com.redpack.account.model.UserAccountDetailDo;
import com.redpack.account.model.UserDo;
import com.redpack.base.controller.BaseController;
import com.redpack.base.controller.TokenUtil;
import com.redpack.base.result.IResult;
import com.redpack.base.result.ResultSupport;
import com.redpack.constant.AccountMsg;
import com.redpack.constant.WebConstants;
import com.redpack.member.IMemberService;
import com.redpack.order.IOrderService;
import com.redpack.order.model.OrderDo;
import com.redpack.utils.DateUtil;
import com.redpack.utils.ResponseUtils;
import com.redpack.withdraw.model.WithdrawDo;

/**
 * 
 * @author MBENBEN
 *
 */
@Controller
@RequestMapping("/member")
public class MemberController extends BaseController{
	private static final Logger logger = Logger.getLogger(MemberController.class);
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IMemberService memberService;
	@Autowired
	private IOrderService orderService;
	@Autowired
	private IUserDao userDao;
	
	@Autowired
	private IUserAccountDetailService userAccountDetailService;
	
	@Autowired
	private IUserAccountIncomeService userAccountIncomeService;
	
	@Autowired
	private IBizUserAccountService bizUserAccountService;
	
	
	/**
	 * 建设中
	 * 
	 * @return
	 */
	@RequestMapping("waitCreate")
	public String waitCreate(HttpServletRequest request,String pwdFlag,Model model) {
		logger.info("----建设中----");
		return getLocalPath(request,"member/waitCreate");
	}
	
	/**
	 * 分享管理
	 * 
	 * @return
	 */
	@RequestMapping("shareManager")
	public String shareManager(HttpServletRequest request,String pwdFlag,Model model) {
		logger.info("----分享管理----");
		return getLocalPath(request,"member/shareManager");
	}
	
	
	/**
	 * 用户安全设置
	 * 
	 * @return
	 */
	@RequestMapping("userSettle")
	public String resetPwdIndex(HttpServletRequest request,String pwdFlag,Model model) {
		logger.info("----用户安全设置----");
		return getLocalPath(request,"member/userSettle");
	}
	/**
	 * 我的收益
	 * 
	 * @return
	 */
	@RequestMapping("userIncome")
	public String userIncome(HttpServletRequest request,String pwdFlag,Model model) {
		logger.info("----我的收益----");
		UserDo currentUser = (UserDo)  request.getSession().getAttribute(WebConstants.SESSION_USER);
		long  userId = currentUser.getId();
		List<BizUserAccountDo> userAccountLst = memberService.getUserAccount(userId);
		if(!CollectionUtils.isEmpty(userAccountLst)){
			for(BizUserAccountDo  account : userAccountLst){
				List<Map<String,Object>> optLst = memberService.getOptionsByAccountCode(account.getAccountType());
				account.setOptLst(optLst);
			}
		}
		model.addAttribute("userAccountList", userAccountLst);
		return getLocalPath(request,"member/userIncome");
	}
	/**
	 * 管理中以
	 * 
	 * @return
	 */
	@RequestMapping("userManager")
	public String userManager(HttpServletRequest request,String pwdFlag,Model model) {
		logger.info("----管理中心----");
		
		UserDo user = (UserDo)request.getSession().getAttribute(WebConstants.SESSION_USER);
		if(null == user){
			
			String id = request.getParameter("id");
			if(null != id){
				user = userDao.getById(Long.valueOf(id));
			}else{
				return getLocalPath(request,"member/userManager");
			}
		}
		
		Long userId = user.getId();
		
//		KuangjiUserAccountDo userAccountDo = memberService.getByUserid(userId);		
		List<UserDo> childDoList = userService.selectChildByParentId(userId);
		
		BigDecimal amount = bizUserAccountService.getAccountTypeAmount(WebConstants.REFFER_ACCOUNT, userId);
		BigDecimal teamAmount = bizUserAccountService.getAccountTypeAmount(WebConstants.TEAM_ACCOUNT, userId);
		
		
		model.addAttribute("amount", amount);
		JSONObject jsonObject1 = new JSONObject();
		jsonObject1.put("name", "直推业绩:" + amount + ";团队业绩:" + teamAmount );
		jsonObject1.put("id", ""+userId);
		if(null != childDoList && childDoList.size()>0){
			jsonObject1.put("isParent", "true");
		}else{
			jsonObject1.put("isParent", "false");
		}
		jsonObject1.put("open", "true");
		
		if(null != childDoList && childDoList.size()>0){
			
			//转换 下级成树的节点
			JSONArray  childArray = new JSONArray();
			Map<String,Object> parameterMap = new HashMap<String,Object>();
			for(UserDo u  : childDoList){
				JSONObject jsonChild = new JSONObject();
//				parameterMap.put("userId", u.getId());
//				List<UserAccountIncomeDo>  incomeList = userAccountIncomeService.selectUserAccount(parameterMap);
//				BigDecimal teamAmount = BigDecimal.ZERO;
//				if(null != incomeList && incomeList.size()> 0 ){
//					UserAccountIncomeDo uIncomeDo = incomeList.get(0);
//					teamAmount = uIncomeDo.getTeamAmount();
//				}
				BigDecimal reffAmount = bizUserAccountService.getAccountTypeAmount(WebConstants.REFFER_ACCOUNT, u.getId());
				BigDecimal uTeamAmount = bizUserAccountService.getAccountTypeAmount(WebConstants.TEAM_ACCOUNT, u.getId());
				jsonChild.put("name", u.getName()+""
							+DateUtil.dateToString(u.getCreateTime())+"直推:"+ reffAmount +  ",团队业绩:"+ uTeamAmount);//name 拼装
				jsonChild.put("id", u.getId());
				jsonChild.put("isParent", "true");//true 才会加载下面的子节点
				childArray.add(jsonChild);
			}
			jsonObject1.put("children", childArray);
		}
		
		//[{ id:'011',	name:'n1.n1',	isParent:true},{ id:'012',	name:'n1.n2',	isParent:false},{ id:'013',	name:'n1.n3',	isParent:true},{ id:'014',	name:'n1.n4',	isParent:false}]
		model.addAttribute("initZtreeJson",jsonObject1.toString());
		model.addAttribute("myTeamAmount",amount);
		
		
		return getLocalPath(request,"member/userManager");
	}
	/**
	 * 我的兑换
	 * 
	 * @return
	 */
	@RequestMapping("userCharge")
	public String userCharge(String pwdFlag,Model model) {
		logger.info("----管理中心----");
		return "member/userCharge";
	}
	/**
	 * 提现记录
	 * 
	 * @return
	 */
	@RequestMapping("withdraw")
	public String withdraw(HttpServletRequest request,Model model) {
		logger.info("----用户提现----");
		String token = TokenUtil.putToken(request, TokenUtil.BIZ_CODE_RECHANGE);
		
		long  userId = getUserId(request);
		BigDecimal amount = bizUserAccountService.getAccountTypeAmount(WebConstants.RMB_ACCOUNT, userId);
		model.addAttribute("amount", amount);
		model.addAttribute("token", token);
		return getLocalPath(request,"member/withdraw");
	}
	
	
	/**
	 * 奖金豆提现记录
	 * 
	 * @return
	 */
	@RequestMapping("withdrawJjd")
	public String withdrawJjd(HttpServletRequest request,Model model) {
		logger.info("----用户提现奖金豆----");
		String token = TokenUtil.putToken(request, TokenUtil.BIZ_CODE_RECHANGE);
		
		long  userId = getUserId(request);
		BigDecimal amount = bizUserAccountService.getAccountTypeAmount(WebConstants.JJD_ACCOUNT, userId);
		model.addAttribute("amount", amount);
		model.addAttribute("token", token);
		return getLocalPath(request,"member/withdrawJjd");
	}
	
	
	
	/**
	 * 用户退本
	 * 
	 * @return
	 */
	@RequestMapping("unPay")
	public void unPay(HttpServletRequest request,HttpServletResponse response,
			Model model,HttpSession session,@ModelAttribute WithdrawDo withdrawDo) {
		logger.info("----用户退本----");
		long  userId = getUserId(request);
		
		String passwordTwo = request.getParameter("passwordTwo");
		UserDo user = (UserDo) session.getAttribute(WebConstants.SESSION_USER);
		// 测试环境注释验证码
		JSONObject jsonObject = new JSONObject();
				
		String userPasswordTwo = user.getTwoLevelPwd();
		String pwdMd5 = DigestUtils.md5Hex(passwordTwo + WebConstants.PASS_KEY);
		if (!pwdMd5.equals(userPasswordTwo)) {
			jsonObject.put("result", "二级密码错误,请重新输入");
			jsonObject.put("success", false);
			ResponseUtils.renderText(response, "UTF-8", JSONObject.fromObject(jsonObject).toString());
			return;
		}
		withdrawDo.setUserId(userId);
		int unpay = bizUserAccountService.userUnPay(withdrawDo);
		
		if(unpay == 0 ){
			jsonObject.put("resultCode", 0);
		}else if(unpay == 1 ){
			jsonObject.put("success", false);
			jsonObject.put("resultCode", 1);
			jsonObject.put("result", "超出时间限制");
		}else{
			jsonObject.put("resultCode", 1);
			jsonObject.put("success", false);
			jsonObject.put("result", "退本失败");
			
		}
		
		ResponseUtils.renderText(response, "UTF-8", jsonObject.toString());
		
	}
	/**
	 * 用户退本
	 * 
	 * @return
	 */
	@RequestMapping("unpayInfo")
	public String unpayInfo(HttpServletRequest request,HttpServletResponse response,
			Model model) {
		logger.info("----用户退本----");
		long  userId = getUserId(request);
		BigDecimal amount = bizUserAccountService.getAccountTypeAmount(WebConstants.SECURITY_ACCOUNT, userId);
		model.addAttribute("amount", amount);
		return getLocalPath(request,"member/unpay");
		
	}

	/**
	 * 资金明细
	 * 
	 * @return
	 */
	@RequestMapping("amountDetail")
	public String amountDetail(HttpServletRequest request,Model model) {
		logger.info("----资金明细----");
		long  userId = getUserId(request);
		//用户帐户信息
//		KuangjiUserAccountDo userAccountDo = memberService.getByUserid(userId);		
//		model.addAttribute("userAccount", userAccountDo);
		
		String dateToday = DateUtil.formatDate(new Date());
		//用户资金明细
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("userId", userId);
		parameterMap.put("createTime", dateToday);
		parameterMap.put("incomeType", AccountMsg.type_4.getAccountType());
		List<UserAccountDetailDo> userAccountDetailList = userAccountDetailService.selectUserAccountDetail(parameterMap);
		
	
		BigDecimal todayAmount = BigDecimal.ZERO;
		for (UserAccountDetailDo uaDetailDo : userAccountDetailList) {
			todayAmount= todayAmount.add(uaDetailDo.getAmount());
		}
		
		parameterMap.put("incomeType", AccountMsg.type_5.getAccountType());
		userAccountDetailList = userAccountDetailService.selectUserAccountDetail(parameterMap);
		BigDecimal teamAmount = BigDecimal.ZERO;
		for (UserAccountDetailDo uaDetailDo : userAccountDetailList) {
			teamAmount= teamAmount.add(uaDetailDo.getAmount());
		}
		
		model.addAttribute("todayAmount", todayAmount);
		model.addAttribute("teamAmount", teamAmount);
		
		
		
		return getLocalPath(request,"member/amountDetail");
	}
	/**
	 * 推荐人明细
	 * 
	 * @return
	 */
	@RequestMapping("refUser")
	public String refUser(HttpServletRequest request,Model model) {
		Object sessionUser = request.getSession().getAttribute(WebConstants.SESSION_USER);
		UserDo user = null;
		if(sessionUser != null){
			user = (UserDo)sessionUser;
			Map<String,Object> parm = new HashMap<String,Object>();
			parm.put("userId", user.getId());
			List<Map<String,Object>> userDoList = userService.listRef(parm);
			model.addAttribute("userCount", userDoList.size());
			model.addAttribute("userList", userDoList);
		}
		
		return getLocalPath(request,"member/refUser");
	}
	
	
	/**
	 * 金币明细
	 * 
	 * @return
	 */
	@RequestMapping("orderDetail")
	public String orderDetail(HttpServletRequest request,Model model) {
		Object sessionUser = request.getSession().getAttribute(WebConstants.SESSION_USER);
		UserDo user = null;
		if(sessionUser != null){
			user = (UserDo)sessionUser;
			Map<String,Object> parm = new HashMap<String,Object>();
			parm.put("userId", user.getId());
			parm.put("orderType", "2");
			List<OrderDo> orderList = orderService.selectPointOrder(parm);
			model.addAttribute("orderList", orderList);
		}
		
		return getLocalPath(request,"member/orderDetail");
	}
	/**
	 * 账户明细
	 * 
	 * @return
	 */
	@RequestMapping("accountDetail")
	public String guquanDetail(HttpServletRequest request,Model model) {
		Object sessionUser = request.getSession().getAttribute(WebConstants.SESSION_USER);
		UserDo user = null;
		if(sessionUser != null){
			user = (UserDo)sessionUser;
			String accountType = request.getParameter("accountType");
			Map<String,Object> parm = new HashMap<String,Object>();
			parm.put("userId", user.getId());
			parm.put("accountType", accountType);
			List<UserAccountDetailDo> userDoList = userAccountDetailService.selectUserAccountDetail(parm);
			model.addAttribute("accountDetail", userDoList);
		}
		
		return getLocalPath(request,"member/accountDetail");
	}

	/**
	 * 充值明细
	 * 
	 * @return
	 */
	@RequestMapping("chongzhiDetail")
	public String chongzhiDetail(HttpServletRequest request,Model model) {
		Object sessionUser = request.getSession().getAttribute(WebConstants.SESSION_USER);
		UserDo user = null;
		if(sessionUser != null){
			user = (UserDo)sessionUser;
			Map<String,Object> parm = new HashMap<String,Object>();
			parm.put("userId", user.getId());
			parm.put("accountType", WebConstants.PET_ACCOUNT);
			parm.put("incomeType", 7);
			List<UserAccountDetailDo> userDoList = userAccountDetailService.selectUserAccountDetail(parm);
			model.addAttribute("pointDetail", userDoList);
		}
		
		return getLocalPath(request,"member/chongzhiDetail");
	}
	
	/**
	 * 现金豆转换
	 * 
	 * @return
	 */
	@RequestMapping("transRmb")
	public String transRmb(HttpServletRequest request,Model model) {
		logger.info("----现金豆转让----");
		String token = TokenUtil.putToken(request, "point_rmb");
		model.addAttribute("token", token);
		Long userId = this.getUserId(request);
		if(null == userId){
			throw new RuntimeException("无效的用户ID");
		}
		
		BigDecimal jjdAmt = bizUserAccountService.getAccountTypeAmount(WebConstants.RMB_ACCOUNT, userId);
		model.addAttribute("pointAmt",jjdAmt);
		return getLocalPath(request,"member/transRmb");
	}
	

	/**
	 * @Description: 储值豆转换
	 * @param response
	 * @param user
	 * @return 1 转让积分未输入|2验证码输入错误 |用户密码错误
	 */
	@RequestMapping(value = "saveTransRmb", method = RequestMethod.POST)
	public void saveTransRmb(HttpServletRequest request, HttpServletResponse response) {
		
		IResult result = ResultSupport.buildResult(ResultSupport.success_code);
		
		String accountType = request.getParameter("accountType");
		String qtyParam = request.getParameter("qty");
		String pwd = request.getParameter("pwd");
		try{
			
//			boolean isPass = TokenUtil.checkToken(request, "point_rmb");
//			String newToken = TokenUtil.putToken(request, "point_rmb");
//			jsonObject.put("token", newToken);
//			if(isPass == false){
//				jsonObject.put("result", 10);
//				return;
//			}
			// 输入转让积分
			if (StringUtils.isBlank(qtyParam) ) {
				result.setErrorMessage("数量不能为空");
				result.setResultCode(ResultSupport.errorCode);
				return;
			}
	
			// 验证码是否正确
//			String sessionCode = (String) request.getSession().getAttribute(pageId + "_checkCode");
//			if (StringUtils.isBlank(code) || !code.equals(sessionCode)) {
//				jsonObject.put("result", 2);
//				return;
//			}
	
			
			// 二级密码验证
			Long userId = this.getUserId(request);
			UserDo loginUser = userService.getById(userId);
			String jiaoyemm = DigestUtils.md5Hex(pwd + WebConstants.PASS_KEY);
			if (StringUtils.isBlank(pwd)  || (!loginUser.getTwoLevelPwd().equals(jiaoyemm)) ) {
				result.setErrorMessage("交易密码错误");
				result.setResultCode(ResultSupport.errorCode);
				return;
			}
			
			
			//大小比较
			BigDecimal qty = BigDecimal.ZERO;
			try{
				qty = new BigDecimal(qtyParam);
			}catch(Exception e){
				logger.error(e);
				result.setErrorMessage("数量数据格式有误");
				result.setResultCode(ResultSupport.errorCode);
				return;
			}
			if(BigDecimal.ZERO.compareTo(qty)>= 0 ){
				result.setErrorMessage("数量必须大于0");
				result.setResultCode(ResultSupport.errorCode);
				return;
			}
			
			/**
			 * 零钱转定存算力转让
			 */
			bizUserAccountService.buySecurity(userId,qty);
			
		
		}catch (RuntimeException e) {
			logger.error(e);
			result.setErrorMessage(e.getMessage());
			result.setResultCode(ResultSupport.errorCode);
		}catch (Exception e) {
			logger.error(e);
			result.setErrorMessage("保存失败");
			result.setResultCode(ResultSupport.errorCode);
		}finally{
			ResponseUtils.renderText(response, "UTF-8", JSONObject.fromObject(result).toString());
		}
		
		
	}
	
	/**
	 * 奖金豆转换
	 * 
	 * @return
	 */
	@RequestMapping("transJjd")
	public String transJjd(HttpServletRequest request,Model model) {
		logger.info("----奖金豆转让----");
		String token = TokenUtil.putToken(request, "point_jjd");
		model.addAttribute("token", token);
		Long userId = this.getUserId(request);
		if(null == userId){
			throw new RuntimeException("无效的用户ID");
		}
		
		BigDecimal jjdAmt = bizUserAccountService.getAccountTypeAmount(WebConstants.JJD_ACCOUNT, userId);
		model.addAttribute("pointAmt",jjdAmt);
		return getLocalPath(request,"member/transJjd");
	}
	
	
	

	/**
	 * @Description: 储值豆转换
	 * @param response
	 * @param user
	 * @return 1 转让积分未输入|2验证码输入错误 |用户密码错误
	 */
	@RequestMapping(value = "saveTransJjd", method = RequestMethod.POST)
	public void saveTransJjd(HttpServletRequest request, HttpServletResponse response) {
		
		JSONObject jsonObject = new JSONObject();
		
		String jifeng = request.getParameter("jifeng");
		String pageId = request.getParameter("pageId");
		String code = request.getParameter("code");
		try{
			
			boolean isPass = TokenUtil.checkToken(request, "point_jjd");
			String newToken = TokenUtil.putToken(request, "point_jjd");
			jsonObject.put("token", newToken);
			if(isPass == false){
				jsonObject.put("result", 10);
				return;
			}
			// 输入转让积分
			if (StringUtils.isBlank(jifeng) ) {
				jsonObject.put("result", 1);
				return;
			}
	
			// 验证码是否正确
			String sessionCode = (String) request.getSession().getAttribute(pageId + "_checkCode");
			if (StringUtils.isBlank(code) || !code.equals(sessionCode)) {
				jsonObject.put("result", 2);
				return;
			}
	
			
			// 二级密码验证
			String pwd = request.getParameter("pwd");
			Long userId = this.getUserId(request);
			UserDo loginUser = userService.getById(userId);
			String jiaoyemm = DigestUtils.md5Hex(pwd + WebConstants.PASS_KEY);
			if (StringUtils.isBlank(pwd)  || (!loginUser.getTwoLevelPwd().equals(jiaoyemm)) ) {
				jsonObject.put("result", 11);
				return;
			}
			
			
			//大小比较
			BigDecimal jifengAmt = BigDecimal.ZERO;
			try{
				jifengAmt = new BigDecimal(jifeng);
			}catch(Exception e){
				e.printStackTrace();
			}
			if(BigDecimal.ZERO.compareTo(jifengAmt)>= 0 ){
				jsonObject.put("result", 4);
				return;
			}
			
			/**
			 * 积分转让
			 */
			
			bizUserAccountService.saveTransJjd(userId, jifengAmt);
			
			// 转让成功
			jsonObject.put("result", 0);
		
		}catch (Exception e) {
			jsonObject.put("result", 3);
			jsonObject.put("msg", e.getMessage());
			e.printStackTrace();
		}finally{
			ResponseUtils.renderText(response, "UTF-8", jsonObject.toString());
		}
		
		
	}
	
	
	/**
	 * 储值豆转换
	 * 
	 * @return
	 */
	@RequestMapping("transPoint")
	public String transPoint(HttpServletRequest request,Model model) {
		logger.info("----积分转让----");
		String token = TokenUtil.putToken(request, "point_tran");
		model.addAttribute("token", token);
		Long userId = this.getUserId(request);
		if(null == userId){
			throw new RuntimeException("无效的用户ID");
		}
		
		BigDecimal pointAmt = userAccountDetailService.selectUserTransPoint(userId);
		model.addAttribute("pointAmt",pointAmt);
		return getLocalPath(request,"member/transPoint");
	}
	
	
	/**
	 * @Description: 储值豆转换
	 * @param response
	 * @param user
	 * @return 1 转让积分未输入|2验证码输入错误 |用户密码错误
	 */
	@RequestMapping(value = "saveTransPoint", method = RequestMethod.POST)
	public void saveTransPoint(HttpServletRequest request, HttpServletResponse response) {
		
		JSONObject jsonObject = new JSONObject();
		
		String jifeng = request.getParameter("jifeng");
		String pageId = request.getParameter("pageId");
		String code = request.getParameter("code");
		try{
			
			boolean isPass = TokenUtil.checkToken(request, "point_tran");
			String newToken = TokenUtil.putToken(request, "point_tran");
			jsonObject.put("token", newToken);
			if(isPass == false){
				jsonObject.put("result", 10);
				return;
			}
			// 输入转让积分
			if (StringUtils.isBlank(jifeng) ) {
				jsonObject.put("result", 1);
				return;
			}
	
			// 验证码是否正确
			String sessionCode = (String) request.getSession().getAttribute(pageId + "_checkCode");
			if (StringUtils.isBlank(code) || !code.equals(sessionCode)) {
				jsonObject.put("result", 2);
				return;
			}
	
			
			
			//大小比较
			BigDecimal jifengAmt = BigDecimal.ZERO;
			try{
				jifengAmt = new BigDecimal(jifeng);
			}catch(Exception e){
				e.printStackTrace();
			}
			if(BigDecimal.ZERO.compareTo(jifengAmt)>= 0 ){
				jsonObject.put("result", 4);
				return;
			}
			
			/**
			 * 积分转让
			 */
			Long userId = this.getUserId(request);
			bizUserAccountService.saveTransPoint(userId, jifengAmt);
			
			// 转让成功
			jsonObject.put("result", 0);
		
		}catch (Exception e) {
			jsonObject.put("result", 3);
			jsonObject.put("msg", e.getMessage());
			e.printStackTrace();
		}finally{
			ResponseUtils.renderText(response, "UTF-8", jsonObject.toString());
		}
		
		
	}
	
	/**
	 * 积分转让
	 * 
	 * @return
	 */
	@RequestMapping("transJifeng")
	public String transJifeng(HttpServletRequest request,Model model) {
		logger.info("----积分转让----");
		String token = TokenUtil.putToken(request, "jifen_tran");
		model.addAttribute("token", token);
		return getLocalPath(request,"member/transJifeng");
	}
	
	
	/**
	 * @Description: 转让积分
	 * @param response
	 * @param user
	 * @return 1 转让积分未输入|2验证码输入错误 |用户密码错误
	 */
	@RequestMapping(value = "saveTrans", method = RequestMethod.POST)
	public void saveTrans(HttpServletRequest request, 
						  HttpServletResponse response) {
		
		JSONObject jsonObject = new JSONObject();
		
		String jifeng = request.getParameter("jifeng");
		String pageId = request.getParameter("pageId");
		String code = request.getParameter("code");
		String mobile = request.getParameter("mobile");
		String psw = request.getParameter("psw");
		try{
			
			boolean isPass = TokenUtil.checkToken(request, "jifen_tran");
			String newToken = TokenUtil.putToken(request, "jifen_tran");
			jsonObject.put("token", newToken);
			if(isPass == false){
				jsonObject.put("result", 10);
				
				return;
			}
			// 输入转让积分
			if (StringUtils.isBlank(jifeng) ) {
				jsonObject.put("result", 1);
				return;
			}
	
			// 验证码是否正确
			String sessionCode = (String) request.getSession().getAttribute(pageId + "_checkCode");
			if (StringUtils.isBlank(code) || !code.equals(sessionCode)) {
				jsonObject.put("result", 2);
				return;
			}
	
			//目标用户查找by mobile
			if (StringUtils.isBlank(mobile) ) {//接受用户手机号不能为空
				jsonObject.put("result", 5);
				return;
			}
			Map<String, Object> parameterMap = new HashMap<String, Object>();
			parameterMap.put("userName", mobile);
			UserDo targetUser = userService.getByUserDo(parameterMap);
			if(null == targetUser){//找不到对应的接受用户
				jsonObject.put("result", 6);
				return;
			}
			
			//出让用户
			Long userId = this.getUserId(request);
			UserDo sourceUser = userService.getById(userId);
			if(null == sourceUser){//找不到对应的接受用户
				jsonObject.put("result", 7);
				return;
			}
			
			//判断二级密码
			String twoPwd = sourceUser.getTwoLevelPwd();
			String pwdMd5 = DigestUtils.md5Hex(psw + WebConstants.PASS_KEY);
			if(!pwdMd5.equals(twoPwd)){
				jsonObject.put("result", 11);
				return;
			}
			
			//判断是否上下级, 16级之内
			boolean isParent = false;
			UserDo targetU = userService.getAllParent(targetUser.getId(), 16);
			for(int i = 0 ; i < 16;i++){
				if(targetU.getReferrerId().longValue() ==  sourceUser.getId().longValue()){
					isParent=true;
					break;
				}
				targetU = targetU.getParentDo();
			}
			
			if(isParent == false){
				targetU = userService.getAllParent(sourceUser.getId(), 16);
				for(int i = 0 ; i < 16;i++){
					if(targetU.getReferrerId().longValue() ==  targetUser.getId().longValue()){
						isParent=true;
						break;
					}
					targetU = targetU.getParentDo();
				}
			}
			
			if(isParent == false){
				jsonObject.put("result", 8);
				return;
			}
			//end 判断是否上下级
			
			// 获取转让用户userId
			parameterMap.clear();
			//incomeLst.clear();
			parameterMap.put("userId", userId);
			parameterMap.put("accountType", WebConstants.JIFEN_ACCOUNT);
			List<BizUserAccountDo>  incomeLst = bizUserAccountService.selectUserAccount(parameterMap);
			if(CollectionUtils.isEmpty(incomeLst)){
				jsonObject.put("result", 4);
				return;
			}
			
			//大小比较
			BigDecimal jifengAmt = BigDecimal.ZERO;
			try{
				jifengAmt = new BigDecimal(jifeng);
			}catch(Exception e){
				
			}
			if(BigDecimal.ZERO.compareTo(jifengAmt)>= 0 ){
				jsonObject.put("result", 4);
				return;
			}
			
			
			BizUserAccountDo accIncome = incomeLst.get(0);
			BigDecimal point = accIncome.getAmount()==null? BigDecimal.ZERO:  accIncome.getAmount();
			if(point.compareTo(jifengAmt)<0){
				jsonObject.put("result", 4);
				return;
			}
			
			/**
			 * 积分转让
			 */
			bizUserAccountService.transJifeng(userId, targetUser.getId(),jifengAmt);
			
			// 转让成功
			jsonObject.put("result", 0);
		
		}catch (Exception e) {
			jsonObject.put("result", 3);
			e.printStackTrace();
		}finally{
			ResponseUtils.renderText(response, "UTF-8", jsonObject.toString());
		}
		
		
	}
	
	/**
	 * 用户退本
	 * 
	 * @return
	 */
	@RequestMapping("doTeamAmount")
	public void doTeam(HttpServletRequest request,HttpServletResponse response,
			Model model) {
		logger.info("----用户退本----");
		List<UserDo> userList = userDao.getAllUser(null);
		for (UserDo userDo : userList) {
			
			System.out.println("userid======" + userDo.getId());
			long refferId = userDo.getReferrerId();
			BigDecimal amount = bizUserAccountService.getAccountTypeAmount(WebConstants.SECURITY_ACCOUNT, userDo.getId());
			giftTeamAmount(refferId,amount.multiply(new BigDecimal("10")), userList);
		}
		
		JSONObject jsonObject = new JSONObject();
	
			jsonObject.put("resultCode", 0);
	
			jsonObject.put("result", "返写成功");
			
		
		ResponseUtils.renderText(response, "UTF-8", jsonObject.toString());
		
	}

	//团队业绩汇总
	public void giftTeamAmount(long refferId, BigDecimal amount,List<UserDo> userList) {
				// 增加用户证券
				BizUserAccountDo bizUserAccountDo = new BizUserAccountDo();
				bizUserAccountDo.setAmount(amount);
				bizUserAccountDo.setUserId(refferId);
				bizUserAccountDo.setAccountType(WebConstants.TEAM_ACCOUNT);
				bizUserAccountService.updateUserAmountById(bizUserAccountDo, AccountMsg.type_11);

				// 给上级添加团队业绩
				UserDo userDo = null;

				
				for (UserDo parentDo : userList) {
					if(parentDo.getId().intValue() == (int)refferId){
						userDo =parentDo;
						break;
					}
					
				}
				if (null == userDo) {
					return;
				}
				Long parentId = userDo.getReferrerId();
				if (null != parentId && 0 != parentId.intValue()) {
					giftTeamAmount(parentId, amount,userList);
				}

			}
		
	
}

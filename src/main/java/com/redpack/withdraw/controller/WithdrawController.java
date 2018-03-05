/*
 * Powered By  huangzl QQ: 272950754
 * Web Site:  
 * Since 2008 - 2016
 */

package com.redpack.withdraw.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.redpack.account.faced.IBizUserAccountService;
import com.redpack.account.model.UserDo;
import com.redpack.base.controller.BaseController;
import com.redpack.base.controller.TokenUtil;
import com.redpack.constant.WebConstants;
import com.redpack.order.IOrderService;
import com.redpack.order.model.OrderDo;
import com.redpack.withdraw.IWithdrawService;
import com.redpack.withdraw.model.WithdrawDo;
import com.redpack.utils.DateUtil;
import com.redpack.utils.ResponseUtils;

@Controller
@RequestMapping(value = "/withdraw")
public class WithdrawController extends BaseController {

	private final Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private IWithdrawService withdrawService;

	@Autowired
	private IOrderService orderService;
	
	@Autowired
	private IBizUserAccountService bizUserAccountService;

	/**
	 * 新增或修改
	 * 
	 * @throws ParseException
	 * 
	 */
	@RequestMapping("/save")
	public void saveWithdraw(@ModelAttribute WithdrawDo withdrawDo, Model model, HttpSession session,
			HttpServletRequest request, HttpServletResponse response) {

		String mobilePhone = request.getParameter("mobile");
		String mobileCode = request.getParameter("mobileCode");
		Object vc = session.getAttribute(mobilePhone);
		String verifyCode = null;
		if (vc != null) {
			verifyCode = String.valueOf(vc);
		}
		
		JSONObject jsonObject = new JSONObject();
		//判断星期几
//		int dateInt = DateUtil.getDayOfMonth(new Date());
//		if( 18 != dateInt&&19 != dateInt && 29 != dateInt){
//			jsonObject.put("result", "只能19号，29号才能提现");
//			jsonObject.put("success", false);
//			ResponseUtils.renderText(response, "UTF-8", JSONObject.fromObject(jsonObject).toString());
//			return;
//		}
		
		
		if(withdrawDo.getAmount().doubleValue()%(double)20 != 0){
			jsonObject.put("result", "提现金额必须是20的倍数");
			jsonObject.put("success", false);
			ResponseUtils.renderText(response, "UTF-8", JSONObject.fromObject(jsonObject).toString());
			return;
		}
		
		if(withdrawDo.getAmount().compareTo(BigDecimal.ZERO)<=0){
			jsonObject.put("result", "提现金额必须必须是正数");
			jsonObject.put("success", false);
			ResponseUtils.renderText(response, "UTF-8", JSONObject.fromObject(jsonObject).toString());
			return;
		}

		
//		String bankName = request.getParameter("bankName");
//		String bankNo = request.getParameter("bankNo");
//		String bankUser = request.getParameter("bankUser");
		String passwordTwo = request.getParameter("passwordTwo");
		UserDo user = (UserDo) session.getAttribute(WebConstants.SESSION_USER);
		// 测试环境注释验证码
//		JSONObject jsonObject = new JSONObject();
				
		String userPasswordTwo = user.getTwoLevelPwd();
		String pwdMd5 = DigestUtils.md5Hex(passwordTwo + WebConstants.PASS_KEY);
		if (!pwdMd5.equals(userPasswordTwo)) {
			jsonObject.put("result", "二级密码错误,请重新输入");
			jsonObject.put("success", false);
			ResponseUtils.renderText(response, "UTF-8", JSONObject.fromObject(jsonObject).toString());
			return;
		}
		
		String[] payType = {WebConstants.RMB_ACCOUNT};
		Boolean canpay = bizUserAccountService.canPay(payType, withdrawDo.getAmount(), user.getId());
		if (!canpay) {
			jsonObject.put("result", "用户提现金额不足");
			jsonObject.put("success", false);
			ResponseUtils.renderText(response, "UTF-8", JSONObject.fromObject(jsonObject).toString());
			return;
		}
		
		//判断是否有激活豆
		BigDecimal petAmt = bizUserAccountService.getAccountTypeAmount(WebConstants.SECURITY_ACCOUNT, user.getId());
		if(petAmt.compareTo(BigDecimal.ZERO)<=0){
			jsonObject.put("result", "用户提现失败");
			logger.error("userId:"+user.getId()+"提现时没有找到激活豆，限制提现");
			jsonObject.put("success", false);
			ResponseUtils.renderText(response, "UTF-8", JSONObject.fromObject(jsonObject).toString());
			return;
		}
		
		boolean checkToken = TokenUtil.checkToken(request, TokenUtil.BIZ_CODE_RECHANGE);
		if(!checkToken){
//			model.addAttribute("errorMsg", "请勿重复提交");
			jsonObject.put("result", "请勿重复提交");
			jsonObject.put("success", false);
			ResponseUtils.renderText(response, "UTF-8", JSONObject.fromObject(jsonObject).toString());
			return;
		}
		
		withdrawDo.setUserId(user.getId());
		// 限制一天提现一次
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("userId", user.getId());
		List<WithdrawDo> withdrawLst = withdrawService.selectWithdraw(parameterMap);
		if (withdrawLst != null && withdrawLst.size() > 0) {
			try {
				Date today = DateUtil.getCurrentDate();
				for (WithdrawDo wd : withdrawLst) {
					if (wd.getCreateTime().getTime() >= today.getTime()) {
						jsonObject.put("result", "一天只能提现一次");
						jsonObject.put("success", false);
						ResponseUtils.renderText(response, "UTF-8", JSONObject.fromObject(jsonObject).toString());
						return;
					}
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
	

		
		withdrawService.addWithdraw(withdrawDo);
		jsonObject.put("result", "提现成功");
		jsonObject.put("success", true);
		ResponseUtils.renderText(response, "UTF-8", JSONObject.fromObject(jsonObject).toString());

		return;
	}
	
	
	/**
	 * 提交奖金豆提现
	 * 
	 * @throws ParseException
	 * 
	 */
	@RequestMapping("/saveJjd")
	public void saveJjd(@ModelAttribute WithdrawDo withdrawDo, Model model, HttpSession session,
			HttpServletRequest request, HttpServletResponse response) {

		String mobilePhone = request.getParameter("mobile");
		String mobileCode = request.getParameter("mobileCode");
		Object vc = session.getAttribute(mobilePhone);
		String verifyCode = null;
		if (vc != null) {
			verifyCode = String.valueOf(vc);
		}
		
		JSONObject jsonObject = new JSONObject();
		//判断星期几
//		int dateInt = DateUtil.getDayOfWeek();
//		if( 6 == dateInt || 7 == dateInt){
//			jsonObject.put("result", "星期五、星期六暂不能提现");
//			jsonObject.put("success", false);
//			ResponseUtils.renderText(response, "UTF-8", JSONObject.fromObject(jsonObject).toString());
//			return;
//		}
		
		
		if(true){
			jsonObject.put("result", "每月15号才能提现");
			jsonObject.put("success", false);
			ResponseUtils.renderText(response, "UTF-8", JSONObject.fromObject(jsonObject).toString());
			return;
		}
		


		
//		String bankName = request.getParameter("bankName");
//		String bankNo = request.getParameter("bankNo");
//		String bankUser = request.getParameter("bankUser");
		String passwordTwo = request.getParameter("passwordTwo");
		UserDo user = (UserDo) session.getAttribute(WebConstants.SESSION_USER);
		// 测试环境注释验证码
//		JSONObject jsonObject = new JSONObject();
				
		String userPasswordTwo = user.getTwoLevelPwd();
		String pwdMd5 = DigestUtils.md5Hex(passwordTwo + WebConstants.PASS_KEY);
		if (!pwdMd5.equals(userPasswordTwo)) {
			jsonObject.put("result", "二级密码错误,请重新输入");
			jsonObject.put("success", false);
			ResponseUtils.renderText(response, "UTF-8", JSONObject.fromObject(jsonObject).toString());
			return;
		}
		
		String[] payType = {WebConstants.RMB_ACCOUNT};
		Boolean canpay = bizUserAccountService.canPay(payType, withdrawDo.getAmount(), user.getId());
		if (!canpay) {
			jsonObject.put("result", "用户提现金额不足");
			jsonObject.put("success", false);
			ResponseUtils.renderText(response, "UTF-8", JSONObject.fromObject(jsonObject).toString());
			return;
		}
		
		//判断是否有激活豆
		BigDecimal petAmt = bizUserAccountService.getAccountTypeAmount(WebConstants.SECURITY_ACCOUNT, user.getId());
		if(petAmt.compareTo(BigDecimal.ZERO)<=0){
			jsonObject.put("result", "用户提现失败");
			logger.error("userId:"+user.getId()+"提现时没有找到激活豆，限制提现");
			jsonObject.put("success", false);
			ResponseUtils.renderText(response, "UTF-8", JSONObject.fromObject(jsonObject).toString());
			return;
		}
		
		boolean checkToken = TokenUtil.checkToken(request, TokenUtil.BIZ_CODE_RECHANGE);
		if(!checkToken){
//			model.addAttribute("errorMsg", "请勿重复提交");
			jsonObject.put("result", "请勿重复提交");
			jsonObject.put("success", false);
			ResponseUtils.renderText(response, "UTF-8", JSONObject.fromObject(jsonObject).toString());
			return;
		}
		
		withdrawDo.setUserId(user.getId());
		// 限制一天提现一次
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("userId", user.getId());
		List<WithdrawDo> withdrawLst = withdrawService.selectWithdraw(parameterMap);
		if (withdrawLst != null && withdrawLst.size() > 0) {
			try {
				Date today = DateUtil.getCurrentDate();
				for (WithdrawDo wd : withdrawLst) {
					if (wd.getCreateTime().getTime() >= today.getTime()) {
						jsonObject.put("result", "一天只能提现一次");
						jsonObject.put("success", false);
						ResponseUtils.renderText(response, "UTF-8", JSONObject.fromObject(jsonObject).toString());
						return;
					}
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
	

		
		withdrawService.addWithdraw(withdrawDo);
		jsonObject.put("result", "提现成功");
		jsonObject.put("success", true);
		ResponseUtils.renderText(response, "UTF-8", JSONObject.fromObject(jsonObject).toString());

		return;
	}
	

	/**
	 * 用户提现列表
	 * 
	 */
	@RequestMapping("/userWithdraw")
	public String userWithdraw(Model model, HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject();
		UserDo user = (UserDo) session.getAttribute(WebConstants.SESSION_USER);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", user.getId());
		List<WithdrawDo> withdrawList = withdrawService.selectWithdraw(paramMap);
		model.addAttribute("withdrawList", withdrawList);
		return "member/withdrawDetail";
	}

	/**
	 * 用户提现列表
	 * 
	 */
	@RequestMapping("/chongzhiDetail")
	public String chongzhiDetail(Model model, HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject();
		UserDo user = (UserDo) session.getAttribute(WebConstants.SESSION_USER);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", user.getId());
		paramMap.put("orderType", 0);
		List<OrderDo> orderList = orderService.selectOrder(paramMap);
		model.addAttribute("orderList", orderList);
		return "member/chongzhiDetail";
	}

	/**
	 * 删除
	 * 
	 */
	public String deleteWithdraw() {
		return "";
	}

	/**
	 * 查找
	 * 
	 * @return
	 */
	public String findById() {
		return "";
	}

	/**
	 * 根据条件查找
	 * 
	 * @return
	 */
	public String query() {
		return "";
	}
	
	public static void main(String[] args) {
		System.out.println( DateUtil.getDayOfMonth(new Date()));
	}

}

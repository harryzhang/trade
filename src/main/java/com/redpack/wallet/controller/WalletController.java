package com.redpack.wallet.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.redpack.account.model.UserDo;
import com.redpack.base.controller.BaseController;
import com.redpack.base.exception.BusinessException;
import com.redpack.base.result.IResult;
import com.redpack.constant.WebConstants;
import com.redpack.wallet.IWalletService;
import com.redpack.utils.ResponseUtils;

/**
 * 收款，付款管理
 * @author: zhangyunhua
 * @date 2015年3月5日 上午11:01:51
 */
@Controller
@RequestMapping("/wallet")
public class WalletController extends BaseController {
	
	private static final Logger logger = Logger.getLogger(WalletController.class);
	
	@Autowired
	IWalletService walletService;
	/**
	 * 跳转付款页面
	 * 
	 * @return
	 * @author: zhangyunhua
	 * @date 2015年8月2日 22:45:08
	 */
	@RequestMapping("listFk")
	public String listFk(HttpServletRequest request,HttpServletResponse response,Model model) {
		logger.info("----跳转付款页面----");
		//model.addAttribute("pwdFlag", pwdFlag);
		List<Map> walletList = walletService.selectUserFk(getUserId(request),null);
		model.addAttribute("walletList", walletList);
		return "wallet/listFk";
	}
	
	
	
	/**
	 * 确认收款
	 * @return
	 */
	@RequestMapping("confirmSK")
	public void confirmSK(HttpServletRequest request,String password,String newPassword,Model model, 
			HttpSession session,HttpServletResponse response) {
		logger.info("----确认收款----");
		UserDo user = (UserDo) session.getAttribute(WebConstants.SESSION_USER);
		String recordIdStr = request.getParameter("recordId"); 
		
		//result json
		JSONObject jsonObject = new JSONObject();
		
		//valid parameter
		if(StringUtils.isBlank(recordIdStr)){
			jsonObject.put("result", "参数无效,请重试"); 
			jsonObject.put("resultCode", "1"); 
			ResponseUtils.renderText(response, "UTF-8", jsonObject.toString());
			return;
		}
		
		long recordId = Long.valueOf(recordIdStr);
		
		try{
			IResult result = walletService.confirmSK(user.getId(), recordId);	
			if(result == null ){
				jsonObject.put("result", "网络异常"); 
				jsonObject.put("resultCode", "1"); 
				ResponseUtils.renderText(response, "UTF-8", jsonObject.toString());
				return;
			}
			
			if(! result.isSuccess()){
				jsonObject.put("result", "网络异常"+ result.getErrorMessage()); 
				jsonObject.put("resultCode", "1"); 
				ResponseUtils.renderText(response, "UTF-8", jsonObject.toString());
				return;
			}
		}catch(BusinessException be){			
			jsonObject.put("result", be.getMessage()); 
			jsonObject.put("resultCode", "1");
			ResponseUtils.renderText(response, "UTF-8", jsonObject.toString());
			return;
		}catch(Exception e){
			jsonObject.put("result", "网络异常"); 
			jsonObject.put("resultCode", "1");
			ResponseUtils.renderText(response, "UTF-8", jsonObject.toString());
			return;
		}
					 
		
		jsonObject.put("result", "成功"); 
		jsonObject.put("resultCode", "0"); 
		ResponseUtils.renderText(response, "UTF-8", jsonObject.toString());
	}
	
	

	/**
	 * 确认 付款
	 * @return
	 */
	@RequestMapping("confirmFK")
	public void confirmFK(Model model, 
							HttpSession session,
							HttpServletResponse response,
							HttpServletRequest request) {
		logger.info("----确认收款----");
		UserDo user = (UserDo) session.getAttribute(WebConstants.SESSION_USER);
		String recordIdStr = request.getParameter("recordId"); 
		
		//result json
		JSONObject jsonObject = new JSONObject();
		
		//valid parameter
		if(StringUtils.isBlank(recordIdStr)){
			jsonObject.put("result", "参数无效,请重试"); 
			jsonObject.put("resultCode", "1"); 
			ResponseUtils.renderText(response, "UTF-8", jsonObject.toString());
			return;
		}
		
		long recordId = Long.valueOf(recordIdStr);
		
		IResult result = walletService.confirmFK(user.getId(), recordId);		
		
		
		if(result == null ){
			jsonObject.put("result", "网络异常"+ result.getErrorMessage()); 
			jsonObject.put("resultCode", "1"); 
			ResponseUtils.renderText(response, "UTF-8", jsonObject.toString());
			return;
		}
		
		if(! result.isSuccess()){
			jsonObject.put("result", "网络异常"+ result.getErrorMessage()); 
			jsonObject.put("resultCode", "1"); 
			ResponseUtils.renderText(response, "UTF-8", jsonObject.toString());
			return;
		}
				 
		
		jsonObject.put("result", "成功"); 
		jsonObject.put("resultCode", "0"); 
		ResponseUtils.renderText(response, "UTF-8", jsonObject.toString());
	}
	
	
	/**
	 * 处理等待的用户
	 * @return
	 */
	@RequestMapping("processWaitingUser")
	public void processWaitingUser(Model model, 
								   HttpSession session,
								   HttpServletResponse response,
								   HttpServletRequest request) {
		logger.info("----处理等待的用户----");
		UserDo user = (UserDo) session.getAttribute(WebConstants.SESSION_USER);
		String groupName = request.getParameter("groupName"); 
		
		//result json
		JSONObject jsonObject = new JSONObject();
		
		//valid parameter
		if(StringUtils.isBlank(groupName)){
			jsonObject.put("result", "参数无效,请重试"); 
			jsonObject.put("resultCode", "1"); 
			ResponseUtils.renderText(response, "UTF-8", jsonObject.toString());
			return;
		}
		
		try{
			walletService.processWaitingUser(groupName);
		}catch(Throwable e){
			jsonObject.put("result", "网络异常"); 
			jsonObject.put("resultCode", "1"); 
			ResponseUtils.renderText(response, "UTF-8", jsonObject.toString());
			e.printStackTrace();
			return;
			
		}
		
		jsonObject.put("result", "成功"); 
		jsonObject.put("resultCode", "0"); 
		ResponseUtils.renderText(response, "UTF-8", jsonObject.toString());
	}
	
	/**
	 * 跳转收款页面
	 * 
	 * @return
	 * @author: zhangyunhua
	 * @date 2015年8月2日 22:45:08
	 */
	@RequestMapping("listSk")
	public String listSk(HttpServletRequest request,HttpServletResponse response,Model model) {
		logger.info("----跳转收款页面----");
		//model.addAttribute("pwdFlag", pwdFlag);
		List<Map> walletList = walletService.selectUserSk(getUserId(request),null);
		model.addAttribute("walletList", walletList);
		return "wallet/listSk";
	}

}

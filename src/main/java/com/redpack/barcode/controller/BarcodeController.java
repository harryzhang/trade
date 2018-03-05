package com.redpack.barcode.controller;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.redpack.account.faced.IUserService;
import com.redpack.account.model.UserDo;
import com.redpack.base.controller.BaseController;
import com.redpack.constant.WebConstants;
import com.redpack.utils.QRCodeUtil;

	

/**
 * 
 * @author zhangyunhmf
 *
 */
@Controller
@RequestMapping(value = "/barcode")
public class BarcodeController extends BaseController {
	private static final Logger logger = Logger.getLogger(BarcodeController.class);

	 private static final String GIF = "image/gif;charset=GB2312";// 设定输出的类型  
	  
	 private static final String JPG = "image/jpeg;charset=GB2312";
	    
	@Autowired
	private IUserService userService;
	
	/**
	 * 退出登录
	 * 
	 * @param request
	 * @param response
	 * @author: zhanbmf
	 * @throws IOException 
	 * @date 2015-3-31 下午3:36:21
	 */
	@RequestMapping(value = "mybarcode")
	public void mybarcode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		//获取访问参数
		String openId = request.getParameter("openId");
		String mobile = request.getParameter("mobile");
		String netWork = request.getParameter("mobile");
		String userId = request.getParameter("userId");
		
		
		//优先考虑session里当前用户做参数
		UserDo user = null;
		Object sessionUser = request.getSession().getAttribute(WebConstants.SESSION_USER);
		if(sessionUser != null){
			user = (UserDo)sessionUser;
			int count = userService.getRefUserCount(user.getId());
			request.getSession().setAttribute("refUser", count);
		}
	
		
		
		System.out.println("=======================================openId="+openId);
		
		//根据参数读用户信息
		
		//获取访问地址
		//String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName();
		String contextPath = request.getContextPath();
		int port = request.getServerPort();
		if(!"".equals(contextPath)&&!"/".equals(contextPath)){
			basePath = basePath+contextPath;
		} 
		
		if(port == 80){
			basePath = basePath+"/account/regIndex.html?mobile="+mobile+"&netWork="+(netWork==null?"A":netWork);
		}else{
			basePath = basePath+":"+port+"/account/regIndex.html?mobile="+mobile+"&netWork="+(netWork==null?"A":netWork);
		}
		

		//二维码内容
		String barcodeContent = basePath;
		response.setContentType(JPG);// 设定输出的类型  
		OutputStream responseStream = response.getOutputStream();		
		QRCodeUtil.encodePR(barcodeContent, 0, 10, responseStream);
		responseStream.flush();
		responseStream.close();
	}
	
	/**
	 * 
	 * 
	 * @param request
	 * @param response
	 * @author: zhanbmf
	 * @date 2015-3-31 下午3:36:21
	 */
	@RequestMapping(value = "toMybarcode")
	public String toMybarcode(HttpServletRequest request, HttpServletResponse response,Model model) {
		String mobile = request.getParameter("mobile");
		String netWork = request.getParameter("netWork");
		model.addAttribute("refMobile", mobile);
		model.addAttribute("netWork", netWork);
		
		String basePath = request.getScheme()+"://"+request.getServerName();
		String contextPath = request.getContextPath();
		int port = request.getServerPort();
		if(!"".equals(contextPath)&&!"/".equals(contextPath)){
			basePath = basePath+contextPath;
		} 
		
		if(port == 80){
			basePath = basePath+"/account/regIndex.html?mobile="+mobile+"&netWork="+(netWork==null?"A":netWork);
		}else{
			basePath = basePath+":"+port+"/account/regIndex.html?mobile="+mobile+"&netWork="+(netWork==null?"A":netWork);
		}
		model.addAttribute("myBarcode", basePath);
		
		UserDo user = null;
		Object sessionUser = request.getSession().getAttribute(WebConstants.SESSION_USER);
		if(sessionUser != null){
			user = (UserDo)sessionUser;
			int count = userService.getRefUserCount(user.getId());
			model.addAttribute("refUser", count);
		}
	
		return getLocalPath(request,"barcode/mybarcode");
	}
	
}

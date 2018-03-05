package com.redpack.weixin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.redpack.base.controller.BaseController;
import com.redpack.customer.controller.CustomerController;

/**
 * 微信公众号页面
 * 功能说明：
 * @author baishui
 * @2016年3月16日 下午1:49:13
 *
 * @com.redpack.web.view.weixin.controller.WeixinPageController.java
 */
@Controller
@RequestMapping("/page")
public class WeixinPageController  extends BaseController {
	private static final Logger logger = Logger
			.getLogger(WeixinPageController.class);
	
	/**
	 * 跳转介绍页面
	 * 
	 */
	@RequestMapping("wxPageNo")
	public String insteadConfirm(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		//从右到左菜单算起1 2 3 树型菜单4 3 21
		String pageNo = request.getParameter("pageNo");
		String url = "wxPage/wx" + pageNo;
		
		return url;
	}
}

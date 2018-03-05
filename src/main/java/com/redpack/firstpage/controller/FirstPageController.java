package com.redpack.firstpage.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.redpack.base.controller.BaseController;


@Controller
@RequestMapping(value = "/firstpage")
public class FirstPageController extends BaseController {
	
	
	private static final Logger logger = Logger.getLogger(FirstPageController.class);
	
	/**
	 * 登录入口
	 * 
	 * @return
	 * @author: zhangyunhua
	 * @date 2015-3-29 上午3:36:11
	 */
	@RequestMapping(value = "/toFirstpage")
	public String toFirstpage(HttpServletRequest request) {
		logger.debug("----FirstPageController.tofirstpage;----");
		return getLocalPath(request,"firstpage/firstpage");
	}
	@RequestMapping(value = "/zhidu")
	public String toZhidu(HttpServletRequest request) {
		logger.debug("----FirstPageController.tofirstpage;----");
		return getLocalPath(request,"firstpage/about");
	}
	

}

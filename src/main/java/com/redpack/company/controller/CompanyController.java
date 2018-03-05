package com.redpack.company.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.redpack.base.controller.BaseController;
import com.redpack.goods.IGoodsService;
import com.redpack.goods.model.CartDo;
import com.redpack.goods.model.GoodsDo;
import com.redpack.utils.ResponseUtils;

@Controller
@RequestMapping("/company")
public class CompanyController extends BaseController {

	private static final Logger logger = Logger.getLogger(CompanyController.class);
	

	/**
	 * 关于我们
	 * 
	 * @return
	 * @author: zhangyunhua
	 * @date 2015-3-29 上午3:36:11
	 */
	@RequestMapping(value = "/aboutUs")
	public String aboutUs(HttpServletRequest request) {
		logger.debug("----CompanyController.aboutUs;----");
		return getLocalPath(request,"company/aboutUs");
	}

	/**
	 * 制度
	 * 
	 * @return
	 * @author: zhangyunhua
	 * @date 2015-3-29 上午3:36:11
	 */
	@RequestMapping(value = "/zhidu")
	public String zhidu(HttpServletRequest request) {
		logger.debug("----CompanyController.aboutUs;----");
		return  getLocalPath(request,"company/zhidu");
	}
	
	/**
	 * 制度
	 * 
	 * @return
	 * @author: zhangyunhua
	 * @date 2015-3-29 上午3:36:11
	 */
	@RequestMapping(value = "/baozhang")
	public String baozhang(HttpServletRequest request) {
		logger.debug("----CompanyController.baozhang;----");
		return  getLocalPath(request,"company/baozhang");
	}
	
	
	/**
	 * 客服
	 * 
	 * @return
	 * @author: zhangyunhua
	 * @date 2015-3-29 上午3:36:11
	 */
	@RequestMapping(value = "/mykefu")
	public String mykefu(HttpServletRequest request) {
		logger.debug("----CompanyController.mykefu;----");
		return  getLocalPath(request,"company/mykefu");
	}
	

}

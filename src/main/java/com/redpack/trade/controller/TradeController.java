/**   
* @Title: TradeController.java 
* @Package com.redpack.web.view.trade.controller 
* @Description: TODO(用一句话描述该文件做什么) 
* @author zhangyunhmf
* @date 2018年3月3日 下午12:16:36 
* @version V1.0   
*/
package com.redpack.trade.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/** 
 * @ClassName: TradeController 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhangyunhmf
 * @date 2018年3月3日 下午12:16:37 
 *  
 */
@Controller
@RequestMapping(value = "/trade")
public class TradeController {

	private final Logger logger = Logger.getLogger(this.getClass());
	
	
	/**
	 * @version 创建时间：2015-7-26 下午06:22:07
	 * @param model
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping("/trade")
	public String index(Model model, HttpSession session, HttpServletRequest request) {
		return "trade/trade";
	}
	
	/**
	 * @version 创建时间：2015-7-26 下午06:22:07
	 * @param model
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping("/guadang")
	public String guadang(Model model, HttpSession session, HttpServletRequest request) {
		return "trade/guadang";
	}
	
	/**
	 * @version 创建时间：2015-7-26 下午06:22:07
	 * @param model
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping("/myorder")
	public String orderlist(Model model, HttpSession session, HttpServletRequest request) {
		return "trade/myorder";
	}
	
}

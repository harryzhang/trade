/*
 * Powered By  huangzl QQ: 272950754
 * Web Site:  
 * Since 2008 - 2016
 */


package com.redpack.bet.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.redpack.base.controller.BaseController;
import com.redpack.bet.IBetWinService;

@Controller
@RequestMapping("/betWin")
public class BetWinController extends BaseController {
	
	private final Logger logger = Logger.getLogger(this.getClass());	
	@Autowired
	private IBetWinService betWinService;
	

	/**
	 * 新增或修改
	 * 
	 */
	public String saveBetWin(){
		return "";
	}
	

	
	
	/**
	 * 删除
	 * 
	 */
	public String deleteBetWin(){
	    return "";
	}
	
	/**
	 * 查找
	 * 
	 * @return
	 */
	public String findById(){
		return "";
	}
	
	/**
	 * 根据条件查找
	 * 
	 * @return
	 */
	public String query(){
		return "";
	}


}

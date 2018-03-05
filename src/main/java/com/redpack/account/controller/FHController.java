
package com.redpack.account.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.redpack.base.controller.BaseController;
import com.redpack.income.IIncomeSevice;
import com.redpack.income.IIncomeTaskAssignSevice;
import com.redpack.utils.DateUtil;
import com.redpack.utils.ResponseUtils;

/**
 * @Description 描述方法作用
 * @author huangzl QQ: 272950754
 * @date 2015年5月27日 下午5:33:55
 * @Project hehenian-lend-login
 * @Package com.hehenian.login.account
 */
@Controller
@RequestMapping(value = "/fh")
public class FHController extends BaseController {
	private static final Logger logger = Logger.getLogger(FHController.class);

	@Autowired
	private IIncomeTaskAssignSevice incomeTaskAssignSevice;
	
	private static boolean  startStatus = true; //执行开关

	

	/**
	 * @Description: 登录
	 * @param response
	 * @param user
	 * @return 1用户名或密码未输入|2验证码输入错误 |用户密码错误
	 */
	@RequestMapping(value = "fh")
	public void fh(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		
		String token = request.getParameter("token");
		logger.info("parameter:"+token);
		if(!"1qaz@WSX#EDC".equals(token)){
			return;
		}
		
		if(!startStatus){
			System.out.println("======================已 有一个手动分红job正在执行=================================");
			return;
		}
		startStatus = false;
		JSONObject jsonObject = new JSONObject();
		try{
			Date now = new Date();
			now = DateUtil.dateAddDay(now, -1);
			incomeTaskAssignSevice.execIncomeNotFeiHong(now);
		}catch(Exception e){
			System.out.println("======================手动分红出错=================================");
			e.printStackTrace();
		}finally{
			
			startStatus = true;
			System.out.println("======================手动分红结束=================================");
			// 登陆成功
			ResponseUtils.renderText(response, "UTF-8", jsonObject.toString());
		}
		
		
	}

	
}

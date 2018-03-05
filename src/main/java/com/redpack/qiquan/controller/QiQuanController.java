package com.redpack.qiquan.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.redpack.base.controller.BaseController;
import com.redpack.base.controller.TokenUtil;
import com.redpack.huilv.IHuilvService;
import com.redpack.huilv.model.HuilvDo;
import com.redpack.utils.DateUtil;
import com.redpack.utils.ResponseUtils;

@Controller
@RequestMapping("/qiquan")
public class QiQuanController extends BaseController {
	@Autowired
	private IHuilvService huilvService;
	
	private void getMaxMin(Date from, Date to,Model model) {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("fromDate", from);
		parameterMap.put("toDate",to);
		Map<String,Object> maxMinCurr = huilvService.selectMaxMin(parameterMap);
		model.addAttribute("maxMin", maxMinCurr);
	}
	
	/**
	 * 二元期权页面
	 * 
	 * @return
	 * @date 2015-3-29 上午3:36:11
	 */
	@RequestMapping(value = "/toqiquan")
	public String toqiquan(HttpServletRequest request,Model model) {
		getMaxMin(DateUtil.getYesterDay(),new Date(System.currentTimeMillis()),model);
		return getLocalPath(request,"qiquan/toqiquan");
	}
	
	


	/**
	 * 二元期权详情页面
	 * 
	 * @return
	 * @date 2015-3-29 上午3:36:11
	 */
	@RequestMapping(value = "/detail")
	public String detail(HttpServletRequest request,Model model) {
		getMaxMin(DateUtil.getYesterDay(),new Date(System.currentTimeMillis()),model);
		String token = TokenUtil.putToken(request, "qiqun");
		model.addAttribute("buyToken", token);
		model.addAttribute("buyDirect","up");
		return getLocalPath(request,"qiquan/detail");
	}
	
	
	
	/**
	 * @Description: 查询echart数据
	 * @param response
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "getHuilv")
	public void getHuilv(HttpServletRequest request, 
						 HttpServletResponse response) {
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("retCode", "0");
		try{
			Map<String, Object> parameterMap = new HashMap<String, Object>();
			parameterMap.put("fromDate", DateUtil.getYesterDay());
			parameterMap.put("toDate",System.currentTimeMillis());
			
			List<HuilvDo> huilvLst = huilvService.selectHuilv(parameterMap);
			
			List<String> xDate = new ArrayList<String>();
			List<String> yHuiLv = new ArrayList<String>();
			
			if(CollectionUtils.isNotEmpty(huilvLst)){
				for(HuilvDo hl : huilvLst){
					yHuiLv.add(hl.getCurrency().toString());
					xDate.add(DateUtil.formatDate(hl.getGetDate(),"MM/dd h:mm:ss"));
				}
			}
			
			jsonObject.put("xdate", xDate);
			jsonObject.put("yData", yHuiLv);
		}catch(Throwable e){
			jsonObject.put("retCode", "1");
			e.printStackTrace();
		}
		
		ResponseUtils.renderText(response, "UTF-8", jsonObject.toString());
	}
	
	
	/**
	 * @Description: 购买期权
	 * @param response
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "buyQiquan")
	public void buyQiquan(HttpServletRequest request, 
						 HttpServletResponse response) {
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("retCode", "0");
		try{
			if(!TokenUtil.checkToken(request, "qiqun")){
				jsonObject.put("retCode", "1");
				jsonObject.put("msg", "请不要重复提交");
				return;
			}
			
			String amtStr = request.getParameter("amount");
			String buyDirect = request.getParameter("buyDirect");
			if(StringUtils.isBlank(amtStr)){
				jsonObject.put("retCode", "1");
				jsonObject.put("msg", "购买金额不能为空");
				return;
			}
			if(StringUtils.isBlank(buyDirect)){
				jsonObject.put("retCode", "1");
				jsonObject.put("msg", "买涨买跌没有选择空");
				return;
			}
			
			double amt = Double.parseDouble(amtStr);
			Long userId = this.getUserId(request);
			
			//TODO
			//doBuy(amt,userId);
			
			jsonObject.put("msg", "购买成功");
		}catch(Throwable e){
			jsonObject.put("retCode", "1");
			jsonObject.put("msg", "购买失败");
			e.printStackTrace();
		}finally{
			String token = TokenUtil.putToken(request, "qiqun");
			jsonObject.put("buyToken", token);
			ResponseUtils.renderText(response, "UTF-8", jsonObject.toString());
		}
	}
	
}

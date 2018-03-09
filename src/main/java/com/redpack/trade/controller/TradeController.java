/**   
* @Title: TradeController.java 
* @Package com.redpack.web.view.trade.controller 
* @Description: TODO(用一句话描述该文件做什么) 
* @author zhangyunhmf
* @date 2018年3月3日 下午12:16:36 
* @version V1.0   
*/
package com.redpack.trade.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.redpack.account.faced.IUserAccountIncomeService;
import com.redpack.account.faced.IUserService;
import com.redpack.account.model.UserDo;
import com.redpack.base.controller.BaseController;
import com.redpack.base.controller.TokenUtil;
import com.redpack.base.result.IResult;
import com.redpack.base.result.ResultSupport;
import com.redpack.constant.WebConstants;
import com.redpack.order.IOrderService;
import com.redpack.order.model.OrderDo;
import com.redpack.utils.CalculateUtils;
import com.redpack.utils.DateUtil;
import com.redpack.utils.ResponseUtils;

/** 
 * @ClassName: TradeController 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhangyunhmf
 * @date 2018年3月3日 下午12:16:37 
 *  
 */
@Controller
@RequestMapping(value = "/trade")
public class TradeController extends BaseController {

	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private IOrderService orderService;
	
	@Autowired
	private IUserAccountIncomeService accountService;
	
	@Autowired
	private IUserService userService;
	
	
	/**
	 * @version 创建时间：2015-7-26 下午06:22:07
	 * @param model
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping("/trade")
	public String index(Model model, HttpSession session, HttpServletRequest request) {
		
		Map<String, Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("orderStatus", "1");
		parameterMap.put("orderType", "1");
		List<OrderDo> saleOrderLst = orderService.selectOrder(parameterMap );
		parameterMap.clear();
		parameterMap.put("orderStatus", "1");
		parameterMap.put("orderType", "2");
		List<OrderDo> buyOrderLst = orderService.selectOrder(parameterMap);
		model.addAttribute("buyBills", buyOrderLst);
		model.addAttribute("saleBills", saleOrderLst);
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
	 * 提交挂单
	 * 
	 * @return
	 * @author: zhangyunhua
	 * @date 2015-3-29 上午3:36:11
	 */
	@RequestMapping(value = "/submitguadang")
	public void  submitGuadang( HttpServletRequest request,
						   	  HttpServletResponse response,
						   	  Model model) {
		
		logger.debug("----OrderController.submitGuadang;----");
		IResult result = ResultSupport.buildResult(ResultSupport.success_code);
		try{
			
			String goodsIdParam = request.getParameter("goodsId");
			String orderType = request.getParameter("orderType");
			String pwd = request.getParameter("pwd");
			String qty = request.getParameter("qty");
			String priceParam = request.getParameter("price");
			
//			boolean checkToken = TokenUtil.checkToken(request, TokenUtil.BIZ_CODE_GOUMAI_GUQUAN);
//			
//			if(!checkToken){
//				model.addAttribute("errorMsg", "请勿重复提交");
//				return view;
//			}
			
			// 二级密码验证
			Long userId = this.getUserId(request);
			UserDo loginUser = userService.getById(userId);
			String jiaoyemm = DigestUtils.md5Hex(pwd + WebConstants.PASS_KEY);
			if (StringUtils.isBlank(pwd)  || (!loginUser.getTwoLevelPwd().equals(jiaoyemm)) ) {
				result.setErrorMessage("交易密码错误");
				result.setResultCode(ResultSupport.errorCode);
				return;
			}
						
			
			//计算账号余额
			BigDecimal price = new BigDecimal(priceParam);
			double totalMoney =CalculateUtils.mul(price.doubleValue(), new Double(qty));
			//卖单
			if("1".equals(orderType)){
				boolean canBuy = accountService.compareAmt(userId, totalMoney);
				if(!canBuy){
					result.setErrorMessage("账号余额不足");
					result.setResultCode(ResultSupport.errorCode);
					return;
				}
			}
			
			
			OrderDo newOrder = new OrderDo();
			long goodsId = Long.parseLong(goodsIdParam);
			newOrder.setCreateTime(new Date());
			newOrder.setGoodsId(goodsId);
			newOrder.setOrderStatus(1);
			newOrder.setQty(Long.valueOf(qty));
			newOrder.setPayStatus(0);
			newOrder.setUserId(userId);
			newOrder.setOrderType(Integer.valueOf(orderType));
			newOrder.setPrice(price);
			newOrder.setTotalPrice(new BigDecimal(totalMoney));
			newOrder.setOrderCode("order_"+userId+DateUtil.getDate()+DateUtil.getThree());
			orderService.addOrder(newOrder);
			
		
		}catch(Exception e){
			logger.error(e);
			result.setResultCode(ResultSupport.errorCode);
			result.setErrorMessage("挂单失败");
		}finally{
			ResponseUtils.renderText(response,null,JSONObject.fromObject(result).toString());
		}
		
		
		
		
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
		
		Map<String, Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("userId", this.getUserId(request));
		List<OrderDo> myOrderLst = orderService.selectOrder(parameterMap);
		
		if(!CollectionUtils.isEmpty(myOrderLst)){
			Collections.sort(myOrderLst, new Comparator<OrderDo>() {
				@Override
	            public int compare(OrderDo o1, OrderDo o2) {
		            return o1.getCreateTime().compareTo(o2.getCreateTime());
	            }
			});
			groupbyOrderByStatus(myOrderLst,model);
		}
		model.addAttribute("allOrderLst", myOrderLst);
		return "trade/myorder";
	}
	
	
	/**
	 * 按状态分组
	 * zhangyunhmf
	 *
	 */
    private void groupbyOrderByStatus(List<OrderDo> myOrderLst, Model model) {
    	List<OrderDo> unTradeLst = new ArrayList<OrderDo>();
    	List<OrderDo> tradingLst = new ArrayList<OrderDo>();
    	List<OrderDo> tradedLst = new ArrayList<OrderDo>();
    	
	    for(OrderDo order : myOrderLst){
	    	if(1  == order.getOrderStatus().intValue()){
	    		unTradeLst.add(order);
	    	}else if(3 == order.getOrderStatus().intValue()){
	    		tradedLst.add(order);
	    	}else{
	    		tradingLst.add(order);
	    	}
	    }
	    model.addAttribute("unTradeLst",unTradeLst);
	    model.addAttribute("tradingLst",tradingLst);
	    model.addAttribute("tradedLst",tradingLst);
	    
    }

	/**
	 * @version 创建时间：2015-7-26 下午06:22:07
	 * @param model
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping("/saveMoney")
	public String saveMoney(Model model, HttpSession session, HttpServletRequest request) {
		String newToken = TokenUtil.putToken(request, "point_rmb");
		return "trade/saveMoney";
	}
	
}

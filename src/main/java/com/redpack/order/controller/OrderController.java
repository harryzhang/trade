package com.redpack.order.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.redpack.account.faced.IUserAccountIncomeService;
import com.redpack.account.model.UserDo;
import com.redpack.base.controller.BaseController;
import com.redpack.base.controller.TokenUtil;
import com.redpack.constant.WebConstants;
import com.redpack.goods.model.CartDo;
import com.redpack.goods.model.GoodsDo;
import com.redpack.order.IOrderService;
import com.redpack.order.model.OrderDo;
import com.redpack.pay.controller.MobileCheckUtil;
import com.redpack.pay.controller.PayComponent;
import com.redpack.pay.service.ParseResult;
import com.redpack.utils.CalculateUtils;
import com.redpack.utils.DateUtil;
import com.redpack.utils.ResponseUtils;
import com.redpack.utils.SwiftpassConfig;


@Controller
@RequestMapping(value = "/order")
public class OrderController extends BaseController {

	private static final String  SESSION_CART = "mycart";
	
	private static final Logger logger = Logger.getLogger(OrderController.class);
	
	@Autowired
	private IOrderService orderService;
	
	@Autowired
	private IUserAccountIncomeService accountService;
	
	
	
	/**
	 * 商品明细
	 * 
	 * @return
	 * @author: zhangyunhua
	 * @date 2015-3-29 上午3:36:11
	 */
	@RequestMapping(value = "/select",method = RequestMethod.GET)
	public String  orderSelect( HttpServletRequest request,
						   HttpServletResponse response) {
		logger.debug("----OrderController.select;----");
		return "order/orderSelect";
	}
	
	
	
	/**
	 * 订单成功提交
	 * 
	 * @return
	 * @author: zhangyunhua
	 * @date 2015-3-29 上午3:36:11
	 */
	@RequestMapping(value = "/success",method = RequestMethod.GET)
	public String  success( HttpServletRequest request,
						   HttpServletResponse response) {
		logger.debug("----OrderController.success;----");
		return "order/success";
	}
	
	/**
	 * 订单提交失败
	 * 
	 * @return
	 * @author: zhangyunhua
	 * @date 2015-3-29 上午3:36:11
	 */
	@RequestMapping(value = "/noMoney",method = RequestMethod.GET)
	public String  noMoney( HttpServletRequest request,
						   HttpServletResponse response) {
		logger.debug("----OrderController.success;----");
		return "order/noMoney";
	}
	
	
	/**
	 * 商品明细
	 * 
	 * @return
	 * @author: zhangyunhua
	 * @date 2015-3-29 上午3:36:11
	 */
	@RequestMapping(value = "/submitOrder")
	public String  submitOrder( HttpServletRequest request,
						   	  HttpServletResponse response,
						   	  Model model) {
		
		logger.debug("----OrderController.submitOrder;----");
		String view = "pay/payError";
		try{
			
			String recIds = request.getParameter("ckgoods");
			String orderType = request.getParameter("orderType");
			CartDo cart = (CartDo)request.getSession().getAttribute(SESSION_CART);
			
			boolean checkToken = TokenUtil.checkToken(request, TokenUtil.BIZ_CODE_GOUMAI_GUQUAN);
			
			if(!checkToken){
				model.addAttribute("errorMsg", "请勿重复提交");
				return view;
			}
			
			//计算账号余额
			Long userId = this.getUserId(request);
			double totalMoney  = cart.toTal(recIds);
			if("2".equals(orderType)){
				boolean canBuy = accountService.compareAmt(userId, totalMoney);
				if(!canBuy){
					model.addAttribute("errorMsg", "账号余额不足");
					return view;
				}
			}
			
			
			UserDo currentUser = (UserDo)  request.getSession().getAttribute(WebConstants.SESSION_USER);
			
			String[] goodsIds = recIds.split(",");
			for(String id : goodsIds){
				OrderDo newOrder = new OrderDo();
				long goodsId = Long.parseLong(id);
				GoodsDo  cartGoods = cart.getGoodsById(goodsId);
				newOrder.setCreateTime(new Date());
				newOrder.setGoodsId(goodsId);
				newOrder.setOrderStatus(1);
				newOrder.setQty(Long.valueOf(cartGoods.getBuyQty()+""));
				newOrder.setPayStatus(0);
				newOrder.setUserId(userId);
				newOrder.setOrderType(Integer.valueOf(orderType));
				newOrder.setPrice(cartGoods.getPrice());
				double d=CalculateUtils.mul(cartGoods.getPrice().doubleValue(), new Double(cartGoods.getBuyQty()+""));
				newOrder.setTotalPrice(new BigDecimal(d));
				newOrder.setOrderCode("order_"+userId+DateUtil.getDate()+DateUtil.getThree());
				
				
				
				//股权购买需要充值
				if("1".equals(orderType)){
					//先保存订单
					newOrder.setOrderStatus(3);
					
//					orderService.addOrderNotBuss(newOrder);
				
					orderService.addNewOrder(newOrder);
				//冲积分	
				}else if("2".equals(orderType)){
					orderService.addOrder(newOrder);
				}
				
				return "pay/pay_success";
				
			}
		}catch(Exception e){
			e.printStackTrace();
			model.addAttribute("errorMsg", "出错了");
		}finally{
			request.getSession().removeAttribute(SESSION_CART);
		}
		
		
		return view;
		
		
	}
	
	
	
	/**
	 * 充值保存
	 * 
	 * @return
	 * @author: zhangyunhua
	 * @date 2015-3-29 上午3:36:11
	 */
	@RequestMapping(value = "/saveChongZhi")
	public String  saveChongZhi( HttpServletRequest request,
						   		HttpServletResponse response) {
		
		logger.debug("----OrderController.saveChongZhi;----");
		try{
			String amt = request.getParameter("amt");
			Long userId = this.getUserId(request);
			OrderDo newOrder = new OrderDo();
			newOrder.setCreateTime(new Date());
			newOrder.setGoodsId(1L);
			newOrder.setOrderStatus(1);
			newOrder.setQty(1L);
			newOrder.setPayStatus(0);
			newOrder.setUserId(userId);
			newOrder.setPrice(BigDecimal.ZERO);
			newOrder.setTotalPrice(new BigDecimal(amt));
			newOrder.setOrderType(0); //充值
			newOrder.setOrderCode("order_"+userId+DateUtil.getDate()+DateUtil.getThree());
			orderService.addOrder(newOrder);
		}catch(Exception e){
			e.printStackTrace();
			return "error";
		}
		
		return "order/success";
	}
	
}

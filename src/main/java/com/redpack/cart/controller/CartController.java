package com.redpack.cart.controller;

import java.math.BigDecimal;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.redpack.account.faced.IBizUserAccountService;
import com.redpack.account.faced.IUserAccountIncomeService;
import com.redpack.base.controller.BaseController;
import com.redpack.base.controller.TokenUtil;
import com.redpack.constant.WebConstants;
import com.redpack.goods.IGoodsService;
import com.redpack.goods.model.CartDo;
import com.redpack.goods.model.GoodsDo;
import com.redpack.order.IOrderService;
import com.redpack.order.service.OrderServiceImpl;
import com.redpack.param.IParamService;
import com.redpack.utils.ResponseUtils;

@Controller
@RequestMapping("/cart")
public class CartController extends BaseController {

	private static final Logger logger = Logger.getLogger(CartController.class);
	
	private static final String  SESSION_CART = "mycart";
	@Autowired
	private IGoodsService goodsService;
	
	@Autowired
	private IParamService paramService;
	
	@Autowired
	private IUserAccountIncomeService accountService;
	
	@Autowired
	private IBizUserAccountService bizUserAccountService;
	
	@Autowired
	private IOrderService orderService;

	/**
	 * 购物车
	 * 
	 * @return
	 * @author: zhangyunhua
	 * @date 2015-3-29 上午3:36:11
	 */
	@RequestMapping(value = "/cart")
	public String toCart(HttpServletRequest request) {
		logger.debug("----CartController.toCart;----");
		return getLocalPath(request,"cart/cart");
	}

	/**
	 * 添加到购物车
	 * 
	 * @return
	 * @author: zhangyunhua
	 * @date 2015-3-29 上午3:36:11
	 */
	@RequestMapping(value = "/addToCart")
	@ResponseBody
	public void addToCart(HttpServletRequest request,
			HttpServletResponse response) {
		logger.debug("----CartController.addToCart;----");
		JSONObject jsonObject = new JSONObject();
		// jsonObject.put("result", 0);
		ResponseUtils.renderText(response, null, jsonObject.toString());
	}

	/**
	 * rec_id: rec_id, _token: token, number: qty
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/changeCartPrice")
	@ResponseBody
	public void changeCartPrice(HttpServletRequest request,
			HttpServletResponse response) {
		logger.debug("----CartController.changeCartPrice;----");
		String recIdStr = request.getParameter("rec_id");
		String qtyStr = request.getParameter("number");
		String token = request.getParameter("_token");
		
		int qty = Integer.parseInt(qtyStr);
		long recId = Long.parseLong(recIdStr);
		
		//修改购买数量
		CartDo cart = (CartDo)request.getSession().getAttribute(SESSION_CART);
		GoodsDo goods= cart.getGoodsById(recId);
		goods.setBuyQty(qty);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("err_msg", "");
		jsonObject.put("rec_id", recId);
		jsonObject.put("qty", qty);
		ResponseUtils.renderText(response, null, jsonObject.toString());
	}

	/**
	 * 计算购物车总价 data: {goods: rec_ids, _token: token},
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getCartGoods")
	@ResponseBody
	public void getCartGoods(HttpServletRequest request,
			HttpServletResponse response) {
		logger.debug("----CartController.changeCartPrice;----");
		String recIds = request.getParameter("goods");
		String token = request.getParameter("_token");
		
		CartDo cart = (CartDo)request.getSession().getAttribute(SESSION_CART);
		double totalMoney = cart.toTal(recIds);
		BigDecimal money = new BigDecimal(totalMoney);
		
		
		JSONObject jsonObject = new JSONObject();
		
		//计算账号余额
		jsonObject.put("result", totalMoney);// 总额
		jsonObject.put("res", 0);// 运费
		
		
		Long userId = this.getUserId(request);
		Map<String, BigDecimal> sliptMap = orderService.splitBuyMoney("buy_account_config",money,userId);
		String payType[] = {WebConstants.PET_ACCOUNT};
		BigDecimal petAmt = sliptMap.get(WebConstants.PET_ACCOUNT);
		boolean canBuy = bizUserAccountService.canPay(payType, petAmt, userId);
		if(!canBuy){
			jsonObject.put("err_msg", "激活豆余额不足");
			ResponseUtils.renderText(response, null, jsonObject.toString());
			return;			
		}
		
		String jifengType[] = {WebConstants.JIFEN_ACCOUNT};
		BigDecimal jifengAmt = sliptMap.get(WebConstants.JIFEN_ACCOUNT);
		canBuy = bizUserAccountService.canPay(jifengType, jifengAmt, userId);
		if(!canBuy){
			jsonObject.put("err_msg", "激活积分余额不足");
			ResponseUtils.renderText(response, null, jsonObject.toString());
			return;			
		}
		
		jsonObject.put("err_msg", "");
		ResponseUtils.renderText(response, null, jsonObject.toString());
		
	}

	/**
	 * 从购物车删除某宝贝 data: {goods: rec_id, _token: token},
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/delCartGoods")
	@ResponseBody
	public void delCartGoods(HttpServletRequest request,
			HttpServletResponse response) {
		logger.debug("----CartController.changeCartPrice;----");
		String recIdStr = request.getParameter("rec_id");
		String token = request.getParameter("_token");
		
		long recId = Long.parseLong(recIdStr);
		
		//修改购买数量
		CartDo cart = (CartDo)request.getSession().getAttribute(SESSION_CART);
		cart.delGoods(recId);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("err_msg", "");
		ResponseUtils.renderText(response, null, jsonObject.toString());
	}

	/**
	 * 结算
	 * 
	 * @return
	 * @author: zhangyunhua
	 * @date 2015-3-29 上午3:36:11
	 */
	@RequestMapping(value = "/jiesuan")
	public String jiesuan(HttpServletRequest request,Model model) {
		logger.debug("----CartController.pay;----");
		GoodsDo goods = goodsService.getKuangJi();
		model.addAttribute("goods", goods);
		
		request.getSession().removeAttribute(SESSION_CART);
		
		Object cart = request.getSession().getAttribute(SESSION_CART);
		if(null == cart){
			CartDo myCart  = new CartDo();
			goods.setBuyQty(1);
			myCart.addGoods(goods);
			request.getSession().setAttribute(SESSION_CART, myCart);
		}
		return getLocalPath(request,"cart/jiesuan");
	}

	
	/**
	 * 充值
	 * 
	 * @return
	 * @author: zhangyunhua
	 * @date 2015-3-29 上午3:36:11
	 */
	@RequestMapping(value = "/chongzhi")
	public String chongzhi(HttpServletRequest request,Model model) {
		logger.debug("----CartController.chongzhi;1111----");
		
		return getLocalPath(request,"cart/chongzhi");
	}
	
	
	
	/**
	 * 去充值
	 * 
	 * @return
	 * @author: zhangyunhua
	 * @date 2015-3-29 上午3:36:11
	 */
	@RequestMapping(value = "/toChongzhi")
	public String toChongzhi(HttpServletRequest request,Model model) {
		logger.debug("----CartController.chongzhi;1111----");
		
		return getLocalPath(request,"cart/gotoPay");
	}
	
	
	
	private void preparePay(HttpServletRequest request,Model model) {
		String token = TokenUtil.putToken(request, TokenUtil.BIZ_CODE_GOUMAI_GUQUAN);
		
		String recIds = request.getParameter("ckgoods");
		CartDo cart = (CartDo)request.getSession().getAttribute(SESSION_CART);
		GoodsDo goods = null;
		if(null == cart){
			goods = goodsService.getById(Long.valueOf(recIds));
			String qty = request.getParameter("qty");
			cart  = new CartDo();
			goods.setBuyQty(Integer.valueOf(qty));
			cart.addGoods(goods);
			request.getSession().setAttribute(SESSION_CART, cart);
		}
		double totalMoney = cart.toTal(recIds);
		double totalQty = cart.toTalQty(recIds);
		
		
		model.addAttribute("totalMoney", totalMoney);
		model.addAttribute("qty", totalQty);
		model.addAttribute("recIds", recIds);
		model.addAttribute("token", token);
	}

	/**
	 * 完成
	 * 
	 * @return
	 * @author: zhangyunhua
	 * @date 2015-3-29 上午3:36:11
	 */
	@RequestMapping(value = "/pay")
	public String pay(HttpServletRequest request,Model model) {
		
		logger.debug("----CartController.pay;----");
		
		model.addAttribute("unit", "1");
		model.addAttribute("orderType", "1");
		
		this.preparePay(request, model);
		Long userId = this.getUserId(request);
		//bizUserAccountService.getAccountTypeAmount(WebConstants.PET_ACCOUNT, userId);
		
		//证券购买订单
		//拆分金额为  现金豆和积分
		double totalMoney = (Double)model.asMap().get("totalMoney");
		Map<String,BigDecimal> accAmtMap = orderService.splitBuyMoney("buy_account_config",new BigDecimal(""+totalMoney),userId);
		model.addAllAttributes(accAmtMap);
		return "cart/pay";
		
	}
	
	/**
	 * 购买积分商品
	 * 
	 * @return
	 * @author: zhangyunhua
	 * @date 2015-3-29 上午3:36:11
	 */
	@RequestMapping(value = "/payJiFeng")
	public String payJiFeng(HttpServletRequest request,Model model) {
		
		logger.debug("----CartController.payJiFeng;----");
		model.addAttribute("unit", "2");
		model.addAttribute("orderType", "2");
		this.preparePay(request, model);
		
		return "cart/pay";
		
	}

}

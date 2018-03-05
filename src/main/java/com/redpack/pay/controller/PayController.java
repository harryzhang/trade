package com.redpack.pay.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.redpack.account.faced.IBizUserAccountService;
import com.redpack.account.model.BizUserAccountDo;
import com.redpack.base.controller.BaseController;
import com.redpack.constant.AccountMsg;
import com.redpack.constant.WebConstants;
import com.redpack.order.IOrderService;
import com.redpack.order.model.OrderDo;
import com.redpack.param.IParamService;
import com.redpack.utils.DateUtil;
import com.smartpay.ops.client.PaymentResult;
import com.smartpay.ops.client.RequestOrder;
import com.smartpay.ops.client.web.MerchantClient;


@Controller
@RequestMapping("/pay")
public class PayController extends BaseController {

	private static final Logger logger = Logger.getLogger(PayController.class);
	
	private static final String PAY_KEY = "1qaz2WSX";//根据实际情况修改;
	//private static final String PAY_KEY = "zxcvbnm1";//根据实际情况修改;
	

	@Autowired
	private IOrderService orderService;
	
	@Autowired
	private IBizUserAccountService bizUserAccountService;

	@Autowired
	private IParamService paramService;

	//支付网关
	private static final String serverUrl = "https://pg.openepay.com/gateway/index.do?";
	//private static final String merchantId = "103610161205001";
	private static final String merchantId = "107004161116003";
	private static final String receiveUrl = "https://xwawken.top/pay/notifyResult.html";
	private static final String pickupUrl = "https://xwawken.top/pay/paySuccess.html";
	private static final String cert = "/usr/local/tomcat/webapps/kaiPay/ops-prod.cer";		 //生产
	
	/**
	 * 去充值
	 * 
	 * 
	 * @return
	 * @author: zhangyunhua
	 * @date 2015-3-29 上午3:36:11
	 */
	@RequestMapping(value = "/toChongzhi")
	public String toChongzhi(HttpServletRequest request, Model model) {
		logger.debug("----PayController.toChongzhi;1111----");
		System.out.println("----PayController.toChongzhi;1111----");
		return "pay/gotoPay";
	}

	/**
	 * 充值通知
	 * 
	 * @return
	 * @author: zhangyunhua
	 * @throws IOException 
	 * @date 2015-3-29 上午3:36:11
	 */
	@RequestMapping(value = "/notifyResult")
	public void notifyResult(HttpServletRequest request, HttpServletResponse resp,
			Model model) throws IOException {
		logger.debug("----PayController.notifyResult;----");
		
		Map map = new HashMap<String,Object>();
		request.setCharacterEncoding("UTF-8");
		String result = "";
		//String cert = "/opsapp/config/ops-test.cer"; //测试
		
		String key = PayController.PAY_KEY;//根据实际情况修改
		System.out.println("receive cert " + PayController.cert);
		PaymentResult paymentResult = new PaymentResult();
		paymentResult.setMerchantId(PayController.merchantId);
		System.out.println("merchantId=" + PayController.merchantId);
		paymentResult.setVersion(request.getParameter("version"));
		System.out.println("version=" + request.getParameter("version"));
		paymentResult.setLanguage(request.getParameter("language"));
		System.out.println("language=" + request.getParameter("language"));
		paymentResult.setSignType(request.getParameter("signType"));
		System.out.println("signType=" + request.getParameter("signType"));
		paymentResult.setPayType(request.getParameter("payType"));
		System.out.println("payType=" + request.getParameter("payType"));
		paymentResult.setIssuerId(request.getParameter("issuerId"));
		System.out.println("issuerId=" + request.getParameter("issuerId"));
		paymentResult.setMchtOrderId(request.getParameter("mchtOrderId"));
		System.out.println("mchtOrderId=" + request.getParameter("mchtOrderId"));
		paymentResult.setOrderNo(request.getParameter("orderNo"));
		System.out.println("orderNo=" + request.getParameter("orderNo"));
	
		paymentResult.setOrderDatetime(request.getParameter("orderDatetime"));
		System.out.println("orderDatetime=" + request.getParameter("orderDatetime"));
		paymentResult.setOrderAmount(request.getParameter("orderAmount"));
		System.out.println("orderAmount=" + request.getParameter("orderAmount"));
		paymentResult.setPayDatetime(request.getParameter("payDatetime"));
		System.out.println("payDatetime=" + request.getParameter("payDatetime"));
		paymentResult.setExt1(request.getParameter("ext1"));
		System.out.println("ext1=" + request.getParameter("ext1"));
		paymentResult.setExt2(request.getParameter("ext2"));
		System.out.println("ext2=" + request.getParameter("ext2"));
		paymentResult.setPayResult(request.getParameter("payResult"));
		System.out.println("payResult=" + request.getParameter("payResult"));
		paymentResult.setKey(key); 
		paymentResult.setSignMsg(request.getParameter("signMsg"));
		System.out.println("signMsg=" + request.getParameter("signMsg"));
		paymentResult.setCertPath(PayController.cert);
		
		map.put("orderNo",  request.getParameter("orderNo"));
	
		boolean verifyResult = paymentResult.verify();
		System.out.println("receive verifyResult :====" + verifyResult);
		if (verifyResult) {
			
			if("1".equals(paymentResult.getPayResult())){//判断订单状态 1为支付成功
				//——请根据您的业务逻辑来编写程序
				
				System.out.println("notifyResult orderNo:"+request.getParameter("orderNo")+"验证完成,支付成功" + verifyResult);
				resp.getWriter().write("success"); //请不要修改或删除
				
				Boolean payStatus = orderService.chongZhiOrder(map);
				
				
			}
		} else {
			
			resp.getWriter().write("fail");
		}
		
		
		/*try {
			req.setCharacterEncoding("utf-8");
			resp.setCharacterEncoding("utf-8");
			resp.setHeader("Content-type", "text/html;charset=UTF-8");
			String resString = XmlUtils.parseRequst(req);
			System.out.println("通知内容：" + resString);

			String respString = "fail";
			if (resString != null && !"".equals(resString)) {
				Map<String, String> map = XmlUtils.toMap(resString.getBytes(),
						"utf-8");
				String res = XmlUtils.toXml(map);
				System.out.println("通知内容：" + res);
				if (map.containsKey("sign")) {
					if (!SignUtils.checkParam(map, SwiftpassConfig.key)) {
						res = "验证签名不通过";
						respString = "fail";
					} else {
						String status = map.get("status");
						if (status != null && "0".equals(status)) {
							String result_code = map.get("result_code");
							if (result_code != null && "0".equals(result_code)) {
								Boolean payStatus = orderService
										.chongZhiOrder(map);
								// 订单处理
								// System.out.println(TestPayServlet.orderResult);
								if (payStatus) {
									respString = "success";
								}
							}
						}

					}
				}
			}
			resp.getWriter().write(respString);
		} catch (Exception e) {
			e.printStackTrace();
		}*/

		// 记录充值成功

	}
	
	
	
	/**
	 * 查询支付结果
	 * 
	 * @return
	 * @author: zhangyunhua
	 * @throws IOException 
	 * @date 2015-3-29 上午3:36:11
	 */
	@RequestMapping(value = "/payResultQuery")
	@ResponseBody
	public void payResultQuery(HttpServletRequest req, 
							   HttpServletResponse resp,
							   Model model) throws IOException {
		logger.debug("----PayController.payResultQuery----");
		String res = "0"; //0表示未支付，1表示已支付
		resp.setCharacterEncoding("utf-8");
		resp.setHeader("Content-type", "text/html;charset=UTF-8");
		try {
			req.setCharacterEncoding("utf-8");
	        String orderNo = req.getParameter("out_trade_no");
	        if(orderService.selectPayStatusByOrderNo(orderNo)){
	        	res = "1";
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.getWriter().print(res);
	}
	
	@RequestMapping(value = "/savePayOffline")
	public String savePayOffline(HttpServletRequest request,
						HttpServletResponse httpResponse, Model model) throws ServletException, IOException {
		
		logger.debug("----未在线冲值支付请示----");
		Long userId = this.getUserId(request);
		
		String payAmount = request.getParameter("payAmount");
		
		BigDecimal amount = BigDecimal.ZERO;
		if(StringUtils.isNotBlank(payAmount)){
			String eourRate = paramService.getByName(WebConstants.EOUR_RATE);
			BigDecimal rete = new BigDecimal(eourRate);
			amount = new BigDecimal(payAmount).divide(rete,2,RoundingMode.HALF_UP);
		}
		
		BizUserAccountDo bizUserAccountDo = new BizUserAccountDo(userId, amount, WebConstants.PET_ACCOUNT);
		bizUserAccountService.updateUserAmountById(bizUserAccountDo, AccountMsg.type_7);
		
		return "pay/pay_success";
	}
	
	
	
	/**
	 * 支付
	 * 
	 * @return
	 * @author: zhangyunhua
	 * @throws ServletException
	 *             , IOException IOException
	 * @date 2015-3-29 上午3:36:11
	 */
	@RequestMapping(value = "/savePay")
	public String toPay(HttpServletRequest request,
						HttpServletResponse httpResponse, Model model) throws ServletException, IOException {
		
		logger.debug("----支付请示----");
		Long userId = this.getUserId(request);
		String outTradeNo = "order_"+userId+DateUtil.getDate()+DateUtil.getThree();

		String error = "";
		try {
			request.setCharacterEncoding("UTF-8");

			//String serverUrl = request.getParameter("serverUrl");
			//String key = request.getParameter("key");
			String key = PayController.PAY_KEY;
			String version = request.getParameter("version");
			String language = request.getParameter("language");
			String inputCharset = request.getParameter("inputCharset");
			//String merchantId = request.getParameter("merchantId");
			//String merchantId = PayController.merchantId;
			//String pickupUrl = request.getParameter("pickupUrl");
			//String receiveUrl = request.getParameter("receiveUrl");
			String payType = request.getParameter("payType");
			String signType = request.getParameter("signType");
//			String orderNo = request.getParameter("orderNo");
			String orderAmount = request.getParameter("payAmount");
			BigDecimal payAmount = new BigDecimal(orderAmount).multiply(new BigDecimal(100));
			
			String orderDatetime = DateUtil.formatTime(new Date(), "yyyyMMddHHmmss");
			String orderCurrency = request.getParameter("orderCurrency");
			String orderExpireDatetime = request.getParameter("orderExpireDatetime");
			String payerTelephone = request.getParameter("payerTelephone");
			String payerEmail = request.getParameter("payerEmail");
			String payerName = request.getParameter("payerName");
			String productName = "平台冲值";
			String productId = request.getParameter("productId");
			String productNum = request.getParameter("productNum");
			String productPrice = request.getParameter("productPrice");
			String productDesc = request.getParameter("productDesc");
			String ext1 = request.getParameter("ext1");
			String ext2 = request.getParameter("ext2");
			String extTL = request.getParameter("extTL");
			String issuerId = request.getParameter("issuerId");

			String sign = "";
		
			String signSrc = "";

			RequestOrder order = new RequestOrder();
			order.setExtTL(extTL);
			order.setInputCharset(Integer.parseInt(inputCharset));
			order.setPickupUrl(PayController.pickupUrl);
			order.setReceiveUrl(PayController.receiveUrl);
			order.setVersion(version);
			order.setLanguage(Integer.parseInt(language));
			order.setSignType(Integer.parseInt(signType));
			order.setMerchantId(PayController.merchantId);
			order.setPayerName(payerName);
			order.setPayerEmail(payerEmail);
			order.setPayerTelephone(payerTelephone);
			order.setOrderNo(outTradeNo);
			order.setOrderAmount(payAmount.longValue());
			order.setOrderCurrency(orderCurrency);
			order.setOrderDatetime(orderDatetime);
			order.setOrderExpireDatetime(orderExpireDatetime);
			order.setProductName(productName);
			order.setProductPrice(Long.parseLong(productPrice));
			order.setProductNum(Integer.parseInt(productNum));
			order.setProductId(productId);
			order.setProductDesc(productDesc);

			order.setExt1(ext1);
			order.setExt2(ext2);
			order.setPayType(Integer.parseInt(payType));
			order.setIssuerId(issuerId);
			order.setKey(key);
			signSrc = new MerchantClient().generateSignSrc(order);
			sign = new MerchantClient().sign(order);
			
//			request.setAttribute("serverUrl", serverUrl);
			model.addAttribute("serverUrl", PayController.serverUrl );
			request.setAttribute("key", key);
			request.setAttribute("version", version);
			request.setAttribute("language",language);
			request.setAttribute("inputCharset",inputCharset);
			request.setAttribute("merchantId",PayController.merchantId);
			request.setAttribute("pickupUrl",PayController.pickupUrl);
			request.setAttribute("receiveUrl",PayController.receiveUrl);
			request.setAttribute("payType",payType);
			request.setAttribute("signType",signType);
			request.setAttribute("orderNo",outTradeNo);
			request.setAttribute("orderAmount",payAmount);
			request.setAttribute("viewAmount",orderAmount);
			request.setAttribute("orderDatetime",orderDatetime);
			request.setAttribute("orderCurrency",orderCurrency);
			request.setAttribute("orderExpireDatetime",orderExpireDatetime);
			request.setAttribute("payerTelephone",payerTelephone);
			request.setAttribute("payerEmail",payerEmail);
			request.setAttribute("payerName",payerName);
			request.setAttribute("productName",productName);
			request.setAttribute("productId",productId);
			request.setAttribute("productNum",productNum);
			request.setAttribute("productPrice",productPrice);
			request.setAttribute("productDesc",productDesc);
			request.setAttribute("ext1",ext1);
			request.setAttribute("ext2",ext2);
			request.setAttribute("extTL",extTL);
			request.setAttribute("sign",sign);
			request.setAttribute("signSrc",signSrc);
			request.setAttribute("issuerId",issuerId);
			beforePay(orderAmount,userId,outTradeNo);
		} catch (Exception e) {
			logger.debug(e.toString());
			error = "error";
		}
		request.setAttribute("error",error);
	
		return "kaipay/eweWithdraw-dochongchi";
	}
	
	
	/**
	 * 
	 * 独立的跳转支付
	 * 跳转支付应用
	 * 
	 * @return
	 * @author: zhangyunhua
	 * @throws ServletException
	 *             , IOException IOException
	 * @date 2015-3-29 上午3:36:11
	 */
	@RequestMapping(value = "/payNet")
	public String payNet(HttpServletRequest request,
						HttpServletResponse httpResponse, 
						Model model,
						RedirectAttributes redirectAttributes) throws ServletException, IOException {
		
		logger.debug("----支付请示----");
		Long userId = this.getUserId(request);
		String outTradeNo = "order_"+userId+DateUtil.getDate()+DateUtil.getThree();

		String error = "";
		try {
			request.setCharacterEncoding("UTF-8");

			String version = request.getParameter("version");
			String language = request.getParameter("language");
			String inputCharset = request.getParameter("inputCharset");
			String payType = request.getParameter("payType");
			String signType = request.getParameter("signType");
//			String orderNo = request.getParameter("orderNo");
			String orderAmount = request.getParameter("payAmount");
			BigDecimal payAmount = new BigDecimal(orderAmount).multiply(new BigDecimal(100));
			
			String orderDatetime = DateUtil.formatTime(new Date(), "yyyyMMddHHmmss");
			String orderCurrency = request.getParameter("orderCurrency");
			String orderExpireDatetime = request.getParameter("orderExpireDatetime");
			String payerTelephone = request.getParameter("payerTelephone");
			String payerEmail = request.getParameter("payerEmail");
			String payerName = request.getParameter("payerName");
			String productName = "平台冲值";
			String productId = request.getParameter("productId");
			String productNum = request.getParameter("productNum");
			String productPrice = request.getParameter("productPrice");
			String productDesc = request.getParameter("productDesc");
			String ext1 = request.getParameter("ext1");
			String ext2 = request.getParameter("ext2");
			String extTL = request.getParameter("extTL");
			String issuerId = request.getParameter("issuerId");

			RequestOrder order = new RequestOrder();
			order.setOrderNo(outTradeNo);
			order.setOrderAmount(payAmount.longValue());
			String checkSign =DigestUtils.md5Hex("orderAmount="+payAmount+"orderNo="+outTradeNo+"aaabbbcccddd");
			
			redirectAttributes.addAttribute("version", version);
			redirectAttributes.addAttribute("userId", userId);
			
			redirectAttributes.addAttribute("sign", checkSign);
			
			redirectAttributes.addAttribute("inputCharset", inputCharset);
			redirectAttributes.addAttribute("language", language);
			redirectAttributes.addAttribute("signType", signType);
			redirectAttributes.addAttribute("extTL", extTL);
			redirectAttributes.addAttribute("payerName", payerName);
			redirectAttributes.addAttribute("payerEmail", payerEmail);
			redirectAttributes.addAttribute("payerTelephone", payerTelephone);
			redirectAttributes.addAttribute("payerEmail", payerEmail);
			redirectAttributes.addAttribute("orderNo", outTradeNo);
			redirectAttributes.addAttribute("payAmount", payAmount);
			redirectAttributes.addAttribute("orderCurrency", orderCurrency);
			redirectAttributes.addAttribute("productPrice", productPrice);
			redirectAttributes.addAttribute("productNum", productNum);
			redirectAttributes.addAttribute("productId", productId);
			redirectAttributes.addAttribute("productDesc", productDesc);
			redirectAttributes.addAttribute("ext1", ext1);
			redirectAttributes.addAttribute("ext2", ext2);
			redirectAttributes.addAttribute("payType", payType);
			redirectAttributes.addAttribute("issuerId", issuerId);
			beforePay(orderAmount,userId,outTradeNo);
		} catch (Exception e) {
			logger.debug(e.toString());
			error = "error";
		}
		request.setAttribute("error",error);
	
		//return "kaipay/eweWithdraw-dochongchi";
		//下面是独立的跳转支付
		return "redirect:https://pay.xwawken.top/pay/showPayPage.html";
	}
	


	/**
	 * 
	 * @param request
	 * @param httpResponse
	 * @param model
	 * @throws ServletException
	 * @throws IOException
	 */
	private int beforePay(String payAmount,Long userId,String orderTradeNo)
			throws ServletException, IOException {
		logger.debug("----支付订单----");
		// 保存订单
		OrderDo newOrder = new OrderDo();
		newOrder.setCreateTime(new Date());
		newOrder.setGoodsId(1L);
		newOrder.setOrderStatus(2);
		newOrder.setQty(1l);
		newOrder.setPayStatus(0);
		newOrder.setUserId(userId);
		newOrder.setOrderType(0);
		newOrder.setPrice(new BigDecimal(payAmount));
		newOrder.setTotalPrice(new BigDecimal(payAmount));
		newOrder.setOrderCode(orderTradeNo);
		return orderService.addOrder(newOrder);
		
	}
	

	@RequestMapping(value = "/paySuccess")
	public String saveOrder(HttpServletRequest request,
			HttpServletResponse httpResponse, Model model)
			throws ServletException, IOException {
		logger.debug("----支付成功----");
		// String payInfo = request.getParameter("payAmount");

		return "pay/pay_success";
	}

	/**
	 * 同步订单
	 * 
	 * @param map
	 * @return
	 */
	public synchronized Boolean payOrderStatus(Map<String, String> map) {

		String orderNo = map.get("out_trade_no");
		Map paramMap = new HashMap<String, Object>();
		paramMap.put("orderCode", orderNo);
		List<OrderDo> orderList = orderService.selectOrder(paramMap);
		if (null == orderList || orderList.size() != 1) {
			return false;
		}
		OrderDo order = orderList.get(0);
		int status = order.getOrderStatus();
		if (status != 2) {
			return false;
		}

		// 更新用户金额

		return true;
	}

}

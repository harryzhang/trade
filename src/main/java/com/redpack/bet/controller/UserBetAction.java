/*
 * Powered By  huangzl QQ: 272950754
 * Web Site:  
 * Since 2008 - 2016
 */


package com.redpack.bet.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.redpack.Thread.ToJingCai;
import com.redpack.account.faced.IBizUserAccountService;
import com.redpack.account.faced.IUserAccountIncomeService;
import com.redpack.account.model.BizUserAccountDo;
import com.redpack.account.model.UserAccountIncomeDo;
import com.redpack.account.model.UserDo;
import com.redpack.base.controller.BaseController;
import com.redpack.bet.IJingpaiService;
import com.redpack.bet.IUserBetService;
import com.redpack.bet.model.JingpaiDo;
import com.redpack.bet.model.UserBetDo;
import com.redpack.constant.AccountMsg;
import com.redpack.constant.WebConstants;
import com.redpack.utils.DateUtil;
import com.redpack.utils.ResponseUtils;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/userBet")
public class UserBetAction  extends BaseController {
	
	private final Logger logger = Logger.getLogger(this.getClass());	
	@Autowired
	private IUserBetService userBetService;
	
	@Autowired
	private IUserAccountIncomeService accountService;
	@Autowired
	private IBizUserAccountService bizUserAccountService;
	
	@Autowired
	private IJingpaiService jingpaiService;
	

	@RequestMapping(value = "/addBet")
	@ResponseBody
	public void addBet(Model model,HttpServletRequest request,
			HttpServletResponse response) {
		logger.debug("----CartController.addToCart;----");
		UserDo currentUser = (UserDo)  request.getSession().getAttribute(WebConstants.SESSION_USER);
		long  userId = currentUser.getId();
		
    	String amount = request.getParameter("amount");
    	String betNumber =  request.getParameter("betNumber");
    	JSONObject jsonObject = new JSONObject();
    	if( StringUtils.isBlank(amount) || StringUtils.isBlank(betNumber)){
    		jsonObject.put("result", "投注失败,参数错误");
    		ResponseUtils.renderText(response, null, jsonObject.toString());
    		return;
    	}
    	
    	if( !("1".equals(betNumber)||
    			"2".equals(betNumber)||
    			"3".equals(betNumber)||
    			"4".equals(betNumber)||
    			"5".equals(betNumber)||
    			"6".equals(betNumber))    			
    			){
    		
    		jsonObject.put("result", "投注失败,参数错误");
    		ResponseUtils.renderText(response, null, jsonObject.toString());
    		return;
    	}
    	
    	

//    	Date  d = DateUtil.parseDate("2017-04-24");
//    	Date  cur = new Date();
//    	if( d.getTime()>cur.getTime()){
//    		jsonObject.put("result", "暂未开放到旧系统参加竞猜");
//    		ResponseUtils.renderText(response, null, jsonObject.toString());
//    		return;
//    	}
    	
    	BigDecimal betAmount = new BigDecimal(amount);
    	if( betAmount.compareTo(BigDecimal.ZERO) <= 0){
    		jsonObject.put("result", "投注失败,参数错误");
    		ResponseUtils.renderText(response, null, jsonObject.toString());
    		return;
    	}
    	
//    	String accType = request.getParameter("accountType");
//    	UserAccountIncomeDo userAccount = accountService.getById(userId);
    	BigDecimal accountAmount = bizUserAccountService.getAccountTypeAmount(WebConstants.RMB_ACCOUNT, userId);
    	
    	if( null == accountAmount){
    		jsonObject.put("result", "用户账户信息为空");
			ResponseUtils.renderText(response, null, jsonObject.toString());
			return;
    	}
    	//扣现金
    	
//    	BigDecimal eourAmount = userAccount.getAmount() ==null ? BigDecimal.ZERO : userAccount.getAmount();
//    	BigDecimal pointAmount = userAccount.getPoint() ==null ? BigDecimal.ZERO : userAccount.getPoint();
		if(accountAmount.compareTo(betAmount) < 0 ){
			jsonObject.put("result", "余额不足");
			ResponseUtils.renderText(response, null, jsonObject.toString());
			return;
		}
//		if("point".equals(accType) && pointAmount.compareTo(betAmount) < 0 ){
//			jsonObject.put("result", "余额不足");
//			ResponseUtils.renderText(response, null, jsonObject.toString());
//			return;
//		}
//    	
    
//    	userBetService.addBet(betAmount,userId,"rmb");
		// 增加用户证券
		BizUserAccountDo bizUserAccountDo = new BizUserAccountDo();
		bizUserAccountDo.setAmount(betAmount.negate());
		bizUserAccountDo.setUserId(userId);
		bizUserAccountDo.setAccountType(WebConstants.RMB_ACCOUNT);
		bizUserAccountService.updateUserAmountById(bizUserAccountDo, AccountMsg.type_13);

		int period = ToJingCai.period;
    	UserBetDo ubDo = new UserBetDo();
    	ubDo.setBetTime(new Date());
    	ubDo.setAmount(betAmount);
    	ubDo.setStatus(WebConstants.RMB_ACCOUNT);
    	ubDo.setUserId((int)userId);
    	ubDo.setPeriods(ToJingCai.period);
    	ubDo.setBetNumber(betNumber);
    	userBetService.addUserBet(ubDo);
    	jsonObject.put("result", "成功");
    	jsonObject.put("period", period);
    	jsonObject.put("betNumber", betNumber);
		ResponseUtils.renderText(response, null, jsonObject.toString());
		
	}
	
	
	@RequestMapping(value = "/addJingpai")
	@ResponseBody
	public void addJingpai(Model model,HttpServletRequest request,
			HttpServletResponse response) {
		logger.debug("----CartController.addToCart;----");
		UserDo currentUser = (UserDo)  request.getSession().getAttribute(WebConstants.SESSION_USER);
		long  userId = currentUser.getId();
	
		String goodsId = request.getParameter("goodsId");
		JSONObject jsonObject = new JSONObject();
		if(StringUtils.isBlank(goodsId)){
			jsonObject.put("result", "商品信息为空");
			ResponseUtils.renderText(response, null, jsonObject.toString());
			return;
		}
		Map pMap = new HashMap<String,Object>();
		pMap.put("goodsId", goodsId);
		List<JingpaiDo> jpList = jingpaiService.selectJingpai(pMap);
		BigDecimal amount = BigDecimal.ZERO;
		JingpaiDo jpDo = null;
		BigDecimal endAmount = null;
		if(jpList.size() > 0){
			jpDo = jpList.get(0);
			amount = jpDo.getLastPrice(); 
			endAmount = jpDo.getEndPrice();
		}else{
			jsonObject.put("result", "竞拍信息为空");
			ResponseUtils.renderText(response, null, jsonObject.toString());
			return;
		}
		
		if(jpDo.getStatus().intValue() == 2){
			jsonObject.put("result", "商品已被其他用户竞拍成功");
			ResponseUtils.renderText(response, null, jsonObject.toString());
			return;
		}
		
		//判断是否超60秒
		long diff = System.currentTimeMillis() - jpDo.getUserTime().getTime();
		if(diff>60*1000){
			jsonObject.put("result", "60秒前商品已被其他用户竞拍成功");
			ResponseUtils.renderText(response, null, jsonObject.toString());
			return;
		}
		
		
		if(amount.compareTo(endAmount) >=0){
			jsonObject.put("result", "竞拍已是最高价");
			ResponseUtils.renderText(response, null, jsonObject.toString());
			return;
		}
		String[] payType = {"rmb"};
		boolean canBuy	= bizUserAccountService.canPay(payType, amount, userId);
//		boolean canBuy = accountService.compareAmt(userId, amount.doubleValue());
		
		if(!canBuy){
			jsonObject.put("result", "余额不足");
			ResponseUtils.renderText(response, null, jsonObject.toString());
			return;
		}
	
		BigDecimal lastPrice = null;
		try{
			lastPrice = jingpaiService.userJingpai(jpDo,userId);
		}catch(Exception e){
			e.printStackTrace();
			
			jsonObject.put("result", "余额不足");
			jsonObject.put("name", currentUser.getName());
			ResponseUtils.renderText(response, null, jsonObject.toString());
			return;
		}
	
		jsonObject.put("result", "成功");
		jsonObject.put("lastPrice", lastPrice);
		jsonObject.put("name", currentUser.getName());
		ResponseUtils.renderText(response, null, jsonObject.toString());
		
	}

	
	public String saveUserBet(){
		return "";
	}
	

	
	
	/**
	 * 删除
	 * 
	 */
	public String deleteUserBet(){
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

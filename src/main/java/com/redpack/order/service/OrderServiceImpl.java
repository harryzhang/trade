/*
 * Powered By  huangzl QQ: 272950754
 * Web Site:  
 * Since 2008 - 2016
 */

package com.redpack.order.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.redpack.account.dao.IUserAccountDetailDao;
import com.redpack.account.dao.IUserAccountIncomeDao;
import com.redpack.account.dao.IUserDao;
import com.redpack.account.faced.IBizUserAccountService;
import com.redpack.account.faced.IKangjiBuyIncomeService;
import com.redpack.account.faced.IUserInfoService;
import com.redpack.account.model.ApplyAgentDo;
import com.redpack.account.model.BizUserAccountDo;
import com.redpack.account.model.KangjiBuyIncomeDo;
import com.redpack.account.model.UserAccountDetailDo;
import com.redpack.account.model.UserAccountIncomeDo;
import com.redpack.account.model.UserDo;
import com.redpack.account.model.UserInfoDo;
import com.redpack.base.result.IResult;
import com.redpack.base.result.ResultSupport;
import com.redpack.constant.AccountMsg;
import com.redpack.constant.WebConstants;
import com.redpack.member.dao.IMemberDao;
import com.redpack.member.model.KuangjiUserAccountDo;
import com.redpack.order.IOrderService;
import com.redpack.order.dao.IOrderDao;
import com.redpack.order.model.OrderDo;
import com.redpack.param.IParamService;
import com.redpack.pay.controller.PayUtil;
import com.redpack.utils.CalculateUtils;

/**
 * @author  huangzl QQ: 272950754
 * @version 1.0
 * @since 1.0
 */


@Service("orderService")
public class OrderServiceImpl implements IOrderService {

	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
    private IOrderDao  orderDao;
	@Autowired
    private IUserAccountIncomeDao  userAccountIncomeDao;
	
	@Autowired
	private IUserAccountDetailDao  userAccountDetailDao;
	
	@Autowired
	private IUserDao userDao;
	@Autowired
	private IMemberDao memberDao;
	
	@Autowired
	private IParamService paramService;
	
	@Autowired
	private IUserInfoService userInfoService;
	
	@Autowired
	private IBizUserAccountService bizUserAccountService;
	/**
	 * 根据ID 查询
	 * @parameter id
	 */
	@Override
	public OrderDo getById(Long id){
	  return orderDao.getById(id);
	}
	
	
	/**
	 * 根据订单号查询订单支付状态: true 已支付， false 未支付
	 */
	@Override
	public  boolean selectPayStatusByOrderNo(String orderNo){
		OrderDo order = this.selectOrderByOrderNo(orderNo);
		if(null == order) return false;
		return 1 == order.getPayStatus().intValue(); 
	}
	
	/**
	 * 根据订单号查询订单
	 */
	@Override
	public  OrderDo selectOrderByOrderNo(String orderNo){
		
		if(StringUtils.isBlank(orderNo))return null;
		
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("orderCode", orderNo);
		List<OrderDo> orderList = orderDao.selectOrder(paramMap);
		if(CollectionUtils.isNotEmpty(orderList)){
			return orderList.get(0);
		}
		return null;
	}
	
	
	/**
	 *根据条件查询列表
	 */
	@Override
	public List<OrderDo> selectOrder(Map<String,Object> parameterMap){
		return orderDao.selectOrder(parameterMap);
	}
	
	/**
	 *积分订单查询
	 */
	public List<OrderDo> selectPointOrder(Map<String,Object> parameterMap){
		return orderDao.selectPointOrder(parameterMap);
	}
	/**
	 * 更新
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public int updateOrderById(OrderDo newOrderDo){
		logger.debug("updateOrder(OrderDo: "+newOrderDo);
		return orderDao.updateOrderById(newOrderDo);
	}
	
	/**
	 * 新增
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public int addOrder(OrderDo newOrderDo){
		logger.debug("addOrder: "+newOrderDo);
		
		return orderDao.addOrder(newOrderDo);
	}
	
	/**
	 * 删除
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public int deleteById(Long id){
		logger.debug("deleteByIdOrder:"+id);
		return orderDao.deleteById(id);
	}
	
	
	/**
	 * 用户上级推荐提成 
	 * 1：直推奖 2：管理奖  3：报单中心  4：区域奖  
	 * @param userDo
	 */
	private void updateParentIncome(int level,UserDo userDo, BigDecimal amount,long kjQty) {
		long refferId = userDo.getReferrerId();
		if( refferId != 0){
			
			UserDo oneUserDo = userDao.getById(refferId);
			//1:推荐人，直推奖, 消费奖
			if( null != oneUserDo){
				BigDecimal refferRate = paramService.getRateByName(WebConstants.REFFER_RATE);
				BigDecimal addMount = amount.multiply(refferRate);
				//增加推荐人账户金额
				String message ="子用户" + userDo.getName() + "买股直推奖,";
				updateRefferAmount(level, refferId, addMount,101, 201, kjQty,message);
				
//				BigDecimal comsumeRate = paramService.getRateByName(WebConstants.COMSUME_RATE);
//				BigDecimal comsumeMount = amount.multiply(comsumeRate);
//				//增加推荐人账户金额
//				updateRefferAmount(level, refferId, comsumeMount,23, 9, kjQty,userDo);
				
			}
			
			//2:管理奖
			if( null != oneUserDo){
				BigDecimal manageRate = paramService.getRateByName(WebConstants.MANAGE_RATE);
				BigDecimal addMount = amount.multiply(manageRate);
				String message ="代子用户" + userDo.getName() + "买股管理奖,";
				managerRate(level, refferId, addMount, 202, kjQty,message);
				
			}
			
			//3:总监、董事中心提成
			if( null != oneUserDo){
				
				String message = "用户" + userDo.getName() + "买股";
				centerRate(level, refferId, amount, 203, kjQty,message);
				
			}
			
			//4:省市代理提成
			if( null != oneUserDo){
				BigDecimal provinceRate = paramService.getRateByName(WebConstants.PROVINCE_RATE);
				BigDecimal cityRate = paramService.getRateByName(WebConstants.CITY_RATE);
				
				
				UserInfoDo userInfoDo = userInfoService.getByUserId(userDo.getId());
				//用户资料
				if( null != userInfoDo){
					String userProvince = userInfoDo.getProvince();
					String userCity = userInfoDo.getCity();
					
					//省代提成
					if(StringUtils.isNotBlank(userProvince) && !"-1".equals(userProvince)){
						//获取省代理推荐奖
						BigDecimal addMount = amount.multiply(provinceRate);
						Map<String,Object> paramMap = new HashMap<String,Object>();
						paramMap.put("agentType", "province");
						paramMap.put("province", userProvince);
						paramMap.put("status", "T");
						List<ApplyAgentDo> applyAgentList = userDao.getAreaAgentUser(paramMap);
						if( null != applyAgentList && applyAgentList.size() >0 ){
							ApplyAgentDo applyAgent = applyAgentList.get(0) ;
							long provinceId = applyAgent.getUserId();
							String message = "用户" + userDo.getName() + "省代奖,";
							updateRefferAmount(0, provinceId, addMount, 104,204, kjQty,message);
						}
					}
					
					//市代提成
					if(StringUtils.isNotBlank(userCity) && !"-1".equals(userCity)){
						//获取市代理推荐奖
						BigDecimal addMount = amount.multiply(cityRate);
						Map<String,Object> paramCityMap = new HashMap<String,Object>();
						paramCityMap.put("agentType", "city");
						paramCityMap.put("city", userCity);
						paramCityMap.put("status", "T");
						List<ApplyAgentDo> applyAgentList = userDao.getAreaAgentUser(paramCityMap);
						if( null != applyAgentList && applyAgentList.size() >0 ){
							ApplyAgentDo applyAgent = applyAgentList.get(0) ;
							long provinceId = applyAgent.getUserId();
							String message = "用户" + userDo.getName() + "市代奖,";
							updateRefferAmount(0, provinceId, addMount,105, 205, kjQty,message);
						}
					}
				}
			}
			
			//5 增加团队业绩
			if( null != oneUserDo){
				teamAmount(refferId, amount);
			}
			
			//报单中心奖
			String centerUserId = userDo.getTreeNode();
			if(StringUtils.isNotBlank(centerUserId)){
				long centerId = Long.valueOf(centerUserId);
//				UserDo centeruser = userDao.getById(centerId);
				BigDecimal centerRate = paramService.getRateByName(WebConstants.REPORT_CENTER_RATE);
				BigDecimal addAmount = amount.multiply(centerRate).setScale(2);
				String msg = userDo.getName() + "买股报单中心奖,";
				updateRefferAmount(level, centerId, addAmount, 133,233, kjQty,msg);
			}
		
			
			
			
		}
	}
	


	//5代管理直线奖
	public void managerRate(int level,Long refferId, BigDecimal addAmount,
			int incomeType, long kjQty,String message){
		UserDo userDo = userDao.getById(refferId);
		if(null == userDo){
			return;
		}
		
		//父推荐人数超过几个才能拿几代推荐最多五代
		Long countId = userDo.getParentId();
		if( countId >= level){
			String newMes = level + message;
			updateRefferAmount(level, refferId, addAmount,102, incomeType, kjQty,newMes);
		}
		
		//向上直推5代
		Long parentId = userDo.getReferrerId();
		if(0 != parentId && level < 6){
			int parentlevel  = level + 1;
			managerRate(parentlevel, parentId, addAmount, incomeType, kjQty,message);
		}
		
	}
	
	// 报单中心奖
	public void centerRate(int level,Long refferId, BigDecimal amount,
			int incomeType, long kjQty,String message){
		UserDo userDo = userDao.getById(refferId);
		if(null == userDo){
			return;
		}
		//用户级别为总监 董事才能拿
		String enabled = userDo.getEnabled();
		String organ = userDo.getOrgan();
		if( "1".equals(enabled)){
			BigDecimal centerRate = paramService.getRateByName(WebConstants.CENTER_RATE);
			BigDecimal addAmount = amount.multiply(centerRate).setScale(2);
			String msg = message + "总监奖,";
			updateRefferAmount(level, refferId, addAmount, 103,incomeType, kjQty,msg);
		}
		if( "2".equals(enabled)){
			BigDecimal centerRate = paramService.getRateByName(WebConstants.DONG_CENTER_RATE);
			BigDecimal addAmount = amount.multiply(centerRate).setScale(2);
			String msg = message + "董事奖,";
			updateRefferAmount(level, refferId, addAmount, 103,incomeType, kjQty,msg);
		}
		
		//报单中心无限拿
		long parentId = userDo.getReferrerId();
		if( 0 != parentId){
			int parentlevel  = level + 1;
			centerRate(parentlevel, parentId, amount, incomeType, kjQty,message);
		}
		
	}
	
	//更新用户帐户金额
	public void updateRefferAmount(int level,Long refferId, BigDecimal addAmount,
			int amountType,int pointType, long kjQty,String message) {

		BigDecimal pointAmount = addAmount;
		BigDecimal eourAmount = BigDecimal.ZERO;
		//动态奖分成比例
		String manageEourPointRate = paramService.getByName(WebConstants.MANAGE_EOUR_POINT_RATE);
		if(StringUtils.isNotBlank(manageEourPointRate)){
			String[] rates = manageEourPointRate.split(":");
			if(rates.length >1 ){
				eourAmount = amountRate(addAmount,rates[0]);
				eourAmount.setScale(2);
				pointAmount = addAmount.subtract(eourAmount);
				pointAmount.setScale(2);
			}
		}
		
		// 更新用户帐户金额
		KuangjiUserAccountDo userAccount = new KuangjiUserAccountDo();
		userAccount.setUserId(refferId);
		userAccount.setAmount(eourAmount);
		userAccount.setPoint(pointAmount);
		userAccount.setPoingTotalAmount(addAmount);
		userAccount.setRecommendTotalAmount(addAmount);
		userAccount.setUpdateTime(new Date());
		memberDao.updateAmountById(userAccount);

		// 增加积分消费明细
		UserAccountDetailDo uaDetail = new UserAccountDetailDo();
		uaDetail.setAmount(pointAmount);
		uaDetail.setCreateTime(new Date());
		uaDetail.setIncomeType(pointType);
		uaDetail.setMoreOrLess(message + "积分：" + pointAmount );
		uaDetail.setUserId(refferId);
		userAccountDetailDao.addUserAccountDetail(uaDetail);
		
		// 增加金币消费明细
		UserAccountDetailDo uaAmountDetail = new UserAccountDetailDo();
		uaAmountDetail.setAmount(eourAmount);
		uaAmountDetail.setCreateTime(new Date());
		uaAmountDetail.setIncomeType(amountType);
		uaAmountDetail.setMoreOrLess(message + "金币：" +  eourAmount);
		uaAmountDetail.setUserId(refferId);
		userAccountDetailDao.addUserAccountDetail(uaAmountDetail);

		// 增加消费项目提成表
//		KangjiBuyIncomeDo kjBuyIncomeDo = new KangjiBuyIncomeDo();
//		kjBuyIncomeDo.setUserId(refferId);
//		kjBuyIncomeDo.setCreateTime(new Date());
//		kjBuyIncomeDo.setGrade(level);
//		kjBuyIncomeDo.setIncome(addAmount);
//		kjBuyIncomeDo.setKangjiQty((int) kjQty);
//		kangjiBuyIncomeService.addKangjiBuyIncome(kjBuyIncomeDo);

	}
	
	//团队业绩汇总
	private void teamAmount(long refferId, BigDecimal amount) {
		//更新用户业绩金额
		KuangjiUserAccountDo userAccount = new KuangjiUserAccountDo();
		userAccount.setUserId(refferId);
		userAccount.setTeamAmount(amount);
		userAccount.setUpdateTime(new Date());
		memberDao.updateAmountById(userAccount);
		
		//给上级添加团队业绩 
		UserDo userDo = userDao.getById(refferId);
		if(null == userDo){
			return;
		}
		Long parentId = userDo.getReferrerId();
		if( null != parentId && 0 != parentId.intValue() ){
			teamAmount(parentId,amount );
		}
		
	}

	//求出金额的百分比
	public BigDecimal amountRate(BigDecimal amount, String rate){
		BigDecimal returnAmount = BigDecimal.ZERO;
		BigDecimal rateBig = new BigDecimal(rate);
		BigDecimal oneRate = new BigDecimal("100");
		
		return amount.multiply(rateBig).divide(oneRate, 2, RoundingMode.HALF_UP);		
	}

	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public synchronized Boolean chongZhiOrder(Map<String, String> map) {
		// TODO Auto-generated method stub

		String orderNo = map.get("orderNo");
		Map paramMap = new HashMap<String, Object>();
		paramMap.put("orderCode", orderNo);
		List<OrderDo> orderList = orderDao.selectOrder(paramMap);
		if (null == orderList || orderList.size() != 1) {
			return false;
		}
		OrderDo order = orderList.get(0);
		int status = order.getOrderStatus();
		int payStatus = order.getPayStatus();
		if(1== payStatus){
			return true;
		}
		
		//修改充值后订单,购买交易结构已发生变化
		
		if( 2 == status ){
			// 欧元汇率
			String eourRate = paramService.getByName(WebConstants.EOUR_RATE);
			BigDecimal eourBig = new BigDecimal("7.5");
			if (StringUtils.isNotBlank(eourRate)) {
				eourBig = new BigDecimal(eourRate);
			}
			BigDecimal totalPrice = order.getTotalPrice();
			// 金币金额 = 充值金额/ 7.5 /100 
			BigDecimal amount = totalPrice.divide(eourBig, 2, RoundingMode.HALF_UP);
			//测试 充多少是多少
//			BigDecimal amount = totalPrice;
			long userId = order.getUserId();
			
			BizUserAccountDo bizUserAccountDo = new BizUserAccountDo(userId, amount, WebConstants.PET_ACCOUNT);
			bizUserAccountService.updateUserAmountById(bizUserAccountDo, AccountMsg.type_10);
			
			order.setOrderStatus(1);
			order.setPayStatus(1);
			order.setPayTime(new Date());
			orderDao.updateOrderById(order);
			
		}
		
		order.setPayStatus(1);
		orderDao.updateOrderById(order);
		return true;
	}

	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public JSONObject addOrderNotBuss(OrderDo newOrderDo) throws IOException {
		// TODO Auto-generated method stub
		//冻结用户金币
		Long userId = newOrderDo.getUserId();
		UserAccountIncomeDo userAccount = 	 userAccountIncomeDao.getById(userId);
		BigDecimal accountAmount = userAccount.getAmount();
		BigDecimal accountPoint = userAccount.getPoint();
		//需付款金额
		BigDecimal payAmount = newOrderDo.getTotalPrice().divide(new BigDecimal("2"), 2,RoundingMode.HALF_UP);
		
		//各种积分　金币扣款金额
		BigDecimal amount = BigDecimal.ZERO;
		BigDecimal point = BigDecimal.ZERO;
		BigDecimal chongzhi = BigDecimal.ZERO;
		
		
		if(accountAmount.compareTo(payAmount) >=0){
			amount = payAmount;
			chongzhi = payAmount;
		}else{
			amount = accountAmount;
			//需付余额
			BigDecimal levelAmount = payAmount.subtract(accountAmount);
			//积分够
			if(accountPoint.compareTo(levelAmount) >=0){
				point = levelAmount;
				chongzhi = payAmount;
			}else{
				//积分不够现金凑数
				 point = accountPoint;
				 chongzhi = payAmount.add(levelAmount.subtract(accountPoint));
			}
			
		}
		String eourRate = paramService.getByName(WebConstants.EOUR_RATE);
		BigDecimal eourBig = new BigDecimal("7.5");
		if (StringUtils.isNotBlank(eourRate)) {
			eourBig = new BigDecimal(eourRate);
		}
		chongzhi = chongzhi.multiply(eourBig);
		JSONObject jsonObject = new JSONObject();
//				PayUtil.getWeiXinPayInfo(chongzhi.toString(), newOrderDo.getOrderCode(), "股权购买",openId);
//		String res = (String) jsonObject.get("res");
//		//汇率
//		
//		jsonObject.put("rate", eourRate);
//		
//		if ("ok".equals(res)) {
			//减少用户金额
			KuangjiUserAccountDo userAccountUpdate = new KuangjiUserAccountDo();
			userAccountUpdate.setUserId(userId);
			userAccountUpdate.setAmount(BigDecimal.ZERO.subtract(amount));
			userAccountUpdate.setPoint(BigDecimal.ZERO.subtract(point));
			userAccount.setUpdateTime(new Date());
			memberDao.updateAmountById(userAccountUpdate);
			
			// 增加金币、积分消费明细
			UserAccountDetailDo uaDetail = new UserAccountDetailDo();
			uaDetail.setAmount(amount);
			uaDetail.setCreateTime(new Date());
			uaDetail.setIncomeType(2);
			uaDetail.setMoreOrLess("-");
			uaDetail.setUserId(userId);
			userAccountDetailDao.addUserAccountDetail(uaDetail);
			
			uaDetail = new UserAccountDetailDo();
			uaDetail.setAmount(point);
			uaDetail.setCreateTime(new Date());
			uaDetail.setIncomeType(1);
			uaDetail.setMoreOrLess("-");
			uaDetail.setUserId(userId);
			userAccountDetailDao.addUserAccountDetail(uaDetail);
			
			
			newOrderDo.setOrderStatus(3);
			orderDao.addOrder(newOrderDo);
//		}else{
//			jsonObject.put("err_msg", "出错了");
//		}
		
		return jsonObject;
				
	}
	
	/**
	 * 购买股权的时候计算支付金额
	 * @param newOrderDo
	 * @param openId
	 * @return
	 * @throws IOException
	 */
	public BigDecimal getPayAmount(OrderDo newOrderDo) {
		//冻结用户金币
		Long userId = newOrderDo.getUserId();
		UserAccountIncomeDo userAccount = 	 userAccountIncomeDao.getById(userId);
		final BigDecimal accountAmount = userAccount.getAmount();
		final BigDecimal accountPoint = userAccount.getPoint();
		
		//需付款金额， 订单金额一半的充值金额
		final BigDecimal payAmount = newOrderDo.getTotalPrice().divide(new BigDecimal("2"), 2,RoundingMode.HALF_UP);
		
		//需充值金额
		BigDecimal chongzhi = payAmount;
		
		//查找汇率
		String eourRate = paramService.getByName(WebConstants.EOUR_RATE);
		BigDecimal eourBig = new BigDecimal("7.5");
		if (StringUtils.isNotBlank(eourRate)) {
			eourBig = new BigDecimal(eourRate);
		}
		
		/***************计算充值金额 开始**********************/
		
		//现金账号余额足够， 支付订单一半的现金
		if(accountAmount.compareTo(chongzhi) >=0){
			return chongzhi.multiply(eourBig);
		}
		
		
		//现金账户金额不够，积分扣抵
		//扣除现金账户余额后，不足部分
		BigDecimal levelAmount = chongzhi.subtract(accountAmount);
		//积分够，
		if(accountPoint.compareTo(levelAmount) >=0){
			return chongzhi.multiply(eourBig);
		}
		
		//积分不够，去冲现金
		chongzhi = chongzhi.add(levelAmount.subtract(accountPoint));
		return chongzhi.multiply(eourBig);
		/***************计算充值金额 结束**********************/
	}
	
	/**
	 * 新增
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public int addNewOrder(OrderDo newOrderDo){
		logger.debug("addOrder: "+newOrderDo);
		BigDecimal amount = newOrderDo.getTotalPrice();
		BigDecimal qty = new BigDecimal(newOrderDo.getQty());
		
		Map<String, BigDecimal> buyAccAmtMap = this.splitBuyMoney("buy_account_config", amount,newOrderDo.getUserId());
		for(Map.Entry<String, BigDecimal> entry : buyAccAmtMap.entrySet()){
			//扣用户金额
			BizUserAccountDo userAcctTemp = new BizUserAccountDo();
			userAcctTemp.setUserId(newOrderDo.getUserId());
			userAcctTemp.setAmount(entry.getValue().negate());
			userAcctTemp.setAccountType(entry.getKey());
			bizUserAccountService.updateUserAmountById(userAcctTemp, AccountMsg.type_1);
		}
		
		//增加用户证券
		BizUserAccountDo bizUserAccountDo = new BizUserAccountDo();
		bizUserAccountDo.setUserId(newOrderDo.getUserId());
		bizUserAccountDo.setAccountType(WebConstants.SECURITY_ACCOUNT);
		bizUserAccountDo.setAmount(qty);
		bizUserAccountService.updateUserAmountById(bizUserAccountDo, AccountMsg.type_2);
		
		//判断用户上级等级
		Long userid = newOrderDo.getUserId();
		UserDo user = userDao.getById(userid);
		if( null != user ){
			//获取推荐人
			Long refferId = user.getReferrerId();
			
			if( null != refferId){
				UserDo refferUser = userDao.getById(refferId);
			
				//更新上级用户等级
				bizUserAccountService.updateUserGrade(refferUser,amount);
				
				//增加团队总金额
				giftTeamAmount(refferId,amount);
			}
			
		}
		
		
		
		return orderDao.addOrder(newOrderDo);
	}
	
	/**
	 * 购买证券各个账户金额比例
	 * map key :rmbAmt, jiFenAmt
	 */
	@Override
	public Map<String, BigDecimal> splitBuyMoney(String string, BigDecimal totalMoney,Long userId) {
		String buyAccConfig = paramService.getByName("buy_account_config");
		Map<String, BigDecimal> retMap = new HashMap<String,BigDecimal>();
		String[] accts = buyAccConfig.split(";");
		
		BigDecimal lastAmt = totalMoney;
		
		for (int k = 0 ; k<accts.length-1 ;k++) {
			String str = accts[k];
			String[] acct = str.split("=");
			if (acct.length < 1) {
				continue;
			}
			BigDecimal acctPercent = BigDecimal.ZERO;
			if(StringUtils.isNotBlank(acct[1])){
				acctPercent = new BigDecimal(acct[1]);
			}
			 
			
			BigDecimal ret = acctPercent.multiply(totalMoney).setScale(2, BigDecimal.ROUND_HALF_UP);
			lastAmt = lastAmt.subtract(ret);
			retMap.put(acct[0], ret);
		}
		
		//最后一项用减法
		String str = accts[accts.length-1];
		String[] acct = str.split("=");
		if (acct.length < 1) {
			throw new RuntimeException("证券购买配置错误");
		}
		lastAmt.setScale(2, BigDecimal.ROUND_HALF_UP);
		retMap.put(acct[0], lastAmt);
		
		//判断积分的额度是否足够， 不够的时候用现金补充
		/*
		BigDecimal jifen = bizUserAccountService.getAccountTypeAmount(WebConstants.JIFEN_ACCOUNT, userId);
		BigDecimal buyJifen = retMap.get(WebConstants.JIFEN_ACCOUNT);
		if(buyJifen != null && buyJifen.compareTo(BigDecimal.ZERO) > 0){
			if(jifen.compareTo(buyJifen)<0){
				retMap.put(WebConstants.JIFEN_ACCOUNT, jifen);
				double pet = CalculateUtils.sub(totalMoney.doubleValue(), jifen.doubleValue());
				retMap.put(WebConstants.PET_ACCOUNT, new BigDecimal(pet));
			}
		}
		*/
		return retMap;
	}
	
	//团队业绩汇总
	public void giftTeamAmount(long refferId, BigDecimal amount) {
		// 增加用户证券
		BizUserAccountDo bizUserAccountDo = new BizUserAccountDo();
		bizUserAccountDo.setAmount(amount);
		bizUserAccountDo.setUserId(refferId);
		bizUserAccountDo.setAccountType(WebConstants.TEAM_ACCOUNT);
		bizUserAccountService.updateUserAmountById(bizUserAccountDo, AccountMsg.type_11);

		// 给上级添加团队业绩
		UserDo userDo = userDao.getById(refferId);
		if (null == userDo) {
			return;
		}
		Long parentId = userDo.getReferrerId();
		if (null != parentId && 0 != parentId.intValue()) {
			giftTeamAmount(parentId, amount);
		}

	}


	/**
	 * 
	 * zhangyunhmf
	 *
	 * @see com.redpack.order.IOrderService#matchOrder(com.redpack.order.model.OrderDo)
	 *
	 */
    @Override
    public void matchOrder(OrderDo newOrder) {
	    this.addOrder(newOrder);
	    Map<String,Object> paraMap = new HashMap<String,Object>();
	    paraMap.put("newStatus", 2);
	    paraMap.put("oldStatus", 1);
	    paraMap.put("matchOrderId", newOrder.getOrderId());
	    paraMap.put("orderId", newOrder.getMatchOrderId());
	    //更改匹配的订单状态为交易中
	    this.orderDao.updateOrderStatusByOldStatus(paraMap);	    
    }


	/**
	 * 确认收款和付款
	 * @see com.redpack.order.IOrderService#confirmOrder(java.lang.Long, java.lang.Long)
	 *
	 */
    @Override
    public void confirmOrder(Long userId, Long orderId) {
	    
    	OrderDo confirmOrder = this.getById(orderId);
    	if(confirmOrder.getUserId().longValue() != userId.longValue()){
    		throw new RuntimeException("非法操作");
    	}
    	
    	Map<String,Object> paraMap = new HashMap<String,Object>();
    	paraMap.put("orderId", orderId);
    	paraMap.put("orderStatus", 2);
    	paraMap.put("newPayStatus", 1);
    	paraMap.put("oldPayStatus", 0);
		this.orderDao.updateOrderPayStatus(paraMap );

		OrderDo matchOrder = this.getById(confirmOrder.getMatchOrderId());
    	if(matchOrder.getPayStatus().intValue() == 1){//已确认支付
    		paraMap.clear();
		    paraMap.put("newStatus", 3);
		    paraMap.put("oldStatus", 2);
		    paraMap.put("orderId", orderId);
		    //更改匹配的订单状态为完成
		    this.orderDao.updateOrderStatusByOldStatus(paraMap);
		    paraMap.clear();
		    paraMap.put("newStatus", 3);
		    paraMap.put("oldStatus", 2);
		    paraMap.put("orderId", matchOrder.getOrderId());
		    //更改匹配的订单状态为完成
		    this.orderDao.updateOrderStatusByOldStatus(paraMap);
		    
		    Long targetUserId = null;
		    Long sourceUserId = null;
		    if(2 == confirmOrder.getOrderType().intValue()){
		    	targetUserId = confirmOrder.getUserId();
		    	sourceUserId = matchOrder.getUserId();
		    }else if (1 == confirmOrder.getOrderType().intValue()){
		    	sourceUserId = confirmOrder.getUserId();
		    	targetUserId = matchOrder.getUserId();
		    }
    		bizUserAccountService.convertBetweenAccount(sourceUserId, 
    				                                    targetUserId, 
    				                                    new BigDecimal(confirmOrder.getQty()), 
    				                                    WebConstants.RMB_ACCOUNT,
    				                                    WebConstants.RMB_ACCOUNT,
    				                                    AccountMsg.type_36,
    				                                    AccountMsg.type_37);
    	}
    	
    	
    }


	
}

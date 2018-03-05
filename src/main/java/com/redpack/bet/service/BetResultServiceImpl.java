/*
 * Powered By  huangzl QQ: 272950754
 * Web Site:  
 * Since 2008 - 2016
 */

package com.redpack.bet.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.redpack.account.dao.IUserAccountDetailDao;
import com.redpack.account.dao.IUserAccountIncomeDao;
import com.redpack.account.faced.IBizUserAccountService;
import com.redpack.account.model.BizUserAccountDo;
import com.redpack.account.model.UserAccountDetailDo;
import com.redpack.account.model.UserAccountIncomeDo;
import com.redpack.base.result.IResult;
import com.redpack.base.result.ResultSupport;
import com.redpack.bet.IBetResultService;
import com.redpack.bet.dao.IBetResultDao;
import com.redpack.bet.dao.IBetWinDao;
import com.redpack.bet.dao.IUserBetDao;
import com.redpack.bet.model.BetResultDo;
import com.redpack.bet.model.BetWinDo;
import com.redpack.bet.model.UserBetDo;
import com.redpack.constant.AccountMsg;
import com.redpack.constant.WebConstants;
import com.redpack.member.dao.IMemberDao;
import com.redpack.member.model.KuangjiUserAccountDo;

/**
 * @author  huangzl QQ: 272950754
 * @version 1.0
 * @since 1.0
 */


@Service("betResultService")
public class BetResultServiceImpl implements IBetResultService {

	private final Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private IBizUserAccountService bizUserAccountService;
	@Autowired
    private IBetResultDao  betResultDao;
	@Autowired
	private IUserBetDao  userBetDao;
	@Autowired
	private IBetWinDao  betWinDao;
	
	@Autowired
    private IUserAccountIncomeDao  userAccountIncomeDao;
	
	@Autowired
	private IUserAccountDetailDao  userAccountDetailDao;
	
	@Autowired
	private IMemberDao memberDao;
	/**
	 * 根据ID 查询
	 * @parameter id
	 */
	@Override
	public BetResultDo getById(int id){
	  return betResultDao.getById(id);
	}
	
	/**
	 *根据条件查询列表
	 */
	@Override
	public List<BetResultDo> selectBetResult(Map<String,Object> parameterMap){
		return betResultDao.selectBetResult(parameterMap);
	}
	
	/**
	 * 更新
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public IResult<?> updateBetResultById(BetResultDo newBetResultDo){
		logger.debug("updateBetResult(BetResultDo: "+newBetResultDo);
		int i =betResultDao.updateBetResultById(newBetResultDo);
		if(i<1){
			return ResultSupport.buildResult(1, newBetResultDo+"更新失败");
		}
		IResult<Integer> result = ResultSupport.buildResult(0);
		result.setModel(Integer.valueOf(i));
		return result;
	}
	
	/**
	 * 新增
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public IResult<?> addBetResult(BetResultDo newBetResultDo){
		logger.debug("addBetResult: "+newBetResultDo);
		int i =betResultDao.addBetResult(newBetResultDo);
		if(i<1){
			return ResultSupport.buildResult(1, newBetResultDo+"新增失败");
		}
		IResult<Integer> result = ResultSupport.buildResult(0);
		result.setModel(Integer.valueOf(i));
		return result;
	}
	
	/**
	 * 删除
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public IResult<?> deleteById(int id){
		logger.debug("deleteByIdBetResult:"+id);
		int i =betResultDao.deleteById(id);
		if(i<1){
			return ResultSupport.buildResult(1, id+"删除失败");
		}
		IResult<Integer> result = ResultSupport.buildResult(0);
		result.setModel(Integer.valueOf(i));
		return result;
	}

	@Override
	public Integer getMaxPeriod() {
		// TODO Auto-generated method stub
		return betResultDao.getMaxPeriod();
	}

	@Override
	@Transactional
	public Boolean openGame(int period) {
		// TODO Auto-generated method stub
		Map paramMap = new HashMap<String,Object>();
		paramMap.put("period", period);
		
		//初始化下一轮数据
		UserBetDo ubDo = new UserBetDo();
		ubDo.setAmount(BigDecimal.ZERO);
		ubDo.setBetNumber("1");
		ubDo.setBetTime(new Date());
		ubDo.setPeriods(period+1);
		ubDo.setUserId(0);
		ubDo.setStatus("1");
		userBetDao.addUserBet(ubDo);
		ubDo.setBetNumber("2");
		userBetDao.addUserBet(ubDo);
		ubDo.setBetNumber("3");
		userBetDao.addUserBet(ubDo);
		ubDo.setBetNumber("4");
		userBetDao.addUserBet(ubDo);
		ubDo.setBetNumber("5");
		userBetDao.addUserBet(ubDo);
		ubDo.setBetNumber("6");
		userBetDao.addUserBet(ubDo);
		
		//查询当前期是否已开奖
		List<BetResultDo> breList = selectBetResult(paramMap);{
			if(breList.size() > 0 ){
				logger.info("===========当前期已开奖：" + period);
				return false;
			}
		}
		
		String winNumber = "";
		int minCount = 0;
		BigDecimal winAmount = BigDecimal.ZERO;
		List<String> winList =new ArrayList<String>();
		BetResultDo brDo = new BetResultDo();
		brDo.setPeriod(period);
	
		
		//找出中奖号码 即投注最少的
		List<Map<String,Object>> brList = userBetDao.selectSumUserBet(paramMap);
	
		for (Map<String,Object> ubMap : brList) {
			String betNumber = (String)ubMap.get("betNumber");
			BigDecimal amount =(BigDecimal)ubMap.get("amount");
			
			if("1".equals(betNumber)){
				brDo.setOneAmount(amount);
			}else if("2".equals(betNumber)){
				brDo.setTwoAmount(amount);
			}else if("3".equals(betNumber)){
				brDo.setThreeAmount(amount);
			}else if("4".equals(betNumber)){
				brDo.setFourAmount(amount);
			}else if("5".equals(betNumber)){
				brDo.setFiveAmount(amount);
			}else if("6".equals(betNumber)){
				brDo.setSixAmount(amount);
			}
			//第一个号 或者 投注金额小于第最底金额
			if(minCount == 0 || winAmount.compareTo(amount) >0){
				winNumber = betNumber;
				winAmount = amount;
				minCount = 1;
				winList.clear();
				winList.add(winNumber);
			//最底金额相等
			}else if( winAmount.compareTo(amount) == 0 ){
				minCount ++;
				//得随机一个中奖号码
				winList.add(betNumber);
			}
		}
		if( minCount > 1){
			Random rand = new Random();
			int i = rand.nextInt(minCount); //生成0-minCount以内的随机数
			winNumber = winList.get(i);
		}
		//判断是否有必中号码
		String goodNumber = userBetDao.getBetGood(period);
		if(StringUtils.isNotBlank(goodNumber)){
			winNumber = goodNumber;
		}
		
		brDo.setBetNum(winNumber);
		brDo.setBetAmount(winAmount);
		brDo.setStatus("1");
		brDo.setBetTime(new Date());
		//保存中奖号码
		betResultDao.addBetResult(brDo);
		
		//保存中奖用户
		saveBetUser(winNumber,period);
		
		
		
		return true;
	}
	
	public void saveBetUser(String betNumber, int period){
		Map paramMap = new HashMap<String,Object>();
		paramMap.put("periods", period);
		paramMap.put("betNumber", betNumber);
//		paramMap.put("status", "1");
		List<UserBetDo> ubList = userBetDao.selectUserBet(paramMap);
		
		//中奖倍数 为5 保存每个用户的中奖数
		for (UserBetDo userBetDo : ubList) {
			int userId = userBetDo.getUserId();
			BigDecimal betAmount = userBetDo.getAmount();
			BetWinDo uwDo = new BetWinDo();
			uwDo.setBetAmount(betAmount);
			uwDo.setCreateTime(new Date());
			uwDo.setPeroid(period);
			uwDo.setStatus("1");
			uwDo.setWinAmount(betAmount.multiply(new BigDecimal(5)));
			uwDo.setUserId(userId);
			betWinDao.addBetWin(uwDo);
			//增加账户金额信息
			String accType = userBetDo.getStatus();
			addUserAccount(uwDo,accType);
			
		}
	}

	
	//增加账户金额信息
	private void addUserAccount(BetWinDo uwDo, String accType) {
		// TODO Auto-generated method stub
		long userId = uwDo.getUserId();
		
//		bizUserAccountService.getAccountTypeAmount(accountType, userId)
//		UserAccountIncomeDo userAccount = userAccountIncomeDao.getById(userId);
		BigDecimal amount = uwDo.getWinAmount();
		// 增加用户证券
		BizUserAccountDo bizUserAccountDo = new BizUserAccountDo();
		bizUserAccountDo.setAmount(amount);
		bizUserAccountDo.setUserId(userId);
		bizUserAccountDo.setAccountType(WebConstants.RMB_ACCOUNT);
		bizUserAccountService.updateUserAmountById(bizUserAccountDo, AccountMsg.type_14);
//		BigDecimal addAmount = amount.multiply(new BigDecimal("0.7"));
//		addAmount.setScale(2,RoundingMode.HALF_UP);
//		BigDecimal addPoint = amount.subtract(addAmount);
//		addPoint.setScale(2);
		// 用户帐户信息为空 增加用户帐户信息
//		if (null != userAccount) {
//
//			// 给提现金币增加0.7 现金增加 0.3
//
//			KuangjiUserAccountDo accountDo = new KuangjiUserAccountDo();
//			UserAccountDetailDo uaDetail = new UserAccountDetailDo();
//			int period = uwDo.getPeroid();
//			String message = "第" + period + "期竞猜中奖,";
//			if("eour".equals(accType)){
//				accountDo.setAmount(amount);
//				accountDo.setIncomeTotalAmount(amount);
//				uaDetail.setIncomeType(120);
//				uaDetail.setMoreOrLess(message + "金币：" + amount);
//			}else{
//				accountDo.setPoint(amount);
//				accountDo.setPoingTotalAmount(amount);
//				uaDetail.setIncomeType(220);
//				uaDetail.setMoreOrLess(message + "积分：" + amount);
//			}
//			
//			
//			accountDo.setUpdateTime(new Date());
//			accountDo.setUserId(userId);
//			memberDao.updateAmountById(accountDo);
//			
//			if(null != amount && amount.compareTo(BigDecimal.ZERO) != 0 ){
//				uaDetail.setAmount(amount);
//				uaDetail.setCreateTime(new Date());
//				uaDetail.setUserId(userId);
//				userAccountDetailDao.addUserAccountDetail(uaDetail);
//			}
//		}

	}

	@Override
	public List<Map<String,Object>> selectUserResult(Map<String, Object> parameterMap) {
		// TODO Auto-generated method stub
		return betResultDao.selectUserResult(parameterMap);
	}

}

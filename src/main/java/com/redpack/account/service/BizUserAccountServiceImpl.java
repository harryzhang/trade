/**  
 * @Project: deposit-biz-service
 * @Package com.hehenian.deposit.service.account
 * @Title: UserServiceImpl.java
 * @Description: TODO
 * @author: liuzgmf
 * @date 2015年3月5日 上午10:58:12
 * @Copyright: HEHENIAN Co.,Ltd. All rights reserved.
 * @version V1.0  
 */
package com.redpack.account.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.redpack.account.dao.IBizUserAccountDao;
import com.redpack.account.dao.IUserAccountDetailDao;
import com.redpack.account.dao.IUserDao;
import com.redpack.account.faced.IBizUserAccountService;
import com.redpack.account.faced.IFeiHongService;
import com.redpack.account.faced.IUserAccountDetailService;
import com.redpack.account.faced.IUserService;
import com.redpack.account.model.BizUserAccountDo;
import com.redpack.account.model.UserAccountDetailDo;
import com.redpack.account.model.UserDo;
import com.redpack.constant.AccountMsg;
import com.redpack.constant.WebConstants;
import com.redpack.param.IParamService;
import com.redpack.utils.DateUtil;
import com.redpack.withdraw.dao.IWithdrawDao;
import com.redpack.withdraw.model.WithdrawDo;

@Service("bizUserAccountService")
public class BizUserAccountServiceImpl implements IBizUserAccountService, IFeiHongService {
	private static final Logger LOGGER = Logger.getLogger(BizUserAccountServiceImpl.class);
	@Autowired
	private IBizUserAccountDao bizUserAccountDao;
	
	@Autowired
	private IUserAccountDetailDao userAccountDetailDao;
	@Autowired
	private IParamService paramService;
	@Autowired
	private IUserDao userDao;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IWithdrawDao withdrawDao;
	@Autowired
	IUserAccountDetailService userAccountDetailService;
	
	@Override
	public BigDecimal getAccountTypeAmount(String accountType, Long userId) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("accountType", accountType);
		BizUserAccountDo udo = bizUserAccountDao.getByUserIdAndAccount(map);
		BigDecimal amount = BigDecimal.ZERO;
		if (null != udo) {
			amount = udo.getAmount();
		}

		return amount;
	}

	@Async
	@Override
	public void updateUserAmountByUserIdAccountType(List<BizUserAccountDo> bizUserAccountLst) {
		LOGGER.info("汇总job开始");
		if(null == bizUserAccountLst || bizUserAccountLst.size()==0){
			LOGGER.info("汇总job结束");
			return;
		}
		
		for(BizUserAccountDo userAcctDo : bizUserAccountLst){
			updateUserAmountByUserIdAccountType(userAcctDo);
		}
		LOGGER.info("汇总job结束");
	}
	
	public int updateUserAmountByUserIdAccountType(BizUserAccountDo bizUserAccountDo) {
		//增加用户收益、消费金额
		BigDecimal amount = bizUserAccountDo.getAmount();
		if( amount.compareTo(BigDecimal.ZERO) >= 0 ){
			bizUserAccountDo.setConsueAmount(BigDecimal.ZERO);
			bizUserAccountDo.setIncomeAmount(amount);
		}else{
			bizUserAccountDo.setIncomeAmount(BigDecimal.ZERO);
			bizUserAccountDo.setConsueAmount(amount.negate());
		}
		
		Long userId = bizUserAccountDo.getUserId();
		String accountType  = bizUserAccountDo.getAccountType();
		//判断用户是否存在此帐户 没有刚增加
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("accountType", accountType);
		BizUserAccountDo udo = bizUserAccountDao.getByUserIdAndAccount(map);
		if( null == udo ){
			bizUserAccountDao.addUserAccount(bizUserAccountDo);
		}else{
		
			bizUserAccountDao.updateUserAmountById(bizUserAccountDo);
		}
		return 0;
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public int updateUserAmountById(BizUserAccountDo bizUserAccountDo, AccountMsg type) {
		//增加用户收益、消费金额
		BigDecimal amount = bizUserAccountDo.getAmount();
		if( amount.compareTo(BigDecimal.ZERO) >= 0 ){
			bizUserAccountDo.setConsueAmount(BigDecimal.ZERO);
			bizUserAccountDo.setIncomeAmount(amount);
		}else{
			bizUserAccountDo.setIncomeAmount(BigDecimal.ZERO);
			bizUserAccountDo.setConsueAmount(amount.negate());
		}
		
		Long userId = bizUserAccountDo.getUserId();
		String accountType  = bizUserAccountDo.getAccountType();
		//判断用户是否存在此帐户 没有刚增加
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("accountType", accountType);
		BizUserAccountDo udo = bizUserAccountDao.getByUserIdAndAccount(map);
		int result = 0;
		if( null == udo && amount.compareTo(BigDecimal.ZERO) >= 0 ){
			result = bizUserAccountDao.addUserAccount(bizUserAccountDo);
		}else{
		
			result =  bizUserAccountDao.updateUserAmountById(bizUserAccountDo);
		}
		
		if(result<1){
			throw new RuntimeException("余额不足");
		}
		
		updateUserAmountDetail(amount, type, userId,accountType,bizUserAccountDo.getRemark());

		return result;
	}

	
	@Override
	public int insertFeiHongTemp(Long userId, BigDecimal resultAmt,
			String account, String calDesc, AccountMsg accountMsgType) {
		
		
		BizUserAccountDo bizUserAccountDo = new BizUserAccountDo();
		bizUserAccountDo.setUserId(userId);
		bizUserAccountDo.setAmount(resultAmt);
		bizUserAccountDo.setAccountType(account);
		bizUserAccountDo.setRemark(calDesc);
		bizUserAccountDao.insertFeiHongTemp(bizUserAccountDo);
		updateUserAmountDetail(resultAmt, accountMsgType, userId,account,calDesc);

		return 0;
	}
	
	@Override
	public boolean canPay(String[] payType, BigDecimal amount, Long userId) {
		// TODO Auto-generated method stub
		BigDecimal total = BigDecimal.ZERO;
		for (int i = 0; i < payType.length; i++) {
			String pay = payType[i];
			BigDecimal typeAmount = getAccountTypeAmount(pay, userId);
			total = total.add(typeAmount);
		}

		if (total.compareTo(amount) < 0) {
			return false;
		}

		return true;
	}
	

	//判断购买证券是否余额足够
	public boolean canPayPet(String[] payType, BigDecimal amount, Long userId) {
		BigDecimal total = BigDecimal.ZERO;
		for (int i = 0; i < payType.length; i++) {
			String pay = payType[i];
			BigDecimal typeAmount = getAccountTypeAmount(pay, userId);
			total = total.add(typeAmount);
		}

		if (total.compareTo(amount) < 0) {
			return false;
		}

		return true;
	}

	@Override
	@Transactional
	public synchronized boolean updateUserAmountByMuilt(String[] payType, BigDecimal amount, AccountMsg type, Long userId) {
		// TODO Auto-generated method stub
		BigDecimal total = BigDecimal.ZERO.subtract(amount);
		BizUserAccountDo bizUserAccountDo = new BizUserAccountDo();
		bizUserAccountDo.setUserId(userId);
		
		Boolean success = false;
		//判断付款类型付款
		for (int i = 0; i < payType.length; i++) {
			String pay = payType[i];
			bizUserAccountDo.setAccountType(pay);
			BigDecimal typeAmount = getAccountTypeAmount(pay, userId);
			
			//余额不足
			if(typeAmount.compareTo(total)<0 ){
				bizUserAccountDo.setAmount(BigDecimal.ZERO.subtract(typeAmount));
				updateUserAmountById(bizUserAccountDo,type);
				total = total.subtract(typeAmount);
			}else{
				bizUserAccountDo.setAmount(BigDecimal.ZERO.subtract(total));
				updateUserAmountById(bizUserAccountDo,type);
				success = true;
				break;
			}
		}
		if(!success){
			 new RuntimeException("用户金额不足");
		}
		
		return success;
	}


	/**
	 * 更新用户明细贪信息
	 * 
	 * @param amount
	 * @param useType
	 * @param userId
	 * @return
	 */
	public boolean updateUserAmountDetail(BigDecimal amount, AccountMsg type, Long userId,String accountType,String remark) {
		// 增加用户消费列表
		UserAccountDetailDo uaDetail = new UserAccountDetailDo();
		uaDetail.setAmount(amount);
		uaDetail.setCreateTime(new Date());
		uaDetail.setIncomeType(type.accountType);
		uaDetail.setMoreOrLess(moreOrLessStr(amount) );
		uaDetail.setAccountType(accountType );
		if(StringUtils.isBlank(remark)){
			uaDetail.setRemark(type.message + amount.toString());
		}else{
			uaDetail.setRemark(remark);
		}
		uaDetail.setUserId(userId);
		userAccountDetailDao.addUserAccountDetail(uaDetail);
		return true;
	}

	@Override
	public List<BizUserAccountDo> selectUserAccount(Map<String, Object> parameterMap) {
		return bizUserAccountDao.selectUserAccount(parameterMap);
		
	}
	
	public String moreOrLessStr(BigDecimal amount){
		String type = "+";
		if(BigDecimal.ZERO.compareTo(amount) > 0 ){
			type = "-";
		}
		
		return type;
		
	}

	@Override
	public int userUnPay(WithdrawDo withdrawDo) {
		
		long userId = withdrawDo.getUserId();
		UserDo user = userDao.getById(userId);
		
		//判断用户能否退本， 时间限制
		BigDecimal unpayRate = getUnPayRate(user);
		if(unpayRate.compareTo(BigDecimal.ZERO) == 0){
			return 1;
		}
		
		//减用户股本
		BigDecimal amount =getAccountTypeAmount(WebConstants.SECURITY_ACCOUNT, userId);
		BizUserAccountDo bizUserAccountDo = new BizUserAccountDo(userId,amount.negate(),WebConstants.SECURITY_ACCOUNT);
		updateUserAmountById(bizUserAccountDo, AccountMsg.type_8);
		
		//减上级等级
		if( null != user){
			Long refferId = user.getReferrerId();
			UserDo refferUser = userDao.getById(refferId);
			
			BigDecimal updateAmount = amount.multiply(new BigDecimal(10));
//			bizUserAccountDo = new BizUserAccountDo(user.getId(),updateAmount,WebConstants.REFFER_ACCOUNT);
//			updateUserAmountById(bizUserAccountDo, AccountMsg.type_9);

			updateUserGrade(refferUser, updateAmount.negate());
		}
		
		//写入提现列表
		//WithdrawDo withdrawDo = new WithdrawDo();
		withdrawDo.setUserId(userId);
		withdrawDo.setAmount(amount.negate());
		String withrawRateStr = paramService.getByName(WebConstants.WITHDRAW_RATE);
		BigDecimal withrawRate = new BigDecimal(withrawRateStr);
	
		
//		BigDecimal charge = amount.multiply(chargeRate)
//				.divide(new BigDecimal(100), 2, RoundingMode.HALF_UP);
		//BigDecimal dateRate = unpayRate(userId);
		//withrawRate = withrawRate.multiply(unpayRate);
		
		BigDecimal payAmount = amount.multiply(withrawRate).multiply(new BigDecimal("10"));
		payAmount = payAmount.multiply(unpayRate);
//		newWithdrawDo.setAmount(amount);
		withdrawDo.setCharge(withrawRate);
		withdrawDo.setPayAmount(payAmount);
		withdrawDo.setCreateTime(new Date());
		withdrawDo.setWithdrawStatus(2);
		
		withdrawDao.addWithdraw(withdrawDo);
		
		return 0;
	}
	
	private BigDecimal getUnPayRate(UserDo userDo) {
		int grade = userDo.getGrade()==null?0:userDo.getGrade().intValue();
		
		if(grade<2){
			return new BigDecimal("1");
		}
		
		Date startDate = userDo.getCreateTime();
		long d0 = startDate.getTime();
		long d1 = DateUtil.addMonths(startDate, 1).getTime();
		long d2 = DateUtil.addMonths(startDate, 2).getTime();
		long d3 = DateUtil.addMonths(startDate, 3).getTime();
		
		long curr = (new Date()).getTime();
		if(curr>= d0 && curr<d1){
			return  new BigDecimal("0.7");
		}else if(curr>=d1 && curr<d2){
			return  new BigDecimal("0.5");
		}else if(curr>= d2 && curr<d3){
			return  new BigDecimal("0.2");
		}
		return BigDecimal.ZERO;
	}

	//更新用户等级
	public void updateUserGrade(UserDo user, BigDecimal amount) {

		if( null == user){
			return;
		}
		// 获取用户等级欧元
		String grade = paramService.getByName(WebConstants.USER_GRADE_AMOUNT);
		if (StringUtils.isBlank(grade)) {
			return;
		}
		BigDecimal refferAmount = getAccountTypeAmount(WebConstants.REFFER_ACCOUNT, user.getId());
		refferAmount = refferAmount.add(amount);

		String[] grades = grade.split(";");
		int updateGrade = 0;
		for (String str : grades) {
			String[] pramGrade = str.split("=");
			if (pramGrade.length < 1) {
				continue;
			}
			BigDecimal gradeAmount = new BigDecimal(pramGrade[1]);
			if (refferAmount.compareTo(gradeAmount) >= 0) {
				updateGrade = Integer.valueOf(pramGrade[0]);
			}
		}

		UserDo updateUser = new UserDo();
		updateUser.setGrade(updateGrade);
		updateUser.setId(user.getId());
		userDao.updateUser(updateUser);
		
		//更上级用户推荐金额
		BizUserAccountDo bizUserAccountDo = new BizUserAccountDo(user.getId(),amount,WebConstants.REFFER_ACCOUNT);
//		bizUserAccountDo.setUserId(refferId);
//		bizUserAccountDo.setAccountType(WebConstants.REFFER_ACCOUNT);
//		bizUserAccountDo.setAmount(amount);
		AccountMsg msg = AccountMsg.type_3;
//		AccountMsg msg = new AccountMsg()
		msg.setMessage("推荐用户购买证券,用户：" + user.getName() + ",消费:");
		updateUserAmountById(bizUserAccountDo, msg);
	
	}
	
	private BigDecimal unpayRate(Long userId){
		String grade = paramService.getByName(WebConstants.UN_PAY_RATE);
		BigDecimal rate = BigDecimal.ONE;
		
		if(StringUtils.isBlank(grade)){
			return rate;
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("accountType", WebConstants.SECURITY_ACCOUNT);
		BizUserAccountDo udo = bizUserAccountDao.getByUserIdAndAccount(map);
		if( null != udo ){
			Date today = new Date();
			Date updateDate = udo.getUpdateTime();
			Long day = DateUtil.diffDays(updateDate,today);
			
			String[] grades = grade.split(";");
			int diffDate = 2000;
			for (String str : grades) {
				String[] pramDate = str.split("=");
				if(pramDate.length <1 ){
					continue;	
				}
				int dateRate = Integer.valueOf(pramDate[0]);	
				//当前日期小于参数日期并且参数日期小于上一个参数日期
				if(day <= dateRate &&  dateRate < diffDate ){
					diffDate = dateRate;
					rate = new BigDecimal(pramDate[1]);
					BigDecimal tempRate = rate.divide(new BigDecimal(100),2,RoundingMode.HALF_UP);
					
					rate = BigDecimal.ONE.subtract(tempRate);
				}
			}
		}
		
		return rate;
		
	}


	@Override
	public List<BizUserAccountDo> selectFeiHongUserAccount(
			Map<String, Object> parameterMap) {
		return bizUserAccountDao.selectFeiHongAccount(parameterMap);
	}

	@Override
	public int selectFeiHongUserAccountCount(Map<String, Object> parameterMap) {
		return bizUserAccountDao.selectFeiHongAccountCount(parameterMap);
	}


	@Override
	public void backupAccount(Date now) {
		String date = DateUtil.formatDate(now,"yyyyMMdd");
		bizUserAccountDao.backupAccount(date);
		
	}


	@Override
	public List<BizUserAccountDo> selectUserAccountFromTemp() {
		return bizUserAccountDao.selectUserAccountFromTemp();
	}


	@Override
	public void deleteFeiHongTemp() {
		bizUserAccountDao.deleteFeiHongTemp();
	}
	
	/**
	 * 积分转让
	 * @param sourceUserId
	 * @param targetUserId
	 * @param jifengAmt
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void transJifeng(Long sourceUserId, Long targetUserId,
			BigDecimal jifengAmt) {
		
		BizUserAccountDo source = new BizUserAccountDo();
		source.setUserId(sourceUserId);
		source.setAmount(jifengAmt.negate());
		source.setAccountType(WebConstants.JIFEN_ACCOUNT);
		this.updateUserAmountById(source, AccountMsg.type_20);

		BizUserAccountDo target = new BizUserAccountDo();
		target.setUserId(targetUserId);
		target.setAmount(jifengAmt);
		target.setAccountType(WebConstants.JIFEN_ACCOUNT);
		this.updateUserAmountById(target, AccountMsg.type_21);
		
		
	}

	/**
	 * 一天只允许转换一次， 通过enddate做唯一索引
	 * 储值豆转换
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void saveTransPoint(Long userId, BigDecimal jifengAmt) {
		//获取当前可转换储值豆
		BigDecimal pointAmt = userAccountDetailService.selectUserTransPoint(userId);
		if(null == pointAmt){
			throw new RuntimeException("没有可转换的储值豆");
		}
		if(null == jifengAmt){
			throw new RuntimeException("本此转换的储值豆额度不能为空");
		}
		
		if(pointAmt.compareTo(jifengAmt)<0){
			throw new RuntimeException("本此转换的储值豆额度不能大于可转换储值豆总额");
		}
		if(jifengAmt.doubleValue()%(double)50 != 0){
			throw new RuntimeException("本此转换的储值豆额度必须是50的倍数");
		}
		
		
		int result = userAccountDetailService.insertPointTranRec(jifengAmt,pointAmt,userId);
		if(result<1){
			throw new RuntimeException("新增转换记录错误，转换失败");
		}
		
		//读配置
		//分配额度
		//BigDecimal changeAmt = jifengAmt.multiply(new BigDecimal("0.5"));
		
		BizUserAccountDo bizUserAccountDo = new BizUserAccountDo();
		//修改额度,减少储值积分，增加积分
		bizUserAccountDo.setAccountType(WebConstants.JIFEN_ACCOUNT);
		bizUserAccountDo.setUserId(userId);
		bizUserAccountDo.setAmount(jifengAmt);
		bizUserAccountDo.setRemark("积分转换:"+jifengAmt);
		this.updateUserAmountById(bizUserAccountDo, AccountMsg.type_31);
		/*
		//修改额度,减少储值积分，增加现金账户
		bizUserAccountDo.setAccountType(WebConstants.RMB_ACCOUNT);
		bizUserAccountDo.setUserId(userId);
		bizUserAccountDo.setAmount(changeAmt);
		bizUserAccountDo.setRemark("积分转换:"+changeAmt);
		this.updateUserAmountById(bizUserAccountDo, AccountMsg.type_31);
		*/
		
		
		//修改额度,减少储值积分，增加积分
		bizUserAccountDo.setAccountType(WebConstants.POINE_ACCOUNT);
		bizUserAccountDo.setUserId(userId);
		bizUserAccountDo.setAmount(jifengAmt.negate());
		bizUserAccountDo.setRemark("积分转换:"+jifengAmt.negate());
		this.updateUserAmountById(bizUserAccountDo,AccountMsg.type_31);
		
		//结转
		BigDecimal  remainPoint = pointAmt.subtract(jifengAmt);
		if(remainPoint.compareTo(BigDecimal.ZERO)>0){
			
			updateUserAmountDetail(remainPoint, AccountMsg.type_30, userId,WebConstants.POINE_ACCOUNT,"积分结转");	
		}
		
	}

	/**
	 * 奖金豆转换积分
	 */
	@Override
	public void saveTransJjd(Long userId, BigDecimal jifengAmt) {
		//获取当前可转换奖金豆
		BigDecimal pointAmt = this.getAccountTypeAmount(WebConstants.JJD_ACCOUNT, userId);
		if(null == pointAmt){
			throw new RuntimeException("没有可转换的奖金豆");
		}
		if(null == jifengAmt){
			throw new RuntimeException("本此转换的奖金豆额度不能为空");
		}
		
		if(pointAmt.compareTo(jifengAmt)<0){
			throw new RuntimeException("本此转换的奖金豆额度不能大于可转换奖金豆总额");
		}
		if(jifengAmt.doubleValue()%(double)50 != 0){
			throw new RuntimeException("本此转换的奖金豆额度必须是50的倍数");
		}
		
		
		int result = userAccountDetailService.insertPointTranRecJjd(jifengAmt,pointAmt,userId,AccountMsg.type_32);
		if(result<1){
			throw new RuntimeException("新增转换记录错误，转换失败");
		}
		
		
		//读配置
		//分配额度
		BizUserAccountDo bizUserAccountDo = new BizUserAccountDo();
		//修改额度,减少奖金豆，增加积分
		bizUserAccountDo.setAccountType(WebConstants.JIFEN_ACCOUNT);
		bizUserAccountDo.setUserId(userId);
		bizUserAccountDo.setAmount(jifengAmt);
		bizUserAccountDo.setRemark("奖金豆转激活积分:"+jifengAmt);
		this.updateUserAmountById(bizUserAccountDo, AccountMsg.type_32);
		//修改额度,减少储值积分，增加积分
		bizUserAccountDo.setAccountType(WebConstants.JJD_ACCOUNT);
		bizUserAccountDo.setUserId(userId);
		bizUserAccountDo.setAmount(jifengAmt.negate());
		bizUserAccountDo.setRemark("奖金豆转激活积分:"+jifengAmt.negate());
		this.updateUserAmountById(bizUserAccountDo,AccountMsg.type_32);
	}

	/**
	 * 现金豆转激活豆
	 */
	@Override
	public void saveTransRmb(Long userId, BigDecimal jifengAmt) {
		//获取当前可转换奖金豆
		BigDecimal pointAmt = this.getAccountTypeAmount(WebConstants.RMB_ACCOUNT, userId);
		if(null == pointAmt){
			throw new RuntimeException("没有可转换的现金豆");
		}
		if(null == jifengAmt){
			throw new RuntimeException("本此转换的现金豆额度不能为空");
		}
		
		if(pointAmt.compareTo(jifengAmt)<0){
			throw new RuntimeException("本此转换的现金豆额度不能大于可转换现金豆总额");
		}
		/*
		if(jifengAmt.doubleValue()%(double)50 != 0){
			throw new RuntimeException("本此转换的现金豆额度必须是50的倍数");
		}
		*/
		
		int result = userAccountDetailService.insertPointTranRecJjd(jifengAmt,pointAmt,userId,AccountMsg.type_33);
		if(result<1){
			throw new RuntimeException("新增转换记录错误，转换失败");
		}
		
		
		//读配置
		//分配额度
		BizUserAccountDo bizUserAccountDo = new BizUserAccountDo();
		//修改额度,减少现金豆，增加激活豆
		bizUserAccountDo.setAccountType(WebConstants.PET_ACCOUNT);
		bizUserAccountDo.setUserId(userId);
		bizUserAccountDo.setAmount(jifengAmt);
		bizUserAccountDo.setRemark("现金豆转激活豆:"+jifengAmt);
		this.updateUserAmountById(bizUserAccountDo, AccountMsg.type_33);
		//修改额度,减少现金豆
		bizUserAccountDo.setAccountType(WebConstants.RMB_ACCOUNT);
		bizUserAccountDo.setUserId(userId);
		bizUserAccountDo.setAmount(jifengAmt.negate());
		bizUserAccountDo.setRemark("现金豆转激活豆:"+jifengAmt.negate());
		this.updateUserAmountById(bizUserAccountDo,AccountMsg.type_33);
	}

	/**
	 * 
	 * 累加下级额度
	 * zhangyunhmf
	 *
	 * @see com.redpack.account.faced.IBizUserAccountService#totalAmt(com.redpack.account.model.UserDo, java.lang.String)
	 *
	 */
    @Override
    public BigDecimal totalAmt(UserDo userDo, String accountType) {
    	
    	BigDecimal result = BigDecimal.ZERO;
    	Map<String, Object> map = new HashMap<String,Object>();
    	map.put("userId", userDo.getId());
    	map.put("accountType", accountType);
		BizUserAccountDo userAccount = bizUserAccountDao.getByUserIdAndAccount(map);
		if(userAccount != null){
			result.add(userAccount.getAmount());
		}
		
		List<UserDo> childList = userDo.getChildList();
		if(CollectionUtils.isEmpty(childList)){
			return result;
		}
		
		for(UserDo user : childList){
			BigDecimal tmp = totalAmt(user,accountType);
			result.add(tmp);
		}
		
	    return result;
    }

	/**
	 * 
	 *
	 * zhangyunhmf
	 *
	 * @see com.redpack.account.faced.IBizUserAccountService#totalReferAmt(java.lang.Long, java.lang.String)
	 *
	 */
    @Override
    public BigDecimal totalReferAmt(Long id, String accountType) {
	    
	    Map<String,Object> map = new HashMap<String,Object>();
	    map.put("referrerId", id);
	    map.put("accountType", accountType);
	    List<Map<String,Object>> result = bizUserAccountDao.totalReferAmt(map );
	    if(CollectionUtils.isEmpty(result)){
	    	return BigDecimal.ZERO;
	    }
	    
	    return (BigDecimal)result.get(0).get("amount");
	    
    }

	/**
	 * 
	 *
	 */
    @Override
    public void convertBetweenAccount(Long sourceUserId,
    								  Long targetUserId,
    								  BigDecimal qty,    								  
    								  String fromAccount,
    								  String toAccount) {

		
		BizUserAccountDo source = new BizUserAccountDo();
		source.setUserId(sourceUserId);
		source.setAmount(qty.negate());
		source.setAccountType(fromAccount);
		this.updateUserAmountById(source, AccountMsg.type_20);

		BizUserAccountDo target = new BizUserAccountDo();
		target.setUserId(targetUserId);
		target.setAmount(qty);
		target.setAccountType(toAccount);
		this.updateUserAmountById(target, AccountMsg.type_21);
		
    }

	/**
	 * 
	 *
	 */
    @Override
    public void buySecurity(Long userId, BigDecimal qty) {
    	this.convertBetweenAccount(userId,userId, qty,WebConstants.RMB_ACCOUNT, WebConstants.SECURITY_ACCOUNT);
    	
    	//统计直推
    	UserDo userDo = userDao.getById(userId);
    	Long refUserId = userDo.getReferrerId();
    	if(null == refUserId){
    		return;
    	}
    	
    	BizUserAccountDo bizUserAccountDo = new BizUserAccountDo();
    	bizUserAccountDo.setUserId(refUserId);
    	bizUserAccountDo.setAccountType(WebConstants.RMB_ACCOUNT);
    	bizUserAccountDo.setAmount(qty.multiply(new BigDecimal("0.1")));
		this.updateUserAmountById(bizUserAccountDo, AccountMsg.type_34);
    	//统计趴点
		totalToPadian(userId,qty);
	    
    }
    
    private void totalToPadian(long UserId , BigDecimal qty){
    	UserDo parent = userService.getParent(UserId);
    	if(parent == null){return;}
    	if(parent.getOrgan().equals("0")){
    		totalToPadian( parent.getId() , qty);
    	}else{
    		BizUserAccountDo bizUserAccountDo = new BizUserAccountDo();
        	bizUserAccountDo.setUserId(parent.getId());
        	bizUserAccountDo.setAccountType(WebConstants.RMB_ACCOUNT);
        	bizUserAccountDo.setAmount(qty.multiply(new BigDecimal("0.15")));
    		this.updateUserAmountById(bizUserAccountDo,AccountMsg.type_35 );
    		return;
    	}
    }
	
	
}

/*
 * Powered By  huangzl QQ: 272950754
 * Web Site:  
 * Since 2008 - 2016
 */

package com.redpack.bet.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.redpack.account.dao.IUserAccountDetailDao;
import com.redpack.account.dao.IUserAccountIncomeDao;
import com.redpack.account.dao.IUserDao;
import com.redpack.account.faced.IKangjiBuyIncomeService;
import com.redpack.account.model.KangjiBuyIncomeDo;
import com.redpack.account.model.UserAccountDetailDo;
import com.redpack.account.model.UserAccountIncomeDo;
import com.redpack.account.model.UserDo;
import com.redpack.base.result.IResult;
import com.redpack.base.result.ResultSupport;
import com.redpack.bet.IUserBetService;
import com.redpack.bet.dao.IUserBetDao;
import com.redpack.bet.model.UserBetDo;
import com.redpack.constant.WebConstants;
import com.redpack.member.dao.IMemberDao;
import com.redpack.member.model.KuangjiUserAccountDo;

/**
 * @author  huangzl QQ: 272950754
 * @version 1.0
 * @since 1.0
 */


@Service("userBetService")
public class UserBetServiceImpl implements IUserBetService {

	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
    private IUserBetDao  userBetDao;
	
	@Autowired
    private IUserAccountIncomeDao  userAccountIncomeDao;
	
	@Autowired
	private IUserAccountDetailDao  userAccountDetailDao;
	@Autowired
	private IMemberDao memberDao;
	
	@Autowired
	private IUserDao userDao;
	
	@Autowired
	private IKangjiBuyIncomeService kangjiBuyIncomeService;
	/**
	 * 根据ID 查询
	 * @parameter id
	 */
	@Override
	public UserBetDo getById(int id){
	  return userBetDao.getById(id);
	}
	
	/**
	 *根据条件查询列表
	 */
	@Override
	public List<UserBetDo> selectUserBet(Map<String,Object> parameterMap){
		return userBetDao.selectUserBet(parameterMap);
	}
	
	/**
	 * 更新
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public IResult<?> updateUserBetById(UserBetDo newUserBetDo){
		logger.debug("updateUserBet(UserBetDo: "+newUserBetDo);
		int i =userBetDao.updateUserBetById(newUserBetDo);
		if(i<1){
			return ResultSupport.buildResult(1, newUserBetDo+"更新失败");
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
	public IResult<?> addUserBet(UserBetDo newUserBetDo){
		logger.debug("addUserBet: "+newUserBetDo);
		int i =userBetDao.addUserBet(newUserBetDo);
		if(i<1){
			return ResultSupport.buildResult(1, newUserBetDo+"新增失败");
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
		logger.debug("deleteByIdUserBet:"+id);
		int i =userBetDao.deleteById(id);
		if(i<1){
			return ResultSupport.buildResult(1, id+"删除失败");
		}
		IResult<Integer> result = ResultSupport.buildResult(0);
		result.setModel(Integer.valueOf(i));
		return result;
	}

	@Override
	public void addBet(BigDecimal amount,long userId) {
		// TODO Auto-generated method stub
		//1:减少前台帐户金额
		
		UserAccountIncomeDo userAccount = userAccountIncomeDao.getById(userId);
		//用户金额  积分  全金币 积分改为0
//		BigDecimal oldPoint = userAccount.getPoint() == null?
//							BigDecimal.ZERO : userAccount.getPoint();
		BigDecimal oldPoint = BigDecimal.ZERO;
		
		if(null != userAccount){
			//金额用SQL语句进行更新 避免并发 减的用负数
		
			KuangjiUserAccountDo accountDo = new KuangjiUserAccountDo();
			//积分小于付款金额 余额由现金付款 
			if(oldPoint.compareTo(amount)< 0 ){
				accountDo.setPoint(BigDecimal.ZERO.subtract(oldPoint));
				accountDo.setAmount(BigDecimal.ZERO.subtract(amount.subtract(oldPoint)));
				
			}else{
				accountDo.setPoint(BigDecimal.ZERO.subtract(amount));
			}
			
			accountDo.setUserId(userId);
			
			accountDo.setConsumeTotalAmount(amount);
			accountDo.setUpdateTime(new Date());
			memberDao.updateAmountById(accountDo);
		}
		
		//增加消费明细
		
		UserAccountDetailDo uaDetail = new UserAccountDetailDo();
		if(oldPoint.compareTo(amount)< 0){
			//积分消费明细
			uaDetail.setAmount(oldPoint);
			uaDetail.setCreateTime(new Date());
			uaDetail.setIncomeType(12);
			uaDetail.setMoreOrLess("-");
			uaDetail.setUserId(userId);
			userAccountDetailDao.addUserAccountDetail(uaDetail);
			
			//现金消费明细
			UserAccountDetailDo amountDetail = new UserAccountDetailDo();
			amountDetail.setAmount(amount.subtract(oldPoint));
			amountDetail.setCreateTime(new Date());
			amountDetail.setIncomeType(13);
			amountDetail.setMoreOrLess("-");
			amountDetail.setUserId(userId);
			userAccountDetailDao.addUserAccountDetail(amountDetail);
		}else{
			uaDetail.setAmount(amount);
			uaDetail.setCreateTime(new Date());
			uaDetail.setIncomeType(12);
			uaDetail.setMoreOrLess("-");
			uaDetail.setUserId(userId);
			userAccountDetailDao.addUserAccountDetail(uaDetail);
		}
		
		//上线提成
		/**
		UserDo userDo = userDao.getById(userId);
		if( null == userDo){
			return;
		}
		UserDo oneUserDo = userDao.getById(userDo.getReferrerId());
	
		//1:推荐人，10%消费奖
		if( null != oneUserDo){
			long refferId = userDo.getReferrerId();
			BigDecimal refferRate = new BigDecimal("0.1");
			BigDecimal addMount = amount.multiply(refferRate);
			//增加推荐人账户金额
//			updateRefferAmount(1, refferId, addMount, 14);
			
			BigDecimal centerRate = new BigDecimal("0.02");
			BigDecimal centerMount = amount.multiply(centerRate);
			
//			centerRate(1, refferId, centerMount, 15);
			
			//团队金额
			teamAmount(refferId,amount);
			
		}
		**/
		
		
	}
	
	@Override
	public void addBet(BigDecimal amount,long userId,String accType) {
		// TODO Auto-generated method stub
		
	}
	
	//更新用户帐户金额 1代10% 2代5%
		public void updateRefferAmount(Integer level,Long refferId, BigDecimal addAmount,
				String accType) {
			// 更新用户帐户金额
			KuangjiUserAccountDo userAccount = new KuangjiUserAccountDo();
			// 增加消费明细
			UserAccountDetailDo uaDetail = new UserAccountDetailDo();
			userAccount.setUserId(refferId);
			if("eour".equals(accType)){
				userAccount.setAmount(addAmount);
				uaDetail.setIncomeType(133);
				uaDetail.setMoreOrLess("下" +level +  "级金币竞猜提成:" + addAmount);
			}else{
				userAccount.setPoint(addAmount);
				uaDetail.setIncomeType(233);
				uaDetail.setMoreOrLess("下" +level + "下级积分竞猜提成:" + addAmount);
			}
			userAccount.setPoingTotalAmount(addAmount);
			userAccount.setRecommendTotalAmount(addAmount);
			userAccount.setUpdateTime(new Date());
			memberDao.updateAmountById(userAccount);
			
			//消费明细
			uaDetail.setAmount(addAmount);
			uaDetail.setCreateTime(new Date());
			uaDetail.setUserId(refferId);
			userAccountDetailDao.addUserAccountDetail(uaDetail);

			// 增加消费项目提成表
			KangjiBuyIncomeDo kjBuyIncomeDo = new KangjiBuyIncomeDo();
			kjBuyIncomeDo.setUserId(refferId);
			kjBuyIncomeDo.setCreateTime(new Date());
			kjBuyIncomeDo.setGrade(level);
			kjBuyIncomeDo.setIncome(addAmount);
			kangjiBuyIncomeService.addKangjiBuyIncome(kjBuyIncomeDo);

		}

		// 报单中心奖
//		public void centerRate(int level,Long refferId, BigDecimal addAmount,
//				int incomeType){
//			UserDo userDo = userDao.getById(refferId);
//			if(null == userDo){
//				return;
//			}
//			//用户级别为报单中心才能拿
//			String enabled = userDo.getEnabled();
//			if( "1".equals(enabled)){
//				updateRefferAmount(level, refferId, addAmount, incomeType);
//			}
//			
//			//报单中心无限拿
//			long parentId = userDo.getReferrerId();
//			if( 0 != parentId){
//				int parentlevel  = level + 1;
//				centerRate(parentlevel, parentId, addAmount, incomeType);
//			}
//			
//		}
		
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
}

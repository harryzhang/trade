/*
 * Powered By  huangzl QQ: 272950754
 * Web Site:  
 * Since 2008 - 2016
 */

package com.redpack.account.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.redpack.account.dao.IKangjiIncomeDao;
import com.redpack.account.dao.IUserAccountDetailDao;
import com.redpack.account.faced.IKangjiIncomeService;
import com.redpack.account.faced.IUserAccountIncomeService;
import com.redpack.account.model.KangjiIncomeDo;
import com.redpack.account.model.UserAccountDetailDo;
import com.redpack.account.model.UserAccountIncomeDo;
import com.redpack.constant.WebConstants;
import com.redpack.member.dao.IMemberDao;
import com.redpack.member.model.KuangjiUserAccountDo;
import com.redpack.param.IParamService;

/**
 * @author  huangzl QQ: 272950754
 * @version 1.0
 * @since 1.0
 */


@Service("kangjiIncomeService")
public class KangjiIncomeServiceImpl implements IKangjiIncomeService {

	private final Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private IUserAccountIncomeService userAccountIncomeService;
	
	@Autowired
    private IKangjiIncomeDao  kangjiIncomeDao;
	
	@Autowired
	private IParamService paramService;
	
	@Autowired
	private IMemberDao memberDao;
	
	@Autowired
	private IUserAccountDetailDao  userAccountDetailDao;
	/**
	 * 根据ID 查询
	 * @parameter id
	 */
	@Override
	public KangjiIncomeDo getById(int id){
	  return kangjiIncomeDao.getById(id);
	}
	
	/**
	 *根据条件查询列表
	 */
	@Override
	public List<KangjiIncomeDo> selectKangjiIncome(Map<String,Object> parameterMap){
		return kangjiIncomeDao.selectKangjiIncome(parameterMap);
	}
	
	/**
	 * 更新
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public int updateKangjiIncomeById(KangjiIncomeDo newKangjiIncomeDo){
		logger.debug("updateKangjiIncome(KangjiIncomeDo: "+newKangjiIncomeDo);
		return kangjiIncomeDao.updateKangjiIncomeById(newKangjiIncomeDo);
	}
	
	/**
	 * 新增
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public int addKangjiIncome(KangjiIncomeDo newKangjiIncomeDo){
		logger.debug("addKangjiIncome: "+newKangjiIncomeDo);
		return kangjiIncomeDao.addKangjiIncome(newKangjiIncomeDo);
	}
	
	/**
	 * 删除
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public int deleteById(int id){
		logger.debug("deleteByIdKangjiIncome:"+id);
		return kangjiIncomeDao.deleteById(id);
		
	}

	/**
	 *查询用户当天收益
	 */
	public List<KangjiIncomeDo> getUserIncome(Map<String,Object> parameterMap){
		return kangjiIncomeDao.getUserIncome(parameterMap);
	}

	@Override
	public void doJob() {
		// TODO Auto-generated method stub
		
		Date today = new Date();
		today.setDate(today.getDate() -1);
		String date = com.redpack.utils.DateUtil.formatDate(today);
		
		//提升报单中心
		
		//用户静态分红
		dojobDate(date);
	}

	@Override
	public void dojobDate(String date) {
		
		List<UserAccountIncomeDo> accountList = userAccountIncomeService.selectUserAccount(null);
		BigDecimal staticRate = paramService.getRateByName(WebConstants.STATIC_ONE_RATE);
		String staticEourPointRate = paramService.getByName(WebConstants.STATIC_EOUR_POINT_RATE);
		String refferCenter = paramService.getByName(WebConstants.REFFER_CENTER);
		String guqanCenter = paramService.getByName(WebConstants.GUQAN_CENTER);
		
		BigDecimal amountRate = new BigDecimal("1");
		BigDecimal pointRate = BigDecimal.ZERO;
		if(StringUtils.isNotBlank(staticEourPointRate)){
			String[] rates = staticEourPointRate.split(":");
			if(rates.length >1 ){
				BigDecimal rate = new BigDecimal(rates[0]);
				amountRate = rate.divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);
				pointRate = BigDecimal.ONE.subtract(amountRate);
				
			}
		}
		
		//读取用户股权信息
		for (UserAccountIncomeDo accountDo : accountList) {
			
			
			BigDecimal guqan = accountDo.getGuquanQty();
			if( null != guqan && guqan.compareTo(new BigDecimal("0.01")) > 0){
				BigDecimal addAmount = guqan.multiply(staticRate);
				
				BigDecimal eourAmount = addAmount.multiply(amountRate);
				BigDecimal pointAmount = addAmount.subtract(eourAmount);
				// 更新用户帐户金额
				KuangjiUserAccountDo userAccount = new KuangjiUserAccountDo();
				userAccount.setUserId(accountDo.getUserId());
				userAccount.setAmount(eourAmount);
				userAccount.setPoint(pointAmount);
				userAccount.setPoingTotalAmount(addAmount);
				userAccount.setIncomeTotalAmount(addAmount);
				userAccount.setUpdateTime(new Date());
				memberDao.updateAmountById(userAccount);
	
				// 增加消费明细
				String message ="用户每天股权数量收益：";
				UserAccountDetailDo uaDetail = new UserAccountDetailDo();
				uaDetail.setAmount(eourAmount);
				uaDetail.setCreateTime(new Date());
				uaDetail.setIncomeType(108);
				uaDetail.setMoreOrLess(message + eourAmount);
				uaDetail.setUserId(accountDo.getUserId());
				userAccountDetailDao.addUserAccountDetail(uaDetail);
				
				// 增加消费明细
				UserAccountDetailDo pointDetail = new UserAccountDetailDo();
				pointDetail.setAmount(pointAmount);
				pointDetail.setCreateTime(new Date());
				pointDetail.setIncomeType(208);
				pointDetail.setMoreOrLess(message + pointAmount);
				pointDetail.setUserId(accountDo.getUserId());
				userAccountDetailDao.addUserAccountDetail(pointDetail);
			}
		}
		//更新报单中心
//		KuangjiUserAccountDo  memberDo = new KuangjiUserAccountDo();
//		memberDo.setGuquanQty(new BigDecimal(guqanCenter));
//		memberDo.setTeamAmount(new BigDecimal(refferCenter));
//		memberDao.updateUserCenterById(memberDo);
		
	}
}

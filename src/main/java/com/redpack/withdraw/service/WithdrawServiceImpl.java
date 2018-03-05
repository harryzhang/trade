/*
 * Powered By  huangzl QQ: 272950754
 * Web Site:  
 * Since 2008 - 2016
 */

package com.redpack.withdraw.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.redpack.account.faced.IBizUserAccountService;
import com.redpack.account.faced.IUserInfoService;
import com.redpack.account.model.BizUserAccountDo;
import com.redpack.constant.AccountMsg;
import com.redpack.constant.WebConstants;
import com.redpack.member.IMemberService;
import com.redpack.param.IParamService;
import com.redpack.withdraw.IWithdrawService;
import com.redpack.withdraw.dao.IWithdrawDao;
import com.redpack.withdraw.model.WithdrawDo;

/**
 * @author  huangzl QQ: 272950754
 * @version 1.0
 * @since 1.0
 */


@Service("withdrawService")
public class WithdrawServiceImpl implements IWithdrawService {

	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
    private IWithdrawDao  withdrawDao;
	
	@Autowired
	private IUserInfoService userInfoService;
	
	@Autowired
	private IMemberService memberService;
	
	@Autowired
	private IParamService paramService;
	
	
	@Autowired
	private IBizUserAccountService bizUserAccountService;
	/**
	 * 根据ID 查询
	 * @parameter id
	 */
	@Override
	public WithdrawDo getById(int id){
	  return withdrawDao.getById(id);
	}
	
	/**
	 *根据条件查询列表
	 */
	@Override
	public List<WithdrawDo> selectWithdraw(Map<String,Object> parameterMap){
		return withdrawDao.selectWithdraw(parameterMap);
	}
	
	/**
	 * 更新
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public int updateWithdrawById(WithdrawDo newWithdrawDo){
		logger.debug("updateWithdraw(WithdrawDo: "+newWithdrawDo);
		return withdrawDao.updateWithdrawById(newWithdrawDo);
	}
	
	/**
	 * 新增
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public int addWithdraw(WithdrawDo newWithdrawDo){
		logger.debug("addWithdraw: "+newWithdrawDo);
		
		String withrawRateStr = paramService.getByName(WebConstants.WITHDRAW_RATE);
		BigDecimal withrawRate = new BigDecimal(withrawRateStr);
		//提现金额
		BigDecimal amount =newWithdrawDo.getAmount();
		
//		BigDecimal charge = amount.multiply(chargeRate)
//				.divide(new BigDecimal(100), 2, RoundingMode.HALF_UP);
		BigDecimal payAmount = amount.multiply(withrawRate);
//		newWithdrawDo.setAmount(amount);
		newWithdrawDo.setCharge(withrawRate);
		newWithdrawDo.setPayAmount(payAmount);
		newWithdrawDo.setCreateTime(new Date());
		newWithdrawDo.setWithdrawStatus(1);
		
		//冻结用户资金
		Long userId = newWithdrawDo.getUserId();
		BizUserAccountDo bizUserAccountDo = new BizUserAccountDo();
		bizUserAccountDo.setUserId(userId);
		bizUserAccountDo.setAmount(amount.negate());
		bizUserAccountDo.setAccountType(WebConstants.RMB_ACCOUNT);
		bizUserAccountDo.setFrozenDeposit(amount);
		bizUserAccountService.updateUserAmountById(bizUserAccountDo, AccountMsg.type_6);
		
		return withdrawDao.addWithdraw(newWithdrawDo);
	}
	
	/**
	 * 删除
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public int deleteById(int id){
		logger.debug("deleteByIdWithdraw:"+id);
		return withdrawDao.deleteById(id);
		
	}

}

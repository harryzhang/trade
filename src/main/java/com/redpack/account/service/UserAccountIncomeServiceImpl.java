/*
 * Powered By  huangzl QQ: 272950754
 * Web Site:  
 * Since 2008 - 2016
 */

package com.redpack.account.service;

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
import com.redpack.account.faced.IUserAccountIncomeService;
import com.redpack.account.model.UserAccountDetailDo;
import com.redpack.account.model.UserAccountIncomeDo;
import com.redpack.account.model.UserDo;

/**
 * @author  huangzl QQ: 272950754
 * @version 1.0
 * @since 1.0
 */


@Service("userAccountIncomeService")
public class UserAccountIncomeServiceImpl implements IUserAccountIncomeService {

	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
    private IUserAccountIncomeDao  userAccountIncomeDao;
	@Autowired
	private IUserDao userDao;
	@Autowired
	private IUserAccountDetailDao  userAccountDetailDao;
	/**
	 * 根据ID 查询
	 * @parameter id
	 */
	@Override
	public UserAccountIncomeDo getById(Long id){
	  return userAccountIncomeDao.getById(id);
	}
	
	/**
	 *根据条件查询列表
	 */
	@Override
	public List<UserAccountIncomeDo> selectUserAccount(Map<String,Object> parameterMap){
		return userAccountIncomeDao.selectUserAccount(parameterMap);
	}
	
	/**
	 * 更新
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public int updateUserAccountById(UserAccountIncomeDo newUserAccountIncomeDo){
		logger.debug("updateUserAccount(UserAccountIncomeDo: "+newUserAccountIncomeDo);
		return userAccountIncomeDao.updateUserAccountById(newUserAccountIncomeDo);
	}
	
	/**
	 * 新增
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public int addUserAccount(UserAccountIncomeDo newUserAccountIncomeDo){
		logger.debug("addUserAccount: "+newUserAccountIncomeDo);
		return userAccountIncomeDao.addUserAccount(newUserAccountIncomeDo);
		
	}
	
	/**
	 * 删除
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public int deleteById(int id){
		logger.debug("deleteByIdUserAccount:"+id);
		return userAccountIncomeDao.deleteById(id);
	}

	/**
	 * 比较账号积分+金币余额
	 */
	@Override
	public boolean compareAmt(Long userId, double totalMoney) {
		UserAccountIncomeDo userAccount = userAccountIncomeDao.getById(userId);
		if(null == userAccount){
			return false;
		}
		
		//积分跟金币进行比较
		BigDecimal account = userAccount.getAmount() == null?BigDecimal.ZERO: userAccount.getAmount();
		BigDecimal point = userAccount.getPoint() == null ? BigDecimal.ZERO: userAccount.getPoint();
		BigDecimal amount = account.add(point);
		
		return amount.compareTo(new BigDecimal(""+totalMoney)) >=0 ;
	}
	/**
	 * 比较账号指定类型金币余额
	 */
	@Override
	public boolean compareAmountAmt(Long userId, double totalMoney) {
		UserAccountIncomeDo userAccount = userAccountIncomeDao.getById(userId);
		if(null == userAccount){
			return false;
		}
		
		BigDecimal amount = userAccount.getAmount() == null?BigDecimal.ZERO: userAccount.getAmount();
		
		return amount.compareTo(new BigDecimal(""+totalMoney)) >=0 ;
	}

	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public int updateUserPointById(UserAccountIncomeDo newUserAccountIncomeDo) {
		return userAccountIncomeDao.updateUserPointById(newUserAccountIncomeDo);
	}

	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void transJifeng(Long sourceUserId, Long targetUserId,
			BigDecimal jifengAmt) {
		UserAccountIncomeDo source = new UserAccountIncomeDo();
		source.setUserId(sourceUserId);
		source.setPoint(jifengAmt.negate());
		int ret = userAccountIncomeDao.updateUserPointById(source);
		if(ret<1){
			throw new RuntimeException("更新出让人积分失败");
		}
		
		//增加积分转让明细
		UserAccountDetailDo uaDetail = new UserAccountDetailDo();
		UserDo userDo = userDao.getById(targetUserId);
		// 积分消费明细
		uaDetail.setAmount(jifengAmt.negate());
		uaDetail.setCreateTime(new Date());
		uaDetail.setIncomeType(212);
		uaDetail.setMoreOrLess("积分转让给" + userDo.getName() + ":" + jifengAmt);
		uaDetail.setUserId(sourceUserId);
		userAccountDetailDao.addUserAccountDetail(uaDetail);

		
		UserAccountIncomeDo target = new UserAccountIncomeDo();
		target.setUserId(targetUserId);
		target.setPoint(jifengAmt);
		ret = userAccountIncomeDao.updateUserPointById(target);
		if(ret<1){
			throw new RuntimeException("更新接受人积分失败");
		}
		//TODO转让记录缺失
		UserAccountDetailDo uDetail = new UserAccountDetailDo();
		UserDo targetDo = userDao.getById(sourceUserId);
		// 积分消费明细
		uDetail.setAmount(jifengAmt.negate());
		uDetail.setCreateTime(new Date());
		uDetail.setIncomeType(212);
		uDetail.setMoreOrLess("积分接受" + targetDo.getName() + ":" + jifengAmt);
		uDetail.setUserId(targetUserId);
		userAccountDetailDao.addUserAccountDetail(uDetail);

		
	}

}

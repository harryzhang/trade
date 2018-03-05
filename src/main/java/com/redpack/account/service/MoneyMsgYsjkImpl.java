package com.redpack.account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redpack.account.model.UserDo;
import com.redpack.grade.model.GroupUserDo;
import com.redpack.group.dao.IGroupUserDao;
import com.redpack.param.IParamService;
import com.redpack.sms.ISysSmsService;
import com.redpack.wallet.dao.IWalletDao;
import com.redpack.wallet.model.WalletDo;

@Service("moneyMsgYsjkImpl")
public class MoneyMsgYsjkImpl implements IMoneyMsg {
	
	@Autowired
	private IGroupUserDao groupUserDao;
	
	
	@Autowired
	private ISysSmsService sysSmsService;
	
	@Autowired	
    private IParamService  paramService;
	
	@Autowired	
    private IWalletDao  walletDao;

	
	/**
	 * 处理打款信息
	 * @param userDo
	 * @param groupUserDo
	 */
	/**
	 * 处理打款信息
	 * @param userDo
	 * @param groupUserDo
	 */
	public void processMoney(UserDo userDo,GroupUserDo groupUserDo){
		WalletDo walletDo = new WalletDo();
		walletDo.setFkUserId(userDo.getId());
		walletDo.setSkUserId(0l); //平台
		walletDo.setRefUserId(userDo.getReferrerId());
		//Double amt = paramService.getReferrerMoney();
		Double amt = 3000d;
		walletDo.setAmt(amt);
		walletDo.setRemark("临时会员转正式会员");
		walletDo.setType(1);
		walletDo.setSkStatus(0);
		walletDo.setFkStatus(0);
		walletDo.setGroupName(groupUserDo.getGroupName());
		walletDo.setValid(1);
		int addInt = walletDao.addWallet(walletDo);
	}
	
	/**
	 * 给上级用户打款
	 */
	private int saveWallet(GroupUserDo groupUserDo,Long userId,Long refUserId, int level){
		WalletDo walletDo = new WalletDo();
		walletDo.setFkUserId(userId);
		walletDo.setSkUserId(groupUserDo.getUserId());
		Double amt = paramService.getPayMoney(level,"A");
		walletDo.setAmt(amt);
		walletDo.setRemark("注册转正式用户付款");
		walletDo.setType(2);
		walletDo.setSkStatus(0);
		walletDo.setFkStatus(0);
		walletDo.setRefUserId(refUserId);
		walletDo.setGroupName(groupUserDo.getGroupName());
		walletDo.setValid(1);
		int addInt = walletDao.addWallet(walletDo);
		
		return addInt;
	}
	
	/**
	 * 给平台打款
	 * @return
	 */
	private int saveFx(Long userId,String groupName){
		return saveFx(userId,groupName,"注册转正式用户给平台付款");
	}
	
	/**
	 * 给平台打款
	 * @return
	 */
	private int saveFx(Long userId,String groupName,String remark){
		WalletDo walletDo = new WalletDo();
		walletDo.setFkUserId(userId);
		walletDo.setSkUserId(0l);
		Double amt = paramService.getPayMoney(0,"A");
		walletDo.setAmt(amt);
		walletDo.setRemark(remark);
		walletDo.setType(2);
		walletDo.setSkStatus(0);
		walletDo.setFkStatus(0);
		walletDo.setGroupName(groupName);
		walletDo.setValid(1);
		int addInt = walletDao.addWallet(walletDo);
		return addInt;
	}
	
	/**
	 * B网给平台打款
	 * @return
	 */
	private int saveFxB(Long userId,String groupName){
		WalletDo walletDo = new WalletDo();
		walletDo.setFkUserId(userId);
		walletDo.setSkUserId(0l);
		Double amt = paramService.getPayMoney(0,"B");
		walletDo.setAmt(amt);
		walletDo.setRemark("B网用户给平台付款");
		walletDo.setType(2);
		walletDo.setSkStatus(0);
		walletDo.setFkStatus(0);
		walletDo.setGroupName(groupName);
		walletDo.setValid(1);
		int addInt = walletDao.addWallet(walletDo);
		return addInt;
	}
	
	/**
	 * 给推荐人打款
	 * @return
	 */
	private int saveReferrer(Long userId,Long referrerId,String groupName){
		WalletDo walletDo = new WalletDo();
		walletDo.setFkUserId(userId);
		walletDo.setSkUserId(referrerId);
		walletDo.setRefUserId(referrerId);
		Double amt = paramService.getReferrerMoney();
		walletDo.setAmt(amt);
		walletDo.setRemark("给推荐人打款");
		walletDo.setType(1);
		walletDo.setSkStatus(0);
		walletDo.setFkStatus(0);
		walletDo.setGroupName(groupName);
		walletDo.setValid(1);
		int addInt = walletDao.addWallet(walletDo);
		return addInt;
	}
	
	

}

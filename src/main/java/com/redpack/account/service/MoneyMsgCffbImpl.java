package com.redpack.account.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redpack.account.model.UserDo;
import com.redpack.grade.model.GroupUserDo;
import com.redpack.grade.model.GroupUserDoWrap;
import com.redpack.group.dao.IGroupUserDao;
import com.redpack.param.IParamService;
import com.redpack.sms.ISysSmsService;
import com.redpack.sms.model.SysSmsDo;
import com.redpack.wallet.dao.IWalletDao;
import com.redpack.wallet.model.WalletDo;

@Service("moneyMsgCffbImpl")
public class MoneyMsgCffbImpl implements IMoneyMsg {
	
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
	@Override
	public void processMoney(UserDo userDo,GroupUserDo groupUserDo){
		
		Long userId =userDo.getId();
		String groupName = groupUserDo.getGroupName();
		
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("groupName", groupName);
		
		//获取团队打款人信息
		int sort = groupUserDo.getSort();
		//第四层的 1-9 打给 第三层的1-9， 第10 打给第二层的 1-3， 11,12 打给平台， 12-19给第一层，第27代打复投，20-26 给B网打款
		List<GroupUserDo> groupAllUserList = groupUserDao.selectGroupUser(paramMap);
		GroupUserDoWrap groupUserDoWrap = new GroupUserDoWrap(groupAllUserList);
		if(sort <= 9){//第四层的 1-9 打给 第三层的1-9
			List<GroupUserDo> userList = groupUserDoWrap.getChildByLevel(3);
			GroupUserDo groupUserDo1 = userList.get((sort-1));
			saveWallet(groupUserDo1,userId, userDo.getReferrerId(),3);
			
		}else if( 13<=sort && sort<=20){//12-19给第一层
			List<GroupUserDo> userList = groupUserDoWrap.getChildByLevel(1);
			GroupUserDo groupUserDo1 = userList.get(0);
			saveWallet(groupUserDo1,userId,userDo.getReferrerId(), 1);
		
		//给平台打款
		}else if(sort == 10){//第10 打给第二层的 1-3
			List<GroupUserDo> userList = groupUserDoWrap.getChildByLevel(2);
			GroupUserDo groupUserDo1 = userList.get(0);
			saveWallet(groupUserDo1,userId, userDo.getReferrerId(),2);
			GroupUserDo groupUserDo2 = userList.get(1);
			saveWallet(groupUserDo2,userId, userDo.getReferrerId(),2);
			GroupUserDo groupUserDo3 = userList.get(2);
			saveWallet(groupUserDo3,userId, userDo.getReferrerId(),2);
			
		}else if(sort == 11|| sort == 12){// 11,12 打给平台
			saveFx(userId,groupName);
		}else if(sort == 27){//复投带代打
			List<GroupUserDo> userList = groupUserDoWrap.getChildByLevel(4);
			saveWallet(userList.get(0),userId,userDo.getReferrerId(), 3);
		}else{//20-26 给B网打款,改成打给平台
			saveFx(userId,groupName,"升级到B网由平台代收，收满后代打");
		}
		
		
		//给推荐人打款
		//saveReferrer(userId, userDo.getReferrerId(),groupName);
		saveReferrer(userId, 0l,groupName);
		
		
		//sort = 18 给用户组长发升级消息
		if(sort == 18){
			SysSmsDo newSysSmsDo = new SysSmsDo();
			newSysSmsDo.setMsgType(1);
			newSysSmsDo.setMsg("升级到B网");
			newSysSmsDo.setUserId(groupUserDoWrap.getChildByLevel(1).get(0).getUserId());
			newSysSmsDo.setGroupName(groupName);
			newSysSmsDo.setNetWork("A");
			newSysSmsDo.setStatus(1);
			newSysSmsDo.setCreateTime(new Date());
			sysSmsService.addSysSms(newSysSmsDo);
		}
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

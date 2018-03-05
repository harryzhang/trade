package com.redpack.wallet;

import java.util.List;
import java.util.Map;

import com.redpack.base.result.IResult;
import com.redpack.wallet.model.WalletDo;

public interface IWalletService {
	
	/**
	 *查询打款记录列表
	 */
	public List<Map> selectUserSk(Long skUserId,Integer skStatus);
	
	/**
	 *查询付款记录列表
	 */
	public List<Map> selectUserFk(Long fkUserId,Integer fkStatus);

	/**
	 * 付款确认
	 * @param id  操作用户
	 * @param recordId 
	 * @return
	 */
	public IResult confirmFK(Long optUserId, long recordId);

	/**
	 * 收款确认
	 * @param id 操作用户
	 * @param recordId
	 * @return
	 */
	public IResult confirmSK(Long optUserId, long recordId);
	
	/**
	 * 查找打款记录
	 * @param paraMap
	 * @return
	 */
	public List<WalletDo> selectWallet(Map<String, Object> paraMap);
	
	
	/**
	 * 失效,找排队的补充
	 * @param walletDo
	 */
	public void replaceUser(WalletDo walletDo);

	/**
	 * 找未处理的记录的人
	 * @return
	 */
	public List<WalletDo> selectOverTimePerson(Map<String, Object> paraMap);
	
	/**
	 * 处理等待分盘的用户
	 * @param groupName
	 */
	public void processWaitingUser(String groupName);
	
	public void updateWalletById(WalletDo newWalletDo);

}

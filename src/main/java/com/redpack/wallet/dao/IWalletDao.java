


package com.redpack.wallet.dao;

/**
 * 我的财富管理
 */
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.redpack.wallet.model.WalletDo;

@Repository
public interface IWalletDao {

	
	/**
	 *查询打款记录列表
	 */
	public List<Map> selectUserSk(Map<String,Object> param);
	
	/**
	 *查询付款记录列表
	 */
	public List<Map> selectUserFk(Map<String,Object> param);
	
	/**
	 *增加财富记录列表
	 */
	public int addWallet(WalletDo walletDo);

	public void updateWalletById(WalletDo newWalletDo);
	
	public void updateWalletByUserId(WalletDo newWalletDo);

	public List<WalletDo> selectWallet(Map<String, Object> paraMap);

	/**
	 * 未处理记录的人
	 * @return
	 */
	public List<WalletDo> selectOverTimePerson(Map<String, Object> paraMap);

	/**
	 * 是否存在未收款确认， 未付款确认的记录 
	 * @param groupName
	 * @return
	 */
	public List<WalletDo> selectUnConfirmRecord(String groupName);
	
	
}

package com.redpack.account.service;

import com.redpack.account.model.UserDo;
import com.redpack.grade.model.GroupUserDo;

/**
 * 打款逻辑
 * @author harry
 *
 */
public interface IMoneyMsg {
	
	public void processMoney(UserDo uer, GroupUserDo groupUser);

}

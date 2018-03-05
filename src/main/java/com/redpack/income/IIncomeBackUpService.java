package com.redpack.income;

import java.util.Date;

public interface IIncomeBackUpService {

	/**
	 * 备份帐户表
	 * @param now
	 */
	void backupAccount(Date now);	

}

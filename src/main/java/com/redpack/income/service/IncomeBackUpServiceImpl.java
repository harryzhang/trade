/*
 * Powered By  huangzl QQ: 272950754
 * Web Site:  
 * Since 2008 - 2016
 */

package com.redpack.income.service;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redpack.account.faced.IBizUserAccountService;
import com.redpack.income.IIncomeBackUpService;

/**
 * @author  huangzl QQ: 272950754
 * @version 1.0
 * @since 1.0
 */


@Service("incomeBackUpService")
public class IncomeBackUpServiceImpl implements IIncomeBackUpService {

	private final Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private IBizUserAccountService bizUserAccountService;
	
	@Override
	public void backupAccount(Date now) {
		logger.info("开始备份 :"+now);
		bizUserAccountService.backupAccount(now);
		logger.info("结束备份 :"+now);
	}
	
	

}

package com.redpack.income;

import java.util.Date;
import java.util.List;

import com.redpack.account.model.BizUserAccountDo;

public interface IIncomeTaskAssignSevice {

	/**
	 * 执行分红
	 * @param now
	 */
	void execIncome(Date now);

	void execIncomeNotFeiHong(Date jobDay);
	

}

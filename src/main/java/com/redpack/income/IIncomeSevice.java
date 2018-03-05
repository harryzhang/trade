package com.redpack.income;

import java.util.List;

import com.redpack.account.model.BizUserAccountDo;

public interface IIncomeSevice {

	void calculateIncome(List<BizUserAccountDo> userAccountLst,int taskId,IIncomeTotalService totalService);

}

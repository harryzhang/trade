/*
 * Powered By  huangzl QQ: 272950754
 * Web Site:  
 * Since 2008 - 2016
 */

package com.redpack.income.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.redpack.account.model.BizUserAccountDo;
import com.redpack.income.IIncomeSevice;
import com.redpack.income.IIncomeTaskAssignSevice;
import com.redpack.income.IIncomeTotalService;

/**
 * @author  huangzl QQ: 272950754
 * @version 1.0
 * @since 1.0
 */


@Service("incomeService")
public class IncomeServiceImpl implements IIncomeSevice {

	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private OnePersonIncomeService  onePersonIncomeService;
	
	
	@Async
	public void calculateIncome(List<BizUserAccountDo> userAccountLst,int taskId,IIncomeTotalService totalService) {
		
		logger.info("======================用户分红job"+taskId+" 开始==================================");
		
		try{
			
			if(null == userAccountLst || userAccountLst.isEmpty()){
				return;
			}
			
			//获取分红配置
			Map<String, QuotaBean> qutoConfigMap = onePersonIncomeService.getQuota();
			if(null == qutoConfigMap || qutoConfigMap.isEmpty()){
				logger.error("分红配置错误");
				return;
			}
			
			//List<QuotaBean> configQuotaList = QuotaUtil.map2List(qutoConfigMap);
			
			MuiltLevelBean muiltLevelBean1 = onePersonIncomeService.getMuiltLevelConfig1();			
			MuiltLevelBean muiltLevelBean2 = onePersonIncomeService.getMuiltLevelConfig2();
			//end 获取分红配置
			
			for(BizUserAccountDo userAcc : userAccountLst){
				
				List<QuotaBean> quotaList = QuotaUtil.copyMap2List(qutoConfigMap);
				
				System.out.println("当前正在分红的用户："+userAcc.toString());
				if(null == userAcc.getAmount()){
					continue;
				}
				
				if(BigDecimal.ZERO.compareTo(userAcc.getAmount()) == 0 ){
					continue;
				}
				try{
					Map<String ,Object> retMap = onePersonIncomeService.calculateIncome(userAcc,quotaList);
					onePersonIncomeService.calculateMuiltLevelIncome(userAcc,retMap,muiltLevelBean1,muiltLevelBean2);
				}catch(Exception e){
					logger.error("=========================用户分红job"+taskId+" 错误=================================="+userAcc.toString());
					logger.error(e);
				}
			}
		}catch(Exception e){
			logger.error("=========================用户分红job"+taskId+" 错误==================================");
			logger.error(e);
		}finally{
			logger.info("========================用户分红job"+taskId+" end==================================");
			totalService.update(taskId);			
		}
		
	}

}

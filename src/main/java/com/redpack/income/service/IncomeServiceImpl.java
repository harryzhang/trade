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
import org.springframework.util.CollectionUtils;

import com.redpack.account.model.BizUserAccountDo;
import com.redpack.constant.WebConstants;
import com.redpack.income.IIncomeSevice;
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
			Map<String, QuotaBean> staticConfigMap = onePersonIncomeService.getQuota(WebConstants.STATIC_FEN_HONG);
			Map<String, QuotaBean> groupConfigMap = onePersonIncomeService.getQuota(WebConstants.GROUP_FEN_HONG);
			if(CollectionUtils.isEmpty(staticConfigMap) || CollectionUtils.isEmpty(groupConfigMap)){
				logger.error("分红配置错误");
				return;
			}
			
			//end 获取分红配置
			
			List<QuotaBean> staticConfigList = QuotaUtil.copyMap2List(staticConfigMap);
			List<QuotaBean> groupConfigList = QuotaUtil.copyMap2List(groupConfigMap);
			
			for(BizUserAccountDo userAcc : userAccountLst){
				
				System.out.println("当前正在分红的用户："+userAcc.toString());
				if(null == userAcc.getAmount()){
					continue;
				}
				
				if(BigDecimal.ZERO.compareTo(userAcc.getAmount()) == 0 ){
					continue;
				}
				try{
					onePersonIncomeService.calculateIncome(userAcc,staticConfigList);
					onePersonIncomeService.calculateGroupIncome(userAcc,groupConfigList);
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

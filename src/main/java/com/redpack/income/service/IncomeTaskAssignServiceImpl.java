/*
 * Powered By  huangzl QQ: 272950754
 * Web Site:  
 * Since 2008 - 2016
 */

package com.redpack.income.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redpack.account.faced.IBizUserAccountService;
import com.redpack.account.faced.IFeiHongService;
import com.redpack.account.faced.IUserServiceCache;
import com.redpack.account.model.BizUserAccountDo;
import com.redpack.constant.WebConstants;
import com.redpack.income.IIncomeBackUpService;
import com.redpack.income.IIncomeSevice;
import com.redpack.income.IIncomeTaskAssignSevice;
import com.redpack.income.IIncomeTotalService;

/**
 * @author  huangzl QQ: 272950754
 * @version 1.0
 * @since 1.0
 */


@Service("incomeTaskAssignService")
public class IncomeTaskAssignServiceImpl implements IIncomeTaskAssignSevice {

	private final Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private IBizUserAccountService bizUserAccountService;
	
	
	@Autowired
	private IFeiHongService manualFeiHongService;
	
	
	@Autowired
	private IIncomeSevice  IncomeSevice;
	
	@Autowired
	private IIncomeTotalService totalService;
	
	@Autowired
	private OnePersonIncomeService  onePersonIncomeService;
	
	@Autowired
	private IUserServiceCache userServiceCache;
	
	@Autowired
	private IIncomeBackUpService  incomeBackUpService;

	
	/**
	 * 1. 从参数配置里读取当天的收益率
	 * 2. 从参数配置里读取分红的，组成比例
	 * 3. 读取所有用户
	 * 4. 读取用户参与计算的 账号上的额度
	 * 5. 保存记录计算结果
	 */
	@Override
	public void execIncome(Date jobDay) {
		doIncome(jobDay,(IFeiHongService)bizUserAccountService);
	}
	
	/**
	 * 1. 从参数配置里读取当天的收益率
	 * 2. 从参数配置里读取分红的，组成比例
	 * 3. 读取所有用户
	 * 4. 读取用户参与计算的 账号上的额度
	 * 5. 保存记录计算结果
	 */
	@Override
	public void execIncomeNotFeiHong(Date jobDay){
		doIncome(jobDay,manualFeiHongService);
		
	}

	/**
	 * 1. 从参数配置里读取当天的收益率
	 * 2. 从参数配置里读取分红的，组成比例
	 * 3. 读取所有用户
	 * 4. 读取用户参与计算的 账号上的额度
	 * 5. 保存记录计算结果
	 */
	public void doIncome(Date jobDay,IFeiHongService feiHongService) {
				
		logger.info("======================用户分红job==================================");
		
		//先备份
		incomeBackUpService.backupAccount(jobDay);
				
		//清空分红临时记录
		bizUserAccountService.deleteFeiHongTemp();
		//清除缓存的配置
		onePersonIncomeService.cleanCache();
		//清楚用户缓存
		userServiceCache.cleanCach();
		
		//缓存用户积分账户
		onePersonIncomeService.loadJiFenAccount();
		
		//获取参与计算的账号
		Map<String,Object> parameterMap = new HashMap<String ,Object>();
		parameterMap.put("accountType", WebConstants.SECURITY_ACCOUNT);
		int cnt = feiHongService.selectFeiHongUserAccountCount(parameterMap);
		
		
		int pageCnt = 30;
		//int pageCnt = 2;
		double pageSizeTmp = cnt/pageCnt;
		int pagesize = (int) Math.floor(pageSizeTmp);
		
		int result = cnt%pageCnt;
		if(result != 0 ){
			pageCnt = pageCnt+1;
		}
		logger.info("分红总用户数:"+cnt);
		logger.info("分红总任务进程:"+pageCnt);
		logger.info("分红每个任务进程处理的用户数:"+pagesize);
				
		
		//添加任务到列表
		for(int i = 0 ; i <= pageCnt;i++ ){
			totalService.addTask(i);
		}
		
		//todo 测试
		//pagesize=2;
		
		parameterMap.put("pageSize", pagesize);
		for(int i = 0 ; i <= pageCnt;i++ ){
			int startRow = pagesize*i;
			parameterMap.put("startRow", startRow);
			List<BizUserAccountDo> userAccountLst = feiHongService.selectFeiHongUserAccount(parameterMap);
			//开始计算
	    	IncomeSevice.calculateIncome(userAccountLst,i,totalService);
		}
	}

}

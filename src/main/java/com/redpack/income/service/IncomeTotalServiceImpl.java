/*
 * Powered By  huangzl QQ: 272950754
 * Web Site:  
 * Since 2008 - 2016
 */

package com.redpack.income.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.redpack.account.faced.IBizUserAccountService;
import com.redpack.account.model.BizUserAccountDo;
import com.redpack.income.IIncomeTotalService;

/**
 * @author  huangzl QQ: 272950754
 * @version 1.0
 * @since 1.0
 */


@Service("incomeTotalService")
public class IncomeTotalServiceImpl implements IIncomeTotalService {

	private final Logger logger = Logger.getLogger(this.getClass());
	
	private List<Integer> taskList = new ArrayList<Integer>();
	private List<Integer> endTaskList = new ArrayList<Integer>();
	
	@Autowired
	private IBizUserAccountService  bizUserAccountService;
	


//	@Override
//	public void totalUserFeiHong() {
//		
//		logger.info("开始汇总分红额度");
//		List<BizUserAccountDo> feiHongList =  bizUserAccountService.selectUserAccountFromTemp();
//
//		int page = 20;
//		int count = feiHongList.size()/page; 
//		int yu = feiHongList.size() % page; 
//
//		for (int i = 0; i < page; i++) { 
//			List<BizUserAccountDo> subList = new ArrayList<BizUserAccountDo>(); 
//			if (i == (page-1)) { 
//				subList = feiHongList.subList(i * count, count * (i + 1) + yu); 
//			} else { 
//				subList = feiHongList.subList(i * count, count * (i + 1)); 
//			} 
//			try{
//				bizUserAccountService.updateUserAmountByUserIdAccountType(subList);
//			}catch(Exception e){
//				logger.error(e);
//			}
//		} 
//		
//		logger.info("end汇总分红额度");
//		
//	}

	
	@Override
	public void totalUserFeiHong() {
		
		logger.info("开始汇总分红额度");
		List<BizUserAccountDo> feiHongList =  bizUserAccountService.selectUserAccountFromTemp();

		for (int i = 0; i < feiHongList.size(); i++) { 
			try{
				bizUserAccountService.updateUserAmountByUserIdAccountType(feiHongList.get(i));
			}catch(Exception e){
				logger.error(e);
			}
		} 
		
		logger.info("end汇总分红额度");
		
	}


	@Async
	@Override
	public void update(int taskId) {
		endTaskList.add(taskId);
		if(taskList.size() == endTaskList.size()){
			totalUserFeiHong();
		}
		
	}




	@Override
	public void addTask(int taskId) {
		taskList.add(taskId);		
	}
	
}

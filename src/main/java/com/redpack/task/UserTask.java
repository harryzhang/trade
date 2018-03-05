package com.redpack.task;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.redpack.income.IIncomeTaskAssignSevice;
import com.redpack.utils.DateUtil;


@Component
public class UserTask extends DefaultTask {
	
	private static boolean  startStatus = true; //执行开关
	
	@Autowired
	private IIncomeTaskAssignSevice incomeTaskAssignSevice;
	
	
	@Scheduled(cron="0 1 0 * * ?")
	public void execute() {
	      super.execute();
    }	
	
	
	
	protected void doJob() {
		
		if(!startStatus){
			System.out.println("======================已 有一个用户分红job正在执行=================================");
			return;
		}
		startStatus = false;
		Date now = new Date();
		now = DateUtil.dateAddDay(now, -1);
		
		//开始分红
		incomeTaskAssignSevice.execIncome(now);
		startStatus = true;
	}
	

	protected String getJobName() {
		return "用户分红";
	}
	

	
}

package com.redpack.task;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.redpack.bet.IJingpaiTaskService;


@Component
public class JingPaiTask extends DefaultTask {
	
	private boolean  startStatus = true; //执行开关
	
	@Autowired
	private IJingpaiTaskService jingpaiTaskService;
	
	
	//@Scheduled(cron="0/10 * * * * ?")
	public void execute() {
	      super.execute();
    }	
	
	
	
	protected void doJob() {
		if(!startStatus){
			System.out.println("======================上次竞拍调度正在执行=================================");
			return;
		}
		startStatus = false;
		try{
			//判断竞拍是否成功
			jingpaiTaskService.betSuccess();
			
			//系统自动竞拍
			jingpaiTaskService.autoBet();
			
			
		}catch(Throwable e){//出错继续
			startStatus = true;
			e.printStackTrace();
		}
		startStatus = true;
	}
	
	protected String getJobName() {
		return "竞拍调度";
	}
}

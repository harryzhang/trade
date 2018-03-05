package com.redpack.task;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.redpack.bet.IJingpaiTaskService;


/**
 * 系统自动竞拍
 * @author harry
 *
 */
@Component
public class AddJingPaiTask extends DefaultTask {
	
	private boolean  startStatus = true; //执行开关
	
	@Autowired
	private IJingpaiTaskService jingpaiTaskService;
	
	
	//@Scheduled(cron="0/10 * * * * ?")
	public void execute() {
	      super.execute();
    }	
	
	
	
	protected void doJob() {
		if(!startStatus){
			System.out.println("======================上次自动竞拍调度任务正在执行=================================");
			return;
		}
		startStatus = false;
		try{
			//系统自动竞拍
			//jingpaiTaskService.autoBet();
			
		}catch(Throwable e){//出错继续
			startStatus = true;
			e.printStackTrace();
		}
		startStatus = true;
	}
	
	protected String getJobName() {
		return "自动竞拍调度";
	}
}

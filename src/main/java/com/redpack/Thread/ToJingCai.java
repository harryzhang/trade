/**
 * 
 */
package com.redpack.Thread;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.redpack.bet.IBetResultService;
import com.redpack.constant.WebConstants;
import com.redpack.param.IParamService;
import com.redpack.utils.DateUtil;

/**
 * @author MBENBEN
 *
 */
public class ToJingCai extends TimeThread{

	public static long liveTime = 300;
	private int openTime = 300;
	public static int period = 300;

	private IParamService paramService;
	private IBetResultService betResultService;
	
	private final Logger logger = Logger.getLogger(ToJingCai.class);
	
	public ToJingCai(String name,ServletContext servletContext) {
		super(name);
		
		
		//开始计算系统启动时距离晚上8点开奖剩余时间，以秒为单位
		liveTime = getInitNextOpenTime();
		System.out.println("距离8点开奖时间还有："+liveTime);
		
		 WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
		 //开奖时间 开奖周期
		 paramService = (IParamService) context.getBean("paramService");
		 betResultService = (IBetResultService) context.getBean("betResultService");
		 if( null != paramService){
			BigDecimal refferRate = paramService.getRateByName(WebConstants.JINCAI_OPEN_TIME);
			period = betResultService.getMaxPeriod() + 1;
			if( null != refferRate){
				openTime = refferRate.intValue();
			}
		 }
		 openTime=86400;
	}

	//下面的方法是5分钟一次的开奖定时器
	/*
	@Override
	protected void task() {
		//暂停1秒
		try {
			sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.debug("===============开奖倒计时：" + period + "=" + liveTime);
		//开始开奖
		if( liveTime == 30){
			
			System.out.println("=======开奖============" + period);
			 period = period +1;
			//线程开奖
			Thread t = new Thread(new Runnable(){  
	            public void run(){  
	            	betResultService.openGame(period -1 );
	            }});  
	        t.start();  
	       
		}
		if(liveTime >0){
			liveTime -- ;
		}else{
			liveTime = openTime;
		}
		
	}
	*/

	
	/**
	 * 开始计算系统启动时距离晚上8点开奖剩余时间，以秒为单位
	 * @return
	 */
	private static long getInitNextOpenTime() {
		long currentMills = System.currentTimeMillis();
		Date d = new Date(currentMills);
		String daystr = DateUtil.formatDate(d, "yyyy-MM-dd");
		Date day = DateUtil.parseDate(daystr);
		Calendar a = Calendar.getInstance();
		a.setTime(day);
		a.set(Calendar.HOUR_OF_DAY, 20);
		//8点前
		if(a.getTime().getTime()>d.getTime()){
			return (a.getTime().getTime() - d.getTime())/1000;
		}else{//8点后
			a.add(Calendar.DAY_OF_MONTH, 1);
			return (a.getTime().getTime() - d.getTime())/1000;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(getInitNextOpenTime());
	}

	//下面的方法是每天晚上8点开奖
	protected void task() {
		//暂停1秒
		try {
			sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//开始开奖
		if( liveTime == 30){
			
			System.out.println("=======开奖============" + period);
			 period = period +1;
			//线程开奖
			Thread t = new Thread(new Runnable(){  
	            public void run(){  
	            	betResultService.openGame(period -1 );
	            }});  
	        t.start();  
	       
		}
		if(liveTime >0){
			liveTime -- ;
		}else{
			liveTime = openTime;
		}
		
	}

}

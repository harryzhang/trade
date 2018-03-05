package com.redpack.account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Component("moneyMsgFactory")
public class MoneyMsgFactory implements ApplicationContextAware {	
	
	@Autowired
	private static ApplicationContext applicationContext;    //Spring应用上下文环境 
	
	public  void setApplicationContext(ApplicationContext applicationContext) {
		MoneyMsgFactory.applicationContext = applicationContext;
	}


	public static IMoneyMsg getMoneyMsg(String beanId){
		return (IMoneyMsg)MoneyMsgFactory.applicationContext.getBean(beanId);
	}

}

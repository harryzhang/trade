package com.redpack.pay.factory;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.redpack.pay.service.IThirdPayService;


@Component("thirdPayServiceFactory")
public class ThirdPayServiceFactory implements  ApplicationListener<ContextRefreshedEvent>, ApplicationContextAware {
	
	private Logger logger = Logger.getLogger("ThirdPayServiceFactory");
	private ApplicationContext context;
	private Map<String,IThirdPayService> payMap = new HashMap<String,IThirdPayService>();
	private  static ThirdPayServiceFactory _factory = null;
	
	
	private ThirdPayServiceFactory(){
		super();
	}
	public static ThirdPayServiceFactory createPayFactory() {
		return _factory;
	}
	
	
	//支付的key转支付实现类的bean spring id名
	private Map<String,String> payKey2BeanIdMap = new HashMap<String,String>();
	
	public final static String SAOMA_PAY = "SAOMA_PAY";// 扫码支付
	public final static String WX_PAY = "WX_PAY";// 微信支付
	public final static String WAP_PAY = "WAP_PAY";// WAP支付
	
	    
	public IThirdPayService  getThirdPayService(String payType){
		IThirdPayService payService =  payMap.get(payType);
		if(null == payService){
			throw new RuntimeException("不支持的支付方式");
		}
		return payService;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.context = applicationContext;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        logger.debug("初始化支付工厂开始");
        key2PayImplBean();
        if(payKey2BeanIdMap.isEmpty()){
        	logger.warn("支付工厂初始化找不到支付实现类");
        }
        for(Map.Entry<String, String> entry : payKey2BeanIdMap.entrySet()){
        	IThirdPayService  payservice = (IThirdPayService)context.getBean(entry.getValue());
        	payMap.put(entry.getKey(), payservice);
        }
        _factory= this;
        logger.debug("初始化支付工厂结束");
    }
	
	private void key2PayImplBean(){
		payKey2BeanIdMap.put(SAOMA_PAY, "saomaPayService");
		payKey2BeanIdMap.put(WX_PAY, "wxPayService");
		//payKey2BeanIdMap.put(WAP_PAY, "wapPayService");
		payKey2BeanIdMap.put(WAP_PAY, "saomaPayService");
	}

}

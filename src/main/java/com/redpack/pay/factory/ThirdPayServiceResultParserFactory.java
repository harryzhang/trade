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
import com.redpack.pay.service.IThirdPayServiceResultParser;

@Component("thirdPayServiceResultParserFactory")
public class ThirdPayServiceResultParserFactory implements  ApplicationListener<ContextRefreshedEvent>, ApplicationContextAware {
	
	private Logger logger = Logger.getLogger("ThirdPayServiceResultParseFactory");
	private ApplicationContext context;
	private Map<String,IThirdPayServiceResultParser> payMap = new HashMap<String,IThirdPayServiceResultParser>();
	private  static ThirdPayServiceResultParserFactory _factory = null;
	
	private ThirdPayServiceResultParserFactory(){
		super();
	}
	public static ThirdPayServiceResultParserFactory createPayServiceResultParserFactory() {
		return _factory;
	}
	
	//支付的key转支付实现类的bean spring id名
	private Map<String,String> payKey2BeanIdMap = new HashMap<String,String>();
	
	public final static String SAOMA_PAY = "SAOMA_PAY";// 扫码支付
	public final static String WX_PAY = "WX_PAY";// 微信支付
	public final static String WAP_PAY = "WAP_PAY";// WAP支付
	
	    
	public IThirdPayServiceResultParser getThirdPayServiceResultParser(String payType){
		IThirdPayServiceResultParser payService =  payMap.get(payType);
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
        logger.debug("初始化支付结果解析工厂开始");
        key2PayImplBean();
        if(payKey2BeanIdMap.isEmpty()){
        	logger.warn("支付工厂初始化找不到支付实现类");
        }
        for(Map.Entry<String, String> entry : payKey2BeanIdMap.entrySet()){
        	IThirdPayServiceResultParser  parseservice = (IThirdPayServiceResultParser)context.getBean(entry.getValue());
        	payMap.put(entry.getKey(), parseservice);
        }
        _factory = this;
        logger.debug("初始化支付结果解析结束");
    }
	
	private void key2PayImplBean(){
		payKey2BeanIdMap.put(SAOMA_PAY, "saomaPayServiceResultParse");
		payKey2BeanIdMap.put(WX_PAY, "wxPayServiceResultParse");
		//payKey2BeanIdMap.put(WAP_PAY, "wxPayServiceResultParse");
		payKey2BeanIdMap.put(WAP_PAY, "saomaPayServiceResultParse");
	}

	

}

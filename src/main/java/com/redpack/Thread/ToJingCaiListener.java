package com.redpack.Thread;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;

/**
 * 竞彩启动计时
 * @author MBENBEN
 *
 */
public class ToJingCaiListener implements ServletContextListener {
	private static Log log = LogFactory.getLog(ToJingCaiListener.class);
	private boolean success = true;
	private ApplicationContext wac = null;
	public void contextDestroyed(ServletContextEvent sce) {

	}

	public void contextInitialized(ServletContextEvent sce) {
		systemStartup(sce.getServletContext());
	}

	/**
	 * 应用平台启动
	 */
	private void systemStartup(ServletContext servletContext) {
		
		ToJingCai toJingCai = new ToJingCai("jingcai-livetime",servletContext);
		toJingCai.start();
	}

	

}

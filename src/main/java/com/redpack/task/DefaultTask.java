package com.redpack.task;

import org.apache.log4j.Logger;


/** 
 *  
 * @author: zhangyunhmf
 * @date 2014年10月15日 上午10:00:26
 */
public abstract class DefaultTask implements ITask {
    
    protected static Logger logger = Logger.getLogger(DefaultTask.class);
    
    
    protected abstract String getJobName(); 

    /* (no-Javadoc) 
     * <p>Title: execute</p> 
     * <p>Description: </p>  
     */
    @Override
    public void execute() {
    	long starttime= System.currentTimeMillis();
        logger.info(getJobName()+"开始=======================");
        doJob();
        logger.info(getJobName()+"结束======================="+(System.currentTimeMillis()-starttime)/1000);
    }
    
    protected abstract void doJob();
    
    

}

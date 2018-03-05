
/**  
 * @Project: deposit-biz-common
 * @Package com.hehenian.deposit.common.account
 * @Title: IUserService.java
 * @Description: TODO
 * @author:  zhangyunhua
 * @date 2015年3月5日 上午10:46:01
 * @Copyright: HEHENIAN Co.,Ltd. All rights reserved.
 * @version V1.0  
 */
package com.redpack.param;

import java.math.BigDecimal;
import java.util.Map;

import com.redpack.income.service.MuiltLevelBean;
import com.redpack.income.service.QuotaBean;
import com.redpack.param.model.ParamDo;

/**
 * 
 * 功能说明：系统参数设置
 * @author baishui
 * @2016年3月11日 上午11:53:05
 *
 * @com.redpack.base.param.IParamService.java
 */
public interface IParamService {

  
	/**
	 * 根据参数名获取参数值 
	 * @param paramName
	 * @return
	 */
    String getByName(String paramName);
   
    /**
     * 根据参数名获取百分比数据 例 2 返回0.02 
     * @param paramName
     * @return
     */
    BigDecimal getRateByName(String paramName);
    
    /**
     * 根据参数名设置参数
     * @param paramName
     * @return
     */
    void setParamName(ParamDo paramDo);
    
    /**
	 * 根据等级获取入会金额，3级 3个人平分入分金额，其他一个人独得；
	 * @param level
	 * @return
	 */
    public Double getPayMoney(int level, String group);
    
    /**
     * 获取感恩金额
     * @return
     */
    public Double getReferrerMoney();

    /**
     * 获取配额配置
     * @param name 配置项名称
     * @return
     */
	Map<String, QuotaBean> getQuoTa(String name);

	/**
	 * 获取多级收益配置
	 * @param name
	 * @paam scheme 方案 
	 * @return
	 */
	MuiltLevelBean getMuiltLevelConfig(String name,String scheme);


}


/**  
 * @Project: deposit-biz-service
 * @Package com.hehenian.deposit.service.account
 * @Title: UserServiceImpl.java
 * @Description: TODO
 * @author:  zhangyunhua
 * @date 2015年3月5日 上午10:58:12
 * @Copyright: HEHENIAN Co.,Ltd. All rights reserved.
 * @version V1.0  
 */
package com.redpack.param.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redpack.income.service.MuiltLevelBean;
import com.redpack.income.service.QuotaBean;
import com.redpack.income.service.QuotaUtil;
import com.redpack.param.IParamService;
import com.redpack.param.dao.IParamDao;
import com.redpack.param.model.ParamDo;
import com.redpack.utils.CalculateUtils;
import com.redpack.utils.StringUtil;

/**
 * 
 * 功能说明：参数设定
 * @author baishui
 * @2016年3月11日 上午11:57:42
 *
 * @com.redpack.service.param.ParamServiceImpl.java
 */
@Service("paramService")
public class ParamServiceImpl implements IParamService {

	@Autowired	
    private IParamDao  paramDao;
	
	@Override
	public String getByName(String paramName) {
		// TODO Auto-generated method stub
		return paramDao.getByName(paramName);
	}

	@Override
	public void setParamName(ParamDo paramDo) {
		// TODO Auto-generated method stub
		paramDao.updateParam(paramDo);
	}
	
	
	/**
	 * 根据等级获取入会金额，3级 3个人平分入分金额，其他一个人独得；
	 * @param level
	 * @return
	 */
	public Double getPayMoney(int level, String group){
		
		//A网金额分配
		if("A".equals(group)){
			String payMoney = getByName("PAY_MONEY_A");
			if(null != payMoney && !"".equals(payMoney)){
				Double amount = StringUtil.strToDouble(payMoney);
				if(level == 2){
					amount = amount /3;
				}
				
				return amount;
			}
		}else if("B".equals(group)){
			String payMoney = getByName("PAY_MONEY_B");
			Double amount = StringUtil.strToDouble(payMoney);
			if(level == 3){
				amount = amount /9;
			}
			
			return amount;
		}
		
		
			
		return 0.0;
	}
	
	/**
	 * 根据等级获取入会金额，3级 3个人平分入分金额，其他一个人独得；
	 * @param level
	 * @return
	 */
	public Double getPayMoneyOrderSort(int level, String group){
		
		//A网金额分配
		if("A".equals(group)){
			String payMoney = getByName("PAY_MONEY_A");
			if(null != payMoney && !"".equals(payMoney)){
				Double amount = StringUtil.strToDouble(payMoney);
				if(level == 3){
					amount = amount /3;
				}
				
				return amount;
			}
		}else if("B".equals(group)){
			String payMoney = getByName("PAY_MONEY_B");
			Double amount = StringUtil.strToDouble(payMoney);
			if(level == 3){
				amount = amount /9;
			}
			
			return amount;
		}
		
		
			
		return 0.0;
	}
	
	public Double getReferrerMoney(){
		String payMoney = getByName("REFERRER_MONEY");
		if(null != payMoney && !"".equals(payMoney)){
			Double amount = StringUtil.strToDouble(payMoney);
			return amount;
		}
		return 0.0;
			
	}

	@Override
	public BigDecimal getRateByName(String paramName) {
		// TODO Auto-generated method stub
		String rate = getByName(paramName);
		
		BigDecimal returnRate = BigDecimal.ZERO;
		if(org.apache.commons.lang.StringUtils.isNotBlank(rate)){
			returnRate = (new BigDecimal(rate)).divide(new BigDecimal(100), 2,RoundingMode.HALF_UP);
		}
		return returnRate;
	}

	
	
	/**
     * 获取配额配置
     * @param name
     * @return
     */
	@Override
	public Map<String, QuotaBean> getQuoTa(String name) {
		String configAcc = this.getByName(name+"_ACCOUNT");		
		String configQuota = this.getByName(name+"_QUOTA");
		String rate = this.getByName(name+"_RATE");
		
		if(StringUtils.isBlank(configAcc)){
			throw new RuntimeException("没有配置分红的name:"+name+"_ACCOUNT");
		}
		
		if(StringUtils.isBlank(configQuota)){
			throw new RuntimeException("没有配置分红的name:"+name+"_QUOTA");
		}
		
		
		if(StringUtils.isBlank(rate)){
			throw new RuntimeException("没有配置分红的name:"+name+"_RATE");
		}
		
		return QuotaUtil.convertString2Map(configAcc, configQuota, rate);
	}

	
	/**
     * 获取多级收益的配置
     * @param name
     * @paam scheme 方案 
     * @return
     */
	@Override
	public MuiltLevelBean getMuiltLevelConfig(String name,String scheme) {
		
		String level = this.getByName(name+"_LEVEL");   //团队奖拿多少代级别 :分割  , 比如:   1:2:3:4:5:6
		String define = this.getByName(name+"_LEVEL_DEFINE");   //股东级别 :分割  , 比如:   1:2:3:4:5:6
		if(StringUtils.isBlank(level)){
			throw new RuntimeException("没有配置团队奖name:"+name+"_LEVEL_DEFINE");
		}
		String[] defineArray = define.split(":");
		String[] percentArray = new String[defineArray.length];
		for(int i = 0 ; i <defineArray.length;i++){
			String de = defineArray[i];
			String levelPercent1 = this.getByName(name+"_LEVEL_PERCENT_"+de); //每个级别 那收益占比  比如:   0.2:0.1:0.05:0.05:0.05:0.05
			if(StringUtils.isBlank(levelPercent1)){
				throw new RuntimeException("没有配置团队奖name:"+name+"_LEVEL_PERCENT");
			}
			percentArray[i] = levelPercent1;
		}
		
		String levelGrade = this.getByName(name+"_LEVEL_GRADE"); // 需要到达对应的级别 , 比如：  1:2:3:4:5
		String baseAccountName = this.getByName(name+"_LEVEL_BASE_ACCOUNT"); // 参与计算的账户 , 比如：  1:2:3:4:5
		
		if(StringUtils.isBlank(level)){
			throw new RuntimeException("没有配置团队奖name:"+name+"_LEVEL");
		}
		
		if(StringUtils.isBlank(levelGrade)){
			throw new RuntimeException("没有配置团队奖name:"+name+"_LEVEL_BASE_ACCOUNT");
		}
		
		
		MuiltLevelBean  muiltlevelConfigBean = MuiltLevelBean.ceateQuotaMuiltLevelBean(level,
																						levelGrade,
																						baseAccountName,
																						defineArray,
																						percentArray);
		Map<String, QuotaBean> quotaMap =  getQuoTa(name+scheme);
		muiltlevelConfigBean.setQuotaMap(quotaMap);
		
		return  muiltlevelConfigBean;
		
	}


}

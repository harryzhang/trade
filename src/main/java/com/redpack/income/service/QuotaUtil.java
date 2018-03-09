package com.redpack.income.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.redpack.account.model.UserDo;
import com.redpack.constant.WebConstants;
import com.redpack.utils.CalculateUtils;

public class QuotaUtil {
	
	/**
	 * 从参数配置读出 : 分割的账号类型和对应的比例，  是账号和比例对应位置的两条参数配置
	 * @param quotaConfig
	 * @return
	 */
	public static  Map<String, QuotaBean> convertString2Map(String quotaAccountConfig,
															String quotaPercentConfig,
															String rateConfig ,
															String fenHongType){
		
		if(StringUtils.isBlank(quotaAccountConfig)){
			return Collections.EMPTY_MAP;
		}
		
		
		Map<String, QuotaBean> retMap= new HashMap<String, QuotaBean>();
		String[]  accArray = quotaAccountConfig.split(":");
		for(String account : accArray){
			QuotaBean  qt = new QuotaBean(account,BigDecimal.ZERO,rateConfig,fenHongType);
			retMap.put(account, qt);
		}
		
		if(StringUtils.isNotBlank(quotaPercentConfig)){
			
			String[]  accPercentArray = quotaPercentConfig.split(":");
			for(int i = 0 ; i < accArray.length ; i ++){
				//账号
				String accountName=accArray[i];
				
				//从占比数组取占比
				String percent  = null;
				if(accPercentArray.length>i){
					percent = accPercentArray[i];
				}
				
				if(null != percent){
					QuotaBean quota= retMap.get(accountName);
					quota.setPercent(new BigDecimal(percent));
				}
			}
		}
		
		return retMap;
		
	}
	
	/**
	 * 收益分配到各个账户
	 * @param qutoConfigMap
	 * @param amount
	 * @param userId
	 * @return
	 */
	public static List<QuotaBean> calculate(List<QuotaBean> qutoList,	
											BigDecimal amount,
											Long userId,
											int level) {
		if(null == qutoList  || qutoList.isEmpty()){
			return Collections.EMPTY_LIST;
		}
		if(null == amount){
			return Collections.EMPTY_LIST;
		}
		
		
		for(QuotaBean quto : qutoList){
			quto.setBaseAmount(amount);
			quto.setUserId(userId);
			quto.setLevel(level);
			quto.calculateIncome();
			
		}
		
		return qutoList;
		
	}
	
	/**
	 * 收益分配到各个账户
	 * @param qutoConfigMap
	 * @param amount
	 * @param userId
	 * @return
	 */
	public static List<QuotaBean> calculateGroup(List<QuotaBean> qutoList,	
			BigDecimal groupAmt,
			BigDecimal refAmt,
			Long userId,
			int level) {
		
		if(null == qutoList  || qutoList.isEmpty()){
			return Collections.EMPTY_LIST;
		}
		if(null == refAmt){
			return Collections.EMPTY_LIST;
		}
		if(null == groupAmt){
			return Collections.EMPTY_LIST;
		}
		
		
		for(QuotaBean quto : qutoList){
			quto.setBaseAmount(groupAmt);
			quto.setUserId(userId);
			quto.setLevel(level);
			quto.calculateGroupIncome(refAmt);
			
		}
		
		return qutoList;
		
	}
	
	
	/**
	 * 收益分配到各个账户
	 * @param qutoConfigMap
	 * @param amount
	 * @param userId
	 * @return
	 */
	public static List<QuotaBean> calculate(Map<String, QuotaBean> qutoConfigMap,	
											BigDecimal amount,
											Long userId,
											int level) {
		if(null == qutoConfigMap  || qutoConfigMap.isEmpty()){
			return Collections.EMPTY_LIST;
		}
		if(null == amount){
			return Collections.EMPTY_LIST;
		}
		
		List<QuotaBean> qutoList = map2List(qutoConfigMap);
		
		for(QuotaBean quto : qutoList){
			quto.setBaseAmount(amount);
			quto.setUserId(userId);
			quto.setLevel(level);
			quto.calculateIncome();
			
		}
		
		return qutoList;
		
	}



	

	
	/**
	 * 计算多层收益
	 * @param muiltLevelBean
	 * @param quotaBeanLst
	 * @param userDo
	 * @param currentLevel
	 * @return
	 */
	public static List<QuotaBean> calculateMuiltLevelIncome(MuiltLevelBean muiltLevelBean, 
															BigDecimal base,
															UserDo parent, 
															String currentLevel) {
		
		int level = Integer.valueOf(currentLevel);
		//获取用户
		//UserDo parent = getParentByLevel(userDo, level);
		if(parent == null){
			return Collections.EMPTY_LIST;
		}
		
		int userGrade  = parent.getGrade() == null ? 0 : parent.getGrade().intValue();
		//配置期望的grade
		int configGrade = muiltLevelBean.getGradeByLevel(currentLevel);
		if(userGrade<configGrade){
			//System.out.println("计算团队奖金：用户级别没有达到：parent:"+parent+"  期望grade："+ configGrade+"实际grade："+userGrade);
			return Collections.EMPTY_LIST;
		}
		
		//乘层级的比例系数
		BigDecimal percent =  muiltLevelBean.getLevelPercent(userGrade,level);
		if(percent.compareTo(BigDecimal.ZERO) == 0){
			System.out.println("计算团队奖金：没有正确配置层级系数");
			return Collections.EMPTY_LIST;
		}
		BigDecimal newbase = percent.multiply(base);
		return calculate(muiltLevelBean.getQuotaList(),newbase, parent.getId(),level);
		
	}


	/**
	 * 获取用户 parent
	 * @param userDo
	 * @param currentLevel
	 * @return
	 */
	public static UserDo getParentByLevel(UserDo userDo, int level) {
		if(userDo == null){
			return null;
		}
		
		UserDo parent = userDo;
		for(int i = 0 ; i< level; i++){
			parent = parent.getParentDo();
			if( null == parent){
				return null;
			}
		}
		return parent;
	}

	/**
	 * map 转 list
	 * @param qutoConfigMap
	 * @return
	 */
	public static List<QuotaBean> map2List(Map<String, QuotaBean> qutoConfigMap) {
		
		List<QuotaBean> lst = new ArrayList<QuotaBean>();
		if(qutoConfigMap == null || qutoConfigMap.isEmpty()){
			return lst;
		}
		
		for(Map.Entry<String, QuotaBean> entry  : qutoConfigMap.entrySet()){
			lst.add(entry.getValue());
		}
		return lst;
	}
	
	
	/**
	 * map 转 list
	 * @param qutoConfigMap
	 * @return
	 */
	public static List<QuotaBean> copyMap2List(Map<String, QuotaBean> qutoConfigMap) {
		
		List<QuotaBean> lst = new ArrayList<QuotaBean>();
		if(qutoConfigMap == null || qutoConfigMap.isEmpty()){
			return lst;
		}
		
		for(Map.Entry<String, QuotaBean> entry  : qutoConfigMap.entrySet()){
			QuotaBean quota = entry.getValue();
			QuotaBean copyQuota = new QuotaBean(quota.getAccount(),quota.getPercent(),quota.getRateConfig(),quota.getFenHongType());
			copyQuota.setAccount(quota.getAccount());
			copyQuota.setPercent(quota.getPercent());
			copyQuota.setIncomeRate(quota.getIncomeRate());
			copyQuota.setBaseAmount(quota.getBaseAmount());
			copyQuota.setCalDesc(quota.getCalDesc());
			copyQuota.setIncomeAmount(quota.getIncomeAmount());
			copyQuota.setLevel(quota.getLevel());
			copyQuota.setUserId(quota.getUserId());
			lst.add(copyQuota);
		}
		return lst;
	}
	
	/**
	 * 多级收益计算的获取参与计算的基数： 累计配置组成基数账户 的金额
	 * @param quotaBeanLst
	 * @param calculateAccountName
	 * @return
	 */
	public static BigDecimal getBaseAmount(Map<String,Object> paraMap) {
		
		if(null == paraMap || paraMap.isEmpty()){
			System.out.println("多级收益计算没有配置组成基数账户");
			return BigDecimal.ZERO;
		}
		
		BigDecimal  fenHong = (BigDecimal)paraMap.get(WebConstants.STATIC_FEN_HONG);
		if(null == fenHong){
			return BigDecimal.ZERO;
		}
		return fenHong;
	}
	
	


}

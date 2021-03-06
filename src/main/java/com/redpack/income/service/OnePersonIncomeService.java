/*
 * Powered By  huangzl QQ: 272950754
 * Web Site:  
 * Since 2008 - 2016
 */

package com.redpack.income.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redpack.account.faced.IBizUserAccountService;
import com.redpack.account.faced.ITotalAccountService;
import com.redpack.account.faced.IUserServiceCache;
import com.redpack.account.model.BizUserAccountDo;
import com.redpack.account.model.UserDo;
import com.redpack.constant.AccountMsg;
import com.redpack.constant.WebConstants;
import com.redpack.param.IParamService;

/**
 * @version 1.0
 * @since 1.0
 */


@Service("onePersonIncomeService")
public class OnePersonIncomeService   {

	private final Logger logger = Logger.getLogger(this.getClass());
	
    
	@Autowired
	private IBizUserAccountService bizUserAccountService;
	
	@Autowired
	private ITotalAccountService totalAccountService;
	
	
	@Autowired
	private IUserServiceCache userServiceCache;
	
	@Autowired
	private IParamService paramService;

	private BigDecimal configChooseAmt = BigDecimal.ZERO;

	private Map<Long,BigDecimal> jiFenMap = new HashMap<Long,BigDecimal>();
	
	private Map<Long,BizUserAccountDo> userAccountCacheMap = new HashMap<Long,BizUserAccountDo>();


	private ThreadLocal<Map<String,Object>> context = new ThreadLocal<Map<String,Object>>();

	/**
	 * 计算分红
	 * @param userAcc
	 * @return
	 */
	public void calculateIncome(BizUserAccountDo userAcc,List<QuotaBean> qutoConfigLst) {
		try{
			List<QuotaBean> qutoLst = QuotaUtil.calculate(qutoConfigLst, userAcc.getAmount(),userAcc.getUserId(),0);
			saveQuota(qutoLst, AccountMsg.type_4);
			
		}catch(Exception e ){
			logger.error(e);
		}
		return;
	}
	
	/**
	 * 计算分红
	 * @param userAcc
	 * @return
	 */
	public void calculateGroupIncome(BizUserAccountDo userAcc,List<QuotaBean> qutoConfigLst) {
		try{
			
			if(null == qutoConfigLst){
				System.out.println("团队奖没有配置");
				return;
			}
			
			
			//获取团队人员
			UserDo currentUser = userServiceCache.getById(userAcc.getUserId());
			userServiceCache.getAllChildren(currentUser);
			UserDo leftChild =  currentUser.getLeftChild();
			UserDo rightChild = currentUser.getRightChild();
			
			//获取小区额度						
			BigDecimal rightAmt = totalAccountService.totalAmt(rightChild,WebConstants.SECURITY_ACCOUNT);
			BigDecimal leftAmt = totalAccountService.totalAmt(leftChild,WebConstants.SECURITY_ACCOUNT);
			BigDecimal groupAmt = leftAmt.compareTo(rightAmt)>0?rightAmt:leftAmt;
			
			//获取直推额度
			BigDecimal refAmt = totalAccountService.totalReferAmt(currentUser.getId(),WebConstants.SECURITY_ACCOUNT);
			
			List<QuotaBean> qutoLst = QuotaUtil.calculateGroup(qutoConfigLst, groupAmt,refAmt,userAcc.getUserId(),0);
			saveQuota(qutoLst, AccountMsg.type_5);
			
		}catch(Exception e ){
			logger.error(e);
		}
		return;
	}
	
	

	/**
	 * 计算团队奖
	 * @param userAcc
	 * @param quotaBeanLst
	 */
	public void calculateMuiltLevelIncome(BizUserAccountDo userAcc,	Map<String,Object> paraMap,MuiltLevelBean muiltLevelBean1,MuiltLevelBean muiltLevelBean2) {
		
		if(null == muiltLevelBean1){
			System.out.println("团队奖没有配置");
			return;
		}
		if(null == muiltLevelBean2){
			System.out.println("团队奖没有配置");
			return;
		}
		
		//获取所有推荐人
		UserDo userDo =userServiceCache.getAllParent(userAcc.getUserId(), muiltLevelBean1.getMaxLevel());	
		
		//逐层计算
		String[] levelArray  = muiltLevelBean1.getLevelArray();
		//获取基数
		BigDecimal base = QuotaUtil.getBaseAmount(paraMap);
		if(null == base || BigDecimal.ZERO.compareTo(base) == 0 ){
			return;
		}
		
		for(String level  :levelArray){
			//获取上级用户
			int currentLevel = Integer.valueOf(level);
			UserDo parent = QuotaUtil.getParentByLevel(userDo, currentLevel);
			
			//判断用户是否满足动态分红的条件
			boolean result = isCanMuiltIncome(parent.getId());
			if(result == false){
				continue;
			}
			
			//判断用哪个标准
			MuiltLevelBean chooseMuiltLevelBean =chooseMuiltConfig(parent.getId(),muiltLevelBean1, muiltLevelBean2);
			logger.info(parent.getId()+"团队奖的分红方案:"+chooseMuiltLevelBean.getQuotaMap());
			
			//获取参与计算的amount
			List<QuotaBean> qutoLst = QuotaUtil.calculateMuiltLevelIncome(chooseMuiltLevelBean, 
																		 	base,
																		 	parent, 
																		 	level);
			saveQuota(qutoLst, AccountMsg.type_5);
		}
		
	
		
	}
	
	private boolean isCanMuiltIncome(Long id) {
		BizUserAccountDo userAcc = this.userAccountCacheMap.get(id);
		
		if(null == userAcc){
			Map<String,Object> paraMap = new HashMap<String,Object>();
			paraMap.put("userId",id);
			paraMap.put("accountType", WebConstants.SECURITY_ACCOUNT);
			List<BizUserAccountDo>  userAccLst = this.bizUserAccountService.selectUserAccount(paraMap);
			if(null == userAccLst){
				return false;
			}
			userAcc = userAccLst.get(0);
			this.userAccountCacheMap.put(id, userAcc);
		}
		if( userAcc == null){
			return false;
		}
		
		if(userAcc.getAmount().intValue()>= 40){
			return true;
		}
		return false;
		
	}




	public MuiltLevelBean chooseMuiltConfig(Long userId,
			MuiltLevelBean muiltLevelBean1, MuiltLevelBean muiltLevelBean2) {
		BigDecimal jieFenAmt = this.getUserJieFenAccount(userId);		 
		BigDecimal configAmt = this.getconfigChooseAmt();
		MuiltLevelBean source =  configAmt.compareTo(jieFenAmt)<=0 ?muiltLevelBean2: muiltLevelBean1;
		
		MuiltLevelBean target = new MuiltLevelBean(null,null,null,null,null);
		
		Map<String, QuotaBean> tmpMap = source.getQuotaMap();
		List<QuotaBean> lst = QuotaUtil.copyMap2List(tmpMap);
		BeanUtils.copyProperties(source, target);
		target.setQuotaList(lst);
		return target;
	}

	
	private BigDecimal getUserJieFenAccount(Long userId) {
		
		BigDecimal jifen = this.jiFenMap.get(userId);
		
		if(null == jifen){
			return BigDecimal.ZERO;
		}
		return jifen;
	}




	private BigDecimal getconfigChooseAmt() {
		if(this.configChooseAmt == null || BigDecimal.ZERO.compareTo(this.configChooseAmt) ==0){
			synchronized (this.configChooseAmt) {
				String  chooseAmt = paramService.getByName(WebConstants.FENHONG_CHOOSE_AMT);
				try{
				this.configChooseAmt = new BigDecimal(chooseAmt);
				}catch(Exception e){
					e.printStackTrace();
					logger.error("没有正确配置分红判断基准");
				}
			}
			
		}
		if(this.configChooseAmt == null ){
			this.configChooseAmt = BigDecimal.ZERO;
		}
		return this.configChooseAmt;
		
	}




	private void saveQuota(List<QuotaBean> qutoLst,AccountMsg accountMsgType) {
		
		if(qutoLst.isEmpty() ){
			return;
		}
		
		for(QuotaBean quto : qutoLst){
			if(null == quto.getIncomeAmount()){
				continue;
			}
			
			
			BigDecimal one = new BigDecimal("1");
			BigDecimal resultAmt = quto.getIncomeAmount().divide(one, 6,BigDecimal.ROUND_HALF_UP);
		
			if(BigDecimal.ZERO.compareTo(resultAmt) ==0 ){
				continue;
			}
			bizUserAccountService.insertFeiHongTemp(quto.getUserId(),resultAmt,quto.getAccount(),quto.getCalDesc(),accountMsgType);
		}
	}
	

	public Map<String, QuotaBean> getQuota(String fenHongConfig) {
		
		Map<String,Object> configMap = (Map)context .get();
		if(null == configMap){
			configMap = new HashMap<String,Object>();
			context.set(configMap);
		}
		Object qutoConfigMap = configMap.get(fenHongConfig);
		if(null == qutoConfigMap){
			qutoConfigMap = paramService.getQuoTa(fenHongConfig);
			configMap.put(fenHongConfig, qutoConfigMap);
		}
		return (Map<String, QuotaBean>)qutoConfigMap;
	}
	
	public MuiltLevelBean getMuiltLevelConfig1() {
		
		Map<String,Object> configMap = (Map)context.get();
		if(null == configMap){
			configMap = new HashMap<String,Object>();
			context.set(configMap);
		}
		
		Object muiltLevelBean = configMap.get(WebConstants.MUILT_LEVEL_INCOME+"1");
		if(null == muiltLevelBean){
			muiltLevelBean = paramService.getMuiltLevelConfig(WebConstants.MUILT_LEVEL_INCOME,"1");
			configMap.put(WebConstants.MUILT_LEVEL_INCOME+"1", muiltLevelBean);
		}
		return (MuiltLevelBean)muiltLevelBean;
		
	}
	
	public MuiltLevelBean getMuiltLevelConfig2() {
		
		Map<String,Object> configMap = (Map)context.get();
		if(null == configMap){
			configMap = new HashMap<String,Object>();
			context.set(configMap);
		}
		
		Object muiltLevelBean = configMap.get(WebConstants.MUILT_LEVEL_INCOME+"2");
		if(null == muiltLevelBean){
			muiltLevelBean = paramService.getMuiltLevelConfig(WebConstants.MUILT_LEVEL_INCOME,"2");
			configMap.put(WebConstants.MUILT_LEVEL_INCOME+"2", muiltLevelBean);
		}
		return (MuiltLevelBean)muiltLevelBean;
		
	}
	
	
	public void cleanCache(){
		this.configChooseAmt = BigDecimal.ZERO;
		this.jiFenMap.clear();
		context.remove();
		this.userAccountCacheMap.clear();
	}




	public void loadJiFenAccount() {
		Map<String, Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("accountType", WebConstants.POINE_ACCOUNT);
		List<BizUserAccountDo> usrAccLst = bizUserAccountService.selectUserAccount(parameterMap);
		for(BizUserAccountDo uc : usrAccLst ){
			this.jiFenMap.put(uc.getUserId(), uc.getAmount());
		}
		
	}
	
}

package com.redpack.income.service;

import java.math.BigDecimal;

import com.redpack.utils.CalculateUtils;

public class QuotaBean {
	
	/**
	 * 收益率
	 */
	private BigDecimal incomeRate; 
	
	/**
	 * 配额收益对应的账号
	 */
	private String  accountName;
	
	/**
	 * 配置收益对应的账号，占比 0.2 表示 20%
	 */
	private BigDecimal percent;
	
	/**
	 * 额度 = baseAmount*incomeRate*percent
	 */
	private BigDecimal incomeAmount; 
	
	private String calDesc; //描述计算公式
	
	private BigDecimal baseAmount; //计算的基数
	
	private int level;
	private String rateConfig;
	
	private String fenHongType; //分红类型
	
	
	/**
	 * 收益归属用户ID
	 */
	private Long userId;
	
	public QuotaBean(String  accountName,BigDecimal percent,String rateConfig,String fenHongType){
		this.accountName = accountName;
		this.percent = percent;
		this.rateConfig = rateConfig;
		this.fenHongType = fenHongType;
	}

	
	public BigDecimal getIncomeRate() {
		return incomeRate;
	}


	public void setIncomeRate(BigDecimal incomeRate) {
		this.incomeRate = incomeRate;
	}


	public String getAccount() {
		return accountName;
	}

	public void setAccount(String account) {
		this.accountName = account;
	}

	public BigDecimal getPercent() {
		return percent;
	}

	public void setPercent(BigDecimal percent) {
		this.percent = percent;
	}

	
	public BigDecimal getIncomeAmount() {
		return incomeAmount;
	}

	public void setIncomeAmount(BigDecimal amount) {
		this.incomeAmount = amount;
	}


	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}



	public String getCalDesc() {
		return calDesc;
	}


	public void setCalDesc(String calDesc) {
		this.calDesc = calDesc;
	}



	public BigDecimal getBaseAmount() {
		return baseAmount;
	}


	public void setBaseAmount(BigDecimal baseAmount) {
		this.baseAmount = baseAmount;
	}

	/**
	 * @return the accountName
	 */
	public String getAccountName() {
		return accountName;
	}


	/**
	 * @param accountName the accountName to set
	 */
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}


	/**
	 * @return the rateConfig
	 */
	public String getRateConfig() {
		return rateConfig;
	}


	/**
	 * @param rateConfig the rateConfig to set
	 */
	public void setRateConfig(String rateConfig) {
		this.rateConfig = rateConfig;
	}


	/**
	 * @return the fenHongType
	 */
	public String getFenHongType() {
		return fenHongType;
	}


	/**
	 * 基数*收益率
	 * @return
	 */
	public BigDecimal  baseAmtMulIncomeRate(){
		if(null == this.baseAmount  ){
			return BigDecimal.ZERO;
		}
		
		this.incomeRate = this.getRateByAmount(this.baseAmount);
		if(null == this.incomeRate ){
			return BigDecimal.ZERO;
		}
		return  this.baseAmount.multiply(this.incomeRate);
	}
	/**
	 * 基数*收益率
	 * @return
	 */
	public BigDecimal  groupBaseAmtMulIncomeRate(BigDecimal refAmt){
		if(null == this.baseAmount  ){
			return BigDecimal.ZERO;
		}
		
		this.incomeRate = this.getGroupRateByAmount(this.baseAmount,refAmt);
		if(null == this.incomeRate ){
			return BigDecimal.ZERO;
		}
		return  this.baseAmount.multiply(this.incomeRate);
	}
	
	/**
	 * 
	 * 根据金额大小确定利率  1-999:0.1;1000-1999:0.2;
	 * zhangyunhmf
	 *
	 */
    private BigDecimal getRateByAmount(BigDecimal baseAmount2) {
	    String[] levels = this.rateConfig.split(";");
	    for(String level : levels){
	    	String[] oneConfig = level.split(":");
	    	String[] fromToAmt = oneConfig[0].split("-");
	    	String from = fromToAmt[0];
	    	String to = fromToAmt[1];
	    	if(baseAmount2.compareTo(new BigDecimal(from))>=0 && baseAmount2.compareTo(new BigDecimal(to))<= 0){
	    		return new BigDecimal(oneConfig[1]);
	    	}
	    	
	    }
	    return BigDecimal.ZERO;
    }
    /**
     * 推荐 refAmt<= 100  ,小区业绩<= 1000
     * 根据金额大小确定利率  100-1000:0.1;1000-1999:0.2;
     * zhangyunhmf
     *
     */
    private BigDecimal getGroupRateByAmount(BigDecimal baseAmount2,BigDecimal refAmt) {
    	String[] levels = this.rateConfig.split(";");
    	for(String level : levels){
    		String[] oneConfig = level.split(":");
    		String[] fromToAmt = oneConfig[0].split("-");
    		String from = fromToAmt[0];
    		String to = fromToAmt[1];
    		if(refAmt.compareTo(new BigDecimal(from))>=0 && baseAmount2.compareTo(new BigDecimal(to))<= 0){
    			return new BigDecimal(oneConfig[1]);
    		}
    		
    	}
    	return BigDecimal.ZERO;
    }


	public void calculateIncome() {
		BigDecimal  step1Result = baseAmtMulIncomeRate();
		if(null == this.getPercent()){
			return ;
		}
		this.incomeAmount = this.getPercent().multiply(step1Result);
		
		String ret= String.format("%.4f", this.incomeAmount.doubleValue());
		this.calDesc = ("分红："+this.fenHongType+":"+ret);
		
	}
	
	public void calculateGroupIncome(BigDecimal refAmt) {
		BigDecimal  step1Result = groupBaseAmtMulIncomeRate(refAmt);
		if(null == this.getPercent()){
			return ;
		}
		this.incomeAmount = this.getPercent().multiply(step1Result);
		
		String ret= String.format("%.4f", this.incomeAmount.doubleValue());
		this.calDesc = ("分红："+this.fenHongType+":"+ret);
		
	}
	



	/**
	 *
	 * zhangyunhmf
	 *
	 * @see java.lang.Object#toString()
	 *
	 */
    @Override
    public String toString() {
	    return "QuotaBean [incomeRate=" + incomeRate + ", accountName=" + accountName
	            + ", percent=" + percent + ", incomeAmount=" + incomeAmount + ", calDesc="
	            + calDesc + ", baseAmount=" + baseAmount + ", level=" + level + ", rateConfig="
	            + rateConfig + ", fenHongType=" + fenHongType + ", userId=" + userId + "]";
    }


	public int getLevel() {
		return level;
	}


	public void setLevel(int level) {
		this.level = level;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		QuotaBean other = (QuotaBean) obj;
		if (accountName == null) {
			if (other.accountName != null)
				return false;
		} else if (!accountName.equals(other.accountName))
			return false;
		return true;
	}


	/**
	 * 
	 * zhangyunhmf
	 *
	 */
    public void setFenHongType(String fenHongType) {
	    // TODO Auto-generated method stub
	    
    }


}

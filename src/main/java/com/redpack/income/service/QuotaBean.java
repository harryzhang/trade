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
	
	
	/**
	 * 收益归属用户ID
	 */
	private Long userId;
	
	public QuotaBean(String  accountName,BigDecimal percent,BigDecimal rate){
		this.accountName = accountName;
		this.percent = percent;
		this.incomeRate = rate;
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
	 * 基数*收益率
	 * @return
	 */
	public BigDecimal  baseAmtMulIncomeRate(){
		if(null == this.baseAmount  ){
			return BigDecimal.ZERO;
		}
		
		if(null == this.incomeRate ){
			return BigDecimal.ZERO;
		}
		return  this.baseAmount.multiply(this.incomeRate);
	}
	
	public void calculateIncome() {
		//StringBuffer sb = new StringBuffer(level == 0 ?"":"团队奖第"+level+"代");
		//sb.append(" 步骤1. 收益率*基数=").append(CalculateUtils.round(this.getIncomeRate().doubleValue())).append("*").append(CalculateUtils.round(this.baseAmount.doubleValue()));
		BigDecimal  step1Result = baseAmtMulIncomeRate();
		//sb.append(" 步骤2. 账户比例系数*第一步结果=").append(this.getPercent().toString()).append("*").append(CalculateUtils.round(step1Result.doubleValue()));
		if(null == this.getPercent()){
			return ;
		}
		this.incomeAmount = this.getPercent().multiply(step1Result);
		
		String ret= String.format("%.4f", this.incomeAmount.doubleValue());
		this.calDesc = (level == 0 ?"分红："+ret:("第"+level+"代团队奖:"+ret));
		
		//sb.append("结果：").append(ret);
		//System.out.println(sb.toString());
	}
	

	@Override
	public String toString() {
		return "QuotaBean [incomeRate=" + incomeRate + ", accountName="
				+ accountName + ", percent=" + percent + ", incomeAmount="
				+ incomeAmount + ", calDesc=" + calDesc + ", baseAmount="
				+ baseAmount + ", level=" + level + ", userId=" + userId + "]";
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


}

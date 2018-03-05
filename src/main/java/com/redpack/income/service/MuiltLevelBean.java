package com.redpack.income.service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MuiltLevelBean {
	
	private Map<String, QuotaBean> quotaMap;
	
	private String[] gradeArray;  //每层的级别要求
	
	private String[] levelArray; //层次定义
	
	private String[] levelPercent;  //每层的占比
	
	private String[] calculateAccountName; //参与分红计算的账户名称
	
	private String[] levelDefineArray; //股东级别定义

	private List<QuotaBean> quotaList;

	private int maxDefineGeneration = -1;
	
	

	public MuiltLevelBean(String[] levels, String[] levelPercents,
			String[] levelGrades,String[] calculateAccountName,String[] levelDefineArray) {
		this.levelArray = levels;
		this.gradeArray = levelGrades;
		this.levelPercent = levelPercents;
		this.calculateAccountName = calculateAccountName;
		this.levelDefineArray = levelDefineArray;
	}

	public void setQuotaMap(Map<String, QuotaBean> quotaMap) {
		this.quotaMap = quotaMap;
		this.quotaList = QuotaUtil.map2List(this.quotaMap);
	}

	public void setQuotaList(List<QuotaBean> quotaList) {
		this.quotaList = quotaList;
	}

	public Map<String, QuotaBean> getQuotaMap() {
		return quotaMap;
	}

	public String[] getGradeArray() {
		return gradeArray;
	}

	public void setGradeArray(String[] gradeArray) {
		this.gradeArray = gradeArray;
	}

	public String[] getLevelArray() {
		return levelArray;
	}

	public void setLevelArray(String[] levelArray) {
		this.levelArray = levelArray;
	}

	public String[] getLevelPercent() {
		return levelPercent;
	}

	public void setLevelPercent(String[] levelPercent) {
		this.levelPercent = levelPercent;
	}

	public String[] getLevelDefineArray() {
		return levelDefineArray;
	}

	public void setLevelDefineArray(String[] levelDefineArray) {
		this.levelDefineArray = levelDefineArray;
	}

	public int getMaxLevel() {
		int retLevel = 0 ;
		for(String level  : levelArray){
			int temp = Integer.valueOf(level);
			if(temp > retLevel){
				retLevel = temp;
			}
		}
		return retLevel;
	}

	/**
	 * 参与计算的现金账户
	 * @return
	 */
	public String[] getCalculateAccountName() {
		return calculateAccountName;
	}

	public void setCalculateAccountName(String[] calculateAccountName) {
		this.calculateAccountName = calculateAccountName;
	}
	
	
	
	/**
	 * 多级收益配置
	 * @param level
	 * @param levelPercent
	 * @param levelGrade
	 * @return
	 */
	public static MuiltLevelBean ceateQuotaMuiltLevelBean(String level,														   
														  String levelGrade,
														  String baseAccountName,
														  String[]  levelDefine,
														  String[] levelPercent) {
		
		String[] levels=level.split(":");
		String[] levelPercents = levelPercent;
		String[] levelGrades = levelGrade.split(":");
		String[] baseAcc = baseAccountName.split(":");
		MuiltLevelBean muiltConfigBean = new MuiltLevelBean(levels,levelPercents,levelGrades,baseAcc,levelDefine);
		
		return muiltConfigBean;
	}

	/**
	 * 获取层级对应的 grade
	 * @param level
	 * @return
	 */
	public int getGradeByLevel( String level) {
		for(int i = 0 ; i < levelArray.length; i++){
			String l = levelArray[i];
			if(level.equals(l)){
				return Integer.valueOf(gradeArray[i]);
			}
		}
		return -1;
	}

	/**
	 * 获取层级对应的系数
	 * @param userLevel 用户等级
	 * @param currentGeneration 用户第几代
	 * @return
	 */
	public BigDecimal getLevelPercent(int userLevel, int currentGeneration) {
		
		int tmpLevel = userLevel;
		if(userLevel > this.getMaxDefineGeneration()){
			tmpLevel = this.getMaxDefineGeneration();
		}
		

		for(int i = 0 ; i < levelDefineArray.length; i++){
			String l = levelDefineArray[i];
			if(tmpLevel == Integer.valueOf(l).intValue()){
				String  percent = levelPercent[i];
				return  new BigDecimal(percent.split(":")[currentGeneration-1]);
			}
		}
		return BigDecimal.ZERO;
	
	}

	private int getMaxDefineGeneration() {
		
		if(this.maxDefineGeneration  != -1){
			return this.maxDefineGeneration;
		}
		
		//计算最大层级
		int retLevel = 0 ;
		for(String level  : levelDefineArray){
			int temp = Integer.valueOf(level);
			if(temp > retLevel){
				retLevel = temp;
			}
		}
		this.maxDefineGeneration = retLevel;
		return this.maxDefineGeneration;
	}

	public List<QuotaBean> getQuotaList() {
		return this.quotaList;		
	}

	@Override
	public String toString() {
		return "MuiltLevelBean [quotaMap=" + quotaMap + ", gradeArray="
				+ Arrays.toString(gradeArray) + ", levelArray="
				+ Arrays.toString(levelArray) + ", levelPercent="
				+ Arrays.toString(levelPercent) + ", calculateAccountName="
				+ Arrays.toString(calculateAccountName) + ", levelDefineArray="
				+ Arrays.toString(levelDefineArray) + ", quotaList="
				+ quotaList + ", maxDefineGeneration=" + maxDefineGeneration
				+ "]";
	}
	
	
}

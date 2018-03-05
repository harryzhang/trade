/**
 * 
 */
package com.redpack.member.model;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.ibatis.type.Alias;

/**
 * @author MBENBEN
 *
 */
@Alias("memberDo")
public class KuangjiUserAccountDo implements java.io.Serializable {
	private java.lang.Long userId;
	private java.math.BigDecimal amount;
	private java.math.BigDecimal guquanQty;
	private java.math.BigDecimal point;
	private java.math.BigDecimal incomeTotalAmount;
	private java.math.BigDecimal ququanTotalAmount;
	private java.math.BigDecimal recommendTotalAmount;
	private java.math.BigDecimal poingTotalAmount;
	private java.math.BigDecimal consumeTotalAmount;
	private java.math.BigDecimal withdrawTotalDeposit;
	private java.math.BigDecimal freezeAmount;
	private BigDecimal teamAmount;
	private java.util.Date updateTime;
	public java.lang.Long getUserId() {
		return userId;
	}
	public void setUserId(java.lang.Long userId) {
		this.userId = userId;
	}
	public java.math.BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(java.math.BigDecimal amount) {
		this.amount = amount;
	}
	public java.math.BigDecimal getGuquanQty() {
		return guquanQty;
	}
	public void setGuquanQty(java.math.BigDecimal guquanQty) {
		this.guquanQty = guquanQty;
	}
	public java.math.BigDecimal getPoint() {
		return point;
	}
	public void setPoint(java.math.BigDecimal point) {
		this.point = point;
	}
	public java.math.BigDecimal getIncomeTotalAmount() {
		return incomeTotalAmount;
	}
	public void setIncomeTotalAmount(java.math.BigDecimal incomeTotalAmount) {
		this.incomeTotalAmount = incomeTotalAmount;
	}
	public java.math.BigDecimal getQuquanTotalAmount() {
		return ququanTotalAmount;
	}
	public void setQuquanTotalAmount(java.math.BigDecimal ququanTotalAmount) {
		this.ququanTotalAmount = ququanTotalAmount;
	}
	public java.math.BigDecimal getRecommendTotalAmount() {
		return recommendTotalAmount;
	}
	public void setRecommendTotalAmount(java.math.BigDecimal recommendTotalAmount) {
		this.recommendTotalAmount = recommendTotalAmount;
	}
	public java.math.BigDecimal getPoingTotalAmount() {
		return poingTotalAmount;
	}
	public void setPoingTotalAmount(java.math.BigDecimal poingTotalAmount) {
		this.poingTotalAmount = poingTotalAmount;
	}
	public java.math.BigDecimal getConsumeTotalAmount() {
		return consumeTotalAmount;
	}
	public void setConsumeTotalAmount(java.math.BigDecimal consumeTotalAmount) {
		this.consumeTotalAmount = consumeTotalAmount;
	}
	public java.math.BigDecimal getWithdrawTotalDeposit() {
		return withdrawTotalDeposit;
	}
	public void setWithdrawTotalDeposit(java.math.BigDecimal withdrawTotalDeposit) {
		this.withdrawTotalDeposit = withdrawTotalDeposit;
	}
	public java.math.BigDecimal getFreezeAmount() {
		return freezeAmount;
	}
	public void setFreezeAmount(java.math.BigDecimal freezeAmount) {
		this.freezeAmount = freezeAmount;
	}
	public java.util.Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}
	public BigDecimal getTeamAmount() {
		return teamAmount;
	}
	public void setTeamAmount(BigDecimal teamAmount) {
		this.teamAmount = teamAmount;
	}
	
	
	
}

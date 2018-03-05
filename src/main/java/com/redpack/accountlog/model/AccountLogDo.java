/*
 * Powered By zhangyunhua
 * Web Site:  
 * Since 2008 - 2015
 */

package com.redpack.accountlog.model;



import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.ibatis.type.Alias;

/**
 * @author zhangyunhua
 * @version 1.0
 * @since 1.0
 */


@Alias("accountLogDo")
public class AccountLogDo  implements java.io.Serializable{
	
	//columns START
	private Long accountLogId;
	private Long tradeId;
	private Long fromAccountId;
	private Long toAccountId;
	private Long transAmt;
	private TransStatus transStatus;
	private java.util.Date transTime;
	private java.util.Date updateTime;
    /**PROCESSING-筹标中,SUCCESS-交易成功,FAILURE-交易失败 */
    public enum TransStatus{
        SUCCESS,FAILURE,PROCESSING
    }
	//columns END
	public Long getAccountLogId() {
		return this.accountLogId;
	}
	
	public void setAccountLogId(Long value) {
		this.accountLogId = value;
	}
	public Long getTradeId() {
		return this.tradeId;
	}
	
	public void setTradeId(Long value) {
		this.tradeId = value;
	}
	public Long getFromAccountId() {
		return this.fromAccountId;
	}
	
	public void setFromAccountId(Long value) {
		this.fromAccountId = value;
	}
	public Long getToAccountId() {
		return this.toAccountId;
	}
	
	public void setToAccountId(Long value) {
		this.toAccountId = value;
	}
	public Long getTransAmt() {
		return this.transAmt;
	}
	
	public void setTransAmt(Long value) {
		this.transAmt = value;
	}

    public TransStatus getTransStatus() {
        return transStatus;
    }

    public void setTransStatus(TransStatus transStatus) {
        this.transStatus = transStatus;
    }

    public java.util.Date getTransTime() {
		return this.transTime;
	}
	
	public void setTransTime(java.util.Date value) {
		this.transTime = value;
	}
	public java.util.Date getUpdateTime() {
		return this.updateTime;
	}
	
	public void setUpdateTime(java.util.Date value) {
		this.updateTime = value;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("AccountLogId",getAccountLogId())
			.append("TradeId",getTradeId())
			.append("FromAccountId",getFromAccountId())
			.append("ToAccountId",getToAccountId())
			.append("TransAmt",getTransAmt())
			.append("TransStatus",getTransStatus())
			.append("TransTime",getTransTime())
			.append("UpdateTime",getUpdateTime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getAccountLogId())
			.toHashCode();
	}

}


/*
 * Powered By zhangyunhua
 * Web Site:  
 * Since 2008 - 2015
 */

package com.redpack.account.model;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.ibatis.type.Alias;

/**
 * @author zhangyunhua
 * @version 1.0
 * @since 1.0
 */

@Alias("userAccountDo")
public class UserAccountDo implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private Long              accountId;
    private Long              parentId;
    private Long              productId;
    private Long              userId;
    private Long              usableAmt;
    private Long              freezeAmt;
    private Long              purchaseAmt;
    private Long    redeemAmt;
    private Long              cashLockAmt;
    private AccountType       accountType;
    private AccountSubType    accountSubType;
    private AccountStatus     accountStatus;
    private java.util.Date    createTime;
    private java.util.Date    updateTime;

    /** 账户类型(GENERAL-普通账户,PLATFORM-平台账户) */
    public enum AccountType {
        GENERAL, PLATFORM
    }

    /** 账户子类型 FEE-费用账户，REDEEMFEE-提前赎回费用账户 ,COMP-代偿账户 */
    public enum AccountSubType{
        FEE, REDEEMFEE, COMP
    }

    /** 账户状态(NOACTIVATE-未激活，ACTIVATED-已激活,REDEEMED-已赎回,LIQUIDATED-已清算) */
    public enum AccountStatus {
        NOACTIVATE, ACTIVATED,REDEEMED,LIQUIDATED;
    }

    // columns END
    public Long getAccountId() {
        return this.accountId;
    }

    public void setAccountId(Long value) {
        this.accountId = value;
    }

    public Long getParentId() {
        return this.parentId;
    }

    public void setParentId(Long value) {
        this.parentId = value;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long value) {
        this.userId = value;
    }

    public Long getUsableAmt() {
        return this.usableAmt;
    }

    public void setUsableAmt(Long value) {
        this.usableAmt = value;
    }

    public Long getFreezeAmt() {
        return this.freezeAmt;
    }

    public void setFreezeAmt(Long value) {
        this.freezeAmt = value;
    }

    public Long getPurchaseAmt() {
        return this.purchaseAmt;
    }

    public void setPurchaseAmt(Long value) {
        this.purchaseAmt = value;
    }

    public Long getRedeemAmt() {
        return this.redeemAmt;
    }

    public void setRedeemAmt(Long value) {
        this.redeemAmt = value;
    }

    public Long getCashLockAmt() {
        return this.cashLockAmt;
    }

    public void setCashLockAmt(Long value) {
        this.cashLockAmt = value;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    /**
     * @return accountType
     */
    public AccountType getAccountType() {
        return accountType;
    }

    /**
     * @param accountType
     *            the accountType to set
     */
    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public AccountSubType getAccountSubType() {
        return accountSubType;
    }

    public void setAccountSubType(AccountSubType accountSubType) {
        this.accountSubType = accountSubType;
    }

    /**
     * @return accountStatus
     */
    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    /**
     * @param accountStatus
     *            the accountStatus to set
     */
    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    public java.util.Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(java.util.Date value) {
        this.createTime = value;
    }

    public java.util.Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(java.util.Date value) {
        this.updateTime = value;
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("AccountId", getAccountId())
                .append("ParentId", getParentId()).append("UserId", getUserId()).append("UsableAmt", getUsableAmt())
                .append("FreezeAmt", getFreezeAmt()).append("PurchaseAmt", getPurchaseAmt())
                .append("RedeemAmt", getRedeemAmt()).append("CashLockAmt", getCashLockAmt())
                .append("AccountType", getAccountType()).append("AccountStatus", getAccountStatus())
                .append("CreateTime", getCreateTime()).append("UpdateTime", getUpdateTime()).toString();
    }

    public int hashCode() {
        return new HashCodeBuilder().append(getAccountId()).toHashCode();
    }

}

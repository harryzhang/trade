/*
 * Powered By  huangzl QQ: 272950754
 * Web Site:  
 * Since 2008 - 2016
 */

package com.redpack.order.model;

import java.math.BigDecimal;
import java.util.Map;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.ibatis.type.Alias;

/**
 * @author  huangzl QQ: 272950754
 * @version 1.0
 * @since 1.0
 */


@Alias("orderDo")
public class OrderDo  implements java.io.Serializable{	
	
	//columns START
	private java.lang.Long orderId;
	private String orderCode;
	private java.lang.Long userId;
	private java.lang.Long goodsId;
	private java.lang.Long qty;
	private java.math.BigDecimal price;
	private java.math.BigDecimal totalPrice;
	private java.lang.String recAddress;
	private java.util.Date createTime;
	private java.lang.Integer orderStatus;
	private java.lang.Integer payStatus;
	private java.util.Date payTime;
	private Integer  orderType; // 0 充值， 1购买矿机 2:积分兑换　３充值未确认
	
	//匹配订单id
	private Long matchOrderId;
	
	private Map<String,BigDecimal> splitAccAmtMap; //多个账户的购买，购买金额在各个账户的分配
	//columns END
	
	
	public java.lang.Long getOrderId() {
		return this.orderId;
	}
	
	/**
	 * @return the matchOrderId
	 */
	public Long getMatchOrderId() {
		return matchOrderId;
	}

	/**
	 * @param matchOrderId the matchOrderId to set
	 */
	public void setMatchOrderId(Long matchOrderId) {
		this.matchOrderId = matchOrderId;
	}

	public void setOrderId(java.lang.Long value) {
		this.orderId = value;
	}
	public String getOrderCode() {
		return this.orderCode;
	}
	
	public void setOrderCode(String value) {
		this.orderCode = value;
	}
	public java.lang.Long getUserId() {
		return this.userId;
	}
	
	public void setUserId(java.lang.Long value) {
		this.userId = value;
	}
	public java.lang.Long getGoodsId() {
		return this.goodsId;
	}
	
	public void setGoodsId(java.lang.Long value) {
		this.goodsId = value;
	}
	public java.lang.Long getQty() {
		return this.qty;
	}
	
	public void setQty(java.lang.Long value) {
		this.qty = value;
	}
	public java.math.BigDecimal getPrice() {
		return this.price;
	}
	
	public void setPrice(java.math.BigDecimal value) {
		this.price = value;
	}
	public java.math.BigDecimal getTotalPrice() {
		return this.totalPrice;
	}
	
	public void setTotalPrice(java.math.BigDecimal value) {
		this.totalPrice = value;
	}
	public java.lang.String getRecAddress() {
		return this.recAddress;
	}
	
	public void setRecAddress(java.lang.String value) {
		this.recAddress = value;
	}
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	public java.lang.Integer getOrderStatus() {
		return this.orderStatus;
	}
	
	public void setOrderStatus(java.lang.Integer value) {
		this.orderStatus = value;
	}
	public java.lang.Integer getPayStatus() {
		return this.payStatus;
	}
	
	public void setPayStatus(java.lang.Integer value) {
		this.payStatus = value;
	}
	public java.util.Date getPayTime() {
		return this.payTime;
	}
	
	public void setPayTime(java.util.Date value) {
		this.payTime = value;
	}



	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("OrderId",getOrderId())
			.append("OrderCode",getOrderCode())
			.append("UserId",getUserId())
			.append("GoodsId",getGoodsId())
			.append("Qty",getQty())
			.append("Price",getPrice())
			.append("TotalPrice",getTotalPrice())
			.append("RecAddress",getRecAddress())
			.append("CreateTime",getCreateTime())
			.append("OrderStatus",getOrderStatus())
			.append("PayStatus",getPayStatus())
			.append("PayTime",getPayTime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getOrderId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof OrderDo == false) return false;
		if(this == obj) return true;
		OrderDo other = (OrderDo)obj;
		return new EqualsBuilder()
			.append(getOrderId(),other.getOrderId())
			.isEquals();
	}
}


/*
 * Powered By  huangzl QQ: 272950754
 * Web Site:  
 * Since 2008 - 2016
 */

package com.redpack.goods.model;

import org.apache.ibatis.type.Alias;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @author  huangzl QQ: 272950754
 * @version 1.0
 * @since 1.0
 */


@Alias("goodsDo")
public class GoodsDo  implements java.io.Serializable{	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//columns START
	private java.lang.Long id;
	private java.lang.Long goodsId;
	private java.lang.String gname;
	private java.lang.String gdesc;
	private java.lang.String status;
	private java.lang.String gcode;
	private java.lang.String gcate;
	private java.lang.String gunite;
	private java.math.BigDecimal price;
	private java.lang.String gkind;
	private java.util.Date createTime;
	private String imageSrc;
	private String bigImageSrc; //大图
	private String bandName; //品牌
	
	//购物车使用的属性
	private int  buyQty;
	
	//columns END
	public java.lang.Long getGoodsId() {
		return this.goodsId;
	}
	
	public void setGoodsId(java.lang.Long value) {
		this.goodsId = value;
	}
	public java.lang.String getGname() {
		return this.gname;
	}
	
	public void setGname(java.lang.String value) {
		this.gname = value;
	}
	public java.lang.String getGdesc() {
		return this.gdesc;
	}
	
	public void setGdesc(java.lang.String value) {
		this.gdesc = value;
	}
	public java.lang.String getStatus() {
		return this.status;
	}
	
	public void setStatus(java.lang.String value) {
		this.status = value;
	}
	public java.lang.String getGcode() {
		return this.gcode;
	}
	
	public void setGcode(java.lang.String value) {
		this.gcode = value;
	}
	public java.lang.String getGcate() {
		return this.gcate;
	}
	
	public void setGcate(java.lang.String value) {
		this.gcate = value;
	}
	public java.lang.String getGunite() {
		return this.gunite;
	}
	
	public void setGunite(java.lang.String value) {
		this.gunite = value;
	}
	public java.math.BigDecimal getPrice() {
		return this.price;
	}
	
	public void setPrice(java.math.BigDecimal value) {
		this.price = value;
	}
	public java.lang.String getGkind() {
		return this.gkind;
	}
	
	public void setGkind(java.lang.String value) {
		this.gkind = value;
	}
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}

	public int getBuyQty() {
		return buyQty;
	}

	public void setBuyQty(int buyQty) {
		this.buyQty = buyQty;
	}

	public String getImageSrc() {
		return imageSrc;
	}

	public void setImageSrc(String imageSrc) {
		this.imageSrc = imageSrc;
	}

	public String getBigImageSrc() {
		return bigImageSrc;
	}

	public void setBigImageSrc(String bigImageSrc) {
		this.bigImageSrc = bigImageSrc;
	}

	public String getBandName() {
		return bandName;
	}

	public void setBandName(String bandName) {
		this.bandName = bandName;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("GoodsId",getGoodsId())
			.append("Gname",getGname())
			.append("Gdesc",getGdesc())
			.append("Status",getStatus())
			.append("Gcode",getGcode())
			.append("Gcate",getGcate())
			.append("Gunite",getGunite())
			.append("Price",getPrice())
			.append("Gkind",getGkind())
			.append("CreateTime",getCreateTime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getGoodsId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof GoodsDo == false) return false;
		if(this == obj) return true;
		GoodsDo other = (GoodsDo)obj;
		return new EqualsBuilder()
			.append(getGoodsId(),other.getGoodsId())
			.isEquals();
	}

	public java.lang.Long getId() {
		return id;
	}

	public void setId(java.lang.Long id) {
		this.id = id;
	}
	
	
}


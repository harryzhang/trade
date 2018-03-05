/*
 * Powered By zhangyunhua
 * Web Site:  
 * Since 2008 - 2015
 */

package com.redpack.account.model;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.type.Alias;

/**
 * @Description 用户基础信息表
 * @author huangzl QQ: 272950754
 * @date 2015年8月3日 下午7:40:01
 * @Project redpack-common
 * @Package com.redpack.account.model
 * @File UserInfoDo.java
 */
@Alias("userInfoDo")
public class UserInfoDo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Long id; // id
	private Long userId; // 用户id
	private String realName; // 真实姓名
	private String idCardNo; // 身份证号
	private String idCardNoString; // 身份证号隐藏中间位数
	private String sex; // 性别
	private String contactAddress; // 联系地址
	private String mobile; // 手机号码
	private String mobileString; // 手机号码隐藏中间位数
	private String eMail; // 用户邮箱
	private String weixiNumber; // 微信号
	private String qqNumber; // QQ号
	private String zfbNumber; // 支付宝
	private String cftNumber; // 财付通
	private String bankName; // 开户银行
	private String province; // 开户省份
	private String city; // 开户市区
	private String bankSubbranch; // 银行支行
	private String bankAccount; // 银行账号
	private String openAccount; // 开户姓名

	public String getIdCardNoString() {
		return idCardNoString;
	}

	public void setIdCardNoString(String idCardNoString) {
		this.idCardNoString = idCardNoString;
	}

	public String getMobileString() {
		return mobileString;
	}

	public void setMobileString(String mobileString) {
		this.mobileString = mobileString;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
		// 隐藏身份证号码部分号码
		if (StringUtils.isNotBlank(idCardNo) && idCardNo.length() >= 7) {
			setIdCardNoString(idCardNo.substring(0, 4) + "***********" + idCardNo.substring(idCardNo.length() - 4));
		}
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getContactAddress() {
		return contactAddress;
	}

	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
		// 隐藏手机号码部分号码
		if (StringUtils.isNotBlank(mobile) && mobile.length() >= 7) {
			setMobileString(mobile.substring(0, 4) + "****" + mobile.substring(mobile.length() - 4));
		}
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getWeixiNumber() {
		return weixiNumber;
	}

	public void setWeixiNumber(String weixiNumber) {
		this.weixiNumber = weixiNumber;
	}

	public String getQqNumber() {
		return qqNumber;
	}

	public void setQqNumber(String qqNumber) {
		this.qqNumber = qqNumber;
	}

	public String getZfbNumber() {
		return zfbNumber;
	}

	public void setZfbNumber(String zfbNumber) {
		this.zfbNumber = zfbNumber;
	}

	public String getCftNumber() {
		return cftNumber;
	}

	public void setCftNumber(String cftNumber) {
		this.cftNumber = cftNumber;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getBankSubbranch() {
		return bankSubbranch;
	}

	public void setBankSubbranch(String bankSubbranch) {
		this.bankSubbranch = bankSubbranch;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getOpenAccount() {
		return openAccount;
	}

	public void setOpenAccount(String openAccount) {
		this.openAccount = openAccount;
	}
}
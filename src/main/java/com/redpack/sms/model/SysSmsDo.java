package com.redpack.sms.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.type.Alias;


@Alias("sysSmsDo")
public class SysSmsDo implements Serializable{
	
	private Long id;
	private Long userId;
	private int msgType;   //1 代表升级的消息
	private String msg;
	private Integer status = 1; // 1 有效， 0 无效
	private Date createTime;
	private Date updateTime;
	private String groupName;
	private String netWork;
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
	public int getMsgType() {
		return msgType;
	}
	public void setMsgType(int msgType) {
		this.msgType = msgType;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getNetWork() {
		return netWork;
	}
	public void setNetWork(String netWork) {
		this.netWork = netWork;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}

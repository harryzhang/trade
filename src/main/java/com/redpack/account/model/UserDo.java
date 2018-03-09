
/**  
 * @Project: deposit-biz-common
 * @Package com.hehenian.deposit.common.account.model
 * @Title: UserDo.java
 * @Description: TODO
 * @author:  zhangyunhua
 * @date 2015年3月5日 上午10:46:58
 * @Copyright: HEHENIAN Co.,Ltd. All rights reserved.
 * @version V1.0  
 */
package com.redpack.account.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;
import org.springframework.util.CollectionUtils;


/**
 * @Description 用户表
 * @author huangzl QQ: 272950754
 * @date 2015年8月3日 下午6:09:33
 * @Project redpack-common
 * @Package com.redpack.account.model 
 * @File UserDo.java
*/
@Alias("userDo")
public class UserDo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final String LEFT="L";
	public static final String RIGHT="R";
	
	
	private Long 	id; 				// 用户ID
	private String 	userName; 			// 登陆用户名
	private String 	weixin; 			// 微信
	private String 	zhifubao; 			// 支付宝
	private String 	password; 			// 用户密码
	private String 	twoLevelPwd; 		// 二级密码(初始密码为用户密码)
	private Integer	grade; 				// 当前等级
	private String 	organ; 				// 组织机构 1:客服 2：管理员 其他为普通人员
	private String 	remark; 			// 备注
	private String 	enabled; 			// 状态
	private Long 	referrerId; 		// 推荐人ID
	private Long 	parentId; 			// 接受人ID
	private String 	treeNode; 			// 业务方向
	private Date 	createTime; 		// 帐号创建时间
	private Date 	updateTime; 		// 帐号修改时间
	private String  name;               //真实姓名
	private Long    createUser;          //记录创建者
	private Integer status ;                  //状态，  0 ：临时会员 ， 1 ： 正式会员  2:封号 3：过期失效 4：出局
	
	private UserInfoDo userInfoDo;		// 用户基础信息表
	private UserDo 	referrerDo; 		// 推荐人userDo
	private UserDo 	parentDo; 			// 接受人userDo
	private int refCount;               // 本用户总的推荐人数
	
	private String openId;              //用户微信ID
	
	private String userCode2; //B网用户编号
	
	private String userCode1; //复头编号
	
	private String userCode;			//用户编码
	private BigDecimal bonus = BigDecimal.ZERO;                  //积分
	private String userLocal;

	private List<UserDo> children;     //所有下级
	
	//左右位置
	private String groupuserIdx; //"L" left , "R" right
	
	
	/**
	 * @return the groupuserIdx
	 */
	public String getGroupuserIdx() {
		return groupuserIdx;
	}
	/**
	 * @param groupuserIdx the groupuserIdx to set
	 */
	public void setGroupuserIdx(String groupuserIdx) {
		this.groupuserIdx = groupuserIdx;
	}
	public String getWeixin() {
		return weixin;
	}
	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}
	public String getZhifubao() {
		return zhifubao;
	}
	public void setZhifubao(String zhifubao) {
		this.zhifubao = zhifubao;
	}
	public List<UserDo> getChildList() {
		return children;
	}
	public void setChildList(List<UserDo> childList) {
		this.children = childList;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTwoLevelPwd() {
		return twoLevelPwd;
	}
	public void setTwoLevelPwd(String twoLevelPwd) {
		this.twoLevelPwd = twoLevelPwd;
	}
	public Integer getGrade() {
		return grade;
	}
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	public String getOrgan() {
		return organ;
	}
	public void setOrgan(String organ) {
		this.organ = organ;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	public Long getReferrerId() {
		return referrerId;
	}
	public void setReferrerId(Long referrerId) {
		this.referrerId = referrerId;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public String getTreeNode() {
		return treeNode;
	}
	public void setTreeNode(String treeNode) {
		this.treeNode = treeNode;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public UserInfoDo getUserInfoDo() {
		return userInfoDo;
	}
	public void setUserInfoDo(UserInfoDo userInfoDo) {
		this.userInfoDo = userInfoDo;
	}
	public UserDo getReferrerDo() {
		return referrerDo;
	}
	public void setReferrerDo(UserDo referrerDo) {
		this.referrerDo = referrerDo;
	}
	public UserDo getParentDo() {
		return parentDo;
	}
	public void setParentDo(UserDo parentDo) {
		this.parentDo = parentDo;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getCreateUser() {
		return createUser;
	}
	public void setCreateUser(Long createUser) {
		this.createUser = createUser;
	}
	
	
	
	public BigDecimal getBonus() {
		return bonus;
	}
	public void setBonus(BigDecimal bonus) {
		this.bonus = bonus;
	}
	
	
	public int getRefCount() {
		return refCount;
	}
	public void setRefCount(int refCount) {
		this.refCount = refCount;
	}
	
	
	public String getUserCode2() {
		return userCode2;
	}
	public void setUserCode2(String userCode2) {
		this.userCode2 = userCode2;
	}
	public String getUserCode1() {
		return userCode1;
	}
	public void setUserCode1(String userCode1) {
		this.userCode1 = userCode1;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
	
	
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDo other = (UserDo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	public String getUserLocal() {
		return userLocal;
	}
	public void setUserLocal(String userLocal) {
		this.userLocal = userLocal;
	}
	@Override
	public String toString() {
		return "UserDo [id=" + id + ", userName=" + userName + ", weixin="
				+ weixin + ", zhifubao=" + zhifubao + ", password=" + password
				+ ", twoLevelPwd=" + twoLevelPwd + ", grade=" + grade
				+ ", organ=" + organ + ", remark=" + remark + ", enabled="
				+ enabled + ", referrerId=" + referrerId + ", parentId="
				+ parentId + ", treeNode=" + treeNode + ", createTime="
				+ createTime + ", updateTime=" + updateTime + ", name=" + name
				+ ", createUser=" + createUser + ", status=" + status
				+ ", userInfoDo=" + userInfoDo 
				+  ", refCount=" + refCount
				+ ", openId=" + openId + ", userCode2=" + userCode2
				+ ", userCode1=" + userCode1 + ", userCode=" + userCode
				+ ", bonus=" + bonus + ", children=" + children + "]";
	}
	
	
	/**
	 * 
	 *
	 * zhangyunhmf
	 *
	 */
    public UserDo getLeftChild() {
    	return getChildByGroupuserIdx(LEFT);
    }
    

    
	/**
	 * 
	 *
	 * zhangyunhmf
	 *
	 */
    public UserDo getRightChild() {
    	return getChildByGroupuserIdx(RIGHT);
    }
    
    
	/**
	 * 
	 *
	 * zhangyunhmf
	 *
	 */
    private UserDo getChildByGroupuserIdx(String flag) {
	    if(CollectionUtils.isEmpty(this.children)){
    		return null;
    	}
    	
	    for(UserDo user :this.children){
	    	if(user.getGroupuserIdx().equals(flag)){
	    		return user;
	    	}
	    }
	    return null;
    }
    
    
    
	/**
	 * 找出最左边的一个节点 
	 * zhangyunhmf
	 *
	 */
    public static UserDo getLastLeftUser(UserDo parent) {
	    UserDo leftUser = parent.getLeftChild();
	    if(null == leftUser){return parent;}
	    return getLastLeftUser(leftUser);
    }
	
	
	
}

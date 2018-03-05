/**  
 * @Project: hehenian-mobile
 * @Package com.hehenian.mobile.common.constants
 * @Title: WebThreadVariable.java
 * @Description: 线程变量
 *
 * @author: zhanbmf
 * @date 2015-3-28 上午11:36:15
 * @Copyright: HEHENIAN Co.,Ltd. All rights reserved.
 * @version V1.0   
 */
package com.redpack.constant;

import com.redpack.account.model.UserDo;
import com.redpack.account.model.UserInfoDo;

/**
 * @Description 描述方法作用
 * @author huangzl QQ: 272950754
 * @date 2015年5月27日 下午5:35:03
 * @Project hehenian-lend-login
 * @Package com.hehenian.login.common.constant 
 * @File WebThreadVariable.java
*/
public class WebThreadVariable {

	/**
	 * 当前用户线程变量
	 */
	private static ThreadLocal<UserInfoDo> userAccountDoVariable = new ThreadLocal<UserInfoDo>();
	
	/**
	 * 当前登录用户
	 */
	private static ThreadLocal<UserDo> userVariable = new ThreadLocal<UserDo>();
	
	/**
	 * session
	 */
	private static ThreadLocal<String> rootVariable = new ThreadLocal<String>();
	
	/**
	 * 得到线程当前用户对象
	 * @return
	 */
	public static UserInfoDo getUserAccountDo(){
		return userAccountDoVariable.get();
	}
	
	/**
	 * 设置当前用户
	 * 
	 * @param user
	 */
	public static void setUserAccountDo(UserInfoDo pd) {
		userAccountDoVariable.set(pd);
	}
	
	/**
	 * 移除当前用户
	 */
	public static void removeUserAccountDo() {
		userAccountDoVariable.remove();
	}
	
	/**
	 * 得到线程当前用户对象
	 * @return
	 */
	public static UserDo getUserDo(){
		return userVariable.get();
	}
	
	/**
	 * 设置当前用户
	 * 
	 * @param user
	 */
	public static void setUserDo(UserDo aud) {
		userVariable.set(aud);
	}
	
	/**
	 * 移除当前用户
	 */
	public static void removeUserDo() {
		userVariable.remove();
	}
	
	public static String getRoot(){
		return rootVariable.get();
	}
	
	public static void setRoot(String root){
		rootVariable.set(root);
	}
	
	public static void removeRoot(){
		rootVariable.remove();
	}
}

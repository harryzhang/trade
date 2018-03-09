/**  
 * @Project: hehenian-lend
 * @Package com.hehenian.web.common.constant
 * @Title: WebConstants.java
 * @Description: TODO
 * @author:  zhangyunhua
 * @date 2015年1月22日 上午11:52:02
 * @Copyright: HEHENIAN Co.,Ltd. All rights reserved.
 * @version V1.0  
 */
package com.redpack.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 常量类
 * 
 * @author:  zhangyunhua
 * @date 2015年1月22日 上午11:52:02
 * @Description 描述方法作用
 * @author huangzl QQ: 272950754
 * @date 2015年5月27日 下午5:34:57
 * @Project hehenian-lend-login
 * @Package com.hehenian.login.common.constant 
 * @File WebConstants.java
*/
public class WebConstants {

    /** 消息提示KEY */
    public static final String MESSAGE_KEY  = "message";
    
    /** session用户信息KEY */
    public static final String SESSION_USER  = "userDo";
    /**
     * session内容：admin管理员
     */
    public static final String SESSION_ADMIN = "admin";
    

    /** 验证码 */
    public static final String VERIFICATION_CODE = "randomCode";


    /**
     * 用户来源
     */
    public static final String SOURCEFROM_WEB            = "1";
    public static final String SOURCEFROM_WEIXIN         = "10";

    /**
     * 密码加密串
     */
    public static String PASS_KEY = "GDgLwwdK270Qj155xho8lyTp";
    
  
    

	public static final String ERR_CODE= "验证码错误";
	
	public static final String ERR_NAME="用户名错误";
	

	public static final String NOT_NULL="用户名密码不能为空";
	
	
	//短信发送用户名密码  财富风暴
//	public static final String SMS_URL ="http://222.73.117.158/msg/index.jsp";
//	   public static final String SMS_USE="vip-ys";
//	    public static final String SMS_PASSWORD="Ysjk9999";
	//埃瓦拉
	public static final String SMS_URL ="http://222.73.117.156/msg/index.jsp";
	public static final String SMS_USE="ANhui123";
    public static final String SMS_PASSWORD="Tch112233";
	
	
// public static final String SMS_URL ="http://127.0.0.1：8080/msg/index.jsp";
//    public static final String SMS_USE="jiekou-clcs-09";
//    public static final String SMS_PASSWORD="Txb778899";
    /**
     * 2%	直推奖
     */
    public static final String REFFER_RATE="REFFER_RATE";
    /**
     * 7.38	欧元汇率
     */
    public static final String EOUR_RATE="EOUR_RATE";
    /**
     * 1000	报单中心金额
     */
    public static final String REFFER_CENTER="REFFER_CENTER";
    /**
     * 68	股价
     */
    public static final String EOUR_AMOUNT="EOUR_AMOUNT";
    /**
     * 1.6	每天静态分线
     */
    public static final String STATIC_ONE_RATE="STATIC_ONE_RATE";
    /**
     * 2	管理奖
     */
    public static final String MANAGE_RATE="MANAGE_RATE";
    /**
     * 3	市投资提成
     */
    public static final String CITY_RATE="CITY_RATE";
    /**
     * 2	省投资提成
     */
    public static final String PROVINCE_RATE="PROVINCE_RATE";
    /**
     * 18	积分赠送比例
     */
    public static final String SENT_RATE="SENT_RATE";
    /**
     * 2	总监中心提成
     */
    public static final String CENTER_RATE="CENTER_RATE";
    /**
     * 2	董事中心提成
     */
    public static final String DONG_CENTER_RATE="DONG_CENTER_RATE";
    /**
     * 2	报单中心提成
     */
    public static final String REPORT_CENTER_RATE="REPORT_CENTER_RATE";
    /**
     * 10	上级消费奖
     */
    public static final String COMSUME_RATE="COMSUME_RATE";
    /**
     * 300	开奖时间
     */
    public static final String JINCAI_OPEN_TIME="JINCAI_OPEN_TIME";
    
    /**
     * 动态分红的判断不同标准的金额基准
     */
    public static final String FENHONG_CHOOSE_AMT="FENHONG_CHOOSE_AMT"; 
    
    /**
     * 1 0 是否促销
     */
    public static final String IF_PROM="IF_PROM";
    /**
     * 1 是 0 否
     */
    public static final String IF_SEND_MESSAGE="IF_SEND_MESSAGE";
    /**
     * 促销买几送一
     */
    public static final String PROM_GUQUAN="PROM_GUQUAN";
    /**
     * 促销随机赚送金币
     */
    public static final String PROM_POINT="PROM_POINT";
    /**
     * 促销推荐多少人送金币
     */
    public static final String PROM_USER="PROM_USER";
    /**
     * 管理奖金币和积分的分成比例
     */
    public static final String MANAGE_EOUR_POINT_RATE="MANAGE_EOUR_POINT_RATE";
    /**
     * 静态奖金币和积分的分成比例
     */
    public static final String STATIC_EOUR_POINT_RATE="STATIC_EOUR_POINT_RATE";
    /**
     * 报单中心股权数量 68
     */
    public static final String GUQAN_CENTER="GUQAN_CENTER";
    /**
     * 提现手续费
     */
    public static final String WITHDRAW_RATE="WITHDRAW_RATE";
    
    /** =====================埃瓦尔证券===================================**/
    /**
     * 帐户类型现金
     */
    public static final String RMB_ACCOUNT="rmb";
    /**
     * 帐户类型积分
     */
    public static final String POINE_ACCOUNT="point";
    /**
     * 帐户类型证券数
     */
    public static final String SECURITY_ACCOUNT="security";
    /**
     * 帐户类型激活豆
     */
    public static final String PET_ACCOUNT="pet";
    
    /**
     * 帐户类型奖金豆
     */
    public static final String JJD_ACCOUNT="jjd";
    
    
    /**
     * 帐户类型激活积分
     */
    public static final String JIFEN_ACCOUNT="jifen";
    
    /**
     * 帐户类型激活豆
     */
    public static final String TEAM_ACCOUNT="team";
    
    
    //静态分红
    public static final String STATIC_FEN_HONG="STATIC_FEN_HONG";
    //团队分红
    public static final String GROUP_FEN_HONG="GROUP_FEN_HONG";
    //团队奖 方案一
    public static final String MUILT_LEVEL_INCOME="MUILT_LEVEL_INCOME";
    
    //团队奖 方案二
    public static final String MUILT_LEVEL_INCOME_2="MUILT_LEVEL_INCOME_2";
    
    /**
     * 帐户类型推荐金额
     */
    public static final String REFFER_ACCOUNT="reffer";
    
    
    /**
     * 用户等级金额
     */
    public static final String USER_GRADE_AMOUNT="USER_GRADE_AMOUNT";
    /**
     * 用户返本扣率
     */
    public static final String UN_PAY_RATE="UN_PAY_RATE";
   
}

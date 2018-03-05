/**
* Created by he.zhiyi on 15/7/17.
*/
var app_ws_url         =  ['ws://120.76.216.160:14020/wetbackoffice','ws://120.76.216.160:14020/wetbackoffice'] ;
var M_USERTYPE_TRADER  = 1;          // 账户类型：交易账户
var M_WETPACKAGE_VER   = '1.0' ;
var M_ISENCRYPT        = '1' ;             // 0=不加密传输； 1=加密传输
var encrypt_iv         = '34F6f83CaC57865F' ;
var encrypt_key        = '0082D4FA04d85FfF' ;

var M_DEBUG_UID = '' ;
var M_DEBUG_PWD = '' ;

var ACCNAME ='微交易';//交易市场名称
var ACCID ='';//交易市场ID

// ---------------------------------------------------------
// 逻辑值定义
// ---------------------------------------------------------
var M_TRUE = '0';
var M_FALSE = '1';

// ---------------------------------------------------------
// 图表历史数据周期枚举
// ---------------------------------------------------------
var cycle_tik = 0;		//逐笔
var cycle_tikex = 1;	//逐笔增强字段
var cycle_sec = 2;		//1秒
var cycle_sec10 = 3;	//10秒
var cycle_min = 4;		//1分钟
var cycle_min5 = 5;		//5分钟
var cycle_min15 = 6;
var cycle_min30 = 7;	//30分钟
var cycle_hour = 8;		//1小时
var cycle_day = 9;		//日线
var cycle_week = 10;		//周线
var cycle_month = 11;
var cycle_year = 12;

// ---------------------------------------------------------
// 状态值定义
// ---------------------------------------------------------
var M_RETCODE_SUCCESS = '0' ;               // 应答报文返回码 - 成功
var M_RETCODE_FAIE = '1' ;                  // 应答报文返回码 - 未知错误
var M_DATA_UNENCRYPT = '0' ;                // 报文数据体加密状态 - 不加密
var M_DATA_ENCRYPT = '1' ;                  // 报文数据体加密状态 - 加密

// ---------------------------------------------------------
// 业务报文消息号定义
// ---------------------------------------------------------
var M_Q_PWDKEY = '1001';          // 密钥请求
var M_R_PWDKEY = '1002';          // 密钥应答
var M_Q_LOGIN_GW = '1003';        // 终端网关登录请求
var M_R_LOGIN_GW = '1004';        // 终端网关登录应答
var M_Q_LOGIN = '1005';           // 业务系统登录请求
var M_R_LOGIN = '1006';           // 业务系统登录应答
var M_Q_LOGOUT = '1007';          // 业务系统登出请求
var M_R_LOGOUT = '1008';          // 业务系统登出应答
var M_Q_CHANGEPWD = '1009';       // 修改登录密码请求
var M_R_CHANGEPWD = '1010';       // 修改登录密码应答
var M_Q_LOGIN_ACC = '1011';       // 管理后台代客下单的交易账户登录请求
var M_R_LOGIN_ACC = '1012';       // 管理后台代客下单的交易账户登录应答

var M_Q_AMOUNT = '2001';                // 资金账户信息查询请求
var M_R_AMOUNT = '2002';                // 资金账户信息查询应答
var M_Q_HOLDBILL = '2003';              // 持仓单查询请求
var M_R_HOLDBILL = '2004';              // 持仓单查询应答
var M_Q_LIMITBILL = '2005';             // 限价单查询请求
var M_R_LIMITBILL = '2006';             // 限价单查询应答
var M_Q_CLOSEBILL = '2007';             // 平仓单查询请求
var M_R_CLOSEBILL = '2008';             // 平仓单查询应答
var M_Q_SYMBOL = '2009';                // 商品信息查询请求
var M_R_SYMBOL = '2010';                // 商品信息查询应答
var M_Q_RULER = '2011';                 // 交易规则查询请求
var M_R_RULER = '2012';                 // 交易规则查询应答
var M_Q_NOTIC = '2013';                 // 交易商公告查询请求
var M_R_NOTIC = '2014';                 // 交易商公告查询应答
var M_Q_NOTICDETAIL = '2015';           // 交易商公告内容查询请求
var M_R_NOTICDETAIL = '2016';           // 交易商公告内容查询应答
var M_Q_TRADEPORT = '2017';             // 交易接口查询请求
var M_R_TRADEPORT = '2018';             // 交易接口查询应答
var M_Q_TOKENCHECK = '2019' ;           // 授权码有效性查询请求
var M_R_TOKENCHECK = '2020' ;           // 授权码有效性查询应答
var M_Q_SYMBOLPRICE = '2021' ;          // 商品最新价格查询请求
var M_R_SYMBOLPRICE = '2022' ;          // 商品最新价格查询应答
var M_Q_HIS_QUOTE    = "2023" ;         // 历史行情数据查询请求
var M_R_HIS_QUOTE    = "2024" ;         // 历史行情数据查询应答
var M_Q_HOLDPOSITION = "2027" ;         // 头寸查询
var M_R_HOLDPOSITION = "2028" ;         // 头寸查询
var M_Q_CER_INFO = '2029';              // 汇率
var M_R_CER_INFO = '2030';              // 汇率
var M_Q_INOUT_MONEY = '2031' ;          // 出入金查询请求
var M_R_INOUT_MONEY = '2032' ;          // 出入金查询应答应答
var M_Q_CANCEL_DW = '2033' ;            // 出入金撤销申请请求
var M_R_CANCEL_DW = '2034' ;            // 出入金撤销申请应答
var M_Q_AMOUNTINOUT = '2035';           // 出入金申请请求
var M_R_AMOUNTINOUT = '2036';           // 出入金申请应答

var M_Q_DELIVERY_ORDER = '2037' ;       // 交割单查询请求
var M_R_DELIVERY_ORDER = '2038' ;       // 交割单查询应答
var M_Q_POSITION_CLOSE_ORDER = '2039' ; // 按头寸平仓请求
var M_R_POSITION_CLOSE_ORDER = '2040' ; // 按头寸平仓应答
var M_Q_GROUP_CLOSE_ORDER = '2041' ;    // 按批量平仓请求
var M_R_GROUP_CLOSE_ORDER = '2042' ;    // 按批量平仓应答
var M_Q_MARKET_STATUS = '2043' ;        // 按市场状态查询请求
var M_R_MARKET_STATUS = '2044' ;        // 按市场状态查询应答

//var M_Q_PAYPORT = '6031'; // 支付机构表请求报文
//var M_R_PAYPORT = '6032'; // 支付机构表应答报文

var M_Q_QTY_PAYPORT = '2101';           // 查询签约银行请求
var M_R_QTY_PAYPORT = '2102';           // 查询签约银行应答
var M_Q_QTY_SYS_PAYPORT = '2103';       // 查询系统支持银行请求
var M_R_QTY_SYS_PAYPORT = '2104';       // 查询系统支持银行应答
var M_Q_PAYPORT_REGIST = '2105';        // 银行签约申请请求
var M_R_PAYPORT_REGIST = '2106';        // 银行签约申请应答
var M_Q_PAYPORT_UNREGIST = '2107';      // 银行解约申请请求
var M_R_PAYPORT_UNREGIST = '2108';      // 银行解约申请应答


//var M_Q_TOKEN = '2019';       // TOKEN 查询请求
//var M_R_TOKEN = '2020';       // TOKEN 查询应答

var M_Q_MARKETOPEN = '3001';            // 市价建仓委托请求
var M_R_MARKETOPEN = '3002';            // 市价建仓委托应答
var M_Q_MARKETCLOSE = '3003';           // 市价平仓委托请求
var M_R_MARKETCLOSE = '3004';           // 市价平仓委托应答
var M_Q_LIMITOPEN = '3005';             // 限价建仓委托请求
var M_R_LIMITOPEN = '3006';             // 限价建仓委托应答
var M_Q_LIMITCLOSE = '3007';            // 限价平仓委托请求
var M_R_LIMITCLOSE = '3008';            // 限价平仓委托应答
var M_Q_LIMITUNDO = '3009';             // 撤销限价单委托请求
var M_R_LIMITUNDO = '3010';             // 撤销限价单委托应答
var M_Q_MODIFYORDER = '3011';           // 修改单据请求
var M_R_MODIFYORDER = '3012';           // 修改单据应答

var M_R_PUSH_QUOTE = '4002';            // 实时行情应答
var M_R_PUSH_GOODSINFO = '4004';        // 商品信息修改通知应答
var PUSH_TRCOMPLETE = '4006';           // 委托单成交通知
var PUSH_ACCCHANGE = '4008';            // 资金变动通知
var PUSH_SAMEUSER_LOGIN = '4010';       // 同名账户登录通知
var PUSH_BE_LOGOUT = '4012';            // 客户端重新登录通知
var PUSH_HOLDBILLCHANGE = '4016';       // 持仓单变更通知
var PUSH_LIMITBILLCHANGE = '4018';      // 限价单变化通知
var PUSH_CLOSEBILLCHANGE = '4020';      // 平仓单变化通知
var PUSH_POSITIONCHANGE = '4022';       // 头寸变化通知
var PUSH_EXCHANGERATE = '4024' ;        // 汇率变更通知
var PUSH_DW_CHANGE = '4026' ;           // 出入金变化通知
var PUSH_SYMBOL_INFO = '4028' ;         // 商品信息变化通知
var PUSH_RISK_DOWN = '4030' ;           // 风险下降变化通知
var PUSH_RISK_UP = '4032' ;             // #风险上升变化通知
var PUSH_SPOTDELIVERY = '4034' ;        // #风险上升变化通知
var PUSH_BULLETIN = '8002' ;            // 交易商公告推送通知
var PUSH_MODIFY_PASSWORD  = '4038';     // 密码变更通知通知
var PUSH_PAYPORT_INFO  = '4040';        // 签约银行通知
var PUSH_BANK_IN_URL  = '4042';         // 签约银行通知

var M_Q_SUB_QUOTE = '5001';             // 订阅商品行情请求
var M_R_SUB_QUOTE = '5002';             // 订阅商品行情应答
var M_Q_UNSUB_QUOTE = '5003';           // 取消订阅商品行情请求
var M_R_UNSUB_QUOTE = '5004';           // 取消订阅商品行情应答


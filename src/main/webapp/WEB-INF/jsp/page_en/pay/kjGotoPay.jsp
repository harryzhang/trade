<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page language="java" import="java.io.*,java.sql.*,javax.sql.*,javax.naming.*"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>快捷通付款到卡网关接口测试页面</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  <body>
       <form id="form1" action="<%=request.getContextPath()%>/kjpay/sendPay.html" method="post">

接口名称:  <input type="text" name="service" value="create_payment_to_card">(例如：create_payment_to_card)
<br/>
接口版本:  <input type="text" name="version" value="1.0">(目前版本：1.0)
<br/>
合作者身份ID:  <input type="text" name="partner_id" value="200000030006">
<br/>
 字符集:  <input type="text" name="_input_charset" value="UTF-8">(类型有：UTF-8;GBK;GB2312)<br/>
 签名: <input type="text" name="sign" value="">(签约合作方的钱包唯一用户号 不可空)<br/>
 签名方式: <input type="text" name="sign_type" value="ITRUSSRV">(签名方式只支持RSA、MD5、ITRUSSRV 不可空)<br/>
 返回页面路径: <input type="text" name="return_url" value="">(页面跳转同步返回页面路径  可空)<br/>
 备注: <input type="text" name="memo" value=""><br/>
<%		
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyyMMddHHmmss");
		String orderTime = format.format(new java.util.Date());
		java.text.DecimalFormat def = new java.text.DecimalFormat("000000");
		int b=(int)(Math.random()*1000000%1000000);//产生0-1000000的整数随机数
		String lstStr = def.format(b);
		String outerTradeNo = orderTime + lstStr;
 %>
商户网站唯一订单号: <input type="text" name="outer_trade_no" value="<%=outerTradeNo%>">(不可空)<br/>
会员标识号: <input type="text" name="identity_no" value="payment001@qq.com">(不可空)<br/>
标识类型: <input type="text" name="identity_type" value="1">(付款方会员标识 不可空)<br/>
付款金额: <input type="text" name="payable_amount" value="0.01">(单位为：RMB Yuan。取值范围为[0.01，1000000.00]，精确到小数点后两位)<br/>
账户类型: <input type="text" name="account_type" value="301">(目前只支持基本户 不可空)<br/>
银行卡号: <input type="text" name="card_no" value="6226960801113465">(不可空)<br/>
银行卡账户名: <input type="text" name="account_name" value="曾人号">(可空)<br/>
银行编码: <input type="text" name="bank_code" value="CITIC">(不可空)<br/>
银行名称: <input type="text" name="bank_name" value="中信银行">(不可空)<br/>
银行分支行名称: <input type="text" name="branch_name" value=""><br/>
银行分支行号: <input type="text" name="bank_line_no" value="">(可空)<br/>
银行所在省: <input type="text" name="bank_prov" value="浙江">(不可空)<br/>
银行所在市: <input type="text" name="bank_city" value="杭州">(不可空)<br/>
对公/对私: <input type="text" name="company_or_personal" value="C">(对公/B,对私/C不可空)<br/>
到账级别: <input type="text" name="fundout_grade" value="">(可空)<br/>
出款目的: <input type="text" name="purpose" value="">(可空)<br/>
提交时间: <input type="text" name="submit_time" value="<%=orderTime%>">(不可空)<br/>
服务器异步通知页面路径 :  <input type="text" name="notify_url" value="http://122.224.203.210:18380/instant-trade-demo/createPaymentToCardNotifyUrlResponse.jsp">(可空)<br/>
商户私钥:  <input type="text" name="sign_key" value="MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAO/6rPCvyCC+IMalLzTy3cVBz/+wamCFNiq9qKEilEBDTttP7Rd/GAS51lsfCrsISbg5td/w25+wulDfuMbjjlW9Afh0p7Jscmbo1skqIOIUPYfVQEL687B0EmJufMlljfu52b2efVAyWZF9QBG1vx/AJz1EVyfskMaYVqPiTesZAgMBAAECgYEAtVnkk0bjoArOTg/KquLWQRlJDFrPKP3CP25wHsU4749t6kJuU5FSH1Ao81d0Dn9m5neGQCOOdRFi23cV9gdFKYMhwPE6+nTAloxI3vb8K9NNMe0zcFksva9c9bUaMGH2p40szMoOpO6TrSHO9Hx4GJ6UfsUUqkFFlN76XprwE+ECQQD9rXwfbr9GKh9QMNvnwo9xxyVl4kI88iq0X6G4qVXo1Tv6/DBDJNkX1mbXKFYL5NOW1waZzR+Z/XcKWAmUT8J9AkEA8i0WT/ieNsF3IuFvrIYG4WUadbUqObcYP4Y7Vt836zggRbu0qvYiqAv92Leruaq3ZN1khxp6gZKl/OJHXc5xzQJACqr1AU1i9cxnrLOhS8m+xoYdaH9vUajNavBqmJ1mY3g0IYXhcbFm/72gbYPgundQ/pLkUCt0HMGv89tn67i+8QJBALV6UgkVnsIbkkKCOyRGv2syT3S7kOv1J+eamGcOGSJcSdrXwZiHoArcCZrYcIhOxOWB/m47ymfE1Dw/+QjzxlUCQCmnGFUO9zN862mKYjEkjDN65n1IUB9Fmc1msHkIZAQaQknmxmCIOHC75u4W0PGRyVzq8KkxpNBq62ICl7xmsPM=">
<br/>
 <input type="submit" name="submit" value="确定提交" />

    </form>

  </body>
</html>

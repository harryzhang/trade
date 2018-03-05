<%@ page import="com.redpack.common.pay.controller.MD5" %>
<%@ page import="com.redpack.common.pay.controller.MD5Util" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'gotopay.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
   
    
    

    <%
    

    String MerNo			= "168885"; 
    
    String Amount  			= "0.01";
    
    String BillNo			= String.valueOf(System.currentTimeMillis());
    
    String MD5key 			= "12345678"; 
    
    //String ReturnURL		= basePath+"pay/payResult.html";    //客户支付返回商户前台网站URL.
    String ReturnURL		= "http://www.cfbabc.com/kuangji/pay/payResult.html";    //客户支付返回商户前台网站URL.
   
    String NotifyURL 		= basePath+"pay/notifyResult.html"; //服务端后台支付状态通知.  
  
    String MD5info; 
  
    String PaymentType		= "ICBC";
   
    String PayType 			= "CSPAY";//CSPAY:网银支付 ; NCPAY:无卡支付;UNION:银联无卡网银合并
   
    String MerRemark		= "Custom data";
    
    String  Products        =  "AppleNike";  
   
    //PaymentType = 银行代码:(空值为跳转到银联)
											//工行		"ICBC"
											//农行		"ABC"
											//中行		"BOCSH"
											//建行		"CCB"
											//招行		"CMB"
											//浦发		"SPDB"
											//广发		"GDB"
											//交行		"BOCOM"
											//邮储		"PSBC"
											//中信		"CNCB"
											//民生		"CMBC"
											//光大		"CEB"
											//华夏		"HXB"
											//兴业		"CIB"

    
	MD5Util md5util = new MD5Util();
		
    MD5info = md5util.signMap(new String[]{MerNo,BillNo,Amount,ReturnURL}, MD5key, "REQ");



%>
    
    
    
    
    <form action = "https://www.95epay.cn/sslpayment" method = "post">
   	
<input    type="hidden"    	name="MerNo"       	  value="<%=MerNo%>"/>

<input    type="hidden"    	name="BillNo"         value="<%=BillNo%>"/>


<input    type="hidden"    	name="Amount"        value="<%=Amount%>"/>

<input    type="hidden"    	name="PaymentType"   value="<%=PaymentType%>"/>

<input 	  type="hidden" 	name="PayType" 			value="<%=PayType%>">

<input    type="hidden"    	name="ReturnURL"      value="<%=ReturnURL%>"/>

<input    type="hidden"    	name="NotifyURL"      value="<%=NotifyURL%>"/>

<input    type="hidden"    	name="MD5info"        value="<%=MD5info%>"/>

<input    type="hidden"    	name="MerRemark"        value="<%=MerRemark%>"/>

<input    type="hidden"    	name="products"                 value="<%=Products%>"/>

<p align ="center"><input type="submit" name="b1"        value="充值"/></p>
   
   </form>
   
   
  </body>
</html>

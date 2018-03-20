<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en" class="heightFull">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=0">
<meta name="apple-touch-fullscreen" content="yes">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">


<title>安全设置</title>

<link href="<c:url value ='/res-kuangji/css/global.css'/>"
	rel="stylesheet" type="text/css" />
<link href="<c:url value ='/res-kuangji/css/security.css'/>"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="<c:url value ='/res-kuangji/js/jquery-2.1.1.min.js'/>"></script>
<script type="text/javascript"
	src="<c:url value ='/res-kuangji/js/top.js'/>"></script>
</head>
<BODY class="heightFull displayFlex">
	<DIV class="top">
		<b onclick="javascript:window.location.href=&#39;<c:url value='/redPack/personalCenter.html'/>&#39;"></b>
		<DD>安全设置</DD>
		<SPAN></SPAN>
		<UL>
			   <li class="index" onclick="javascript:window.location.href=&#39;<c:url value='/redPack/personalCenter.html'/>&#39;">首 页</li>
	            <li class="member" onclick="javascript:window.location.href=&#39;<c:url value='/redPack/personalCenter.html'/>&#39;">会员中心</li>
	            <li class="shoppingCart" onclick="javascript:window.location.href=&#39;<c:url value='/cart/cart.html'/>&#39;">购物车</li>
	       
		</UL>
	</DIV>
	<STYLE>
.top dd{
	font-size: 1.6rem;
}
.alert {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px
}

.alert-danger {
	color: #a94442;
	background-color: #f2dede;
	border-color: #ebccd1;
}

.alert {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
}

.alert {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px
}

.alert-success {
	color: #3c763d;
	background-color: #dff0d8;
	border-color: #d6e9c6;
}

.alert {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
}
.heightFull{
	height: 100%;
	overflow: hidden;
}
.displayFlex {
    display: flex;
    flex-direction: column;
    background: #191c23;
}
.top{
	background-color: #20222e;
}
.top ul{
	border: 1px solid #e5e5e5;
}
.top ul li{
    width: initial;
	background: #191c23;
	border-top: 1px solid rgb(204, 204, 204); 
}
.top ul li:first-child{
	border-top: 0;
}
.ul-container{
	flex: 1;
	overflow-y: auto;
	
}
ul.manage li{
	border-bottom-color: #1c1e2a;
	color:#fff;
}
</STYLE>

	<UL class="manage ul-container">
		<LI
			onclick="javascript:window.location.href ='<c:url value='/common/modifyInfo.html'/>'">个人资料</LI>
		<LI
			onclick="javascript:window.location.href ='<c:url value='/account/resetPwdIndex.html?pwdFlag=login'/>'">重置登录密码</LI>
			
		<LI
			onclick="javascript:window.location.href ='<c:url value='/account/resetPwdIndex.html?pwdFlag=login&pwdType=twoPwd'/>'">重置二级密码</LI>
		<%--
 		<LI 
 			onclick="javascript:window.location.href ='<c:url value='/account/applyAgent.html'/>'">代理申请</LI>
 		<LI 
 			onclick="javascript:window.location.href ='<c:url value='/account/reportCenter.html'/>'">设置报单中心</LI>
 		--%>
	</UL>
	<DIV class="mengban"></DIV>
	<DIV class="tanceng">
		<INPUT type="password" placeholder="请输入支付密码">
		<P></P>
		<INPUT class="confirm" value="确认" type="button">
	</DIV>
</BODY>
</HTML>

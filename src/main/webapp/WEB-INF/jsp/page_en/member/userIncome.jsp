<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=0">
<meta name="apple-touch-fullscreen" content="yes">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">


<title>My income</title>

<link href="<c:url value ='/res-kuangji/css/global.css'/>"
	rel="stylesheet" type="text/css" />
<link href="<c:url value ='/res-kuangji/css/goodsDetails.css'/>"
	rel="stylesheet" type="text/css" />
<link href="<c:url value ='/res-kuangji/css/myWealth.css'/>"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="<c:url value ='/res-kuangji/js/jquery-2.1.1.min.js'/>"></script>
<script type="text/javascript"
	src="<c:url value ='/res-kuangji/js/top.js'/>"></script>

</head>
<body>


	<div class="top">
		<b
			onclick="javascript:window.location.href=&#39;<c:url value='/redPack/personalCenter.html'/>&#39;"></b>
		<dd>My income</dd>
		<span></span>
		<ul>
			<li class="index" onclick="">首 页</li>
			<li class="member" onclick="">会员中心</li>
			<li class="shoppingCart">购物车</li>
		</ul>
	</div>

	<div class="topmoney">
		<div class="balance">Balance（RMB）</div>
		<div class="money">${userAccount.amount}</div>

	</div>
	
	<div class="centent">
		<dl class="cenleft">
			<dt class="exchange">
				Stock number<font> ${userAccount.kuangjiQty} </font>
			</dt>
			
		</dl>

	</div>
	
	<div class="centent">
		<dl class="cenleft">
			<dt class="exchange">
				Balance <font> ${userAccount.amount} RMB</font>
			</dt>
			<dd onclick="javascript:window.location.href ='<c:url value='/member/amountDetail.html'/>'">
				<font>View details</font>
			</dd>
		</dl>
		<dl class="" onclick="">
			<dt></dt>
			<dd></dd>
		</dl>
		<dl class="cenright xinzeng" onclick="window.location.href ='<c:url value='/member/withdraw.html'/>'">
			<dt></dt>
			<dd>Withdraw</dd>
		</dl>
	</div>

	<!--
<div class="centent">
    <dl class="cenleft">
        <dt class="exchange">代金券<font>0张</font></dt>
        <dd onClick="javascript:window.location.href = 'http://yzc.ll0534.net/mobile/member/user_ticket_detail'"><font>查看明细</font></dd>
    </dl>
    <dl class="cenright zhuanzhang" onClick="javascript:window.location.href ='http://yzc.ll0534.net/mobile/member/transferTicket'">
        <dt></dt>
        <dd>转账</dd>
    </dl>
</div>-->


	<div class="centent">
		<dl class="cenleft">
			<dt class="exchange">
				Cash records<font> </font>
			</dt>
			<dd
				onclick="javascript:window.location.href = '<c:url value='/withdraw/userWithdraw'/>'">
				<font>View details</font>
			</dd>
		</dl>

	</div>


</body>
</html>
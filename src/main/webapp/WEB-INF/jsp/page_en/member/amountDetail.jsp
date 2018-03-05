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


<title>Fund details</title>

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
		<dd>Fund details</dd>
		<span></span>
		<ul>
			<li class="index" onclick="">Home</li>
			<li class="member" onclick="">User center</li>
		</ul>
	</div>

	
	<div class="centent">
		<dl class="cenleft">
			<dt class="exchange">
				Income detail
			</dt>
			
		</dl>

	</div>
	
	<div class="centent">
		<dl class="cententAll">
			<dt class="exchange">
				Today create euro<font> ${kjMap.income== null?0 : kjMap.income} RMB</font>
			</dt>
			<dd class="exchange">
				Reference Income
			</dd>
			<dd class="exchange">
				<font> one： ${kjMap.oneCommission == null?0 :kjMap.oneCommission }  RMB;&nbsp&nbsp&nbsp&nbsp two： ${kjMap.twoCommission== null?0 :kjMap.twoCommission  }  RMB ;&nbsp&nbsp&nbsp&nbsp  three： ${kjMap.threeCommission == null?0 :kjMap.threeCommission } RMB</font>
			</dd>
			<br>
			<dd class="exchange">
				Manager Income
			</dd>
			<dd class="exchange">
				<font>one:${userIncome.one == null?0 : userIncome.one} RMB;&nbsp&nbsp&nbsp&nbsp two:${userIncome.two == null?0 : userIncome.two}  RMB;&nbsp&nbsp&nbsp&nbsp three:${userIncome.three == null? 0 : userIncome.three} RMB</font>
			</dd>
		</dl>
	

	</div>

	<div class="centent">
		<dl class="cenleft">
			<dt class="exchange">
				Cash records<font> </font>
			</dt>
			<dd
				onclick="javascript:window.location.href = '<c:url value='/withdraw/userWithdraw'/>'">
				<font>View detail</font>
			</dd>
		</dl>

	</div>

	<div class="centent">
		<dl class="cenleft">
			<dt class="exchange">
				Recharge records<font> </font>
			</dt>
			<dd
				onclick="javascript:window.location.href = '<c:url value='/withdraw/chongzhiDetail'/>'">
				<font>View detail</font>
			</dd>
		</dl>

	</div>


</body>
</html>
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


<title>资金明细</title>

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
<style type="text/css">
	.heightFull{
		height: 100%;
		overflow: hidden;
	}
	.displayFlex {
        display: flex;
        flex-direction: column;
        background: #191c23;
    }
	div.centent{
		border-bottom: 10px solid #191c23;	
		background-color: #1c1e2a;
	}
	div.centent dl.cenleft dt, div.centent dl.cententAll dt{
		color: #fff;
	}
	.top{
		background: #20222e;
		border-bottom: 0;
	}
	.top ul{
		background: #20222e;
		border: 1px solid #e5e5e5;
	}
	.top ul li:last-child{
		border-bottom: 0;
	}
</style>
</head>
<body  class="heightFull displayFlex">


	<div class="top">
		<b
			onclick="javascript:window.location.href=&#39;<c:url value='/redPack/personalCenter.html'/>&#39;"></b>
		<dd style="color: #fff">资金明细</dd>
		<span></span>
		<ul>
			<li class="index" onclick="">首 页</li>
			<li class="member" onclick="">会员中心</li>
			<li class="shoppingCart">购物车</li>
		</ul>
	</div>

	
	<div class="centent">
		<dl class="cenleft">
			<dt class="exchange">
				 收益明细
			</dt>
		</dl>

	</div>
	
	<div class="centent">
		<dl class="cententAll">
			<dt class="exchange">
				今日静态分红<font> <fmt:formatNumber type="number"    pattern="#,###,###,###,###,##0.0000" value="${todayAmount}"/> 欧</font>
			</dt>
			<dd class="exchange">
				今日团队收益 <font> <fmt:formatNumber type="number"    pattern="#,###,###,###,###,##0.0000" value="${teamAmount}"/> 欧</font> 
			</dd>
<!-- 			<dd class="exchange"> -->
<%-- 				<font> 一代： ${kjMap.oneCommission == null?0 :kjMap.oneCommission }  元;&nbsp&nbsp&nbsp&nbsp 二代： ${kjMap.twoCommission== null?0 :kjMap.twoCommission  }  元 ;&nbsp&nbsp&nbsp&nbsp  三代： ${kjMap.threeCommission == null?0 :kjMap.threeCommission }元</font> --%>
<!-- 			</dd> -->
<!-- 			<br> -->
<!-- 			<dd class="exchange"> -->
<!-- 				管理收益 -->
<!-- 			</dd> -->
<!-- 			<dd class="exchange"> -->
<%-- 				<font>一代:${userIncome.one == null?0 : userIncome.one} 元;&nbsp&nbsp&nbsp&nbsp 二代:${userIncome.two == null?0 : userIncome.two}  元;&nbsp&nbsp&nbsp&nbsp 三代:${userIncome.three == null? 0 : userIncome.three} 元</font> --%>
<!-- 			</dd> -->
		</dl>

	</div>

	<div class="centent">
		<dl class="cenleft">
			<dt class="exchange">
				提现记录<font> </font>
			</dt>
			<dd
				onclick="javascript:window.location.href = '<c:url value='/withdraw/userWithdraw'/>'">
				<font>查看明细</font>
			</dd>
		</dl>

	</div>

	<div class="centent">
		<dl class="cenleft">
			<dt class="exchange">
				充值记录<font> </font>
			</dt>
			<dd
				onclick="javascript:window.location.href = '<c:url value='/member/chongzhiDetail.html'/>'">
				<font>查看明细</font>
			</dd>
		</dl>

	</div>


</body>
</html>
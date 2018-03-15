<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=0">
<meta name="apple-touch-fullscreen" content="yes">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<title>个人中心</title>
<link href="<c:url value ='/res-kuangji/css/global.css'/>"	rel="stylesheet" type="text/css">
<link href="<c:url value ='/res-kuangji/css/member_yzc.css'/>"	rel="stylesheet" type="text/css">

<script type="text/javascript"	src="<c:url value ='/res-kuangji/js/jquery-2.1.1.min.js'/>"></script>
<script type="text/javascript"	src="<c:url value ='/res-kuangji/js/top.js'/>" used="1"></script>
<script type="text/javascript"	src="<c:url value ='/res-kuangji/js/member_yzc.js'/>"></script>

<style type="text/css">
	html,.body{
	    width: 100%;
	    height: 100%;
	    background: #191c23;
	}
	#container{
		width: 100%;
		height: 100%;
	    background: #191c23;
		overflow: hidden;
		display: flex;
		flex-direction: column;
	}
	.memberInfo{
		background-color: #2f2c3f;
	}
	.tleft{
		margin-top: 35px;
	}
	.tleft dl dt{
		margin-right: 15px;
	}
	.tab-container{
		flex: 1;
		overflow-y: auto;
	}
	.tab-container ul{
		margin-top: 1.6rem;
		margin-bottom: 6rem;
		background-color: #20222e;
	}
	.tab-container ul li{
		height: 5rem;
		line-height: 5rem;
		color: #fff;
	    width: 90%;
    	margin: 0 auto;
    	font-size: 1.6rem;
		border-top: 1px solid #1c1e2a; 
	}
	.tab-container ul li:first-child{
		border-top: 0;
	}
	.tab-container ul li img{
		width: 2rem;
	    vertical-align: text-top;
    	margin-right: 1rem;
	}
</style>

</head>
<body class="body">
	<div id="container">
		<div class="memberInfo">
			<div class="tleft">
				<dl>
					<dt>
						<img src="<c:url value='/res-kuangji/images/log.jpg'/>">
					</dt>
					<dd class="uname">${userDo.name}</dd>
					<dd class="uma">欢迎登录CMCC加拿大枫叶加密数字货币</dd>				
				</dl>
			</div>
		</div>

		<div class="tab-container">
			<ul>
				<li onclick="window.location.href ='<c:url value='/account/regIndex.html'/>'" ><img src="<c:url value ='/res-kuangji/images/mitems_04.png'/>" >注册</li>
				<li onclick="window.location.href ='<c:url value='/member/userIncome.html'/>'" ><img src="<c:url value ='/res-kuangji/images/menus_02.png'/>">帐户预览</li>
				<li onclick="window.location.href ='<c:url value ='/trade/myorder.html'/>'" ><img src="<c:url value ='/res-kuangji/images/indexs_03.png'/>">我的订单</li>
				<li onclick="window.location.href ='<c:url value='/trade/saveMoney.html'/>'" ><img src="<c:url value ='/res-kuangji/images/mitems_05.png'/>">存入算力钱包</li>
				<li onclick="javascript:window.location.href=&#39;<c:url value ='/barcode/toMybarcode.html?netWork=A&mobile=' />${userDo.userName}&#39;" ><img src="<c:url value ='/res-kuangji/images/mitems_03.png'/>">我的二维码</li>
				<%--<li onclick="window.location.href ='/member/userManager.html'"><img src="<c:url value ='/res-kuangji/images/fenxiao_manage.png'/>">分享管理</li> --%>
				<li onclick="window.location.href ='<c:url value='/member/shareManager.html'/>'"><img src="<c:url value ='/res-kuangji/images/fenxiao_manage.png'/>">分享管理</li>
				<li onclick="window.location.href ='<c:url value='/member/amountDetail.html'/>'" ><img src="<c:url value ='/res-kuangji/images/mitems_20.png'/>">收益明细</li>
				<li onclick="window.location.href = '<c:url value='/member/waitCreate.html'/>'"><img src="<c:url value ='/res-kuangji/images/mitems_01.png'/>" />价格走势</li>
				<li onclick="window.location.href ='<c:url value='/member/userSettle.html'/>'" ><img src="<c:url value ='/res-kuangji/images/mitems_01.png'/>">帐户设置</li>
				<li onclick="window.location.href ='<c:url value='/member/waitCreate.html'/>'"><img src="<c:url value ='/res-kuangji/images/mitems_01.png'/>">商家对接</li>
				<li onclick="javascript:window.location.href ='<c:url value='/member/waitCreate.html'/>'"><img src="<c:url value ='/res-kuangji/images/mitems_03.png'/>">一币拍宝</li>
				<li onclick="window.location.href = '<c:url value='/member/waitCreate.html'/>'"><img src="<c:url value ='/res-kuangji/images/nav_02.png'/>" />有奖竞猜</li>
				<li onclick="window.location.href ='<c:url value='/notice/notice.html'/>'" ><img src="<c:url value ='/res-kuangji/images/mitems_06.png'/>" />公告</li>
				<li onclick="window.location.href ='<c:url value='/quest/questView.html'/>'" ><img	src="<c:url value ='/res-kuangji/images/duihuan_detail.png'/>" />留言反馈</li>
				<li onclick="window.location.href ='<c:url value='/login/loginout.html'/>'"><img src="<c:url value ='/res-kuangji/images/mitems_06.png'/>">退出帐户</li>
			</ul>
		</div>

		<%@ include file="../include/foot_kangji.jsp"%>
	</div>
</body>
</html>
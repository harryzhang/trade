<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=0">
<meta name="apple-touch-fullscreen" content="yes">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">


<title>分享管理</title>


<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" href='<c:url value="/res/js/plugins/modal/modal.css?v="/>${cssversion}'/>
<link href="<c:url value ='/res-kuangji/css/global.css'/>"	rel="stylesheet" type="text/css">
<link href="<c:url value ='/res-kuangji/css/member_yzc.css'/>"	rel="stylesheet" type="text/css">
<link href="<c:url value ='/res-shichang/css/reset.css'/>" rel="stylesheet" type="text/css">

<script type="text/javascript"	src="<c:url value ='/res-kuangji/js/jquery-2.1.1.min.js'/>"></script>
<script type="text/javascript"	src="<c:url value ='/res-kuangji/js/top.js'/>" used="1"></script>
<script type="text/javascript"	src="<c:url value ='/res/js/global.js'/>"></script>
<script type="text/javascript" src='<c:url value="/res/js/plugins/modal/modal.js?v="/>${jsversion}'></script>


<style type="text/css">
		.body{
			display: flex;
			flex-direction: column;
		}
		
		#container{
			width: 100%;
			height: 100%;
		    background: #191c23;
    		overflow: hidden;
    		display: flex;
    		flex-direction: column;
		}
		.tab-container{
			flex: 1;
			overflow-y: auto;
		}
		.title{
			height: 50px;
			line-height: 50px;
			color: #fff;
			font-size: 1.7rem;
			text-align: center;
		}
		.bodyImg{
		    transform: rotateY(180deg);
		    opacity: .2;
		    position: absolute;
		    width: 8rem;
		    left: -1.7rem;
		    z-index: 0;
		}
		.profit{
		    width: 90%;
		    margin: 0 auto;
		    background: #2f2c3f;
		    height: 13rem;
		    position: relative;
		    overflow: hidden;
		}
		.profit p{
			position: absolute;
    		left: 2rem;
    		font-size: 1.5rem;
		}
		.profit-title{
			top: 2rem;
		}
		.profit-money{
			font-size: 5rem !important;
			top: 5rem;
		}
		.profit-yesterday{
			top: 10rem;
			color: #999;
		}
		.profitImg{
		    width: 20rem;
		    opacity: .1;
		    float: right;
		    margin-top: -3rem;
		    margin-right: -8rem;
		}
		.profit-color{
			color: #facd02;
		}
		.colorWhite{
			color: #fff;
		}
		.msg-warp{
			display: flex;
			flex-direction: column;
			justify-content: center;
		}
		.msg-calculated{
			height: 60%;
			width: 100%;
			display: flex;
		}
		.flex{
			margin-top: 1.6rem;
			flex: 1;
		}
		.msg-money{
		    width: 42%;
		    margin-top: 1.6rem;
		}
		.msg-money p{
			font-size: 1.5rem;
			text-indent: 1rem;
			margin-top: 1.5rem;
			color: #888;
		}
		.msg-money p:nth-child(2){
    		font-size: 2.4rem;
		    color: #facd02;
		}



	</style>
	
</head>
<body class="body">
	<div class="top">
		<b onclick="javascript:history.go(-1);">返回</b>
		<dd>分享管理</dd>
		<span></span>
		<ul>
			<li class="index" onclick="">首 页</li>
			<li class="member" onclick="">会员中心</li>
		</ul>
	</div>

	<div id="container">
		
		<div class="tab-container">
			<div class="profit">
				<p class="profit-color profit-title">算力累计收益</p>
				<p class="profit-color profit-money">8888.88888</p>
				<p class="profit-yesterday">昨日收益 555.55555</p>
				<img src="./img/leaves.png" alt="" class="profitImg">
			</div>
			<div class="msg-warp">
				<div class="msg-calculated">
					<div class="flex"></div>
					<div class="msg-money">
						<p>左区分享</p>
						<p>1234.50</p>
					</div>
					<div class="flex"></div>
					<div class="msg-money">
						<p>右区分享</p>
						<p>123.40</p>
					</div>
					<div class="flex"></div>
				</div>
				<div class="msg-calculated">
					<div class="flex"></div>
					<div class="msg-money">
						<p>左周新增</p>
						<p>1234.50</p>
					</div>
					<div class="flex"></div>
					<div class="msg-money">
						<p>右周新增</p>
						<p>123.40</p>
					</div>
					<div class="flex"></div>
				</div>
				<div class="msg-calculated">
					<div class="flex"></div>
					<div class="msg-money">
						<p>直推分享</p>
						<p>1234.50</p>
					</div>
					<div class="flex"></div>
					<div class="msg-money">
						<p>团队业绩</p>
						<p>123.40</p>
					</div>
					<div class="flex"></div>
				</div>
			</div>
		
		</div>
	</div>
	
	<%@ include file="../include/foot_kangji.jsp"%>



<script type="text/javascript">
	
    
</script>

	</body>
</html>
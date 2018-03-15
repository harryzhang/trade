<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=0">
<meta name="apple-touch-fullscreen" content="yes">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">


<title>敬请期待</title>


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
		.top{
			position: relative;
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
		.containerImg{
			width: 20rem;
			height: 20rem;
			margin: 20% auto;
		}
		.containerImg img{
			width: 100%;
			height: 100%;
		}
		.container-text{
			text-align: center;
		    color: #fff;
		    font-size: 1.6rem;
		}
	</style>
	
</head>
<body class="body">

	<div class="top">
		<b onclick="javascript:history.go(-1);">返回</b>
		<dd>敬请期待</dd>
		<span></span>
		<ul>
			<li class="index" onclick="">首 页</li>
			<li class="member" onclick="">会员中心</li>
		</ul>
	</div>
	
	<div id="container">
		<div class="tab-container">
			<div class="containerImg">
				<img src="<c:url value='/res/images/leaves.png'/>" alt="">
			</div>
			<!-- <div>
				<p class="container-text">敬请期待</p>
			</div> -->		
		</div>
	</div>

	<%@ include file="../include/foot_kangji.jsp"%>



<script type="text/javascript">    
    
    
    
</script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=0">
<meta name="apple-touch-fullscreen" content="yes">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">


<title>定存算力</title>


<link href="<c:url value ='/res-kuangji/css/global.css'/>"	rel="stylesheet" type="text/css">
<link href="<c:url value ='/res-kuangji/css/member_yzc.css'/>"	rel="stylesheet" type="text/css">
<link href="<c:url value ='/res-shichang/css/reset.css'/>"	rel="stylesheet" type="text/css">

<script type="text/javascript"	src="<c:url value ='/res-kuangji/js/jquery-2.1.1.min.js'/>"></script>
<script type="text/javascript"	src="<c:url value ='/res-kuangji/js/top.js'/>" used="1"></script>
<script type="text/javascript"	src="<c:url value ='/res-kuangji/js/member_yzc.js'/>"></script>


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
		#tab{
			height: 4rem;
			background: #20222e;
    		overflow: hidden;
		}
		#tab ul{
			display: flex;
			height: 100%;
		}
		#tab ul li{
			flex: 1;
			font-size: 1.6rem;
    		color: #fff; 
    		text-align: center;
		    line-height: 3.5rem;
		}
		#tab .tab-select{
			width: 50%;
		    height: 95%;
	    	margin: 0 auto;
	    	color: #84888b;
	    	line-height: 4rem;
	    	text-align: center;
		}
		.tab-active .tab-select{
			color: #fbcb11 !important;
			border-bottom: 3px solid #fbcb11;
		}
		.tab-container{
			flex: 1;
			overflow-y: auto;
		}
		.tab-container-active{
			display: block !important;
		}
		.tab-container .tab-container-content{
			font-size: 1.4rem;
			color: #fff;
			color: #84888b;
			display: none;
			text-align: center;
		}
		.tab-container-content p{
			margin-top: 1rem;
			text-align: center;
		}
		.tab-container-content .title{
		    height: 4rem;
		    font-size: 1.8rem;
		    line-height: 4rem;
		    text-align: left;
		    display: flex;
		}
		.tab-container-content .title span{
			line-height: 4rem;
		    margin-left: 1.7rem;
			width: 50%;
		}

		.box{
			width: 100%;
   			height: auto;
    		overflow: hidden;
		}
		.box li{
		    padding: 0px 5%;
		    width: 100%;
		    height: 45px;
		    border-bottom-color: #1c1e2a;
		    border-bottom-width: 1px;
		    border-bottom-style: solid;
		    float: left;
		    background-color: #20222e;
		}
		.box li label {
		    width: auto;
		    height: 45px;
		    line-height: 45px;
		    font-size: 1.6rem;
		    float: left;
		    color: #fff;
		}
		.box li input {
		    padding-left: 7px;
		}
		input.wtext {
		    border: currentColor;
		    width: 70%;
		    height: 43px;
		    color: rgb(201, 201, 201);
		    font-size: 1.6rem;
		    float: left;
	        background: transparent;
	        outline: 0;
		}	
	   	input.txbut {
		    margin: 10px 5%;
		    border-radius: 5px;
		    border: currentColor;
		    width: 90%;
		    height: 45px;
		    text-align: center;
		    color: #000;
		    float: left;
		    background-color: #facd02;
		    font-size: 1.6rem;
		}
		.fee{
		    position: absolute;
    		right: 2rem;
		}
		.tipColor{
			color: #facd02;
		}
	</style>
	
</head>
<body class="body">

	<div class="top">
		<b
			onclick="javascript:window.location.href=&#39;<c:url value='/redPack/personalCenter.html'/>&#39;"></b>
		<dd>定存算力</dd>
		<span></span>
		<ul>
			<li class="index" onclick="">首 页</li>
			<li class="member" onclick="">会员中心</li>
		</ul>
	</div>
	
	<div id="container">
		 
		<div class="tab-container">
			<div class="tab-container-content tab-container-active" data='0'>
				<ul class="box">
					<li>
						<label>定存数量</label>
						<input class="wtext" onblur="checkNo(this.value)" id="" value="" name="" maxlength="4" type="number" placeholder="请输入100 ~ 2000且为100整数倍的数量">
					</li>
					<li>
						<label>支付密码</label> 
						<input class="wtext" type="password" placeholder="请输入≥6位的字母+数字的密码">  
					</li>
					<li>
						<label>总价：$0 </label>
					</li>
				</ul>
				<div>
					<input class="txbut" value="存入" type="button">
				</div>
			</div>
		</div>
	</div>

	<%@ include file="../include/foot_kangji.jsp"%>

</body>
</html>
<script type="text/javascript">
	$(".tab").find('li').on('click', function(){
		$(".tab").find('li').removeClass('tab-active')
		$(this).addClass('tab-active')
		var index = $(this).attr('data')

		$(".tab-container").find('div').removeClass('tab-container-active')
		$.each($(".tab-container").find('div'), function(idx, data){
			if(index == $(data).attr('data')){
				$(data).addClass('tab-container-active')
			}
		})
	})
</script>
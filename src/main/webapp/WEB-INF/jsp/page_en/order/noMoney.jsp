<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=0">
        <meta name="apple-touch-fullscreen" content="yes">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="apple-mobile-web-app-status-bar-style" content="black">
        <meta name="format-detection" content="telephone=no">


        <title>会员中心--订单</title>

        <link href="<c:url value ='/res-kuangji/css/global.css'/>" rel="stylesheet" type="text/css">
        <link href="<c:url value ='/res-kuangji/css/shoppingCart.css'/>" rel="stylesheet" type="text/css">
		
		
		<style>
		    .alert{padding:15px;margin-bottom:20px;border:1px solid transparent;border-radius:4px}
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
		</style>

		<style>
		    .alert{padding:15px;margin-bottom:20px;border:1px solid transparent;border-radius:4px}
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
		</style>
 
   </head>
<body>

<div class="top">
		<b onclick="javascript:window.location.href=&#39;<c:url value='/redPack/personalCenter.html'/>&#39;" ></b><dd>出错了
</div>
<div class="main">
    <div class="tanceng cent_a" style="top: 285px;display:block">
        <ul>
        	<img style="margin:0;width:100%" src="<c:url  value='/res-kuangji/images/error.jpg' />">
            <li style="text-align: center;font-size: large;">
            	余额不足
            </li>
        </ul>
    </div>
</div>
</body>
</html>
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
        <link href="<c:url value ='/res-kuangji/css/orderList.css'/>" rel="stylesheet" type="text/css">
        <script type="text/javascript" src="<c:url value ='/res-kuangji/js/jquery-2.1.1.min.js'/>"></script>
        <script type="text/javascript" src="<c:url value ='/res-kuangji/js/top.js'/>"></script>
		<script type="text/javascript" src="<c:url value ='/res-kuangji/js/orderList.js'/>"></script>
 
 
		
		<script type="text/javascript">
		</script>
   </head>
   <body>

        <div class="top">
	<b onclick="javascript:window.location.href=&#39;<c:url value='/redPack/personalCenter.html'/>&#39;"></b>
	<dd>购物订单</dd>
	<span></span>
        <ul>
            <li class="index" onclick="javascript:window.location.href=&#39;<c:url value='/firstpage/toFirstpage.html'/>&#39;">首 页</li>
            <li class="member" onclick="javascript:window.location.href=&#39;<c:url value='/redPack/personalCenter.html'/>&#39;">会员中心</li>
            <li class="shoppingCart" onclick="javascript:window.location.href=&#39;<c:url value='/cart/cart.html'/>&#39;">购物车</li>
	            
        </ul>
</div>

        
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
 
<div class="main">


    
        


    </div>
    <div class="mengban" style="height: 702px;"></div>
    <div class="tanceng cent_a" style="top: 285px;">
        <ul>
            <li>是否立即删除?</li>
            <li class="gbbut qx">取消</li>
            <li class="gbbut xgcolor">确定</li>
        </ul>
    </div>

    <div class="tanceng cent_b" style="top: 285px;">
        <ul>
            <li>是否确定收货?</li>
            <li class="gbbut qx">取消</li>
            <li class="gbbut xgcolor">确定</li>
        </ul>
    </div>
    <div class="recordsInfo" style="top: 285px;">
        <li onclick="javascript:window.location.href = &#39;returnGoods_apply.html&#39;">退款退货</li>
        <li onclick="javascript:window.location.href = &#39;returnMoney_tuikuan.html&#39;">仅退款</li>
    </div>
    <div class="success">已延长三天</div>
    <div class="backtop">
        <a href="javascript:scroll(0,0)" hidefocus="true"></a>
    </div>
    
    

</body>
</html>
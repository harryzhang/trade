<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=0">
        <meta name="apple-touch-fullscreen" content="yes">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="apple-mobile-web-app-status-bar-style" content="black">
        <meta name="format-detection" content="telephone=no">


        <title>Recharge</title>

		<link href="<c:url value ='/res-kuangji/css/global.css'/>" rel="stylesheet" type="text/css">
        <link href="<c:url value ='/res-kuangji/css/shoppingCart.css'/>" rel="stylesheet" type="text/css">
        <script type="text/javascript" src="<c:url value ='/res-kuangji/js/jquery-2.1.1.min.js'/>"></script>
        <script type="text/javascript" src="<c:url value ='/res-kuangji/js/top.js'/>"></script>
		<script type="text/javascript" src="<c:url value ='/res-kuangji/js/shoppingCart.js'/>"></script>
		
		
		
<script>
   
    
    function chongzhi() {
	    var amt = $("#amt").val();
	    if(amt == ""){
		    alert("Recharge amount is not null");
		    return false;
		}
		if(parseInt(amt)<0){
			alert("Recharge must be langer 0");
		    return false;
		}
		document.forms[0].submit();
    }

    
</script>

    </head>
    <body>
        
 <div class="top">
    	<b
			onclick="javascript:window.location.href=&#39;<c:url value='/redPack/personalCenter.html'/>&#39;"></b>
		<dd>Recharge</dd>
   		 <span></span>

</div>
<div class="main">    
    <form  name="amtForm" action="<c:url value='/order/saveChongZhi.html'/>" method="post">
	    <div style="margin-top: 20px">
	        <div class="goods">
	            	Aomunt：<input name="amt" id="amt" type="text" value=""  >RMB
	        </div>
	    </div>
    </form>
</div>

<div class="total">
    <input class="jsbtn" type="button" value="Submit" onclick="chongzhi()">
</div>




<div class="footer">
	<%--
    <dl onclick="javascript:window.location.href =&#39;<c:url value='/firstpage/toFirstpage.html'/>&#39;">
         <dt></dt>
        <dd>首页</dd>
    </dl>
     --%>
     <dl  onclick="javascript:window.location.href =&#39;<c:url value='/redPack/personalCenter.html'/>&#39;">
         <dt></dt>
        <dd>Home</dd>
    </dl>
    <dl onclick="javascript:window.location.href =&#39;<c:url value='/redPack/personalCenter.html'/>&#39;">
         <dt></dt>
        <dd>Personal Center</dd>
    </dl>
  
    
</div>

<div class="mengban" style="height: 622px;"></div>    


</body></html>
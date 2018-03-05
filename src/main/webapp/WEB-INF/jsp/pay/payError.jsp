<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no" />
    <meta name="format-detection" content="telephone=no">
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="apple-mobile-web-app-status-bar-style" content="black" />
    <meta name="keywords" content="" />
    <meta name="description" content="" />
    <title>登录</title>
	<jsp:include page="../common/common.jsp"></jsp:include>
	<script type="text/javascript" src='<c:url value="/res/js/libs/jquery.min.js?v="/>${jsversion}'></script>
	<script type="text/javascript" src='<c:url value="/res/js/plugins/safe/jQuery.md5.js?v="/>${jsversion}'></script>
</head>

<body>
   
    <div class="top-nav">
        <a class="link"  href="javascript:;" onclick="history.back();">&lt;返回</a>
        <h2>扫码支付</h2>
    </div>
	<form   method="post">
	
	    <div class="exit-wrap" >
	    
	    	<div class="item" style="color:black">
	    		<span>商品名称:充值</span>
	    		<span style="margin-left: 30px;">金额:${payAmount}元</span>
	    	</div>
	    	<div class="item" style="color:black">
	    		<span>订单编号:${outTradeNo}</span>
	    		<input type="hidden" id="out_trade_no" value="${outTradeNo}"/>
	    	</div>
	    	
            <div class="item">
	    		<span style="color:red;text-align: center;">出错了：${errorMsg }</span>
	    	</div>
	    </div>
	</form>
	
	
</body>
</html>
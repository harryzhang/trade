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


<title>分享好友</title>

<link href="<c:url value ='/res-kuangji/css/global.css'/>"
	rel="stylesheet" type="text/css" />
<link href="<c:url value ='/res-kuangji/css/share2.css'/>"
	rel="stylesheet" type="text/css" />

<script type="text/javascript"
	src="<c:url value ='/res-kuangji/js/jquery-bar.min.js'/>"></script>
<script type="text/javascript"
	src="<c:url value ='/res-kuangji/js/top.js'/>"></script>
<script type="text/javascript"
	src="<c:url value ='/res-kuangji/js/jquery.qrcode.min.js'/>"></script>
<script type="text/javascript"
	src="<c:url value ='/res-kuangji/js/share.js'/>"></script>

<script>

    $(function() {
	
    $(".ewm").html("");
    var text = "${myBarcode}";
   // alert(text);
    //alert(text);
    $(".ewm").qrcode({
   // render: "table", //table方式,canvas默认
            text: text //任意内容
 	 });
	// $('.ewm').attr("src","http://127.0.0.1:8080/barcode/mybarcode.html?mobile=13480667237&netWork=A");
    });

</script>
              </head>
    <body>

        <div class="top">
    	<b
			onclick="javascript:window.location.href=&#39;<c:url value='/redPack/personalCenter.html'/>&#39;"></b>
		<dd>分享好友</dd>
    <span></span>

</div>
<div class="main">
	<div class="ewmBox">
        <img class="bg" src="<c:url value='/res-kuangji/images/shbg2.png'/>">
        <!--<img class="ewm" src="http://yzc.ll0534.net/assets/mobile/img/erweima.png" />-->
        <dd class="ewm"><canvas width="256" height="256"> </canvas></dd>
<%--             <img src='<c:url value="/barcode/mybarcode.html"/>?mobile=${refMobile}&netWork=${netWork}' alt=""> --%>
		
        <dl>
            <dt><img src="<c:url value='/res-kuangji/images/sh2.png'/>"></dt>
            <dd>分享</dd>
        </dl>
    </div>
    <div class="box">
    	<label>我的邀请好友（${refUser}）</label>
        <ul>
            <li onclick="javascript:window.location.href=&#39;<c:url value='/member/refUser.html'/>&#39;">
            	<font>会员（${refUser}）</font>
                <span>查看明细</span>
            </li>
       
        </ul>
    </div>
</div>
<div class="mengban" style="height: 667px;">
	<img src="/res-kuangji/images/zhizhen.png">
</div>
<div class="mask" style="height: 667px;"></div>
<div class="tanceng" style="top: 296px;">
    <dl>
    	<dt><img src="/res-kuangji/images/weixinbg_02.png"></dt>
        <dd>分享给朋友</dd>
    </dl>
    <dl>
    	<dt><img src="/res-kuangji/images/friendsbg_02.png"></dt>
        <dd>分享到朋友圈</dd>
    </dl>
</div>
<!--<div class="invite">
    <dl>
        <dt class="yaoqing">邀请好友扫一扫</dt>
        <dd id="qrcode"></dd>      
    </dl>
    <div class="dianji">        
        <font class="size_left">点击右上角“</font>
        <dd><img src="http://yzc.ll0534.net/assets/mobile/img/weixinanniu.png" /></dd>
        <font class="size_right">”按钮，分享给好友！</font>        
    </div>
</div>
<div class="yaoqing_cj">
    <div class="biaoti">我的邀请成就（0）</div>
    <ul class="shanghu">
                <li class="mingzi">体验商户（0）</li>
        <li class="mingxi" onclick="javascript:window.location.href ='http://yzc.ll0534.net/mobile/member/showShareFriend?id=1&amp;name=体验商户'">查看明细</li>
                <li class="mingzi">门店商户（0）</li>
        <li class="mingxi" onclick="javascript:window.location.href ='http://yzc.ll0534.net/mobile/member/showShareFriend?id=2&amp;name=门店商户'">查看明细</li>
                <li class="mingzi">品牌商户（0）</li>
        <li class="mingxi" onclick="javascript:window.location.href ='http://yzc.ll0534.net/mobile/member/showShareFriend?id=3&amp;name=品牌商户'">查看明细</li>
        
        <li class="mingzi">免费会员（0）</li>
        <li class="mingxi" onclick="javascript:window.location.href ='http://yzc.ll0534.net/mobile/member/showShareFriend'">查看明细</li>
    </ul></div>-->


    

</body></html>
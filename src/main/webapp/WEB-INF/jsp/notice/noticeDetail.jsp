<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en" class="heightFull"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=0">
        <meta name="apple-touch-fullscreen" content="yes">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="apple-mobile-web-app-status-bar-style" content="black">
        <meta name="format-detection" content="telephone=no">


        <title>公告</title>

		<link href="<c:url value ='/res-kuangji/css/global.css'/>" rel="stylesheet" type="text/css">
        <link href="<c:url value ='/res-kuangji/css/shoppingCart.css'/>" rel="stylesheet" type="text/css">
        <script type="text/javascript" src="<c:url value ='/res-kuangji/js/jquery-2.1.1.min.js'/>"></script>
        <script type="text/javascript" src="<c:url value ='/res-kuangji/js/top.js'/>"></script>
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
          .top{
            background-color: #20222e;
            border: 0;
          }
          .container{
            flex: 1;
            overflow-y: auto;
            color:#fff;
          }
          .container div{
            border-bottom: 0 !important;
          }
          .container p{
            width: 90%;
            margin: 10px   auto;
            text-indent: 36px;
            color: #fff;
          }
        </style>
   </head>
<body  class="heightFull displayFlex">
 <div class="top">
    	<b
			onclick="javascript:window.location.href=&#39;<c:url value='/redPack/personalCenter.html'/>&#39;"></b>
		<dd>公告</dd>
   		 <span></span>

</div>
<div class="main container"> 
   <div style="margin:0;width:100%">
       <div style="text-align: center;color:#000; margin: auto;  padding-top: 20px;  padding-bottom: 10px; border-bottom: thin dashed black; font-size: 18px;"><span style="margin-right:20px">
       		${noticeContent}
       	</div>
   </div>
</div>
</body>
</html>
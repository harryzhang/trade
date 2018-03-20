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
          .ul-container{
            flex: 1;
            overflow-y: auto;
            color:#fff;
          }
          .ul-container div div{
            border-bottom: thin dashed #999 !important;
          }
          .ul-container div:last-child{
            border-bottom: 0 !important;
          }
          .ul-container div a{
            color:#fff;
          }
        </style>
   </head>
<body class="heightFull displayFlex">
 <div class="top">
    	<b
			onclick="javascript:window.location.href=&#39;<c:url value='/redPack/personalCenter.html'/>&#39;"></b>
		<dd>公告</dd>
   		 <span></span>

</div>
<div class="main ul-container"> 
   		<div style="margin:0;width:100%">
      	 <c:forEach items="${noticeList}" var="notice">
       <div style="text-align: center;color:#000;    margin: auto;  padding-top: 20px;  padding-bottom: 10px; border-bottom: thin dashed black; font-size: 18px;"><span style="margin-right:20px">
       		<a href="#" onclick="javascript:window.location.href ='<c:url value='/notice/detail.html'/>?noticeId=${notice.id}'" >${notice.notice_title}</a>
       	</div>
       	</c:forEach>
       	<div style="text-align: center;color:red;margin-top: 20px">
       	<a href="#" onclick="javascript:window.location.href ='<c:url value='/redPack/personalCenter.html'/>'" ><font color="red">进入主页</font></a>
       	</div>
 		</div>
</div>
</body>
</html>
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


        <title>错误提示</title>

        <link href="<c:url value ='/res-kuangji/css/global.css'/>" rel="stylesheet" type="text/css">
   </head>
<body style="margin:0;width:100%">

 <div style="margin:0;width:100%">
     <img style="margin:0;width:100%" src="<c:url  value='/res-kuangji/images/error.jpg' />">
     <div style="text-align: center;font-size:20px">获取用户失败，请登录重试</div>
 </div>
</body>
</html>
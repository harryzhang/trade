<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>查看团队联系方式</title>
	<meta name="viewport" content="width=device-width,inital-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=nos"/>
	<link rel="stylesheet" href='<c:url value="/res/css/mainsm.css?v="/>${cssversion}'/>
</head>
<body>
	<div class="wrap">
		<header class="header">
			<a class="header-left" href='<c:url value="/redPack/personalCenter.html"/>'><img src='<c:url value="/res/images/icon-back.png"/>' alt=""></a>
			<h1 class="header-title">查看团队联系方式</h1>
		</header>
		<div class="content nopad">
			<div class="table">
            <ul class="hd flex">
               <li>序号</li>
               <li>姓名</li>
               <li>手机</li>
               <li>等级</li>
            </ul>
            <div class="table-bd">
			<c:forEach items="${childList}" var="row" varStatus="status">
               <ul class="bd flex">
                  <li>${status.index + 1}</li>
				  <li>${row.name}</li>
				  <li>${row.userName}</li>
<%-- 				  <li>${row.refCount}</li> --%>
				  <li>${row.grade}</li>
               </ul>
             </c:forEach>
            </div>               
         </div>
		</div>		
	</div>
</body>
</html>
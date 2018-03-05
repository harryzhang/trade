<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="../common/head.jsp" %>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>重置密码成功</title>
	</head>
	
<body class="bg-3">
	<header class="top-bar bg-f">
		<a href="javascript:history.go(-1);">
		<span class="icon-back2">
			</span>
		</a>
		<p>重置密码成功</p>
	</header>
	
	<section class="succ-area">
	<div class="succ-logo">
	</div>
	
	</section>
	<script>
	$(function(){
		$("body").css('height', window.innerHeight);
		var url ="<c:url value='/login/main.html'/>";
		setTimeout("window.location.href='"+url+"'", 1000)
	}); 
	</script>
</body>
</html>
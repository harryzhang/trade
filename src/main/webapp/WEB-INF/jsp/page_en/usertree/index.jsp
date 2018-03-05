<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>查看团队结构树</title>
<link rel="stylesheet"
	href='<c:url value="/res/css/mainsm.css?v="/>${cssversion}' />
<link
	href='<c:url value="/res/js/plugins/modal/modal.css?"/>${jsversion}'
	type="text/css" rel="stylesheet">
<script type="text/javascript"
	src='<c:url value="/res/js/libs/zepto.min.js?v="/>${jsversion}'></script>
<script type="text/javascript"
	src='<c:url value="/res/js/global.js?v="/>${jsversion}'></script>
<script type="text/javascript"
	src='<c:url value="/res/js/plugins/modal/modal.js?v="/>${jsversion}'></script>

<script type="text/javascript"
	src='<c:url value="/res/js/plugins/jit/jit.js?v="/>${jsversion}'></script>
<script type="text/javascript"
	src='<c:url value="/res/js/userTree.js?v="/>${jsversion}'></script>
<script type="text/javascript"
	src='<c:url value="/res/js/organization.js?v="/>${jsversion}'></script>
	
</head>

<body onload="init();">
	<div id="container">
		<div id="center-container">
			<div id="infovis"></div>
		</div>
	</div>
</body>

</html>
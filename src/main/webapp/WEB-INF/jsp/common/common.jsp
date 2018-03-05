<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;


request.setAttribute("basePath",basePath);


%>

<script type="text/javascript">
	var globalConfig = {basePath:'${basePath}',globalSaltVal:'${md5SaltVal}'};
</script>
<script type="text/javascript" src='<c:url value="/res/js/libs/zepto.min.js?v="/>${jsversion}' ></script>
<script type="text/javascript" src='<c:url value="/res/js/global.js?v="/>${jsversion}' ></script>
<script type="text/javascript" src='<c:url value="/res/js/plugins/modal/modal.js?v="/>${jsversion}'></script>
<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" href='<c:url value="/res/css/base.css?v="/>${cssversion}' />
<link rel="stylesheet" href='<c:url value="/res/js/plugins/modal/modal.css?v="/>${cssversion}'/>
<link rel="stylesheet" href='<c:url value="/res/css/exit.css?v="/>${cssversion}' />
<link rel="stylesheet" href='<c:url value="/res/css/main.css?v="/>${cssversion}'/>

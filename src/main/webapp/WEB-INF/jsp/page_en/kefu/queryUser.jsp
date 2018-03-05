<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>客服查询会员</title>
	<meta name="viewport" content="width=device-width,inital-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=nos"/>
	<link rel="stylesheet" href='<c:url value="/res/css/mainsm.css?v="/>${cssversion}'/>
	<link href='<c:url value="/res/js/plugins/modal/modal.css?"/>${jsversion}' type="text/css" rel="stylesheet">
	
</head>
<body>
	<div class="wrap">
		<header class="header">
			<a class="header-left" href="<c:url value='/redPack/personalCenter.html'/>"><img src="<c:url value="/res/images/icon-back.png"/>" alt=""></a>
			<div class="search flex">
				<a href="javascript:void(0);"><img onclick="search()"  src='<c:url value="/res/images/search.png"/>' alt=""></a>
				<div class="search-input"><input type="text" id="userName" name ="userName" placeholder="请输入手机号码"></div>
				<div class="left-bor"></div>
				<div class="bottom-bor"></div>
			</div>
		</header>
		<div class="content record forbid-num">
		<c:if test="${!empty userDo}">
		<div class="item">
				<div class="person-info">
					<p>会员姓名：<span>${userDo.name}</span></p>
					<p>会员姓名手机：<span>${userDo.userName }</span></p>
					<p>会员状态：<span>
								<c:if test="${userDo.status==1}">正式会员</c:if>
								<c:if test="${userDo.status==2}">封号会员</c:if>
				   				<c:if test="${userDo.status == 0}">临时式会员</c:if>
                              </span>
                    </p>
					<p>上级推荐人：<span>${userDo.referrerDo.name}</span></p>
					<p>推荐会员数量：<span>${userDo.refCount }</span></p>
				</div>
	    </div>
		</c:if>
		
					
		</div>
	</div>
</body>
</html>
<script type="text/javascript" src='<c:url value="/res/js/libs/zepto.min.js?v="/>${jsversion}' ></script>
<script type="text/javascript" src='<c:url value="/res/js/global.js?v="/>${jsversion}' ></script>
<script type="text/javascript" src='<c:url value="/res/js/plugins/modal/modal.js?v="/>${jsversion}'></script>
<script type="text/javascript">
	
	function search(){
		var userName = $("#userName").val();
		var url ='<c:url value="/kefu/doQueryUser" />?userName=' + userName ;
		window.location.href=url; 
	}
    
</script>

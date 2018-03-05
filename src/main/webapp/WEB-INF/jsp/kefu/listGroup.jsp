<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>团队管理</title>
	<meta name="viewport" content="width=device-width,inital-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=nos"/>
	<link rel="stylesheet" href='<c:url value="/res/css/mainsm.css?v="/>${cssversion}'/>
</head>
<body>
	<div class="wrap">
		<header class="header">
			<a class="header-left" href='<c:url value="/redPack/personalCenter.html"/>'><img src='<c:url value="/res/images/icon-back.png"/>' alt=""></a>
			
			
			<div class="search flex">
				<a href="#"><img onclick="search()" src="<c:url value='/res/images/search.png'/>" alt=""></a>
				<div class="search-input"><input id="userName" name ="userName" type="text" placeholder="团队实际总人数"></div>
				<div class="left-bor"></div>
				<div class="bottom-bor"></div>
			</div>
			
		</header>
		
		<div class="content nopad">
			<div class="table">
            <ul class="hd flex">
               <li>姓名</li>
               <li>手机</li>
               <li>状态</li>
               <li>总人数</li>
               <li>操作</li>
            </ul>
            <div class="table-bd">
			<c:forEach items="${groupList}" var="row" varStatus="status">
               <ul class="bd flex">
				  <li>${row.name}</li>
				  <li>${row.username}</li>
				  <li> 
				       <c:if test="${row.status == 0}">
				            完成
				       </c:if>
				       <c:if test="${row.status == 2}">
				            建设中
				       </c:if>
				  </li>
 				  <li>${row.refcnt}人</li>
				  <li><a  href='<c:url value="/userTree/tree.html?treeDataUserId="/>${row.id}&netWork=${row.networkgroup}' class="item">
            			<p class="text">查看团队</p>
        			 </a>
        		 </li>
               </ul>
             </c:forEach>
            </div>               
         </div>
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
			var url ='<c:url value="/kefu/doListGroup?userName="/>' + userName ;
			window.location.href=url; 
		}
	</script>
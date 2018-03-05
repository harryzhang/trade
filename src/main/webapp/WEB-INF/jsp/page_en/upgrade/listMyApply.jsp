
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="../include/top.jsp"%>
<%@ include file="../common/head.jsp" %>
	<title>申请记录</title>
</head>
	<style>
.err{
  width: 100%;
  display: block;
  color: #f00;
  position: absolute;
  left: 0;
  padding: 0 80px;
  margin-top: -18px;
}
</style>
<body class="bg-2">
	<header class="top-bar">
		<a href="javascript:history.go(-1);">
			<span class="icon-back">
			</span>
		</a>
		<p>申请记录</p>
	</header>
    <form action="tookBack" method="post" >
    <input type="hidden" id="id" name="id" />
	<section class="p1">
		<c:if test="${empty upgradeList}">
	    	没记录
	    </c:if>
		<c:forEach items="${upgradeList}" var="upgradeDo">
		<article class="loan_inf">
			您于<fmt:formatDate value="${upgradeDo.upgradeDate}" pattern="yyyy-MM-dd" /> 提出了升级申请
			    
			     <c:if test="${ upgradeDo.status eq '0' }">
			    	操作: <a class="btn" upid="${upgradeDo.id}" optType="tookBack">撤回</a>
			    </c:if>
		</article>
		</c:forEach>
	</section>
	
	</form>
	<%@ include file="../include/foot.jsp"%>
	<script>
	$(function(){
		$(".btn").bind('click', function(){
			var that = this;
			$("#id").val($(that).attr("upid"));
			tookBack();
		})
	});

	function tookBack(){
		var id = $("#id").val();
		var options = {type:"POST",url:"/upgrade/tookBack.html",data:{id:id}};
		ajaxRequest(options,function(data){
		    if(data.result==1){
		    	popWindow("操作成功");
				window.location.href="/upgrade/listApply.html";
			}else{
				popWindow("操作失败");
			}
		});
	}
	</script>
</body>
</html>
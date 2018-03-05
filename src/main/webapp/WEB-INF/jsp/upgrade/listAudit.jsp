<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="../include/top.jsp"%>
<%@ include file="../common/head.jsp" %>
	<title>审批记录</title>
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
		<p>审批记录</p>
	</header>
    <form id="upgradeForm" action="audit" method="post" >
    <input type="hidden" id="id" name="id" />
    <input type="hidden" id="optType" name="optType" />
	<section class="p1">
	    <c:if test="${empty upgradeList}">
	    	没记录
	    </c:if>
		<c:forEach items="${upgradeList}" var="upgradeDo">
		<article class="loan_inf">
			申请人编号： ${upgradeDo.applyUser.userId}   申请人姓名：${upgradeDo.applyUser.realName} 手机号：${upgradeDo.applyUser.mobile} 金额 ：${upgradeDo.upgradeAmount}   等级 ： ${upgradeDo.afterUpgrade}级  
			    
			    ${upgradeDo.statusName}
			    
			    操作: <a class="btn" upid="${upgradeDo.id}" optType="audit">同意</a> <a class="btn" upid="${upgradeDo.id}" optType="refused">拒绝</a>
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
			$("#optType").val($(that).attr("optType"));
			audit();
		})
	});

	function audit(){
		var id = $("#id").val();
		var optType = $("#optType").val();
		var options = {type:"POST",url:"/upgrade/audit.html",data:{id:id,optType:optType}};
		ajaxRequest(options,function(data){
		    if(data.result==1){
		    	popWindow("操作成功");
				window.location.href="/upgrade/listAudit.html";
			}else{
				popWindow("操作失败");
			}
		});
	}
	</script>
</body>
</html>
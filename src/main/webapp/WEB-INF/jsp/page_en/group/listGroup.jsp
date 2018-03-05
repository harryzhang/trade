<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="../include/top.jsp"%>
<%@ include file="../common/head.jsp" %>
	<title>组</title>
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
.btn{    
    border: 1px solid #ccc;
    border-radius: 8px;
    background: #ccc;
    padding-left: 10px;
    padding-right: 10px;
   
    float: right;
    text-align: right;}
</style>
<body style="background-color: white;">
	<header class="top-bar">
		<a href="javascript:history.go(-1);">
			<span class="icon-back"></span>
			<span>组列表</span>
		</a>
		
	</header>
    <form id="upgradeForm" action="audit" method="post" >
    <input type="hidden" id="groupName" name="groupName" />
	
    <c:if test="${empty groupList}">
    	<div style="border: 1px solid #ccc;padding: 10px;">	    
    	    <span    style="color: #894c8d;">没记录</span>
    	</div>
    </c:if>
	<c:forEach items="${groupList}" var="groupDo">
		<div style="border: 1px solid #ccc;padding: 20px;">
			<span style="color: #894c8d;">组名：</span> ${groupDo.groupName} 
			<span  style="padding: 40px;color: #894c8d;">状态：<span id="${groupDo.groupName}" style="padding: 10px;" >未分盘</span></span>
			<span class="btn"><a class="btn" groupName="${groupDo.groupName}" style="color: #894c8d;">分盘</a></span>
		</div>
	</c:forEach>
	
	
	</form>
	<%@ include file="../include/foot.jsp"%>
	<script>
	$(function(){
		$(".btn").bind('click', function(){
			var that = this;
			$("#groupName").val($(that).attr("groupName"));
			audit();
		})
	});

	function closeTip(){
		$("#ptipws").remove();
	}
	
	function popTipWindow (msg) {
	    var pwCtn = '<div class="pws" style="background: white;height:150px;border: 1px solid #ccc;" id="ptipws"><img src="<c:url value="/res/img/alertClose.png"/>" style="float:right;valign:top;margin-top:-8px;margin-right:-8px;width:32px" onclick="closeTip();"/><div class="pwsTop">' + msg + '</div></div>'
	    $("body").append(pwCtn);
	    
	};
	
	function audit(){
		var groupName = $("#groupName").val();
		var options = {type:"POST",url:'<c:url value="/groupManager/audit.html"/>',data:{groupName:groupName}};
		ajaxRequest(options,function(data){
		    if(data.result==0){
		    	popTipWindow(data.resultMsg);
		    	$("#"+groupName).text("已分盘");
				//window.location.href='<c:url value="/group/listGroup.html"/>';
			}else{
				popWindow("操作失败");
			}
		});
	}
	</script>
</body>
</html>
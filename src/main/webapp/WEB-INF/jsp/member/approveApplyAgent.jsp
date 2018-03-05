<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>审批代理</title>
	<meta name="viewport" content="width=device-width,inital-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=nos"/>
	<link rel="stylesheet" href='<c:url value="/res/css/mainsm.css?v="/>${cssversion}'/>
	<link href='<c:url value="/res/js/plugins/modal/modal.css?"/>${jsversion}' type="text/css" rel="stylesheet">
	<script type="text/javascript" src='<c:url value="/res/js/libs/zepto.min.js?v="/>${jsversion}' ></script>
	<script type="text/javascript" src='<c:url value="/res/js/global.js?v="/>${jsversion}' ></script>
	<script type="text/javascript" src='<c:url value="/res/js/plugins/modal/modal.js?v="/>${jsversion}'></script>
	
</head>
<body>
	<div class="wrap">
		<header class="header">
			<a class="header-left" href="<c:url value='/redPack/personalCenter.html'/>">
				<img src="<c:url value="/res/images/icon-back.png"/>" alt="">
			</a>
			<span style="color:white;font-size:1.7rem;" >代理申请列表</span>
		</header>
		<div class="content record forbid-num">
		<c:if test="${!empty applyUserList}">
			<c:forEach   var="rowMap" items="${applyUserList}" >
			<div class="item flex">
				<div class="txt">
					<p>姓名：<span>${rowMap.realName}</span></p>
					<p>代理类型：<span>${rowMap.agentType}</span></p>
					<p>代理省：<span>${rowMap.province}</span></p>
					<p>代理市：<span>${rowMap.city}</span></p>
					<p>状态：<span>${rowMap.status}</span></p>
					<p>申请日期：<span>${rowMap.createTime}</span></p>
				</div>
				<div class="btn" ><a href="#" class="subbtn" id="subbtn${rowMap.id}" cdataid="${rowMap.id }" >同意</a></div>
			</div>
			</c:forEach>
		</c:if>
		</div>
	</div>
</body>
<script type="text/javascript">
		
	$(function(){
	    //code 点击事件
	    $(".subbtn").bind("click",function(){
	        var id = $(this).attr("cdataid");	        
	        HHN.popupConfirm("你确定要审批通过?", 
	                 function(){submitConfirm(id,'F'); return true;}, 
	                 function(){submitConfirm(id,'T'); 
	                            return true;},
	                            null,null,'拒绝','同意');
	    });
	});
	//提交信息
    function submitConfirm(recId,status){
    	var param ={"id":recId,"status":status};
    	$.post('<c:url value="/account/saveApproveApplyAgent.html"/>', param, function(data) {
    		
    		if(data.resultCode==0){
				HHN.popup("审批成功");
				window.location.href='<c:url value="/account/approveApplyAgent.html"/>'; 
			}else {
				HHN.popup(data.result);
			}
		},"json");
    }
	function search(){
			var userName = $("#userName").val();
			var url ='<c:url value="/kefu/unvalidUser?userName="/>' + userName ;
			window.location.href=url; 
		}
	</script>
</html>
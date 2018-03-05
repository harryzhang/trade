<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>临时会员</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Cache-Control" content="no-cache" />
<meta name="viewport"
	content="width=device-width,initial-scale=1.0,minimum-scale=1.0, maximum-scale=1.0,user-scalable=no" />
<meta name="format-detection" content="telephone=no" />
<meta name="MobileOptimized" content="320" />
<%@ include file="../common/head.jsp" %>
<link href="${fileServerUrl }/res/css/eLoan.css?v=${jsversion}"
	rel="stylesheet" type="text/css">
</head>
<body style="padding-bottom: 60px">

	<article>
		<section>
			<ul class="center-list">
				<li><span class="hj">临时会员</span><em></em>
					<table class="r_table">
						<thead>
							<tr>
								<th>姓名</th>
								<th>手机</th>
								<th>等级</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${childList}" var="row" varStatus="status">
								<tr>
									<td>${row.userInfoDo.realName}</td>
									<td>${row.userInfoDo.mobile}</td>
									<td>${row.grade}</td>
									<td><a class="btn" data-uid="${row.id}" data-uname="${row.userInfoDo.realName}" >转正式会员</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</li>
			</ul>
		</section>
		</article>
		
		<%@ include file="../include/foot.jsp"%>
</body>
</html>
<script>
	$(function(){
		$(".btn").bind('click', function(){
			console.info("=============================");
			var that = this;
			var userId = $(that).attr("data-uid");
			var userName = $(that).attr("data-uname");			
			console.info("=============================userId"+userId);
			audit(userId,userName);
		});
	});

	function audit(userId,userName){
		var options = {type:"POST",url:"/upgrade/auditTempUser.html",data:{"userId":userId}};
		
		var rst=confirm("确定审批"+userName+"?");
		if (rst !=true){
			return false;
		}
		
		ajaxRequest(options,function(data){
		    if(data.result==1){
		    	popWindow("操作成功");
		    	window.location.href="/upgrade/viewTempUserInfo.html";
			}else{
				popWindow("操作失败");
			}
		});
	}
	</script>
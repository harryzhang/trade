<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>升级</title>
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
			<a class="header-left" href="<c:url value='/redPack/personalCenter.html'/>"><img src="<c:url value="/res/images/icon-back.png"/>" alt=""></a>
			<h1 class="header-title">升级</h1>
		</header>
		<form action="${loginServerUrl }/param/setFxAccount.do"  method="post">
		<div class="content manager nopad">
			<div class="manager-inn">
				<div class="ipt">
					<span>B网推荐人手机号码：</span><input type="text" name="fxPhone" id="fxPhone"    value="${fxPhone}">
				</div>
				<div class="btn"><a href="#" id="regBtn" >升级到B网</a></div>
				<input type="hidden" id="msgId" value="${msg.id }"/>
			</div>
		</div>
		</form>
	</div>
	<script>
	$(function(){
		$("#regBtn").bind("click",function(){
			var fxPhone = $("#fxPhone").val();
			var msgId = $("#msgId").val();

			$(this).attr('disabled',true);
			
			if(msgId==null || msgId.length==0){
				HHN.popup("您还不够条件升级");
				return;
			}
			
			if(fxPhone==null || fxPhone.length==0){
				HHN.popup("请输入B网推荐人手机号码");
				$('#regBtn').removeAttr('disabled');
				return;
			}
            
			
			var param ={"fxPhone":fxPhone,"msgId":msgId};
			$.post('<c:url value="/upgrade/apply.html"/>', param, function(data) {
	    		
				if(data.result== 0){
					HHN.popup('B网升级成功');
				}else{
					HHN.popup(data.result);
					$('#regBtn').removeAttr('disabled');
				}
			},"json");
		});
		});
	</script>	
</body>
</html>

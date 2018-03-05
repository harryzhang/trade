<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>设置会员过期时间</title>
<meta name="viewport"
	content="width=device-width,inital-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=nos" />
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
			<h1 class="header-title">设置超时时间</h1>
		</header>
		<form action="${loginServerUrl }/param/setPayMoney.do" method="post">
			<div class="content manager nopad">
				<div class="manager-inn">
					<div class="ipt">
						<span>超时时间(小时)：</span><input type="number" name="expirationTime" id="expirationTime" placeholder="请输入时间" value="${expirationTime}">
					</div>
					<div class="btn">
						<a href="#" id="regBtn">保存</a>
					</div>
				</div>
			</div>
		</form>
	</div>
<script>
	$(function() {
		$("#regBtn").bind("click", function() {
			var expirationTime = $("#expirationTime").val();
			if (expirationTime == null || expirationTime.length == 0) {
				HHN.popup("请设置超时时间");
				return;
			}

			$(this).attr('disabled', true);
		/* 	var options = {
				type : "POST",
				url : "<c:url value='/param/setExpirationTime.html'/>",
				data : {
					expirationTime : expirationTime
				}
			};
			ajaxRequest(options, function(data) {
				if (data.result == '设置超时时间成功') {
					popWindow(data.result);
					var url = "${loginServerUrl }/redPack/personalCenter.html";
					setTimeout("window.location.href='" + url + "'", 1000);
				} else {
					popWindow(data.result);
					$('#regBtn').removeAttr('disabled');
				}
			}); */
			
			var param ={expirationTime : expirationTime};
			$.post('<c:url value="/param/setExpirationTime.html"/>', param, function(data) {
	    		
				if(data.result=='设置超时时间成功'){
					HHN.popup(data.result);
					var url ="${loginServerUrl }/redPack/personalCenter.html";
					setTimeout("window.location.href='"+url+"'", 1000); 
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

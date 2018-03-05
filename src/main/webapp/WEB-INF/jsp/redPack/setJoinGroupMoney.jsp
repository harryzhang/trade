<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>设置会费</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="Cache-Control" content="no-cache" />
		<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0, maximum-scale=1.0,user-scalable=no" />
		<meta name="format-detection" content="telephone=no" />
		<meta name="MobileOptimized" content="320" />
		<%@ include file="../include/top.jsp"%>
		<link href="${fileServerUrl }/res/css/main.min.css?v=${jsversion}" type="text/css" rel="stylesheet">
		<style>
			.personinf_radio div{
				padding: 0 10px;
			}
			.personinf_ {
				background: #fff;
			}
			.personinf_ p{
				width: 50%;
				line-height: 24px;
			}
			.personinf_img{
			-webkit-display: flex;
			display: flex;
			padding:10px 1.1vw;
			background:#fff;
			width:100%;
			-webkit-box-sizing: border-box;
			box-sizing: border-box;
			-webkit-flex-wrap: wrap;
			flex-wrap: wrap;
			/*justify-content:center;*/
			}
			.psrimgli{
			width:32.6vw;
			height:32.6vw;
			padding: 1.1vw;
			overflow:hidden;
			float: left;
			}
		</style>
	</head>
<body class="bg-2"  style="padding-bottom:60px;" >
	<header class="top-bar">
		<a href="<c:url value='/redPack/personalCenter.html'/>">
			<span class="icon-back"></span>
		</a>
		<span>设置会费</span>
	</header>
		<section class="sign-area">
			<form action="${loginServerUrl }/account/register.do" id="regForm" method="post">
				<div class="sign-style br-3">
					<span class="sign-lable pr75" style="color: #894c8d;">入会金额</span>
					<input type="number" name="groupMoney" id="groupMoney" placeholder="请输入入会金额">
				</div>
				<div class="sign-sub">
					<input style="background: #894c8d;"   type="button" id="regBtn" value="保存">
				</div>
			</form>
		</section>
	<%@ include file="../include/foot.jsp"%>
	<script src="${fileServerUrl }/res/js/main.min.js?v=${jsversion}"></script>
	<script>
		$(function(){
			$("nav ul li").eq(2).addClass("current");
			$("body").css('height', window.innerHeight);
			$("#regBtn").bind("click",function(){
				var groupMoney = $("#groupMoney").val();
				if(groupMoney==null || groupMoney.length==0){
					popWindow("请输入入会金额");
					return;
				}
                
				$(this).attr('disabled',true);
				var options = {type:"POST",url:"<c:url value='/upgrade/saveJoinGroupMoney.html'/>",data:{groupMoney:groupMoney}};
				ajaxRequest(options,function(data){
					if(data.result=='设置会费成功'){
						popWindow(data.result);
						var url ="${loginServerUrl }/redPack/personalCenter.html";
						setTimeout("window.location.href='"+url+"'", 1000); 
					}else{
						popWindow(data.result);
						$('#regBtn').removeAttr('disabled');
					}
				});
			});
		});
	</script>
</body>
</html>
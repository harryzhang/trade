<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>创建团队</title>
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
		<span>创建团队</span>
	</header>
	<section class="sign-area">
		<form action="${loginServerUrl }/upgrade/saveNewGroup.html" id="regForm" method="post">
			
			<div class="sign-style br-2">
				<span class="sign-lable" style="color: #894c8d;">团队领导手机号码</span>
				<input type="text" id="mobilePhone" name="mobilePhone" placeholder="请输入管理员手机号码">
			</div>
			<div class="sign-style br-2">
					<span class="sign-lable" style="color: #894c8d;">初始密码</span>
					<input type="text" id="pwd" name="pwd" placeholder="管理员初始密码">
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
			$("nav ul li").eq(1).addClass("current");
			$("body").css('height', window.innerHeight);
			$("#regBtn").bind("click",function(){
					
				var mobilePhone = $('#mobilePhone').val();
				if (mobilePhone == "" || mobilePhone.length==0) {
					popWindow("请输入手机号");
					return;
				}
                var re =new RegExp("/^1[3|5|7|8|][0-9]{9}$/");
                if (!/^0{0,1}(13[0-9]|15[0-9]|18[0-9]|14[0-9]|17[0-9])[0-9]{8}$/.test(mobilePhone)){
                     popWindow("输入手机号的格式有误");
                     return;
                }
                
                var pwd = $('#pwd').val();
				if (pwd == "" || pwd.length==0) {
					popWindow("请输入初始密码");
					return;
				}
                
				$(this).attr('disabled',true);
				var options = {type:"POST",url:"${loginServerUrl }/upgrade/saveNewGroup.html",data:{mobilePhone:mobilePhone,pwd:pwd}};
				ajaxRequest(options,function(data){
					if(data.result=='团队创建成功'){
						popWindow(data.result);
						var url ="${loginServerUrl }/redPack/personalCenter.html";
						setTimeout("window.location.href='"+url+"'", 1000); 
					}else{
						popWindow(data.result);
						$('#regBtn').removeAttr('disabled');
					}
				});
			});
		
		})
	</script>
</body>
</html>
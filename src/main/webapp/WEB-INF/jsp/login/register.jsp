<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="heightFull">
<%@ include file="../common/head.jsp" %>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>${channel_name}-注册</title>
		<style>
	        .background{
	            flex: 1;
	        }
	        .background .exit-wrap{
	            margin-top: 15px;
	        }
	        #code{
	            margin-left: 1rem;
	        }
	        #codeNum{
	            margin-right: 1rem;
	        }
	        #userLocal{
	            color: #fff;
	            border: 0;
	        }
	        #userLocal option{
	            background: #20222e;
	            border-bottom: 1px solid #1c1e2a;
	        }
	    </style>
	</head>
<body class="bg-2 heightFull displayFlex">
<header class="top-bar">
		<a href="<c:url value='/login/index.do'/>">
		<span class="icon-back">
			</span>
		</a>
		<p>注册账号</p>
	</header>
	<div class="background">
		
	
		<section class="sign-area">
			<form action="<c:url value='/account/register.do'/>" id="regForm" method="post">
				<div class="sign-style br-3">
					<span class="sign-lable pr75">真实姓名</span>
					<input type="text" name="userName" id="userName" placeholder="请输入您的真实姓名">
				</div>
				<div class="sign-style br-2">
				<span class="sign-lable">登录密码</span>
					<input type="password" name="password" id="password" placeholder="6-20个字符">
				</div>
				<div class="sign-style br-3">
					<span class="sign-lable">确认密码</span>
					<input type="password" id="confirmPassword" name="confirmPassword" placeholder="6-20个字符">
				</div>
				<div class="sign-style br-2">
					<span class="sign-lable">手机号码</span>
					<input type="text" id="mobilePhone" name="mobilePhone" placeholder="请输入注册手机号码">
				</div>
				<div class="sign-style br-5">
					<span class="sign-lable">推荐人</span>
					<input type="text" id="referenceMobile" name="referenceMobile" placeholder="请输入推荐人手机号码">
				</div>
				<div class="sign-sub">
					<input type="button" id="regBtn" value="立即注册">
				</div>
			</form>
		</section>
	</div>
	<script>
		$(function(){
			sbh();
			//register
			$("#regBtn").bind("click",function(){
				var userName = $("#userName").val();
				if(userName==null || userName.length==0){
					popWindow("请输入真实姓名");
					return;
				}
			 	if (!/^[\u4E00-\u9FA5]{2,5}$/ig.test(userName)) {
				 	popWindow("请输入2-5字的中文");
					return ;
				}  
				
				var password = $('#password').val();
				if (password == "" || password.length==0) {
					popWindow("请设置您的密码");
					return;
				}
				if (password.indexOf(" ")>=0) {
                    popWindow("密码不能包含空格");
					return;
                }
                if (password.length < 6) {
                	popWindow("密码长度为6-20个字符");
					return;
				} 
				//confirmPassword
				var confirmPassword = $('#confirmPassword').val();
				if (confirmPassword == "" || confirmPassword.length==0) {
					popWindow("请再次输入密码确认");
					return;
				}
				if (confirmPassword != password) {
					popWindow("两次输入的密码不一致");
					return;
				} 
				//telephone
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
				var referenceMobile = $('#referenceMobile').val();
				if (referenceMobile == "" || referenceMobile.length==0) {
					popWindow("请输入手机号");
					return;
				}
                if (!/^0{0,1}(13[0-9]|15[0-9]|18[0-9]|14[0-9]|17[0-9])[0-9]{8}$/.test(referenceMobile)){
                     popWindow("输入手机号的格式有误");
                     return;
                }
				$(this).attr('disabled',true);
				var options = {type:"POST",url:"<c:url value='/account/register.html'/>",data:{userName:userName,password:password,mobilePhone:mobilePhone,referenceMobile:referenceMobile}};
				ajaxRequest(options,function(data){
					if(data.result=='注册成功'){
						window.location.href="<c:url value='/redPack/modifyInfo.html'/>";
					}else{
						popWindow(data.result);
						$('#regBtn').removeAttr('disabled');
					}
				});
			});
			//获取手机验证码
		/* 	$('#ida').bind("click",function(){
				if ($(this).attr("disable") == null) {
				   var param = {checkPhone:true};
				   sendPhoneVirifyCode(param);
				}
			}); */
		})
		function sendPhoneVirifyCode(param) {
			var mobilePhone = $("#mobilePhone");
			if(mobilePhone!=null){
				if(mobilePhone.val()==null || mobilePhone.val().length==0){
					popWindow("请输入手机号码");
					return;
				}
				var verifyR = verifyPhone_(mobilePhone.val());
				if(verifyR!=""){
					popWindow(verifyR);
					return;
				}
				param["mobilePhone"]=mobilePhone.val();
			}
			
			$.post("<c:url value='/common/sendPhoneVirifyCode.do'/>", param, function(data) {
				if(data.ret==1){
					popWindow("发送验证码失败");
				}else if(data.ret==2){
					popWindow("手机号码不存在");
				}else if(data.ret==3){
					popWindow("手机号码已存在");
				}else{
					$("#ida").attr("disable", true).text("60s重获");
					telephoneTimer_();
					//popWindow("验证码："+data.ret);
				}
			},"json");
		}
	</script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
	<head>
		<%@ include file="../include/top.jsp"%>
		<title>密码修改</title>
	</head>
<style>
.err {
	width: 100%;
	display: block;
	color: #f00;
	position: absolute;
	left: 0;
	padding: 0 80px;
	margin-top: -18px;
}
</style>
<body>
	<form action="confirmPassword" method="post" >
		<input type="hidden" name="id" value="${userDo.userInfoDo.id}"/>
		<input type="hidden" name="userId" value="${userDo.userInfoDo.userId}"/>
		<section class="p1">
		<article class="loan_inf">
			<h3 class="inf_tip"><i class="t1"></i>密码修改</h3>
			<div class="form-group db_f">
				<label for="" class="lab">原密码：</label>
				<input type="text" id="password" placeholder="请输入原密码"  class="txt bf1 db" name="password"   >
			</div>
			<div class="form-group db_f">
				<label for="" class="lab">新密码：</label>
				<input type="text" id ="newPassword"  placeholder="请输入新密码"   class="txt bf1 db"  name="newPassword" />
			</div>
			<div class="form-group db_f">
				<label for="" class="lab">确认新密码：</label>
				<input type="text" id ="confirmPassword"  placeholder="请确认输入新密码"  class="txt bf1 db"  name="confirmPassword"/>
			</div>
			
		</article>
	<section class="p1" style="padding: 10px 10px 60px;">
			<a href="javascript:void(0);" id="apply" class="apply">确定修改</a>
	</section>
	</form>
	<%@ include file="../include/foot.jsp"%>
	<script>
	$(function(){
		   var validatePassword = function(){
				var temp = $("#password");
				if(temp.val()==0){
					temp.parent().after('<em class="err bs" style="z-index:0;">请输入原密码</em>');
					return false;
				}else{
					return true;
				}
			};
			var validateConfirPassword = function(){
				var temp = $("#confirmPassword");
				var temp1 = $("#newPassword");
				if(temp.val()==0 || temp1.val()==0 ){
					temp.parent().after('<em class="err bs" style="z-index:0;">请输入新密码</em>');
					return false;
				}else if (temp.val() != temp1.val()) {
					temp.parent().after('<em class="err bs" style="z-index:0;">两次密码收入有误</em>');
					return false;
				}else{
					return true;
				}
			};
			
			$("#password").blur(validatePassword);
			
			$("#confirmPassword").blur(validateConfirPassword);
			$(".txt,input,select").focus(function(){
				$(this).parent().next('.err').remove();
			});
			
		$("#apply").bind('touchstart', function(){
			$(".err").remove();
			var a =0;
			if(!validatePassword()) a++;
			if(!validateConfirPassword()) a++;
			//if(!validateMobile()) a++;
			//if(!validateQqNumber()) a++;
			//if(!validateWeixiNumber()) a++;
			//if(!validateEMail()) a++;
			//if(!validateContactAddress()) a++;
			//if(!validateBankName()) a++;
			//if(!validateOpenAccount()) a++;
			//if(!validateBankAccount()) a++;
			//if(!validateProvince()) a++;
			//if(!validateCity()) a++;
			//if(!validateBankSubbranch()) a++;
			//if(!validateZfbNumber()) a++;
			//if(!validateCftNumber()) a++;
			if(a==0){ 
				document.forms[0].submit();
			}
		})
	})
	</script>
</body>
</html>
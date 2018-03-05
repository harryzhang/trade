<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>推荐会员</title>
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
		<span>推荐会员</span>
	</header>
		<section class="sign-area">
			<form action="${loginServerUrl }/account/register.do" id="regForm" method="post">
				<div class="sign-style br-3">
					<span class="sign-lable pr75" style="color: #894c8d;">真实姓名</span>
					<input type="text" name="userName" id="userName" placeholder="请输入您的真实姓名">
				</div>
				<div class="sign-style br-3">
					<span class="sign-lable pr75" style="color: #894c8d;">身份证号码</span>
					<input type="text" name="idCardNo" id="idCardNo" placeholder="请输入您的身份证号码">
				</div>
				<div class="sign-style br-2">
					<span class="sign-lable" style="color: #894c8d;">手机号码</span>
					<input type="text" id="mobilePhone" name="mobilePhone" placeholder="请输入注册手机号码">
				</div>
				<div class="sign-style br-2">
					<span class="sign-lable" style="color: #894c8d;">推荐人手机号</span>
					<input type="text"  id="referenceId" name="referenceId"  value="">
				</div>
				<div class="sign-style br-2">
					<span class="sign-lable" style="color: #894c8d;">初始密码</span>
					<input type="text" id="pwd" name="pwd" placeholder="初始密码">
				</div>
				<div class="sign-sub">
					<input style="background: #894c8d;"   type="button" id="regBtn" value="立即注册">
				</div>
			</form>
		</section>
	<%@ include file="../include/foot.jsp"%>
	<script src="${fileServerUrl }/res/js/main.min.js?v=${jsversion}"></script>
	<script type="text/javascript" src="${fileServerUrl }/res/js/idCard.js?v=${jsversion}"></script>
	<script>
	
		$(function(){
			$("nav ul li").eq(2).addClass("current");
			$("body").css('height', window.innerHeight);
			$("#regBtn").bind("click",function(){
				var userName = $("#userName").val();
				if(userName==null || userName.length==0){
					popWindow("请输入真实姓名");
					return;
				}
			 	if (!/^[\u4E00-\u9FA5]{2,20}$/ig.test(userName)) {
				 	popWindow("请输入2-10字的真实姓名");
					return ;
				}  
				
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

                if(!validateIdCardNo()){
                	popWindow("请输入正确的身份证号");
					return;
                }
                    
				$(this).attr('disabled',true);
				var referenceId = $("#referenceId").val();
				var pwd = $("#pwd").val();
				var pwd = $("#pwd").val();
				var idCardNo = $("#idCardNo").val();
				
				var options = {type:"POST",url:"${loginServerUrl }/account/register.html",data:{pwd:pwd,userName:userName,mobilePhone:mobilePhone,referenceId:referenceId,idCardNo:idCardNo}};
				ajaxRequest(options,function(data){
					if(data.result=='注册成功'){
						popWindow(data.result);
						var url ="${loginServerUrl }/redPack/personalCenter.html";
						setTimeout("window.location.href='"+url+"'", 1000); 
					}else if(data.result=='登录超时'){
						window.location.href="${loginServerUrl }/login/index.html";
					}else{
						popWindow(data.result);
						$('#regBtn').removeAttr('disabled');
					}
				});
			});
			var touchObj = {};
			$(".personinf_img li").bind('touchstart', function(e){
	        }).bind('touchmove', function(e){
	        	
	        }).bind('touchend', function(e){
				touchObj.endDate = new Date();
				if(touchObj.endDate - touchObj.startDate > 200) {
					return false;
				}
	        	 var imgurl = $(this).attr('data-img');
	             var str = 
	             '<section class="dialog" style="overflow: scroll;">'+
	             '    <img src="'+ imgurl +'" alt="">'+
	             '    <span class="close-dialog"></span>'+
	             '</section>';
	             $("body").append(str);
	        })
	        
	        $("body").on('touchstart', '.close-dialog', function(){
	            $(this).parent().remove();
	        })
		});

		var validateIdCardNo = function(){
			var temp = $("#idCardNo");
			if(temp.val()==0){
				//temp.parent().after('<em class="err bs" style="z-index:0;">请输入身份证号</em>');
				return false;
			}else if (!isIdCardNo(temp.val())) {
				//temp.parent().after('<em class="err bs" style="z-index:0;">身份证号格式有误</em>');
				return false;
			}else{
				return true;
			}
		};
		
	</script>
</body>
</html>
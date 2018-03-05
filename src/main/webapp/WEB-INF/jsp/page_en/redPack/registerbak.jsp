<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>会员注册</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="Cache-Control" content="no-cache" />
		<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0, maximum-scale=1.0,user-scalable=no" />
		<meta name="format-detection" content="telephone=no" />
		<meta name="MobileOptimized" content="320" />
		<%@ include file="../include/top.jsp"%>
		<link href='<c:url value="/res/css/main.min.css?"/>v=${jsversion}' type="text/css" rel="stylesheet">
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
		<span>会员注册</span>
	</header>
		<section class="sign-area">			
			<form action='<c:url value="/account/register.do"/>' id="regForm" method="post">
				<%--
				<div class="sign-style br-2">
					<span class="sign-lable" style="color: #894c8d;">组&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名:</span>
					<input type="text" id="groupName" name="groupName" placeholder="组名">
				</div>
				<div class="sign-style br-3">
					<span class="sign-lable pr75" style="color: #894c8d;">编&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号:</span>
					<input type="text" name="userNo" id="userNo" placeholder="编号">
				</div>
				 --%>
				<div class="sign-style br-3">
					<span class="sign-lable pr75" style="color: #894c8d;">真实姓名:</span>
					<input type="text" name="name" id="name" placeholder="真实姓名">
				</div>
				
				<div class="sign-style br-2">
					<span class="sign-lable" style="color: #894c8d;">手机号码:</span>
					<input type="text" id="mobilePhone" name="mobilePhone" placeholder="手机号码">
				</div>
				<div class="sign-style br-2">
					
					<input type="text" id="mobilePhoneCode" name="mobilePhoneCode" placeholder="验证码">
					<span class="sign-lable"  >获取验证码</span>
				</div>
				<div class="sign-style br-2">
					<span class="sign-lable" style="color: #894c8d;">微&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;信:</span>
					<input type="text"  id="weixin" name="weixin" placeholder="微信" value="">
				</div>
				<div class="sign-style br-2">
					<span class="sign-lable" style="color: #894c8d;">&nbsp;&nbsp;&nbsp;&nbsp;支付宝:</span>
					<input type="text"  id="zhifubao" name="zhifubao" placeholder="支付宝" value="">
				</div>				
				<div class="sign-style br-2">
					<span class="sign-lable" style="color: #894c8d;">&nbsp;&nbsp;&nbsp;&nbsp;推荐人:</span>
					<input type="text"  id="referenceId" name="referenceId"  placeholder="推荐人手机号" value="">
				</div>
				<div class="sign-style br-2">
					<span class="sign-lable" style="color: #894c8d;">登陆密码:</span>
					<input type="text" id="pwd" name="pwd" placeholder="登陆密码">
				</div>
				
				<div class="sign-sub">
					<input style="background: #894c8d;"   type="button" id="regBtn" value="立即注册">
				</div>
			</form>
		</section>
	<%@ include file="../include/foot.jsp"%>
	<script src='<c:url value="/res/js/jquery-1.11.1.min.js"/>'></script>
	<script src='<c:url value="/res/js/main.min.js?"/>v=${jsversion}'></script>
	<script>

	function closeTip(){
		$("#ptipws").remove();
	}
	
	function popTipWindow (msg) {
	    var pwCtn = '<div class="pws" style="background: white;height:150px;border: 1px solid #ccc;" id="ptipws"><img src="<c:url value="/res/img/alertClose.png"/>" style="float:right;valign:top;margin-top:-8px;margin-right:-8px;width:32px" onclick="closeTip();"/><div class="pwsTop">' + msg + '</div></div>'
	    $("body").append(pwCtn);
	    
	};
	
		$(function(){
			$("nav ul li").eq(2).addClass("current");
			$("body").css('height', window.innerHeight);
			$("#regBtn").bind("click",function(){
				
				
				
				var name = $("#name").val();
				if(name==null || name.length==0){
					popWindow("请输入真实姓名");
					return;
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

                var weixin = $("#weixin").val();
				if(weixin==null || weixin.length==0){
					popWindow("请输入微信号");
					return;
				}

				var zhifubao = $("#zhifubao").val();
				if(zhifubao==null || zhifubao.length==0){
					popWindow("请输入支付宝");
					return;
				}
				
				//var userNo = $("#userNo").val();
				//if(userNo==null || userNo.length==0){
				//	popWindow("请输入编码");
				//	return;
				//}
				
				var referenceId = $("#referenceId").val();
				if(referenceId==null || referenceId.length==0){
					popWindow("请输入推荐人手机号码");
					return;
				}


				var pwd = $("#pwd").val();
				if(pwd==null || pwd.length==0){
					popWindow("请输入登陆密码");
					return;
				}
				
                    
				$(this).attr('disabled',true);
				
				
				var options = {type:"POST",url:'<c:url value="/account/register.html"/>',data:{zhifubao:zhifubao,pwd:pwd,mobilePhone:mobilePhone,weixin:weixin,referenceId:referenceId,name:name}};
				ajaxRequest(options,function(data){
					if(data.result==0){
				    	//popTipWindow(data.resultMsg);						
						popWindow('注册成功');						
						
						//$('#regBtn').removeAttr('disabled');
						var url ='<c:url value="/redPack/personalCenter.html"/>';
						setTimeout("window.location.href='"+url+"'", 1000); 
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
	</script>
</body>
</html>
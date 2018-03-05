<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no" />
    <meta name="format-detection" content="telephone=no">
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="apple-mobile-web-app-status-bar-style" content="black" />
    <meta name="keywords" content="" />
    <meta name="description" content="" />
    <title>注册</title>
	<jsp:include page="../common/common.jsp"></jsp:include>
	<script type="text/javascript" src="${basePath}/res/js/libs/jquery.min.js?v=${jsversion}"></script>
	<script type="text/javascript" src="${basePath}/res/js/plugins/safe/jQuery.md5.js?v=${jsversion}"></script>
</head>

<body>
	<div class="top-nav">
        <a class="link"  href="javascript:;" onclick="history.back();">&lt;返回</a>
        <h2>更新用户信息</h2>
    </div>   
	    <div class="exit-wrap">
	    	<form id="toLoginForm" action='<c:url value="/account/register.html"/>' method="POST">
	        
	        <div class="item">
	            <i class="icon icon-suggest"></i>
	            <input id="weixin" type="text" placeholder="微信号" />
	        </div>
	        
	        <div class="item">
	            <i class="icon icon-suggest"></i>
	            <input id="zhifubao" type="text" placeholder="支付宝" />
	        </div>
	       
	    </div>
    <div class="form-btns">
        <a href="javascript:;" class="btn" id="submit">确定</a>
    </div>
   
    <script>
   

    
    //获取验证码----3请求
    function sendMobileCheckCode(mobile) {
		var param = {"mobile":mobile,"checkMobile":true,"code":$code.val(),"pageId":"register"};
		
		$.post('<c:url value="/account/sendVirifyCode.html"/>', param, function(data) {
			if(data.success && data.resultCode == '0'){
                refCode(true);
				HHN.popup(data.resultMessage);
			}else{
                refCode(false);
				HHN.popup(data.resultMessage);
				countup();
			}
		},"json");
	}
	
    //提交注册信息----1
    $submit.on('click', function() {
    	var mobile = $mobile.val();
    	var name = $("#name").val();
    	var weixin = $("#weixin").val();
    	var zhifubao = $("#zhifubao").val();
    	
    	if(mobile_check(mobile)){
    		var mobileCode = $('#mobileCode').val();
        	if(mobileCode == null || mobileCode == "" || mobileCode.length == 0){//验证码
        		HHN.popup("请输入验证码", 'danger');
        		return false;
        	}else{
                var code = $code.val();
                var bCode = code_check(code);
        		if(bCode){
                    //验证密码
                    var password = $psw.val();//密码
                    var password2 = $('#psw2').val();//重复密码
                    var bPwd = pwd_check(password);
                    if(bPwd){
                        if(password2 == null || password2 == "" || password2.length == 0){
                            HHN.popup("请输入重复密码", 'danger');
                            return false;
                        }else{
                            if(password != password2){
                                HHN.popup("两次输入的密码不一致", 'danger');
                                return false;
                            }else{
                                var refferee = $('#suggest').val();//推荐人手机号
                                var chennelCode = $('#chennelCode').val();//渠道ID
                                //password = $.md5(password+globalConfig.globalSaltVal);
                                var param = {"mobile":mobile,"mobileCode":mobileCode,"password":password,"refferee":refferee,
                                		     "chennelCode":chennelCode,"code":code ,"pageId":"register"
                                		     ,"name":name,"weixin":weixin,"zhifubao":zhifubao
                                		     }; 
                                if(refferee != null && refferee != '' && refferee.length > 0){
                                    if(mobile_check(refferee)){
                                        submitReg(param);
                                    }
                                }else{
                                    submitReg(param);
                                }
                            }
                        }
                    }
                }
        	}
        	
    	}
    	        
    });
    //提交注册信息
    function submitReg(param){
    	$.post('<c:url value="/account/register.html"/>', param, function(data) {
			if(data.result == '注册成功'){
				HHN.popup(data.resultMessage);
					setTimeout(function(){
                        //refCode(true);
                        window.location.href= '<c:url value="/login/index.html"/>';
					},1500);
			}else{
                refCode(false);
				HHN.popup(data.result);
			}
		},"json");
    }
    
  //提交登录信息
    function submitLogin(param){
    	$.post("${basePath}/loanuser/login.do", param, function(data) {
			if(data.success && data.resultCode == '0'){
                refCode(true);
				HHN.popup(data.resultMessage);
				//window.location.href=data.model;
				window.location.href=data.model.fromUrl;
			}else{
                refCode(false);
				HHN.popup(data.resultMessage);
				setTimeout(function(){
					$('#toLoginForm').submit();
				},1500);
			}
		},"json");
    }
    
    $toLogin.on('click', function() {
    	window.location.href='<c:url value="/login/index.html"/>';
    });
    </script>
</body>

</html>

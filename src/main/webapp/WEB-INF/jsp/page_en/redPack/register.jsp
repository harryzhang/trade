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
    <title> Register</title>
    	<link href="<c:url value ='/res-kuangji/css/security.css'/>"
	rel="stylesheet" type="text/css" />
	<jsp:include page="../common/common.jsp"></jsp:include>
	<script type="text/javascript" src="${basePath}/res/js/libs/jquery.min.js?v=${jsversion}"></script>
	<script type="text/javascript" src="${basePath}/res/js/plugins/safe/jQuery.md5.js?v=${jsversion}"></script>

</head>

<body>
	<div class="top">
		<b onclick="javascript:window.location.href=&#39;<c:url value='/redPack/personalCenter.html'/>&#39;"></b>
		<dd>Register</dd>
  </div> 
	    <div class="exit-wrap">
	    	<form id="toLoginForm" action='<c:url value="/account/register.html"/>' method="POST">
		        <div class="item">
		            <i class="icon"></i>
		            <input type="text" placeholder="Real name" id="name1" name="name1" />
		        </div>
		        <div class="item">
		            <i class="icon"></i>
		            <input type="text" placeholder="Real name" id="name" name="name" />
		        </div>
		        <div class="item">
		            <i class="icon"></i>
		            <input type="tel" placeholder="Cell phone number" id="mobile" name="mobile" />
		        </div>
		        
	        </form>
            <div class="item">                
                <input type="text" id="code" name="code" placeholder="Image verification">            
                <img src='<c:url value="/common/imageCode.html?pageId=register"/>' width="120" height="40" title="Click change image verification" id="codeNum" />                            
            </div>
	        <div class="item">
	            <i class="icon icon-code"></i>
	            <input name="mobileCode" type="text" placeholder="Input image verification" id="mobileCode" />
	            <button class="code-btn" id="getcode">Get free</button>
	        </div>
	       
	        <div class="item">
	            <i class="icon icon-suggest"></i>
	            <c:if test="${!empty refMobile}">
		            <input id="suggest" type="tel" placeholder="Reference number" value="${refMobile}" readonly="readonly" />
	            </c:if>
	            <c:if test="${empty refMobile}">
		            <input id="suggest" type="tel" placeholder="Reference number"  />
	            </c:if>
	        </div>
	        
	        
<!-- 	        <div class="item"> -->
<!-- 	            <i class="icon icon-suggest"></i> -->
<!-- 	            <input id="weixin" type="text" placeholder="微信号" /> -->
<!-- 	        </div> -->
	        
<!-- 	        <div class="item"> -->
<!-- 	            <i class="icon icon-suggest"></i> -->
<!-- 	            <input id="zhifubao" type="text" placeholder="支付宝" /> -->
<!-- 	        </div> -->
	        
	         <div class="item">
	            <i class="icon icon-psw-new"></i>
	            <input id="psw" type="password" placeholder="Set a new password" />
	        </div>
	        <div class="item" id="spanDivPwd" style="display:none;">
	        	<span style="font-size:12px;color:red;">Password length between 6 to 20.</span>
	        </div>
	        <div class="item">
	            <i class="icon icon-psw"></i>
	            <input id="psw2" type="password" placeholder="Confirm new password" />
	        </div>
	        
	        <input id="netWork" type="hidden"  value="${netWork}" />
	        
	    </div>
    <div class="form-btns">
        <a href="javascript:;" class="btn" id="submit">Confirm </a>
        	<p class="tip-right"><a href="javascript:;" id="toLogin">Already have an account</a></p>
    </div>
   
   <script type="text/javascript">
        $(function(){
            //code 点击事件
            $("#codeNum").bind("click",function(){
                $(this).attr("src", '<c:url value="/common/imageCode.html?pageId=register&d="/>' + new Date().getTime());
            });
        });
    </script>

    <script>
    var $psw = $('#psw'),
        $submit = $('#submit'),
        $mobile = $('#mobile'),
        $getcode = $('#getcode');
        $toLogin = $('#toLogin');
        $code = $("#code");
        
        
     $psw.on('focus',function(){
    	 $('#spanDivPwd').attr('style','display:block;');
     });
     $psw.on('blur',function(){
    	 $('#spanDivPwd').attr('style','display:none;');
     }); 
	
    //验证手机格式----1
    function mobile_check(mobile){
    	var msg = verifyMsg(mobile);
    	if(msg != ""){
    		HHN.popup(msg, 'danger');
    		return false;
    	}
    	return true;
    }
    //验证手机格式----2
    function verifyMsg(mobile){
    	if (mobile == null || mobile == "" || mobile.length == 0) {
            return "Input cell phone number";
        }else{
        	if(!HHN.checkPhone(mobile) || mobile.length != 11){
        		return "Error cell phone number！";
        	}else{
        		return "";
        	}
        }
    }
    
    //验证密码--------1
    function pwd_check(password){
    	var msg = verPwdMsg(password);
    	if(msg != ""){
    		HHN.popup(msg, 'danger');
    		return false;
    	}
    	return true;
    }
    //验证密码--------2
    function verPwdMsg(password){
    	if(password == null && password =='' || password.length == 0){
			return "Input password";
		}else{
			if(password.length < 6 || password.length > 20){
				return "Password length  between 6 to 20";
			}
		}
    	return "";
    }
	

    //图形验证密码--------1
    function code_check(code){
        var msg = codeMsg(code);
        if(msg != ""){
            HHN.popup(msg, 'danger');
            return false;
        }
        return true;
    }
    //图形验证密码--------2
    function codeMsg(code){
        if(code == null || code =='' || code.length == 0 || code.length != 4){
                return "Input Image verification";
        }
        return "";
    }

    function refCode(isPass){
        if(true){
            $("#codeNum").attr("src", '<c:url value="/common/imageCode.html?pageId=register&d="/>' + new Date().getTime());
        }else{
            $("#code").attr("src", '<c:url value="/common/imageCode.html?pageId=register&d="/>' + new Date().getTime());
        }
    }


    //获取验证码 ---1
    $getcode.on('click', function(){
    	if(mobile_check($mobile.val())){
    		if(code_check($code.val())){
                countdown();
                sendMobileCheckCode($mobile.val());
            }
    	}
    });
    //获取验证码----2计时
    var interval;
    function countdown() {
        $getcode.attr('disabled', 'disabled').html('120秒');
        var step = 120;
        interval = setInterval(function() {
                if (!--step) {
                    clearInterval(interval);
                    $getcode.html('Afresh').attr('disabled', null);
                    return;
                }
                $getcode.html(step + 'Second');
            }, 1000);
    };
    function countup() {
    	clearInterval(interval);
    	$getcode.html('Afresh').attr('disabled', null);
    	return;
    }; 
    
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
    	var netWork = $("#netWork").val();
    	
    	if(mobile_check(mobile)){
    		var mobileCode = $('#mobileCode').val();
        	if(mobileCode == null || mobileCode == "" || mobileCode.length == 0){//验证码
        		HHN.popup("Input Image verification", 'danger');
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
                            HHN.popup("Input verify password", 'danger');
                            return false;
                        }else{
                            if(password != password2){
                                HHN.popup("Two input password is not the same", 'danger');
                                return false;
                            }else{
                                var refferee = $('#suggest').val();//推荐人手机号
                                var chennelCode = $('#chennelCode').val();//渠道ID
                                //password = $.md5(password+globalConfig.globalSaltVal);
                                var param = {"mobile":mobile,"mobileCode":mobileCode,"password":password,"refferee":refferee,
                                		     "chennelCode":chennelCode,"code":code ,"pageId":"register"
                                		     ,"name":name,"weixin":weixin,"zhifubao":zhifubao,"netWork":netWork
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
				HHN.popup("Register Success!");
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

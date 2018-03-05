<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
    <title>微信登录</title>
	<jsp:include page="../common/common.jsp"></jsp:include>
	<script type="text/javascript" src='<c:url value="/res/js/libs/jquery.min.js?v="/>${jsversion}'></script>
	<script type="text/javascript" src='<c:url value="/res/js/plugins/safe/jQuery.md5.js?v="/>${jsversion}'></script>
</head>

<body>
    <div class="top-nav">
        <a class="link"  href="javascript:;" onclick="history.back();">&lt;返回</a>
        <h2>微信登录</h2>
    </div>
	<form id="loginForm" action="<c:url value='/wx/wxLogin.html'/>" method="post">
				<input type="hidden" name="openId" id="openId" value="${openId}">
	    <div class="exit-wrap">
	        <div class="item">
	            <i class="icon"></i>
	            <input type="tel" placeholder="请输入手机号码" id="mobile" value="${mobile}">
	        </div>
	         <div class="item">
	            <i class="icon icon-code"></i>
	            <input type="text" placeholder="输入验证码" id="mobileCode" />
	            <button class="code-btn" id="getcode">免费获取</button>
	        </div>	        
	   </div>
	</form>
	
    <div class="form-btns">
        <a href="javascript:;" class="btn" id="submit">微信登陆</a>
    </div>
    <script>
        var $submit  = $('#submit'),
        $mobile   = $('#mobile'),
        $getcode = $('#getcode');
        
		
        var $Ipt=$('#exit-wrap input');
        $Ipt.on('focus',function(){
            $(this).parent().addClass('borColor');
        })
        $Ipt.on('blur',function(){
            $(this).parent().removeClass('borColor');
        })
        $Ipt.on('keyup',function(){
            if($mobile.val()!=''){
                $submit.removeClass('normal');
            }
            if($mobile.val()==''){
                $submit.addClass('normal');
            }
        })

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
                return "请输入手机号";
            }else{
            	if(!HHN.checkPhone(mobile) || mobile.length != 11){
            		return "手机号码格式错误！";
            	}else{
            		return "";
            	}
            }
        }             
		
        //获取验证码 ---1
        $getcode.on('click', function(){
        	if(mobile_check($mobile.val())){
        		countdown();
        		sendMobileCheckCode($mobile.val());
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
                        $getcode.html('重新获取').attr('disabled', null);
                        return;
                    }
                    $getcode.html(step + '秒');
                }, 1000);
        };
        function countup() {
        	clearInterval(interval);
        	$getcode.html('重新获取').attr('disabled', null);
        	return;
        }; 
        //获取验证码----3请求
        function sendMobileCheckCode(mobile) {
    		var param = {"mobile":mobile,"checkMobile":true};
    		$.post('<c:url value="/common/imageCode.html?pageId=userlogin"/>', param, function(data) {
    			if(data.success && data.resultCode == '0'){
    				HHN.popup(data.resultMessage);
    			}else{
    				HHN.popup(data.resultMessage);
    				countup();
    			}
    		},"json");
    	}
        
        //处理微信登录数据
        $submit.on('click', function(){
        	var mobile = $mobile.val();
        	var openId = $('#openId').val();
        	if(mobile_check($mobile.val())){
        		var mobileCode = $('#mobileCode').val();
            	if(mobileCode == null || mobileCode == "" || mobileCode.length == 0){//验证码
            		HHN.popup("请输入验证码", 'danger');
            		return false;
            	}else{
        		    submitWxLogin(mobile,mobileCode,openId);
            	}
        	}
        });
       
        //提交微信登录信息
        function submitWxLogin(mobile,mobileCode,openId){
        	var param = {"mobile":mobile,"mobileCode":mobileCode,"openId":openId};
        	$.post('<c:url value="/wx/wxLogin.html"/>', param, function(data) {
        		if(data.success && data.resultCode == '0'){
    				HHN.popup(data.resultMessage);
    				window.location.href=data.model;
    			}else{
    				HHN.popup(data.resultMessage);
    			}
    		},"json");
        }
    </script>
</body>
</html>
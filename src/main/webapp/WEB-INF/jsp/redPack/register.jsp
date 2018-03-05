<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html  class="heightFull">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no" />
    <meta name="format-detection" content="telephone=no">
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="apple-mobile-web-app-status-bar-style" content="black" />
    <meta name="keywords" content="" />
    <meta name="description" content="" />
    <title> 用户注册</title>
    	<link href="<c:url value ='/res-kuangji/css/security.css'/>"
	rel="stylesheet" type="text/css" />
	<jsp:include page="../common/common.jsp"></jsp:include>
	<script type="text/javascript" src="<c:url value='/res/js/libs/jquery.min.js'/>?v=${jsversion}"></script>
	<script type="text/javascript" src="<c:url value='/res/js/plugins/safe/jQuery.md5.js'/>?v=${jsversion}"></script>
        <style>
          .background{
              flex: 1;
          }
          .background .exit-wrap{
              margin-top: 5px;
          }
          #code{
              margin-left: 1rem;
          }
          #codeNum,#getcode{
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

<body class="heightFull displayFlex">
	<div class="top">
		<b onclick="javascript:window.location.href=&#39;<c:url value='/redPack/personalCenter.html'/>&#39;"></b>
		<dd>注册</dd>
  </div> 
    <div class="background">
	    <div class="exit-wrap">
	    	<form id="toLoginForm" action='<c:url value="/account/register.html"/>' method="POST">
		        <div class="item">
		            <i class="icon"></i>
		        </div>
		        <div class="item">
		            <i class="icon"></i>
		            <input type="text" placeholder="真实姓名" id="name" name="name" />
		        </div>
		        <div class="item">
		            <i class="icon"></i>
		            <input type="tel" placeholder="手机号码" id="mobile" name="mobile" />
		        </div>
		        
	        </form>
            <div class="item">                
                <input type="text" id="code" name="code" placeholder="图片验证码">            
                <img src='<c:url value="/common/imageCode.html?pageId=register"/>' width="120" height="40" title="点击更换验证码" id="codeNum" />                            
            </div>
	        <div class="item">
	            <i class="icon icon-code"></i>
	            <input name="mobileCode" type="text" placeholder="输入验证码" id="mobileCode" />
	            <button class="code-btn" id="getcode">免费获取</button>
	        </div>
	       
	        <div class="item">
	            <i class="icon icon-suggest"></i>
	            <c:if test="${!empty refMobile}">
		            <input id="suggest" type="tel" placeholder="推荐人手机号" value="${refMobile}" readonly="readonly" />
	            </c:if>
	            <c:if test="${empty refMobile}">
		            <input id="suggest" type="tel" placeholder="推荐人手机号"  />
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
	            <input id="psw" type="password" placeholder="设置登陆密码" />
	        </div>
	        <div class="item" id="spanDivPwd" style="display:none;">
	        	<span style="font-size:12px;color:red;">密码长度为6-20之间，建议由字母、数据、符号两种以上构成</span>
	        </div>
	        <div class="item">
	            <i class="icon icon-psw"></i>
	            <input id="psw2" type="password" placeholder="确认登陆密码" />
	        </div>
	        
	        <div class="item">
	            <i class="icon icon-psw-new"></i>
	            <input id="passwordTwo" type="password" placeholder="设置二级密码" />
	        </div>
	        <div class="item" id="spanDivPwd2" style="display:none;">
	        	<span style="font-size:12px;color:red;">密码长度为6-20之间，建议由字母、数据、符号两种以上构成</span>
	        </div>
	        <div class="item">
	            <i class="icon icon-psw"></i>
	            <input id="passwordTwo2" type="password" placeholder="确认二级密码" />
	        </div>
	        
	        
	        <input id="netWork" type="hidden"  value="${netWork}" />
	        
	    </div>
    <div class="form-btns">
        <a href="javascript:;" class="btn" id="submit">确定</a>
        	<p class="tip-right"><a href="javascript:;" id="toLogin">已有帐号去登录</a></p>
    </div>
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
    function mobile_check(mobile,sourcemsg){
    	var msg = verifyMsg(mobile,sourcemsg);
    	if(msg != ""){
    		HHN.popup(msg, 'danger');
    		return false;
    	}
    	return true;
    }
    //验证手机格式----2
    function verifyMsg(mobile,sourcemsg){
    	if (mobile == null || mobile == "" || mobile.length == 0) {
            return "请输入"+sourcemsg+"手机号";
        }else{
        	if(!HHN.checkPhone(mobile) || mobile.length != 11){
        		return sourcemsg+"手机号码格式错误！";
        	}else{
        		return "";
        	}
        }
    }
    
    //验证密码--------1
    function pwd_check(password,sourcemsg){
    	var msg = verPwdMsg(password,sourcemsg);
    	if(msg != ""){
    		HHN.popup(msg, 'danger');
    		return false;
    	}
    	return true;
    }
    //验证密码--------2
    function verPwdMsg(password,sourcemsg){
    	if(password == null && password =='' || password.length == 0){
			return "请输入"+sourcemsg+"密码";
		}else{
			if(password.length < 6 || password.length > 20){
				return sourcemsg+"密码长度在6-20之间";
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
                return "请填写正确的图片验证码";
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

    	if(name == null || name == "" || name.length == 0){//昵称
    		HHN.popup("请输入昵称", 'danger');  	
       		return false;
    	}
    	
    	if(!mobile_check(mobile,"用户")){    	
       		return false;
    	}
    	
   		var mobileCode = $('#mobileCode').val();
       	if(mobileCode == null || mobileCode == "" || mobileCode.length == 0){//验证码
       		HHN.popup("请输入验证码", 'danger');
       		return false;
       	}

        	
        var code = $code.val();
        var bCode = code_check(code);
   		if(!bCode){
       		return false;
   		}
   		
        //验证密码
        var password = $psw.val();//密码
        var password2 = $('#psw2').val();//重复密码
        var bPwd = pwd_check(password,"登陆密码");
         if(!bPwd){
             return false;
         }
         
         if(password2 == null || password2 == "" || password2.length == 0){
             HHN.popup("请输入确认登陆密码", 'danger');
             return false;
         }
         if(password != password2){
             HHN.popup("两次输入登陆的密码不一致", 'danger');
             return false;
         }

       	 //验证二级密码
         var passwordTwo = $("#passwordTwo").val();//密码
         var passwordTwo2 = $('#passwordTwo2').val();//重复密码
         bPwd = pwd_check(passwordTwo,"二级密码");
          if(!bPwd){
              return false;
          }
          
          if(passwordTwo2 == null || passwordTwo2 == "" || passwordTwo2.length == 0){
              HHN.popup("请输入确认二级密码", 'danger');
              return false;
          }
          if(passwordTwo != passwordTwo2){
              HHN.popup("两次输入的二级密码不一致", 'danger');
              return false;
          }
          
         
         var refferee = $('#suggest').val();//推荐人手机号
         var chennelCode = $('#chennelCode').val();//渠道ID
                                 
          if(refferee != null && refferee != '' && refferee.length > 0){
              if(!mobile_check(refferee,"推荐人")){
                  return false ;
              }
          }
                                
          var param = {"mobile":mobile,"mobileCode":mobileCode,"password":password,"refferee":refferee,
     		     "chennelCode":chennelCode,"code":code ,"pageId":"register"
     		     ,"name":name,"weixin":weixin,"zhifubao":zhifubao,"netWork":netWork,"passwordTwo":passwordTwo
     		     };

          submitReg(param);
    
    	        
    });


    
    //提交注册信息
    function submitReg(param){
    	$.post('<c:url value="/account/register.html"/>', param, function(data) {
			if(data.result == '注册成功'){
				HHN.popup("恭喜您注册成功!");
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
    	$.post("<c:url value='/loanuser/login.do'/>", param, function(data) {
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

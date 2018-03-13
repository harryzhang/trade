<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html class="heightFull">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no" />
    <meta name="format-detection" content="telephone=no">
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="apple-mobile-web-app-status-bar-style" content="black" />
    <meta name="keywords" content="" />
    <meta name="description" content="" />
    <title>登录</title>
	<jsp:include page="../common/common.jsp"></jsp:include>
	<script type="text/javascript" src='<c:url value="/res/js/libs/jquery.min.js?v="/>${jsversion}'></script>
	<script type="text/javascript" src='<c:url value="/res/js/plugins/safe/jQuery.md5.js?v="/>${jsversion}'></script>
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
<body class="heightFull displayFlex">
    <div class="top-nav">
        <a class="link"  href="javascript:;" onclick="history.back();">&lt;返回</a>
        <h2>登录</h2>
    </div>
    <div class="background">
        <form id="loginForm" action="<c:url value='/loanuser/login.do'/>" method="post">
            <input type="hidden" name="fromUrl" id="fromUrl" value="${fromUrl}" >
            <div class="exit-wrap">
                <div class="item">
                    <i class="icon"></i>
                    <input type="tel" placeholder="请输入手机号码" id="mobile" value="" >
                    <input type="hidden" hidden="true" id="openId" value="${openId}" >
                </div>
                <div class="item">
                    <i class="icon icon-psw"></i>
                    <input id="psw" type="password" placeholder="填写密码" value="" >
                    <i class="icon icon-eye" id="seePsw"></i>
                </div>
                <div class="item">                
                    <input type="text" id="code" name="code" placeholder="图片验证码">            
                    <img src="<c:url value="/common/imageCode.html?pageId=userlogin"/>" width="120" height="40" title="点击更换验证码" id="codeNum" />                            
                </div>
                   <div class="item">
                    <i class="icon"></i>
                    <select id="userLocal" name="userLocal" style=" width :160px">
                        
                        <option value="" >Choose language</option>
                        <option value="en" >english</option>
                        <option value="cn" >中文</option>
                    </select>
                </div>
            </div>
        </form>
    
        <div class="form-btns">
            <a href="javascript:;" class="btn" id="submit">登录</a>      
            <p class="tip-right">
                      <a href='<c:url value="/account/regIndex.html"/>' style="float:left;"  id="toRegHtmlA">立即注册</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                      <%--<a href="javascript:;" id="wxLogin" style="float:left;width:45%">微信登录</a>--%>
                      <a href="<c:url value='/account/resetPwdIndex.html'/>" id="restPsw">忘记密码?</a>
            </p>
        </div>
    </div>
	
    <script type="text/javascript">
        $(function(){
            //code 点击事件
            $("#codeNum").bind("click",function(){
                $(this).attr("src", '<c:url value="/common/imageCode.html?pageId=userlogin&d="/>' + new Date().getTime());
            });
            
            //处理多语言登录数据
            $('#userLocal').change(function(){ 
                var url = '/login/index.do?userLocal=' + $('#userLocal').val();
            	window.location.href="<c:url value='" +url+ "' />";//页面跳转并传参 
            })  
        });

        var $psw = $('#psw'),
        $seePsw  = $('#seePsw'),
        $submit  = $('#submit'),
        $mobile   = $('#mobile'),
        $restPsw = $('#restPsw');
        $code = $("#code");
		
        var $Ipt=$('#exit-wrap input');
        $Ipt.on('focus',function(){
            $(this).parent().addClass('borColor');
        })
        $Ipt.on('blur',function(){
            $(this).parent().removeClass('borColor');
        })
        $Ipt.on('keyup',function(){
            if($mobile.val()!='' && $psw.val()!=''){
                $submit.removeClass('normal');
            }
            if($mobile.val()=='' || $psw.val()==''){
                $submit.addClass('normal');
            }
        })
        
        $seePsw.on('click', function(){
            if(!$seePsw.hasClass('icon-eye-open')){
                $seePsw.addClass('icon-eye-open');
                $psw.attr('type', 'text');
            }else{
                $seePsw.removeClass('icon-eye-open');
                $psw.attr('type', 'password');
            }
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
                return "请输入手机号";
            }else{
            	if(!HHN.checkPhone(mobile) || mobile.length != 11){
            		return "手机号码格式错误！";
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
                return "请输入密码";
            }else{
                if(password.length < 6 || password.length > 20){
                    return "密码长度在6-20之间";
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
                $("#codeNum").attr("src", '<c:url value="/common/imageCode.html?pageId=userlogin&d="/>' + new Date().getTime());
            }else{
                $("#code").attr("src", '<c:url value="/common/imageCode.html?pageId=userlogin&d="/>' + new Date().getTime());
            }
        }

		
        //处理登录数据
        $submit.on('click', function(){
        	var mobile = $mobile.val();
        	if(mobile_check($mobile.val())){
        		var psw = $psw.val();
        		var bPsw = pwd_check(psw);
                var code = $code.val();
                var bCode = code_check(code);
                var userLocal = $("#userLocal").val();
                if( "" == userLocal){
                	userLocal = 'cn';
                }
        		if(bPsw){
                    if(bCode){
                        //psw = $.md5(psw+globalConfig.globalSaltVal);
                        var param = {"userName":mobile,"password":psw,"fromUrl":$('#fromUrl').val() ,"code":code ,
                        		"pageId":"userlogin","userLocal":userLocal,"openId":$('#openId').val()};
                        submitLogin(param);
                    }        			
        		}
        	}
        });
        //提交登录信息
        function submitLogin(param){
        	$.post('<c:url value="/login/login.html"/>', param, function(data) {
        		
        		if(data.result==1){
        			refCode(false);
        			HHN.popup("登录信息填写不完整");
    			}else if(data.result==2){
    				refCode(false);
    				HHN.popup("验证码输入有误");
    			}else if(data.result==3){ 
    				refCode(false);
    				HHN.popup("用户名或密码错误");
    			}else if(data.result==4){ 
    				//去完善个人资料
    				window.location.href ='<c:url value="/common/modifyInfo.html"/>';
    			}else{
    				//HHN.popup(data.resultMessage);
    				//window.location.href ='<c:url value="/redPack/personalCenter.html"/>';
    				//window.location.href ='<c:url value="/notice/notice.html"/>';
    				window.location.href ="<c:url value='/firstpage/toFirstpage.html'/>";
    			}
    		},"json");
        }
        
        
  
        
  

        
        
        //去重置页面
        $restPsw.on('click', function() {
        	$('#restPswForm').submit();//这里用POST表单提交方式来跳转
        	//var mobile = $mobile.val();
        	//var param = {"mobile":mobile};
        	//window.location.href="toLogin.do?mobile="+mobile;
        });
    </script>

</body>
</html>
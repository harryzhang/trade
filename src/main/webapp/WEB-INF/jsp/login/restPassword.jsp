<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <title>重置密码</title>
	<jsp:include page="../common/common.jsp"></jsp:include>
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
          input::-webkit-input-placeholder{
              color:#fff;
          }
          input::-moz-placeholder{   /* Mozilla Firefox 19+ */
              color:#fff;
          }
          input:-moz-placeholder{    /* Mozilla Firefox 4 to 18 */
              color:#fff;
          }
          input:-ms-input-placeholder{  /* Internet Explorer 10-11 */ 
              color:#fff;
          }
      </style>
</head>

<body class="heightFull displayFlex">
<c:if test="${platform ne 'android' && platform ne 'ios' && platform ne 'pad'}">
    <div class="top-nav">
        <a class="link"  href="javascript:;" onclick="javascript:window.location.href=&#39;<c:url value='/member/userSettle.html'/>&#39;">&lt;返回</a>
        <h2>重置密码</h2>
    </div>
</c:if>   
    <div class="background"> 
	    <div class="exit-wrap">
	    	<form id="toLoginForm" action="<c:url value='/account/restLoginPwd.html'/>" method="POST">
	    	    <input type="hidden" id="pwdType" name="pwdType" value="${pwdType}"/>
		        <div class="item">
		            <i class="icon"></i>
		          <c:if test="${!empty userDo.userName}">
		            <input type="tel" placeholder="手机号码" readOnly="true" id="mobile" name="mobile"  value="${userDo.userName}" />
		          </c:if>
		          <c:if test="${empty userDo.userName}">
		            <input type="tel" placeholder="手机号码"  id="mobile" name="mobile"   />
		          </c:if>
		        </div>
	        </form>
            <div class="item">                
                <input type="text" id="code" name="code" placeholder="图片验证码">            
                <img src='<c:url value="/common/imageCode.html?pageId=reset"/>' width="120" height="40" title="点击更换验证码" id="codeNum" />                            
            </div>
	        <div class="item">
	            <i class="icon icon-code"></i>
	            <input type="text" placeholder="输入验证码" id="mobileCode" />
	            <button class="code-btn" id="getcode">免费获取</button>
	        </div>
	        <div class="item">
	            <i class="icon icon-psw-new"></i>
	            <input id="psw" type="password" placeholder="设置新密码" />
	        </div>
	        <div class="item">
	            <i class="icon icon-psw"></i>
	            <input id="psw2" type="password" placeholder="确认密码" />
	        </div>
	    </div>
        <div class="form-btns">
            <a href="javascript:;" class="btn" id="submit">确定</a>
            <!-- <p class="tip-right" ><a href="javascript:;" id="toLogin" style="display: none;">去登录</a></p> -->
        </div>
    </div>
    <script type="text/javascript">
        $(function(){
            //code 点击事件
            $("#codeNum").bind("click",function(){
                $(this).attr("src", '<c:url value="/common/imageCode.html?pageId=reset"/>' + new Date().getTime());
            });
        });
    </script>
    <script>
    var $psw = $('#psw'),
        $submit = $('#submit'),
        $mobile = $('#mobile'),
        $getcode = $('#getcode');
        $toLogin = $('#toLogin');
    var $code = $("#code");
	
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
            $("#codeNum").attr("src", '<c:url value="/common/imageCode.html?pageId=reset"/>' + new Date().getTime());
        }else{
            $("#code").attr("src", '<c:url value="/common/imageCode.html?pageId=reset"/>' + new Date().getTime());
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
		var param = {"mobile":mobile,"code":$code.val(),"pageId":"reset"};
		
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
    	var pwdType = $("#pwdType").val();
    	
    	
    	if(mobile_check(mobile)){
    		var mobileCode = $('#mobileCode').val();
            var code = $code.val();
            var bCode = code_check(code);
        	if(bCode){
                if(mobileCode == null || mobileCode == "" || mobileCode.length == 0){//验证码
                HHN.popup("请输入验证码", 'danger');
                    return false;
                }else{
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
                                var param = {"mobile":mobile,"mobileCode":mobileCode,"password":password,"code":code,"pageId":"reset","pwdType":pwdType};          
                                submitInfo(param);
                            }
                        }
                    }
                }
            }
    	}
    });
    
    //提交信息
    function submitInfo(param){
    	$.post('<c:url value="/account/restLoginPwd.html"/>', param, function(data) {
			if(data.result == '0'){
				HHN.popup("修改成功");
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
    
    $toLogin.on('click', function() {
    	window.location.href= '<c:url value="/login/index.html"/>';
    });
    </script>
</body>

</html>

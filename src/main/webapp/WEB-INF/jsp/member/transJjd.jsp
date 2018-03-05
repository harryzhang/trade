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
    <title>奖金豆转换</title>
	<jsp:include page="../common/common.jsp"></jsp:include>
	<script type="text/javascript" src='<c:url value="/res/js/libs/jquery.min.js?v="/>${jsversion}'></script>
	<script type="text/javascript" src='<c:url value="/res/js/plugins/safe/jQuery.md5.js?v="/>${jsversion}'></script>
</head>
<body>
    <div class="top-nav">
        <a class="link"  href="javascript:;" onclick="history.back();">&lt;返回</a>
        <h2>奖金豆转换激活积分</h2>
    </div>
	<form id="loginForm" action="<c:url value='/member/saveTrans.html'/>" method="post">
	    <div class="exit-wrap">
	        <div class="item">
	        	<input type="hidden"  id="token" name="token" value="${token }"/>
	        	<input type="hidden" id="pointAmt" value="${pointAmt}"/>
	        	<input type="text"   readonly="readonly"    value="可转换额度：${pointAmt}" >
	        </div>
	        <div class="item">
	            <input type="number" placeholder="输入本次转换额度(50的倍数)" id="jifeng"  value="" >
	        </div>
	        <div class="item">
	             <input type="text"   readonly="readonly" id="jieyu" placeholder="本次转换结余" value="" >
	        </div>
	        <div class="item">
	            <i class="icon icon-psw"></i>
	            <input id="psw" type="password" placeholder="填写二级密码" value="" >
	            <i class="icon icon-eye" id="seePsw"></i>
	        </div>
            <div class="item">                
                <input type="text" id="code" name="code" placeholder="图片验证码">            
                <img src="<c:url value="/common/imageCode.html?pageId=transJifeng"/>" width="120" height="40" title="点击更换验证码" id="codeNum" />                            
            </div>
	    </div>
	</form>
	
    <div class="form-btns">
        <a href="javascript:;" class="btn" id="submit">确定</a>      
    </div>
    <script type="text/javascript">
        $(function(){
            //code 点击事件
            $("#codeNum").bind("click",function(){
                $(this).attr("src", '<c:url value="/common/imageCode.html?pageId=transJifeng&d="/>' + new Date().getTime());
            });

            //code 点击事件
            $("#jifeng").bind("blur",function(){
                var jifen = $(this).val();
                var pointAmt = $("#pointAmt").val();
				if(jifen == null || jifen == '' || typeof(jifen)== "undefined" ){
					return false;
				}

				if(parseFloat(jifen) < 0 ){
					$(this).val("");
                	$("#jieyu").val("");
                	HHN.popup("转换奖金豆必须大于0");
                	return false;
				}
				
                if(parseFloat(jifen)>parseFloat(pointAmt)){
                	$(this).val("");
                	$("#jieyu").val("");
                	HHN.popup("本次转换额度不能大于可转换额度");
                	return false;
                 }
                if(jifen%50 != 0 ){ 
                	$(this).val("");
                	$("#jieyu").val("");
                	HHN.popup("转换额度必须是50的倍数");
                	return false;
                 }
                
                $("#jieyu").val(pointAmt-jifen);
            });
        });

        //图形验证密码--------2
        function codeMsg(code){
            if(code == null || code =='' || code.length == 0 || code.length != 4){
                return "请填写正确的图片验证码";
            }
            return "";
        }
        

        function refCode(isPass){
            if(true){
                $("#codeNum").attr("src", '<c:url value="/common/imageCode.html?pageId=transJifeng&d="/>' + new Date().getTime());
            }else{
                $("#code").attr("src", '<c:url value="/common/imageCode.html?pageId=transJifeng&d="/>' + new Date().getTime());
            }
        }

		
        //处理数据
        $("#submit").on('click', function(){
        	var jifeng = $("#jifeng").val();
            var code = $("#code").val();
			
            if(""==jifeng || typeof(jifeng)=='undefined'){
            	HHN.popup("转换奖金豆不能为空");
            	return false;
            }

            if(parseFloat(jifeng) < 0 ){
            	HHN.popup("转换奖金豆必须大于0");
            	return false;
            }

            var msg = codeMsg(code);
            if(msg != ""){
                HHN.popup(msg, 'danger');
                return false;
            }
            
            var param = {"code":code , "pageId":"transJifeng","jifeng":jifeng,"token":$("#token").val(),"pwd":$("#psw").val()};
            submitLogin(param);
        });
        
        //提交
        function submitLogin(param){
        	$.post('<c:url value="/member/saveTransJjd.html"/>', param, function(data) {
        		$("#token").val(data.token);
        		if(data.result==1){
        			refCode(false);
        			HHN.popup("转换奖金豆不能为空");
    			}else if(data.result==2){
    				refCode(false);
    				HHN.popup("验证码输入有误");
    			}else if(data.result==3){ 
    				refCode(false);
    				HHN.popup("转换失败:"+data.msg);
    			}else if(data.result==4){ 
    				refCode(false);
    				HHN.popup("转换奖金豆不能大于当前可转总奖金豆");
    			}else if(data.result==6){ 
    				refCode(false);
    				HHN.popup("转换奖金豆必须是50的倍数");
    			}else if(data.result== 10){ 
    				refCode(false);
    				HHN.popup("请勿重复提交");
    			}else if(data.result== 11){ 
    				refCode(false);
    				HHN.popup("二级密码错误");
    			}else if(data.result== 0){
    				HHN.popup("转换奖金豆成功");
    				window.location.href ='<c:url value="/redPack/personalCenter.html"/>';
    			}
        		
    		},"json");
        }
        
    </script>

</body>
</html>
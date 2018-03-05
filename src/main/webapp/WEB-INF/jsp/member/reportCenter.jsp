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
    <title>设定报单中心</title>
	<jsp:include page="../common/common.jsp"></jsp:include>
	<script type="text/javascript" src='<c:url value="/res/js/libs/jquery.min.js?v="/>${jsversion}'></script>
	<script type="text/javascript" src='<c:url value="/res/js/plugins/safe/jQuery.md5.js?v="/>${jsversion}'></script>
</head>
<body>
    <div class="top-nav">
        <a class="link"  href="javascript:;" onclick="history.back();">&lt;返回</a>
        <h2>设定报单中心</h2>
    </div>
	<form id="payForm" action='<c:url value="/pay/savePay.html"/>' method="post">
		
	    <div class="exit-wrap">
	        <div class="item">
	            <i class="icon"></i>
	            <input type="number" placeholder="请输入报单中心号码" id="mobile" name="mobile" value="" >
	        </div>	        
	</form>
	
    <div class="form-btns">
        <a href="javascript:;" class="btn" id="submit">提交</a> 
    </div>
     <script type="text/javascript">
        //处理数据
        $("#submit").on('click', function(){
        	var mobile = $("#mobile").val();
			
            if(""==mobile || typeof(jifeng)=='mobile'){
            	HHN.popup("报单中心手机不能为空");
            	return false;
            }
            var param = {"mobile":mobile};
            submitPay(param);
        });
   
		
		
        //提交
        function submitPay(param){
			$.post('<c:url value="/account/saveCenter.html"/>', param, function(data) {
        		
        		if(data.result !=1){
        			HHN.popup("设定失败：" + data.message);
    			}else{
    				alert("设定成功" );
    				window.location.href ='<c:url value="/redPack/personalCenter.html"/>';
    			}
        		
    		},"json");
        }
        
        
    </script>
</body>
</html>
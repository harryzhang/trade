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
    <title>充值</title>
	<jsp:include page="../common/common.jsp"></jsp:include>
	<script type="text/javascript" src='<c:url value="/res/js/libs/jquery.min.js"/>'></script>
	<script type="text/javascript" src='<c:url value="/res/js/plugins/safe/jQuery.md5.js"/>'></script>
</head>
<body>
    <div class="top-nav">
        <a class="link"  href="javascript:;" onclick="history.back();">&lt;返回</a>
        <h2>用户充值</h2>
    </div>
	<form id="payForm" action='<c:url value="/pay/payNet.html"/>' method="post">
		
		<%-- 	
		
		<form id="payForm" action='<c:url value="/pay/savePayOffline.html"/>' method="post"> 
		<input type="hidden" name="serverUrl" value="https://pg.openepay.com/gateway/index.do?" />
		<input type="hidden" name="receiveUrl" value="http://www.skwuas.top/pay/notifyResult.html" />
		<input type="hidden" name="pickupUrl" value="http://www.skwuas.top/pay/paySuccess.html" />
		--%>
		<%--<input type="hidden" name="merchantId" value="103610161205001" />--%>
		<%--<input type="hidden" name="key" value="zxcvbnm1" />  --%>
		<input type="hidden" name="inputCharset" value="1" />
		
		
		<input type="hidden" name="version" value="v1.0" />
		<input type="hidden" name="language" value="1" />
		<input type="hidden" name="signType" value="1" />
		
		<input type="hidden" name="payerName" value="平台冲值" />
		<input type="hidden" name="orderCurrency" value="156" />
		<input type="hidden" id="orderDatetime" name="orderDatetime" value="20161129133632" />
		<input type="hidden" name="productPrice" value="100" />
		<input type="hidden" name="productNum" value="1" />
		<input type="hidden" name="payType" value="0" />
		
	    <div class="exit-wrap">
	        <div class="item">
	            <i class="icon"></i>
	            <input type="number" placeholder="请输入充值金额" id="payAmount" name="payAmount" value="" >
	        </div>	        
	</form>
	
    <div class="form-btns">
        <a href="javascript:;" class="btn" id="submit">充值</a> 
    </div>
     <script type="text/javascript">
        //处理数据
        $("#submit").on('click', function(){
        	var jifeng = $("#payAmount").val();
			
            if(""==jifeng || typeof(jifeng)=='undefined'){
            	HHN.popup("充值金额不能为空");
            	return false;
            }
            var param = {"payAmount":jifeng};
            submitPay(param);
        });
      /*   var handler = function(tradeNo){
        	 var param = {"tradeNo":tradeNo};
			$.post('<c:url value="/pay/quaryPay.html"/>',param,function(msg){
				alert(msg);
				if(msg == '1'){
//						alert("支付成功");
					document.location.href='<c:url value="/pay/paySuccess.html"/>';
					clearInterval(timer);
				}
			});
		} */
		
		
        //提交
        function submitPay(param){
			HHN.loading('冲值已提交,请等待...');
			//HHN.loading('系统更新中,请稍等...');
			$("#payForm").submit();
			/*
        	$.post('<c:url value="/pay/savePay.html"/>', param, function(data) {
        		
        		if(data.result==1){
        			refCode(false);
        			HHN.popup("充值失败：" + data.message);
    			}else{
    				
    				var payInfo = data.pay_info;
    				var tradeNo  = data.out_trade_no;
    				//timer = setInterval(handler(tradeNo) , 5000);
    				window.location.href =payInfo ;
    			}
        		
    		},"json");
    		*/
        }
        
        
    </script>
</body>
</html>
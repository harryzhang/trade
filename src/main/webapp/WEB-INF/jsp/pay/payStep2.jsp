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
    <title>在线支付</title>
	<jsp:include page="../common/common.jsp"></jsp:include>
	<script type="text/javascript" src='<c:url value="/res/js/libs/jquery.min.js?v="/>${jsversion}'></script>
	<script type="text/javascript" src='<c:url value="/res/js/plugins/safe/jQuery.md5.js?v="/>${jsversion}'></script>
</head>


<body>
<c:if test="${ payType eq 'WX_PAY' || payType eq 'WAP_PAY'  }">
	<script type="text/javascript">
		document.location.href="${payVal}";
	</script>
</c:if>



<c:if test="${ payType eq 'SAOMA_PAY' }">
   
    <div class="top-nav">
        <a class="link"  href="javascript:;" onclick="history.back();">&lt;返回</a>
        <h2>扫码支付</h2>
    </div>
	<form   method="post">
	
	    <div class="exit-wrap" >
	    
	    	<div class="item" style="color:black">
	    		<span>商品名称:充值</span>
	    		<span style="margin-left: 30px;">金额:${payAmount}元</span>
	    	</div>
	    	<div class="item" style="color:black">
	    		<span>订单编号:${outTradeNo}</span>
	    		<input type="hidden" id="out_trade_no" value="${outTradeNo}"/>
	    	</div>
	    	
            <div class="item">
	    		<span style="color:red;text-align: center;">请在5分钟完成充值,否则订单失效</span>
	    	</div>
	    </div>
	    <div>                
           <img src="${payVal}" width="100%"  />                            
        </div>
	</form>
	
	<script type="text/javascript">
	   var timer;
		$(function(){
			//alert('abc');
			//if("WX_PAY" ==  '${payType}' || "WAP_PAY"==  '${payType}' ){ // 微信支付,WAP支付
			//	window.location.href =${payVal};
			//}
			
			var handler = function(){
				var out_trade_no = $('#out_trade_no').val();
				$.post("<c:url value='/pay/payResultQuery.html'/>?out_trade_no="+out_trade_no,{},function(msg){
					if(msg == '1'){
						document.location.href="<c:url value='/pay/paySuccess.html'/>";
						clearInterval(timer);
					}
				});
			}
			timer = setInterval(handler , 2000);
		});
	</script>
	
</c:if>	
</body>
</html>
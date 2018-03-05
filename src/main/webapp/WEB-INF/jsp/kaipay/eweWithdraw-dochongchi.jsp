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
	<script type="text/javascript" src='<c:url value="/res/js/libs/jquery.min.js?v="/>${jsversion}'></script>
	<script type="text/javascript" src='<c:url value="/res/js/plugins/safe/jQuery.md5.js?v="/>${jsversion}'></script>
</head>
<body>
    <div class="top-nav">
        <a class="link"  href="javascript:;" onclick="history.back();">&lt;返回</a>
        <h2>充值确认</h2>
    </div>
  		<form id="form2" name="form2" action="${serverUrl}" method="post">	
<br/>
<table class="table_box" width="50%"><tr><td style="padding-left:20px;">
	<table class="table_box">
	  
	</table>	
</td>
</tr>
</table>	
  <div class="exit-wrap">
	        <div class="item">
	        	<label style="color:#000">充值金额(元)：</label>
	            <i class="icon"></i>
	            <input type="number" placeholder="充值金额"  name="payAmount" value="${viewAmount}" >
	        </div>	 
  <div class="form-btns">
        <a href="javascript:;" class="btn" id="submit">充值</a> 
    </div>
		<input type="hidden" name="inputCharset" id="inputCharset" value="${inputCharset}"/>
		<input type="hidden" name="inputCharset" value="${inputCharset}"/>
		<input type="hidden" name="pickupUrl" value="${pickupUrl}"/>
		<input type="hidden" name="receiveUrl" value="${receiveUrl}" />
		<input type="hidden" name="version" value="${version }"/>
		<input type="hidden" name="language" value="${language }" />
		<input type="hidden" name="signType" value="${signType}"/>
		<input type="hidden" name="merchantId" value="${merchantId}" />
		<input type="hidden" name="payerName" value="${payerName}"/>
		<input type="hidden" name="payerEmail" value="${payerEmail}" />
		<input type="hidden" name="payerTelephone" value="${payerTelephone }" />
		<input type="hidden" name="orderNo" value="${orderNo}" />
		<input type="hidden" name="orderAmount" value="${orderAmount }"/>
		<input type="hidden" name="orderCurrency" value="${orderCurrency}" />
		<input type="hidden" name="orderDatetime" value="${orderDatetime}" />
		<input type="hidden" name="orderExpireDatetime" value="${orderExpireDatetime }"/>
		<input type="hidden" name="productName" value="${productName}" />
		<input type="hidden" name="productPrice" value="${productPrice}" />
		<input type="hidden" name="productNum" value="${productNum }"/>
		<input type="hidden" name="productId" value="${productId}" />
		<input type="hidden" name="productDesc" value="${productDesc}" />
		<input type="hidden" name="ext1" value="${ext1}" />
		<input type="hidden" name="ext2" value="${ext2}" />
		<input type="hidden" name="extTL" value="${extTL}" />
		<input type="hidden" name="payType" value="${payType}" />
		<input type="hidden" name="issuerId" value="${issuerId}" />
		<input type="hidden" name="signMsg" value="${sign }" />
	 </form>
	 <script type="text/javascript">
	  $("#submit").on('click', function(){
	 		$("#form2").submit();
	  })
	 </script>
 </body>
</html>
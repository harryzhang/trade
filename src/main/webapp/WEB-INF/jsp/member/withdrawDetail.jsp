<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=0">
<meta name="apple-touch-fullscreen" content="yes">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">


<title>提现明细</title>

<link href="<c:url value ='/res-kuangji/css/global.css'/>"
	rel="stylesheet" type="text/css" />
<link href="<c:url value ='/res-kuangji/css/redPacketRecords.css'/>"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="<c:url value ='/res-kuangji/js/jquery-2.1.1.min.js'/>"></script>
<script type="text/javascript"
	src="<c:url value ='/res-kuangji/js/top.js'/>"></script>
<BODY>
	<DIV class="top">
		<B
			onclick="javascript:window.location.href=&#39;<c:url value='/redPack/personalCenter.html'/>&#39;"></B>
		<DD>提现明细</DD>
		<SPAN></SPAN>
		<UL>
			<LI class="index"
				onclick="javascript:window.location.href=&#39;<c:url value='/redPack/personalCenter.html'/>&#39;'">首
				页</LI>
			<LI class="member" onclick="">会员中心</LI>
			<LI class="shoppingCart" onclick="">购物车</LI>
		</UL>
	</DIV>
	<DIV class="main">
		<DIV class="tab_div">
			<DIV class="active">
				<UL>
				<c:forEach items="${withdrawList}" var="row" varStatus="status">
              
                  <li style="color: #000">${status.index + 1}、 
                 
							 <fmt:formatDate value="${row.createTime}"
											pattern="yyyy-MM-dd" />  	
											
                  	金额 : <font>${row.amount}</font>欧 
                  	现金 : <font>${row.payAmount}</font>元 
                  
                  	状态 :  <c:if test="${ row.payStatus == '1' }">  成功 </c:if>
                  	 <c:if test="${ row.payStatus != '1' }">  待处理 </c:if>
                  
                  
               
<%--                     <font>付款时间：<fmt:formatDate value="${row.payTime}" pattern="yyyy-MM-dd" />  </font> --%>
                 
                    
                </li>
            
				</c:forEach>
				</UL>
			</DIV>
		</DIV>
	</DIV>
</BODY>
</HTML>

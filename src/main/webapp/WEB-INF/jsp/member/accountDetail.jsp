<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en" class="heightFull">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=0">
<meta name="apple-touch-fullscreen" content="yes">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">


<title>账户明细</title>

<link href="<c:url value ='/res-kuangji/css/global.css'/>"
	rel="stylesheet" type="text/css" />
<link href="<c:url value ='/res-kuangji/css/redPacketRecords.css'/>"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="<c:url value ='/res-kuangji/js/jquery-2.1.1.min.js'/>"></script>
<script type="text/javascript"
	src="<c:url value ='/res-kuangji/js/top.js'/>"></script>
<style>
	.heightFull{
		height: 100%;
		overflow: hidden;
	}
	.displayFlex {
        display: flex;
        flex-direction: column;
        background: #191c23;
    }
	.top{
		background-color: #20222e;
	}
	.top ul{
		border: 1px solid #e5e5e5;
	}
	.top ul li{
	    width: initial;
		background: #191c23;
		border-top: 1px solid rgb(204, 204, 204); 
	}
	.top ul li:first-child{
		border-top: 0;
	}
	.ul-container{
		flex: 1;
		overflow-y: auto;
		color:#fff;
	}
	.tab_div div ul li{
		border-bottom-color: #1c1e2a;
	}
</style>
<BODY class="heightFull displayFlex">
	<DIV class="top">
		<B
			onclick="javascript:window.location.href=&#39;<c:url value='/redPack/personalCenter.html'/>&#39;"></B>
		<DD>账户明细</DD>
		<SPAN></SPAN>
		<UL>
			<LI class="index"
				onclick="javascript:window.location.href=&#39;<c:url value='/redPack/personalCenter.html'/>&#39;'">首
				页</LI>
			<LI class="member" onclick="">会员中心</LI>
			<LI class="shoppingCart" onclick="">购物车</LI>
		</UL>
	</DIV>
	<DIV class="main ul-container">
		<DIV class="tab_div">
			<DIV class="active">
				<UL>
				<c:forEach items="${accountDetail}" var="row" varStatus="status">
              
                  <li>${status.index + 1}、 
                    
					<fmt:formatDate value="${row.createTime}"
							pattern="yyyy-MM-dd" />  </font>   	
											
                  	<font>  ${row.remark}</font>   
                  	   
                </li>
            
				</c:forEach>
				</UL>
			</DIV>
		</DIV>
	</DIV>
</BODY>
</HTML>

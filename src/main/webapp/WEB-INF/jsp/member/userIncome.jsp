<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=0">
<meta name="apple-touch-fullscreen" content="yes">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">


<title>账户预览</title>


<link href="<c:url value ='/res-kuangji/css/global.css'/>"	rel="stylesheet" type="text/css">
<link href="<c:url value ='/res-kuangji/css/member_yzc.css'/>"	rel="stylesheet" type="text/css">
<link href="<c:url value ='/res-shichang/css/reset.css'/>"	rel="stylesheet" type="text/css">

<script type="text/javascript"	src="<c:url value ='/res-kuangji/js/jquery-2.1.1.min.js'/>"></script>
<script type="text/javascript"	src="<c:url value ='/res-kuangji/js/top.js'/>" used="1"></script>
<script type="text/javascript"	src="<c:url value ='/res-kuangji/js/member_yzc.js'/>"></script>

<style type="text/css">
		#container{
			width: 100%;
			height: 100%;
		    background: #191c23;
    		overflow: hidden;
    		display: flex;
    		flex-direction: column;
		}
		#tab{
			height: 4rem;
			background: #20222e;
			margin-top: .2rem;
    		overflow: hidden;
		}
		#tab ul{
			display: flex;
			height: 100%;
		}
		#tab ul li{
			flex: 1;
			font-size: 1.6rem;
    		color: #fff; 
    		text-align: center;
		    line-height: 3.5rem;
		}
		#tab .tab-select{
			width: 60%;
		    height: 95%;
	    	margin: 0 auto;
	    	color: #84888b;
	    	text-align: center;
    	    line-height: 4rem;
		}
		.tab-active .tab-select{
			color: #fbcb11 !important;
			border-bottom: 3px solid #fbcb11;
		}
		.tab-container{
			flex: 1;
			overflow-y: auto;
		}
		.tab-container-active{
			display: block !important;
		}
		.tab-container .tab-container-content{
			font-size: 1.4rem;
			color: #fff;
			color: #84888b;
			display: none;
			text-align: center;
		}
		.tab-container-content p{
			margin-top: 1rem;
		    margin-bottom: 6rem;
		    text-align: center;
		}
		.order{
		    width: 100%;
		    height: auto;
		    background: #20222e;
		    margin-bottom: 1.5rem;
		    padding: 1rem 0;
		    overflow: hidden;
		}
		.order:last-child{
			margin-bottom: 6rem;
		}

		.order .time{
		    height: 3rem;
		    text-align: right;
		    margin-right: 2rem;
		    font-size: 2.4rem;
		}
		.order table{
			width: 100%;
		}
		.order td {
			font-size: 1.8rem;
    		line-height: 3.5rem;
    		text-align: center;
		}
		.order .number{
			color: #fff;			
		}
		.order .number td{
			font-size: 2rem;
		}
		.order ul{
			margin-top: .5rem;
			display: flex;
			justify-content: center;
			align-items: center;
			height: 3rem
		}
		.order li{
			float: left;
		    font-size: 1.6rem;

		}
		.order .orderType{
			color: #000;
			padding: .4rem 1.2rem;
			border-radius: 3rem;
		}
		.order li:first-child{	
			padding-left: 2rem;	
			padding-right: 1rem;
    		line-height: 2rem;
		}
		.order li:nth-child(2){	
			flex: 1;
		    text-align: left;
		    padding: 0 1rem;
		}
		.order li a{				
			border-radius: 3px;
			padding: .4rem 1rem;
			margin-right: 1.8rem;
			line-height: 2rem;
		}
		.order li .orderState{
			color: #000;
			background: #fbcb11;
			border: 1px solid #fbcb11;
		}
		.order li .contactBuyer{
			color: #fbcb11;
			background: #fff;
			border: 1px solid #fbcb11;
		}
		.bgYellow{
			background: #f7cc01;
		}
		.bgOrange{
			background: #fb8106;
		}
		   
	</style>
	
</head>
<body>	
	
	<div id="container">
		
		<div class="tab-container">
			<div class="tab-container-content tab-container-active" data='0'>
			  	<c:forEach var="oneAccount" items="${userAccountList }">
				<div class="order">
					<table>
						<tr>
							<td>账户名</td>
							<td>数量</td>
							<td>单位</td>
						</tr>
						<tr class="number">
							<td>${oneAccount.accountName}</td>
							<td>${oneAccount.amount}</td>
							<td>${oneAccount.unit}</td>
						</tr>
					</table>
					<div>
						<ul>
							<c:forEach var="accountOption" items="${oneAccount.optLst }">
								<li onclick="doAccountOpt('${accountOption.opt}','${oneAccount.accountType}');"><span class="orderType bgYellow">${accountOption.optDesc}</span></li>
							</c:forEach>
							
						</ul>
					</div>
				</div>
			    </c:forEach>
			</div>
		</div>
	</div>

	<%@ include file="../include/foot_kangji.jsp"%>

</body>
</html>
<script type="text/javascript">
	$(".tab").find('li').on('click', function(){
		$(".tab").find('li').removeClass('tab-active')
		$(this).addClass('tab-active')
		var index = $(this).attr('data')

		$(".tab-container").find('div').removeClass('tab-container-active')
		$.each($(".tab-container").find('div'), function(idx, data){
			if(index == $(data).attr('data')){
				$(data).addClass('tab-container-active')
			}
		})
	})
	
	function doAccountOpt(opt,accountCode){
		//查看明细
		if("viewDetail" == opt){
			window.location.href ="<c:url value='/member/accountDetail.html?accountType='/>"+accountCode;
			return;
		}
		
		//转让
		if("zhuanrang" == opt){
			window.location.href ="<c:url value='/member/transJifeng.html?accountType='/>"+accountCode;
			return;
		}
		//提现
		if("tixian" == opt){
			window.location.href ="<c:url value='/member/withdraw.html?accountType='/>"+accountCode;
			return;
		}
	}
			
</script>
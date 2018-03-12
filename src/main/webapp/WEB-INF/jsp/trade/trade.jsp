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


<title>市场</title>


<link href="<c:url value ='/res-kuangji/css/global.css'/>"	rel="stylesheet" type="text/css">
<link href="<c:url value ='/res-kuangji/css/member_yzc.css'/>"	rel="stylesheet" type="text/css">
<link href="<c:url value ='/res-shichang/css/reset.css'/>"	rel="stylesheet" type="text/css">

<link href='<c:url value="/res/js/plugins/modal/modal.css?"/>${jsversion}' type="text/css" rel="stylesheet">
<script type="text/javascript" src='<c:url value="/res/js/global.js?v="/>${jsversion}' ></script>
<script type="text/javascript" src='<c:url value="/res/js/plugins/modal/modal.js?v="/>${jsversion}'></script>

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
		.head{
			height: 4rem;
			background: #20222e;
		}
		.title{
			color: #fff;
			width: 100%;
			height: 100%;
			text-align: center;
			font-size: 2rem;
			line-height: 4rem;
		}
		.title span{
			line-height: 4rem;
			position: absolute;
    		right: 2rem;
		}
		.title img{
			width: 1.5rem;
    		height: 1.5rem;
			position: relative;
    		right: .5rem;
		}
		.price{
			height: 4.5rem;
			background: #20222e;
		}
		.price table{
			width: 100%;
			height: 2rem;
		}
		.price td{
			font-size: 1.6rem;
			color: #fff;
			line-height: 2rem;
			text-align: center;
		}
		.price .rowspan{
			vertical-align:middle;
		    border-right: 1px solid #e1e4eb;
		}
		.price .label{
		    float: left;
		    position: relative;
		    left: 3rem;
		}
		.price .money{
		    float: right;
		    position: relative;
		    right: 3rem;
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
			width: 50%;
		    height: 95%;
	    	margin: 0 auto;
	    	color: #84888b;
	    	line-height: 4rem;
	    	text-align: center;
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
		    height: 11rem;
		    background: #20222e;
		    margin-top: 1.5rem;
		    overflow: hidden;
		}
		.order table{
			width: 100%;
		}
		.order td {
			font-size: 2rem;
    		line-height: 3.5rem;
    		text-align: center;
		}
		.order .number{
			color: #fff;			
		}
		.order .number td{
			font-size: 2.8rem;
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
		.order li:first-child{	
			padding-left: 2rem;	
			padding-right: 1rem;
    		border-right: 1px solid #fff;
    		line-height: 2rem;
		}
		.order li:nth-child(2){	
			flex: 1;
		    text-align: left;
		    padding: 0 1rem;
		}
		.order li a{	
			color: #fbcb11;
			border: 1px solid #fbcb11;
			border-radius: 3px;
			padding: .4rem 1rem;
			margin-right: 2rem;
			line-height: 2rem;
		}	   
	</style>
	
</head>
<body>	
	
	<div id="container">
		<div class="head">
			<div class="title">市场<span id="guadang"><img src="<c:url value ='/res-kuangji/images/goodslogo.png'/>" alt="" >挂单</span></div>
		</div>
		<div class="price">
			<table>
				<tbody>				
					<tr>
						<td rowspan="2" class="rowspan"><span class="label">指导价</span><span class="money">$${basePrice}</span></td>
						<td><span class="label">最高价</span><span class="money">$${upPrice}</span></td>
					</tr>
					<tr>					
						<td><span class="label">最低价</span><span class="money">$${basePrice}</span></td>
					</tr>
				</tbody>
			</table>
		</div>
		<div id="tab">
			<ul class="tab">
				<li class="tab-active" data='0'>
					<div class="tab-select">买单市场</div>
				</li>
				<li data='1'>
					<div class="tab-select">卖单市场</div>
				</li>
			</ul>
		</div>
		<div class="tab-container">
			<div class="tab-container-content tab-container-active" data='0'>
				<c:forEach items="${buyBills}" var="bill">
				<div class="order">
					<table>
						<tr>
							<td>数量(个)</td>
							<td>价格($)</td>
							<td>总计($)</td>
						</tr>
						
						<tr class="number">
							<td>${bill.qty }</td>
							<td>${bill.price }</td>
							<td>${bill.totalPrice }</td>
						</tr>
					</table>
					<div>
						<ul>
							<li></li>
							<li>${bill.createTime }</li>
							<li><a href="javascript:doBuy(${bill.orderId });">可匹配</a></li>
						</ul>
					</div>
				</div>
				</c:forEach>
				<p>没有更多数据了</p>
			</div>
			
			<div class="tab-container-content" data='1'>
				
				<c:forEach items="${saleBills}" var="bill">
				<div class="order">
					<table>
						<tr>
							<td>数量(个)</td>
							<td>价格($)</td>
							<td>总计($)</td>
						</tr>
						
						<tr class="number">
							<td>${bill.qty }</td>
							<td>${bill.price }</td>
							<td>${bill.totalPrice }</td>
						</tr>
					</table>
					<div>
						<ul>
							<li></li>
							<li>${bill.createTime }</li>
							<li><a href="javascript:doBuy(${bill.orderId },this);">可匹配</a></li>
						</ul>
					</div>
				</div>
				</c:forEach>
				<p>没有更多数据了</p>
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
	
	
	$("#guadang").on('click', function(){
		window.location.href="<c:url value ='/trade/guadang.html'/>";
	})
	
	
	function doBuy(orderId,obj){
		
		HHN.popupConfirm("你确定要匹配?", 
                function(){return true;}, 
                function(){submitSave(orderId,obj); 
                         return true;});
	}
	
	
	
	//提交信息
    function submitSave(orderId,obj){
    	var param ={"orderId":orderId};
    	$.post('<c:url value="/trade/matchOrder.html"/>', param, function(data) {
    		if(data.resultCode=="0"){
    			HHN.popup("匹配成功");
    			$(obj).text("匹配成功");    			
    			setTimeout(function(){
    				window.location.href="<c:url value ='/trade/trade.html'/>";
				},1500);
    			
			}else{
				HHN.popup(data.errorMessage);
			}
    		
		},"json");
    }
	
</script>
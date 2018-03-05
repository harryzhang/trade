<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=0">
        <meta name="apple-touch-fullscreen" content="yes">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="apple-mobile-web-app-status-bar-style" content="black">
        <meta name="format-detection" content="telephone=no">


        <title>商品竞拍</title>

        <link href="<c:url value ='/res-kuangji/css/global.css'/>" rel="stylesheet" type="text/css" />
        <link href="<c:url value ='/res-kuangji/css/goodsDetails.css'/>" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="<c:url value ='/res-kuangji/js/jquery-2.1.1.min.js'/>"></script>


        <script type="text/javascript" src="<c:url value ='/res-kuangji/js/top.js'/>"></script>
		<script type="text/javascript" src="<c:url value ='/res-kuangji/js/TouchSlide.1.1.js'/>"></script>
		<script type="text/javascript" src="<c:url value ='/res-kuangji/js/goodsDetails.js'/>"></script>
		<script type="text/javascript" src="<c:url value ='/res-kuangji/js/cart_order.js'/>"></script>
<style type="text/css">
.tz_btn {
	width: 80px;
	height: 30px;
	background: red;
	line-height: 30px;
	display: inline-block;
	margin: 20px 0 10px 0;
	text-align:center;
	/* padding-top:10px; */
	font-size: 100%;
	color: #000;
	border-radius: 8px;
	font-weight: bold;
}
.price_btn {
	width: 80px;
	height: 30px;
	background: #FF9800;
	line-height: 30px;
	display: inline-block;
	margin: 20px 0 10px 0;
	text-align:center;
	/* padding-top:10px; */
	font-size: 100%;
	color: red;
	border-radius: 8px;
	font-weight: bold;
}
.goodstable {
	background: blue;
	
}
.goodstable tbody tr td {
	color:#fff
	
}

</style>
    </head>
    <body>
    <div class="top">
		<b onclick="self.location=document.referrer"></b>
		<dd>竞拍详情</dd>
	</div>
	<!-- 
    <div class="blank"></div>
     -->
    <div class="goodsInfo">
    	    <div>   
         	<p style="padding-top:8px;height:30px;  text-align: center; color:#FF9800;background: blue"><b>竞拍说明</b></p>
         	<p style="color:#000;padding:5px 15px">商品起拍价格1.00积分</p>
         	<p style="color:#000;padding:5px 15px">每竞价一次商品当前价格上涨0.01积分！</p>
         	<p style="color:#000;padding:5px 15px">60秒，无人以更高价格竞价时成交！</p>
            </div>  
            
       
        <div class="infos">
            <div class="goodsDetail" style="min-height: 666px;">
               	<c:forEach items="${jingpaiList}" var="row" varStatus="status">
               	<p style="padding-top:5px;height:30px;  text-align: center; color:black;background: #FF9800"><b style="float:left;color:red"> 共两个竞拍商品 </b>竞拍品:${status.index + 1}</p>
                <table cellpadding="0" cellspacing="0" class="goodstable">
                    <tbody>
                    <tr >
                        <td>商品名称：${row.gName}</td>
                        <td>最高竞价：${row.endPrice} 积分</td>
                    </tr>
                    <tr>
                        <td>市场价格：${row.price}元</td>
                        <td>竞拍增幅：0.01</td>
                    </tr>
                    <tr>
                        <td  >当前价格：<b id= "price${row.goodsId}">${row.lastPrice}</b></td>
                        <td>竞拍会员：<b id= "name${row.goodsId}"> ${row.lastUser}</b></td>
                    </tr>
                </tbody>
                </table>
                <p style="text-align: center"><a href="#"   
                 id="price_btn${row.goodsId}" class="price_btn" style="color:red" >${row.lastPrice}</a>
                 <a href="#" onclick="xiazhu(${row.goodsId})"  class="tz_btn">立即竞价</a></p>
                
                <hr>
                
                <p>
                	<img src="<c:url value ='/res-kuangji/goodsImg/${row.bigImageSrc}'/>" title="${goods.gname}" >
                </p>
                <p style="color:#333;margin-top: 20px;margin-bottom: 20px;">
                	&nbsp;&nbsp;&nbsp;&nbsp;${row.gdesc}
                </p>
                
				</c:forEach>
            </div>
            <img width="100%" height="100%"  src="<c:url value ='/res-kuangji/goodsImg/P_about.jpg'/>" title="拍卖需知" >
        </div>
    </div>
    
<div class="backtop" style="display: block;">
    <a href="javascript:scroll(0,0)" hidefocus="true"></a>
</div>
<script>
	 function xiazhu(goodsId){
		// var price = parent.document.all['price' + goodsId].innerHTML;
		// var nowprice = parseFloat(price) + 0.01;
		// parent.document.all['price' + goodsId].innerHTML = nowprice.toFixed(2);
		// parent.document.all['price_btn' + goodsId].innerHTML = nowprice.toFixed(2);
		 var param =   {'goodsId' : goodsId}; 
		$.post('<c:url value="/userBet/addJingpai.html"/>', param, function(data) {
				if(data.result=='成功'){
					alert("竞拍成功！竞拍价格是："  + data.lastPrice)
					 var nowprice = parseFloat(data.lastPrice) + 0.01;
					 parent.document.all['price' + goodsId].innerHTML = nowprice.toFixed(2);
					 parent.document.all['price_btn' + goodsId].innerHTML = nowprice.toFixed(2);
					 parent.document.all['name' + goodsId].innerHTML =data.name;
				}else{
					alert(data.result)
				}
		},"json");
	 }

		function getJingpaiPrice() {
			 $.post('<c:url value="/jingcai/getJingpai.html"/>', null, function(data) {
				if(data.result=='成功'){
					 var goodsObject = data.jingpaiList;
					 var goodsList = eval(goodsObject); 
					 if(goodsList){
						 for(var key in goodsList){ //第一层循环取到各个list 
							 var goods = goodsList[key]; 
							 var price = goods.lastPrice;
							 var jingpaiStatus = goods.status;
							 parent.document.all['price' + goods.goodsId].innerHTML = price;
							 parent.document.all['price_btn' + goods.goodsId].innerHTML = price;
							 
							 if('2'== jingpaiStatus){
								 parent.document.all['name' + goods.goodsId].innerHTML =  goods.lastUser+'恭喜竞拍成功！';
							}else{
								 parent.document.all['name' + goods.goodsId].innerHTML = goods.lastUser;
							}
						} 
					 }
				}
			},"json"); 
		}
		newstimer = setInterval("getJingpaiPrice()", 3000)
</script>
</body>
</html>
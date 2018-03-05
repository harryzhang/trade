<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=0">
        <meta name="apple-touch-fullscreen" content="yes">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="apple-mobile-web-app-status-bar-style" content="black">
        <meta name="format-detection" content="telephone=no">


        <title>购物车</title>

		<link href="<c:url value ='/res-kuangji/css/global.css'/>" rel="stylesheet" type="text/css">
        <link href="<c:url value ='/res-kuangji/css/shoppingCart.css'/>" rel="stylesheet" type="text/css">
        <script type="text/javascript" src="<c:url value ='/res-kuangji/js/jquery-2.1.1.min.js'/>"></script>
        <script type="text/javascript" src="<c:url value ='/res-kuangji/js/top.js'/>"></script>
		<script type="text/javascript" src="<c:url value ='/res-kuangji/js/shoppingCart.js'/>"></script>
		
		
		
	<script type="text/javascript">
	   	function cart(){
	   		$.ajax({
	   		    type: "POST",
	   		            url: "<c:url value='/order/submitOrder.html'/>",
	   		            data: {ckgoods: ${recIds}},
	   		            dataType: "json",
	   		            success: function (res) {
	   			            if (res) {
	   				            if (res.err_msg.length == 0){
	   				            	alert('操作成功');
	   				            	window.location.href="<c:url value='/order/success.html'/>";
	   				            } else {
	   				            	window.location.href="<c:url value='/order/noMoney.html'/>";
	   				            }
	   			            }
	   		            }
	   		    });
		}
	</script>

    </head>
    <body style="color:#333">
        
 <div class="top">
    	<b
			onclick="javascript:window.location.href=&#39;<c:url value='/redPack/personalCenter.html'/>&#39;"></b>
		<dd>购物车</dd>
   		 <span></span>

</div>



<div class="main">
    
    
    <div class="order">
        <div class="odtop">
            <li> </li>
            <dd class="edit"></dd>			
        </div>

        <div class="goods">
            <div class="goodsPic">
                <!-- 
                <img src="<c:url value ='/res-kuangji/images/339_thumb_G_1469070102895.jpg'/>">
                 -->
            </div>
            <div class="goodsInfo" style="height:auto">
                <dd class="gname">支付金额:<span>￥<fmt:formatNumber  value="${totalMoney}" pattern="##,###,###.00"/></span></dd>
                <dd class="gname">
                                                                 数量:<font>${qty}股</font>
                </dd>
                <dd class="gname">
                <span>支付方式：欧元</span>
                </dd>
                
            </div>
        </div>	
    </div>


</div>
<%--
<div class="notice" style="top: 373.2px;">删除成功</div>
 --%>
<div class="total">
   <%--
    <div class="all">
        <input name="a" type="checkbox">
        <label for="a">全选</label>
    </div>
    <div class="tprice">
        <dd class="heji">合计：<span id="totalAmount">0</span></dd>
        <!--<dd class="freight">不含运费</dd>-->
        <dd id="totalRes" class="freight"></dd>
    </div>
     --%>
    <input class="jsbtn" type="button" value="完成" onclick="cart()">
</div>




<div class="footer">
	
    <dl onclick="javascript:window.location.href =&#39;<c:url value='/firstpage/toFirstpage.html'/>&#39;">
         <dt></dt>
        <dd>首页</dd>
    </dl>
    
     <dl  onclick="javascript:window.location.href =&#39;<c:url value='/redPack/personalCenter.html'/>&#39;">
         <dt></dt>
        <dd>个人中心</dd>
    </dl>
    <%--
    <dl onclick="javascript:window.location.href =&#39;<c:url value='/goods/select.html'/>&#39;">
         <dt></dt>
        <dd>商品</dd>
    </dl>
    <dl class="cur" onclick="javascript:window.location.href =&#39;<c:url value='/cart/cart.html'/>&#39;">
          <dt></dt>
        <dd>购物车</dd>
    </dl>
     --%>
</div>

<div class="mengban" style="height: 622px;"></div>




    


</body></html>
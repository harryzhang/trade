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


        <title>商品详情</title>

        <link href="<c:url value ='/res-kuangji/css/global.css'/>" rel="stylesheet" type="text/css" />
        <link href="<c:url value ='/res-kuangji/css/goodsDetails.css'/>" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="<c:url value ='/res-kuangji/js/jquery-2.1.1.min.js'/>"></script>


        <script type="text/javascript" src="<c:url value ='/res-kuangji/js/top.js'/>"></script>
		<script type="text/javascript" src="<c:url value ='/res-kuangji/js/TouchSlide.1.1.js'/>"></script>
		<script type="text/javascript" src="<c:url value ='/res-kuangji/js/goodsDetails.js'/>"></script>
		<script type="text/javascript" src="<c:url value ='/res-kuangji/js/cart_order.js'/>"></script>

    </head>
    <body>
    <div class="top">
		<b onclick="self.location=document.referrer"></b>
		<dd>商品详情</dd>
	</div>
	<!-- 
    <div class="blank"></div>
     -->
    <div class="goodsInfo">
    	 <!--        
        <ul>
            <li class="selected"><a href="javascript:;">商品详情</a></li>
	    	<li><a href="#show">晒单分享</a></li>
			<li><a href="#records">购买记录</a></li>
        </ul>
        -->
        <div class="infos">
            <div class="goodsDetail" style="min-height: 666px;">
                <h4 id="goodsDetail" style="display: none;"></h4>
                <table cellpadding="0" cellspacing="0">
                    <tbody>
                    <tr>
                        <td>品名：${goods.gname}</td>
                        <td>积分：${goods.price}</td>
                    </tr>
                    <tr>
                        <td>品牌：${goods.bandName}
                        </td>
                        <td>类别:
                        	<c:choose>
                        		<c:when test="${goods.gcate eq '1' }">服装</c:when>
                        		<c:when test="${goods.gcate eq '2' }">手袋</c:when>
                        		<c:when test="${goods.gcate eq '3' }">饰品</c:when>
                        		<c:when test="${goods.gcate eq '4' }">玉器</c:when>
                        		<c:otherwise>其他</c:otherwise>
                        	</c:choose>
                        </td>
                    </tr>
                </tbody>
                </table>
                <hr>

                <p style="color:#333;margin-top: 20px;margin-bottom: 20px;">
                	${goods.gdesc}
                </p>
                <p>
                	<img src="<c:url value ='/res-kuangji/goodsImg/images/${goods.bigImageSrc}'/>" title="${goods.gname}" >
                </p> 

            </div>
            <div class="show" style="min-height: 666px;">
                <h4 id="show" style="display: none;"></h4>

                            </div>
            <div class="records" style="min-height: 666px;">
                <h4 id="records" style="display: none;"></h4>

                            </div>	
        </div>
    </div>
    
	<div class="caozuo">
	
	    <ul>
	        <li>
	            <dl class="add">
	                <dt></dt>
	                <dd>加入购物车</dd>
	            </dl>
	        </li>
	        <li>
	            <input class="buy" type="button" value="立即购买">
	        </li>
	    </ul>	
	</div>
<div class="backtop" style="display: block;">
    <a href="javascript:scroll(0,0)" hidefocus="true"></a>
</div>
<form action="" method="post" name="ECS_FORMBUY" id="ECS_FORMBUY">
    <div class="shopping" style="color:#333;">
        <img class="gb" src="<c:url value ='/res-kuangji/images/close_01.png'/>">
        <div class="xzitems">
            <div class="shnum">
                <label>购买数量</label>
                <dd>
                    <span class="jian">－</span>
                    <input name="number" type="text" id="number" value="1" onkeyup="if (this.value.length == 1){this.value = this.value.replace(/[^1-9]/g, &#39;&#39;)} else{this.value = this.value.replace(/\D/g, &#39;&#39;)}; goods_num_ty();" onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,&#39;0&#39;)}else{this.value=this.value.replace(/\D/g,&#39;&#39;)}">
                    <span class="add">＋</span>
                </dd>
            </div>
        </div>	
        <div class="pay">
        	<!-- 
            <dd class="d">合计：<span id="ECS_GOODS_AMOUNT">购物积分450</span></dd>
             -->
             <dd class="d"><span id="ECS_GOODS_AMOUNT"></span></dd>
            <input type="hidden" name="btype" id="btype" value="1">
            <input type="button" onclick="javascript:addToCart(${goods.goodsId})" value="确定">
        </div>
    </div>
    <div class="notice" style="top: 310.5px;"></div>
</form>
<script>
    function goods_num_ty(){
	    var num = $("#number").val();
	    var shengyu = "1000";
	    if (parseInt(num) > parseInt(shengyu)){
		    alert("您的购买数量超出库存！");
		    $("#number").val(shengyu);
		    return;
	    } else if (parseInt(num) <= 0){
	    	$("#number").val(1);
	    }	
	    changePrice();
    }
    
    $(function () {
	    var count = 3;
	    var countdown = setInterval(CountDown, 1000);
	    function CountDown() {
		    $("#goods_countdown").html(count);
		    if (count == 0) {
		    $("#goods_countdown").hide();
		    $("#goods_next").show();
		    clearInterval(countdown);
		    }
		    count--;
	    }
    });
    
    $(document).ready(function () {
    	changePrice();
    });
    
    $(document).ready(function(){
		//加的效果
	    $(".add").click(function(){
		    var n = $(this).prev().val();
		    var num = parseInt(n) + 1;
		    if (num == 0){ return; }
		    var shengyu = "1000";
		    if (num > parseInt(shengyu)){
			    alert("您的购买数量超出库存！");
			    num = parseInt(num) - 1;
			    $(this).prev().val(num);
			    return;
		    }
		    $(this).prev().val(num);
		    changePrice();
	    });
		//减的效果
	    $(".jian").click(function(){
		    var n = $(this).next().val();
		    var num = parseInt(n) - 1;
		    if (num == 0){ return}
		    $(this).next().val(num);
		    changePrice();
	    });
    })

	    /**
	     * 点选可选属性或改变数量时修改商品价格的函数
	     */
		function changePrice(){

		    var id = arguments[0] ? arguments[0] : 0;
		    if (id){
			    $("#attr_" + id).siblings().removeClass('selected');
			    $("#attr_" + id).addClass('selected');
		    }

		    var attr = getSelectedAttributes(document.forms['ECS_FORMBUY']);
		    var qty = document.forms['ECS_FORMBUY'].elements['number'].value;
		    qty = !isNaN(qty)?parseInt(qty):1;
		    var token = "ZvL0EsRqGaHp6eIMAedm7QqkKFohqpu5KDMH77cP";
		    var goodsId = "334";
		    $.ajax({
		    type: "POST",
			    url: "<c:url value='/cart/price.html'/>",
			    data: {id: goodsId, _token: token, attr:attr, number:qty},
			    dataType: "json",
			    success: function (res) {

			    changePriceResponse(res);
			    }
		    });
		}

    
	    /* *
	     * 添加商品到购物车 
	     */
	    function addToCart(goodsId, parentId){
		    var goods = new Object();
		    var spec_arr = new Array();
		    var fittings_arr = new Array();
		    var number = 1;
		    var formBuy = document.forms['ECS_FORMBUY'];
		    var quick = 0;
			// 检查是否有商品规格 
		    if (formBuy){
			    spec_arr = getSelectedAttributes(formBuy);
			    if (formBuy.elements['number']){
			    	number = formBuy.elements['number'].value;
			    }		
			    quick = 1;
		    }
	
		    goods.quick = quick;
		    goods.spec = spec_arr;
		    goods.goods_id = goodsId;
		    goods.number = number;
		    goods.parent = (typeof (parentId) == "undefined") ? 0 : parseInt(parentId);
		    var btype = $("#btype").val();
		    var token = "ZvL0EsRqGaHp6eIMAedm7QqkKFohqpu5KDMH77cP";
		    window.location.href = "<c:url value='/cart/payJiFeng.html'/>?ckgoods="+goodsId+"&qty="+number;
	    }

	    /* *
	     * 处理添加商品到购物车的反馈信息
	     */
	    function addToCartResponse(result, t){

		    if (result.error > 0){
				// 如果需要缺货登记，跳转
			    // openSpeDiv(result.message, result.goods_id, result.parent);
			    
			    $('.shopping').hide();
			    $('.mengban').hide();
			    $('.notice').html(result.message).show().fadeOut(2500);
			    return false;
		    }else{
			    if (t == 1){
				    $('.shopping').hide();
				    $('.mengban').hide();
				    $('.notice').html('已加入购物车').show().fadeOut(2500);
			    } else{
			    	location.href = "<c:url value='/cart/addToCart.html'/>";
			    }
		   
		    }
	    }
</script>
</body>
</html>
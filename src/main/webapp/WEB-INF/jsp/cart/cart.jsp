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
		
		
	<%--	
<script>
    $(document).ready(function () {
    getCartTotal();
    })
            function getCartTotal() {

            var rec_ids = '';
            $("input[name^='ckgoods']:checkbox").each(function () {
            if ($(this).hasClass('active')) {
            rec_ids += $(this).val() + ",";
            }

            });
           
            if (!rec_ids) {
                 $("#totalAmount").html(0);
            return false;
            }
            var token = "ZvL0EsRqGaHp6eIMAedm7QqkKFohqpu5KDMH77cP";
            $.ajax({
            type: "POST",
                    url: "http://yingzc.net/mobile/getCartGoods",
                    data: {goods: rec_ids, _token: token},
                    dataType: "json",
                    success: function (res) {
                    if (res) {
                    if (res.err_msg.length == 0)
                    {
                    $("#totalAmount").html(res.result);
                    $("#totalRes").html(res.res);
                    } else {
                    alert('操作失败');
                    }
                    }
                    }
            });
            }
    function cart() {
    var rs = '';
    $("input[name^='ckgoods']:checkbox").each(function () {
    if ($(this).hasClass('active')) {
    rs += $(this).val() + ",";
    }


    });
    if (rs == '') {
    alert('请选择要购买的商品');
    return false;
    } else {
// $("#formCart").submit();


    location.href = "<c:url value='/cart/jiesuan.html'/>" + "?ckgoods=" + rs;
    }
    }

    function delCartGoods(e, rec_id) {
    var token = "ZvL0EsRqGaHp6eIMAedm7QqkKFohqpu5KDMH77cP";
    $.ajax({
    type: "POST",
            url: "http://yingzc.net/mobile/delCartGoods",
            data: {goods: rec_id, _token: token},
            dataType: "json",
            success: function (res) {
            if (res)
            {
            var glen = $(e).parents('.order').children('.goods').size();
            if (glen > 1) {
            $(e).parents('.goods').remove();
            } else {
            $(e).parents('.order').remove();
            }
            $('.notice').show().fadeOut(2000, function(){
            if ($('.main').find('div').filter('.order').length <= 0)
            {
                  $(".total").hide();
            $(".main").prepend("<a href=http://yingzc.net/mobile/index ><dl class='default'><dt><img src='http://yingzc.net/assets/mobile/img/noshopping.png' /></dt><dd>购物车空空如也~</dd><dd>快去逛逛吧！</dd></dl></a>");
          
            }
            });
            }
            }
    });
    return false;
    }

//计算是否超过库存
    function goods_num_ty(rec_id, e)
    {
    var num = isNaN($("#number" + rec_id).val())? 0 : $("#number" + rec_id).val() <= 0 ? 0 : $("#number" + rec_id).val();
    var shengyu = $(e).next("span").attr("name");
    if (parseInt(num) > parseInt(shengyu))
    {
    alert("您的购买数量超出库存！");
    $("#number" + rec_id).val(shengyu);
    return;
    } else if (parseInt(num) <= 0)
    {

    $("#number" + rec_id).val(1);
    }

    changeCartPrice(rec_id);
    }
//加的效果
    function  plus(rec_id, e) {
    if (!rec_id) {
    return false;
    }
    var n = $("#number" + rec_id).val();
    var num = parseInt(n) + 1;
    if (num == 0) {
    return;
    }
    if (num > parseInt($(e).attr("name"))){
    alert("您的购买数量超出库存！");
    return;
    }
    $("#number" + rec_id).val(num);
    changeCartPrice(rec_id);
    }
    ;
//减的效果
    function minus(rec_id, e) {
    if (!rec_id) {
    return false;
    }
    var n = $("#number" + rec_id).val();
    var num = parseInt(n) - 1;
    if (num == 0) {
    return
    }
    $("#number" + rec_id).val(num);
    changeCartPrice(rec_id);
    }


    /**
     * 点选可选属性或改变数量时修改商品价格的函数
     */
    function changeCartPrice(rec_id)
    {

    if (!rec_id) {
    return false;
    }
    var qty = !isNaN($('#number' + rec_id).val())?parseInt($('#number' + rec_id).val()):1;
    var token = "ZvL0EsRqGaHp6eIMAedm7QqkKFohqpu5KDMH77cP";
    $.ajax({
    type: "POST",
            url: "http://yingzc.net/mobile/changeCartPrice",
            data: {rec_id: rec_id, _token: token, number: qty},
            dataType: "json",
            success: function (res) {
            getCartTotal();
            changeCartPriceResponse(res);
            }
    });
    }
    /**
     * 接收返回的信息
     */
    function changeCartPriceResponse(res)
    {
    if (res.err_msg.length == 0)
    {
// alert(res.err_msg);
//   $('.shopping').hide();
//     $('.mengban').hide();
// $('.notice').html(res.message).show().fadeOut(2500);

    $("#number" + res.rec_id).val(res.qty);
    $("#number_cart" + res.rec_id).html("X" + res.qty);
//   document.forms['formCart'].elements['number'+res.rec_id].value = res.qty;
    /*   if (document.getElementById('ECS_GOODS_AMOUNT'))
     document.getElementById('ECS_GOODS_AMOUNT').innerHTML = res.result;*/
    }
    }
</script>
 --%>
    </head>
    <body>
        
 <div class="top">
    	<b
			onclick="javascript:window.location.href=&#39;<c:url value='/redPack/personalCenter.html'/>&#39;"></b>
		<dd>购物车</dd>
   		 <span></span>

</div>

<div class="main">
    <%--
    
    <div class="order">
        <div class="odtop">
<!--            <input name="a" type="checkbox" />
            <label for="a"></label>-->
            <li> </li>
            <dd class="edit">编辑</dd>			
        </div>

        <div class="goods">
            <input name="ckgoods[]" type="checkbox" value="339">
            <label for="ckgoods[]"></label>
            <div class="goodsPic">
                <img src="<c:url value ='/res-kuangji/images/339_thumb_G_1469070102895.jpg'/>">
            </div>
            <div class="goodsInfo">
                <dd class="gname">薏米杏仁粉</dd>
<!--                <dd class="gstyle"></dd>-->
                <dd class="gprice">
                    <span>￥0.00</span>
                    <font id="number_cart10127">X1</font>
                    <span>  购物积分:450</span>
                </dd>
            </div>
            <div class="modifyInfo">
                <dd class="gstyle"></dd>
                <dd class="caozuo">
                    <div class="gnumber">
                        <span name="1000" onclick="minus(10127, this)">－</span>
                        <input type="text" value="1" name="number10127" id="number10127" onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,&#39;&#39;)}else{this.value=this.value.replace(/\D/g,&#39;&#39;)}; goods_num_ty(10127, this);" onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,&#39;0&#39;)}else{this.value=this.value.replace(/\D/g,&#39;&#39;)}">
                        <span name="1000" onclick="plus(10127, this)">＋</span>
                    </div>
                    <input class="delbtn" type="image" src="<c:url value ='/res-kuangji/images/deletebg.png'/>" onclick="delCartGoods(this,10127)">
                </dd>
            </div>
        </div>	


    </div>



    




 --%>

</div>

<%--
<div class="notice" style="top: 373.2px;">删除成功</div>
<div class="total">
    <div class="all">
        <input name="a" type="checkbox">
        <label for="a">全选</label>
    </div>
    <div class="tprice">
        <dd class="heji">合计：<span id="totalAmount">0</span></dd>
        <!--<dd class="freight">不含运费</dd>-->
        <dd id="totalRes" class="freight"></dd>
    </div>
    <input class="jsbtn" type="button" value="去结算" onclick="cart()">
</div>
 --%>


<%@ include file="../include/foot_kangji.jsp"%>

<div class="mengban" style="height: 622px;"></div>




    


</body></html>
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
        



    <div id="banner" class="focus">
        <div class="top">
			<b onclick="self.location=document.referrer"></b>
			<span></span>
	        <ul>
	            <li class="index" onclick="javascript:window.location.href=&#39;<c:url value='/firstpage/toFirstpage.html'/>&#39;">首 页</li>
	            <li class="member" onclick="javascript:window.location.href=&#39;<c:url value='/redPack/personalCenter.html'/>&#39;">会员中心</li>
	            <li class="shoppingCart" onclick="javascript:window.location.href=&#39;<c:url value='/cart/cart.html'/>&#39;">购物车</li>
	        </ul>
		</div>

        <div class="hd">
            <ul><li class="">1</li><li class="on">2</li><li class="">3</li></ul>
        </div>
        <div class="bd">
            <div class="tempWrap" style="overflow:hidden; position:relative;">
	            <ul style="width: 4089px; position: relative; overflow: hidden; padding: 0px; margin: 0px; transition-duration: 0ms; transform: translate(-1363px, 0px) translateZ(0px);">
	                    <li style="display: table-cell; vertical-align: top; width: 1363px;">
	                    	<img src="<c:url value ='/res-kuangji/images/334_G_1469068420209.jpg'/>">
	                    </li>
	                    <li style="display: table-cell; vertical-align: top; width: 1363px;">
	                    	<img src="<c:url value ='/res-kuangji/images/334_P_1469068426251.jpg'/>">
	                    </li>
	                    <li style="display: table-cell; vertical-align: top; width: 1363px;">
	                    	<img src="<c:url value ='/res-kuangji/images/334_P_1469068428282.jpg'/>">
	                    </li>
	                    <li>
	                    	<img _src="http://image.yutu365.org/images/201607/thumb_img/334_thumb_G_1469068422874.jpg" src="http://image.yutu365.org/images/201607/thumb_img/334_thumb_G_1469068422874.jpg" />
	                    </li>
	            </ul>
            </div>
        </div>
    </div>
    
    <div class="goods">
        <dl>
            <dd class="gname">小麦胚芽</dd>
            <dd class="description">本产品包邮</dd>
        </dl>
        <div class="general">
            <ul>
                <li>
		    		    <font>购物积分450</font>
		                        <!--<del>市场价￥489</del>    -->              
                </li>
                <li>
                    <del>市场价￥489</del>
		    		    <font>已售0件</font> 
		                    </li>
            </ul>
        </div>


    </div>

    <div class="blank"></div>
    <div class="goodsInfo">
        <ul>
            <li class="selected"><a href="http://yingzc.net/mobile/goodsDetail/334#goodsDetail">商品详情</a></li>
	    <!--            <li><a href="#show">晒单分享</a></li>
			<li><a href="#records">购买记录</a></li>-->
        </ul>
        <div class="infos">
            <div class="goodsDetail" style="min-height: 666px;">
                <h4 id="goodsDetail" style="display: none;"></h4>
                <table cellpadding="0" cellspacing="0">
                    <tbody><tr>
                        <td>品牌：五谷养生</td>
                        <td>编号：GN0000000334</td>
                    </tr>
                    <tr>
                        <td>分类：五谷养生</td>
                        <td>重量：


                            600 克


                        </td>
                    </tr>
                </tbody></table>
                <hr>

                <p>
                	<img src="<c:url value ='/res-kuangji/images/1469068372714240.jpeg'/>" title="1469068372714240.jpeg" alt="404221188874176953.jpg">
                	<img src="<c:url value ='/res-kuangji/images/1469068381343098.jpeg'/>" title="1469068381343098.jpeg" alt="xy00003.jpg">
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


<!--同城且可换购的商品尾部浮条结束-->
<!--普通商品尾部浮条开始-->
<div class="caozuo">

    <ul>
        <li>
	    <!--            			<dl class="collection" onClick="collection()">
			    <dt></dt>
			    <dd>收藏</dd>
			</dl>
			-->
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
<!--普通商品尾部浮条结束--><!--wzx 2016.01.07-->
<!--增加换购币尾部浮条结束-->

<!--请登录尾部浮条开始-->
<!--<div class="login"><input type="button" value="立即登录" onclick="javascript:window.location.href = 'http://yingzc.net/auth/login'" /></div>-->
<!--请登录尾部浮条结束-->

<div class="mengban" style="height: 666px;"></div>
<!--<div class="tanceng">
    <img class="close" src="http://yingzc.net/assets/mobile/img/close_01.png" />
    <p>电话：</p>
    <input type="button" value="立即呼叫" onClick="" />
</div>-->
<!--<div class="next">
        <a href="http://yingzc.net/mobile/goodsDetail/333" id="goods_next"><img src="http://yingzc.net/assets/mobile/img/nextbtn.png" /></a>
        <b id="goods_countdown">3</b>
</div>-->
<!--<div class="share">
    <a href="#"><img src="http://yingzc.net/assets/mobile/img/sharebtn.png" /></a>
</div>-->
<div class="backtop" style="display: block;">
    <a href="javascript:scroll(0,0)" hidefocus="true"></a>
</div>
<form action="" method="post" name="ECS_FORMBUY" id="ECS_FORMBUY">
    <div class="shopping">
        <img class="gb" src="<c:url value ='/res-kuangji/images/close_01.png'/>">
        <dl>
            <dt><img src="<c:url value ='/res-kuangji/images/334_thumb_G_1469068422874.jpg'/>"></dt>
	    	    <dd class="shfl" style="font-size: 12px;"> 购物积分：450</dd>
	    
            <dd class="inventory">库存：1000件</dd>
        </dl>
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
            <dd class="d">合计：<span id="ECS_GOODS_AMOUNT">购物积分450</span></dd>
            <input type="hidden" name="btype" id="btype" value="1">
            <input type="button" onclick="javascript:addToCart(334)" value="确定">
        </div>
    </div>
    <!--    <div class="tanchuang">
            <img src="http://yingzc.net/assets/mobile/img/rpbg.png" />
            <li>广州箱包旗舰店</li>
            <dt>￥<span>200.00</span></dt>
            <dd>商家红包</dd>
        </div>-->
    <div class="notice" style="top: 310.5px;"></div>
</form>
<script>
    function goods_num_ty()
    {
    var num = $("#number").val();
    var shengyu = "1000";
    if (parseInt(num) > parseInt(shengyu))
    {
    alert("您的购买数量超出库存！");
    $("#number").val(shengyu);
    return;
    } else if (parseInt(num) <= 0)
    {
    $("#number").val(1);
    }

    changePrice();
    }
    function collection(){
    var token = "ZvL0EsRqGaHp6eIMAedm7QqkKFohqpu5KDMH77cP";
    var goodsId = "334";
    $.ajax({
    type: "POST",
	    url: "http://yingzc.net/mobile/goods/collection",
	    data: {goodsId: goodsId, _token: token },
	    dataType: "json",
	    success: function (data) {
	    if (data.error > 0){
	    alert(data.msg);
	    } else{
	    var c = parseInt($("#collection_count").text());
	    $('#collection_count').text(c + 1);
	    $('.collection').addClass('cur');
	    $('.collection').children('dd').text('已收藏');
	    $('.shopping').hide();
	    $('.mengban').hide();
	    $('.notice').html('已收藏').show().fadeOut(2500);
	    }
	    },
	    error:function(){
	    // alert('收藏失败');
	    $('.shopping').hide();
	    $('.mengban').hide();
	    $('.notice').html('收藏失败').show().fadeOut(2500);
	    }
    });
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
		    function changePrice()
		    {

		    var id = arguments[0] ? arguments[0] : 0;
		    if (id){
		    $("#attr_" + id).siblings().removeClass('selected');
		    $("#attr_" + id).addClass('selected');
		    }


		    //$(".styitems ul").find('li').find('input').attr('checked',false);
		    //  $(this).parent('li').find('input').attr('checked',true).parent('li').siblings().find('input').removeAttr('checked');
		    var attr = getSelectedAttributes(document.forms['ECS_FORMBUY']);
		    var qty = document.forms['ECS_FORMBUY'].elements['number'].value;
		    qty = !isNaN(qty)?parseInt(qty):1;
		    var token = "ZvL0EsRqGaHp6eIMAedm7QqkKFohqpu5KDMH77cP";
		    var goodsId = "334";
		    $.ajax({
		    type: "POST",
			    url: "http://yingzc.net/mobile/price",
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
	    /* *
	     * 添加商品到购物车 
	     */
	    function addToCart(goodsId, parentId)
	    {


	    var goods = new Object();
	    var spec_arr = new Array();
	    var fittings_arr = new Array();
	    var number = 1;
	    var formBuy = document.forms['ECS_FORMBUY'];
	    var quick = 0;
// 检查是否有商品规格 
	    if (formBuy)
	    {
	    spec_arr = getSelectedAttributes(formBuy);
	    if (formBuy.elements['number'])
	    {
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
	    if (btype == 1){
	    var token = "ZvL0EsRqGaHp6eIMAedm7QqkKFohqpu5KDMH77cP";
	    $.ajax({
	    type: "POST",
		    url: "<c:url value='/cart/addToCart.html'/>",
		    data: {goods: JSON.stringify(goods), _token: token },
		    dataType: "json",
		    success: function (res) {
		    /*$('.shopping').hide();
		     $('.mengban').hide();
		     $('.notice').html('res.message');show().fadeOut(2500);*/
		    addToCartResponse(res, 1);
		    }
	    });
	    } else{
	    var token = "ZvL0EsRqGaHp6eIMAedm7QqkKFohqpu5KDMH77cP";
	    $.ajax({
	    type: "POST",
		    url: "<c:url value='/cart/addToCart.html'/>",
		    data: {goods: JSON.stringify(goods), _token: token },
		    dataType: "json",
		    success: function (res) {

		    /*$('.shopping').hide();
		     $('.mengban').hide();
		     $('.notice').html('res.message');show().fadeOut(2500);*/
		    addToCartResponse(res, 2);
		    }
	    });
	    }

	    }

	    /* *
	     * 处理添加商品到购物车的反馈信息
	     */
	    function addToCartResponse(result, t)
	    {

	    if (result.error > 0)
	    {
// 如果需要缺货登记，跳转
	    /* if (result.error == 2)
	     {
	     if (confirm(result.message))
	     {
	     location.href = 'user.php?act=add_booking&id=' + result.goods_id + '&spec=' + result.product_spec;
	     }
	     }
	     // 没选规格，弹出属性选择框
	     else if (result.error == 6)
	     {
	     openSpeDiv(result.message, result.goods_id, result.parent);
	     }
	     else
	     {
	     alert(result.message);
	     }*/
	    // alert(result.message);
	    $('.shopping').hide();
	    $('.mengban').hide();
	    $('.notice').html(result.message).show().fadeOut(2500);
	    return false;
	    }
	    else
	    {
	    if (t == 1){
	    $('.shopping').hide();
	    $('.mengban').hide();
	    $('.notice').html('已加入购物车').show().fadeOut(2500);
	    } else{
	    location.href = "http://yingzc.net/mobile/cart";
	    }
	    /*
	     var cartInfo = document.getElementById('ECS_CARTINFO');
	     var cart_url = 'flow.php?step=cart';
	     if (cartInfo)
	     {
	     cartInfo.innerHTML = result.content;
	     }
	     
	     if (result.one_step_buy == '1')
	     {
	     location.href = cart_url;
	     }
	     else
	     {
	     switch(result.confirm_type)
	     {
	     case '1' :
	     if (confirm(result.message)) location.href = cart_url;
	     break;
	     case '2' :
	     if (!confirm(result.message)) location.href = cart_url;
	     break;
	     case '3' :
	     location.href = cart_url;
	     break;
	     default :
	     break;
	     }
	     }*/
	    }
	    }

</script>

    


</body></html>
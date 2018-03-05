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


<title>我的收益</title>

<link href="<c:url value ='/res-kuangji/css/global.css'/>" rel="stylesheet" type="text/css" />
<link href="<c:url value ='/res-kuangji/css/comman.css'/>" rel="stylesheet" type="text/css" />
<link href="<c:url value ='/res-kuangji/css/css.css'/>"rel="stylesheet" type="text/css" />
<link href="<c:url value ='/res-kuangji/css/jquery.mobile-1.3.2.min.css'/>"rel="stylesheet" type="text/css" />
<link href="<c:url value ='/res-kuangji/css/jquery_dialog.css'/>"rel="stylesheet" type="text/css" />
<link href="<c:url value ='/res-kuangji/css/PreFoot.css'/>"rel="stylesheet" type="text/css" />
<link href="<c:url value ='/res-kuangji/css/styl.css'/>"rel="stylesheet" type="text/css" />
<link href="<c:url value ='/res-kuangji/css/style.css'/>"rel="stylesheet" type="text/css" />
<link href="<c:url value ='/res-kuangji/css/styles.css'/>"rel="stylesheet" type="text/css" />
<link href="<c:url value ='/res-kuangji/css/top_12318.css'/>"rel="stylesheet" type="text/css" />
<link href="<c:url value ='/res-kuangji/css/user2015.css'/>"rel="stylesheet" type="text/css" />



<script type="text/javascript"
	src="<c:url value ='/res-kuangji/js/jquery-2.1.1.min.js'/>"></script>
<script type="text/javascript"
	src="<c:url value ='/res-kuangji/js/top.js'/>"></script>

</head>
    <body>

        
<div class="top">
	<b onclick="history.go(-1);"></b>
	<font>管理中心</font>
	<span></span>
	<ul>
		<li class="index" onclick="">首 页</li>
		<li class="member" onclick="">会员中心</li>
        <li class="shoppingCart" onclick="">购物车</li>
	</ul>
  </div>



<div style="min-height:300px; padding-bottom:10px; font-size:14px; background:#FFF" class="ucenter">

    <div data-role="content" class="ui-content" role="main" style="padding-top:8px; overflow:inherit">
        		
		<div class="uitem">
				<p class="pp">
						<a href="javascript:;" onclick="ajax_show_sub(1,this);"><i></i>分销订单</a>
				</p>
				<ul class="gonglist gg1">
						<li class="uli6"><p><a href=""><i></i>一级订单<span>10笔</span></a></p></li>
						<li class="uli9"><p><a href=""><i></i>二级订单<span>8笔</span></a></p></li>
						<li class="uli10"><p><a href=""><i></i>三级订单<span>23笔</span></a></p></li>	
						<div class="clear"></div>
				</ul>
		</div> 

        <div class="uitem">
            <p class="pp">
                <a href="javascript:;" onclick="ajax_show_sub(2, this);"><i></i>我的推广  </a>
            </p>
            <ul class="gonglist gg2">
                <li class="uli10" onclick=""><p><a href="javascript:void(0)"><i></i>下单购买<span>8单</span></a></p></li>
                <li class="uli6" onclick=""><p><a><i></i>我的邀请<span>10人</span></a></p></li>


                <div class="clear"></div>
            </ul>
        </div>

        <div class="uitem">
            <p class="pp">
                <a href="javascript:;" onclick="ajax_show_sub(3, this);"><i></i>我的奖励 </a>
            </p>
            <ul class="gonglist gg3">
<!--				<li class="uli6"><p><a href="http://weixin.yutu365.net/m/daili.php?act=mymoneydata&status=weifu"><i></i>未付款订单奖励<span>0.00元</span></a></p></li>-->
                <li class="uli9"><p><a href="javascript:void(0);"><i></i>一级奖励<span>160元</span></a></p></li>
                <li class="uli9"><p><a href="javascript:void(0);"><i></i>二级奖励<span>38元</span></a></p></li>
                <li class="uli9"><p><a href="javascript:void(0);"><i></i>三级奖励<span>86元</span></a></p></li>
<!--				<li class="uli10"><p><a href="http://weixin.yutu365.net/m/daili.php?act=mymoneydata&status=shouhuo"><i></i>已收货订单奖励<span>435.45元</span></a></p></li>		
                <li class="uli10"><p><a href="http://weixin.yutu365.net/m/daili.php?act=mymoneydata&status=quxiao"><i></i>已取消作废奖励<span>0.00元</span></a></p></li>		
                <li class="uli10"><p><a href="http://weixin.yutu365.net/m/daili.php?act=mymoneydata&status=tongguo"><i></i>审核通过的奖励<span>435.45元</span></a></p></li>-->
                <div class="clear"></div>
            </ul>
        </div>

<!--        <div class="uitem">
            <p class="pp">
                <a href="http://yzc.ll0534.net/mobile/member/tixian" style="background:url(http://weixin.yutu365.net/m/images/404-2.png) 90% center no-repeat"><i style="background:url(http://weixin.yutu365.net/m/images/x.png) 10% center no-repeat"></i>申请提款</a>
            </p>
        </div>-->

        <div class="uitem">
            <p class="pp">
                <a href="" style="background:url(../res-kuangji/images/404-2.png) 90% center no-repeat"><i style="background:url(../res-kuangji/images/x.png) 10% center no-repeat"></i>提款记录</a>
            </p>
        </div>


    </div>

</div>
<script type="text/javascript">
    function ajax_show_sub(k, obj) {
        $(".gg" + k).toggle();
        ll = $(".gg" + k).css('display');
        if (ll == 'none') {
            $(obj).find('i').css('background', 'url(../res-kuangji/images/+h.png) 10% center no-repeat');
        } else {
            $(obj).find('i').css('background', 'url(../res-kuangji/images/-h.png) 10% center no-repeat');
        }
    }
    function ajax_checked_fenxiao(obj) {
        //createwindow();
        $.post('', {action: 'ajax_checked_fenxiao'}, function (data) {
            //removewindow();
            if (data == '1') {
                window.location.href = '';
            } else {
                $(obj).parent().parent().hide(200);
                $('.ajax_checked_fenxiao').show();
                $('.ajax_checked_fenxiao').html(data);
                return false;
            }
        })
        return false;
    }
</script>


    

</body></html>
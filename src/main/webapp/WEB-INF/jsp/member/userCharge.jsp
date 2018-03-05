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


<title>安全设置</title>

<link href="<c:url value ='/res-kuangji/css/global.css'/>"
	rel="stylesheet" type="text/css" />
<link href="<c:url value ='/res-kuangji/css/exchangeMingxi.css'/>"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="<c:url value ='/res-kuangji/js/jquery-2.1.1.min.js'/>"></script>
<script type="text/javascript"
	src="<c:url value ='/res-kuangji/js/top.js'/>"></script>


<script type="text/javascript">
$(document).ready(function(){
    var page = 1;
    $("#loading").click(function(){
	 var token = "0BZyoINMaXt4Wz9QlZ4lyVimwX0aoMlD6SRxDaG0";
	 page+=1;
	$.ajax({
	    url:"http://yzc.ll0534.net/mobile/loadingMore",
	    type:'post',
	    data:{page:page,_token: token},
	    dataType: 'json',
	    success:function(data){
		var item = eval(data);
		var res = '';
		for(var i=0;i<item.length;i++) {
		   
		    res += "<ul id='invite_list'>";
		    res += "<li><span>用户名："+item[i].name+"</span><font>"+item[i].created_at+"</font></li>";
		    res += "<li><span>余额："+item[i].user_money+"元</span><font>购物积分："+item[i].repeat_prize+"</font></li>";
		    res += "<li>玉兔账户名："+item[i].old_user_name+"</li>";
		    if(item[i].ticket_kind == 1){
			res += "<li><span>兔劵：玉兔券</span><font>兑换个数："+item[i].convert_number+"</font></li>";
		    }
		    else if(item[i].ticket_kind == 2){
			 res += "<li><span>兔劵：玉兔券</span><font>兑换个数："+item[i].convert_number+"</font></li>";
		    }
		    res += "</ul>";
		}
		$("#invite_list").append(res);
	    },
	    error: function () {
		alert('Ajax error!');
            }
	})
    })
})
</script>
              </head>
    <body>

         
<div class="top">
	<b onclick="history.go(-1);"></b>
	<dd>兑换操作列表</dd>
	<span></span>
        <ul>
            <li class="index" onclick="">首 页</li>
            <li class="member" onclick="">会员中心</li>
            <li class="shoppingCart" onclick="">购物车</li>
        </ul>
</div>

<div class="box">
        <div id="invite_list"></div>
    <li style="text-align:center;" id="loading">查看更多</li>
</div>

    

</body></html>
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>会员位置互换</title>
	<meta name="viewport" content="width=device-width,inital-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=nos"/>
	<link rel="stylesheet" href='<c:url value="/res/css/mainsm.css?v="/>${cssversion}'/>
	<link href='<c:url value="/res/js/plugins/modal/modal.css?"/>${jsversion}' type="text/css" rel="stylesheet">
	<script type="text/javascript" src='<c:url value="/res/js/libs/zepto.min.js?v="/>${jsversion}' ></script>
	<script type="text/javascript" src='<c:url value="/res/js/global.js?v="/>${jsversion}' ></script>
	<script type="text/javascript" src='<c:url value="/res/js/plugins/modal/modal.js?v="/>${jsversion}'></script>
	
</head>
<body>
	<div class="wrap">
		<header class="header">
			<a class="header-left" href='<c:url value="/redPack/personalCenter.html"/>'><img src='<c:url value="/res/images/icon-back.png"/>' alt=""></a>
			<h1 class="header-title">会员位置互换</h1>
		</header>
		<div class="content record forbid-num">
			<div class="item flex">
				<div class="txt">
					<p>会员1手机号码：<span><input type="text" id="user1Mobile"  /></span></p>
					<p>会员2手机号码：<span><input type="text" id="user2Mobile" /></span></p>
				</div>
				<div class="btn" ><a href="#" class="subbtn" id="subbtn"  >互换位置</a></div>
				
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
		
	$(function(){
	    //code 点击事件
	    $(".subbtn").bind("click",function(){
	        HHN.popupConfirm("你确定要将这倆个会员位置互换吗?", 
	                 function(){return true;}, 
	                 function(){submitConfirm(); 
	                            return true;});
            
	    });
	});
	//提交信息
    function submitConfirm(){
    	var user2Mobile = $("#user2Mobile").val();
    	var user1Mobile = $("#user1Mobile").val();

    	if(""==user2Mobile || ""==user1Mobile){
    		HHN.popup("会员的手机号码不能为空");
        	return false;
        }
    	
    	var param ={"user2Mobile":user2Mobile,"user1Mobile":user1Mobile};
    	$.post('<c:url value="/kefu/doExchangePosition.html"/>', param, function(data) {
    		
    		if(data.resultCode==1){
    			HHN.popup(data.result);
			}else if(data.resultCode==0){
				HHN.popup("会员位置互换成功");
				search();
			}
		},"json");
    }
	
	</script>
</html>
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>设置入会金额</title>
	<meta name="viewport" content="width=device-width,inital-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=nos"/>
	<link href='<c:url value="/res/js/plugins/modal/modal.css?"/>${jsversion}' type="text/css" rel="stylesheet">
	
	<link rel="stylesheet" href='<c:url value="/res/css/base.css?v="/>${cssversion}' />
	<link rel="stylesheet" href='<c:url value="/res/css/exit.css?v="/>${cssversion}' />
	<link rel="stylesheet" href='<c:url value="/res/css/main.css?v="/>${cssversion}'/>

	
	<script type="text/javascript" src='<c:url value="/res/js/libs/zepto.min.js?v="/>${jsversion}' ></script>
	<script type="text/javascript" src='<c:url value="/res/js/global.js?v="/>${jsversion}' ></script>
	<script type="text/javascript" src='<c:url value="/res/js/plugins/modal/modal.js?v="/>${jsversion}'></script>
	
	<script>
	$(function(){
			$("#regBtn").bind("click",function(){
				var payMoney = $("#payMoney").val();
				if(payMoney==null || payMoney.length==0){
					HHN.popup("请输入入会金额");
					return;
				}
				
				var referrerMoney = $("#referrerMoney").val();
				if(referrerMoney==null || referrerMoney.length==0){
					HHN.popup("请输感恩金额");
					return;
				}
                
				
				$(this).attr('disabled',true);
				/**
				var options = {type:"POST",url:"<c:url value='/param/setPayMoney.html'/>",data:{payMoney:payMoney,referrerMoney:referrerMoney}};
				
				ajaxRequest(options,function(data){
					if(data.result=='设置会费成功'){
						popWindow(data.result);
						var url ="${loginServerUrl }/redPack/personalCenter.html";
						setTimeout("window.location.href='"+url+"'", 1000); 
					}else{
						popWindow(data.result);
						$('#regBtn').removeAttr('disabled');
					}
				});
				**/
				var param ={payMoney:payMoney,referrerMoney:referrerMoney};
				$.post('<c:url value="/param/setPayMoney.html"/>', param, function(data) {
		    		
					if(data.result=='设置会费成功'){
						HHN.popup(data.result);
						var url ="${loginServerUrl }/redPack/personalCenter.html";
						setTimeout("window.location.href='"+url+"'", 1000); 
					}else{
						HHN.popup(data.result);
						$('#regBtn').removeAttr('disabled');
					}
				},"json");
			});
		});
	</script>	
</head>

<body>
	
	<div class="top-nav">
        <a class="link"  href="<c:url value='/redPack/personalCenter.html'/>" onclick="history.back();">&lt;返回</a>
        <h2>设置入会金额</h2>
    </div>
    
	<form action="${loginServerUrl }/param/setPayMoney.do"  method="post">
	<div class="exit-wrap">
		<div class="item">
				<i style="display: block;  height: 20px;  -webkit-background-size: 140px auto;  background-size: 140px auto;    margin-right: 5px;">入会金额：</i>
				<input type="number" name="payMoney" id="payMoney" placeholder="请输入入会金额（不含感恩金额）" value="${payMoney}">
		</div>
		<div class="item">
				<i style="display: block;  height: 20px;  -webkit-background-size: 140px auto;  background-size: 140px auto;    margin-right: 5px;" >感恩金额：</i>
				<input type="number" name="referrerMoney" id="referrerMoney" placeholder="请输入感恩金额"  value="${referrerMoney}">
		</div>
		
	</div>
	</form>	
	<div class="form-btns">
    		<a href="javascript:;" class="btn"   id="regBtn"  >保存</a>
	</div>
</body>
</html>

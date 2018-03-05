<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>设置用户账号</title>
	<meta name="viewport" content="width=device-width,inital-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=nos"/>
	<link rel="stylesheet" href='<c:url value="/res/css/base.css?v="/>${cssversion}' />
	<link rel="stylesheet" href='<c:url value="/res/css/exit.css?v="/>${cssversion}' />
	<link rel="stylesheet" href='<c:url value="/res/css/main.css?v="/>${cssversion}'/>

	<link href='<c:url value="/res/js/plugins/modal/modal.css?"/>${jsversion}' type="text/css" rel="stylesheet">
	<script type="text/javascript" src='<c:url value="/res/js/libs/zepto.min.js?v="/>${jsversion}' ></script>
	<script type="text/javascript" src='<c:url value="/res/js/global.js?v="/>${jsversion}' ></script>
	<script type="text/javascript" src='<c:url value="/res/js/plugins/modal/modal.js?v="/>${jsversion}'></script>
	
	
</head>

<body>
	<div class="top-nav">
       <a class="link"  href="<c:url value='/redPack/personalCenter.html'/>" onclick="history.back();">&lt;返回</a>
       <h2>设置用户账号</h2>
    </div>
    
	<form action='<c:url value="/param/setUserAccount.do"/>'  method="post">
	<div class="search flex">
		<a href="#"><img onclick="search()" src="<c:url value='/res/images/search.png'/>" alt=""></a>
		<div class="search-input"><input id="userName" name ="userName" type="text" placeholder="请输入需封号手机号码"></div>
		<div class="left-bor"></div>
		<div class="bottom-bor"></div>
	</div>
			
	<div class="exit-wrap">
		<div class="item">
			<i style="display: block;  height: 20px;  -webkit-background-size: 140px auto;  background-size: 140px auto;    margin-right: 5px;">用户：</i>
			<input type="text" name="fxName" id="fxName" realOnly="true" value="${fxName}">
		</div>
		<div class="item">
			<i style="display: block;  height: 20px;  -webkit-background-size: 140px auto;  background-size: 140px auto;    margin-right: 5px;">手机号码：</i>
			<input type="text" name="fxPhone" id="fxPhone" realOnly="true"  value="${fxPhone}">
		</div>
		<div class="item">
			<i style="display: block;  height: 20px;  -webkit-background-size: 140px auto;  background-size: 140px auto;    margin-right: 5px;">支付宝：</i>
			<input type="text" name="fxZfb" id="fxZfb" placeholder="请输入支付宝" value="${fxZfb}">
		</div>
		<div class="item">
			<i style="display: block;  height: 20px;  -webkit-background-size: 140px auto;  background-size: 140px auto;    margin-right: 5px;">微信：</i>
			<input type="text" name="fxWeixin" id="fxWeixin" placeholder="请输入微信"  value="${fxWeixin}">
		</div>
	</div>
	</form>
	<div class="form-btns">
    		<a href="javascript:;" class="btn"   id="regBtn"  >保存</a>
	</div>
<script>
	$(function(){
		$("#regBtn").bind("click",function(){
			var fxName = $("#fxName").val();
			if(fxName==null || fxName.length==0){
				HHN.popup("请输用户");
				return;
			}
			
			var fxPhone = $("#fxPhone").val();
			if(fxPhone==null || fxPhone.length==0){
				HHN.popup("请输入用户手机");
				return;
			}
			var fxZfb = $("#fxZfb").val();
			//if(fxZfb==null || fxZfb.length==0){
			//	popWindow("请输入支付宝");
			//	return;
			//}
			var fxWeixin = $("#fxWeixin").val();
			//if(fxWeixin==null || fxWeixin.length==0){
			//	popWindow("请输入平台联系人");
			//	return;
			//}
            
			$(this).attr('disabled',true);
			/* var options = {type:"POST",url:"<c:url value='/param/setFxAccount.html'/>",
					data:{fxName:fxName,
						fxPhone:fxPhone,
						fxZfb: fxZfb,
						fxWeixin : fxWeixin
						}};
			ajaxRequest(options,function(data){
				if(data.result=='设置平台账号成功'){
					popWindow(data.result);
					var url ="${loginServerUrl }/redPack/personalCenter.html";
					setTimeout("window.location.href='"+url+"'", 1000); 
				}else{
					popWindow(data.result);
					$('#regBtn').removeAttr('disabled');
				}
			}); */
			var param ={fxName:fxName,fxPhone:fxPhone,
					fxZfb: fxZfb,fxWeixin : fxWeixin};
			$.post('<c:url value="/param/setUserAccount.html"/>', param, function(data) {
	    		
				if(data.result=='设置用户账号成功'){
					HHN.popup(data.result);
					var url = '<c:url value="/redPack/personalCenter.html"/>';
					setTimeout("window.location.href='"+url+"'", 1000); 
				}else{
					HHN.popup(data.result);
					$('#regBtn').removeAttr('disabled');
				}
			},"json");
		});
		});
	</script>	
</body>
</html>

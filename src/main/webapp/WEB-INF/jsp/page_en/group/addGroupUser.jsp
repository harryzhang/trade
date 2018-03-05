<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>添加群组用户</title>
	<meta name="viewport" content="width=device-width,inital-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=nos"/>
	<link rel="stylesheet" href='<c:url value="/res/css/mainsm.css?v="/>${cssversion}'/>
	<link href='<c:url value="/res/js/plugins/modal/modal.css?"/>${jsversion}' type="text/css" rel="stylesheet">
	<script type="text/javascript" src='<c:url value="/res/js/libs/zepto.min.js?v="/>${jsversion}' ></script>
	<script type="text/javascript" src='<c:url value="/res/js/global.js?v="/>${jsversion}' ></script>
	<script type="text/javascript" src='<c:url value="/res/js/plugins/modal/modal.js?v="/>${jsversion}'></script>
	
	<script>
	$(function(){
			$("#regBtn").bind("click",function(){
				var groupName = $("#groupName").val();
				if(groupName==null || groupName.length==0){
					HHN.popup("请输入群组名");
					return;
				}
				var refferMobile = $("#refferMobile").val();
				if(refferMobile==null || refferMobile.length!=11){
					HHN.popup("请输入推荐人手机号码");
					return;
				}
				
				var mobile = $("#mobile").val();
				if(mobile==null || mobile.length!=11){
					HHN.popup("请输入加入的会员手机号码");
					return;
				}
				
				var name = $("#name").val();
				if(name==null || name.length==0){
					HHN.popup("请输入加入的会员姓名");
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
				var param ={groupName:groupName,refferMobile:refferMobile,mobile:mobile,name:name};
				$.post('<c:url value="/groupManager/saveGroupUser.html"/>', param, function(data) {
		    		
					if(data.result=='0'){
						HHN.popup(data.message);
						var url ="${loginServerUrl }/redPack/personalCenter.html";
						setTimeout("window.location.href='"+url+"'", 1000); 
					}else{
						HHN.popup(data.message);
						$('#regBtn').removeAttr('disabled');
					}
				},"json");
			});
		});
	</script>	
</head>

<body>
	<div class="wrap">
		<header class="header">
			<a class="header-left" href="<c:url value='/redPack/personalCenter.html'/>"><img src="<c:url value="/res/images/icon-back.png"/>" alt=""></a>
			<h1 class="header-title">添加群组</h1>
		</header>
		<form action="${loginServerUrl }/groupManager/saveGroup.do"  method="post">
		<div class="content manager nopad">
			<div class="manager-inn">
				<div class="ipt">
					<span>群组名称：</span><input type="text" name="groupName" id="groupName" >
				</div>
				<div class="ipt">
					<span>推荐人手机号码：</span><input type="number" name="refferMobile" id="refferMobile" >
				
				</div>
				<div class="ipt">
					<span>会员手机号码：</span><input type="number" name="mobile" id="mobile" >
				
				</div>
				<div class="ipt">
					<span>会员人员姓名：</span><input type="text" name="name" id="name" >
				
				</div>
				<div class="btn"><a href="#" id="regBtn" >保存</a></div>
			</div>
		</div>
		</form>
	</div>
	
</body>
</html>

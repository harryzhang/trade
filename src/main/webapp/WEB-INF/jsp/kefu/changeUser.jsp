<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>用户替换</title>
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
			<a class="header-left" href="<c:url value='/redPack/personalCenter.html'/>"><img src="<c:url value="/res/images/icon-back.png"/>" alt=""></a>
			<div class="search flex">
				<a href="#"><img onclick="search()" src="<c:url value='/res/images/search.png'/>" alt=""></a>
				<div class="search-input"><input id="userName" name ="userName" type="text" placeholder="请输入需替换人手机号码"></div>
				<div class="left-bor"></div>
				<div class="bottom-bor"></div>
			</div>
		</header>
		
		<div class="content record forbid-num">
		<div class="exit-wrap">
			<div class="item"><input id="changeName" name ="changeName" type="text" placeholder="请输入替换封号人的姓名"></div>
			<div class="item"><input id="changePhone" name ="changePhone" type="text" placeholder="请输入替换封号的手机号码"></div>
		</div>
	
		
		<c:if test="${!empty userList}">
			<c:forEach   var="userDo" items="${userList}" >
			<div class="item flex">
				<div class="txt">
					<p>手机号码：<span>${userDo.userName}</span></p>
					<p>姓名：<span>${userDo.name}</span></p>
				</div>
				<div class="btn" ><a href="#" class="subbtn" id="subbtn${userDo.id}" cdataid="${userDo.id }" >封号</a></div>
			</div>
			</c:forEach>
		</c:if>
		</div>
	</div>
</body>
<script type="text/javascript">
		
	$(function(){
	    //code 点击事件
	    $(".subbtn").bind("click",function(){
	        var userId = $(this).attr("cdataid");	        
	        var changeName =  $("#changeName").val();
	        if(changeName==null || changeName.length == 0){
	        	HHN.popup("请输入需替换人的名字！");
				return;
			}
	        var changePhone =  $("#changePhone").val();
	        if(changePhone==null || changePhone.length != 11){
	        	HHN.popup("请输入正确的需替换的手机号码！");
				return;
			}
	         HHN.popupConfirm("你确定要封这个会员的号?", 
	                 function(){return true;}, 
	                 function(){submitConfirm(userId,changeName,changePhone); 
	                          return true;});
	          
	    });
	});
	//提交信息
    function submitConfirm(userId,changeName,changePhone){
    	var param ={"userId":userId,"changeName":changeName,"changePhone":changePhone};
    	$.post('<c:url value="/kefu/setChangeUser.html"/>', param, function(data) {
    		if(data.resultCode==1){
    			HHN.popup(data.result);
			}else{
				HHN.popup("封号成功");
				search();
			}
    		
		},"json");
    }
	function search(){
			var userName = $("#userName").val();
			var url ='<c:url value="/kefu/changeUser?userName="/>' + userName ;
			window.location.href=url; 
		}
	</script>
</html>
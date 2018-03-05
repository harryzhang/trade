<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>客服重置密码</title>
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
				<div class="search-input"><input id="userName" name ="userName" type="text" placeholder="请输入需会员手机号码"></div>
				<div class="left-bor"></div>
				<div class="bottom-bor"></div>
			</div>
		</header>
		<div class="content record forbid-num">
		<c:if test="${!empty userList}">
			<c:forEach   var="userDo" items="${userList}" >
			<div class="item flex">
				<div class="txt">
					<p>手机号码：<span>${userDo.userName}</span></p>
					<p>姓名：<span>${userDo.name}</span></p>
					<p>会员状态：<span>
            				<c:if test="${userDo.status==1}">正式会员</c:if>
            				<c:if test="${userDo.status==2}">封号会员</c:if>
				   			<c:if test="${userDo.status == 0}">临时式会员</c:if>
                         </span>
            		</p>
				</div>
				<div class="btn" ><a href="#" class="subbtn" id="subbtn${userDo.id}" cdataid="${userDo.id }" >重置密码</a></div>
				
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

	        HHN.popupConfirm("你确定要给这个会员重置密码成123123吗?", 
	                 function(){return true;}, 
	                 function(){submitConfirm(userId); 
	                            return true;});
            
	    });
	});
	//提交信息
    function submitConfirm(userId){
    	var param ={"userId":userId};
    	$.post('<c:url value="/kefu/doResetPwdUser.html"/>', param, function(data) {
    		
    		if(data.resultCode==1){
    			HHN.popup(data.result);
			}else if(data.resultCode==0){
				HHN.popup("重置密码成功");
				search();
			}
		},"json");
    }
	function search(){
			var userName = $("#userName").val();
			var url ='<c:url value="/kefu/resetPwdUser?userName="/>' + userName ;
			window.location.href=url; 
		}
	</script>
</html>
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>设置管理员</title>
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
			<h1 class="header-title">设置客服人员</h1>
		</header>
		<div class="content manager nopad">
			<div class="manager-inn">
				<div class="ipt"><span>客服手机号码：</span><input type="number" name="userName" id="userName" ></div>
				<div class="btn"><a href="#" id="regBtn" >保存</a></div>
			</div>
		</div>
		<%-- <div class="footer">
			<div class="flex">
            	<a href="javascript:;" class="bar-item ">
            	    <div class="bar_icon">
            	        <img src="${fileServerUrl }/res/images/sy.png" alt="">
            	    </div>
            	    <p class="bar_label">首页</p>
            	</a>
            	<a href="javascript:;" class="bar-item active">
            	    <div class="bar_icon">
            	        <img src="${fileServerUrl }/res/images/gr-active.png" alt="">
            	    </div>
            	    <p class="bar_label">个人中心</p>
            	</a>
            	<a href="javascript:;" class="bar-item">
            	    <div class="bar_icon">
            	        <img src="${fileServerUrl }/res/images/tj.png" alt="">
            	    </div>
            	    <p class="bar_label">推荐会员</p>
            	</a>
            	<a href="javascript:;" class="bar-item">
            	    <div class="bar_icon">
            	        <img src="${fileServerUrl }/res/images/td.png" alt="">
            	    </div>
            	    <p class="bar_label ">团队结构</p>
            	</a>
            </div>
        </div> --%>
	</div>
</body>
<script>
	$(function(){
			$("#regBtn").bind("click",function(){
				var userName = $("#userName").val();
				if(userName==null || userName.length==0){
					HHN.popup("请输客服手机号");
					return;
				}
				
                
				$(this).attr('disabled',true);
				/* var options = {type:"POST",url:"<c:url value='/param/setFxKefu.html'/>",data:{userName:userName}};
				ajaxRequest(options,function(data){
					if(data.result=='设置客服成功'){
						popWindow(data.result);
						var url ="${loginServerUrl }/redPack/personalCenter.html";
						setTimeout("window.location.href='"+url+"'", 1000); 
					}else{
						popWindow(data.result);
						$('#regBtn').removeAttr('disabled');
					}
				 });*/
			var param ={userName:userName};
			$.post('<c:url value="/param/setFxKefu.html"/>', param, function(data) {
	    		
				if(data.result=='设置客服成功'){
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
</html>

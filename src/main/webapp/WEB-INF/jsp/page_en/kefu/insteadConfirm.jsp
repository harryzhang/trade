<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>客服确认收款</title>
	<meta name="viewport" content="width=device-width,inital-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=nos"/>
	<link rel="stylesheet" href='<c:url value="/res/css/mainsm.css?v="/>${cssversion}'/>
	<link href='<c:url value="/res/js/plugins/modal/modal.css?"/>${jsversion}' type="text/css" rel="stylesheet">
	
</head>
<body>
	<div class="wrap">
		<header class="header">
			<a class="header-left" href="<c:url value='/redPack/personalCenter.html'/>"><img src="<c:url value="/res/images/icon-back.png"/>" alt=""></a>
			<div class="search flex">
				<a href="javascript:void(0);"><img onclick="search()"  src='<c:url value="/res/images/search.png"/>' alt=""></a>
				<div class="search-input"><input type="text" id="userName" name ="userName" placeholder="请输入手机号码"></div>
				<div class="left-bor"></div>
				<div class="bottom-bor"></div>
			</div>
		</header>
		<div class="content record forbid-num">
		<c:if test="${!empty walletList}">
			<c:forEach   var="walletItem" items="${walletList}" >
			<div class="item flex">
				<div class="txt">
					<p>付款人：<span>${walletItem.name}</span></p>
					<p>付款人手机：<span>${walletItem.userName }</span></p>
					<p>付款人微信：<span>${walletItem.weixin }</span></p>
					<p>付款人支付宝：<span> ${walletItem.zhifubao }</span></p>
					<p>收款金额：<span>￥${walletItem.amt }</span></p>
					<p>状态：<span id="s${walletItem.id}">
					            <c:if test="${walletItem.skStatus == 1 }"> 已收</c:if>
								<c:if test="${walletItem.skStatus == 0 }"> 未收</c:if>
							</span>
					</p>
				</div>
				<c:if test="${walletItem.skStatus == 0}" >
					<div class="btn"><a href="#" class="subbtn" id="subbtn${walletItem.id}" cdataid="${ walletItem.id }" >确认收款</a></div>
				</c:if>
				
			</div>
			</c:forEach>
		</c:if>			
		</div>
	</div>
</body>
</html>
<script type="text/javascript" src='<c:url value="/res/js/libs/zepto.min.js?v="/>${jsversion}' ></script>
<script type="text/javascript" src='<c:url value="/res/js/global.js?v="/>${jsversion}' ></script>
<script type="text/javascript" src='<c:url value="/res/js/plugins/modal/modal.js?v="/>${jsversion}'></script>
<script type="text/javascript">
	
	$(function(){
	    //code 点击事件
	    $(".subbtn").bind("click",function(){
	        var recId = $(this).attr("cdataid");
	        HHN.popupConfirm("你确定已经收款?", 
	                 function(){return true;}, 
	                 function(){submitConfirm(recId); 
	                            return true;});
            	        
	    });
	});

	function search(){
		var userName = $("#userName").val();
		var url ='<c:url value="/kefu/doConfirm" />?userName=' + userName ;
		window.location.href=url; 
	}
	

	//提交信息
    function submitConfirm(recId){
    	var param ={"recordId":recId};
    	$.post('<c:url value="/wallet/confirmSK.html"/>', param, function(data) {
    		
    		if(data.resultCode==1){
    			HHN.popup(data.result);
			}else if(data.resultCode==0){
				refRec(recId);
				HHN.popup("操作成功");
			}
		},"json");
    }

    function refRec(recId){
    	$("#s"+recId).text("已收"); 
    	$("#subbtn"+recId).hide();
    }
    
</script>

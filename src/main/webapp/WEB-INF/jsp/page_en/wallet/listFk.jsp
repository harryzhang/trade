<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>付款记录</title>
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
			<h1 class="header-title">付款记录</h1>
		</header>
		<div class="content record">
		<div class="inner">
		<c:if test="${!empty walletList}">
			<c:forEach   var="walletItem" items="${walletList}" >
			<div class="item">
				<div class="person-info">
					<span>收款人：${walletItem.name}</span>
					<span>收款人手机：${walletItem.userName } </span>
					<span>收款人微信：${walletItem.weixin }  </span>
					<span>收款人支付宝： ${walletItem.zhifubao }   </span>
					<span>收款时间： <fmt:formatDate value="${walletItem.skUpdateTime}" pattern="yyyy-MM-dd HH:mm:ss" />  </span>
				</div>
				<div class="txt-info">
					<ul class="flex">
						<li>
							<span class="strong">￥${walletItem.amt }</span>
							<span class="til">收款金额</span>
						</li>
						<li>
							<span class="strong" id="s${walletItem.id}">
								<c:if test="${walletItem.fkStatus == 0 and walletItem.skStatus == 0 }"> 未付</c:if>
								<c:if test="${walletItem.fkStatus == 1 and walletItem.skStatus == 0 }"> 已付款</c:if>
								<c:if test="${walletItem.fkStatus == 1  and walletItem.skStatus == 1 }"> 付款成功</c:if>
							</span>
							<span class="til">状态</span>
						</li>
					</ul>
					<div class="date">
						<span>确认日期：</span>
						<span class="strong">
						<fmt:formatDate value="${walletItem.fkUpdateTime}" pattern="yyyy-MM-dd HH:mm:ss" />
						</span>
					</div>
				</div>
				<c:if test="${walletItem.fkStatus == 0}" >
				<div class="btn"><a href="javascript:;" class="subbtn" id="subbtn${walletItem.id}" cdataid="${ walletItem.id }" >我已付款</a></div>
				</c:if>
			</div>	
			
			</c:forEach>
			</c:if>
			</div>
			<c:if test="${empty walletList}">
				<div class="no-record">
					<div class="imgs"><img src='<c:url value="/res/images/empty-tip.png"/>' alt=""></div>
					<p>亲，没有记录哦！</p>
				</div>
			</c:if>
			
		</div>
	</div>
</body>
</html>
<script type="text/javascript">
	$(function(){
	    //code 点击事件
	    $(".subbtn").bind("click",function(){
	        var recId = $(this).attr("cdataid");	        

	        HHN.popupConfirm("你确定已经付款?", 
	                 function(){return true;}, 
	                 function(){submitConfirm(recId); 
	                            return true;});
            
	    });
	});


	//提交信息
    function submitConfirm(recId){
    	var param ={"recordId":recId};
    	$.post('<c:url value="/wallet/confirmFK.html"/>', param, function(data) {
    		
    		if(data.resultCode==1){
    			HHN.popup(data.result);
			}else if(data.resultCode==0){
				refRec(recId);
				HHN.popup("操作成功");
			}
		},"json");
    }

    function refRec(recId){
    	$("#s"+recId).text("待确认"); 
    	$("#subbtn"+recId).hide();
    }
    
</script>
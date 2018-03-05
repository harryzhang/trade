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


<title>账户预览</title>

<link href="<c:url value ='/res-kuangji/css/global.css'/>"
	rel="stylesheet" type="text/css" />
<link href="<c:url value ='/res-kuangji/css/goodsDetails.css'/>"
	rel="stylesheet" type="text/css" />
<link href="<c:url value ='/res-kuangji/css/myWealth.css'/>"
	rel="stylesheet" type="text/css" />
		<link href='<c:url value="/res/js/plugins/modal/modal.css?"/>${jsversion}' type="text/css" rel="stylesheet">
	
<script type="text/javascript"
	src="<c:url value ='/res-kuangji/js/jquery-2.1.1.min.js'/>"></script>
<script type="text/javascript"
	src="<c:url value ='/res-kuangji/js/top.js'/>"></script>
<script type="text/javascript" src='<c:url value="/res/js/global.js?v="/>${jsversion}' ></script>
<script type="text/javascript" src='<c:url value="/res/js/plugins/modal/modal.js?v="/>${jsversion}'></script>
	
</head>
<body>


	<div class="top">
		<b
			onclick="javascript:window.location.href=&#39;<c:url value='/redPack/personalCenter.html'/>&#39;"></b>
		<dd>账户预览</dd>
		<span></span>
		<ul>
			<li class="index" onclick="">首 页</li>
			<li class="member" onclick="">会员中心</li>
			<li class="shoppingCart">购物车</li>
		</ul>
	</div>

	<div class="topmoney">
		<div class="balance">余额（现金豆）</div>
		<div class="money">${userAccount.rmb}（欧）</div>

	</div>
	
	<div class="centent">
		<dl class="cenleft">
			<dt class="exchange">
				证券数量<font> ${userAccount.security} 股</font>
			</dt>
			<dd onclick="javascript:window.location.href ='<c:url value='/member/accountDetail.html?accountType=security'/>'">
				<font>查看明细</font>
			</dd>
		</dl>
		<dl class="" >
				<dt></dt>
				<dd></dd>
				</dl>
				<dl class="cenright xinzeng" onclick="javascript:window.location.href ='<c:url value='/member/unpayInfo.html'/>'">
					<dt></dt>
					<dd>退本</dd>
		</dl>
	</div>
	
	<div class="centent">
		<dl class="cenleft">
			<dt class="exchange">
				余额(现金豆)<font> ${userAccount.rmb} 欧元</font>
			</dt>
			<dd onclick="javascript:window.location.href ='<c:url value='/member/accountDetail.html?accountType=rmb'/>'">
				<font>查看明细</font>
			</dd>
		</dl>
		<dl class="cenright xinzeng" onclick="window.location.href ='<c:url value='/member/transRmb.html'/>'">
			<dt></dt>
			<dd>转换</dd>
		</dl>
		<dl class="cenright xinzeng" onclick="window.location.href ='<c:url value='/member/withdraw.html'/>'">
			<dt></dt>
			<dd>提现</dd>
		</dl>
	</div>

	<div class="centent">
		<dl class="cenleft">
			<dt class="exchange">
				奖金豆<font> ${userAccount.jjd} 欧元</font>
			</dt>
			<dd onclick="javascript:window.location.href ='<c:url value='/member/accountDetail.html?accountType=jjd'/>'">
				<font>查看明细</font>
			</dd>
		</dl>
		<dl class="cenright xinzeng" onclick="window.location.href ='<c:url value='/member/withdrawJjd.html'/>'">
			<dt></dt>
			<dd>提现</dd>
		</dl>
		<dl class="cenright xinzeng" onclick="window.location.href ='<c:url value='/member/transJjd.html'/>'">
			<dt></dt>
			<dd>转换</dd>
		</dl>
	</div>
	
	<div class="centent">
	    <dl class="cenleft">
	        <dt class="exchange">激活豆<font> ${userAccount.pet} 个</font></dt>
	        <dd onClick="javascript:window.location.href ='<c:url value='/member/accountDetail.html?accountType=pet'/>'"><font>查看明细</font></dd>
	    </dl>
	    <dl class="cenright xinzeng" onClick="javascript:window.location.href ='<c:url value='/pay/toChongzhi.html'/>'">
	        <dt></dt>
	        <dd>充值</dd>
	    </dl>
	</div>
	<div class="centent">
	    <dl class="cenleft">
	        <dt class="exchange">储值豆<font> ${userAccount.point} 个</font></dt>
	        <dd onClick="javascript:window.location.href ='<c:url value='/member/accountDetail.html?accountType=point'/>'"><font>查看明细</font></dd>
	    </dl>
	    <dl class="cenright xinzeng" onClick="javascript:window.location.href ='<c:url value='/member/transPoint.html'/>'">
	        <dt></dt>
	        <dd>转换</dd>
	    </dl>
	</div>
	<div class="centent">
	    <dl class="cenleft">
	        <dt class="exchange">激活积分<font> ${userAccount.jifen} 个</font></dt>
	        <dd onClick="javascript:window.location.href ='<c:url value='/member/accountDetail.html?accountType=jifen'/>'"><font>查看明细</font></dd>
	    </dl>
	    <dl class="cenright xinzeng" onClick="javascript:window.location.href ='<c:url value='/member/transJifeng.html'/>'">
	        <dt></dt>
	        <dd>转让</dd>
	    </dl>
	</div>

<!-- 	<div class="centent" > -->
<!-- 		    <dl class="cenleft"> -->
<!-- 		        <dt class="exchange" onClick="#" >提现记录</font></dt> -->
<!-- 		        <dd onClick="javascript:window.location.href ='<c:url value='/member/userWithdraw.html'/>'"><font>查看明细</font></dd> -->
		  
<!-- 		    </dl> -->
<!-- 	</div> -->

<script type="text/javascript">

/*  function unpay(){
	 var sec = ${userAccount.security};
	 if(sec <1 ){
		 HHN.popup("当前证券数量不足。");
		 return;
	 }
	HHN.popupConfirm("你确定要退回本金?", 
              function(){return true;}, 
              function(){
            	  submitConfirm(); 
                       return true;});
	
	
 } */
	//提交信息
 function submitConfirm(){
 	$.post('<c:url value="/member/unPay.html"/>', '', function(data) {
 		if(data.resultCode==1){
 				HHN.popup(data.result);
			}else{
				alert("退本成功,请等待管理员审核");
				location.reload();
			}
 		
		},"json");
 }
</script> 
	


</body>
</html>
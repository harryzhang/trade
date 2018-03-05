<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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


<title>个人中心</title>


<link href="<c:url value ='/res-kuangji/css/global.css'/>"
	rel="stylesheet" type="text/css">
<link href="<c:url value ='/res-kuangji/css/member_yzc.css'/>"
	rel="stylesheet" type="text/css">





<script type="text/javascript"
	src="<c:url value ='/res-kuangji/js/jquery-2.1.1.min.js'/>"></script>
<script type="text/javascript"
	src="<c:url value ='/res-kuangji/js/top.js'/>" used="1"></script>
<script type="text/javascript"
	src="<c:url value ='/res-kuangji/js/member_yzc.js'/>"></script>



</head>
<body>
	<div class="main">
	
		<div class="memberInfo">
		<div class="tleft">
			<dl>
				<dt>
					<img src="<c:url value ='/res-kuangji/images/logo01.jpg'/>">
				</dt>
				<dd class="uname">${userDo.name}</dd>
				<dd class="uma">欢迎登陆SDE资产证券</dd>
				<dd class="uma"><c:if test="${ userDo.grade == '0' }">普通会员</c:if>
				<c:if test="${ userDo.grade == '1' }">一级股东</c:if>
				<c:if test="${ userDo.grade == '2' }">二级股东</c:if>
				<c:if test="${ userDo.grade == '3' }">三级股东</c:if>
				<c:if test="${ userDo.grade == '4' }">四级股东</c:if>
				<c:if test="${ userDo.grade == '5' }">五级股东</c:if>
				<c:if test="${ userDo.grade == '6' }">六级股东</c:if>
				
				 </dd>
			</dl>
		</div>
		<div class="tright"
			onclick="">
			<img src="<c:url value ='/res-kuangji/images/QRcode.png'/>">
		</div>

	</div>
	
		<table cellpadding="0" cellspacing="0">
			<tbody>
				<tr>
					<td>
						<dl
							onclick="window.location.href ='<c:url value='/account/regIndex.html'/>'">
							<dt>
								<img src="<c:url value ='/res-kuangji/images/mitems_04.png'/>">
							</dt>
							<dd>注册</dd>
						</dl>
					</td>
					<td>
						<dl
							onclick="window.location.href ='<c:url value='/member/userIncome.html'/>'">
							<dt>
								<img src="<c:url value ='/res-kuangji/images/menus_02.png'/>">
							</dt>
							<dd>账户预览</dd>
						</dl>
					</td>
					<td>
						<dl
							onclick="window.location.href ='<c:url value ='/trade/myorder.html'/>'">
							<dt>
								<img
									src="<c:url value ='/res-kuangji/images/indexs_03.png'/>">
							</dt>
							<dd>我的订单</dd>
						</dl>
					</td>
				</tr>
				<tr>
					<td>
					<%--
						<dl
							onclick="window.location.href ='<c:url value='/cart/chongzhi.html'/>'">
							<dt>
								<img src="<c:url value ='/res-kuangji/images/mitems_05.png'/>">
							</dt>
							<dd>充值</dd>
						</dl>
						
						<dl
							onclick="window.location.href ='<c:url value='/pay/toChongzhi.html'/>'">
							<dt>
								<img src="<c:url value ='/res-kuangji/images/mitems_05.png'/>">
							</dt>
							<dd>充值</dd>
						</dl>
						
						 --%>
						<dl
							onclick="window.location.href ='<c:url value='/pay/toChongzhi.html'/>'">
							<dt>
								<img src="<c:url value ='/res-kuangji/images/mitems_05.png'/>">
							</dt>
							<dd>充值</dd>
						</dl>
					</td>
					<td>
						<dl
							onclick="javascript:window.location.href=&#39;<c:url value ='/barcode/toMybarcode.html?netWork=A&mobile=' />${userDo.userName}&#39;">
							<dt>
								<img src="<c:url value ='/res-kuangji/images/mitems_03.png'/>">
							</dt>
							<dd>我要推广</dd>
						</dl>
					</td>
					<td>
						<dl
							onclick="window.location.href ='<c:url value='/member/userManager.html'/>'">
							<dt>
								<img
									src="<c:url value ='/res-kuangji/images/fenxiao_manage.png'/>">
							</dt>
							<dd>推广管理</dd>
						</dl>
					</td>
				</tr>
				<tr>					
					<td>
						<dl
							onclick="window.location.href ='<c:url value='/member/amountDetail.html'/>'">
							<dt>
								<img
									src="<c:url value ='/res-kuangji/images/mitems_20.png'/>">
							</dt>
							<dd>资金明细</dd>
						</dl>
					</td>
						<td >
						<dl style="padding-top:28px; " onclick="window.location.href = '<c:url value='/kefu/qitai.html'/>'">
<%-- 						<dl style="background: #FF9800;padding-top:28px; " onclick="window.location.href = '<c:url value='/kefu/qitai.html'/>'"> --%>
							<dd style="color:#4325B9;font-weight: bold;font-size: 15px">开心藏宝</dd>
						</dl>
					</td>
					<td>
						<dl
							onclick="window.location.href ='<c:url value='/member/userSettle.html'/>'" >
							<dt>
								<img src="<c:url value ='/res-kuangji/images/mitems_01.png'/>">
							</dt>
							<dd>账户设置</dd>
						</dl>
					</td>
				
				</tr>
				
				<tr>
					<td>
						<dl
							onclick="window.location.href ='<c:url value='/member/withdraw.html'/>'">
							<dt>
								<img src="<c:url value ='/res-kuangji/images/mitems_05.png'/>">
							</dt>
							<dd>提现</dd>
						</dl>
					</td>
					<td>
						<dl onclick="javascript:window.location.href =&#39;<c:url value='/jingcai/jingpai.html'/>&#39;">
							<dt>
								<img src="<c:url value ='/res-kuangji/images/mitems_03.png'/>">
							</dt>
							<dd>一元竞拍</dd>
						</dl>
					</td>
					<td>
						<dl onclick="window.location.href = '<c:url value='/jingcai/tojingcai.html'/>'">
							<dt><img src="<c:url value ='/res-kuangji/images/nav_02.png'/>" /></dt>
							<dd>有奖竞猜</dd>
						</dl>
					</td>
				</tr>
				
				<tr>					
					
					<td>
						<dl
							onclick="window.location.href ='<c:url value='/notice/notice.html'/>'" >
							<dt>
								<img src="<c:url value ='/res-kuangji/images/mitems_06.png'/>">
							</dt>
							<dd>公告</dd>
						</dl>
					</td>
						<td>
						<dl
							onclick="window.location.href ='<c:url value='/quest/questView.html'/>'">
							<dt>
								<img
									src="<c:url value ='/res-kuangji/images/duihuan_detail.png'/>">
							</dt>
							<dd>留言反馈</dd>
						</dl>
					</td>
					
					<td>
						<dl
							onclick="window.location.href ='<c:url value='/login/loginout.html'/>'" >
							<dt>
								<img src="<c:url value ='/res-kuangji/images/mitems_06.png'/>">
							</dt>
							<dd>退出帐户</dd>
						</dl>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<%@ include file="../include/foot_kangji.jsp"%>

</body>
</html>
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


<title>Personal Center</title>


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


	<div class="memberInfo">
		<div class="tleft">
			<dl>
				<dt>
					<img src="<c:url value ='/res-kuangji/images/logo01.jpg'/>">
				</dt>
				<dd class="uname">${userDo.name}</dd>
				<dd class="uma">Welcome to Sociedad Deportiva Eibar</dd>
			</dl>
		</div>
		<div class="tright"
			onclick="">
			<img src="<c:url value ='/res-kuangji/images/QRcode.png'/>">
		</div>

	</div>
	<div class="main">
		<table cellpadding="0" cellspacing="0">
			<tbody>
				<tr>
					<td>
						<dl
							onclick="window.location.href ='<c:url value='/account/regIndex.html'/>'">
							<dt>
								<img src="<c:url value ='/res-kuangji/images/mitems_04.png'/>">
							</dt>
							<dd>Register</dd>
						</dl>
					</td>
					<td>
						<dl
							onclick="window.location.href ='<c:url value='/member/userIncome.html'/>'">
							<dt>
								<img src="<c:url value ='/res-kuangji/images/menus_02.png'/>">
							</dt>
							<dd>Preview account</dd>
						</dl>
					</td>
					<td>
						<dl
							onclick="window.location.href ='<c:url value ='/cart/jiesuan.html'/>'">
							<dt>
								<img
									src="<c:url value ='/res-kuangji/images/indexs_03.png'/>">
							</dt>
							<dd>Euro purchase</dd>
						</dl>
					</td>
				</tr>
				<tr>
					<td>
					
						<dl
							onclick="window.location.href ='<c:url value='/cart/chongzhi.html'/>'">
							<dt>
								<img src="<c:url value ='/res-kuangji/images/mitems_05.png'/>">
							</dt>
							<dd>Recharge</dd>
						</dl>
						<%--
						<dl
							onclick="window.location.href ='<c:url value='/pay/toChongzhi.html'/>'">
							<dt>
								<img src="<c:url value ='/res-kuangji/images/mitems_05.png'/>">
							</dt>
							<dd>充值</dd>
						</dl>
						
						
						<dl
							onclick="window.location.href ='<c:url value='/kjpay/toChongzhi.html'/>'">
							<dt>
								<img src="<c:url value ='/res-kuangji/images/mitems_05.png'/>">
							</dt>
							<dd>Recharge</dd>
						</dl>
						 --%>
					</td>
					<td>
						<dl
							onclick="javascript:window.location.href=&#39;<c:url value ='/barcode/toMybarcode.html?netWork=A&mobile=' />${userDo.userName}&#39;">
							<dt>
								<img src="<c:url value ='/res-kuangji/images/mitems_03.png'/>">
							</dt>
							<dd>promote</dd>
						</dl>
					</td>
					<td>
						<dl
							onclick="window.location.href ='<c:url value='/member/userManager.html'/>'">
							<dt>
								<img
									src="<c:url value ='/res-kuangji/images/fenxiao_manage.png'/>">
							</dt>
							<dd>Promotional manage</dd>
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
							<dd>Fund details</dd>
						</dl>
					</td>
					<td>
						<dl
							onclick="window.location.href ='<c:url value='/member/userSettle.html'/>'" >
							<dt>
								<img src="<c:url value ='/res-kuangji/images/mitems_01.png'/>">
							</dt>
							<dd>Account Setup</dd>
						</dl>
					</td>
					<td>
						<dl onclick="window.location.href = '<c:url value='/company/baozhang.html'/>'">
							<dt><img src="<c:url value ='/res-kuangji/images/mitems_07.png'/>" /></dt>
							<dd>Profit model</dd>
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
							<dd>Withdraw</dd>
						</dl>
					</td>
					<td>
						<dl
							onclick="javascript:window.location.href=&#39;<c:url value ='/company/mykefu.html' />13480667237&#39;">
							<dt>
								<img src="<c:url value ='/res-kuangji/images/mitems_03.png'/>">
							</dt>
							<dd>Help service</dd>
						</dl>
					</td>
					<td>
						<dl
							onclick="window.location.href ='<c:url value='/company/aboutUs.html'/>'">
							<dt>
								<img
									src="<c:url value ='/res-kuangji/images/duihuan_detail.png'/>">
							</dt>
							<dd>About us</dd>
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
							<dd>Announcement</dd>
						</dl>
					</td>
					<td>
						<dl onclick="window.location.href = '<c:url value='/company/zhidu.html'/>'">
							<dt><img src="<c:url value ='/res-kuangji/images/nav_02.png'/>" /></dt>
							<dd>Reward system</dd>
						</dl>
					</td>
					<td>
						<dl
							onclick="window.location.href ='<c:url value='/login/loginout.html'/>'" >
							<dt>
								<img src="<c:url value ='/res-kuangji/images/mitems_06.png'/>">
							</dt>
							<dd>Log out</dd>
						</dl>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<%@ include file="../include/foot_kangji.jsp"%>

</body>
</html>
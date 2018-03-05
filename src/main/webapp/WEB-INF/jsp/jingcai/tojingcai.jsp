<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">

<title>埃瓦尔足球俱乐部竞猜游戏</title>


<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">

     <script type="text/javascript" src="<c:url value ='/res-kuangji/js/jquery-2.1.1.min.js'/>"></script>
<link href="<c:url value ='/res-kuangji/css/myWealth.css'/>"
	rel="stylesheet" type="text/css" />
<style>
*{ margin:0; padding:0;}
body,div,dl,dt,dd,ul,ol,li,h1,h2,h3,h4,h5,h6,pre,form,fieldset,input,textarea,p,blockquote,th,td,font,span,b{margin:0;padding:0;} 
html{font-size: 100%;}
body{ font-size: 1.4rem;}
table {border-collapse:collapse;border-spacing:0;} 
fieldset,img {border:0} 
ol,ul{list-style:none} 
ol li,ul li,li{list-style:none} 

.time-item{
	padding:15px 0;
}
.time-item strong {
	background: #C71C60;
	color: #fff;
	line-height: 49px;
	font-size: 48px;
	font-family: Arial;
	padding: 0 10px;
	margin-top: 20px;
	border-radius: 5px;
	box-shadow: 1px 1px 3px rgba(0, 0, 0, 0.2);
}



.item-title .unit {
	background: none;
	line-height: 49px;
	font-size: 25px;
	padding: 10 10px;
	float: left;
}

a {
	text-decoration: none
}


.btn {
	margin: 2em auto; /*32px / 16px*/
}

.btn a {
	display: block;
}

.input_common_box1 {
	float: left;
	width: 30%;
	background: #ffffff;
	border: 1px solid #b9b9b9;
	color: #999;
	height: 0.675em; /*14px/16px*/
	font-size: 0.875em; /*14px/16px*/
	padding: 0.8125em 0.375em; /*6px/16px*/
}


body {
	/*background-image: url(images/bt.jpg);
	background-repeat: no-repeat;
	background-position: center top;*/
	background-color: #FF9800;

}
.list_box00{
 font-size:120%;
}
.list_box00 table{
  margin-top:10px;
  width:90%
}
</style>

<style type="text/css">
.tz_btn {
	width: 250px;
	height: 50px;
	background: blue;
	line-height: 50px;
	display: inline-block;
	margin: 20px 0 10px 0;
	font-size: 100%;
	color: #fff;
	border-radius: 8px;
	font-weight: bold;
}

.tz_btn_admin {
	width: 200px;
	height: 50px;
	background: #EECB3F;
	line-height: 50px;
	display: inline-block;
	margin: 0px 0 10px 0;
	font-size: 28px;
	color: #fff;
	border-radius: 8px;
	font-weight: bold;
}

.block6 .block6_2{
	margin: 20px 0;
	font-size:150%;
}
.block6 .block6_2 .block6_21{
	margin: 20px 0;
}
</style>

</head>
<body>
	
	<div id="container">
		<div class="top" >
		<b
			onclick="javascript:window.location.href=&#39;<c:url value='/redPack/personalCenter.html'/>&#39;"></b>
		<dd style="font-size: 50px">竞猜中心</dd>
		<span></span>
		<ul>
			<li class="index" onclick="">首 页</li>
			<li class="member" onclick="">会员中心</li>
			<li class="shoppingCart">购物车</li>
		</ul>
	</div>
		<h1 style="text-align:center; font-size:250%;margin: 30px">埃瓦尔足球俱乐部竞猜游戏</h1>
		
		<div align="center" style="width:100%">
			<img width="90%" height="350px" src="/res-kuangji/jingcaiImg/logo.gif">
		</div>
		<!--开奖部分-->

		<!--开奖部分-->

		<div class="block6">
			<!--搜索记录块开始-->
			<div class="block6_2">

				<div align="center" style="color: #FFFFFF;font-size:150%">

					当前第 <font color="red" id="qishu"> ${period} </font> 期
					竞猜倒计时：
					<div class="time-item">
						<strong id="oTime"> ${openTime}秒</strong>
					</div>
					<p>
						<a href="/jingcai/toxiazu.html"  class="tz_btn">立即下注</a>
						 <p style="color:red">5倍奖金！5倍奖金！</p>
					</p>

					<p></p>
					<div class="block6_21">
						<p style="color: #FFFFFF;padding-top:10px" >第<span id="oldperiod">${period}</span>期开奖结果</p>
						<p>
							<img src= '${numImage}' width="230" height="230"></img>
							<img src="${bigImage}" name="kj2" width="230"
								height="230" id="kj2" style="margin-top: 10px;" ></img>
						</p>

					</div>
				</div>
				<br>
				<!-- 未开始状态 -->
				<div class="list_box00" align="center">
				<p> 我的投注记录</p>
				<table  width="90%" border="1" cellspacing="1" bgcolor="#FFA953" >
						<tbody>
							<tr style="color:#fff; font-size:110%;">
								<td width="20%" bgcolor="#FF9900" height="35px"><div align="center">期数</div></td>
								<td width="20%" bgcolor="#FF9900"><div align="center">投注号</div></td>
								<td width="20%" bgcolor="#FF9900"><div align="center">开奖号</div></td>
								<td width="20%" bgcolor="#FF9900"><div align="center">投注额</div></td>
								<td width="20%" bgcolor="#FF9900"><div align="center">获奖</div></td>
							</tr>
						<c:forEach   var="user" items="${userList}" >
							<tr>
								<td width="128" bgcolor="#FFFFFF" scope="col"><div
										align="center">
										<b>${user.periods}</b>
									</div></td>
							
								<td bgcolor="#FFFFFF" scope="col"><div align="center">
										${user.betNumber}
									</div>
								</td>
								
								<td bgcolor="#FFFFFF" scope="col"><div align="center">
										${user.winNumber}
										
									</div>
								</td>
								
								<td bgcolor="#FFFFFF" scope="col"><div align="center">
										<b>${user.amount}</b>
									</div>
								</td>
								
								<td bgcolor="#FFFFFF" scope="col"><div align="center">
										<c:if test="${user.winStatus ==1 }">${user.amount * 5}</c:if>
										<c:if test="${user.winStatus ==0 }"> 0 </c:if>
									</div>
								</td>
								
							</tr>
							</c:forEach>

						</tbody>
					</table>
					
					<p>&nbsp;</p>
					<p> 历史开奖记录</p>
					<table  width="90%" border="1" cellspacing="1" bgcolor="#FFA953" >
						<tbody>
							<tr style="color:#fff; font-size:110%;">
								<td width="40%" bgcolor="#FF9900" height="35px"><div align="center">期数</div></td>
								<td width="30%" bgcolor="#FF9900"><div align="center">球星</div></td>
								<td width="30%" bgcolor="#FF9900"><div align="center">开奖号码</div></td>
							</tr>
						<c:forEach   var="win" items="${betList}" >
							<tr>
								<td width="128" bgcolor="#FFFFFF" scope="col"><div
										align="center">
										<b>${win.period}</b>
									</div></td>
								<td bgcolor="#FFFFFF" style="font-size: 20px;"><div
										align="center">
										<img src='${win.status}' width="100" height="100">
									</div></td>
								<td bgcolor="#FFFFFF" scope="col"><div align="center">
										<img src='${win.betNum}' width="50" height="49">
									</div></td>


								
							</tr>
							</c:forEach>

						</tbody>
					</table>
				</div>


			</div>
		</div>


		<div  class="block6_2"  style="font-size:150%;text-align:center;color:#ffa;  padding: 1rem 0">
			<p>全天24小时不间断开奖</p>
			<p>24小时开一次奖，场场爆满</p>
		</div>
		
			
			<div style="color: #000; font-size: 150%;  line-height: 27px; padding:10px; text-align: center;">
				<p>Copyright @ 埃瓦尔足球俱乐部</p>
			</div>
	
	</div>
	
<script type="text/javascript">
		var sum = 86400;
		var ISSUE = 126713;
		
		 $.post('<c:url value="/betResult/getOpenTime.html"/>', null, function(data) {
			if(data.result=='成功'){
				 sum = data.openTime;
				 parent.document.all['oTime'].innerHTML=sum + " 秒";
				 parent.document.all['qishu'].innerHTML=data.period;
				 parent.document.all['oldperiod'].innerHTML=data.period -1 ;
			}
		},"json"); 
		

		var str;
		var str2;
		var str3;
		var newstimer;

		function cDate(oSum) {
			var min = Math.floor(oSum / 60);
			if (min < 1) {
				min == 0
			}

			var sec = oSum % 60;
			str = min * 60 + sec

			str = +str + " 秒"

			return str
		}

		function submitForm() {
			if (sum <= 0) {
				parent.document.all['oTime'].innerHTML = "开奖中……";
				location.href="/jingcai/tojingcai.html"
			
			
			} else {
				sum = sum - 1
				//sum2=sum2-1
				cDate(sum)
				parent.document.all['oTime'].innerHTML = str;
			
			}
		}
		newstimer = setInterval("submitForm()", 1000)
	</script>


</body>
</html>
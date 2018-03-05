<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>埃瓦尔足球俱乐部竞猜游戏</title>

<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<script type="text/javascript"
	src="<c:url value ='/res-kuangji/js/jquery-2.1.1.min.js'/>"></script>
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
 font-size:120%
}
.list_box00 table{
  
}
</style>

<style type="text/css">
.tz_btn {
	width: 250px;
	height: 60px;
	background: BLUE;
	line-height: 50px;
	display: inline-block;
	margin: 20px 0 10px 0;
	padding-top:10px;
	font-size: 100%;
	color: #fff;
	border-radius: 8px;
	font-weight: bold;
}

.tz_btn_admin {
	width: 200px;
	height: 50px;
	background: blue;
	line-height: 50px;
	display: inline-block;
	margin: 0px 0 10px 0;
	font-size: 28px;
	color: #fff;
	border-radius: 8px;
	font-weight: bold;
	text-aling:center;
}

.block6 .block6_2{
	margin: 20px 0;
	font-size:150%;
}
.block6 .block6_2 .block6_21{
	margin: 20px 0;
}

.textinput{
	height: 1.6rem;
	width:80px;
}
.choose{
	align:center;
	
}
.choose table td{
	padding:5px;
	text-align:center;
}
.choose img{
	width:230px;
	height:230px
}
</style>


</head>
<body>
<div id="container">
		<div class="top">
		<b
			onclick="window.location.href = '<c:url value='/jingcai/tojingcai.html'/>'"></b>
		<dd>投注中心</dd>
		<span></span>
		<ul>
			<li class="index" onclick="">首 页</li>
			<li class="member" onclick="">会员中心</li>
			<li class="shoppingCart">购物车</li>
		</ul>
	</div>
	<div id="container" style="text-align:center">

		<h1 style="text-align:center; font-size:250%;margin: 30px">竞猜游戏-点击头像下注</h1>
		<div class="choose" align="center">
		<table>
		<tr>
			<td><a   class="tz_btn_admin" onclick="choose(1)"><img src="/res-kuangji/jingcaiImg/big_1.jpg"> <p>1号：贝克汉姆</a></td>
			<td><a  class="tz_btn_admin" onclick="choose(2)"><img src="/res-kuangji/jingcaiImg/big_2.jpg"> <p>2号：罗本</a></td>
			<td><img src="/res-kuangji/jingcaiImg/big_3.jpg"><p><a   class="tz_btn_admin" onclick="choose(3)">3号：罗纳尔多</a></td>
		</tr>
		<tr>
			<td><img src="/res-kuangji/jingcaiImg/big_4.jpg"> <p><a   class="tz_btn_admin" onclick="choose(4)">4号：梅西</a></td>
			<td><img src="/res-kuangji/jingcaiImg/big_5.jpg"> <p><a  class="tz_btn_admin" onclick="choose(5)">5号：C罗</a></td>
			<td><img src="/res-kuangji/jingcaiImg/big_6.jpg"> <p><a  class="tz_btn_admin" onclick="choose(6)">6号：齐达内</a></td>
		</tr>
		</table>
		</div>
		用户欧元：${account}
		<p style="padding-top:20px;font-size: 120%">
		 	当前投注号码：<strong id="betNumber"> </strong> 号
		 </p>
		
		<p style="padding-top:20px">
		   下注金额：  
<!-- 		   <select id="accountType" name="accountType" style=" width :90px;height:27px"> -->
<!-- 	            	<option value="eour" >欧元</option> -->
<!-- 	            	<option value="point" >积分</option> -->
<!-- 	            </select> -->
		   
		   <input class="textinput" type="hidden" id="eour" value="${account }" />
<%-- 		   <input class="textinput" type="hidden" id="point" value="${account.point }" /> --%>
		   <input class="textinput" type="number" id="amount" name="amount" />
		 </p>
		 <p><a href="#"  class="tz_btn" onclick="xiazhu()">立即投注</a></p>
		<div  class="block6_2"  style="font-size:150%;text-align:center;color:#ffa;  padding: 1rem 0">
			<p style="color:red;font-size: 110%;font-size: bold">5倍奖金!5倍奖金!5倍奖金!</p>
			<p>全天24小时不间断开奖</p>
			<p>24小时开一次奖，场场爆满</p>
		</div>
		
			
			<div style="color: #000; font-size: 150%;  line-height: 27px; padding:10px; text-align: center;">
				<p>Copyright @ 埃瓦尔足球俱乐部</p>
			</div>
	</div>
	
<script type="text/javascript">
var betNumber = 0;
function choose(num){
	betNumber = num;
	 parent.document.all['betNumber'].innerHTML= betNumber;
	
}
function  xiazhu(){
	 var eourt =  $('#account').val();
	 //var point = $('#point').val();
	 //var accounttype = $('#accountType').val();
	if(0 == betNumber){
			alert('请选择投注号码');
			return;
		}
		var amount = $('#amount').val();
		if('' == amount ){
			alert('请输入投注金额');
			return;
		}
		if( amount <=0  ){
			alert('请输入正确金额');
			$('#amount').val(1)
			return;
		}
		
		if(  parseFloat(amount) > parseFloat(eourt) ){
			alert('下注金币不足,请重新下注');
			return;
		}
// 		if( accounttype == 'point' && parseFloat(amount) > parseFloat(point) ){
// 			alert('下注积分不足,请重新下注');
// 			return;
// 		}
		
		var param =   {'betNumber' : betNumber,'amount':amount,'accountType': 'rmb'}; 
			
			$.post('<c:url value="/userBet/addBet.html"/>', param, function(data) {
				if(data.result=='成功'){
					alert("投注成功！ 第 " + data.period + "期,号码是："  + data.betNumber)
					window.location.href='<c:url value='/jingcai/tojingcai.html'/>'
				}else{
					alert(data.result)
				}
			},"json");
	}
</script>
</body>
</html>
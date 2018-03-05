<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>微交易</title>

	<meta http-equiv="Content-Type" content="text/html; charset=GBK">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no" />
    <meta name="format-detection" content="telephone=no">
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="apple-mobile-web-app-status-bar-style" content="black" />
    <script type="text/javascript" src="<c:url value ='/res-kuangji/js/jquery-2.1.1.min.js'/>"></script>

<style>
* {
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
}
*{ margin:0; padding:0;}
body{ font-size: 1.4rem;background-color: #333;}
table {border-collapse:collapse;border-spacing:0;} 
fieldset,img {border:0} 
ol,ul{list-style:none} 
ol li,ul li,li{list-style:none} 
a {
	text-decoration: none
}
.container{margin-top:80px;}

.memberInfo {
    width: 100%;
    height: 80px;
    float: left;
    background-color: #533F63;
    background-size: cover;
    position: fixed;
    top: 0;
}
.tleft {
    width: 100%;
    height: 55px;
    float: left;
    margin: 5px;
}
.tleft dl {
    width: 100%;
    height: auto;
    float: left;
}
.tleft dl dt {
    width: 50px;
    height: 50px;
    float: left;
    margin-left: 10px;
    margin-top: 10px;
    margin-right: 10px;
    border-radius: 50%;
    overflow: hidden;
    border: 1px solid #FFF;
}
.tleft dl dt img {
    width: 100%;
    border: none;
}
.tleft dl dd {
    width: auto;
    height: 20px;
    line-height: 20px;
    float: left;
    margin-right: 5px;
    margin-top: 20px;   
    background-color: #03A9F4;
    font-size: 10px;
    border-radius: 3px;
    padding-left: 10px;
    padding-right: 10px;
    color:#fff;
}

.goodslst {
    width: 100%;
    height: 40px;
    line-height: 40px;
    color:#fff;
    background-color:#333;
    border-bottom:1px blue solid ;
}

.goodslst li{
    width: 33%;
    float: left;
    height:100%;
    line-height: 100%;
    padding-top:8px;
    text-align: center;
}

.goodslst .title {
	background-color: #03A9F4;
    font-size: 1.2rem;
    border-radius: 3px;
    text-align: center;
    padding-right: 8px;
    padding-left: 8px;
    padding-bottom: 5px;
    padding-top: 5px;
}
.goodslst .goodsName {
     padding-left:10px;
     text-align: left;
}

</style>

</head>
<body>

	<div class="memberInfo">
		<div class="tleft">
			<dl>
				<dt>
					<img src="/res-kuangji/images/logo01.jpg">
				</dt>
				<dd>账户充值</dd>
			</dl>
		</div>
	</div>
	
	<div class="container">
	    <div style="height:20px;width: 100%"></div>		
		<ul class="goodslst">
			<li class="goodsName">
				<span>商品</span>
			</li>
			<li>
				<span>买涨</span>
			</li>
			<li>
				<span>买跌</span>
			</li>
		</ul>
		
		<ul class="goodslst" onclick="window.location.href='<c:url value='/qiquan/detail.html'/>'">
			<li class="goodsName" >
				<span>欧元兑美元</span>
			</li>
			<li>
				<span class="title">${maxMin.maxCurr}</span>
			</li>
			<li>
				<span class="title">${maxMin.minCurr}</span>
			</li>
		</ul>
								
	</div>
	
<script type="text/javascript">

</script>


</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="heightFull">
<head>

	<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no" />
    <meta name="format-detection" content="telephone=no">
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="apple-mobile-web-app-status-bar-style" content="black" />
    <meta name="keywords" content="" />
    <meta name="description" content="" />
    

<title>问题反馈</title>
<link href="<c:url value ='/res-kuangji/css/global.css'/>"
	rel="stylesheet" type="text/css">
<link href="<c:url value ='/res-kuangji/css/shoppingCart.css'/>"
	rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="<c:url value ='/res-kuangji/js/jquery-2.1.1.min.js'/>"></script>
<script type="text/javascript"
	src="<c:url value ='/res-kuangji/js/top.js'/>"></script>
<style type="text/css">
.btn {
	width: 100%;
	height: 50px;
	float: right;
	border: none;
	background-color: #E50011;
	line-height: 50px;
	text-align: center;
	color: #FFF;
	font-size: 1.6rem;
}
.heightFull{
	height: 100%;
	overflow: hidden;
}
.displayFlex {
    display: flex;
    flex-direction: column;
    background: #191c23;
}
#content{
	background: #20222e;
    width: 90%;
	color: #fff;
}
.top{
	background-color: #20222e;
	border: 0;
}
.total{
	padding: 15px 20px;
	background-color: transparent;
}
.total .btn {
    height: 40px;
    background: #facd02;
    line-height: 40px;
    display: block;
    color: #000;
    text-align: center;
    border-radius: 5px;
}
textarea::-webkit-input-placeholder{
    color:#fff;
}
textarea::-moz-placeholder{   /* Mozilla Firefox 19+ */
    color:#fff;
}
textarea:-moz-placeholder{    /* Mozilla Firefox 4 to 18 */
    color:#fff;
}
textarea:-ms-input-placeholder{  /* Internet Explorer 10-11 */ 
    color:#fff;
}
</style>
</head>
<body width="100%" class="heightFull displayFlex">
	<div class="top" style="width:100%">
		<b
			onclick="javascript:window.location.href=&#39;<c:url value='/redPack/personalCenter.html'/>&#39;"></b>
		<dd>问题反馈</dd>
		<span></span>

	</div>
	<div class="main">
		<div style="margin: 0; width: 100%">
			 <form  name="amtForm" action="<c:url value='/quest/saveQuest.html'/>" method="post">
			<div
				style="text-align: center;  margin: auto; padding-top: 20px; padding-bottom: 10px; border-bottom: thin dashed black; font-size: 18px;">
<!-- 				<input name="content" id="content" type="text" value=""  > -->
				<textarea name="content" id="content"  rows="18" cols="50" placeholder="请输入反馈内容..."></textarea>
			</div>
			 </form>
			
		</div>
		 <div style="margin-top: 20px; width: 100%">
				<table>
				<c:forEach items="${feedbackList}" var="row" varStatus="status">
              	<tr>
              	<td ><font color="red">问： ${row.question}</font></td>
              	</tr>
                 <tr>
              	<td height="32px"><font color="blue">答：${row.content}<br></font></td>
              	</tr> 
            
				</c:forEach>
				</table>
		</div>
		
	</div>
	<div class="total">
   	 <input class="btn" type="button" value="提交" onclick="quest()">
	</div>
</body>
<script>
    function quest() {
	    var amt = $("#content").val();
	    if(amt == ""){
		    alert("反馈内容不能为空");
		    return false;
		}
		document.forms[0].submit();
    }
    
</script>
</html>
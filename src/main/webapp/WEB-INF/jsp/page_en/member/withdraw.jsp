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


<title>User Withdraw</title>

<link href="<c:url value ='/res-kuangji/css/global.css'/>"
	rel="stylesheet" type="text/css" />
<link href="<c:url value ='/res-kuangji/css/bank_mobile.css'/>"
	rel="stylesheet" type="text/css" />
<link href="<c:url value ='/res-kuangji/css/hbtixian.css'/>"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="<c:url value ='/res-kuangji/js/jquery-2.1.1.min.js'/>"></script>
<script type="text/javascript"
	src="<c:url value ='/res-kuangji/js/top.js'/>"></script>
<script type="text/javascript"
	src="<c:url value ='/res-kuangji/js/hbtixian.js'/>"></script>
<style type="text/css">
div.cs-skin-border {
	background: transparent;
	font-size: 2em;
	font-weight: 700;
	max-width: 600px;
}

@media screen and (max-width: 30em) {
	.cs-skin-border {
		font-size: 1em;
	}
}

.cs-skin-border>span {
	border: 5px solid #000;
	border-color: inherit;
	transition: background 0.2s, border-color 0.2s;
}

.cs-skin-border>span::after, .cs-skin-border .cs-selected span::after {
	font-family: 'icomoon';
	content: '\e000';
}

.cs-skin-border ul span::after {
	content: '';
	opacity: 0;
}

.cs-skin-border .cs-selected span::after {
	content: '\e00e';
	color: #ddd9c9;
	font-size: 1.5em;
	opacity: 1;
	transition: opacity 0.2s;
}

.cs-skin-border.cs-active>span {
	background: #fff;
	border-color: #fff;
	color: #2980b9;
}

.cs-skin-border .cs-options {
	color: #2980b9;
	font-size: 0.75em;
	opacity: 0;
	transition: opacity 0.2s, visibility 0s 0.2s;
}

.cs-skin-border.cs-active .cs-options {
	opacity: 1;
	transition: opacity 0.2s;
}

.cs-skin-border ul span {
	padding: 1em 2em;
	backface-visibility: hidden;
}

.cs-skin-border .cs-options li span:hover, .cs-skin-border li.cs-focus span
	{
	background: #f5f3ec;
}
</style>
<META name="GENERATOR" content="MSHTML 9.00.8112.16684">
</HEAD>
<BODY>
	<DIV class="top">
		<B
			onclick="javascript:window.location.href=&#39;<c:url value='/redPack/personalCenter.html'/>&#39;"></B>
		<DD>Withdraw</DD>
		<SPAN></SPAN>
		<UL>
			<li class="index"
				onclick="javascript:window.location.href=&#39;<c:url value='/redPack/personalCenter.html'/>&#39;">Home</li>
			<li class="member"
				onclick="javascript:window.location.href=&#39;<c:url value='/redPack/personalCenter.html'/>&#39;">Personal
				Center</li>


		</UL>
	</DIV>


	<DIV class="box">
		<UL id="boxul">

			<LI><LABEL>Name：</LABEL> <INPUT id="bank_user"
				class="wtext wtext3" name="bank_user" value="${userDo.name }"
				readOnly="true" maxLength="20" type="text"></LI>
			<LI><LABEL>Account：</LABEL><INPUT id="bank_name"
				class="wtext wtext3" name="bank_name"
				value="${userDo.userInfoDo.bankName }" readOnly="true"
				maxLength="20" type="text"></LI>
			<LI><LABEL>Cardno：</LABEL><INPUT onblur="checkNo(this.value)"
				id="bank_no" class="wtext wtext4" readOnly="true"
				value="${userDo.userInfoDo.bankAccount }" name="bank_no"
				maxLength="19" type="tel"></LI>
			<LI><LABEL>ZhiFuBao：</LABEL><INPUT id="alipay" class="wtext wtext3"
				readOnly="true" name="alipay" type="text"
				value="${userDo.userInfoDo.zfbNumber }"></LI>
			<LI><LABEL>WeiXin：</LABEL><INPUT id="alipay" class="wtext wtext3"
				readOnly="true" name="alipay" type="text"
				value="${userDo.userInfoDo.weixiNumber }"></LI>
			<LI><LABEL>Amount：</LABEL> <!-- 			<INPUT class="wtext wtext5" -->
				<!-- 				type="number" placeholder="请输入提现金额(100的整数倍)"> --> <select
				class="wtext wtext5"
				style="width: 30%; height: 36px; font-size: 1.5rem;">
					<option value="100">100</option>
					<option value="200">200</option>
					<option value="500">500</option>
					<option value="1000">1000</option>
					<option value="2000">2000</option>
					<option value="5000">5000</option>
			</select></LI>
			<LI><LABEL id="user_money">Person Amount：${userAccount.amount} </LABEL></LI>
		</UL>
		<INPUT id="bank_id" name="bank_id" value="2" type="hidden"><INPUT
			id="bank" name="bank" type="hidden"><INPUT id="bank_branch"
			name="bank_branch" type="hidden"> <INPUT class="txbut"
			value="提现" type="button">
	</DIV>
	<DIV class="mengban"></DIV>

	<SCRIPT>var modifiable = "0";
        var ischange = "0";
        var bankchange = 0;
        var bankuserchange = 0;
        var banknochange = 0;
        function checkNo(value){
        var _reg = /^\d{16}|\d{19}$/;
        if (value && !(_reg.test(value))) {
        alert('请正确输入银行卡号');
        return;
        }
        }
        $('.txbut').on('click', function () {
        // if (new Date().getDay()) {
        var jine = $('.wtext5').val();
        var bank_id = $('#bank_id').val();
        var bank_user = $('.wtext3').val();
        var bank_no = $('.wtext4').val();
        var alipay = $('#alipay').val();
        var numStr = '${userAccount.amount}';
        var num = 0;
        if(numStr==''|| numStr=='null' ){
            num = 0;
        }else{
			num = parseFloat(numStr);
        }
        var _reg = /^\d{16}|\d{19}$/;
        if( bank_user == ''){

        alert('提现信息不完整，请填写姓名！');
        return false;
        }
        if ((bank_id == ''  || bank_no == '') && alipay == ''){

        alert('提现信息不完整，请完善个人资料设置中开户银行或支付宝信息！');
        return false;
        } if (bank_no && !(_reg.test(bank_no))) {
        alert('请正确输入银行卡号');
        return;
        }

        if (jine == ''){
        alert('请输入正确的金额');
        return false;
        }

        if (jine < 50){
        alert('提现金额不能低于50');
        return false;
        }

      //  alert(parseInt(num)  + "=" + parseInt(jine))
        if (parseInt(num) < parseInt(jine)){
        alert('余额不足,当前只有' + num + '元');
        return false;
        }

        if (jine != 100 && jine != 200 && jine != 500 && jine != 1000 && jine != 5000 && jine != 10000){
	        alert('提现金额每日限一次(以 100,200,500,1000,5000,10000)为单位');
	        return false;
        }

        if (jine >= 50 && jine % 50 == 0) {
        $.ajax({
	       		    type: 'post',
	                url: "<c:url value='/withdraw/save.html'/>",
	                data: {amount:jine},
	                dataType: 'json',
	                success: function (data) {
	                	alert('申请成功！请等待管理员确认！');
		            	var url ="<c:url value='/redPack/personalCenter.html'/>";
						setTimeout("window.location.href='"+url+"'", 1000); 
	                },
	                error: function (xhr, type) {
	                	alert('提现失败!请联系管理');
	                return false;
	                }
	        });
        }
      
        });
        function checkNum() {
        var num = parseFloat("0.00");
        var jine = parseFloat($('.wtext5').val());
        if (jine >= 50) {
        if (num >= jine) {
        if (jine % 50 == 0){

        $(".txbut").removeAttr("disabled");
        } else{
        alert('提现金额须为50的整数倍');
        return false;
        }

        } else {
        $(".txbut").attr({"disabled": "disabled"});
        alert('余额不足,当前只有' + num + '元');
        return false;
        }
        } else {
        alert('提现金额最低限额50');
        return false;
        }
        }

        function zhiHang(zhihang, id) {
        var zval = $('#bank').val() + ' ' + zhihang;
        $('#bank_id').val(id);
        $('#bank_branch').val(zhihang);
        $('.wtext2').val(zval);
        $("div.zhihang").hide();
        $('.mengban').hide();
        $('.box').show();
        bankchange = 1;
        //  $('body').addClass('overfl');
        }
</SCRIPT>
</BODY>
</HTML>

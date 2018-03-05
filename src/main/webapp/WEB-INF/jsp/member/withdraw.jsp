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


<title>用户提现</title>

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

</HEAD>
<BODY>
	<div class="top">
		<B
			onclick="javascript:window.location.href=&#39;<c:url value='/redPack/personalCenter.html'/>&#39;"></B>
		<dd>提现</dd>
		<span></span>
		<ul>
			<li class="index"
				onclick="javascript:window.location.href=&#39;<c:url value='/redPack/personalCenter.html'/>&#39;">首
				页</li>
			<li class="member"
				onclick="javascript:window.location.href=&#39;<c:url value='/redPack/personalCenter.html'/>&#39;">会员中心</li>
			<li class="shoppingCart"
				onclick="javascript:window.location.href=&#39;<c:url value='/cart/cart.html'/>&#39;">购物车</li>

		</ul>
	</div>


	<div class="box">
		<ul id="boxul">
			<LI><label>姓名：</label> <input id="bank_user"
				class="wtext wtext3" name="bank_user" value="${userDo.userInfoDo.realName }"
				 maxLength="20" type="text"></LI>
			<LI><label>开户银行：</label>
			<select
				id="bank_name"
				class="wtext wtext3"
				style="width: 30%; height: 36px; font-size: 1.5rem;">
					<option value="中国建设银行">中国建设银行</option> 
					<option value="中国工商银行">中国工商银行</option>
					<option value="中国农业银行">中国农业银行</option>
					<option value="中国银行">中国银行</option>
					<option value="中国人民银行 ">中国人民银行 </option>
					<option value="中国招商银行">中国招商银行</option>
					<option value="中国邮政储蓄银行">中国邮政储蓄银行</option>
					<option value="中国农业发展银行">中国农业发展银行</option>
					<option value="中信银行">中信银行</option>

					<option value="中国民生银行">中国民生银行</option>
					<option value="中国光大银行">中国光大银行</option>
					<option value="中国平安银行">中国平安银行</option>
					<option value="中国兴业银行">中国兴业银行</option>
					<option value="中国交通银行">中国交通银行</option>

			</select>	
			</li>
			<li><label>银行卡号：</label><input onblur="checkNo(this.value)"
				id="bank_no" class="wtext wtext4" 
				value="${userDo.userInfoDo.bankAccount }" name="bank_no"
				maxLength="19" type="number"></li>
			<li>
			<label>提现金额：</label> <input class="wtext wtext5" 
					type="number" placeholder="请输入提现金额(20的整数倍)" />  
			</li>
			<li>
				<label>二级密码：</label> <input id="passwordTwo" class="wtext wtext3" 
					type="text" placeholder="请输入二级密码" />
			</li>		
			<li><label id="user_money">当前余额：${amount} 欧 </label></li>
	</div>
	</ul>
	<input class="txbut" value="提现" type="button">
	</div>
	<div class="mengban"></div>

	<script>
        
        function checkNo(value){
	        var _reg = /^\d{16}|\d{19}$/;
	        if (value && !(_reg.test(value))) {
		        //alert('请正确输入银行卡号');
		        return;
        	}
        }
        
        $('.txbut').on('click', function () {       	

        	
	        var jine = $('.wtext5').val();
	        var bank_user = $('#bank_user').val();
	        var bank_no = $('#bank_no').val();
	        var passwordTwo = $('#passwordTwo').val();
	        var bankName = $('#bank_name').val();
	        var numStr = '${amount}';
	    	var token='${token}' ;
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
	        if ( bank_no == ''){
	        	alert('提现信息不完整，请完善开户银行账号信息！');
	        	return false;
	        } 
	        if ( passwordTwo == ''){
	        	alert('提现信息不完整，请输入二级密码！');
	        	return false;
	        } 
	       
	
	        if (jine == ''){
		        alert('请输入正确的金额');
		        return false;
	        }
	
	        if (jine < 20){
		        alert('提现金额不能低于20');
		        return false;
	        }
	
	      //  alert(parseInt(num)  + "=" + parseInt(jine))
	        if (parseInt(num) < parseInt(jine)){
	        alert('余额不足,当前只有' + num + '欧');
	        return false;
	        }
	
	        if ( jine % 20 != 0){
		        alert('提现金必须以20为单位');
		        return false;
	        }
	        if (jine >= 20 && jine % 20 == 0) {
	        	//alert('系统更新中!请联系管理员');
	        $.ajax({
		       		    type: 'post',
		                url: "<c:url value='/withdraw/save.html'/>",
		                data: {amount:jine,bankName:bankName,passwordTwo:passwordTwo,
		                	      bankNo:bank_no,bankUser:bank_user,token:token},
		                dataType: 'json',
		                success: function (data) {
		                	if(data.success ==  false){
		                		alert(data.result);
		                	}else{
	
			                	alert('申请成功！请等待管理员确认！');
				            	var url ="<c:url value='/redPack/personalCenter.html'/>";
								setTimeout("window.location.href='"+url+"'", 1000); 
		                	}
		                	
		                
		                },
		                error: function (xhr, type) {
		                	alert('提现失败!请联系管理员'+ data.result);
		                return false;
		                }
		        }); 
	        }
      
        });
     
        
      //获取验证码 ---1
      //  $getcode = $('#getcode');
     
        
      //  $getcode.on('click', function(){
      //  	if(mobile){
      //              countdown();
       //             sendMobileCheckCode(mobile);
             
      //  	}
      //  });
        //获取验证码----2计时
        
        var interval;
        function countdown() {
            $getcode.attr('disabled', 'disabled').html('120秒');
            var step = 120;
            interval = setInterval(function() {
                    if (!--step) {
                        clearInterval(interval);
                        $getcode.html('重新获取').attr('disabled', null);
                        return;
                    }
                    $getcode.html(step + '秒');
                }, 1000);
        };
        function countup() {
        	clearInterval(interval);
        	$getcode.html('重新获取').attr('disabled', null);
        	return;
        }; 
        
        //获取验证码----3请求
        function sendMobileCheckCode(mobile) {
    		var param = {"mobile":mobile,"checkMobile":true,mobileCode: mobileCode,"pageId":"withdraw"};
    		
    		$.post('<c:url value="/account/sendVirifyCode.html"/>', param, function(data) {
    			if(data.success && data.resultCode == '0'){
                    refCode(true);
    				HHN.popup(data.resultMessage);
    			}else{
                    refCode(false);
    				HHN.popup(data.resultMessage);
    				countup();
    			}
    		},"json");
    	}
</script>
</BODY>
</HTML>

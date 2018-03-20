<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html class="heightFull">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=no" />
<meta name="format-detection" content="telephone=no">
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<meta name="keywords" content="" />
<meta name="description" content="" />
<title>修改资料</title>
<jsp:include page="../common/common.jsp"></jsp:include>
<script type="text/javascript"
	src="<c:url value='/res/js/libs/jquery.min.js'/>?v=${jsversion}"></script>
<script type="text/javascript"
	src="<c:url value='/res/js/plugins/safe/jQuery.md5.js'/>?v=${jsversion}"></script>
<script type="text/javascript"	src="<c:url value='/res/js/plugins/province.js'/>?v=${jsversion}"></script>
<style type="text/css">
	.heightFull{
		height: 100%;
		overflow: hidden;
	}
	.displayFlex {
        display: flex;
        flex-direction: column;
        background: #191c23;
    }
	.top{
		background-color: #20222e;
	}
	.container{
		flex: 1;
		overflow-y: auto;
		color:#fff;
	}
	.top-nav h2{
		font-size: 1rem;
	}
	.form-btns{
		padding: 15px 20px;
	}
	input::-webkit-input-placeholder{
        color:#fff;
    }
    input::-moz-placeholder{   /* Mozilla Firefox 19+ */
        color:#fff;
    }
    input:-moz-placeholder{    /* Mozilla Firefox 4 to 18 */
        color:#fff;
    }
    input:-ms-input-placeholder{  /* Internet Explorer 10-11 */ 
        color:#fff;
    }
    #province, #city{
    	color:#fff;
    }
</style>
</head>

<body class="heightFull displayFlex">
	<div class="top-nav">
		<a class="link" href="javascript:;" onclick="javascript:window.location.href=&#39;<c:url value='/member/userSettle.html'/>&#39;">&lt;返回</a>
		<h2>个人资料</h2>
	</div>
	<input type="text" id="userInfoid" hidden="true" name="userInfoid" value="${ userDo.userInfoDo.id}" />
	<div class="exit-wrap container">
		<div class="item">
			<i class="icon"></i> <input type="text" placeholder="真实姓名"
				id="realName" name="realName" value="${ userDo.userInfoDo.realName}" />
		</div>

		<div class="item">
			<i class="icon"></i> <input type="tel" placeholder="手机号码" id="mobile"
				name="mobile" readOnly="true" value="${ userDo.userName}" />
		</div>



		<div class="item">
			<i class="icon icon-suggest"></i>
<%-- 			<c:if test="${userDo.parentDo.userName == '' }"> --%>
				<input id="suggest" type="tel" placeholder="推荐人手机号"
					value="${userDo.parentDo.name}" readonly="readonly"
					value="${userDo.parentDo.name}" />
<%-- 			</c:if> --%>
<%-- 			<c:if test="${empty userDo.parentDo.userName}"> --%>
<!-- 				<input id="suggest" type="tel" placeholder="推荐人手机号" /> -->
<%-- 			</c:if> --%>
		</div>

		<div class="item">
			<i class="icon icon-suggest"></i> <input id="idNumber"
				type="text" placeholder="身份证号"
				value="${userDo.userInfoDo.idCardNo}" />
		</div>
		
		<div class="item" id="provinceDiv" name="provinceDiv">
			<i class="icon icon-suggest"></i>
			<span style="color:#fff;font-size:14px">所在省：</span>
			<select id="province" name="province">
				<option value="-1">--请选择--</option>
			</select>
		</div>
		<div class="item">
			<i class="icon icon-suggest"></i>
			<span style="color:#fff;font-size:14px">所在市：</span>
			<select id="city" name="city">
				<option value="-1">--请选择--</option>
			</select>
		</div>
		
		<div class="item">
			<i class="icon icon-suggest"></i> <input id="Address"
				type="text" placeholder="收货地址"
				value="${userDo.userInfoDo.contactAddress}" />
		</div>
		<div class="item">
			<i class="icon icon-suggest"></i> <input id="weixiNumber"
				type="text" placeholder="微信号"
				value="${userDo.userInfoDo.weixiNumber}" />
		</div>

		<div class="item">
			<i class="icon icon-suggest"></i> <input id="zfbNumber" type="text"
				placeholder="支付宝" value="${ userDo.userInfoDo.zfbNumber}" />
		</div>

		<div class="item">
			<i class="icon icon-psw-new"></i> <input id="bankName"
				type="bankName" placeholder="开户行"
				value="${ userDo.userInfoDo.bankName}" />
		</div>
		<div class="item">
			<i class="icon icon-psw-new"></i> <input id="bankSubbranch"
				type="bankSubbranch" placeholder="开户支行"
				value="${ userDo.userInfoDo.bankSubbranch}" />
		</div>

		<div class="item">
			<i class="icon icon-psw"></i> <input id="bankAccount"
				type="bankAccount" placeholder="银行卡号"
				value="${ userDo.userInfoDo.bankAccount}" />
		</div>



	</div>
	<div class="form-btns">
		<a href="javascript:;" class="btn" id="submit">确定</a>
	</div>
	
	
	
	<script>
	$(function(){

		var proOpts = {
				cityInitVal:'${userDo.userInfoDo.city}',
				provinceInitVal:'${userDo.userInfoDo.province}',
				cityId:"city",
		        cityUrl:'<c:url value="/baseData/getCity.html"/>',		
				provinceId:"provinceDiv",
				provinceUrl:'<c:url value="/baseData/getProvince.html"/>'};
		var provinceSelector = new ProvinceSelector(proOpts);
		
		$("#submit").bind("click",function(){
			var mobile = $("#mobile").val();
	    	var realName = $("#realName").val();
	    	var weixiNumber = $("#weixiNumber").val();
	    	var zfbNumber = $("#zfbNumber").val();
	    	var bankName = $("#bankName").val();
	    	var zfbNumber = $("#zfbNumber").val();
	    	var bankSubbranch = $("#bankSubbranch").val();
	    	var bankAccount = $("#bankAccount").val();
	    	var userInfoId = $("#userInfoid").val();

	    	var province = $("#province").val();
	    	var city = $("#city").val();
	    	
			var param = {
					"id" : userInfoId,
					"mobile" : mobile,
					"realName" : realName,
					"weixiNumber" : weixiNumber,
					"zfbNumber" : zfbNumber,
					"bankName" : bankName,
					"bankSubbranch" : bankSubbranch,
					"bankAccount" : bankAccount,
					"idCardNo" : $("#idNumber").val(),
					"contactAddress" : $("#Address").val(),
					"province":province,
					"city":city
				
				};
			$.post('<c:url value="/common/modifyUpdata.html"/>', param, function(data) {
	    		
				if(data.result=='修改成功'){
					HHN.popup(data.result);
					var url ="<c:url value='/redPack/personalCenter.html'/>";
					setTimeout("window.location.href='"+url+"'", 1000); 
				}else{
					HHN.popup(data.result);
					$('#regBtn').removeAttr('disabled');
				}
			},"json");
		});
		});
	</script>	
	
</body>

</html>

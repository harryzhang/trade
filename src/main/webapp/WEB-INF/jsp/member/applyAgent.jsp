<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>

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
<title>代理申请</title>
<jsp:include page="../common/common.jsp"></jsp:include>
<script type="text/javascript"	src="${basePath}/res/js/libs/jquery.min.js?v=${jsversion}"></script>
<script type="text/javascript"	src="${basePath}/res/js/plugins/safe/jQuery.md5.js?v=${jsversion}"></script>
<script type="text/javascript"	src="${basePath}/res/js/plugins/province.js?v=${jsversion}"></script>
</head>

<body>
	<div class="top-nav">
		<a class="link" href="javascript:;" onclick="javascript:window.location.href=&#39;<c:url value='/member/userSettle.html'/>&#39;">&lt;返回</a>
		<h2>代理申请</h2>
	</div>
	<input type="text" id="userInfoid" hidden="true" name="userInfoid" value="${ userDo.userInfoDo.id}" />
	<div class="exit-wrap">
		<div class="item">
			<i class="icon"></i> 
			<span style="color:#333;font-size:14px">代理类型：</span>
			<select id="agentType" name="agentType">
				<option value="province">省代理</option>
				<option value="city">市级代理</option>
			</select>
		</div>

		<div class="item" id="provinceDiv" name="provinceDiv">
			<i class="icon icon-suggest"></i>
			<span style="color:#333;font-size:14px">代理哪个省：</span>
			<select id="province" name="province">
				<option value="-1">--请选择--</option>
			</select>
		</div>
		<div class="item">
			<i class="icon icon-suggest"></i>
			<span style="color:#333;font-size:14px">代理哪个市：</span>
			<select id="city" name="city">
				<option value="-1">--请选择--</option>
			</select>
		</div>



	</div>
	<div class="form-btns">
		<a href="javascript:;" class="btn" id="submit">确定</a>
	</div>
	
	
	
<script type="text/javascript">
	$(function(){

		var proOpts = {
				cityId:"city",
		        cityUrl:'<c:url value="/baseData/getCity.html"/>',		
				provinceId:"provinceDiv",
				provinceUrl:'<c:url value="/baseData/getProvince.html"/>'};
		
		var provinceSelector = new ProvinceSelector(proOpts);
		
		$("#submit").bind("click",function(){
			var agentType = $("#agentType").val();
	    	var province = $("#province").val();
	    	var city = $("#city").val();
			var param = {
					"city" : city,
					"province" : province,
					"agentType" : agentType				
				};
			
			$.post('<c:url value="/account/saveApplyAgent.html"/>', param, function(data) {
	    		
				if(data.result=='代理申请成功'){
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


<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="../include/top.jsp"%>
	<title>升级申请</title>
</head>
	<style>
.err{
  width: 100%;
  display: block;
  color: #f00;
  position: absolute;
  left: 0;
  padding: 0 80px;
  margin-top: -18px;
}
</style>
<body>
    <form action="listApply" method="post" >
    
    <c:if test="${empty msg}">
	<section class="p1">
		<article class="loan_inf">
			<div class="form-group db_f">
				
				祝贺您申请成功！</br>
				   请你及时到银行给您的上家办理汇款或转账、支付宝、微信！
				  您的上家只有在收到您的款项之后才能够给你升级！   
								
			</div>
			<div class="form-group db_f">
				进入等待审核程序，您也可以电话或QQ联系您的上家予以确认				
			</div>
		</article>
		
		<!--
		<article class="loan_inf">
			<h3 class="inf_tip"><i class="t2"></i>上家信息</h3>
			<div class="form-group db_f">
				<label for="" class="lab">接受人姓名：</label>
				<input type="text" id="bank" readonly="readonly"  class="txt bf1 db" name="realName"  value="${recieverUserInfo.realName}" >
			</div>
			<div class="form-group db_f">
				<label for="" class="lab">手机号码：</label>
				<input type="text" id ="mobile"  readonly="readonly"   class="txt bf1 db"  name="mobile" value="${ recieverUserInfo.mobile }"/>
			</div>
			<div class="form-group db_f">
				<label for="" class="lab">当前级别：</label>
				<input type="text" id ="qqNumber" readonly="readonly" class="txt bf1 db"  name="" value="${recieverUser.grade}"/>
			</div>
			<div class="form-group db_f">
				<label for="" class="lab">开户姓名：</label>
				<input class="txt bf1 db" id="email" readonly="readonly" type="email"  name="openAccount" value="${recieverUserInfo.openAccount}"/>
			</div>
			<div class="form-group db_f">
				<label for="" class="lab">银行账号:</label>
				<input type="text" readonly="readonly" id ="xm2" class="txt bf1 db"  name="bankAccount" value="${recieverUserInfo.bankAccount }"/>
			</div>
			<div class="form-group db_f">
				<label for="" class="lab">开户省份：</label>
				<input type="text" id="area" readonly="readonly" class="txt bf1 db" name="province"  value="${recieverUserInfo.province}" >
			</div>
			<div class="form-group db_f">
				<label for="" class="lab">开户城市：</label>
				<input type="text" id="addr"  readonly="readonly" name="city" class="txt bf1 db" value="${recieverUserInfo.city}">
			</div>
			<div class="form-group db_f">
				<label for="" class="lab">微信帐号：</label>
				<input type="text" id="addr" readonly="readonly"  name="weixiNumber" class="txt bf1 db" value="${recieverUserInfo.weixiNumber}">
			</div>
			<div class="form-group db_f">
				进入等待审核程序，您也可以电话或QQ联系您的上家予以确认				
			</div>
		</article>
		  -->
	</section>
	</c:if>
	<c:if test="${not empty msg}">
	   <div class="form-group db_f">
				${msg}				
		</div>
	</c:if>
	<section class="p1" style="padding: 10px 10px 60px;">
			<a href="javascript:void(0);" id="apply" class="apply">完成</a>
	</section>
	</form>
	<%@ include file="../include/foot.jsp"%>
	<script>
	$(function(){
		$("#apply").bind('click', function(){
			$(".err").remove();
			document.forms[0].submit();
		})
	})
	</script>
</body>
</html>
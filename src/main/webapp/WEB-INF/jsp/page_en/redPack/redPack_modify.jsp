<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
	<head>
		<%@ include file="../include/top.jsp"%>
		<title>资料修改</title>
	</head>
<style>
.err {
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
	<form action="modifyUpdata" method="post" >
		<input type="hidden" name="id" value="${userDo.userInfoDo.id}"/>
		<input type="hidden" name="userId" value="${userDo.userInfoDo.userId}"/>
		<section class="p1">
		<article class="loan_inf">
			<h3 class="inf_tip"><i class="t1"></i>基础信息</h3>
			<div class="form-group db_f">
				<label for="" class="lab">真实姓名：</label>
				<input type="text" id="realName" placeholder="请输入真实姓名"  class="txt bf1 db" name="realName"  value="${userDo.userInfoDo.realName}" >
			</div>
			<div class="form-group db_f">
				<label for="" class="lab">身份证号：</label>
				<input type="text" id ="idCardNo"  placeholder="请输入身份证号码"   class="txt bf1 db"  name="idCardNo" value="${userDo.userInfoDo.idCardNo}"/>
			</div>
			<div class="form-group db_f">
				<label for="" class="lab">手机号码：</label>
				<input type="text" id ="mobile"  readonly="readonly"  class="txt bf1 db"  name="mobile" value="${userDo.userName}"/>
			</div>
			<div class="form-group db_f">
				<label for="" class="lab">QQ号码：</label>
				<input type="text" id ="qqNumber"   placeholder="请输入QQ号码" class="txt bf1 db"  name="qqNumber" value="${userDo.userInfoDo.qqNumber}"/>
			</div>
			<div class="form-group db_f">
				<label for="" class="lab">微信号:</label>
				<input type="text"  id ="weixiNumber"  placeholder="请输入微信号"  class="txt bf1 db"  name="weixiNumber" value="${userDo.userInfoDo.weixiNumber }"/>
			</div>
			<div class="form-group db_f">
				<label for="" class="lab">用户邮箱:</label>
				<input type="text"  id ="eMail"  placeholder="请输入用户邮箱"  class="txt bf1 db"  name="eMail" value="${userDo.userInfoDo.eMail }"/>
			</div>
			<div class="form-group db_f">
				<label for="" class="lab">联系地址：</label>
				<input type="text" id="contactAddress"  placeholder="例如：广东省深圳市罗湖区XX小区8栋8号"  class="txt bf1 db"   name="contactAddress"  value="${userDo.userInfoDo.contactAddress }">
			</div>
			
		</article>
		
		
		<article class="loan_inf">
			<h3 class="inf_tip"><i class="t2"></i>银行信息</h3>
			<div class="form-group db_f">
				<label for="" class="lab">开户银行：</label>
				<input type="text" id="bankName" placeholder="请输入开户银行"  class="txt bf1 db" name="bankName"  value="${userDo.userInfoDo.bankName}" >
			</div>
			<div class="form-group db_f">
				<label for="" class="lab">开户姓名：</label>
				<input type="text"  id="openAccount" placeholder="请输入有开户姓名"class="txt bf1 db" name="openAccount" value="${userDo.userInfoDo.openAccount}"/>
			</div>
			<div class="form-group db_f">
				<label for="" class="lab">银行账号:</label>
				<input type="text"  id ="bankAccount"  placeholder="请输入银行账号" class="txt bf1 db"  name="bankAccount" value="${userDo.userInfoDo.bankAccount }"/>
			</div>
			<div class="form-group db_f">
				<label for="" class="lab">开户省份：</label>
				<input type="text" id="province"  placeholder="请输入开户省份" class="txt bf1 db" name="province"  value="${userDo.userInfoDo.province}" >
			</div>
			<div class="form-group db_f">
				<label for="" class="lab">开户城市：</label>
				<input type="text" id="city"  placeholder="请输入开户城市" class="txt bf1 db" name="city"  value="${userDo.userInfoDo.city}">
			</div>
			<div class="form-group db_f">
				<label for="" class="lab">开户银行点：</label>
				<input type="text" id="bankSubbranch" placeholder="请输入开户银行点"  name="bankSubbranch" class="txt bf1 db" value="${userDo.userInfoDo.bankSubbranch}">
			</div>
		</article>
		<article class="loan_inf">
			<h3 class="inf_tip">
				<i class="t3"></i>其他支付方式				
			</h3>
			<div class="form-group db_f">
				<label for="" class="lab">支付宝：</label>
				<input type="text"  id="zfbNumber" placeholder="请输入支付宝帐号"  class="txt bf1 db" name="zfbNumber"  value="${userDo.userInfoDo.zfbNumber }"/>
				
			</div>
			<div class="form-group db_f">
				<label for="" class="lab">财付通：</label>
				<input type="text" id ="cftNumber" placeholder="请输入财付通帐号"   class="txt bf1 db"  name="cftNumber" value="${userDo.userInfoDo.cftNumber }"/>
			</div>
		</article>
	</section>
	
	<section class="p1" style="padding: 10px 10px 60px;">
			<a href="javascript:void(0);" id="apply" class="apply">确定修改</a>
	</section>
	</form>
	<%@ include file="../include/foot.jsp"%>
	<script type="text/javascript" src="${fileServerUrl }/res/js/idCard.js?v=${jsversion}"></script>
	<script>
	$(function(){
		   var validateRealName = function(){
				var temp = $("#realName");
				if(temp.val()==0){
					temp.parent().after('<em class="err bs" style="z-index:0;">请输入真实姓名</em>');
					return false;
				}else if (!/^[\u4e00-\u9fa5]+$/.test(temp.val())){
					temp.parent().after('<em class="err bs" style="z-index:0;">请填写中文名字</em>');
				}else{
					return true;
				}
			};
			var validateIdCardNo = function(){
				var temp = $("#idCardNo");
				if(temp.val()==0){
					temp.parent().after('<em class="err bs" style="z-index:0;">请输入身份证号</em>');
					return false;
				}else if (!isIdCardNo(temp.val())) {
					temp.parent().after('<em class="err bs" style="z-index:0;">身份证号格式有误</em>');
					return false;
				}else{
					return true;
				}
			};
			var validateMobile = function(){
				var temp = $("#mobile");
				if(temp.val()==0){
					temp.parent().after('<em class="err bs" style="z-index:0;">请输入手机号码</em>');
					return false;
				}else if (!/^0{0,1}(13[0-9]|15[0-9]|18[0-9]|14[0-9]|17[0-9])[0-9]{8}$/.test(temp.val())){
					temp.parent().after('<em class="err bs" style="z-index:0;">手机号码格式有误</em>');
				}else{
					return true;
				}
			};	
			var validateQqNumber = function(){
				var temp = $("#qqNumber");
				if(temp.val()==0){
					temp.parent().after('<em class="err bs" style="z-index:0;">请输入QQ号码</em>');
					return false;
				}else{
					return true;
				}
			};			
			var validateWeixiNumber = function(){
				var temp = $("#weixiNumber");
				if(temp.val()==0){
					temp.parent().after('<em class="err bs" style="z-index:0;">请输入微信号码</em>');
					return false;
				}else{
					return true;
				}
			};			
			var validateEMail = function(){
				var temp = $("#eMail");
				if(temp.val()==0){
					temp.parent().after('<em class="err bs" style="z-index:0;">请输入用户邮箱</em>');
					return false;
				}else if (!/^(\w)+(\.\w+)*@(\w)+((\.\w+)+)$/.test(temp.val())){
					temp.parent().after('<em class="err bs" style="z-index:0;">用户邮箱格式错误</em>');
					return false;
				}else{
					return true;
				}
			};			
			var validateContactAddress = function(){
				var temp = $("#contactAddress");
				if(temp.val()==0){
					temp.parent().after('<em class="err bs" style="z-index:0;">请输入联系地址</em>');
					return false;
				}else{
					return true;
				}
			};			
			var validateBankName = function(){
				var temp = $("#bankName");
				if(temp.val()==0){
					temp.parent().after('<em class="err bs" style="z-index:0;">请输入开户银行</em>');
					return false;
				}else if (!/^[\u4e00-\u9fa5]+$/.test(temp.val())){
					temp.parent().after('<em class="err bs" style="z-index:0;">请填写中文</em>');
					return false;
				}else{
					return true;
				}
			};			
			var validateOpenAccount = function(){
				var temp = $("#openAccount");
				if(temp.val()==0){
					temp.parent().after('<em class="err bs" style="z-index:0;">请输入开户姓名</em>');
					return false;
				}else if (!/^[\u4e00-\u9fa5]+$/.test(temp.val())){
					temp.parent().after('<em class="err bs" style="z-index:0;">请填写中文名字</em>');
				}else{
					return true;
				}
			};
			var validateBankAccount = function(){
				var temp = $("#bankAccount");
				if(temp.val()==0){
					temp.parent().after('<em class="err bs" style="z-index:0;">请输入银行账号</em>');
					return false;
				}else if (!/^[1-9]\d*$/g.test(temp.val())){
					temp.parent().after('<em class="err bs" style="z-index:0;">银行账号格式错误</em>');
					return false;
				}else{
					return true;
				}
			};	
			var validateProvince = function(){
				var temp = $("#province");
				if(temp.val()==0){
					temp.parent().after('<em class="err bs" style="z-index:0;">请输入开户省份</em>');
					return false;
				}else if (!/^[\u4e00-\u9fa5]+$/.test(temp.val())){
					temp.parent().after('<em class="err bs" style="z-index:0;">请填写中文</em>');
					return false;
				}else{
					return true;
				}
			};
			var validateCity = function(){
				var temp = $("#city");
				if(temp.val()==0){
					temp.parent().after('<em class="err bs" style="z-index:0;">请输入开户市区</em>');
					return false;
				}else if (!/^[\u4e00-\u9fa5]+$/.test(temp.val())){
					temp.parent().after('<em class="err bs" style="z-index:0;">请填写中文</em>');
					return false;
				}else{
					return true;
				}
			};
			var validateBankSubbranch = function(){
				var temp = $("#bankSubbranch");
				if(temp.val()==0){
					temp.parent().after('<em class="err bs" style="z-index:0;">请输入银行支行</em>');
					return false;
				}else if (!/^[\u4e00-\u9fa5]+$/.test(temp.val())){
					temp.parent().after('<em class="err bs" style="z-index:0;">请填写中文</em>');
					return false;
				}else{
					return true;
				}
			};
			var validateZfbNumber = function(){
				var temp = $("#zfbNumber");
				if(temp.val()==0){
					temp.parent().after('<em class="err bs" style="z-index:0;">请输入支付宝</em>');
					return false;
				}else{
					return true;
				}
			};
			var validateCftNumber = function(){
				var temp = $("#cftNumber");
				if(temp.val()==0){
					temp.parent().after('<em class="err bs" style="z-index:0;">请输入财付通</em>');
					return false;
				}else{
					return true;
				}
			};
			
			$("#realName").blur(validateRealName);
			
			$("#mobile").blur(validateMobile);
			$(".txt,input,select").focus(function(){
				$(this).parent().next('.err').remove();
			});
			
		$("#apply").bind('touchstart', function(){
			$(".err").remove();
			var a =0;
			if(!validateRealName()) a++;
			if(!validateIdCardNo()) a++;
			//if(!validateMobile()) a++;
			//if(!validateQqNumber()) a++;
			//if(!validateWeixiNumber()) a++;
			//if(!validateEMail()) a++;
			//if(!validateContactAddress()) a++;
			//if(!validateBankName()) a++;
			//if(!validateOpenAccount()) a++;
			//if(!validateBankAccount()) a++;
			//if(!validateProvince()) a++;
			//if(!validateCity()) a++;
			//if(!validateBankSubbranch()) a++;
			//if(!validateZfbNumber()) a++;
			//if(!validateCftNumber()) a++;
			if(a==0){ 
				document.forms[0].submit();
			}
		})
	})
	</script>
</body>
</html>
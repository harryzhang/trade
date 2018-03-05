<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>系统设置</title>
	<meta name="viewport" content="width=device-width,inital-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=nos"/>
	<link href='<c:url value="/res/js/plugins/modal/modal.css?"/>${jsversion}' type="text/css" rel="stylesheet">
	
	<link rel="stylesheet" href='<c:url value="/res/css/base.css?v="/>${cssversion}' />
	<link rel="stylesheet" href='<c:url value="/res/css/exit.css?v="/>${cssversion}' />
	<link rel="stylesheet" href='<c:url value="/res/css/main.css?v="/>${cssversion}'/>

	
	<script type="text/javascript" src='<c:url value="/res/js/libs/zepto.min.js?v="/>${jsversion}' ></script>
	<script type="text/javascript" src='<c:url value="/res/js/global.js?v="/>${jsversion}' ></script>
	<script type="text/javascript" src='<c:url value="/res/js/plugins/modal/modal.js?v="/>${jsversion}'></script>
	
	<script>
	$(function(){
			$("#regBtn").bind("click",function(){
				var eourAmount = $("#eourAmount").val();
				if(eourAmount==null || eourAmount.length==0){
					HHN.popup("请输入股价");
					return;
				}
				
				var eourRate = $("#eourRate").val();
				if(eourRate==null || eourRate.length==0){
					HHN.popup("请输汇率");
					return;
				}
				var staticOneRate = $("#staticOneRate").val();
				if(staticOneRate==null || staticOneRate.length==0){
					HHN.popup("请输入静态分红");
					return;
				}
				var manageRate = $("#manageRate").val();
				if(manageRate==null || manageRate.length==0){
					HHN.popup("管理提成");
					return;
				}
				var refferCenter = $("#refferCenter").val();
				if(refferCenter==null || refferCenter.length==0){
					HHN.popup("请输报单中心金额");
					return;
				}
				var cityRate = $("#cityRate").val();
				if(cityRate==null || cityRate.length==0){
					HHN.popup("请输入城市汇率");
					return;
				}
				var provinceRate = $("#provinceRate").val();
				if(provinceRate==null || provinceRate.length==0){
					HHN.popup("请输入省会汇率");
					return;
				}
				var sentRate = $("#sentRate").val();
				if(sentRate==null || sentRate.length==0){
					HHN.popup("请输入积分汇率");
					return;
				}
				var sentRate = $("#sentRate").val();
				if(sentRate==null || sentRate.length==0){
					HHN.popup("请输入积分汇率");
					return;
				}
				var sentRate = $("#sentRate").val();
				if(sentRate==null || sentRate.length==0){
					HHN.popup("请输入积分汇率");
					return;
				}
				var ifPram = $("#ifPram").val();
				if(ifPram==null || ifPram.length==0){
					HHN.popup("请输入是否促销");
					return;
				}
				
				var manageEourPointRate = $("#manageEourPointRate").val();
				if(manageEourPointRate==null || manageEourPointRate.length==0){
					HHN.popup("请输入管理积分与现金比例");
					return;
				}
				
				var promGuquan = $("#promGuquan").val();
                
				
				$(this).attr('disabled',true);
				/**
				var options = {type:"POST",url:"<c:url value='/param/setPayMoney.html'/>",data:{payMoney:payMoney,referrerMoney:referrerMoney}};
				
				ajaxRequest(options,function(data){
					if(data.result=='设置会费成功'){
						popWindow(data.result);
						var url ="${loginServerUrl }/redPack/personalCenter.html";
						setTimeout("window.location.href='"+url+"'", 1000); 
					}else{
						popWindow(data.result);
						$('#regBtn').removeAttr('disabled');
					}
				});
				**/
				var param ={eourAmount:eourAmount,eourRate:eourRate,
						staticOneRate:staticOneRate,manageRate:manageRate,
						refferCenter:refferCenter,cityRate:cityRate,
						provinceRate:provinceRate,sentRate:sentRate,
						ifProm:ifProm,
						manageEourPointRate:manageEourPointRate,
						promGuquan:promGuquan
				
				};
				$.post('<c:url value="/param/setSetting.html"/>', param, function(data) {
		    		
					if(data.result=='设置成功'){
						HHN.popup(data.result);
						var url ="${loginServerUrl }/redPack/personalCenter.html";
						setTimeout("window.location.href='"+url+"'", 1000); 
					}else{
						HHN.popup(data.result);
						$('#regBtn').removeAttr('disabled');
					}
				},"json");
			});
		});
	</script>	
</head>

<body>
	
	<div class="top-nav">
        <a class="link"  href="<c:url value='/redPack/personalCenter.html'/>" onclick="history.back();">&lt;返回</a>
        <h2>设置入会金额</h2>
    </div>
    
	<form action="${loginServerUrl }/param/setSetting.do"  method="post">
	<div class="exit-wrap">
		<div class="item">
				<i style="display: block;  height: 20px; font-size:12px;font-weight:bold; color:black; -webkit-background-size: 140px auto;  background-size: 140px auto;    margin-right: 5px;">股价：</i>
				<input type="number" name="eourAmount" id="eourAmount" placeholder="请输入股价" value="${eourAmount}">
		</div>
		<div class="item">
				<i style="display: block;  height: 20px; font-size:12px;font-weight:bold; color:black; -webkit-background-size: 140px auto;  background-size: 140px auto;    margin-right: 5px;" >欧元汇率：</i>
				<input type="number" name="eourRate" id="eourRate" placeholder="欧元汇率"  value="${eourRate}">
		</div>
		<div class="item">
				<i style="display: block;  height: 20px; font-size:12px;font-weight:bold; color:black; -webkit-background-size: 140px auto;  background-size: 140px auto;    margin-right: 5px;">静态分红%：</i>
				<input type="number" name="staticOneRate" id="staticOneRate" placeholder="静态分红" value="${staticOneRate}">
		</div>
		<div class="item">
				<i style="display: block;  height: 20px; font-size:12px;font-weight:bold; color:black; -webkit-background-size: 140px auto;  background-size: 140px auto;    margin-right: 5px;" >管理提成%：</i>
				<input type="number" name="manageRate" id="manageRate" placeholder="管理提成%"  value="${manageRate}">
		</div>
		<div class="item">
				<i style="display: block;  height: 20px; font-size:12px;font-weight:bold; color:black; -webkit-background-size: 140px auto;  background-size: 140px auto;    margin-right: 5px;">报单中心金额：</i>
				<input type="number" name="refferCenter" id="refferCenter" placeholder="报单中心金额" value="${refferCenter}">
		</div>
		<div class="item">
				<i style="display: block;  height: 20px; font-size:12px;font-weight:bold; color:black; -webkit-background-size: 140px auto;  background-size: 140px auto;    margin-right: 5px;" >城代提成%：</i>
				<input type="number" name="cityRate" id="cityRate" placeholder="城代提成%"  value="${cityRate}">
		</div>
		<div class="item">
				<i style="display: block;  height: 20px; font-size:12px;font-weight:bold; color:black; -webkit-background-size: 140px auto;  background-size: 140px auto;    margin-right: 5px;">省代提成%：</i>
				<input type="number" name="provinceRate" id="provinceRate" placeholder="省代提成%" value="${provinceRate}">
		</div>
		<div class="item">
				<i style="display: block;  height: 20px; font-size:12px;font-weight:bold; color:black; -webkit-background-size: 140px auto;  background-size: 140px auto;    margin-right: 5px;" >积分比率%：</i>
				<input type="number" name="sentRate" id="sentRate" placeholder="积分比率%"  value="${sentRate}">
		</div>
		<div class="item">
				<i style="display: block;  height: 20px; font-size:12px;font-weight:bold; color:black; -webkit-background-size: 140px auto;  background-size: 140px auto;    margin-right: 5px;" >是否促销：</i>
				<input type="number" name="ifPram" id="ifPram" placeholder="是否促销(1：是  0否)"  value="${ifProm}">
		</div>
		<div class="item">
				<i style="display: block;  height: 20px; font-size:12px;font-weight:bold; color:black; -webkit-background-size: 140px auto;  background-size: 140px auto;    margin-right: 5px;" >买多少赠送股权：</i>
				<input type="number" name="promGuquan" id="promGuquan" placeholder="买多少赠送股权（2就是2送一）"  value="${promGuquan}">
		</div>
		
		<div class="item">
				<i style="display: block;  height: 20px; font-size:12px;font-weight:bold; color:black; -webkit-background-size: 140px auto;  background-size: 140px auto;    margin-right: 5px;" >管理提多金币：积分</i>
				<input type="number" name="manageEourPointRate" id="manageEourPointRate" placeholder="管理提多金币：积分"  value="${manageEourPointRate}">
		</div>
		
		
		<div class="item">
				<i style="display: block;  height: 20px; font-size:12px;font-weight:bold; color:black; -webkit-background-size: 140px auto;  background-size: 140px auto;    margin-right: 5px;" >积分比率%：</i>
				<input type="number" name="promPoint" id="promPoint" placeholder="促销赚送金币"  value="${promPoint}">
		</div>
	
	
		
	</div>
	</form>	
	<div class="form-btns">
    		<a href="javascript:;" class="btn"   id="regBtn"  >保存</a>
	</div>
</body>
</html>

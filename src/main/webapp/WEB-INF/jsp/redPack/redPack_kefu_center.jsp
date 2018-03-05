<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
   	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no" />
    <meta name="format-detection" content="telephone=no">
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="apple-mobile-web-app-status-bar-style" content="black" />
    <meta name="keywords" content="" />
    <meta name="description" content="" />
    <title>个人中心</title>
	<jsp:include page="../common/common.jsp"></jsp:include>
</head>

<body class="bg">
<c:if test="${platform ne 'android' && platform ne 'ios' && platform ne 'pad'}">
    <div class="top-nav">
        <a class="link"  href="javascript:;" onclick="history.back();">&lt;返回</a>
        <h2>个人中心</h2>
    </div>
</c:if>
    <div class="main-panel user-main-panel">
        <div class="avator" style="background-image: url(${basePath}/res/images/avator-white.png);">
        </div>
        <p class="note">${userAccount.userShow}</p>
        <div class="info">
            <p>登录用户：<i>${userDo.name }</i></p>
            <p>会员状态：<i>
            				<c:if test="${userDo.status>0}">正式会员</c:if>
				   			<c:if test="${userDo.status == 0}">临时式会员</c:if>
                         </i>
            </p>
        </div>
    </div>



	<div class="menu-group personal-ico">
     	<a href="<c:url value ='/barcode/toMybarcode.html?netWork=A&mobile=' />${userDo.userName}" class="item">
            <i class="icon icon-no1"></i>
            <p class="text">我的A网推荐二维码</p>
        </a>
        <a href="<c:url value ='/barcode/toMybarcode.html?netWork=B&mobile=' />${userDo.userName}" class="item">
            <i class="icon icon-no1"></i>
            <p class="text">我的B网推荐二维码</p>
        </a>
        <a href="<c:url value ='/wallet/listSk.html' />" class="item">
            <i class="icon icon-no1"></i>
            <p class="text">收款记录</p>
        </a>
        
        <a href="<c:url value ='/wallet/listFk.html' />" class="item">
            <i class="icon icon-no3"></i>
            <p class="text">付款记录</p>
        </a> 
    </div>

   <div class="menu-group personal-ico">
         <a  href='<c:url value="/upgrade/viewLowerUserInfo.html?netWork=A"/>' class="item">
            <i class="icon icon-no4"></i>
            <p class="text">查看A网团队联系方式</p>
        </a>
        <a  href='<c:url value="/upgrade/viewLowerUserInfo.html?netWork=B"/>' class="item">
            <i class="icon icon-no4"></i>
            <p class="text">查看B网团队联系方式</p>
        </a>
        <a  href='<c:url value="/userTree/tree.html?netWork=A"/>' class="item">
            <i class="icon icon-no4"></i>
            <p class="text">查看A网团队结构树</p>
        </a>
        <a  href='<c:url value="/userTree/tree.html?netWork=B"/>' class="item">
            <i class="icon icon-no4"></i>
            <p class="text">查看B网团队结构树</p>
        </a>
    </div>

     <div class="menu-group personal-ico">
     	<a href="<c:url value="/kefu/listRef.html"/>" class="item">
            <i class="icon icon-no8"></i>
            <p class="text">推荐排行</p>
        </a>
        <a href="<c:url value="/kefu/unvalidUser.html"/>" class="item">
            <i class="icon icon-no8"></i>
            <p class="text">客服封号</p>
        </a>
        <a href="<c:url value="/kefu/deleteUser.html"/>" class="item">
            <i class="icon icon-no8"></i>
            <p class="text">客服删号</p>
        </a>
        <a href="<c:url value="/kefu/changeUser.html"/>" class="item">
            <i class="icon icon-no8"></i>
            <p class="text">客服替换会员</p>
        </a>
        <a href="<c:url value="/kefu/resetPwdUser.html"/>" class="item">
            <i class="icon icon-no8"></i>
            <p class="text">客服重置密码</p>
        </a>
        <a href="<c:url value="/kefu/queryUser.html"/>" class="item">
            <i class="icon icon-no8"></i>
            <p class="text">客服查询会员</p>
        </a>
         <a href="<c:url value="/kefu/exchangePosition.html"/>" class="item">
            <i class="icon icon-no8"></i>
            <p class="text">会员位置对换</p>
        </a>
        <a href="<c:url value="/kefu/insteadConfirm.html"/>" class="item">
            <i class="icon icon-no9"></i>
            <p class="text">客服代确认收款</p>
        </a>
        <a href="<c:url value ='/param/fxUserAccount.html' />" class="item">
            <i class="icon icon-no7"></i>
            <p class="text">用户信息</p>
        </a> 
        <a href="<c:url value="/account/resetPwdIndex.html"/>" class="item">
            <i class="icon icon-no10"></i>
            <p class="text">修改登录密码</p>
        </a>
        <a  href='<c:url value="/login/loginout.html"/>'  class="item">
            <i class="icon icon-no11"></i>
            <p class="text">安全退出</p>
        </a>
    </div>

    <input type="hidden" id="fkRecord" value="${fkRecord }"/>
    <input type="hidden" id="skRecord" value="${skRecord }"/>
</body>

</html>


<script type="text/javascript">
	$(function(){
		var fkRecord = $("#fkRecord").val();
		var skRecord = $("#skRecord").val();

		var tipMsg = "";
		if(fkRecord){
			tipMsg="你有"+fkRecord+"条打款记录需要处理";
		}
		
		if(skRecord){
			tipMsg="你有"+skRecord+"条收款记录需要处理";
		}
		if(fkRecord&&skRecord){
			tipMsg="你有"+skRecord+"条收款记录和"+fkRecord+"条打款记录需要处理";
		}

		if(fkRecord|| skRecord){
			HHN.popupConfirm(tipMsg, 
	                function(){return true;}, 
	                function(){goMsg(fkRecord,skRecord); 
	                           return true;});
		}
	   
	});

    function goMsg(fkRecord,skRecord){
    	if(fkRecord){window.location.href ="<c:url value ='/wallet/listFk.html' />";}
        if(skRecord){window.location.href ="<c:url value ='/wallet/listSk.html' />";}
        
    }
    
</script>
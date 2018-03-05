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
	<title>Promotional management</title>
	
	<link href="<c:url value ='/res-kuangji/css/global.css'/>" rel="stylesheet" type="text/css">
	<link href="<c:url value ='/res-kuangji/ztreecss/zTreeStyle/zTreeStyle.css'/>"rel="stylesheet" type="text/css" />
	<link href="<c:url value ='/res-kuangji/ztreecss/demo.css'/>"rel="stylesheet" type="text/css" />
	<style type="text/css">
	.top{ width:100%; height:50px; float:left; background-color:#333;color:#fff}
	.top b{ width:12%; height:50px; display:block; float:left; background:url("<c:url value ='/res-kuangji/images/fanhui.png'/>") center center no-repeat; background-size:11px 25px; color:#333;}
	.top dd{ width:76%; height:50px; display:block; float:left; font-size:17px; line-height:50px; text-align:center;}
	.top span{ width:12%; height:50px; display:block; float:right; background:url("<c:url value ='/res-kuangji/images/caidan.png'/>") center center no-repeat; background-size:14px 30px;}
	.top ul{ width:100px; height:auto; position:absolute; top:50px; right:5px; z-index:9; background-color:#FFF; border:1px solid #e5e5e5; display:none;}
	.top ul li{ width:100px; height:40px; line-height:40px; text-align:center; padding:0 20px;font-size:1.4rem;}
		
	</style>
	
	<script type="text/javascript"	src="<c:url value ='/res-kuangji/js/jquery-2.1.1.min.js'/>"></script>
	<script type="text/javascript" src="<c:url value ='/res-kuangji/ztreejs/jquery.ztree.core.js'/>"></script>
	
	<script type="text/javascript"	src="<c:url value ='/res-kuangji/js/top.js'/>"></script>
	<SCRIPT type="text/javascript">
		<!--
		var initTreeData = ${initZtreeJson};
		var setting = {			
			async: {
				enable: true,
				url:"<c:url value='/userTree/getMemberTree.do'/>",
				autoParam:["id", "name=n", "level=lv"],
				otherParam:{"otherParam":"othreParameter"},
				dataFilter: filter
			}  
		};

		function filter(treeId, parentNode, childNodes) {
			if (!childNodes) return null;
			for (var i=0, l=childNodes.length; i<l; i++) {
				childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
			}
			return childNodes;
		}

		
		$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting,initTreeData);
		});
		//-->
	</SCRIPT>
</HEAD>

<BODY>
	<div class="top">
			<b	onclick="javascript:window.location.href=&#39;<c:url value='/redPack/personalCenter.html'/>&#39;"></b>
			<dd>Promotional management</dd>
		<span></span>
	</div>
	<div style="min-height:300px; padding-bottom:10px; font-size:14px; background:#FFF" class="ucenter">
	
	    <div data-role="content" class="ui-content" role="main" style="padding-top:8px; overflow:inherit">
	
	        <div class="content_wrap" style="width:100%;height:600px">
				<div class="zTreeDemoBackground left" style="width:100%;height:95%">
					<ul id="treeDemo" class="ztree" style="width:100%;height:90%"></ul>
				</div>
			</div>
	    </div>
	</div>
</body>
</html>

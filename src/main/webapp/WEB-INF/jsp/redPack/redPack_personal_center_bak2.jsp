<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>个人中心</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Cache-Control" content="no-cache" />
<meta name="viewport"
	content="width=device-width,initial-scale=1.0,minimum-scale=1.0, maximum-scale=1.0,user-scalable=no" />
<meta name="format-detection" content="telephone=no" />
<meta name="MobileOptimized" content="320" />
<link href="${fileServerUrl }/res/css/eLoan.css?v=${jsversion}"
	rel="stylesheet" type="text/css">
</head>
<body style="padding-bottom: 60px">
	<header class="center_head">
		<div class="head-inf">
			<c:if test="${tzNum >0}">
				<a href="<c:url value='/app/elend/tongzhi'/>" class="tongzhi">
					<em>${tzNum} </em>
				</a>
			</c:if>
			<span class="bf1" style="display:inline">${userDo.name }</span>
			<span class="bf1">${userDo.userName}</span>
		</div>
		<c:if test="${isManager ne 'T' }">
			<div class="head-lend db_f">
				<span><em>会员状态</em>
				   <c:if test="${userDo.status>0}">正式会员</c:if>
				   <c:if test="${userDo.status == 0}">临时式会员</c:if>
				</span> 
				<span><em>会员级别</em>${userDo.grade}级会员</span> 
				<span><em>团队会员数</em>
				<fmt:formatNumber
						value="${groupCount}"
						pattern="###################" /></span>
				<span><em>积分</em>
				<fmt:formatNumber
						value="${userDo.bonus}"
						pattern="#################0.00" /></span>
			</div>
		</c:if>
		<c:if test="${isManager eq 'T' }">
			<div class="head-lend db_f">
				<span style="width:100%"><em>会员状态</em>管理员
				</span>
			</div>
		</c:if>
	</header>

	<article>
	<!--
		<section>
			<h2 class="progess-tip p1">
				<em> 升级付分信息
				</em>
			</h2>
			<div class="">
				<p style="padding:0 10px;">当前等级：${userDo.grade}级</p>
				<p style="padding:0 10px;">下一个付分等级：${gradeFee.afterUpgrade}级</p>
				<p style="padding:0 10px;">付分金额：${gradeFee.gradeAmount}分</p>
			</div>
		</section>
	-->
		
		<section>
			<ul class="center-list">
				<li>
					<a href="<c:url value='/common/modifyInfo.html'/>">
						<span class="hj">完善个人资料</span><em></em>
					</a>
				</li>
				<li>
					<a href="<c:url value='/upgrade/toApply.html'/>">
						<span class="ld">我要升级</span><em></em>	
					</a>				
				</li>
				 <li>
				 	<a href="<c:url value='/upgrade/listApply.html'/>" >
				 	<span class="ld">申请记录</span><em></em>
				 	</a>
				 </li>
				 
				<li>
					<a href="<c:url value='/upgrade/viewMyRefUserInfo.html'/>">
						<span class="ld">我的推荐</span><em></em>
					</a>				
				</li>
				<li>
					<a href="<c:url value='/upgrade/viewLowerUserInfo.html'/>">
						<span class="hj">查看团队联系方式</span><em></em>
					</a>
				</li>
				
				<li><a href="<c:url value='/account/resetPwdIndex.html?pwdFlag=login'/>" >
						<span class="hj">修改登录密码</span><em></em>
					</a>
				</li>     
				
				<c:if test="${isManager eq 'T' }">
					<li>
						<a href="<c:url value='/upgrade/listAudit.html'/>" >
							<span class="ld">升级审批</span><em></em>
						</a>					
					</li>
					<li>
						<a href="<c:url value='/upgrade/viewTempUserInfo.html'/>">
							<span class="ld">审批临时会员</span><em></em>
						</a>					
					</li>
				
					<li>
						<a href="<c:url value='/upgrade/setJoinGroupMoney.html'/>">
							<span class="ld">设置会费</span><em></em>
						</a>					
					</li>
					
					<li>
						<a href="<c:url value='/upgrade/setManagerMember.html'/>">
							<span class="ld">设置管理员</span><em></em>
						</a>					
					</li>
					
					<li>
						<a href="<c:url value='/upgrade/createGroup.html'/>">
							<span class="ld">创建团队</span><em></em>
						</a>					
					</li>
				</c:if>
				<li>
					<a href="<c:url value='/login/loginout.html'/>">
						<span class="hj">安全退出</span><em></em>
					</a>
				</li>
			</ul>
		</section>
		
		</article>
		
		<article>
				<section>
					<ul class="personinf_img p1 bs">
						<li class="bs psrimgli" data-img="<c:url value='${fileServerUrl }/res/img/red1.png'/>">
								<img src="<c:url value='${fileServerUrl }/res/img/red1.png'/>" alt="" >
						</li>
						
						<li class="bs psrimgli" data-img="<c:url value='${fileServerUrl }/res/img/red3.png'/>">
								<img src="<c:url value='${fileServerUrl }/res/img/red3.png'/>" alt="" >
						</li>
					</ul>
				</section>
			</article>

		<%@ include file="../include/foot.jsp"%>
</body>
</html>
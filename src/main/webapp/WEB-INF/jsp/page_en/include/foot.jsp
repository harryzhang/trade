<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<nav>
  <ul class="bs db_f footer_nav">
    <li class="bf1"><a href="<c:url value='/login/main.html'/>"><span>首页</span></a></li>
    <li class="bf1"><a href="<c:url value='/redPack/personalCenter.html'/>"><span>个人中心</span></a></li>
    <%-- <li class="bf1"><a href="<c:url value='/account/regIndex.html'/>"><span>推荐会员</span></a></li>
    <li class="bf1"><a href="<c:url value='/userTree/tree.html'/>"><span>团队结构</span></a></li>
    --%>
  </ul>
</nav>

<script src='<c:url value="/res/js/zepto.js?"/>v=${jsversion}'></script>


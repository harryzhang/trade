<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
 	if(request.getSession().getAttribute("fromUrl")==null){
		request.getSession().setAttribute("fromUrl", "");
	} 
	response.sendRedirect(request.getSession().getAttribute("fromUrl")+"login/index.html");
%>
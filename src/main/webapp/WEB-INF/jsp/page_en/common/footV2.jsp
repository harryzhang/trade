<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${platform ne 'android' && platform ne 'ios' && platform ne 'pad'}">
<div class="fix-nav">
        <!-- active-n 代表第n个高亮 -->
        <div class="active-1">
            <div class="fix-nav-wrap">
                
                <a href="${basePath}/product/toMain.do">
                    <i class="icon icon-loan"></i>
                    <p>首页</p>
                </a>
                
                <a href="${basePath}/product/toSmallLoan.do">
                    <i class="icon icon-three"></i>
                    <p>三人微贷</p>
                </a>
                <!-- 
                <a href="${basePath}/myWealth/toMyWealth.do">
                    <i class="icon icon-setting"></i>
                    <p>我</p>
                </a>
				 -->
                <a href="${basePath}/loanPersonalCenter/toPersonalCenter.do">

                    <i class="icon icon-setting"></i>
                    <p>我</p>
                </a>
            </div>
        </div>
    </div>
</c:if>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
if(basePath.indexOf("m.hehuayidai.com")>0){
	request.setAttribute("basePath","https://m.hehuayidai.com");
}else{
	request.setAttribute("basePath",basePath);
}
%>
<script type="text/javascript">
	var globalConfig = {basePath:'${basePath}',globalSaltVal:'${md5SaltVal}'};
</script>
<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" href="${basePath}/res/v2/css/base.css?v=${cssversion}" />
<link rel="stylesheet" href="${basePath }/res/js/plugins/modal/modal.css?v=${cssversion}" />
<link rel="stylesheet" href="${basePath }/res/css/exit.css?v=${cssversion}" />
<link rel="stylesheet" href="${basePath }/res/v2/css/main.css?v=${cssversion}" />
<link rel="stylesheet" href="${basePath }/res/v2/css/sideSelector.css?v=${cssversion}" />
<script type="text/javascript" src="${basePath}/res/js/libs/zepto.min.js?v=${jsversion}"></script>
<script type="text/javascript" src="${basePath}/res/js/global.js?v=${jsversion}"></script>
<script type="text/javascript" src="${basePath}/res/js/plugins/modal/modal.js?v=${jsversion}"></script>
<script type="text/javascript" src="${basePath}/res/js/plugins/selector.js?v=${jsversion}"></script>

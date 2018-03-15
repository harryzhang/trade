<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=0">
<meta name="apple-touch-fullscreen" content="yes">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">


<title>首页</title>

<link href="<c:url value ='/res-kuangji/css/global.css'/>"	rel="stylesheet" type="text/css">
<link href="<c:url value ='/res-kuangji/css/member_yzc.css'/>"	rel="stylesheet" type="text/css">
<link href="<c:url value ='/res-shichang/css/reset.css'/>"	rel="stylesheet" type="text/css">
<link href='<c:url value="/res/js/plugins/modal/modal.css?"/>${jsversion}' type="text/css" rel="stylesheet">

<script type="text/javascript" src='<c:url value="/res/js/global.js?v="/>${jsversion}' ></script>
<script type="text/javascript" src='<c:url value="/res/js/plugins/modal/modal.js?v="/>${jsversion}'></script>

<script type="text/javascript"	src="<c:url value ='/res-kuangji/js/jquery-2.1.1.min.js'/>"></script>
<script type="text/javascript"	src="<c:url value ='/res-kuangji/js/top.js'/>" used="1"></script>




<style type="text/css">
		.body{
			display: flex;
			flex-direction: column;
		}
		
		#container{
			width: 100%;
			height: 100%;
		    background: #191c23;
    		overflow: hidden;
    		display: flex;
    		flex-direction: column;
		}
		.tab-container{
			flex: 1;
			overflow-y: auto;
		}
		.title{
			height: 50px;
			line-height: 50px;
			color: #fff;
			font-size: 1.7rem;
			text-align: center;
		}
		.bodyImg{
		    transform: rotateY(180deg);
		    opacity: .2;
		    position: absolute;
		    width: 8rem;
		    left: -1.7rem;
		    z-index: 0;
		}
		.profit{
		    width: 90%;
		    margin: 0 auto;
		    background: #2f2c3f;
		    height: 13rem;
		    position: relative;
		    overflow: hidden;
		}
		.profit p{
			position: absolute;
    		left: 2rem;
    		font-size: 1.5rem;
		}
		.profit-title{
			top: 2rem;
		}
		.profit-money{
			font-size: 5rem !important;
			top: 5rem;
		}
		.profit-yesterday{
			top: 10rem;
			color: #999;
		}
		.profitImg{
		    width: 20rem;
		    opacity: .1;
		    float: right;
		    margin-top: -3rem;
		    margin-right: -8rem;
		}
		.profit-color{
			color: #facd02;
		}
		.colorWhite{
			color: #fff;
		}
		.msg-warp{
			height: 14rem;
			background: #1c1e2a;
			display: flex;
			flex-direction: column;
			justify-content: center;
		}
		.msg-calculated{
			height: 60%;
			width: 100%;
			display: flex;
		    margin-bottom: 1.6rem;
		}
		.flex{
			margin-top: 1.6rem;
			flex: 1;
			height: 50%;
		}
		.msg-money{
		    width: 42%;
		    margin-top: 1.6rem;
		    background: #facd02;
		}
		.msg-money p{
			font-size: 1.6rem;
			text-indent: 1rem;
			margin-top: 1.5rem;
		}
		.msg-money p:nth-child(2){
    		font-size: 2.4rem;
		}
		.msg-container{
			flex: 1;
		    border: 1px solid #24232b;
		    border-left: 0;
		    border-right: 0;
		}
		.msg-container img{
		    width: 2rem;
		    margin-left: 2rem;
		    margin-top: 1rem;
		    float: left;
		}
		.msg-container span{
		    font-size: 1.6rem;
		    color: #fff;
		    line-height: 4rem;
	        margin-left: 1rem;
		}
		.msg-container span:last-child{
		    float: right;
		    margin-right:2rem;
		    color: #999;
		}
		.share-warp{
			height: 12rem;
			background: #1c1e2a;
			margin-top: 1.6rem;
			overflow: hidden;
		}
		.share-container{
			width: 90%;
			margin: 5% auto;
			height: 70%;
			background: #2c2e3a;
			overflow: hidden;
		}
		.share-container p{
			text-indent: 1.6rem;
			margin-top: 1.8rem;
		}
		.share-container p:first-child{
			font-size: 1.8rem;
			color: #facd02;
		}
		.share-container p:nth-child(2){
			font-size: 1.4rem;
			color: #fff;
		}
		.btn-warp{
			height: 10rem;
			background: #1c1e2a;
			margin-top: 1.6rem;
			overflow: hidden;
			display: flex;
		}
		.btn-warp dl{
			flex: 1;
		}
		.btn-warp dl dt img{
			width: 3rem;
			height: 3rem;
		    margin: 5% auto;
			display: block;
		}
		.btn-warp dl dd{
			text-align: center;
		    line-height: 1.6rem;
    		font-size: 1.6rem;
		}
	</style>
	
</head>
<body class="body">

	<div id="container">
		<p class="title">CMCC</p>
		<img src="<c:url value ='/res/images/leaves.png'/>" alt="" class='bodyImg'>
		
		<div class="tab-container">
			<div class="profit">
				<p class="profit-color profit-title">算力累计收益</p>
				<p class="profit-color profit-money">${totalIncome }555</p>
				<p class="profit-yesterday">昨日收益 ${yesterdayIncome}8888</p>
				<img src="<c:url value ='/res/images/leaves.png'/>" alt="" class="profitImg">
			</div>
			<div class="msg-warp">
				<div class="msg-calculated">
					<div class="flex"></div>
					<div class="msg-money">
						<p>智算能力</p>
						<p>${security.amount }888</p>
					</div>
					<div class="flex"></div>
					<div class="msg-money">
						<p>释放算力</p>
						<p>${pet.amount}8888</p>
					</div>
					<div class="flex"></div>
				</div>
				
				<div class="msg-container">
					<img src="<c:url value ='/res/images/leaves.png'/>" alt="">
					<span>CMCC枫叶币正式上线通知</span>
					<span>更多</span>
				</div>
			</div>

			<div class="share-warp">
				<div class="share-container">
					<p>分享好友，获得分享资产，狂赚枫叶币</p>
					<p>分享收益=分享资产×利率(以定期利率相同)</p>
				</div>
			</div>

			<div class="btn-warp">
				<dl>
					<dt><img src="<c:url value ='/res/images/leaves.png'/>"></dt>
					<dd class="colorWhite">发送</dd>
				</dl>
				<dl>
					<dt><img src="<c:url value ='/res/images/leaves.png'/>"></dt>
					<dd class="colorWhite">扫描</dd>
				</dl>
				<dl>
					<dt><img src="<c:url value ='/res/images/leaves.png'/>"></dt>
					<dd class="colorWhite">接收</dd>
				</dl>
			</div>
		</div>
	</div>

	
<div class="footer">
    
    <dl class="cur" onclick="javascript:window.location.href ='<c:url value ='/firstpage/toFirstpage.html'/>'">
         <dt></dt>
        <dd>首页</dd>
    </dl>
     
     <dl onclick="javascript:window.location.href ='<c:url value ='/trade/trade.html'/>'">
          <dt></dt>
        <dd>市场</dd>
    </dl> 
    
    
     
    <dl class="cur" onclick="javascript:window.location.href ='<c:url value ='/redPack/personalCenter.html'/>'">
         <dt></dt>
        <dd>个人中心</dd>
    </dl>
    

    
     
</div>



<script type="text/javascript">
	$(".tab").find('li').on('click', function(){
		$(".tab").find('li').removeClass('tab-active')
		$(this).addClass('tab-active')
		var index = $(this).attr('data')

		$(".tab-container").find('div').removeClass('tab-container-active')
		$.each($(".tab-container").find('div'), function(idx, data){
			if(index == $(data).attr('data')){
				$(data).addClass('tab-container-active')
			}
		})
	})
	
	function doSale(){
		
		var pwd = $("#pwd1").val();
    	var qty = $("#qty1").val();
    	var price = $("#price1").val();
    	var orderType = "1";
    	var goodsId = "1";

    	if(price == null || price == "" || price.length == 0){//昵称
    		HHN.popup("请输入价格", 'danger');  	
       		return false;
    	}
    	if(qty == null || qty == "" || qty.length == 0){//昵称
    		HHN.popup("请输入数量", 'danger');  	
       		return false;
    	}
   		
         if(pwd == null || pwd == "" || pwd.length == 0){
             HHN.popup("请输入确认密码", 'danger');
             return false;
         }
         
                                
          var param = {"pwd":pwd,"qty":qty,"price":price,"goodsId":goodsId,
     		     "orderType":orderType};
		
		submitGuadan(param);
	}
	
	function doBuy(){
		
		var pwd = $("#pwd").val();
    	var qty = $("#qty").val();
    	var price = $("#price").val();
    	var orderType = "2";
    	var goodsId = "1";
    	 

    	if(price == null || price == "" || price.length == 0){//昵称
    		HHN.popup("请输入价格", 'danger');  	
       		return false;
    	}
    	if(qty == null || qty == "" || qty.length == 0){//昵称
    		HHN.popup("请输入数量", 'danger');  	
       		return false;
    	}
   		
         if(pwd == null || pwd == "" || pwd.length == 0){
             HHN.popup("请输入确认密码", 'danger');
             return false;
         }
         
                                
          var param = {"pwd":pwd,"qty":qty,"price":price,"goodsId":goodsId,
     		     "orderType":orderType};
          
		submitGuadan(param);
	}


    
    //提交信息
    function submitGuadan(param){
    	$.post('/trade/submitguadang.html', param, function(data) {
			if(data.resultCode == '0'){
				HHN.popup("挂单成功!");					
			}else{                
				HHN.popup(data.errorMessage);
			}
		},"json");
    }
    
    
    
    
</script>
	<div style="position: static; display: none; width: 0px; height: 0px; border: none; padding: 0px; margin: 0px;">
		<div id="trans-tooltip"><div id="tip-left-top" style="background: url(&quot;chrome-extension://ikkbfngojljohpekonpldkamedehakni/imgs/map/tip-left-top.png&quot;);"></div>
		<div id="tip-top" style="background: url(&quot;chrome-extension://ikkbfngojljohpekonpldkamedehakni/imgs/map/tip-top.png&quot;) repeat-x;"></div>
		<div id="tip-right-top" style="background: url(&quot;chrome-extension://ikkbfngojljohpekonpldkamedehakni/imgs/map/tip-right-top.png&quot;);"></div>
		<div id="tip-right" style="background: url(&quot;chrome-extension://ikkbfngojljohpekonpldkamedehakni/imgs/map/tip-right.png&quot;) repeat-y;"></div>
		<div id="tip-right-bottom" style="background: url(&quot;chrome-extension://ikkbfngojljohpekonpldkamedehakni/imgs/map/tip-right-bottom.png&quot;);"></div>
		<div id="tip-bottom" style="background: url(&quot;chrome-extension://ikkbfngojljohpekonpldkamedehakni/imgs/map/tip-bottom.png&quot;) repeat-x;"></div>
		<div id="tip-left-bottom" style="background: url(&quot;chrome-extension://ikkbfngojljohpekonpldkamedehakni/imgs/map/tip-left-bottom.png&quot;);"></div>
		<div id="tip-left" style="background: url(&quot;chrome-extension://ikkbfngojljohpekonpldkamedehakni/imgs/map/tip-left.png&quot;);"></div>
		<div id="trans-content"></div></div><div id="tip-arrow-bottom" style="background: url(&quot;chrome-extension://ikkbfngojljohpekonpldkamedehakni/imgs/map/tip-arrow-bottom.png&quot;);"></div>
		<div id="tip-arrow-top" style="background: url(&quot;chrome-extension://ikkbfngojljohpekonpldkamedehakni/imgs/map/tip-arrow-top.png&quot;);"></div>
	</div>
	</body>
</html>
$(function(){
	var wheight=$(window).height();
	$(".mengban").height(wheight);
	var rheight=$(".recordsInfo").height();
	var rtheight=(wheight-rheight)/3;
	//alert(rtheight);
	$('.recordsInfo').css('top',rtheight);	
	$(".know").click(function(){
		$(".mengban").hide();
		$(".recordsInfo").hide();
	});
	$('.footer dl').click(function(){
		$(this).addClass('cur').siblings().removeClass('cur');
	});
	$('.mengban').click(function(){
		$(this).hide();
		$('.recordsInfo').hide();
	});
	/*var b=1;//判断第一次登录显示的弹窗
	if(b==1){
		$('.mengban').show();
	   $('div.recordsInfo').show();	
	}else{//第二次登录显示的弹窗
	   $('.mengban').hide();
	   $('div.recordsInfo').hide();	
	}*/

});
	
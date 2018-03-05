// JavaScript Document
$(function(){
	$('.box label').click(function(){
		if($(this).hasClass('selected')){
			$(this).removeClass('selected');
			$(this).siblings('ul').slideDown();
		}else{
			$(this).addClass('selected');
			$(this).siblings('ul').slideUp();
		}
	});
	var wheight=$(window).height();
	var tcheight=$('.tanceng').height();
	var ttop=(wheight-tcheight)/2;
	$('.tanceng').css('top',ttop);
	$('.mengban').height(wheight);
	$('.mask').height(wheight);
	var s=1;	//后台开发人员需判断分享到微信还是分享到APP
	$('.ewmBox dl').click(function(){
		if(s==1){
			$('.mengban').show();
			$('body').addClass('overf');
		}else{
			$('.mask').show();
			$('.tanceng').show();
			$('body').addClass('overf');
		}
		
	});
	$('.mengban').click(function(){
		$(this).hide();
		$('body').removeClass('overf');
	});
	$('.mask').click(function(){
		$(this).hide();
		$('.tanceng').hide();
		$('body').removeClass('overf');
	});
});
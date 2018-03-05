// JavaScript Document
$(function(){
	var oheight=$('.goodsPic').height();
	$('.goodsInfo').height(oheight);
	var dheight=$(window).height();
	$('.mengban').height(dheight);
	var theight=$('.tanceng').height();
	var ttop=(dheight-theight)/2;
	$('.tanceng').css('top',ttop);
	$('.recordsInfo').css('top',ttop);
	$('.odtop input.edit').click(function(){
		$('.mengban').show();
		$('.tanceng').show();
	})	
	$('.confirm').click(function(){
		$(this).parents('.tanceng').hide();
		$('.mengban').hide();
	})
	$('.cancle').click(function(){
		$(this).parents('.tanceng').hide();
		$('.mengban').hide();
	})
	
	$('dd.tuihuo').click(function(){
	 	$('.mengban').show();
		$('.recordsInfo').show();
	})
	$('.mengban').click(function(){
		$('.mengban').hide();
		$('.recordsInfo').hide();
	})
})
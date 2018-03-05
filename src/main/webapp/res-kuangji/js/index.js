$(function(){
	var wheight=$(window).height();	
	$('.mengban').height(wheight);
	var cheight=$('.tanchuang').height();
	var theight=(wheight-cheight)/2;
	$('.tanchuang').css('top',theight);
	$('.top ul li').click(function(){
		$('.mengban').show();
		$('.tanchuang').show();
	})
	$('.mengban').click(function(){
		$(this).hide();
		$('.tanchuang').hide();
	})
	$('.tanchuang .close').click(function(){
		$('.mengban').hide();
		$('.tanchuang').hide();
	})
	$(window).scroll(function(){
		var sheight=$(this).scrollTop();
		var wheight=$(window).height()/4;
		if(sheight>wheight){
			$(".backtop").fadeIn();
		}else{
			$(".backtop").fadeOut();
		}
	})
	TouchSlide({ 
		slideCell:"#banner",
		titCell:".hd ul", 
		mainCell:".bd ul", 
		effect:"left", 
		autoPlay:true,
		autoPage:true, 
		switchLoad:"_src" 
	});
	TouchSlide({ 
		slideCell:"#brands",
		titCell:".hd ul", 
		mainCell:".bd ul", 
		effect:"left", 
		autoPlay:true,
		autoPage:true, 
		switchLoad:"_src" 
	});
	//$('.footer dl').click(function(){
//		$(this).addClass('cur').siblings().removeClass('cur');
//	})
	$('.newFooter dl').click(function(){
		$(this).addClass('cur').siblings().removeClass('cur');
	})
})
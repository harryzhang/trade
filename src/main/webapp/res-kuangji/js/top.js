// JavaScript Document
$(function(){
	$('.top span').click(function(){
		if($(this).siblings('ul').is(':visible')){
			$(this).siblings('ul').slideUp();
		}else{
			$(this).siblings('ul').slideDown();
		}
	}) ;
    orient();
});
function orient() {
	//alert('gete');
	if (window.orientation == 0 || window.orientation == 180) {
		$("body").attr("class", "portrait");
		orientation = 'portrait';
		return false;
	}
	else if (window.orientation == 90 || window.orientation == -90) {
		$("body").attr("class", "landscape");
		orientation = 'landscape';
		return false;
	}
}
$(window).bind( 'orientationchange', function(e){
	orient();
});
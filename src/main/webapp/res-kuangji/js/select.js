// JavaScript Document
$(function(){
	/*$(".top dd").click(function(){
		if(!$(this).siblings("ul").is(":visible")){
			$(this).siblings("ul").slideDown().siblings("ol").hide();
			$(this).siblings("ul").children("li").click(function(){
				var lt=$(this).text();
				$(this).parents("ul").slideUp();
				$(this).parents("ul").siblings("dd").text(lt);
			})
		}else{
			$(this).siblings("ul").slideUp();
		}
	})
	*/

	$(window).scroll(function(){
		var sheight=$(this).scrollTop();
		var wheight=$(window).height()/4;
		if(sheight>wheight){
			$(".backtop").fadeIn();
		}else{
			$(".backtop").fadeOut();
		}
	})
	var dfu=$(".ssbox input").val();
	$(".ssbox input").focus(function(){
		if(this.value==dfu){
			this.value="";
			$(this).addClass("selected");
			$(this).parents("div.ssbox").siblings("ul").hide();
		}
		$(".hotgoods").hide();
	}).on("input",function(){
		$(this).siblings("li").show();
		$(this).parents("div.ssbox").siblings("ul").hide();
		$(this).parents("div.ssbox").siblings("ol").show();
		$(this).parents("div.ssbox").siblings("ol").children("li").click(function(){
			$(this).parents("ol").siblings("div.ssbox").children("input").val($(this).text());
			$(this).parents("ol").slideUp();
			$(this).parents("ol").siblings("div.ssbox").children("li").show();
		})
	}).blur(function(){
		if(this.value==""){
			this.value=dfu;
			$(this).removeClass("selected");
		}
		//$(this).siblings("li").hide();
	})
	$('.ssbox li').click(function(){
		$(this).siblings("input").val("");
		$(this).hide();
	})
	$(".goodsList ul > li").click(function(){
		$(this).addClass("cur").siblings().removeClass("cur");
		$(this).parents("ul").siblings("ol").slideUp();
	})
	$(".goodsList ul > li:nth-child(1)").click(function(){
		if(!$(this).parent("ul").siblings("ol").is(":visible")){
			$(this).children().find("dt").addClass("selected");
			$(this).parent("ul").siblings("ol").slideDown();
			$(this).parent("ul").siblings("ol").children("li").click(function(){
				var dtext=$(this).text();
				$(this).addClass("cur").siblings().removeClass("cur");
				$(this).parent("ol").slideUp();
				$(this).parent("ol").siblings("ul").children("li.sort").find("dd").text(dtext);
				$(this).parent("ol").siblings("ul").children("li.sort").find("dt").removeClass("selected");
			})
		}else{
			$(this).parent("ul").siblings("ol").slideUp();
			$(this).children().find("dt").removeClass("selected");
		}	
	})
})

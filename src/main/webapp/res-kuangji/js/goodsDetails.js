// JavaScript Document
$(function () {
    TouchSlide({
        slideCell: "#banner",
        titCell: ".hd ul", //开启自动分页 autoPage:true ，此时设置 titCell 为导航元素包裹层
        mainCell: ".bd ul",
        effect: "left",
        autoPlay: true, //自动播放
        autoPage: true, //自动分页
        switchLoad: "_src" //切换加载，真实图片路径为"_src" 
    });
    $(".hright").click(function () {
        if ($(this).next().is(":visible")) {
            $(this).next().fadeOut();
        } else {
            $(this).next().fadeIn();
        }
    })
    var k = 1;	//通过k的值来判断红包显示与否
    var m = 1;    //通过m的值来判断红包是平台红包还是商家红包
    var wheight = $(window).height();
    var tcheight = $('.tanceng').height();
    var tgheight = $('.tanchuang').height();
    var tctop = (wheight - tcheight) / 2;
    var tgtop = (wheight - tgheight) / 2;
    $('.mengban').height(wheight);
    $('.tanceng').css('top', tctop);
    $('.tanchuang').css('top', tgtop);

    /*        
     if(k==1&&m==1){
     $('.mengban').show();
     $('.tanchuang').show();	
     $('body').addClass('ovf');
     $('.tanchuang li').text('网站管理平台络科技有限公司');	
     $('.tanchuang dd').text('平台红包');
     }else if(k==1&&m==0){
     $('.mengban').show();
     $('.tanchuang').show();
     $('body').addClass('ovf');
     $('.tanchuang li').text('广州箱包旗舰店');	
     $('.tanchuang dd').text('商家红包');
     }else{   
     $('.mengban').hide();
     $('.tanchuang').hide();	
     $('body').removeClass('ovf');
     }
     $('.tanchuang').click(function(){
     $(this).hide();
     $('.mengban').hide();
     $('body').removeClass('ovf');
     })
     
     */

    var j = 1;  //判断是否是微信，来控制是否弹出电话号码	
    if (j == 1) {
        $('.contact dt').click(function () {
            $('.mengban').show();
            $('.tanceng').show();
            $('body').addClass('ovf');
        })
        $('.close').click(function () {
            $('.mengban').hide();
            $('.tanceng').hide();
            $('body').removeClass('ovf');
        })
    }




    $(window).scroll(function () {
        var sheight = $(this).scrollTop();
        var gheight = $('.goodsInfo').offset().top;
        var bheight = $('#banner').height();
        var gdheight = $('.goods').height();
        var mheight = $('.merchantBox').height();
        var tlheight = bheight + gdheight + mheight + 20;
        $('.infos div').css('min-height', wheight);
        if (sheight > tlheight) {
            $('.goodsInfo ul li').click(function () {
                var _index = $(this).index();
                var iheight = $('.infos div').eq(_index).height();
                $(this).addClass('selected').siblings().removeClass('selected');
                $('.infos > div').eq(_index).show().siblings().hide();
                $('.infos > div').eq(_index).find('h4').show();
                if (iheight > wheight) {
                    $('.goodsInfo ul').addClass('haha');
                    $('.infos div').eq(_index).find('h4').show();
                } else {
                    $('.goodsInfo ul').removeClass('haha');
                    $('.infos div').eq(_index).find('h4').hide();
                }
            })
            $('.goodsInfo ul').addClass('haha');
        } else {
            $('.goodsInfo ul li').click(function () {
                var _index = $(this).index();
                var iheight = $('.infos div').eq(_index).height();
                $(this).addClass('selected').siblings().removeClass('selected');
                $('.infos > div').eq(_index).show().siblings().hide();
                if (iheight > wheight) {
                    $('.goodsInfo ul').addClass('haha');
                    $('.infos div').eq(_index).find('h4').show();
                } else {
                    $('.goodsInfo ul').removeClass('haha');
                    $('.infos div').eq(_index).find('h4').hide();
                }
            })
            $('.goodsInfo ul').removeClass('haha');
            $('.goodsInfo .infos div').removeClass('cur');
            $('.infos > div').find('h4').hide();
        }
        if (sheight > wheight / 2) {
            $('.backtop').show();
        } else {
            $('.backtop').hide();
        }
        //if(wheight>theight+50){		//此处放开就要加载ajax
//			alert("haha");
//		}
    })
    var nheight = $('.notice').height();
    var ntop = (wheight - nheight) / 2;
    $('.notice').css('top', ntop);
    var u;	//通过u来控制加入购物车弹层的显示与否
    $('.add').click(function () {
        $('.mengban').show();
        $('.shopping').show();
        //  $("#btype").val(1);
        u = 1;
    })
    $('.buy').click(function () {
        $('html,body').addClass('ovf');
        $('.mengban').show();
        $('.shopping').show();
          $("#btype").val(2);
        u = 2;
    })
    /*$('.pay input').click(function(){
     if(u==1){
     $(this).parents('.shopping').hide();
     $('.mengban').hide();
     $('.notice').show().fadeOut(2500);
     }else{
     window.location.href='';
     }		
     })*/
    /*$(".styitems ul li").click(function(){
     $(this).addClass("selected").siblings().removeClass("selected");
     //$(".styitems ul").find('li').find('input').attr('checked',false);
     $(this).find('input').attr('checked',true).parent('li').siblings().find('input').removeAttr('checked');
     })*/
    $('.shopping .gb').click(function () {
        $('.mengban').hide();
        $('.shopping').hide();
        $('html,body').removeClass('ovf');
    })
    //<!--wzx 2016.01.07*-->
    /*$('.collection').click(function(){
     $(this).addClass('cur');
     $(this).children('dd').text('已收藏');
     })*/
    //<!--wzx 2016.01.07*-->
})
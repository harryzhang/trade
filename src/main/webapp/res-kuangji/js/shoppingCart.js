// JavaScript Document
        $(function () {
            $('.odtop label').click(function () {
                if ($(this).hasClass('checked')) {
                    $(this).removeClass('checked');
                    $(this).parents('.odtop').siblings().children('label').removeClass('checked');
                    $(this).parents('.odtop').siblings().children('input').removeClass('active');
                   $(this).parents('.odtop').siblings().children('input').attr('checked', false);
                } else {
                    $(this).addClass('checked');
                    $(this).parents('.odtop').siblings().children('label').addClass('checked');
                    $(this).parents('.odtop').siblings().children('input').addClass('active');
                    $(this).parents('.odtop').siblings().children('input').attr('checked', true);
                }
                getCartTotal();
            });
            var al = $('.main .order .goods').size();
            $('.goods label').click(function () {
                if ($(this).hasClass('checked')) {
                    $(this).removeClass('checked');
                    $(this).siblings().removeClass('active');
                    $(this).siblings().attr('checked', false);
                  //  $(this).attr('checked', true);
                } else {
                    $(this).addClass('checked');
                    $(this).siblings().addClass('active');
                    $(this).siblings().attr('checked', true);
                }
                var gsize = $(this).parents('.order').find('.goods').size();
                var gcsize = $(this).parents('.order').find('.goods label.checked').length;

                if (gcsize == gsize) {
                    $(this).parents('.order').find('.odtop label').addClass('checked');
                    $(this).parents('.order').find('.goods input').attr('checked', true);
                } else {
                    $(this).parents('.order').find('.odtop label').removeClass('checked');
                    $(this).parents('.order').find('.goods input').attr('checked', false);
                }
                var tl = $(this).parents('.main').find('.goods label.checked').length;
                if (tl == al) {
                    $(this).parents('body').find('.total .all label').addClass('checked');
                   $(this).parents('body').find('.total .all input').attr('checked', true);
                } else {
                    $(this).parents('body').find('.total .all label').removeClass('checked');
                    $(this).parents('body').find('.total .all input').attr('checked', false);
                }
                getCartTotal();
            })
            $('.all label').click(function () {
                if ($(this).hasClass('checked')) {
                    $(this).removeClass('checked');
                    $('.odtop label').removeClass('checked');
                    $('.goods label').removeClass('checked');
                    $('.goods input').removeClass('active');
                    $('.goods input').attr('checked', false);
                } else {
                    $(this).addClass('checked');
                    $('.odtop label').addClass('checked');
                    $('.goods label').addClass('checked');
                    $('.goods input').addClass('active');
                    $('.goods input').attr('checked', true);
                }
                getCartTotal();
            })
            /*var u=1;		//通过u的值判断购物车内是否为空
             if(u==1){
             $('.main dl.default').hide();
             $('.total').show();
             $('.main .order').show();
             }else{
             $('.main dl.default').show();
             $('.total').hide();
             $('.main .order').hide();
             }*/
            $('.edit').click(function () {
                var dvalue = $(this).text();
                if ($(this).text() == '修改购买数量') {
                    $(this).text('完成');
                    $(this).parents('.odtop').siblings('.goods').find('.modifyInfo').show();
                    $(this).parents('.odtop').siblings('.goods').find('.goodsInfo').hide()
                } else {
                    $(this).text('修改购买数量');
                    $(this).parents('.odtop').siblings('.goods').find('.goodsInfo').show();
                    $(this).parents('.odtop').siblings('.goods').find('.modifyInfo').hide()
                }
            })
            var wheight = $(window).height();
            $('.mengban').height(wheight);
            $('.notice').css('top', 3 * wheight / 5);
            /*   $('.delbtn').click(function () {
             var glen = $(this).parents('.order').children('.goods').size();
             //alert(glen);
             if (glen > 1) {
             $(this).parents('.goods').remove();
             $('.notice').show().fadeOut(2000);
             } else {
             $(this).parents('.order').remove();
             $('.notice').show().fadeOut(2000);
             }
             })*/
            $('.modifyInfo .gstyle').click(function () {
                $('.mengban').show();
                $('.shopping').show();
            })
            $('.gb').click(function () {
                $('.mengban').hide();
                $('.shopping').hide();
            })
            $('.pay input').click(function () {
                $('.mengban').hide();
                $('.shopping').hide();
            })
            $('.styitems ul li').click(function () {
                $(this).addClass('selected').siblings().removeClass('selected');
            })
            //公共尾部js
            $(".menu dl").click(function () {
                $(this).addClass("selected").siblings().removeClass("selected");
            })
            $('.footer dl').click(function () {
                $(this).addClass('cur').siblings().removeClass('cur');
            })


        })
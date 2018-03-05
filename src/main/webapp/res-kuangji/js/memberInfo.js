// JavaScript Document
$(function () {
    var wheight = $(window).height();
    var cheight = $('.general').height();
    var theight = (wheight - cheight) / 4;

    $('.general').css('top', theight);
    $('.mengban').height(wheight);
    $('.touxiang').click(function () {
        $('.mengban').show();
        $('.tanchuang').show();
    });
    $('.alipay').click(function () {
        if ($("input[name='alipay']").val() == "" || $("input[name='alipay']").val() == "****") {
            $('.mengban').show();
            $('.general').show();
            $('.general').find('dl dd input[name="typeid"]').val(1);
            $('.general').find('span').text('请输入支付宝账号');
            var text = $('.general').find('dl dd input[name="textid"]');
            text.attr("type", "text");
            text.val("");
            text.focus();
        }
    });
    $('.youxiang').click(function () {
        if ($("input[name='email']").val() == "") {
            $('.mengban').show();
            $('.general').show();
            $('.general').find('dl dd input[name="typeid"]').val(2);
            $('.general').find('span').text('请输入邮箱');
            var text = $('.general').find('dl dd input[name="textid"]');
            text.attr("type", "email");
            text.val("");
            text.focus();
        }
    });
    $('.invitation').click(function () {
        if ($("input[name='parent_id']").val() == ""||$("input[name='parent_id']").val()==0) {
            $('.mengban').show();
            $('.general').show();
            $('.general').find('dl dd input[name="typeid"]').val(3);
            $('.general').find('span').text('请输入邀请码（仅允许更新一次）');
            var text = $('.general').find('dl dd input[name="textid"]');
            text.attr("type", "text");
            text.val("");
            text.focus();
        }
    });
    $('.m_bank_no').click(function () {
        if ($("input[name='m_bank_no']").val() == "") {
            $('.mengban').show();
            $('.general').show();
            $('.general').find('dl dd input[name="typeid"]').val(5);
            $('.general').find('span').text('请输入建行卡号');
            var text = $('.general').find('dl dd input[name="textid"]');
            text.attr("type", "text");
            text.val("");
            text.focus();
        }
    });
    $('.general .cancle').click(function () {
        $(this).parents('.general').hide();
        $('.mengban').hide();
    });
    $('.bank').click(function () {
        if ($('.bank font span:eq(0)').text() == ""||((modifiable>ischange) && (bankchange == 0))) {
            $(".yinhang").show();
           // $('.mengban').show();
//            $('body').addClass('overfl');
        }
    });
    $('.bank_user').click(function () {
        if ($('.bank_user font:eq(1)').text() == ""||((modifiable>ischange)&&(bankuserchange ==0))) {
            $(".kaihuname").show();
            $('.mengban').show();
//            $('body').addClass('overfl');
        }
    });
    //银行户名修改
    $('.kaihuname .gb').click(function () {
        var addr = $('.kaihuname .key input').val();
        if (addr == "") {
            alert('请输入开户人姓名!');
            return;
        }
        bankuserchange = 1;
        saveUser('m_bank_user', addr);
        $('.bank_user font:eq(1)').text(addr);
        $(".mengban").hide();
        $(".kaihuname").hide();
    });
	if( $('#hide_phone').val() == ''){
		$(".phone").click(function(){
			if ($("input[name='phone']").val() == "") {
				$('.mengban').show();
				$('.general').show();
			    $('.general').find('dl dd input[name="typeid"]').val(4);
			    $('.general').find('span').text('请输入手机号');
			    var text = $('.general').find('dl dd input[name="textid"]');
			    text.attr("type", "phone");
			    text.val("");
			    text.focus();
			}
		})
	}
});
$(function () {
    var a = $('ul li input.wtext1').val();
    $('ul li input.wtext1').focus(function () {
        if (this.value == a) {
            $(this).val('');
            $(this).addClass('clsize');
        }
    }).blur(function () {
        if (this.value == '') {
            $(this).val(a);
            $(this).removeClass('clsize');
        }
    })
    var b = $('ul li input.wtext2').val();
    $('ul li input.wtext2').click(function () {
        if ($(this).val()==''|| ((bankchange == 0)&&(modifiable>ischange))) {
            $(this).addClass('clsize');
            $('.yinhang').show();
            $('.mengban').show();
        }
    })
    var c = $('ul li input.wtext3').val();
    $('ul li input.wtext3').click(function () {
        if ($(this).val()==''|| ((bankuserchange == 0)&&(modifiable>ischange))) {
            $(this).addClass('clsize');
        }
    })
    var d = $('ul li input.wtext4').val();
    $('ul li input.wtext4').click(function () {
        if ($(this).val()==''|| ((banknochange == 0)&&(modifiable>ischange))) {
            $(this).addClass('clsize');
        } 
    })
    var f = $('ul li input.wtext5').val();
    $('ul li input.wtext5').focus(function () {
        if (this.value == f) {
            $(this).val('');
            $(this).addClass('clsize');
        }
    }).blur(function () {
        if (this.value == '') {
            $(this).val(f);
            $(this).removeClass('clsize');
        }
    })
})
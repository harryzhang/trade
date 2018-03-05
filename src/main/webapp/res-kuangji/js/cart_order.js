/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 * 获得选定的商品属性
 */
function getSelectedAttributes(formBuy)
{
    var spec_arr = new Array();
    var j = 0;
    for (i = 0; i < formBuy.elements.length; i++)
    {
        var prefix = formBuy.elements[i].name.substr(0, 5);
        if (prefix == 'spec_' && (
                ((formBuy.elements[i].type == 'radio' || formBuy.elements[i].type == 'checkbox') && formBuy.elements[i].checked) ||
                formBuy.elements[i].tagName == 'SELECT'))
        {
            spec_arr[j] = formBuy.elements[i].value;
            j++;
        }
    }

    return spec_arr;
}
/**
 * 接收返回的信息
 */
function changePriceResponse(res)
{
    if (res.err_msg.length > 0)
    {
       // alert(res.err_msg);
         $('.shopping').hide();
                     $('.mengban').hide();
                     $('.notice').html(res.message).show().fadeOut(2500);
    } else
    {
        document.forms['ECS_FORMBUY'].elements['number'].value = res.qty;
        if (document.getElementById('ECS_GOODS_AMOUNT'))
            document.getElementById('ECS_GOODS_AMOUNT').innerHTML = res.result;
//            document.getElementById('ECS_GOODS_AMOUNT').innerHTML = res.result['shop_price']+'<br>代金券 '+res.result['offset_ticket']+'   重复消费 '+res.result['repeat_prize'];
    }
}
/**
 * 接收返回的信息
 */
function changeExchangePriceResponse(res)
{
    if (res.err_msg.length > 0)
    {
        alert(res.err_msg);
    } else
    {
        //document.forms['ECS_FORMBUY'].elements['number'].value = res.qty;
        if (document.getElementById('pay'))
            document.getElementById('pay').innerHTML = res.result;
    }
}
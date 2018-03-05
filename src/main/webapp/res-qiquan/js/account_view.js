var AccountView = Backbone.View.extend({
    el : '#account',
	template : '',
    startTime:'',
    endTime:'',
    acc:null,
    holdBill:Array(),
    events: {},
    initialize: function() {
		var _self=this;
        $.MsgBox.Loading('加载中...');
        $.get(assets_path+'page/trade/account.html?v=1.0', function(result){
            $.MsgBox.Clean();
            _self.template=_.template(result);
            _self.render();
        });
    },
    render: function() {
        var accountInfo = app.getAccountInfo();
        var userInfo = app.getUserInfo();
        this.$el.html(this.template({
            'assets_path': assets_path,
            'acc':accountInfo,
            'user':userInfo,
            'headimgurl' : wx_headimgurl,
        }));

        $('#account .qrcode').unbind('click');
        $('#account .qrcode').bind('click',function () {
            var qrUrl = 'http://wechat.luhongsuo.com/index.php?r=wechat/nqrcode&serial_code='+ac_serial_code;
            $.get(qrUrl,function (data) {
                var _html = "<img src=\""+data+"\"/>";
                $.MsgBox.Alert('提示',_html);
            })
        })

        $('#in-money-btn').unbind('click');
        $('#in-money-btn').bind('click',function(){
            if(window.wxPayPortInfo==undefined){
                $.MsgBox.AlertB('提示','该帐号暂未签约，无法入金，请马上签约！',function(){
                    $.afui.loadContent('#wechatPayRegister',false,false,'fade');
                });
            }else{
                window.tag ="in";
                $.afui.loadContent('#wechatPayMoney',false,false,'fade');

            }
        })
        $('#out-money-btn').unbind('click');
        $('#out-money-btn').bind('click',function(){
            if(window.wxPayPortInfo==undefined){
                $.MsgBox.AlertB('提示','该帐号暂未签约，无法出金，请马上签约',function(){
                    $.afui.loadContent('#wechatPayRegister',false,false,'fade');
                });
            }else{
                $.afui.loadContent('#wechatPayMoney',false,false,'fade');
                window.tag ="out";
                window.wechatPayMoneyView.moneyOutShow();

            }
        })

        $('#cancle-btn').unbind('click');
        $('#cancle-btn').bind('click',function(){
            if(window.wxPayPortInfo==undefined){
                $.MsgBox.AlertB('提示','该帐号暂未签约,请马上签约',function(){
                    $.afui.loadContent('#wechatPayRegister',false,false,'fade');
                });
            }else{
                var _html="<div align=\"center\">确定解除微信签约！</div>";
                $.MsgBox.Confirm('提示',_html,function(){
                    canclePayPort();
                });
            }
        })

        $('#hold-list-btn').unbind('click');
        $('#hold-list-btn').bind('click',function(){
            window.tag='holdbill';
            $.afui.loadContent('#holdbill',false,false,'fade');
        });

        $('#history-list-btn').unbind('click');
        $('#history-list-btn').bind('click',function(){
            window.tag='history';
            $.afui.loadContent('#holdbill',false,false,'fade');
        });

        app.updateHoldBills(function(){
            var _bill = app.getBills();
            $('#account .amholdListCount').html(_bill.length);

            var _closeProfitRate = app.getCloseProfitRate();
            $('#account .amCloseProfitRate').html(_closeProfitRate.toFixed(2));
        })

        $('.main-menu').width($('#account').width());

        sdk.on(PUSH_ACCCHANGE,this.accountChange,sdk);

        updatePayPort();
    },
    accountChange:function (mod) {
        var accountInfo = app.getAccountInfo();
        if(accountInfo['amWithdrawable']<0){
            accountInfo['amWithdrawable'] = 0;
        }
        $('#account .amWithdrawable').html(commafy(accountInfo['amWithdrawable'].toFixed(2)));
        $('#account .amMarginUsed').html(commafy(accountInfo['amMarginUsed'].toFixed(2)));
        $('#account .amMarginRemain').html(commafy(accountInfo['amMarginRemain'].toFixed(2)));
        //$('#account .amDailyCloseProfit').html(commafy(accountInfo['amDailyCloseProfit'].toFixed(2)));

        app.updateHoldBills(function(){
            var _bill = app.getBills();
            $('#account .amholdListCount').html(_bill.length);

            var _closeProfitRate = app.getCloseProfitRate();
            $('#account .amCloseProfitRate').html(_closeProfitRate.toFixed(2));
        })
    }
});

function canclePayPort() {
    var _userInfo=sdk.getUserInfo();
    var _payNo=_userInfo.userId+"3";
    var csrfToken = $('meta[name="csrf-token"]').attr("content");
    var _data= {
        payNo  : _payNo,
        _csrf  : csrfToken
    };
    var _ajax = $.ajax({
        url:api_path+"?r=server/jscanclepayport",
        data:_data,
        type:"POST",
        dataType:'json',
        timeout : 5000,
        beforeSend:function(){
        },
        success:function(result){
            if(result.retCode==0){
                window.wxPayPortInfo=undefined;
                window.accPayPortInfo=undefined;
                $.MsgBox.AlertB('success','解约成功！',function(){
                    $('#cancle-btn').hide();
                });
            }else{
                $.MsgBox.Alert('提示','解约失败！'+result.retText);
            }
        },
        complete : function(XMLHttpRequest,status){
            if(status=='timeout'){
                $.MsgBox.Alert('提示','服务连接超时！');
                _ajax.abort();
            }
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            $.MsgBox.Alert('提示','服务连接超时！');
        }
    })
}


function jsApiCall(args){
    WeixinJSBridge.invoke(
        'getBrandWCPayRequest',args,
        function(res){
            WeixinJSBridge.log(res.err_msg);
            window.tag = 'in';
            if(!window.wechatPayMoneyView){
                window.wechatPayMoneyView=new WechatPayMoneyView();
            }else{
                window.wechatPayMoneyView.render();
            }
            /*
            if(res.err_msg=='ok'){
                $.afui.loadContent('#wechatPayMoney',false,false,'fade');
            }else{
             $.afui.loadContent('#wechatPayMoney',false,false,'fade');
            }
            */
        }
    );
}

function callpay(args){
    if (typeof WeixinJSBridge == "undefined"){
        if( document.addEventListener ){
            document.addEventListener('WeixinJSBridgeReady', jsApiCall, false);
        }else if (document.attachEvent){
            document.attachEvent('WeixinJSBridgeReady', jsApiCall);
            document.attachEvent('onWeixinJSBridgeReady', jsApiCall);
        }
    }else{
        jsApiCall(args);
    }
}

var WechatPayMoneyView = Backbone.View.extend({
    el : "#wechatPayMoney",
    template : "",
    amChangeType : "wechat",
    events: {},
    initialize:function() {
        var _self=this;
        $.MsgBox.Loading('加载中...');
        $.get(assets_path+'page/trade/wechatPayMoney.html', function(result){
            $.MsgBox.Clean();
            _self.template=_.template(result);
            _self.render();
        });
    },
    render:function() {
        var _self=this;
        this.$el.html( this.template({
            'assets_path':assets_path,
            'ac_score':ac_score
        }));
        $('#wechatPayMoney .in-money-btn').unbind('click');
        $('#wechatPayMoney .in-money-btn').bind('click',function(){
            _self.moneyInShow();
        });
        $('#wechatPayMoney .out-money-btn').unbind('click');
        $('#wechatPayMoney .out-money-btn').bind('click',function(){
            _self.moneyOutShow();
        });

        var args={
            onSuccess:function(mods){
                if(mods.length>0){
                    window.accPayPortInfo = mods[0];
                    if(mods[0].payAccessType==2 && mods[0].payPortId ==20){
                        window.wxPayPortInfo = mods[0];
                    }
                }else{
                    window.wxPayPortInfo = undefined;
                }
                if(window.tag == 'in'){
                    _self.moneyInShow();
                }
                if(window.tag == 'out'){
                    _self.moneyOutShow();
                }
            },
            onFailure:function(code,message){
                window.wxPayPortInfo = undefined;
            }
        };
        sdk.queryPayPortInfo(args);
    },
    moneyOutShow:function(){
        var _self=this;
        $('#wechatPayMoney .triangle-down').hide();
        $('#wechatPayMoney .auClick').removeClass('current');
        $('#wechatPayMoney .triangle-down').eq(0).show();
        $('#wechatPayMoney .auClick').eq(0).addClass('current');
        $('#money-out-box input[name=amChange]').val("0");
        $('#money-out-box').show();
        $('#money-in-box').hide();

        $('#money-change-btn').unbind('click');
        $('#money-change-btn').bind('click',function(){
            _self.doMoneyOut();
        });
        var account=app.getAccountInfo();

        if(parseFloat(account['amWithdrawable'])<0){
            $('#money-out-box .acc-money').html("0");
        }else{
            $('#money-out-box .acc-money').html(commafy(account['amWithdrawable'].toFixed(2)));
        }

        $("#city").citySelect({
            url:"assets/default/component/city/city.min.js",
            prov:"", //省份
            city:"", //城市
            dist:"", //区县
            nodata:"none" //当子集无数据时，隐藏select
        });

        _self.amChangeType="wechat";
        $('.bankType').removeClass('current');
        if(_self.amChangeType=='ebank'){
            $("#wechatPayMoney .ebank").show();
            $('.bankType').eq(1).addClass('current');
        }
        if(_self.amChangeType=='wechat'){
            $("#wechatPayMoney .ebank").hide();
            $('.bankType').eq(0).addClass('current');
        }
        $(".bankType").unbind('click');
        $(".bankType").bind('click',function () {
            $('.bankType').removeClass('current');
            var _bankType = $(this).attr('bankType');
            _self.amChangeType = _bankType;
            if(_self.amChangeType=='ebank'){
                $("#wechatPayMoney .ebank").show();
                $('.bankType').eq(1).addClass('current');
            }
            if(_self.amChangeType=='wechat'){
                $("#wechatPayMoney .ebank").hide();
                $('.bankType').eq(0).addClass('current');
            }
        });
        $("input[name=amChange]").focus(function(){
            $(this).val("");
        });
    },
    moneyInShow:function(){
        var _self=this;
        var _amChangeType=_self.amChangeType;
        $('#wechatPayMoney .triangle-down').hide();
        $('#wechatPayMoney .auClick').removeClass('current');
        $('#wechatPayMoney .triangle-down').eq(1).show();
        $('#wechatPayMoney .auClick').eq(1).addClass('current');
        $('#money-in-box input[name=amChange]').val("0");
        $('#money-in-box').show();
        $('#money-out-box').hide();
        $('#money-change-btn').unbind('click');
        $('#money-change-btn').bind('click',function(){
            _self.doMoneyIn();
        })
        var account=app.getAccountInfo()
        $('#money-in-box .acc-money').html(parseFloat(account.amMarginRemain).toFixed(2));
        $("input[name=amChange]").focus(function(){
            $(this).val("");
        });
    },
    doMoneyOut:function(){
        var _self=this;
        $page=$('#money-out-box');
        var _amChange=parseFloat($page.find('input[name=amChange]').val());
        var _userInfo=sdk.getUserInfo();
        var _account=app.getAccountInfo();
        var _key=_userInfo.userId+"3";
        var csrfToken = $('meta[name="csrf-token"]').attr("content");

        var _bankName=$page.find('select[name=bankName]').val();
        var _bankSn=$page.find('input[name=bankSn]').val();
        var _bankUserName=$page.find('input[name=bankUserName]').val();
        var _bankProvinces=$page.find('select[name=bankProvinces]').val();
        var _bankCity=$page.find('select[name=bankCity]').val();

        var _amChangeType = _self.amChangeType;

        if(_amChangeType == 'ebank'){
            if(_bankName =='' ){
                $.MsgBox.Tip('error','请输入银行名称',function(){
                    _self.MONEYSUBMIT = 0;
                });
                return;
            }
            if(_bankSn =='' ){
                $.MsgBox.Tip('error','请输入银行帐号',function(){
                    _self.MONEYSUBMIT = 0;
                });
                return;
            }
            if(_bankUserName =='' ){
                $.MsgBox.Tip('error','请输入银行帐号户名',function(){
                    _self.MONEYSUBMIT = 0;
                });
                return;
            }
            if(_bankProvinces =='' || _bankProvinces==undefined ){
                $.MsgBox.Tip('error','请输入银行所在省份',function(){
                    _self.MONEYSUBMIT = 0;
                });
                return;
            }
            if(_bankCity =='' || _bankCity==undefined ){
                $.MsgBox.Tip('error','请输入银行所在城市',function(){
                    _self.MONEYSUBMIT = 0;
                });
                return;
            }
            if(_amChange<100){
                $.MsgBox.Tip('error','最小出金金额为100元整',function(){
                    _self.MONEYSUBMIT = 0;
                });
                return;
            }
        }

        if(_amChange<=0 || _amChange==undefined || isNaN(_amChange) || _amChange==''|| _amChange>parseFloat(_account['amWithdrawable']) ){
            $.MsgBox.Tip('error','请输入有效金额',function(){
                _self.MONEYSUBMIT = 0;
            });
            return;
        }


        var _data= {
            'amChange'       : _amChange,
            'key' : _key,
            'bankName'      : _bankName,
            'bankSn'        : _bankSn,
            'bankUserName' : _bankUserName,
            'bankProvinces': _bankProvinces,
            'bankCity'      : _bankCity,
            '_csrf'  : csrfToken
        };
        var _postUrl = "";
        if(_amChangeType == 'ebank'){
            _postUrl = api_path+"?r=server/jsbanktranferscall";
        }else{
            _postUrl = api_path+"?r=server/jstranferscall";
        }
        var _ajax = $.ajax({
            url:_postUrl,
            data:_data,
            type:"POST",
            dataType:'json',
            timeout : 5000,
            beforeSend:function(){
                $('#money-change-btn').html('提交中...');
            },
            success:function(result){
                _self.MONEYSUBMIT=0;
                $page.find('input[name=amChange]').val('0');
                if(result.retCode==0){
                    $.MsgBox.AlertB('提示','出金成功！',function(){
                        //$.afui.loadContent('#account',false,false,'fade');
                    });
                }else{
                    $.MsgBox.AlertB('提示',result.retText+'！',function(){
                        //$.afui.loadContent('#account',false,false,'fade');
                    });
                }
                $('#money-change-btn').html('提交');
            },
            complete : function(XMLHttpRequest,status){
                if(status=='timeout'){
                    _ajax.abort();
                    $.MsgBox.Tip('error','服务连接超时！',function(){
                        $page.find('input[name=amChange]').val('0');
                    });
                }
                $('#money-change-btn').html('提交');
            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                $.MsgBox.Tip('error','服务连接超时！',function(){
                    $page.find('input[name=amChange]').val('0');
                    $page.find('.money-btn').removeAttr("disabled");
                    _self.MONEYSUBMIT = 0;
                });
                $('#money-change-btn').html('提交');
            }
        })
    },
    doMoneyIn:function(){
        var _self=this;
        $page=$('#money-in-box');
        var _amChange=parseFloat($page.find('input[name=amChange]').val());
        var _userInfo=sdk.getUserInfo();
        var _key=_userInfo.userId+"3";
        var csrfToken = $('meta[name="csrf-token"]').attr("content");

        if(_amChange<=0 || _amChange==undefined || isNaN(_amChange) || _amChange=='' ){
            $.MsgBox.Tip('error','请输入有效金额',function(){
                _self.MONEYSUBMIT = 0;
            });
            return;
        }
        var isSign = 0;
        if(window.wxPayPortInfo!=undefined){
            isSign = 1;
        }
        var _data= {
            'amChange'       : _amChange,
            'key' :_key,
            '_csrf'  : csrfToken,
            'sign':isSign
        };
        var _ajax = $.ajax({
            url:api_path+"?r=server/jsapicall",
            data:_data,
            type:"POST",
            dataType:'json',
            timeout : 5000,
            beforeSend:function(){
                $('#money-change-btn').html('提交中...');
            },
            success:function(mod){
                if(mod.retCode==0){
                    callpay(mod.data);
                }else{
                    $.MsgBox.AlertB('提示','入金失败:'+mod.msg+'!',function(){
                        //$.afui.loadContent('#account',false,false,'fade');
                    });
                }
                $('#money-change-btn').html('提交');
            },
            complete : function(XMLHttpRequest,status){
                if(status=='timeout'){
                    _ajax.abort();
                    $.MsgBox.Alert('提示','服务连接超时！');
                    _self.MONEYSUBMIT = 0;
                }
                $('#money-change-btn').html('提交');
            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                $.MsgBox.Alert('提示','服务连接超时！');
                $page.find('input[name=amChange]').val('0');
                $page.find('.money-btn').removeAttr("disabled");
                _self.MONEYSUBMIT = 0;
                $('#money-change-btn').html('提交');
            }
        })
    }
});

var WechatPayRegisterView = Backbone.View.extend({
    el : '#wechatPayRegister',
    template : '',
    events: {},
    initialize:function() {
        var _self=this;
        $.MsgBox.Loading('加载中...');
        $.get('?r=site%2Fpayportregister', function(result){
            $.MsgBox.Clean();
            _self.template=_.template(result);
            _self.render();
        });
    },
    render:function() {
        var _self=this;
        var user= app.getUserInfo();
        var acc =app.getAccountInfo();
        this.$el.html( this.template({
            'assets_path':assets_path,
            'account':acc,
            'user':user,
            'wx_nickname':wx_nickname
        }));
        $('#payport-regist-btn').unbind('click');
        $('#payport-regist-btn').bind('click',function(){
            _self.doPayPortRegist();
        });
        $('#infoIDType').change(function(){
            $(this).parent().find('span').html($('#infoIDType option[value='+$(this).val() +']').html());
        })
        $('#verifyImg').attr('src','?r=site/validatecode&v='+Math.random());
        $('#verifyImg').unbind('click');
        $('#verifyImg').bind('click',function () {
            $(this).attr('src','?r=site/validatecode&v='+Math.random());
        })
    },
    doPayPortRegist:function(){
        $page=$(this.el);
        var _self=this;
        var _payAccountName= $page.find('input[name=payAccountName]').val();
        var _infoIDType= $page.find('select[name=infoIDType]').val();
        var _infoIDCard= $page.find('input[name=infoIDCard]').val();
        var _monyPwd= $page.find('input[name=monyPwd]').val();
        var _payNo = $page.find('input[name=payNo]').val();
        var _verify = $page.find('input[name=verify]').val();
        if(_payAccountName.length==0){
            $.MsgBox.Alert('error','请输入姓名');
            return;
        }
        if(_infoIDType.length==0){
            $.MsgBox.Alert('error','请选择证件类型');
            return;
        }
        if(_infoIDCard.length==0){
            $.MsgBox.Alert('error','请输入证件号');
            return;
        }
        if(_payNo.length==0){
            $.MsgBox.Alert('error','请输入席位号');
            return;
        }
        if(_verify.length==0){
            $.MsgBox.Alert('error','请输入验证码');
            return;
        }
        $('#payport-regist-btn').attr('disabled',"true");
        var csrfToken = $('meta[name="csrf-token"]').attr("content");
        var _data= {
            payAccountName : _payAccountName,
            infoIDType     : _infoIDType,
            infoIDCard     : _infoIDCard,
            monyPwd        : _monyPwd,
            payNo          : _payNo,
            verifyCode    : _verify,
            _csrf          : csrfToken
        };

        var _ajax = $.ajax( {
            url:api_path+"?r=server/jspayportregist",
            data:_data,
            type:'post',
            timeout : 5000,
            dataType:'json',
            before:function(){},
            success:function(mod) {
                console.log(mod);
                if(mod.retCode == 0){
                    updatePayPort();
                    $.MsgBox.AlertB('success','签约成功！',function(){
                        $.afui.loadContent('#account',false,false,'fade');
                    });
                }else{
                    $.MsgBox.AlertB('error','签约失败：'+mod.retText,function(){
                        _self.reset();
                    });
                }
            },
            complete : function(XMLHttpRequest,status){
                $('#payport-regist-btn').removeAttr("disabled");
                if(status=='timeout'){
                    _ajax.abort();
                    $.MsgBox.Alert('提示','服务连接超时！');
                }
            },
            error : function(XMLHttpRequest, textStatus, errorThrown) {
                $('#payport-regist-btn').removeAttr("disabled");
                _ajax.abort();
                $.MsgBox.Alert('提示','异常1！');

            }
        });
    },
    reset:function () {
        $('#verifyImg').attr('src','?r=site/validatecode&v='+Math.random());
        $('input[name=verify]').val('');
    },
    destroy:function(){
    },
});
function updatePayPort(){
    setTimeout(function(){
        var args={
            onSuccess:function(mods){
                $('#cancle-btn').hide();
                console.log(mods);
                if(mods.length>0){
                    window.accPayPortInfo = mods[0];
                    if(mods[0].payAccessType==2 && mods[0].payPortId ==20){
                        window.wxPayPortInfo = mods[0];
                        $('#cancle-btn').show();
                    }
                }else{
                    window.accPayPortInfo = undefined;
                    window.wxPayPortInfo = undefined;
                }
            },
            onFailure:function(code,message){}
        };
        sdk.queryPayPortInfo(args);
    },500);
}



var openSymbol;
var tChartInterval;
var TradeView=Backbone.View.extend({
    el : '#trade',
    template : '',
    initialize:function(){
        var _self=this;
        $.MsgBox.Loading('提示','加载中...');
        $.get(assets_path+'page/trade/trade.html', function(result){
            $.MsgBox.Clean();
            _self.template=_.template(result);
            _self.doXieyi();
        });
    },
    render:function() {
        var _self = this;
        this.$el.html(this.template({
            'symbols': app.getSymbolList(),
            'assets_path': assets_path,
            'headimgurl' : wx_headimgurl,
            'account':app.getAccountInfo()
        }));
        $('.pro-list li').each(function(index) {
            $(this).unbind("click");
            $(this).bind("click",function () {
                openSymbol = app.getSymbol($(this).attr("symbolId"));
                $.afui.loadContent('#order',false,false,'fade');
            });
        });
        $('#to-money-in-btn').unbind('click');
        $('#to-money-in-btn').bind('click',function () {
            window.tag = 'in';
            $.afui.loadContent('#wechatPayMoney',false,false,'fade');
        });

        $('#to-money-out-btn').unbind('click');
        $('#to-money-out-btn').bind('click',function () {
            if(ac_real==0){
                $.MsgBox.AlertB('提示',"请补充个人资料，审核通过后方可出金",function () {
                    $.afui.loadContent('#info',false,false,'fade');
                });
            }else{
                window.tag = 'out';
                $.afui.loadContent('#wechatPayMoney',false,false,'fade');
            }
        });

        $('.main-menu').width($('#trade').width());
        if (sdk) {
            sdk.on(M_R_PUSH_QUOTE,this.onQuoted,sdk) ;
        }
        updateUserInfo(function () {
            _self.doTip();
        });
    },
    onQuoted:function(mods) {
        var symbolInfo = mods[0];
        if (symbolInfo) {
            var $symbolObj = $('#' + symbolInfo.symbolCode);
            var $sell_obj =  $('#' + symbolInfo.symbolCode +' .sell');
            var $buy_obj =  $('#' + symbolInfo.symbolCode +' .buy');
            var $state_obj=  $('#' + symbolInfo.symbolCode +' .state');
            var $symbolTime_obj = $('#' + symbolInfo.symbolCode +' .symbolTime');
            if ($symbolObj.length > 0) {
                $buy_obj.html(commafy(symbolInfo.bid,symbolInfo.decimal));
                $sell_obj.html(commafy(symbolInfo.ask,symbolInfo.decimal));
                $symbolTime_obj.html(symbolInfo.lastTime.substr(10,10));
                if(symbolInfo.upOrDown>0){
                    $buy_obj.removeClass('red-bg').removeClass('green-bg').removeClass('grey-bg').addClass('red-bg');
                    $sell_obj.removeClass('red-bg').removeClass('green-bg').removeClass('grey-bg').addClass('red-bg');
                    $state_obj.removeClass('fall').addClass('rise');
                }
                if(symbolInfo.upOrDown<0){
                    $buy_obj.removeClass('red-bg').removeClass('green-bg').removeClass('grey-bg').addClass('green-bg');
                    $sell_obj.removeClass('red-bg').removeClass('green-bg').removeClass('grey-bg').addClass('green-bg');
                    $state_obj.removeClass('rise').addClass('fall');
                }
                if(symbolInfo.upOrDown==0){
                    $buy_obj.removeClass('red-bg').removeClass('green-bg').removeClass('grey-bg').addClass('grey-bg');
                    $sell_obj.removeClass('red-bg').removeClass('green-bg').removeClass('grey-bg').addClass('grey-bg');
                    $state_obj.find('.state').removeClass('rise').addClass('fall');
                }
            }
        }
    },
    getContract:function(symbolCode){
        if(sdk){
            var _contract = sdk.getContract(symbolCode);//合约编号
            if(!_contract){
                return null;
            }
            var arr = new Array();
            for (var i = 0; i < _contract.length; i++) {
                arr.push(_contract[i]);
            }
            arr.sort(function(x, y){
                return x.pricePoints>y.pricePoints;
            });
        }
        return arr;
    },
 	doXieyi:function () {
        var _self = this;
        var loginTime = getCookie('loginTime');
        var nowTime = Date.parse(new Date())/1000;
        if(nowTime-loginTime<1800){
            _self.render();
            return;
        }
        $.get(assets_path + 'page/trade/xieyi.html', function (text) {
            $.MsgBox.Xieyi('风险揭示书',text,function () {
                $.MsgBox.Clean();
                if(ac_real =="1"){
                    _self.render();
                }else{
					var _html ='<div>提交完整个人资料</div>';
					_html +='<div style="text-align:left;">尊敬的交易投资者：</div>';
					_html +='<div style="text-align:left;">您好，欢迎您登录四川禄宏微交易，为了保障您的账户资金安全，以及让你能够更及时出入金。请您完善您的个人资料，如未完成个人资料审核，您将无法正常出金。祝您交易愉快！</div>';
					_html +='<div style="text-align:right;">四川禄宏微交易</div>';
                    $.MsgBox.InfoT('提示',_html,function () {
                        $.afui.loadContent('#info',false,false,'fade');
                    },function () {
                        _self.render();
                    });
                }
            });
        });
    },
    doTip:function () {
        var userInfo =app.getUserInfo();
        var isshow = getCookie('tradeTip'+userInfo.uid);
        if(isshow!="1" && ac_login=="0"){
            $.get(assets_path + 'page/trade/tradetip.html', function (html) {
                $.MsgBox.Guide('新手指引',html);
                setCookie('tradeTip'+userInfo.uid,"1");
            });
        }
    },
    destroy:function(){
        if (sdk) {
            sdk.un(M_R_PUSH_QUOTE,this.onQuoted) ;
        }
    }
});


var OrderView=Backbone.View.extend({
    el: '#order',
    template: '',
    period: 4,//数据周期
    barCnt: 40, //历史数量
    contract: null,
    contracts: null,
    bsCode: '',//买卖方向
    bsCodeStr: '',
    amount :0,
    chartType :'t',
    contractUpdateCall: undefined,
    initialize: function () {
        var _self = this;
        $.MsgBox.Loading('提示', '加载中...');
        $.get(assets_path + 'page/trade/order.html?t='+Math.random(), function (result) {
            $.MsgBox.Clean();
            _self.template = _.template(result);
            _self.render();
        });
    },
    render: function () {
        var _self = this;
        var _gainsPoint = parseFloat(openSymbol.priceCurrent).add(-parseFloat(openSymbol.preClose));
        var _gains = (_gainsPoint) / openSymbol.preClose * 100;
        if (openSymbol.preClose == 0) _gains = 0;
        openSymbol.gains=_gains;
        openSymbol.gainsPoint=_gainsPoint;
        if(openSymbol.upOrDown>0){
            openSymbol.priceClass="red";
        }else if(openSymbol.upOrDown<0){
            openSymbol.priceClass="green";
        }else{
            openSymbol.priceClass="grey";
        }
        this.$el.html(this.template({
            'symbol': openSymbol,
            'assets_path': assets_path
        }));

        app.getSymbolContract(openSymbol.symbolId,function (mods) {
            _self.contracts = mods;
            //合约点数
            for (var i = 0; i < _self.contracts.length; i++) {
                if(i==0){
                    _self.contract = _self.contracts[0];
                }
                var _profitRate = (_self.contracts[i].profitRate*100).toFixed(0);
                $('.order-point').append("<li value='"+_self.contracts[i].contractId+"'  ><span>"+_self.contracts[i].pricePoints+"</span><span>"+_profitRate+"%</span></li>");
            }
            $('.order-point li').eq(0).addClass('current');
            $('.order-point li').unbind('click');
            $('.order-point li').bind('click',function () {
                $('.order-point li').removeClass('current');
                $(this).addClass('current');
                //$('input[name=order_money]').val("");
                _self.contract =  _self.getContract(parseInt( $(this).attr('value')));
                //_self.bindOrderPrice();
            });

            //投资金额
            if( _self.contract){
                _self.contract=_self.contracts[0];
                $('.order-price .amount').remove();
                $('.order-price').prepend('<li class="amount" value="500">500元</li>');
                $('.order-price').prepend('<li class="amount" value="200">200元</li>');
                $('.order-price').prepend('<li class="amount current" value="20">20元</li>');
                $('.order-price li').unbind('click');
                $('.order-price li').bind('click',function () {
                    $('.order-price li').removeClass('current');
                    $(this).addClass('current');
                    _self.amount=parseInt( $(this).attr('value'));
                });
            }
            $('input[name=order_money]').focus(function () {
                $(this).val("");
                $('.order-price li').removeClass('current');
                $(this).parent().addClass('current');
            })

            //下单
            $('.sell-button').unbind('click');
            $('.sell-button').bind('click',function () {
                _self.bsCode = "s";
                _self.doOrder();
            })
            $('.buy-button').unbind('click');
            $('.buy-button').bind('click',function () {
                _self.bsCode = "b";
                _self.doOrder();
            })
        })

        $('.kline-menu li').unbind('click');
        $('.kline-menu li').each(function(index){
            $(this).bind('click',function () {
                $('.kline-menu li').removeClass('current');
                $(this).addClass('current');
                switch (index){
                    case 0:
                        _self.period = 4;
                        _self.chartType = 't';
                        break;
                    case 1:
                        _self.period = 4;
                        _self.chartType = 'k';
                        break;
					case 2:
                        _self.period = 5;
                        _self.chartType = 'k';
                        break;
					case 3:
                        _self.period = 6;
                        _self.chartType = 'k';
                        break;
                    case 4:
                        _self.period = 7;
                        _self.chartType = 'k';
                        break;
                }
                _self.updateKind();
            });
        });
        $('.hold-tab').unbind('click');
        $('.hold-tab').bind('click',function () {
            window.tag='holdbill';
            $.afui.loadContent('#holdbill',false,false,'fade');
        });

        this.updateKind();
        tChartInterval = setInterval(function () {
            window.kline_frame.postMessage(openSymbol,'http://wechat.luhongsuo.com');
        },1000);

 $('.main-menu').width($('#order').width());

        this.doTip();

        if (sdk) {
            sdk.on(M_R_PUSH_QUOTE,this.onQuoted,sdk) ;
        }
    },
    bindOrderPrice:function () {
        var _self = this;
        $('.order-price .amount').remove();
        $('.order-price').prepend('<li class="amount" value="500">500元</li>');
        $('.order-price').prepend('<li class="amount" value="200">200元</li>');
        $('.order-price').prepend('<li class="amount current" value="20">20元</li>');
        $('.order-price li').unbind('click');
        $('.order-price li').bind('click',function () {
            $('.order-price li').removeClass('current');
            $(this).addClass('current');
            _self.amount=parseInt( $(this).attr('value'));
        });
    },
    updateKind:function(){
        var date =  new Date(openSymbol.lastTime.replace(/-/g, '/'));
        var timestamp = Date.parse(date);
        var timestamp = timestamp / 1000;
        $('.kline-box').html(' <iframe id="kline_frame" name="kline_frame" src="?r=site/chart&period='+this.period+"&symbolcode="+openSymbol.symbolCode+"&barcount="+this.barCnt+"&decimal="+openSymbol.decimal+"&market="+market+'&charttype='+this.chartType+'&nowTime='+timestamp+'"+ width="100%" height="200" frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="no" allowtransparency="yes"></iframe>');
    },
    getContract:function (contractId) {
        for (var i = 0; i < this.contracts.length; i++) {
            if(this.contracts[i].contractId == contractId){
                return this.contracts[i];
            }
        }
    },
    doOrder:function(){
        var _self = this;
        var symbolCode = openSymbol.symbolCode;
        var contractId = this.contract.contractId;
        var invest = 0;
        var bsCode = this.bsCode;
        var orderPrice = openSymbol.priceCurrent;
        var pointOffset = DEFAULT_POINT_OFFSET;

        if($('.order-price .current').length>0){
            if($('.order-price .current input[name=order_money]').length>0){
                invest = $('input[name=order_money]').val();
            }else{
                invest = $('.order-price .current').attr('value');
            }
        }
        if(invest.length==0){
            $.MsgBox.Alert('提示',"请输入合约定金");
            return;
        }
        var re = /^[1-9]+[0-9]*]*$/;
        if (!re.test(invest)) {
            $.MsgBox.Alert('提示','合约定金必须为整数');
            return;
        }
        if(parseInt(invest)!=invest){
            $.MsgBox.Alert('提示','合约定金必须为整数');
            return;
        }
        if(invest > this.contract.amountMax ){
            $.MsgBox.Tip('提示','商品最大合约定金为'+this.contract.amountMax);
            return;
        }
        if(invest < this.contract.amountMin ){
            $.MsgBox.Tip('提示','商品最小合约定金为'+this.contract.amountMin);
            return;
        }

        var _bsCodeStr='';
        var _bsCodeClass='';
        if( bsCode =='s'){
            _bsCodeStr='买跌';
            _bsCodeClass='green-bg buy-icon';

        }
        if( this.bsCode=='b'){
            _bsCodeStr='买涨';
            _bsCodeClass='red-bg buy-icon';
        }
      if(this.bsCode =="b"){
            var _winPrice = parseFloat(openSymbol.priceCurrent).add(this.contract.pricePoints/Math.pow(10,openSymbol.decimal));
            var _losePrice = parseFloat(openSymbol.priceCurrent).add(-this.contract.pricePoints/Math.pow(10,openSymbol.decimal));
        }
        if(this.bsCode =="s"){
            var _winPrice = parseFloat(openSymbol.priceCurrent).add(-this.contract.pricePoints/Math.pow(10,openSymbol.decimal));
            var _losePrice = parseFloat(openSymbol.priceCurrent).add(this.contract.pricePoints/Math.pow(10,openSymbol.decimal));
        }
        var _orderNum = (parseFloat(invest)/parseFloat(openSymbol.priceCurrent)).toString();
        _orderNum=_orderNum.substr(0,_orderNum.lastIndexOf('.')+3);

        var _orderDate={
            'symbolName':openSymbol.symbolName,
            'symbolCode':openSymbol.symbolCode,
            'pricePoints':this.contract.pricePoints,
            'priceCurrent':openSymbol.priceCurrent,
            'winPrice':_winPrice,
            'losePrice':_losePrice,
            'amount':invest,
            'bsCodeStr':_bsCodeStr,
            'bsCodeClass':_bsCodeClass,
            'orderNum':_orderNum,
            'profitRate':parseFloat(this.contract.profitRate)*100,
            'unit':getUnit(openSymbol.symbolCode),
            'decimal':openSymbol.decimal
        };

        var orderTemplate = _.template($('#orderConfirmTemplate').html());
        var comfimHtml=orderTemplate({
            'item':_orderDate
        });
        $.MsgBox.OrderConfirm('订单确认',comfimHtml,function(){
            sdk.pointOptionOpen({
                symbolCode:symbolCode,
                contractId:contractId,
                quantity:invest,
                bsCode:bsCode,
                orderPrice:openSymbol.priceCurrent,
                pointOffset:pointOffset,
                timeExpire:'',
                memo:'',
                onSuccess: function (mods) {
                    if(mods.bizRet=="0"){
                        $.MsgBox.Tip('success','下单成功');
                    }else{
                        $.MsgBox.Alert('error',mods.message);
                         $('input[name=order_money]').val("");
                    }
                },
                onFailure: function (mods, message) {
                    $.MsgBox.Alert('提示',message);
                    $('input[name=order_money]').val("");
                }
            })
        })


    },
    onQuoted:function(mods){
        var symbolInfo = mods[0] ;
        if (symbolInfo) {
            if(symbolInfo.symbolCode== openSymbol.symbolCode){
                openSymbol = symbolInfo;
                var _gainsPoint = parseFloat(openSymbol.priceCurrent).add(-parseFloat(openSymbol.preClose));
                var _gains = (_gainsPoint) / openSymbol.preClose * 100;
                if(window.orderView.bsCode =="b"){
                    var _winPrice = parseFloat(openSymbol.priceCurrent).add( window.orderView.contract.pricePoints/Math.pow(10,openSymbol.decimal));
                    var _losePrice = parseFloat(openSymbol.priceCurrent).add(- window.orderView.contract.pricePoints/Math.pow(10,openSymbol.decimal));
                }
                if(window.orderView.bsCode =="s"){
                    var _winPrice = parseFloat(openSymbol.priceCurrent).add(-window.orderView.contract.pricePoints/Math.pow(10,openSymbol.decimal));
                    var _losePrice = parseFloat(openSymbol.priceCurrent).add( window.orderView.contract.pricePoints/Math.pow(10,openSymbol.decimal));
                }
                $('.symbol-price').html(commafy(openSymbol.priceCurrent,openSymbol.decimal));
                $('.order-symbol-price').html(commafy(openSymbol.priceCurrent,openSymbol.decimal));
                $('.symbol-gains').html(commafy(_gainsPoint,openSymbol.decimal)+"("+_gains.toFixed(2)+"%)");
                $('.low-price').html(commafy(openSymbol.dailyLowest,openSymbol.decimal));
                $('.high-price').html(commafy(openSymbol.dailyHighest,openSymbol.decimal));
                $('.'+symbolInfo.symbolCode+'winPrice').html(commafy(_winPrice,openSymbol.decimal));
                $('.'+symbolInfo.symbolCode+'losePrice').html(commafy(_losePrice,openSymbol.decimal));
            }
        }
    },
    doTip:function () {
        var userInfo =app.getUserInfo();
        var isshow = getCookie('orderTip'+userInfo.uid);
        if(isshow!="1" && ac_login=="0"){
            $.get(assets_path + 'page/trade/ordertip.html', function (html) {
                $.MsgBox.Guide('新手指引',html);
                setCookie('orderTip'+userInfo.uid,"1");
                $.get(api_path+"?r=server/firstlogin&uid="+userInfo.uid);
            });
        }
    },
    destroy:function(){
        if (sdk) {
            sdk.un(M_R_PUSH_QUOTE,this.onQuoted) ;
        }
        clearInterval(tChartInterval);
    }
});




function getBill(code){
    for(var i=0;i<window.tradeView.holdBill.length;i++){
        if(window.tradeView.holdBill[i].orderCode==code.toString()){
            return window.tradeView.holdBill[i];
        }
    }
}
function buildContract(symbolCode){
    var _contracts = window.tradeView.getContract(symbolCode);
    window.tradeView.contracts = _contracts;
    var _contractHtml= "<span class=\"contract-tit\">止盈/止损点</span>";
    if(_contracts){
        _contractHtml += "<div style='width:68%; float: left; overflow: hidden; '>";
        for(var i=0;i<_contracts.length;i++){
            if( parseInt( _contracts[i].contractStatus) ==2 ){
                _contractHtml += "<div class=\"toplimit-point\" style=\"width:29%;margin-bottom: 5px\" contractId=\""+_contracts[i].contractId+"\">"+_contracts[i].pricePoints+"</span></div>";
            }
        }
        _contractHtml += "</div>";
        if(_contracts.length%3==0){
            $('.toplimit').height(parseInt(_contracts.length/3)*30);
        }else{
            $('.toplimit').height(parseInt(_contracts.length/3)*30+30);
        }
    }
    $('.toplimit').html(_contractHtml);
    $('.toplimit-point').each(function(){
        $(this).bind('click',function(){
            $('.toplimit-point').removeClass('active');
            $(this).addClass('active');
            window.tradeView.contract=getContractByID(openSymbol.symbolCode,$(this).attr('contractId'));
        })
    })
    $('.pro-order').width($('.pro-order').parent().width());
}
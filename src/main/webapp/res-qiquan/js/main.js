var loginStatus = 0;
var DEFAULT_POINT_OFFSET = 10;
var MAX_POINT_OFFSET = 20;
var BINARYMODE ="2";
var BinMarketBill = Array();
var Bulletin =Array();
var marketBillInterval,bulletinInterval;
var images = {
    localId: [],
    serverId: [],
    imgMap: []
};
var encrypt = new JSEncrypt();
encrypt.setPublicKey($('#pubkey').val());
_.extend(window, Backbone.Events);
window.onresize = function() { window.trigger('resize') };
(function(){
    "use strict";
	$.afui.useOSThemes=false;
	$.afui.loadDefaultHash=false;
	$.afui.autoLaunch=true;
    $.afui.setBackButtonText("");
    $.afui.setBackButtonVisibility(false);
	if($.os.ios)
	$.afui.animateHeader(false);
    $.afui.ready(function(){
		Backbone.history.start();
		var AppRouter = Backbone.Router.extend({
			routes:{
			    "login"  : "login",
			    "main"   : "main",
			    "trade"  : "trade",
                "order"  : "order",
                "system" : "system",
                "account": "account",
                "info"   : "info",
                "history" :"history",
                "holdbill" :"holdbill",
                "message"  : "message",
                "point"  : "point",
                "pointHistory"  : "pointHistory",
                "promote":"promote",
                "circle_wealth":"circle_wealth",
                "about":"about",
                "wechatPayRegister":"wechatPayRegister",
                "wechatPayMoney":"wechatPayMoney",
                "sign":"sign"
			},
            destroy:function(){
                if(window.tradeView){
                    window.tradeView.destroy();
                }
                if(window.orderView){
                    window.orderView.destroy();
                }
				if(window.holdBillView){
                    window.holdBillView.destroy();
                }
            },
			main:function(){
			},
			trade:function(){
				this.destroy();
  				if(!window.tradeView){
					window.tradeView = new TradeView();
                }else{
					window.tradeView.render();
				}
                FastClick.attach(document.body);
			},
            order:function () {
                this.destroy();
                if(!window.orderView){
                    window.orderView = new OrderView();
                }else{
                    window.orderView.render();
                }
                FastClick.attach(document.body);
            },
            system:function(){
                this.destroy();
                if(!window.systemView){
                    window.systemView = new SystemView();
                }else{
                    window.systemView.render();
                }
                FastClick.attach(document.body);
            },
            account:function(){
                this.destroy();
                if(!window.accountView){
                    window.accountView = new AccountView();
                }else{
                    window.accountView.render();
                }
                FastClick.attach(document.body);
            },
            info:function(){
                this.destroy();
                if(!window.infoView){
                    window.infoView = new InfoView();
                }else{
                    window.infoView.render();
                }
                FastClick.attach(document.body);
            },
            history:function(){
                this.destroy();
                if(!window.historyView){
                    window.historyView = new HistoryView();
                }else{
                    window.historyView.render();
                }
                FastClick.attach(document.body);
            },
            holdbill:function(){
                this.destroy();
                if(!window.holdBillView){
                    window.holdBillView = new HoldbillView();
                }else{
                    window.holdBillView.render();
                }
                FastClick.attach(document.body);
            },
            message:function(){
                this.destroy();
                if(!window.messageView){
                    window.messageView = new MessageView();
                }else{
                    window.messageView.render();
                }
                FastClick.attach(document.body);
            },
            point:function(){
                this.destroy();
                if(!window.pointView){
                    window.pointView = new PointView();
                }else{
                    window.pointView.render();
                }
                FastClick.attach(document.body);
            },
            pointHistory:function(){
                this.destroy();
                if(!window.pointHistoryView){
                    window.pointHistoryView = new PointHistoryView();
                }else{
                    window.pointHistoryView.render();
                }
                FastClick.attach(document.body);
            },
            promote:function(){
                this.destroy();
                if(!window.promoteView){
                    window.promoteView = new PromoteView();
                }else{
                    window.promoteView.render();
                }
                FastClick.attach(document.body);
            },
            circle_wealth:function(){
                this.destroy();
                if(!window.circleWealthView){
                    window.circleWealthView = new CircleWealthView();
                }else{
                    window.circleWealthView.render();
                }
                FastClick.attach(document.body);
            },
            about:function(){
                this.destroy();
                if(!window.aboutView){
                    window.aboutView = new AboutView();
                }else{
                    window.aboutView.render();
                }
                FastClick.attach(document.body);
            },
            wechatPayMoney:function(){
                this.destroy();
                if(!window.wechatPayMoneyView){
                    window.wechatPayMoneyView=new WechatPayMoneyView();
                }else{
                    window.wechatPayMoneyView.render();
                }
                FastClick.attach(document.body);
            },
            wechatPayRegister:function(){
                this.destroy();
                if(!window.wechatPayRegisterView){
                    window.wechatPayRegisterView=new WechatPayRegisterView();
                }else{
                    window.wechatPayRegisterView.render();
                }
                FastClick.attach(document.body);
            },
            sign:function(){
                this.destroy();
                if(!window.signView){
                    window.signView=new SignView();
                }else{
                    window.signView.render();
                }
                FastClick.attach(document.body);
            },
			defaultRoute:function(actions){

			}
        });
        var app_router = new AppRouter;
        //if(loginStatus==0){
        //	new LoginView();
        //}
        resetW();
        FastClick.attach(document.body);

    });


    $(window).resize(function() {
        resetW();
    });
    if(isWeiXin()){
        getWXJSSDK();
    }

})(jQuery);


function addSocketListener(){
    if(!sdk){
        return false;
    }
    app.initSymbols();
    app.initAccountInfo();
    setTimeout(function () {
        app.initHoldBill();
    },1000);
    //用户在异地登录通知
    sdk.on(PUSH_SAMEUSER_LOGIN,function(){
        $.MsgBox.AlertB('提示','服务连接中断，请重新登录!',function(){
            location.reload();
        });
    },sdk);

    //下单回调应答通知
    sdk.on(PUSH_BINMARKETBILL,function(mods){
        if(mods.length>0){
            for(var i=0;i<mods.length;i++){
                if(mods[i].orderStatus=="2" || mods[i].orderStatus=="4"){
                    BinMarketBill.push(mods[i]);
                }
            }
        }
    },sdk);

    //帐号资金变动通知
    sdk.on(PUSH_ACCCHANGE,function(mods){
        app.updateAccountInfo(mods);
    },sdk);

    //交易商公告通知
    sdk.on(PUSH_BULLETIN,function(mods){
        if(mods.length>0){
            for(var i=0;i<mods.length;i++){
                Bulletin.push(mods[i]);
            }
        }
    },sdk);

    //行情通知
    sdk.on(M_R_PUSH_QUOTE,app.updateSymbols,sdk) ;


    var defalt_point_offset
    defalt_point_offset = getCookie("defalt_point_offset");
    if(defalt_point_offset != 'undefined' && defalt_point_offset !=undefined && defalt_point_offset !=""){
        DEFAULT_POINT_OFFSET = defalt_point_offset;
    }
     marketBillInterval=setInterval(function(){
        showBinMarketBill();
    },1500);
    bulletinInterval=setInterval(function(){
        showBulletin();
    },10000);
    window.addEventListener('offline', updateIndicator);
}



/*
* 公共函数
* */
function updateIndicator(){
    $.MsgBox.AlertB('提示',"网络中断，请重新登录",function(){
        location.reload();
    });
}

function onVisibility(){
	sdk.queryBinHoldBill({
		onSuccess : function(mods) {
			for(var i=0;i<mods.length;i++){
				if(mods[i].binaryMode==BINARYMODE) {
                    var old_hold_bill = holdBills;
                    for(var j=0;j<old_hold_bill.length;j++){
                        if(mods[i].orderCode==old_hold_bill[j].orderCode && (mods[i].orderStatus == "4"|| mods[i].orderStatus == "2" )){
                            BinMarketBill.push(mods[i]);
                        }
                    }
				}
			}
            holdBills = mods;
            $('#account .amholdListCount').html(holdBills.length);
            var _closeProfitRate = app.getCloseProfitRate();
            $('#account .amCloseProfitRate').html(_closeProfitRate.toFixed(2));
            resetW();
		},
		onFailure : function(code,message){
            resetW();
		}
	});
	$.MsgBox.Tip('提示','加载中...');
}
function showBulletin(){
    if(Bulletin.length>0 && $('.open-box').length==0) {
        var mod = Bulletin[0];
        Bulletin.remove(0);
        var template = _.template($('#bulletinTemplate').html());
        var _html=template({
            'item':mod
        });
        $.MsgBox.Bulletin(mod.title,_html,function(){});
        console.log('showBulletin');
        console.log(mod);
    }
}
function showBinMarketBill(){
 	if(BinMarketBill.length>0 && $('#tline-iframe').length>0){
        $.MsgBox.Clean();
    }
    if(BinMarketBill.length>0 && $('.open-box').length==0){
        var mod = BinMarketBill[0];
        var symbol = sdk.getSymbol( sdk.symbolId2Code(mod.symbolId));
        mod.symbolName = symbol.symbolName;
 		var _temp=mod.closeTime.split('T');
       	if(_temp.length>1){
			  var _endDateStr=_temp[0].substring (0,4)+"-"+_temp[0].substring  (4,6)+"-"+_temp[0].substring  (6,8)+" "+_temp[1].substring (0,2)+":"+_temp[1].substring  (2,4)+":"+_temp[1].substring  (4,6);
			  var _closeDate =  new Date(Date.parse(_endDateStr.replace(/-/g, "/")));
			   mod.closeTime= _closeDate.format('yyyy-MM-dd hh:mm:ss');
		}
		var _temp1=mod.openTime.split('T');
		if(_temp1.length>1){
			 var _startDateStr=_temp1[0].substring (0,4)+"-"+_temp1[0].substring  (4,6)+"-"+_temp1[0].substring  (6,8)+" "+_temp1[1].substring (0,2)+":"+_temp1[1].substring  (2,4)+":"+_temp1[1].substring  (4,6);
			 var _openDate =  new Date(Date.parse(_startDateStr.replace(/-/g, "/")));
			  mod.openTime= _openDate.format('yyyy-MM-dd hh:mm:ss');
		}

        if(mod.bsCode=='b'){
            mod.bsCodeStr = "买涨";
            mod.bsCodeClaaa="red-bg";
mod.pricePoint = mod.pointTakeProfit;
        }
        if(mod.bsCode=='s'){
            mod.bsCodeStr ="买跌";
            mod.bsCodeClaaa="green-bg";
	mod.pricePoint = mod.pointStopLose;
        }
        if(mod.profitClose>0){
            mod.profitColor = 'red';
        }
        if(mod.profitClose<0){
            mod.profitColor = 'green';
        }
        if(mod.profitClose>0){
            mod.profitClose=parseFloat(mod.profitClose)-parseFloat(mod.chargeClose)-parseFloat(mod.chargeOpen);
        }
mod.decimal = symbol.decimal;
        		var _orderNum = (parseFloat(mod.amount)/parseFloat(mod.priceOpen)).toString();
        _orderNum=_orderNum.substr(0,_orderNum.lastIndexOf('.')+3);
		mod.orderNum =  _orderNum;
        
        var template = _.template($('#noteTemplate').html());
        var _html=template({
            'item':mod
        });
        BinMarketBill.remove(0);
        $('#'+mod.orderCode).remove();//删除持仓显示
		playMusic();//播放音频
        $.MsgBox.OrderCloseConfirm('提示',_html,function(){
            if($('.hold-list-content li').length==0){
                $('.hold-list-content').html("<div class='no-recorder'>暂无记录</div>");
            }
        });
    }

}
function pickCircle(symbol,holdBill){
    /*
    var isHold = 0;
    for(var i=0;i<window.tradeView.holdBill.length;i++){
        if(holdBill.orderCode == window.tradeView.holdBill[i].orderCode){
            window.tradeView.holdBill.remove(i);
        }
    }
    for(var i=0;i<window.tradeView.holdBill.length;i++){
        if(symbol.symbolId == window.tradeView.holdBill[i].symbolId){
            isHold = 1;
        }
    }
    if(isHold == 0){
        $('#'+symbol.symbolCode+'circle').removeClass('circle2').addClass('nocircle');
    }
    */
}
function resetW(){

    var $pageObj=$('.pages').eq(0);
    if(loginStatus==1){
        $pageObj=$('.pages').eq(1);
    }
    if($pageObj.width()<$(window).width()){
        var _left=($(window).width()-$pageObj.width())/2;
        $pageObj.css({'margin-left':_left});
		if($('#guide_mb_box').length>0){
            $('#guide_mb_box').css({'margin-left':_left});
            $('#guide_mb_box').width($pageObj.width());
        }
    }
    $pageObj.find('.main-menu').width($pageObj.width());

}
function updateCloseProfit(callback){
    var dailyCloseProfit = 0;
    sdk.queryBinHoldBill({
        onSuccess : function(mods) {
			holdBills = mods;
            var closeHoldList =Array();
            for(var i=0;i<mods.length;i++){
                if(mods[i].binaryMode==BINARYMODE) {
                    if (mods[i].orderStatus == "4" || mods[i].orderStatus == "2") {
                        if (mods[i].profitClose > 0) {
                            dailyCloseProfit = dailyCloseProfit + mods[i].profitClose*mods[i].profitRate;
                        } else {
                            dailyCloseProfit = dailyCloseProfit + mods[i].profitClose;
                        }
                        closeHoldList.push(mods[i]);
                    }
                }
            }
            $('.amDailyCloseProfit').html(commafy(dailyCloseProfit.toFixed(2)));
            if(typeof callback == 'function'){
                callback(closeHoldList);
            }
        }
    });
}
function getDateStr(AddDayCount) {
    var dd = new Date();
    dd.setDate(dd.getDate()+AddDayCount);//获取AddDayCount天后的日期
    var y = dd.getFullYear();
    var m = dd.getMonth()+1;//获取当前月份的日期
    var d = dd.getDate();
    return dd.format('yyyy-MM-dd');
}
function getUnit(symbolCode){
    if(symbolCode.indexOf('Q')>-1){
       return 't';
    }
    if(symbolCode.indexOf('G')>-1){
        return 'kg';
    }
    if(symbolCode.indexOf('AG')>-1){
        return 'kg';
    }
    if(symbolCode.indexOf('OIL')>-1){
        return 't';
    }
    if(symbolCode.indexOf('AL')>-1){
        return 't';
    }
    return 'g';
}
function setCookie(name,value){
    var Days = 30;
    var exp = new Date();
    exp.setTime(exp.getTime() + Days*24*60*60*1000);
    document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
}
function getCookie(name){
    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
    if(arr=document.cookie.match(reg))
        return unescape(arr[2]);
    else
        return "";
}
function commafy(num,pointNum) {
    //1.先去除空格,判断是否空值和非数
    //2.数字格式转换成千分位
    num = num + "";
    num = num.replace(/[ ]/g, ""); //去除空格
	if (num == "--") {
        return num;
    }
    if (num == "") {
        return;
    }
    if (isNaN(num)){
        return;
    }
    //2.针对是否有小数点，分情况处理
    var index = num.indexOf(".");
    if (index==-1) {//无小数点
        var reg = /(-?\d+)(\d{3})/;
        while (reg.test(num)) {
            num = num.replace(reg, "$1,$2");
        }
    } else {
        var intPart = num.substring(0, index);
        var pointPart;
        if(pointNum){
            pointPart = num.substring(index + 1, index + 1+pointNum);
            if(pointPart.length<pointNum){
                var zeroStr ='0000000000';
                pointPart = pointPart + zeroStr.substring(0,pointNum-pointPart.length);
            }
        }else{
            pointPart = num.substring(index + 1, num.length);
        }
        var reg = /(-?\d+)(\d{3})/;
        while (reg.test(intPart)) {
            intPart = intPart.replace(reg, "$1,$2");
        }
        num = intPart +"."+ pointPart;
    }
    return num;
}
//给Number类型增加一个add方法，调用起来更加方便。
Number.prototype.add = function (arg) {
    return accAdd(arg, this);
};
/**
 ** 加法函数，用来得到精确的加法结果
 ** 说明：javascript的加法结果会有误差，在两个浮点数相加的时候会比较明显。这个函数返回较为精确的加法结果。
 ** 调用：accAdd(arg1,arg2)
 ** 返回值：arg1加上arg2的精确结果
 **/
function accAdd(arg1, arg2) {
    var r1, r2, m, c;
    try {
        r1 = arg1.toString().split(".")[1].length;
    }
    catch (e) {
        r1 = 0;
    }
    try {
        r2 = arg2.toString().split(".")[1].length;
    }
    catch (e) {
        r2 = 0;
    }
    c = Math.abs(r1 - r2);
    m = Math.pow(10, Math.max(r1, r2));
    if (c > 0) {
        var cm = Math.pow(10, c);
        if (r1 > r2) {
            arg1 = Number(arg1.toString().replace(".", ""));
            arg2 = Number(arg2.toString().replace(".", "")) * cm;
        } else {
            arg1 = Number(arg1.toString().replace(".", "")) * cm;
            arg2 = Number(arg2.toString().replace(".", ""));
        }
    } else {
        arg1 = Number(arg1.toString().replace(".", ""));
        arg2 = Number(arg2.toString().replace(".", ""));
    }
    return (arg1 + arg2) / m;
}
//修复IE9&safari 的sort方法
!function(window){
    var ua = window.navigator.userAgent.toLowerCase();
    reg = /msie|applewebkit|applewebkit.+safari/;
    if(reg.test(ua)){
        var _sort = Array.prototype.sort;
        Array.prototype.sort = function(fn){
            if(!!fn && typeof fn === 'function'){
                if(this.length < 2) return this;
                var i = 0, j = i + 1, l = this.length, tmp, r = false, t = 0;
                for(; i < l; i++){
                    for(j = i + 1; j < l; j++){
                        t = fn.call(this, this[i], this[j]);
                        r = (typeof t === 'number' ? t :
                            !!t ? 1 : 0) > 0
                            ? true : false;
                        if(r){
                            tmp = this[i];
                            this[i] = this[j];
                            this[j] = tmp;
                        }
                    }
                }
                return this;
            }else{
                return _sort.call(this);
            }
        };
    }
}(window);

var symbols = Array();
var symbolsMap = Array();
var accountInfo = {};
var holdBills = Array();
var app={
    "tradeMarketId":20,
    "tradeMarketName":"微交易",
    /**
     * 初始化商品
     */
    "initSymbols":function () {
        var _temp = sdk.getSymbols(-1);
        for(var i=0;i<_temp.length;i++){
            if(_temp[i].tradeMarketId==this.tradeMarketId){
                symbols.push(_temp[i]);
                symbolsMap.push(_temp[i].symbolId);
            }
        }
    },
    /**
     * 商品更新
     */
    "updateSymbols":function (mods) {
        for(var i=0; i<mods.length;i++){
            var index = _.indexOf(symbolsMap, mods[i].symbolId);
            symbols[index] = mods[i];
            console.log(index);
        }
    },
    /**
     * 商品更新
     * @parse symbolId
     */
    "getSymbol":function (symbolId) {
        var index = _.indexOf(symbolsMap, parseInt(symbolId));
        return symbols[index];
    },
    /**
     * 商品列表
     * @parse symbolId
     */
    "getSymbolList":function () {
        return symbols;
    },
    /**
     * 帐号信息
     */
    "getUserInfo":function () {
        return sdk.getUserInfo();
    },
    /**
     * 初始化资金帐号信息
     */
    "initAccountInfo":function () {
        accountInfo = sdk.getAccountById(this.tradeMarketName);
    },
    /**
     * 资金帐号信息
     */
    "getAccountInfo":function () {
        if(accountInfo.amWithdrawable<0){
            accountInfo.amWithdrawable=0;
        }
 		if(accountInfo.amMarginFree<0){
            accountInfo.amMarginFree=0;
        }
        return accountInfo;
    },
    /**
     * 更新资金帐号信息
     */
    "updateAccountInfo":function (mods) {
        for(var i=0; i<mods.length;i++){
            if(accountInfo.accId == mods[i].accId){
                accountInfo.amDailyCharge = mods[i].amDailyCharge;
                accountInfo.amMarginFreezed = mods[i].amMarginFreezed;
                accountInfo.amMarginRemain = mods[i].amMarginRemain;
                accountInfo.amMarginUsed = mods[i].amMarginUsed;
                accountInfo.amWithdrawable = mods[i].amWithdrawable;
            }
            var args={
                'onSuccess':function(mods){
                    for(var i=0;i<mods.length;i++){
                        if(mods[i].tradeMarketId==accountInfo.tradeMarketId){
                            accountInfo = mods[i];
                            if(accountInfo.amWithdrawable<0){
                                accountInfo.amWithdrawable = 0;
                            }
 							if(accountInfo.amMarginFree<0){
                                accountInfo.amMarginFree = 0;
                            }
                            $('.amMarginFree').html(commafy(parseFloat(accountInfo.amMarginFree).toFixed(2)));
                            $('.amMarginRemain').html(commafy(parseFloat(accountInfo.amMarginRemain).toFixed(2)));
                            $('.amWithdrawable').html(commafy(parseFloat(accountInfo.amWithdrawable).toFixed(2)));
                        }
                    }
                    updateCloseProfit();
                },
                'onFailure':function(code,message){
                    console.log(message);
                }
            }
            sdk.queryAccount(args);

        }
    },
    /**
     * 商品合约
     */
    "getSymbolContract":function (symbolId,callback) {
        var symbolContract = Array();
        sdk.queryContracts({
            'onSuccess':function(mods){
                for(var i=0;i<mods.length;i++){
                    if(mods[i].symbolId == symbolId && mods[i].contractStatus == 2){

                        var _unit = 1;
                        var  _unitStr='';
                        if (mods[i].periodUnit == 3) {
                            _unit = 3600;
                            _unitStr='h';
                        }
                        if (mods[i].periodUnit == 2) {
                            _unit = 60;
                            _unitStr='m';
                        }
                        if (mods[i].periodUnit == 1) {
                            _unit = 1;
                            _unitStr='s';
                        }
                        mods[i].time = _unit * mods[i].period;
                        mods[i].unitStr = _unitStr;
                        symbolContract.push(mods[i]);
                    }
                }
                symbolContract.sort(function(x, y){
                    return x.time>y.time;
                });
                callback(symbolContract);
            },
            'onFailure':function(code,message){
                callback(symbolContract);
            }
        });
    },
    /**
     * 初始化持仓
     */
    "initHoldBill":function () {
        sdk.queryBinHoldBill({
            onSuccess : function(mods) {
                holdBills = mods;
            },
            'onFailure':function(code,message){
            }
        }) ;
    },
    "getBills":function () {
        return holdBills;
    },
    "getHoldBills":function (symbleId) {
        var _temp = Array();
        for(var i=0; i<holdBills.length;i++){
            if(holdBills[i].orderStatus!="4"  && holdBills[i].orderStatus!="2"){
                _temp.push(holdBills[i]);
            }
        }
        return _temp;
        /*
        holdBills = _temp;
        if(symbleId == 0){
            return holdBills;
        }
        */

    },
    /**
     * 更新持仓
     */
    "updateHoldBills":function (callback) {
        sdk.queryBinHoldBill({
            onSuccess : function(mods) {
                holdBills = mods;
                callback();
            },
            'onFailure':function(code,message){
                callback();
            }
        }) ;
    },
    /**
     * 读取今日成功率
     */
    "getCloseProfitRate":function () {
        var winCount = 0;
        var count = 0;
var dailyCloseProfit = 0;
        for(var i=0;i<holdBills.length;i++){
            if (holdBills[i].orderStatus == "4" || holdBills[i].orderStatus == "2") {
                if(parseFloat(holdBills[i].profitClose)>0){
                    winCount = winCount + 1;
                }
                count++;
if (holdBills[i].profitClose > 0) {
					dailyCloseProfit = dailyCloseProfit + holdBills[i].profitClose*holdBills[i].profitRate;
				} else {
					dailyCloseProfit = dailyCloseProfit + holdBills[i].profitClose;
				}
            }
$('.amDailyCloseProfit').html(commafy(dailyCloseProfit.toFixed(2)));
        }
        if(winCount==0){
            return 0;
        }
        return winCount/count;
    },
    /**
     * 获取今日成功率
     */
    "getContractByID" : function(symbolCode,contractId){
        var contracts = sdk.getContract(symbolCode);
        for (var i = 0; i < contracts.length; i++) {
            if(contracts[i].contractId==contractId){
                return contracts[i];
            }
        }
        return null;
    }
}
Array.prototype.remove=function(dx)
{
    if(isNaN(dx)||dx>this.length){return false;}
    for(var i=0,n=0;i<this.length;i++)
    {
        if(this[i]!=this[dx])
        {
            this[n++]=this[i]
        }
    }
    this.length-=1
}
Date.prototype.format = function(format) {
    var date = {
        "M+": this.getMonth() + 1,
        "d+": this.getDate(),
        "h+": this.getHours(),
        "m+": this.getMinutes(),
        "s+": this.getSeconds(),
        "q+": Math.floor((this.getMonth() + 3) / 3),
        "S+": this.getMilliseconds()
    };
    if (/(y+)/i.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length));
    }
    for (var k in date) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1
                ? date[k] : ("00" + date[k]).substr(("" + date[k]).length));
        }
    }
    return format;
}
function clearNoNum(obj)
{
    obj.value = obj.value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符
    obj.value = obj.value.replace(/^\./g,"");  //验证第一个字符是数字而不是.
    obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的
    obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
    obj.value=obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3');//只能输入两个小数
}
function timestamptostr(timestamp) {
    var unixTimestamp = new Date(timestamp * 1000) ;
    // return unixTimestamp.toLocaleString();
    var d = new Date(timestamp * 1000);
    var jstimestamp = (d.getFullYear())+"-"+(d.getMonth()+1)+"-"+
        (d.getDate())+" "+(d.getHours()-8)+":"+(d.getMinutes())+":"
        +(d.getSeconds());
    return jstimestamp;
}
function getRealLen(str) {
    return str.replace(/[^\x00-\xff]/g, '__').length; //这个把所有双字节的都给匹配进去了
}
function checkName(str) {
    if (/^[\u4e00-\u9fa5]+$/.test(str)) {
        return true ;
    }
    else{
        return false ;
    }
}
function checkMobile(str) {
    var re = /^1\d{10}$/
    if (re.test(str)) {
        return true ;
    } else {
        return false;
    }
}
function  playMusic() {
    var music = document.getElementById("music");
    music.play();
}

var market = 'demo';
if(market=='demo'){
    LOGINTYPE = 1;
}






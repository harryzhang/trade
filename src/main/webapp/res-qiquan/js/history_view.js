/**
 * Created by lmd on 2015/11/26.
 */
var holdChartInterval;
var holdChartSymbol;
var HoldbillView = Backbone.View.extend({
    el: '#holdbill',
    template: '',
    tag: '',
	startTime:'',
    endTime:'',
    initialize: function () {
        var _self = this;
        $.MsgBox.Loading('提示', '加载中...');
        $.get(assets_path + 'page/trade/holdbill.html', function (result) {
            $.MsgBox.Clean();
            $.afui.hideMask();
            _self.template = _.template(result);
            _self.render();
        });
    },
    render: function () {
        var _self = this;
        if ($(this.el).html() == '') {
            this.$el.html(this.template({
                'assets_path': assets_path
            }));
        }
		sdk.on(M_R_PUSH_QUOTE, this.onQuoted, sdk);
        if (window.tag == 'holdbill') {
            $('.history-top li').removeClass('current');
            $('.history-top li').eq(0).addClass('current');
            $('.hold-list-content').show();
            $('.history-list-content').hide();
			$('.selTime').hide();
            this.holdbillShow();
        }
        if (window.tag == 'history') {
            $('.history-top li').removeClass('current');
            $('.history-top li').eq(1).addClass('current');
            $('.hold-list-content').hide();
            $('.history-list-content').show();
			_self.startTime = "";
            _self.endTime = "";
            _self.action = "";
            $('.selTime').show();
            this.historyShow();
        }
        $('.history-top li').unbind('click');
        $('.history-top li').eq(0).bind('click', function () {
            $('.history-top li').removeClass('current');
            $(this).addClass('current');
            $('.hold-list-content').show();
            $('.history-list-content').hide();
			$('.selTime').hide();
            _self.holdbillShow();
        })
        $('.history-top li').eq(1).bind('click', function () {
            $('.history-top li').removeClass('current');
            $(this).addClass('current');
            $('.hold-list-content').hide();
            $('.history-list-content').show();
			$('.selTime').show();
            $('input[name=date_start]').val("");
            _self.startTime = "";
            _self.endTime = "";
            _self.action = '';
            _self.historyShow();
        })
        $('.main-menu').width($('#holdbill').width());
		var currYear = (new Date()).getFullYear();
        var opt={};
        opt.date = {preset : 'date'};
        opt.datetime = {preset : 'datetime'};
        opt.time = {preset : 'time'};
        opt.default = {
            theme: 'android-ics light', //皮肤样式
            display: 'modal', //显示方式
            mode: 'scroller', //日期选择模式
            dateFormat: 'yyyy-mm-dd',
            lang: 'zh',
            showNow: true,
            nowText: "今天",
            startYear: currYear - 2, //开始年份
            endYear: currYear + 1 //结束年份
        };
        $("#date_start").mobiscroll($.extend(opt['date'], opt['default']));

        $('.search-btn').unbind('click');
        $('.search-btn').bind('click',function(){
            _self.startTime = $("#date_start").val()+" 00:00:00";
            _self.endTime = $("#date_start").val()+" 23:59:59";
            if(_self.startTime=="" ){
                $.MsgBox.Alert('提示','请选择日期！');
                return;
            }
            _self.action = 'history';
            _self.historyShow();
        })
    },
    holdbillShow: function () {
        if($('.hold-list-content').html().length==0){
            $.MsgBox.Loading('提示', '加载中...');
        }
        app.updateHoldBills(function () {
            $.MsgBox.Clean();
            var _holdList = app.getHoldBills(0);
            for (var i = 0; i < _holdList.length; i++) {
                var symbolInfo = app.getSymbol(_holdList[i].symbolId)
                if (symbolInfo) {
                    _holdList[i].symbolName = symbolInfo.symbolName;
                    _holdList[i].symbolCode = symbolInfo.symbolCode;
                    _holdList[i].bsCodeStr = ((_holdList[i].bsCode === 'b') ? '买涨' : '买跌');
                    _holdList[i].bsCodeClass = ((_holdList[i].bsCode === 'b') ? 'red-bg' : 'green-bg');
                    _holdList[i].profitRate = (_holdList[i].profitRate * 100).toFixed(0);
                    _holdList[i].decimal = symbolInfo.decimal;
                    _holdList[i].priceCurrent = symbolInfo.priceCurrent;
                    var _profit = 0;
                    if (_holdList[i].bsCode == "b") {
                        if (symbolInfo.priceCurrent > _holdList[i].priceOrder) {
                            _profit = _holdList[i].profitRate * _holdList[i].amount / 100;
                        }
                        if (symbolInfo.priceCurrent < _holdList[i].priceOrder) {
                            _profit = -_holdList[i].amount;
                        }
                        _holdList[i].lastTimeClass = 'red';
                        _holdList[i].pricePoint = _holdList[i].pointTakeProfit;
                    }
                    if (_holdList[i].bsCode == "s") {
                        if (symbolInfo.priceCurrent < _holdList[i].priceOrder) {
                            _profit = _holdList[i].profitRate * _holdList[i].amount / 100;
                        }
                        if (symbolInfo.priceCurrent > _holdList[i].priceOrder) {
                            _profit = -_holdList[i].amount;
                        }
                        _holdList[i].lastTimeClass = 'green';
                        _holdList[i].pricePoint = _holdList[i].pointStopLose;
                    }
                    if (_profit > 0) {
                        _holdList[i].profitColor = "red";
                    }
                    if (_profit < 0) {
                        _holdList[i].profitColor = "green";
                    }
                    _holdList[i].profit = _profit;

                    var _orderNum = (parseFloat(_holdList[i].amount) / parseFloat(_holdList[i].priceOpen)).toString();
                    _holdList[i].orderNum = _orderNum.substr(0, _orderNum.lastIndexOf('.') + 3);

                    _holdList[i].unit = getUnit(_holdList[i].symbolCode);
					var _orderNum=(parseFloat(_holdList[i].amount)/parseFloat(_holdList[i].priceOpen)).toString();
                    _holdList[i].orderNum=_orderNum.substr(0,_orderNum.lastIndexOf('.')+3);

                    _holdList[i].unit=getUnit(_holdList[i].symbolCode);

                    _holdList[i].winPrice =  parseFloat(_holdList[i].priceTakeProfit).toFixed(symbolInfo.decimal);
                    _holdList[i].losePrice = parseFloat(_holdList[i].priceStopLose).toFixed(symbolInfo.decimal);

                    var _temp1=_holdList[i].openTime.split('T');
                    _holdList[i].openTime=_temp1[0].substring (0,4)+"-"+_temp1[0].substring  (4,6)+"-"+_temp1[0].substring  (6,8)+" "+_temp1[1].substring (0,2)+":"+_temp1[1].substring  (2,4)+":"+_temp1[1].substring  (4,6);

                    var symbolDate =  new Date(symbolInfo.date.replace(/-/g, '/'));
                    var symbolTimeStamp = Date.parse(symbolDate)/1000;
                    var billDate = new Date(_holdList[i].openTime.replace(/-/g, '/'));
                    var billTimeStamp =  Date.parse(billDate)/1000;
                    if(billTimeStamp>symbolTimeStamp+45){
                        _holdList[i].priceCurrent = "--";
                    }
                }
            }
            window.newholdBills = _holdList;
            var _template = _.template($('#holdListTemplate').html());
            $("#holdbill .hold-list-content").html(_template({list: _holdList}));

            $('.hold-list-content li').unbind('click');
            $('.hold-list-content li').each(function (index) {
                $(this).bind('click', function () {
                    var symbolId = $(this).attr('symbolId');
                    var period = $(this).attr('period');
                    var periodUnit = $(this).attr('periodUnit');
                    var orderSymbol = app.getSymbol(symbolId);
                    var unitStr = '';
                    if (periodUnit == 3) {
                        unitStr = 'h';
                    }
                    if (periodUnit == 2) {
                        unitStr = 'm';
                    }
                    if (periodUnit == 1) {
                        unitStr = 's';
                    }
                    var periodStr = period + unitStr;

                    var _orderData = {
                        'symbolName': $(this).attr('symbolName'),
                        'symbolCode': $(this).attr('symbolCode'),
                        'symbolId': $(this).attr('symbolId'),
                        'orderCode': $(this).attr('orderCode'),
                        'bsCodeStr': $(this).attr('bsCodeStr'),
                        'bsCodeClass': $(this).attr('bsCodeClass'),
                        'bsCode': $(this).attr('bsCode'),
                        'amount': $(this).attr('amount'),
                        'profitRate': $(this).attr('profitRate'),
                        'priceOpen': $(this).attr('priceOpen'),
                        'priceCurrent': orderSymbol.priceCurrent,
                        'periodStr': periodStr,
                        'pricePoint': $(this).attr('pricePoint'),
                        'orderNum': $(this).attr('orderNum'),
                        'unit': $(this).attr('unit'),
                        'winPrice': $(this).attr('winPrice'),
                        'losePrice': $(this).attr('losePrice'),
                        'openTime': $(this).attr('openTime'),
                        'decimal': $(this).attr('decimal')
                    };


                    var _profit = parseFloat($(this).attr('profitClose'));
                    if (_profit > 0) {
                        _orderData.profitClass = 'red';
                    }
                    if (_profit < 0) {
                        _orderData.profitClass = 'green';
                    }
                    var historyTemplate = _.template($('#holdTemplate').html());
                    var _html = historyTemplate({
                        'item': _orderData
                    });
                    $.MsgBox.OrderCloseConfirm('订单详情', _html, function () {
                    })

                    $('#tline-iframe').attr("src", "?r=site/tchart&period=4&symbolcode=" + _orderData.symbolCode + "&barcount=40&decimal=" + _orderData.decimal + "&market=real&charttype=t&stopLosePrice=" + _orderData.losePrice + "&stopTakePrice=" + _orderData.winPrice + "&bsCode=" + _orderData.bsCode);
                    clearInterval(holdChartInterval);
                    holdChartSymbol = app.getSymbol(_orderData.symbolId);
                    holdChartInterval = setInterval(function () {
                        if (window.tlineIframe) {
                            window.tlineIframe.postMessage(holdChartSymbol, 'http://wechat.luhongsuo.com');
                        }
                    }, 1000);

                    $('#show-tline').bind("click", function () {
                        clearInterval(holdChartInterval);
                        holdChartSymbol = app.getSymbol(_orderData.symbolId);
                        holdChartInterval = setInterval(function () {
                            if (window.tlineIframe) {
                                window.tlineIframe.postMessage(holdChartSymbol, 'http://wechat.luhongsuo.com');
                            }
                        }, 1000);

                        $('#tline-info-box').show();
                        $('#hold-info-box').hide();
                        $('#tline-iframe').attr("src", "?r=site/tchart&period=4&symbolcode=" + _orderData.symbolCode + "&barcount=40&decimal=" + _orderData.decimal + "&market=real&charttype=t&stopLosePrice=" + _orderData.losePrice + "&stopTakePrice=" + _orderData.winPrice + "&bsCode=" + _orderData.bsCode);
                    })

                    $('#show-order-info').bind("click", function () {
                        $('#tline-info-box').hide();
                        $('#hold-info-box').show();
                    })
                })
            })
        });
        
    },
    historyShow: function () {
        var _acc = app.getAccountInfo();
        var _csrfToken = $('meta[name="csrf-token"]').attr("content");
        var _historyData = {
			'action':this.action,
            'acc_id': _acc.accId,
            'startDate':this.startTime,
            'endDate':this.endTime,
            '_csrf': _csrfToken,
            'market': market
        };
        $.ajax({
            url: api_path + "?r=server%2Fhistory",
            data: _historyData,
            type: "POST",
            beforeSend: function () {
                 if($('.history-list-content').html().length==0){
                    $.MsgBox.Loading('提示', '加载中...');
                }
            },
            success: function (mods) {
                $.MsgBox.Clean();
                _historylog = eval('(' + mods + ')');

                var _todayProfit = 0;
                var _todayCount = 0;


                for (var i = 0; i < _historylog.length; i++) {
                    var symbolInfo = app.getSymbol(_historylog[i].symbolId)
                    _historylog[i].symbolName = symbolInfo.symbolName;
                    _historylog[i].symbolCode = symbolInfo.symbolCode;
                    _historylog[i].bsCodeStr = ((_historylog[i].bsCode === 'b') ? '买涨' : '买跌');
                    _historylog[i].bsCodeClass = ((_historylog[i].bsCode === 'b') ? 'red-bg buy-icon' : 'green-bg sell-icon');
                    _historylog[i].unit = getUnit(symbolInfo.symbolCode);

                    _historylog[i].decimal = symbolInfo.decimal;

                    if (_historylog[i].profitClose > 0) {
                        _historylog[i].profitClass = "red";
                        _historylog[i].closeType = '止盈';
                        _historylog[i].profitClose = parseFloat(_historylog[i].profitClose) * _historylog[i].profitRate;
                    }
                    if (_historylog[i].profitClose < 0) {
                        _historylog[i].profitClass = "green";
                        _historylog[i].closeType = '止损';
                    }
                    if (_historylog[i].profitClose == 0) {
                        _historylog[i].profitClass = "";
                        _historylog[i].closeType = '持平';
                    }

                    _historylog[i].orderquantity = commafy(_historylog[i].orderquantity, 0);

                    var _orderNum = (parseFloat(_historylog[i].orderQuantity) / parseFloat(_historylog[i].holdPrice) * 100).toString();
                    _historylog[i].orderNum = _orderNum.substr(0, _orderNum.lastIndexOf('.') + 3);

                    _historylog[i].pricePoints = parseInt(_historylog[i].pricePoints).toFixed(0);
                    _historylog[i].orderTimeStamp = Date.parse(new Date(_historylog[i].orderTime));

                    var _orderTimeDate = new Date(Date.parse(_historylog[i].orderTime.replace(/-/g, "/")));
                    var _dealTimeDate = new Date(Date.parse(_historylog[i].dealTime.replace(/-/g, "/")));

                    _historylog[i].orderTime =  _orderTimeDate.format('yyyy-MM-dd hh:mm:ss');
                    _historylog[i].dealTime =  _dealTimeDate.format('yyyy-MM-dd hh:mm:ss');

                    var _orderTimeDate = new Date(Date.parse(_historylog[i].orderTime.replace(/-/g, "/")));
                    _historylog[i].key = _orderTimeDate.getFullYear() + "-" + (parseInt(_orderTimeDate.getMonth()) > 8 ? (_orderTimeDate.getMonth() + 1) : '0' + (_orderTimeDate.getMonth() + 1)) + "-" + (parseInt(_orderTimeDate.getDate()) > 9 ? _orderTimeDate.getDate() : '0' + _orderTimeDate.getDate());

                    _historylog[i].holdPrice = parseFloat(_historylog[i].holdPrice).toFixed(symbolInfo.decimal);
                    _historylog[i].closedQuantity = parseFloat(_historylog[i].closedQuantity).toFixed(symbolInfo.decimal);
                }

                var _list = [];
                if (_historylog.length == 0) {
                    $(".history-list-content").html("<div class=\"no-recorder\">暂无记录</div>");
                } else {
                    for (var i = 0; i < _historylog.length; i++) {
                        if (!_list[_historylog[i].key]) {
                            _list[_historylog[i].key] = [];
                        }
                        _list[_historylog[i].key].push(_historylog[i]);
                    }
                    var _html = "";
                    for (var key in _list) {
                        console.log(typeof(_list[key]));
                        if (typeof(_list[key]) != "function") {
                            _html += " <p class=\"pro-date\" ><span>" + key + "</span></p>";
                            for (var k = 0; k < _list[key].length; k++) {
                                _html += "<dl class=\"pro-list\">";

                                var historyTemplate = _.template($('#historyListTemplate').html());
                                _html += historyTemplate({
                                    'item': _list[key][k]
                                });
                            }
                        }
                    }
                    $(".history-list-content").html(_html);
                    $('.history-list-content li').unbind('click');
                    $('.history-list-content li').each(function (index) {
                        $(this).bind('click', function () {
                            var _orderData = {
                                'symbolName': $(this).attr('symbolName'),
                                'orderCode': $(this).attr('orderCode'),
                                'orderQuantity': $(this).attr('orderquantity'),
                                'closedQuantity': $(this).attr('closedQuantity'),
                                'holdPrice': $(this).attr('holdPrice'),
                                'orderTime': $(this).attr('orderTime'),
                                'dealTime': $(this).attr('dealTime'),
                                'profitClose': $(this).attr('profitClose'),
                                'bsCode': $(this).attr('bsCode'),
                                'bsCodeStr': $(this).attr('bsCodeStr'),
                                'bsCodeClass': $(this).attr('bsCodeClass'),
                                'profitClass': '',
                                'orderNum': $(this).attr('orderNum'),
                                'closeType': $(this).attr('closeType'),
                                'pricePoints': $(this).attr('pricePoints'),
                                'unit': $(this).attr('unit'),
                            };
                            var _profit = parseFloat($(this).attr('profitClose'));
                            if (_profit > 0) {
                                _orderData.profitClass = 'red';
                            }
                            if (_profit < 0) {
                                _orderData.profitClass = 'green';
                            }
                            var historyTemplate = _.template($('#historyTemplate').html());
                            var _html = historyTemplate({
                                'item': _orderData
                            });
                            $.MsgBox.OrderCloseConfirm('订单详情', _html, function () {
                            })
                        })
                    })
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                $.MsgBox.Tip('error', '查询超时！');
            }
        })
    },
    more: function () {
        var _acc = sdk.getAccount(-1);
        var _csrfToken = $('meta[name="csrf-token"]').attr("content");
        var _historyData = {
            'action': 'history',
            'acc_id': _acc.accId,
            'startDate': window.historyView.startTime,
            'endDate': window.historyView.endTime,
            '_csrf': _csrfToken,
        };
        $.ajax({
            url: api_path + "?r=server%2Fhistory",
            data: _historyData,
            type: "POST",
            beforeSend: function () {
                $.afui.showMask('加载中...');
            },
            success: function (mods) {
                $.afui.hideMask();
                _historylog = eval('(' + mods + ')');

                var _todayProfit = 0;
                var _todayCount = 0;
                for (var i = 0; i < _historylog.length; i++) {
                    if (_historylog[i].closedQuantity > 0) {
                        _todayCount++;
                        _todayProfit += parseFloat(_historylog[i].profitClose);
                    }

                    _historylog[i].orderQuantity = commafy(_historylog[i].orderQuantity, 2);
                    _historylog[i].profitClose = commafy(_historylog[i].profitClose, 2);
                    _historylog[i].holdPrice = commafy(_historylog[i].holdPrice, 2);
                    _historylog[i].closedQuantity = commafy(_historylog[i].closedQuantity, 2);
                    _historylog[i].bsCodeStr = ((_historylog[i].bsCode === 'b') ? '买涨' : '买跌');
                    _historylog[i].bsCodeClass = ((_historylog[i].bsCode === 'b') ? 'red-bg buy-icon' : 'green-bg sell-icon');
                    _historylog[i].orderNum = (parseFloat(_historylog[i].orderQuantity) / parseFloat(_historylog[i].holdPrice) * 100).toString();

                    if (_historylog[i].profitClose > 0) {
                        _historylog[i].profitClass = "red";
                    }
                    if (_historylog[i].profitClose < 0) {
                        _historylog[i].profitClass = "green";
                    }
                    if (_historylog[i].profitClose == 0) {
                        _historylog[i].profitClass = "";
                    }
                }
                var _template = _.template($('#historyListTemplate').html());
                $('#more_history_btn').remove();
                $(".history-list-content").append(_template({datas: _historylog}));
                if (_historylog.length == 0) {
                    $(".history-list-content").html('<div id="more_history_btn" >没有最近7天交易记录</div>');
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                $.MsgBox.Tip('error', '查询超时！');
            }
        })
    },
    onQuoted: function (mods) {
        var symbol = mods[0];
        if (symbol) {
            if ($(".hold" + symbol.symbolCode + ' .priceCurrent').length > 0) {
                $(".hold" + symbol.symbolCode + ' .priceCurrent').each(function () {
                    $(this).html(commafy(symbol.priceCurrent, symbol.decimal));
                })
            }
            if (holdChartSymbol) {
                if (holdChartSymbol.symbolCode == symbol.symbolCode) {
                    holdChartSymbol = symbol;
                }
            }
        }
    },
    destroy: function () {
        if (sdk) {
            sdk.un(M_R_PUSH_QUOTE, this.onQuoted, sdk);
        }
        clearInterval(holdChartInterval);
    }
});

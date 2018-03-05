(function() {
    $.MsgBox = {
        Tip: function(title, msg) {
            GenerateHtml("tip", title, msg, null);
            btnOk(); //alert只是弹出消息，因此没必要用到回调函数callback
            btnNo();
        },

        Tip: function(title, msg, callback) {
            GenerateHtml("tip", title, msg, callback);
            btnOk(callback); //alert只是弹出消息，因此没必要用到回调函数callback
            btnNo();
        },

        Alert: function(title, msg) {
            GenerateHtml("alert", title, msg, null);
            btnOk();
            //btnNo();
        },
        AlertB: function(title, msg,callback) {
            GenerateHtml("alert", title, msg, null);
            btnOk(callback);
            //btnNo();
        },

        Confirm: function(title, msg, callback) {
            GenerateHtml("confirm", title, msg, null);
            btnOk(callback);
            btnNo();

        },

        OrderConfirm: function(title, msg, callback) {
            GenerateHtml("order-confirm", title, msg, null);
            btnOk(callback);
            btnNo();
        },

        OrderCloseConfirm: function(title, msg, callback) {
            GenerateHtml("order-close-confirm", title, msg, null);
            btnOk(callback);
            btnNo();
        },
        ConfirmT: function(title, msg, callback1,callback2) {
            GenerateHtml("confirm", title, msg, null);
            btnOk(callback1);
            btnNoT(callback2);
        },
        InfoT: function(title, msg, callback1,callback2) {
            GenerateHtml("info", title, msg, null);
            btnOk(callback1);
            btnNoT(callback2);
        },
		Loading: function(title, msg, callback) {
            GenerateHtml("Loading", title, msg, null);
            btnOk();
            //btnNo();
        },
		Call: function(title, msg, callback) {
            GenerateHtml("call", title, msg, null);
            btnOk();
			btnNo();
        },
		Photo:function(title, msg, callback){
			GenerateHtml("photo", title, msg, null);
            btnNo();
		},
        Bulletin:function(title, msg, callback){
            GenerateHtml("bulletin", title, msg, null);
            btnNo();
        },
        Xieyi:function (title,msg,callBack) {
            GenerateHtml("xieyi", title, msg, null);
            btnOk(callBack);
        },
        Guide:function (title,msg,callBack) {
            GenerateHtml("guide", title, msg, null);
            btnOk(callBack);
        },
		Clean:function(){
			 $("#mb_box,#mb_con,#mb_bg,#cover").remove();
             $('.pages').removeClass('filter');
		}
    }

    //生成Html

    var GenerateHtml = function(type, title, msg, callback) {
        $("#mb_box,#mb_con,#mb_bg,#cover").remove();
        var _html = "";
		if(type == "Loading"){
            _html += '<div class="loading" id="mb_box">';
            _html += '<div class="loading-box"> <span class="loading-round">';
            _html += '<div class="loader">';
            _html += '<div class="loader-inner ball-beat">';
            _html += '<div></div>';
            _html += '<div></div>';
            _html += '<div></div>';
            _html += '<div></div>';
            _html += '<div></div>';
            _html += '</div>';
            _html += '</div>';
            _html += '</span> <span class="loading-wen">加载中...</span> </div>';
            _html += '</div>';
            _html += '<div id="cover" class="transition" style="display: block;"></div>';
		}

        if (type == "confirm") {
            _html += '<div class="open-box" id="mb_box">';
            _html += '<span id="errormsg">' + msg + '</span>';
            _html += '<div class="confirm-btn">';
            _html += '<a class="open-btn" style="width:50%;margin:0;border-radius:0 0 0 8px;" href="javascript:void(0)">确定</a>';
            _html += '<a class="close-btn" style="width:50%;margin:0;border-radius:0 0 8px 0;" href="javascript:void(0)">取消</a>';
            _html += '</div>';
            _html += '</div>';
            _html += '<div id="cover" class="transition" style="display: block;"></div>';
        }

        if (type == "info") {
            _html += '<div class="open-box" id="mb_box">';
            _html += '<span id="errormsg">' + msg + '</span>';
            _html += '<div class="confirm-btn">';
            _html += '<a class="open-btn" style="width:50%;margin:0;border-radius:0 0 0 8px;" href="javascript:void(0)">立即上传</a>';
            _html += '<a class="close-btn" style="width:50%;margin:0;border-radius:0 0 8px 0;" href="javascript:void(0)">暂不上传</a>';
            _html += '</div>';
            _html += '</div>';
            _html += '<div id="cover" class="transition" style="display: block;"></div>';
        }

        if (type == "call") {
            _html += '<div id="mb_bg" style=" background:#000; position:fixed; z-index:10000; height:100%; width:100%; top:0; left:0; opacity:0.5;"></div>';
			if($(window).width()<350){
				_html += '<div id="mb_box" class="alertwin ajaxwin" style="background:#fff; position:fixed; top:50%;left:50%; width:90%; height:auto; z-index:10002;">';
			}else{
				_html += '<div id="mb_box" class="alertwin ajaxwin" style="background:#fff; position:fixed; top:50%;left:50%; width:90%; height:auto; z-index:10002;">';
			}
            _html += '<div style="float: right; font-size:2em; margin:0 2px; width:30px; height:30px; line-height:2em; text-align:center"  class="close click-ccc-aaa"><a id="mb_ico"><i class="fa fa-times"></i></a></div>';
			_html += '<div style="margin:0 1em 0 1em; text-align:left; font-size:2em;line-height:2em;border-bottom:1px solid #000;text-align:center" >' + title + '</div>';
            _html += '<div style="margin:1em 0.5em 1em 0.5em; text-align:left; font-size:1.3em" >' + msg + '</div>';
            _html += '<div class="buttondiv" style="margin:1em auto 0 auto; height:4em; text-align:center">';
            _html += '<button type="button" id="mb_btn_ok" style=" padding:0.5em;margin-left:auto;margin-right:auto;font-size:1.5em;background:#498DDE; color:#fff;text-align:center;-moz-border-radius: 0.5em;-webkit-border-radius: 0.5em;border-radius:6px;border:none;width:90%;" class="ok">呼叫</button>';
            _html += '</div>';
            _html += '</div>';

        }

        if (type == "alert") {
            _html += '<div class="open-box" id="mb_box">';
            _html += '<span id="errormsg" >' + msg + '</span>';
            _html += '<a class="open-btn" style="width:92%" href="javascript:void(0)">确定</a>';
            _html += '</div>';
            _html += '<div id="cover" class="transition" style="display: block;" onclick=" $.MsgBox.Clean()"></div>';
        }

        if(type == "bulletin"){
            _html += '<div class="open-box" id="mb_box">';
            _html += '<span id="errortitle" style="text-align: center;width: 100% !important; margin-left: 0%; font-size: 1em; " >' + title + '</span>';
            _html += '<span id="errormsg" style="text-align: left;width: 95% !important; margin-left: 5%; max-height: 160px; overflow:scroll;" >' + msg + '</span>';
            _html += '<a class="notice-close-btn" style="width:100%"  onclick=" $.MsgBox.Clean()">忽略</a>';
            _html += '</div>';
            _html += '<div id="cover" class="transition" style="display: block;" onclick=" $.MsgBox.Clean()"></div>';
        }

        if (type == "xieyi") {
            _html += '<div class="open-box" id="mb_box">';
            _html += '<span id="errortitle" style="text-align: center;width: 100% !important; margin-left: 0%; font-size: 1em; " >' + title + '</span>';
            _html += '<span id="errormsg" style="text-align: left;width: 94% !important; margin-left: 3%; max-height: 160px; overflow:scroll;" >' + msg + '</span>';
            _html += '<a class="open-btn" style="width:94%; " href="javascript:void(0)">我已经阅读《'+title+'》并同意遵守</a>';
            _html += '</div>';
            _html += '<div id="cover" class="transition" style="display: block;" ></div>';
        }

        if (type == "guide") {
            _html += '<div id="guide_mb_box"  style="z-index:2000; position: fixed; width: 100%; height: auto;" onclick=" $.MsgBox.Clean()">'+msg+'</div>';
        }

        if(type == "order-close-confirm"){
            _html += '<div class="open-box" id="mb_box">';
            _html += '<span id="errormsg" style="width: 100%; margin-left: 0px; margin-top: 0px;">' + msg + '<div class="close-btn"  style="width: 20px; height: 20px; position: absolute; top:10px; right: 10px; background: url(assets/default/css/images/close.png) center center no-repeat;background-size: 100% auto; margin: 0px;"></div></span>';
            //_html += '<div class="confirm-btn" style="border-top:none; margin-top: 0px; height: 45px;">';
            //_html += '<a class="open-btn" style="width:100%;border-radius:0px 0px 8px 8px; background:#3f3f3f; color: #bfbfbf; margin: 0% 0 0% 0%; border-top:2px solid #4d4d4d" href="javascript:void(0)">关闭</a>';
            _html += '</div>';
            _html += '</div>';
            _html += '<div id="cover" class="transition" style="display: block;" onclick=" $.MsgBox.Clean()"></div>';
        }

        if(type == "order-confirm"){
            _html += '<div class="open-box" id="mb_box">';
            _html += '<span id="errortitle" style="text-align: center;width: 95% !important; margin-left: 5%; font-size: 1.2em; " >' + title + '<div class="close-btn"  style="width: 20px; height: 20px; position: absolute; top:10px; right: 10px; background: url(assets/default/css/images/close.png) center center no-repeat;background-size: 100% auto; margin: 0px;"></div></span>';
            _html += '<span id="errormsg" style="width: 100%; margin-left: 0px;">' + msg + '</span>';
            _html += '<div class="confirm-btn" style="border-top:none; margin-top: 0px; height: 65px;">';
            _html += '<a class="open-btn subimt-button" style="width:90%;border-radius:8px; margin: 5% 0 5% 5%;" href="javascript:void(0)">下单</a>';
            _html += '</div>';
            _html += '</div>';
            _html += '<div id="cover" class="transition" style="display: block;" onclick=" $.MsgBox.Clean()"></div>';
        }

        if (type == "tip") {
            _html += '<div class="open-box" id="mb_box">';
            _html += '<span id="errormsg" style="margin-bottom:15px; ">' + msg + '</span>';
            _html += '</div>';
            _html += '<div id="cover" class="transition" style="display: block;"></div>';
        }

		if (type == "photo") {
            _html += '<div id="mb_box" class="alertwin ajaxwin" style="background:#fff; position:fixed; top:100%;left:50%; width:90%; height:auto; z-index:100001;border-radius:1em;">';
            _html += '<div style="margin:0; text-align:center; font-size:1.7em" >' + msg + '</div>';
			_html += '<button type="button" id="mb_btn_no" style=" width:100%;height:50px;font-size:1.5em;line-height:50px; float:left; text-align:center; background:#fff;border:none; border-top:1px solid #CCC;border-radius:0em 0em 1em 1em;" >取消</button>';
            _html += '</div>';
            _html += '<div id="mb_bg" style=" background:#000; position:fixed; z-index:10001; height:100%; width:100%; top:0; left:0; opacity:0.5;"></div>';
        }
        //必须先将_html添加到body
        $("body").append(_html);
        //设置Css样式
        GenerateCss(type);

        if (type == "tip") {
            setTimeout(function() {
                $("#mb_box,#cover").remove();
                $(".pages").removeClass("filter");
                if (typeof(callback) == 'function') {
                    setTimeout(callback, 1);
                }
            },1000);
        }

        if(type =="xieyi" || type == "bulletin"){
            if( typeof IScroll == 'function'){
                var xieYiScroll = new IScroll('#errormsg', {mouseWheel: true});
            }
        }
    }
   

    //生成Css

    var GenerateCss = function(type) {
        //$('.pages').removeClass('filter').addClass('filter');
        $("#mb_box").removeClass('fadeIn').addClass('fadeIn');
        //$("#mb_box").css({'opacity':1}).show();
    }

    //确定按钮事件
    var btnOk = function(callback) {
         $(".open-btn,#guide_mb_box").click(function() {
            $("#mb_box,#cover,#open-box,#guide_mb_box").remove();
            $(".pages").removeClass("filter");
            if (typeof(callback) == 'function') {
                callback();
            }
        });
    }

    //取消按钮事件
    var btnNo = function() {
         $(".close-btn").click(function() {
            $("#mb_box,#cover").remove();
            $(".pages").removeClass("filter");
            if (typeof(callback) == 'function') {
                callback();
            }
        });
        $("#mb_btn_no,#mb_ico,#mb_bg,#guide_mb_box").click(function() {
            $("#mb_box,#mb_con,#mb_bg,#guide_mb_box").remove();
        });
    }

    //取消按钮事件
    var btnNoT = function(callback) {
        $("#mb_btn_no,#mb_ico,#mb_bg,#guide_mb_box,.close-btn").click(function() {
            $("#mb_box,#cover").remove();
            $("#mb_box,#mb_con,#mb_bg,#guide_mb_box").remove();
        });
        if (typeof(callback) == 'function') {
            callback();
        }
    }

})(); 

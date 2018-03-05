var InfoView=Backbone.View.extend({
	el : '#info',
    template : '',
    events: {},
    initialize:function(){
        var _self=this;
        $.MsgBox.Loading('提示','加载中...');
        $.get('?r=site/info', function(result){
            $.MsgBox.Clean();
            _self.template=_.template(result);
            _self.render();
        });

    },
    render:function() {
        var _self=this;
    	if($(this.el).html()==''){
            this.$el.html(this.template({
                'assets_path':assets_path,
                'info':sdk.getUserInfo()
            }));
        }

        $(".box .acc input").focus(function () {
            $(this).parent().find(".acc-wen").hide();
        });

        $(".box .acc input").blur(function () {
            if (this.value == "") {
                $(this).parent().find(".acc-wen").show();
            }
        });

        $('#update-info-btn').unbind('click');
        $('#update-info-btn').bind('click',function(){
            _self.updateInfo();
        })

        if(!isWeiXin()){
            var options = {
                success: function(data) {
                    mod = eval("(" + data + ")");
                    if(parseInt(mod.retCode)==0){
                        $.MsgBox.AlertB('提示','修改成功',function () {
                            $.afui.goBack();
                        });
                    }else if(parseInt(mod.retCode) == 13){
                        $.MsgBox.Alert('提示',"用户已存在");
                    }else if(parseInt(mod.retCode) == 40){
                        $.MsgBox.Alert('提示',"登录授权超时，请重新登录");
                    }else{
                        $.MsgBox.Alert('提示',mod.retText);
                    }
                    $('#update-info-btn').removeAttr('disabled');
                }
            };
            $('#updateForm').ajaxForm(options);
        }else{
            $('#info input[type=file]').remove();
            $('#photo_upload_1').unbind('click');
            $('#photo_upload_1').bind('click',function(){
                takePhoto('1',function(res,images){
                    uploadPhoto(res.localIds[0],function(serverId){
                        $('#photo_upload_1').find('i').removeClass('fa-camera').addClass('fa-check-square-o');
                        $('input[name=image1]').val(serverId);
                        $('#photo_upload_1 .acc-wen').html('已选择');

                    })
                }) ;
            })
            $('#photo_upload_2').unbind('click');
            $('#photo_upload_2').bind('click',function(){
                takePhoto('2',function(res,images){
                    uploadPhoto(res.localIds[0],function(serverId){
                        $('#photo_upload_2').find('i').removeClass('fa-camera').addClass('fa-check-square-o');
                        $('input[name=image2]').val(serverId);
                        $('#photo_upload_2 .acc-wen').html('已选择');
                    })
                }) ;
            })
            $('#photo_upload_3').unbind('click');
            $('#photo_upload_3').bind('click',function(){
                takePhoto('3',function(res,images){
                    uploadPhoto(res.localIds[0],function(serverId){
                        $('#photo_upload_3').find('i').removeClass('fa-camera').addClass('fa-check-square-o');
                        $('input[name=image3]').val(serverId);
                        $('#photo_upload_3 .acc-wen').html('已选择');
                    })
                }) ;
            })
        }
        $('input[type=password]').val('');

        $('#btnSendCode').bind('click',function(){
            _self.doSendCode();
        });
    },
    doSendCode:function(){
        var $obj=$('.mes');
        var SMS = {
            node:null,
            count:180,
            start:function(){
                //console.log(this.count);
                if(this.count > 0){
                    this.node.html(this.count--);
                    var _this = this;
                    setTimeout(function(){
                        _this.start();
                    },1000);
                }else{
                    this.node.attr("send",0);
                    this.node.html('重发');
                    this.count = 180;
                }
            },
            //初始化
            init:function(node){
                this.node = node;
                this.node.attr("send",1);
                this.start();
            }
        };
        if($obj.attr('send')=='1'){
            return ;
        }
        //验证
        var mobile = $('#txtTel').val() ;

        if (checkMobile(mobile) &&  mobile.length==11) {
            var csrfToken = $('meta[name="csrf-token"]').attr("content");
            $.ajax({
                url:'?r=server/verfiy',
                data:{'phone':encrypt.encrypt(mobile),'market':market,'_csrf':csrfToken},
                type:"POST",
                dataType:"json",
                timeout : 10000,
                beforeSend:function(){},
                success:function(mod){
                    console.log(mod);
                    $.MsgBox.Tip('success',"验证码已经发出");
                    SMS.init($obj);
                }
            });
        }else{
            $.MsgBox.Alert('提示',"请输入手机号");
            return false;
        }
    },
    updateInfo:function () {
        var userName = $('#txtUsername').val();
        var idCard = $('#txtIdcard').val();
        var phone = $('#txtTel').val();
        //var password = $('#txtPassword').val();

        if(userName.length==0){
            $.MsgBox.Alert('提示','请输入姓名！');
            return false;
        }
        var userNameLength = getRealLen(userName);
		userName=userName.trim(); 
        if(!checkName(userName) || userNameLength<4 || userNameLength >12){
            $.MsgBox.Alert('提示','请输入合法姓名！');
            return false;
        }
        if(idCard.length==0){
            $.MsgBox.Alert('提示','请输入身份证号！');
            return false;
        }
        /*
        if(password.length==0){
            $.MsgBox.Alert('提示','请输入交易密码！');
            return ;
        };
        */

        if(isWeiXin()){
            if ($('input[name=image1]').val()=="") {
                $.MsgBox.Alert('提示',"请上传身份证正面照！");
                return false;
            }
            if ($('input[name=image2]').val()=="") {
                $.MsgBox.Alert('提示',"请上传身份证反面照！");
                return false;
            }
            if ($('input[name=image3]').val()=="") {
                $.MsgBox.Alert('提示',"请上传手持身份证半身照！");
                return false;
            }
        }
        $('#update-info-btn').removeAttr('disabled');
        if(isWeiXin()){
            var csrfToken = $('meta[name="csrf-token"]').attr("content");
            var userInfo = app.getUserInfo();
            var postData={
                'loginid':userInfo.userid,
                'logincode':userInfo.uid,
                'username':encrypt.encrypt(userName),
                'phone':encrypt.encrypt(phone),
                'idcard':encrypt.encrypt(idCard),
                //'password':encrypt.encrypt(password),
                'market':market,
                'token':wxToken,
                '_csrf':csrfToken,
                'image1':$('input[name=image1]').val(),
                'image2':$('input[name=image2]').val(),
                'image3':$('input[name=image3]').val()
            };
            var ajax = $.ajax({
                url:'?r=server/updateinfo&market='+market,
                data:postData,
                type:"POST",
                dataType:"json",
                beforeSend:function(){
                    $('#update-info-btn').val("加载中..");
                    $('#update-info-btn').attr('disabled','true');
                },
                success:function(mod){
                    if(mod.retCode==0){
                        ac_real  = 1;
                        $.MsgBox.AlertB('提示','修改成功',function () {
                            $.afui.goBack();
                        });
                    }else{
                        if(parseInt(mod.retCode) == 13) {
                            $.MsgBox.Alert('提示', "用户已存在");
                        }else if(parseInt(mod.retCode) == 40){
                            $.MsgBox.AlertB('提示',"登录授权超时，请重新登录",function () {
                                location.reload();
                            });
                        }else{
                            $.MsgBox.Alert('提示',mod.retText);
                        }
                        $('#update-info-btn').val("提 交");
                        $('#update-info-btn').removeAttr('disabled');
                    }
                },
                complete:function(){
                    if(status=='timeout'){
                        ajax.abort();
                        $.MsgBox.Alert('提示',"服务器连接超时！");
                    }
                    $('#update-info-btn').val("提 交");
                    $('#update-info-btn').removeAttr('disabled');
                }
            });
        }else{
            $('input[name=username]').val(encrypt.encrypt(userName));
            $('input[name=idcard]').val(encrypt.encrypt(idCard));
            $('input[name=phone]').val(encrypt.encrypt(phone));
            //$('input[name=password]').val(encrypt.encrypt(password));
            $('#updateForm').attr('action','?r=server/updateinfo&market='+market);
            $('#updateForm').submit();
        }
    }
});
var share_url = 'http://wechat.luhongsuo.com/index.php?serial_code='+serial_code;
var app_name = '禄宏微交易';
function isWeiXin(){
    var ua = window.navigator.userAgent.toLowerCase();
    if(ua.match(/MicroMessenger/i) == 'micromessenger'){
        return true;
    }else{
        return false;
    }
}
// 初始化微信 JS API
function getWXJSSDK() {
    wx.config({
        debug     : false,
        appId     : wx_appid,
        timestamp : wx_timestamp,
        nonceStr  : wx_noncestr,
        signature : wx_signature,
        jsApiList : [
            // 所有要调用的 API 都要加到这个列表中
            'checkJsApi',
            'chooseImage',
            'uploadImage',
            'downloadImage',
            'previewImage',
            'hideOptionMenu',
            'scanQRCode'
        ]
    });
    wx.ready(function(){
        wx.onMenuShareTimeline({
            title: app_name, // 分享标题
            desc: app_name+'，1分钟线上开户，便捷，安全，收益高.', // 分享描述
            link: share_url, // 分享链接
            imgUrl: assets_path+'/image/qrlogo.png', // 分享图标
            success: function () {
                // 用户确认分享后执行的回调函数
            },
            cancel: function () {
                // 用户取消分享后执行的回调函数
            }
        });
        wx.onMenuShareAppMessage({
            title:app_name, // 分享标题
            desc: app_name+'，1分钟线上开户，便捷，安全，收益高.', // 分享描述
            link: share_url, // 分享链接
            imgUrl: assets_path+'/image/qrlogo.png', // 分享图标
            type: '', // 分享类型,music、video或link，不填默认为link
            dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
            success: function () {
                // 用户确认分享后执行的回调函数
            },
            cancel: function () {
                // 用户取消分享后执行的回调函数
            }
        });
        //分享到QQ空间
        wx.onMenuShareQZone({
            title: app_name, // 分享标题
            desc: app_name+'，1分钟线上开户，便捷，安全，收益高.', // 分享描述
            link: share_url,
            imgUrl: assets_path+'/image/qrlogo.png', // 分享图标
            success: function () {
            },
            cancel: function () {
            }
        });
        //分享到QQ
        wx.onMenuShareQQ({
            title: app_name, // 分享标题
            desc: app_name+'，1分钟线上开户，便捷，安全，收益高.', // 分享描述
            link: share_url,// 分享链接
            imgUrl: assets_path+'/image/qrlogo.png', // 分享图标
            success: function () {
            },
            cancel: function () {
            }
        });
        wx.error(function(res){
            console.log(res);
        });
    });
};
// 选择照片
function takePhoto(key,callBack) {
    wx.chooseImage({
        success: function (res) {
            var imgKey = '#img_' + key ;

            images.imgMap[imgKey] = res.localIds[res.localIds.length-1] ;

            if (typeof callBack === 'function') {
                callBack(res,images) ;
            }
        }
    });
};

//上传照片
function uploadPhoto(localId,callBack){
    wx.uploadImage({
        localId : localId,
        isShowProgressTips: 1, // 默认为1，显示进度提示
        success : function (res) {
            if (typeof callBack==='function') {
                callBack(res.serverId) ;
            }
        },
        fail : function (res) {
            console.log(res);
            $.MsgBox.Tip('error','网络繁忙，请稍后再上传！');
        }
    }) ;
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

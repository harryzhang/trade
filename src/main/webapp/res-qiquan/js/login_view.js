/**
 * Created by yyc on 2015/11/26.
 */
var wxToken;
var loginModel = 3;
var LoginView=Backbone.View.extend({
    el: '#login',
    initialize:function(){
        this.render();
    },
    render:function(){
        var _self=this;
        $('#login').show();
        
        $('#btnLogin').bind('click',function () {
            _self.doLogin();
        });

        $.get('http://localhost:8080?r=server/wxdata&rnd='+Math.random(), function (result) {
            result = eval('(' + result + ')');
            if(result.retCode =="0"){
                $('.registerTap').hide();
                $('.loginTap').show();
                $('#btnWxLogin').bind('click',function () {
                    _self.doAutoLogin(result.uid,result.pwd);
                });
            }else{
                if(result.retCode == "43"){
                    $('.registerTap').show();
                    $('.loginTap').hide();
                    $('#btnWxRegister').bind('click',function () {
                        _self.doAutoRegister();
                    });
                }else{
                    $.MsgBox.AlertB('��ʾ','����ʱ�������µ�¼',function () {
                        location.reload();
                    });
                }
            }
        });

    },
    doLogin:function () {
        if (sdk) {
            var _uid=$('input[name=uid]').val();
            var _pwd=$('input[name=pwd]').val();
            $.MsgBox.Loading('��ʾ','������...');
			LOGINTYPE =1;
            sdk.init(encrypt_key,encrypt_iv,'assets/default/component/wetbizsdk',function() {
                sdk.loginWithUserId({
                    uid   : _uid,
                    pwd   : _pwd,
                    vcode : '',
                    urls  : app_ws_url,
                    onSuccess : function() {
                        $.MsgBox.Clean();
                        $('#login_page').hide();
                        if(loginStatus ==1){
							setTimeout(function(){
                                onVisibility();
                            },500);
                        }else{
                            loginStatus = 1;
                            addSocketListener();
                            resetW();
                            if(!window.tradeView){
                                window.tradeView = new TradeView();
                            }else{
                                window.tradeView.render();
                            }
                        }
                    },
                    onFailure : function(retCode,message) {
                        $.MsgBox.Clean();
                        if(loginStatus==1){
                            $.MsgBox.AlertB('��ʾ','��¼��ʱ�������µ�¼',function () {
                                location.reload();
                            });
                        }else{
                            $.MsgBox.Alert('��ʾ','��¼ʧ�ܣ�[' + retCode + ']' + message);
                        }
                    }
                });
            });
        }
    },
    registerShow:function () {
        if(serial_code==""){
            var html = "";
            html ="<label class=\"label-box relative\">";
            html += "<div class=\"default-tit\">������</div>"
            html += "<input class=\"default-num\" type=\"number\" min=\"0\" id=\"serial_code\" value=\"\">";
            html += "</label>";
            $.MsgBox.Alert('��ʾ',html);
        }

        $('.default-tit').css({'text-align':'left'});
        $('.open-btn').unbind('click');
        $('.open-btn').bind('click',function(){
            var defalt_point_offset= $("#txtdiancha").val();
            if(defalt_point_offset==""){
                $("#txtdiancha").val(DEFAULT_POINT_OFFSET);
                return;
            }
            if(parseInt(defalt_point_offset)!=defalt_point_offset || defalt_point_offset<0 || defalt_point_offset>MAX_POINT_OFFSET){
                $("#txtdiancha").val(DEFAULT_POINT_OFFSET);
                return;
            }
            setCookie('defalt_point_offset',defalt_point_offset);
            DEFAULT_POINT_OFFSET = defalt_point_offset;
            $.MsgBox.Clean();
        })
    },
    doAutoRegister:function () {
		var serial_code = $('#serial_code').val();
        if(serial_code == ""){
            $.MsgBox.Alert('��ʾ',"������������");
            return;
        }
        var csrfToken = $('meta[name="csrf-token"]').attr("content");
        var userName = '��ʱ�ʺ�';
        var password = parseInt(Math.random()*Math.pow(10,8));
        var phone = parseInt(Math.random()*Math.pow(10,11));
        var idcard = generateMixed(18);
        var postData={
            'serialcode':serial_code,
            'username':encrypt.encrypt(userName),
            'password':encrypt.encrypt(password),
            'phone':encrypt.encrypt(phone),
            'idCard':encrypt.encrypt(idcard),
            'market':market,
            '_csrf':csrfToken,
        };

        var parmars = "&serialcode="+serial_code;
        parmars += "&username="+encodeURIComponent(postData.username);
        parmars += "&password="+encodeURIComponent(postData.password);
        parmars += "&phone="+encodeURIComponent(postData.phone);
        parmars += "&idCard="+encodeURIComponent(postData.idCard);
        parmars += "&openid="+encodeURIComponent(wx_key);
        var _ajax = $.ajax({
            async: false,
            url: "http://regwjy.luhongsuo.com/?r=server/jsregister" + parmars,
            type: "GET",
            dataType: "jsonp",
            jsonp: 'jsoncallback',
            timeout: 10000,
            beforeSend:function(){
                $('#btnWxRegister').val("������..");
                $('#btnWxRegister').attr('disabled','true');
            },
            success:function(mod){
                if(mod.retCode==0){
                    location.reload();
                }else{
                    if(parseInt(mod.retCode) == 26){
                        $.MsgBox.Alert('��ʾ',"��Ч������");
                    }else{
                        $.MsgBox.Alert('��ʾ',mod.retText);
                    }
                    $('#btnWxRegister').val("ȷ��������");
                    $('#btnWxRegister').removeAttr('disabled');
                }
            },
            error:function () {
                $.MsgBox.AlertB('��ʾ',"���������ӳ�ʱ��",function () {
                    location.reload();
                });
            },
            complete:function(){
                if(status=='timeout'){
                    _ajax.abort();
                    $.MsgBox.AlertB('��ʾ',"���������ӳ�ʱ��",function () {
                        location.reload();
                    });
                }
                $('#btnWxRegister').val("ȷ��������");
                $('#btnWxRegister').removeAttr('disabled');
            }
        });
        /*
        var ajax = $.ajax({
            url:api_path+'?r=server/jsregister&market='+market,
            data:postData,
            type:"POST",
            dataType:"json",
            timeout :10000,
            beforeSend:function(){
                $('#btnWxRegister').val("������..");
                $('#btnWxRegister').attr('disabled','true');
            },
            success:function(mod){
                if(mod.retCode==0){
                   location.reload();
                }else{
                    if(parseInt(mod.retCode) == 26){
                        $.MsgBox.Alert('��ʾ',"��Ч������");
                    }else{
                        $.MsgBox.Alert('��ʾ',mod.retText);
                    }
                    $('#btnWxRegister').val("ȷ��������");
                    $('#btnWxRegister').removeAttr('disabled');
                }
            },
            complete:function(){
                if(status=='timeout'){
                    ajax.abort();
                    $.MsgBox.Alert('��ʾ',"���������ӳ�ʱ��");
                }
                $('#btnWxRegister').val("ȷ��������");
                $('#btnWxRegister').removeAttr('disabled');
            }
        });
        */
    },
    doAutoLogin:function (uid,pwd) {
        var self =this;
        if (sdk) {
            var _uid = uid;
            var _pwd = pwd;
            wxToken = pwd;
            $.MsgBox.Loading('��ʾ','������...');
            sdk.init(encrypt_key,encrypt_iv,'assets/default/component/wetbizsdk',function() {
                LOGINTYPE =3;
                sdk.loginWithUserId({
                    uid   : _uid,
                    pwd   : _pwd,
                    vcode : '',
                    urls  : app_ws_url,
                    onSuccess : function() {
                        $.MsgBox.Clean();
                        $('#login_page').hide();
                        if(loginStatus ==1){
                            setTimeout(function(){
                                onVisibility();
                            },500);
                        }else{
                            loginStatus = 1;
                            addSocketListener();
                            resetW();
                            if(!window.tradeView){
                                window.tradeView = new TradeView();
                            }else{
                                window.tradeView.render();
                            }
                        }
                    },
                    onFailure : function(retCode,message) {
                        $.MsgBox.Clean();
                        if(loginStatus==1){
                            $.MsgBox.AlertB('��ʾ',retCode+':��¼��ʱ�������µ�¼',function () {
                                location.reload();
                            });
                        }else{
							$.MsgBox.AlertB('��ʾ',retCode+':��¼��ʱ�������µ�¼',function () {
                                location.reload();
                            });
                            //$.MsgBox.Alert('��ʾ','��¼ʧ�ܣ�[' + retCode + ']' + message);
                        }
                    }
                });
            });
        }
    }
});
function updateUserInfo( callback) {
    var userInfo = app.getUserInfo();
    $.ajax({
        url: '?r=server/userdata&userid='+userInfo.userid+'&rnd='+Math.random(),
        type: "GET",
        success: function (result) {
            result = eval('(' + result + ')');
            if(result.retCode =="0"){
                ac_score = result.ac_score;
                ac_real = result.ac_real;
                ac_login = result.ac_login;
                ac_serial_code = result.ac_serial_code;
                ac_score = result.ac_score;
            }
            if(typeof(callback)== 'function' ){
                callback();
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            if(typeof(callback)== 'function' ){
                callback();
            }
        }
    })
}
function generateMixed(n) {
    var chars = ['0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','!','#','$','%','*','(',')'];
    var res = "";
    for(var i = 0; i < n ; i ++) {
        var id = Math.ceil(Math.random()*35);
        res += chars[id];
    }
    return res;
}
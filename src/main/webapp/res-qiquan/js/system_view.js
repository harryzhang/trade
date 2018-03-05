var SystemView=Backbone.View.extend({
	el : '#system',
    template : '',
    events: {},
    initialize:function(){
        var _self=this;
        $.MsgBox.Loading('提示','加载中...');
        $.get(assets_path+'page/trade/system.html?v=1.0', function(result){
            $.MsgBox.Clean();
            _self.template=_.template(result);
            _self.render();
        });
    },
    render:function() {
        if($(this.el).html()==''){
            this.$el.html(this.template({
                'assets_path':assets_path,
                'user':app.getUserInfo(),
                'headimgurl' : wx_headimgurl,
                'ac_score':ac_score
            }));
        }
        $('#system .qrcode').unbind('click');
        $('#system .qrcode').bind('click',function () {
            var qrUrl = 'http://wechat.luhongsuo.com/index.php?r=wechat/nqrcode&serial_code='+ac_serial_code;
            $.get(qrUrl,function (data) {
                var _html = "<img src=\""+data+"\"/>";
                $.MsgBox.Alert('提示',_html);
            })
        })

        $('#login-out-btn').unbind('click');
        $('#login-out-btn').bind('click',function(){
            $.MsgBox.Confirm('提示','确定要退出交易！',function(){
                $.get('?r=server/wxunlogin',function () {
                    location.reload();
                });
            });
        })
        $('#system .default-point-setting').unbind('click');
        $('#system .default-point-setting').bind('click',function(){
            var defalt_point_offset,html;
            defalt_point_offset = getCookie("defalt_point_offset");
            if(defalt_point_offset == 'undefined' || defalt_point_offset ==undefined || defalt_point_offset ==""){
                defalt_point_offset = DEFAULT_POINT_OFFSET;
            }
            html ="<label class=\"label-box relative\">";
            html += "<div class=\"default-tit\">默认允许偏离点差设置</div>"
            html += "<input class=\"default-num\" type=\"number\" min=\"0\" id=\"txtdiancha\" value=\""+defalt_point_offset+"\">";
            html += "</label>";
            $.MsgBox.Alert('提示',html);
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
        })
        $('.main-menu').width($('#system').width());

    }
});
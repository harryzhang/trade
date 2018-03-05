var MessageView=Backbone.View.extend({
	el : '#message',
    template : '',
    notices:'',
    events: {},
    initialize:function(){
        var _self=this;
        $.MsgBox.Loading('提示','加载中...');
        $.get(assets_path+'page/trade/message.html', function(result){
            $.MsgBox.Clean();
            _self.template=_.template(result);
            _self.render();
        });

    },
    render:function() {
    	if($(this.el).html()==''){
            this.$el.html(this.template({
                'assets_path':assets_path
            }));
        }
        this.getNotices();
    },
    getNotices:function(){
        sdk.queryNotics({
            onSuccess:function(mods){
                console.log(mods);
                var template = _.template($('#noticeTemplate').html());
                var _html=template({
                    'list':mods
                });
                notices = mods;
                $("#notice_content").html(_html);
                $('#message .n-list').each(function(index){
                    $(this).click(function(){
                        var mod = notices[index];
                        var template = _.template($('#bulletinTemplate').html());
                        var _html=template({
                            'item':mod
                        });
                        $.MsgBox.Bulletin(mod.title,_html,function(){});
                    });
                })
              
            },
            onFailure:function(){

            }
        })
    }
});
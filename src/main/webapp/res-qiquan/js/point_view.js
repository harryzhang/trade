var SignView=Backbone.View.extend({
    el : '#sign',
    template : '',
    events: {},
    initialize:function(){
        var _self = this;
        $.MsgBox.Loading('提示', '加载中...');
        $.get( '?r=site/sign', function (result) {
            $.MsgBox.Clean();
            _self.template = _.template(result);
            _self.render();
        });
    },
    render:function() {
        var _self = this;
        this.$el.html(_self.template({
            'user':app.getUserInfo(),
            'assets_path':assets_path,
            'ac_score':ac_score
        }));

        $('#do-sign-button').unbind('click');
        $('#do-sign-button').bind('click',function () {
            $(this).attr('disable',true);
            var csrfToken = $('meta[name="csrf-token"]').attr("content");
            $.ajax({
                url:api_path+'?r=server/sign',
                data:{'_csrf':csrfToken},
                type:"POST",
                dataType:"json",
                timeout : 10000,
                beforeSend:function(){
                    $('#do-sign-button').html('提交中...');
                },
                success:function(result){
                    if(result.retCode==0){
                        $.MsgBox.Alert('提示',result.retMsg);
                        ac_score = result.score;
                        $.get( '?r=site/sign&t='+Math.random(), function (result) {
                            _self.template = _.template(result);
                            _self.render();
                        });
                    }else{
                        $.MsgBox.Alert('提示',result.retMsg);
                    }
                }
            });
        })

    }
});
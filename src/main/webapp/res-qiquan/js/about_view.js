/**
 * Created by yyc on 2016/5/13.
 */
var AboutView=Backbone.View.extend({
    el : '#about',
    template : '',
    notices:'',
    events: {},
    initialize:function(){
        var _self=this;
        $.MsgBox.Loading('提示','加载中...');
        $.get(assets_path+'page/trade/about.html', function(result){
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
$('.kf-tel').width($('#about').width());
    }
});

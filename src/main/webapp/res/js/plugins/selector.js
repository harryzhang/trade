function Selector(opts) {
    $.extend(this, {
        data:null,
        value: undefined,
        text:null,
        input:null,
        callback: function() {},
        showCallBack: function(){},
        template: '<section class="side-selector hide">' +
            '    <div class="selector-wrapper">' +
            '        <h3><span class="icon-back2"></span>'+(opts.title||'请选择')+'</h3>' +
            '        <div class="selector-list">' +
            '            <ul>' +
            '            </ul>' +
            '        </div>' +
            '    </div>' +
            '</section>'
    }, opts || {});
    this.init();
};

Selector.prototype = {
    init: function() {
        this.curValue = this.value;
        this.target = $(this.template);
        $(document.body).append(this.target);
        this.listTarget = this.target.find('.selector-list ul');
        this.events();
        if(this.value!==undefined){
            this.text.text(this.data[this.value]);
            this.input.val(this.value);
        }

        if(this.data){
            this.create();
        }
    },
    create: function(){
        var html = '',key;
        for(key in this.data){
            html+='<li'+(key!=this.value?'':' class="selected"')+' data-id="'+key+'">'+this.data[key]+'</li>';
        }
        this.listTarget.html(html);
    },
    show: function() {
        var that = this;
        this.target.removeClass('hide');
        this.showCallBack.call(this,this.listTarget);
        setTimeout(function() {
            that.target.addClass('active');
        }, 0);
    },
    close: function() {
        var that = this;
        this.target.removeClass('active');
        setTimeout(function() {
            that.target.addClass('hide');
        }, 310);
    },
    events: function() {
        var that = this;
        this.listTarget.delegate('li', 'click', function() {
            var closing = that.callback.call(that,$(this).data('id'),$(this).text());
            if(closing!==false){
                that.text.removeClass('default');
                $(this).addClass('selected').siblings().removeClass();
                that.close();
            }
        });
        this.target.delegate('.icon-back2', 'click', function() {
            that.close();
        });
    }
};


function SelectorPro(opts){
    opts.progress = 0;
    opts.cacheData= [opts.data];
    opts.cacheCode = [];
    this.opts = opts;
    this.init();
};

SelectorPro.prototype = {
    init: function(){
        var selector = new Selector(this.opts);
        selector.callback = this._callback;
        selector.showCallBack = this._showCallBack;
        selector.opts = this.opts;
        this.parent = selector;
        $.extend(this ,selector);

    },
    _showCallBack: function(){
         this.overightEvent();
    },
    _callback: function(code, text){
        var close = this.opts.callback.call(this, code, text);
        this.cacheData[this.progress] = this.data;
        this.cacheCode[this.progress-1] = code;
        return close;
    },
    overightEvent: function(){
        var that = this;
        this.target.undelegate('.icon-back2', 'click').
        delegate('.icon-back2', 'click', function(){
            if(that.parent.progress === 0){
                that.close();
            }else{
                that.data = that.parent.cacheData[--that.parent.progress];
                that.value = that.parent.cacheCode[that.parent.progress];
                console.log(that.value)
                that.create();
            }
        });
    }
};


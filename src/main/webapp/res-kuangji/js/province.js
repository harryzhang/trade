// JavaScript Document

function ProvinceSelector(opts) {
    $.extend(this, {
    	cityId:null,
        cityUrl:null,		
		provinceId:null,
		provinceUrl:null,
		provinceTarget:null,
		cityTarget:null,
        callback: function() {}
    }, opts || {});
    this.init();
};
ProvinceSelector.prototype = {
		
	    init: function() {
	    	
	    	this.provinceTarget = $("#"+this.provinceId);
	    	this.cityTarget = $("#"+this.cityId);
	    	this.events();
	        this.createProvince();
	    },
	    createProvince: function() {//初始化省
	    	var that = this;
	    	//初始化省
	    	var opts = "<option value='-1'>--请选择--</option>";
	    	var provinceParam = {};
	    	$.post(that.provinceUrl, provinceParam, function(data) {
	    		if(data.result=='0'){
	    			 jsonLst = data.provinceLst;
	    			 $.each(jsonLst, function (n, value) {
	    	               var trs =  "<option value='" + value.provinceId + "'>" + value.provinceName + "</option>";
	    	               opts += trs;
	    	           });
	    			 $(that.provinceTarget).find("select").html("");
	    			 $(that.provinceTarget).find("select").append(opts); 
	    		}
	    	},"json");
	    },
	    events: function() {
	        var that = this, type, id, name;
	        this.provinceTarget.delegate('select', 'change', function() {	        	
	    		var province = $(this).val();
	    		var param = {
	    				"province" : province			
	    			};
	    		
	    		$.post(that.cityUrl, param, function(data) {
	    			if(data.result=='0'){
	    				 jsonLst = data.cityLst;
	    				 var opts = "<option value='-1'>--请选择--</option>";
	    				 $.each(jsonLst, function (n, value) {
	    		               var trs =  "<option value='" + value.cityId + "'>" + value.cityName + "</option>";
	    		               opts += trs;
	    		           });
	    				 $(that.cityTarget).html("");
	    				 $(that.cityTarget).append(opts); 
	    			}
	    		},"json");
	    	
	        });
	    }
};
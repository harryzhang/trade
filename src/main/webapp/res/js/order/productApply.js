
//检查期限
		function checkSchemePriod(inputPriodVal){
    		var schemeId = $('#productRadio li.on').attr("data-value");
    		var schemeName = $('#productRadio li.on').text();
    		var minPriod =  $('#minLoanPeriod-'+schemeId).val();
    		var maxPriod =  $('#maxLoanPeriod-'+schemeId).val();
    		if(inputPriodVal<minPriod || inputPriodVal>maxPriod){
	    		HHN.popup('选定产品'+ schemeName+'，只能选择'+minPriod+'-'+maxPriod+'个月', 'warning');	
	    		return false;
	    	}
    		return true;
    	}
    	
    	function getProudctValData(){
			var schemeId = $('#productRadio li.on').attr("data-value");
			 var value = {
			    loanId:"",
		    	loanType: $('#loanType li.on').attr("data-value"),
		    	schemeId: schemeId,
		    	prodId: $("#prodId").val(),
		    	productCode: $("#productCode").val(),
		        loanPeriod: parseInt($("#loanPeriod").val()),
		        applyAmount: parseInt($("#applyAmount").val()),
		        annualRate:parseFloat($('#annualRate-'+schemeId).val()),
    			settleWay:$('#settleWay-'+schemeId).val()
		    };
		    if(!value.loanType){
		        HHN.popup('请选择贷款类型', 'warning');
		        return;
		    }
		    if(!value.schemeId){
		        HHN.popup('请选择产品', 'warning');
		        return;
		    }
		    
		    //检查期限
		    var checkResult = checkSchemePriod(value.loanPeriod);
		    if(!checkResult){
		    	return;
		    }
		    
		    if(value.applyAmount%100!=0 || value.applyAmount<10000 || value.applyAmount>1000000 || !value.applyAmount){
		    	HHN.popup('请输入1万到100万且是100整数倍数字', 'warning');
		        return;
		    }
		    return value;
		}
		
		
function ProductSchemeSelector(opts) {
    $.extend(this, opts || {});
    this.init();
};

ProductSchemeSelector.prototype = {
		
		init: function() {
			
			var lTypelength = $('#loanType li.on').length;
			if(lTypelength<1){
				$('#loanType li:nth-child(1)').addClass('on');
			}
			var productlength = $('#productRadio li.on').length;
			if(productlength<1){
				//$('#productRadio li:nth-child(1)').addClass('on');
				$("#productRadio li").each(function(index){
					if($("#schemeRadio li.on").attr("data-value")==$(this).attr("data-value")){
						$("#productRadio li").removeClass('on');
						$(this).addClass('on');
					}					
				});
				$('#schemeName').text($('#schemeRadio li.on').text());
				//$('#productRadio li:on').addClass('on');
			}else{
				$("#schemeRadio li").each(function(index){
					if($("#productRadio li.on").attr("data-value")==$(this).attr("data-value")){
						$("#schemeRadio li").removeClass('on');
						$(this).addClass('on');
					}					
				});
				$('#schemeName').text($('#schemeRadio li.on').text());
			}
				
			//初始化贷款期数
			//$('#loanPeriod').val(this.opts.loanPeriod);
			if($('#loanPeriod').val() == null || $('#loanPeriod').val() == "" ){
				$('#loanPeriod').val(12+'个月');
			}
			
			
			//初始化数据处理
			var prodId = $("#prodId").val();	
			var productCode = $("#productCode").val();
			
			//applyBtn           = $('#'+this.opts.confirmBtn),
			buySlide           = $('#buySlide'),
			closeBuySlide      = $('#closeBuySlide'),
			productRadio       = $('#productRadio li'),
			minus              = $('#minus'),
			plus               = $('#plus'),
			loanPeriod         = $('#loanPeriod'),
			applyAmount        = $('#applyAmount');
			
			closeBuySlide.on('click', function(){
			    buySlide.removeClass('show');
			});
			
			productRadio.on('click', function(){
			    productRadio.removeClass('on');
			    $(this).addClass('on');
			    var schemeId = $(this).attr("data-value");
	    		var minPriod =  $('#minLoanPeriod-'+schemeId).val();
	    		loanPeriod.val(minPriod+'个月' );
	    		
			});
			
			minus.on('click', function(){
				var period = parseInt($("#loanPeriod").val());
			    var checkResult = checkSchemePriod(period-1);
			    if(!checkResult){
			    	return;
			    }
			    loanPeriod.val( --period+'个月' );
			});
			plus.on('click', function(){
				var period = parseInt($("#loanPeriod").val());
			    var checkResult = checkSchemePriod(period+1);
			    if(!checkResult){
			    	return;
			    }
			    loanPeriod.val( ++period+'个月' );
			});
		}
        //end init
}
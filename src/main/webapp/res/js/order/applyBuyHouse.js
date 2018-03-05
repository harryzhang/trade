  $(function(){
     var buyTimes       = $('#buyTimes').val();            
        if(buyTimes !=null && buyTimes !="" && buyTimes !=undefined){
        	var times = parseInt(buyTimes);           	
        	purchaseTimes.val( times+'次' );
        }
        
        var houseTypelength = $('#houseTypeRadio li.on').length;
		if(houseTypelength<1){
			$('#houseTypeRadio li:nth-child(1)').addClass('on');
		}
  });
  
  function getHouseTotalPrice(x,y) {
	  if(x==undefined || x=="" || x=='NaN'){return "";}
	  if(y==undefined || y=="" || y=='NaN'){return "";}
	  var f=parseFloat(x)*parseFloat(y);                                   
      return Math.round(f*100)/100;         
   }  

    var houseTypeRadio = $('#houseTypeRadio li'),
        minus              = $('#minus'),
        plus               = $('#plus'),
        purchaseTimes      = $('#purchaseTimes');
        housePrice= $('#housePrice');
        coveredArea= $('#coveredArea');
        
        houseTypeRadio.on('click', function(){
            houseTypeRadio.removeClass('on');
            $(this).addClass('on');
        });   
        minus.on('click', function(){
            var value = parseInt(purchaseTimes.val());
            if(value<=1){
                HHN.popup('购房次数不能少于1次', 'warning');
                return;
            }
            purchaseTimes.val( --value+'次' );
        });
        plus.on('click', function(){
            var value = parseInt(purchaseTimes.val());
            if(value>=36){
                HHN.popup('这也太多了吧', 'warning');
                return;
            }
            purchaseTimes.val( ++value+'次' );
        });   
        housePrice.on('blur', function(){
        	var housePrice = $('#housePrice').val();
        	var coveredArea = $('#coveredArea').val();
        	if(housePrice==null || housePrice==""){   	    	
    	        HHN.popup('请填写房屋价格', 'warning');
    	        return;
    	    }
    	    if( !HHN.checkTowDigit(housePrice)  || housePrice>100000 || housePrice<500){
    	        HHN.popup('请填写500~10万之间最多两位小数的的房屋价格', 'warning');
    	        return;
    	    }
    	    $('#houseTotalPrice').val(getHouseTotalPrice(housePrice,coveredArea));
        }); 
        
        coveredArea.on('blur', function(){
        	var housePrice = $('#housePrice').val();
        	var coveredArea = $('#coveredArea').val();
        	if(coveredArea==null || coveredArea==""){  
    	        HHN.popup('请填写房屋面积', 'warning');
    	        return;
    	    }
    	    if( !HHN.checkTowDigit(coveredArea) || coveredArea>1000 || coveredArea<5){
    	        HHN.popup('请填写5~1000之间最多两位小数的房屋面积', 'warning');
    	        return;
    	    }
    	    $('#houseTotalPrice').val(getHouseTotalPrice(housePrice,coveredArea));
        });  
        
        
        var houseAddress =$('#houseAddress').val();    	
	   	 if(houseAddress !=null && houseAddress !=""){
	   		houseAddress = houseAddress.split(',');
	   	  }else{
	   		houseAddress='';
	   	  }    
        var myDistrit = new DistritSelector({
            data: district,
            value: houseAddress,
            text: $('#addressText'),
            input: $('#houseAddress'),
            callback: function(code, text) {
                if (!code) return;
                this.text.text(text.join('-'));
                this.input.val(code.join(','));
            }
        });

        $('#address').on('click', function() {
            myDistrit.show();
        });
        
        $('#applyBtn').on('click', function(){
        	var loanId = $("#loanId").val();
    		if(loanId!=null && loanId!=undefined && loanId!='NaN' && loanId!=''){
    			loanId=parseInt(loanId);
    		}else{
    			loanId="";
    		}
    	    var value = {
    	    	loanId: loanId,
    	    	houseAddress: $('#houseAddress').val(),
    	    	houseType: $('#houseTypeRadio li.on').attr("data-value"),
    	    	purchaseTimes: parseInt($("#purchaseTimes").val()),
    	    	houseName: $("#houseName").val(),
    	    	housePrice: parseFloat($("#housePrice").val()),
    	    	coveredArea: parseFloat($("#coveredArea").val()),
    	    	houseTotalPrice: parseFloat($("#houseTotalPrice").val())
    	    };    	    
    	    if(!value.houseAddress){
    	        HHN.popup('请选择购房位置', 'warning');
    	        return;
    	    }
    	    if(!value.houseType){
    	        HHN.popup('请选择楼盘性质', 'warning');
    	        return;
    	    }
    	    if(!value.houseName){
    	        HHN.popup('请填写楼盘名字', 'warning');
    	        return;
    	    }
    	    if(HHN.checkAllLetter(value.houseName) || HHN.checkAllSign(value.houseName) || HHN.checkAllDigit(value.houseName) ){
    	        HHN.popup('请正确填写楼盘名字', 'warning');
    	        return;
    	    }
    	    if(value.houseName.length>100){
    	    	 HHN.popup('楼盘名字长度超100，请重新填写', 'warning');
     	        return;
    	    }
    	    if(!value.housePrice){   	    	
    	        HHN.popup('请填写房屋价格', 'warning');
    	        return;
    	    }
    	    if( !HHN.checkTowDigit(value.housePrice)  || value.housePrice>100000 || value.housePrice<500){
    	        HHN.popup('请填写500~10万之间最多两位小数的的房屋价格', 'warning');
    	        return;
    	    }
    	    if(!value.coveredArea){
    	        HHN.popup('请填写房屋面积', 'warning');
    	        return;
    	    }
    	    if( !HHN.checkTowDigit(value.coveredArea) || value.coveredArea>1000 || value.coveredArea<5){
    	        HHN.popup('请填写5~1000之间最多两位小数的房屋面积', 'warning');
    	        return;
    	    }
//    	    if(!value.houseTotalPrice){
//    	        HHN.popup('请填写房屋总价', 'warning');
//    	        return;
//    	    }
//    	    if( !HHN.checkTowDigit(value.houseTotalPrice)  || value.houseTotalPrice>10000000 || value.houseTotalPrice<50000){
//    	        HHN.popup('请填写5万~1000万之间最多两位小数的房屋总价', 'warning');
//    	        return;
//    	    }    	
    	    $.post(globalConfig.basePath+"/order/savePurchaseHouse.do",value,function(data) {
    	    	if(data.success && data.resultCode == '0'){
    				window.location.href=globalConfig.basePath+"/order/toLoanPersonInfo.do?loanId="+data.model;
    			}else{
    				HHN.popup(data.resultMessage);
    			}
    		},"json");
    	    
    	});
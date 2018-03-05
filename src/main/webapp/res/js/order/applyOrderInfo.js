 $(function(){
	          var purchaseWay = $('#purchaseWay').val();
          if(purchaseWay=='1'){
        	  $('#purchaseWay').prop("checked", true);
          }else{
        	  $('#purchaseWay').prop("checked", false); 
          }
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
    
    //购房日期
    function createDecialArrary(start, end, text) {
        var result = {};
        for (; start <= end; start++) {
            result[start + ''] = start + text;
        }
        return result;
    };
    var dayTime = new Date,
        curYear = dayTime.getFullYear(),
        buyDate = [],
        buySelector = new SelectorPro({
            title: '请选择购房日期',
            data: createDecialArrary(1992, curYear, '年'), 
            text: $('#timeText'),
            input: $('#purchaseDate'),
            callback: function(code) {
            	var year = code+"年";
            	this.text.html(year);
                this.input.val(code);
                /*if (this.progress < 2) {
                    buyDate[this.progress] = code;
                    this.data = this.progress === 0 ?
                        createDecialArrary(1, 12, '月') :
                        createDecialArrary(1, new Date(buyDate[0], code, 0).getDate(), '日');
                    this.progress++;
                    this.create();
                    return false;
                } else {
                    buyDate[this.progress] = code;
                    var value = buyDate.join('-').replace(/\b(\w)\b/g, '0$1');
                    this.text.html(value);
                    this.input.val(value);
                }*/
            }
        });
        $('#timeSelector').on('click', function(){
        	buySelector.show();
        });
        
      function checkRefferee(refferee){
     	   var msg="";
     	   $.ajax({
     	          type:"POST",  
     	          url:globalConfig.basePath+"/loanuser/isExistsRefferee.do",  //校验推荐人手机号是否存在，与注册手机号是否一致
     	          data:{refferee:refferee},
     	          dataType:"json",  
     	          async:false,
     	          success:function(data){
     	        	  if(data.success && data.resultCode == '0'){
                          //  return true;
     	    			}else{
     	    				//HHN.popup(data.resultMessage);
     	    				msg=data.resultMessage;
     	        	       // return false;
     	    			}
     	          }
             });
     	   return msg;
       }
      
      function checkMobile(mobile){
    	  var msg="";
    	   $.ajax({
    	          type:"POST",  
    	          url:globalConfig.basePath+"/loanuser/isExistsMobile.do",  //校验推荐人手机号是否存在用户表中
    	          data:{mobile:mobile},
    	          dataType:"json",  
    	          async:false,
    	          success:function(data){
    	        	  if(data.success && data.resultCode == '0'){
                         //  return true;
    	    			}else{
    	    				//HHN.popup(data.resultMessage);
    	    				msg=data.resultMessage;
    	        	       // return false;
    	    			}
    	          }
            });
    	   return msg;
      }
     	   
  $('#orderApplyBtn').on('click', function(){
	  var loanId = $("#loanId").val();
  		if(loanId!=null && loanId!=undefined && loanId!='NaN' && loanId!=""){
  			loanId=parseInt(loanId);
  		}else{
  			loanId="";
  		}
     var purchaseWay = $('#purchaseWay').attr('checked');
  	if(purchaseWay){
  		purchaseWay='1';
  	}else{
  		purchaseWay='0';
  	}
	  var value = {
  	    	loanId: loanId,
  	    	productCode: $('#productCode').val(),  	    	
  	    	realName: $('#realName').val(),      	    	
  	    	mobile: $('#mobile').val(),
  	    	houseAddress: $('#houseAddress').val(),
  	    	liveCommunity: $('#liveCommunity').val(),
  	    	//houseNo:$("#houseNo").val(),
  	    	addressText:$('#addressText').text(),
  	    	coveredArea: parseFloat($("#coveredArea").val()),
  	    	purchaseDate: $("#purchaseDate").val(),
  	    	purchaseWay: purchaseWay,      	    	
  	    	maxCreditAmount: parseFloat($("#maxCreditAmount").val()),
  	    	loanUsage:$('#loanUsage').val(),
  	    	applyAmount: parseFloat($("#applyAmount").val()),
  	    	loanPeriod: parseInt($('#loanPeriod').val()),
  	    	refferee:$('#refferee').val()
  	    };
	 var  realName = value.realName;
  	 if(realName=="" || realName==null){
  	       HHN.popup('请填写真实姓名', 'warning');
  	       return;
  	  }
  	  if(!HHN.checkChinese(realName) || realName.length>10){
  	       HHN.popup('请填写长度不超过10位中文姓名', 'warning');
  	       return;
  	  }
  	 if(!value.mobile){
        HHN.popup('请填写手机号码', 'warning');
        return;
     }else{
    	   if(!HHN.checkPhone(value.mobile)){
    	        HHN.popup('请填写正确的手机号码', 'warning');
    	        return;
    	   }
    	  var msg = checkMobile(value.mobile);//校验是否存在用户表中
	      if(msg!="" && msg!==null){
	    	  HHN.popup(msg, 'warning');
		      return;  
	      }
     }
  
    if(!value.houseAddress){
    	HHN.popup('请选择住址', 'warning');
        return;
     }
    var liveCommunity=value.liveCommunity;
    if(liveCommunity=="" || liveCommunity==null){
    	HHN.popup('请填写居住小区', 'warning');
        return;
    }
    if(HHN.checkAllLetter(liveCommunity) || HHN.checkAllSign(liveCommunity) || HHN.checkAllDigit(liveCommunity) || liveCommunity.length>50){
        HHN.popup('请填写长度不超过50的居住小区信息', 'warning');
        return;
    }
//    var houseNo=value.houseNo;
//    if(houseNo=="" || houseNo==null){
//    	HHN.popup('请填写房号', 'warning');
//        return;
//    }
//    if(HHN.checkAllLetter(houseNo) || HHN.checkAllSign(houseNo)){
//        HHN.popup('请填写正确格式的房号信息', 'warning');
//        return;
//    }
//    if((houseNo.toString()).length>25){
//        HHN.popup('请填写长度不超过25的房号信息', 'warning');
//        return;
//    }
    if(!value.purchaseDate){
        HHN.popup('请选择购房时间', 'warning');
        return;
    }
    if(!value.coveredArea){
        HHN.popup('请填写购房面积', 'warning');
        return;
    }
    if( !HHN.checkTowDigit(value.coveredArea) || value.coveredArea>1000 || value.coveredArea<5){
        HHN.popup('请填写5~1000之间最多两位小数的购房面积', 'warning');
        return;
    }
    var maxCreditAmount=value.maxCreditAmount;
    if(maxCreditAmount==null || isNaN(maxCreditAmount) || maxCreditAmount.toString()==""){   	    	
        HHN.popup('请填写单张信用卡最高额度', 'warning');
        return;
    }
    if( !HHN.checkTowDigit(maxCreditAmount)  || maxCreditAmount>1000000 || maxCreditAmount<0){
        HHN.popup('请填写0~100万之间最多两位小数的单张信用卡最高额度', 'warning');
        return;
    }
    var loanUsage=value.loanUsage;
    if(loanUsage=="" || loanUsage==null){
    	HHN.popup('请填写贷款用途', 'warning');
        return;
    }
    if(HHN.checkAllLetter(loanUsage) || HHN.checkAllSign(loanUsage) || HHN.checkAllDigit(loanUsage) || loanUsage.length>50){
        HHN.popup('请填写长度不超过50的贷款用途', 'warning');
        return;
    }
    if(!value.applyAmount || isNaN(value.applyAmount)){   	    	
        HHN.popup('请填写贷款金额', 'warning');
        return;
    }
    if(value.applyAmount%100!=0 || value.applyAmount<10000 || value.applyAmount>1000000 || !value.applyAmount){
    	HHN.popup('请输入1万到100万且是100整数倍的贷款金额', 'warning');
        return;
    }
    var loanPeriod=value.loanPeriod;
    if(loanPeriod==null || isNaN(loanPeriod) || loanPeriod.toString()==""){   	    	
        HHN.popup('请填写贷款期限', 'warning');
        return;
    }
    if( !HHN.checkAllDigit(loanPeriod)  || loanPeriod>36 || loanPeriod<1){
        HHN.popup('请填写1~36之间整数的贷款期限', 'warning');
        return;
    }
    if(value.refferee){//推荐手机号不为空时校验
	      if(!HHN.checkPhone(value.refferee)){//校验手机号格式是否正确
	         HHN.popup('请填写正确的推荐人手机号', 'warning');
	         return;
	      }
	      var msg = checkRefferee(value.refferee);//校验是否存在，与注册人手机号是否一致
	      if(msg!="" && msg!==null){
	    	  HHN.popup(msg, 'warning');
		      return;  
	      }
    }
	$.post(globalConfig.basePath+"/order/saveOrderInfo.do",value,function(data) {
    	if(data.success && data.resultCode == '0'){
			window.location.href=globalConfig.basePath+"/loanPersonalCenter/toLoanSchedule.do?loanId="+data.model;
	    }else{
			HHN.popup(data.resultMessage);
	    }
	},"json");
  });
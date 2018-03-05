   $(function(){        
     /*$("#educationRadio li").each(function(index){
			if($("#highestEdu").val()==$(this).attr("data-value")){
				$("#educationRadio li").removeClass('on');
				$(this).addClass('on');
			}					
		});*/
  });
   
  /*var educationRadio = $('#educationRadio li');
    educationRadio.on('click', function() {
        educationRadio.removeClass('on');
        $(this).addClass('on');
    });*/
    
	var address =$('#addressInput').val();    	
	 if(address !=null && address !=""){
		 address = address.split(',');
	  }else{
		  address='';
   	  }   
    var myDistrit = new DistritSelector({
            data: district,
            value: address,
            text: $('#addressText'),
            input: $('#addressInput'),
            callback: function(code, text) {
                if (!code) return;
                this.text.text(text.join('-'));
                this.input.val(code.join(','));
            }
        });
    
        $('#address').on('click', function() {
            myDistrit.show();
        });  
        
	    $('#showSettleDetail').on('click', function() {
	    	var param={
	    			loanAmount:parseInt($("#oApplyAmount").text()),
	    			loanPeriod:parseInt($("#oLoanPeriod").text()),
	    			annualRate:parseFloat($("#annualRate").val()),
	    			settleWay:$("#settleWay").val()
	    			};	    	
		    $.post(globalConfig.basePath+"/repayment/getSettleDetailList.do",param,function(data) {
		    	$("#buySlide").html(data);
        		$("#buySlide").addClass('show');
        		$('#closeBuySlide').on('click', function(){
        		$("#buySlide").removeClass('show');
    			});
			},"html");
	    });
	    
	    function checkIdNo(){
	    	var param={idNo:$("#idNo").val()};
        	if(!param.idNo){
        		personIdnoFalg = false;
        		personIdnoMsg="请填写身份证";
    	        HHN.popup('请填写身份证', 'warning');
    	        return false;
    	    } 
    	    if(!param.idNo || !HHN.checkIdNo(param.idNo)){
    	    	personIdnoFalg = false;
    	    	personIdnoMsg="请填写正确的身份证";
    	        HHN.popup('请填写正确的身份证', 'warning');
    	        return false;
    	    }
    	    
    	    var callRet= false;
    	    
    	    $.ajax({
    	          type:"POST",  
    	          url:globalConfig.basePath+"/order/isExistsByIdNo.do",  
    	          data:param,
    	          dataType:"json",  
    	          async:false,
    	          success:function(data){
    	        	  if(data.success && data.resultCode == '0'){
    	        		    callRet = true;
    	    	    		return true;	
    	    			}else{
    	    				callRet = false;
    	    				HHN.popup(data.resultMessage);
    	        	        return false;
    	    			}
    	          }
   	        });
    	    return callRet;
	    }
//    	    $.post(globalConfig.basePath+"/order/isExistsByIdNo.do",async:false,param,function(data) {
//    	    	if(data.success && data.resultCode == '0'){
//    	    		personIdnoFalg = true;
//    	    		//return msg;	
//    			}else{
//    				personIdnoFalg = false;
//    				personIdnoMsg=data.resultMessage;
//    				HHN.popup(data.resultMessage);
//        	        //return msg;
//    			}	
//    		},"json");
//	    }
	    
        $('#idNo').on('change', function() {
        	 checkIdNo();
        });  
        
        $('#modifyApplyProduct').on('click', function(){
        	var param = {
        			productId:$('#productId').val(),
        			applyAmount:parseInt($("#oApplyAmount").text()),
	    			loanPeriod:parseInt($("#oLoanPeriod").text()),
        			selectedSchemeCode:$("#selectedSchemeCode").val(),
        			selectedLoanType:$("#selectedLoanType").val()
        			};
        	$.post(globalConfig.basePath+"/product/getProudctSchemeList.do",param,function(data) {
        		$("#buySlide").html(data);
        		$("#buySlide").addClass('show');
        		var productSchemeSelector = new ProductSchemeSelector({loanPeriod:'12个月' , confirmBtn :'updateProductBtn'});
        		$("#productRadio li").each(function(index){
					if($("#schemeId").val()==$(this).attr("data-value")){
						$("#productRadio li").removeClass('on');
						$(this).addClass('on');
					}					
				});
        		//将界面的期限加上‘个月’单位
        		var periodValue = $("#loanPeriod").val();
        		$("#loanPeriod").val(periodValue+'个月');
        		//************************增加新元素的事件绑定  
        		$('#updateProductBtn').on('click', function(){
        		    
        			//getProudctValData
        			var value = getProudctValData();
        			$("#settleWay").val(value.settleWay);
    	    		$("#annualRate").val(value.annualRate);
    	    		value.loanId = $('#loanId').val();
    	    		value.prodId = $('#productId').val();
        		    	   
        		    $("#buySlide").removeClass('show');
        			 $.post(globalConfig.basePath+"/order/toUpdateLoanOrderInfo.do",value,function(data) {
        	    	    	if(data.success && data.resultCode == '0'){
        	    	    		$("#oApplyAmount").text(data.model.applyAmount);
        	    	    		$("#oLoanTypeName").text(data.model.loanTypeName);
        	    	    		$("#oProductName").text(data.model.productName);
        	    	    		$("#oLoanPeriod").text(data.model.loanPeriod);
        	    	    		$("#selectedSchemeCode").val(data.model.code);
        	    	    		$("#selectedLoanType").val(data.model.loanType);
        	    	    		$("#schemeId").val(data.model.schemeId);
        	    			}else{
        	    				HHN.popup(data.resultMessage);
        	    			}
        	    		},"json");
        		});   
        		
        		//************************
    		},"html");
        });
        
        var loanProtocolCount = 0;
        $("#loanProtocol").click(function(){
        	loanProtocolCount = loanProtocolCount+1;
        	//alert("loanProtocol点击的次数："+loanProtocolCount);
        	if(loanProtocolCount%2==0){
        		$(this).attr("checked","checked");
        	}else{
        		$(this).attr("checked","");
        	}
        });
        
        var serviceProtocolCount = 0;
        $("#serviceProtocol").click(function(){
        	serviceProtocolCount = serviceProtocolCount+1;
        	//alert("serviceProtocol点击的次数："+serviceProtocolCount);
        	if(serviceProtocolCount%2==0){
        		$(this).attr("checked","checked");
        	}else{
        		$(this).attr("checked","");
        	}
        });
        
        $('#applyBtn').on('click', function(){
        	//alert("选中的学历为："+$('#educationRadio').val());
    	    var value = {
    	    	loanId:$("#loanId").val(),
    	        realName:$("#realName").val(),
    	        idNo:$("#idNo").val(),
    	        monthIncome: parseFloat($("#monthIncome").val()),
    	        mobile:$("#mobile").val(),
    	        addressInput:$("#addressInput").val(),
    	        addressDetail:$("#addressDetail").val(),
    	        familyMobile1:$("#familyMobile1").val(),
    	        familyMobile2:$("#familyMobile2").val(),
    	        friendMobile1:$("#friendMobile1").val(),
    	        friendMobile2:$("#friendMobile2").val(),
    	        education:$('#educationRadio').val()
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
    	    
        	if(checkIdNo()==false){
        		return;
        	}
//    	    if(!value.idNo){
//    	        HHN.popup('请填写身份证', 'warning');
//    	        return;
//    	    } 
//    	    if(!HHN.checkIdNo(value.idNo)){
//    	        HHN.popup('请填写正确的身份证', 'warning');
//    	        return;
//    	    }
    	    if(!value.monthIncome){
    	        HHN.popup('请填写收入', 'warning');
    	        return;
    	    }
    	    if(!HHN.checkDigit(value.monthIncome) || value.monthIncome <2000 || value.monthIncome>1000000){
    	        HHN.popup('请填写2000~100万之间最多两位小数的收入', 'warning');
    	        return;
    	    }
    	    if(!value.addressInput){
    	    	HHN.popup('请选择住址位置', 'warning');
     	        return;
    	    }
    	    var addressDetail=value.addressDetail;
    	    if(addressDetail=="" || addressDetail==null){
    	    	HHN.popup('请填写住址详细信息', 'warning');
     	        return;
    	    }
    	    if(HHN.checkAllLetter(addressDetail) || HHN.checkAllSign(addressDetail) || HHN.checkAllDigit(addressDetail) || addressDetail.length>50){
    	        HHN.popup('请填写长度不超过50的住址详细信息', 'warning');
    	        return;
    	    }
    	    if(!value.familyMobile1){
    	        HHN.popup('请填写家人1手机号', 'warning');
    	        return;
    	    }
    	    if(!HHN.checkPhone(value.familyMobile1)){
    	        HHN.popup('请填写正确的家人1手机号', 'warning');
    	        return;
    	    }
    	    if(!value.familyMobile2){
    	        HHN.popup('请填写家人2手机号', 'warning');
    	        return;
    	    }
    	    if(!HHN.checkPhone(value.familyMobile2)){
    	        HHN.popup('请填写正确的家人2手机号', 'warning');
    	        return;
    	    }
    	    if(!value.friendMobile1){
    	        HHN.popup('请填写朋友1手机号', 'warning');
    	        return;
    	    }
    	    if(!HHN.checkPhone(value.friendMobile1)){
    	        HHN.popup('请填写正确的朋友1手机号', 'warning');
    	        return;
    	    }
    	    if(!value.friendMobile2){
    	        HHN.popup('请填写朋友2手机号', 'warning');
    	        return;
    	    }
    	    if(!HHN.checkPhone(value.friendMobile2)){
    	        HHN.popup('请填写正确的朋友2手机号', 'warning');
    	        return;
    	    }
    	    if(value.familyMobile1==value.familyMobile2 || value.familyMobile1==value.friendMobile1 || value.familyMobile1==value.friendMobile2
    	    		|| value.familyMobile2==value.friendMobile1 || value.familyMobile2==value.friendMobile2 || value.friendMobile1==value.friendMobile2){
    	    	HHN.popup('请不要填写重复的手机号', 'warning');
    	        return;
    	    }
    	    if($("#loanProtocol").attr("checked")!="checked"){
    	    	HHN.popup('请同意《合和年在线协议条款》', 'warning');
    	        return;
    	    }
    	    if($("#serviceProtocol").attr("checked")!="checked"){
    	    	HHN.popup('请同意《借款咨询服务协议》', 'warning');
    	        return;
    	    }
    	    if(loanProtocolCount%2!=0){
    	    	HHN.popup('请同意《合和年在线协议条款》', 'warning');
    	        return;
    	    }
    	    if(serviceProtocolCount%2!=0){
    	    	HHN.popup('请同意《借款咨询服务协议》', 'warning');
    	        return;
    	    }
    	    $.post(globalConfig.basePath+"/order/saveLoanPersonInfo.do",value,function(data) {
    	    	if(data.success && data.resultCode == '0'){
    				window.location.href=globalConfig.basePath+"/order/toApplySuccess.do?loanId="+data.model;
    			}else{
    				HHN.popup(data.resultMessage);
    			}
    		},"json");
    	    
    	});
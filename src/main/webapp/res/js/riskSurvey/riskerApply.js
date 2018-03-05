$(function(){   
	    //初始化加载信息
		var image = "<img src='../res/v2/images/ok.png' alt=''>";
	    if("T"==$('#personalStatus').val()){
	    	$("#isPersonInfoOk").html(image);
	    	$('#riskerPersonalInfo').addClass('done');
	    }
	    if("T"==$('#addressStatus').val()){
	    	$("#isAddressOk").html(image);
	    	$('#riskerAddress').addClass('done');
	    }
	    if("T"==$('#materialStatus').val()){
	    	$("#isSummaryInfoOk").html(image);
	    	$('#riskerMaterial').addClass('done');
	    }
	    judgeShowSubmitBtn(); 
	    controllShowNavBtn();
   });
  
  function goMain(){
	  window.location.href=globalConfig.basePath+"/riskSurvey/toRiskerApply.do";  
  }


  function judgeShowSubmitBtn(){
	  if($('#riskerPersonalInfo').hasClass('done') && $('#riskerAddress').hasClass('done') && $('#riskerMaterial').hasClass('done')){
		  $('#submitBtn').show();
	  }else{
		  $('#submitBtn').hide();
	  }
  }
  function controllShowNavBtn(param){
	  if('riskerPersonalInfo'==param){
		  if(!$('#riskerPersonalInfo').hasClass('light')){
			  $('#riskerPersonalInfo').addClass('light');
		  }
	  }else{
		  if($('#riskerPersonalInfo').hasClass('light')){
			  $('#riskerPersonalInfo').removeClass('light');
		  }
	  }
	  if('riskerAddress'==param){
		  if(!$('#riskerAddress').hasClass('light')){
			  $('#riskerAddress').addClass('light');
		  }
	  }else{
		  if($('#riskerAddress').hasClass('light')){
			  $('#riskerAddress').removeClass('light');
		  }
	  }
	  if('riskerMaterial'==param){
		  if(!$('#riskerMaterial').hasClass('light')){
			  $('#riskerMaterial').addClass('light');
		  }
	  }else{
		  if($('#riskerMaterial').hasClass('light')){
			  $('#riskerMaterial').removeClass('light');
		  }
	  }
	    
  }
  
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
  //风控宝个人信息处理
   $('#riskerPersonalInfo').on('click',function(){
	   controllShowNavBtn('riskerPersonalInfo');
	   $("#needToHidden").hide();
	   $("#goBack").show();
	   $("#submitBtn").hide();
	   $.post(globalConfig.basePath+"/riskSurvey/getRiskerPersonalInfo.do",null,function(data) {
	    	$('#riskerContent').html(data);//加载个人信息div页面片段	
	    	$("#goBackToFront").hide();
	    	$('#saveRiskerPersonalInfo').on('click',function(){//个人信息保存按钮绑定事件
	    		var  value = {
	    				      realName:$('#realName').val(),
	    				      mobile:$('#mobile').val(),
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
	    	     }
	    	     if(!HHN.checkPhone(value.mobile)){
	    	        HHN.popup('请填写正确的手机号码', 'warning');
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

	    		$.post(globalConfig.basePath+"/riskSurvey/saveRiskerPersonalInfo.do",value,function(data) {
	    	    	if(data.success && data.resultCode == '0'){
	    	    		controllShowNavBtn();
	    	    		$('#riskerContent').html("");
	    	    		$('#riskerPersonalInfo').addClass('done');
	    	    		$('#personalStatus').val('T');
	    	    		judgeShowSubmitBtn();
	    	    		goMain();
	    	    		HHN.popup(data.resultMessage);
	    		    }else{
	    				HHN.popup(data.resultMessage);
	    		    }
	    		},"json");
	    	});
		},"html");
   });
  //风控宝住址信息处理
   $('#riskerAddress').on('click',function(){
	   $("#needToHidden").hide();
	   $("#goBack").show();
	   $("#submitBtn").hide();
	   controllShowNavBtn('riskerAddress');
	   $.post(globalConfig.basePath+"/riskSurvey/getRiskerHouseInfo.do",null,function(data) {
/* 		   if($('#riskerAddress').hasClass('done')){
			    $('#riskerAddress').removeClass('done');
			    $('#addressStatus').val('F');
			    $('#submitBtn').hide();
		    }	 */
	    	$('#riskerContent').html(data);
	    	$("#goBackToFront").hide();
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
	    	$('#saveRiskerAddress').on('click',function(){//地址信息保存按钮绑定事件
	    	var  value = {
		    			houseAddress:$('#houseAddress').val(),
		    			liveCommunity:$('#liveCommunity').val(),
		    			//houseNo:$('#houseNo').val(),
		    			addressText:$('#addressText').text()
	                    };
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
//	    	    var houseNo=value.houseNo;
//	    	    if(houseNo=="" || houseNo==null){
//	    	    	HHN.popup('请填写房号', 'warning');
//	    	        return;
//	    	    }
//	    	    if(HHN.checkAllLetter(houseNo) || HHN.checkAllSign(houseNo)){
//	    	        HHN.popup('请填写正确格式的房号信息', 'warning');
//	    	        return;
//	    	    }
//	    	    if(houseNo.length>25){
//	    	        HHN.popup('请填写长度不超过25的房号信息', 'warning');
//	    	        return;
//	    	    }
	    	    $.post(globalConfig.basePath+"/riskSurvey/saveRiskerHouseInfo.do",value,function(data) {
	    	    	if(data.success && data.resultCode == '0'){
	    	    		controllShowNavBtn();
	    	    		$('#riskerContent').html("");
	    	    		$('#riskerAddress').addClass('done');
	    	    		$('#addressStatus').val('T');
	    	    		judgeShowSubmitBtn();
	    	    		goMain();
	    	    		HHN.popup(data.resultMessage);
	    		    }else{
	    				HHN.popup(data.resultMessage);
	    		    }
	    		},"json");
	       }); 	    
		},"html");
   });
   //风控宝资料信息处理
   var resultBase64 = new Array(3);
   function readFile(file, target,index,zipImage) {
       var reader = new FileReader();
       reader.onload = function(e) {
           resultBase64[index] = zipImage;
           target.style.backgroundImage = 'url(' + zipImage + ')';
           target.style.backgroundSize = 'cover';
           target.style.webkitBackgroundSize = 'cover';
       };
       reader.readAsDataURL(file);
   }
   
   $('#riskerMaterial').on('click',function(){
	   $("#needToHidden").hide();
	   $("#goBack").show();
	   $("#submitBtn").hide();
	   controllShowNavBtn('riskerMaterial');
	   $.post(globalConfig.basePath+"/riskSurvey/toRiskerMaterial.do",null,function(data) {	    
	    	$('#riskerContent').html(data);
	    	$("#goBackToFront").hide();
	    	if($('#zmData').val()!=null && $('#zmData').val()!="" && $('#zmData').val()!=undefined){
	    		var zmImgUrl ="background-image:"+ $('#zmData').val()+";background-size: cover;";
		    	$("#zmID").attr("style",zmImgUrl);
	    	}
	    	if($('#fmData').val()!=null && $('#fmData').val()!="" && $('#fmData').val()!=undefined){
	    	    var fmImgUrl ="background-image:"+ $('#fmData').val()+";background-size: cover;";
                $("#fmID").attr("style",fmImgUrl);
	    	}
	    	if($('#zsData').val()!=null && $('#zsData').val()!="" && $('#zsData').val()!=undefined){
		    	var zsImgUrl ="background-image:"+ $('#zsData').val()+";background-size: cover;";
		    	$("#zsID").attr("style",zsImgUrl);
	    	}
	    	//监听文件上传动作
	    	$('input[type="file"]').change(function(e){
	    		var target = $(this).parent()[0];
	    		var that = this;
	    	    var index = $(this).data('index');
	    	    lrz(that.files[0], {
	    	        width: 1024
	    	    })
	    	        .then(function (rst) {
	    	        	readFile(that.files[0], target,index,rst.base64);
	    	        });
	    	});
	    	
	    	$('#saveRiskerMaterial').bind('touchstart', function(){//资料信息保存按钮绑定事件
	    		if(resultBase64==null || resultBase64=="" || resultBase64.length<3){
	    			HHN.popup("请补齐资料文件上传再保存！");
	    			return;
	    		}
	    		var IDCARDZ=resultBase64[0];
	    		var IDCARDF=resultBase64[1];
	    		var IDCARDZS=resultBase64[2];
	    		var zmIDImg = $('#zmID').css('backgroundImage');	    		
	    		if(IDCARDZ==undefined && zmIDImg==null){
	    			HHN.popup("请补齐身份证正面文件上传再保存！");
	    			return;
	    		}
	    		var fmIDImg =  $('#fmID').css('backgroundImage');
	    		if(IDCARDF==undefined  && fmIDImg==null){
	    			HHN.popup("请补齐身份证反面文件上传再保存！");
	    			return;
	    		}
	    		var zsIDImg =  $('#zsID').css('backgroundImage');
	    		if(IDCARDZS==undefined  && zsIDImg==null){
	    			HHN.popup("请补齐手持身份证正面文件上传再保存！");
	    			return;
	    		}
	    		if(IDCARDZ==undefined && zmIDImg!=null){
	    			IDCARDZ=zmIDImg.substring(1+zmIDImg.indexOf('('),zmIDImg.length-1);    			
	    		}
	    		if(IDCARDF==undefined  && fmIDImg!=null){
	    			IDCARDF=fmIDImg.substring(1+fmIDImg.indexOf('('),fmIDImg.length-1);     			
	    		}
	    		if(IDCARDZS==undefined  && zsIDImg!=null){
	    			IDCARDZS=zsIDImg.substring(1+zsIDImg.indexOf('('),zsIDImg.length-1);     
	    		}
	    		var value={'IDCARDZ':IDCARDZ,'IDCARDF':IDCARDF,'IDCARDZS':IDCARDZS};
	    		 $.ajax({
	                    url: globalConfig.basePath+"/riskSurvey/saveRiskerMaterialInfo.do",
	                    type: "post",
	                    data: value,
	                    dataType: "json",
	                    success: function(data) {
	                    	if(data.success && data.resultCode == '0'){
	    	    	    		controllShowNavBtn();
	    	    	    		$('#riskerContent').html("");
	    	    	    		$('#riskerMaterial').addClass('done');
	    	    	    		$('#materialStatus').val('T');
	    	    	    		judgeShowSubmitBtn();
	    	    	    		goMain();
	    	    	    		HHN.popup(data.resultMessage);
	    	    		    }else{
	    	    				HHN.popup(data.resultMessage);
	    	    		    }
	                    },
	                    error: function(XMLHttpRequest, textStatus, errorThrown) {
	                    	HHN.popup("图片上传失败，请控制图片大小！");
	                    },
	                    complete: function(XMLHttpRequest, textStatus) {
	                        this; // 调用本次AJAX请求时传递的options参数
	                    }
	                });
	       }); 	    
		},"html");
   });
   
   $('#riskerApplySubmit').on('click',function(){
	   if(!$('#riskerPersonalInfo').hasClass('done')){
		   HHN.popup("请完善个人信息！");
		   return;
	   }
	   if(!$('#riskerAddress').hasClass('done')){
		   HHN.popup("请完善地址信息！");
		   return;
	   }
	   if(!$('#riskerMaterial').hasClass('done')){
		   HHN.popup("请完善资料信息！");
		   return;
	    }
	   var value={groupCode:'FK'};
	   $.post(globalConfig.basePath+"/riskSurvey/saveGroupUserApply.do",value,function(data) {
	    	if(data.success && data.resultCode == '0'){
	    		 new Modal({
	    	            content:'<div style="color:#4385F5"><h3 style="font-size:16px;">您的申请已经提交</h3><p style="font-size:12px;">稍后工作人员会与您联系</p></div>',
	    	            ok: function(){
                            window.location.href=globalConfig.basePath+"/product/toSmallLoan.do"; //返回首页
	    	            }
	    	      });
		    }else{
				HHN.popup(data.resultMessage);
		    }
		},"json");
   });
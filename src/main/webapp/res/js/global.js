var HHN = HHN || {};
HHN.formatNumber = function(num) {
    num = num + '';
    var rgx = /(\d+)(\d{3})/g;
    while (rgx.test(num)) {
        num = num.replace(rgx, '$1' + ',' + '$2');
    }
    return num;
};

HHN.checkPhone = function(val) {//手机号码
    return /^0?(13|15|18|14|17)[0-9]{9}$/.test($.trim(val));
};
HHN.checkMail = function(val){ //邮箱地址
    return /^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$/.test(val);
};
HHN.checkIdNo = function(val) { //身份证15位和18位
	//return /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$/.test(val) || /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{4}$/.test(val);
   return /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$/.test(val) || /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}(x|X))$/.test(val);
};
HHN.checkRealName = function(val) { //姓名校验
	 return /^[a-zA-Z\u4e00-\u9fa5]+$/.test(val);
};
HHN.checkDigit = function(val) { //纯数字，包括小数点
   return /^\+?[1-9][0-9]*$/.test(val) || /^(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*))$/.test(val); 
};
HHN.checkAllDigit = function(val){
	return /^[0-9]*$/.test(val);
};
HHN.checkAllLetter = function(val){  //纯字母
	return /^[A-Za-z]+$/.test(val);
};
HHN.checkAllSign = function(val){  //纯符号
	return /[^a-zA-Z0-9\u4e00-\u9fa5]+$/.test(val);
};
HHN.checkTowDigit = function(val){  //最多两位小数的数字
	return /^\d+(?:\.\d{1,2})?$/.test(val);
};
HHN.checkChinese = function(val){  //纯汉字
	return /^[\u4e00-\u9fa5]+$/.test(val);
};


HHN.popup = function(content, type, time) {
    return new Modal({
        model: 'popup',
        type: type || 'warning',
        content: content,
        time:time||5000
    });
};


HHN.popupConfirm = function(content,fncCancel, fncOk, type, time, cancelText, okText ) {
    return new Modal({
        model: 'dialog',
        type: type || 'warning',
        cancel: fncCancel,
		ok: fncOk,
        content: content,
        time:time||5000,
        cancelText: cancelText||'取消',
		okText: okText||'确定'
    });
};

HHN.loading = function(content ) {
    return new Modal({
        model: 'loading',
        type: 'warning',
        content: content,
        time:5000
    });
};


HHN.setCookie = function(cname, cvalue, exdays, domain) {
    exdays = exdays || 365;
    var d = new Date();
    d.setTime(d.getTime() + (exdays*24*60*60*1000));
    var expires = "expires="+d.toUTCString();
    var domainInfo = "domain="+domain;
    document.cookie = cname + "=" + cvalue + "; " + expires + "; " + domainInfo;
};
//获取cookie
HHN.getCookie = function(cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for(var i=0; i<ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0)==' ') c = c.substring(1);
        if (c.indexOf(name) != -1) return c.substring(name.length, c.length);
    }
    return "";
};

HHN.clearCookie = function(name) {  
    setCookie(name, "", -1);  
};
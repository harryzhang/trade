 /*  
 * AJAX请求  
 *   
 *url  是传输的目标url 
 *data 是要传输的数据 
 *func 当ajax执行成功之后跳转到自己的函数  
 */  

/*

      ajaxPost("url", "data", function(data) {
          alert(data.name)
      });
*/

function ajaxPost(url, data, func) { //封装ajax的一些常用参数  //data数据可以为空  
      $.ajax({  

            sync: true,  
            type: 'POST',  
            url: url,  
            dataType: 'json',  
            data: data,      
            success: function (data) {  
                  func(data) 
            }  
      });  
}  
  
function ajaxGet(url, data, func) { //封装ajax的一些常用参数  //data数据可以为空  
      $.ajax({  

            sync: true,  
            type: 'GET',  
            url: url,  
            dataType: 'json',  
            data: data,      
            success: function (data) {  
                  func(data); 
            }  
      });  
}

$.ajaxSetup({
      beforeSend: function () {
            //ajax请求之前
      },
      complete: function () {
            //ajax请求完成，不管成功失败
      },
      error: function () {
            //ajax请求失败
      }
});

<%@ page import="com.redpack.common.pay.controller.MD5" %>
<%@ page import="com.redpack.common.pay.controller.MD5Util" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    
    String CharacterEncoding = "UTF-8";
    request.setCharacterEncoding(CharacterEncoding);
    
	String MerNo = request.getParameter("MerNo"); 
	
    String MD5key = "12345678";
	
    String BillNo = request.getParameter("BillNo");	
   
    String Amount = request.getParameter("Amount");	
    	
    String Succeed = request.getParameter("Succeed");
    
    String Result  = request.getParameter("Result"); 
	
    String MD5info = request.getParameter("MD5info");
    
    String MerRemark  = request.getParameter("MerRemark"); 
    
   	String md5str;
   	MD5Util md5util = new MD5Util();
   	
	md5str = md5util.signMap(new String[]{MerNo,BillNo,Amount,String.valueOf(Succeed)}, MD5key, "RES");
    
   

    

	if(MD5info.equals(md5str)){
		if(Succeed.equals("88")){
		
				System.out.println(Result);
		}else {
		
				System.out.println(Result);
		}
		
	}
%>


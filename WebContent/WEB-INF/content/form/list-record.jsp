<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">  
    <title>查看自定义表单</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="<%=path%>/js/ckeditor/ckeditor.js"></script>
	<script type="text/javascript" src="<%=path%>/validation/presetValidators.js"></script>
    <script type="text/javascript" src="<%=path%>/validation/validation.js"></script>
    <script type="text/javascript" src="<%=path%>/jquery/jquery-1.4.2.min.js"></script>
	
  </head>
  
  <body> 
   ${formname}
  <br/>
  
        <form action="form/add-record.action" enctype="application/x-www-form-urlencoded" method="post">
      
        <s:hidden name="tableid"></s:hidden>
            ${textArea}
          
          <input type="submit" value="提交" >
         </form> 
     

  </body>

</html>

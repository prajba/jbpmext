<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

    
    <title>表单数据展现</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>


<s:iterator value="tablecontent" >   
    <s:iterator value="top"  >   
        <s:property value="key"/>    <s:property value="[0].value"/>   
    </s:iterator> 
     <a  href="view-content.action?rid=${top.rid}&amp;tableid=${tableid}">查看</a>  
     <a href="list-task.action?recordid=${top.rid}&tableid=${tableid}">流程操作</a>  

    <br/>   
</s:iterator>

  </body>
</html>

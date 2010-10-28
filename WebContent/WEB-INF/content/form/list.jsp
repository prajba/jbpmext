<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>test.jsp</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  <body>
    自定义表单列表
    
 <br/>
 <br/>
  <p><a href="form/create.action">新增自定义表单</a></p>
 <br/>
 <br/>
 

 <br/>
 <br/>
 <br/>
  <table>
      <tr>
	       <td>表单中文名称</td>
	       <td>表单描述</td>
	       <td>作者</td>
	       <td>操作</td>
      </tr>
      
       <s:iterator value="alltablelist" var="p">
              <tr>
                <td align="center" bgcolor="#FFFFFF">${p.tableChinesename}</td>
                <td align="center" bgcolor="#FFFFFF">${p.tableDescript}</td>
                <td align="center" bgcolor="#FFFFFF">${p.tableCreatname}</td>
                <td>
                  
                   <a href="form/delete-table.action?tableid=${p.tableId}">删除</a>
                   <a href="form/view-table.action?tableid=${p.tableId}">预览</a>
                   <a href="form/list-record.action?tableid=${p.tableId}">新增记录</a>
                   <a href="form/query-table.action?tableid=${p.tableId}">查询该表单所有记录</a>
                   <a href="flow/edit.action?tableid=${p.tableId}">流程图设计</a>
                </td>
              </tr>    
       </s:iterator>  
  </table>
  </body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>自定义表单实现</title>
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
    <a href="user/UserAction.action">点击此处——调用UserAction.action的默认方法-execute</a><br />
    <a href="user/UserAction_test.action">点击此处--调用UserAction.action的test方法</a><br />
    <a href="user/userAction.action">调用Spring管理的UserAction.action的test方法</a><br /><br />
    <a href="user/toRegister.action">注册</a><br />
    <a href="user/fckeditortest.action">自定义表单演示程序</a><br />
    
     <a href="user/tableall.action">表单列表</a><br />
  </body>
</html>

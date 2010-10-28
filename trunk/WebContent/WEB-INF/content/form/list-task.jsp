<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*,org.jbpm.api.*"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>
<form action="${ctx}/form/list-task.action">
<input type="hidden" name="tableid" value="${tableid}"/>
<input type="hidden" name="recordid" value="${recordid}"/>
用户：<input type="text"
	name="userName" value="${userName}"></input> <input type="submit"
	value="查询" /></form>
<h1 collapsible="true">任务列表</h1>

<table border="1" bordercolor="black">
	<tr>
		<td>用户</td>
		<td>名称</td>
		<td>描述</td>
		<td>创建时间</td>
		<td>过期时间</td>
		<td></td>
	</tr>
	<s:iterator value="taskList">
		<tr>
			<td>${assignee}</td>
			<td>${name}</td>
			<td>${description}</td>
			<td>${createTime}</td>
			<td>${duedate}</td>  
			<td><a href="${ctx}/form/task.action?tableid=${tableid}&recordid=${recordid}&taskId=${id}">操作</a></td>
		</tr>
	</s:iterator>
</table>
</body>
</html>
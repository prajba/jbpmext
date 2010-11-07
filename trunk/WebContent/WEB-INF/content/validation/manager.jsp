<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ include file="/common/taglibs.jsp"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><s:text name="val.manager.title"/></title>
<%@ include file="/common/styles.jsp" %>
<%@ include file="/common/scripts.jsp" %>
<script type="text/javascript" src="${ctx}/js/user-validation/valman.js"></script>
<script type="text/javascript" src="${ctx}/js/user-validation/messages.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/styles/validation.css"/>
</head>
<body><s:property value="validators"/>
<div>
<table id="vallist">
<thead>
<tr>
	<th width="200"></th>
	<th width="100"></th>
	<th width="300"></th>
</tr>
</thead>
<tbody>
<s:iterator value="validators">
<tr>
	<td><s:property value="name"/></td>
	<td><s:property value="parameters.size()"/></td>
	<td><s:property value="remarks"/></td>
</tr>
</s:iterator>
</tbody>
</table>
</div>
</body>
</html>
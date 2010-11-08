<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ include file="/common/taglibs.jsp"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><s:text name="val.editer.title"/></title>
<%@ include file="/common/styles.jsp" %>
<%@ include file="/common/scripts.jsp" %>
<script type="text/javascript" src="${ctx}/js/editor-dialog.js"></script>
<script type="text/javascript" src="${ctx}/js/edt-dlg-msgs.js"></script>
<script type="text/javascript" src="${ctx}/js/user-validation/editor.js"></script>
</head>
<body>
<div id="editor">
<table width="380" cellpadding="2" cellspacing="3">
<tr height="25">
	<td width="100"><s:text name="val.label.name"/></td>
	<td><input type="text" class="filled" id="valname"/></td>
</tr>
<tr height="320">
	<td><s:text name="val.label.snippet"/></td>
	<td><textarea id="valsnippet" class="filled"></textarea></td>
</tr>
<tr height="40">
	<td><s:text name="val.label.remarks"/></td>
	<td><textarea id="valremarks" class="filled"></textarea></td>
</tr>
</table>
</div>
</body>
</html>

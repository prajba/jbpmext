<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ include file="/common/taglibs.jsp"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><s:text name="cat.editer.title"/></title>
<%@ include file="/common/styles.jsp" %>
<%@ include file="/common/scripts.jsp" %>
<script type="text/javascript" src="${ctx}/js/editor-dialog.js"></script>
<script type="text/javascript" src="${ctx}/js/edt-dlg-msgs.js"></script>
<script type="text/javascript" src="${ctx}/js/dict/cat-editor.js"></script>
</head>
<body>
<div id="editor">
<table width="380" cellpadding="2" cellspacing="3">
<tr height="25">
	<td width="100"><s:text name="cat.label.name"/></td>
	<td><input type="text" class="filled" id="displayName"/></td>
</tr>
<tr height="25">
	<td><s:text name="cat.label.valuetype"/></td>
	<td><select class="filled" id="valueType">
		<option value="int"><s:text name="cat.valuetype.int"/></option>
		<option value="string"><s:text name="cat.valuetype.string"/></option>
	</select></td>
</tr>
<tr height="25">
	<td><s:text name="cat.label.tablename"/></td>
	<td>
		<table class="filled" cellspacing="0" cellpadding="0"><tr>
		<td width="10">dict_</td>
		<td><input type="text" id="tableName" class="filled"/></td></tr></table>
	</td>
</tr>
<tr height="40">
	<td><s:text name="cat.label.remarks"/></td>
	<td><textarea id="remarks" class="filled"></textarea></td>
</tr>
</table>
</div>
</body>
</html>

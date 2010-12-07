<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ include file="/common/taglibs.jsp"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><s:text name="entry.editer.title"/></title>
<%@ include file="/common/styles.jsp" %>
<%@ include file="/common/scripts.jsp" %>
<script type="text/javascript" src="${ctx}/js/editor-dialog.js"></script>
<script type="text/javascript" src="${ctx}/js/edt-dlg-msgs.js"></script>
<script type="text/javascript" src="${ctx}/js/dict/entry-editor.js"></script>
</head>
<body>
<div id="editor">
<table width="380" cellpadding="2" cellspacing="3">
<tr height="25">
	<td width="100"><s:text name="entry.label.key"/></td>
	<td><input type="text" class="filled" id="key"/></td>
</tr>
<tr height="25">
	<td><s:text name="entry.label.value"/></td>
	<td><input type="text" class="filled" id="value"/></td>
</tr>
</table>
</div>
</body>
</html>

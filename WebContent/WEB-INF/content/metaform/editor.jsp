<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ include file="/common/taglibs.jsp"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><s:text name="metaform.editer.title"/></title>
<%@ include file="/common/styles.jsp" %>
<%@ include file="/common/scripts.jsp" %>
<script type="text/javascript" src="${ctx}/js/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="${ctx}/js/ckeditor/adapters/jquery.js"></script>
<script type="text/javascript" src="${ctx}/js/editor-dialog.js"></script>
<script type="text/javascript" src="${ctx}/js/edt-dlg-msgs.js"></script>
<script type="text/javascript" src="${ctx}/js/metaform/editor.js"></script>
</head>
<body>
<div class="inner-layout">
<div region="north">
	<table width="640" cellspacing="5"><tr>
		<td valign="top" width="80"><label for="formName"><s:text name="metaform.label.formname"/></td><td valign="top"><input type="text" id="formName" class="filled"/></td>
	</tr><tr>
		<td valign="top"><label for="tableName"><s:text name="metaform.label.tablename"/></td><td valign="top"><input type="text" id="tableName" class="filled"/></td>
	</tr><tr>
		<td valign="top" height="60"><label for="remarks"><s:text name="metaform.label.remarks"/></td><td valign="top"><textarea class="filled" id="remarks"></textarea></td>
	</tr></table>
</div>
<div region="center">
	<textarea id="formHtml"></textarea>
</div>
</div>
</body>
</html>

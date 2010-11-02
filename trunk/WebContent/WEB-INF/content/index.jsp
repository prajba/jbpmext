<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ include file="/common/taglibs.jsp"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><s:text name="index.title"/></title>
<%@ include file="/common/styles.jsp" %>
<%@ include file="/common/scripts.jsp" %>
<script type="text/javascript" src="${ctx}/js/index.js"></script>
</head>

<body>
<div id="northContent"><s:text name="index.projname"></s:text></div>
<div id="westContent">
</div>
<div id="workareaPlaceholder">
	<div id="workareaContent"><s:text name="index.welcome"></s:text></div>
</div>
<div id="southContent"><s:text name="index.footer.content"></s:text></div>
</body>
</html>

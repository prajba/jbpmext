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
<div id="northContent"><%@ include file="/WEB-INF/content/main/banner.jsp" %></div>
<div id="westContent"><%@ include file="/WEB-INF/content/main/menu.jsp" %></div>
<div id="workareaContent"><%@ include file="/WEB-INF/content/main/desktop.jsp" %></div>
<div id="southContent"><%@ include file="/WEB-INF/content/main/footer.jsp" %></div>
</body>
</html>

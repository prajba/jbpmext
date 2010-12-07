<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ include file="/common/taglibs.jsp"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><s:text name="dict.manager.title"/></title>
<%@ include file="/common/styles.jsp" %>
<%@ include file="/common/scripts.jsp" %>
<script type="text/javascript" src="${ctx}/js/dict/messages.js"></script>
<script type="text/javascript" src="${ctx}/js/dict/catManager.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/styles/dict.css"/>
</head>
<body>
<div>
<span class="hidden" id="catId"><s:property value="cat.id"/></span>
<span class="hidden" id="catName"><s:property value="cat.displayName"/></span>
<span class="hidden" id="valType"><s:property value="cat.valueType"/></span>
<div id="entrylist">
</div>
</div>
</body>
</html>
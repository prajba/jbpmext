<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ include file="/common/taglibs.jsp"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><s:text name="bizproc.editer.title"/></title>
<%@ include file="/common/styles.jsp" %>
<%@ include file="/common/scripts.jsp" %>
<script type="text/javascript" src="${ctx}/js/bizproc/editor.js"></script>
</head>
<body class="easyui-layout">
<div id="myflow_tools" region="west" title="工具集"
	style="background-color: #fff; width: 60px; cursor: default;">
<a href="javascript:void(0);" iconCls="icon-save" plain="true"
	class="node easyui-linkbutton" id="myflow_save">保存</a>
<hr />
<a href="javascript:void(0);" iconCls="icon-select" plain="true"
	class="node selectable easyui-linkbutton" id="pointer">选择</a>
<a href="javascript:void(0);" iconCls="icon-transit" plain="true"
	class="node selectable easyui-linkbutton" id="path">转换</a>
<hr />
<div class="node state" id="start" type="start"><img
	src="${ctx}/js/myflow/img/16/start_event_empty.png" />&nbsp;&nbsp;开始</div>

<div class="node state" id="task" type="task"><img
	src="${ctx}/js/myflow/img/16/task_empty.png" />&nbsp;&nbsp;任务</div>
<div class="node state" id="decision" type="decision"><img
    src="${ctx}/js/myflow/img/16/gateway_parallel.png" />&nbsp;&nbsp;决定</div>
	
<div class="node state" id="fork" type="fork"><img
	src="${ctx}/js/myflow/img/16/gateway_parallel.png" />&nbsp;&nbsp;分支</div>
<div class="node state" id="join" type="join"><img
	src="${ctx}/js/myflow/img/16/gateway_parallel.png" />&nbsp;&nbsp;合并</div>
<div class="node state" id="end" type="end"><img
	src="${ctx}/js/myflow/img/16/end_event_terminate.png" />&nbsp;&nbsp;结束</div>
<div class="node state" id="end-cancel" type="end-cancel"><img
	src="${ctx}/js/myflow/img/16/end_event_cancel.png" />&nbsp;&nbsp;取消</div>
<div class="node state" id="end-error" type="end-error"><img
	src="${ctx}/js/myflow/img/16/end_event_error.png" />&nbsp;&nbsp;错误</div>
</div>

<div region="east" title="属性" split="true"
	style="background-color: #fff; width: 240px; padding: 3px;">
<div id="myflow_props">
<table border="0" width="100%" cellpadding="0" cellspacing="0" class="proped">
</table>
</div></div>
<div id="designer" region="center"></div>
</body>
</html>
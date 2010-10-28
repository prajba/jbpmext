<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css"
	href="${ctx}/js/myflow/lib/jquery-ui-1.8.4.custom/css/smoothness/jquery-ui-1.8.4.custom.css"
	rel="stylesheet" />

<script type="text/javascript" src="${ctx}/js/myflow/lib/raphael.js"></script>
<script type="text/javascript"
	src="${ctx}/js/myflow/lib/jquery-ui-1.8.4.custom/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript"
	src="${ctx}/js/myflow/lib/jquery-ui-1.8.4.custom/js/jquery-ui-1.8.4.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/js/myflow/myflow.js"></script>
<script type="text/javascript" src="${ctx}/js/myflow/myflow.jpdl4.js"></script>
<script type="text/javascript" src="${ctx}/js/myflow/myflow.editors.js"></script>
<script type="text/javascript">
	
	function submitForm(outcome) {
		$('#taskForm').append('<input type="hidden" name="outcome" value="' + outcome + '"/>').submit();
	}
</script>
<style type="text/css">
body {
	margin: 0;
	pading: 0;
	text-align: left;
	font-family: Arial, sans-serif, Helvetica, Tahoma;
	font-size: 12px;
	line-height: 1.5;
	color: black;
	background-image: url(img/bg.png);
}

.node {
	width: 70px;
	text-align: center;
	vertical-align: middle;
	border: 1px solid #fff;
}

.mover {
	border: 1px solid #ddd;
	background-color: #ddd;
}

.selected {
	background-color: #ddd;
}

.state {
	
}

#myflow_props table {
	
}

#myflow_props th {
	letter-spacing: 2px;
	text-align: left;
	padding: 6px;
	background: #ddd;
}

#myflow_props td {
	background: #fff;
	padding: 6px;
}

#pointer {
	background-repeat: no-repeat;
	background-position: center;
}

#path {
	background-repeat: no-repeat;
	background-position: center;
}

#task {
	background-repeat: no-repeat;
	background-position: center;
}

#state {
	background-repeat: no-repeat;
	background-position: center;
}
</style>

<title>Insert title here</title>
</head>
<body>

<iframe src="${ctx}/form/view-content.action?tableid=${tableid}&rid=${recordid}" frameborder="0" style="border: 0;"></iframe>
<br></br>
<form action="${ctx}/form/complete-task.action" id="taskForm"><input
	type="hidden" name="taskId" value="${taskId}" />
	<input
    type="hidden" name="tableid" value="${tableid}" />
    <input
    type="hidden" name="recordid" value="${recordid}" />
	 <s:iterator
	value="outcomes" var="out">
	<input type="button" value="${out}" onclick="submitForm('${out}');"></input>
	<input type="button" value="查看流程" onclick="window.open('${ctx}/form/view-flow.action?tableid=${tableid}&taskId=${taskId}&recordid=${recordid}','','fullscreen=no,width=800,height=600');"></input>
</s:iterator></form>

</body>
</html>
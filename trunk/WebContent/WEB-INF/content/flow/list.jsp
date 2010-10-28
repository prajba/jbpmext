<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*,org.jbpm.api.*"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript"
	src="${ctx}/js/myflow/lib/jquery-ui-1.8.4.custom/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript">
	$(function() {
		$.fn.collapsible = function() {
			return this.each(function() {
				if ($(this).next().is('table')) {
					$(this).append('<span style="background-color:#ddd;cursor: pointer;">+</span>').find('span').click(function() {
						if ($(this).html() == '+') {
							$(this).html('-');
							$(this).parent().next().show();
						} else {
							$(this).html('+');
							$(this).parent().next().hide();
						}
					});
					$(this).next().hide();
					if($(this).attr('collapsible')) $(this).find('span').click();
				}
			});
		};

		$('h1').collapsible();
	});
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<s:debug></s:debug>
<h1 collapsible="true">流程定义</h1>
<table border="1" bordercolor="black">
	<tr>
		<td >ID</td>
		<td>Key</td>
		<td>Name</td>
		<td>Version</td>
		<td>DeploymentId</td>
		<td>ImageResourceName</td>
		<td>Description</td>
		<td>Suspended</td>
		<td>Action</td>
	</tr>
	<s:iterator value="definitionList">
		<tr>
			<td>${id}</td>
			<td>${key}</td>
			<td>${name}</td>
			<td>${version}</td>
			<td>${deploymentId}</td>
			<td>${imageResourceName}</td>
			<td>${description}</td>
			<td>${suspended}</td>
			<td><a href="${ctx}/flow/start.action?definitionId=${id}">Start</a>,<a
				href="${ctx}/flow/remove.action?deploymentId=${deploymentId}">Remove</a></td>
		</tr>
	</s:iterator>
</table>
<h1 collapsible="true">流程实例</h1>
<table border="1" bordercolor="black">
	<tr>
		<td>ID</td>
		<td>Key</td>
		<td>Name</td>
		<td>State</td>
		<td>Ended</td>
		<td>Suspended</td>
		<td>Priority</td>
		<td>ActiveActivityNames</td>

	</tr>
	<%
		List<ProcessInstance> instanceList = (List<ProcessInstance>) request
				.getAttribute("instanceList");
		for (ProcessInstance ins : instanceList) {
	%>

	<tr>
		<td><%=ins.getId()%></td>
		<td><%=ins.getKey()%></td>
		<td><%=ins.getName()%></td>
		<td><%=ins.getState()%></td>
		<td><%=ins.isEnded()%></td>
		<td><%=ins.isSuspended()%></td>
		<td><%=ins.getPriority()%></td>
		<td><%=ins.findActiveActivityNames()%></td>

	</tr>
	<%
		}
	%>
</table>
<h1>历史流程实例</h1>
<table border="1" bordercolor="black">
	<tr>
		<td>ProcessInstanceId</td>
		<td>ProcessDefinitionId</td>
		<td>Key</td>
		<td>State</td>
		<td>StartTime</td>
		<td>EndTime</td>
		<td>Duration</td>
		<td>EndActivityName</td>

	</tr>
	<s:iterator value="hisInstanceList">
		<tr>
			<td>${processInstanceId}</td>
			<td>${processDefinitionId}</td>
			<td>${key}</td>
			<td>${state}</td>
			<td>${startTime}</td>
			<td>${endTime}</td>
			<td>${duration}</td>
			<td>${endActivityName}</td>

		</tr>
	</s:iterator>
</table>
<h1>&nbsp;</h1>
<form action="${ctx}/flow/list.action">用户：<input type="text"
	name="userName" value="${userName}"></input> <input type="submit"
	value="查询" /></form>
<h1 collapsible="true">任务列表</h1>

<table border="1" bordercolor="black">
	<tr>
		<td>ID</td>
		<td>Assignee</td>
		<td>Name</td>
		<td>Description</td>
		<td>CreateTime</td>
		<td>Duedate</td>
		<td>Priority</td>
		<td>Progress</td>
		<td>ExecutionId</td>
		<td>ActivityName</td>
		<td>FormResourceName</td>
		<td>Action</td>
	</tr>
	<s:iterator value="taskList">
		<tr>
			<td>${id}</td>
			<td>${assignee}</td>
			<td>${name}</td>
			<td>${description}</td>
			<td>${createTime}</td>
			<td>${duedate}</td>
			<td>${priority}</td>
			<td>${Progress}</td>
			<td>${executionId}</td>
			<td>${activityName}</td>
			<td>${formResourceName}</td>
			<td><a href="${ctx}/flow/task.action?taskId=${id}">View</a></td>
		</tr>
	</s:iterator>
</table>
<h1>任务历史列表</h1>

<table border="1" bordercolor="black">
	<tr>
		<td>ID</td>
		<td>ExecutionId</td>
		<td>CreateTime</td>
		<td>EndTime</td>
		<td>Duration</td>
		<td>State</td>
		<td>Assignee</td>
		<td>Outcome</td>
		<td>Action</td>
	</tr>
	<s:iterator value="hisTaskList">
		<tr>
			<td>${id}</td>
			<td>${executionId}</td>
			<td>${createTime}</td>
			<td>${endTime}</td>
			<td>${duration}</td>
			<td>${state}</td>
			<td>${assignee}</td>
			<td>${outcome}</td>

			<td><a href="${ctx}/flow/task.action?taskId=${id}">View</a></td>
		</tr>
	</s:iterator>
</table>

<h1>任务状态列表</h1>

<table border="1" bordercolor="black">
	<tr>
		<td>ActivityName</td>
		<td>StartTime</td>
		<td>EndTime</td>
		<td>Duration</td>
		<td>ExecutionId</td>
		<td>TransitionNames</td>
	</tr>
	<s:iterator value="hisActivityInstanceList" >
		<tr>
			<td>${activityName}</td>
			<td>${startTime}</td>
			<td>${endTime}</td>
			<td>${duration}</td>
			<td>${executionId}</td>
			<td><s:iterator value="transitionNames" var="item">${item}</s:iterator></td>
		</tr>
	</s:iterator>
</table>
</body>
</html>
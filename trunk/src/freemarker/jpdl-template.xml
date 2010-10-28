<?xml version="1.0" encoding="UTF-8"?>

<process key="${myflowkey}" name="${props.props.name.value}" xmlns="http://jbpm.org/4.4/jpdl">
	<description>${myflowdata}</description>
	
	<on  event="start">
		<event-listener class="org.jbpmext.jbpm.listener.TraceListener">
			<field name="type"><int value="0"/></field>
		</event-listener>
	</on>
	
	<#assign keys = states?keys> 

	<#list keys as k> 
	<${states[k].type} name="${states[k].text.text}" g="${states[k].attr.x},${states[k].attr.y},${states[k].attr.width},${states[k].attr.height}"
	<#if states[k].type=="task">
	assignee="${states[k].props.assignee.value}"  
	</#if>
	>
	<#if states[k].type=="decision">
    <handler class="org.jbpmext.jbpm.handler.MyDecisionHandler">
    <field name="expr"><string value='${states[k].props.expr.value}'/></field>
    </handler>
    </#if>
	<on  event="start">
		<event-listener class="org.jbpmext.jbpm.listener.TraceListener">
			<field name="type"><int value="1"/></field>
			<field name="activity"> <string value="${states[k].text.text}"/></field>
		</event-listener>
	</on>
	<on  event="end">
		<event-listener class="org.jbpmext.jbpm.listener.TraceListener">
			<field name="type"><int value="2"/></field>
			<field name="activity"> <string value="${states[k].text.text}"/></field>
		</event-listener>
	</on>
	
	<#assign pkeys = paths?keys>
	<#list pkeys as pk> 
	<#if paths[pk].from==k>
		<transition to="${states[paths[pk].to].text.text}" name="${paths[pk].text.text}" g="<#list paths[pk].dots as d>${d.x},${d.y}</#list>:${paths[pk].text.textPos.x},${paths[pk].text.textPos.y}">
			<event-listener class="org.jbpmext.jbpm.listener.TraceListener">
				<field name="type"><int value="3"/></field>
				<field name="activity"> <string value="${states[k].text.text}"/></field>
				<field name="transition"> <string value="${paths[pk].text.text}"/></field>
			</event-listener>
		</transition>
	</#if>
	</#list>
	
	</${states[k].type}>
	</#list>
	
	
</process>
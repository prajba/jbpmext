package org.jbpmext.web;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.jbpm.api.Execution;
import org.jbpm.api.ExecutionService;
import org.jbpm.api.HistoryService;
import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.RepositoryService;
import org.jbpm.api.TaskService;
import org.jbpm.api.history.HistoryActivityInstance;
import org.jbpm.api.history.HistoryDetail;
import org.jbpm.api.history.HistoryProcessInstance;
import org.jbpm.api.history.HistoryTask;
import org.jbpm.api.task.Task;
import org.jbpmext.dao.TableListDao;
import org.jbpmext.jbpm.listener.TraceListener;
import org.jbpmext.model.CusTabTable;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@Namespace(value = "/flow")
public class FlowAction extends ActionSupport {
	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private ExecutionService executionService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private HistoryService historyService;
	@Autowired
	private TableListDao tableListDao;

	private Integer tableid;
	private CusTabTable table;
	@Action(value = "edit")
	public String edit() {
		System.out.println("===========");
//		this.table = this.tableListDao.findById(this.tableid);
//		if(this.table.getFlowDescribe()==null){
//			this.table.setFlowDescribe("{}");
//		}
		return SUCCESS;
	}

	private String data;

	@Action(value = "save")
	public String save() {
		System.out.println("===========================");
		this.table = this.tableListDao.findById(this.tableid);
		this.table.setFlowId("myflowkey"+this.tableid);
		this.table.setFlowDescribe(data);
		
		JSONObject dataMap = JSONObject.fromObject(data);

		dataMap.put("myflowdata", "<![CDATA[" + data + "]]>");
		dataMap.put("myflowkey", "myflowkey"+this.tableid);

		Configuration config = new Configuration();
		try {

			config.setDirectoryForTemplateLoading(new File(ServletActionContext
					.getServletContext().getRealPath(
							"/WEB-INF/classes/freemarker/")));

			Template temp = config.getTemplate("jpdl-template.xml", "utf-8");

			StringWriter w = new StringWriter();
			temp.process(dataMap, w);
			System.out.println(w.toString());
			this.repositoryService.createDeployment().addResourceFromString(
					"test.jpdl.xml", w.toString()).deploy();
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
		return NONE;
	}

	private List<ProcessDefinition> definitionList;
	private List<ProcessInstance> instanceList;
	private List<HistoryProcessInstance> hisInstanceList;
	private List<Task> taskList;
	private List<HistoryTask> hisTaskList;
	private List<HistoryActivityInstance> hisActivityInstanceList;
	private List<HistoryDetail> hisDetail;
	private String userName;

	@Action(value = "list")
	public String list() {
		this.definitionList = this.repositoryService
				.createProcessDefinitionQuery().list();
		this.instanceList = this.executionService.createProcessInstanceQuery()
				.list();
		this.hisInstanceList = this.historyService
				.createHistoryProcessInstanceQuery().list();

		this.taskList = this.taskService.findPersonalTasks(this.userName);

		this.hisTaskList = this.historyService.createHistoryTaskQuery()
				.assignee(this.userName).list();

		this.hisActivityInstanceList = this.historyService
				.createHistoryActivityInstanceQuery().list();
		return SUCCESS;
	}

	private String deploymentId;
	private String definitionId;

	@Action(value = "start", results = { @Result(name = "success", location = "list.action", type = "redirect") })
	public String start() {
		this.executionService.startProcessInstanceById(definitionId);
		return SUCCESS;
	}

	@Action(value = "remove", results = { @Result(name = "success", location = "list.action", type = "redirect") })
	public String remove() {
		this.repositoryService.deleteDeploymentCascade(deploymentId);

		return SUCCESS;
	}

	private ProcessDefinition definition;
	private Task task;
	private String taskId;
	private Set<String> outcomes;
	private Execution execution;
	private Set<String> activeActivityNames;
	private String trace;

	@Action(value = "task")
	public String task() {
		this.task = this.taskService.getTask(taskId);
		this.outcomes = this.taskService.getOutcomes(taskId);

		this.execution = this.executionService.findExecutionById(this.task
				.getExecutionId());
		this.activeActivityNames = this.execution.findActiveActivityNames();
		this.definition = this.repositoryService.createProcessDefinitionQuery()
				.processDefinitionId(this.execution.getProcessDefinitionId())
				.uniqueResult();
		this.hisActivityInstanceList = this.historyService.createHistoryActivityInstanceQuery()
				.processInstanceId(this.execution.getProcessInstance().getId())
				.list();
		
		this.trace = JSONObject.fromObject(this.executionService.getVariable(this.execution.getId(), TraceListener.VAR_TRANCE)).toString();
		return "task";
	}

	private String outcome;

	@Action(value = "complete-task", results = { @Result(name = "success", location = "list.action", type = "redirect") })
	public String completeTask() {
		//System.out.println(URLDecoder.decode(outcome));
		this.taskService.completeTask(taskId, URLDecoder.decode(outcome));
		return SUCCESS;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public List<ProcessDefinition> getDefinitionList() {
		return definitionList;
	}

	public void setDefinitionList(List<ProcessDefinition> definitionList) {
		this.definitionList = definitionList;
	}

	public String getDeploymentId() {
		return deploymentId;
	}

	public void setDeploymentId(String deploymentId) {
		this.deploymentId = deploymentId;
	}

	public String getDefinitionId() {
		return definitionId;
	}

	public void setDefinitionId(String definitionId) {
		this.definitionId = definitionId;
	}

	public List<ProcessInstance> getInstanceList() {
		return instanceList;
	}

	public void setInstanceList(List<ProcessInstance> instanceList) {
		this.instanceList = instanceList;
	}

	public List<Task> getTaskList() {
		return taskList;
	}

	public void setTaskList(List<Task> taskList) {
		this.taskList = taskList;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public Set<String> getOutcomes() {
		return outcomes;
	}

	public void setOutcomes(Set<String> outcomes) {
		this.outcomes = outcomes;
	}

	public String getOutcome() {
		return outcome;
	}

	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}

	public List<HistoryTask> getHisTaskList() {
		return hisTaskList;
	}

	public void setHisTaskList(List<HistoryTask> hisTaskList) {
		this.hisTaskList = hisTaskList;
	}

	public List<HistoryProcessInstance> getHisInstanceList() {
		return hisInstanceList;
	}

	public void setHisInstanceList(List<HistoryProcessInstance> hisInstanceList) {
		this.hisInstanceList = hisInstanceList;
	}

	public List<HistoryActivityInstance> getHisActivityInstanceList() {
		return hisActivityInstanceList;
	}

	public void setHisActivityInstanceList(
			List<HistoryActivityInstance> hisActivityInstanceList) {
		this.hisActivityInstanceList = hisActivityInstanceList;
	}

	public ProcessDefinition getDefinition() {
		return definition;
	}

	public void setDefinition(ProcessDefinition definition) {
		this.definition = definition;
	}

	public Execution getExecution() {
		return execution;
	}

	public void setExecution(Execution execution) {
		this.execution = execution;
	}

	public Set<String> getActiveActivityNames() {
		return activeActivityNames;
	}

	public void setActiveActivityNames(Set<String> activeActivityNames) {
		this.activeActivityNames = activeActivityNames;
	}

	public String getTrace() {
		return trace;
	}

	public void setTrace(String trace) {
		this.trace = trace;
	}

	public Integer getTableid() {
		return tableid;
	}

	public void setTableid(Integer tableid) {
		this.tableid = tableid;
	}

	public CusTabTable getTable() {
		return table;
	}

	public void setTable(CusTabTable table) {
		this.table = table;
	}

}

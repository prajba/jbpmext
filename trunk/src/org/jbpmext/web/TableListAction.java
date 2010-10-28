package org.jbpmext.web;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.jbpm.api.Execution;
import org.jbpm.api.ExecutionService;
import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.TaskService;
import org.jbpm.api.task.Task;
import org.jbpmext.dao.TableListDao;
import org.jbpmext.jbpm.listener.TraceListener;
import org.jbpmext.model.*;
import org.jbpmext.service.TableListService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;

import org.jbpmext.model.CusTabTable;

import com.opensymphony.xwork2.ActionSupport;

@Namespace(value = "/form")
public class TableListAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware {

	HttpServletRequest request;
	HttpServletResponse response;

	@Resource
	private TableListService tableListService;

	private String success;

	private String formname; // 表单名称
	private String tablename; // 表名称
	private String textArea; // 表内容

	private String tableid;
	private List tablecontent;
	private String appendTablehtml;
	private String ms;

	public String getMs() {
		return ms;
	}

	public void setMs(String ms) {
		this.ms = ms;
	}

	public String getAppendTablehtml() {
		return appendTablehtml;
	}

	public void setAppendTablehtml(String appendTablehtml) {
		this.appendTablehtml = appendTablehtml;
	}

	private User user = null;

	private static final long serialVersionUID = 4092496206304964465L;

	@Action(value = "list")
	public String list() {
		// 得到 自定义的列表
		alltablelist = this.tableListService.findAllCusTabTables();

		return SUCCESS;
	}

	@Action(value = "create")
	public String create() {
		return SUCCESS;
	}

	// 删除一个自定义表单
	@Action(value = "delete-table", results = { @Result(name = "success", location = "list.action", type = "redirect") })
	public String deleteTable() {
		String tableId = request.getParameter("tableid");
		try {

			this.tableListService.delete(Integer.valueOf(tableId));

		} catch (Exception e) {

		}
		return SUCCESS;
	}

	@Action(value = "create-table", results = { @Result(name = "success", location = "list.action", type = "redirect") })
	public String createTable() throws Exception {

		// System.out.println(this.getFormname());
		// System.out.println(this.getTablename());
		// System.out.println(this.getTextArea());

		// truncateHTML(this.getTextArea(),2);

		this.tableListService.saveTable(this.getFormname(),
				this.getTablename(), this.getTextArea(), this.getMs());

		// STEP 1: 将html解析，摘出油也弄个的字段，备用生成表单时使用。
		// Step 2: 将html 加上验证，生成 新的HTML表单，并存入数据库。

		return SUCCESS;
		//
		// userService.registerUser(user);
		// System.out.println(user);

	}

	// 增加表单内容
	@Action(value = "list-record")
	public String listRecord() throws Exception {

		// step 1: 主表 中取得 HTML
		// step 2: 拼出JSP页面 显示在前台
		tableid = request.getParameter("tableid");
		CusTabTable ct = this.tableListService.findCusTabTableById(Integer
				.valueOf(tableid));
		formname = ct.getTableChinesename();
		textArea = ct.getTableContnet();
		return SUCCESS;
	}

	// 显示表单内容
	@Action(value = "view-table")
	public String viewTable() throws Exception {

		tableid = request.getParameter("tableid");

		CusTabTable ct = this.tableListService.findCusTabTableById(Integer
				.valueOf(tableid));
		textArea = ct.getTableContnet();
		formname = ct.getTableChinesename();
		return SUCCESS;

	}

	@Autowired
	private ExecutionService executionService;

	private CusTabTable table;

	// 填充表单内容
	@Action(value = "add-record", results = { @Result(name = "success", location = "list.action", type = "redirect") })
	public String tableadd() throws Exception {
		this.table = this.tableListService.findCusTabTableById(Integer
				.parseInt(this.tableid));
		ProcessInstance instance = this.executionService
				.startProcessInstanceByKey(this.table.getFlowId());

		instance.getId();

		Map sm = request.getParameterMap();

		Map newMap = new HashMap<String, Object>(sm);
		newMap.put("exampleId", new String[] { instance.getId() });
		newMap.put("exampleStat", new String[] { instance
				.findActiveActivityNames().toString() });

		newMap.remove("tableid");

		this.tableListService.tableAdd(newMap, tableid);
		return SUCCESS;
	}

	// 取出该表单下所有数据

	@Action(value = "query-table")
	public String tablequery() throws Exception {

		// 通过ID得到该表单的名称
		// 查询数据库中该名称表中的所有数据
		tableid = request.getParameter("tableid");

		CusTabTable ct = this.tableListService.findCusTabTableById(Integer
				.valueOf(tableid));

		tablename = ct.getTableName();

		tablecontent = this.tableListService.getTabmeContent(tablename);

		// System.out.println(tablecontent.size());

		return SUCCESS;

	}

	// 展现该表单下一条选中数据内容
	// 修改表单结构

	// 解析HTML工具类
	public static String truncateHTML(String content, int len) throws Exception {
		// URL url = new URL("http://www.baidu.com/"); //test

		// URL url = new URL("www.baidu.com");
		Document dirtyDocument = Jsoup.parse(content);
		Elements source = dirtyDocument.getElementsByTag("input");

		// System.out.println(content);

		// 遍历 html代码 ，取出所有的Input的 名称
		Document clean = Document.createShell(dirtyDocument.baseUri());
		List<String> list = new ArrayList<String>();
		for (Element element : source) {
			// element.outerHtml() 和 element.toString()效果一样
			// System.out.println("input代码：" + element.outerHtml());
			// System.out.println("input值：" + element.attr("value") + "input值："
			// + element.attr("name"));
			list.add(element.tagName());
		}
		//	      

		// System.out.println("test");
		Element dest = clean.body();
		System.out.println(dest.outerHtml());
		// truncateHTML(source,dest,len);
		return dest.outerHtml();
	}

	@Autowired
	private TaskService taskService;
	@Autowired
	private TableListDao tableListDao;
	private Integer recordid;
	private Map record;
	private String userName;
	private List<Task> taskList;

	@Action(value = "list-task")
	public String listTask() {
		this.table = this.tableListService.findCusTabTableById(Integer
				.parseInt(this.tableid));
		this.record = this.tableListDao.appendTableHtml(this.table
				.getTableName(), null, this.recordid.toString());

		this.taskList = this.taskService.createTaskQuery().processInstanceId(
				this.record.get("exampleId").toString())
				.assignee(this.userName).list();

		return SUCCESS;
	}

	private String taskid;
	private ProcessDefinition definition;
	private Task task;
	private String taskId;
	private Set<String> outcomes;
	private Execution execution;
	private Set<String> activeActivityNames;
	private String trace;

	@Action(value = "task")
	public String task() {
		this.table = this.tableListService.findCusTabTableById(Integer
				.parseInt(this.tableid));
		this.record = this.tableListDao.appendTableHtml(this.table
				.getTableName(), null, this.recordid.toString());

		this.task = this.taskService.getTask(taskId);
		this.outcomes = this.taskService.getOutcomes(taskId);
		this.execution = this.executionService.findExecutionById(this.task
				.getExecutionId());

		this.activeActivityNames = this.execution.findActiveActivityNames();

		return SUCCESS;
	}
	
	@Action(value="view-flow")
	public String viewFlow(){
		this.table = this.tableListService.findCusTabTableById(Integer
				.parseInt(this.tableid));
		this.record = this.tableListDao.appendTableHtml(this.table
				.getTableName(), null, this.recordid.toString());

		this.task = this.taskService.getTask(taskId);
		this.outcomes = this.taskService.getOutcomes(taskId);
		this.execution = this.executionService.findExecutionById(this.task
				.getExecutionId());

		this.activeActivityNames = this.execution.findActiveActivityNames();

		this.trace = JSONObject.fromObject(
				this.executionService.getVariable(this.execution.getId(),
						TraceListener.VAR_TRANCE)).toString();
		return SUCCESS;
	}

	private String outcome;

	@Action(value = "complete-task", results = { @Result(name = "success", location = "query-table.action", params = {
			"tableid", "${tableid}" }, type = "redirect") })
	public String completeTask() {
		// System.out.println(URLDecoder.decode(outcome));

		this.task = this.taskService.getTask(taskId);
		this.execution = this.executionService.findExecutionById(this.task
				.getExecutionId());
		this.execution = this.execution.getProcessInstance();
		this.taskService.completeTask(taskId, URLDecoder.decode(outcome));
		Set<String> names = this.execution
		.findActiveActivityNames();
		this.tableListService.updateTableInfo(Integer.parseInt(this.tableid),
				this.execution.getId(), (names.size()==0?"结束":names.toString()), this.recordid
						.toString());

		return SUCCESS;
	}

	@Action(value = "view-content")
	public String tableviewcontent() {

		String rid = request.getParameter("rid");

		CusTabTable ct = this.tableListService.findCusTabTableById(Integer
				.valueOf(tableid));
		textArea = ct.getTableContnet();
		formname = ct.getTableChinesename();
		tablename = ct.getTableName();

		appendTablehtml = this.tableListService.appendTableHtml(tablename,
				textArea, rid);

		System.out.println(appendTablehtml);
		return SUCCESS;
	}

	public List getTablecontent() {
		return tablecontent;
	}

	public void setTablecontent(List tablecontent) {
		this.tablecontent = tablecontent;
	}

	private List<CusTabTable> alltablelist;

	public String getFormname() {
		return formname;
	}

	public void setFormname(String formname) {
		this.formname = formname;
	}

	public String getTablename() {
		return tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}

	public String getTextArea() {
		return textArea;
	}

	public void setTextArea(String textArea) {
		this.textArea = textArea;
	}

	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
	}

	public void setServletResponse(HttpServletResponse arg0) {
		this.response = arg0;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getTableid() {
		return tableid;
	}

	public void setTableid(String tableid) {
		this.tableid = tableid;
	}

	public List<CusTabTable> getAlltablelist() {
		return alltablelist;
	}

	public void setAlltablelist(List<CusTabTable> alltablelist) {
		this.alltablelist = alltablelist;
	}

	public CusTabTable getTable() {
		return table;
	}

	public void setTable(CusTabTable table) {
		this.table = table;
	}

	public Integer getRecordid() {
		return recordid;
	}

	public void setRecordid(Integer recordid) {
		this.recordid = recordid;
	}

	public Map getRecord() {
		return record;
	}

	public void setRecord(Map record) {
		this.record = record;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<Task> getTaskList() {
		return taskList;
	}

	public void setTaskList(List<Task> taskList) {
		this.taskList = taskList;
	}

	public String getTaskid() {
		return taskid;
	}

	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}

	public ProcessDefinition getDefinition() {
		return definition;
	}

	public void setDefinition(ProcessDefinition definition) {
		this.definition = definition;
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

	public String getOutcome() {
		return outcome;
	}

	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}

}

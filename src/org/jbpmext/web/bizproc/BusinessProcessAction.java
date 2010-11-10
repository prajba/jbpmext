/**
 * 
 */
package org.jbpmext.web.bizproc;

import java.io.File;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.RepositoryService;
import org.jbpmext.util.ActionJsonUtil;
import org.jbpmext.util.EasyUIGridWrapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * @author weiht
 *
 */
@SuppressWarnings("serial")
@Namespace("/bizproc")
public class BusinessProcessAction extends ActionSupport {
	private static final Logger logger = LogManager.getLogger(BusinessProcessAction.class);
	@Autowired
	private RepositoryService repositoryService;
	
	private List<ProcessDefinition> definitions;
	private ProcessDefinition definition;
	private String data;

	@Override
	@Action(value="manager", results={@Result(name="success", location="/WEB-INF/content/bizproc/manager.jsp")})
	public String execute() throws Exception {
		return SUCCESS;
	}
	
	@Action(value="list", results={@Result(name="success", location="/common/json.jsp")})
	public String list() throws Exception {
		try {
			definitions = repositoryService.createProcessDefinitionQuery().list();
		} catch (Exception ex) {
			logger.error("Loading process definitions:", ex);
			definitions = new ArrayList<ProcessDefinition>();
		}
		ActionJsonUtil.putJson(new EasyUIGridWrapper(procDefsToStrings(definitions)));
		return SUCCESS;
	}
	
	private List<String> procDefsToStrings(List<ProcessDefinition> defs) {
		List<String> result = new ArrayList<String>(defs.size());
		for (ProcessDefinition def: defs) {
			//def.
		}
		return result;
	}

	@Action(value="save", results={@Result(name="success", location="/common/json.jsp")})
	public String save() throws Exception {
		logger.debug(data);
		JSONObject dataMap = JSONObject.fromObject(data);
		dataMap.put("myflowdata", "<![CDATA[" + data + "]]>");

		Configuration config = new Configuration();
		try {

			config.setDirectoryForTemplateLoading(new File(ServletActionContext
					.getServletContext().getRealPath("/WEB-INF/classes/freemarker/")));
			Template temp = config.getTemplate("jpdl-template.tpl", "utf-8");
			StringWriter w = new StringWriter();
			temp.process(dataMap, w);
			logger.debug(w);
			this.repositoryService.createDeployment().addResourceFromString(
					"test.jpdl.xml", w.toString()).deploy();
			ActionJsonUtil.putJson(data);
		} catch (Exception ex) {
			logger.error("Saving process definition:", ex);
			ActionJsonUtil.putJson(new Object());
		}
		return SUCCESS;
	}
	
	@Action(value="editor", results={@Result(name="success", location="/WEB-INF/content/bizproc/editor.jsp")})
	public String editor() throws Exception {
		return SUCCESS;
	}
	
	public ProcessDefinition getDefinition() {
		return definition;
	}

	public void setDefinition(ProcessDefinition definition) {
		this.definition = definition;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}

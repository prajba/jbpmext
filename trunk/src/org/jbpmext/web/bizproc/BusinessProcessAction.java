/**
 * 
 */
package org.jbpmext.web.bizproc;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.RepositoryService;
import org.jbpmext.util.ActionJsonUtil;
import org.jbpmext.util.EasyUIGridWrapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

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
		ActionJsonUtil.putJson(new EasyUIGridWrapper(definitions));
		return SUCCESS;
	}
	
	@Action(value="save", results={@Result(name="success", location="/common/json.jsp")})
	public String save() throws Exception {
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
}

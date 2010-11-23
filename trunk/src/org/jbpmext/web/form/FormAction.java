/**
 * 
 */
package org.jbpmext.web.form;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.jbpmext.model.MetaField;
import org.jbpmext.model.MetaForm;
import org.jbpmext.service.FormService;
import org.jbpmext.util.ActionJsonUtil;
import org.jbpmext.util.EasyUIGridWrapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author weiht
 *
 */
@SuppressWarnings("serial")
@Namespace("/metaform")
public class FormAction extends ActionSupport {
	private static final Logger logger = LogManager.getLogger(FormAction.class);
	@Autowired
	private FormService service;
	
	private List<MetaForm> forms;
	private MetaForm form;

	@Override
	@Action(value="manager", results={@Result(name="success", location="/WEB-INF/content/metaform/manager.jsp")})
	public String execute() throws Exception {
		return SUCCESS;
	}
	
	@Action(value="list", results={@Result(name="success", location="/common/json.jsp")})
	public String list() throws Exception {
		try {
			forms = service.findAllForms();
		} catch (Exception ex) {
			logger.error("Loading forms", ex);
		}
		ActionJsonUtil.putJson(new EasyUIGridWrapper(forms));
		return SUCCESS;
	}
	
	@Action(value="editor", results={@Result(name="success", location="/WEB-INF/content/metaform/editor.jsp")})
	public String edit() throws Exception {
		return SUCCESS;
	}
	
	@Action(value="save", results={@Result(name="success", location="/common/json.jsp")})
	public String save() throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("Saving meta form: " + form);
		}
		try {
			for (MetaField f: form.getFields()) {
				f.setForm(form);
			}
			service.save(form);
			ActionJsonUtil.putJson(form);
		} catch (RuntimeException ex) {
			logger.error("Error saving form: ", ex);
			throw ex;
		}
		return SUCCESS;
	}

	public MetaForm getForm() {
		return form;
	}

	public void setForm(MetaForm form) {
		this.form = form;
	}
}

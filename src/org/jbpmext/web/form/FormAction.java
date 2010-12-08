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
import org.jbpmext.model.MetaForm;
import org.jbpmext.service.FormService;
import org.jbpmext.util.ActionJsonUtil;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
	private FormService formService;
	
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
			forms = formService.findAllFormsMaxVer();
		} catch (Exception ex) {
			logger.error("Loading forms", ex);
		}
		ActionJsonUtil.putJson(formsToString(forms));
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
			formService.save(form);
			ActionJsonUtil.putJson(formToString(form));
		} catch (RuntimeException ex) {
			logger.error("Error saving form: ", ex);
			throw ex;
		}
		return SUCCESS;
	}
	
	private String formsToString(List<MetaForm> forms) {
		StringBuilder buff = new StringBuilder();
		for (MetaForm f: forms) {
			if (buff.length() > 0) buff.append(',');
			buff.append(formToString(f));
		}
		return "{\"total\":" + forms.size() + ",\"rows\":[" + buff + "]}";
	}
	
	private String formToString(MetaForm form) {
		Gson g = new GsonBuilder().setExclusionStrategies(new ExclusionStrategy(){
			@Override
			public boolean shouldSkipClass(Class<?> cls) {
				return false;
			}

			@Override
			public boolean shouldSkipField(FieldAttributes fattr) {
				return fattr.getName().equals("form");
			}
		}).create();
		return g.toJson(form);
	}

	public MetaForm getForm() {
		return form;
	}

	public void setForm(MetaForm form) {
		this.form = form;
	}
}

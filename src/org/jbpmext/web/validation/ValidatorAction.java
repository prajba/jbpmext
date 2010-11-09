/**
 * 
 */
package org.jbpmext.web.validation;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.jbpmext.model.UserValidator;
import org.jbpmext.service.ValidatorService;
import org.jbpmext.util.ActionJsonUtil;
import org.jbpmext.util.EasyUIGridWrapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author weiht
 *
 */
@SuppressWarnings("serial")
@Namespace("/validation")
public class ValidatorAction extends ActionSupport {
	private static final Logger logger = LogManager.getLogger(ValidatorAction.class);
	@Autowired
	private ValidatorService service;

	private List<UserValidator> validators;
	private UserValidator validator;

	@Action(value="manager", results={
		@Result(name="success", location="/WEB-INF/content/validation/manager.jsp")}
	)
	public String execute() {
		return SUCCESS;
	}
	
	@Action(value="list", results={@Result(name="success", location="/common/json.jsp")})
	public String list() {
		try {
			validators = service.findAllValidators();
		} catch (Exception ex) {
			logger.error("Loading user defined validators:", ex);
			validators = new ArrayList<UserValidator>();
		}
		ActionJsonUtil.putJson(new EasyUIGridWrapper(validators));
		return SUCCESS;
	}
	
	@Action(value="editor", results={@Result(name="success", location="/WEB-INF/content/validation/editor.jsp")})
	public String editor() {
		return SUCCESS;
	}
	
	@Action(value="save", results={@Result(name="success", location="/common/json.jsp")})
	public String save() {
		if (logger.isDebugEnabled()) {
			logger.debug("Saving validator: " + validator);
		}
		try {
			service.save(validator);
			ActionJsonUtil.putJson(validator);
		} catch (RuntimeException ex) {
			logger.error("Error saving validator: ", ex);
			throw ex;
		}
		return SUCCESS;
	}
	
	@Action(value="remove", results={@Result(name="success", location="/common/json.jsp")})
	public String remove() {
		try {
			service.remove(validator);
		} catch (Exception ex) {
			validator.setId(null);
			logger.error("Removing user defined validator:", ex);
		}
		ActionJsonUtil.putJson(validator);
		return SUCCESS;
	}
	
	public List<UserValidator> getValidators() {
		return validators;
	}

	public void setValidators(List<UserValidator> validators) {
		this.validators = validators;
	}
	
	public UserValidator getValidator() {
		return validator;
	}

	public void setValidator(UserValidator validator) {
		this.validator = validator;
	}
}

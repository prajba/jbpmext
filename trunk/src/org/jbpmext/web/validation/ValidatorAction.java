/**
 * 
 */
package org.jbpmext.web.validation;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.jbpmext.model.UserValidator;
import org.jbpmext.service.ValidatorService;
import org.jbpmext.util.ActionJsonUtil;
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
		validators = service.findAllValidators();
		ActionJsonUtil.putJson(validators);
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
	
	public List<UserValidator> getValidators() {
		return validators;
	}

	public void setValidators(List<UserValidator> validators) {
		this.validators = validators;
	}
	
	public ValidatorService getService() {
		return service;
	}

	@Autowired
	public void setService(ValidatorService service) {
		this.service = service;
	}

	public UserValidator getValidator() {
		return validator;
	}

	public void setValidator(UserValidator validator) {
		this.validator = validator;
	}
}

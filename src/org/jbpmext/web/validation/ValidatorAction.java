/**
 * 
 */
package org.jbpmext.web.validation;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.jbpmext.model.UserValidator;
import org.jbpmext.service.ValidatorService;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author weiht
 *
 */
@SuppressWarnings("serial")
@Namespace("validation")
public class ValidatorAction extends ActionSupport {
	private ValidatorService service;

	private List<UserValidator> validators;

	@Action(value="manager", results={
		@Result(name="success", location="/WEB-INF/content/validation/manager.jsp")}
	)
	public String execute() {
		validators = service.findAllValidators();
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
}

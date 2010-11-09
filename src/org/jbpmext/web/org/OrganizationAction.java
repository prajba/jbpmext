/**
 * 
 */
package org.jbpmext.web.org;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.jbpmext.model.Organization;
import org.jbpmext.service.OrganizationService;
import org.jbpmext.util.ActionJsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author weiht
 *
 */
@SuppressWarnings("serial")
@Namespace(value="/org")
@Component
public class OrganizationAction extends ActionSupport {
	private static final Logger logger = LogManager.getLogger(OrganizationAction.class);
	@Autowired
	private OrganizationService service;

	private List<Organization> orgs;
	
	private Integer id;

	@Action(value="orgManager", results={
		@Result(name="success", location="/WEB-INF/content/org/orgManager.jsp")
	})
	public String execute() {
		return SUCCESS;
	}
	
	@Action(value="orgList", results={
		@Result(name="success", location="/common/json.jsp")
	})
	public String list() {
		if (id == null) {
			logger.debug("loading root org...");
			orgs = service.getRoot();
		} else {
			if (logger.isDebugEnabled()) {
				logger.debug("loading child org for id:" + id);
			}
			orgs = service.getChildOrgs(id);
		}
		ActionJsonUtil.putJson(orgs);
		return SUCCESS;
	}
	
	public List<Organization> getOrgs() {
		return orgs;
	}

	public void setOrgs(List<Organization> orgs) {
		this.orgs = orgs;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}

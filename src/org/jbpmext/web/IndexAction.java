/**
 * 
 */
package org.jbpmext.web;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author weiht
 *
 */
@SuppressWarnings("serial")
public class IndexAction extends ActionSupport {
	@Action(value="index",
			results={
				@Result(name="success", location="/WEB-INF/content/index.jsp")
			})
	public String execute() {
		return SUCCESS;
	}
}

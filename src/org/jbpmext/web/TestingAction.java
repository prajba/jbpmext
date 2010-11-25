/**
 * 
 */
package org.jbpmext.web;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.jbpmext.util.ActionJsonUtil;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author weiht
 *
 */
@SuppressWarnings("serial")
@Namespace("/test")
public class TestingAction extends ActionSupport {
	@Action(value="mapToJson", results={@Result(name="success", location="/common/json.jsp")})
	public String mapToJson() {
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("id", 1);
		m.put("name", "Notices");
		m.put("draftTime", new Date());
		ActionJsonUtil.putJson(m);
		return SUCCESS;
	}
}

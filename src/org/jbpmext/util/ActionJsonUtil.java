/**
 * 
 */
package org.jbpmext.util;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;

/**
 * @author weiht
 *
 */
public class ActionJsonUtil {
	private ActionJsonUtil() {}
	
	private static final String REQ_JSON_ATTR = "__REQ_JSON_ATTR__";
	
	public static void putJson(Object o) {
		Gson g = new Gson();
		String json = g.toJson(o);
		ServletActionContext.getRequest().setAttribute(REQ_JSON_ATTR, json);
	}
	
	public static String getJson() {
		return (String)ServletActionContext.getRequest().getAttribute(REQ_JSON_ATTR);
	}
}

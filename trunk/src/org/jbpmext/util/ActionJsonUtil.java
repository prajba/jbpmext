/**
 * 
 */
package org.jbpmext.util;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;

/**
 * @author weiht
 *
 */
public class ActionJsonUtil {
	private static final Logger logger = LogManager.getLogger(ActionJsonUtil.class);
	private ActionJsonUtil() {}
	
	private static final String REQ_JSON_ATTR = "__REQ_JSON_ATTR__";
	
	public static void putJson(Object o) {
		logger.debug("Converting object to json...");
		Gson g = new Gson();
		String json = g.toJson(o);
		logger.debug(json);
		ServletActionContext.getRequest().setAttribute(REQ_JSON_ATTR, json);
	}
	
	public static void putJson(String s) {
		logger.debug(s);
		ServletActionContext.getRequest().setAttribute(REQ_JSON_ATTR, s);
	}
	
	public static String getJson() {
		return (String)ServletActionContext.getRequest().getAttribute(REQ_JSON_ATTR);
	}
}

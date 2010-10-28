package org.jbpmext.jbpm.handler;

import java.util.HashMap;


import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;
import org.jbpmext.util.JuelUtil;


public class MyDecisionHandler implements DecisionHandler {
	String expr;

	@Override
	public String decide(OpenExecution execution) {
		if (expr == null || expr.trim().equals(""))
			return null;
		System.out.println("aaaaaaaaaaaaa");
		return JuelUtil.eval(new HashMap<String, Object>(), expr);
	}
}

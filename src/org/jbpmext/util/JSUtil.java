package org.jbpmext.util;

import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleScriptContext;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

import java.io.*;

public class JSUtil {
	private static ScriptEngine engine;

	public static synchronized ScriptEngine getScriptEngine() {
		if (engine == null) {
			ScriptEngineManager manager = new ScriptEngineManager();
			engine = manager.getEngineByName("JavaScript");
		}
		return engine;
	}

	public static ScriptContext createScriptContext(PageContext pageContext) {
		ScriptContext scriptContext = new SimpleScriptContext();
		int scope = ScriptContext.ENGINE_SCOPE;
		scriptContext.setAttribute("pageContext", pageContext, scope);
		scriptContext.setAttribute("page", pageContext.getPage(), scope);
		scriptContext.setAttribute("request", pageContext.getRequest(), scope);
		scriptContext
				.setAttribute("response", pageContext.getResponse(), scope);
		scriptContext.setAttribute("out", pageContext.getOut(), scope);
		scriptContext.setAttribute("session", pageContext.getSession(), scope);
		scriptContext.setAttribute("config", pageContext.getServletConfig(),
				scope);
		scriptContext.setAttribute("application", pageContext
				.getServletContext(), scope);
		
		
		
		System.out.println("end");
		
		
		scriptContext.setWriter(pageContext.getOut());
		return scriptContext;
	}

	public static ScriptContext getScriptContext(PageContext pageContext)
			throws IOException {
		ServletRequest request = pageContext.getRequest();
		synchronized (request) {
			ScriptContext scriptContext = (ScriptContext) request
					.getAttribute("jsee.ScriptContext");
			if (scriptContext == null) {
				
				System.out.println("begin");
				scriptContext = createScriptContext(pageContext);
				request.setAttribute("jsee.ScriptContext", scriptContext);
			}
			return scriptContext;
		}
	}

	public static void runScript(String source, PageContext pageContext)
			throws ServletException, IOException {
		try {
			getScriptEngine().eval(source, getScriptContext(pageContext));
		} catch (ScriptException x) {
			((HttpServletResponse) pageContext.getResponse()).setStatus(500);
			PrintWriter out = new PrintWriter(pageContext.getOut());
			out.println("<pre>" + source + "</pre>");
			out.println("<pre>");
			x.printStackTrace(out);
			out.println("</pre>");
			out.flush();
			throw new ServletException(x);
		}
	}

}

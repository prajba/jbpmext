package org.jbpmext.servlet;

import javax.script.*;
import javax.servlet.*;
import javax.servlet.http.*;

import org.jbpmext.servlet.cache.*;

import java.io.*;

import org.jbpmext.servlet.cache.ScriptCache;

public class JSServlet extends HttpServlet {
	private String cacheControlHeader;
	private String contentTypeHeader;
	private ScriptCache scriptCache;

	// init() 会获得几个配置参数并创建一个 ScriptCache 对象。
	// servlet getRealPath() 获得与给定 URI 相映射的脚本文件的路径
	public void init() throws ServletException {
		ServletConfig config = getServletConfig();
		cacheControlHeader = config.getInitParameter("Cache-Control");
		contentTypeHeader = config.getInitParameter("Content-Type");
		int maxCachedScripts = Integer.parseInt(config
				.getInitParameter("Max-Cached-Scripts"));
		scriptCache = new ScriptCache(maxCachedScripts) {
			public File getScriptFile(String uri) {
				return new File(getServletContext().getRealPath(uri));
			}
		};
	}

	/*
	 * 脚本上下文
	 */
	protected ScriptContext createScriptContext(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		ScriptContext scriptContext = new SimpleScriptContext();
		scriptContext.setWriter(response.getWriter());
		int scope = ScriptContext.ENGINE_SCOPE;
		scriptContext.setAttribute("config", getServletConfig(), scope);
		scriptContext.setAttribute("application", getServletContext(), scope);
		scriptContext.setAttribute("session", request.getSession(), scope);
		scriptContext.setAttribute("request", request, scope);
		scriptContext.setAttribute("response", response, scope);
		scriptContext.setAttribute("out", response.getWriter(), scope);
		scriptContext.setAttribute("factory", scriptCache.getEngine()
				.getFactory(), scope);
		return scriptContext;
	}

	/*
	 * runScript() 方法从缓存获得一个编译后的脚本并调用 eval() 方法， 将给定的脚本上下文作为参数传递
	 * 
	 * 在同一个上下文中，可以连续运行多个脚本。 例如，一个脚本可以定义一组变量和函数，
	 * 而另一个脚本则可以使用之前在相同上下文内执行的脚本的变量和函数进行一些处理。
	 */
	protected String runScript(String uri, ScriptContext scriptContext)
			throws ScriptException, IOException {
		Object o = "";
		o = scriptCache.getScript(uri).eval(scriptContext);

		return "" + o;

	}

	/*
	 * 可设置这些 HTTP 报头、运行 init.jss 脚本、从请求的 URI 中删除此上下文路径、 执行具有所获得的 URI
	 * 的脚本，然后运行另一个名为 finalize.jss 的脚本
	 */
	protected void handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		if (cacheControlHeader != null)
			response.setHeader("Cache-Control", cacheControlHeader);
		if (contentTypeHeader != null)
			response.setContentType(contentTypeHeader);
		response.setContentType("text/html;charset=gbk"); 
		ScriptContext scriptContext = createScriptContext(request, response);
		try {
			runScript("/init.jss", scriptContext);
			String uri = (String) request
					.getAttribute("javax.servlet.include.request_uri");
			if (uri == null)
				uri = (String) request
						.getAttribute("javax.servlet.forward.request_uri");
			if (uri == null)
				uri = request.getRequestURI();
			uri = uri.substring(request.getContextPath().length());
			try {
				String json = (String) request.getParameter("inputField");
				System.out.println("得到的json "+json);
				
				String rere = runScript("/presetValidators.js", scriptContext);

			//	System.out.println("返回 "+rere);
				
			} catch (FileNotFoundException x) {
				response.sendError(404, request.getRequestURI());
			}
			runScript("/finalize.jss", scriptContext);
		} catch (ScriptException x) {
			response.setStatus(500);
			PrintWriter out = response.getWriter();
			out.println("<pre>");
			x.printStackTrace(out);
			out.println("</pre>");
			out.flush();
			throw new ServletException(x);
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		handleRequest(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		handleRequest(request, response);
	}

	/*public void outStr(HttpServletResponse response, String htmlstr) {
		response.setContentType("text/xml;charset=gb2312");
		try {
			response.getOutputStream().print(htmlstr);
			response.getOutputStream().flush();
			PrintWriter out = response.getWriter();
			out.println(htmlstr);
		} catch (IOException e) {
			e.printStackTrace();rintWriter out =
		}
	}*/
}

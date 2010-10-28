package org.jbpmext.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;


public class UserTableDefine extends ActionSupport implements ServletRequestAware,ServletResponseAware{

	private static final long serialVersionUID = 1L;
	HttpServletRequest request;
	HttpServletResponse response;
	
	private String method="";
	private String success;

	public String execute(){
		this.method = "execute";
		return Action.SUCCESS;
	}
	public String test(){
		this.method = "test";
		return Action.SUCCESS;
	}
	public String createTable(){
		//
//		userService.registerUser(user);
//		System.out.println(user);
		return Action.SUCCESS;
	}
	public String getMethod() {
		
		System.out.println(method);
		
		
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
	}
	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		
	}

}

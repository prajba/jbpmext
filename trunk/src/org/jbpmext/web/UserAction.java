package org.jbpmext.web;

import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Clob;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.hibernate.Hibernate;
import org.jbpmext.model.User;
import org.jbpmext.service.TableListService;
import org.jbpmext.service.UserService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import bsh.This;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;


public class UserAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{
	HttpServletRequest request;
	HttpServletResponse response;
	
	@Resource
	private UserService userService;
	@Resource
	private TableListService tableListService;

	private String method="";
	private String success;
	private String ms;         //表单描述
	private String formname;   //表单名称
	private String tablename;  //表名称
	public String getMs() {
		return ms;
	}
	public void setMs(String ms) {
		this.ms = ms;
	}
	private String textArea;   //表内容

	public String getFormname() {
		return formname;
	}
	public void setFormname(String formname) {
		this.formname = formname;
	}
	public String getTablename() {
		return tablename;
	}
	public void setTablename(String tablename) {
		this.tablename = tablename;
	}
	public String getTextArea() {
		return textArea;
	}
	public void setTextArea(String textArea) {
		this.textArea = textArea;
	}
	private User user = null;
	
	private static final long serialVersionUID = 4092496206304964465L;

	public String execute(){
		this.method = "execute";
		return Action.SUCCESS;
	}
	public String test(){
		
		System.out.println("233");
		this.method = "test";
		return Action.SUCCESS;
	}
	public String register(){
		//
		userService.registerUser(user);
		System.out.println(user);
		return "registerSuccess";
	}

	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	
	public String testAction() throws IOException{
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");
		response.getWriter().println("Hello world!");
		
		response.getWriter().println("success"+success);
	
		return null;
	}
	
    public String createTable() throws Exception{

    	System.out.println(this.getFormname());
    	System.out.println(this.getTablename());
    	System.out.println(this.getMs());

    	//step1:将字段内容等插入插入数据库 
    	
    	System.out.println("3333333");
    	
    	this.tableListService.saveTable(formname, tablename, textArea,ms);
    	
    	
    	//step2:生成数据库表结构 。
    	
//    	truncateHTML(this.getTextArea(),2);
    	
//        this.tableListService.createTable(truncateHTML(this.getTextArea(),2));
    	this.method="test";
		return Action.SUCCESS;
    	

    	//this.userService.createTable(this.getFormname(),this.getTablename(),this.getTextArea());
    	
    	//STEP 1:  将html解析，摘出油也弄个的字段，备用生成表单时使用。
    	//Step 2:  将html 加上验证，生成 新的HTML表单，并存入数据库。
 
		//
//		userService.registerUser(user);
//		System.out.println(user);
		
	}

	public String test2(){
		this.method="test2";
		return Action.SUCCESS;
	}
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
	}
	public void setServletResponse(HttpServletResponse arg0) {
		this.response = arg0;
	}
	
	public String getSuccess() {
		return success;
	}
	public void setSuccess(String success) {
		this.success = success;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

//	public static List truncateHTML(String content,int len) throws Exception{
//		 //URL url = new URL("http://www.baidu.com/");  //test
//		
//		 //URL url = new URL("www.baidu.com");
//	     Document  dirtyDocument = Jsoup.parse(content);   
//	     Elements source = dirtyDocument.getElementsByTag("input");   
//	    
//
//	     //遍历 html代码 ，取出所有的Input的 名称 
//	     
//	     Document clean = Document.createShell(dirtyDocument.baseUri());
//	     List<String> list = new ArrayList<String>();
//	     for (Element element : source) {   
//	            //element.outerHtml() 和  element.toString()效果一样   
//	           // System.out.println("input代码：" + element.outerHtml());   
//	           // System.out.println("input值：" + element.attr("value") + "  input值：" + element.attr("name"));
//	            list.add(element.tagName());
//	        }   
////	      ff
////	     System.out.println("test");
//	    
//	     
//      
//	     Element dest = clean.body();    
////	     truncateHTML(source,dest,len);   
//	     return list;   
//	}   

}

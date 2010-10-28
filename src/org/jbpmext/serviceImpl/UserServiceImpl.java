package org.jbpmext.serviceImpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


import javax.annotation.Resource;

import org.jbpmext.dao.UserDao;
import org.jbpmext.model.User;
import org.jbpmext.service.UserService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Resource
	private UserDao dao ;
	
	public void delete(User user) {
		dao.delete(user.getId());	
	}
	public void findAllUsers() {
		dao.findAll();
	}

	public void findUserById(Long id) {
		dao.findById(id);
	}

	public void findUsersByHql(String hql) {
		dao.findByHql();
	}

	public User login(User user) {
		return null;
	}

	public Serializable registerUser(User user) {
		return dao.add(user);
	}

	public void update(User user) {
		dao.update(user);
	}

	//创建表单业务处理部分
	
	/**
	 * 得到创建表单所必须的参数 
	 * @throws Exception 
	 * 
	 */
	
	public boolean saveTable(String formname, String tablename, String textArea) throws Exception {   
		List<Element> list = truncateHTML(textArea);
		String mysqlcreatsql = createMySql(list,tablename);
		return this.dao.createMsqlTable(mysqlcreatsql);
	}

//	public boolean createTable() {
//		// TODO Auto-generated method stub
//		return this.dao.createTable("list", new ArrayList());
//	}

	//返回所有数据名称的List
	private  static List<Element> truncateHTML(String content) throws Exception{
		 //URL url = new URL("http://www.baidu.com/");  //test
	     Document  dirtyDocument = Jsoup.parse(content);   
	     Elements source = dirtyDocument.getElementsByTag("input");    
	     Elements selectCode = dirtyDocument.getElementsByTag("select");
	     Elements selecttextare = dirtyDocument.getElementsByTag("textarea");
	     Elements query = dirtyDocument.getElementsByTag("image");
	     
	     for(int i=0;i<query.size();i++){
	    	 System.out.println(query.get(i));
	    	 
	    	 System.out.println(query.get(i));
	    	 
	    	 
	 
	     }
 
	    // dirtyDocument.select("input");   
 
	     //遍历 html代码 ，取出所有的Input的 名称 
	     List<Element> tagList = new ArrayList<Element>();
	    
	     Document clean = Document.createShell(dirtyDocument.baseUri());
	     
	     /**
	      * 都是集合元素，为什么要返回List呢
	      * 
	      * 考虑：因为 有可能所需要创建表的字段不只是 input标签产生的字段。所以，为了后期可扩展的因素。干脆一下返回一个LIST集合
	      * 
	      * 
	      */
	     for (Element element : source) {   
	            tagList.add(element);
	      }
	     for (Element element : selectCode) {   
	            //element.outerHtml() 和  element.toString()效果一样          
	            tagList.add(element);
	      } 
	     for (Element element : selecttextare) {   
	            tagList.add(element);
	      } 
		     Element dest = clean.body();    
	//	     truncateHTML(source,dest,len);   
		     return tagList;   
	}   

	private  static String createMySql(List<Element> list,String tablename){
		StringBuffer sb = new StringBuffer();	
		sb.append("CREATE TABLE `t_");
		sb.append(tablename);  
		sb.append("`(`Id` int(11) NOT NULL auto_increment,");
		
//		System.out.print("test");
		
		
		for(int i=0;i<list.size();i++){			
			sb.append("`");
			sb.append(list.get(i).attr("name"));
			sb.append("` varchar(255) default NULL,");
		}
		sb.append(" PRIMARY KEY(`id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;");
		try{
		}catch(Exception e){	
			e.printStackTrace();
		}
	
		System.out.println("test---------start"+sb.toString()+"--------end");
		
		return sb.toString();
	}
}

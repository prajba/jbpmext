package org.jbpmext.serviceImpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.jbpmext.dao.CusTabColumnDao;
import org.jbpmext.dao.GenericDao;
import org.jbpmext.dao.TableListDao;
import org.jbpmext.model.CusTabColumn;
import org.jbpmext.model.CusTabTable;
import org.jbpmext.model.User;
import org.jbpmext.service.TableListService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bsh.This;


@Service
@Transactional
public class TableListServiceImpl  implements TableListService{
	@Resource
	private TableListDao tableListDao;
	
	@Resource
	
	private CusTabColumnDao cusTabColumnDao;
	
	
	
	
	public boolean saveTable(String formname, String tablename,
			String textArea,String ms) throws Exception {
	   CusTabTable ct =  this.saveTableInfo(formname,tablename,textArea,ms);
	   this.createTable(tablename,truncateHTML(textArea,2),ct);
		return true;
	}

	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
		this.tableListDao.delete(id);
	}
	public CusTabTable saveTableInfo(String formname, String tablename,
			String textArea,String ms){
		
		CusTabTable ct = this.tableListDao.saveTable(formname,tablename,textArea,ms);
		
		return ct;
	}

	public List<CusTabTable> findAllCusTabTables() {
		// TODO Auto-generated method stub
		return tableListDao.findAll();
	}

	public CusTabTable findCusTabTableById(Integer id) {
		// TODO Auto-generated method stub
		return this.tableListDao.findById(id);
	}

	public void findCusTabTablesByHql(String hql) {
		// TODO Auto-generated method stub
		
	}

	public CusTabTable login(CusTabTable cusTabTable) {
		// TODO Auto-generated method stub
		return null;
	}

	public Serializable registerUser(CusTabTable cusTabTable) {
		// TODO Auto-generated method stub
		return null;
	}

	public void update(CusTabTable cusTabTable) {
		// TODO Auto-generated method stub
		
	}

	public boolean createTable(String formname, String tablename,
			String textArea, String ms) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean createTable(String tablename,List truncateHTML,CusTabTable ct) {
		
		String sql = this.cteateSqlByMS(tablename, truncateHTML, ct);
		
		this.tableListDao.insertTbaleColumn(sql);
		// TODO Auto-generated method stub
		return true;
	}
	
	
	private String cteateSqlByMS(String tablename,List truncateHTML, CusTabTable ct){
		 
		 StringBuffer sb = new StringBuffer();
		 sb.append("create table t_"+ct.getTableName());    //创建表名
		 sb.append(" (RID int identity(1,1),");  //自增主键设定
		 sb.append(" uname varchar(255),");  //填写人ID
		 sb.append(" exampleId varchar(255),");  
		 sb.append(" exampleStat varchar(255),");  
		 
//		 List list = this.tableListDao.findByHql("from CusTabTable where tableName="+tablename);
//		 CusTabTable cb = (CusTabTable)list.get(0);
//		 Integer cbid = cb.getTableId();
		 for(int i=0;i<truncateHTML.size();i++){		 
			 sb.append( truncateHTML.get(i));	
			 CusTabColumn  ctc = new CusTabColumn();
			 ctc.setColumnName((String)truncateHTML.get(i));
			 ctc.setTableId(ct.getTableId());
			 this.cusTabColumnDao.add(ctc);
			 sb.append(" varchar(255),");
		 }
 
		 String ms = sb.substring(0, sb.length()-1);
		 ms= ms+")";
		 return ms;
	
	}

	public static List truncateHTML(String content,int len) throws Exception{
		 //URL url = new URL("http://www.baidu.com/");  //test
		
		 //URL url = new URL("www.baidu.com");
	     Document  dirtyDocument = Jsoup.parse(content);   
	     Elements source = dirtyDocument.getElementsByTag("input");
	     Elements selectSource = dirtyDocument.getElementsByTag("select");
	     Elements textarea = dirtyDocument.getElementsByTag("textarea");
	  
	     //遍历 html代码 ，取出所有的Input的 名称 
	     
	     Document clean = Document.createShell(dirtyDocument.baseUri());
	     List<String> list = new ArrayList<String>();
	     for (Element element : source) {   
	            //element.outerHtml() 和  element.toString()效果一样   
	           // System.out.println("input代码：" + element.outerHtml());   
	           // System.out.println("input值：" + element.attr("value") + "  input值：" + element.attr("name"));
	    	 
	    	 if(!list.contains(element.attr("name"))){
	            list.add(element.attr("name"));
	    	 }
	            
	     }
	     for (Element selectelement : selectSource) {   
	            //element.outerHtml() 和  element.toString()效果一样   
	           // System.out.println("input代码：" + element.outerHtml());   
	           // System.out.println("input值：" + element.attr("value") + "  input值：" + element.attr("name"));
	            list.add(selectelement.attr("name"));   
	     }
	     for (Element textareaelement : textarea) {   
	            //element.outerHtml() 和  element.toString()效果一样   
	           // System.out.println("input代码：" + element.outerHtml());   
	           // System.out.println("input值：" + element.attr("value") + "  input值：" + element.attr("name"));
	            list.add(textareaelement.attr("name"));   
	     }
//	      
//	     System.out.println("test");
         Element dest = clean.body();    
//	     truncateHTML(source,dest,len);   
	     return list;   
	}

	//查询表单字段 比对将MAP数据插入数据库
	
	@SuppressWarnings("unchecked")
	public void tableAdd(Map sm,String tableId) {
	     
		//首先查询 cusTabColumn表中内容。查询出该表单所有的字段
		
		List<CusTabColumn> list  = this.cusTabColumnDao.findByHql("from CusTabColumn where tableId="+tableId);
		CusTabColumn c1 = new CusTabColumn();
		c1.setColumnName("exampleId");
		list.add(c1);
		CusTabColumn c2 = new CusTabColumn();
		c2.setColumnName("exampleStat");
		list.add(c2);
		String tablename = this.tableListDao.findById(Integer.valueOf(tableId)).getTableName();
		StringBuffer sb = new StringBuffer();
		sb.append("insert into"+" t_"+tablename+"(");	
		for(int i=0;i<list.size();i++){
			sb.append(list.get(i).getColumnName());
			sb.append(",");		
		}
		sb.deleteCharAt(sb.lastIndexOf(","));
		sb.append(") values(");
        for(int i=0;i<list.size();i++){
          String[] values = (String[])sm.get(list.get(i).getColumnName());	
          if(values.length==0){
        	  sb.append("'");	
    		  sb.append("'");
    		  sb.append(",");
          }else if(values.length==1){ 
		  sb.append("'");	
		  sb.append(values[0]);	
		  sb.append("'");
		  sb.append(",");
          }else if(values.length>1){
        	  sb.append("'");	
        	  for(int j=0;j<values.length;j++){
        		  sb.append(values[j]);	
        		  sb.append(",");		  
        	  }
        	  sb.deleteCharAt(sb.lastIndexOf(","));
        	  sb.append("'");
    		  sb.append(",");
          }
           
		}
        
        sb.deleteCharAt(sb.lastIndexOf(","));
        sb.append(")");
 
        this.tableListDao.insertTbaleColumn(sb.toString());

	}

	public List getTabmeContent(String tablename){	
		return this.tableListDao.getTableContent(tablename);
	
	}   
	
	/**
	 * 
	 * @param tableId   表ID
 	 * @param exampleId  你需要的两个字段	
	 * @param exampleStat 
	 * @param dtableId  动态表单 所要更新的那条记录的ID
	 */
	
	public void updateTableInfo(Integer tableId,String exampleId,String exampleStat,String dtableId){ 	
		   CusTabTable ct = this.tableListDao.findById(tableId);
		   String tablename = ct.getTableName();
		   StringBuffer sb = new StringBuffer();
		   sb.append("update "); 
		   sb.append("t_"+tablename);
		   sb.append(" set exampleId=" +"'"+exampleId+"'," );
		   sb.append("exampleStat="+"'"+ exampleStat+"'" );
		   sb.append("where RID="+dtableId);  
		   System.out.println(sb.toString()+"更新的语句");
		   this.tableListDao.executUpdateSql(sb.toString());

	}

	@Override
	public String appendTableHtml(String tablename, String textArea, String rid) {

		 Map map = this.tableListDao.appendTableHtml(tablename,textArea,rid);		 
		 Document  dirtyDocument = Jsoup.parse(textArea);   
	     Elements source = dirtyDocument.getElementsByTag("input");
	     Elements selectSource = dirtyDocument.getElementsByTag("select");
	     Elements textarea = dirtyDocument.getElementsByTag("textarea");    
	     //遍历 html代码 ，取出所有的Input的 名称 
	     
	     Document clean = Document.createShell(dirtyDocument.baseUri());
	     List<String> list = new ArrayList<String>();
	     for (Element element : source) { 
	    	    
	    	     element.attr("value", (String)map.get(element.attr("name")));
	    	 
	    	     
	    	     System.out.println(element.text());
	    	 
//	    	     element.appendElement("input").attr(attributeKey, attributeValue)
	            //element.outerHtml() 和  element.toString()效果一样   
	           // System.out.println("input代码：" + element.outerHtml());   
	           // System.out.println("input值：" + element.attr("value") + "  input值：" + element.attr("name"));
	    	 
	    	 if(!list.contains(element.attr("name"))){
	            list.add(element.attr("name"));
	    	 }
	            
	     }
	     for (Element selectelement : selectSource) {   
	            //element.outerHtml() 和  element.toString()效果一样   
	           // System.out.println("input代码：" + element.outerHtml());   
	           // System.out.println("input值：" + element.attr("value") + "  input值：" + element.attr("name"));
	            list.add(selectelement.attr("name"));   
	     }
	     for (Element textareaelement : textarea) {

	            //element.outerHtml() 和  element.toString()效果一样   
	           // System.out.println("input代码：" + element.outerHtml());   
	           // System.out.println("input值：" + element.attr("value") + "  input值：" + element.attr("name"));
	            list.add(textareaelement.attr("name"));   
	     }
//	      
//	     System.out.println("test");
        Element dest = clean.body();    
//	     truncateHTML(source,dest,len);   

		 return dirtyDocument.toString();

	}

}

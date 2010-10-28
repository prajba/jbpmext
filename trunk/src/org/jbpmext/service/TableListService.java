package org.jbpmext.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.jbpmext.model.CusTabTable;
import org.jbpmext.model.User;


public interface TableListService {
	
	public Serializable registerUser(CusTabTable cusTabTable);
	public CusTabTable login(CusTabTable cusTabTable);
	public void update(CusTabTable cusTabTable);
	public void delete(Integer tableId);
	public CusTabTable findCusTabTableById(Integer id);
	public List<CusTabTable> findAllCusTabTables();
	public void findCusTabTablesByHql(String hql);
	public boolean saveTable(String formname, String tablename, String textArea,String ms) throws Exception;
	public boolean createTable(String tablename,List truncateHTML,CusTabTable ct);
	public void tableAdd(Map sm,String tableId);
	public List getTabmeContent(String tablename);
	public void updateTableInfo(Integer tableId,String exampleId,String exampleStat,String dtableId);
	public String appendTableHtml(String tablename, String textArea, String rid);

}

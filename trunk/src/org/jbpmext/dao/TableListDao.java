package org.jbpmext.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.jbpmext.model.CusTabTable;
import org.jbpmext.model.User;


public interface TableListDao {
	
	public Serializable add(CusTabTable cusTabTable);
	public CusTabTable findById(Integer id);
	public void delete(Integer id);
	public void update(CusTabTable cusTabTable);
	public List<CusTabTable> findAll();
	public List<CusTabTable> findByHql(String tablename);
//	public boolean createMsqlTable(String sql);
	public CusTabTable saveTable(String formname, String tablename,
			String textArea, String ms);
	public void insertTbaleColumn(String string);
	public List getTableContent(String tablename);
	public void executUpdateSql(String string);
	public Map appendTableHtml(String tablename, String textArea, String rid);


}

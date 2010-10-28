package org.jbpmext.dao;

import java.io.Serializable;
import java.util.List;

import org.jbpmext.model.CusTabAssorDir;
import org.jbpmext.model.CusTabTable;


public interface CusTabAssorDirDao {
	
	public Serializable add(CusTabAssorDir cusTabAssorDir);
	public CusTabAssorDir findById(Integer id);
	public void delete(Integer id);
	public void update(CusTabAssorDir cusTabAssorDir);
	public List<CusTabAssorDir> findAll();
	public List<CusTabAssorDir> findByHql();
	public boolean createMsqlTable(String sql);
//	public boolean saveTable(String formname, String tablename,
//			String textArea, String ms);


}

package org.jbpmext.service;

import java.io.Serializable;
import java.util.List;

import org.jbpmext.model.CusTabAssorDir;



public interface CusTabAssorService {
	
	
	public Serializable registerUser(CusTabAssorDir cusTabAssorDir);
	public CusTabAssorDir login(CusTabAssorDir cusTabAssorDir);
	public void update(CusTabAssorDir cusTabAssorDir);
	public void delete(Integer cusTabId);
	public void findCusTabAssorDirById(Integer id);
	public List<CusTabAssorDir> findAllCusTabAssorDirs();
	public void findCusTabAssorDirByHql(String hql);
//	public boolean saveTable(String formname, String tablename, String textArea,String ms) throws Exception;

}

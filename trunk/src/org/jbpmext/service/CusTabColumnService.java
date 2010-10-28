package org.jbpmext.service;

import java.io.Serializable;
import java.util.List;

import org.jbpmext.model.CusTabColumn;


public interface CusTabColumnService {
	

	public Serializable add(CusTabColumn cusTabColumn);
	public CusTabColumn findById(Integer id);
	public void delete(Integer id);
	public void update(CusTabColumn cusTabColumn);
	public List<CusTabColumn> findAll();
	public List<CusTabColumn> findByHql();
	public boolean createMsqlTable(String sql);

}

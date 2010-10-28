package org.jbpmext.dao;

import java.io.Serializable;
import java.util.List;

import org.jbpmext.model.CusTabColumn;


public interface CusTabColumnDao {
	

	public Serializable add(CusTabColumn cusTabColumn);
	public CusTabColumn findById(Integer id);
	public void delete(Integer id);
	public void update(CusTabColumn cusTabColumn);
	public List<CusTabColumn> findAll();
	public List<CusTabColumn> findByHql(String hql);
	public boolean createMsqlTable(String sql);
	

}

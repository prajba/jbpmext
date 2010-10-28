package org.jbpmext.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.jbpmext.dao.CusTabAssorDirDao;
import org.jbpmext.model.CusTabAssorDir;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Component;
import org.springside.modules.orm.hibernate.HibernateDao;


@Component
public class CusTabAssorDirImpl extends HibernateDao<CusTabAssorDir, Integer> implements CusTabAssorDirDao{

	public Serializable add(CusTabAssorDir cusTabAssorDir) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean createMsqlTable(String sql) {
		// TODO Auto-generated method stub
		return false;
	}

	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

	public List<CusTabAssorDir> findAll() {
		
		
		// TODO Auto-generated method stub
		return null;
	}

	public List<CusTabAssorDir> findByHql() {
		// TODO Auto-generated method stub
		return null;
	}

	public CusTabAssorDir findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void update(CusTabAssorDir cusTabAssorDir) {
		// TODO Auto-generated method stub
		
	}

}

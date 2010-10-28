package org.jbpmext.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.jbpmext.dao.CusTabColumnDao;
import org.jbpmext.model.CusTabColumn;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Component;
import org.springside.modules.orm.hibernate.HibernateDao;


@Component
public class CusTabColumnDaoImpl extends HibernateDao<CusTabColumn, Integer> implements
		CusTabColumnDao {

	public Serializable add(CusTabColumn cusTabColumn) {
		this.save(cusTabColumn);
		return cusTabColumn.getId();
	}

	public boolean createMsqlTable(String sql) {
		// TODO Auto-generated method stub
		return false;
	}

	public void delete(Integer id) {
		// TODO Auto-generated method stub

	}

	public List<CusTabColumn> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<CusTabColumn> findByHql(String hql) {
		// TODO Auto-generated method stub
		return this.find(hql);
	}

	public CusTabColumn findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void update(CusTabColumn cusTabColumn) {
		// TODO Auto-generated method stub

	}
}

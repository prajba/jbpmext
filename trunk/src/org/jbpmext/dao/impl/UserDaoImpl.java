package org.jbpmext.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.jbpmext.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.stereotype.Component;
import org.springside.modules.orm.hibernate.HibernateDao;


@Component
public class UserDaoImpl extends HibernateDao<User, Long> implements
		org.jbpmext.dao.UserDao {

	public Serializable add(User user) {
		return this.add(user);
	}

	public void delete(Long id) {

	}

	public User findById(Long id) {
		return null;
	}

	public List<User> findAll() {
		return null;
	}

	public List<User> findByHql() {
		return null;
	}

	public void update(User user) {

	}

	public boolean createMsqlTable(String sql) {
		boolean creatsql = false;
		try {
			SingleConnectionDataSource ds = new SingleConnectionDataSource();
			ds.setDriverClassName("net.sourceforge.jtds.jdbc.Driver");
			ds.setUrl("jdbc:jtds:sqlserver://localhost:1433/customtable");
			ds.setUsername("sa");
			ds.setPassword("123");
			JdbcTemplate jt = new JdbcTemplate(ds);

			jt.execute(sql);
			ds.destroy();
			creatsql = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return creatsql;
	}

}

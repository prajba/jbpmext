/**
 * 
 */
package org.jbpmext.dao.h3;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.jbpmext.dao.TermedDAO;
import org.jbpmext.model.Termed;
import org.jbpmext.model.UsableStatuses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Component;

/**
 * @author weiht
 *
 */
@SuppressWarnings({"rawtypes", "unchecked"})
@Component("dao")
public class H3TermedDAO extends HibernateDaoSupport implements TermedDAO {
	@Override
	public void initTermed(Termed t) {
		Date now = new Date();
		t.setBeginTime(now);
		t.setUsableStatus(UsableStatuses.NORMAL);
	}

	@Override
	public void removeTermed(Termed t) {
		Date now = new Date();
		t.setEndTime(now);
		t.setUsableStatus(UsableStatuses.DISABLED);
		getHibernateTemplate().update(t);
	}

	@Override
	public void terminate(Termed t, Date endTime) {
		t.setEndTime(endTime);
		t.setUsableStatus(UsableStatuses.SCHEDULE_TERMED);
		getHibernateTemplate().update(t);
	}

	@Override
	public Serializable add(Object o) {
		return getHibernateTemplate().save(o);
	}

	@Override
	public void update(Object o) {
		getHibernateTemplate().update(o);
	}

	@Override
	public void remove(Object o) {
		getHibernateTemplate().delete(o);
	}

	@Override
	public Object get(Class cls, Serializable id) {
		return getHibernateTemplate().get(cls, id);
	}

	@Override
	public List find(String statement) {
		return getHibernateTemplate().find(statement);
	}

	@Override
	public List find(String statement, Object... parameters) {
		return getHibernateTemplate().find(statement, parameters);
	}
	
	@Autowired
	public void setFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}
}

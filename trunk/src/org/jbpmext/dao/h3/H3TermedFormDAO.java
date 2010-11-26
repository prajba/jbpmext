/**
 * 
 */
package org.jbpmext.dao.h3;

import java.io.Serializable;

import org.jbpmext.dao.TermedFormDAO;
import org.springframework.stereotype.Component;

/**
 * @author weiht
 *
 */
@Component("termedFormDao")
public class H3TermedFormDAO extends H3TermedDAO implements TermedFormDAO {
	@Override
	public Serializable add(String entityName, Object o) {
		return getHibernateTemplate().save(entityName, o);
	}

	@Override
	public void update(String entityName, Object o) {
		getHibernateTemplate().update(entityName, o);
	}

	@Override
	public void remove(String entityName, Object o) {
		getHibernateTemplate().delete(entityName, o);
	}

	@Override
	public Object get(String entityName, Serializable id) {
		return getHibernateTemplate().get(entityName, id);
	}
}

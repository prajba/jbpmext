/**
 * 
 */
package org.jbpmext.dao;

import java.io.Serializable;
import java.util.List;

/**
 * @author weiht
 *
 */
@SuppressWarnings("rawtypes")
public interface DAO {
	public Serializable add(Object o);
	public void update(Object o);
	public void remove(Object o);
	public Object get(Class cls, Serializable id);
	public List find(String statement);
	public List find(String statement, Object... parameters);
}

/**
 * 
 */
package org.jbpmext.dao;

import java.io.Serializable;

/**
 * @author weiht
 *
 */
public interface FormDAO extends DAO {
	public Serializable add(String entityName, Object o);
	public void update(String entityName, Object o);
	public void remove(String entityName, Object o);
	public Object get(String entityName, Serializable id);
}

/**
 * 
 */
package org.jbpmext.dao;

import java.util.Date;

import org.jbpmext.model.Termed;

/**
 * Dedicated to processing Termed objects.
 * 
 * @author weiht
 *
 */
public interface TermedDAO extends DAO {
	/**
	 * Initialize termed object.
	 * 
	 * @param t Object to initialize.
	 */
	public void initTermed(Termed t);
	
	/**
	 * Remove a termed object.
	 * 
	 * @param t Object to remove.
	 */
	public void removeTermed(Termed t);
	
	/**
	 * Terminate a termed object, marking it unusable after a specified time.
	 * 
	 * @param t Object to terminate.
	 * @param endTime Time for termination.
	 */
	public void terminate(Termed t, Date endTime);
}

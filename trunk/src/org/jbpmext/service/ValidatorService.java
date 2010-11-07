/**
 * 
 */
package org.jbpmext.service;

import java.util.List;

import org.jbpmext.dao.TermedDAO;
import org.jbpmext.model.UserValidator;

/**
 * @author weiht
 *
 */
public interface ValidatorService {
	public void setDao(TermedDAO dao);
	public List<UserValidator> findAllValidators();
}

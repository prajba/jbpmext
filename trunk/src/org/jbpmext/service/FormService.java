/**
 * 
 */
package org.jbpmext.service;

import java.util.List;

import org.jbpmext.dao.TermedDAO;
import org.jbpmext.model.MetaForm;

/**
 * @author weiht
 *
 */
public interface FormService {
	public void setDao(TermedDAO dao);
	public List<MetaForm> findAllForms();
}

/**
 * 
 */
package org.jbpmext.service.h3;

import org.hibernate.SessionFactory;
import org.jbpmext.dao.h3.H3TermedFormDAO;
import org.jbpmext.model.MetaForm;
import org.jbpmext.service.FormTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author weiht
 *
 */
@Component("formTableService")
public class FormTableServiceH3 implements FormTableService {
	private H3TermedFormDAO formDao;
	private SessionFactory sessionFactory;

	private void reloadSessionFactory() {
		if (sessionFactory == null || formDao == null) return;
	}
	
	@Override
	public void saveFormTable(MetaForm form) {
		
	}

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		reloadSessionFactory();
	}

	@Autowired
	public void setFormDao(H3TermedFormDAO formDao) {
		this.formDao = formDao;
		reloadSessionFactory();
	}
}

/**
 * 
 */
package org.jbpmext.service.h3;

import java.util.List;

import org.jbpmext.dao.TermedDAO;
import org.jbpmext.model.MetaForm;
import org.jbpmext.service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author weiht
 *
 */
@Component("metaformService")
public class FormServiceH3 implements FormService {
	private static final String HQL_FIND_ALL = "from MetaForm f";
	
	private TermedDAO dao;
	
	@Override
	@Autowired
	public void setDao(TermedDAO dao) {
		this.dao = dao;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MetaForm> findAllForms() {
		return dao.find(HQL_FIND_ALL);
	}
}

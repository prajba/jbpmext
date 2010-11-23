/**
 * 
 */
package org.jbpmext.service.h3;

import java.util.List;

import org.jbpmext.dao.TermedDAO;
import org.jbpmext.model.MetaField;
import org.jbpmext.model.MetaForm;
import org.jbpmext.service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author weiht
 *
 */
@Component("metaformService")
@Transactional
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

	@Override
	public void save(MetaForm form) {
		if (form.getId() == null) {
			dao.initTermed(form);
			dao.add(form);
		} else {
			dao.update(form);
		}
		List<MetaField> flds = form.getFields();
		if (flds != null && !flds.isEmpty()) {
			for (MetaField f: flds) {
				f.setForm(form);
				if (f.getId() == null) {
					dao.add(f);
				} else {
					dao.update(f);
				}
			}
		}
	}
}

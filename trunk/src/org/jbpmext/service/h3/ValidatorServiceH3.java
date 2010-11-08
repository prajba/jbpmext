/**
 * 
 */
package org.jbpmext.service.h3;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jbpmext.dao.TermedDAO;
import org.jbpmext.model.UsableStatuses;
import org.jbpmext.model.UserValidator;
import org.jbpmext.service.ValidatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author weiht
 *
 */
@Component("validatorService")
@Transactional
public class ValidatorServiceH3 implements ValidatorService {
	private static final Logger logger = LogManager.getLogger(ValidatorServiceH3.class);
	private static final String HQL_FIND_ALL = "from UserValidator v where v.usableStatus = ?";
	
	private TermedDAO dao;

	public TermedDAO getDao() {
		return dao;
	}

	@Override
	@Autowired
	public void setDao(TermedDAO dao) {
		this.dao = dao;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserValidator> findAllValidators() {
		logger.debug(HQL_FIND_ALL);
		return dao.find(HQL_FIND_ALL, UsableStatuses.NORMAL);
	}

	@Override
	public void save(UserValidator validator) {
		if (validator.getId() != null)
			dao.update(validator);
		else
			dao.add(validator);
	}
}

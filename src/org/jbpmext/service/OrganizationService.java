/**
 * 
 */
package org.jbpmext.service;

import java.util.List;

import org.jbpmext.dao.TermedDAO;
import org.jbpmext.model.Organization;

/**
 * @author weiht
 *
 */
public interface OrganizationService {
	public void setDao(TermedDAO dao);
	public List<Organization> getRoot();
	public List<Organization> getChildOrgs(int parent);
}

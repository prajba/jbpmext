/**
 * 
 */
package org.jbpmext.service;

import java.util.List;
import java.util.Map;

import org.jbpmext.dao.TermedDAO;
import org.jbpmext.model.DictCategory;
import org.jbpmext.model.DictEntry;

/**
 * @author weiht
 *
 */
public interface DictionaryService {
	public void setDao(TermedDAO dao);
	public List<DictCategory> listCategories();
	public void saveCategory(DictCategory cat);
	@SuppressWarnings("rawtypes")
	public List listEntries(Integer catId);
	public void saveEntry(Integer catId, DictEntry entry);
}

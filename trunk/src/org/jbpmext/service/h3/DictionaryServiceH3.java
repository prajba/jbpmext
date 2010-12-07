/**
 * 
 */
package org.jbpmext.service.h3;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.jbpmext.dao.FormDAO;
import org.jbpmext.dao.TermedDAO;
import org.jbpmext.model.DictCategory;
import org.jbpmext.model.DictEntry;
import org.jbpmext.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * @author weiht
 *
 */
@Component("dictionaryService")
@Transactional
public class DictionaryServiceH3 implements DictionaryService {
	private static final Logger logger = LogManager.getLogger(DictionaryServiceH3.class);
	private static final String HQL_LIST_CATEGORIES = "from DictCategory d";
	private static final String HQL_CAT_TABLE_NAME = "select d.displayName from DictCategory d where d.id = ?";
	private static final String mappingTemplateLocation = "/WEB-INF/classes/freemarker/";
	
	private TermedDAO dao;
	@Autowired
	private FormDAO dictDao;
	@Autowired
	private SessionFactoryConfig factoryConfig;
	
	@Override
	@Autowired
	public void setDao(TermedDAO dao) {
		this.dao = dao;
		initDictTables();
	}

	private void initDictTables() {
		List<DictCategory> cats = listCategories();
		try {
			Map<DictCategory, String> xmls;
			xmls = catsToMappingStrings(cats);
			factoryConfig.addDictCatMappingXmls(xmls);
		} catch (Exception e) {
			logger.error("Initializing dicts:", e);
			throw new HibernateException("Error initializing dicts.", e);
		}
	}

	private Map<DictCategory, String> catsToMappingStrings(
			List<DictCategory> cats) throws IOException, TemplateException {
		Template temp = initMappingTemplate();
		Map<DictCategory, String> result = new HashMap<DictCategory, String>(cats.size());
		for (DictCategory c: cats) {
			result.put(c, catToMappingString(c, temp));
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DictCategory> listCategories() {
		return dao.find(HQL_LIST_CATEGORIES);
	}

	@Override
	public void saveCategory(DictCategory cat) {
		if (cat.getId() == null) {
			dao.add(cat);
			saveCategoryTable(cat);
		} else {
			dao.update(cat);
		}
	}

	private void saveCategoryTable(DictCategory cat) {
		try {
			Template temp = initMappingTemplate();
			String s = catToMappingString(cat, temp);
			factoryConfig.addDictCatMappingXml(cat, s);
		} catch (Exception ex) {
			logger.error("Saving form mapping xml file:", ex);
			throw new HibernateException("Error saving table structure.", ex);
		}
	}

	private String catToMappingString(DictCategory cat, Template temp)
	throws TemplateException, IOException {
		StringWriter w = new StringWriter();
		temp.process(cat, w);
		String s = w.toString();
		logger.debug(w);
		return s;
	}

	private Template initMappingTemplate() throws IOException {
		Configuration config = new Configuration();
		config.setDirectoryForTemplateLoading(new File(ServletActionContext
				.getServletContext().getRealPath(mappingTemplateLocation)));
		Template temp = config.getTemplate("cat-to-table.tpl", "utf-8");
		return temp;
	}

	@SuppressWarnings({ "rawtypes" })
	@Override
	public List listEntries(Integer catId) {
		String name = getCategoryName(catId);
		return dao.find("from " + name + " c where c.usableStatus = 0");
	}

	@SuppressWarnings({ "rawtypes" })
	private String getCategoryName(Integer catId) {
		List l = dao.find(HQL_CAT_TABLE_NAME, catId);
		if (l == null || l.isEmpty()) {
			throw new HibernateException("No mapping found for category id: " + catId);
		}
		String name = (String)l.get(0);
		return name;
	}

	@Override
	public void saveEntry(Integer catId, DictEntry entry) {
		String name = getCategoryName(catId);
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("key", entry.getKey());
		Object v = entry.getIntValue();
		if (v == null) v = entry.getStringValue();
		m.put("value", v);
		m.put("usableStatus", entry.getUsableStatus());
		Integer id = entry.getId();
		if (id == null) {
			dictDao.add(name, m);
			entry.setId((Integer)m.get("id"));
		} else {
			m.put("id", id);
			dictDao.update(name, m);
		}
	}
}

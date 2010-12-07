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
import org.jbpmext.dao.TermedDAO;
import org.jbpmext.model.MetaField;
import org.jbpmext.model.MetaForm;
import org.jbpmext.service.FormService;
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
@Component("metaformService")
@Transactional
public class FormServiceH3 implements FormService {
	private static final Logger logger = LogManager.getLogger(FormServiceH3.class);
	private static final String HQL_FIND_ALL = "from MetaForm f";
	private static final String mappingTemplateLocation = "/WEB-INF/classes/freemarker/";
	
	private TermedDAO dao;
	@Autowired
	private SessionFactoryConfig factoryConfig;
	
	@Override
	@Autowired
	public void setDao(TermedDAO dao) {
		this.dao = dao;
		initFormTables();
	}

	private void initFormTables() {
		List<MetaForm> forms = findAllForms();
		try {
			Map<MetaForm, String> xmls;
			xmls = formsToMappingStrings(forms);
			factoryConfig.addFormMappingXmls(xmls);
		} catch (Exception e) {
			logger.error("Initializing forms:", e);
			throw new HibernateException("Error initializing forms.", e);
		}
	}

	private Map<MetaForm, String> formsToMappingStrings(List<MetaForm> forms) throws IOException, TemplateException {
		Template temp = initMappingTemplate();
		Map<MetaForm, String> result = new HashMap<MetaForm, String>(forms.size());
		for (MetaForm f: forms) {
			result.put(f, formToMappingString(f, temp));
		}
		return result;
	}

	private void saveFormTable(MetaForm form) {
		try {
			Template temp = initMappingTemplate();
			String s = formToMappingString(form, temp);
			factoryConfig.addFormMappingXml(form, s);
		} catch (Exception ex) {
			logger.error("Saving form mapping xml file:", ex);
			throw new HibernateException("Error saving table structure.", ex);
		}
	}

	private String formToMappingString(MetaForm form, Template temp)
			throws IOException, TemplateException {
		StringWriter w = new StringWriter();
		temp.process(form, w);
		String s = w.toString();
		logger.debug(w);
		return s;
	}

	private Template initMappingTemplate()
			throws IOException {
		Configuration config = new Configuration();
		config.setDirectoryForTemplateLoading(new File(ServletActionContext
				.getServletContext().getRealPath(mappingTemplateLocation)));
		Template temp = config.getTemplate("form-to-table.tpl", "utf-8");
		return temp;
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
		saveFormTable(form);
	}
}

/**
 * 
 */
package org.jbpmext.service.h3;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.mapping.PersistentClass;
import org.hibernate.mapping.Property;
import org.jbpmext.model.DictCategory;
import org.jbpmext.model.MetaField;
import org.jbpmext.model.MetaForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
/**
 * @author weiht
 *
 */
@Component("sessionFactoryConfig")
public class SessionFactoryConfig {
	private static final Logger logger = LogManager.getLogger(SessionFactoryConfig.class);
	
	private SessionFactory factory;
	@Autowired
	private AnnotationSessionFactory bean;

	public SessionFactory getFactory() {
		if (factory == null)
			rebuildFactory();
		return factory;
	}
	
	@SuppressWarnings("rawtypes")
	private void rebuildFactory() {
		if (logger.isDebugEnabled()) {
			logger.debug("Data source: " + bean.getDataSource());
			logger.debug(bean.getConfiguration().getProperties());
			
			logger.debug("=============class mappings=============");
			Iterator it = bean.getConfiguration().getClassMappings();
			while (it.hasNext()) {
				logger.debug(it.next());
			}
			
			logger.debug("=============table mappings=============");
			it = bean.getConfiguration().getTableMappings();
			while (it.hasNext()) {
				logger.debug(it.next());
			}
			logger.debug("========================================");
		}
		factory = bean.getConfiguration().buildSessionFactory();
	}
	
	public void addFormMappingXml(MetaForm form, String xml) {
		doAddFormMappingXml(form, xml);
		rebuildFactory();
	}

	private void doAddFormMappingXml(MetaForm form, String xml) {
		PersistentClass m = bean.getConfiguration().getClassMapping(form.getFormName());
		if (m == null) {
			if (logger.isDebugEnabled())
				logger.debug("Add xml mapping: mapping named [" + form.getFormName() + "] not found.");
			bean.getConfiguration().addXML(xml);
		} else {
			for (MetaField f: form.getFields()) {
				if (!hasProperty(m, f.getFieldName())) {
					Property p = new Property();
					p.setName(f.getFieldName());
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("column", f.getColumnName());
					map.put("type", f.getDataType());
					if ("string".equals(f.getDataType()))
						map.put("length", 200);
					m.setMetaAttributes(map);
					m.addProperty(p);
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	private boolean hasProperty(PersistentClass cls, String fieldName) {
		Iterator<Property> it = cls.getPropertyIterator();
		while (it.hasNext()) {
			if (it.next().getName().equals(fieldName))
				return true;
		}
		return false;
	}

	public void addFormMappingXmls(Map<MetaForm, String> xmls) {
		for (Entry<MetaForm, String> xml: xmls.entrySet()) {
			doAddFormMappingXml(xml.getKey(), xml.getValue());
		}
		rebuildFactory();
	}
	
	public void addDictCatMappingXml(DictCategory cat, String xml) {
		doAddDictCatMappingXml(cat, xml);
		rebuildFactory();
	}

	private void doAddDictCatMappingXml(DictCategory cat, String xml) {
		PersistentClass m = bean.getConfiguration().getClassMapping(cat.getDisplayName());
		if (m == null) {
			if (logger.isDebugEnabled())
				logger.debug("Add xml mapping: mapping named [" + cat.getDisplayName() + "] not found.");
			bean.getConfiguration().addXML(xml);
		}
	}
	
	public void addDictCatMappingXmls(Map<DictCategory, String> xmls) {
		for (Entry<DictCategory, String> xml: xmls.entrySet()) {
			doAddDictCatMappingXml(xml.getKey(), xml.getValue());
		}
		rebuildFactory();
	}
}

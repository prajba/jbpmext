/**
 * 
 */
package org.jbpmext.service.h3;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
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
	
	private void rebuildFactory() {
		if (logger.isDebugEnabled()) {
			logger.debug("Data source: " + bean.getDataSource());
			logger.debug(bean.getConfiguration().getProperties());
		}
		factory = bean.getConfiguration().buildSessionFactory();
	}
	
	public void addXml(String xml) {
		bean.getConfiguration().addXML(xml);
		rebuildFactory();
	}
	
	public void addXmls(List<String> xmls) {
		Configuration config = bean.getConfiguration();
		for (String xml: xmls) {
			config.addXML(xml);
		}
		rebuildFactory();
	}
}

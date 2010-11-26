/**
 * 
 */
package org.jbpmext.service.h3;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;

/**
 * @author weiht
 *
 */
public class AnnotationSessionFactory extends AnnotationSessionFactoryBean {
	private List<String> oldMappingResources = new ArrayList<String>();
	
	@Override
	public void setMappingResources(String[] mappingResources) {
		mergeResources(mappingResources);
		super.setMappingResources(oldMappingResources.toArray(new String[oldMappingResources.size()]));
	}

	private void mergeResources(String[] mappingResources) {
		for (String r: mappingResources) {
			if (r != null && oldMappingResources.indexOf(r) < 0)
				oldMappingResources.add(r);
		}
	}

	@Override
	protected SessionFactory newSessionFactory(Configuration config)
			throws HibernateException {
		return new SessionFactoryWrapper(config);
	}
}

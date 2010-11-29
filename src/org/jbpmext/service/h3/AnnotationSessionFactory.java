/**
 * 
 */
package org.jbpmext.service.h3;

import javax.sql.DataSource;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;

/**
 * @author weiht
 *
 */
public class AnnotationSessionFactory extends AnnotationSessionFactoryBean {
	@Autowired
	private SessionFactory factoryWrapper;
	@Autowired
	private SessionFactoryConfig conf;
	
	private static DataSource sharedDataSource;
	
	public static DataSource getSharedDataSource() {
		return sharedDataSource;
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		super.afterPropertiesSet();
		getConfiguration().setProperty(Environment.CONNECTION_PROVIDER,
				SharedConnectionProvider.class.getCanonicalName());
	}

	@Override
	public void setDataSource(DataSource dataSource) {
		sharedDataSource = dataSource;
		super.setDataSource(dataSource);
	}
	
	@Autowired
	public void setFactoryWrapper(SessionFactoryWrapper factoryWrapper) {
		this.factoryWrapper = factoryWrapper;
	}

	@Override
	protected SessionFactory newSessionFactory(Configuration config)
			throws HibernateException {
		return factoryWrapper;
	}
}

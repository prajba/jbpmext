/**
 * 
 */
package org.jbpmext.service.h3;

import java.io.Serializable;
import java.sql.Connection;
import java.util.Map;
import java.util.Set;

import javax.naming.NamingException;
import javax.naming.Reference;

import org.hibernate.HibernateException;
import org.hibernate.Interceptor;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.hibernate.engine.FilterDefinition;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.metadata.CollectionMetadata;
import org.hibernate.stat.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author weiht
 *
 */
@SuppressWarnings({"serial", "rawtypes"})
@Component("sessionFactoryWrapper")
public class SessionFactoryWrapper implements SessionFactory {
	private SessionFactoryConfig factoryConfig;
	
	@Autowired
	public void setFactoryConfig(SessionFactoryConfig factoryConfig) {
		this.factoryConfig = factoryConfig;
	}

	public void close() throws HibernateException {
		
		factoryConfig.getFactory().close();
	}

	public void evict(Class arg0, Serializable arg1) throws HibernateException {
		factoryConfig.getFactory().evict(arg0, arg1);
	}

	public void evict(Class arg0) throws HibernateException {
		factoryConfig.getFactory().evict(arg0);
	}

	public void evictCollection(String arg0, Serializable arg1)
			throws HibernateException {
		factoryConfig.getFactory().evictCollection(arg0, arg1);
	}

	public void evictCollection(String arg0) throws HibernateException {
		factoryConfig.getFactory().evictCollection(arg0);
	}

	public void evictEntity(String arg0, Serializable arg1)
			throws HibernateException {
		factoryConfig.getFactory().evictEntity(arg0, arg1);
	}

	public void evictEntity(String arg0) throws HibernateException {
		factoryConfig.getFactory().evictEntity(arg0);
	}

	public void evictQueries() throws HibernateException {
		factoryConfig.getFactory().evictQueries();
	}

	public void evictQueries(String arg0) throws HibernateException {
		factoryConfig.getFactory().evictQueries(arg0);
	}

	public Map getAllClassMetadata() throws HibernateException {
		return factoryConfig.getFactory().getAllClassMetadata();
	}

	public Map getAllCollectionMetadata() throws HibernateException {
		return factoryConfig.getFactory().getAllCollectionMetadata();
	}

	public ClassMetadata getClassMetadata(Class arg0) throws HibernateException {
		return factoryConfig.getFactory().getClassMetadata(arg0);
	}

	public ClassMetadata getClassMetadata(String arg0)
			throws HibernateException {
		return factoryConfig.getFactory().getClassMetadata(arg0);
	}

	public CollectionMetadata getCollectionMetadata(String arg0)
			throws HibernateException {
		return factoryConfig.getFactory().getCollectionMetadata(arg0);
	}

	public Session getCurrentSession() throws HibernateException {
		return factoryConfig.getFactory().getCurrentSession();
	}

	public Set getDefinedFilterNames() {
		return factoryConfig.getFactory().getDefinedFilterNames();
	}

	public FilterDefinition getFilterDefinition(String arg0)
			throws HibernateException {
		return factoryConfig.getFactory().getFilterDefinition(arg0);
	}

	public Reference getReference() throws NamingException {
		return factoryConfig.getFactory().getReference();
	}

	public Statistics getStatistics() {
		return factoryConfig.getFactory().getStatistics();
	}

	public boolean isClosed() {
		return factoryConfig.getFactory().isClosed();
	}

	public Session openSession() throws HibernateException {
		return factoryConfig.getFactory().openSession();
	}

	public Session openSession(Connection arg0, Interceptor arg1) {
		return factoryConfig.getFactory().openSession(arg0, arg1);
	}

	public Session openSession(Connection arg0) {
		return factoryConfig.getFactory().openSession(arg0);
	}

	public Session openSession(Interceptor arg0) throws HibernateException {
		return factoryConfig.getFactory().openSession(arg0);
	}

	public StatelessSession openStatelessSession() {
		return factoryConfig.getFactory().openStatelessSession();
	}

	public StatelessSession openStatelessSession(Connection arg0) {
		return factoryConfig.getFactory().openStatelessSession(arg0);
	}
}

/**
 * 
 */
package org.jbpmext.service.h3;

import java.io.File;
import java.io.FileFilter;
import java.io.Serializable;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import javax.naming.NamingException;
import javax.naming.Reference;

import org.apache.struts2.ServletActionContext;
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

/**
 * @author weiht
 *
 */
@SuppressWarnings({"serial", "rawtypes"})
public class SessionFactoryWrapper implements SessionFactory {
	public static final String KEY_WATCHING_DIR = "watching_dir";
	public static final String KEY_WATCHING_INTERVAL = "watching_interval";
	private SessionFactory innerFactory;
	private Configuration config;
	private String watchingDir;
	private Map<String, Long> timestamps;
	private int watchingInterval = 10000;
	private Timer watchingTimer;
	
	public SessionFactoryWrapper(Configuration config) {
		this.config = config;
		watchingDir = config.getProperty(KEY_WATCHING_DIR);
		timestamps = new HashMap<String, Long>();
		try {
			watchingInterval = Integer.parseInt(config.getProperty(KEY_WATCHING_INTERVAL));
		} catch (NumberFormatException e) {
		}
		watchDir();
		reloadInnerFactory();
	}

	private void watchDir() {
		watchingTimer = new Timer(true);
		watchingTimer.schedule(new TimerTask() {
			@Override
			public void run() {
				if (mappingFilesChanged())
					reloadInnerFactory();
			}
		}, watchingInterval);
	}
	
	private boolean mappingFilesChanged() {
		File[] files = new File(
				ServletActionContext.getServletContext().getRealPath(watchingDir)
			).listFiles(new FileFilter() {
				@Override
				public boolean accept(File f) {
					return f.isFile() && f.getName().endsWith(".hbm.xml");
				}
			});
		boolean result = false;
		for (File f: files) {
			String fn = f.getName();
			Long l = timestamps.get(fn);
			Long n = null;
			if (l == null || !l.equals(n = f.lastModified()))
				result = true;
			timestamps.put(fn, n);
		}
		return result;
	}

	private void reloadInnerFactory() {
		for (Entry<String, Long> e: timestamps.entrySet()) {
			if (e.getValue() != null)
				config.addFile(e.getKey());
		}
		SessionFactory sf = config.buildSessionFactory();
		innerFactory = sf;
	}

	public void close() throws HibernateException {
		innerFactory.close();
	}

	public void evict(Class arg0, Serializable arg1) throws HibernateException {
		innerFactory.evict(arg0, arg1);
	}

	public void evict(Class arg0) throws HibernateException {
		innerFactory.evict(arg0);
	}

	public void evictCollection(String arg0, Serializable arg1)
			throws HibernateException {
		innerFactory.evictCollection(arg0, arg1);
	}

	public void evictCollection(String arg0) throws HibernateException {
		innerFactory.evictCollection(arg0);
	}

	public void evictEntity(String arg0, Serializable arg1)
			throws HibernateException {
		innerFactory.evictEntity(arg0, arg1);
	}

	public void evictEntity(String arg0) throws HibernateException {
		innerFactory.evictEntity(arg0);
	}

	public void evictQueries() throws HibernateException {
		innerFactory.evictQueries();
	}

	public void evictQueries(String arg0) throws HibernateException {
		innerFactory.evictQueries(arg0);
	}

	public Map getAllClassMetadata() throws HibernateException {
		return innerFactory.getAllClassMetadata();
	}

	public Map getAllCollectionMetadata() throws HibernateException {
		return innerFactory.getAllCollectionMetadata();
	}

	public ClassMetadata getClassMetadata(Class arg0) throws HibernateException {
		return innerFactory.getClassMetadata(arg0);
	}

	public ClassMetadata getClassMetadata(String arg0)
			throws HibernateException {
		return innerFactory.getClassMetadata(arg0);
	}

	public CollectionMetadata getCollectionMetadata(String arg0)
			throws HibernateException {
		return innerFactory.getCollectionMetadata(arg0);
	}

	public Session getCurrentSession() throws HibernateException {
		return innerFactory.getCurrentSession();
	}

	public Set getDefinedFilterNames() {
		return innerFactory.getDefinedFilterNames();
	}

	public FilterDefinition getFilterDefinition(String arg0)
			throws HibernateException {
		return innerFactory.getFilterDefinition(arg0);
	}

	public Reference getReference() throws NamingException {
		return innerFactory.getReference();
	}

	public Statistics getStatistics() {
		return innerFactory.getStatistics();
	}

	public boolean isClosed() {
		return innerFactory.isClosed();
	}

	public Session openSession() throws HibernateException {
		return innerFactory.openSession();
	}

	public Session openSession(Connection arg0, Interceptor arg1) {
		return innerFactory.openSession(arg0, arg1);
	}

	public Session openSession(Connection arg0) {
		return innerFactory.openSession(arg0);
	}

	public Session openSession(Interceptor arg0) throws HibernateException {
		return innerFactory.openSession(arg0);
	}

	public StatelessSession openStatelessSession() {
		return innerFactory.openStatelessSession();
	}

	public StatelessSession openStatelessSession(Connection arg0) {
		return innerFactory.openStatelessSession(arg0);
	}
}

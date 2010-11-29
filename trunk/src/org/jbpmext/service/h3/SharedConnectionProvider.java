/**
 * 
 */
package org.jbpmext.service.h3;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.HibernateException;
import org.hibernate.connection.ConnectionProvider;

/**
 * @author weiht
 *
 */
public class SharedConnectionProvider implements ConnectionProvider {
	private DataSource dataSource;
	
	@Override
	public void close() throws HibernateException {
	}

	@Override
	public void closeConnection(Connection conn) throws SQLException {
		conn.close();
	}

	@Override
	public void configure(Properties props) throws HibernateException {
		this.dataSource = AnnotationSessionFactory.getSharedDataSource();
	}

	@Override
	public Connection getConnection() throws SQLException {
		return this.dataSource.getConnection();
	}

	@Override
	public boolean supportsAggressiveRelease() {
		return false;
	}

}

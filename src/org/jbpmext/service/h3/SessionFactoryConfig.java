/**
 * 
 */
package org.jbpmext.service.h3;

import org.hibernate.cfg.Configuration;

/**
 * @author weiht
 *
 */
public class SessionFactoryConfig {
	private Configuration config;
	
	public SessionFactoryConfig(Configuration config) {
		this.config = config;
	}
}

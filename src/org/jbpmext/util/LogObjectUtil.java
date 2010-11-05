/**
 * 
 */
package org.jbpmext.util;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * @author weiht
 *
 */
public class LogObjectUtil {
	private static final Logger logger = LogManager.getLogger(LogObjectUtil.class);
	
	private LogObjectUtil() {}
	
	public static void logObject(Object o) {
		if (logger.isDebugEnabled()) {
			logger.debug(o);
		}
	}
	
	@SuppressWarnings("rawtypes")
	public static void logIterable(Iterable it) {
		if (logger.isDebugEnabled()) {
			logger.debug("Iterable: [----------------");
			if (it == null) {
				logger.debug("null");
			} else {
				for (Object o: it) {
					logObject(o);
				}
			}
			logger.debug("----------------] End of iterable.");
		}
	}
}

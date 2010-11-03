/**
 * 
 */
package org.jbpmext.model;

import java.util.Date;

/**
 * @author weiht
 *
 */
public interface Termed extends Usable {
	public Date getBeginTime();
	public void setBeginTime(Date dt);
	public Date getEndTime();
	public void setEndTime(Date dt);
}

/**
 * 
 */
package org.jbpmext.dao.h3;

import java.util.Date;

import org.jbpmext.dao.TermedDAO;
import org.jbpmext.model.Termed;
import org.jbpmext.model.UsableStatuses;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * @author weiht
 *
 */
public class H3TermedDAO extends HibernateDaoSupport implements TermedDAO {
	@Override
	public void initTermed(Termed t) {
		Date now = new Date();
		t.setBeginTime(now);
		t.setUsableStatus(UsableStatuses.NORMAL);
	}

	@Override
	public void removeTermed(Termed t) {
		Date now = new Date();
		t.setEndTime(now);
		t.setUsableStatus(UsableStatuses.DISABLED);
		getHibernateTemplate().update(t);
	}

	@Override
	public void terminate(Termed t, Date endTime) {
		t.setEndTime(endTime);
		t.setUsableStatus(UsableStatuses.SCHEDULE_TERMED);
		getHibernateTemplate().update(t);
	}
}

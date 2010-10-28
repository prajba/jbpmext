package org.jbpmext.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.jbpmext.dao.GenericDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Component;
import org.springside.modules.orm.hibernate.HibernateDao;

@Component
public class HibernateBaseDaoImpl<T, ID extends Serializable> extends
		HibernateDaoSupport implements GenericDao<T, ID> {
	private Class<T> persistentClass = null;

	@Resource
	public void setSessionFactory2(SessionFactory sessionFactory) {
		this.setSessionFactory(sessionFactory);
	}

	protected Query getQuery(Session session, String cmd) {
		Query query;
		try {
			query = session.getNamedQuery(cmd);// 创建xml中声明的query名称
		} catch (HibernateException e) {
			try {
				query = session.createQuery(cmd);// 否则创建Hibernate的SQL
			} catch (HibernateException ex) {
				query = session.createSQLQuery(cmd);// 否则创建数据库SQL
			}
		}
		return query;
	}

	/*
	 * Cmd parameter is the named query's name. Please refer to Hibernate Named
	 * Query.
	 * 
	 * @see com.zybedu.dao.FindCmdDao#findCmd(java.lang.String,
	 * java.lang.Object[], int, int)
	 */
	@SuppressWarnings("unchecked")
	public List findListByCmd(final String cmd, final int page,
			final int count, final Object... params) {
		List result = null;
		Object o = this.getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				Query query = getQuery(session, cmd);
				if (params != null) {
					int len = params.length;
					for (int i = 0; i < len; i++) {
						query.setParameter(i, params[i]);
					}
				}
				if (page > 0) {
					int maxResult = count;
					if (count < 1)
						maxResult = 1;
					int first = (page - 1) * maxResult;
					query.setFirstResult(first);
					query.setMaxResults(maxResult);
				} else {

				}
				List list = query.list();
				org.hibernate.Hibernate.initialize(list);
				return list;
			}
		});
		if (o != null)
			result = (List) o;
		return result;
	}

	public void delete(T entity) {
		this.getHibernateTemplate().delete(entity);
		this.getSession().flush();
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		List list = this.getHibernateTemplate().loadAll(
				this.getPersistentClass());
		org.hibernate.Hibernate.initialize(list);
		return list;
	}

	public T loadObjectById(ID id) {
		return (T) this.getHibernateTemplate().get(this.getPersistentClass(),
				id);
	}

	public T saveOrUpdate(T entity) {
		this.getHibernateTemplate().saveOrUpdate(entity);
		return entity;
	}

	public T save(T entity) {
		this.getHibernateTemplate().save(entity);
		return entity;
	}

	public T update(T entity) {
		this.getHibernateTemplate().update(entity);
		return entity;
	}

	@SuppressWarnings("unchecked")
	public void executeDelOrUpdateHql(final String hql) {
		Object o = this.getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {

				Query query = getQuery(session, hql);
				query.executeUpdate();
				return null;
			}
		});
	}

	@SuppressWarnings("unchecked")
	protected Class<T> getPersistentClass() {
		if (this.persistentClass == null) {
			this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
					.getGenericSuperclass()).getActualTypeArguments()[0];
		}
		return this.persistentClass;
	}

	@SuppressWarnings( { "unchecked" })
	public List findMapListByDynSql(String querySql, final int page,
			final int count) {
		List result = null;
		final String fullHql = querySql;
		if (fullHql != null) {
			Object o = this.getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)
								throws HibernateException {

							Query query = getQuery(session, fullHql);// 创建sql对象

							if (page > 0) {
								int maxResult = count;
								if (count < 1)
									maxResult = 1;
								int first = (page - 1) * maxResult;
								query.setFirstResult(first);
								query.setMaxResults(maxResult);
							}
							return query.setResultTransformer(
									Criteria.ALIAS_TO_ENTITY_MAP).list();
						}
					});
			if (o != null)
				result = (List) o;
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public Object queryForCmd(String querySql, final Object... params) {
		Object result = null;
		final String fullHql = querySql;
		// LazyParser parser=parsers.get(cmd);
		if (fullHql != null) {
			Object o = this.getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)
								throws HibernateException {

							Query query = getQuery(session, fullHql);
							if (params != null) {
								int len = params.length;
								for (int i = 0; i < len; i++) {
									query.setParameter(i, params[i]);
								}
							}
							return query.uniqueResult();
						}
					});
			if (o != null)
				result = o;
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public Object queryForSingleResultMap(String querySql,
			final Object... params) {
		Object result = null;
		final String fullHql = querySql;
		if (fullHql != null) {
			Object o = this.getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)
								throws HibernateException {

							Query query = getQuery(session, fullHql);
							if (params != null) {
								int len = params.length;
								for (int i = 0; i < len; i++) {
									query.setParameter(i, params[i]);
								}
							}
							return query.setResultTransformer(
									Criteria.ALIAS_TO_ENTITY_MAP)
									.uniqueResult();
						}
					});
			if (o != null)
				result = o;
		}
		return result;
	}

	public boolean createTable(String tablename, List<String> zdlist) {

		return false;
	}
}

/*
 * 1. 例如:objectDao.queryForCmd(
 * "select usercode,passwd,did,pid from s_user t where t.did = 1") 返回的是长度为4的一个数组
 * 
 * 2. 例如: SUser user = (SUser)
 * genericDao.queryForCmd("from com.jj.demo.hbm.SUser as u where u.did = 1");
 * 返回的是SUser对象
 * 
 * 3. 例如:
 * objectDao.queryForSingleResultMap("select * from F_D_FILE where did = ?",3);
 * 返回单条记录为一个map
 * 
 * 4. 例如:
 * objectDao.queryForCmd("from com.jj.demo.hbm.FDFile as fd where fd.fieldname = ?"
 * ,"CREATOR"); 返回为一个FDFile对象
 * 
 * 5. 例如: objectDao.findMapListByDynSql("select * from d_file1", -1, -1);
 * 返回为每条记录为一个map的多条记录list
 * 
 * 6. 例如: objectDao.queryForCmd("select count(*) from s_all"); 返回为一个结果obj .
 * 可转换为整型
 * 
 * 
 * Java代码 List<Map> ls = objectDao.findMapListByDynSql("select * from D_FILE1",
 * 1, 30); for (Map map : ls) { System.out.println(map.get("TITLE")); }
 */

package org.jbpmext.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.jbpmext.model.CusTabTable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Component;


@Component
public class TableListDaoImpl extends HibernateDaoSupport implements
		org.jbpmext.dao.TableListDao {

	@Resource
	public void setSessionFactory2(SessionFactory sessionFactory) {
		this.setSessionFactory(sessionFactory);
	}

	@Resource
	public void setDataSouce(DataSource dataSource) {
		this.jdbcTemplate.setDataSource(dataSource);
	}

	JdbcTemplate jdbcTemplate = new JdbcTemplate();

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public Serializable add(CusTabTable cusTabTable) {
		// TODO Auto-generated method stub
		return null;
	}

	// public boolean createMsqlTable(String sql) {
	// boolean istrue = false;
	// try{
	// istrue = true;
	// jdbcTemplate.execute(sql);
	//		
	// }catch(Exception e){
	//			
	// e.printStackTrace();
	// }
	// return istrue;
	// }

	public void delete(Integer id) {

		// jdbcTemplate.execute("CREATE TABLE S (SNO char(2), SNAME char(8), AGE decimal(2), SEX char(2) DEFAULT'男', DEPT char(2));");

		CusTabTable cusTabTable = this.findById(id);
		this.getHibernateTemplate().delete(cusTabTable);
	}

	public List<CusTabTable> findAll() {

		// TODO Auto-generated method stub
		return getHibernateTemplate().find("from CusTabTable");
	}

	public List<CusTabTable> findByHql(String querString) {

		return getHibernateTemplate().find(querString);
	}

	public CusTabTable findById(Integer id) {
		return this.getHibernateTemplate().get(CusTabTable.class, id);
	}

	public void update(CusTabTable cusTabTable) {
		// TODO Auto-generated method stub

	}

	public CusTabTable saveTable(String formname, String tablename,
			String textArea, String ms) {
		boolean istrue = false;
		CusTabTable ct = new CusTabTable();
		ct.setTableName(tablename);
		ct.setTableChinesename(formname);
		ct.setTableDescript(ms);
		ct.setTableContnet(textArea);
		ct.setAssoridrId(1);
		ct.setTableCreatname(1);

		// ct.setAssoridrId(assoridrId)

		// this.getSession(true).beginTransaction().

		try {
			this.getHibernateTemplate().save(ct);

			System.out.println(ct.getTableId());
			this.getHibernateTemplate().flush();

			istrue = true;

		} catch (Exception e) {

			e.printStackTrace();
		}

		return ct;

	}

	public void insertTbaleColumn(String string) {
		jdbcTemplate.execute(string);

	}

	public List getTableContent(String tablename) {

		List list = jdbcTemplate.queryForList("select * from t_" + tablename);

		return list;
	}

	public List<CusTabTable> findByHql() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void executUpdateSql(String string) {
		
		jdbcTemplate.execute(string);
		
	}

	@Override
	public Map appendTableHtml(String tablename, String textArea, String rid) {
		
        Map map = jdbcTemplate.queryForMap("select * from t_"+tablename+" where RID="+rid);
		return map;
	}
}

/**
 * 
 */
package org.jbpmext.service.h3;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.jbpmext.dao.h3.H3TermedFormDAO;
import org.jbpmext.model.MetaForm;
import org.jbpmext.service.FormTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;
import org.springframework.stereotype.Component;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * @author weiht
 *
 */
@Component("formTableService")
public class FormTableServiceH3 implements FormTableService {
	private static final Logger logger = LogManager.getLogger(FormTableServiceH3.class);
	private static final String mappingFileLocation = "/WEB-INF/formclasses/";
	private static final String mappingTemplateLocation = "/WEB-INF/classes/freemarker/";
	
	private H3TermedFormDAO formDao;
	private SessionFactory sessionFactory;

	private void reloadSessionFactory() {
		if (sessionFactory == null || formDao == null) return;
		if (sessionFactory instanceof LocalSessionFactoryBean) {
			((LocalSessionFactoryBean)sessionFactory).setMappingResources(getMappingFiles());
		}
		formDao.setSessionFactory(sessionFactory);
	}
	
	private String[] getMappingFiles() {
		File[] files = new File(
			ServletActionContext.getServletContext().getRealPath(mappingFileLocation)
		).listFiles(new FileFilter() {
			@Override
			public boolean accept(File f) {
				return f.isFile() && f.getName().endsWith(".hbm.xml");
			}
		});
		String[] fn = new String[files.length];
		for (int i = 0; i < files.length; i ++) {
			File f = files[i];
			fn[i] = f.getName();
		}
		return null;
	}

	@Override
	public void saveFormTable(MetaForm form) {
		Configuration config = new Configuration();
		try {
			config.setDirectoryForTemplateLoading(new File(ServletActionContext
					.getServletContext().getRealPath(mappingTemplateLocation)));
			Template temp = config.getTemplate("form-to-table.tpl", "utf-8");
			StringWriter w = new StringWriter();
			temp.process(form, w);
			writeMappingFile(form, w.toString());
			reloadSessionFactory();
			logger.debug(w);
		} catch (Exception ex) {
			logger.error("Saving form mapping xml file:", ex);
		}
	}

	private void writeMappingFile(MetaForm form, String string) {
		File f = new File(ServletActionContext.getServletContext().getRealPath(mappingFileLocation)
				+ "/" + form.getTableName() + ".hbm.xml");
		if (logger.isDebugEnabled()) {
			logger.debug("form mapping file: " + f.getAbsolutePath());
		}
		if (f.exists()) f.delete();
		try {
			f.createNewFile();
		} catch (IOException e) {
			logger.error("Creating form mapping xml file:", e);
			throw new HibernateException("Error creating mapping file:", e);
		}
		try {
			FileOutputStream os = new FileOutputStream(f);
			try {
				os.write(string.getBytes("utf-8"));
			} finally {
				os.close();
			}
		} catch (FileNotFoundException e) {
			logger.error("Writing form mapping xml file:", e);
			throw new HibernateException("Error creating mapping file:", e);
		} catch (UnsupportedEncodingException e) {
			logger.error("Writing form mapping xml file:", e);
			throw new HibernateException("Error creating mapping file:", e);
		} catch (IOException e) {
			logger.error("Writing form mapping xml file:", e);
			throw new HibernateException("Error creating mapping file:", e);
		}
	}

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		reloadSessionFactory();
	}

	@Autowired
	public void setFormDao(H3TermedFormDAO formDao) {
		this.formDao = formDao;
		reloadSessionFactory();
	}
}

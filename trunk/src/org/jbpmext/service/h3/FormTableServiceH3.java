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

	@Override
	public void saveFormTable(MetaForm form) {
		// TODO Auto-generated method stub
		
	}
}

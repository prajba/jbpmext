package org.jbpmext.web;

import javax.annotation.Resource;

import org.jbpmext.model.CusTabColumn;
import org.jbpmext.service.CusTabAssorService;

import com.opensymphony.xwork2.ActionSupport;

public class CusTabAssorDirAction extends ActionSupport {

	@Resource
	private static final long serialVersionUID = 1L;

	public String add() {
		return null;
	}

	public String delete() {
		CusTabColumn ctc = new CusTabColumn();
		ctc.getColumnName();
		ctc.getDataType();
		ctc.getColumnName();

		return null;
	}

	public String update() {

		return null;
	}

	public String list() {
		return null;
	}
}

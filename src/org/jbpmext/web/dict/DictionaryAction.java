/**
 * 
 */
package org.jbpmext.web.dict;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.jbpmext.model.DictCategory;
import org.jbpmext.model.DictEntry;
import org.jbpmext.service.DictionaryService;
import org.jbpmext.util.ActionJsonUtil;
import org.jbpmext.util.EasyUIGridWrapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author weiht
 *
 */
@SuppressWarnings("serial")
@Namespace("/dict")
public class DictionaryAction extends ActionSupport {
	private static final Logger logger = LogManager.getLogger(DictionaryAction.class);
	@Autowired
	private DictionaryService service;
	private DictCategory cat;
	private DictEntry entry;

	@Override
	@Action(value="manager", results={@Result(name="success", location="/WEB-INF/content/dict/manager.jsp")})
	public String execute() throws Exception {
		return SUCCESS;
	}
	
	@Action(value="listCats", results={@Result(name="success", location="/common/json.jsp")})
	public String listCategories() throws Exception {
		List<DictCategory> cats;
		try {
			cats = service.listCategories();
		} catch (Exception ex) {
			logger.error("Finding all categories:", ex);
			cats = new ArrayList<DictCategory>(0);
		}
		ActionJsonUtil.putJson(new EasyUIGridWrapper(cats));
		return SUCCESS;
	}
	
	@Action(value="catEditor", results={@Result(name="success", location="/WEB-INF/content/dict/catEditor.jsp")})
	public String catEditor() throws Exception {
		return SUCCESS;
	}
	
	@Action(value="saveCategory", results={@Result(name="success", location="/common/json.jsp")})
	public String saveCategory() throws Exception {
		try {
			service.saveCategory(cat);
		} catch (Exception ex) {
			logger.error("Saving dictionary category:", ex);
			throw ex;
		}
		ActionJsonUtil.putJson(cat);
		return SUCCESS;
	}
	
	@Action(value="catManager", results={@Result(name="success", location="/WEB-INF/content/dict/catManager.jsp")})
	public String catManager() throws Exception {
		return SUCCESS;
	}
	
	@Action(value="listEntries", results={@Result(name="success", location="/common/json.jsp")})
	@SuppressWarnings("rawtypes")
	public String listEntries() throws Exception {
		List entries;
		try {
			entries = service.listEntries(cat.getId());
		} catch (Exception ex) {
			logger.error("Finding category entries:", ex);
			entries = new ArrayList<Map>(0);
		}
		ActionJsonUtil.putJson(new EasyUIGridWrapper(entries));
		return SUCCESS;
	}
	
	@Action(value="entryEditor", results={@Result(name="success", location="/WEB-INF/content/dict/entryEditor.jsp")})
	public String entryEditor() throws Exception {
		return SUCCESS;
	}
	
	@Action(value="saveEntry", results={@Result(name="success", location="/common/json.jsp")})
	public String saveEntry() throws Exception {
		try {
			service.saveEntry(cat.getId(), entry);
		} catch (Exception ex) {
			logger.error("Saving dictionary entry:", ex);
			throw ex;
		}
		ActionJsonUtil.putJson(entry);
		return SUCCESS;
	}
	
	public DictCategory getCat() {
		return cat;
	}

	public void setCat(DictCategory cat) {
		this.cat = cat;
	}

	public DictEntry getEntry() {
		return entry;
	}

	public void setEntry(DictEntry entry) {
		this.entry = entry;
	}
}

/**
 * 
 */
package org.jbpmext.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author weiht
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="meta_forms")
public class MetaForm implements Serializable, Termed {
	private Integer id;
	private String formName;
	private String tableName;
	private String remarks;
	private String formHtml;
	private Date beginTime;
	private Date endTime;
	private int usableStatus;
	private boolean sysPreset;
	private List<MetaField> fields;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="form_id", nullable=false, unique=true, updatable=false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="form_name", nullable=false, length=100)
	public String getFormName() {
		return formName;
	}
	public void setFormName(String formName) {
		this.formName = formName;
	}
	
	@Column(name="table_name", nullable=false, length=200)
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	@Column(name="remarks", nullable=false, length=100)
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	@Lob
	@Column(name="form_html", nullable=false)
	public String getFormHtml() {
		return formHtml;
	}
	public void setFormHtml(String formHtml) {
		this.formHtml = formHtml;
	}
	
	@Column(name="begin_time", nullable=false)
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	
	@Column(name="end_time")
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	@Column(name="usable_status", nullable=false)
	public int getUsableStatus() {
		return usableStatus;
	}
	public void setUsableStatus(int usableStatus) {
		this.usableStatus = usableStatus;
	}

	@Column(name="sys_preset")
	public boolean isSysPreset() {
		return sysPreset;
	}
	public void setSysPreset(boolean sysPreset) {
		this.sysPreset = sysPreset;
	}
	
	@OneToMany(mappedBy="form", cascade={CascadeType.PERSIST})
	public List<MetaField> getFields() {
		return fields;
	}
	public void setFields(List<MetaField> fields) {
		this.fields = fields;
	}
}

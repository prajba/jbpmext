/**
 * 
 */
package org.jbpmext.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * @author weiht
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="meta_fields")
public class MetaField implements Serializable, Usable {
	private Integer id;
	private MetaForm form;
	private String fieldName;
	private String columnName;
	private String inputHint;
	private String displayType;
	private String dataType;
	private String dictCategory;
	private MetaForm referencingForm;
	private String remarks;
	private int usableStatus;
	private String validators;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="field_id", nullable=false, unique=true)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@ManyToOne(targetEntity=MetaForm.class)
	@JoinColumn(name="form_id")
	public MetaForm getForm() {
		return form;
	}
	public void setForm(MetaForm form) {
		this.form = form;
	}
	
	@Column(name="field_name", nullable=false, length=100)
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	
	@Column(name="column_name", nullable=false, length=200)
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	
	@Column(name="input_hint", length=100)
	public String getInputHint() {
		return inputHint;
	}
	public void setInputHint(String inputHint) {
		this.inputHint = inputHint;
	}
	
	@Column(name="display_type", nullable=false, length=50)
	public String getDisplayType() {
		return displayType;
	}
	public void setDisplayType(String displayType) {
		this.displayType = displayType;
	}
	
	@Column(name="data_type", nullable=false, length=50)
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	
	@Column(name="dict_category", length=100)
	public String getDictCategory() {
		return dictCategory;
	}
	public void setDictCategory(String dictCategory) {
		this.dictCategory = dictCategory;
	}

	@Column(name="remarks", length=100)
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	@Column(name="usable_status", nullable=false)
	public int getUsableStatus() {
		return usableStatus;
	}
	public void setUsableStatus(int usableStatus) {
		this.usableStatus = usableStatus;
	}
	
	@ManyToOne
	@JoinColumn(name="ref_form_id")
	public MetaForm getReferencingForm() {
		return referencingForm;
	}
	public void setReferencingForm(MetaForm referencingForm) {
		this.referencingForm = referencingForm;
	}
	
	@Lob
	@Column(name="validators")
	public String getValidators() {
		return validators;
	}
	public void setValidators(String validators) {
		this.validators = validators;
	}
}

package org.jbpmext.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CusTabColumn entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CusTab_Column", schema = "dbo", catalog = "customtable")
public class CusTabColumn implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer tableId;
	private String columnName;
	private String fieldName;
	private String fieldHint;
	private String displayType;
	private String dataType;
	private Integer isRequired;
	private String dictDesc;
	private String validators;

	// Constructors

	/** default constructor */
	public CusTabColumn() {
	}

	/** minimal constructor */
	public CusTabColumn(Integer tableId) {
		this.tableId = tableId;
	}

	/** full constructor */
	public CusTabColumn(Integer tableId, String columnName, String fieldName,
			String fieldHint, String displayType, String dataType,
			Integer isRequired, String dictDesc, String validators) {
		this.tableId = tableId;
		this.columnName = columnName;
		this.fieldName = fieldName;
		this.fieldHint = fieldHint;
		this.displayType = displayType;
		this.dataType = dataType;
		this.isRequired = isRequired;
		this.dictDesc = dictDesc;
		this.validators = validators;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "table_id", nullable = false)
	public Integer getTableId() {
		return this.tableId;
	}

	public void setTableId(Integer tableId) {
		this.tableId = tableId;
	}

	@Column(name = "column_name", length = 50)
	public String getColumnName() {
		return this.columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	@Column(name = "field_name", length = 100)
	public String getFieldName() {
		return this.fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	@Column(name = "field_hint", length = 100)
	public String getFieldHint() {
		return this.fieldHint;
	}

	public void setFieldHint(String fieldHint) {
		this.fieldHint = fieldHint;
	}

	@Column(name = "display_type", length = 50)
	public String getDisplayType() {
		return this.displayType;
	}

	public void setDisplayType(String displayType) {
		this.displayType = displayType;
	}

	@Column(name = "data_type", length = 50)
	public String getDataType() {
		return this.dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	@Column(name = "is_required")
	public Integer getIsRequired() {
		return this.isRequired;
	}

	public void setIsRequired(Integer isRequired) {
		this.isRequired = isRequired;
	}

	@Column(name = "dict_desc", length = 50)
	public String getDictDesc() {
		return this.dictDesc;
	}

	public void setDictDesc(String dictDesc) {
		this.dictDesc = dictDesc;
	}

	@Column(name = "validators", length = 50)
	public String getValidators() {
		return this.validators;
	}

	public void setValidators(String validators) {
		this.validators = validators;
	}

}
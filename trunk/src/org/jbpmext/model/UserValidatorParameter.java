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
import javax.persistence.Table;

/**
 * @author weiht
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="user_validator_parameters")
public class UserValidatorParameter implements Serializable {
	private int id;
	private String name;
	private int displayOrder;
	private String valueType;
	private String remarks;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="parameter_id", nullable=false, unique=true, updatable=false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="display_name", length=50)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="value_type", length=20)
	public String getValueType() {
		return valueType;
	}
	public void setValueType(String valueType) {
		this.valueType = valueType;
	}
	
	@Column(name="remarks", length=100)
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Column(name="display_order", nullable=false)
	public int getDisplayOrder() {
		return displayOrder;
	}
	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
	}
}

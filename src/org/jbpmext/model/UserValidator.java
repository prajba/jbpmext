/**
 * 
 */
package org.jbpmext.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author weiht
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="user_validators")
public class UserValidator implements Serializable, Usable {
	private Integer id;
	private String name;
	private String remarks;
	private String scriptSnippet;
	private int usableStatus;
	private List<UserValidatorParameter> parameters = new ArrayList<UserValidatorParameter>();

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="validator_id", nullable=false, unique=true, updatable=false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="validator_name", nullable=false, length=25)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="remarks", length=100)
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	@Lob
	@Column(name="script_snippet", nullable=false)
	public String getScriptSnippet() {
		return scriptSnippet;
	}
	public void setScriptSnippet(String scriptSnippet) {
		this.scriptSnippet = scriptSnippet;
	}
	
	@Column(name="usable_status", nullable=false)
	public int getUsableStatus() {
		return usableStatus;
	}
	public void setUsableStatus(int usableStatus) {
		this.usableStatus = usableStatus;
	}
	
	@OneToMany
	@JoinColumn(name="validator_id")
	public List<UserValidatorParameter> getParameters() {
		return parameters;
	}
	public void setParameters(List<UserValidatorParameter> parameters) {
		this.parameters = parameters;
	}
	
	@Override
	public String toString() {
		return "[id=" + id + ", name=" + name + ", remarks=" + remarks
			+ ", usableStatus=" + usableStatus + ", parameters:" + (parameters == null ? 0 : parameters.size())
			+ ", scriptSnippets: " + scriptSnippet + "]";
	}
}

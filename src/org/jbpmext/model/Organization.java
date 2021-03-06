/**
 * 
 */
package org.jbpmext.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * @author weiht
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="organizations")
public class Organization implements Serializable, Termed {
	public static final String TYPE_ROLE = "role";
	
	private Integer id;
	private String name;
	private String code;
	private int displayOrder;
	private Organization parent;
	private String type;
	private Date beginTime;
	private Date endTime;
	private int usableStatus;
	private String state;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="org_id", nullable=false, unique=true, updatable=false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="org_name", nullable=false, length=100)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="org_code", nullable=false, length=60)
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	@Column(name="display_order", nullable=false)
	public int getDisplayOrder() {
		return displayOrder;
	}
	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
	}
	
	@ManyToOne
	@JoinColumn(name="parent_id")
	public Organization getParent() {
		return parent;
	}
	public void setParent(Organization parent) {
		this.parent = parent;
	}
	
	@Column(name="org_type", nullable=false)
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	
	@Column(name="tree_node_state")
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}

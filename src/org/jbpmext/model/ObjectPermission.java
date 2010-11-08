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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author weiht
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="obj_permissions")
public class ObjectPermission implements Serializable {
	private Integer id;
	private Member owner;
	private Organization group;
	private int permission;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="obj_id", nullable=false, unique=true, updatable=false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@ManyToOne
	@JoinColumn(name="owner_id")
	public Member getOwner() {
		return owner;
	}
	public void setOwner(Member owner) {
		this.owner = owner;
	}
	
	@ManyToOne
	@JoinColumn(name="group_id")
	public Organization getGroup() {
		return group;
	}
	public void setGroup(Organization group) {
		this.group = group;
	}
	
	@Column(name="permission_value", nullable=false)
	public int getPermission() {
		return permission;
	}
	public void setPermission(int permission) {
		this.permission = permission;
	}
}

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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author weiht
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="obj_permissions")
public class ObjectPermission implements Serializable {
	private int id;
	private Member owner;
	private Organization group;
	private int permission;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="obj_id", nullable=false, unique=true, updatable=false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@OneToMany
	@JoinColumn(name="owner_id")
	public Member getOwner() {
		return owner;
	}
	public void setOwner(Member owner) {
		this.owner = owner;
	}
	
	@OneToMany
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

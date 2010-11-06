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
@Table(name="org_members")
public class OrgMember implements Serializable, Termed {
	private int id;
	private Organization organization;
	private Member member;
	private int displayOrder;
	private Date beginTime;
	private Date endTime;
	private int usableStatus;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="org_member_id", nullable=false, unique=true, updatable=false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@ManyToOne
	@JoinColumn(name="org_id")
	public Organization getOrganization() {
		return organization;
	}
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	
	@ManyToOne
	@JoinColumn(name="member_id")
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	
	@Column(name="display_order", nullable=false, columnDefinition="default 0")
	public int getDisplayOrder() {
		return displayOrder;
	}
	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
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
}

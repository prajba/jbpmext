/**
 * 
 */
package org.jbpmext.model.forms;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.jbpmext.model.Member;
import org.jbpmext.model.ObjectPermission;
import org.jbpmext.model.Termed;

/**
 * @author weiht
 *
 */
public abstract class AbstractForm implements Termed {
	private Integer id;
	private Date beginTime;
	private Date endTime;
	private int usableStatus;
	private Member draftsman;
	private ObjectPermission permission;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="form_id", nullable=false, unique=true, updatable=false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="begin_time", nullable=false, updatable=false)
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
	
	@Column(name="usable_status")
	public int getUsableStatus() {
		return usableStatus;
	}
	public void setUsableStatus(int usableStatus) {
		this.usableStatus = usableStatus;
	}
	
	@ManyToOne
	@JoinColumn(name="draftsman_id", nullable=false)
	public Member getDraftsman() {
		return draftsman;
	}
	public void setDraftsman(Member draftsman) {
		this.draftsman = draftsman;
	}

	@OneToOne
	@JoinColumn(name="permission_id")
	public ObjectPermission getPermission() {
		return permission;
	}
	public void setPermission(ObjectPermission permission) {
		this.permission = permission;
	}
}

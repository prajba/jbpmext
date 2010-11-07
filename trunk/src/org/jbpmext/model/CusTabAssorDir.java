package org.jbpmext.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CusTabAssorDir entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CusTab_AssorDir")
public class CusTabAssorDir implements java.io.Serializable {

	// Fields

	/**
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String dirname;
	private Integer parentid;

	// Constructors

	/** default constructor */
	public CusTabAssorDir() {
	}

	/** full constructor */
	public CusTabAssorDir(String dirname, Integer parentid) {
		this.dirname = dirname;
		this.parentid = parentid;
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

	@Column(name = "dirname", length = 50)
	public String getDirname() {
		return this.dirname;
	}

	public void setDirname(String dirname) {
		this.dirname = dirname;
	}

	@Column(name = "parentid")
	public Integer getParentid() {
		return this.parentid;
	}

	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}

}
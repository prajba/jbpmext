package org.jbpmext.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CusTabTable entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CusTab_Table", schema = "dbo", catalog = "customtable")
public class CusTabTable implements java.io.Serializable {

	// Fields

	private Integer tableId;
	private Integer assoridrId;
	private String tableChinesename;
	private String tableDs;
	private String tableDescript;
	private String tableContnet;
	private Integer tableCreatname;
	private String tableName;
	private String flowId;
	private String flowDescribe;
	private String flowStat;

	// Constructors

	/** default constructor */
	public CusTabTable() {
	}

	/** minimal constructor */
	public CusTabTable(Integer assoridrId) {
		this.assoridrId = assoridrId;
	}

	/** full constructor */
	public CusTabTable(Integer assoridrId, String tableChinesename,
			String tableDs, String tableDescript, String tableContnet,
			Integer tableCreatname, String tableName, String flowId,
			String flowDescribe, String flowStat) {
		this.assoridrId = assoridrId;
		this.tableChinesename = tableChinesename;
		this.tableDs = tableDs;
		this.tableDescript = tableDescript;
		this.tableContnet = tableContnet;
		this.tableCreatname = tableCreatname;
		this.tableName = tableName;
		this.flowId = flowId;
		this.flowDescribe = flowDescribe;
		this.flowStat = flowStat;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "table_id", unique = true, nullable = false)
	public Integer getTableId() {
		return this.tableId;
	}

	public void setTableId(Integer tableId) {
		this.tableId = tableId;
	}

	@Column(name = "assoridr_id", nullable = false)
	public Integer getAssoridrId() {
		return this.assoridrId;
	}

	public void setAssoridrId(Integer assoridrId) {
		this.assoridrId = assoridrId;
	}

	@Column(name = "table_chinesename", length = 50)
	public String getTableChinesename() {
		return this.tableChinesename;
	}

	public void setTableChinesename(String tableChinesename) {
		this.tableChinesename = tableChinesename;
	}

	@Column(name = "table_ds", length = 50)
	public String getTableDs() {
		return this.tableDs;
	}

	public void setTableDs(String tableDs) {
		this.tableDs = tableDs;
	}

	@Column(name = "table_descript", length = 100)
	public String getTableDescript() {
		return this.tableDescript;
	}

	public void setTableDescript(String tableDescript) {
		this.tableDescript = tableDescript;
	}

	@Column(name = "table_contnet")
	public String getTableContnet() {
		return this.tableContnet;
	}

	public void setTableContnet(String tableContnet) {
		this.tableContnet = tableContnet;
	}

	@Column(name = "table_creatname")
	public Integer getTableCreatname() {
		return this.tableCreatname;
	}

	public void setTableCreatname(Integer tableCreatname) {
		this.tableCreatname = tableCreatname;
	}

	@Column(name = "table_name", length = 50)
	public String getTableName() {
		return this.tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	@Column(name = "flow_id")
	public String getFlowId() {
		return this.flowId;
	}

	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}

	@Column(name = "flow_describe")
	public String getFlowDescribe() {
		return this.flowDescribe;
	}

	public void setFlowDescribe(String flowDescribe) {
		this.flowDescribe = flowDescribe;
	}

	@Column(name = "flow_stat")
	public String getFlowStat() {
		return this.flowStat;
	}

	public void setFlowStat(String flowStat) {
		this.flowStat = flowStat;
	}

}
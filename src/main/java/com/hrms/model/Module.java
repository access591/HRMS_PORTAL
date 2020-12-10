package com.hrms.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "M_MODULE")
public class Module implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8978451192594112630L;

	@Id
	@Column(name = "MODULE_CODE")
	private String moduleCode;

	@Column(name = "MODULE_NAME")
	private String moduleName;

	@Column(name = "ACTIVE_YN")
	private String active;

	@Column(name = "INS_BY")
	private String insertedBy;

	@Column(name = "INS_DATE")
	private Date insertedDate = new Date();

	@Column(name = "UPDATE_BY")
	private String updateBy;

	@Column(name = "UPDATE_DATE")
	private Date updatedDate = new Date();

	@Column(name = "SEQ_NO")
	private int seqNo;

//	@OneToMany(mappedBy = "moduleCode")
//	private List<SubModule> subModules;
//
//	@OneToMany(mappedBy = "pModuleCode")
//	private List<Program> modulePrograms;

	public String getModuleCode() {
		return moduleCode;
	}

	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getInsertedBy() {
		return insertedBy;
	}

	public void setInsertedBy(String insertedBy) {
		this.insertedBy = insertedBy;
	}

	public Date getInsertedDate() {
		return insertedDate;
	}

	public void setInsertedDate(Date insertedDate) {
		this.insertedDate = insertedDate;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public int getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(int seqNo) {
		this.seqNo = seqNo;
	}

//	public List<SubModule> getSubModules() {
//		return subModules;
//	}
//
//	public void setSubModules(List<SubModule> subModules) {
//		this.subModules = subModules;
//	}
//
//	public List<Program> getModulePrograms() {
//		return modulePrograms;
//	}
//
//	public void setModulePrograms(List<Program> modulePrograms) {
//		this.modulePrograms = modulePrograms;
//	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

}

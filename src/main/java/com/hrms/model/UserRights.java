package com.hrms.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "M_URIGHTS")
public class UserRights implements Serializable {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3115353075342584614L;

	 @Id
	 @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
	@Column(name = "USER_CODE")
	private String userCode;
	
	@Column(name = "MODULE_CODE")
	private String moduleCode;
	
	@Column(name = "SUB_MODULE_CODE")
	private String subModuleCode;

	@Column(name = "PRG_CODE")
	private String prgCode;

	@Column(name = "ACTIVE_YN")
	private String active;

	@Column(name = "INS_BY", updatable = false)
	private String insBy;

	@Column(name = "INS_DATE", updatable = false)
	private Date insDate = new Date();

	@Column(name = "UPD_BY", insertable = false)
	private String updBy;

	@Column(name = "UPD_DATE", insertable = false)
	private Date updDate = new Date();
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getModuleCode() {
		return moduleCode;
	}
	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}
	public String getSubModuleCode() {
		return subModuleCode;
	}
	public void setSubModuleCode(String subModuleCode) {
		this.subModuleCode = subModuleCode;
	}
	public String getPrgCode() {
		return prgCode;
	}
	public void setPrgCode(String prgCode) {
		this.prgCode = prgCode;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public String getInsBy() {
		return insBy;
	}
	public void setInsBy(String insBy) {
		this.insBy = insBy;
	}
	public Date getInsDate() {
		return insDate;
	}
	public void setInsDate(Date insDate) {
		this.insDate = insDate;
	}
	public String getUpdBy() {
		return updBy;
	}
	public void setUpdBy(String updBy) {
		this.updBy = updBy;
	}
	public Date getUpdDate() {
		return updDate;
	}
	public void setUpdDate(Date updDate) {
		this.updDate = updDate;
	}
	
	
	
}

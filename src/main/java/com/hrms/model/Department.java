/**
 * 
 */
package com.hrms.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Access
 *
 */
@Entity
@Table(name = "M_DEPARTMENT")

public class Department implements Serializable
{

	/**
	 * Surendra Access 
	 */
	private static final long serialVersionUID = -4001846236713344340L;

	@Id
	@Column(name = "DEPARTMENT_CODE")
	private String departmentCode;
	
	@Column(name = "DEP_NAME")
	private String deptName;
	
	@Column(name = "DEPH_Code")
	private String dephCode;
	
	
	@Column(name = "DEPH_NAME")
	private String dephName;

	@Column(name = "ACTIVE_YN")
	private String active;
    @Column(name = "INS_BY",updatable = false)
	private String insBy;
	
	@Column(name = "INS_DATE",updatable = false)
	private Date insDate =new Date();
	
	@Column(name = "UPD_BY",insertable = false)
	private String updBy;
	
	@Column(name = "UPD_DATE",insertable = false)
	private Date  updDate = new Date();
	

	
	public String getDepartmentCode() {
		return departmentCode;
	}
	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getDephCode() {
		return dephCode;
	}
	public void setDephCode(String dephCode) {
		this.dephCode = dephCode;
	}
	public String getDephName() {
		return dephName;
	}
	public void setDephName(String dephName) {
		this.dephName = dephName;
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
	
	
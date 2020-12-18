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
	 * Mohit Access 
	 */
	private static final long serialVersionUID = -4001846236713344340L;

	@Id
	@Column(name = "DEPARTMENT_CODE")
	private String Department_Code;
	
	@Column(name = "DEP_NAME")
	private String Dep_Name;
	
	@Column(name = "DEPH_Code")
	private String Deph_code;
	
	
	@Column(name = "DEPH_NAME")
	private String Deph_Name;
	
	@Column(name = "INS_BY")
	private String Ins_by;
	
	@Column(name = "INS_DATE")
	private Date ins_date =new Date();
	
	@Column(name = "UPD_BY")
	private String upd_by;
	
	@Column(name = "UPD_DATE")
	private Date  upd_date = new Date();
	@Column(name = "ACTIVE_YN")
	private String active;

	public String getDepartment_Code() {
		return Department_Code;
	}
	public void setDepartment_Code(String department_Code) {
		Department_Code = department_Code;
	}
	public String getDep_Name() {
		return Dep_Name;
	}
	public void setDep_Name(String dep_Name) {
		Dep_Name = dep_Name;
	}
	public String getDeph_code() {
		return Deph_code;
	}
	public void setDeph_code(String deph_code) {
		Deph_code = deph_code;
	}
	public String getDeph_Name() {
		return Deph_Name;
	}
	public void setDeph_Name(String deph_Name) {
		Deph_Name = deph_Name;
	}
	public String getIns_by() {
		return Ins_by;
	}
	public void setIns_by(String ins_by) {
		Ins_by = ins_by;
	}
	public Date getIns_date() {
		return ins_date;
	}
	public void setIns_date(Date ins_date) {
		this.ins_date = ins_date;
	}
	public String getUpd_by() {
		return upd_by;
	}
	public void setUpd_by(String upd_by) {
		this.upd_by = upd_by;
	}
	public Date getUpd_date() {
		return upd_date;
	}
	public void setUpd_date(Date upd_date) {
		this.upd_date = upd_date;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
}	
	
	
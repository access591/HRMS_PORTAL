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
 * @author Access surendra
 *
 */
@Entity
@Table(name = "M_DESIGNATION")

public class Designation implements Serializable{

	private static final long serialVersionUID = -5996491080389467939L;
	@Id
	@Column(name = "DESG_CODE")
	private String Desg_code;

	@Column(name = "DESG_NAME")
	private String Desg_Name;
	
	@Column(name = "INS_BY")
	private String Ins_by;
	
	@Column(name = "INS_DATE")
	private Date ins_date = new Date();
	
	@Column(name = "UPD_BY")
	private String upd_by;
	
	@Column(name = "UPD_DATE")
	private Date  upd_date = new Date();
	@Column(name = "ACTIVE_YN")
	private String active;
	
	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getDesg_code() {
		return Desg_code;
	}

	public void setDesg_code(String desg_code) {
		Desg_code = desg_code;
	}

	public String getDesg_Name() {
		return Desg_Name;
	}

	public void setDesg_Name(String desg_Name) {
		
		
		Desg_Name = desg_Name;
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
	
}

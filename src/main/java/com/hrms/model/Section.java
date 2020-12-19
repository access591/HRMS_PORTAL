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
@Table(name = "M_SECTION")

public class Section implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4845127670430227293L;

	/**
	 * 
	 */
	

	/**
	 * Mohit Access 
	 */
	

	@Id
	@Column(name = "SECT_CODE")
	private String Sect_Code;
	
	@Column(name = "AMOUNT_LIMIT")
	private String Amt_lt;
	
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
	
	
	public String getSect_Code() {
		return Sect_Code;
	}
	public void setSect_Code(String sect_Code) {
		Sect_Code = sect_Code;
	}
	public String getAmt_lt() {
		return Amt_lt;
	}
	public void setAmt_lt(String amt_lt) {
		Amt_lt = amt_lt;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

	
}	
	
	
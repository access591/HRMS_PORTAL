package com.hrms.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
	 * 
	 */
	
/**
 * @author Access Mohit
 *
 */
@Entity
@Table(name = "M_LEAVE")

public class Leave implements Serializable{

	private static final long serialVersionUID = 8576554099474445024L;
	@Id
	@Column(name = "LEV_CODE")
	private String Lev_code;

	@Column(name = "LEV_TYPE")
	private String lev_type;
	
	@Column(name = "TOTAL_LEV", updatable = false)
	private String total_lev;
	
	@Column(name = "UNPAID")
	private String unpaid;
	
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
	
	
	public String getLev_code() {
		return Lev_code;
	}
	public void setLev_code(String Lev_code) {
		this.Lev_code = Lev_code;
	}
	public String getLev_type() {
		return lev_type;
	}
	public void setLev_type(String lev_type) {
		this.lev_type = lev_type;
	}
	public String getTotal_lev() {
		return total_lev;
	}
	public void setTotal_lev(String total_lev) {
		this.total_lev = total_lev;
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
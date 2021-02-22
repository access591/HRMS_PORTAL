package com.hrms.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "M_COUNTRY")
public class Country implements Serializable {

	/**
	 *  @author Access Surendra
	 */
	private static final long serialVersionUID = 3666736716574465121L;
	
	@Id
	@Column(name = "COUNTRY_CODE")
	private String countryCode;
	@Column(name = "COUNTRY_NAME")
	private String countryName;
	@Column(name = "ACTIVE_YN")
	private String active;
	@Column(name = "INS_BY",updatable = false)
	private String Ins_by;
	
	@Column(name = "INS_DATE",updatable = false)
	private Date ins_date =new Date();
	
	@Column(name = "UPD_BY",insertable = false)
	private String upd_by;
	
	@Column(name = "UPD_DATE",insertable = false)
	private Date  upd_date = new Date();
	

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
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

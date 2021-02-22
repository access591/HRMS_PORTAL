package com.hrms.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "M_CITY")
public class City implements Serializable {

	/**
	 *  @author Access Surendra
	 */
	private static final long serialVersionUID = -4022504081712702959L;
	@Id
	@Column(name = "CITY_CODE")
	private String cityCode;
	
	@Column(name = "CITY_NAME")
	private String cityName;
	
	@Column(name = "ACTIVE_YN")
	private String active;
	@Column(name = "STATE_CODE")
	private String stateCode;
	@Column(name = "INS_BY",updatable = false)
	private String Ins_by;
	
	@Column(name = "INS_DATE",updatable = false)
	private Date ins_date =new Date();
	
	@Column(name = "UPD_BY",insertable = false)
	private String upd_by;
	
	@Column(name = "UPD_DATE",insertable = false)
	private Date  upd_date = new Date();
	

	public String getStateCode() {
		return stateCode;
	}
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
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

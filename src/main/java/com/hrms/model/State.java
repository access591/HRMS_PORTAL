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
@Table(name = "M_STATE")
public class State implements Serializable{

	/**
	 *  @author Access Surendra
	 */
	private static final long serialVersionUID = -6376128149451393606L;
	@Id
	@Column(name = "STATE_CODE")
	private String stateCode;
	
	@Column(name = "STATE_NAME")
	private String stateName;
	
	@Column(name = "ACTIVE_YN")
	private String active;
	
	@OneToMany(mappedBy = "stateCode")
	private List<City> Citys;
	
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
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public List<City> getCitys() {
		return Citys;
	}
	public void setCitys(List<City> citys) {
		Citys = citys;
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

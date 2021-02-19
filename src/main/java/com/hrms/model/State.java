package com.hrms.model;

import java.io.Serializable;
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
	
	
}

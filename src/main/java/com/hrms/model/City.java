package com.hrms.model;

import java.io.Serializable;
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
	
	
}

package com.hrms.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "M_TRAVEL")
public class Travel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2540893849572427408L;
   
	@Id
	@Column(name = "TRAVEL_CODE")
	private String travelCode;
	
	@Column(name ="STRT_CITY_CODE")
	private String strtCityCode;
	
	@Column(name ="STRT_CITY_NAME")
	private String strtCityName;
	
	@Column(name ="VISIT_CITY_CODE")
	private String visitCityCode;
	
	@Column(name ="VISIT_CITY_NAME")
	private String visitCityName;
	@Column(name = "ACTIVE_YN")
	private String active;

	public String getTravelCode() {
		return travelCode;
	}

	public void setTravelCode(String travelCode) {
		this.travelCode = travelCode;
	}

	public String getStrtCityCode() {
		return strtCityCode;
	}

	public void setStrtCityCode(String strtCityCode) {
		this.strtCityCode = strtCityCode;
	}

	public String getStrtCityName() {
		return strtCityName;
	}

	public void setStrtCityName(String strtCityName) {
		this.strtCityName = strtCityName;
	}

	public String getVisitCityCode() {
		return visitCityCode;
	}

	public void setVisitCityCode(String visitCityCode) {
		this.visitCityCode = visitCityCode;
	}

	public String getVisitCityName() {
		return visitCityName;
	}

	public void setVisitCityName(String visitCityName) {
		this.visitCityName = visitCityName;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}
	
	
	
	
}

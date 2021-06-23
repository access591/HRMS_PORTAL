package com.hrms.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
@Entity
@Table(name = "M_TRAVEL")
public class Travel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2540893849572427408L;
   
	@Id
	@Size(max = 15)
	@Column(name = "TRAVEL_CODE")
	private String travelCode;
	@Size(max = 15)
	@Column(name ="STRT_CITY_CODE")
	private String strtCityCode;
	@Size(max = 50)
	@Column(name ="STRT_CITY_NAME")
	private String strtCityName;
	@Size(max = 15)
	@Column(name ="VISIT_CITY_CODE")
	private String visitCityCode;
	@Size(max = 50)
	@Column(name ="VISIT_CITY_NAME")
	private String visitCityName;
	@Size(max = 1)
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

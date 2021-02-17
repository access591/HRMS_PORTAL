package com.hrms.model;

import java.io.Serializable;
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
	
	
}

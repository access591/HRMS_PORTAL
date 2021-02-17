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

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	
}

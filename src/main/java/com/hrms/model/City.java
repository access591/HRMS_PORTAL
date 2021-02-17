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
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	
}

package com.hrms.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "M_ACTIV")
public class Activities implements Serializable{
	/**
	 * Access skm
	 */
	private static final long serialVersionUID = -7265729884533931859L;
	@Id
	@Column(name = "ACT_CODE")
	private String actCode;
	@Column(name = "ACT_NAME")
	private String actName;
	@Column(name = "ACTIVE_YN")
	private String active;
	public String getActCode() {
		return actCode;
	}
	public void setActCode(String actCode) {
		this.actCode = actCode;
	}
	public String getActName() {
		return actName;
	}
	public void setActName(String actName) {
		this.actName = actName;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}

}

package com.hrms.model;

import java.io.Serializable;
import java.util.Date;

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
    @Column(name = "INS_BY",updatable = false)
	private String insBy;
	
	@Column(name = "INS_DATE",updatable = false)
	private Date insDate =new Date();
	
	@Column(name = "UPD_BY",insertable = false)
	private String updBy;
	
	@Column(name = "UPD_DATE",insertable = false)
	private Date  updDate = new Date();
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
	public String getInsBy() {
		return insBy;
	}
	public void setInsBy(String insBy) {
		this.insBy = insBy;
	}
	public Date getInsDate() {
		return insDate;
	}
	public void setInsDate(Date insDate) {
		this.insDate = insDate;
	}
	public String getUpdBy() {
		return updBy;
	}
	public void setUpdBy(String updBy) {
		this.updBy = updBy;
	}
	public Date getUpdDate() {
		return updDate;
	}
	public void setUpdDate(Date updDate) {
		this.updDate = updDate;
	}
	

}

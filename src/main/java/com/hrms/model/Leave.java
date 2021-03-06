package com.hrms.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
	 * 
	 */
	
/**
 * @author Access Mohit
 *
 */
@Entity
@Table(name = "M_LEAVE")

public class Leave implements Serializable{

	private static final long serialVersionUID = 8576554099474445024L;
	@Id
	@Column(name = "LEV_CODE")
	private String levCode;

	@Column(name = "LEV_TYPE")
	private String levType;
	
	@Column(name = "TOTAL_LEV", updatable = false)
	private String totalLev;
	
	@Column(name = "UNPAID")
	private String unpaid;
    @Column(name = "INS_BY",updatable = false)
	private String insBy;
	
	@Column(name = "INS_DATE",updatable = false)
	private Date insDate =new Date();
	
	@Column(name = "UPD_BY",insertable = false)
	private String updBy;
	
	@Column(name = "UPD_DATE",insertable = false)
	private Date  updDate = new Date();
	@Column(name = "ACTIVE_YN")
	private String active;
	
	
	
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public String getLevCode() {
		return levCode;
	}
	public void setLevCode(String levCode) {
		this.levCode = levCode;
	}
	public String getLevType() {
		return levType;
	}
	public void setLevType(String levType) {
		this.levType = levType;
	}
	public String getTotalLev() {
		return totalLev;
	}
	public void setTotalLev(String totalLev) {
		this.totalLev = totalLev;
	}
	public String getUnpaid() {
		return unpaid;
	}
	public void setUnpaid(String unpaid) {
		this.unpaid = unpaid;
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
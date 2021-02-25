package com.hrms.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name ="M_SHIFT")
public class Shift  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8424287516722821070L;
	@Id
	@Column(name = "SHIFT_CODE")
	private String shiftCode;
	@Column(name = "START_TIME")
	private  String startTime;
	
	@Column(name ="BRKST_TIME")
	private  String brkstTime;
	@Column(name ="SHIFT_NAME")
	private  String shiftName;
	@Column(name ="END_TIME")
	private  String  endTime;
	@Column(name ="BRKND_TIME")
	private  String  brkndTime;
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

	
	
	public String getShiftCode() {
		return shiftCode;
	}
	public void setShiftCode(String shiftCode) {
		this.shiftCode = shiftCode;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getBrkstTime() {
		return brkstTime;
	}
	public void setBrkstTime(String brkstTime) {
		this.brkstTime = brkstTime;
	}
	public String getShiftName() {
		return shiftName;
	}
	public void setShiftName(String shiftName) {
		this.shiftName = shiftName;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getBrkndTime() {
		return brkndTime;
	}
	public void setBrkndTime(String brkndTime) {
		this.brkndTime = brkndTime;
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

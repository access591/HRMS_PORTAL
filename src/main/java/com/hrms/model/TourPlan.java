package com.hrms.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="TOUR_PLAN")
public class TourPlan {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String empCode;
	private String empName;
	private String deptCode;
	private String tourPlanId;
	private String fyCode = null;
	private String cCode = null;
	private String approvedBy;
	private String mobile;
	
	private String tourPlanDate = null;
	
	private String tourStartDate;
	private String tourEndDate;
	
	@Column(updatable = false)
	private String Udate;
	
	private String approvedStatus = "N";
	
	@Column(name = "INS_BY",updatable = false)
	private String insBy;
		
	@Column(name = "INS_DATE",updatable = false)
	private Date insDate =new Date();
		
	@Column(name = "UPD_BY",insertable = false)
	private String updBy;
		
	@Column(name = "UPD_DATE",insertable = false)
	private Date  updDate = new Date();
		
	
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getTourPlanId() {
		return tourPlanId;
	}
	public void setTourPlanId(String tourPlanId) {
		this.tourPlanId = tourPlanId;
	}
	public String getFyCode() {
		return fyCode;
	}
	public void setFyCode(String fyCode) {
		this.fyCode = fyCode;
	}
	public String getcCode() {
		return cCode;
	}
	public void setcCode(String cCode) {
		this.cCode = cCode;
	}
	public String getApprovedBy() {
		return approvedBy;
	}
	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getTourPlanDate() {
		return tourPlanDate;
	}
	public void setTourPlanDate(String tourPlanDate) {
		this.tourPlanDate = tourPlanDate;
	}
	public String getTourStartDate() {
		return tourStartDate;
	}
	public void setTourStartDate(String tourStartDate) {
		this.tourStartDate = tourStartDate;
	}
	public String getTourEndDate() {
		return tourEndDate;
	}
	public void setTourEndDate(String tourEndDate) {
		this.tourEndDate = tourEndDate;
	}
	public String getUdate() {
		return Udate;
	}
	public void setUdate(String udate) {
		Udate = udate;
	}
	public String getApprovedStatus() {
		return approvedStatus;
	}
	public void setApprovedStatus(String approvedStatus) {
		this.approvedStatus = approvedStatus;
	}
	
	
	
	

}

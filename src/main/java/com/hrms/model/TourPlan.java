package com.hrms.model;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Column;


@Entity(name="TOUR_PLAN_MAST")
public class TourPlan implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7748536710350573569L;
	@Id
	@Column(name = "TOUR_PLAN_ID")
	private String tourPlanId;
	
	@Column(name = "TOUR_PLAN_DT")
	private Date tourPlanDate;
	
	@ManyToOne
	@JoinColumn(name ="EMP_CODE")
	private Employee empCode;
	
	@JoinColumn(name ="DEPT_CODE")
	private Department dept_code ;
	
	@Column(name = "TOUR_START_DT")
	private Date tourStartDate;
	
    @Column(name = "MOB_NO")
	private int  mobNo;
    @Column(name = "TOUR_END_DT")
	private Date  tourEndDate;
	
    @Column(name = "APPROVED_BY")
	private String  approvedBy ;   
	
	@Column(name = "APPROVAL_STATUS")
	private String  approvalStatus ;
	
	@Column(name = "APPROVAL_DATE")
	private Date approvalDate ;

	@Column(name = "INS_BY", updatable = false)
	private String insBy;

	@Column(name = "INS_DATE", updatable = false)
	private Date insDate = new Date();

	public String getTourPlanId() {
		return tourPlanId;
	}

	public void setTourPlanId(String tourPlanId) {
		this.tourPlanId = tourPlanId;
	}

	public Date getTourPlanDate() {
		return tourPlanDate;
	}

	public void setTourPlanDate(Date tourPlanDate) {
		this.tourPlanDate = tourPlanDate;
	}

	public Employee getEmpCode() {
		return empCode;
	}

	public void setEmpCode(Employee empCode) {
		this.empCode = empCode;
	}

	public Department getDept_code() {
		return dept_code;
	}

	public void setDept_code(Department dept_code) {
		this.dept_code = dept_code;
	}

	public Date getTourStartDate() {
		return tourStartDate;
	}

	public void setTourStartDate(Date tourStartDate) {
		this.tourStartDate = tourStartDate;
	}

	public int getMobNo() {
		return mobNo;
	}

	public void setMobNo(int mobNo) {
		this.mobNo = mobNo;
	}

	public Date getTourEndDate() {
		return tourEndDate;
	}

	public void setTourEndDate(Date tourEndDate) {
		this.tourEndDate = tourEndDate;
	}

	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public Date getApprovalDate() {
		return approvalDate;
	}

	public void setApprovalDate(Date approvalDate) {
		this.approvalDate = approvalDate;
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
	
}

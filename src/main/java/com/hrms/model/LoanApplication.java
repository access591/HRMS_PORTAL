package com.hrms.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
@Entity
@Table(name = "LOAN_REQUEST")
public class LoanApplication  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6794795665038552177L;
	
	@Id
	@Column(name = "APP_NO")
	private String appNo;
	
	@Column(name="APP_DATE")
	private Date appDate;
	
	@ManyToOne
	@JoinColumn(name = "EMPLOYEE_CODE",updatable = false)
	private Employee empCode;
	
	@Size(max =1)
	@Column(name="LOAN_TYPE")
	private String loanType;
	
	@ManyToOne
	@JoinColumn(name ="LOAN_CODE",updatable = false)
	private Loan loanCode;
	
	@Size(max =1)
	@Column(name="LOAN_STATUS")
	private String loanStatus;
	
	@Column(name="AMOUNT_REQUIRED")
	private String amountRequired;
	
	@Column(name="EFF_SCHEDULE_DATE")
	private Date effScheduleDate;
	
	 
	@Column(name="AMOUNT_SANCTIONED")
	private String amountSanctioned;
	
	
	
	@Column(name = "APPROVED_BY")
	private String  approvedBy ;   
	
	@Column(name = "APPROVAL_STATUS")
	private String  approvalStatus ;
	
	@Column(name = "INS_BY", updatable = false)
	private String insBy;

	@Column(name = "INS_DATE", updatable = false)
	private Date insDate = new Date();

	public String getAppNo() {
		return appNo;
	}

	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}

	public Date getAppDate() {
		return appDate;
	}

	public void setAppDate(Date appDate) {
		this.appDate = appDate;
	}

	public Employee getEmpCode() {
		return empCode;
	}

	public void setEmpCode(Employee empCode) {
		this.empCode = empCode;
	}

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	public Loan getLoanCode() {
		return loanCode;
	}

	public void setLoanCode(Loan loanCode) {
		this.loanCode = loanCode;
	}

	public String getLoanStatus() {
		return loanStatus;
	}

	public void setLoanStatus(String loanStatus) {
		this.loanStatus = loanStatus;
	}



	public String getAmountRequired() {
		return amountRequired;
	}

	public void setAmountRequired(String amountRequired) {
		this.amountRequired = amountRequired;
	}

	public Date getEffScheduleDate() {
		return effScheduleDate;
	}

	public void setEffScheduleDate(Date effScheduleDate) {
		this.effScheduleDate = effScheduleDate;
	}

	public String getAmountSanctioned() {
		return amountSanctioned;
	}

	public void setAmountSanctioned(String amountSanctioned) {
		this.amountSanctioned = amountSanctioned;
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

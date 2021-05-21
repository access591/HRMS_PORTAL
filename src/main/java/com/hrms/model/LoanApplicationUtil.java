package com.hrms.model;

import java.util.Date;


public class LoanApplicationUtil {
	
	private String  empCode;
	private String  empName;
	private String departmentCode;
	private String  deptName;
	private String desgCode;
	private String desgName;
	private String  empPayCode;
	//-----------------------------------
	private String appNo;
	private Date appDate;
	
	private String loanCode;
	private String loanType;
	
	private String loanStatus;
	
	
	private String amountRequired;
	
	
	private Date effScheduleDate;
	
	private String amountSanctioned;

	public LoanApplicationUtil(String empName2, String deptName2, String desgName2, String employeePayeeCode) {
		this.empName = empName2;
		this.deptName = deptName2;
		this.desgName = desgName2;
		this.empPayCode = employeePayeeCode;
	}

	public String getAppNo() {
		return appNo;
	}

	public void setAppNo(String appNo) {
		this.appNo = appNo;
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

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDesgCode() {
		return desgCode;
	}

	public void setDesgCode(String desgCode) {
		this.desgCode = desgCode;
	}

	public String getDesgName() {
		return desgName;
	}

	public void setDesgName(String desgName) {
		this.desgName = desgName;
	}

	public String getEmpPayCode() {
		return empPayCode;
	}

	public void setEmpPayCode(String empPayCode) {
		this.empPayCode = empPayCode;
	}

	public Date getAppDate() {
		return appDate;
	}

	public void setAppDate(Date appDate) {
		this.appDate = appDate;
	}

	public String getLoanCode() {
		return loanCode;
	}

	public void setLoanCode(String loanCode) {
		this.loanCode = loanCode;
	}

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
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

	

	public LoanApplicationUtil(String empCode, String empName, String departmentCode, String deptName, String desgCode,
			String desgName, String empPayCode, String appNo, Date appDate, String loanCode, String loanType,
			String loanStatus, String amountRequired, Date effScheduleDate, String amountSanctioned) {
		super();
		this.empCode = empCode;
		this.empName = empName;
		this.departmentCode = departmentCode;
		this.deptName = deptName;
		this.desgCode = desgCode;
		this.desgName = desgName;
		this.empPayCode = empPayCode;
		this.appNo = appNo;
		this.appDate = appDate;
		this.loanCode = loanCode;
		this.loanType = loanType;
		this.loanStatus = loanStatus;
		this.amountRequired = amountRequired;
		this.effScheduleDate = effScheduleDate;
		this.amountSanctioned = amountSanctioned;
	}

	public LoanApplicationUtil() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}

package com.hrms.model;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class EmployeeLeaveRequest {

	private String empName;
	private String deptName;
	private String desgName;
	
	private String leaveCode;
	private String toDate;
	  
	private String fromDate;

	private String fromDateType;
	private String toDateType;
	
	private String applyDate;
	
	private String approvedDate;
	private String approevedBy;
	private String shiftCode;
	private String reason;
	private String leaveFor;

	private String cancelBy;
	
	private String cancelDate;

	public EmployeeLeaveRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDesgName() {
		return desgName;
	}

	public void setDesgName(String desgName) {
		this.desgName = desgName;
	}

	public String getLeaveCode() {
		return leaveCode;
	}

	public void setLeaveCode(String leaveCode) {
		this.leaveCode = leaveCode;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getFromDateType() {
		return fromDateType;
	}

	public void setFromDateType(String fromDateType) {
		this.fromDateType = fromDateType;
	}

	public String getToDateType() {
		return toDateType;
	}

	public void setToDateType(String toDateType) {
		this.toDateType = toDateType;
	}

	public String getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}

	public String getApprovedDate() {
		return approvedDate;
	}

	public void setApprovedDate(String approvedDate) {
		this.approvedDate = approvedDate;
	}

	public String getApproevedBy() {
		return approevedBy;
	}

	public void setApproevedBy(String approevedBy) {
		this.approevedBy = approevedBy;
	}

	public String getShiftCode() {
		return shiftCode;
	}

	public void setShiftCode(String shiftCode) {
		this.shiftCode = shiftCode;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getLeaveFor() {
		return leaveFor;
	}

	public void setLeaveFor(String leaveFor) {
		this.leaveFor = leaveFor;
	}

	public String getCancelBy() {
		return cancelBy;
	}

	public void setCancelBy(String cancelBy) {
		this.cancelBy = cancelBy;
	}

	public String getCancelDate() {
		return cancelDate;
	}

	public void setCancelDate(String cancelDate) {
		this.cancelDate = cancelDate;
	}

	public EmployeeLeaveRequest(String empName, String deptName,  String leaveCode, String toDate,
			String fromDate, String applyDate, 
			String approevedBy, String reason, String leaveFor) {
		super();
		this.empName = empName;
		this.deptName = deptName;
		//this.desgName = desgName;
		this.leaveCode = leaveCode;
		this.toDate = toDate;
		this.fromDate = fromDate;
		//this.fromDateType = fromDateType;
		//this.toDateType = toDateType;
		this.applyDate = applyDate;
		//this.approvedDate = approvedDate;
		this.approevedBy = approevedBy;
		//this.shiftCode = shiftCode;
		this.reason = reason;
		this.leaveFor = leaveFor;
		//this.cancelBy = cancelBy;
		//this.cancelDate = cancelDate;
	}
	
	
	
}

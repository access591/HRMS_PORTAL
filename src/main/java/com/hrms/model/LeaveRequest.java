package com.hrms.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="LEAVE_REQUEST")
public class LeaveRequest  {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "LEAVE_REQUEST_ID")
	private Long leaveRequestId;
	
	
	private String empCode;
	
	private String empName;
	
	private String deptCode;
	
	private String deptName;

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
	
	private String numberOfLeaves;
	
	private String requestFromDate;
	
	private String requestToDate;
	
	private String requestFromDateType;
	
	private String requestToDateTYpe;
	
	@Column(name = "INS_BY", updatable = false)
	private String insBy;

	@Column(name = "INS_DATE", updatable = false)
	private Date insDate = new Date();

	@Column(name = "UPD_BY", insertable = false)
	private String updBy;

	@Column(name = "UPD_DATE", insertable = false)
	private Date updDate = new Date();
	

	private String status = new String("N");
	
	
	public LeaveRequest() {
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


	public Long getLeaveRequestId() {
		return leaveRequestId;
	}


	public void setLeaveRequestId(Long leaveRequestId) {
		this.leaveRequestId = leaveRequestId;
	}


	public String getEmpCode() {
		return empCode;
	}


	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}


	public String getDeptCode() {
		return deptCode;
	}


	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
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


	public String getNumberOfLeaves() {
		return numberOfLeaves;
	}


	public void setNumberOfLeaves(String numberOfLeaves) {
		this.numberOfLeaves = numberOfLeaves;
	}


	public String getRequestFromDate() {
		return requestFromDate;
	}


	public void setRequestFromDate(String requestFromDate) {
		this.requestFromDate = requestFromDate;
	}


	public String getRequestToDate() {
		return requestToDate;
	}


	public void setRequestToDate(String requestToDate) {
		this.requestToDate = requestToDate;
	}


	public String getRequestFromDateType() {
		return requestFromDateType;
	}


	public void setRequestFromDateType(String requestFromDateType) {
		this.requestFromDateType = requestFromDateType;
	}


	public String getRequestToDateTYpe() {
		return requestToDateTYpe;
	}


	public void setRequestToDateTYpe(String requestToDateTYpe) {
		this.requestToDateTYpe = requestToDateTYpe;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "LeaveRequest [leaveRequestId=" + leaveRequestId + ", empCode=" + empCode + ", empName=" + empName
				+ ", deptCode=" + deptCode + ", deptName=" + deptName + ", leaveCode=" + leaveCode + ", toDate="
				+ toDate + ", fromDate=" + fromDate + ", fromDateType=" + fromDateType + ", toDateType=" + toDateType
				+ ", applyDate=" + applyDate + ", approvedDate=" + approvedDate + ", approevedBy=" + approevedBy
				+ ", shiftCode=" + shiftCode + ", reason=" + reason + ", leaveFor=" + leaveFor + ", cancelBy="
				+ cancelBy + ", cancelDate=" + cancelDate + ", numberOfLeaves=" + numberOfLeaves + ", requestFromDate="
				+ requestFromDate + ", requestToDate=" + requestToDate + ", requestFromDateType=" + requestFromDateType
				+ ", requestToDateTYpe=" + requestToDateTYpe + ", insBy=" + insBy + ", insDate=" + insDate + ", updBy="
				+ updBy + ", updDate=" + updDate + ", status=" + status + "]";
	}

	

	
	
	
	

}

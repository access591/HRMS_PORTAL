package com.hrms.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
//@Table(name="leave_request")
@Table(name="LEAVE_REQUEST")
public class LeaveRequest  {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "LEAVE_REQUEST_ID")
	private Long leaveRequestId;
	
	@Column(name = "EMP_CODE")
	private String empCode;
	
	@Column(name = "EMP_NAME")
	private String empName;
	
	@Column(name = "DEPT_CODE")
	private String deptCode;
	
	@Column(name = "DEPT_NAME")
	private String deptName;
	
	@Column(name = "LEAVE_CODE")  
	private String leaveCode;
	
	@Column(name = "TO_DATE")
	private Date toDate;
	
	@Column(name = "FROM_DATE")
	private Date fromDate;
	
	@Column(name = "FROM_DATE_TYPE")
	private String fromDateType;
	
	@Column(name = "TO_DATE_TYPE")
	private String toDateType;
	
	@Column(name = "APPLY_DATE")
	private Date applyDate;
	
	@Column(name = "APPROVE_DATE")
	private Date approvedDate;
	
	@Column(name = "APPROVED_BY")
	private String approevedBy;
	
	@Column(name = "SHIFT_CODE")
	private String shiftCode;
	
	@Column(name = "REASON")
	private String reason;
	
	@Column(name = "LEAVE_FOR")
	private String leaveFor;
	
	@Column(name = "CANCEL_BY")
	private String cancelBy;
	
	@Column(name = "CANCEL_DATE")
	private String cancelDate;
	
	@Column(name = "NUMBER_OF_LEAVES")
	private String numberOfLeaves;
	
	@Column(name = "REQUEST_FROM_DATE")
	private String requestFromDate;
	
	@Column(name = "REQUEST_TO_DATE")
	private String requestToDate;
	
	@Column(name = "REQUEST_FROM_DATE_TYPE")
	private String requestFromDateType;
	
	@Column(name = "REQUEST_TO_DATE_TYPE")
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


	public Date getToDate() {
		return toDate;
	}


	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}


	public Date getFromDate() {
		return fromDate;
	}


	public void setFromDate(Date fromDate) {
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


	public Date getApplyDate() {
		return applyDate;
	}


	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}


	public Date getApprovedDate() {
		return approvedDate;
	}


	public void setApprovedDate(Date approvedDate) {
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

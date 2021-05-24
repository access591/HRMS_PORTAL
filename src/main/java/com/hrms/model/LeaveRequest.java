package com.hrms.model;



import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
//@Table(name="leave_request")
@Table(name="LEAVE_REQUEST")
public class LeaveRequest implements Serializable  {  
	
	


	/**
	 * 
	 */
	private static final long serialVersionUID = -1127997960626777460L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "LEAVE_REQUEST_ID" ,length=100)
	private Long leaveRequestId;
	
	
//	@Column(name = "EMP_CODE")
//	private String empCode;
	
	@ManyToOne
	@JoinColumn(name = "EMP_CODE")
	private Employee employee;

	
//	@Column(name = "DEPT_CODE",length=50)
//	private String deptCode;
	
	@ManyToOne
	@JoinColumn(name = "DEPT_CODE")
	private Department department;
	

	
//	@Column(name = "LEAVE_CODE")  
//	private String leaveCode;

	@ManyToOne
	@JoinColumn(name = "LEAVE_CODE")
	private Leave leave;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "TO_DATE")  
	private Date toDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "FROM_DATE")  
	private Date fromDate;

	
	@Column(name = "FROM_DATE_TYPE",length=10)
	private String fromDateType;
	
	@Column(name = "TO_DATE_TYPE",length=10)
	private String toDateType;
	
	//@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "APPLY_DATE")
	private Date applyDate;
	
	@Column(name = "APPROVE_DATE",length=100)
	private String approvedDate;

	
	@Column(name = "APPROVED_BY",length=20)
	private String approevedBy;
	
	@Column(name = "SHIFT_CODE",length=20)
	private String shiftCode;
	
	@Column(name = "REASON",length=200)
	private String reason;
	
	@Column(name = "DAYS",length=20)
	private String leaveFor;

	
	@Column(name = "CANCEL_BY",length=20)
	private String cancelBy;
	
	@Column(name = "CANCEL_DATE",length=20)
	private String cancelDate;
	
	
	
	@Column(name = "REQUEST_FROM_DATE",length=20)
	private String requestFromDate;
	
	@Column(name = "REQUEST_TO_DATE",length=20)
	private String requestToDate;
	
	@Column(name = "REQUEST_FROM_DATE_TYPE",length=10)
	private String requestFromDateType;
	
	@Column(name = "REQUEST_TO_DATE_TYPE",length=10)
	private String requestToDateTYpe;
	
	@Column(name = "INS_BY", updatable = false,length=20)
	private String insBy;

	@Column(name = "INS_DATE", updatable = false,length=20)
	private Date insDate = new Date();

	@Column(name = "UPD_BY", insertable = false,length=20)
	private String updBy;

	@Column(name = "UPD_DATE", insertable = false,length=20)
	private Date updDate = new Date();
	
	@Column(length=20)
	private String status = new String("N");
	
	@Column(name = "REQUEST_TYPE",length=20)
	private String requestType;

	public Long getLeaveRequestId() {
		return leaveRequestId;
	}

	public void setLeaveRequestId(Long leaveRequestId) {
		this.leaveRequestId = leaveRequestId; 
	}

	

//	public String getEmpCode() {
//		return empCode;
//	}
//
//	public void setEmpCode(String empCode) {
//		this.empCode = empCode;
//	}
//
//	public String getDeptCode() {
//		return deptCode;
//	}
//
//	public void setDeptCode(String deptCode) {
//		this.deptCode = deptCode;
//	}
//
//	public String getLeaveCode() {
//		return leaveCode;
//	}
//
//	public void setLeaveCode(String leaveCode) {
//		this.leaveCode = leaveCode;
//	}
	
	


	public Employee getEmployee() {
		return employee;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Leave getLeave() {
		return leave;
	}

	public void setLeave(Leave leave) {
		this.leave = leave;
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

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public LeaveRequest() {
		super();
		// TODO Auto-generated constructor stub
	}


	
	

	

	
	
	


	

	
	
	
	

}

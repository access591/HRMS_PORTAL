package com.hrms.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "LEAVE_GRANT")
public class LeaveGrant  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5399997637115740573L;
	
	@Id
	@Column(name = " EMP_CODE")
	private String empCode;
	
	@Column(name ="EMP_NAME")
	private String empName;
	@Column(name = "LEAVE_CODE")
	private String leaveCode;

	@Column(name = "NO_OF_LEAVES_GRANTED")
	private String noOfLeavesGranted;

	@Column(name = " PREVIOUS_YR_BALANCE")
	private String previousYrBalance;

	@Column(name = "YEAR")
	private String year;

	@Column(name = "LEAVE_TYPE")
	private String leaveType;

	@Column(name = "LEAVE_AVAILED")
	private String leaveAvailed;

	@Column(name = "CLOSING_BAL")
	private String closingBal;

	
	@Column(name = "INS_BY", updatable = false)
	private String insBy;

	@Column(name = "INS_DATE", updatable = false)
	private Date insDate = new Date();

	@Column(name = "UPD_BY", insertable = false)
	private String updBy;

	@Column(name = "UPD_DATE", insertable = false)
	private Date updDate = new Date();

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

	public String getLeaveCode() {
		return leaveCode;
	}

	public void setLeaveCode(String leaveCode) {
		this.leaveCode = leaveCode;
	}

	public String getNoOfLeavesGranted() {
		return noOfLeavesGranted;
	}

	public void setNoOfLeavesGranted(String noOfLeavesGranted) {
		this.noOfLeavesGranted = noOfLeavesGranted;
	}

	public String getPreviousYrBalance() {
		return previousYrBalance;
	}

	public void setPreviousYrBalance(String previousYrBalance) {
		this.previousYrBalance = previousYrBalance;
	}

	
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	
	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	public String getLeaveAvailed() {
		return leaveAvailed;
	}

	public void setLeaveAvailed(String leaveAvailed) {
		this.leaveAvailed = leaveAvailed;
	}

	public String getClosingBal() {
		return closingBal;
	}

	public void setClosingBal(String closingBal) {
		this.closingBal = closingBal;
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

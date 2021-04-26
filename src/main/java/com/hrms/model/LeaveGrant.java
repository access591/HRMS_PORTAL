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
	@Column(name="LEAVEGRANT_CODE")
	private String leaveGrantCode;
	
	@Column(name = " EMP_CODE")
	private String empCode;
	
	@Column(name ="EMP_NAME")
	private String empName;
	@Column(name = "LEAVE_CODE")
	private String leaveCode;

	@Column(name = "NO_OF_LEAVES_GRANTED")
	private int noOfLeavesGranted;

	@Column(name = " PREVIOUS_YR_BALANCE")
	private int previousYrBalance;

	@Column(name = "YEAR")
	private String year;

	@Column(name = "LEAVE_TYPE")
	private String leaveType;

	@Column(name = "LEAVE_AVAILED")
	private int leaveAvailed;

	@Column(name = "CLOSING_BAL")
	private int closingBal;

	
	public String getLeaveGrantCode() {
		return leaveGrantCode;
	}

	public void setLeaveGrantCode(String leaveGrantCode) {
		this.leaveGrantCode = leaveGrantCode;
	}

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

	public int getNoOfLeavesGranted() {
		return noOfLeavesGranted;
	}

	public void setNoOfLeavesGranted(int noOfLeavesGranted) {
		this.noOfLeavesGranted = noOfLeavesGranted;
	}

	public int getPreviousYrBalance() {
		return previousYrBalance;
	}

	public void setPreviousYrBalance(int previousYrBalance) {
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

	public int getLeaveAvailed() {
		return leaveAvailed;
	}

	public void setLeaveAvailed(int leaveAvailed) {
		this.leaveAvailed = leaveAvailed;
	}

	public int getClosingBal() {
		return closingBal;
	}

	public void setClosingBal(int closingBal) {
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

package com.hrms.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "M_LEAVE_GRANT")
public class LeaveGrant  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5399997637115740573L;
	/*
	 * @Id
	 * 
	 * @GeneratedValue(strategy= GenerationType.IDENTITY) private Long id;
	 */
	 @Id
	 @Column(name ="CARD_NO")
	 private int cardNo;
	 
	 @Column(name ="C_CODE")
	 private String ccode;
	 
	 @Column(name ="CLOSING_BAL")
	 private String closingBal;
	 
	 @Column(name =" EMP_CODE")
	 private String empCode;
	 
	 @Column(name =" FYCODE")
	 private String  fycode;
	 
	 @Column(name ="FYR_TYPE")
	 private String fyrType;
	 
	 @Column(name ="LEAVE_AVAILED")
	 private String leaveAvailed;
	 
	 @Column(name ="LEAVE_CODE")
	 private String leaveCode;
	 
	 @Column(name ="NO_OF_LEAVES_GRANTED")
	 private String noOfLeavesGranted;
	 
	 @Column(name ="NO_OF_LEAVES_PENDING")
	 private String  noOfLeavesPending;
	 
	 @Column(name =" PREVIOUS_YR_BALANCE")
	 private String  previousYrBalance;
	 
	 @Column(name ="UCODE")
	 private String ucode;
	 
	 @Column(name ="UDATE")
	 private String udate;
	 
	 @Column(name ="YEAR")
	 private Date year;
	 
	
		/*
		 * public Long getId() { return id; } public void setId(Long id) { this.id = id;
		 * }
		 */
	public int getCardNo() {
		return cardNo;
	}
	public void setCardNo(int cardNo) {
		this.cardNo = cardNo;
	}
	public String getCcode() {
		return ccode;
	}
	public void setCcode(String ccode) {
		this.ccode = ccode;
	}
	public String getClosingBal() {
		return closingBal;
	}
	public void setClosingBal(String closingBal) {
		this.closingBal = closingBal;
	}
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	public String getFycode() {
		return fycode;
	}
	public void setFycode(String fycode) {
		this.fycode = fycode;
	}
	public String getFyrType() {
		return fyrType;
	}
	public void setFyrType(String fyrType) {
		this.fyrType = fyrType;
	}
	public String getLeaveAvailed() {
		return leaveAvailed;
	}
	public void setLeaveAvailed(String leaveAvailed) {
		this.leaveAvailed = leaveAvailed;
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
	public String getNoOfLeavesPending() {
		return noOfLeavesPending;
	}
	public void setNoOfLeavesPending(String noOfLeavesPending) {
		this.noOfLeavesPending = noOfLeavesPending;
	}
	public String getPreviousYrBalance() {
		return previousYrBalance;
	}
	public void setPreviousYrBalance(String previousYrBalance) {
		this.previousYrBalance = previousYrBalance;
	}
	public String getUcode() {
		return ucode;
	}
	public void setUcode(String ucode) {
		this.ucode = ucode;
	}
	public String getUdate() {
		return udate;
	}
	public void setUdate(String udate) {
		this.udate = udate;
	}
	public Date getYear() {
		return year;
	}
	public void setYear(Date year) {
		this.year = year;
	} 
	
	
}

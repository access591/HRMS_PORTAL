package com.hrms.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name = "EMP_LOCAL_RMBSMT")
public class LocalConvyence implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6235454307588629360L;
	@Id
	@Column(name = "LOCAL_CONV_ID")
	private String localConvId;
	
	@Column(name ="LOCAL_CONV_DT")
	private Date localConvDate;
	
	@Column(name ="TOTAL_CLAIM")
	private int totalClaim;
	
	@Column(name ="TOTAL_PASS")
	private int totalPas;
	
	@ManyToOne
	@JoinColumn(name = "EMPLOYEE_CODE",updatable = false)
	private Employee empCode;
	
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
	
	@Column(name = "FILL_DATE", updatable = false)
	private Date filDate = new Date();

	public String getLocalConvId() {
		return localConvId;
	}

	public void setLocalConvId(String localConvId) {
		this.localConvId = localConvId;
	}

	public Date getLocalConvDate() {
		return localConvDate;
	}

	public void setLocalConvDate(Date localConvDate) {
		this.localConvDate = localConvDate;
	}

	public int getTotalClaim() {
		return totalClaim;
	}

	public void setTotalClaim(int totalClaim) {
		this.totalClaim = totalClaim;
	}

	public int getTotalPas() {
		return totalPas;
	}

	public void setTotalPas(int totalPas) {
		this.totalPas = totalPas;
	}

	public Employee getEmpCode() {
		return empCode;
	}

	public void setEmpCode(Employee empCode) {
		this.empCode = empCode;
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

	public Date getFilDate() {
		return filDate;
	}

	public void setFilDate(Date filDate) {
		this.filDate = filDate;
	}
	
	
	

			
}

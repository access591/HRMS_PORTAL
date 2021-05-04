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
@Table(name = "MEDICLAIM_DETAILS")
public class MedicalReimbursement implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5378221635565507400L;

	@Id
	@Column(name = "SLIP_NO")
	private String slipNo;

	@Column(name = "SLIP_DATE")
	private Date dateOfSlip;
	@ManyToOne
	@JoinColumn(name = "EMPLOYEE_CODE", nullable = false)
	private Employee empCode;

	@Column(name = "MONTH")
	private Date claimingDate;

	@Column(name = "NAME_OF_PERSON")
	private String nameOfPerson;

	@Column(name = "EMP_RELATION")
	private String empRelation;

	@Column(name = "TREATMENT_TYPE")
	private String treatmentType;

	@Column(name = "TREAT_DESCRIPTION")
	private String treatDescription;
	@Column(name = "MED_IND_OUT")
	private String medIndOut;
	
	//>>>>>>>>>>>>>>>>>>>>>>>>>second Step Start>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	@Id
	@Column(name = "SR_NO")
	private String  srNo;  
	@Column(name = "MED_ST_DR_NAME")
	private String medStDr ;  
	@Column(name = "CASHMEMO_NO")
	private String caseMemoNo;
	
	@Column(name = "CASHMEMO_DATE")
	private String caseMemoDate;
	
	@Column(name = "AMOUNT")
	private String calmlAmount;
	
	@Column(name = "PASSED_AMT")
	private String passedAmount;
	@Column(name = "GOVT_EXMPT_AMT")
	private String govtexemptionAmount;
	@Column(name = "REMARKS")
	private String remark;
	
	//>>>>>>>>>>>>>>>>>>>>>>>>>3 Step Start>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

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
	
	public String getSlipNo() {
		return slipNo;
	}

	public void setSlipNo(String slipNo) {
		this.slipNo = slipNo;
	}

	public Date getDateOfSlip() {
		return dateOfSlip;
	}

	public void setDateOfSlip(Date dateOfSlip) {
		this.dateOfSlip = dateOfSlip;
	}

	public Employee getEmpCode() {
		return empCode;
	}

	public void setEmpCode(Employee empCode) {
		this.empCode = empCode;
	}

	public Date getClaimingDate() {
		return claimingDate;
	}

	public void setClaimingDate(Date claimingDate) {
		this.claimingDate = claimingDate;
	}

	public String getNameOfPerson() {
		return nameOfPerson;
	}

	public void setNameOfPerson(String nameOfPerson) {
		this.nameOfPerson = nameOfPerson;
	}

	public String getEmpRelation() {
		return empRelation;
	}

	public void setEmpRelation(String empRelation) {
		this.empRelation = empRelation;
	}

	public String getTreatmentType() {
		return treatmentType;
	}

	public void setTreatmentType(String treatmentType) {
		this.treatmentType = treatmentType;
	}

	public String getTreatDescription() {
		return treatDescription;
	}

	public void setTreatDescription(String treatDescription) {
		this.treatDescription = treatDescription;
	}

	public String getMedIndOut() {
		return medIndOut;
	}

	public void setMedIndOut(String medIndOut) {
		this.medIndOut = medIndOut;
	}
//...............................................

	public String getSrNo() {
		return srNo;
	}

	public void setSrNo(String srNo) {
		this.srNo = srNo;
	}

	public String getMedStDr() {
		return medStDr;
	}

	public void setMedStDr(String medStDr) {
		this.medStDr = medStDr;
	}

	public String getCaseMemoNo() {
		return caseMemoNo;
	}

	public void setCaseMemoNo(String caseMemoNo) {
		this.caseMemoNo = caseMemoNo;
	}

	public String getCaseMemoDate() {
		return caseMemoDate;
	}

	public void setCaseMemoDate(String caseMemoDate) {
		this.caseMemoDate = caseMemoDate;
	}

	public String getCalmlAmount() {
		return calmlAmount;
	}

	public void setCalmlAmount(String calmlAmount) {
		this.calmlAmount = calmlAmount;
	}

	public String getPassedAmount() {
		return passedAmount;
	}

	public void setPassedAmount(String passedAmount) {
		this.passedAmount = passedAmount;
	}

	public String getGovtexemptionAmount() {
		return govtexemptionAmount;
	}

	public void setGovtexemptionAmount(String govtexemptionAmount) {
		this.govtexemptionAmount = govtexemptionAmount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

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
	
	
}

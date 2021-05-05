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
@Table(name = "MEDICLAIM_MASTER")
public class MedicalReimbursement implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2627010190406966654L;

	/**
	 * 
	 */
	

	/**
	 * 
	 */

	

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

	
	
	
}

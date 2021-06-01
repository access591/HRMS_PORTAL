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

@Entity
@Table(name = "INDUCT_TRAINING_DET")
public class InductionTrainingDetail  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2614057709105003896L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@ManyToOne
	@JoinColumn(name = "EMPLOYEE_CODE",updatable = false)
	private Employee empCode;
	@ManyToOne
	@JoinColumn(name = "DEPARTMENT_CODE",updatable = false)
	private Department deptCode;
	
	
	@Column(name = "CONT_PERSON")
	private String contPerson;

	@Column(name = "TRAINING_DATE")
	private Date trainingDate;

	@Column(name = "DURATION")
	private String duration;

	@Column(name = "INS_BY", updatable = false)
	private String insBy;

	@Column(name = "INS_DATE", updatable = false)
	private Date insDate = new Date();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Employee getEmpCode() {
		return empCode;
	}

	public void setEmpCode(Employee empCode) {
		this.empCode = empCode;
	}

	public Department getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(Department deptCode) {
		this.deptCode = deptCode;
	}

	public String getContPerson() {
		return contPerson;
	}

	public void setContPerson(String contPerson) {
		this.contPerson = contPerson;
	}

	public Date getTrainingDate() {
		return trainingDate;
	}

	public void setTrainingDate(Date trainingDate) {
		this.trainingDate = trainingDate;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
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

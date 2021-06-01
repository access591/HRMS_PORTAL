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
@Table(name = "INDUCT_TRAINING_MAST")
public class InductionTraining implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3128717299783557771L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@ManyToOne
	@JoinColumn(name = "EMPLOYEE_CODE",updatable = false)
	private Employee empCode;
	@ManyToOne
	@JoinColumn(name = "DEPARTMENT_CODE",updatable = false)
	private Department deptCode;
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

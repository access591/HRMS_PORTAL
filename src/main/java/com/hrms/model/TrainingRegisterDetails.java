package com.hrms.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TR_REGISTER_DETAIL")
public class TrainingRegisterDetails implements Serializable {

	/**
	 * 
	 * 
	 */
	private static final long serialVersionUID = -6290645706436649061L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "TR_REG_CODE")
	private TrainingRegister trainingRegister;

	@ManyToOne
	@JoinColumn(name = "TR_REG_DATE")
	private TrainingRegister trRegDate;
	
	@ManyToOne
	@JoinColumn(name = "EMPLOYEE_CODE")
	private Employee  employee;
	
	
	@Column(name = "EMP_FEEDBACK")
	private  String  empFeedback;


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public TrainingRegister getTrainingRegister() {
		return trainingRegister;
	}


	public void setTrainingRegister(TrainingRegister trainingRegister) {
		this.trainingRegister = trainingRegister;
	}


	public TrainingRegister getTrRegDate() {
		return trRegDate;
	}


	public void setTrRegDate(TrainingRegister trRegDate) {
		this.trRegDate = trRegDate;
	}


	public Employee getEmployee() {
		return employee;
	}


	public void setEmployee(Employee employee) {
		this.employee = employee;
	}


	public String getEmpFeedback() {
		return empFeedback;
	}


	public void setEmpFeedback(String empFeedback) {
		this.empFeedback = empFeedback;
	}
	
	
	  
	
}

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
	 */
	private static final long serialVersionUID = -6290645706436649061L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "TR_SCH_CODE")
	private TrainingRegister trSchCode;
	
	
	@ManyToOne
	@JoinColumn(name = "TR_REG_DATE")
	private TrainingRegister trRegCode;
	
	@ManyToOne
	@JoinColumn(name = "EMPLOYEE_CODE")
	private Employee  empCode;
	
	
	@Column(name = "EMP_FEEDBACK")
	private  String  empFeedback;
	
	
	  
	
}

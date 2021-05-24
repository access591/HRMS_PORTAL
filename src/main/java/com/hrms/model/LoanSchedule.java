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
@Table(name = "LOAN_SCHEDULE")

public class LoanSchedule implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7986595644818206372L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "APP_NO",updatable = false)
	private LoanApplication appNo;
	
	@ManyToOne
	@JoinColumn(name = "APP_DATE",updatable = false)
	private LoanApplication appDate;

	@Column(name = "INST_NO")
	private int instNo;

	@Column(name = "INST_DATE")
	private Date instDate;

	@Column(name = "INST_PAID")
	private int instPaid;

	@Column(name = "INST_AMT")
	private int instAmt;

	@Column(name = "INST_PAID_DATE")
	private Date instPaidDate;
	
}

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
@Table(name = "EMPLOYEE_ENQUIRY")
public class TrackallEnquiries implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3100963585510436672L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EMP_ENQ_ID")
	private  long  id;
	
	@ManyToOne
	@JoinColumn(name = "EMP_CODE")
	private Employee  empCode;
	
	@ManyToOne
	@JoinColumn(name = "DESIGN_CODE")
	private Designation designCode;
	
	@ManyToOne
	@JoinColumn(name = "CATEGORY_CODE")
	private Category categoryCode;
	
	@Column(name = "PRESENT_POSTING")
	private String prsentPosting;
	@Column(name = "DOB")
	private Date dob;
	
	@Column(name = "DEPUTATION_FILE_NO")
	private String depuFileNo;
	
	@Column(name = "DEPUTATION_PERIOD")
	private String deputationPeriod;
	

}

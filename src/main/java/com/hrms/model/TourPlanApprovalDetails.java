package com.hrms.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
@Entity(name="TOUR_PLAN_DT")
public class TourPlanApprovalDetails implements Serializable {

	/**
	 * 
	 */
private static final long serialVersionUID = 8658800224712878231L;

@Id
@Column(name = "TOUR_PLAN_ID")
private String  tourPlanId;

@Column(name = "TOUR_PLAN_DT")
private Date  tourPlanDate;

@Column(name = "START_PLACE")
private String  startPlace;

@Column(name = "END_PLACE")
private String  endPlace;


}

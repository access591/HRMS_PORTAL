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
@Entity(name="TOUR_PLAN_DT")
public class TourPlanApprovalDetails implements Serializable {

	/**
	 * 
	 */
private static final long serialVersionUID = 8658800224712878231L;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private long id;
@ManyToOne
@JoinColumn(name = "TOUR_PLAN_ID")
private TourPlan  tourPlanId;
@ManyToOne
@JoinColumn(name = "TOUR_PLAN_DT")
private TourPlan  tourPlanDate;

@Column(name = "START_PLACE")
private String  startPlace;

@Column(name = "END_PLACE")
private String  endPlace;
@Column(name = "FROM_DT")
private Date  fromDate;
@Column(name = "TO_DATE")
private Date  toDate;
@Column(name = "PURPOSE")
private String  purpose;

public TourPlan getTourPlanId() {
	return tourPlanId;
}
public void setTourPlanId(TourPlan tourPlanId) {
	this.tourPlanId = tourPlanId;
}
public TourPlan getTourPlanDate() {
	return tourPlanDate;
}
public void setTourPlanDate(TourPlan tourPlanDate) {
	this.tourPlanDate = tourPlanDate;
}
public String getStartPlace() {
	return startPlace;
}
public void setStartPlace(String startPlace) {
	this.startPlace = startPlace;
}
public String getEndPlace() {
	return endPlace;
}
public void setEndPlace(String endPlace) {
	this.endPlace = endPlace;
}
public Date getFromDate() {
	return fromDate;
}
public void setFromDate(Date fromDate) {
	this.fromDate = fromDate;
}
public Date getToDate() {
	return toDate;
}
public void setToDate(Date toDate) {
	this.toDate = toDate;
}
public String getPurpose() {
	return purpose;
}
public void setPurpose(String purpose) {
	this.purpose = purpose;
}



}

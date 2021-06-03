package com.hrms.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ORDER_ISSUE_TRACKING")
public class OrderIssueTracking {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ORDER_TR_ID")
	private Long orderTrackingId;
	
	@Column(name="DATE_OF_ISSUE")
	private Date dateOfIssue;
	
	@Column(name="ORDER_NUMBER")
	private String orderNumber;
	
	@Column(name="ORDER_TITLE")
	private String orderTitle;
	
	@Column(name="ORDER_DESCRIPTION")
	private String orderDescription;
	
	@Column(name="DEPT_CODE")
	private String deptCode;
	
	@Column(name="ISSUE_BY")
	private String issueBy;

	public OrderIssueTracking() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getOrderTrackingId() {
		return orderTrackingId;
	}

	public void setOrderTrackingId(Long orderTrackingId) {
		this.orderTrackingId = orderTrackingId;
	}

	public Date getDateOfIssue() {
		return dateOfIssue;
	}

	public void setDateOfIssue(Date dateOfIssue) {
		this.dateOfIssue = dateOfIssue;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getOrderTitle() {
		return orderTitle;
	}

	public void setOrderTitle(String orderTitle) {
		this.orderTitle = orderTitle;
	}

	public String getOrderDescription() {
		return orderDescription;
	}

	public void setOrderDescription(String orderDescription) {
		this.orderDescription = orderDescription;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getIssueBy() {
		return issueBy;
	}

	public void setIssueBy(String issueBy) {
		this.issueBy = issueBy;
	}
	
	

}

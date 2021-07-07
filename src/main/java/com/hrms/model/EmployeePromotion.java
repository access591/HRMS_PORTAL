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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name = "EMP_PROMOTION")
public class EmployeePromotion implements Serializable {

	/**
	 * Auth Surendra
	 */
	private static final long serialVersionUID = -3704869556831614220L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name ="MEMO_NO")
	private long memoNo;
	@ManyToOne
	@JoinColumn(name = "EMPLOYEE_CODE",updatable = false)
	private Employee empCode;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "PROMOTION_DATE")
	private Date promotionDate;
	
	@Column(name = "REMARKS")
	private String remarks;
	
	@Column(name = "OLD_POST")
	private String oldPost;
	
	@Column(name = "new_POST")
	private String newPost;

	@Column(name = "EMP_UPLOAD__DOC", nullable = true)
	private String empUploadDoc;


	public long getMemoNo() {
		return memoNo;
	}

	public void setMemoNo(long memoNo) {
		this.memoNo = memoNo;
	}

	public Employee getEmpCode() {
		return empCode;
	}

	public void setEmpCode(Employee empCode) {
		this.empCode = empCode;
	}

	public Date getPromotionDate() {
		return promotionDate;
	}

	public void setPromotionDate(Date promotionDate) {
		this.promotionDate = promotionDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getOldPost() {
		return oldPost;
	}

	public void setOldPost(String oldPost) {
		this.oldPost = oldPost;
	}

	public String getNewPost() {
		return newPost;
	}

	public void setNewPost(String newPost) {
		this.newPost = newPost;
	}

	public String getEmpUploadDoc() {
		return empUploadDoc;
	}

	public void setEmpUploadDoc(String empUploadDoc) {
		this.empUploadDoc = empUploadDoc;
	}
	
	
	
	
}
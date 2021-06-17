package com.hrms.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="TRAININGREQUISITION")
public class TrainingRequisition {
	
	@Id
	@Column(name="TR_REQ_CODE")
	private String trReqCode;
	
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@Column(name="TR_REQ_DATE")
	private LocalDate trReqDate;
	
	@ManyToOne
	@JoinColumn(name = "DEPT_CODE",updatable=false)
	private Department department;
	
	@Column(name="TR_APPROVER")
	private String trApprover;
	
	@Column(name="TR_APPROVER_DATE")
	private Date trApproverDate;
	
	@Column(name="REMARKSE")
	private String remarks;
	
	@Column(name="FY_CODE")   
	private String fyCode;
	
	@Column(name="INS_BY")
	private String insBy;
	
	@Column(name="INS_DATE")
	private Date insDate = new Date();
	
	@Column(name="TR_REQ_STATUS")
	private String trReqStatus = "N";
	
	
	@OneToMany(mappedBy="trainingRequisition",cascade = CascadeType.ALL)
	private List<TrainingRequisitionDetail> listTransactionRequisitionDetail;
	
	@OneToMany(mappedBy="trainingRequisition",cascade = CascadeType.ALL)
	private List<TrainingReqEmployeeDetail> listTransactionReqEmployeeDetail;
	
	public String getTrReqCode() {
		return trReqCode;
	}
	public void setTrReqCode(String trReqCode) {
		this.trReqCode = trReqCode;
	}
	public LocalDate getTrReqDate() {
		return trReqDate;
	}
	public void setTrReqDate(LocalDate trReqDate) {
		this.trReqDate = trReqDate;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public String getTrApprover() {
		return trApprover;
	}
	public void setTrApprover(String trApprover) {
		this.trApprover = trApprover;
	}
	public Date getTrApproverDate() {
		return trApproverDate;
	}
	public void setTrApproverDate(Date trApproverDate) {
		this.trApproverDate = trApproverDate;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getFyCode() {
		return fyCode;
	}
	public void setFyCode(String fyCode) {
		this.fyCode = fyCode;
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
	public String getTrReqStatus() {
		return trReqStatus;
	}
	public void setTrReqStatus(String trReqStatus) {
		this.trReqStatus = trReqStatus;
	}
	
	
	public List<TrainingRequisitionDetail> getListTransactionRequisitionDetail() {
		return listTransactionRequisitionDetail;
	}
	public void setListTransactionRequisitionDetail(List<TrainingRequisitionDetail> listTransactionRequisitionDetail) {
		this.listTransactionRequisitionDetail = listTransactionRequisitionDetail;
	}
	
	
	public List<TrainingReqEmployeeDetail> getListTransactionReqEmployeeDetail() {
		return listTransactionReqEmployeeDetail;
	}
	public void setListTransactionReqEmployeeDetail(List<TrainingReqEmployeeDetail> listTransactionReqEmployeeDetail) {
		this.listTransactionReqEmployeeDetail = listTransactionReqEmployeeDetail;
	}
	
	public TrainingRequisition() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	
	
	

}

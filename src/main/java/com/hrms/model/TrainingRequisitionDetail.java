package com.hrms.model;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="TRAN_REQUISITION_DETAIL")
public class TrainingRequisitionDetail {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="TR_REQDETAILID")
	private Long reqId;
	
	@Column(name = "TOPIC_SRL_NO")
	private String topicSRLNo;
	
	@Column(name = "PRIORITY")
	private String prioity;
	
	@Column(name = "TOPIC_DTL")
	private String topicDetail;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@Column(name = "TR_DATE")
	private LocalDate trDate;
	
	@Column(name = "REMARKS")
	private String remarks;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TR_REQ_CODE",updatable = false)
	private TrainingRequisition trainingRequisition;
	
	public TrainingRequisitionDetail() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getReqId() {
		return reqId;
	}

	public void setReqId(Long reqId) {
		this.reqId = reqId;
	}

	public String getTopicSRLNo() {
		return topicSRLNo;
	}

	public void setTopicSRLNo(String topicSRLNo) {
		this.topicSRLNo = topicSRLNo;
	}

	public String getPrioity() {
		return prioity;
	}

	public void setPrioity(String prioity) {
		this.prioity = prioity;
	}

	public String getTopicDetail() {
		return topicDetail;
	}

	public void setTopicDetail(String topicDetail) {
		this.topicDetail = topicDetail;
	}

	public LocalDate getTrDate() {
		return trDate;
	}

	public void setTrDate(LocalDate trDate) {
		this.trDate = trDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public TrainingRequisition getTrainingRequisition() {
		return trainingRequisition;
	}

	public void setTrainingRequisition(TrainingRequisition trainingRequisition) {
		this.trainingRequisition = trainingRequisition;
	}
	
	
	
	
	

}

package com.hrms.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "TR_REGISTER_MASTER")
public class TrainingRegister implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6960632326976412838L;
	
	@Id
	@Column(name = "TR_SCH_CODE")
	private String trSchCode;
	
	
	@Column(name = "TR_SCH_DATE")
	private Date trSchDate;
	
	
	@Column(name = "TOPIC_SRL_NO")
	private String topicSrlNo;
	
	
	@Column(name = "TR_REG_CODE")
	private String trRegCode;
	
	   
	@Column(name = "TR_REG_DATE")
	private Date trRegDate;
	
	
	
	@Column(name = "TR_DATE_FROM")
	private Date trDateFrom;
	
	@Column(name = "TR_DATE_TO")
	private Date trDateTO;
	
	
	@Column(name = "TRAINER_CODE")
	private String trainerCode;
	   
	
	@Column(name = "TRAINER")
	private String trainer;
	
	@Column(name = "TR_FEEDBACK")
	private String trFeedback; 
	
	
	@Column(name = "HOD_CODE")
	private String hodCode;       
	  
	@Column(name = "HOD_FEEDBACK")
	private String hodFeedback;       
	      
	@Column(name = "INS_BY", updatable = false)
	private String insBy;

	@Column(name = "INS_DATE", updatable = false)
	private Date insDate = new Date();

	public String getTrSchCode() {
		return trSchCode;
	}

	public void setTrSchCode(String trSchCode) {
		this.trSchCode = trSchCode;
	}

	public Date getTrSchDate() {
		return trSchDate;
	}

	public void setTrSchDate(Date trSchDate) {
		this.trSchDate = trSchDate;
	}

	public String getTopicSrlNo() {
		return topicSrlNo;
	}

	public void setTopicSrlNo(String topicSrlNo) {
		this.topicSrlNo = topicSrlNo;
	}

	public String getTrRegCode() {
		return trRegCode;
	}

	public void setTrRegCode(String trRegCode) {
		this.trRegCode = trRegCode;
	}

	public Date getTrRegDate() {
		return trRegDate;
	}

	public void setTrRegDate(Date trRegDate) {
		this.trRegDate = trRegDate;
	}

	public Date getTrDateFrom() {
		return trDateFrom;
	}

	public void setTrDateFrom(Date trDateFrom) {
		this.trDateFrom = trDateFrom;
	}

	public Date getTrDateTO() {
		return trDateTO;
	}

	public void setTrDateTO(Date trDateTO) {
		this.trDateTO = trDateTO;
	}

	public String getTrainerCode() {
		return trainerCode;
	}

	public void setTrainerCode(String trainerCode) {
		this.trainerCode = trainerCode;
	}

	public String getTrainer() {
		return trainer;
	}

	public void setTrainer(String trainer) {
		this.trainer = trainer;
	}

	public String getTrFeedback() {
		return trFeedback;
	}

	public void setTrFeedback(String trFeedback) {
		this.trFeedback = trFeedback;
	}

	public String getHodCode() {
		return hodCode;
	}

	public void setHodCode(String hodCode) {
		this.hodCode = hodCode;
	}

	public String getHodFeedback() {
		return hodFeedback;
	}

	public void setHodFeedback(String hodFeedback) {
		this.hodFeedback = hodFeedback;
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
	  
	

}

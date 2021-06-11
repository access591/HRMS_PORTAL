package com.hrms.util;

import java.util.Date;


public class TrainingRegisterUtil {
	
	
	private Date trSchDate;
	
	
	
	private String topicSrlNo;
	
	
	private Date trDateFrom;
	
	
	private Date trDateTO;
	
	
	
	private String trainerCode;
	   
	
	
	private String trainer;
	

	private String trFeedback; 
	
	
	
	private String hodCode;       
	  
	
	private String hodFeedback;


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


	public TrainingRegisterUtil(Date trSchDate, String topicSrlNo, Date trDateFrom, Date trDateTO, String trainerCode,
			String trainer, String trFeedback, String hodCode, String hodFeedback) {
		super();
		this.trSchDate = trSchDate;
		this.topicSrlNo = topicSrlNo;
		this.trDateFrom = trDateFrom;
		this.trDateTO = trDateTO;
		this.trainerCode = trainerCode;
		this.trainer = trainer;
		this.trFeedback = trFeedback;
		this.hodCode = hodCode;
		this.hodFeedback = hodFeedback;
	}


	public TrainingRegisterUtil() {
		super();
	}       
	
	
	
}

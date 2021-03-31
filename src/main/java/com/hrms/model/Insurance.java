package com.hrms.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name ="M_INSURANCE")
public class Insurance implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4529410314829331915L;
@Id
@Column(name = "INS_CODE")
private String 	insCode;

@Column(name = "INS_BY",updatable = false)
private String insBy;

@Column(name = "INS_DATE",updatable = false)
private Date insDate =new Date();

@Column(name = "UPD_BY",insertable = false)
private String updBy;

@Column(name = "UPD_DATE",insertable = false)
private Date  updDate = new Date();

public String getInsCode() {
	return insCode;
}
public void setInsCode(String insCode) {
	this.insCode = insCode;
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
public String getUpdBy() {
	return updBy;
}
public void setUpdBy(String updBy) {
	this.updBy = updBy;
}
public Date getUpdDate() {
	return updDate;
}
public void setUpdDate(Date updDate) {
	this.updDate = updDate;
}

}

package com.hrms.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "M_SKILLCATEGORY")
public class SkillCategory implements Serializable {

	/**
	 * 
	 * ACCESS  SURENDRA
	 *  
	 * 
	 */
private static final long serialVersionUID = -5018920894314776560L;
@Id
@Column(name = "SKILL_CODE")
private String skillLevelCode;

@Column(name ="SKILL_LEVEL")
private String skillLevel;

@Column(name ="SKILL_PARA_DESC")
private String skillParaDes;

@Column(name ="SKILL_CAT_DESC")
private String skillCatDesc;

@Column(name ="SKILL_PARA_CODE")
private String skillParaCode;

@Column(name ="SKILL_PARA_MAX_MARK")
private String skillParaMaxMark;
@Column(name = "INS_BY",updatable = false)
private String Ins_by;

@Column(name = "INS_DATE",updatable = false)
private Date ins_date =new Date();

@Column(name = "UPD_BY",insertable = false)
private String upd_by;

@Column(name = "UPD_DATE",insertable = false)
private Date  upd_date = new Date();

@Column(name = "ACTIVE_YN")
private String active;

public String getSkillLevelCode() {
	return skillLevelCode;
}
public void setSkillLevelCode(String skillLevelCode) {
	this.skillLevelCode = skillLevelCode;
}
public String getSkillLevel() {
	return skillLevel;
}
public void setSkillLevel(String skillLevel) {
	this.skillLevel = skillLevel;
}
public String getSkillParaDes() {
	return skillParaDes;
}
public void setSkillParaDes(String skillParaDes) {
	this.skillParaDes = skillParaDes;
}
public String getSkillCatDesc() {
	return skillCatDesc;
}
public void setSkillCatDesc(String skillCatDesc) {
	this.skillCatDesc = skillCatDesc;
}
public String getSkillParaCode() {
	return skillParaCode;
}
public void setSkillParaCode(String skillParaCode) {
	this.skillParaCode = skillParaCode;
}
public String getSkillParaMaxMark() {
	return skillParaMaxMark;
}
public void setSkillParaMaxMark(String skillParaMaxMark) {
	this.skillParaMaxMark = skillParaMaxMark;
}
public String getActive() {
	return active;
}
public void setActive(String active) {
	this.active = active;
}
public String getIns_by() {
	return Ins_by;
}
public void setIns_by(String ins_by) {
	Ins_by = ins_by;
}
public Date getIns_date() {
	return ins_date;
}
public void setIns_date(Date ins_date) {
	this.ins_date = ins_date;
}
public String getUpd_by() {
	return upd_by;
}
public void setUpd_by(String upd_by) {
	this.upd_by = upd_by;
}
public Date getUpd_date() {
	return upd_date;
}
public void setUpd_date(Date upd_date) {
	this.upd_date = upd_date;
}



}

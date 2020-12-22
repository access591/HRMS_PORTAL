package com.hrms.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name ="M_ALLOWANCE_DEDUCTION")
public class MiscAllowance implements Serializable {

	/**
	 * 
	 *  @author Access Surendra
	 */
private static final long serialVersionUID = -1201231658058617763L;
	
	@Id
	@Column(name ="ALLOWANCE_CODE")
	private String Allowance_Code;
	@Column(name ="HEAD")
	private String head;
	@Column(name ="ACT_CODE")
	private String Act_Code;
	@Column(name ="SUB_GROUP_CODE")
	private String Sub_Group_Code;
	@Column(name ="ACTIVE")
	private String active;
	@Column(name ="DESCRIPTION")
	private String description;
	@Column(name ="TYPE")
	private String type;
	@Column(name ="ACCOUNT_NAME")
	private String Account_Name;
	@Column(name ="SUB_GROUP_NAME")
	private String Sub_Group_Name;
	
	@Column(name = "INS_BY")
	private String Ins_by;
	
	@Column(name = "INS_DATE")
	private Date ins_date = new Date();
	
	@Column(name = "UPD_BY")
	private String upd_by;
	
	@Column(name = "UPD_DATE")
	private Date  upd_date = new Date();
	
	
	public String getAllowance_Code() {
		return Allowance_Code;
	}
	public void setAllowance_Code(String allowance_Code) {
		Allowance_Code = allowance_Code;
	}
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public String getAct_Code() {
		return Act_Code;
	}
	public void setAct_Code(String act_Code) {
		Act_Code = act_Code;
	}
	public String getSub_Group_Code() {
		return Sub_Group_Code;
	}
	public void setSub_Group_Code(String sub_Group_Code) {
		Sub_Group_Code = sub_Group_Code;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAccount_Name() {
		return Account_Name;
	}
	public void setAccount_Name(String account_Name) {
		Account_Name = account_Name;
	}
	public String getSub_Group_Name() {
		return Sub_Group_Name;
	}
	public void setSub_Group_Name(String sub_Group_Name) {
		Sub_Group_Name = sub_Group_Name;
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

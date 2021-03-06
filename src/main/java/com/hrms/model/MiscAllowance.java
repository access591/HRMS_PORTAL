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
	private String allowanceCode;
	@Column(name ="HEAD")
	private String head;
	@Column(name ="ACT_CODE")
	private String actCode;
	@Column(name ="SUB_GROUP_CODE")
	private String subGroupCode;
	@Column(name ="ACTIVE")
	private String active;
	@Column(name ="DESCRIPTION")
	private String description;
	@Column(name ="TYPE")
	private String type;
	@Column(name ="ACCOUNT_NAME")
	private String accountName;
	@Column(name ="SUB_GROUP_NAME")
	private String subGroupName;
	
    @Column(name = "INS_BY",updatable = false)
	private String insBy;
	
	@Column(name = "INS_DATE",updatable = false)
	private Date insDate =new Date();
	
	@Column(name = "UPD_BY",insertable = false)
	private String updBy;
	
	@Column(name = "UPD_DATE",insertable = false)
	private Date  updDate = new Date();

	public String getAllowanceCode() {
		return allowanceCode;
	}

	public void setAllowanceCode(String allowanceCode) {
		this.allowanceCode = allowanceCode;
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public String getActCode() {
		return actCode;
	}

	public void setActCode(String actCode) {
		this.actCode = actCode;
	}

	public String getSubGroupCode() {
		return subGroupCode;
	}

	public void setSubGroupCode(String subGroupCode) {
		this.subGroupCode = subGroupCode;
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

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getSubGroupName() {
		return subGroupName;
	}

	public void setSubGroupName(String subGroupName) {
		this.subGroupName = subGroupName;
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

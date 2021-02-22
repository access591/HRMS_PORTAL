package com.hrms.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Myuser")
public class UserEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -862375354973874455L;

	@Id
	@Column(name = "User_code")
	private String userCode;

	@Column(name = "User_Name")
	private String userName;

	@Column(name = "Emp_code")
	private String empCode;

	@Column(name = "User_pass")
	private String userPass;

	@Column(name = "User_active_yn")
	private String userActiveYn;

	@Column(name = "Ins_by",updatable = false)
	private String insBy;

	@Column(name = "ins_date",updatable = false)
	private Date insDate= new Date();

	@Column(name = "upd_by",insertable = false)
	private String updBy;

	@Column(name = "upd_date",insertable = false)
	private Date updDate=new Date(); 
	@Column(name ="Desg_Name")
	private String  DesgName;
	
	
	public String getDesgName() {
		return DesgName;
	}

	public void setDesgName(String desgName) {
		DesgName = desgName;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public String getUserPass() {
		return userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	public String getUserActiveYn() {
		return userActiveYn;
	}

	public void setUserActiveYn(String userActiveYn) {
		this.userActiveYn = userActiveYn;
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
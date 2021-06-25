package com.hrms.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="Myuser")
public class UserEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -862375354973874455L;

	@Id
	@Size(max = 15)
	@Column(name = "User_code")
	private String userCode;
	@Size(max =80)
	@Column(name = "User_Name")
	private String userName;
	@ManyToOne
	@JoinColumn(name = "Emp_code")
	private Employee empCode;
	@Size(max =100)
	@Column(name = "User_pass")
	private String userPass;
	@Size(max = 1)
	@Column(name = "User_active_yn")
	private String userActiveYn;
	@Size(max =50)
	@Column(name = "Ins_by",updatable = false)
	private String insBy;

	@Column(name = "ins_date",updatable = false)
	private Date insDate= new Date();
	@Size(max =50)
	@Column(name = "upd_by",insertable = false)
	private String updBy;

	@Column(name = "upd_date",insertable = false)
	private Date updDate=new Date(); 
	
	


	
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

	
	public Employee getEmpCode() {
		return empCode;
	}

	public void setEmpCode(Employee empCode) {
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
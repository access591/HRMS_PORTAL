package com.hrms.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "M_URIGHTS")
public class UserRights implements Serializable {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3115353075342584614L;

	@Id
	@Column(name = "USER_CODE")
	private String user_code;
	
	@Column(name = "MODULE_CODE")
	private String module_code;
	
	@Column(name = "SUB_MODULE_CODE")
	private String sub_module_code;
	
	@Column(name = "PRG_CODE")
	private String prg_code;
	
	@Column(name = "ACTIVE_YN")
	private String active_yn;
	
	@Column(name = "INS_BY")
	private String ins_by;
	
	@Column(name = "INS_DATE")
	private Date ins_date = new Date();
	
	@Column(name = "UPD_BY")
	private String upd_by;
	
	@Column(name = "UPD_DATE")
	private Date upd_date = new Date();
	
	
	
	public String getUser_code() {
		return user_code;
	}
	public void setUser_code(String user_code) {
		this.user_code = user_code;
	}
	public String getModule_code() {
		return module_code;
	}
	public void setModule_code(String module_code) {
		this.module_code = module_code;
	}
	
	public String getSub_module_code() {
		return sub_module_code;
	}
	public void setSub_module_code(String sub_module_code) {
		this.sub_module_code = sub_module_code;
	}
	public String getPrg_code() {
		return prg_code;
	}
	public void setPrg_code(String prg_code) {
		this.prg_code = prg_code;
	}
	public String getActive_yn() {
		return active_yn;
	}
	public void setActive_yn(String active_yn) {
		this.active_yn = active_yn;
	}
	public String getIns_by() {
		return ins_by;
	}
	public void setIns_by(String ins_by) {
		this.ins_by = ins_by;
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

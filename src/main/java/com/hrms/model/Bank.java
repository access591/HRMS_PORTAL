/**
 * 
 */
package com.hrms.model;


import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Access
 *
 */
@Entity
@Table(name = "M_BANK")

public class Bank implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1785482366741143946L;

	/**
	 * 
	 */
	

	/**
	 * 
	 */
	

	/**
	 * Mohit Access 
	 */
	

	@Id
	@Column(name = "BANK_CODE")
	private String Bank_Code;
	
	@Column(name = "BANK_NAME")
	private String bank_name;
	
	@Column(name = "BANK_ADD")
	private String bank_add;
	
	@Column(name = "BANK_ADDR2")
	private String bank_addr2;
	
	@Column(name = "CITY_CODE")
	private String city_code;
	
	@Column(name = "STATE_CODE")
	private String state_code;
	
	@Column(name = "COUNTRY_CODE")
	private String country_code;
	
	@Column(name = "ACCOUNT_NO")
	private String account_no;
	
	@Column(name = "IFSC_CODE")
	private String ifsc_code;
	
	@Column(name = "SWIFT_CODE")
	private String swift_code;
	
	@Column(name = "TELEPHONE_NO")
	private String telephone_no;
	
	@Column(name = "MOBILE_NO")
	private String mobile_no;
	
	@Column(name = "INS_BY")
	private String Ins_by;
	
	@Column(name = "INS_DATE")
	private Date ins_date =new Date();
	
	@Column(name = "UPD_BY")
	private String upd_by;
	
	@Column(name = "UPD_DATE")
	private Date  upd_date = new Date();
	
	@Column(name = "ACTIVE_YN")
	private String active;

	public String getBank_Code() {
		return Bank_Code;
	}

	public void setBank_Code(String bank_Code) {
		Bank_Code = bank_Code;
	}

	public String getBank_name() {
		return bank_name;
	}

	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}

	public String getBank_add() {
		return bank_add;
	}

	public void setBank_add(String bank_add) {
		this.bank_add = bank_add;
	}

	public String getBank_addr2() {
		return bank_addr2;
	}

	public void setBank_addr2(String bank_addr2) {
		this.bank_addr2 = bank_addr2;
	}

	public String getCity_code() {
		return city_code;
	}

	public void setCity_code(String city_code) {
		this.city_code = city_code;
	}

	public String getState_code() {
		return state_code;
	}

	public void setState_code(String state_code) {
		this.state_code = state_code;
	}

	public String getCountry_code() {
		return country_code;
	}

	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}

	public String getAccount_no() {
		return account_no;
	}

	public void setAccount_no(String account_no) {
		this.account_no = account_no;
	}

	public String getIfsc_code() {
		return ifsc_code;
	}

	public void setIfsc_code(String ifsc_code) {
		this.ifsc_code = ifsc_code;
	}

	public String getSwift_code() {
		return swift_code;
	}

	public void setSwift_code(String swift_code) {
		this.swift_code = swift_code;
	}

	public String getTelephone_no() {
		return telephone_no;
	}

	public void setTelephone_no(String telephone_no) {
		this.telephone_no = telephone_no;
	}

	public String getMobile_no() {
		return mobile_no;
	}

	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
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

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
	
	
		
}	
	
	
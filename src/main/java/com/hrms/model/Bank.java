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
	private String bankCode;
	
	@Column(name = "BANK_NAME")
	private String bankName;
	
	@Column(name = "BANK_ADD")
	private String bankAdd;
	
	@Column(name = "BANK_ADDR2")
	private String bankAddr2;
	
	@Column(name = "CITY_CODE")
	private String cityCode;
	
	@Column(name = "STATE_CODE")
	private String stateCode;
	
	@Column(name = "COUNTRY_CODE" ,updatable = false)
	private String countryCode;
	
	@Column(name = "ACCOUNT_NO")
	private String accountNo;
	
	@Column(name = "IFSC_CODE")
	private String ifscCode;
	
	@Column(name = "SWIFT_CODE")
	private String swiftCode;
	
	@Column(name = "TELEPHONE_NO")
	private String telephoneNo;
	
	@Column(name = "MOBILE_NO")
	private String mobileNo;
	
    @Column(name = "INS_BY",updatable = false)
	private String insBy;
	
	@Column(name = "INS_DATE",updatable = false)
	private Date insDate =new Date();
	
	@Column(name = "UPD_BY",insertable = false)
	private String updBy;
	
	@Column(name = "UPD_DATE",insertable = false)
	private Date  updDate = new Date();
	
	

	
	@Column(name = "ACTIVE_YN")
	private String active;

	



	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankAdd() {
		return bankAdd;
	}

	public void setBankAdd(String bankAdd) {
		this.bankAdd = bankAdd;
	}

	public String getBankAddr2() {
		return bankAddr2;
	}

	public void setBankAddr2(String bankAddr2) {
		this.bankAddr2 = bankAddr2;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public String getSwiftCode() {
		return swiftCode;
	}

	public void setSwiftCode(String swiftCode) {
		this.swiftCode = swiftCode;
	}

	public String getTelephoneNo() {
		return telephoneNo;
	}

	public void setTelephoneNo(String telephoneNo) {
		this.telephoneNo = telephoneNo;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
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
	
	
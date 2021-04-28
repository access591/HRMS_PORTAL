package com.hrms.model;

import java.io.Serializable;
import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

import javax.persistence.Table;
import javax.validation.constraints.Size;


@Entity
@Table(name = "M_EMPLOYEE")
public class Employee implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3548208510334449114L;

	/**
	 * @author Access Surendra
	 */

	@Id
	@Size(max = 25)
	@Column(name ="EMPLOYEE_CODE")
	private String empCode;
	
	@Size(max = 200)
	@Column(name = "EMPLOYEE_NAME")
	private String empName;
	
	@Size(max = 20)
	@Column(name ="BATCH_YEAR")
	private String batchYear;
	
	@Column(name ="DATE_OF_JOINING")
	private Date dateOfJoining;
	
	@Column(name ="DATE_OF_POSTING")
	private Date dateOfPosting;
	
	@Column(name ="DATE_OF_RETIREMENT")
	private Date dateOfRetirement;
	@Size(max = 25)
	@Column(name ="CATEGORY_CODE")
	private String categoryCode;
	@Size(max = 25)
	@Column(name ="DEPARTMENT_CODE")
	private String departmentCode;
	
	

	@Size(max = 25)
	@Column(name ="DESIGNATION_CODE")
	private String designationCode;

	@Lob
	@Column(name = "EMP_IMG", length = Integer.MAX_VALUE, nullable = true)
	private byte[] imageProfile;
	
	@Size(max = 20)
	@Column(name ="EMPLOYEE_PAYEE_CODE")
	private String   employeePayeeCode;
	
	@Size(max = 10)
	@Column(name ="OFFICER_TYPE")
	private String officerType;
	
	@Size(max = 200)
	@Column(name ="PRESENT_POSTING")
	private String presentPosting;
	
	@Size(max = 100)
	@Column(name ="SUSPENTION")
	private String suspention;
	@Size(max =2)
	@Column(name ="TYPE_COURT_DEPARTMENT")
	private String  typeCourtDepartment;
	@Size(max = 200)
	@Column(name ="VIGILANCE_QUERY")
	private String vigilanceQuery;
	@Size(max =20)
	@Column(name ="VRS")
	private String vrs;
	
	
	
	
	// STEP-1=========17=============end....
	@Size(max =16)
	@Column(name = "AADHAR_NO")
	private String aadharNo;
	@Size(max =255)
	@Column(name = "ADD_CHARGE")
	private String addCharge;
	@Size(max =200)
	@Column(name ="ADDRESS_1")
	private String address1;
	@Size(max =200)
	@Column(name ="ADDRESS_2")
	private String address2;
	@Size(max =25)
	@Column(name="CITY_CODE")
	private String cityCode;
	@Size(max =25)
	@Column(name="STATE_CODE")
	private String stateCode;
	@Size(max =25)
	@Column(name="COUNTRY_CODE")
	private String countryCode;
	@Size(max =100)
	@Column(name = "EMAIL")
	private String email;
	@Size(max =1)
	@Column(name = "GENDER")
	private String gender;
	@Size(max =255)
	@Column(name = "MARTIAL_STATUS")
	private String martialStatus;
	
	@Size(max =12)
	@Column(name = "TELEPHONE")
	private String telephone;
	@Size(max =10)
	@Column(name = "MOBILE_NUMBER1")
	private String mobileNumber1;
	@Size(max =10)
	@Column(name = "MOBILE_NUMBER2")
	private String mobileNumber2;
	@Size(max =255)
	@Column(name = "ON_ADDITIONAL_CHARGE")
	private String onAdditionalCharge;
	
	@Column(name = "ORDER_DATE")
	private Date orderDate;
	
	@Size(max =255)
	@Column(name = "ORDER_NO")
	private String orderNo;
	
	@Size(max =10)
	@Column(name = "PAN_NO")
	private String panNo;
	
	@Size(max =6)
	@Column(name = "PINCODE")
	private String pinCode;
	@Size(max =200)
	@Column(name = "QUALIFICATION")
	private String qualification;
	@Size(max =255)
	@Column(name = "TRANSFER")
	private String transfer;
	@Size(max =15)
	@Column(name = "UAN")
	private String uan;
	@Size(max =1)
	@Column(name = "UNDER_RULE_7")
	private String underRule7;
	@Size(max =1)
	@Column(name = "UNDER_RULE_8")
	private String underRule8;

	@Column(name = "INS_BY", updatable = false)
	private String insBy;

	@Column(name = "INS_DATE", updatable = false)
	private Date insDate = new Date();

	@Column(name = "UPD_BY", insertable = false)
	private String updBy;

	@Column(name = "UPD_DATE", insertable = false)
	private Date updDate = new Date();

	/*
	 * @OneToMany(mappedBy = "armsCode") public List<ArmsLicenseDetails>
	 * armsLicenseDetails;
	 */
	// STEP-2=============23=========end....
	
	

	public String getEmpCode() {
		return empCode;
	}


	/*
	 * public List<ArmsLicenseDetails> getArmsLicenseDetails() { return
	 * armsLicenseDetails; }
	 * 
	 * 
	 * public void setArmsLicenseDetails(List<ArmsLicenseDetails>
	 * armsLicenseDetails) { this.armsLicenseDetails = armsLicenseDetails; }
	 */

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getBatchYear() {
		return batchYear;
	}

	public void setBatchYear(String batchYear) {
		this.batchYear = batchYear;
	}

	public Date getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public Date getDateOfPosting() {
		return dateOfPosting;
	}

	public void setDateOfPosting(Date dateOfPosting) {
		this.dateOfPosting = dateOfPosting;
	}

	public Date getDateOfRetirement() {
		return dateOfRetirement;
	}

	public void setDateOfRetirement(Date dateOfRetirement) {
		this.dateOfRetirement = dateOfRetirement;
	}

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public String getDesignationCode() {
		return designationCode;
	}

	public void setDesignationCode(String designationCode) {
		this.designationCode = designationCode;
	}

	public byte[] getImageProfile() {
		return imageProfile;
	}

	public void setImageProfile(byte[] imageProfile) {
		this.imageProfile = imageProfile;
	}

	public String getEmployeePayeeCode() {
		return employeePayeeCode;
	}

	public void setEmployeePayeeCode(String employeePayeeCode) {
		this.employeePayeeCode = employeePayeeCode;
	}

	public String getOfficerType() {
		return officerType;
	}

	public void setOfficerType(String officerType) {
		this.officerType = officerType;
	}

	public String getPresentPosting() {
		return presentPosting;
	}

	public void setPresentPosting(String presentPosting) {
		this.presentPosting = presentPosting;
	}

	public String getSuspention() {
		return suspention;
	}

	public void setSuspention(String suspention) {
		this.suspention = suspention;
	}

	public String getTypeCourtDepartment() {
		return typeCourtDepartment;
	}

	public void setTypeCourtDepartment(String typeCourtDepartment) {
		this.typeCourtDepartment = typeCourtDepartment;
	}

	public String getVigilanceQuery() {
		return vigilanceQuery;
	}

	public void setVigilanceQuery(String vigilanceQuery) {
		this.vigilanceQuery = vigilanceQuery;
	}

	public String getVrs() {
		return vrs;
	}

	public void setVrs(String vrs) {
		this.vrs = vrs;
	}

	public String getAadharNo() {
		return aadharNo;
	}

	public void setAadharNo(String aadharNo) {
		this.aadharNo = aadharNo;
	}

	public String getAddCharge() {
		return addCharge;
	}

	public void setAddCharge(String addCharge) {
		this.addCharge = addCharge;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMartialStatus() {
		return martialStatus;
	}

	public void setMartialStatus(String martialStatus) {
		this.martialStatus = martialStatus;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getMobileNumber1() {
		return mobileNumber1;
	}

	public void setMobileNumber1(String mobileNumber1) {
		this.mobileNumber1 = mobileNumber1;
	}

	public String getMobileNumber2() {
		return mobileNumber2;
	}

	public void setMobileNumber2(String mobileNumber2) {
		this.mobileNumber2 = mobileNumber2;
	}

	public String getOnAdditionalCharge() {
		return onAdditionalCharge;
	}

	public void setOnAdditionalCharge(String onAdditionalCharge) {
		this.onAdditionalCharge = onAdditionalCharge;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getPanNo() {
		return panNo;
	}

	public void setPanNo(String panNo) {
		this.panNo = panNo.toUpperCase();
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getTransfer() {
		return transfer;
	}

	public void setTransfer(String transfer) {
		this.transfer = transfer;
	}

	public String getUan() {
		return uan;
	}

	public void setUan(String uan) {
		this.uan = uan;
	}

	public String getUnderRule7() {
		return underRule7;
	}

	public void setUnderRule7(String underRule7) {
		this.underRule7 = underRule7;
	}

	public String getUnderRule8() {
		return underRule8;
	}

	public void setUnderRule8(String underRule8) {
		this.underRule8 = underRule8;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
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

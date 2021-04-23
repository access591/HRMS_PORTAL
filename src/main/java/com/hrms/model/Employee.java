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
	
	
	
	
	// STEP-1======================end....

	@Column(name = "TRANSFER")
	private String transfer;

	@Column(name = "ORDER_NO")
	private String orderNo;

	@Column(name = "ORDER_DATE")
	private String orderDate;

	@Column(name = "UNDER_RULE_7")
	private String underRule7;

	@Column(name = "UNDER_RULE_8")
	private String underRule8;

	@Column(name = "UAN")
	private String uan;

	@Column(name = "ADD_CHARGE")
	private String addCharge;

	@Column(name = "ON_ADDITIONAL_CHARGE")
	private String onAdditionalCharge;

	@Column(name = "PAN_NO")
	private String panNo;

	@Column(name = "QUALIFICATION")
	private String qualification;

	@Column(name = "AADHAR_NO")
	private String aadharNo;

	@Column(name = "MOBILE_NUMBER")
	private String mobileNumber;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "GENDER")
	private String gender;

	@Column(name = "MARTIAL_STATUS")
	private String martialStatus;

	@Column(name = "ADDRESS")
	private String address;

	@Column(name = "CITY_STATE")
	private String cityState;

	@Column(name = "PINCODE")
	private String pinCode;
	
	// STEP-2======================end....
	
	
	@Column(name = "FATHER_NAME")
	private String fatherName;

	@Column(name = "ADDRESS_ARMS")
	private String addressArms;

	@Column(name = "DISTRICT")
	private String  district;

	@Column(name = "STATE")
	private String state;

	@Column(name = "ARMS_AREA")
	private String armsArea;

	@Column(name = "DOI")
	private String doi;

	@Column(name = "DOV")
	private String dov;

	@Column(name = "TOA")
	private String toa;

	@Column(name = "TOP")
	private String top;

	@Column(name = "NOL")
	private String nol;

	@Column(name = "LCD")
	private String lcd;


	@Column(name = "DEALER_DETAILS")
	private String dealerDetails;





	public String getEmpCode() {
		return empCode;
	}



	public void setEmpCode(String empCode) {
		this.empCode = empCode;
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



	public String getTransfer() {
		return transfer;
	}



	public void setTransfer(String transfer) {
		this.transfer = transfer;
	}



	public String getOrderNo() {
		return orderNo;
	}



	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}



	public String getOrderDate() {
		return orderDate;
	}



	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
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



	public String getUan() {
		return uan;
	}



	public void setUan(String uan) {
		this.uan = uan;
	}



	public String getAddCharge() {
		return addCharge;
	}



	public void setAddCharge(String addCharge) {
		this.addCharge = addCharge;
	}



	public String getOnAdditionalCharge() {
		return onAdditionalCharge;
	}



	public void setOnAdditionalCharge(String onAdditionalCharge) {
		this.onAdditionalCharge = onAdditionalCharge;
	}



	public String getPanNo() {
		return panNo;
	}



	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}



	public String getQualification() {
		return qualification;
	}



	public void setQualification(String qualification) {
		this.qualification = qualification;
	}



	public String getAadharNo() {
		return aadharNo;
	}



	public void setAadharNo(String aadharNo) {
		this.aadharNo = aadharNo;
	}



	public String getMobileNumber() {
		return mobileNumber;
	}



	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
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



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public String getCityState() {
		return cityState;
	}



	public void setCityState(String cityState) {
		this.cityState = cityState;
	}



	public String getPinCode() {
		return pinCode;
	}



	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}



	

	public String getEmpName() {
		return empName;
	}



	public void setEmpName(String empName) {
		this.empName = empName;
	}



	public String getFatherName() {
		return fatherName;
	}



	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}



	public String getAddressArms() {
		return addressArms;
	}



	public void setAddressArms(String addressArms) {
		this.addressArms = addressArms;
	}



	public String getDistrict() {
		return district;
	}



	public void setDistrict(String district) {
		this.district = district;
	}



	public String getState() {
		return state;
	}



	public void setState(String state) {
		this.state = state;
	}



	public String getArmsArea() {
		return armsArea;
	}



	public void setArmsArea(String armsArea) {
		this.armsArea = armsArea;
	}



	public String getDoi() {
		return doi;
	}



	public void setDoi(String doi) {
		this.doi = doi;
	}



	public String getDov() {
		return dov;
	}



	public void setDov(String dov) {
		this.dov = dov;
	}



	public String getToa() {
		return toa;
	}



	public void setToa(String toa) {
		this.toa = toa;
	}



	public String getTop() {
		return top;
	}



	public void setTop(String top) {
		this.top = top;
	}



	public String getNol() {
		return nol;
	}



	public void setNol(String nol) {
		this.nol = nol;
	}


	public String getLcd() {
		return lcd;
	}

	public void setLcd(String lcd) {
		this.lcd = lcd;
	}

	public String getDealerDetails() {
		return dealerDetails;
	}

	public void setDealerDetails(String dealerDetails) {
		this.dealerDetails = dealerDetails;
	}

}

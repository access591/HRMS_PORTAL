package com.hrms.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "M_EMPLOYEE")
public class Employee implements Serializable {

	/**
	 * @author Access Surendra
	 */
	private static final long serialVersionUID = -5019623265481596682L;
	@Id
	@Column(name = "EMP_CODE")
	private String empCode;
	@Lob
    @Column(name = "ImageProfile", length = Integer.MAX_VALUE, nullable = true)
    private byte[] imageProfile;
	@Column(name = "EMP_IMG")
	private String empImg;

	@Column(name = "EMP_NAME")
	private String empName;

	@Column(name = "EACTIVE")
	private String eActive;

	@Column(name = "COMP_CODE")
	private String compCode;

	@Column(name = "DATE_OF_BIRTH")
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;

	@Column(name = "BIRTH_STATE")
	private String birthState;

	@Column(name = "EMP_REF_NO")
	private String empRefNo;

	@Column(name = "EMP_TYPE")
	private String empType;

	@Column(name = "LOCATION_TYPE")
	private String locationType;

	@Column(name = "WEEKLY_WORKING_DAY")
	private String weeklyWorkingDay;

	@Column(name = "BIRTH_PLACE")
	private String birthPlace;

	@Column(name = "DOMICILE")
	private String domicile;
	// STEP-1======================end....
	@Column(name = "CARD_NO")
	private String cardNo;

	@Column(name = "EMP_DESIGNATION")
	private String empDesignation;

	@Column(name = "SHIFT_CODE")
	private String shiftCode;

	@Column(name = "PASSPORT_NO")
	private String passportNo;

	@Column(name = "LOI_DATE")
	@Temporal(TemporalType.DATE)
	private Date loiDate;

	@Column(name = "STATUS_DATE")
	@Temporal(TemporalType.DATE)
	private Date statusDate;

	@Column(name = "REASON")
	private String reason;

	@Column(name = "DEPARTMENT_CODE")
	private String departmentCode;

	@Column(name = "MANAGER_CODE")
	private String managerCode;

	@Column(name = "IT_PAN_NO")
	private String itPanNo;

	@Column(name = "UAN_NO")
	private String uanNo;
	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_OF_JOINING")
	private Date dateOfJoining;

	@Column(name = "LEAVING_DATE")
	@Temporal(TemporalType.DATE)
	private Date leavingDate;
	// step-2=====================end.....=============
	@Column(name = "EMP_GENDER")
	private String empGender;

	@Column(name = "EMP_HEIGHT")
	private String empHeight;

	@Column(name = "IDENTITY_MARK")
	private String identityMark;

	@Column(name = "BLOOD_GROUP")
	private String bloodGroup;

	@Column(name = "MARITAL_STATUS")
	private String maritalStatus;
	// line-1=====================end.....

	@Column(name = "PRE_DISEASE")
	private String preDisease;

	@Column(name = "WEDDING_DATE")
	@Temporal(TemporalType.DATE)
	private Date weddingDate;

	@Column(name = "NO_OF_CHILD")
	private String noOfChild;

	@Column(name = "EMP_CASTE")
	private String empCaste;

	@Column(name = "EMP_RELIGION")
	private String empReligion;
	// line-2=====================end.....

	@Column(name = "EMP_NATIONALITY")
	private String empNationality;

	@Column(name = "COMPANY_CODE")
	private String companyCode;

	@Column(name = "MEDICLAIM")
	private String mediclaim;

	@Column(name = "GPA")
	private String gpa;
	// line-3=====================end..4...

	@Column(name = "EMP_ADDRESS1")
	private String empAddress1;

	@Column(name = "EMP_ADDRESS2")
	private String empAddress2;

	@Column(name = "EMP_CITY")
	private String empCity;

	@Column(name = "EMP_STATE")
	private String empState;

	@Column(name = "PIN_CODE")
	private String pinCode;

	@Column(name = "EMP_MOBILE")
	private String empMobile;
	// line-4=====================end..6...

	@Column(name = "EMP_LOCAL_ADD1")
	private String empLocalAdd1;

	@Column(name = "EMP_LOCAL_ADD2")
	private String empLocalAdd2;

	@Column(name = "EMP_LOCAL_CITY")
	private String empLocalCity;

	@Column(name = "EMP_LOCAL_STATE")
	private String empLocalState;

	@Column(name = "EMP_LOCAL_PINCODE")
	private String empLocalPinCode;

	@Column(name = "EMP_LOCAL_MOBILE_NO")
	private String empLocalMobileNo;

//step-3=====================end.....==========6========
	@Column(name = "COMP_MOBILE_NO")
	private String compMobileNo;

	@Column(name = "COMPANY_EMAIL")
	private String companyEmail;

	@Column(name = "PROF_INST")
	private String profInst;

	@Column(name = "LOCAL_TRAVEL_PR_KM")
	private String localTravelPrkm;

	@Column(name = "HOBBIES")
	private String hobbies;

	@Column(name = "CLUBS")
	private String clubs;

	@Column(name = "REG_CODE")
	private String regCode;

	@Column(name = "ESTF_WORK")
	private String estfWork;

	@Column(name = "E_EMP_CAT")
	private String electronicEmpCat;

	@Column(name = "E_CATEGORY")
	private String electronicCategory;

	@Column(name = "EMP_AGREE")
	private String empAgree;
	// step-4=====================end.....==11================

	public String geteActive() {
		return eActive;
	}


	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public String getEmpImg() {
		return empImg;
	}

	public void setEmpImg(String empImg) {
		this.empImg = empImg;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getCompCode() {
		return compCode;
	}

	public void setCompCode(String compCode) {
		this.compCode = compCode;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getBirthState() {
		return birthState;
	}

	public void setBirthState(String birthState) {
		this.birthState = birthState;
	}

	public String getEmpRefNo() {
		return empRefNo;
	}

	public void setEmpRefNo(String empRefNo) {
		this.empRefNo = empRefNo;
	}

	public String getEmpType() {
		return empType;
	}

	public void setEmpType(String empType) {
		this.empType = empType;
	}

	public String getLocationType() {
		return locationType;
	}

	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}

	public String getWeeklyWorkingDay() {
		return weeklyWorkingDay;
	}

	public void setWeeklyWorkingDay(String weeklyWorkingDay) {
		this.weeklyWorkingDay = weeklyWorkingDay;
	}

	public String getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	public void seteActive(String eActive) {
		this.eActive = eActive;
	}

	public String getDomicile() {
		return domicile;
	}

	public void setDomicile(String domicile) {
		this.domicile = domicile;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getEmpDesignation() {
		return empDesignation;
	}

	public void setEmpDesignation(String empDesignation) {
		this.empDesignation = empDesignation;
	}

	public String getShiftCode() {
		return shiftCode;
	}

	public void setShiftCode(String shiftCode) {
		this.shiftCode = shiftCode;
	}

	public String getPassportNo() {
		return passportNo;
	}

	public void setPassportNo(String passportNo) {
		this.passportNo = passportNo;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Date getLoiDate() {
		return loiDate;
	}

	public void setLoiDate(Date loiDate) {
		this.loiDate = loiDate;
	}

	public Date getStatusDate() {
		return statusDate;
	}

	public void setStatusDate(Date statusDate) {
		this.statusDate = statusDate;
	}

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public String getManagerCode() {
		return managerCode;
	}

	public void setManagerCode(String managerCode) {
		this.managerCode = managerCode;
	}

	public String getItPanNo() {
		return itPanNo;
	}

	public void setItPanNo(String itPanNo) {
		this.itPanNo = itPanNo;
	}

	public String getUanNo() {
		return uanNo;
	}

	public void setUanNo(String uanNo) {
		this.uanNo = uanNo;
	}

	public Date getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public Date getLeavingDate() {
		return leavingDate;
	}

	public void setLeavingDate(Date leavingDate) {
		this.leavingDate = leavingDate;
	}

	public String getEmpGender() {
		return empGender;
	}

	public void setEmpGender(String empGender) {
		this.empGender = empGender;
	}

	public String getEmpHeight() {
		return empHeight;
	}

	public void setEmpHeight(String empHeight) {
		this.empHeight = empHeight;
	}

	public String getIdentityMark() {
		return identityMark;
	}

	public void setIdentityMark(String identityMark) {
		this.identityMark = identityMark;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getPreDisease() {
		return preDisease;
	}

	public void setPreDisease(String preDisease) {
		this.preDisease = preDisease;
	}

	public Date getWeddingDate() {
		return weddingDate;
	}

	public void setWeddingDate(Date weddingDate) {
		this.weddingDate = weddingDate;
	}

	public String getNoOfChild() {
		return noOfChild;
	}

	public void setNoOfChild(String noOfChild) {
		this.noOfChild = noOfChild;
	}

	public String getEmpCaste() {
		return empCaste;
	}

	public void setEmpCaste(String empCaste) {
		this.empCaste = empCaste;
	}

	public String getEmpReligion() {
		return empReligion;
	}

	public void setEmpReligion(String empReligion) {
		this.empReligion = empReligion;
	}

	public String getMediclaim() {
		return mediclaim;
	}

	public void setMediclaim(String mediclaim) {
		this.mediclaim = mediclaim;
	}

	public String getGpa() {
		return gpa;
	}

	public void setGpa(String gpa) {
		this.gpa = gpa;
	}

	public String getEmpNationality() {
		return empNationality;
	}

	public void setEmpNationality(String empNationality) {
		this.empNationality = empNationality;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getEmpAddress1() {
		return empAddress1;
	}

	public void setEmpAddress1(String empAddress1) {
		this.empAddress1 = empAddress1;
	}

	public String getEmpAddress2() {
		return empAddress2;
	}

	public void setEmpAddress2(String empAddress2) {
		this.empAddress2 = empAddress2;
	}

	public String getEmpCity() {
		return empCity;
	}

	public void setEmpCity(String empCity) {
		this.empCity = empCity;
	}

	public String getEmpState() {
		return empState;
	}

	public void setEmpState(String empState) {
		this.empState = empState;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public String getEmpMobile() {
		return empMobile;
	}

	public void setEmpMobile(String empMobile) {
		this.empMobile = empMobile;
	}

	public String getEmpLocalAdd1() {
		return empLocalAdd1;
	}

	public void setEmpLocalAdd1(String empLocalAdd1) {
		this.empLocalAdd1 = empLocalAdd1;
	}

	public String getEmpLocalAdd2() {
		return empLocalAdd2;
	}

	public void setEmpLocalAdd2(String empLocalAdd2) {
		this.empLocalAdd2 = empLocalAdd2;
	}

	public String getEmpLocalCity() {
		return empLocalCity;
	}

	public void setEmpLocalCity(String empLocalCity) {
		this.empLocalCity = empLocalCity;
	}

	public String getEmpLocalState() {
		return empLocalState;
	}

	public void setEmpLocalState(String empLocalState) {
		this.empLocalState = empLocalState;
	}

	public String getEmpLocalPinCode() {
		return empLocalPinCode;
	}

	public void setEmpLocalPinCode(String empLocalPinCode) {
		this.empLocalPinCode = empLocalPinCode;
	}

	public String getEmpLocalMobileNo() {
		return empLocalMobileNo;
	}

	public void setEmpLocalMobileNo(String empLocalMobileNo) {
		this.empLocalMobileNo = empLocalMobileNo;
	}

	public String getHobbies() {
		return hobbies;
	}

	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
	}

	public String getClubs() {
		return clubs;
	}

	public void setClubs(String clubs) {
		this.clubs = clubs;
	}

	public String getCompMobileNo() {
		return compMobileNo;
	}

	public void setCompMobileNo(String compMobileNo) {
		this.compMobileNo = compMobileNo;
	}

	public String getCompanyEmail() {
		return companyEmail;
	}

	public void setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
	}

	public String getProfInst() {
		return profInst;
	}

	public void setProfInst(String profInst) {
		this.profInst = profInst;
	}

	public String getLocalTravelPrkm() {
		return localTravelPrkm;
	}

	public void setLocalTravelPrkm(String localTravelPrkm) {
		this.localTravelPrkm = localTravelPrkm;
	}

	public String getRegCode() {
		return regCode;
	}

	public void setRegCode(String regCode) {
		this.regCode = regCode;
	}

	public String getEstfWork() {
		return estfWork;
	}

	public void setEstfWork(String estfWork) {
		this.estfWork = estfWork;
	}

	public String getElectronicEmpCat() {
		return electronicEmpCat;
	}

	public void setElectronicEmpCat(String electronicEmpCat) {
		this.electronicEmpCat = electronicEmpCat;
	}

	public String getElectronicCategory() {
		return electronicCategory;
	}

	public void setElectronicCategory(String electronicCategory) {
		this.electronicCategory = electronicCategory;
	}

	public String getEmpAgree() {
		return empAgree;
	}

	public void setEmpAgree(String empAgree) {
		this.empAgree = empAgree;
	}


	@Override
	public String toString() {
		return "Employee [empCode=" + empCode + ", ImageProfile=" + Arrays.toString(imageProfile) + ", empImg=" + empImg
				+ ", empName=" + empName + ", eActive=" + eActive + ", compCode=" + compCode + ", dateOfBirth="
				+ dateOfBirth + ", birthState=" + birthState + ", empRefNo=" + empRefNo + ", empType=" + empType
				+ ", locationType=" + locationType + ", weeklyWorkingDay=" + weeklyWorkingDay + ", birthPlace="
				+ birthPlace + ", domicile=" + domicile + ", cardNo=" + cardNo + ", empDesignation=" + empDesignation
				+ ", shiftCode=" + shiftCode + ", passportNo=" + passportNo + ", loiDate=" + loiDate + ", statusDate="
				+ statusDate + ", reason=" + reason + ", departmentCode=" + departmentCode + ", managerCode="
				+ managerCode + ", itPanNo=" + itPanNo + ", uanNo=" + uanNo + ", dateOfJoining=" + dateOfJoining
				+ ", leavingDate=" + leavingDate + ", empGender=" + empGender + ", empHeight=" + empHeight
				+ ", identityMark=" + identityMark + ", bloodGroup=" + bloodGroup + ", maritalStatus=" + maritalStatus
				+ ", preDisease=" + preDisease + ", weddingDate=" + weddingDate + ", noOfChild=" + noOfChild
				+ ", empCaste=" + empCaste + ", empReligion=" + empReligion + ", empNationality=" + empNationality
				+ ", companyCode=" + companyCode + ", mediclaim=" + mediclaim + ", gpa=" + gpa + ", empAddress1="
				+ empAddress1 + ", empAddress2=" + empAddress2 + ", empCity=" + empCity + ", empState=" + empState
				+ ", pinCode=" + pinCode + ", empMobile=" + empMobile + ", empLocalAdd1=" + empLocalAdd1
				+ ", empLocalAdd2=" + empLocalAdd2 + ", empLocalCity=" + empLocalCity + ", empLocalState="
				+ empLocalState + ", empLocalPinCode=" + empLocalPinCode + ", empLocalMobileNo=" + empLocalMobileNo
				+ ", compMobileNo=" + compMobileNo + ", companyEmail=" + companyEmail + ", profInst=" + profInst
				+ ", localTravelPrkm=" + localTravelPrkm + ", hobbies=" + hobbies + ", clubs=" + clubs + ", regCode="
				+ regCode + ", estfWork=" + estfWork + ", electronicEmpCat=" + electronicEmpCat
				+ ", electronicCategory=" + electronicCategory + ", empAgree=" + empAgree + "]";
	}


	public byte[] getImageProfile() {
		return imageProfile;
	}


	public void setImageProfile(byte[] imageProfile) {
		this.imageProfile = imageProfile;
	}	

}

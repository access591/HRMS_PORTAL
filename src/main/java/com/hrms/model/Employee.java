package com.hrms.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name ="M_EMPLOYEE")
public class Employee implements Serializable {

	/**
	 *  @author Access Surendra
	 */
	private static final long serialVersionUID = -5019623265481596682L;
	@Id
	@Column(name ="EMP_CODE")
	private String Emp_Code;
	
	@Column(name ="EMP_NAME")
	private String Emp_Name;
	
	@Column(name ="EACTIVE")
	private String eActive;
	
	@Column(name ="COMP_CODE")
	private String Comp_Code;
	
	@Column(name ="DATE_OF_BIRTH")
	private Date Date_Of_Birth;
	
	@Column(name ="BIRTH_STATE")
	private String  Birth_State;
	
	@Column(name ="EMP_REF_NO")
	private String Emp_Ref_No;
	
	@Column(name ="EMP_TYPE")
	private String Emp_Type;
	
	@Column(name ="LOCATION_TYPE")
	private String Location_Type;
	
	@Column(name ="WEEKLY_WORKING_DAY")
	private String Weekly_Working_Day;
	
	@Column(name ="BIRTH_PLACE")
	private String Birth_Place;
	
	@Column(name ="DOMICILE")
	private String domicile;
	//STEP-1======================end....
	@Column(name ="CARD_NO")
	private String Card_No;
	
	@Column(name ="EMP_DESIGNATION")
	private String Emp_Designation;
	
	@Column(name ="SHIFT_CODE")
	private String Shift_Code;
	
	@Column(name ="PASSPORT_NO")
	private String Passport_No;
	
	@Column(name ="LOI_DATE")
	private Date Loi_Date;
	
	@Column(name ="STATUS_DATE")
	private Date Status_Date;
	
	@Column(name ="REASON")
	private String reason;
	
	@Column(name ="DEPARTMENT_CODE")
	private String Department_Code;
	
	@Column(name ="MANAGER_CODE")
	private String Manager_Code;
	
	@Column(name ="IT_PAN_NO")
	private String It_Pan_No;
	
	@Column(name ="UAN_NO")
	private String Uan_No;
	
	@Column(name ="DATE_OF_JOINING")
	private Date Date_Of_Joining;
	
	@Column(name ="LEAVING_DATE")
	private Date Leaving_Date;
	//step-2=====================end.....=============
	@Column(name ="EMP_GENDER")
	private String Emp_Gender;
	
	@Column(name ="EMP_HEIGHT")
	private String Emp_Height;
	
	@Column(name ="IDENTITY_MARK")
	private String Identity_Mark;
	
	@Column(name ="BLOOD_GROUP")
	private String Blood_Group;
	
	@Column(name ="MARITAL_STATUS")
	private String Marital_Status;
	//line-1=====================end.....
	
	@Column(name ="PRE_DISEASE")
	private String Pre_Disease;
	
	@Column(name ="WEDDING_DATE")
	private Date Wedding_Date;
	
	@Column(name ="NO_OF_CHILD")
	private String No_Of_Child;
	
	@Column(name ="EMP_CASTE")
	private String Emp_Caste;
	
	@Column(name ="EMP_RELIGION")
	private String Emp_Religion;
	//line-2=====================end.....
	
	@Column(name ="EMP_NATIONALITY")
	private String Emp_Nationality;
	
	@Column(name ="COMPANY_CODE")
	private String Company_Code;
	
	@Column(name ="MEDICLAIM")
	private String mediclaim;
	
	@Column(name ="GPA")
	private String gpa;
	//line-3=====================end..4...
	
	@Column(name ="EMP_ADDRESS1")
	private String Emp_Address1;
	
	@Column(name ="EMP_ADDRESS2")
	private String Emp_Address2;
	
	@Column(name ="EMP_CITY")
	private String Emp_City;
	
	@Column(name ="EMP_STATE")
	private String Emp_State;
	
	@Column(name ="PIN_CODE")
	private String Pin_Code;
	
	@Column(name ="EMP_MOBILE")
	private String Emp_Mobile;
	//line-4=====================end..6...
	
	@Column(name ="EMP_LOCAL_ADD1")
	private String Emp_Local_Add1;
	
	@Column(name ="EMP_LOCAL_ADD2")
	private String Emp_Local_Add2;
	
	@Column(name ="EMP_LOCAL_CITY")
	private String Emp_Local_City;
	
	@Column(name ="EMP_LOCAL_STATE")
	private String Emp_Local_State;
	
	@Column(name ="EMP_LOCAL_PINCODE")
	private String Emp_Local_PinCode;
	
	@Column(name ="EMP_LOCAL_MOBILE_NO")
	private String Emp_Local_Mobile_No;
	
	
//step-3=====================end.....==========6========
	@Column(name ="COMP_MOBILE_NO")
	private String Comp_Mobile_No;
	
	@Column(name ="COMPANY_EMAIL")
	private String Company_Email;
	
	@Column(name ="PROF_INST")
	private String Prof_Inst;
	
	@Column(name ="LOCAL_TRAVEL_PR_KM")
	private String Local_Travel_Pr_km;
	
	@Column(name ="HOBBIES")
	private String hobbies;
	
	@Column(name ="CLUBS")
	private String clubs;
	
	@Column(name ="REG_CODE")
	private String Reg_Code;
	
	@Column(name ="ESTF_WORK")
	private String Estf_Work;
	
	@Column(name ="E_EMP_CAT")
	private String E_Emp_Cat;
	
	@Column(name ="E_CATEGORY")
	private String E_Category;
	
	@Column(name ="EMP_AGREE")
	private String Emp_Agree;
	//step-4=====================end.....==11================	

	public String getEmp_Code() {
		return Emp_Code;
	}

	public void setEmp_Code(String emp_Code) {
		Emp_Code = emp_Code;
	}

	public String getEmp_Name() {
		return Emp_Name;
	}

	public void setEmp_Name(String emp_Name) {
		Emp_Name = emp_Name;
	}

	public String geteActive() {
		return eActive;
	}

	public void seteActive(String eActive) {
		this.eActive = eActive;
	}

	public String getComp_Code() {
		return Comp_Code;
	}

	public void setComp_Code(String comp_Code) {
		Comp_Code = comp_Code;
	}

	public Date getDate_Of_Birth() {
		return Date_Of_Birth;
	}

	public void setDate_Of_Birth(Date date_Of_Birth) {
		Date_Of_Birth = date_Of_Birth;
	}

	public String getBirth_State() {
		return Birth_State;
	}

	public void setBirth_State(String birth_State) {
		Birth_State = birth_State;
	}

	public String getEmp_Ref_No() {
		return Emp_Ref_No;
	}

	public void setEmp_Ref_No(String emp_Ref_No) {
		Emp_Ref_No = emp_Ref_No;
	}

	public String getEmp_Type() {
		return Emp_Type;
	}

	public void setEmp_Type(String emp_Type) {
		Emp_Type = emp_Type;
	}

	public String getLocation_Type() {
		return Location_Type;
	}

	public void setLocation_Type(String location_Type) {
		Location_Type = location_Type;
	}

	public String getWeekly_Working_Day() {
		return Weekly_Working_Day;
	}

	public void setWeekly_Working_Day(String weekly_Working_Day) {
		Weekly_Working_Day = weekly_Working_Day;
	}

	public String getBirth_Place() {
		return Birth_Place;
	}

	public void setBirth_Place(String birth_Place) {
		Birth_Place = birth_Place;
	}

	public String getDomicile() {
		return domicile;
	}

	public void setDomicile(String domicile) {
		this.domicile = domicile;
	}

	public String getCard_No() {
		return Card_No;
	}

	public void setCard_No(String card_No) {
		Card_No = card_No;
	}

	public String getEmp_Designation() {
		return Emp_Designation;
	}

	public void setEmp_Designation(String emp_Designation) {
		Emp_Designation = emp_Designation;
	}

	public String getShift_Code() {
		return Shift_Code;
	}

	public void setShift_Code(String shift_Code) {
		Shift_Code = shift_Code;
	}

	public String getPassport_No() {
		return Passport_No;
	}

	public void setPassport_No(String passport_No) {
		Passport_No = passport_No;
	}

	public Date getLoi_Date() {
		return Loi_Date;
	}

	public void setLoi_Date(Date loi_Date) {
		Loi_Date = loi_Date;
	}

	public Date getStatus_Date() {
		return Status_Date;
	}

	public void setStatus_Date(Date status_Date) {
		Status_Date = status_Date;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getDepartment_Code() {
		return Department_Code;
	}

	public void setDepartment_Code(String department_Code) {
		Department_Code = department_Code;
	}

	public String getManager_Code() {
		return Manager_Code;
	}

	public void setManager_Code(String manager_Code) {
		Manager_Code = manager_Code;
	}

	public String getIt_Pan_No() {
		return It_Pan_No;
	}

	public void setIt_Pan_No(String it_Pan_No) {
		It_Pan_No = it_Pan_No;
	}

	public String getUan_No() {
		return Uan_No;
	}

	public void setUan_No(String uan_No) {
		Uan_No = uan_No;
	}

	public Date getDate_Of_Joining() {
		return Date_Of_Joining;
	}

	public void setDate_Of_Joining(Date date_Of_Joining) {
		Date_Of_Joining = date_Of_Joining;
	}

	public Date getLeaving_Date() {
		return Leaving_Date;
	}

	public void setLeaving_Date(Date leaving_Date) {
		Leaving_Date = leaving_Date;
	}

	public String getEmp_Gender() {
		return Emp_Gender;
	}

	public void setEmp_Gender(String emp_Gender) {
		Emp_Gender = emp_Gender;
	}

	public String getEmp_Height() {
		return Emp_Height;
	}

	public void setEmp_Height(String emp_Height) {
		Emp_Height = emp_Height;
	}

	public String getIdentity_Mark() {
		return Identity_Mark;
	}

	public void setIdentity_Mark(String identity_Mark) {
		Identity_Mark = identity_Mark;
	}

	public String getBlood_Group() {
		return Blood_Group;
	}

	public void setBlood_Group(String blood_Group) {
		Blood_Group = blood_Group;
	}

	public String getMarital_Status() {
		return Marital_Status;
	}

	public void setMarital_Status(String marital_Status) {
		Marital_Status = marital_Status;
	}

	public String getPre_Disease() {
		return Pre_Disease;
	}

	public void setPre_Disease(String pre_Disease) {
		Pre_Disease = pre_Disease;
	}

	public Date getWedding_Date() {
		return Wedding_Date;
	}

	public void setWedding_Date(Date wedding_Date) {
		Wedding_Date = wedding_Date;
	}

	public String getNo_Of_Child() {
		return No_Of_Child;
	}

	public void setNo_Of_Child(String no_Of_Child) {
		No_Of_Child = no_Of_Child;
	}

	public String getEmp_Caste() {
		return Emp_Caste;
	}

	public void setEmp_Caste(String emp_Caste) {
		Emp_Caste = emp_Caste;
	}

	public String getEmp_Religion() {
		return Emp_Religion;
	}

	public void setEmp_Religion(String emp_Religion) {
		Emp_Religion = emp_Religion;
	}

	public String getEmp_Nationality() {
		return Emp_Nationality;
	}

	public void setEmp_Nationality(String emp_Nationality) {
		Emp_Nationality = emp_Nationality;
	}

	public String getCompany_Code() {
		return Company_Code;
	}

	public void setCompany_Code(String company_Code) {
		Company_Code = company_Code;
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

	public String getEmp_Address1() {
		return Emp_Address1;
	}

	public void setEmp_Address1(String emp_Address1) {
		Emp_Address1 = emp_Address1;
	}

	public String getEmp_Address2() {
		return Emp_Address2;
	}

	public void setEmp_Address2(String emp_Address2) {
		Emp_Address2 = emp_Address2;
	}

	public String getEmp_City() {
		return Emp_City;
	}

	public void setEmp_City(String emp_City) {
		Emp_City = emp_City;
	}

	public String getEmp_State() {
		return Emp_State;
	}

	public void setEmp_State(String emp_State) {
		Emp_State = emp_State;
	}

	public String getPin_Code() {
		return Pin_Code;
	}

	public void setPin_Code(String pin_Code) {
		Pin_Code = pin_Code;
	}

	public String getEmp_Mobile() {
		return Emp_Mobile;
	}

	public void setEmp_Mobile(String emp_Mobile) {
		Emp_Mobile = emp_Mobile;
	}

	public String getEmp_Local_Add1() {
		return Emp_Local_Add1;
	}

	public void setEmp_Local_Add1(String emp_Local_Add1) {
		Emp_Local_Add1 = emp_Local_Add1;
	}

	public String getEmp_Local_Add2() {
		return Emp_Local_Add2;
	}

	public void setEmp_Local_Add2(String emp_Local_Add2) {
		Emp_Local_Add2 = emp_Local_Add2;
	}

	public String getEmp_Local_City() {
		return Emp_Local_City;
	}

	public void setEmp_Local_City(String emp_Local_City) {
		Emp_Local_City = emp_Local_City;
	}

	public String getEmp_Local_State() {
		return Emp_Local_State;
	}

	public void setEmp_Local_State(String emp_Local_State) {
		Emp_Local_State = emp_Local_State;
	}

	public String getEmp_Local_PinCode() {
		return Emp_Local_PinCode;
	}

	public void setEmp_Local_PinCode(String emp_Local_PinCode) {
		Emp_Local_PinCode = emp_Local_PinCode;
	}

	public String getEmp_Local_Mobile_No() {
		return Emp_Local_Mobile_No;
	}

	public void setEmp_Local_Mobile_No(String emp_Local_Mobile_No) {
		Emp_Local_Mobile_No = emp_Local_Mobile_No;
	}

	public String getComp_Mobile_No() {
		return Comp_Mobile_No;
	}

	public void setComp_Mobile_No(String comp_Mobile_No) {
		Comp_Mobile_No = comp_Mobile_No;
	}

	public String getCompany_Email() {
		return Company_Email;
	}

	public void setCompany_Email(String company_Email) {
		Company_Email = company_Email;
	}

	public String getProf_Inst() {
		return Prof_Inst;
	}

	public void setProf_Inst(String prof_Inst) {
		Prof_Inst = prof_Inst;
	}

	public String getLocal_Travel_Pr_km() {
		return Local_Travel_Pr_km;
	}

	public void setLocal_Travel_Pr_km(String local_Travel_Pr_km) {
		Local_Travel_Pr_km = local_Travel_Pr_km;
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

	public String getReg_Code() {
		return Reg_Code;
	}

	public void setReg_Code(String reg_Code) {
		Reg_Code = reg_Code;
	}

	public String getEstf_Work() {
		return Estf_Work;
	}

	public void setEstf_Work(String estf_Work) {
		Estf_Work = estf_Work;
	}

	public String getE_Emp_Cat() {
		return E_Emp_Cat;
	}

	public void setE_Emp_Cat(String e_Emp_Cat) {
		E_Emp_Cat = e_Emp_Cat;
	}

	public String getE_Category() {
		return E_Category;
	}

	public void setE_Category(String e_Category) {
		E_Category = e_Category;
	}

	public String getEmp_Agree() {
		return Emp_Agree;
	}

	public void setEmp_Agree(String emp_Agree) {
		Emp_Agree = emp_Agree;
	}
	
	
	
	
	
	
}

package com.hrms.model;

import java.io.Serializable;

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
	private String Date_Of_Birth;
	
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
	private String Loi_Date;
	
	@Column(name ="STATUS_DATE")
	private String Status_Date;
	
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
	private String Date_Of_Joining;
	
	@Column(name ="LEAVING_DATE")
	private String Leaving_Date;
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
	private String Wedding_Date;
	
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
	
}

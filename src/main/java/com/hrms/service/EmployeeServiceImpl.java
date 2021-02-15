package com.hrms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.Employee;
import com.hrms.repository.EmployeeDao;


@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	EmployeeDao employeeDao;
	@Override
	public void addEmployee(Employee employee) {
	employee.setEmp_Code(employeeDao.getMAX_Id("EMP"));
	this.employeeDao.saveOrUpdate(employee);
		
	}

	@Override
	public List<Employee> getAllEmployees() {
		
		 List<Employee> listEmployee = employeeDao.findAll();
			return listEmployee;
	}

	@Override
	public Employee findEmployeeById(String id) {
		
		return employeeDao.findById(id);
	}

	@Override
	public void updateEmployee(Employee e) {
	e.setEmp_Name(e.getEmp_Name());
	e.setEmp_Img(e.getEmp_Img());
	e.seteActive(e.geteActive());
	e.setComp_Code(e.getComp_Code());
	e.setDate_Of_Birth(e.getDate_Of_Birth());
	e.setBirth_State(e.getBirth_State());
	e.setEmp_Ref_No(e.getEmp_Ref_No());
	e.setEmp_Type(e.getEmp_Type());
	e.setLocation_Type(e.getLocation_Type());
	e.setWeekly_Working_Day(e.getWeekly_Working_Day());
	e.setBirth_Place(e.getBirth_Place());
	e.setDomicile(e.getDomicile());
	//Second Step Start...................................................
	e.setCard_No(e.getCard_No());
	e.setEmp_Designation(e.getEmp_Designation());
	e.setShift_Code(e.getShift_Code());
	e.setPassport_No(e.getPassport_No());
	e.setLoi_Date(e.getLoi_Date());
	e.setStatus_Date(e.getStatus_Date());
	e.setReason(e.getReason());
	e.setDepartment_Code(e.getDepartment_Code());
	e.setManager_Code(e.getManager_Code());
	e.setIt_Pan_No(e.getIt_Pan_No());
	e.setUan_No(e.getUan_No());
	e.setDate_Of_Joining(e.getDate_Of_Joining());
	e.setLeaving_Date(e.getLeaving_Date());
	//three  Step Start...................................................
	e.setEmp_Gender(e.getEmp_Gender());
	e.setEmp_Height(e.getEmp_Height());
	e.setIdentity_Mark(e.getIdentity_Mark());
	e.setBlood_Group(e.getBlood_Group());
	e.setMarital_Status(e.getMarital_Status());
	e.setPre_Disease(e.getPre_Disease());
	e.setWedding_Date(e.getWedding_Date());
	e.setNo_Of_Child(e.getNo_Of_Child());
	e.setEmp_Caste(e.getEmp_Caste());
	e.setEmp_Religion(e.getEmp_Religion());
	e.setEmp_Nationality(e.getEmp_Nationality());
	e.setCompany_Code(e.getCompany_Code());
	e.setMediclaim(e.getMediclaim());
	e.setGpa(e.getGpa());
	e.setEmp_Address1(e.getEmp_Address1());
	e.setEmp_Address2(e.getEmp_Address2());
	e.setEmp_City(e.getEmp_City());
	e.setEmp_State(e.getEmp_State());
	e.setPin_Code(e.getPin_Code());
	e.setEmp_Mobile(e.getEmp_Mobile());
	e.setEmp_Local_Add1(e.getEmp_Local_Add1());
	e.setEmp_Local_Add2(e.getEmp_Local_Add2());
	e.setEmp_Local_City(e.getEmp_Local_City());
	e.setEmp_Local_State(e.getEmp_Local_State());
	e.setEmp_Local_PinCode(e.getEmp_Local_PinCode());
	e.setEmp_Local_Mobile_No(e.getEmp_Local_Mobile_No());
	//four  Step Start...................................................
	e.setComp_Mobile_No(e.getComp_Mobile_No());
	e.setCompany_Email(e.getCompany_Email());
	e.setProf_Inst(e.getProf_Inst());
	e.setLocal_Travel_Pr_km(e.getLocal_Travel_Pr_km());
	e.setHobbies(e.getHobbies());
	e.setClubs(e.getClubs());
	e.setReg_Code(e.getReg_Code());
	e.setEstf_Work(e.getEstf_Work());
	e.setE_Emp_Cat(e.getE_Emp_Cat());
	e.setE_Category(e.getE_Category());
	
	this.employeeDao.saveOrUpdate(e);
		
	}

	@Override
	public void removeEmployeet(String id) {
	this.employeeDao.delete(id);
		
	}

}

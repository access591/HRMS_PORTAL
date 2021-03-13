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
	employee.setEmpCode(employeeDao.getMaxId("EMP"));
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
	e.setEmpName(e.getEmpName());
	e.setEmpImg(e.getEmpImg());
	e.seteActive(e.geteActive());
	e.setCompCode(e.getCompCode());
	e.setDateOfBirth(e.getDateOfBirth());
	e.setBirthState(e.getBirthState());
	e.setEmpRefNo(e.getEmpRefNo());
	e.setEmpType(e.getEmpType());
	e.setLocationType(e.getLocationType());
	e.setWeeklyWorkingDay(e.getWeeklyWorkingDay());
	e.setBirthPlace(e.getBirthPlace());
	e.setDomicile(e.getDomicile());
	//Second Step Start...................................................
	e.setCardNo(e.getCardNo());
	e.setEmpDesignation(e.getEmpDesignation());
	e.setShiftCode(e.getShiftCode());
	e.setPassportNo(e.getPassportNo());
	e.setLoiDate(e.getLoiDate());
	e.setStatusDate(e.getStatusDate());
	e.setReason(e.getReason());
	e.setDepartmentCode(e.getDepartmentCode());
	e.setManagerCode(e.getManagerCode());
	e.setItPanNo(e.getItPanNo());
	e.setUanNo(e.getUanNo());
	e.setDateOfJoining(e.getDateOfJoining());
	e.setLeavingDate(e.getLeavingDate());
	//three  Step Start...................................................
	e.setEmpGender(e.getEmpGender());
	e.setEmpHeight(e.getEmpHeight());
	e.setIdentityMark(e.getIdentityMark());
	e.setBloodGroup(e.getBloodGroup());
	e.setMaritalStatus(e.getMaritalStatus());
	e.setPreDisease(e.getPreDisease());
	e.setWeddingDate(e.getWeddingDate());
	e.setNoOfChild(e.getNoOfChild());
	e.setEmpCaste(e.getEmpCaste());
	e.setEmpReligion(e.getEmpReligion());
	e.setEmpNationality(e.getEmpNationality());
	e.setCompanyCode(e.getCompanyCode());
	e.setMediclaim(e.getMediclaim());
	e.setGpa(e.getGpa());
	e.setEmpAddress1(e.getEmpAddress1());
	e.setEmpAddress2(e.getEmpAddress2());
	e.setEmpCity(e.getEmpCity());
	e.setEmpState(e.getEmpState());
	e.setPinCode(e.getPinCode());
	e.setEmpMobile(e.getEmpMobile());
	e.setEmpLocalAdd1(e.getEmpLocalAdd1());
	e.setEmpLocalAdd2(e.getEmpLocalAdd2());
	e.setEmpLocalCity(e.getEmpLocalCity());
	e.setEmpLocalState(e.getEmpLocalState());
	e.setEmpLocalPinCode(e.getEmpLocalPinCode());
	e.setEmpLocalMobileNo(e.getEmpLocalMobileNo());
	//four  Step Start...................................................
	e.setCompMobileNo(e.getCompMobileNo());
	e.setCompanyEmail(e.getCompanyEmail());
	e.setProfInst(e.getProfInst());
	e.setLocalTravelPrkm(e.getLocalTravelPrkm());
	e.setHobbies(e.getHobbies());
	e.setClubs(e.getClubs());
	e.setRegCode(e.getRegCode());
	e.setEstfWork(e.getEstfWork());
	e.setElectronicEmpCat(e.getElectronicEmpCat());
	e.setElectronicCategory(e.getElectronicCategory());
	
	this.employeeDao.saveOrUpdate(e);
		
	}

	@Override
	public void removeEmployeet(String id) {
	this.employeeDao.delete(id);
		
	}

}

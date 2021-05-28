package com.hrms.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hrms.model.AttendenceRegisterUtil;
import com.hrms.model.Department;
import com.hrms.model.Designation;
import com.hrms.model.Employee;

import com.hrms.model.MenuModule;
import com.hrms.service.DepartmentService;
import com.hrms.service.DesignationService;
import com.hrms.service.EmployeeService;
import com.hrms.service.ModuleService;

@Controller
public class AttendanceRegisterController {
	@Autowired
	private ModuleService moduleService;
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	DesignationService designationService;
	@Autowired
	DepartmentService departmentService;
	@GetMapping("/attendanceRegister")
	public String attendanceRegister(Model model, HttpSession session) {
		List<Department> listDepartment = departmentService.getAllDepartments();
		model.addAttribute("listDepartment", listDepartment);
		
		List<Employee> lrt = employeeService.getAllEmployees();
		model.addAttribute("listEmployee", lrt);
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		session.setAttribute("username", session.getAttribute("username"));
		return "attendanceRegister";
	}
	//@CrossOrigin
		@ResponseBody
	    @GetMapping("/viewEmployeeDetailsBydepartment/{id}")
	    public List<AttendenceRegisterUtil>  getLocalConvyenceById(@PathVariable(value = "id") String id,Model model,HttpSession session) {
			
			
			Department d=departmentService.findDepartmentById(id);
			List<Employee> e = employeeService.findByDepartmentCode(d.getDepartmentCode());
			  List<AttendenceRegisterUtil> listAttendenceRegisterUtil = new ArrayList<AttendenceRegisterUtil>();
			  for (int i = 0; i < e.size(); i++) 
			  {
				  String empCode = e.get(i).getEmpCode();
				  AttendenceRegisterUtil lc = new AttendenceRegisterUtil();
				  Employee employee = employeeService.findEmployeeById(empCode);
				   Department department = departmentService.findDepartmentById(employee.getDepartmentCode());
				    Designation designation = designationService.findDesignationById(employee.getDesignationCode());
				
				  lc.setEmpCode(employee.getEmpCode());
				  lc.setEmpName(employee.getEmpName());
				  lc.setEmployeePayeCode(employee.getEmployeePayeeCode());
				  lc.setDepartmentCode(department.getDepartmentCode());
				  lc.setDeptName(department.getDeptName());
				  lc.setDesgCode(designation.getDesgCode());
				  lc.setDesgName(designation.getDesgName());
				  listAttendenceRegisterUtil.add(lc);
				  
				
			  }
			  return listAttendenceRegisterUtil;
	       
	    }

	

}

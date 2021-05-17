package com.hrms.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hrms.model.Department;
import com.hrms.model.Designation;
import com.hrms.model.Employee;
import com.hrms.model.LocalConvyence;
import com.hrms.model.LocalConvyenceUtil;
import com.hrms.model.MenuModule;
import com.hrms.service.DepartmentService;
import com.hrms.service.DesignationService;
import com.hrms.service.EmployeeService;
import com.hrms.service.ModuleService;

@Controller
public class LocalConvyenceController {
	@Autowired
	private ModuleService moduleService;
	@Autowired
	EmployeeService employeeService;
	@Autowired
	DepartmentService  departmentService;
	@Autowired
	DesignationService designationService;
	@GetMapping("/localConvyence")
	public String localConvyence(Model model,HttpSession session) {

		  List<Employee> listEmployee = employeeService.getAllEmployees();
		  model.addAttribute("listEmployee", listEmployee);
		
	 
		String userCode= (String)session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
	  
	  session.setAttribute("username",session.getAttribute("username"));
	  return"localConvyence";
		}

	
	//@CrossOrigin
	@ResponseBody
    @GetMapping("/viewEmployeeDetails/{id}")
    public LocalConvyenceUtil  getLocalConvyenceById(@PathVariable(value = "id") String id) {
		Employee e = employeeService.findEmployeeById(id);
		

		
		Department d = departmentService.findDepartmentById(e.getDepartmentCode());
		Designation des=designationService.findDesignationById(e.getDesignationCode());
		LocalConvyenceUtil l=new LocalConvyenceUtil(
					e.getEmpName(),d.getDeptName(),des.getDesgName(),e.getEmployeePayeeCode()
					
				);
		
        return l;
    }

 
	
}

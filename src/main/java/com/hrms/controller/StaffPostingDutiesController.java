package com.hrms.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hrms.model.Department;
import com.hrms.model.Designation;
import com.hrms.model.Employee;

import com.hrms.model.MenuModule;

import com.hrms.model.StaffPostingDuties;
import com.hrms.service.DepartmentService;
import com.hrms.service.DesignationService;
import com.hrms.service.EmployeeService;
import com.hrms.service.ModuleService;
import com.hrms.service.StaffPostingDutiesService;
import com.hrms.util.SaffPostingDutiesUtil;



@Controller
public class StaffPostingDutiesController {
	
	@Autowired
	private ModuleService moduleService;
	@Autowired
	EmployeeService employeeService;
	@Autowired
	DepartmentService  departmentService;
	@Autowired
	DesignationService designationService;
	@Autowired
	StaffPostingDutiesService staffPostingDutiesService;
	
	
	@GetMapping("/staffPostingDuties")
	public String staffPostingDuties(Model model,HttpSession session) {
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		  List<Employee> listEmployee = employeeService.getAllEmployees();
		  model.addAttribute("listEmployee", listEmployee);
		  session.setAttribute("username",session.getAttribute("username"));
		  return"staffPostingDuties"; 
	}
	
	//@CrossOrigin
		@ResponseBody
	    @GetMapping("/viewEmployeeDetailsDuties/{id}")
	    public SaffPostingDutiesUtil  getLocalConvyenceById(@PathVariable(value = "id") String id) {
			Employee e = employeeService.findEmployeeById(id);
			Department d = departmentService.findDepartmentById(e.getDepartmentCode());
			Designation des=designationService.findDesignationById(e.getDesignationCode());
			SaffPostingDutiesUtil l=new SaffPostingDutiesUtil(
						e.getEmpName(),d.getDeptName(),des.getDesgName(),e.getEmployeePayeeCode());
			
	        return l;
	    }
		
		@PostMapping("/savestaffDuties")
		public String savestaffDuties(@ModelAttribute("saffPostingDuties") SaffPostingDutiesUtil ux, Model model,HttpSession session) {
			
			StaffPostingDuties staffduties=new StaffPostingDuties();
			Employee emp = new Employee();
			emp.setEmpCode(ux.getEmpCode());
			staffduties.setEmpCode(emp);
			staffduties.setPositionCode(ux.getPositionCode());
			staffduties.setJobDesc(ux.getJobDesc());
			
			String insertedBY = (String) session.getAttribute("USER_NAME");
			staffduties.setInsBy(insertedBY);
			this.staffPostingDutiesService.addStaffPostingDuties(staffduties);
			

			session.setAttribute("username", session.getAttribute("username"));
			return "redirect:/staffPostingDuties";
			
		
		}
	
		

}

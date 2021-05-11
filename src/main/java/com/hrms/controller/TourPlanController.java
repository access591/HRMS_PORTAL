package com.hrms.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.hrms.model.Award;
import com.hrms.model.Department;
import com.hrms.model.Designation;
import com.hrms.model.Employee;
import com.hrms.model.MenuModule;
import com.hrms.model.TourPlan;
import com.hrms.service.DepartmentService;
import com.hrms.service.DesignationService;
import com.hrms.service.EmployeeService;
import com.hrms.service.ModuleService;
import com.hrms.service.PageMappingService;
import com.hrms.service.TourPlanService;

@Controller
public class TourPlanController {
	
	int pageno = 51;
	String reqPage = "/tourPlan";
	@Autowired
	DepartmentService departmentService;
	@Autowired PageMappingService pageMappingService;
	@Autowired private ModuleService moduleService;
	@Autowired private EmployeeService employeeService;
	@Autowired private DesignationService designationService;
	@Autowired private TourPlanService tourPlanService;
	

	@GetMapping("/tourPlan")
	public String salaryGeneration(Model model, HttpSession session) {
		
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		List<Department> listDepartment = departmentService.getAllDepartments();
		model.addAttribute("listDepartment", listDepartment);
		List<Employee> listEmployee = employeeService.getAllEmployees();
		if(listEmployee != null) {
			model.addAttribute("listEmployee" , listEmployee);
		}
		List<Designation> listDesignation = designationService.getAllDesignations();
		if(listDesignation != null) {
			model.addAttribute("listDesignation", listDesignation);
		}
		session.setAttribute("username", session.getAttribute("username"));
		return pageMappingService.PageRequestMapping(reqPage, pageno);
	}  
	
	
	@PostMapping("/saveTourPlan")
	public String saveAward(@ModelAttribute("tourPlan") TourPlan tourPlan, Model model, HttpSession session) {
		String insertedBY = (String) session.getAttribute("userlogin");
		tourPlan.setInsBy(insertedBY);
		
		System.out.println("current user : "+ tourPlan.getEmpName());
		
		tourPlanService.addTourPlan(tourPlan);
		session.setAttribute("username", session.getAttribute("username"));

		return "redirect:" + pageMappingService.PageRequestMapping(reqPage, pageno);

	}

}

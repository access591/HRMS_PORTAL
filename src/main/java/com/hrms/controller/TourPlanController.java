package com.hrms.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import com.hrms.model.Department;
import com.hrms.model.Designation;
import com.hrms.model.Employee;
import com.hrms.model.MenuModule;
import com.hrms.model.TourPlan;
import com.hrms.model.TourPlanUtil;
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
	@Autowired 
	ModuleService moduleService;
	@Autowired 
	 EmployeeService employeeService;
	@Autowired
    DesignationService designationService;
	@Autowired 
	TourPlanService tourPlanService;
	

	@GetMapping("/tourPlan")
	public String tourPlan(Model model, HttpSession session) {
		
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		List<Department> listDepartment = departmentService.getAllDepartments();
		model.addAttribute("listDepartment", listDepartment);
		
		List<Employee> lrt = employeeService.getAllEmployees();
		model.addAttribute("listEmployee", lrt);
		
	
		List<Designation> listDesignation = designationService.getAllDesignations();
		model.addAttribute("listDesignation", listDesignation);
	
		session.setAttribute("username", session.getAttribute("username"));
		return "tourPlan";
	}  
	
	
	@PostMapping("/saveTourPlan")
	public String saveTourPlan(@ModelAttribute("tourPlan")TourPlanUtil u, Model model, HttpSession session,HttpServletRequest request) {
		String insertedBY = (String) session.getAttribute("uuu");
		TourPlan tourPlan=new TourPlan();
		//TourPlanApprovalDetails TourPlanApprovalDe=new TourPlanApprovalDetails();
		 
		
		Employee emp=new Employee();
		 emp.setEmpCode(u.getEmpCode());
		 
		 Department dept=new Department();
		 dept.setDepartmentCode(u.getDepartmentCode());
		 Designation des= new Designation();
		 des.setDesgCode(u.getDesgCode());
		 
		 
		 
			/* tourPlan.setTourPlanDate(u.getTourPlanDate()); */
		 tourPlan.setEmpCode(emp);
		 tourPlan.setDepartmentCode(dept);
		 tourPlan.setDesgCode(des);
		 tourPlan.setMobNumber(u.getMobNumber());
		 
		 
		 
	
			  tourPlan.setTourStartDate(u.getTourStartDate());
			  tourPlan.setTourEndDate(u.getTourEndDate());
			 
		 tourPlan.setInsBy(insertedBY);
		
		
		tourPlan.setInsBy(insertedBY);

		tourPlanService.addTourPlan(tourPlan);
		session.setAttribute("username", session.getAttribute("username"));

		return "redirect:/tourPlan";
	}

}

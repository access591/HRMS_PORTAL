package com.hrms.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hrms.model.Department;
import com.hrms.model.Employee;
import com.hrms.model.EmployeePayDetail;
import com.hrms.model.MenuModule;
import com.hrms.service.DepartmentService;
import com.hrms.service.EmployeePayDetailService;
import com.hrms.service.EmployeeService;
import com.hrms.service.ModuleService;
import com.hrms.service.PageMappingService;

@Controller
public class SalaryGenerationController {
	
	int pageno = 49;
	String reqPage = "/salaryGeneration";
	
	@Autowired PageMappingService pageMappingService;
	@Autowired private ModuleService moduleService;
	@Autowired private DepartmentService departmentService;
	@Autowired private EmployeeService employeeService;
	@Autowired private EmployeePayDetailService employeePayDetailService;
	
	
	@GetMapping("/salaryGeneration")
	public String salaryGeneration(Model model, HttpSession session) {
		
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		List<Department> listDepartment = departmentService.getAllDepartments();
		if (listDepartment != null) {
			model.addAttribute("listDepartment", listDepartment);
		}
		session.setAttribute("username", session.getAttribute("username"));

		return pageMappingService.PageRequestMapping(reqPage, pageno);
	}
	
	
	@ResponseBody
	@PostMapping("/employeebydeptcode/{deptCode}")
	public List<Employee> getAllEmployeeByDeptCode(@PathVariable("deptCode") String deptCode,HttpSession session,
													Model model) {
		
		/*
		 * System.out.println("get emp[loyee by dept code "); String userCode = (String)
		 * session.getAttribute("username"); List<MenuModule> modules =
		 * moduleService.getAllModulesList(userCode); if (modules != null) {
		 * model.addAttribute("modules", modules); } List<Department> listDepartment =
		 * departmentService.getAllDepartments(); if (listDepartment != null) {
		 * model.addAttribute("listDepartment", listDepartment); }
		 */
		
		List<Employee> listEmployee = employeeService.getEmployeeByDeptCode(deptCode);
		
		
		/*
		 * if(listEmployee != null) { model.addAttribute("listEmployee", listEmployee);
		 * } session.setAttribute("username", session.getAttribute("username"));
		 */
		
		return listEmployee;
		
	}
	
	@ResponseBody
	@PostMapping("getEmployeeByEmpCode/{empCode}")
	public EmployeePayDetail getEmployeeByEmpCode(@PathVariable("empCode") String empCode) {
		
		return employeePayDetailService.findEmployeePayDetaildById(empCode);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

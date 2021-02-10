package com.hrms.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.hrms.model.Employee;
import com.hrms.model.MenuModule;
import com.hrms.service.EmployeeService;
import com.hrms.service.ModuleService;

@Controller
public class EmployeeController {
	@Autowired
	private ModuleService moduleService;
	@Autowired
	EmployeeService employeeService;
	@GetMapping("/employeeMaster")
	public String employeeMaster(Model model,HttpSession session) {
		String userCode= (String)session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		session.setAttribute("username",session.getAttribute("username"));
			return "empInfoMaster";
		}
	
@PostMapping("/saveEmployee")
	public String employeeMasterSave(@ModelAttribute("employees") Employee employee, Model model,HttpSession session) {

	       employeeService.addEmployee(employee); 
		session.setAttribute("username",session.getAttribute("username"));

	return"redirect:/employeeMaster";

		}	

}

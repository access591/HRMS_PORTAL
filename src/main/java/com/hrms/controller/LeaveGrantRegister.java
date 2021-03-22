package com.hrms.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.hrms.model.Employee;
import com.hrms.model.LeaveGrant;
import com.hrms.model.MenuModule;
import com.hrms.service.EmployeeService;
import com.hrms.service.LeaveGrantRegisterService;
import com.hrms.service.ModuleService;

@Controller
public class LeaveGrantRegister {
	@Autowired
	private ModuleService moduleService;
	@Autowired
	EmployeeService employeeService;
	@Autowired
	LeaveGrantRegisterService leaveGrantRegisterService;
	@GetMapping("/leaveGrant")
	public String leaveGrantRegister(Model model,HttpSession session) {
		List<Employee> listEmployee = employeeService.getAllEmployees();
		model.addAttribute("listEmployee", listEmployee);
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		session.setAttribute("username",session.getAttribute("username"));
			return "leaveGrant";
		}
	@GetMapping(value = {"/editLeaveGrant/{id}"})
	  public String editdesignation(@PathVariable("id")String id,  Model model,HttpSession session)
	   { 
		  
		LeaveGrant leaveGrantEdit = leaveGrantRegisterService.findLeaveGrantById(id);
		  model.addAttribute("leaveGrantEdit", leaveGrantEdit);
	
	      session.setAttribute("username",session.getAttribute("username")); 
	         return "/editLeaveGrant"; 
	  }

}

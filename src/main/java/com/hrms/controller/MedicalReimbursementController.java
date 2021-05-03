package com.hrms.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.hrms.model.Employee;
import com.hrms.service.EmployeeService;

@Controller
public class MedicalReimbursementController {
	@Autowired
	EmployeeService employeeService;
	@GetMapping("/medicalReimbursement")
	public String medicalReimbursement(Model model, HttpSession session) {
		List<Employee> listEmployee = employeeService.getAllEmployees();
		model.addAttribute("listEmployee", listEmployee);
		return "medicalReimbursement";
	}
	
	}



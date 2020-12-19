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

import com.hrms.model.Department;
import com.hrms.service.DepartmentService;


@Controller
public class DepartmentController {

	@Autowired
	DepartmentService departmentService;
	
	
@GetMapping("/departmentMaster")
public String DepartmentMaster(Model model,HttpSession session) {
	
	List<Department> listDepartment = departmentService.getAllDepartments();
	model.addAttribute("listDepartment", listDepartment);
	session.setAttribute("username",session.getAttribute("username"));
		return "departmentMaster";
	}


@PostMapping("/saveDepartment")
	public String SaveDepartment(@ModelAttribute("department") Department department, Model model) {
		if (department.getDepartment_Code() != "") {
			departmentService.addDepartment(department);
			
			List<Department> listDepartment = departmentService.getAllDepartments();
			model.addAttribute("listDepartment", listDepartment);
		} 
		return "redirect:/departmentMaster";

	}	


	


}

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
import com.hrms.model.LeaveDetail;
import com.hrms.model.LtaRequest;
import com.hrms.model.LtaRequestUtil;
import com.hrms.model.MenuModule;
import com.hrms.service.EmployeeService;
import com.hrms.service.LtaRequestService;
import com.hrms.service.ModuleService;

@Controller

public class LtaRequestController {
	@Autowired
	private ModuleService moduleService;
	@Autowired
	LtaRequestService ltaRequest ;
	@Autowired
	EmployeeService employeeService;
	
	@GetMapping("/ltaRequest")
	public String ltaRequest(Model model, HttpSession session) {

		List<Employee> lrt = employeeService.getAllEmployees();
		model.addAttribute("listEmployee", lrt);
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		session.setAttribute("username", session.getAttribute("username"));
		 return "ltaRequest";
	}
	
	
	@PostMapping("/saveLtaRequest")
	public String saveLtaRequest(@ModelAttribute("LtaRequest") LtaRequestUtil ltaRequestUtil, Model model, HttpSession session) {
		LtaRequest ltaRequest=new LtaRequest();
		Employee emp = new Employee();
		emp.setEmpCode(ltaRequestUtil.getEmpCode());
		ltaRequest.setEmpCode(emp);
		String insertedBY = (String) session.getAttribute("USER_NAME");
		ltaRequest.setInsBy(insertedBY);
		ltaRequest.setAppDate(ltaRequestUtil.getAppDate());
		ltaRequest.setEligibilityDate(ltaRequestUtil.getEligibilityDate());
		ltaRequest.setWhenDue(ltaRequestUtil.getWhenDue());
		ltaRequest.setWhenAvailed(ltaRequestUtil.getWhenAvailed());
		
		ltaRequest.setLeaveFrom(ltaRequestUtil.getLeaveFrom());
		ltaRequest.setLeaveTo(ltaRequestUtil.getLeaveTo());	
		
		ltaRequest.setAdavance(ltaRequestUtil.getAdavance());
		ltaRequest.setRemarks(ltaRequestUtil.getRemarks());
		

		this.ltaRequest.addLtaRequest(ltaRequest);

		session.setAttribute("username", session.getAttribute("username"));

		return "redirect:/ltaRequest";

	}

}

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
import com.hrms.model.DepartmentUtiliy;
import com.hrms.model.Employee;
import com.hrms.model.LeaveDetail;
import com.hrms.model.LocalConvyence;
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
	LtaRequestService ltaRequestService ;
	@Autowired
	EmployeeService employeeService;
	
	@GetMapping("/ltaRequest")
	public String ltaRequest(Model model, HttpSession session) {

		List<Employee> lrt = employeeService.getAllEmployees();
		model.addAttribute("listEmployee", lrt);
		List<LtaRequest> listLtaRequest = ltaRequestService.getAllLTARequest();
		model.addAttribute("listLtaRequest", listLtaRequest);
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
		

		this.ltaRequestService.addLtaRequest(ltaRequest);

		session.setAttribute("username", session.getAttribute("username"));

		return "redirect:/ltaRequest";

	}
	@GetMapping(value = {"/editLtaRequest/{id}"})
	public String editLtaRequest(@PathVariable("id")String id,  Model model,HttpSession session)
	 { 
		List<Employee> em = employeeService.getAllEmployees();
		model.addAttribute("listEmployee", em);
		
		LtaRequest ltaRequestEdit =	ltaRequestService.findByIdLta(id);
		  model.addAttribute("ltaRequestEdit", ltaRequestEdit);
	   
	    return "editLtaRequest";
	}
	
	
	@PostMapping("/updateLtaRequest")
	public String updateLtaRequest(@ModelAttribute("LtaRequest") LtaRequestUtil ltaRequestUtil, Model model) {
	 
		   try {
			   LtaRequest ltaRequest=new LtaRequest();
				Employee emp = new Employee();
				emp.setEmpCode(ltaRequestUtil.getEmpCode());
				ltaRequest.setEmpCode(emp);
			   
				ltaRequest.setAppDate(ltaRequestUtil.getAppDate());
				ltaRequest.setEligibilityDate(ltaRequestUtil.getEligibilityDate());
				ltaRequest.setWhenDue(ltaRequestUtil.getWhenDue());
				ltaRequest.setWhenAvailed(ltaRequestUtil.getWhenAvailed());
				
				ltaRequest.setLeaveFrom(ltaRequestUtil.getLeaveFrom());
				ltaRequest.setLeaveTo(ltaRequestUtil.getLeaveTo());	
				
				ltaRequest.setAdavance(ltaRequestUtil.getAdavance());
				ltaRequest.setRemarks(ltaRequestUtil.getRemarks());
				ltaRequest.setLtaCode(ltaRequestUtil.getLtaCode());
				
				
				this.ltaRequestService.updateLtaRequest(ltaRequest);   
			   
			   
		} catch (Exception e) {
		
		}
		
	  	  
		   return "redirect:/ltaRequest";

	}
	
	@GetMapping(value = {"/deleteLtaRequest/{id}"})
	public String deleteLtaRequest(@PathVariable("id")String id,  Model model,HttpSession session)
	 { 
		  this.ltaRequestService.removeLTAReques(id);
		  return "redirect:/ltaRequest";

	}

}

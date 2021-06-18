package com.hrms.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.hrms.ImageUtil;
import com.hrms.model.Employee;

import com.hrms.model.LtaRequest;
import com.hrms.util.LtaRequestUtil;
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
	@InitBinder("LtaRequest")
    public void customizeBinding (WebDataBinder binder) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        dateFormatter.setLenient(false);
                binder.registerCustomEditor(Date.class, "appDate",new CustomDateEditor(dateFormatter, true));
				binder.registerCustomEditor(Date.class, "eligibilityDate", new CustomDateEditor(dateFormatter, true));
				binder.registerCustomEditor(Date.class, "whenDue",new CustomDateEditor(dateFormatter, true));
				binder.registerCustomEditor(Date.class, "whenAvailed",new CustomDateEditor(dateFormatter, true));
				binder.registerCustomEditor(Date.class, "leaveFrom",new CustomDateEditor(dateFormatter, true));
				binder.registerCustomEditor(Date.class, "leaveTo",new CustomDateEditor(dateFormatter, true));
				
       
    }
	
	
	
	@GetMapping("/ltaRequest")
	public String ltaRequest(Model model, HttpSession session) {
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		List<Employee> lrt = employeeService.getAllEmployees();
		model.addAttribute("listEmployee", lrt);
		List<LtaRequest> listLtaRequest = ltaRequestService.getAllLTARequest();
		model.addAttribute("listLtaRequest", listLtaRequest);
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		session.setAttribute("imgUtil", new ImageUtil());
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
		ltaRequest.setApprovalStatus("N");

		this.ltaRequestService.addLtaRequest(ltaRequest);

		session.setAttribute("username", session.getAttribute("username"));

		return "redirect:/ltaRequest";

	}
	@GetMapping(value = {"/editLtaRequest/{id}"})
	public String editLtaRequest(@PathVariable("id")String id,  Model model,HttpSession session)
	 { session.setAttribute("imgUtil", new ImageUtil());
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
		e.printStackTrace();
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

package com.hrms.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.hrms.model.MenuModule;
import com.hrms.service.ModuleService;

@Controller
public class LeaveGrantRegister {
	@Autowired
	private ModuleService moduleService;
	@GetMapping("/leaveGrant")
	public String leaveGrantRegister(Model model,HttpSession session) {
		
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		session.setAttribute("username",session.getAttribute("username"));
			return "leaveGrant";
		}

	@GetMapping(value = {"/searchLeaveGrant/{year}/{leaveType}"})
	  public String editdesignation(@PathVariable String year,@PathVariable String leaveType,  Model model,HttpSession session)
	   { 
		  
		  //Designation designationEdit = designationService.findDesignationById( leaveYear, barcode);
		  

	      session.setAttribute("username",session.getAttribute("username")); 
	    return "redirect:/leaveGrant";
	  }

}

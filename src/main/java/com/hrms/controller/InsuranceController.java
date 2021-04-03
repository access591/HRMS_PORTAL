package com.hrms.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.hrms.model.MenuModule;
import com.hrms.service.ModuleService;

@Controller
public class InsuranceController {
	@Autowired
	private ModuleService moduleService;
	@GetMapping("/insuranceMaster")
	public String insuranceMaster(Model model,HttpSession session) {
		
		String userCode= (String)session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		
		
		  return "insuranceMaster";
		}
}
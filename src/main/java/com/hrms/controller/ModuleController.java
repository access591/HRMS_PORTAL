package com.hrms.controller;

import java.util.List;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hrms.model.MenuModule;
import com.hrms.model.Module;
import com.hrms.service.ModuleService;

@Controller
public class ModuleController {

	@Autowired
	ModuleService moduleService;

	@GetMapping("/module")
	public String module(@ModelAttribute Module module, Model model, HttpSession session) {
		List<Module> modules1 = moduleService.getModules();
	
		model.addAttribute("modules1", modules1);
		List<MenuModule> modules = moduleService.getAllModules();
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		session.setAttribute("username", session.getAttribute("username"));
		return "module";
	}

	@PostMapping("/saveModule")
	  public String SaveModule(@ModelAttribute("module") Module module, Model model,HttpSession session,RedirectAttributes redirectAttributes) {
			
		boolean isModuleExist = moduleService.checkModuleExists(module);
		
		if (isModuleExist) {
			    redirectAttributes.addFlashAttribute("message", "Module Already exists !  ");
			    redirectAttributes.addFlashAttribute("alertClass", "alert-success");
			
			return"redirect:/module";
		}
		else 
		{
			moduleService.addModule(module); 
			
			session.setAttribute("username",session.getAttribute("username"));	
		}
		
		return"redirect:/module";
	  
	  }
	


}

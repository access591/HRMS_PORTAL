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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hrms.model.MenuModule;
import com.hrms.model.Module;
import com.hrms.service.ModuleService;

@Controller
public class ModuleController {

	@Autowired
	ModuleService moduleService;
	/**
	 * Method to get Module Result 	
	 * @param model
	 * @param session
	 * @return
	 */
	
	@GetMapping("/module")
	public String module(@ModelAttribute Module module, Model model, HttpSession session) {
		List<Module> modules1 = moduleService.getModules();
		model.addAttribute("modules1", modules1);
		String userCode= (String)session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		session.setAttribute("username", session.getAttribute("username"));
		return "module";
	}

	/**
	 * Method to Save Module 	
	 * @param model
	 * @param session
	 * @return
	 */
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
			List<Module> modules1 = moduleService.getModules();
			model.addAttribute("modules1", modules1);
			session.setAttribute("username",session.getAttribute("username"));	
			return"redirect:/module";
		}
	  }
	/**
	 * Method to Edit Module 	
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping(value = {"/editModule/{id}"})
	public String editModule(@PathVariable("id")String id,  Model model,HttpSession session)
	 { 
		Module moduleEdit = moduleService.findModuleById(id);
		  model.addAttribute("moduleEdit", moduleEdit);

	    session.setAttribute("username",session.getAttribute("username")); 
	       return "/editModule"; 
	}
	/**
	 * Method to Edit Module 	
	 * @param model
	 * @param session
	 * @return
	 */
	@PostMapping("/updateModule")
	public String updateModule(@ModelAttribute("moduleupdate") Module m, Model model) {

		  this.moduleService.updateModule(m);
	  	  
		  return "redirect:/module";
	}
	/**
	 * Method to Delete Module 	
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping(value = {"/deleteModule/{id}"})
	public String deleteModule(@PathVariable("id")String id,  Model model,HttpSession session)
	 { 
		  this.moduleService.removeModule(id);
	    session.setAttribute("username",session.getAttribute("username")); 
	    return "redirect:/module";
	}
	


}

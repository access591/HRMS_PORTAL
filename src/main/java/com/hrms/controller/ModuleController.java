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

import com.hrms.model.Module;
import com.hrms.service.ModuleService;

@Controller
public class ModuleController {

	@Autowired
	ModuleService moduleService;

	@GetMapping("/module")
	public String module(@ModelAttribute Module module, Model model,HttpSession session) {
		List<Module> modules = moduleService.getModules();
		model.addAttribute("modules", modules);
		session.setAttribute("username",session.getAttribute("username"));
		return "module";
	}

	@PostMapping("/addModule")
	public String addModule(@ModelAttribute Module module, Model model,HttpSession session) {
		moduleService.addModule(module);
		session.setAttribute("username",session.getAttribute("username"));
		return "redirect:/module";
	}

	@GetMapping(value = {"/module/{moduleId}/edit"})
	public String editModule(@PathVariable String moduleId, Model model,HttpSession session) {
		Module module = moduleService.findModuleById(moduleId);
		model.addAttribute("module", module);
		session.setAttribute("username",session.getAttribute("username"));
		return "module-edit";
	}
	
	

}

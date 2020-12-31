package com.hrms.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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

	


}

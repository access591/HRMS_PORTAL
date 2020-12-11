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

import com.hrms.model.Module;
import com.hrms.service.ModuleService;

@Controller
public class ModuleController {

	@Autowired
	ModuleService moduleService;

	@GetMapping("/module")
	public String module(@ModelAttribute Module module, Model model, HttpSession session) {
		List<Module> modules = moduleService.getModules();
		model.addAttribute("modules", modules);
		session.setAttribute("username", session.getAttribute("username"));
		return "module";
	}

	@PostMapping("/addModule")
	public String addModule(@ModelAttribute Module module, Model model, HttpSession session) {
		moduleService.addModule(module);
		session.setAttribute("username", session.getAttribute("username"));
		return "redirect:/module";
	}

	@PostMapping(value = { "/update_module" })
	public ResponseEntity<?> editModule(@RequestBody Map<String, String> map, Model model, HttpSession session) {
		moduleService.update(map);
		session.setAttribute("username", session.getAttribute("username"));
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}

	@GetMapping("/modules")
	public ResponseEntity<?> modules(Model model, HttpSession session) {
		List<Module> modules = moduleService.getModules();
		model.addAttribute("modules", modules);
		session.setAttribute("username", session.getAttribute("username"));
		return new ResponseEntity<List<Module>>(modules, HttpStatus.OK);
	}

}

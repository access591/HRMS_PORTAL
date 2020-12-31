package com.hrms.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.hrms.model.MenuModule;
import com.hrms.model.Module;
import com.hrms.model.Program;
import com.hrms.service.ModuleService;
import com.hrms.service.ProgramService;

@Controller
public class ProgramController {

	@Autowired
	ProgramService programService;
	@Autowired
	private ModuleService moduleService;

	@GetMapping("/program")
	public String program(@ModelAttribute Module module, Model model, HttpSession session) {
		List<Program> programs = programService.getPrograms();
		model.addAttribute("programs", programs);
		List<MenuModule> modules = moduleService.getAllModules();
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		session.setAttribute("username", session.getAttribute("username"));
		return "program";
	}

	@PostMapping("/addProgram")
	public String addProgram(@ModelAttribute Program program, Model model, HttpSession session) {
		programService.addProgram(program);
		session.setAttribute("username", session.getAttribute("username"));
		return "redirect:/program";
	}
}

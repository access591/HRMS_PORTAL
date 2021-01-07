package com.hrms.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hrms.model.MenuModule;
import com.hrms.model.Module;
import com.hrms.model.Program;
import com.hrms.model.SubModule;
import com.hrms.service.ModuleService;
import com.hrms.service.ProgramService;
import com.hrms.service.SubModuleService;

@Controller
public class ProgramController {
	@Autowired
	ProgramService programService;
	@Autowired
	private ModuleService moduleService;
	@Autowired
	SubModuleService subModuleService;
	@GetMapping("/program")
	public String program(@ModelAttribute Module module, Model model, HttpSession session) {
		List<Program> listpPrograms = programService.getAllPrograms();
		model.addAttribute("listpPrograms", listpPrograms);
		List<Module> modulesList = moduleService.getActiveModules();
		model.addAttribute("modulesList", modulesList);
		
		List<SubModule>subModulesList=subModuleService.getActiveSubModules();
		model.addAttribute("subModulesList", subModulesList);
		
		List<MenuModule> modules = moduleService.getAllModules();
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		session.setAttribute("username", session.getAttribute("username"));
		return "program";
	}

	@PostMapping("/saveProgram")
	public String SaveProgram1(@ModelAttribute("program")Program program, Model model,
			RedirectAttributes redirectAttributes, HttpSession session) {
		Module m=new Module();
		SubModule S=new SubModule();
		m.setModuleCode(program.getDmoduleCode());
		S.setSubModuleCode(program.getDsubMouduleCode());
		program.setpModuleCode(m);
		program.setSubModuleCode(S);
		
	
		boolean isSubModuleExist = programService.checkProgramExists(program);
		if (isSubModuleExist) {
			redirectAttributes.addFlashAttribute("message", " Sub Module Already exists !");
			redirectAttributes.addFlashAttribute("alertClass", "alert-success");
			return "redirect:/program";
		}

		else {
		
			programService.addProgram(program);
			List<Program> listpPrograms = programService.getAllPrograms();
			model.addAttribute("listpPrograms", listpPrograms);
			session.setAttribute("username", session.getAttribute("username"));
		}
		return "redirect:/program";

	}
	
	

}

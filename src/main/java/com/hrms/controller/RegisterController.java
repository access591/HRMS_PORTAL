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

import com.hrms.model.Register;
import com.hrms.model.MenuModule;
import com.hrms.service.ModuleService;
import com.hrms.service.RegisterService;
@Controller
public class RegisterController {
	@Autowired
	private ModuleService moduleService;
	@Autowired
	RegisterService registerService;

	@GetMapping("/registerMaster")
	public String registerMaster(Model model, HttpSession session) {
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}

		List<Register> listRegister = registerService.getAllRegisters();
		model.addAttribute("listRegister", listRegister);

		session.setAttribute("username", session.getAttribute("username"));

		return "registerMaster";
	}

	@PostMapping("/saveRegister")
	public String saveRegister(@ModelAttribute("register") Register register, Model model, HttpSession session) {
		
		registerService.addRegister(register);
		List<Register> listRegister = registerService.getAllRegisters();
		model.addAttribute("listRegister", listRegister);

		session.setAttribute("username", session.getAttribute("username"));

		return "redirect:/registerMaster";

	}

	@GetMapping(value = { "/deleteRegister/{id}" })
	public String deleteRegister(@PathVariable("id") String id, Model model, HttpSession session) {
		this.registerService.removeRegister(id);
		session.setAttribute("username", session.getAttribute("username"));
		return "redirect:/registerMaster";
	}

}

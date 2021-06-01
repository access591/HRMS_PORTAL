package com.hrms.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.hrms.model.MenuModule;
import com.hrms.service.ModuleService;
import com.hrms.service.TrainingRegisterDetailsService;
import com.hrms.service.TrainingRegisterService;
@Controller
public class TrainingRegisterController {
	@Autowired
	private ModuleService moduleService;
	
	@Autowired
	TrainingRegisterDetailsService trainingRegisterDetailsService;
	@Autowired
	TrainingRegisterService trainingRegisterService;
	

	@GetMapping("/trainingRegister")
	public String trainingRegister(Model model, HttpSession session) {

		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		return "trainingRegister";

	}
}

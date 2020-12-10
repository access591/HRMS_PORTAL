package com.hrms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.hrms.model.MenuModule;
import com.hrms.model.Module;
import com.hrms.service.ModuleService;

@Controller
public class DashbordController {

	@Autowired
	private ModuleService moduleService;

	@GetMapping("/getDashBoardData")
	public String getDashBoardData(Model model) {
		List<MenuModule> modules = moduleService.getAllModules();
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		return "redirect:/";

	}

}

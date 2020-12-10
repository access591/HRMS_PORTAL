package com.hrms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.hrms.model.SubModule;
import com.hrms.service.SubModuleService;

@Controller
public class SubModuleController {
	@Autowired
	SubModuleService subModuleService;

	@GetMapping("/submodulepage")
	public String SubmodulePage(Model model) {
		List<SubModule> listSubModule = subModuleService.getAllSubModules();
		model.addAttribute("listSubModule", listSubModule);

		return "subModule";
	}

	@PostMapping("/saveSubModule")
	public String SaveSubModule(@ModelAttribute("submodule") SubModule subModule, Model model) {
		if (subModule.getSubModuleCode() != "") {
			subModuleService.addSubModule(subModule);
			List<SubModule> listSubModule = subModuleService.getAllSubModules();
			model.addAttribute("listSubModule", listSubModule);
		} else {
			subModuleService.updateSubModule(subModule);

		}
		return "redirect:/submodulepage";

	}

}

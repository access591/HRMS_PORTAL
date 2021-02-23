package com.hrms.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.hrms.model.City;
import com.hrms.model.MenuModule;
import com.hrms.model.SkillCategory;
import com.hrms.service.ModuleService;
import com.hrms.service.SkillCategoryService;
@Controller
public class SkillCategoryController {
	@Autowired
	private ModuleService moduleService;
	@Autowired
	SkillCategoryService skillCategoryService;
	@GetMapping("/skillCategoryMaster")
	public String skillCategoryMaster(Model model, HttpSession session) {
		List<SkillCategory> listSkillCategory = skillCategoryService.getAllSkillCategorys();
		model.addAttribute("listSkillCategory", listSkillCategory);
		
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		session.setAttribute("username", session.getAttribute("username"));
		return "skillCategoryMaster";
	}

	@PostMapping("/saveSkillCategory")
	public String saveCity(@ModelAttribute("skillCategory") SkillCategory sc, Model model, HttpSession session) {
		String insertedBY = (String) session.getAttribute("userlogin");
		sc.setIns_by(insertedBY);
		skillCategoryService.addSkillCategory(sc);
		List<SkillCategory> listSkillCategory = skillCategoryService.getAllSkillCategorys();
		model.addAttribute("listSkillCategory", listSkillCategory);
		
		session.setAttribute("username", session.getAttribute("username"));

		return "redirect:/skillCategoryMaster";

	}	
	
	
}

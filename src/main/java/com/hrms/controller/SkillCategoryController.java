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
	
	@GetMapping(value = { "/editSkillCategory/{id}" })
	public String editSkillCategory(@PathVariable("id") String id, Model model, HttpSession session) {
		SkillCategory skillCategoryEdit = skillCategoryService.findSkillCategoryById(id);
		model.addAttribute("skillCategoryEdit", skillCategoryEdit);
		session.setAttribute("username", session.getAttribute("username"));
		return "/editSkillCategory";
	}
	
	@PostMapping("/updateSkillCategory")
	public String updateCity(@ModelAttribute("skillCategory") SkillCategory sc, Model model, HttpSession session) {
		String updatedBY = (String) session.getAttribute("userlogin");
		sc.setUpd_by(updatedBY);
		this.skillCategoryService.updateSkillCategory(sc);
		return "redirect:/skillCategoryMaster";
	}
	
	
	@GetMapping(value = { "/deleteSkillCategory/{id}" })
	public String deleteSkillCategory(@PathVariable("id") String id, Model model, HttpSession session) {
		this.skillCategoryService.removeSkillCategory(id);
		session.setAttribute("username", session.getAttribute("username"));
		return "redirect:/skillCategoryMaster";
	}

	
	
}

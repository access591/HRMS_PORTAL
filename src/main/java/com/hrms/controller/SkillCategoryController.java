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

import com.hrms.ImageUtil;
import com.hrms.model.MenuModule;
import com.hrms.model.SkillCategory;
import com.hrms.service.ModuleService;
import com.hrms.service.PageMappingService;
import com.hrms.service.SkillCategoryService;
@Controller
public class SkillCategoryController {
	int pageno = 35;
	String reqPage = "/skillCategoryMaster";
	@Autowired
	private ModuleService moduleService;
	@Autowired
	SkillCategoryService skillCategoryService;
	@Autowired
	PageMappingService pageMappingService;
	/**
	 * Request Mapping Skill Cat Master 
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping("/skillCategoryMaster")
	public String skillCategoryMaster(Model model, HttpSession session) {
		
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		List<SkillCategory> listSkillCategory = skillCategoryService.getAllSkillCategorys();
		model.addAttribute("listSkillCategory", listSkillCategory);
		
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		session.setAttribute("imgUtil", new ImageUtil());
		session.setAttribute("username", session.getAttribute("username"));
		return pageMappingService.PageRequestMapping(reqPage, pageno);
	}
/**
 * 
 * @param save Skill Category
 * @param model
 * @param session
 * @return
 */
	@PostMapping("/saveSkillCategory")
	public String saveCity(@ModelAttribute("skillCategory") SkillCategory sc, Model model, HttpSession session) {
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		String insertedBY = (String) session.getAttribute("USER_NAME");
		sc.setInsBy(insertedBY);
		skillCategoryService.addSkillCategory(sc);
		List<SkillCategory> listSkillCategory = skillCategoryService.getAllSkillCategorys();
		model.addAttribute("listSkillCategory", listSkillCategory);
		
		session.setAttribute("username", session.getAttribute("username"));

		return "redirect:/"+ pageMappingService.PageRequestMapping(reqPage, pageno);
	}	
	
	/**
	 * 
	 * @param id find and set value update page 
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping(value = { "/editSkillCategory/{id}" })
	public String editSkillCategory(@PathVariable("id") String id, Model model, HttpSession session) {
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		
		int editPageNo =36;
		String reqPageedit ="/editSkillCategory";
		
		SkillCategory skillCategoryEdit = skillCategoryService.findSkillCategoryById(id);
		model.addAttribute("skillCategoryEdit", skillCategoryEdit);
		session.setAttribute("username", session.getAttribute("username"));
		session.setAttribute("imgUtil", new ImageUtil());
		return pageMappingService.PageRequestMapping(reqPageedit, editPageNo);
	}
	/**
	 * 
	 * @param update Skill Category
	 * @param model
	 * @param session
	 * @return
	 */
	@PostMapping("/updateSkillCategory")
	public String updateCity(@ModelAttribute("skillCategory") SkillCategory sc, Model model, HttpSession session) {
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		
		String updatedBY = (String) session.getAttribute("USER_NAME");
		sc.setUpdBy(updatedBY);
		this.skillCategoryService.updateSkillCategory(sc);
		return "redirect:/"+ pageMappingService.PageRequestMapping(reqPage, pageno);
	}
	
	/**
	 * delete skill cat.
	 * @param id
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping(value = { "/deleteSkillCategory/{id}" })
	public String deleteSkillCategory(@PathVariable("id") String id, Model model, HttpSession session) {
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		
		this.skillCategoryService.removeSkillCategory(id);
		session.setAttribute("username", session.getAttribute("username"));
		return "redirect:/"+ pageMappingService.PageRequestMapping(reqPage, pageno);
	}

	
	
}

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


import com.hrms.model.Category;
import com.hrms.model.MenuModule;
import com.hrms.service.CategoryService;
import com.hrms.service.ModuleService;
import com.hrms.service.PageMappingService;


@Controller
public class CategoryController {

	int pageNo = 53;
	String reqPage = "/categoryMaster"; //categoryMaster.html
	@Autowired 
	ModuleService moduleService; 

	@Autowired
	PageMappingService pageMappingService;

	
	@Autowired private ModuleService moduleService; 
	@Autowired PageMappingService pageMappingService;

	@Autowired CategoryService categoryService;
	
	
	@GetMapping("/categoryMaster")
	public String categoryMaster(Model model,HttpSession httpSession) {
	
		String userCode = (String) httpSession.getAttribute("username");



		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		
		List<Category> listCategory = categoryService.getAllCategory();
		if(listCategory != null) {
			model.addAttribute("listCategory" ,listCategory);
		}
		
		httpSession.setAttribute("username", userCode);


		return pageMappingService.PageRequestMapping(reqPage,pageNo);
	}
	
	@PostMapping("/saveCategory")
	public String saveCategory(@ModelAttribute("category") Category category ,Model model,HttpSession session,RedirectAttributes redirectAttributes) {
		
		boolean checkCategory = categoryService.chaeckCategoryExistOrNot(category);
		if (checkCategory) {
			redirectAttributes.addFlashAttribute("message", "Category Name Already exists !  ");
			redirectAttributes.addFlashAttribute("alertClass", "alert-success");
			  return "redirect:"+pageMappingService.PageRequestMapping(reqPage,pageNo);
		} else {
		    categoryService.addCategory(category);
			return "redirect:"+pageMappingService.PageRequestMapping(reqPage,pageNo);
		}
	}


	public void saveCategory(@ModelAttribute("category") Category category ,Model model,HttpSession session) {
		
		boolean checkCategory = categoryService.chaeckCategoryExistOrNot(category);
		
		if(!checkCategory) {
			categoryService.addCategory(category);
			System.out.println("adding category..");
		}
		else {
			System.out.println("allready exist ");
		}
		List<Category> listCategory = categoryService.getAllCategory();
		if(listCategory != null) {
			model.addAttribute("listCategory" ,listCategory);
		}
		
	}
	
	
	
	
	
	

}

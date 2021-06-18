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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hrms.ImageUtil;
import com.hrms.model.Category;
import com.hrms.model.Designation;
import com.hrms.util.DesignationUtil;
import com.hrms.model.MenuModule;
import com.hrms.service.CategoryService;
import com.hrms.service.DesignationService;
import com.hrms.service.ModuleService;
import com.hrms.service.PageMappingService;

@Controller
public class DesignationController {
	int pageno=5;
	String reqPage="/designationMaster";
	@Autowired
	DesignationService designationService;
	@Autowired
	private ModuleService moduleService;
	@Autowired
	PageMappingService pageMappingService;
	@Autowired 
	CategoryService categoryService;
	/**
	 * 
	 * @param Request mapping of list Designation data
	 * @param session
	 * @return
	 */
	@GetMapping("/designationMaster")
	public String designationMaster(Model model, HttpSession session) {
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		List<Designation> listDesignation = designationService.getAllDesignations();
		model.addAttribute("listDesignation", listDesignation);
		List<Category> listCategory = categoryService.getAllCategory();
		model.addAttribute("listCategory" ,listCategory);
		String userCode = (String) session.getAttribute("username");
		
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		session.setAttribute("imgUtil", new ImageUtil());
		session.setAttribute("username", session.getAttribute("username"));
		 return pageMappingService.PageRequestMapping(reqPage,pageno);
	}

	/**
	 * 
	 * @param Add                Request mapping Designation
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@PostMapping("/saveDesignation")
	public String saveDesignation(@ModelAttribute("designation") DesignationUtil designationUtil, Model model,
			RedirectAttributes redirectAttributes) {
	    	Designation designation=new Designation();
	    	Category category=new Category();
	    	category.setCategoryCode(designationUtil.getCategoryCode());
	    	designation.setCategoryCode(category);
	    	designation.setDesgName(designationUtil.getDesgName());
	    	designation.setActive(designationUtil.getActive());
	    	
		boolean isModuleExist = designationService.checkDesignationExists(designation);

		if (isModuleExist) {
			redirectAttributes.addFlashAttribute("message", "Designation Name Already exists !  ");
			redirectAttributes.addFlashAttribute("alertClass", "alert-success");
			  return "redirect:"+pageMappingService.PageRequestMapping(reqPage,pageno);
		} else {
			designationService.addDesignation(designation);
			List<Designation> listDesignation = designationService.getAllDesignations();
			model.addAttribute("listDesignation", listDesignation);
		}
		  return "redirect:"+pageMappingService.PageRequestMapping(reqPage,pageno);

	}

	/**
	 * 
	 * @param Request Mapping Of edit Designation
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping(value = { "/editDesignation/{id}" })
	public String editdesignation(@PathVariable("id") String id, Model model, HttpSession session) {
		int editPageNo=6;
		String reqPageedit="/editDesignation";
		List<Category> listCategory = categoryService.getAllCategory();
		model.addAttribute("listCategory" ,listCategory);
		Designation designationEdit = designationService.findDesignationById(id);
		model.addAttribute("designationEdit", designationEdit);

		session.setAttribute("username", session.getAttribute("username"));
		 return pageMappingService.PageRequestMapping(reqPageedit,editPageNo);
	}

	/**
	 * 
	 * @param upadte Request Mapping Designation
	 * @param model
	 * @return
	 */
	@PostMapping("/updateDesignation")
	public String updateDesignation(@ModelAttribute("desiupdate") DesignationUtil designationUtil, Model model) {

		Designation designation=new Designation();
    	Category category=new Category();
    	category.setCategoryCode(designationUtil.getCategoryCode());
    	designation.setCategoryCode(category);
    	designation.setDesgCode(designationUtil.getDesgCode());
    	designation.setDesgName(designationUtil.getDesgName());
    	designation.setActive(designationUtil.getActive());
		
		
		this.designationService.updateDesignation(designation);

		 return "redirect:/"+pageMappingService.PageRequestMapping(reqPage,pageno);
	}

	/**
	 * 
	 * @param delete  Request mapping Designation
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping(value = { "/deleteDesignation/{id}" })
	public String deletedesignation(@PathVariable("id") String id, Model model, HttpSession session) {

		this.designationService.removeDesignation(id);

		session.setAttribute("username", session.getAttribute("username"));
		 return "redirect:/"+pageMappingService.PageRequestMapping(reqPage,pageno);
	}

}

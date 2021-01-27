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

import com.hrms.model.Designation;
import com.hrms.model.MenuModule;
import com.hrms.service.DesignationService;
import com.hrms.service.ModuleService;

@Controller
public class DesignationController {

	@Autowired
	DesignationService designationService;
	@Autowired
	private ModuleService moduleService;

	/**
	 * 
	 * @param Request mapping of list Designation data
	 * @param session
	 * @return
	 */
	@GetMapping("/designationMaster")
	public String DesignationMaster(Model model, HttpSession session) {

		List<Designation> listDesignation = designationService.getAllDesignations();
		model.addAttribute("listDesignation", listDesignation);
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		session.setAttribute("username", session.getAttribute("username"));
		return "designationMaster";
	}

	/**
	 * 
	 * @param Add                Request mapping Designation
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@PostMapping("/saveDesignation")
	public String SaveDesignation(@ModelAttribute("designation") Designation designation, Model model,
			RedirectAttributes redirectAttributes) {
		boolean isModuleExist = designationService.checkDesignationExists(designation);

		if (isModuleExist) {
			redirectAttributes.addFlashAttribute("message", "Designation Code Already exists !  ");
			redirectAttributes.addFlashAttribute("alertClass", "alert-success");
			return "redirect:/designationMaster";
		} else {
			designationService.addDesignation(designation);
			List<Designation> listDesignation = designationService.getAllDesignations();
			model.addAttribute("listDesignation", listDesignation);
		}
		return "redirect:/designationMaster";

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

		Designation designationEdit = designationService.findDesignationById(id);
		model.addAttribute("designationEdit", designationEdit);

		session.setAttribute("username", session.getAttribute("username"));
		return "/editDesignation";
	}

	/**
	 * 
	 * @param upadte Request Mapping Designation
	 * @param model
	 * @return
	 */
	@PostMapping("/updateDesignation")
	public String updateDesignation(@ModelAttribute("desiupdate") Designation d, Model model) {

		this.designationService.updateDesignation(d);

		return "redirect:/designationMaster";
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
		return "redirect:/designationMaster";
	}

}
